//$Id: SendFacilityAssignmentOrder3.java,v 1.2 2009/02/03 23:36:00 ch1463 Exp $
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
//#      © 2008-2015 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 01/08/2008	   Rommel Baldivas			  Creation
//############################################################################
package com.sbc.eia.bis.facades.testing.rm;

import gnu.regexp.RE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestCaseException;
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
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder3Return;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder3Request;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder3RequestBISMsg;
import com.sbc.eia.idl.rm.bishelpers.SendFacilityAssignmentOrder3ReturnBisHelper;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

public class SendFacilityAssignmentOrder3 extends TestRMCaseBase
{
	private SendFacilityAssignmentOrder3Return result = null;
	private String aSOACServiceOrderNumber = null;
	private String aSOACServiceOrderCorrectionSuffix = null;
	private String aNetworkType = null;
	private String aOrderActionId = null;
	private String aOrderNumber = null;
	private String aOrderActionType = null;
	private BooleanOpt aCompletionIndicator = null;
	private StringOpt aSubActionType = null;
	private String aCircuitId = null;
	private Location aServiceLocation = null;
	private EiaDate aOriginalDueDate = null;
	private EiaDateOpt aSubsequentDueDate = null;
	private EiaDate aApplicationDate = null;
	private StringOpt aRelatedServiceOrderNumber = null;
	private BooleanOpt aLineShareDisconnectFlag = null;
	private String aClassOfService = null;
	private BooleanOpt aResendFlag = null;
	private ObjectPropertySeqOpt aProperties = null;
	private Hashtable props = null;
	
	private StringOpt aOldNetworkType = null;
	private StringOpt aSecondaryCircuitID = null;
	private StringOpt aSecondaryRelatedCircuitID = null;
	private CompositeObjectKey aBillingAccountNumber = null;
	private StringOpt aRelatedCircuitID = null;
    
	private BooleanOpt aInterceptRedirectIndicator = null;
	private StringOpt aDryloopRelatedCircuitId = null;
	private StringOpt aDSLDisconnectTelephoneNumber = null;
	private StringOpt aExceptionRoutingIndicator = null;
	
	
	/**
	 * Constructor for SendFacilityAssignmentOrder3.
	 */
	public SendFacilityAssignmentOrder3()
	{
		super();
	}

	/**
	 * Method displayResult.
	 */
	protected void displayResult()
	{
		if (result != null)
		{
			/* rename SendF1F2OrderReturnBisHelper to SendFacilityAssignmentOrderReturnBisHelper*/
			TestClient.log.printLog(
				"OUTPUT DATA: " + (new SendFacilityAssignmentOrder3ReturnBisHelper (result)).toString());
									   
		}
	}

