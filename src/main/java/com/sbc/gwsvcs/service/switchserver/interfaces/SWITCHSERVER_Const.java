package com.sbc.gwsvcs.service.switchserver.interfaces;
import org.omg.CORBA.*;

public interface SWITCHSERVER_Const extends org.omg.CORBA.Object { 
	final public static int ADDR_APPLDATA_LENGTH = (int) (129);
	final public static int SWITCH_WC_LNGTH = (int) (7);
	final public static int TAG_NXX_MAX = (int) (10);
	final public static int TAG_ASGLIM_MAX = (int) (6);
	final public static int EXCH_KEY_ID_LNGTH = (int) (7);
	final public static int TAG_EX_MAX = (int) (10);
	final public static int WC_CD_LNGTH = (int) (9);
	final public static int MSEG_CD_LNGTH = (int) (2);
	final public static int ORD_2_NBR_LNGTH = (int) (13);
	final public static int ORD_TYPE_2_CD_LNGTH = (int) (10);
	final public static int ASGNM_ACTN_CD_LNGTH = (int) (4);
	final public static int VIEW_DT_LNGTH = (int) (9);
	final public static int USER_MODE_IND_LNGTH = (int) (2);
	final public static int UUID_LNGTH = (int) (15);
	final public static int CONTEXT_LNGTH = (int) (31);
	final public static int HOST_NM_LNGTH = (int) (41);
	final public static int TRAN_ID_LNGTH = (int) (41);
	final public static int DESTINATION_LNGTH = (int) (121);
	final public static int MSG_NBR_LNGTH = (int) (9);
	final public static int MSG_TYPE_LNGTH = (int) (2);
	final public static int LIST_TYPE_LNGTH = (int) (2);
	final public static int TN_LN_CT_LNGTH = (int) (4);
	final public static int TN_PATT_SELT_OPT_LNGTH = (int) (5);
	final public static int SWITCH_TN_REQ_QTY_LNGTH = (int) (3);
	final public static int TN_WORD_PATT_LNGTH = (int) (8);
	final public static int EXCL_TN_IND_LNGTH = (int) (2);
	final public static int TN_TYPE_CD_LNGTH = (int) (2);
	final public static int TN_SPARE_IND_LNGTH = (int) (2);
	final public static int DISCT_ASGNM_CTGY_CD_LNGTH = (int) (8);
	final public static int DISCT_CO_TYPE_CD_LNGTH = (int) (6);
	final public static int TN_RLS_DT_TX_LNGTH = (int) (9);
	final public static int TN_RMK_TX_LNGTH = (int) (61);
	final public static int TN_LIST_NBR_LNGTH = (int) (4);
	final public static int TN_LIM_VALU_CD_LNGTH = (int) (4);
	final public static int TN_HI_RNGE_ID_LNGTH = (int) (25);
	final public static int TN_MASK_ID_LNGTH = (int) (25);
	final public static int TN_PARSE_CD_LNGTH = (int) (5);
	final public static int TN_UPD_FCN_CD_LNGTH = (int) (4);
	final public static int OPTNL_MSG_TX_LNGTH = (int) (10);
	final public static int INVNTY_ORD_NBR_LNGTH = (int) (14);
	final public static int STEP_ID_LNGTH = (int) (4);
	final public static int TAG_EMP_MAX = (int) (3);
	final public static int TAG_CHGDT_MAX = (int) (2);
	final public static int ACTN_CD_LNGTH = (int) (4);
	final public static int SOURCE_LNGTH = (int) (7);
	final public static int PORT_IND_LNGTH = (int) (2);
	final public static int RT_ZONE_LNGTH = (int) (4);
	final public static int CTX_NM_LNGTH = (int) (19);
	final public static int NPUB_IND_LNGTH = (int) (2);
	final public static int DLCT_IND_LNGTH = (int) (2);
	final public static int PTY_CKT_LNGTH = (int) (2);
	final public static int AU_LNGTH = (int) (6);
	final public static int CATY_LNGTH = (int) (7);
	final public static int CLS_SVC_USOC_CD_LNGTH = (int) (6);
	final public static int CTGY_CD_LNGTH = (int) (2);
	final public static int FOPTN_LNGTH = (int) (2);
	final public static int OTPT_LN_QTY_LNGTH = (int) (5);
	final public static int OTPT_LN_DESC_TX_LNGTH = (int) (81);
	final public static int HI_VALU_LNGTH = (int) (25);
	final public static int CKT_OPTN_IND_LNGTH = (int) (2);
	final public static int LAST_VIEW_IND_LNGTH = (int) (2);
	final public static int NTWK_UNIT_TYPE_CD_LNGTH = (int) (3);
	final public static int CA_PR_LOW_NBR_LNGTH = (int) (16);
	final public static int CA_PR_HI_RNGE_NBR_LNGTH = (int) (16);
	final public static int SWITCH_ID_NM_LNGTH = (int) (6);
	final public static int SWITCH_ID_LNGTH = (int) (46);
	final public static int YR_LNGTH = (int) (5);
	final public static int MO_LNGTH = (int) (3);
	final public static int DY_LNGTH = (int) (3);
	final public static int NU_TYPE_CD_LNGTH = (int) (5);
	final public static int NU_LOW_ID_LNGTH = (int) (25);
	final public static int SELT_STATE_CD_LNGTH = (int) (6);
	final public static int OPEN_SIDE_CD_LNGTH = (int) (5);
	final public static int INPT_SOPTN_CD_LNGTH = (int) (51);
	final public static int OTPT_SOPTN_CD_LNGTH = (int) (51);
	final public static int PROCSG_MODE_IND_LNGTH = (int) (4);
	final public static int ORD_WRK_TASK_IND_LNGTH = (int) (2);
	final public static int STS_3_CD_LNGTH = (int) (4);
	final public static int DATA_IND_LNGTH = (int) (2);
	final public static int CTRL_CD_LNGTH = (int) (2);
	final public static int CKT_2_ID_LNGTH = (int) (52);
	final public static int ASGNM_REQ_IND_LNGTH = (int) (2);
	final public static int FRM_RMK_TX_LNGTH = (int) (29);
	final public static int CKT_TERMN_ID_LNGTH = (int) (52);
	final public static int ASGN_USOC_CD_LNGTH = (int) (6);
	final public static int USOC_LNGTH = (int) (6);
	final public static int SWITCH_TYPE_CD_LNGTH = (int) (6);
	final public static int SELT_CD_LNGTH = (int) (2);
	final public static int USER_NM_LNGTH = (int) (9);
	final public static int RET_IND_LNGTH = (int) (2);
	final public static int INTRCPT_CD_LNGTH = (int) (4);
	final public static int NPA_LNGTH = (int) (4);
	final public static int PRFX_CD_LNGTH = (int) (4);
	final public static int LN_LNGTH = (int) (5);
	final public static int TIME_OFFSET_LNGTH = (int) (6);
	final public static int PNDG_IND_LNGTH = (int) (2);
	final public static int EMP_ID_LNGTH = (int) (9);
	final public static int AVDT_IND_LNGTH = (int) (2);
	final public static int RTE_IDX_LNGTH = (int) (5);
	final public static int TN_LIM_TYPE_CD_LNGTH = (int) (4);
	final public static int SYS_CONN_LNGTH = (int) (2);
	final public static int DIP_IND_LNGTH = (int) (2);
	final public static int GRP_ID_LNGTH = (int) (5);
	final public static int FDT_TX_LNGTH = (int) (6);
	final public static int ORD_TYPE_CD_LNGTH = (int) (2);
	final public static int CORR_CD_LNGTH = (int) (2);
	final public static int FND_SVC_IND_LNGTH = (int) (2);
	final public static int FND_CKT_IND_LNGTH = (int) (2);
	final public static int CKT_VIEW_CD_LNGTH = (int) (9);
	final public static int DLCT_CD_LNGTH = (int) (5);
	final public static int INACT_CD_LNGTH = (int) (2);
	final public static int INVAL_ID_LNGTH = (int) (13);
	final public static int CO_ADMN_TYPE_CD_LNGTH = (int) (6);
	final public static int EST_HUND_CLG_LOAD_NBR_LNGTH = (int) (2);
	final public static int ESNL_SVC_LN_IND_LNGTH = (int) (2);
	final public static int SVC_CLS_CD_LNGTH = (int) (2);
	final public static int CNDCTR_NBR_LNGTH = (int) (2);
	final public static int CO_TERMN_CD_LNGTH = (int) (2);
	final public static int SVC_GRAD_LNGTH = (int) (2);
	final public static int PLSG_IND_LNGTH = (int) (2);
	final public static int TRAN_QUAL_IND_LNGTH = (int) (2);
	final public static int SIGG_CD_LNGTH = (int) (2);
	final public static int SVC_TYPE_CD_2_LNGTH = (int) (2);
	final public static int HR_LNGTH = (int) (3);
	final public static int MTE_LNGTH = (int) (3);
	final public static int SECS_LNGTH = (int) (3);
	final public static int AIS_CD_LNGTH = (int) (2);
	final public static int ASGNM_CAPY_QTY_LNGTH = (int) (3);
	final public static int ASGNM_USE_QTY_LNGTH = (int) (3);
	final public static int AVAIL_CAPY_IND_LNGTH = (int) (2);
	final public static int CALL_CT_LNGTH = (int) (3);
	final public static int ORD_3_NBR_LNGTH = (int) (14);
	final public static int NEG_STATUS_LNGTH = (int) (2);
	final public static int RLS_DT_IND_LNGTH = (int) (2);
	final public static int TN_SELT_IND_LNGTH = (int) (2);
	final public static int SPCFC_FUNCLT_CD_LNGTH = (int) (6);
	final public static int DENY_NPYMT_IND_LNGTH = (int) (3);
	final public static int TR_CALL_IND_LNGTH = (int) (2);
	final public static int SSP_IND_LNGTH = (int) (2);
	final public static int CTRL_GRP_ID_LNGTH = (int) (8);
	final public static int RT_ZONE_NBR_LNGTH = (int) (4);
	final public static int CBL_NBR_LNGTH = (int) (11);
	final public static int PR_NBR_LNGTH = (int) (6);
	final public static int TX_80_LNGTH = (int) (81);
	final public static int TX_120_LNGTH = (int) (121);
	final public static int TX_1024_LNGTH = (int) (1025);
	final public static String SWITCHServerName = (String) ("SWITCHServer");
	final public static String SWITCHServerVersion = (String) ("11.0.0");
	final public static int SWITCHServerSvcID = (int) (10014);
	final public static int SWITCH_TN_MAX = (int) (99);
	final public static int SWITCH_SEGMNT_SIZE = (int) (16000);
	final public static int SwitchTnInqReq = (int) (5000);
	final public static int SwitchTnInqResp = (int) (5001);
	final public static int SwitchTnUpdReq = (int) (5010);
	final public static int SwitchTnUpdResp = (int) (5011);
	final public static int SwitchAssignableTnReq = (int) (5020);
	final public static int SwitchAssignableTnResp = (int) (5021);
	final public static int SwitchQueryCktReq = (int) (5030);
	final public static int SwitchQueryCktResp = (int) (5031);
	final public static int SwitchUpdCktReq = (int) (5040);
	final public static int SwitchUpdCktResp = (int) (5041);
	final public static int SwitchInqCktReq = (int) (5050);
	final public static int SwitchInqCktResp = (int) (5051);
	final public static int SwitchInqGrpReq = (int) (5060);
	final public static int SwitchInqGrpResp = (int) (5061);
	final public static int SwitchInqOrdReq = (int) (5080);
	final public static int SwitchInqOrdResp = (int) (5081);
	final public static int SwitchInqNtuReq = (int) (5250);
	final public static int SwitchInqNtuResp = (int) (5251);
	final public static int SwitchInqAsmReq = (int) (5260);
	final public static int SwitchInqAsmResp = (int) (5261);
	final public static int SwitchWsiTnlReq = (int) (5090);
	final public static int SwitchWsiTnlResp = (int) (5091);
	final public static int SwitchUpdMscReq = (int) (5100);
	final public static int SwitchUpdMscResp = (int) (5101);
	final public static int SwitchPreTdoReq = (int) (5110);
	final public static int SwitchPreTdoResp = (int) (5111);
	final public static int SwitchRptNtuReq = (int) (5120);
	final public static int SwitchRptNtuResp = (int) (5121);
	final public static int SwitchWsiPrvReq = (int) (5130);
	final public static int SwitchWsiPrvResp = (int) (5131);
	final public static int SwitchWsiAsmReq = (int) (5140);
	final public static int SwitchWsiAsmResp = (int) (5141);
	final public static int SwitchUpdAsmReq = (int) (5150);
	final public static int SwitchUpdAsmResp = (int) (5151);
	final public static int SwitchCorTdoReq = (int) (5180);
	final public static int SwitchCorTdoResp = (int) (5181);
	final public static int SwitchPreMctReq = (int) (5160);
	final public static int SwitchPreMctResp = (int) (5161);
	final public static int SwitchWsiNtuReq = (int) (5170);
	final public static int SwitchWsiNtuResp = (int) (5171);
	final public static int SwitchChgAsgReq = (int) (5190);
	final public static int SwitchChgAsgResp = (int) (5191);
	final public static int SwitchSelTnReq = (int) (5200);
	final public static int SwitchSelTnResp = (int) (5201);
	final public static int SwitchSelTneReq = (int) (5210);
	final public static int SwitchSelTneResp = (int) (5211);
	final public static int SwitchRtnTnReq = (int) (5220);
	final public static int SwitchRtnTnResp = (int) (5221);
	final public static int SuccessResp = (int) (9997);
	final public static int EndOfDataResp = (int) (9998);
	final public static int ExceptionResp = (int) (9999);
}
