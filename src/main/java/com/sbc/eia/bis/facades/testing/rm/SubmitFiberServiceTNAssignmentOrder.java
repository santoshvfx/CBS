//$Id: SubmitFiberServiceTNAssignmentOrder.java,v 1.2 2009/07/01 23:11:16 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date         | Author                     | Notes
//# ----------------------------------------------------------------------------
//# 05/27/2009     Eugene Boi Bautista          Creation.
//# 07/01/2009	   Eugene Boi Bautista		    Parse data from XML request.

package com.sbc.eia.bis.facades.testing.rm;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.SubmitFiberServiceTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._submitFiberServiceTNAssignmentOrderRequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._submitFiberServiceTNAssignmentOrderResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._submitFiberServiceTNAssignmentOrderResponseMsg;
import com.sbc.eia.idl.rm.bishelpers.SubmitFiberServiceTNAssignmentOrderReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.vicunalite.api.MMarshalObject;

/**
 * SubmitFiberServiceTNAssignmentOrder test driver.
 * 
 * @author: Eugene Boi Bautista
 */
public class SubmitFiberServiceTNAssignmentOrder extends TestRMCaseBase
{
    private SubmitFiberServiceTNAssignmentOrderReturn result = null;
    private String aSOACServiceOrderNumber = null;
    private String aSOACServiceOrderCorrectionSuffix = null;
    private String aOrderNumber = null;
    private String aOrderActionType = null;
    private StringOpt aSubActionType = null;
    private BooleanOpt aCompletionIndicator = null;
    private Location aServiceLocation = null;
    private EiaDate aOriginalDueDate = null;
    private EiaDateOpt aSubsequentDueDate = null;
    private EiaDate aApplicationDate = null;
    private BooleanOpt aResendFlag = null;
    private StringOpt aWireCenter = null;
    private StringOpt aRateCenter = null;
    private TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs = null;
    private ObjectPropertySeqOpt aProperties = null;
    
    /**
     * Constructor for SubmitFiberServiceTNAssignmentOrder
     */
    public SubmitFiberServiceTNAssignmentOrder()
    {
        super();
		setMyMethodName("SubmitFiberServiceTNAssignmentOrder");
    }

    /**
     * Method displayResult.
     */
    protected void displayResult()
    {    
    	if (isXMLTestData) 
    	{
    		_submitFiberServiceTNAssignmentOrderResponse res =  new _submitFiberServiceTNAssignmentOrderResponse();
    		String responseXML = null;
        
    		if (result != null) 
    		{       
    			res.aSubmitFiberServiceTNAssignmentOrderReturn(result);      
    			responseXML = convertMMarshalObjectToXML(new _submitFiberServiceTNAssignmentOrderResponseMsg(res));
    		}
    		else  
    		{
    			if (responseError != null) 
    			{
    				if(responseError instanceof SystemFailure)
    					res.aSystemFailure( (SystemFailure)responseError );
    				if(responseError instanceof InvalidData)
    					res.aInvalidData( (InvalidData)responseError );
    				if(responseError instanceof AccessDenied)
    					res.aAccessDenied( (AccessDenied)responseError );
    				if(responseError instanceof BusinessViolation)
    					res.aBusinessViolation( (BusinessViolation)responseError );
    				if(responseError instanceof NotImplemented)
    					res.aNotImplemented( (NotImplemented)responseError );
    				if(responseError instanceof ObjectNotFound)
    					res.aObjectNotFound( (ObjectNotFound)responseError );
    				if(responseError instanceof DataNotFound)
    					res.aDataNotFound( (DataNotFound)responseError );
                
    				responseXML = convertMMarshalObjectToXML(new _submitFiberServiceTNAssignmentOrderResponseMsg(res));
    			}
    		}
    		if(responseXML != null)
    		{
    			TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
    		}
	   	}
	    else if (result != null) 
	    {
	        TestClient.log.printLog("OUTPUT DATA: " + (new SubmitFiberServiceTNAssignmentOrderReturnBisHelper(result)).toString());
	    }
    }

    /**
     * Method displayProxyResult.
     */
    protected void displayProxyResult() 
    {
    	
    }

    /**
     * Method execute.
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound The exception description.
     * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
     */
    protected void execute()
        throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
    {
        TestClient.log.printLog("EJB testing Not Implemented.");
    }

