// $Id: SendActivateTNPortingSubscriptionMsg.java,v 1.1 2006/05/30 15:17:56 jp2854 Exp $
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
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//# 05/30/2006  Jyothi Jasti  Creation.

package com.sbc.eia.bis.facades.testing.rm;

/**
 * @author jp2854
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgResponseBISMsg;
import com.sbc.eia.idl.rm.bishelpers.SendActivateTNPortingSubscriptionMsgReturnBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringSeqBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

public class SendActivateTNPortingSubscriptionMsg extends TestRMCaseBase {

	private SendActivateTNPortingSubscriptionMsgReturn aResult = null;
	private String aSOACServiceOrderNumber = null;
	private String aSOACServiceOrderCorrectionSuffix = null;
	private String aLocalServiceProviderId = null;
	private String[] aTelephoneNumbers = null;
	private Logger aLogger = null;
	private Hashtable localProperties = null;

	/**
	 * constructor: SendActivateTNPortingSubscriptionMsg
	 */
	public SendActivateTNPortingSubscriptionMsg() {
		super();
		setMyMethodName("SendActivateTNPortingSubscriptionMsg");

	}

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

		displayInput();

		com.sbc.eia.bis.facades.rm.transactions.SendActivateTNPortingSubscriptionMsg.SendActivateTNPortingSubscriptionMsg aTrans =
			new com.sbc.eia.bis.facades.rm.transactions.SendActivateTNPortingSubscriptionMsg.SendActivateTNPortingSubscriptionMsg(localProperties);

		aResult =
			aTrans.execute(
				((aContext != null) ? aContext : TestClient.myBisContext),
				this.aSOACServiceOrderNumber,
				this.aSOACServiceOrderCorrectionSuffix,
				this.aLocalServiceProviderId,
				this.aTelephoneNumbers,
				aLogger);

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

		displayInput();

		aResult =
			((Rm) remote).sendActivateTNPortingSubscriptionMsg(
				((aContext != null) ? aContext : TestClient.myBisContext),
				this.aSOACServiceOrderNumber,
				this.aSOACServiceOrderCorrectionSuffix,
				this.aLocalServiceProviderId,
				this.aTelephoneNumbers);
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
		
		TestClient.log.printLog("Vicunia proxy support not implemented");

	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String)
	 */
	protected void init(java.lang.String paramList) {

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
				if (tag.equals("BisContext")) {
					aContext = objHelpers.getBisContext(st);
				}
				if (tag.equals("SOACServiceOrderNumber")) {
					aSOACServiceOrderNumber = TestClient.nextToken(st);
				}
				if (tag.equals("SOACServiceOrderCorrectionSuffix")) {
					aSOACServiceOrderCorrectionSuffix =
						TestClient.nextToken(st);
				}
				if (tag.equals("LocalServiceProviderId")) {
					aLocalServiceProviderId = TestClient.nextToken(st);
				}
				if (tag.equals("TelephoneNumbers")) {
					aTelephoneNumbers = objHelpers.getStrings(st);
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
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception {
		//Read XML test data file
		requestXML = readFile(xmlFileName, true);

		//Convert XML to IDL object
		MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);

		//build request IDL - java objects from MMarshalObject
		_sendActivateTNPortingSubscriptionMsgRequestBISMsg aRequestMsg =
			(_sendActivateTNPortingSubscriptionMsgRequestBISMsg) aRequest;
		aContext = aRequestMsg.value.aContext;
		aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
		aSOACServiceOrderCorrectionSuffix =
			aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
		aLocalServiceProviderId = aRequestMsg.value.aLocalServiceProviderId;
		aTelephoneNumbers = aRequestMsg.value.aTelephoneNumbers;

	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapMessage(String paramList, Properties aProps) {
		// parse the argument from the test case.
		this.init(paramList);

		if (!isXMLTestData)
			this.displayInput();

		sendMessage(
			aProps,
			null,
		//RmFacadeSOABuilder._sendActivateTNPortingSubscriptionMsg,
		createMessage());
	}

	/**
	 * method creates XML from Java objects
	 * @return
	 */
	private String createMessage() {
		String xml = null;
		try {
			_sendActivateTNPortingSubscriptionMsgRequest request =
				new _sendActivateTNPortingSubscriptionMsgRequest(
					this.aContext,
					this.aSOACServiceOrderNumber,
					this.aSOACServiceOrderCorrectionSuffix,
					this.aLocalServiceProviderId,
					this.aTelephoneNumbers);

			_sendActivateTNPortingSubscriptionMsgRequestBISMsg requestMsg =
				new _sendActivateTNPortingSubscriptionMsgRequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode java objects into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS - RmFacadeSOABuilder._sendActivateTNPortingSubscriptionMsg
			xml = addSOAPTagstoXML(xml, "com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn com.sbc.eia.idl.rm.RmFacadePackage.sendActivateTNPortingSubscriptionMsg(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String[])");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return xml;
	}
	

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.lang.String)
	 */
	protected void init(
		java.lang.String paramList,
		java.lang.String propertiesFile) {

		// parse the argument from the test case
		System.out.println("propertiesFile = " + propertiesFile);

		File file;
		FileInputStream fis = null;

		try {
			file = new File(propertiesFile.trim());
			fis = new FileInputStream(file);
		} catch (Exception fe) {
			System.out.println("Properties File Not Found: " + fe.getMessage());
		}

		//get properties file
		Properties p = new Properties();
		try {
			p.load(fis);
		} catch (IOException ie) {
			System.out.println("IOException reading properties file.");
		}

		// Set up properties for specific Bis
		this.localProperties = (Hashtable) p;
		init(paramList);

	}

	
	/**
	 * method: displayInput()
	 */
	protected void displayInput() {

		if (isXMLTestData) {
			TestClient.log.printLog("INPUT DATA: \n" + requestXML);
			return;
		}

		TestClient.log.printLog(
			"INPUT DATA:aContext<"
				+ (new BisContextBisHelper((aContext != null)
					? aContext
					: TestClient.myBisContext))
					.toString()
				+ ">");
		if (aSOACServiceOrderNumber != null)
			TestClient.log.printLog(
				"INPUT DATA:SOACServiceOrderNumber<"
					+ aSOACServiceOrderNumber
					+ ">");
		if (aSOACServiceOrderCorrectionSuffix != null)
			TestClient.log.printLog(
				"INPUT DATA:SOACServiceOrderCorrectionSuffix<"
					+ aSOACServiceOrderCorrectionSuffix
					+ ">");
		if (aLocalServiceProviderId != null)
			TestClient.log.printLog(
				"INPUT DATA:LocalServiceProviderId<"
					+ aLocalServiceProviderId
					+ ">");
		TestClient.log.printLog("INPUT DATA:TelephoneNumbers=<"
							+ (new StringSeqBisHelper(aTelephoneNumbers)).toString()
							+ ">");

	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#displayResult()
	 */
	protected void displayResult() {
		//XML test data
		if (isXMLTestData) {
			_sendActivateTNPortingSubscriptionMsgResponse res =
				new _sendActivateTNPortingSubscriptionMsgResponse();
			String responseXML = null;

			if (aResult != null) {
				res.aSendActivateTNPortingSubscriptionMsgReturn(aResult);
				responseXML =
					convertMMarshalObjectToXML(
						new _sendActivateTNPortingSubscriptionMsgResponseBISMsg(res));
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
							new _sendActivateTNPortingSubscriptionMsgResponseBISMsg(res));
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
						new SendActivateTNPortingSubscriptionMsgReturnBisHelper(aResult))
						.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapHttpMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapHttpMessage(String ParamList, Properties props) {
		TestClient.log.printLog("SOAP/HTTP not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initMessage(java.lang.String, java.util.Hashtable)
	 */
	protected void initMessage(java.lang.String paramList, Hashtable props) {
		TestClient.log.printLog("MDB support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#init(java.lang.String, java.util.Hashtable)
	 */
	protected void init(java.lang.String paramList, Hashtable props) {
		TestClient.log.printLog("Vicunia proxy support not implemented");
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#displayProxyResult()
	 */
	protected void displayProxyResult() {
	}

}
