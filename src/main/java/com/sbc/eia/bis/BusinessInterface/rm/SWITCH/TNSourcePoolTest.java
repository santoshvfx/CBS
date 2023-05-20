//$Id: TNSourcePoolTest.java,v 1.2.4.2 2007/03/28 17:50:28 mb6834 Exp $
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
//# --------------------------------------------------------------------
//# 06/23/2006  Rene Duka             Creation.

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.rm.database.CvoipOrderRow;
import com.sbc.eia.bis.rm.database.CvoipRulesRow;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatus;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberPortingStatusOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerHelper;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerTest;
import com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TrnsptType_e;

/**
 * @author rd2842
 */


public class TNSourcePoolTest {

    protected BisContext callerContext = null;
    private Utility aUtility = null;

    public static void main(String args[]) {

        Hashtable myProperties = new Hashtable();
        myProperties.put("STDOUT","TRUE");
        myProperties.put("BIS_NAME","RM13.0");
        myProperties.put("BIS_VERSION","13.0.1");
        myProperties.put("LOG_POLICY_PATH",""); 
        myProperties.put("bis.platform","dev");
        myProperties.put("APPLDATA", "rd2842");
        myProperties.put("SWITCHSERVER_APPLDATA", "");
        myProperties.put("SWITCHSERVER_TIMEOUT", "60");
        myProperties.put("GWSVCS_CLNTUUID", "nrmbis");
        myProperties.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
        myProperties.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");

/*
jdbcDATA_SOURCE_NAME=jdbc/NAMDataSource9i
jdbcUSERID=RMUSER
jdbcPASSWORD=V^FKq&5\u000Bw/"3y4\u0008NN
jdbcURL=jdbc:oracle:thin:@cidb2001.sbc.com:1525:ostl638
jdbcDRIVER=oracle.jdbc.driver.OracleDriver
jdbcUSE_CONNECTION_POOL=DATASOURCE
*/

        myProperties.put("jdbcDATA_SOURCE_NAME", "jdbc/NAMDataSource9i");
        myProperties.put("jdbcUSERID", "RMUSER");
        myProperties.put("jdbcPASSWORD", "TH75TGV");
        myProperties.put("jdbcURL", "jdbc:oracle:thin:@cidb2001.sbc.com:1525:ostl638");
        myProperties.put("jdbcDRIVER", "oracle.jdbc.driver.OracleDriver");
        myProperties.put("jdbcUSE_CONNECTION_POOL", "DATASOURCE");

        //myProperties.put("CVOIP_SWITCH_HIPCS_WC", "999001");

        //TestLogger myLogger = new TestLogger();
        DummyUtility aUtility = new DummyUtility();
        
        // aBisContext
        BisContext aContext = BisContextHelper.setBisContext(null, null, null, null,   myProperties);

        // aSOACServiceOrderNumber
        String aSOACServiceOrderNumber      = "C910200L1";

        // aOriginalDueDate
        short yyyy = 2005;  // 2005
        short mm =  10;     // 10
        short dd =  30;     // 30
        EiaDate aOriginalDueDate            = new EiaDate(yyyy, mm, dd);

        // aTelephoneNumberOrderPairs
        TelephoneNumberOrderPairSeqOpt  aTelephoneNumberOrderPairs = prepareTelephoneNumberOrderPairSeqOpt();

        // aCvoipOrderRow
        CvoipOrderRow[] aCvoipOrderRows = null;
        //CvoipOrderRow[] aCvoipOrderRows = prepareCvoipOrderRows();
        
        // aCvoipRulesRow
        CvoipRulesRow[] aCvoipRulesRows = prepareCvoipRulesRows();

        int aType = 2;
        // 1 = Test Update of CVOIP ORDER table            
        if (aType == 1) {
            // update table
            boolean aUpdateOk = updateCvoipOrder(aUtility,
                                                  myProperties,
                                                  aContext,
                                                  aSOACServiceOrderNumber);
    
        }

        // 2 = Setup TNs for HIPCS Port
        if (aType == 2) {
            // prepare TNs in SWITCH
            String aWireCenter = "999001";
            String aRequestCategory = "SSCVOIP";
            String aTNList = "GAHANNAOHH";
            String[] aTNs = new String[2];
            aTNs[0] = "999-701-1101";
            aTNs[1] = "999-701-1102";
            
            boolean aPrepareOk = prepareTNsInSWITCH(aWireCenter,
                                                     aRequestCategory,
                                                     aTNList,
                                                     aTNs);
        }

        // 3 = Test TNs for HIPCS TN functionality
        if (aType == 3) {
            boolean portingInfoUpdated = true;
            // call to SWITCH
            try {
                TNSourcePool aTNSourcePool = new TNSourcePool(aUtility, myProperties);
    
                portingInfoUpdated = aTNSourcePool.updatePortingInfo(aContext,
                                                                     aSOACServiceOrderNumber,
                                                                     aOriginalDueDate,
                                                                     aTelephoneNumberOrderPairs,
                                                                     aCvoipOrderRows,
                                                                     aCvoipRulesRows);
                // print results
                System.out.println("Result is: " + portingInfoUpdated);
                TelephoneNumberOrderPair[] aTelephoneNumberOrderPair = aTelephoneNumberOrderPairs.theValue();
                for (int i=0; i < aTelephoneNumberOrderPair.length; i++) {
                    System.out.println("===> " + i);
                    System.out.println("TN          : " + aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTelephoneNumber.theValue());
                    if (!OptHelper.isStringOptEmpty(aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool)) {
                        System.out.println("TNSourcePool: " + aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool.theValue());
                        System.out.println("aTNSourcePoolUpdateNotifier: " + aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier.theValue());
                        System.out.println("aError.aCode               : " + aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aError.theValue().aCode.trim());
                        System.out.println("aError.aDescription        : " + aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aError.theValue().aDescription.trim());
                    }    
                    else {
                        System.out.println("TNSourcePool: ");
                    }
                }
            }
            catch (Exception e) {
                System.out.println("I found an Exception: " + e.getMessage());
            }
        }
    }

