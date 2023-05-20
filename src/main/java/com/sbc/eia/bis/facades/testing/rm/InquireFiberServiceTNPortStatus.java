// $Id: InquireFiberServiceTNPortStatus.java,v 1.2 2009/07/01 23:11:16 js7440 Exp $
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
//# 06/05/2009     Ma. Victoria Escaro          Creation.
//# 06/30/2009     Julius Sembrano				Parse data from request XML             

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
import com.sbc.eia.idl.rm.InquireFiberServiceTNPortStatusReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._inquireFiberServiceTNPortStatusRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._inquireFiberServiceTNPortStatusResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._inquireFiberServiceTNPortStatusResponseBISMsg;
import com.sbc.eia.idl.rm.bishelpers.InquireFiberServiceTNPortStatusReturnBisHelper;
import com.sbc.eia.idl.types.Time;
import com.sbc.vicunalite.api.MMarshalObject;

/**
 * ActivateFiberServiceTNPort test driver.
 * 
 * @author: Ma. Victoria Escaro 
 */
public class InquireFiberServiceTNPortStatus extends TestRMCaseBase
{

	private InquireFiberServiceTNPortStatusReturn aResult = null;
	private String aSOACServiceOrderNumber = null; 
	private String aOldServiceProviderId = null; 
	private String aNewServiceProviderId = null; 
	private Time aOrderDueDateTime = null; 
	private Time aAppointmentDateTime = null; 
	private String[] aTelephoneNumbers = null;
	
	/**
	 * constructor: InquireFiberServiceTNPortStatus
	 */
	public InquireFiberServiceTNPortStatus()
	{
		super();
		setMyMethodName("InquireFiberServiceTNPortStatus");
	}
	
    /**
     * method: execute()
     */
	protected void execute() 
		throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound 
	{
		TestClient.log.printLog("EJB Testing Not Implemented.");
	}

    /**
     * method: execute(EJBObject)
     */
	protected void execute(javax.ejb.EJBObject remote) 
		throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound, 
			RemoteException 
	{
		aResult = ((Rm)remote).inquireFiberServiceTNPortStatus(
				(aContext != null) ? aContext : TestClient.myBisContext, 
				this.aSOACServiceOrderNumber, 
				this.aOldServiceProviderId, 
				this.aNewServiceProviderId, 
				this.aOrderDueDateTime, 
				this.aAppointmentDateTime, 
				this.aTelephoneNumbers);
	}

    /**
     * method: execute(Hashtable)
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
	protected void execute(Hashtable propsFile) 
		throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound 
	{
		TestClient.log.printLog("Vicuña proxy support not implemented");
	}

    /**
     * method: init(String)
     */
	protected void init(java.lang.String paramList) 
	{
		// parse the bar delimited test case string
		StringTokenizer st =
			new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
		try 
		{
			while (st.hasMoreElements()) 
			{
				String tag = TestClient.nextToken(st);
				if (tag.equals("XML")) 
				{
					isXMLTestData = true;
					String xmlFileName = TestClient.nextToken(st);
					TestClient.log.printLog(
						"XML Test Data File :" + xmlFileName);
					buildIDLObjectsFromXML(xmlFileName);
					return;
				}
			}
		} 
		catch (Throwable t) 
		{
			//Notify user of error
			System.out.println("Verify test data, failure parsing test data");
			t.printStackTrace();
		}
	}
	
	/**
	 * builds IDL - java objects from XML 
	 * @param xmlFileName
	 * @throws Exception
	 */
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception 
	{
		//Read XML test data file
		requestXML = readFile(xmlFileName, true);

		//Convert XML to IDL object
		MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);

		//build request IDL - java objects from MMarshalObject
		_inquireFiberServiceTNPortStatusRequestBISMsg aRequestMsg =
			(_inquireFiberServiceTNPortStatusRequestBISMsg) aRequest;
		
