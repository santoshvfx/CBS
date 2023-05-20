//$Id$
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 12/2005	   Jon Costa			  Creation
//# 06/05/2005 Doris Sunga			  LS R3 rename SendF1F2Order to SendFacilityAssignmentOrder
//#									  		and receiveF1F2Order to publishFacilityAssignmentOrderNotification
//# 16/15/2006 Rene Duka              LS R3 enhancements. Modified for publishTNAssignmentOrderNotification.
//# 07/20/2006 Rene Duka              LS R3 enhancements. pTAO - Code WT changes.
//# 12/04/2006 Doris Sunga			  DR #170893 - correlation id issue in PFAO log and xml output,
//# 10/08/2007 Doris Sunga			  LS6 - implement local ejb for pfao
//# 11/10/2007 Doris Sunga			  LS6:CR14149 - implement local ejb for MFI
//# 11/13/2007 Doris Sunga			  LS6:CR14149 - MFI - handle invalid client
//# 01/29/2008 Ott Phannavong		  LS7: PFAON2 - BBNMS/OMS receive different response format.
//# 01/29/2008 Doris Sunga			  LS7: MFI2 - implement local ejb for MFI2
//# 02/19/2008 Doris Sunga			  LS7: MFI/MF2 - for application Id not equal to B is OMS
//# 02/21/2008 Doris Sunga			  LS7: PFAO2 - call PFAO2
//# 02/26/2008 Doris Sunga			  LS7: Update isVersionOne initialization
//# 03/05/2008 Doris Sunga			  LS7: Process BOND network type for BBNMS
//# 01/07/2009 Doris Sunga			  LS10: replacing PFAO2 with PFAO3
//# 09/17/2010 Julius Sembrano		  Added IBCO and IBRT Network Types
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.util.Hashtable;

import javax.naming.InitialContext;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo.ModifyFacilityInfo;
import com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo.ModifyFacilityInfoHome;
import com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo2.ModifyFacilityInfo2;
import com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo2.ModifyFacilityInfo2Home;
import com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification.PublishFacilityAssignmentOrderNotification;
import com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification.PublishFacilityAssignmentOrderNotificationHome;
import com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification3.PublishFacilityAssignmentOrderNotification3;
import com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification3.PublishFacilityAssignmentOrderNotification3Home;
import com.sbc.eia.bis.facades.rm.transactions.PublishTNAssignmentOrderNotification.PublishTNAssignmentOrderNotification;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.ApplicationMapper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
* @author jc1421
* 
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates. To enable and disable the creation of type
* comments go to Window>Preferences>Java>Code Generation.
*/
public class SoacFCIF
{
	private Logger myLogger = null;

	private String myTransaction = null;
	private Hashtable myProperties = null;

	// Update the following in the unlikely event the incoming unsolicited codes
	// change:
	private final static String[] unSolicitedCodes =
	{"FMIU", "FPLU", "FUAU", "FUND", "FANU"};
	private final static String RESEND_INDICATOR = "R";

	protected final static String PUBLISHFACILITYASSIGNMENTORDER = "PublishFacilityAssignmentOrderNotification";
	protected final static String PUBLISHFACILITYASSIGNMENTORDER3 = "PublishFacilityAssignmentOrderNotification3";
	protected final static String MODIFYFACILITYINFO = "ModifyFacilityInfo";
	protected final static String MODIFYFACILITYINFO2 = "ModifyFacilityInfo2";	
	protected final static String PUBLISHTNASSIGNMENTORDERNOTIFICATION = "PublishTNAssignmentOrderNotification";

	private PublishTNAssignmentOrderNotification cache_pTAO = null;
	private PublishFacilityAssignmentOrderNotification cache_pFAO = null;
	public static boolean unKnownClient = false;
	public boolean isVersionOne = false;

	/**
	 * Constructor: SoacFCIF.
	 * 
	 * @param aProperties
	 * @param aLogger
	 */
	public SoacFCIF(Hashtable aProperties, Logger aLogger)
	{
		myProperties = aProperties;
		myLogger = aLogger;
	}

	/**
	 * Method isUnsolicitedMsg.
	 * 
	 * @param aStatusCode
	 * @return boolean
	 */
	protected boolean isUnsolicitedMsg(String aStatusCode)
	{
		String myMethodNameName = "SoacFCIF.isUnsolicitedMsg()";
		myLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

		boolean retVal = false;

		for(int i = 0; i < unSolicitedCodes.length; i++)
		{
			if(aStatusCode.equalsIgnoreCase(unSolicitedCodes[i]))
				retVal = true;
		}

		myLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
		return retVal;
	}

	/**
	 * Method getmyTransaction.
	 * 
	 * @return String
	 */
	public String getmyTransaction()
	{
		return myTransaction;
	}

