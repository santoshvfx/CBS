//$Id: CPSOS.java,v 1.20 2009/05/09 02:07:59 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 01/2009      Sheilla Lirio         	Creation.
//# 03/03/3009	 Sheilla Lirio			Fixed DR123206
//# 03/04/2009	 Sheilla Lirio			Fixed DR122813
//# 03/10/2009	 Sheilla Lirio			DR 123545: CPSOS is not being called when UNN1 FID is not present from SM BIS.
//# 04/06/2009	 Sheilla Lirio		    CR 25554: RM to update call to CPSOS
//# 04/27/2009	 Sheilla Lirio		    DR 128284: RM is returning a system failure 00521 when a WTN with a null value existing on a ADSL loop with cls fomat only.
//# 05/06/2009   Julius Sembrano        Added REMOTE_CALL and REMOTE_RETURN

package com.sbc.eia.bis.BusinessInterface.rm.CPSOS;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;


import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationRequestHelper;
import com.sbc.eia.bis.BusinessInterface.rm.PublishValidateFacilityNotification.PublishValidateFacilityNotificationResponseHelper;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;


/**
 * Class:		CPSOS
 * Description:	This class will do the following:
 * 				- populate details for the XML request
 * 				- send the Dsl Account Lookup request to CPSOS
 * 				- receive the Dsl Account Lookup response from CPSOS
 * 				- analyze response and return WTN if successful or Error ID if not
 * @author 		sl7683
 *
 */

public class CPSOS
{
	
	private URL url = null;
	private HttpsURLConnection conn = null;
	private Utility aUtility = null;
    private Hashtable aProperties = null;
    
	private String aWTN =  "";
	private String aState = "";
	public String aCPSOSUrl = "";
	public boolean aCpsosSuccessInd = false;
	public String aHostName = "CPSOS";
	
	PublishValidateFacilityNotificationRequestHelper aPvfnReqHelper = new PublishValidateFacilityNotificationRequestHelper();
	PublishValidateFacilityNotificationResponseHelper aPvfnRespHelper = new PublishValidateFacilityNotificationResponseHelper();
	DslAccountLookupRequest aReqLookupRequest = null;
	DslAccountLookupResponse aReqLookupResponse = null;
	
	
		
	/**
	 * @param utility
	 * @param properties
	 * @param wtn
	 * @param state
	 * @param pvfnReqHelper
	 */
	public CPSOS (Utility utility, 
			Hashtable properties, 
			String wtn, 
			String state,
			PublishValidateFacilityNotificationRequestHelper pvfnReqHelper,
			PublishValidateFacilityNotificationResponseHelper pvfnRespHelper)
	{
		aProperties = properties;
        aUtility = utility;
        aWTN = wtn;
        aState = state;
        aPvfnReqHelper = pvfnReqHelper;
        aPvfnRespHelper = pvfnRespHelper;
    }
	
