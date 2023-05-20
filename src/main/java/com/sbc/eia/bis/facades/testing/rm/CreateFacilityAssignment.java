// $Id: CreateFacilityAssignment.java,v 1.1 2005/05/09 18:47:40 jp2854 Exp $
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
//# 05/09/2005  Rene Duka    Creation.
//# 05/16/2005  Rene Duka    Modified for TestClient testing.
//# 05/20/2005  Rene Duka    Modified for TestClient testing.
//# 06/06/2005  mk3198       Modifications for soap via ldap.
//# 06/23/2005  Rene Duka    Changed VOIPOpt to VOIP.
//# 06/30/2005  kk8467       Changed call getDSLAM() to getDSLAMTransport().
//# 07/18/2005  Rene Duka    Added the Logger in the execute method.
//# 08/04/2005  Rene Duka    Modified for LS Release 1.
//# 11/11/2005  Rene Duka    Made changes for IDL Bundle 33.
//# 01/19/2006  Rene Duka    Made changes for LS Release 2.
//# 02/02/2006  Rene Duka    Made changes for LS Release 2.
//# 05/10/2006  Mrinalini	 Updated for LS3
package com.sbc.eia.bis.facades.testing.rm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.utilities.EMBusContext;
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
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn;
import com.sbc.eia.idl.rm.RmFacadeSOABuilder;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentRequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentResponseMsg;
import com.sbc.eia.idl.rm.bishelpers.CreateFacilityAssignmentReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.VOIPOpt;
import com.sbc.eia.idl.rm_ls_types.bishelpers.NetworkTypeBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.embus.client.Delivery;
import com.sbc.embus.client.Messenger;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;
import com.sbc.vicunalite.vaxb.VAXBWriter;

/**
 * @author rd2842
 *
 */
public class CreateFacilityAssignment extends TestRMCaseBase {
    private CreateFacilityAssignmentReturn aResult = null;
    private String aCustomerTransportId = null;
    private CompositeObjectKey aBillingAccountNumber = null;
    private Location aServiceLocation = null;
    private StringOpt aServiceBundleId = null;
    private VOIPOpt aVOIP = null;
    private BooleanOpt aMaintenanceFlag = null;
    private EiaDate aDueDate = null;
    private OrderAction aOrderAction = null;
    private ProductSubscription[] aProductSubscriptions = null;
    private StringOpt aTaperCode = null;
    private NetworkType aNetworkTypeChoice = null;
    private String aNetworkType = null;
    private ObjectPropertySeqOpt aProperties = null;
    private Hashtable aProps = null;
    private Logger aLogger = null;

    //FOR RM PROXY
    private com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.BisContext aProxyContext = null;
    private ObjectPropertyProxyManager objProxyHelper = null;

    // FOR RM MDB
    private final static String ENVIRONMENT_NAME_KEY = "RESOURCE_MANAGEMENT";
    private final static String TEST_EMBUS_CLIENT_CONFIG_FILE_NAME_KEY = "test-client-config.xml";
    private final static int SLEEP_TIME = 10 * 1000;

    /**
     * constructor: CreateFacilityAssignment
     */
    public CreateFacilityAssignment() {
        super();

        // set method name
        setMyMethodName("CreateFacilityAssignment");

    }

    /**
     * method: displayResult
     */
    protected void displayResult() {
        //XML test data
        if (isXMLTestData) {
            _createFacilityAssignmentResponse res =  new _createFacilityAssignmentResponse();       
            String responseXML = null;
            
            if (aResult != null) {       
                res.aCreateFacilityAssignmentReturn(aResult);      
                responseXML = convertMMarshalObjectToXML(new _createFacilityAssignmentResponseMsg(res));
            }
            else  {
                if (responseError != null) {
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
                    
                    responseXML = convertMMarshalObjectToXML(new _createFacilityAssignmentResponseMsg(res));
                }
            }
    
            if(responseXML != null)
                TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
        }
        else if (aResult != null) {
            TestClient.log.printLog(
                "OUTPUT DATA: " 
                    + (new CreateFacilityAssignmentReturnBisHelper(aResult)).toString());
        }
    }