    /**
     * Method execute.
     * @param remote
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws RemoteException
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    protected void execute(javax.ejb.EJBObject remote)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            RemoteException,
            ObjectNotFound,
            DataNotFound
    {
    	displayInput();
        result = ((Rm) remote).submitFiberServiceTNAssignmentOrder(
        		 (aContext != null) ? aContext : TestClient.myBisContext, 
        			       this.aSOACServiceOrderNumber,
        			       this.aSOACServiceOrderCorrectionSuffix,
        			       this.aOrderNumber,
        			       this.aOrderActionType,
        			       this.aSubActionType,
        			       this.aCompletionIndicator,
        			       this.aServiceLocation,
        			       this.aOriginalDueDate,
        			       this.aSubsequentDueDate,
        			       this.aApplicationDate,
        			       this.aResendFlag,
        			       this.aWireCenter,
        			       this.aRateCenter,
        			       this.aTelephoneNumberOrderPairs,
        			       this.aProperties);
    }

    /**
     * Method execute.
     * @param props
     */
    protected void execute(Hashtable props)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound 
	{
    	TestClient.log.printLog("Vicunia proxy support not implemented");
    }

    /**
     * Method init.
     * @param paramList
     */
    protected void init(java.lang.String paramList)
    {
        // parse the argument from the test case
        StringTokenizer st = new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
        try
        {
            
                String tag = TestClient.nextToken(st);
                System.out.println("Processing Tag: [" + tag + "]");
                if (tag.equals("XML")) 
                {
                    isXMLTestData = true;
                    String xmlFileName = TestClient.nextToken( st ) ;
                    TestClient.log.printLog("XML Test Data File :" + xmlFileName);
                    buildIDLObjectsFromXML( xmlFileName );
                    return;
                }
        }       
        catch (Throwable t)
        {
            // Notify user of error
            System.out.println("Verify test data/format, failure parsing test case string");
        } 
    }

    /**
     * method: buildIDLObjectsFromXML(String)
     */
    public void buildIDLObjectsFromXML(String xmlFileName) throws Exception 
    {
    	//Read XML test data file
    	requestXML = readFile(xmlFileName, true);
    
    	//Convert XML to IDL object
    	MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);
    
    	//build request IDL objects from MMarshalObject
    	_submitFiberServiceTNAssignmentOrderRequestMsg aRequestMsg = (_submitFiberServiceTNAssignmentOrderRequestMsg)aRequest;

    	aContext = aRequestMsg.value.aContext;
    	aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
    	aSOACServiceOrderCorrectionSuffix = aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
    	aOrderNumber = aRequestMsg.value.aOrderNumber;
    	aOrderActionType = aRequestMsg.value.aOrderActionType;
    	aSubActionType = aRequestMsg.value.aSubActionType;
    	aCompletionIndicator = aRequestMsg.value.aCompletionIndicator;
    	aServiceLocation = aRequestMsg.value.aServiceLocation;
    	aOriginalDueDate = aRequestMsg.value.aOriginalDueDate;
    	aSubsequentDueDate = aRequestMsg.value.aSubsequentDueDate;
    	aApplicationDate = aRequestMsg.value.aApplicationDate;
    	aResendFlag = aRequestMsg.value.aResendFlag;
    	aWireCenter = aRequestMsg.value.aWireCenter;
    	aRateCenter = aRequestMsg.value.aRateCenter;
    	aTelephoneNumberOrderPairs = aRequestMsg.value.aTelephoneNumberOrderPairs;
    	aProperties = aRequestMsg.value.aProperties;
    }

    /**
     * Method init.
     * @param paramList
     * @param propertiesFile
     */
    protected void init(java.lang.String paramList, java.lang.String propertiesFile) 
    {
    	
    }
    
    /**
     * Method init.
     * @param paramList
     * @param props
     */
    protected void init(java.lang.String paramList, Hashtable props) 
    {
    	
    }

    /**
     * Method initMessage.
     * @param paramList
     */
    protected void initMessage(java.lang.String paramList, Hashtable props)
    {
    	TestClient.log.printLog("MDB support not implemented");
    }

    /**
     * Method initSoapMessage.
     */
    protected void initSoapMessage(String paramList, Properties props)
    {
    	TestClient.log.printLog("SOAFI proxy support not implemented");
    }

    /**
     * Method initSoapHttpMessage.
     */
    protected void initSoapHttpMessage(String ParamList, Properties props)
    {
        TestClient.log.printLog("SOAP/HTTP not implemented");
    }
    
    /**
     * Method displayInput.
     */
    protected void displayInput()
    {
        if (isXMLTestData) 
        {
        	TestClient.log.printLog( "INPUT DATA: \n" +  requestXML );
            return;
        }
    }
}