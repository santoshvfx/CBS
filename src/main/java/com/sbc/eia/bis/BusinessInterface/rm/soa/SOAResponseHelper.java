//$Id: SOAResponseHelper.java,v 1.5 2006/07/26 22:16:41 jp2854 Exp $
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
//# 06/06/2006  Jyothi Jasti         Creation for LS3.
//# 07/24/2006  Jyothi Jasti         Corrected the reason for sending email in validateMessage method.

package com.sbc.eia.bis.BusinessInterface.rm.soa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TimeZone;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.soa.interfaces.OpIdl33DefnOrderNumberDef;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.types.EiaDate;

public class SOAResponseHelper {

	private Utility utility = null;
	private Hashtable properties = null;

	/**
	 * Constructor
	 * @param aUtility
	 * @param aProperties
	 */
	public SOAResponseHelper(Utility aUtility, Hashtable aProperties) {
		properties = aProperties;
		utility = aUtility;

	}

	/**
	 * filterMessage method.
	 * @param message
	 * @return boolean flag.
	 */
	protected boolean filterMessage(
		OpIdl33ClientOrderPathSubscriptionNotificationsNotifySvActivityFailureImpl message) {
		boolean isRequiredMessage = false;
		String status = message.getActStatus();

		for (int i = 0; i < SOAConstants.SOA_ACTIVITY_STATUS.length; i++) {
			if (status.equals(SOAConstants.SOA_ACTIVITY_STATUS[i])) {
				isRequiredMessage = true;
				break;
			}
		}
		return isRequiredMessage;
	}

	/**
	 * validateMessage method validates the SOA response for SOAC order number.
	 * @param orderNumber
	 * @return boolean flag
	 */
	protected boolean validateMessage(
		OpIdl33DefnOrderNumberDef orderNumber,
		EmailHelper emailHelper) {

		boolean isValidMessage = true;

		if (orderNumber == null
			|| isStringEmpty(orderNumber.getSerNum())
			|| isStringEmpty(orderNumber.getSupNum())) {
				
			isValidMessage = false;
			emailHelper.setSendEmail(true);
			emailHelper.setRootCause(SOAConstants.REASONS_FOR_SENDING_EMAIL[1]);
			
		}

		return isValidMessage;

	}
	
	/**
	 * method checks the SOA response status.
	 * @param response
	 * @return true if failure response from SOA.
	 */
	protected boolean processResponse(OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl response) {
		String methodName = "SOAResponseHelper:processResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
		boolean isFailureResponse = false;

		if (SOA.soaToBisFailureCodeMappings.containsKey(response.getReturn())) {
			isFailureResponse = true;
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
		return isFailureResponse;
	}

	/**
	 * decode method converts the SOA response XML message to a Java object. 
	 * @param xmlMessage
	 * @return Java object 
	 * @throws DecoderException
	 * @throws ServiceException
	 */
	public Object decode(String xmlMessage)
		throws DecoderException, ServiceException {

		String methodName = "SOAResponseHelper:decode()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

		//Set marshalUnmarshalOptions properties.
		Properties marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
			"true");

		Object response = null;

		DefaultJAXBEncoderDecoder decoder =
			new DefaultJAXBEncoderDecoder(
				"com.sbc.eia.bis.embus.service.soa.interfaces",
				marshalUnmarshalOptions);

		try {
			response = decoder.decode(xmlMessage)[0];
		} catch (DecoderException e) {
			utility.log(
				LogEventId.ERROR,
				"Decoder Exception: [" + e.toString() + "]");
			throw e;
		} catch (ServiceException e) {
			utility.log(
				LogEventId.ERROR,
				"Service Exception: [" + e.toString() + "]");

			throw e;
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
		return response;
	}

	/**
	 * buildEiaDate method converts date which is in GMT to local and populates the EiaDate using date.
	 * @param date
	 * @return EiaDate
	 */
	public EiaDate buildEiaDate(String strDate) {
		//The date format is yyyyMMddhhmmss.GMT" 
		EiaDate aEiaDate = null;

		if (strDate != null && strDate.trim().length() > 0) {
			Date date = null;
			try {
				SimpleDateFormat myDateFormat =
					new SimpleDateFormat("yyyyMMddhhmmss");
				myDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				date = myDateFormat.parse(strDate.trim());
			} catch (ParseException e) {
				utility.log(LogEventId.INFO_LEVEL_1,"Failed to parse the date. " + strDate + " [" + e.toString() + "]");
				return aEiaDate;
			}
			aEiaDate = new EiaDate();
			Calendar dateCal = Calendar.getInstance();
			dateCal.setTime(date);
			aEiaDate.aDay = (short) dateCal.get(Calendar.DAY_OF_MONTH);
			aEiaDate.aMonth = (short) (dateCal.get(Calendar.MONTH) + 1);
			aEiaDate.aYear = (short) dateCal.get(Calendar.YEAR);
		}
		return aEiaDate;
	}
	
	/**
	 * isStringEmpty checks if the string is empty or not.
	 * @param stringToCheck
	 * @return boolean flag
	 */
	public static boolean isStringEmpty(String stringToCheck) {
		if (stringToCheck != null && stringToCheck.trim().length() > 0)
			return false;

		return true;
	}

}