	/**
	 * Execute CPSOS connection
	 * @throws DataNotFound 
	 * @throws ObjectNotFound 
	 * @throws NotImplemented 
	 * @throws AccessDenied 
	 * @throws BusinessViolation 
	 * @throws SystemFailure 
	 * @throws InvalidData 
	 * 
	 * @throws Exception
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 
	 * @Author SL7683
	 * 
	 */
	public DslAccountLookupResponse executeConnection () 
	{
		String aMethodName = "CPSOS: executeConnection()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        	
        String userId = null;
        String password = null;
        String trackingNumber = null;  
        SimpleDateFormat aDate = new SimpleDateFormat();
         
        try 
        {
            //populate Dsl Account Lookup Request information
	        aCPSOSUrl = (String) aProperties.get("CPSOS_URL");
	        userId = (String) aProperties.get("CPSOS_USERID");
	    	password = new Encryption().decodePassword((String) aProperties.get("OSS_PUBLIC_KEY"), (String) aProperties.get("CPSOS_PASSWORD"));
	    	
	    	aUtility.log(LogEventId.INFO_LEVEL_1, "CPSOS URL : " + aCPSOSUrl);
	    	
	    	BisContext aContext = new BisContext();
	    	aContext = aPvfnReqHelper.getContext();
	    	BisContextManager theBisContextManager = new BisContextManager(aContext);
	    	String corrId = theBisContextManager.getLoggingInformationString();
	    	int endIndex = corrId.indexOf('|');
			int beginIndex = endIndex - 20;
			trackingNumber = corrId.substring(beginIndex, endIndex);
	    	
			//check the format of the input WTN
			aWTN = formatWtn(aWTN);
			aReqLookupRequest = new DslAccountLookupRequest(aUtility, aProperties);
			
			aReqLookupRequest.setNpa(aWTN.substring(0, 3));
			aReqLookupRequest.setNxx(aWTN.substring(3, 6));
			aReqLookupRequest.setLine(aWTN.substring(6, 10));
			aReqLookupRequest.setState(aState);
			
	    	aReqLookupRequest.setUserId(userId);
	    	aReqLookupRequest.setPassword(password);
	    	aReqLookupRequest.setPartnerId((String) aProperties.get("CPSOS_Partner_Id"));
	    	aReqLookupRequest.setReqOrgId((String) aProperties.get("CPSOS_Org_Id"));
	    	aReqLookupRequest.setReqAffiliateId((String) aProperties.get("CPSOS_Affiliate_Id"));
	    	aReqLookupRequest.setServerName((String) aProperties.get("CPSOS_Server_Name"));
	    	aReqLookupRequest.setInterfaceVesion((String) aProperties.get("CPSOS_Version"));
	    	aReqLookupRequest.setTrackingNumber(trackingNumber);
	        aReqLookupRequest.setTimestamp(aDate);
	        aReqLookupRequest.setEndUserAuth((Integer.parseInt((String)(aProperties.get("CPSOS_End_User_Authentication")))));
	        
	        //create CPSOS request in an XML format
	        CPSOSRequestHelper reqHelper = new CPSOSRequestHelper(aUtility, aProperties, aReqLookupRequest);
	        String xmlReq = reqHelper.createCPSOSRequest(aReqLookupRequest);
	        
	        //perform CPSOS operation: send request and receive response
			aUtility.log(
					LogEventId.REMOTE_CALL,
					aCPSOSUrl,
					"CPSOS " + (String) aProperties.get("CPSOS_Version"),
					"CPSOS " + (String) aProperties.get("CPSOS_Version"),
					"DSLAccountLookup");
			
	        String xmlResp = performCPSOSOperation(xmlReq);
	        
			aUtility.log(
					LogEventId.REMOTE_RETURN,
					aCPSOSUrl,
					"CPSOS " + (String) aProperties.get("CPSOS_Version"),
					"CPSOS " + (String) aProperties.get("CPSOS_Version"),
					"DSLAccountLookup");
	        
	        if (xmlResp != null)
	        {
	        	//convert response from String to XML doc
		        CPSOSResponseHelper respHelper = new CPSOSResponseHelper(aUtility, aProperties);
		        aReqLookupResponse = respHelper.convertStringToDoc(xmlResp);
		        
		        //check if CPSOS response is Successful or Error
		        if (aReqLookupResponse.getRespType().equalsIgnoreCase(CPSOSConstants.Xml_AccountLookupResp_Element))
		        {
		        	aCpsosSuccessInd = true;
		        	StringBuffer cpsosWTN = new StringBuffer();
		        	cpsosWTN.append(aReqLookupResponse.getNpa());
		        	cpsosWTN.append(aReqLookupResponse.getNxx());
		        	cpsosWTN.append(aReqLookupResponse.getLine());
		        	
		        	aUtility.log(LogEventId.INFO_LEVEL_1, "CPSOS WTN : " + cpsosWTN.toString());
		        }
		        
		        if (aReqLookupResponse.getRespType().equalsIgnoreCase(CPSOSConstants.Xml_ErrorResponse_Element))
		        {
		        	aCpsosSuccessInd = false;
		        	String errorId = aReqLookupResponse.getErrorId();
		        	aUtility.log(LogEventId.INFO_LEVEL_1, "Error ID : " + errorId);
		        	aUtility.log(LogEventId.INFO_LEVEL_1, "Error Description : " + aReqLookupResponse.getErrorText());
		        	
		        }
	        }
	        
        }
        catch (NullPointerException e) 
        {
            // log: exception message            
        	StringBuffer nullLogMessage = new StringBuffer();
            nullLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, nullLogMessage.toString());
        }
        catch (Exception e) 
        {
            // log: exception message            
        	StringBuffer nullLogMessage = new StringBuffer();
            nullLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, nullLogMessage.toString());
        }
        finally 
        {
        	aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        }
        
        return aReqLookupResponse;
    }
	
	/**
	 * Formats the input WTN.
	 * 
	 * @param wtn
	 * @return
	 * 
	 * @Author SL7683
	 * 
	 */
	private String formatWtn (String wtn)
	{
		String aMethodName = "CPSOS: formatWtn()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        StringBuffer wtnStrBuf = new StringBuffer();
		wtnStrBuf.append(wtn);
		
		try
		{
			if (wtnStrBuf.length() == PublishValidateFacilityNotificationConstants.WTN_Format3_LENGTH)
			{
				aWTN = wtnStrBuf.toString();
			} 
			else if (wtnStrBuf.length() == PublishValidateFacilityNotificationConstants.WTN_Format2_LENGTH)
			{
				if ((wtnStrBuf.charAt(3) == '-' || Character.isWhitespace(wtnStrBuf.charAt(3))) 
						&& (wtnStrBuf.charAt(7) == '-' || Character.isWhitespace(wtnStrBuf.charAt(7))))
				{
					wtnStrBuf.deleteCharAt(3);
					wtnStrBuf.deleteCharAt(6);
					aWTN = wtnStrBuf.toString();
				}
			}
			aUtility.log(LogEventId.INFO_LEVEL_1, "WTN: " + aWTN);
		}
		catch (NullPointerException e) 
        {
            // log: exception message            
            StringBuffer nullLogMessage = new StringBuffer();
            nullLogMessage.append("> Exception: [ ").append(e.getMessage()).append(" ]");
            aUtility.log(LogEventId.ERROR, nullLogMessage.toString());
        }
		finally
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		return aWTN;
	}
	
	/**
	 * Sends the Xml request to CPSOS.
	 * 
	 * @param xmlStr
	 * @throws IOException
	 * 
	 * @author sl7683
	 * @throws DataNotFound 
	 * @throws ObjectNotFound 
	 * @throws NotImplemented 
	 * @throws AccessDenied 
	 * @throws BusinessViolation 
	 * @throws SystemFailure 
	 * @throws InvalidData 
	 * 
	 */
	private void sendRequest(String xmlStr) 
		throws 
			IOException, 
			InvalidData, 
			SystemFailure, 
			BusinessViolation, 
			AccessDenied, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound 
	{
		String aMethodName = "CPSOS: sendRequest()";
		String userId = (String) aProperties.get("CPSOS_USERID");
    	String password = new Encryption().decodePassword((String) aProperties.get("OSS_PUBLIC_KEY"), (String) aProperties.get("CPSOS_PASSWORD"));
    	
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        aUtility.log(LogEventId.INFO_LEVEL_1, "XML Request: " + xmlStr.replaceAll(userId, " ").replaceAll(password, " "));
		OutputStream out = null;
		
		try 
		{
			// Send Request.
			out = conn.getOutputStream();
			out.write(xmlStr.getBytes());
		} 
		catch (IOException e) 
        {
            // log: exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		finally 
		{
			if (out != null) 
			{
				out.close(); 
			}
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
	}
	
	/**
	 * Retrieves the Xml response returned by CPSOS.
	 * 
	 * @return
	 * @throws IOException
	 * 
	 * @author sl7683
	 * @throws DataNotFound 
	 * @throws ObjectNotFound 
	 * @throws NotImplemented 
	 * @throws AccessDenied 
	 * @throws BusinessViolation 
	 * @throws SystemFailure 
	 * @throws InvalidData 
	 * 
	 */
	private String receiveResponse() 
		throws 
			IOException, 
			InvalidData, 
			SystemFailure, 
			BusinessViolation, 
			AccessDenied, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound 
	{
		String aMethodName = "CPSOS: receiveResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		
        BufferedReader in = null;
		StringBuffer xmlRespStringBuf = null;
		String userId = (String) aProperties.get("CPSOS_USERID");
    	
				
		try 
		{
			//Invoke the servlet -- retreive response
			in = new BufferedReader(
					new InputStreamReader(this.conn.getInputStream()));
			xmlRespStringBuf = new StringBuffer();
			int c = in.read();

			// while there is data to read
			while (c != -1) 
			{
				// append the last char read
				xmlRespStringBuf.append((char) c);
				// read another char
				c = in.read();
			}
		} 
		catch (IOException e) 
        {
            // log: exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		finally 
		{
			//always close connection.
			if (in != null) 
			{
				in.close(); 
			}
			aUtility.log(LogEventId.INFO_LEVEL_1, "XML Response: " + xmlRespStringBuf.toString().replaceAll(userId, " "));
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return xmlRespStringBuf.toString();
	}
	
	/**
	 * Performs all the CPSOS processing.
	 * 
	 * @param xmlRequestStr
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * 
	 * @author sl7683
	 * 
	 */
	public String performCPSOSOperation(String xmlRequestStr)
		throws 
			MalformedURLException, 
			IOException, 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			ObjectNotFound, 
			DataNotFound, 
			NotImplemented 
	{
		String aMethodName = "CPSOS: performCPSOSOperation()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		
		String xmlResponse = null;
		String keyStoreName = (String) aProperties.get("CPSOS_KeyStore_Name");
		String keyStorePassword = new Encryption().decodePassword((String) 
				aProperties.get("OSS_PUBLIC_KEY"), (String) aProperties.get("CPSOS_KeyStore_Password"));
		
		try 
		{
			aUtility.log(LogEventId.INFO_LEVEL_1, "CPSOS KeyStore Name: " + keyStoreName);
			
			URL aUrl = com.sbc.bccs.utilities.PropertiesFileLoader.getAsURL(keyStoreName, aUtility);
			
			String keyStorePath = aUrl.toExternalForm();               
			
			//set keyStore and trustStore for security
            System.setProperty("javax.net.ssl.trustStore", keyStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword",	keyStorePassword);
            
            System.setProperty("javax.net.ssl.keyStore", keyStorePath);
            System.setProperty("javax.net.ssl.keyStorePassword",keyStorePassword);

            //create connection to CPSOS Url
			url = new URL(aCPSOSUrl);
			
			conn = (HttpsURLConnection) this.url.openConnection();
			
			//set connection properties
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-type", "text/xml");
			
			if (!conn.getURL().getContent().equals(null))
			{
				//send request and receive response via HTTPS
				sendRequest(xmlRequestStr); 
				
				xmlResponse = receiveResponse();
			}
					
		} 
		catch (MalformedURLException e) 
        {
	        // log exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		catch (NullPointerException e) 
        {
			//log exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		catch (IOException e) 
        {
            // log exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getClass().toString() + e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		catch (Exception e) 
        {
            // log exception message            
			ExceptionBuilderResult aExceptionBuilderResult = ExceptionBuilder.parseException(aPvfnReqHelper.getContext(),
            		(String) aProperties.get("EXCEPTION_BUILDER_CPSOS_RULE_FILE"),
                    null,
                    e.getMessage(),
                    e.getMessage(),
                    false,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    e,
                    aUtility,            
                    aHostName,               
                    null,               
                    null);
			aPvfnRespHelper.handleException(aExceptionBuilderResult.getException(),aPvfnReqHelper);
        }
		finally 
		{
			conn.disconnect();
			
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return xmlResponse;
	}
	
}
