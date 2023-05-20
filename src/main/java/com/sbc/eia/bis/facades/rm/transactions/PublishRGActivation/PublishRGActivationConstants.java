//$Id: PublishRGActivationConstants.java,v 1.1.2.2 2007/10/18 21:49:46 ra0331 Exp $
//###############################################################################
//#
//#   Copyright Notice:
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
//# Date      | Author               | Notes
//# ----------------------------------------------------------------------------
//# 09/27/2007  ra0331				   Created constants for pRGA			

package com.sbc.eia.bis.facades.rm.transactions.PublishRGActivation;


public class PublishRGActivationConstants {

	public static String JMS_propertyName = "TransactionType";
	public static String JMS_propertyValue = "PublishRGActivation";
    public static String PRE_MIGRATION = "PRE_MIGRATION";
    public static String DURING_MIGRATION = "DURING_MIGRATION";
    public static String POST_MIGRATION = "POST_MIGRATION";
    public static String MIGRATION_COMPLETE = "MIGRATION_COMPLETE";
    public static String MIGRATION_FLAG = "Y";
    
    public static String BEGIN_VAXB_TAG = "<vaxb:VAXB version=\"3.1.0\" xmlns:vaxb=\"urn:RmFacadePackage.rm.idl.eia.sbc.com\">" ;
    public static String END_VAXB_TAG = "</vaxb:VAXB>" ;    
}
