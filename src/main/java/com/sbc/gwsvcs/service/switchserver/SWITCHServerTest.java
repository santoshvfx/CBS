// $Id: SWITCHServerTest.java,v 1.5 2006/06/09 18:32:43 rd2842 Exp $

package com.sbc.gwsvcs.service.switchserver;

import java.util.*;
import com.sbc.gwsvcs.service.switchserver.exceptions.*;
import com.sbc.gwsvcs.service.switchserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Provides standalone testing.
 * Creation date: (2/26/01 12:32:12 PM)
 * @author: Creighton Malet
 */
public class SWITCHServerTest implements com.sbc.bccs.utilities.Logger {
/**
 * Class constructor.
 */
public SWITCHServerTest() {
    super();
}
/**
 * Implementation of Logger.log().
 * Creation date: (2/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
    System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * Executes the program.
 * Creation date: (2/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
    Hashtable props = new Hashtable();
    props.put("APPLDATA", "ccmalet");
    props.put("SWITCHSERVER_APPLDATA", "");
    props.put("SWITCHSERVER_TIMEOUT", "60");
    props.put("GWSVCS_CLNTUUID", "nrmbis");
    props.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
    props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");

    try
    {
        com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
        SWITCHServerTest aSWITCHServerTest = new SWITCHServerTest();
        SWITCHServerHelper helper = new SWITCHServerHelper(props, aSWITCHServerTest);

        Header_t hdr = new Header_t("BIS", "BIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

        // Inq NTU
        InqNtuReq_t InqNtuReq = new InqNtuReq_t(new Ex_t("ME","MECP.IR01104.003.02-011")," "," ",new Dt_t()," ","D");
        SwitchInqNtuReq_t switchInqNtuRequest  = new SwitchInqNtuReq_t(hdr, InqNtuReq, "501847");
        response = helper.switchInqNtuReq(null, null, 0, switchInqNtuRequest);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchInqNtuResp_t inqNtuResult = (SwitchInqNtuResp_t)response.getTheObject();

        if (inqNtuResult.InqNtuResp.length > 0 )
            for (int i = 0; i < inqNtuResult.InqNtuResp.length; i++)
                for (int x = 0 ; x <inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX.length; x++)

                    System.out.println("OTPT_LN_DESC_TX[" + x + "]<" +
                        inqNtuResult.InqNtuResp[i].OTPT_LN_DESC_TX[x] + ">");
        else
            for (int i = 0; i < inqNtuResult.Umsg.length; i++)
                System.out.println( "MSG_TX<" + inqNtuResult.Umsg[i].MSG_TX + ">");

        // Inq CKT
        InqCktReq_t InqCktReq = new InqCktReq_t(new Ex_t("TN", "904-544-3008"),"D");
        SwitchInqCktReq_t switchInqCktRequest  = new SwitchInqCktReq_t(hdr, InqCktReq, "");
        response = helper.switchInqCktReq(null, null, 0, switchInqCktRequest);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchInqCktResp_t result = (SwitchInqCktResp_t)response.getTheObject();
        System.out.println("ExchKeyId: <" + result.ExchKeyId + ">");

        // Inquire numbers
        NpaPrfx_t[] npaPrfxArray = new NpaPrfx_t[] { }; // new NpaPrfx_t("510", "753") };
        TnInqReq_t tnInqReq = new TnInqReq_t(new Ex_t("", ""), npaPrfxArray, "WORD", "3", "5443***", "", "", "Y");
        SwitchTnInqReq_t switchTnInqRequest  = new SwitchTnInqReq_t(hdr, tnInqReq, "904544");
        response = helper.switchTnInqReq(null, null, 0, switchTnInqRequest);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchTnInqResp_t result1 = (SwitchTnInqResp_t)response.getTheObject();
        for (int i = 0; i < result1.TnInqResp.length; i++)
        {
            System.out.println(result1.TnInqResp[i].Ex.SWITCH_ID + " " +
                result1.TnInqResp[i].TN_RLS_DT_TX);
        }


        // Update numbers
        DtTm_t dTtM = new DtTm_t(new Dt_t("1997", "05", "05"), new Tm_t("00", "00", "00"), "");
        Updopt_t updOpt = new Updopt_t("", "", "", "",
            //new ComnUattr_t("", "", new Asglim_t[] {}, "", "", "", "", "", new Dt_t("", "", ""), "", "", "", "", "", "", "", ""),
            new ComnUattr_t("", "", new Asglim_t[] {}, "", "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", new Dt_t("", "", ""), "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", "", "", "", "", "", ""),
            new String[] {}, new Dt_t[] {}, new Dt_t("", "", ""), new Dt_t("", "", ""), new Comp_t[] {},
            new Ex_t[] {}, new Ex_t("", ""), new Ex_t("", ""));
        Ntu_t ntu = new Ntu_t(new Ex_t("", ""), "", "",
            //new ComnUattr_t("", "", new Asglim_t[] {}, "", "", "", "", "", new Dt_t("", "", ""), "", "", "", "", "20010302", "", "", ""),
            new ComnUattr_t("", "", new Asglim_t[] {}, "", "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", new Dt_t("", "", ""), "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", "", "", "", "", "", ""),
            "", "", new Fctr_t(new Ex_t("", ""), ""), new Ex_t("", ""), new Ex_t[] {});
        TnUpdReq_t[] tnUpdReq = new TnUpdReq_t[] { new TnUpdReq_t(new Ex_t("TN", "904-544-3008"), "", "", "", "CHG", "", dTtM, "", "", updOpt, "MOD", "", ntu, "", "") };
        SwitchTnUpdReq_t switchTnUpdRequest  = new SwitchTnUpdReq_t(hdr, tnUpdReq, "904544");
/*      response = helper.switchTnUpdReq(null, null, 0, switchTnUpdRequest);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchTnUpdResp_t result2 = (SwitchTnUpdResp_t)response.getTheObject();
        for (int i = 0; i < result2.Umsg.length; i++)
        {
            System.out.println(result2.Umsg[i].MSG_NBR + " " +
                result2.Umsg[i].MSG_TX);
        }
*/

