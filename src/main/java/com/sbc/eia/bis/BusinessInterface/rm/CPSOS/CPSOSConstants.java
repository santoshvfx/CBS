//$Id: CPSOSConstants.java,v 1.2 2009/02/19 21:37:24 sl7683 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 01/2009      Sheilla Lirio         Creation.

package com.sbc.eia.bis.BusinessInterface.rm.CPSOS;

/**
 * Class      : CPSOSConstants
 * Description: Defines constants used in the CPSOS xml request and response.
 * 
 * @author sl7683
 */
public class CPSOSConstants {
	
	//CPSOS Error Codes
	public static String Service_Request_Authorization_Error_Code = "07048003";
	public static String Backend_System_Error_Code = "07048001";
	
	//XML Request Identification Tag Name
	public static String NameSpace_URI = "http://www.w3.org/2001/XMLSchema-instance";
    public static String NameSpace_Qualified_Name = "xmlns:xsi";
    public static String Schema_Location = "/appl/cpsos/cpsosb2b/xmlschema/v8_06_0/master.xsd";
    public static String NoNamSpace_Qualified_Name = "xsi:noNamespaceSchemaLocation";
    public static String Xml_Root_Element = "SBCTransaction";
    
    public static String Xml_ReqId_Element = "RequestIdentification";
    public static String Xml_ReqType_Attribute = "ReqType";
    public static String Xml_ToolbarUserId_Attribute = "ToolbarUserId";
    public static String Xml_ToolbarPswd_Attribute = "ToolbarPswd";
    public static String Xml_PartnerId_Attribute = "PartnerId";
    public static String Xml_ReqstrOrgId_Attribute = "ReqstrOrgId";
    public static String Xml_ReqstrAffId_Attribute = "ReqstrAffId";
    public static String Xml_ReqstrTrkNbr_Attribute = "ReqstrTrkNbr";
    public static String Xml_ReqstrTimeStamp_Attribute = "ReqstrTs";
    public static String Xml_ReqstrServerName_Attribute = "ReqstrServerName";
    public static String Xml_IntVer_Attribute = "IntVer";
    
    //XML Request Tag Names
    public static String Xml_AccountLookupReq_Element = "DslAccountLookupRequest";
    public static String Xml_DslPhone_Element = "DslPhone";
    public static String Xml_DslTnNpa_Attribute = "DslTnNpa";
    public static String Xml_DslTnNxx_Attribute = "DslTnNxx";
    public static String Xml_DslTnLine_Attribute = "DslTnLine";
    
    public static String Xml_ServiceAddress_Element = "ServiceAddress";
    public static String Xml_SrvcAddrState_Attribute = "SrvcAddrState";
    
	public static String Xml_AcctLookupDetails_Element = "AcctLookupDetails";
	public static String Xml_EndUserAuthorization_Attribute = "EndUserAuthorization";
	
	//XML Response Tag Names
	public static String Xml_RespId_Element = "ResponseIdentification";
	
	public static String Xml_RespType_Attribute = "RespType";
    public static String Xml_RespTimeStamp_Attribute = "RespTs";
    public static String Xml_ResponderId_Attribute = "ResponderId";
    
    public static String Xml_AccountLookupResp_Element = "DslAccountLookupResponse";
    public static String Xml_ErrorResponse_Element = "ErrorResponse";
    public static String Xml_ErrId_Attribute = "ErrId";
    public static String Xml_ErrLogId_Attribute = "ErrLogId";
    public static String Xml_ErrTimeStamp_Attribute = "ErrTs";
    public static String Xml_ErrTxt_Attribute = "ErrTxt";

}
