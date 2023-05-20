//$Id: EmailHelper.java,v 1.6 2006/07/26 22:16:41 jp2854 Exp $
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
//# 06/12/2006  Jyothi Jasti         Creation for LS3.
//# 06/22/2006  Jyothi Jasti         Additional changes and code WT changes.

package com.sbc.eia.bis.BusinessInterface.rm.soa;

import java.util.Hashtable;

import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.ExceptionDataOptBisHelper;
import com.sbc.bccs.utilities.Utility;

public class EmailHelper {

	public static String NA = "NA";
	public static String NEW_LINE = "\n";
	private Utility utility = null;
	private Hashtable properties = null;
	private boolean sendEmail = false;
	private boolean emailSent = false;
	private String rootCause = null;
	private String emailSubject = null;
	private String emailBody = null;
	private String soaMessage = null;

	private PublishTNPortingNotificationReturn publishTNPortingNotification =
		null;

	public EmailHelper(Utility aUtility, Hashtable aProperties) {
		properties = aProperties;
		utility = aUtility;
	}

	/**
	 * @return 
	 */
	public String getRootCause() {
		return rootCause;
	}

	/**
	 * @return
	 */
	public boolean isSendEmail() {
		return sendEmail;
	}

	/**
	 * @return
	 */
	public boolean isEmailSent() {
		return emailSent;
	}

	/**
	 * @return
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @return
	 */
	public String getEmailBody() {
		return emailBody;
	}

	/**
	 * @return
	 */
	public String getSoaMessage() {
		return soaMessage;
	}

	/**
	 * @param string
	 */
	public void setRootCause(String string) {
		rootCause = string;
	}

	/**
	 * @param flag
	 */
	public void setSendEmail(boolean flag) {
		sendEmail = flag;
	}

	/**
	 * @param flag
	 */
	public void setEmailSent(boolean flag) {
		emailSent = flag;
	}

	/**
	 * @param string
	 */
	public void setEmailSubject(String string) {
		emailSubject = string;
	}

	/**
	 * @param string
	 */
	public void setEmailBody(String string) {
		emailBody = string;
	}

	/**
	 * @param string
	 */
	public void setSoaMessage(String string) {
		soaMessage = string;
	}

	/**
	 * @return
	 */
	public PublishTNPortingNotificationReturn getPublishTNPortingNotification() {
		return publishTNPortingNotification;
	}

	/**
	 * @param publishTNreturn
	 */
	public void setPublishTNPortingNotificationReturn(PublishTNPortingNotificationReturn publishTNreturn) {
		publishTNPortingNotification = publishTNreturn;
	}

	/**
	 * buildEmailMessage method prepares the Email subject and body and sets to instance variable. 
	 */
	public void buildEmailMessage() {

		setEmailSubject("LightSpeed Assignment Problem ");

		StringBuffer msg = new StringBuffer("");

		msg.append(
			"A pending service order, or an existing service, may require your attention.\n\n\n");
		
		msg.append( rootCause + NEW_LINE );

		if (publishTNPortingNotification == null) {
			if (soaMessage != null) {
				msg.append(soaMessage);
			}
		} else {
			msg.append(
				NEW_LINE
					+ "SOAC Order Number			:	["
					+ getValue(
						publishTNPortingNotification.aSoacServiceOrderNumber)
					+ "]");

			msg.append(
				NEW_LINE
					+ "Correction Suffix			:	["
					+ getValue(
						publishTNPortingNotification
							.aSOACServiceOrderCorrectionSuffix)
					+ "]");

			msg.append(
				NEW_LINE
					+ "Initiating SPID			:	["
					+ getValue(
						publishTNPortingNotification.aLocalServiceProviderId)
					+ "]");

			msg.append(
				NEW_LINE
					+ "TN					:	["
					+ getValue(publishTNPortingNotification.aTelephoneNumber)
					+ "]");

			msg.append(
				NEW_LINE
					+ "Cause Code				:	["
					+ getValue(publishTNPortingNotification.aCauseCode)
					+ "]");

			msg.append(
				NEW_LINE
					+ "Notify Activity Type		:	["
					+ getValue(publishTNPortingNotification.aNotifyActivityType)
					+ "]");

			msg.append(
				NEW_LINE
					+ "Old SPID				:	["
					+ getValue(
						publishTNPortingNotification.aOldServiceProviderId)
					+ "]");

			msg.append(
				NEW_LINE
					+ "New SPID				:	["
					+ getValue(
						publishTNPortingNotification.aNewServiceProviderId)
					+ "]");

			String temp = null;
			//If there is any exception the temp will be null. The temp is set to "NA" if it is null or "".
			try {
				temp =
					BisDateUtil.dateToString(
						publishTNPortingNotification.aDueDate.theValue(),
						"yyyyMMdd");	
			} catch (org.omg.CORBA.BAD_OPERATION e) {
			}
			if (temp == null || temp.equals(""))
				temp = NA;
			msg.append(
				NEW_LINE + "Due Date Time			:	[" + temp + "]");

			temp = null;
			//If there is any exception the temp will be null. The temp is set to "NA" if it is null or "".
			try {
				temp =
					(new ExceptionDataOptBisHelper(publishTNPortingNotification
						.aStatus))
						.toString();
			} catch (Exception e) {
			}
			if (temp == null || temp.equals(""))
				temp = NA;

			msg.append(
				NEW_LINE + "Exception Status			:	[" + temp + "]");

		}
		emailBody = msg.toString();

		utility.log(
			LogEventId.INFO_LEVEL_1,
			"EMail Message : " + emailSubject + " : " + emailBody);
	}

	/**
	 * @param aStringOpt
	 * @return
	 */
	public String getValue(StringOpt aStringOpt) {
		String value = null;
		try {
			value = aStringOpt.theValue().trim();
		} catch (Exception e) {
		}

		if (value == null || value.equals(""))
			value = NA;

		return value;
	}

}