		aContext = aRequestMsg.value.aContext;
		aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
		aOldServiceProviderId = aRequestMsg.value.aOldServiceProviderId;
		aNewServiceProviderId = aRequestMsg.value.aNewServiceProviderId;
		aOrderDueDateTime = aRequestMsg.value.aOrderDueDateTime;
		aAppointmentDateTime = aRequestMsg.value.aAppointmentDateTime;
		aTelephoneNumbers = aRequestMsg.value.aTelephoneNumbers;
	}
	
    /**
     * method: initSoapMessage(String, Properties)
     */
	protected void initSoapMessage(String paramList, Properties aProps) 
	{
		// parse the argument from the test case.
		this.init(paramList);

		if (!isXMLTestData)
			this.displayInput();

		sendMessage(
			aProps,
			null,
		//RmFacadeSOABuilder._activateFiberServiceTNPort,
		createMessage());
	}

	/**
	 * method creates XML from Java objects
	 * @return
	 */
	private String createMessage(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.lang.String)
	 */
	protected void init(java.lang.String paramList, java.lang.String propertiesFile) 
	{
		
	}

	/**
	 * method: displayInput()
	 */
	protected void displayInput() 
	{
		if (isXMLTestData) {
			TestClient.log.printLog("INPUT DATA: \n" + requestXML);
			return;
		}
	}
	
    /**
     * method: displayResult
     */
	protected void displayResult() 
	{
		if (isXMLTestData) 
		{
			_inquireFiberServiceTNPortStatusResponse res =
				new _inquireFiberServiceTNPortStatusResponse();
			String responseXML = null;

			if (aResult != null) 
			{
				res.aInquireFiberServiceTNPortStatusReturn(aResult);
				responseXML =
					convertMMarshalObjectToXML(
						new _inquireFiberServiceTNPortStatusResponseBISMsg(res));
			} else {
				if (responseError != null) 
				{
					if (responseError instanceof SystemFailure)
						res.aSystemFailure((SystemFailure) responseError);
					if (responseError instanceof InvalidData)
						res.aInvalidData((InvalidData) responseError);
					if (responseError instanceof AccessDenied)
						res.aAccessDenied((AccessDenied) responseError);
					if (responseError instanceof BusinessViolation)
						res.aBusinessViolation(
							(BusinessViolation) responseError);
					if (responseError instanceof NotImplemented)
						res.aNotImplemented((NotImplemented) responseError);
					if (responseError instanceof ObjectNotFound)
						res.aObjectNotFound((ObjectNotFound) responseError);
					if (responseError instanceof DataNotFound)
						res.aDataNotFound((DataNotFound) responseError);

					responseXML =
						convertMMarshalObjectToXML(
							new _inquireFiberServiceTNPortStatusResponseBISMsg(res));
				}
			}

			if (responseXML != null)
				TestClient.log.printLog("OUTPUT DATA: \n" + responseXML);
		}
		//Bar delimitted test data
		else if (aResult != null) 
		{
			TestClient.log.printLog(
				"OUTPUT DATA: "
					+ (
						new InquireFiberServiceTNPortStatusReturnBisHelper(aResult))
						.toString());
		}
	}
	
    /**
     * method: initSoapMessage(String, Properties)
     */
	protected void initSoapHttpMessage(String ParamList, Properties props)	
	{
		TestClient.log.printLog("SOAP/HTTP not implemented");
	}
	

    /**
     * method: initMessage(String)
     */
	protected void initMessage(java.lang.String paramList, Hashtable props) 
	{
		TestClient.log.printLog("MDB support not implemented");
	}
	
    /**
     * method: initMessage(String, hashtable)
     */
	protected void init(java.lang.String paramList, Hashtable props) 
	{
		TestClient.log.printLog("Vicuna proxy support not implemented");
	}
	
    /**
     * method: displayProxyResult()
     */
	protected void displayProxyResult() 
	{
		
	}
}