    public static CvoipOrderRow[] prepareCvoipOrderRows() {
/*
        String aSOAC_SO_NBR, 
        String aTN, 
        String aUPDATE_DATE 
 */

        CvoipOrderRow[] aCvoipOrderRows = new CvoipOrderRow[2];

        aCvoipOrderRows[0] = new CvoipOrderRow();
        aCvoipOrderRows[0].setSOAC_SO_NBR("Z910200L1");
        aCvoipOrderRows[0].setTN("111-111-1111");
        aCvoipOrderRows[0].setUPDATE_DATE("2006-06-21");

        aCvoipOrderRows[1] = new CvoipOrderRow();
        aCvoipOrderRows[1].setSOAC_SO_NBR("Z910200L1");
        aCvoipOrderRows[1].setTN("222-222-2222");
        aCvoipOrderRows[1].setUPDATE_DATE("2006-06-21");

        return aCvoipOrderRows;
    }


    public static CvoipRulesRow[] prepareCvoipRulesRows() {
/*
        String aORDER_ACTION_TYPE,
        String aORDER_ACTION_SUBTYPE,
        String aACTION_IND,
        String aACTIVITY_IND,
        String aORDER_EXISTS,
        String aTN_EXISTS,
        String aSOAC_FUNC_TYPE,
        String aSOAC_ACTION_IND,
        String aHIPCS_SWITCH_UPD,
        String aSWITCH_ACTION_IND,
        String aORDER_TABLE_ACTION,
        String aUPDATE_DATE)
*/
        CvoipRulesRow[] aCvoipRulesRows = new CvoipRulesRow[2];
        
        aCvoipRulesRows[0] = new CvoipRulesRow();
        aCvoipRulesRows[0].setORDER_ACTION_TYPE("aOrderActionType");
        aCvoipRulesRows[0].setORDER_ACTION_SUBTYPE("aOrderActionSubType");
        aCvoipRulesRows[0].setACTION_IND("aActionInd");
        aCvoipRulesRows[0].setACTIVITY_IND("aActivityInd");
        aCvoipRulesRows[0].setORDER_EXISTS("aOrderExists");
        aCvoipRulesRows[0].setTN_EXISTS("aTNExists");
        aCvoipRulesRows[0].setSOAC_FUNC_TYPE("aSOACFuncType");
        aCvoipRulesRows[0].setSOAC_ACTION_IND("aSOACActionInd");
        aCvoipRulesRows[0].setHIPCS_SWITCH_UPD("HIPCSUpdate");
        aCvoipRulesRows[0].setSWITCH_ACTION_IND("aSWITCHActionInd");
        aCvoipRulesRows[0].setORDER_TABLE_ACTION("OrderTableAction");
        aCvoipRulesRows[0].setUPDATE_DATE("UpdateDate");

        aCvoipRulesRows[1] = new CvoipRulesRow();
        aCvoipRulesRows[1].setORDER_ACTION_TYPE("Provide");
        aCvoipRulesRows[1].setORDER_ACTION_SUBTYPE(null);
        aCvoipRulesRows[1].setACTION_IND("I");
        aCvoipRulesRows[1].setACTIVITY_IND("I");
        aCvoipRulesRows[1].setORDER_EXISTS("false");
        aCvoipRulesRows[1].setTN_EXISTS("false");
        aCvoipRulesRows[1].setSOAC_FUNC_TYPE("PRE");
        aCvoipRulesRows[1].setSOAC_ACTION_IND("I");
        aCvoipRulesRows[1].setHIPCS_SWITCH_UPD("true");
        aCvoipRulesRows[1].setSWITCH_ACTION_IND("I");
        aCvoipRulesRows[1].setORDER_TABLE_ACTION("Insert Order");
        aCvoipRulesRows[1].setUPDATE_DATE("2006-06-21");

        return aCvoipRulesRows;
    }


