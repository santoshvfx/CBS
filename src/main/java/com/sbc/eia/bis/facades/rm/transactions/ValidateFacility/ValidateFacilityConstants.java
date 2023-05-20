//$Id: ValidateFacilityConstants.java,v 1.4 2009/01/21 19:15:18 hw7243 Exp $
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2007	    Jon Costa			  Creation
//# 11/13/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility;

/**
 * Class      : ValidateFacilityConstants
 * Description: Public static constants for validate facility related transactions.
 *              - used by vF.
 */
public class ValidateFacilityConstants
{
    public static String JMS_propertyName = "TransactionType";
	public static String JMS_propertyValue = "ValidateFacility";
	public static String VALIDATEFACILITY2 = "ValidateFacility2";
	public static String VALIDATEFACILITY3 = "ValidateFacility3";
	public static String VALIDATEFACILITYFORPROVISIONING = "ValidateFacilityForProvisioning";
	
    public static String BEGIN_VAXB_TAG = "<vaxb:VAXB version=\"3.1.0\" xmlns:vaxb=\"urn:RmFacadePackage.rm.idl.eia.sbc.com\">" ;
    public static String END_VAXB_TAG = "</vaxb:VAXB>" ;    
}