        // Select numbers (SELTN)
        TnSeltVarb_t tnSeltVarb = new TnSeltVarb_t("1", new NpaPrfx_t("", ""), "", new NpaPrfxLn_t("", "", ""));
        SelTnReq_t selTnReq = new SelTnReq_t("BIS", tnSeltVarb, new Lst_t("SEATTLEB"));
        SwitchSelTnReq_t switchSelTnReq  = new SwitchSelTnReq_t(hdr, selTnReq, "206883");
/*      response = helper.switchSelTnReq(null, null, 0, switchSelTnReq);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchSelTnResp_t result3 = (SwitchSelTnResp_t)response.getTheObject();
        for (int i = 0; i < result3.SelTnResp.Nbr.length; i++)
        {
            System.out.println( result3.SelTnResp.Nbr[i].TN.NPA + " " +
                                result3.SelTnResp.Nbr[i].TN.PRFX_CD + " " +
                                result3.SelTnResp.Nbr[i].TN.LN);
        }
*/


        // Select numbers (SELTNE)
        TneSeltVarb_t tneSeltVarb = new TneSeltVarb_t("1", new NpaPrfx_t("", ""), "", new GoodInfo_t("12", "8831***"));
        SelTneReq_t selTneReq = new SelTneReq_t("BIS", tneSeltVarb, new Lst_t("SEATTLEB"));
        SwitchSelTneReq_t switchSelTneReq  = new SwitchSelTneReq_t(hdr, selTneReq, "206883");
/*      response = helper.switchSelTneReq(null, null, 0, switchSelTneReq);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchSelTnResp_t result4 = (SwitchSelTnResp_t)response.getTheObject();
        for (int i = 0; i < result4.SelTnResp.Nbr.length; i++)
        {
            System.out.println( result4.SelTnResp.Nbr[i].TN.NPA + " " +
                                result4.SelTnResp.Nbr[i].TN.PRFX_CD + " " +
                                result4.SelTnResp.Nbr[i].TN.LN);
        }
*/

        // Return numbers
        RtnTnReq_t rtnTnReq = new RtnTnReq_t("", "", new NpaPrfxLn_t[] {}, new NpaPrfxLn_t[] { new NpaPrfxLn_t("206", "883", "1000") });
        SwitchRtnTnReq_t switchRtnTnReq  = new SwitchRtnTnReq_t(hdr, rtnTnReq, "206883");
/*      response = helper.switchRtnTnReq(null, null, 0, switchRtnTnReq);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchRtnTnResp_t result5 = (SwitchRtnTnResp_t)response.getTheObject();
        for (int i = 0; i < result5.Umsg.length; i++)
        {
            System.out.println( result5.Umsg[i].MSG_TX);
        }
*/
        // Cancel TN Reservation, Port HIPCS TN, Cancel HIPCS TN Port
        WsiNtuReq_t wsiNtuReq = new WsiNtuReq_t( new Ex_t ( "TN", "904-544-3008"), "CHG" );
        SwitchWsiNtuReq_t switchWsiNtuReq = new SwitchWsiNtuReq_t( hdr, wsiNtuReq, "904544");
        response = helper.switchWsiNtuReq(null, null, 0, switchWsiNtuReq);
        System.out.println("Received event: " + response.getEventNbr());
        SwitchWsiNtuResp_t result6 = (SwitchWsiNtuResp_t)response.getTheObject();

        System.out.println( "length is " + result6.Umsg.length );
        for (int i = 0; i < result6.Umsg.length; i++)
        {
            System.out.println( result6.Umsg[i].MSG_TX);
        }

        System.out.println( "INTRCPT_CD =" + result6.WsiNtuResp.INTRCPT_CD );

    }
    catch (SWITCHServerException e)
    {
        System.out.println("SWITCHServerException: " + e.getExceptionCode() + " " + e.getMessage());
    }
    catch (ServiceException e)
    {
        System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
        e.printStackTraces();
    }
}
}
