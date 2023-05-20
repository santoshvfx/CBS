// $Id: PublishRGActivation.java,v 1.12.6.1 2007/10/18 21:46:32 ra0331 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date         | Author              | Notes
//# ----------------------------------------------------------------------------
//# 05/09/2005   kk8467                  Creation.
//#	06/06/2005	 mk3198	                 Modifications for soap via ldap.
//#	07/18/2005	 kk8467	                 Added Logger to execute() method.
//#	11/09/2005	 kk8467	                 IDL bundle 33_0 changes.
//# 04/17/2006	 kk8467					 Added CustomerTransportId in displayInput().

package com.sbc.eia.bis.facades.testing.rm;

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
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponseBISMsg;
import com.sbc.eia.idl.rm.bishelpers.PublishRGActivationReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.bishelpers.DSLAMTransportOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.ResidentialGatewayBisHelper;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.Time;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.TimeBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

/**
 * 
 * PublishRGActivation test driver
 * @author kk8467
 *
 */

public class PublishRGActivation extends TestRMCaseBase {

	private PublishRGActivationReturn result = null;
	private StringOpt aCustomerTransportId = null;
	private CompositeObjectKey aBillingAccountNumber = null;
	private DSLAMTransportOpt aDSLAM = null;
	private ResidentialGateway aRG = null;
	private Time aActivationTime = null;
	private OrderAction aOrderAction = null;
	private ObjectPropertySeqOpt aProperties = null;
	private Hashtable props = null;
	private Logger aLogger = null;

	/**
	 * Constructor for PublishRGActivation.
	 */
	public PublishRGActivation() {
		super();
	}

	protected void displayResult() {
		//XML test data 
		if ( isXMLTestData ) { 
			_publishRGActivationResponse res =  new _publishRGActivationResponse(); 
			String responseXML = null; 
		
			if (result != null) {					
				res.aPublishRGActivationReturn( result );		
				responseXML = convertMMarshalObjectToXML( new _publishRGActivationResponseBISMsg(res) );
			}
			else {
				if(responseError != null) {
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
				
					responseXML = convertMMarshalObjectToXML(new _publishRGActivationResponseBISMsg(res));
				}
			}
			if(responseXML != null)
				TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
		}
		//Bar delimitted test data
		else if( result != null )
		{
			TestClient.log.printLog(
				"OUTPUT DATA: "
					+ (new PublishRGActivationReturnBisHelper(result))
						.toString());
		}
	}

	/**
	 * Insert the method's description here.
	 */
	protected void displayProxyResult() {

	}

	/**
	 * Insert the method's description here.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound The exception description.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
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
		com
			.sbc
			.eia
			.bis
			.facades
			.rm
			.transactions
			.PublishRGActivation
			.PublishRGActivation publishRGA =
			new com
				.sbc
				.eia
				.bis
				.facades
				.rm
				.transactions
				.PublishRGActivation
				.PublishRGActivation(props);

		result =
			publishRGA.execute(
				(aContext != null) ? aContext : TestClient.myBisContext,
				this.aCustomerTransportId,
				this.aBillingAccountNumber,
				this.aDSLAM,
				this.aRG,
				this.aActivationTime,
				this.aOrderAction,
				this.aProperties,
				aLogger);
	}

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
		result =
			((Rm) remote).publishRGActivation(
				(aContext != null) ? aContext : TestClient.myBisContext,
				this.aCustomerTransportId,
				this.aBillingAccountNumber,
				this.aDSLAM,
				this.aRG,
				this.aActivationTime,
				this.aOrderAction,
				this.aProperties);
	}
	/*  PROXY CHANGE
	 *
	 * Insert the method's description here.
	 *
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

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (05/09/05)
	 */
	protected void init(java.lang.String paramList) {
		// parse the argument from the test case
		StringTokenizer st =
			new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
		try {
			while (st.hasMoreElements()) {
				String tag = TestClient.nextToken(st);
				
//              System.out.println("Next Tag => " + tag);				
				if( tag.equals("XML")) {
					isXMLTestData = true;
					String xmlFileName = TestClient.nextToken( st ) ;
					TestClient.log.printLog("XML Test Data File :" + xmlFileName);
					buildIDLObjectsFromXML( xmlFileName );
					return;
				}
				
				if (tag.equals("BisContext"))
					aContext = objHelpers.getBisContext(st);
				if (tag.equals("CustomerTransportId"))
					aCustomerTransportId = objHelpers.getStringOpt(st);
				if (tag.equals("BillingAccountNumber"))
					aBillingAccountNumber = objHelpers.getCompositeObjectKey(st);
					
				if (tag.equals("ResidentialGateway")) 
				{
					aRG = objHelpers.getResidentialGateway(st);	
				}				
				if (tag.equals("ActivationTime"))
				{
					aActivationTime = objHelpers.getTime(st);
				}
				if (tag.equals("OrderAction"))
				{
					aOrderAction = objHelpers.getOrderAction(st);
				}
				if (tag.equals("Properties")) 
				{
					aProperties = objHelpers.getObjectPropertySeqOpt(st);
				}
				if (tag.equals("DSLAMTransportOpt"))
				{
					aDSLAM = objHelpers.getDSLAMTransportOpt(st);					
				}					
			}
		} catch (Throwable t) { 
			//Notify user of error
			System.out.println("Verify test data, failure parsing test data");
		}
	}

	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception
	{
		//Read XML test data file
		requestXML = readFile(xmlFileName, true);
		
		//Convert XML to IDL object
		MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);
		