    public static TelephoneNumberOrderPairSeqOpt prepareTelephoneNumberOrderPairSeqOpt() {
        
        TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairSeqOpt = new TelephoneNumberOrderPairSeqOpt();

        TelephoneNumberOrderPair aTnOrdPair[] = new TelephoneNumberOrderPair[3];

        // "CVOIP"
        String aTNType1 = "CVOIP", aTNType2 = "CVOIP", aTNType3 = "CVOIP", aTNType4 = "CVOIP";
        // <Value>
        String aTN1 = "999-101-0101", aTN2 = "999-101-0102", aTN3 = "999-101-0103", aTN4 = "5069782222";
        // "OMS", "AMSS"
        String aRequesterID1 = "OMS", aRequesterID2 = "OMS", aRequesterID3 = "OMS", aRequesterID4 = "OMS";
        // "I", "O"
        String aActivityIndicator1 = "I", aActivityIndicator2 = "I", aActivityIndicator3 = "I", aActivityIndicator4 = "I";
        // "I", "D", "M", "C". "R"
        String aActionIndicator1 = "I", aActionIndicator2 = "I", aActionIndicator3 = "I", aActionIndicator4 = "I";
        // "HIPCS"
        String aTNSourcePool1 = "HIPCS", aTNSourcePool2 = "", aTNSourcePool3 = "HIPCS", aTNSourcePool4 = "HIPCS";
        // "INVU" ...
        String aRetainedPortingIndicator1 = "INVU", aRetainedPortingIndicator2 = "INVU", aRetainedPortingIndicator3 = "INVU", aRetainedPortingIndicator4 = "INVU";
        // "RTN", "POUT" ...
        String aNonRetainedPortingIndicator1 = "RTN", aNonRetainedPortingIndicator2 = "RTN", aNonRetainedPortingIndicator3 = "RTN", aNonRetainedPortingIndicator4 = "RTN";
        // <Value>
        String aOldProvider1 = "aOP", aOldProvider2 = "aOP", aOldProvider3 = "aOP", aOldProvider4 = "aOP";
        // <Value>
        String aNewProvider1 = "aNP", aNewProvider2 = "aNP", aNewProvider3 = "aNP", aNewProvider4 = "aNP";
        // <Value>
        String aLocalRoutingNumber1 = "aLRN", aLocalRoutingNumber2 = "aLRN", aLocalRoutingNumber3 = "aLRN", aLocalRoutingNumber4 = "5069782222";

        aTnOrdPair[0] = prepareTelephoneNumberOrderPair(aTNType1, aTN1, aRequesterID1, aActivityIndicator1, aActionIndicator1, aTNSourcePool1, aRetainedPortingIndicator1, aNonRetainedPortingIndicator1, aOldProvider1, aNewProvider1, aLocalRoutingNumber1);
        aTnOrdPair[1] = prepareTelephoneNumberOrderPair(aTNType2, aTN2, aRequesterID2, aActivityIndicator2, aActionIndicator2, aTNSourcePool2, aRetainedPortingIndicator2, aNonRetainedPortingIndicator2, aOldProvider2, aNewProvider2, aLocalRoutingNumber2);
        aTnOrdPair[2] = prepareTelephoneNumberOrderPair(aTNType3, aTN3, aRequesterID3, aActivityIndicator3, aActionIndicator3, aTNSourcePool3, aRetainedPortingIndicator3, aNonRetainedPortingIndicator3, aOldProvider3, aNewProvider3, aLocalRoutingNumber3);
//        aTnOrdPair[3] = prepareTelephoneNumberOrderPair(aTNType4, aTN4, aRequesterID4, aActivityIndicator4, aActionIndicator4, aTNSourcePool4, aRetainedPortingIndicator4, aNonRetainedPortingIndicator4, aOldProvider4, aNewProvider4, aLocalRoutingNumber4);
        
        aTelephoneNumberOrderPairSeqOpt.theValue(aTnOrdPair);
        
        return aTelephoneNumberOrderPairSeqOpt;
            
    }