	/**
	 * Method displayProxyResult.
	 */
	protected void displayProxyResult()
	{}

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
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{

		displayInput();
		com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder3.SendFacilityAssignmentOrder3 sendFacilityAssignment3 =
			new com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder3.SendFacilityAssignmentOrder3(props);

		result =
			sendFacilityAssignment3.execute(
				(aContext != null) ? aContext : TestClient.myBisContext,
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aNetworkType,
				aOldNetworkType,
				aOrderActionId,
				aOrderNumber,
				aOrderActionType,
				aCompletionIndicator,
				aSubActionType,
				aCircuitId,
				aSecondaryCircuitID,
				aServiceLocation,
				aOriginalDueDate,
				aSubsequentDueDate,
				aApplicationDate,
				aRelatedCircuitID, 
				aSecondaryRelatedCircuitID,
				aRelatedServiceOrderNumber,
				aLineShareDisconnectFlag,
				aClassOfService,
				aResendFlag,
				aBillingAccountNumber,
				aInterceptRedirectIndicator,
				aDryloopRelatedCircuitId,
				aDSLDisconnectTelephoneNumber,
				aExceptionRoutingIndicator,
				aProperties,
				(com.sbc.eia.bis.RmNam.utilities.Logger) TestClient.logger);
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

		result =
			((Rm) remote).sendFacilityAssignmentOrder3(
				(aContext != null) ? aContext : TestClient.myBisContext,
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aNetworkType,
				aOldNetworkType,
				aOrderActionId,
				aOrderNumber,
				aOrderActionType,
				aCompletionIndicator,
				aSubActionType,
				aCircuitId,
				aSecondaryCircuitID,
				aServiceLocation,
				aOriginalDueDate,
				aSubsequentDueDate,
				aApplicationDate,
				aRelatedCircuitID, 
				aSecondaryRelatedCircuitID,
				aRelatedServiceOrderNumber,
				aLineShareDisconnectFlag,
				aClassOfService,
				aResendFlag,
				aBillingAccountNumber,
				aInterceptRedirectIndicator,
				aDryloopRelatedCircuitId,
				aDSLDisconnectTelephoneNumber,
				aExceptionRoutingIndicator,
				aProperties);
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
			DataNotFound
	{}

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
			while (st.hasMoreElements())
			{
				String tag = TestClient.nextToken(st);
				if (tag.equals("XML")) 
				{
					isXMLTestData = true;
					String xmlFileName = TestClient.nextToken(st);
					TestClient.log.printLog("XML Test Data File :" + xmlFileName);
					buildIDLObjectsFromXML(xmlFileName);
					return;
				}
				System.out.println("Processing Tag: [" + tag + "]");

				if (tag.equals("BisContext"))
					aContext = objHelpers.getBisContext(st);
				else if (tag.equals("aSOACServiceOrderNumber"))
					aSOACServiceOrderNumber = TestClient.nextToken(st);
				else if (tag.equals("aSOACServiceOrderCorrectionSuffix"))
					aSOACServiceOrderCorrectionSuffix = TestClient.nextToken(st);
				else if (tag.equals("aNetworkType"))
					aNetworkType = TestClient.nextToken(st);
				else if (tag.equals("aOldNetworkType"))
				{
				    aOldNetworkType = objHelpers.getStringOpt(st);
				}
				else if (tag.equals("aOrderActionId"))
					aOrderActionId = TestClient.nextToken(st);
				else if (tag.equals("aOrderNumber"))
					aOrderNumber = TestClient.nextToken(st);
				else if (tag.equals("aOrderActionType"))
					aOrderActionType = TestClient.nextToken(st);
				else if (tag.equals("aCompletionIndicator"))
				{
					aCompletionIndicator = new BooleanOpt();
					aCompletionIndicator.theValue(
						(Boolean.valueOf(TestClient.nextToken(st))).booleanValue());
				}
				else if (tag.equals("aSubActionType"))
					aSubActionType = objHelpers.getStringOpt(st);
				else if (tag.equals("aCircuitId"))
					aCircuitId = TestClient.nextToken(st);
				else if (tag.equals("aSecondaryCircuitID"))
				{
				    aSecondaryCircuitID = objHelpers.getStringOpt(st);
				}
				else if (tag.equals("aServiceLocation"))
					aServiceLocation = objHelpers.getLocation(st);
				else if (tag.equals("aOriginalDueDate"))
					aOriginalDueDate = objHelpers.getDate(st);
				else if (tag.equals("aSubsequentDueDate"))
					aSubsequentDueDate = objHelpers.getDateOpt(st);
				else if (tag.equals("aApplicationDate"))
					aApplicationDate = objHelpers.getDate(st);
				else if (tag.equals("aRelatedServiceOrderNumber"))
					aRelatedServiceOrderNumber = objHelpers.getStringOpt(st);
				else if (tag.equals("aLineShareDisconnectFlag"))
				{
					aLineShareDisconnectFlag = new BooleanOpt();
					aLineShareDisconnectFlag.theValue(
						(Boolean.valueOf(TestClient.nextToken(st))).booleanValue());
				}
				else if (tag.equals("aClassOfService"))
					aClassOfService = TestClient.nextToken(st);
				else if (tag.equals("aResendFlag"))
				{
					aResendFlag = new BooleanOpt();
					aResendFlag.theValue(
						(Boolean.valueOf(TestClient.nextToken(st))).booleanValue());
				}
				else if (tag.equals("aRelatedCircuitID"))
				{
				    aRelatedCircuitID = objHelpers.getStringOpt(st);
				}
				else if (tag.equals("aSecondaryRelatedCircuitID"))
				{
				    aSecondaryRelatedCircuitID = objHelpers.getStringOpt(st);
				}
				else if (tag.equals("aBillingAccountNumber"))
				{
					aBillingAccountNumber = objHelpers.getCompositeObjectKey(st);
				}
				else if (tag.equals("aInterceptRedirectIndicator"))
				{	
					aInterceptRedirectIndicator = new BooleanOpt();
					aInterceptRedirectIndicator.theValue(
						(Boolean.valueOf(TestClient.nextToken(st))).booleanValue());
				}
				else if (tag.equals("aDryloopRelatedCircuitId"))
					aDryloopRelatedCircuitId = objHelpers.getStringOpt(st);
				else if (tag.equals("aDSLDisconnectTelephoneNumber"))
					aDSLDisconnectTelephoneNumber = objHelpers.getStringOpt(st);
				else if (tag.equals("aExceptionRoutingIndicator"))
					aExceptionRoutingIndicator = objHelpers.getStringOpt(st);
				else if (tag.equals("aProperties"))
					aProperties = objHelpers.getObjectPropertySeqOpt(st);
			}			
		}
		catch (Throwable t)
		{
			// Notify user of error
			System.out.println("Verify test data/format, failure parsing test case string");
		}
	}

	/**
	 * Method init.
	 * @param paramList
	 * @param propertiesFile
	 */
	protected void init(java.lang.String paramList, java.lang.String propertiesFile)
	{

		File file;
		FileInputStream fis = null;

		try
		{
			file = new File(propertiesFile.trim());
			fis = new FileInputStream(file);
		}
		catch (Exception fe)
		{
			System.out.println("Properties File Not Found: " + fe.getMessage());
		}

		//get properties file
		Properties p = new Properties();

		try
		{
			p.load(fis);
		}
		catch (IOException ie)
		{
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
	protected void init(java.lang.String paramList, Hashtable props)
	{}

	/**
	 * Method initMessage.
	 * @param paramList
	 */
	protected void initMessage(java.lang.String paramList, Hashtable props)
	{
		String aFCIFStringFromSOAC = null;
		try
		{
			RE text = new RE("[|]");
			aFCIFStringFromSOAC = text.substituteAll(paramList, "\n", paramList.indexOf("|") + 1);
			TestClient.log.printLog("INPUT DATA: <FCIF or XML=" + aFCIFStringFromSOAC + ">");

		}
		catch (Exception e)
		{
			System.out.println("Exception in gnu.regexp.RE object when parsing FCIF string");
			return;
		}
		//send to the queue
		try
		{
			RMEmbusServiceHelper aHelper = new RMEmbusServiceHelper(props);
			aHelper.send(aFCIFStringFromSOAC);
			TestClient.log.printLog("Message sent to Queue without error");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
	}
	/**
     * method: buildIDLObjectsFromXML(String)
     */
	public void buildIDLObjectsFromXML(String xmlFileName) throws Exception {
		//Read XML test data file
		requestXML = readFile(xmlFileName, true);

		//Convert XML to IDL object
		MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);

		//build request IDL objects from MMarshalObject
		_sendFacilityAssignmentOrder3RequestBISMsg aRequestMsg =
		(_sendFacilityAssignmentOrder3RequestBISMsg) aRequest;
		
		aContext = aRequestMsg.value.aContext;
		aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
		aSOACServiceOrderCorrectionSuffix = aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
		aNetworkType = aRequestMsg.value.aNetworkType;
		aOrderActionId = aRequestMsg.value.aOrderActionId;
		aOrderNumber = aRequestMsg.value.aOrderNumber;
		aOrderActionType = aRequestMsg.value.aOrderActionType;
		aCompletionIndicator  = aRequestMsg.value.aCompletionIndicator ;
		aSubActionType = aRequestMsg.value.aSubActionType;
		aCircuitId = aRequestMsg.value.aCircuitId;
		aServiceLocation = aRequestMsg.value.aServiceLocation;
		aOriginalDueDate = aRequestMsg.value.aOriginalDueDate;
		aSubsequentDueDate = aRequestMsg.value.aSubsequentDueDate;
		aApplicationDate = aRequestMsg.value.aApplicationDate;
		aRelatedServiceOrderNumber = aRequestMsg.value.aRelatedServiceOrderNumber;
		aLineShareDisconnectFlag = aRequestMsg.value.aLineShareDisconnectFlag;
		aClassOfService = aRequestMsg.value.aClassOfService;
		aResendFlag = aRequestMsg.value.aResendFlag;
		aBillingAccountNumber = aRequestMsg.value.aBillingAccountNumber;
		aSecondaryCircuitID = aRequestMsg.value.aSecondaryCircuitId;
		aSecondaryRelatedCircuitID = aRequestMsg.value.aSecondaryRelatedCircuitID;
		aOldNetworkType = aRequestMsg.value.aOldNetworkType;
		aRelatedCircuitID = aRequestMsg.value.aRelatedCircuitID;

		aInterceptRedirectIndicator = aRequestMsg.value.aInterceptRedirectIndicator;
		aDryloopRelatedCircuitId = aRequestMsg.value.aDryloopRelatedCircuitId;
		aDSLDisconnectTelephoneNumber = aRequestMsg.value. aDSLDisconnectTelephoneNumber;
		aExceptionRoutingIndicator = aRequestMsg.value.aExceptionRoutingIndicator;
		aProperties = aRequestMsg.value.aProperties;
	}


	/**
	 * Method displayInput.
	 */
	protected void displayInput() {
		TestClient.log.printLog(
			"INPUT DATA:aContext<"
				+ (new BisContextBisHelper((aContext != null) ? aContext : TestClient.myBisContext))
					.toString()
				+ ">");
		try {
			TestClient.log.printLog(
				"INPUT DATA:aSOACServiceOrderNumber<" + aSOACServiceOrderNumber + ">");
			}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSOACServiceOrderNumber<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aSOACServiceOrderCorrectionSuffix<"
					+ aSOACServiceOrderCorrectionSuffix
					+ ">");
			}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSOACServiceOrderCorrectionSuffix<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aNetworkType<" + aNetworkType + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aNetworkType<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aOldNetworkType<" + aOldNetworkType.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aOldNetworkType<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aOrderActionId<" + aOrderActionId + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aOrderActionId<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aOrderNumber<" + aOrderNumber + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aOrderNumber<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aOrderActionType<" + aOrderActionType + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aOrderActionType<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aCompletionIndicator<"
					+ (new BooleanOptBisHelper(aCompletionIndicator)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aCompletionIndicator<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aSubActionType<" + aSubActionType.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSubActionType<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aCircuitId<" + aCircuitId + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aCircuitId<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aSecondaryCircuitID<" + aSecondaryCircuitID.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSecondaryCircuitID<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aServiceLocation<"
					+ (new LocationBisHelper(aServiceLocation)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aServiceLocation<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aOriginalDueDate<"
					+ (new EiaDateBisHelper(aOriginalDueDate)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aOriginalDueDate<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aSubsequentDueDate<"
					+ (new EiaDateOptBisHelper(aSubsequentDueDate)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSubsequentDueDate<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aApplicationDate<"
					+ (new EiaDateBisHelper(aApplicationDate)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aApplicationDate<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aRelatedServiceOrderNumber<"
					+ aRelatedServiceOrderNumber.theValue()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aRelatedServiceOrderNumber<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aLineShareDisconnectFlag<"
					+ (new BooleanOptBisHelper(aLineShareDisconnectFlag)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aLineShareDisconnectFlag<null>");
		}

		try {
			TestClient.log.printLog("INPUT DATA:aClassOfService<" + aClassOfService + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aClassOfService<null>");
		}
		
		try {
			TestClient.log.printLog(
				"INPUT DATA:aResendFlag<"
					+ (new BooleanOptBisHelper(aResendFlag)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aResendFlag<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aProperties<"
					+ (new ObjectPropertySeqOptBisHelper(aProperties)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aProperties<null>");
		}
		
		try {
			TestClient.log.printLog(
				"INPUT DATA:aRelatedCircuitID<"
					+ aRelatedCircuitID.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aRelatedCircuitID<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aSecondaryRelatedCircuitID<"
					+ aSecondaryRelatedCircuitID.theValue()+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aSecondaryRelatedCircuitID<null>");
		}

		try {
	           TestClient.log.printLog(
	                   "INPUT DATA:aBillingAccountNumber<"
	                       + (new CompositeObjectKeyBisHelper(aBillingAccountNumber)).toString() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aBillingAccountNumber<null>");
		}

		try {
			TestClient.log.printLog(
				"INPUT DATA:aInterceptRedirectIndicator<"
					+ (new BooleanOptBisHelper(aInterceptRedirectIndicator)).toString()
					+ ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aInterceptRedirectIndicator<null>");
		}
		
		try {
			TestClient.log.printLog(
				"INPUT DATA:aDryloopRelatedCircuitId<"
					+ aDryloopRelatedCircuitId.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aDryloopRelatedCircuitId<null>");
		}
			
		try {
			TestClient.log.printLog(
				"INPUT DATA:aDSLDisconnectTelephoneNumber<"
					+ aDSLDisconnectTelephoneNumber.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aDSLDisconnectTelephoneNumber<null>");
		}
		
		try {
			TestClient.log.printLog(
				"INPUT DATA:aExceptionRoutingIndicator<"
					+ aExceptionRoutingIndicator.theValue() + ">");
		}
		catch (Exception e) {
			TestClient.log.printLog("INPUT DATA:aExceptionRoutingIndicator<null>");
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
	 */
	protected void initSoapMessage(String paramList, Properties props)
	{
			// parse the argument from the test case.
		this.init(paramList);

		this.displayInput();

		sendMessage(props,
			RmFacadeSOABuilder._sendFacilityAssignmentOrder,
			createMessage());
	}

	/**
	* createMessage
	*/
	private String createMessage() {
		String xml = null;
		try {

			_sendFacilityAssignmentOrder3Request request =
				new _sendFacilityAssignmentOrder3Request(
					this.aContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aNetworkType, 
					aOldNetworkType,
					aOrderActionId,
					aOrderNumber,
					aOrderActionType,
					aCompletionIndicator,
					aSubActionType,
					aCircuitId, 
					aSecondaryCircuitID,
					aServiceLocation,
					aOriginalDueDate,
					aSubsequentDueDate,
					aApplicationDate,
					aRelatedCircuitID,
					aSecondaryRelatedCircuitID,
					aRelatedServiceOrderNumber,
					aLineShareDisconnectFlag,
					aClassOfService,
					aResendFlag,
					aBillingAccountNumber,
					aInterceptRedirectIndicator,
					aDryloopRelatedCircuitId,
					aDSLDisconnectTelephoneNumber,
					aExceptionRoutingIndicator,
					aProperties);

			_sendFacilityAssignmentOrder3RequestBISMsg requestMsg =
				new _sendFacilityAssignmentOrder3RequestBISMsg(request);

			MMarshalObject msgs = requestMsg;
			//Encode them into XML
			xml = VAXBWriter.encode(msgs);

			//add SOAP TAGS
			xml =
				addSOAPTagstoXML(
					xml,
					RmFacadeSOABuilder._sendFacilityAssignmentOrder);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		return xml;
	}

	protected void processMessage(String paramList) throws TestCaseException
	{}
	protected void initSoapHttpMessage( String ParamList, Properties props )
	{
		TestClient.log.printLog ("SOAP/HTTP not implemented" );
	}
}
