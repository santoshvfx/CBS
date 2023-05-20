//$Id: TIRKSJXConstants.java,v 1.3 2011/05/07 01:52:34 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

/**
 * Contains the TIRKSJX field constants
 * 
 * @author js7440
 */
public interface TIRKSJXConstants 
{
	public final static String HEADER_NAME = "jX API Request";
	public final static String HEADER_SENDER = "RM BIS";
	public final static String HEADER_SENDER_ROLE = "Initiating";
	
	public final static String ACTION_NEW = "New";
	public final static String ACTION_CLOSE = "Close";
	public final static String ACTION_F1 = "F1";
	public final static String ACTION_F2 = "F2";
	public final static String ACTION_F8 = "F8";
	
	public final static String SCREEN_CBLS = "CBLS";
	public final static String SCREEN_CXRS = "CXRS";
	public final static String SCREEN_DRI = "DRI";
	public final static String SCREEN_EQPSC = "EQPSC";
	public final static String SCREEN_RDLOC = "RDLOC";
	public final static String SCREEN_WA = "WA";

	public final static String FIELD_ACNA = "ACNA";
	public final static String FIELD_ACTIVITY_CUR = "ACTIVITY CUR";
	public final static String FIELD_ACTIVITY_PND = "ACTIVITY PND";
	public final static String FIELD_BTN = "BTN";
	public final static String FIELD_CABLE = "CABLE";
	public final static String FIELD_CAC = "CAC";
	public final static String FIELD_CCNA = "CCNA";
	public final static String FIELD_CIRCUIT_IDENTIFICATION = "CIRCUIT IDENTIFICATION";
	public final static String FIELD_CKT = "CKT";
	public final static String FIELD_CKTID_CLO = "CKTID/CLO";
	public final static String FIELD_CKR = "CKR";
	public final static String FIELD_CLO = "CLO";
	public final static String FIELD_COMPANY = "COMPANY";
	public final static String FIELD_CURRENT_ACTIVITY = "CUR ACT";
	public final static String FIELD_DUE_DATE = "DUE DATE";
	public final static String FIELD_DESCRIPTION = "DESCRIPTION";
	public final static String FIELD_EQUIP_CODE = "EQUIP CODE";
	public final static String FIELD_F = "F";
	public final static String FIELD_FAC_DES = "FAC DES";
	public final static String FIELD_FAC_TYPE = "FAC TYPE";
	public final static String FIELD_FROM_UNIT = "FROM UNIT";
	public final static String FIELD_LAST_UNIT = "LAST UNIT";
	public final static String FIELD_LEVEL = "LEVEL";
	public final static String FIELD_LOC_NAME = "LOC NAME";
	public final static String FIELD_LOCA = "A (LOC)";
	public final static String FIELD_LOCATION = "LOCATION";
	public final static String FIELD_LOCATION_CODE = "LOCATION CODE";
	public final static String FIELD_LOCZ = "Z (LOC)";
	public final static String FIELD_MCN = "MCN";
	public final static String FIELD_NC = "NC";
	public final static String FIELD_NC1 = "NCI (PRILOC)";
	public final static String FIELD_NC2 = "NCI (SECLOC)";
	public final static String FIELD_ORD = "ORD";
	public final static String FIELD_OWNER = "OWNER";
	public final static String FIELD_OUTPUT = "OUTPUT";
	public final static String FIELD_OUTPUT_CONTINUED = "OUTPUT CONTINUED";
	public final static String FIELD_PENDING_ACTIVITY = "PEND ACT";
	public final static String FIELD_PON = "PON";
	public final static String FIELD_RELAY_RACK = "RELAY RACK";
	public final static String FIELD_SYSMSG = "SYSMSG";
	public final static String FIELD_TERM_A = "TERM A";
	public final static String FIELD_TERM_Z = "TERM Z";
	public final static String FIELD_UNIT = "UNIT";
	
	public final static String INSTANCE_0 = "0";
	public final static String INSTANCE_1 = "1";
	public final static String INSTANCE_2 = "2";
	
	public final static String NEW_SESSION_REQUEST_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest";
	public final static String CLOSE_SESSION_REQUEST_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest";
	public final static String ACTION_ON_SCREEN_REQUEST_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest";
	public final static String NEW_SESSION_RESPONSE_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse";
	public final static String CLOSE_SESSION_RESPONSE_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse";
	public final static String ACTION_ON_SCREEN_RESPONSE_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse";
	public final static String ERROR_ON_SCREEN_RESPONSE_IMPLEMENTATION_CLASS = "com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse";
	
	public final static String EOF_DELIMITER = "\u001A";
	public final static String ERROR_RETURN = "ErrorReturn";
	public final static String CBLS_SUCCESS = "TCB000I";
	public final static String CBLS_SUCCESS_PGDN = "TCB000I";
	public final static String CLOSE_SESSION_SUCCESS = "Session Closed";
	public final static String CXRS_SUCCESS = "CXR801I";
	public final static String CXRS_SUCCESS_PGDN = "CXR701I";
	public final static String DRI_SUCCESS = "DRI930I";
	public final static String EQPSC_SUCCESS = "EQP007I";
	public final static String EQPSC_SUCCESS_PGDN = "EQP010I";
	public final static String RDLOC_SUCCESS = "LOC505I";
	public final static String WA_SUCCESS = "WAS930I";
}
