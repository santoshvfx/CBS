//$Id: Ping.java,v 1.12 2008/07/24 17:00:15 js7440 Exp $
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
//# 03/10/2008	Julius Sembrano		  Added client authorization for ping and selfTest enhancement
//# 07/06/2008  Julius Seembrano      Defect #99746 - RM 21.10 selfTest - CorrID not matching in the logs.
//#                                   Defect #100175 - RM 21.10 ping - No Correlation ID returned
//#                                   Defect #100679 - For Ping & SelfTest methods, if the missing Application tag/Value is passed it should throw invalid data 
//#                                   exception but it is throwing Access denied.

package com.sbc.eia.bis.facades.rm.transactions.Ping;
import java.util.Hashtable;

//import com.ibm.etools.j2ee.commonarchivecore.exception.DuplicateObjectException;
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
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.DuplicateObject;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.PingReturn;
import com.sbc.eia.idl.rm.bishelpers.PingReturnBisHelper;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.bis.RmNam.utilities.Logger;

public class Ping extends com.sbc.eia.bis.framework.methods.Ping{

	private Hashtable aProperties = null;

	/**
	 * Constructor for  Ping.
	 * @param param
	 */
	public Ping(Hashtable param) {
		aProperties = param;
	}

	/**
	 * @param aBisContext
	 * @return PingReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws MultipleExceptions
	 */
	public PingReturn execute(BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions {

		PingReturn pingReturn = null;
		
		// if aContext or aContext.aProperties is null initialize BisContext to prevent null pointer or marshalling errors.
		aContext = BisContextHelper.initialize(aContext);

		BisLogger bisLogger =
			BisLoggerFactory.create(
				(String) aProperties.get("BIS_NAME"),
				(String) aProperties.get("BIS_VERSION"));

		try {

			BisContextManager bisContextManager =
				new BisContextManager(aContext);

			String li = bisContextManager.getLoggingInformationString();

			if (li != null && li.trim().length() > 0) {
				//this is done for the case where the client has put logging information in the bis context 
				bisLogger.setLoggingInformationString(li);
			}			
			
			BisContext resultBisContext =
				execute(
					bisContextManager.getBisContext(),
					bisLogger);

			pingReturn = new PingReturn(resultBisContext);
			
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
				"RM Caught an exception while executing the ping method: "
					+ e.toString());

			throw new SystemFailure(
				aContext,
				new ExceptionData(
					ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
					"RM Caught an exception while executing the ping method: "
						+ e.toString(),
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(
						SeverityOpt.class,
						Severity.UnRecoverable)));
		}

		return pingReturn;
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
		
		String myMethodName = "ping::validateContentAndClient()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try 
		{					
			if ( ClientAuthorization.isAuthorized(				
				new String("ping"),
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

	protected BisContext pingLogic(
			BisContext bisContext, 
			BisLogger bisLogger) 
		throws 
		InvalidData, 
		DataNotFound, 
		DuplicateObject, 
		ObjectNotFound, 
		AccessDenied, 
		MultipleExceptions, 
		NotImplemented, 
		BusinessViolation, 
		SystemFailure 
	{
		
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( bisContext.aProperties );
		
		validateContextAndClient(
				bisContext,
			 	aContextOPM,
			 	null,			// group_id
				ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION,
				bisLogger);
		
		return bisContext;
	}
}
