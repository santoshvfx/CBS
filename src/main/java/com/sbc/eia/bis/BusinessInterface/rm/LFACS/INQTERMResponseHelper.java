//$Id: INQTERMResponseHelper.java,v 1.3 2009/04/24 03:31:30 js7440 Exp $
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
//# 01/16/2009   Julius Sembrano       Creation.
//# 03/12/2009   Julius Sembrano       DR123847: INQTERM call for the new VRAD functionality seems to not be working correctly.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotificationConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : INQTERMResponseHelper
 * Description: Helper used for handling the response of the INQTERM contract of FACSRCAccess.  
 */
public class INQTERMResponseHelper implements INQTERMConstants
{
	private Utility aUtility = null;
	private Hashtable aProperties = null;
	private String aETYP = null;
	private String aERRMSG = null;
	private String aErrorCode = null;
	private String aErrorDescription = null;
	private ArrayList aSYSTPValues = null;

	/**
     * Constructor: INQTERMResponseHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Julius Sembrano
     */
	public INQTERMResponseHelper(Utility utility, Hashtable properties)
	{
		aUtility = utility;
		aProperties = properties;
		aSYSTPValues = new ArrayList();
	}
	
    /**
     * Parses the response information.
     * 
     * @param String aResponse
     * 
     * @author Julius Sembrano
     */
	public void parseResponse(String aResponse)
		throws Exception
	{
        String aMethodName = "INQTERMResponseHelper: parseResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);  
        
        // create instance of Document Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try
        {
        	DocumentBuilder builder = factory.newDocumentBuilder();        	
        	Document document = builder.parse(new InputSource(new StringReader(aResponse)));
        	document.getDocumentElement().normalize();
        	
        	// parse status info for errors
        	NodeList statusInfoList = document.getElementsByTagName(STATUS_INFO);
        	int statusInfoListSize = statusInfoList.getLength();
        	
        	for(int a = 0; a < statusInfoListSize; a ++)
        	{
        		Node statusInfoNode = statusInfoList.item(a);
        		Element statusInfoElement = (Element) statusInfoNode;
        		NodeList errorsList = statusInfoElement.getElementsByTagName(ERRORS);
        		int errorsListSize = errorsList.getLength();
        		
        		for(int b = 0; b < errorsListSize; b ++)
        		{
        			Node errorsNode = errorsList.item(b);
        			Element errorsElement = (Element) errorsNode;
        			NodeList errorCodeList = errorsElement.getElementsByTagName(ERROR_CODE);
        			int errorCodeListSize = errorCodeList.getLength();
        			
        			if(errorCodeListSize != 0)
        			{
        				Element errorCodeElement = (Element) errorCodeList.item(0);
    					NodeList errorCodeValue = errorCodeElement.getChildNodes();
    					aErrorCode = ((Node)errorCodeValue.item(0)).getNodeValue().trim();
        			}
        			
        			Node errorsNode2 = errorsList.item(b);
        			Element errorsElement2 = (Element) errorsNode2;
        			NodeList errorDescriptionList = errorsElement2.getElementsByTagName(ERROR_DESCRIPTION);
        			int errorDescriptionListSize = errorDescriptionList.getLength();
        			
        			if(errorDescriptionListSize != 0)
        			{
        				Element errorDescriptionElement = (Element) errorDescriptionList.item(0);
    					NodeList errorDescriptionValue = errorDescriptionElement.getChildNodes();
    					aErrorDescription = ((Node)errorDescriptionValue.item(0)).getNodeValue().trim();
        			}        			
        		}// close for errors loop      		
        	}// close for status info loop
        	
        	// parse results
        	NodeList resultsList = document.getElementsByTagName(RESULTS);
        	int resultsListSize = resultsList.getLength();
        	
        	for(int i = 0; i < resultsListSize; i ++)
        	{
        		Node resultsNode = resultsList.item(i);
        		Element resultsElement = (Element) resultsNode;
        		NodeList INQTERMList = resultsElement.getElementsByTagName(INQTERM);
        		int INQTERMListSize = INQTERMList.getLength();
        		
        		for(int j = 0; j < INQTERMListSize; j ++)
        		{
        			Node INQTERMNode = INQTERMList.item(j);
        			Element INQTERMElement = (Element) INQTERMNode;
        			NodeList RSPList = INQTERMElement.getElementsByTagName(RSP);
        			int RSPListSize = RSPList.getLength();        			
        			// check if optional RSP is present
        			if(RSPListSize != 0)
        			{
	        			for(int k = 0; k < RSPListSize; k ++)
	        			{
	            			Node RSPNode = RSPList.item(k);
	            			Element RSPElement = (Element) RSPNode;
	            			NodeList TERMList = RSPElement.getElementsByTagName(TERM);
	            			int TERMListSize = TERMList.getLength();
	            			// check if optioanl TERM is present
	            			if(TERMListSize != 0)
	            			{
		            			for(int l = 0; l < TERMListSize; l ++)
		            			{
		            				Node TERMNode = TERMList.item(l);
		            				Element TERMElement = (Element) TERMNode;
		            				NodeList INCNTList = TERMElement.getElementsByTagName(INCNT);
		            				int INCNTListSize = INCNTList.getLength();
		            				// check if optional INCNT is present
		            				if(INCNTListSize != 0)
		            				{
			            				for(int m = 0; m < INCNTListSize; m ++)
			            				{
			            					Node INCNTNode = INCNTList.item(m);
			            					Element INCNTElement = (Element) INCNTNode;
			            					NodeList SYSTPList = INCNTElement.getElementsByTagName(SYSTP);
			            					int SYSTPListSize = SYSTPList.getLength();
			            					// check if optional SYSTP is present
			            					if(SYSTPListSize != 0)
			            					{
				            					Element SYSTPElement = (Element) SYSTPList.item(0);
				            					NodeList SYSTPValue = SYSTPElement.getChildNodes();
				            					String aSYSTP = ((Node)SYSTPValue.item(0)).getNodeValue().trim();
				            					if(aSYSTP.equalsIgnoreCase(INQTERMConstants.ALLOWED_SYSTP1)
				            							|| aSYSTP.equalsIgnoreCase(INQTERMConstants.ALLOWED_SYSTP2))
				            					{
				            						aSYSTPValues.add(aSYSTP);
				            						return;
				            					}
				            									            					
				            				}// close if SYSTP loop
			            				}// close for INCNT loop
		            				}// close if INCNT loop
		            			}//close for TERM loop
	            			}// close if TERM loop
	        			}// close for RSP loop
	        		}
        			else
        			{ 
            			NodeList ERRRSPList = INQTERMElement.getElementsByTagName(ERRRSP);
            			int ERRRSPListSize = ERRRSPList.getLength();
            			// check if optional ERRRSP is present
            			if(ERRRSPListSize != 0)
            			{
    	        			for(int k = 0; k < ERRRSPListSize; k ++)
    	        			{
    	        				Node ERRRSPNode = ERRRSPList.item(k);
    	        				Element ERRRSPElement = (Element) ERRRSPNode;
    	        				NodeList ETYPList = ERRRSPElement.getElementsByTagName(ETYP);
    	        				int ETYPListSize = ETYPList.getLength();
    	        				// check if optional ETYP is present
    	        				if(ETYPListSize != 0)
    	        				{
    		        				Element ETYPElement = (Element) ETYPList.item(0);
    		        				NodeList ETYPValue = ETYPElement.getChildNodes();
    		        				aETYP = ((Node)ETYPValue.item(0)).getNodeValue().trim(); 
    	        				}//close if ETYP Loop
    	        				
    	        				Node ERRRSPNode2 = ERRRSPList.item(k);
    	        				Element ERRRSPElement2 = (Element) ERRRSPNode2;
    	        				NodeList ERRMSGList = ERRRSPElement2.getElementsByTagName(ERRMSG);
    	        				int ERRMSGListSize = ERRMSGList.getLength();
    	        				// check if optional ERRMSG is present
    	        				if(ERRMSGListSize != 0)
    	        				{
    		        				Element ERRMSGElement = (Element) ERRMSGList.item(0);
    		        				NodeList ERRMSGValue = ERRMSGElement.getChildNodes();
    		        				aERRMSG = ((Node)ERRMSGValue.item(0)).getNodeValue().trim();
    	        				}//close if ERRMSG loop
    	        			}// close for ERRRSP loop
            			}// close if ERRRSP loop
            		}// close else RSP loop
        		}// close for INQTERM loop
        	}// close for results loop
        	
        }
        catch (Exception e) 
        {
        	aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in INQTERMResponseHelper: parseResponse() " + e.getMessage());
        	

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            
            if (aERRMSG != null)
            {
                eLogMessage.append(aERRMSG);
            }
            else
            {
                eLogMessage.append(e.getMessage());
            }
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            
            if (aETYP != null)
            {
                eLogMessage.append(aETYP);
            }
            else
            {
                eLogMessage.append(e.getMessage());
            }
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());
            
            // throw: Exception
            throw new Exception(eLogMessage.toString());
        }
		finally
		{
			 aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);	
		}
	}

	/**
     * Get the value of SYSTP.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public ArrayList getSYSTPValues() 
	{
		return this.aSYSTPValues;
	}
	
	/**
     * Get the value of ERRMSG.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
    public String getERRMSG() {
		return this.aERRMSG;
	}

    /**
     * Set the value of ERRMSG.
     * 
     * @return 
     * 
     * @author Julius Sembrano
     */
    public void setERRMSG(String aerrmsg) {
		this.aERRMSG = aerrmsg;
	}

	/**
     * Get the value of ETYP.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public String getETYP() {
		return this.aETYP;
	}

    /**
     * Set the value of ETYP.
     * 
     * @return 
     * 
     * @author Julius Sembrano
     */
	public void setETYP(String aetyp) 
	{
		this.aETYP = aetyp;
	}

	/**
     * Get the value of errorCode.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public String getErrorCode() 
	{
		return aErrorCode;
	}

    /**
     * Set the value of errorCode.
     * 
     * @return 
     * 
     * @author Julius Sembrano
     */
	public void setErrorCode(String errorCode) 
	{
		aErrorCode = errorCode;
	}

	/**
     * Get the value of errorDescription.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public String getErrorDescription() 
	{
		return aErrorDescription;
	}

    /**
     * Set the value of errorDescription.
     * 
     * @return 
     * 
     * @author Julius Sembrano
     */
	public void setErrorDescription(String errorDescription) 
	{
		aErrorDescription = errorDescription;
	}

}
