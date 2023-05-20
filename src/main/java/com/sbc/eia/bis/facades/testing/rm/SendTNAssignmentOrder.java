//$Id:$
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/2006    Jon Costa              Creation

package com.sbc.eia.bis.facades.testing.rm;

import gnu.regexp.RE;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
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
import com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderResponseMsg;
import com.sbc.eia.idl.rm.bishelpers.SendTNAssignmentOrderReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatus;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatusOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

public class SendTNAssignmentOrder extends TestRMCaseBase
{
    private SendTNAssignmentOrderReturn result = null;
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
    private Hashtable props = null;

    /**
     * Constructor for SendTNAssignmentOrder
     */
    public SendTNAssignmentOrder()
    {
        super();
    }

    /**
     * Method displayResult.
     */
    protected void displayResult()
    {
        if (isXMLTestData) {
            _sendTNAssignmentOrderResponse res =  new _sendTNAssignmentOrderResponse();
            String responseXML = null;
            
            if (result != null) {       
                res.aSendTNAssignmentOrderReturn(result);      
                responseXML = convertMMarshalObjectToXML(new _sendTNAssignmentOrderResponseMsg(res));
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
                    
                    responseXML = convertMMarshalObjectToXML(new _sendTNAssignmentOrderResponseMsg(res));
                }
            }
    
            if(responseXML != null)
                TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
        }
        else if (result != null) {
            TestClient.log.printLog("OUTPUT DATA: " + (new SendTNAssignmentOrderReturnBisHelper(result)).toString());
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
        throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
    {

        displayInput();
        com.sbc.eia.bis.facades.rm.transactions.SendTNAssignmentOrder.SendTNAssignmentOrder sendTNAO =
            new com.sbc.eia.bis.facades.rm.transactions.SendTNAssignmentOrder.SendTNAssignmentOrder(props);

        result =
            sendTNAO.execute(
                (aContext != null) ? aContext : TestClient.myBisContext,
                aSOACServiceOrderNumber,
                aSOACServiceOrderCorrectionSuffix,
                aOrderNumber,
                aOrderActionType,
                aSubActionType,
                aCompletionIndicator,
                aServiceLocation,
                aOriginalDueDate,
                aSubsequentDueDate,
                aApplicationDate,
                aResendFlag,
                aWireCenter,
                aRateCenter,
                aTelephoneNumberOrderPairs,
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
            ((Rm) remote).sendTNAssignmentOrder(
                (aContext != null) ? aContext : TestClient.myBisContext,
                aSOACServiceOrderNumber,
                aSOACServiceOrderCorrectionSuffix,
                aOrderNumber,
                aOrderActionType,
                aSubActionType,
                aCompletionIndicator,
                aServiceLocation,
                aOriginalDueDate,
                aSubsequentDueDate,
                aApplicationDate,
                aResendFlag,
                aWireCenter,
                aRateCenter,
                aTelephoneNumberOrderPairs,
                aProperties);
    }
    /*  PROXY CHANGE
     *
     * Insert the method's description here.
     *
     */
    protected void execute(Hashtable props)
        throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
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
                System.out.println("Processing Tag: [" + tag + "]");
                if (tag.equals("XML")) {
                    isXMLTestData = true;
                    String xmlFileName = TestClient.nextToken( st ) ;
                    TestClient.log.printLog("XML Test Data File :" + xmlFileName);
                    buildIDLObjectsFromXML( xmlFileName );
                    return;
                }
                if (tag.equals("BisContext"))
                    aContext = objHelpers.getBisContext(st);
                if (tag.equals("aSOACServiceOrderNumber"))
                    aSOACServiceOrderNumber = TestClient.nextToken(st);
                if (tag.equals("aSOACServiceOrderCorrectionSuffix"))
                    aSOACServiceOrderCorrectionSuffix = TestClient.nextToken(st);
                if (tag.equals("aOrderNumber"))
                    aOrderNumber = TestClient.nextToken(st);
                if (tag.equals("aOrderActionType"))
                    aOrderActionType = TestClient.nextToken(st);
                if (tag.equals("aSubActionType"))
                    aSubActionType = objHelpers.getStringOpt(st);
                if (tag.equals("aCompletionIndicator"))
                    aCompletionIndicator = objHelpers.getBooleanOpt(st);
                if (tag.equals("aServiceLocation"))
                    aServiceLocation = objHelpers.getLocation(st);
                if (tag.equals("aOriginalDueDate"))
                    aOriginalDueDate = objHelpers.getDate(st);
                if (tag.equals("aSubsequentDueDate"))
                    aSubsequentDueDate = objHelpers.getDateOpt(st);
                if (tag.equals("aApplicationDate"))
                    aApplicationDate = objHelpers.getDate(st);
                if (tag.equals("aResendFlag"))
                    aResendFlag = objHelpers.getBooleanOpt(st);
                if (tag.equals("aWireCenter"))
                    aWireCenter = objHelpers.getStringOpt(st);
                if (tag.equals("aRateCenter"))
                    aRateCenter = objHelpers.getStringOpt(st);
                if (tag.equals("aTelephoneNumberOrderPairs"))
                {
                    //TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs  

                    ArrayList aList = new ArrayList();
                    TelephoneNumberOrder aTelephoneNumberOrder = null;
                    TelephoneNumberOrderOpt aOldTelephoneNumberOrder = null;
                    TelephoneNumberPortingStatusOpt aTNPSO = null;
                    String sToken = TestClient.nextToken(st);
                    boolean pairsExists = false;


                    while ((sToken != null) && (!sToken.equalsIgnoreCase("END")))
                    {
                        aOldTelephoneNumberOrder = new TelephoneNumberOrderOpt();
                        aOldTelephoneNumberOrder._default();

                        if (sToken.equalsIgnoreCase("aOldTelephoneNumberOrder"))
                        {
                            TelephoneNumberOrder aTNO = new TelephoneNumberOrder();
                            aTNO.aTelephoneNumberType = objHelpers.getStringOpt(st);
                            aTNO.aTelephoneNumber = objHelpers.getStringOpt(st);
                            aTNO.aRequesterId = objHelpers.getStringOpt(st);
                            aTNO.aActivityIndicator = objHelpers.getStringOpt(st);
                            aTNO.aActionInd = TestClient.nextToken(st);
                            aTNO.aTNSourcePool = objHelpers.getStringOpt(st);
                            aTNO.aTNSourcePoolUpdateNotifier = objHelpers.getBooleanOpt(st);
                            aTNO.aError = new ExceptionDataOpt();
                            aTNO.aError._default();
                            TelephoneNumberPortingStatus aTNPS = new TelephoneNumberPortingStatus();
                            aTNPS.aRetainedPortingIndicator = objHelpers.getStringOpt(st);
                            aTNPS.aNonRetainedPortingIndicator = objHelpers.getStringOpt(st);
                            aTNPS.aOldProvider = objHelpers.getStringOpt(st);
                            aTNPS.aNewProvider = objHelpers.getStringOpt(st);
                            aTNPS.aLocalRoutingNumber = objHelpers.getStringOpt(st);
                            aTNO.aTelephoneNumberPortingStatus = new TelephoneNumberPortingStatusOpt();
                            aTNO.aTelephoneNumberPortingStatus.theValue(aTNPS);

                            aOldTelephoneNumberOrder.theValue(aTNO);
                            sToken = TestClient.nextToken(st);
                        }
                        if (sToken.equalsIgnoreCase("aTelephoneNumberOrder"))
                        {
                            aTelephoneNumberOrder = new TelephoneNumberOrder();
                            aTelephoneNumberOrder.aTelephoneNumberType = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aTelephoneNumber = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aRequesterId = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aActivityIndicator = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aActionInd = TestClient.nextToken(st);
                            aTelephoneNumberOrder.aTNSourcePool = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier = objHelpers.getBooleanOpt(st);
                            aTelephoneNumberOrder.aError = new ExceptionDataOpt();
                            aTelephoneNumberOrder.aError._default();
                            TelephoneNumberPortingStatus aTNPS = new TelephoneNumberPortingStatus();
                            aTNPS.aRetainedPortingIndicator = objHelpers.getStringOpt(st);
                            aTNPS.aNonRetainedPortingIndicator = objHelpers.getStringOpt(st);
                            aTNPS.aOldProvider = objHelpers.getStringOpt(st);
                            aTNPS.aNewProvider = objHelpers.getStringOpt(st);
                            aTNPS.aLocalRoutingNumber = objHelpers.getStringOpt(st);
                            aTelephoneNumberOrder.aTelephoneNumberPortingStatus = new TelephoneNumberPortingStatusOpt();
                            aTelephoneNumberOrder.aTelephoneNumberPortingStatus.theValue(aTNPS);
                        }

                        aList.add(new TelephoneNumberOrderPair(aOldTelephoneNumberOrder, aTelephoneNumberOrder));
                        sToken = TestClient.nextToken(st);
                        pairsExists = true;
                    } // while loop

                    if ( pairsExists )
                        aTelephoneNumberOrderPairs =
                        (TelephoneNumberOrderPairSeqOpt) IDLUtil.toOpt(
                            TelephoneNumberOrderPairSeqOpt.class,
                            (TelephoneNumberOrderPair[]) aList.toArray(new TelephoneNumberOrderPair[aList.size()]));
                    else
                    {
                        aTelephoneNumberOrderPairs = new TelephoneNumberOrderPairSeqOpt();
                        aTelephoneNumberOrderPairs.__default();
                    }

                }
                if (tag.equals("aProperties"))
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
     * method: buildIDLObjectsFromXML(String)
     */
    public void buildIDLObjectsFromXML(String xmlFileName)
        throws Exception {
        //Read XML test data file
        requestXML = readFile(xmlFileName, true);
        
        //Convert XML to IDL object
        MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);
        
        //build request IDL objects from MMarshalObject
        _sendTNAssignmentOrderRequestMsg aRequestMsg = (_sendTNAssignmentOrderRequestMsg)aRequest;

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
     * Method displayInput.
     */
    protected void displayInput()
    {
        if (isXMLTestData) {
            TestClient.log.printLog( "INPUT DATA: \n" +  requestXML );
            return;
        }

        TestClient.log.printLog(
            "INPUT DATA:aContext<"
                + (new BisContextBisHelper((aContext != null) ? aContext : TestClient.myBisContext)).toString()
                + ">");
        try
        {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderNumber<" + aSOACServiceOrderNumber + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderNumber<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderCorrectionSuffix<" + aSOACServiceOrderCorrectionSuffix + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderCorrectionSuffix<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aOrderNumber<" + aOrderNumber + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aOrderNumber<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aOrderActionType<" + aOrderActionType + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aOrderActionType<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aSubActionType<" + aSubActionType.theValue() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aSubActionType<null>");
        }

        try
        {
            TestClient.log.printLog(
                "INPUT DATA:aCompletionIndicator<" + (new BooleanOptBisHelper(aCompletionIndicator)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aCompletionIndicator<null>");
        }

        try
        {
            TestClient.log.printLog(
                "INPUT DATA:aServiceLocation<" + (new LocationBisHelper(aServiceLocation)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aServiceLocation<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aOriginalDueDate<" + (new EiaDateBisHelper(aOriginalDueDate)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aOriginalDueDate<null>");
        }

        try
        {
            TestClient.log.printLog(
                "INPUT DATA:aSubsequentDueDate<" + (new EiaDateOptBisHelper(aSubsequentDueDate)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aSubsequentDueDate<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aApplicationDate<" + (new EiaDateBisHelper(aApplicationDate)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aApplicationDate<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aResendFlag<" + (new BooleanOptBisHelper(aResendFlag)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aResendFlag<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aWireCenter<" + aWireCenter.theValue() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aWireCenter<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aRateCenter<" + aRateCenter.theValue() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aRateCenter<null>");
        }

        try
        {
            TestClient.log.printLog("INPUT DATA:aTelephoneNumberOrderPairs:");
            TelephoneNumberOrderPair[] aTNAPs = aTelephoneNumberOrderPairs.theValue();

            for (int i = 0; i < aTNAPs.length; i++)
            {
                try
                {
                    TestClient.log.printLog(
                        "INPUT DATA:"
                            + "["
                            + i
                            + "].aOldTelephoneNumberOrder<"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumberType.theValue()
                            + "|"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aTelephoneNumber.theValue()
                            + "|"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aRequesterId.theValue()
                            + "|"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aActivityIndicator.theValue()
                            + "|"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aActionInd
                            + "|"
                            + aTNAPs[i].aOldTelephoneNumberOrder.theValue().aTNSourcePool.theValue()
                            + "|"
                            + new BooleanOptBisHelper(aTNAPs[i].aOldTelephoneNumberOrder.theValue().aTNSourcePoolUpdateNotifier)
                                .toString()
                            + "|>");
                }
                catch (Exception e)
                {
                    TestClient.log.printLog("[" + i + "].aOldTelephoneNumberOrder<null>");
                }
                TestClient.log.printLog(
                    "INPUT DATA:"
                        + "["
                        + i
                        + "].aTelephoneNumberOrder<"
                        + validOpt(aTNAPs[i].aTelephoneNumberOrder.aTelephoneNumberType)
                        + "|"
                        + validOpt(aTNAPs[i].aTelephoneNumberOrder.aTelephoneNumber)
                        + "|"
                        + validOpt(aTNAPs[i].aTelephoneNumberOrder.aRequesterId)
                        + "|"
                        + validOpt(aTNAPs[i].aTelephoneNumberOrder.aActivityIndicator)
                        + "|"
                        + aTNAPs[i].aTelephoneNumberOrder.aActionInd
                        + "|"
                        + validOpt(aTNAPs[i].aTelephoneNumberOrder.aTNSourcePool)
                        + "|"
                        + new BooleanOptBisHelper(aTNAPs[i].aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier).toString()
                        + "|>");
            }
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aTelephoneNumberOrderPairs<null>");
        }

        try
        {
            TestClient.log.printLog(
                "INPUT DATA:aProperties<" + (new ObjectPropertySeqOptBisHelper(aProperties)).toString() + ">");
        }
        catch (Exception e)
        {
            TestClient.log.printLog("INPUT DATA:aProperties<null>");
        }
    }

    private String validOpt(StringOpt aStringOpt)
    {
        try
        {
            return aStringOpt.theValue();
        }
        catch (Exception e)
        {}
        return "null";
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.facades.testing.rm.TestRMCaseBase#initSoapMessage(java.lang.String, java.util.Properties)
     */
    protected void initSoapMessage(String paramList, Properties props)
    {
        // parse the argument from the test case.
        this.init(paramList);

        this.displayInput();

//      sendMessage(props, RmFacadeSOABuilder._sendF1F2Order, createMessage());
        sendMessage(props, RmFacadeSOABuilder._sendFacilityAssignmentOrder, createMessage());
    }

    /**
    * createMessage
    */
    private String createMessage()
    {
        String xml = null;
        try
        {

            _sendTNAssignmentOrderRequest request =
                new _sendTNAssignmentOrderRequest(
                    this.aContext,
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

            _sendTNAssignmentOrderRequestBISMsg requestMsg = new _sendTNAssignmentOrderRequestBISMsg(request);

            MMarshalObject msgs = requestMsg;
            //Encode them into XML
            xml = VAXBWriter.encode(msgs);

            //add SOAP TAGS
//          xml = addSOAPTagstoXML(xml, RmFacadeSOABuilder._sendF1F2Order);
            xml = addSOAPTagstoXML(xml, RmFacadeSOABuilder._sendFacilityAssignmentOrder);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        return xml;
    }

    protected void processMessage(String paramList) throws TestCaseException
    {}
    protected void initSoapHttpMessage(String ParamList, Properties props)
    {
        TestClient.log.printLog("SOAP/HTTP not implemented");
    }
}