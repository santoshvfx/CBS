//$Id: SelfTestEMBUS.java,v 1.1 2011/04/08 17:18:53 rs278j Exp $
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
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 12/19/2006  Doris Sunga             Creation.
//# 02/28/2008  Julius Sembrano			SelfTest Enhancements

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.lang.reflect.Constructor;
import java.util.Hashtable;

//import com.ibm.security.krb5.internal.ServiceName;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
//import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
//added by js7440
import com.sbc.eia.bis.embus.service.bbnms.BBNMSHelper;
import com.sbc.eia.bis.embus.service.codes.access.CodesRCAccessHelper;
import com.sbc.eia.bis.embus.service.facsrc.FACSRCHelper;
import com.sbc.eia.bis.embus.service.first.FIRSTHelper;
import com.sbc.eia.bis.embus.service.netprovision.access.NetProvisionHelper;
import com.sbc.eia.bis.embus.service.npconnector.access.NpConnectorHelper;
import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;
import com.sbc.eia.bis.embus.service.soa.SOAHelper;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sun.java.util.collections.ArrayList;
import com.sbc.eia.idl.types.StringOpt;


public class SelfTestEMBUS
{
		
					
	/**
	 * Constructor for selfTestEMBUS.
	 */
		
	public SelfTestEMBUS(
			String fileName,
			BisContext aContext, 
			Hashtable aProperties, 
			Logger aLogger,
			BisLogger bisLogger, 
			ArrayList aResultList)
	{
		super();
		String myMethodName = "selfTestEMBUS::selfTestEMBUS()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList embusArr = FileLoader.loadFileContents(fileName, "EMBUS",  bisLogger);
		String service = "";
		String serviceName = "";
		
		for (int i = 0; i < embusArr.size(); i=i+2)
		{
			try
			{
				service = (String) embusArr.get(i);
				serviceName = service.substring((service.lastIndexOf("_")+1), service.length());
				
				// Obtain helper class instance
				Class myHelper = Class.forName(embusArr.get(i+1).toString());

				Constructor C =
					myHelper.getConstructor(
						new Class[] {
							java.util.Hashtable.class,
							com.sbc.bccs.utilities.Logger.class });

				ServiceHelper aServiceHelper =
					(ServiceHelper) C.newInstance(new Object[] { aProperties, aLogger });
				
				aServiceHelper.selfTest(aContext);
				
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					serviceName + " Access Tested Successfully");
					
				SelfTestResult.addResultToList(
					"EMBUS",
					serviceName + " Access Tested Successfully.",
					true,
					bisLogger,
					aResultList);
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, serviceName + " Access Failed");
				SelfTestResult.addResultToList(
					"EMBUS",
					serviceName + " Access Failed. " + e.getMessage(),
					false,
					bisLogger,
					aResultList);
			}
		}

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}