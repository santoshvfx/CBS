// $Id: SendFacilityAssignmentOrder4.java,v 1.1 2010/03/11 08:17:17 eb231v Exp $
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
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date         | Author                     | Notes
//# ----------------------------------------------------------------------------
//# 03/09/2010    Eugene Boi Bautista           Creation.

package com.sbc.eia.bis.facades.testing.rm;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJBObject;

import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder4Return;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder4Response;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder4ResponseMsg;
import com.sbc.eia.idl.rm.bishelpers.SendFacilityAssignmentOrder4ReturnBisHelper;

public class SendFacilityAssignmentOrder4 extends TestRMCaseBase
{

	private SendFacilityAssignmentOrder4Return result = null;

	/**
	 * constructor: SendFacilityAssignmentOrder4
	 */
	public SendFacilityAssignmentOrder4(){
		super();
		setMyMethodName("SendFacilityAssignmentOrder4");
	}
	
	
	protected void displayProxyResult() {}

	protected void displayResult()
	 {    
    	if (isXMLTestData) 
    	{
    		_sendFacilityAssignmentOrder4Response res =  new _sendFacilityAssignmentOrder4Response();
    		String responseXML = null;
        
    		if (result != null) 
    		{       
    			res.aSendFacilityAssignmentOrder4Return(result);      
    			responseXML = convertMMarshalObjectToXML(new _sendFacilityAssignmentOrder4ResponseMsg(res));
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
                
    				responseXML = convertMMarshalObjectToXML(new _sendFacilityAssignmentOrder4ResponseMsg(res));
    			}
    		}
    		if(responseXML != null)
    		{
    			TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
    		}
	   	}
	    else if (result != null) 
	    {
	        TestClient.log.printLog("OUTPUT DATA: " + (new SendFacilityAssignmentOrder4ReturnBisHelper(result)).toString());
	    }
    }
	
	/**
	 * method: displayInput()
	 */
	protected void displayInput() {}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute()
	 */
	protected void execute()
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound {
		TestClient.log.printLog("EJB testing Not Implemented.");
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute(javax.ejb.EJBObject)
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
		DataNotFound {
	result = ((Rm)remote).sendFacilityAssignmentOrder4(
			(aContext != null) ? aContext : TestClient.myBisContext, 
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#execute(java.util.Hashtable)
	 */
	protected void execute(Hashtable props)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound {		
	TestClient.log.printLog("Vicuna proxy support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String)
	 */
	protected void init(String paramList) {}

	/**
	 * builds IDL - java objects from XML 
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception {}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.lang.String)
	 */
	protected void init(java.lang.String paramList, java.lang.String propertiesFile) {}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.util.Hashtable)
	 */
	protected void init(java.lang.String paramList, Hashtable props) {
		TestClient.log.printLog("Vicuna proxy support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initMessage(java.lang.String, java.util.Hashtable)
	 */
	protected void initMessage(java.lang.String paramList, Hashtable props) 
	{
		TestClient.log.printLog("MDB support not implemented");
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapMessage(String paramList, Properties aProps) 
	{
		TestClient.log.printLog("SOAFI proxy support not implemented");
	}

	/**
	 * method creates XML from Java objects
	 * @return
	 */
	private String createMessage() 
	{
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapHttpMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapHttpMessage(String ParamList, Properties props) 
	{
		TestClient.log.printLog("SOAP/HTTP not implemented");
	}

}
