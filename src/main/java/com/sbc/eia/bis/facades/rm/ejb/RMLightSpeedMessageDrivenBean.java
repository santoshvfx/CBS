// $Id: RMLightSpeedMessageDrivenBean.java,v 1.29.10.1 2012/04/04 16:19:52 js7440 Exp $
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
//# --------------------------------------------------------------------------
//# 04/2005	    Jon Costa			  Creation
//# 08/11/2005	Sriram Chevuturu	  Added code to process ModifyFacilityInfo.
//# 08/11/2005	Sriram Chevuturu	  Added Code for DR 142178: Exception Handling.
//# 09/11/2005  Jon Costa			  Extra logic parsing echo section for R2. 
//# 11/16/2006  Rene Duka             Initialized callerContext in onMessage. 
//# 12/04/2006  Doris Sunga			  DR #170893 - correlation id issue in PFAO log and xml output,  
//#                                   need to pass corr id when calling SoacFcif.messageRouter()	
//# 02/23/2006  Doris Sunga			  replacing System.out.println() with INFO_LEVEL_1
//# 05/23/2007	Doris Sunga			  replacing AUDIT_TRAIL to DEBUG_LEVEL_1 
//# 10/03/2007  Doris Sunga			  LS R6: For unexpected MDB errors, add ERR_RM_UNEXPECTED_ERROR
//# 06/26/2008  Jon Costa             DR:99255: A Unique Correlation ID is not being returned.

package com.sbc.eia.bis.facades.rm.ejb;

import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacFCIF;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.bis.facades.rm.ejb.PublishValidateFacilityNotification.PublishValidateFacilityNotification;
import com.sbc.eia.bis.facades.rm.ejb.PublishValidateFacilityNotification.PublishValidateFacilityNotificationHome;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;

/**
 * Bean implementation class for Enterprise Bean: RMLightSpeedMDB
 */