    public static  TelephoneNumberOrderPair prepareTelephoneNumberOrderPair(
        String aTNType,
        String aTN,
        String aRequesterID,
        String aActivityIndicator,
        String aActionIndicator,
        String aTNSourcePool,
        String aRetainedPortingIndicator,
        String aNonRetainedPortingIndicator,
        String aOldProvider,
        String aNewProvider,
        String aLocalRoutingNumber) {

        TelephoneNumberOrder aTNOrd = prepareTelephoneNumberOrder(aTNType,
                                                                  aTN,
                                                                  aRequesterID,
                                                                  aActivityIndicator,
                                                                  aActionIndicator,
                                                                  aTNSourcePool,
                                                                  aRetainedPortingIndicator,
                                                                  aNonRetainedPortingIndicator,
                                                                  aOldProvider,
                                                                  aNewProvider,
                                                                  aLocalRoutingNumber); 
                                                                  
        TelephoneNumberOrderOpt aTNOrdOpt = prepareTelephoneNumberOrderOpt(aTN);

        TelephoneNumberOrderPair aTNNumOrdpair = new TelephoneNumberOrderPair(aTNOrdOpt,
                                                                              aTNOrd);

        return aTNNumOrdpair;   
    }
    

    public static TelephoneNumberOrderOpt prepareTelephoneNumberOrderOpt(String TnNum) {
        StringOpt aStOpt = new StringOpt();
        String aSt       = new String();
        StringOpt aTnOpt = new StringOpt();
        aTnOpt.theValue(TnNum);

        TelephoneNumberOrder aTN = new TelephoneNumberOrder(null,aTnOpt,null,null,null,null,null,null,null);
        
        TelephoneNumberOrderOpt aTnNumOrdOpt  = new TelephoneNumberOrderOpt();
        aTnNumOrdOpt.theValue(aTN);
        
        return aTnNumOrdOpt;
    }

