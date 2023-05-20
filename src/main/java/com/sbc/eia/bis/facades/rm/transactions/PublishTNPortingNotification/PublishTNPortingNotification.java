//$Id: PublishTNPortingNotification.java,v 1.6 2008/06/23 17:15:07 op1664 Exp $
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
//# 06/05/2006  Jyothi Jasti         Creation.
//# 06/22/06    Jyothi Jasti         Updated for LS3.
//# 5/13/2008   Ott Phannavong		 LS7 CR18595 modified validateInput() to accept NotifySvDisconnect message

package com.sbc.eia.bis.facades.rm.transactions.PublishTNPortingNotification;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.soa.SOA;
import com.sbc.eia.bis.BusinessInterface.rm.soa.SOAConstants;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.rm_ls_types.NotifyActivityTypeValues;

public class PublishTNPortingNotification extends TranBase {

	private Utility utility = null;
	private Hashtable properties = null;
	private SOA service = null;
	private BisContext context = null;

	/**
	 * Constructor
	 */
	public PublishTNPortingNotification() {
		super();
	}

	/**
	 * Constructor
	 * @param param
	 */
	public PublishTNPortingNotification(Hashtable param) {
		super(param);
		utility = this;
		properties = getPROPERTIES();
	}

	/**
	 * execute method process the logic to read, parse, create and send message
	 * @param aContext
	 * @param message
	 * @param aLogger
	 */
	public void execute(BisContext aContext, String message, Logger aLogger) {

		myLogger = aLogger;

		String methodName = "PublishTNPortingNotification:execute()";
		log(LOG_DEBUG_LEVEL_1, ">" + methodName);

		logInput(aContext, message);

		if (aContext == null) {
			aContext =
				BisContextHelper.setBisContext(
					null,
					SOAConstants.SOA,
					null,
					myLogger.getBisLogger().get_correlation_id(),
					properties);
		}

		String msgType = validateInput(message);

		if (service == null) {
			service = new SOA(utility, properties);
		}

		try {
			if (!msgType.equals(SOAConstants.UNKNOWN)) {
				service.publishTNPortingNotification(
					aContext,
					message,
					msgType);
			} else {
				log(LOG_INFO_LEVEL_1, "Received unknown message.");
			}
		} finally {
			log(LOG_DEBUG_LEVEL_1, "<" + methodName);
		}

	}

	/**
	 * method validates the XML message for particular tags
	 * @param message 
	 * @return the activity type.
	 */
	private String validateInput(String message) {
		String methodName = "PublishTNPortingNotification:validateInput()";
		log(LOG_DEBUG_LEVEL_1, ">" + methodName);

		String msgType = null;

		if (message.indexOf(SOAConstants.PUBLISH_TN_PORTING_NOTIFICATION_RESPONSE_BIS_MSG)!= -1)
		{
			msgType = SOAConstants.PUBLISH_TN_PORTING_NOTIFICATION_RESPONSE_BIS_MSG;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_SV_ACTIVITY_FAILURE) != -1)
		{
			msgType = SOAConstants.NOTIFY_SV_ACTIVITY_FAILURE;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_ACTIVATE) != -1)
		{
			msgType = NotifyActivityTypeValues.NOTIFY_ACTIVATE;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_CREATE) != -1)
		{
			msgType = NotifyActivityTypeValues.NOTIFY_CREATE;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_RELEASE) != -1)
		{
			msgType = NotifyActivityTypeValues.NOTIFY_RELEASE;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_PORT_TO_ORIGINAL) != -1)
		{
			msgType = NotifyActivityTypeValues.NOTIFY_PORT_TO_ORIGINAL;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_CANCEL) != -1)
		{
			msgType = NotifyActivityTypeValues.NOTIFY_CANCEL;
		}
		else if (message.indexOf(SOAConstants.NOTIFY_SV_DISCONNECT) != -1)
		{
			msgType = SOAConstants.NOTIFY_SV_DISCONNECT;
		}
		else
		{
			msgType = SOAConstants.UNKNOWN;
		}

		log(LOG_DEBUG_LEVEL_1, "<" + methodName);
		return msgType;
	}

	/**
	 * logs the input XML message
	 * @param message
	 */
	private void logInput(BisContext aContext, String message) {
		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
			log(LOG_DEBUG_LEVEL_2, "aContext<null>");
		}
		log(LOG_INPUT_DATA, message);
	}

}
