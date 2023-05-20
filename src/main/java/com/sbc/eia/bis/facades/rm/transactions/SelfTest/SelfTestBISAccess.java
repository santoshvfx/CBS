//$Id: SelfTestBISAccess.java,v 1.4 2011/04/08 18:21:44 rs278j Exp $
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
//# 02/21/2008	Arez Quiñones		  selfTest Enhancements
//# 04/31/2009  Julius Sembrano       PR# 24678848 - fixed MVT errors in ETS, CLEC, LSPSE and LSPROD

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.sm.SmFacade;
//import com.sbc.bis.sm.facade.SmFacade;
import com.sbc.eia.bis.RmNam.utilities.IomClient.IomClient;
import com.sbc.eia.bis.RmNam.utilities.NAMClient.NAMClient;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
//import com.sbc.eia.bis.facades.nam.ejb.Nam;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.nam.NamFacade;
import com.sbc.iomip.sessionbeans.IomipBIS;
import com.sun.java.util.collections.ArrayList;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SelfTestBISAccess
{
	private String PING_TEST = "ping()";
	private String RESULT_TEST_TYPE = "BIS";
	private String ACCESS_FAILED = "BIS Access Failed.";
	private String CONNECT_FAILED = "Connection Failed.";
	private String PING_RETURN = "Ping returned successfully.";
	private String ACCESS_SUCCESSFUL = "BIS Access Tested Successfully.";
	private String IOM = "IOM";
	private String NAM = "NAM";
	private String SM = "SM";
		
	/**
	 * Constructor for selfTestBISAccess.
	 */
	public SelfTestBISAccess(
		String fileName,
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::selfTestBISAccess()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList bisArr = FileLoader.loadFileContents(fileName, "BIS", bisLogger);
		String bis = "";
		String bisName = "";
		
		for (int i = 0; i < bisArr.size(); i=i+2)
		{
			try
			{
				bis = (String) bisArr.get(i);
				bisName = bis.substring((bis.lastIndexOf("_")+1), bis.length());
				
				if(bisName.equalsIgnoreCase(IOM) && Boolean.valueOf(bisArr.get(i+1).toString()).booleanValue())
				{					
					this.IOM_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
				}
				if (bisName.equalsIgnoreCase(NAM) && Boolean.valueOf(bisArr.get(i+1).toString()).booleanValue())
				{
					this.NAM_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
				}
				if (bisName.equalsIgnoreCase(SM) && Boolean.valueOf(bisArr.get(i+1).toString()).booleanValue())
				{
					this.SM_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
				}
				
			}
			catch(Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "Error in SelfTest BIS Access");
			}
		}

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method IOM_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */
	private void IOM_SelfTest(
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::IOM_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			IomClient theIOMClient =
				new IomClient(
					(String) aProperties.get("IOM_PROVIDER_URL"),
					(String) aProperties.get("IOM_BIS_NAME"),
					(String) aProperties.get("BIS_NAME"),
					(String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));

			bisLogger.log(
				LogEventId.REMOTE_CALL,
				(String) aProperties.get("IOM_PROVIDER_URL"),
				(String) aProperties.get("IOM_BIS_NAME"),
				(String) aProperties.get("IOM_BIS_NAME"),
				PING_TEST);

			IomipBIS iomBean =
				theIOMClient.getIomConnection(
					aContext,
					aUtility,
					"IOMException",
					"IOM " + CONNECT_FAILED);

			com.sbc.eia.idl.iomip.PingReturn aPingReturn = iomBean.ping(aContext);
			bisLogger.log(LogEventId.REMOTE_RETURN, PING_RETURN);
			bisLogger.log(LogEventId.INFO_LEVEL_1, "IOM " + ACCESS_SUCCESSFUL);

			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"IOM " + ACCESS_SUCCESSFUL,
				true,
				bisLogger,
				aResultList);
		}
		catch (Exception e)
		{
			handleException("IOM ", bisLogger, aResultList, e);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}

	/**
	 * Method SM_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */
	private void SM_SelfTest(
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::SM_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			SmClient theSMClient =
				new SmClient();
//					(String) aProperties.get("SM_PROVIDER_URL"),
//					(String) aProperties.get("SM_BIS_NAME"),
//					(String) aProperties.get("BIS_NAME"),
//					(String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));

			bisLogger.log(
				LogEventId.REMOTE_CALL,
				(String) aProperties.get("SM_PROVIDER_URL"),
				(String) aProperties.get("SM_BIS_NAME"),
				(String) aProperties.get("SM_BIS_NAME"),
				PING_TEST);

			SmFacade smBean =
				theSMClient.getSmConnection(
					aContext,
					aUtility,
					ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
					"SM " + CONNECT_FAILED);

			com.sbc.eia.idl.sm.PingReturn aPingReturn = smBean.ping(aContext);
			bisLogger.log(LogEventId.REMOTE_RETURN, PING_RETURN);
			bisLogger.log(LogEventId.INFO_LEVEL_1, "SM " + ACCESS_SUCCESSFUL);

			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"SM " + ACCESS_SUCCESSFUL,
				true,
				bisLogger,
				aResultList);
		}
		catch (Exception e)
		{
			handleException("SM ", bisLogger, aResultList, e);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}

	/**
	 * Method NAM_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */
	private void NAM_SelfTest(
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::NAM_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			NAMClient theNAMClient =
				new NAMClient();
//					(String) aProperties.get("NAM_PROVIDER_URL"),
//					(String) aProperties.get("NAM_BIS_NAME"),
//					(String) aProperties.get("BIS_NAME"),
//					(String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));

			bisLogger.log(
				LogEventId.REMOTE_CALL,
				(String) aProperties.get("NAM_PROVIDER_URL"),
				(String) aProperties.get("NAM_BIS_NAME"),
				(String) aProperties.get("NAM_BIS_NAME"),
				PING_TEST);

			NamFacade namBean =
				theNAMClient.getNamConnection(
					aContext,
					aUtility,
					ExceptionCode.ERR_NAMCL_REMOTE_EXCEPTION,
					"NAM " + CONNECT_FAILED);

			String env = System.getProperty("bis.platform");
			if (env.equals("NON271SAT")) {
				BisContextManager contextMgr = new BisContextManager();
				contextMgr.setApplication("NAMDEV");
				contextMgr.setCustomerName("NAMDEV");
				com.sbc.eia.idl.nam.PingReturn aPingReturn = namBean.ping(contextMgr.getBisContext());
			} else  {
				com.sbc.eia.idl.nam.PingReturn aPingReturn = namBean.ping(aContext);
			}

			bisLogger.log(LogEventId.REMOTE_RETURN, PING_RETURN);
			bisLogger.log(LogEventId.INFO_LEVEL_1, "NAM " + ACCESS_SUCCESSFUL);

			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"NAM " + ACCESS_SUCCESSFUL,
				true,
				bisLogger,
				aResultList);
		}
		catch (Exception e)
		{
			handleException("NAM ", bisLogger, aResultList, e);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}
	
	private void handleException(
			String BISName, 
			BisLogger bisLogger, 
			ArrayList aResultList, 
			Exception e)
	{
		if(e instanceof AccessDenied)
		{
			AccessDenied ad = (AccessDenied)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ ad.aExceptionData.aCode + " " 
					+ ad.aExceptionData.aDescription + " "
					+ ad.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ ad.aExceptionData.aCode + " " 
				+ ad.aExceptionData.aDescription + " "
				+ ad.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof InvalidData)
		{
			InvalidData id = (InvalidData)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ id.aExceptionData.aCode + " " 
					+ id.aExceptionData.aDescription + " "
					+ id.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ id.aExceptionData.aCode + " " 
				+ id.aExceptionData.aDescription + " " 
				+ id.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof DataNotFound)
		{
			DataNotFound dnf = (DataNotFound)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ dnf.aExceptionData.aCode + " " 
					+ dnf.aExceptionData.aDescription + " "
					+ dnf.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ dnf.aExceptionData.aCode + " " 
				+ dnf.aExceptionData.aDescription + " " 
				+ dnf.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof ObjectNotFound)
		{
			ObjectNotFound onf = (ObjectNotFound)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ onf.aExceptionData.aCode + " " 
					+ onf.aExceptionData.aDescription + " "
					+ onf.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ onf.aExceptionData.aCode + " " 
				+ onf.aExceptionData.aDescription + " " + " " 
				+ onf.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof BusinessViolation)
		{
			BusinessViolation bv = (BusinessViolation)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ bv.aExceptionData.aCode + " " 
					+ bv.aExceptionData.aDescription + " "
					+ bv.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ bv.aExceptionData.aCode + " " 
				+ bv.aExceptionData.aDescription + " " + " " 
				+ bv.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof NotImplemented)
		{
			NotImplemented ni = (NotImplemented)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ ni.aExceptionData.aCode + " " 
					+ ni.aExceptionData.aDescription + " "
					+ ni.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ ni.aExceptionData.aCode + " " 
				+ ni.aExceptionData.aDescription + " " + " " 
				+ ni.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else if(e instanceof SystemFailure)
		{
			SystemFailure sf = (SystemFailure)e;
			bisLogger.log(
					LogEventId.FAILURE, 
					BISName + ACCESS_FAILED + " "
					+ sf.aExceptionData.aCode + " " 
					+ sf.aExceptionData.aDescription + " "
					+ sf.aExceptionData.aOrigination);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " 
				+ sf.aExceptionData.aCode + " " 
				+ sf.aExceptionData.aDescription + " " + " " 
				+ sf.aExceptionData.aOrigination,
				false,
				bisLogger,
				aResultList);
		}
		else
		{
			bisLogger.log(LogEventId.FAILURE, BISName + ACCESS_FAILED);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				BISName + ACCESS_FAILED + " " + e.getMessage(),
				false,
				bisLogger,
				aResultList);
		}
	}
}