public class RMLightSpeedMessageDrivenBean
	extends TranBase
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener
{
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;
	private javax.ejb.SessionContext mySessionCtx = null;
	private SoacFCIF aSoacFcif = null;

	protected final static String PROP_FILE_LOCATION = "java:comp/env/PROP_FILE_LOCATION";

	/**
	 * getMessageDrivenContext
	 */
	public javax.ejb.MessageDrivenContext getMessageDrivenContext()
	{
		return fMessageDrivenCtx;
	}

	/**
	 * setMessageDrivenContext
	 */
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx)
	{
		fMessageDrivenCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException, java.rmi.RemoteException
	{
		init(mySessionCtx);
	}

	/**
	 * Method getSessionContext.
	 * @return SessionContext
	 */
	public javax.ejb.SessionContext getSessionContext()
	{
		return mySessionCtx;
	}

	/**
	 * Method setSessionContext.
	 * @param ctx
	 * @throws RemoteException
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) throws java.rmi.RemoteException
	{
		mySessionCtx = ctx;
	}

	/**
	 * Required method for entry into MDB:
	 * 
	 * @see javax.jms.MessageListener#onMessage(Message)
	 */
	public void onMessage(javax.jms.Message msg)
	{
        callerContext = new BisContext();
        this.setCallerContext(callerContext);

		log(LogEventId.ENTRY, "RMLightSpeedMessageDrivenBean:onMessage()");

		// Capture transaction details for AUDIT purposes.
		String msgTxt = null;
		if (aSoacFcif == null)
			aSoacFcif = new SoacFCIF(getPROPERTIES(), (com.sbc.bccs.utilities.Logger) this);

		try
		{			
			if ((msg instanceof TextMessage))
			{
				// Log the input message.
				msgTxt = ((TextMessage) msg).getText();
				log(LogEventId.INFO_LEVEL_1, "Input from client: [" + msgTxt + "]");
			}

			// Route message to transaction for handling...
			String trType = msg.getStringProperty(ValidateFacilityConstants.JMS_propertyName);
			if (trType != null && trType.substring(0, 16).equalsIgnoreCase(ValidateFacilityConstants.JMS_propertyValue))
			{
				log(LogEventId.INFO_LEVEL_1, "Processing validateFacility message");
			
				//doing the local EJB lookup     
				InitialContext ctx = new InitialContext();
				Object o = ctx.lookup("java:comp/env/ejb/PublishValidateFacilityNotificationHome");

				PublishValidateFacilityNotificationHome home =
					(PublishValidateFacilityNotificationHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						PublishValidateFacilityNotificationHome.class);

				PublishValidateFacilityNotification publishValidateFacilityNotificationBean = home.create();

				publishValidateFacilityNotificationBean.publishValidateFacilityNotification(msg, myLogger);
			}
			else
			{
				log(LogEventId.INFO_LEVEL_1, "Processing FCIF message");
				aSoacFcif.messageRouter((Utility) this, msgTxt, this.myLogger.getBisLogger().getCorrelationId());
			}
        }
		/* No response/exception to SOAC is possible, catch ALL exceptions and 
		 * log all that is known to AUDIT_TRAIL:
		 *   RECEIVED: [ raw input message ]
		 * TRANS_TYPE: [ transaction expected to handle message ]
		 *  EXCEPTION: [ error message ]
		 */
		catch (SystemFailure sfe)
		{
			logAuditTrail(msgTxt, aSoacFcif.getmyTransaction(), sfe.aExceptionData.aDescription);
		}
		catch (Throwable e)
		{
			logAuditTrail(LOG_FAILURE, ExceptionCode.ERR_RM_UNEXPECTED_ERROR+ " Unexpected error processing the message. " , e.getMessage());
		}

		log(LogEventId.EXIT, "RMLightSpeedMessageDrivenBean:onMessage()");
	}

	/**
	 * Method logAuditTrail.
	 * @param aMsgTxt
	 * @param aTrans
	 * @param aErrMsg
	 */
	private void logAuditTrail(String aMsgTxt, String aTrans, String aErrMsg)
	{
		log(LogEventId.INFO_LEVEL_1, "RECEIVED: [ " + (aMsgTxt != null ? aMsgTxt : "unknown") + "]");
		log(LogEventId.INFO_LEVEL_1, "TRANS_TYPE: [" + (aTrans != null ? aTrans : "unknown") + "]");
		log(LogEventId.INFO_LEVEL_1, "EXCEPTION: [" + (aErrMsg != null ? aErrMsg : "unknown") + "]");
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove()
	{}

	/**
	 * Method loadProperty.
	 * @param bisContext
	 * @throws SystemFailure
	 */
	public void loadProperty(BisContext bisContext) throws SystemFailure
	{
		String myMethodNameName = "RMLightSpeedMessageDrivenBean.loadProperty()";
		log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

		try
		{
			/**
			  * Load RM related properties from a file.
			  */
			if (PROPERTIES == null)
			{
				PROPERTIES = new java.util.Properties();
				Context cxt = new javax.naming.InitialContext();
				String loc = (String) cxt.lookup(PROP_FILE_LOCATION);
				PROPERTIES = PropertiesFileLoader.read(loc, null);
				setPROPERTIES(PROPERTIES);
				//initRMBase ();
				setCallerContext(bisContext);
				log(LogEventId.DEBUG_LEVEL_2, "RM related properties loaded...");
			}
			else
			{
				setCallerContext(bisContext);
			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			log(LogEventId.INFO_LEVEL_1,"Load Properties NullPointerException <" + e + ">");
			ExceptionData excData =
				new ExceptionData(
					ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
					"Required RM properties not found during server startup.",
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable));
			log(LogEventId.INFO_LEVEL_1,"Code=[" + excData.aCode + "] Description=" + excData.aDescription);
			throw new SystemFailure(bisContext, excData);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log(LogEventId.INFO_LEVEL_1,"Load Properties Exception <" + e.getMessage() + ">");
			ExceptionData excData =
				new ExceptionData(
					ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
					"RM properties file not found during server startup.",
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable));
			log(LogEventId.INFO_LEVEL_1,"Code=[" + excData.aCode + "] Description=" + excData.aDescription);
			throw new SystemFailure(bisContext, excData);
		}
		log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
	}
}
