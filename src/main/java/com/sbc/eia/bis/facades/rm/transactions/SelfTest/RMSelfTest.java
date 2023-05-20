//$Id: RMSelfTest.java,v 1.25 2011/04/08 17:22:43 rs278j Exp $
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
//# 10/06/2006  Jyothi Jasti          Creation.
//# 10/15/2006  Sam Lok               Removed LogInput() and LogOutput().   The Framework does it.
//# 10/16/2006  Sam Lok               Removed extraneous code that caused the resultBisContext to be overwritten with an incorrect LoggingInformation before return.
//# 12/05/2006  Jon Costa			  Added selftestVicunaServices() method call and eMail funtionality.
//# 12/19/2006	Doris Sunga			  Added selfTestEMBUS() method.
//# 01/17/2007	Doris Sunga			  Added selfTestDBConnection() method.
//# 01/18/2006  Doris Sunga			  Comment out all indiv calling of selfTest components due to RM# NOT available yet
//# 02/13/2008  Julius Sembrano 	  Added client authorization and uncommented the selftest components
//# 07/06/2008  Julius Seembrano      Defect #99746 - RM 21.10 selfTest - CorrID not matching in the logs.
//#                                   Defect #100175 - RM 21.10 ping - No Correlation ID returned
//#                                   Defect #100679 - For Ping & SelfTest methods, if the missing Application tag/Value is passed it should throw invalid data 
//#                                   exception but it is throwing Access denied.
//# 04/31/2009  Julius Sembrano       PR# 24678848 - fixed MVT errors in ETS, CLEC, LSPSE and LSPROD
//# 05/29/2009  Julius Sembrano       DR 132786 - Added CPSOS and Granite SelfTest 

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.authorization.AuthorizationException;
import com.sbc.eia.bis.authorization.ClientAuthorization;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.BisLoggerFactory;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.framework.methods.SelfTest;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.bis.rm.utilities.Emailer;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.SelfTestReturn;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sun.java.util.collections.ArrayList;
import com.sbc.eia.bis.facades.rm.transactions.SelfTest.SelfTestTIRKSJXConnection;

public class RMSelfTest extends SelfTest
{
	private Hashtable aProperties = null;
	private ArrayList aResultList = new ArrayList();
	
	private class SecondaryLogger extends TranBase
	{
		BisLogger abisLogger = null;
		public SecondaryLogger(BisContext aContext, BisLogger bisLogger)
		{ 
			super();
			this.setPROPERTIES(aProperties);
			this.setCallerContext(aContext);
			this.theLogAssistant.setCorrID(bisLogger.getCorrelationId());

			abisLogger = bisLogger;
		}
		// Override log methods to use the BisLogger log methods
		public void log(String eventId, String message)
		{
			abisLogger.log(eventId, message);
		}

		public void log(String eventId, String code, String text, String origination)
		{
			abisLogger.log(eventId, code, text, origination);
		}
	};

	/**
	 * Constructor for  SelfTest.
	 * @param param
	 */
	public RMSelfTest(Hashtable param)
	{
		aProperties = param;
	}