    /**
     * method: displayProxyResult
     */
    protected void displayProxyResult() {
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

        displayInput();

        com.sbc.eia.bis.facades.rm.transactions.CreateFacilityAssignment.CreateFacilityAssignment aTrans =
            new com.sbc.eia.bis.facades.rm.transactions.CreateFacilityAssignment.CreateFacilityAssignment(aProps);

        aResult = aTrans.execute(((aContext != null) ? aContext : TestClient.myBisContext),
                                 this.aCustomerTransportId,
                                 this.aBillingAccountNumber,
                                 this.aServiceLocation,                                                                this.aMaintenanceFlag,
                                 this.aDueDate,
                                 this.aOrderAction,                                 
                                 this.aTaperCode, 
                                 this.aNetworkType,
                                 this.aNetworkTypeChoice,
                                 this.aProperties,
                                 aLogger);
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
            DataNotFound {

        displayInput();

        aResult = ((Rm) remote).createFacilityAssignment(((aContext != null) ? aContext : TestClient.myBisContext),
                                                     this.aCustomerTransportId,
                                                     this.aBillingAccountNumber,
                                                     this.aServiceLocation,                                                    
                                                     this.aMaintenanceFlag,
                                                     this.aDueDate,
                                                     this.aOrderAction,                                                    
                                                     this.aTaperCode, 
                                                     this.aNetworkType,
                                                     this.aNetworkTypeChoice,
                                                     this.aProperties);
    }

    /**
     * method: execute(Hashtable)
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
     * method: init(String)
     */
    protected void init(java.lang.String paramList) {

        // parse the argument from the test case
        StringTokenizer st = new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
        try {
            while (st.hasMoreElements()) {
                String tag = TestClient.nextToken(st) ;
                System.out.println("Next Tag => " + tag);
                if (tag.equals("XML")) {
                    isXMLTestData = true;
                    String xmlFileName = TestClient.nextToken( st ) ;
                    TestClient.log.printLog("XML Test Data File :" + xmlFileName);
                    buildIDLObjectsFromXML( xmlFileName );
                    return;
                }

                if (tag.equals("BisContext")) {
                    aContext = objHelpers.getBisContext(st);
                }
                if (tag.equals("CustomerTransportId")) {
                    String aToken = st.nextToken();
                    if (aToken != null && aToken.equalsIgnoreCase("null"))
                        aToken = null;
                    aCustomerTransportId = aToken;
                }
                if (tag.equals("BillingAccountNumber")) {
                    aBillingAccountNumber = objHelpers.getCompositeObjectKey(st);
                }
                if (tag.equals("Location")) {
                    aServiceLocation = objHelpers.getLocation(st);
                }

                if (tag.equals("MaintenanceFlag")) {
                    aMaintenanceFlag = objHelpers.getBooleanOpt(st);
                }
                if (tag.equals("DueDate")) {
                    aDueDate = objHelpers.getDate(st);
                }
                if (tag.equals("OrderAction")) {
                    aOrderAction = objHelpers.getOrderAction(st);
                }

                if (tag.equals("TaperCode")) {
                    aTaperCode = objHelpers.getStringOpt(st);
                }
                if (tag.equals("NetworkType")){
                	String aToken = st.nextToken();
//	        	if(aToken != null && aToken.equalsIgnoreCase("null"))
//                	aToken=null;
                	aNetworkType = aToken;  
                	
                }
                if (tag.equals("NetworkTypeChoice")) {
                    aNetworkTypeChoice = objHelpers.getNetworkType(st);
                }
                if (tag.equals("Properties")) {
                    aProperties = objHelpers.getObjectPropertySeqOpt(st);

                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Notify user of error
            System.out.println("Verify test data/format, failure parsing test case string");
        }
    }

    /**
     * method: buildIDLObjectsFromXML(String)
     */
    public void buildIDLObjectsFromXML(String xmlFileName)
        throws Exception {
        //Read XML test data file
        requestXML = readFile(xmlFileName, true);
        
        //Convert XML to IDL object
        MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);
        
        //build request IDL objects from MMarshalObject
        _createFacilityAssignmentRequestMsg aRequestMsg = (_createFacilityAssignmentRequestMsg)aRequest;

        aContext = aRequestMsg.value.aContext ;
        aCustomerTransportId = aRequestMsg.value.aCustomerTransportId;
        aBillingAccountNumber = aRequestMsg.value.aBillingAccountNumber;
        aServiceLocation = aRequestMsg.value.aServiceLocation;        
        aMaintenanceFlag = aRequestMsg.value.aMaintenanceFlag;
        aDueDate = aRequestMsg.value.aDueDate;
        aOrderAction = aRequestMsg.value.aOrderAction;
        aTaperCode = aRequestMsg.value.aTaperCode;
        aNetworkType = aRequestMsg.value.aNetworkType;
        aNetworkTypeChoice = aRequestMsg.value.aNetworkTypeChoice;
        aProperties = aRequestMsg.value.aProperties;
    }

    /**
     * method: init(String, String)
     */
    protected void init(java.lang.String paramList, java.lang.String propertiesFile) {

        // parse the argument from the test case
        System.out.println("propertiesFile = " + propertiesFile);

        File file;
        FileInputStream fis = null;

        try {
            file = new File(propertiesFile.trim());
            fis = new FileInputStream(file);
        }
        catch (Exception fe) {
            System.out.println("Properties File Not Found: " + fe.getMessage());
        }

        //get properties file
        Properties p = new Properties();
        try {
            p.load(fis);
        }
        catch (IOException ie) {
            System.out.println("IOException reading properties file.");
        }

        // Set up properties for specific Bis
        this.aProps = (Hashtable) p;
        init(paramList);

    }

    /**
     * method: init(String, Hashtable)
     */
    protected void init(java.lang.String paramList, Hashtable props) {
    }


    /**
     * method: initMessage(String)
     */
    protected void initMessage(java.lang.String paramList, Hashtable props) {
        File file;
        FileInputStream fis = null;
        try {
            file = new File(this.propertiesRMFile);
            fis = new FileInputStream(file);
        }
        catch (Exception fe) {
            System.out.println("Properties File Not Found: " + fe.getMessage());
        }

        //get properties file
        Properties properties = new Properties();
        try {
            properties.load(fis);
        }
        catch (IOException ie) {
            System.out.println("IOException reading properties file.");
        }

        // Set up properties for specific Bis
        this.aProps = (Hashtable) properties;

        // parse the argument from the test case.
        StringTokenizer st = new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
        String xml = "";

        try {
            while (st.hasMoreElements()) {
                String tag = TestClient.nextToken(st);
                System.out.println("Next Tag => " + tag);
                if (tag.equals("BisContext")) {
                    aContext = objHelpers.getBisContext(st);
                }
                if (tag.equals("CustomerTransportId")) {
                    String aToken = st.nextToken();
                    if (aToken != null && aToken.equalsIgnoreCase("null"))
                        aToken = null;
                    aCustomerTransportId = aToken;
                }
                if (tag.equals("BillingAccountNumber")) {
                    aBillingAccountNumber = objHelpers.getCompositeObjectKey(st);
                }
                if (tag.equals("Location")) {
                    aServiceLocation = objHelpers.getLocation(st);
                }

                if (tag.equals("MaintenanceFlag")) {
                    aMaintenanceFlag = objHelpers.getBooleanOpt(st);
                }
                if (tag.equals("DueDate")) {
                    aDueDate = objHelpers.getDate(st);
                }
                if (tag.equals("OrderAction")) {
                    aOrderAction = objHelpers.getOrderAction(st);
                }

                if (tag.equals("TaperCode")) {
                    aTaperCode = objHelpers.getStringOpt(st);
                }
                if (tag.equals("NetworkType")) {
                	String aToken = st.nextToken();
                /*	if (aToken != null && aToken.equalsIgnoreCase("null"))
                		aToken = null;*/
                    aNetworkType = aToken;
                }
                if (tag.equals("NetworkTypeChoice")) {
                    aNetworkTypeChoice = objHelpers.getNetworkType(st);
                }
                if (tag.equals("Properties")) {
                    aProperties = objHelpers.getObjectPropertySeqOpt(st);

                }
            }

            // Parse the argument from the test case.
            // Instantiate and populate the MSG classes for each object type created above
            _createFacilityAssignmentRequest aCreateFacilityAssignmentRequest =
                new _createFacilityAssignmentRequest(
                    aContext,
                    this.aCustomerTransportId,
                    this.aBillingAccountNumber,
                    this.aServiceLocation,
                    this.aMaintenanceFlag,
                    this.aDueDate,
                    this.aOrderAction,
                    this.aTaperCode, 
                    this.aNetworkType,
                    this.aNetworkTypeChoice,
                    this.aProperties);

            _createFacilityAssignmentRequestBISMsg aCreateFacilityAssignmentRequestMsg =
                new _createFacilityAssignmentRequestBISMsg(aCreateFacilityAssignmentRequest);

            // Create MMarshalObject array of the input msg’s which is used as input parameter to VAXBWriter.
            MMarshalObject[] msgs = { aCreateFacilityAssignmentRequestMsg };

            // Create XML Document to be passed to MDB as TextMessage
            xml = SoapParserHelper.wrapVaxBWithSoapEnvelope(VAXBDocumentWriter.encode(msgs));

            TestClient.log.printLog("****Request Message****");
            TestClient.log.printLog(xml);
            //              }

        }
        catch (Throwable t) {
            TestClient.log.printLog("*********Exception caught in Marshalling*********" + t.getMessage());
            TestClient.log.printLog("*********End of Test*********");
            return;
        }

        EMBusContext embusContext = null;
        try {

            if (embusContext == null) {
                embusContext =
                    new EMBusContext(
                        new TranBase(this.aProps),
                        ENVIRONMENT_NAME_KEY,
                        TEST_EMBUS_CLIENT_CONFIG_FILE_NAME_KEY);
            }

            Messenger messenger = embusContext.createMessenger();
            Message theMessage = messenger.createTextMessage(xml);
            //No applicable here theMessage.setBooleanProperty(MessageConstants.IS_FINAL_MESSAGE,true);
            Delivery delivery = null;

            try {
                TestClient.log.printLog("****Sending the message now****");
                delivery =
                    messenger.request(
                        embusContext.getSubsetMap(
                            new embusLogger(),
                            "embus.messagingFunctions.createFacilityAssignmentService"),
                        theMessage);

                //Look for a response for 5min and 10 seconds
                System.out.println("****Waiting for response message!****");
                for (int i = 0; i < 31; i++) {
                    Thread.currentThread().sleep(this.SLEEP_TIME);
                    if (delivery != null && delivery.getMessage() != null) {
                        break;
                    }
                }

                if (delivery == null)
                    System.out.println("delivery == null");
                if (delivery != null && delivery.getMessage() == null)
                    System.out.println("delivery.getMessage() == null");

                if (delivery != null && delivery.getMessage() != null)
                    //Print the result from the backend
                    printQMessage((TextMessage) delivery.getMessage());
                else
                    System.out.println("Receive timed out.");

            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            finally {
                if (delivery != null) {
                    try {
                        delivery.getReceipt().discard();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                    }
                }

                if (messenger != null) {
                    try {
                        messenger.dismiss();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method: printQMessage(TextMessage)
     */
    private static void printQMessage(TextMessage message) {
        String responseMsg = null;
        try {
            TestClient.log.printLog("*********Reading message *********");
            responseMsg = message.getText();
            TestClient.log.printLog(responseMsg);

            System.out.println("message recieved");
        }
        catch (JMSException e) {
            System.out.println("Exception occurred: " + e.toString());
        }

        // display date & time transaction ended
        java.util.Date today = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
        today = new java.util.Date();
        String dateString = formatter.format(today);
        System.out.println("TestMDBProxy returned at " + dateString);
        TestClient.log.printLog("TestMDBProxy returned at " + dateString);
    }

    /**
     * method: embusLogger()
     */
    public class embusLogger extends Logger implements com.sbc.bccs.utilities.Logger {
    }

    /**
     * method: displayInput()
     */
    protected void displayInput() {

        if (isXMLTestData) {
            TestClient.log.printLog( "INPUT DATA: \n" +  requestXML );
            return;
        }

        TestClient.log.printLog(
            "INPUT DATA:aContext<"
                + (new BisContextBisHelper((aContext != null) ? aContext : TestClient.myBisContext)).toString()
                + ">");
        if (aCustomerTransportId != null)
            TestClient.log.printLog(
                "INPUT DATA:aCustomerTransportId<"
                    + aCustomerTransportId
                    + ">");

        if (aBillingAccountNumber != null)
            TestClient.log.printLog(
                "INPUT DATA:aBillingAccountNumber<"
                    + (new CompositeObjectKeyBisHelper(aBillingAccountNumber)).toString()
                    + ">");

        if (aServiceLocation != null)
            TestClient.log.printLog(
                "INPUT DATA:aServiceLocation<"
                    + (new LocationBisHelper(aServiceLocation)).toString()
                    + ">");


        if (aMaintenanceFlag != null)
            TestClient.log.printLog(
                "INPUT DATA:aMaintenanceFlag<"
                    + (new BooleanOptBisHelper(aMaintenanceFlag)).toString()
                    + ">");

        if (aDueDate != null)
            TestClient.log.printLog(
                "INPUT DATA:aDueDate<"
                    + (new EiaDateBisHelper(aDueDate)).toString()
                    + ">");

        if (aOrderAction != null)
            TestClient.log.printLog(
                "INPUT DATA:aOrderAction<"
                    + (new OrderActionBisHelper(aOrderAction)).toString()
                    + ">");


        if (aTaperCode != null)
            TestClient.log.printLog(
                "INPUT DATA:aTaperCode<"
                    + (new StringOptBisHelper(aTaperCode)).toString()
                    + ">");
                   
	     if(aNetworkType.trim().length() == 0){            
            aNetworkType = "";
        	TestClient.log.printLog(
        		"INPUT DATA :aNetworkType<"
        			+ aNetworkType
        			+ ">");
        }	
        if (aNetworkTypeChoice != null)
            TestClient.log.printLog(
                "INPUT DATA:aNetworkTypeChoice<"
                    + (new NetworkTypeBisHelper(aNetworkTypeChoice)).toString()
                    + ">");

        if (aProperties != null)
            TestClient.log.printLog(
                "INPUT DATA:aProperties<"
                    + (new ObjectPropertySeqOptBisHelper(aProperties)).toString()
                    + ">");
    }

    /**
     * method: initSoapMessage(String, Properties)
     */
    protected void initSoapMessage(String paramList, Properties props) {
        // parse the argument from the test case.
        this.init(paramList);

        if (!isXMLTestData)
            this.displayInput();

        // this.displayInput();

        sendMessage(props,
                    RmFacadeSOABuilder._createFacilityAssignment,
                    createMessage());

    }

    /**
     * method: createMessage()
     */
    private String createMessage() {
        String xml = null;
        try {
            _createFacilityAssignmentRequest request = new _createFacilityAssignmentRequest(
                this.aContext,
                this.aCustomerTransportId,
                this.aBillingAccountNumber,
                this.aServiceLocation,
                this.aMaintenanceFlag,
                this.aDueDate,
                this.aOrderAction,               
                this.aTaperCode, 
                this.aNetworkType,
                this.aNetworkTypeChoice,
                this.aProperties);

            _createFacilityAssignmentRequestBISMsg requestMsg = new _createFacilityAssignmentRequestBISMsg(request);

            MMarshalObject msgs = requestMsg;

            //Encode them into XML
            xml = VAXBWriter.encode(msgs);

            //add SOAP TAGS
            xml = addSOAPTagstoXML(xml,RmFacadeSOABuilder._createFacilityAssignment);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return xml;
    }

    /**
     * method: initSoapHttpMessage()
     */
    protected void initSoapHttpMessage( String ParamList, Properties props )  {
        TestClient.log.printLog ("SOAP/HTTP not implemented" );
    }
}