    public static TelephoneNumberOrder prepareTelephoneNumberOrder(
        String aTNType,
        String aTN,
        String aRequesterID,
        String aActivityIndicator,
        String aActionIndicator,
        String aTNSourcePool,
        String aRetainedPortingIndicator,
        String aNonRetainedPortingIndicator,
        String aOldProvider,
        String aNewProvider,
        String aLocalRoutingNumber) {
/*                    
  StringOpt aTelephoneNumberType
  StringOpt aTelephoneNumber
  StringOpt aRequesterId
  StringOpt aActivityIndicator
  String aActionInd
  StringOpt aTNSourcePool
  BooleanOpt aTNSourcePoolUpdateNotifier        ==> updated by SWITCH processing
  ExceptionDataOpt aError                       ==> updated by SWITCH processing
  TelephoneNumberPortingStatusOpt aTelephoneNumberPortingStatus
*/

        StringOpt aStOpt = new StringOpt();
        String aSt       = new String();
        StringOpt aTnOpt = new StringOpt();
        aTnOpt.theValue(aTN);
        
        TelephoneNumberOrder aTNO = new TelephoneNumberOrder();
        aTNO.aTelephoneNumberType = (StringOpt) IDLUtil.toOpt(aTNType);
        aTNO.aTelephoneNumber = (StringOpt) IDLUtil.toOpt(aTN);
        aTNO.aRequesterId = (StringOpt) IDLUtil.toOpt(aRequesterID);
        aTNO.aActivityIndicator = (StringOpt) IDLUtil.toOpt(aActivityIndicator);
        aTNO.aActionInd = aActionIndicator;
        aTNO.aTNSourcePool = (StringOpt) IDLUtil.toOpt(aTNSourcePool);
/*
                boolean aValue = Boolean.valueOf(aConnectivityStatus.theValue()).booleanValue();
                BooleanOpt aBooleanOpt = new BooleanOpt();
                aBooleanOpt.theValue(aValue);
                aDSLAM.aConnectivityStatus = aBooleanOpt;
*/ 
        //aTNO.aTNSourcePoolUpdateNotifier = (BooleanOpt) IDLUtil.toOpt(BooleanOpt.class, new BooleanOpt());
        //aTNO.aError = (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, new ExceptionDataOpt());
        aTNO.aTNSourcePoolUpdateNotifier = new BooleanOpt();
        aTNO.aError = new ExceptionDataOpt();
        aTNO.aTelephoneNumberPortingStatus = prepareTelephoneNumberPortingStatusOpt(aRetainedPortingIndicator, 
                                                                                    aNonRetainedPortingIndicator, 
                                                                                    aOldProvider, 
                                                                                    aNewProvider, 
                                                                                    aLocalRoutingNumber);
        
        return aTNO;
        
    }
    
    public static TelephoneNumberPortingStatusOpt prepareTelephoneNumberPortingStatusOpt(
        String aRetainedPortingIndicator,
        String aNonRetainedPortingIndicator,
        String aOldProvider,
        String aNewProvider,
        String aLocalRoutingNumber) {

/*
  StringOpt aRetainedPortingIndicator
  StringOpt aNonRetainedPortingIndicator
  StringOpt aOldProvider
  StringOpt aNewProvider
  StringOpt aLocalRoutingNumber
*/
        TelephoneNumberPortingStatusOpt aTelephoneNumberPortingStatusOpt = new TelephoneNumberPortingStatusOpt();

        TelephoneNumberPortingStatus aTelephoneNumberPortingStatus = new TelephoneNumberPortingStatus();

        aTelephoneNumberPortingStatus.aRetainedPortingIndicator = (StringOpt) IDLUtil.toOpt(aRetainedPortingIndicator);
        aTelephoneNumberPortingStatus.aNonRetainedPortingIndicator = (StringOpt) IDLUtil.toOpt(aNonRetainedPortingIndicator);
        aTelephoneNumberPortingStatus.aOldProvider = (StringOpt) IDLUtil.toOpt(aOldProvider);
        aTelephoneNumberPortingStatus.aNewProvider = (StringOpt) IDLUtil.toOpt(aNewProvider);
        aTelephoneNumberPortingStatus.aLocalRoutingNumber = IDLUtil.toOpt(aLocalRoutingNumber);

        aTelephoneNumberPortingStatusOpt.theValue(aTelephoneNumberPortingStatus);
        
        return aTelephoneNumberPortingStatusOpt;
    }
    
    public static boolean updateCvoipOrder(
        Utility aUtility,
        Hashtable aProperties,
        BisContext aContext,
        String aSOACServiceOrderNumber) {

        boolean aOk = true;
        
        String aSWITCHActionType = "O";
        String aTN = "9991019999";
        
        SWITCHServer aSWITCHServer = new SWITCHServer(aUtility, aProperties);

        try {
            aSWITCHServer.updateCVOIPOrderTable(aContext,
                                                aSWITCHActionType,
                                                aSOACServiceOrderNumber,
                                                aTN);
        
        } 
        catch (Exception e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
        }
        return aOk;
    }