		//build request IDL objects from MMarshalObject
		_publishRGActivationRequestBISMsg aRequestMsg = (_publishRGActivationRequestBISMsg)aRequest;
		
		aContext = aRequestMsg.value.aContext ;
		aCustomerTransportId = aRequestMsg.value.aCustomerTransportId;
		aBillingAccountNumber = aRequestMsg.value.aBillingAccountNumber;
		aRG = aRequestMsg.value.aRG;
		aActivationTime = aRequestMsg.value.aActivationTime;
		aOrderAction = aRequestMsg.value.aOrderAction;
		aDSLAM = aRequestMsg.value.aDSLAM;
		aProperties = aRequestMsg.value.aProperties;
	}
	
	/**
	 * Insert the method's description here.
	 */
	protected void init(
		java.lang.String paramList,
		java.lang.String propertiesFile) {

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

		this.props = (Hashtable) p;
		init(paramList);
	}

	/*
	 * PROXY CHANGE
	 *
	*/
	protected void init(java.lang.String paramList, Hashtable props) {

	}

	/**
	 * Insert the method's description here.
	*/
	protected void initMessage(java.lang.String paramList, Hashtable props) {
		TestClient.log.printLog("MDB is not implemented for this method");
	}

	protected void displayInput() {
		
		if(isXMLTestData) {
			TestClient.log.printLog( "INPUT DATA: \n" +  requestXML );
			return;
		}

		TestClient.log.printLog(
			"INPUT DATA:aContext<"
				+ (new BisContextBisHelper((aContext != null)
					? aContext
					: TestClient.myBisContext))
					.toString()
				+ ">");
		if (aCustomerTransportId != null)
			TestClient.log.printLog(
				"INPUT DATA:aCustomerTransportId<"
					+ (new StringOptBisHelper(aCustomerTransportId))
						.toString()
					+ ">");
		if (aBillingAccountNumber != null)
			TestClient.log.printLog(
				"INPUT DATA:aBillingAccountId<"
					+ (new CompositeObjectKeyBisHelper(aBillingAccountNumber))
						.toString()
					+ ">");
		if (aDSLAM != null)
			TestClient.log.printLog(
				"INPUT DATA:aDSLAM<"
					+ (new DSLAMTransportOptBisHelper(aDSLAM)).toString()
					+ ">");
		if (aRG != null)
			TestClient.log.printLog(
				"INPUT DATA:aRG<"
					+ (new ResidentialGatewayBisHelper(aRG)).toString()
					+ ">");
		if (aActivationTime != null)
			TestClient.log.printLog(
				"INPUT DATA:aActivation<"
					+ (new TimeBisHelper(aActivationTime)).toString()
					+ ">");
		if (aOrderAction != null)
			TestClient.log.printLog(
				"INPUT DATA:aOrderAction<"
					+ (new OrderActionBisHelper(aOrderAction)).toString()
					+ ">");
		if (aProperties != null)
			TestClient.log.printLog(
				"INPUT DATA:aProperties<"
					+ (new ObjectPropertySeqOptBisHelper(aProperties)).toString()
					+ ">");
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String)
	 */
	protected void initSoapMessage(String paramList) {
		TestClient.log.printLog(
			"Soap proxy is not implemented for this method");

	}

	/********************************************************
	 * ********************SOAP PROXY************************/
	protected void initSoapMessage(String paramList, Properties props) {
		// parse the argument from the test case.
		this.init(paramList);

		if(!isXMLTestData)
			this.displayInput();

		sendMessage(props,
			RmFacadeSOABuilder._publishRGActivation,
			createMessage());
	}

	/**
	* createMessage
	*/
	private String createMessage() {
		String xml = null;
		try {

			_publishRGActivationRequest request =
				new _publishRGActivationRequest(
					this.aContext,
					this.aCustomerTransportId,
					this.aBillingAccountNumber,
					this.aDSLAM,
					this.aRG,
					this.aActivationTime,
					this.aOrderAction,
					this.aProperties);
					
			_publishRGActivationRequestBISMsg requestMsg =
				new _publishRGActivationRequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode them into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS
			xml =
				addSOAPTagstoXML(
					xml,
					RmFacadeSOABuilder._publishRGActivation);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		return xml;
	}
protected void initSoapHttpMessage( String ParamList, Properties props )
	{
		TestClient.log.printLog ("SOAP/HTTP not implemented" );
	}

}