	/**
	 * @param aBisContext
	 * @return SelfTestReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws MultipleExceptions
	 */
	public SelfTestReturn execute(BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions
	{
		SelfTestReturn aReturn = null;

		// if aContext or aContext.aProperties is null initialize BisContext to prevent null pointer or marshalling errors. 
		aContext = BisContextHelper.initialize(aContext);

		BisLogger bisLogger =
			BisLoggerFactory.create(
				(String) aProperties.get("BIS_NAME"),
				(String) aProperties.get("BIS_VERSION"));

		try
		{
			BisContextManager bisContextManager = new BisContextManager(aContext);

			String li = bisContextManager.getLoggingInformationString();

			if (li != null && li.trim().length() > 0)
			{
				//this is done for the case where the client has put logging information in the bis context 
				bisLogger.setLoggingInformationString(li);
			}

			BisContext resultBisContext = execute(bisContextManager.getBisContext(), bisLogger);
			aReturn = new SelfTestReturn(resultBisContext);
		}
		catch(AccessDenied e)
		{
			throw e;
		}
		catch(InvalidData e)
		{
			throw e;
		}
		catch(BusinessViolation e)
		{
			throw e;
		}
		catch(SystemFailure e)
		{
			throw e;
		}
		catch(NotImplemented e)
		{
			throw e;
		}
		catch(ObjectNotFound e)
		{
			throw e;
		}
		catch(DataNotFound e)
		{
			throw e;
		}
		catch(MultipleExceptions e)
		{
			throw e;
		}		
		catch (Throwable e) 
		{
		
			bisLogger.log(
				LogEventId.FAILURE,
				"RM Caught an exception while executing the selfTest method: "
					+ e.toString());

			throw new SystemFailure(
				aContext,
				new ExceptionData(
					ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
					"RM Caught an exception while executing the selfTest method: "
						+ e.toString(),
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(
						SeverityOpt.class,
						Severity.UnRecoverable)));
		}
		return aReturn;
	}

	/**
	 * @see com.sbc.eia.bis.framework.methods.SelfTest#selfTestLogic(BisContext, BisLogger)
	 */
	public BisContext selfTestLogic(BisContext aContext, BisLogger bisLogger)
		throws
			InvalidData,
			DataNotFound,
			ObjectNotFound,
			AccessDenied,
			MultipleExceptions,
			NotImplemented,
			BusinessViolation,
			SystemFailure
	{
		String myMethodName = "RMSelfTest::selfTestLogic()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		SecondaryLogger aSecondaryLogger = new SecondaryLogger(aContext, bisLogger);
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( aContext.aProperties );
		
		//get the selftest property files from the rm.properties
		String selfTestPropertiesFile = (String)aProperties.get("SELFTEST_PROPERTIES_FILE");
		String selfTestServicesFile = (String)aProperties.get("SELFTEST_SERVICES_FILE");
		String selfTestDatabaseFile = (String)aProperties.get("SELFTEST_DATABASE_FILE");
		String selfTestBISFile = (String)aProperties.get("SELFTEST_SERVICES_FILE");
		
		validateContextAndClient(
				aContext,
			 	aContextOPM,
			 	null,			// group_id
				ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION,
				bisLogger);

		new SelfTestPropertiesFiles(selfTestPropertiesFile, aContext, bisLogger, aResultList);
		new SelfTestVicunaServices(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
		new SelfTestEMBUS(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);

		//TODO: Document
		new SelfTestRCAccess(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);

		new SelfTestBISAccess(selfTestBISFile, aContext, aProperties, bisLogger, (Utility) aSecondaryLogger, aResultList);
		new SelfTestDBConnection(selfTestDatabaseFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);

		//TODO: Uncomment if used
//		new SelfTestURLConnection(selfTestServicesFile, aContext, aProperties, bisLogger, aResultList);

		//TODO: Is TIRKSJX used in RM?
		if(Boolean.valueOf((String)aProperties.get("TIRKSJX_OPTION")).booleanValue() == true)
		{
			new SelfTestTIRKSJXConnection(aContext, aProperties, (Utility) aSecondaryLogger, bisLogger, aResultList);
		}
		
		this.sendEmail(aContext, aSecondaryLogger, bisLogger); 
		//
		SelfTestResult.displayResultSet(bisLogger, aResultList);
		
		validateResult(aContext, bisLogger, aResultList);
		///////////////////////////////
		
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aContext;
	}

	/**
	 * Method getEmailProps.
	 * @param bisLogger
	 * @return Properties
	 */
	private Properties getEmailProps(BisLogger bisLogger)
	{
		String myMethodName = "RMSelfTest::getEmailProps()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		Properties aEmailProps = new Properties();

		aEmailProps.put("mail.transport.protocol", "smtp");

		String PRIMARY_MAIL_HOST;
		if ((PRIMARY_MAIL_HOST = ((String) aProperties.get("PRIMARY_SMTP_SERVER")).trim()) != null)
		{
			aEmailProps.put("mail.smtp.host", PRIMARY_MAIL_HOST);

			try
			{
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"PRIMARY_SMTP_SERVER=<" + PRIMARY_MAIL_HOST + ">");
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "PRIMARY_SMTP_SERVER not set");
			}
		}

		String SECONDARY_MAIL_HOST;
		if ((SECONDARY_MAIL_HOST = ((String) aProperties.get("SECONDARY_SMTP_SERVER")).trim())
			!= null)
		{
			aEmailProps.put("secondary.mail.smtp.host", SECONDARY_MAIL_HOST);

			try
			{
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"SECONDARY_SMTP_SERVER=<" + SECONDARY_MAIL_HOST + ">");
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "SECONDARY_MAIL_HOST not set");
			}
		}

		if ((PRIMARY_MAIL_HOST.trim().equals("")) && (SECONDARY_MAIL_HOST.trim().equals("")))
			bisLogger.log(
				LogEventId.FAILURE,
				"Missing Both Primary SMTP Server "
					+ "and  the Secondary SMTP Server Names. "
					+ "Require atleast one SMTP Server Name");

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aEmailProps;
	}

	/**
	 * Method sendEmail.
	 * @param aContext
	 * @param aSecondaryLogger
	 * @param bisLogger
	 */
	private void sendEmail(
		BisContext aContext,
		SecondaryLogger aSecondaryLogger,
		BisLogger bisLogger)
	{
		String myMethodName = "RMSelfTest::sendEmail()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String recipient = null;
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

		if ((recipient = aContextOPM.getValue("EMAIL_RECIPIENT_RMSELFTEST")) != null)
		{
			String[] recipients = { recipient };
			Emailer aEmailer = new Emailer((Utility) aSecondaryLogger, aProperties);
			aEmailer.setEmailConfig(getEmailProps(bisLogger));

			try
			{
				aEmailer.sendEmail(
					recipients,
					((String) aProperties.get("SENDER_EMAIL_ADDRESS")).trim(),
					"RMSelfTest result log",
					SelfTestResult.toString(aResultList));
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "Email failed to send: " + e.getMessage());
			}
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method validateContextAndClient
	 * @param bisLogger
	 * @param aResultList
	 * @param aContext
	 * @param aContextOPM
	 * @param groupId
	 * @param errorCode
	 * @param systemFailureCode
	 * @param bisLogger
	 */
	public void validateContextAndClient(
			BisContext aContext,
			ObjectPropertyManager aContextOPM, 
			String groupId,
			String errorCode,
			String systemFailureCode,
			BisLogger bisLogger)
			throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound
	{
		
		String myMethodName = "RMSelfTest::validateContentAndClient()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try 
		{					
			if ( ClientAuthorization.isAuthorized(				
				new String("selfTest"),
				aContextOPM.getValue( BisContextProperty.APPLICATION ),		// application
				aContextOPM.getValue( BisContextProperty.BUSINESSUNIT),		// business unit
				aContextOPM.getValue( BisContextProperty.SERVICECENTER),	// service center
				groupId,													// group id 
				aProperties,												// hash table
				bisLogger ) == false )										// logger
			{
				throw new AuthorizationException(
						"Unauthorized user <" + aContextOPM.getValue( BisContextProperty.APPLICATION ) + "> <" +
						aContextOPM.getValue( BisContextProperty.BUSINESSUNIT) + "> <" +
						aContextOPM.getValue( BisContextProperty.SERVICECENTER) + "> <" +
						groupId + ">");
			}
		}
		catch (AuthorizationException e )
		{
			bisLogger.log(
					LogEventId.FAILURE, 
					ExceptionCode.ERR_RM_UNAUTHORIZED_USER, 
					e.getMessage(), 
					(String) aProperties.get("BIS_NAME"));
			
			throw new AccessDenied(
					aContext,
					new ExceptionData(
						ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
						e.toString(),
						IDLUtil.toOpt("RM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
		} 
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method validateResult
	 * @param aContext
	 * @param bisLogger
	 * @param aResultSet
	 */
	public void validateResult(BisContext aContext, BisLogger bisLogger, ArrayList aResultSet) 
		throws 
		InvalidData, 
		AccessDenied, 
		BusinessViolation, 
		SystemFailure, 
		NotImplemented, 
		ObjectNotFound, 
		DataNotFound,
		MultipleExceptions{
		
		String myMethodName = "RMSelfTest::validateResult()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList failures = new ArrayList();
		
		ArrayList failedResults = SelfTestResult.validateResultSet(bisLogger, aResultList);
		
		if(!failedResults.isEmpty())
		{	
			if(failedResults.size() == 1)
			{
				bisLogger.log(
						LogEventId.FAILURE,
						ExceptionCode.ERR_RM_SYSTEM_FAILURE,
						(String)failedResults.get(0),
						(String) aProperties.get("BIS_NAME"));
				
				throw new SystemFailure(
					aContext,
					new ExceptionData(
						ExceptionCode.ERR_RM_SYSTEM_FAILURE,
						(String)failedResults.get(0),
						IDLUtil.toOpt("RM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
			}
			else
			{
				for(int i = 0; i < failedResults.size(); i++)
				{
					String results = (String)failedResults.get(i);
					failures.add(new ExceptionData(
						ExceptionCode.ERR_RM_SYSTEM_FAILURE,
						results.toString(),
						IDLUtil.toOpt("RM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
				}
			}
		}
		if(!failures.isEmpty())
		{
			bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"RM encountered MultipleExceptions while running selfTest transaction.");
			
			ExceptionData[] exceptions = new ExceptionData[failures.size()];
			
			for(int i = 0; i < failures.size(); i++)
			{
				exceptions[i] = (ExceptionData)failures.get(i);
				
				bisLogger.log(
					LogEventId.FAILURE, exceptions[i].aCode,
					exceptions[i].aDescription,
					(String) aProperties.get("BIS_NAME"));
			}
			throw new MultipleExceptions(aContext, exceptions);
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}
