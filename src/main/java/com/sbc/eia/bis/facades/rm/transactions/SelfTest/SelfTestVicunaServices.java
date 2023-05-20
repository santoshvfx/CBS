//$Id: SelfTestVicunaServices.java,v 1.2 2011/04/08 18:03:44 rs278j Exp $
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
//# 11/28/2006  Jon Costa             Creation.
//# 02/18/2008	Julius Sembrano		  SelfTest enhancements

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.lang.reflect.Constructor;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sun.java.util.collections.ArrayList;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SelfTestVicunaServices
{
	/**
	 * Constructor for selfTestVicunaServices.
	 */
	public SelfTestVicunaServices(
		String fileName,
		BisContext aContext,
		Hashtable aProperties,
		Logger aLogger,
		BisLogger bisLogger,
		ArrayList aResultList)
	{
		super();
		String myMethodName = "selfTestVicunaServices::selfTestVicunaServices()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList serviceArr = FileLoader.loadFileContents(fileName, "VICUNA", bisLogger);
		String service = "";
		String serviceName = "";
		
		for (int i = 0; i < serviceArr.size(); i=i+2)
		{
			try
			{
				service = (String) serviceArr.get(i);
				serviceName = service.substring((service.lastIndexOf("_")+1), service.length());
				
				if (serviceName.equalsIgnoreCase("CIRCUITPROVISIONING")
						&& Boolean.valueOf((String)aProperties.get("TIRKSJX_OPTION")).booleanValue())
				{
					continue;
				}
				
				// Obtain helper class instance
				Class myHelper = Class.forName(serviceArr.get(i+1).toString());

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
					"VicunaServices",
					serviceName + " Access Tested Successfully.",
					true,
					bisLogger,
					aResultList);
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, serviceName + " Access Failed");
				SelfTestResult.addResultToList(
					"VicunaServices",
					serviceName + " Access Failed. " + e.getMessage(),
					false,
					bisLogger,
					aResultList);
			}
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}
