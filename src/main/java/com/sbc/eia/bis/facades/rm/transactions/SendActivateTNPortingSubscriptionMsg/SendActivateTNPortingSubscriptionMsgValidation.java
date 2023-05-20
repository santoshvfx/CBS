// $Id: SendActivateTNPortingSubscriptionMsgValidation.java,v 1.1 2006/06/01 15:52:36 jp2854 Exp $
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
//# 06/01/2006  Jyothi Jasti          Creation
package com.sbc.eia.bis.facades.rm.transactions.SendActivateTNPortingSubscriptionMsg;

import com.sbc.eia.idl.bis_types.BisContext;

public class SendActivateTNPortingSubscriptionMsgValidation {
	public BisContext aContext = null;
	public String aSOACServiceOrderNumber = null;
	public String aSOACServiceOrderCorrectionSuffix = null;
	public String aLocalServiceProviderId = null;
	public String[] aTelephoneNumbers = null;

	public SendActivateTNPortingSubscriptionMsgValidation(
		BisContext context,
		String sOACServiceOrderNumber,
		String sOACServiceOrderCorrectionSuffix,
		String localServiceProviderId,
		String[] telephoneNumbers) {

		aContext = context;
		aSOACServiceOrderNumber = sOACServiceOrderNumber;
		aSOACServiceOrderCorrectionSuffix = sOACServiceOrderCorrectionSuffix;
		aLocalServiceProviderId = localServiceProviderId;
		aTelephoneNumbers = telephoneNumbers;
	}
}
