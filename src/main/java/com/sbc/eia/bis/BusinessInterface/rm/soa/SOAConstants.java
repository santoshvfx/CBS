//$Id: SOAConstants.java,v 1.9 2008/06/23 17:17:46 op1664 Exp $
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
//# 06/02/2006  Jyothi Jasti         Creation for LS3.
//# 07/19/2006  Jyothi Jasti         Updated REASONS_FOR_SENDING_EMAIL variable.
//# 5/13/2008   Ott Phannavong		 LS7 CR18595 add new constants to accept NotifySvDisconnect

package com.sbc.eia.bis.BusinessInterface.rm.soa;

public class SOAConstants {
	
	public static final String SOA = "SOA" ;
	public static final String PUBLISH_TN_PORTING_NOTIFICATION = "PublishTNPortingNotification";
	public static final String SEND_ACTIVATE_TN_PORTING_NOTIFICATION_MSG = "SendActivateTNPortingSubscriptionMsg";
	
	public static final String PUBLISH_TN_PORTING_NOTIFICATION_RESPONSE_BIS_MSG = "_publishTNPortingNotificationResponseBISMsg";
	public static final String NOTIFY_SV_ACTIVITY_FAILURE = "NotifySvActivityFailure";
	public static final String NOTIFY_CREATE = "NotifySvCreateAck";
	public static final String NOTIFY_ACTIVATE = "NotifySvActivate>";
	public static final String NOTIFY_RELEASE = "NotifySvReleaseAck";
	public static final String NOTIFY_CANCEL = "NotifySvCancel>";
	public static final String NOTIFY_PORT_TO_ORIGINAL = "NotifySvPTO>";
	public static final String UNKNOWN = "Unknown";
	
	public static final String ORDER_TYPE = "C";
	public static final String[] SOA_ACTIVITY_STATUS = { "FailedActivity", "CancelledActivity", "AutoCancelledActivity" };
	public static final String[] REASONS_FOR_SENDING_EMAIL = { "Failed to decode SOA message", "Missing SOAC Order Number or Correction suffix in SOA message.", "Failed to create BIS message", "Failed to send BIS message"}; 
	public static final String NOTIFY_SV_DISCONNECT = "NotifySvDisconnect";
	public static final String SUBSCRIPTION_VERSION_STATE_ACTIVE = "Active";
}