	/**
	 * Method messageRouter.
	 * 
	 * @param aUtility
	 * @param msg
	 * @throws Exception
	 */
	public void messageRouter(Utility aUtility, String msg, String correlationId)
			throws SystemFailure, Exception
	{
		String myMethodNameName = "SoacFCIF: messageRouter()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		myTransaction = null;
		try
		{
			// Parse incoming FCIF string:
			SOACServiceOrderResponseParser parsedFCIF = new SOACServiceOrderResponseParser(
					myProperties, myLogger);
			parsedFCIF.processFCIFResponseString(msg);

			String aStatusCode = parsedFCIF.getStatusCode().substring(0, 4)
					.trim();
			String aResendInd = parsedFCIF.getResendInd();

			//parse application Indicator then map to applidation ID
			//O=OMS, B=BBNMS; unknown client response is sent to OMS				
			String applicationID = retrieveApplicationType(parsedFCIF,
					aUtility);
			
			String aApplicationIDTmp = ((applicationID
					  .equalsIgnoreCase(SvcOrderConstants.BBNMS_APPLICATIONID)) 
					? applicationID
					: SvcOrderConstants.OMS_APPLICATIONID);

		    // default application ID value to BBNMS if network type is BOND (FTTNBP)			    
			aApplicationIDTmp = ((parsedFCIF.getNetworkType().equalsIgnoreCase(
					  SvcOrderConstants.FTTNBP_NETWORK)) 
					? SvcOrderConstants.BBNMS_APPLICATIONID 
					: aApplicationIDTmp);
			
			myLogger.log(LogEventId.DEBUG_LEVEL_1, "Status Code[" + aStatusCode
					+ "] Resend Indicator[" + aResendInd + "]");

			if(parsedFCIF.getNetworkType().equalsIgnoreCase(
					SvcOrderConstants.VOIP_NETWORK))
			{
				myTransaction = PUBLISHTNASSIGNMENTORDERNOTIFICATION;
				myLogger.log(LogEventId.INFO_LEVEL_1, "Receiving: "
						+ myTransaction);

				if(cache_pTAO == null)
				{
					cache_pTAO = new PublishTNAssignmentOrderNotification(
							aUtility, myProperties, myLogger);
				}

				cache_pTAO.execute(parsedFCIF, correlationId);
			}
			// ***** ModifyFacilityInfo *****
			else if(this.isUnsolicitedMsg(aStatusCode) == true
					|| (aResendInd != null && aResendInd
							.equalsIgnoreCase(RESEND_INDICATOR)))
			{
				InitialContext ctx = new InitialContext();
							
				myLogger.log(LogEventId.INFO_LEVEL_1,
							">******* ApplicationID : " + aApplicationIDTmp);
				// isVersionOne is set to true when applicationId is OMS
				// isVersionOne is set to false when applicationId is BBNMS					
				isVersionOne = (aApplicationIDTmp
							.equalsIgnoreCase(SvcOrderConstants.OMS_APPLICATIONID)?
							true : false);
				if(isVersionOne)
				{
				    //LS6 MFI: process response for OMS by calling MFI local ejb				    
				    myTransaction = MODIFYFACILITYINFO;
					myLogger.log(LogEventId.INFO_LEVEL_1, "Receiving: "
								+ myTransaction);
					Object o = ctx
						  .lookup("java:comp/env/ejb/ModifyFacilityInfoHome");

				    ModifyFacilityInfoHome home = (ModifyFacilityInfoHome)javax.rmi.PortableRemoteObject
						  .narrow(o, ModifyFacilityInfoHome.class);
				    ModifyFacilityInfo modifyFacilityInfoBean = home.create();
				    modifyFacilityInfoBean.modifyFacilityInfo(aUtility,
								myProperties, parsedFCIF, correlationId,
								aApplicationIDTmp, myLogger);				        
				}
				else
				{
				    //LS6 MFI2: process response for OMS by calling MFI2 local ejb				    
				    myTransaction = MODIFYFACILITYINFO2;
					myLogger.log(LogEventId.INFO_LEVEL_1, "Receiving: "
								+ myTransaction);
					Object o = ctx
						  .lookup("java:comp/env/ejb/ModifyFacilityInfo2Home");

				    ModifyFacilityInfo2Home home = (ModifyFacilityInfo2Home)javax.rmi.PortableRemoteObject
						  .narrow(o, ModifyFacilityInfo2Home.class);
				    ModifyFacilityInfo2 modifyFacilityInfo2Bean = home.create();
					modifyFacilityInfo2Bean.modifyFacilityInfo2(aUtility,
								myProperties, parsedFCIF, correlationId,
								aApplicationIDTmp, myLogger);						    
				}
				
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"<******* ApplicationID : " + aApplicationIDTmp);				
				
			}
			// ***** PublishFacilityAssignmentOrderNotification *****
			else if(parsedFCIF.getNetworkType().equalsIgnoreCase(
					SvcOrderConstants.FTTPIP_NETWORK)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.FTTN_NETWORK)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.RGPN_NETWORK)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.GPON_NETWORK)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.FTTNBP_NETWORK)
					// ra0331: add LS10 changes
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
									SvcOrderConstants.IPCO)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
									SvcOrderConstants.IPRT)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.IPRTBP_NETWORK)
					|| parsedFCIF.getNetworkType().equalsIgnoreCase(
							SvcOrderConstants.IPCOBP_NETWORK)
					) 
			{
				InitialContext ctx = new InitialContext();
				// isVersionOne is set to true when applicationId is OMS
				// isVersionOne is set to false when applicationId is BBNMS
				isVersionOne = (aApplicationIDTmp
						.equalsIgnoreCase(SvcOrderConstants.OMS_APPLICATIONID)?
						true : false);

				myLogger.log(LogEventId.INFO_LEVEL_1,
						">******* ApplicationID : " + aApplicationIDTmp + ", IsVersionOne" + isVersionOne);	
				
				if(isVersionOne)
				{
					//process response for OMS by calling PFAO local ejb
					myTransaction = PUBLISHFACILITYASSIGNMENTORDER;
					myLogger.log(LogEventId.INFO_LEVEL_1, "Receiving: "
							+ myTransaction);				    
					Object o = ctx
							.lookup("java:comp/env/ejb/PublishFacilityAssignmentOrderNotificationHome");
					PublishFacilityAssignmentOrderNotificationHome home = (PublishFacilityAssignmentOrderNotificationHome)javax.rmi.PortableRemoteObject
							.narrow(
									o,
									PublishFacilityAssignmentOrderNotificationHome.class);
					PublishFacilityAssignmentOrderNotification publishFacilityAssignmentOrderNotificationBean = home
							.create();
					publishFacilityAssignmentOrderNotificationBean
							.publishFacilityAssignmentOrderNotification(
									myLogger, parsedFCIF, correlationId,
									aApplicationIDTmp);
				}
				else
				{
					//LS10 process response for BBNMS by calling PFAO3 local ejb
					myTransaction = PUBLISHFACILITYASSIGNMENTORDER3;
					myLogger.log(LogEventId.INFO_LEVEL_1, "Receiving: "
							+ myTransaction);				    
					Object o = ctx
							.lookup("java:comp/env/ejb/PublishFacilityAssignmentOrderNotification3Home");
					PublishFacilityAssignmentOrderNotification3Home home = (PublishFacilityAssignmentOrderNotification3Home)javax.rmi.PortableRemoteObject
							.narrow(
									o,
									PublishFacilityAssignmentOrderNotification3Home.class);
					PublishFacilityAssignmentOrderNotification3 publishFacilityAssignmentOrderNotification3Bean = home
							.create();
					publishFacilityAssignmentOrderNotification3Bean
							.publishFacilityAssignmentOrderNotification3(
									myLogger, parsedFCIF, correlationId,
									aApplicationIDTmp);
				}
				myLogger.log(LogEventId.INFO_LEVEL_1,
						"<******* ApplicationID : " + aApplicationIDTmp);				
			}
		}
		catch(SystemFailure sfe)
		{
			throw sfe;
		}
		catch(Exception e)
		{
			throw e;
		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	}
	/**
	 * Translate application indicator given by back end to application ID.
	 * This method also sets the unKnown field to true when application indicator given by
	 * backend can not be found in the applicationIDToIndicator.properties file.
	 * @param parsedFCIF
	 * @param aUtility
	 * @return application ID or empty string if unknown
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private String retrieveApplicationType(
			SOACServiceOrderResponseParser parsedFCIF, Utility aUtility)
			throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
			NotImplemented, ObjectNotFound, DataNotFound
	{
		String myMethodName = "SoacFCIF: retrieveApplicationType()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String aApplicationIndicator = null;
		String retVal = null;

		aApplicationIndicator = parsedFCIF.getApplicationIndicator()
				.toUpperCase();

		if(aApplicationIndicator != null)

		{
			ApplicationMapper applicationMapper = ApplicationMapper
					.getInstanceOfApplicationMapper(aUtility, myProperties);
			retVal = applicationMapper
					.retrieveApplicationID(aApplicationIndicator);

			if(retVal == null)
			{
				unKnownClient = true;
				retVal = "";
			}
			//LS 7
			if(retVal.equals(SvcOrderConstants.OMS_APPLICATIONID))
			{
				isVersionOne = true;
			}

		}
		myLogger.log(LogEventId.DEBUG_LEVEL_2, "ApplicationID = <" + retVal
				+ ">");
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return retVal;
	}
}