// $Id: AddFiberServiceTNPort2.java,v 1.2 2009/07/01 23:11:16 js7440 Exp $
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
//# 06/05/2009    Crisper Rosete                Creation.
//# 06/30/2009	  Julius Sembrano				Parse data from request XML

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
import com.sbc.eia.idl.rm.AddFiberServiceTNPort2Return;
import com.sbc.eia.idl.rm.RmFacadePackage._addFiberServiceTNPort2RequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._addFiberServiceTNPort2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._addFiberServiceTNPort2ResponseBISMsg;
import com.sbc.eia.idl.rm.bishelpers.AddFiberServiceTNPort2ReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.AddFiberServiceTNPortTelephoneNumberRequest;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.vicunalite.api.MMarshalObject;

/**
 * ActivateFiberServiceTNPort test driver.
 * 
 * @author: Crisper Rosete
 */
public class AddFiberServiceTNPort2 extends TestRMCaseBase{

	private AddFiberServiceTNPort2Return aResult = null;
	private String aState = null; 
	private StringOpt aSOACServiceOrderNumber = null; 
	private StringOpt aSOACServiceOrderCorrectionSuffix = null; 
	private StringOpt aOrderNumber = null; 
	private StringOpt aOldServiceProviderId = null; 
	private StringOpt aNewServiceProviderId = null; 
	private EiaDate aOrderDueDate = null; 
	private AddFiberServiceTNPortTelephoneNumberRequest[] aTelephoneNumbers = null;

	/**
	 * constructor: AddFiberServiceTNPort2
	 */
	public AddFiberServiceTNPort2(){
		super();
		setMyMethodName("AddFiberServiceTNPort2");
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
		DataNotFound {
		TestClient.log.printLog("EJB testing Not Implemented.");
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
			RemoteException,
			ObjectNotFound,
			DataNotFound 
	{
		displayInput();
		
		aResult = ((Rm)remote).addFiberServiceTNPort2(
			(aContext != null) ? aContext : TestClient.myBisContext,
			this.aState, 
			this.aSOACServiceOrderNumber, 
			this.aSOACServiceOrderCorrectionSuffix, 
			this.aOrderNumber, 
			this.aOldServiceProviderId, 
			this.aNewServiceProviderId, 
			this.aOrderDueDate, 
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
		TestClient.log.printLog("Vicuña proxy support not implemented");
	}
	
    /**
     * method: init(String)
     */
	protected void init(String paramList) 
	{
		// parse the bar delimited test case string
		StringTokenizer st =
			new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
		try {
			while (st.hasMoreElements()) {
				String tag = TestClient.nextToken(st);
				if (tag.equals("XML")) {
					isXMLTestData = true;
					String xmlFileName = TestClient.nextToken(st);
					TestClient.log.printLog(
						"XML Test Data File :" + xmlFileName);
					buildIDLObjectsFromXML(xmlFileName);
					return;
				}
			}
		} catch (Throwable t) {
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
		{
			//Read XML test data file
			requestXML = readFile(xmlFileName, true);

			//Convert XML to IDL object
			MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);

			//build request IDL - java objects from MMarshalObject
			_addFiberServiceTNPort2RequestBISMsg aRequestMsg =
				(_addFiberServiceTNPort2RequestBISMsg) aRequest;
			aContext = aRequestMsg.value.aContext;
			aState = aRequestMsg.value.aState;
			aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
			aSOACServiceOrderCorrectionSuffix =	aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
			aOrderNumber = aRequestMsg.value.aOrderNumber;
			aOldServiceProviderId = aRequestMsg.value.aOldServiceProviderId;
			aNewServiceProviderId = aRequestMsg.value.aNewServiceProviderId;
			aOrderDueDate = aRequestMsg.value.aOrderDueDate;
			aTelephoneNumbers = aRequestMsg.value.aTelephoneNumberRequest;
		}	
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
	private String createMessage() {
		return null;
	}	
	
	protected void init(String paramList, String propertiesFile) {}
	
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
		if (isXMLTestData) {
			_addFiberServiceTNPort2Response res =
				new _addFiberServiceTNPort2Response();
			String responseXML = null;

			if (aResult != null) {
				res.aAddFiberServiceTNPort2Return(aResult);
				responseXML =
					convertMMarshalObjectToXML(
						new _addFiberServiceTNPort2ResponseBISMsg(res));
			} else {
				if (responseError != null) {
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
							new _addFiberServiceTNPort2ResponseBISMsg(res));
				}
			}

			if (responseXML != null)
				TestClient.log.printLog("OUTPUT DATA: \n" + responseXML);
		}
		//Bar delimitted test data
		else if (aResult != null) {
			TestClient.log.printLog(
				"OUTPUT DATA: "
					+ (
						new AddFiberServiceTNPort2ReturnBisHelper(aResult))
						.toString());
		}
	}
	
    /**
     * method: initSoapMessage(String, Properties)
     */
	protected void initSoapHttpMessage(String ParamList, Properties props)	{
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
	protected void displayProxyResult() {}
}