    public static boolean prepareTNsInSWITCH(
        String aWireCenter,
        String aRequestCategory,
        String aTNList,
        String[] aTNs) {
            
        boolean aOk = true;

        Hashtable props = new Hashtable();
        props.put("APPLDATA", "ccmalet");
        props.put("SWITCHSERVER_APPLDATA", "");
        props.put("SWITCHSERVER_TIMEOUT", "60");
        props.put("GWSVCS_CLNTUUID", "nrmbis");
        props.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
        props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");

        try {
            com.sbc.gwsvcs.access.vicuna.EventResultPair aResponse = null;
            SWITCHServerTest aSWITCHServerTest = new SWITCHServerTest();
            SWITCHServerHelper aHelper = new SWITCHServerHelper(props, aSWITCHServerTest);
    
            Header_t aHdr = new Header_t("BIS", "BIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

            NpaPrfxLn_t[] zNpaPrfxLn_t = new NpaPrfxLn_t[aTNs.length];

            // select numbers (SELTN)
            for (int i=0; i < aTNs.length; i++) {
                NpaPrfxLn_t aNpaPrfxLn_t = new NpaPrfxLn_t();
                StringTokenizer st = new StringTokenizer(aTNs[i], "-");
                if (st.hasMoreElements())
                    aNpaPrfxLn_t.NPA = st.nextToken().trim();
                if (st.hasMoreElements())
                    aNpaPrfxLn_t.PRFX_CD = st.nextToken().trim();
                if (st.hasMoreElements())
                    aNpaPrfxLn_t.LN = st.nextToken().trim();

                TnSeltVarb_t tnSeltVarb = new TnSeltVarb_t("1", new NpaPrfx_t("", ""), "", aNpaPrfxLn_t);
                SelTnReq_t selTnReq = new SelTnReq_t(aRequestCategory, tnSeltVarb, new Lst_t(aTNList));
                SwitchSelTnReq_t switchSelTnReq  = new SwitchSelTnReq_t(aHdr, selTnReq, aWireCenter);
                aResponse = aHelper.switchSelTnReq(null, null, 0, switchSelTnReq);
    
                System.out.println("Received event: " + aResponse.getEventNbr());
    
                SwitchSelTnResp_t aResult1 = (SwitchSelTnResp_t) aResponse.getTheObject();
                for (int j=0; j < aResult1.SelTnResp.Nbr.length; j++) {
                    System.out.println(aResult1.SelTnResp.Nbr[j].TN.NPA + " " +
                                       aResult1.SelTnResp.Nbr[j].TN.PRFX_CD + " " +
                                       aResult1.SelTnResp.Nbr[j].TN.LN);
                }
                System.out.println("SwitchSelTnReq_t Ok: TN ==> " + aTNs[i]);
                zNpaPrfxLn_t[i] = aNpaPrfxLn_t;
            }

            // Return numbers
            RtnTnReq_t rtnTnReq = new RtnTnReq_t("A", "1", zNpaPrfxLn_t, new NpaPrfxLn_t[] {});
            SwitchRtnTnReq_t switchRtnTnReq  = new SwitchRtnTnReq_t(aHdr, rtnTnReq, aWireCenter);
            aResponse = aHelper.switchRtnTnReq(null, null, 0, switchRtnTnReq);

            System.out.println("Received event: " + aResponse.getEventNbr());

            SwitchRtnTnResp_t aResult2 = (SwitchRtnTnResp_t) aResponse.getTheObject();
            for (int k=0; k < aResult2.Umsg.length; k++) {
                System.out.println(aResult2.Umsg[k].MSG_TX);
                System.out.println("Error in SwitchRtnTnReq_t");
                return false;
            }
            System.out.println("SwitchRtnTnReq_t Ok");
        }
        catch (SWITCHServerException e) {
            System.out.println("SWITCHServerException: " + e.getExceptionCode() + " " + e.getMessage());
        }
        catch (ServiceException e) {
            System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
            e.printStackTraces();
        }
        return aOk;
    }

}
