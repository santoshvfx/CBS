// $Id: SendFacilityAssignmentOrder2Validation.java,v 1.2 2008/02/25 07:58:08
// op1664 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//# RESTRICTED - PROPRIETARY INFORMATION
//# The information herein is for use only by authorized employees
//# of SBC Services Inc. and authorized Affiliates of SBC Services,
//# Inc., and is not for general distribution within or outside the
//# respective companies.
//# Copying or reproduction without prior written approval is prohibited.
//#
//# © 2008-2015 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date | Author | Notes
//# 01/29/2008 Ott Phannavong Created initial file
//# --------------------------------------------------------------------
//############################################################################

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder2;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder.SendFacilityAssignmentOrderValidation;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;

public class SendFacilityAssignmentOrder2Validation extends
		SendFacilityAssignmentOrderValidation
{
	//The name of these fields need to match the xml validation script
	// SendFacilityAssignmentOrder2Validate.xml
	public String oldNetworkType = null;
	public String secondaryCircuitId = null;

	public CompositeObjectKey billingAccountNumber = null;

	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aNetworkType
	 * @param aOrderActionId
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aCompletionIndicator
	 * @param aSubActionType
	 * @param aCircuitId
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aTDMTelphoneNumber
	 * @param aRelatedServiceOrderNumber
	 * @param aLineShareDisconnectFlag
	 * @param aClassOfService
	 * @param aResendFlag
	 * @param aOldNetworkType
	 * @param aSecondaryCircuitID
	 * @param aSecondaryRelatedCircuitID
	 * @param aBillingAccountNumber
	 */
	public SendFacilityAssignmentOrder2Validation(BisContext aContext,
			String aSOACServiceOrderNumber,
			String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
			String aOrderActionId, String aOrderNumber,
			String aOrderActionType, BooleanOpt aCompletionIndicator,
			StringOpt aSubActionType, String aCircuitId,
			Location aServiceLocation, EiaDate aOriginalDueDate,
			EiaDateOpt aSubsequentDueDate, EiaDate aApplicationDate,
			StringOpt aTDMTelphoneNumber, StringOpt aRelatedServiceOrderNumber,
			BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
			BooleanOpt aResendFlag, StringOpt aOldNetworkType,
			StringOpt aSecondaryCircuitID,
			StringOpt aSecondaryRelatedCircuitID,
			CompositeObjectKey aBillingAccountNumber)
	{

		super(aContext, aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix, aNetworkType,
				aOrderActionId, aOrderNumber, aOrderActionType,
				aCompletionIndicator, aSubActionType, aCircuitId,
				aServiceLocation, aOriginalDueDate, aSubsequentDueDate,
				aApplicationDate, aTDMTelphoneNumber,
				aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
				aClassOfService, aResendFlag);

		// Old Network Type is conditional for change or change amend order,
		// otherwise this field is optional.
		if(aOrderActionType
						.equalsIgnoreCase(OrderActionTypeValues.ORDER_ACTION_CHANGE) 
						&& (aNetworkType.equalsIgnoreCase(
								SoacServiceOrderConstants.FTTNBP_INDICATOR) ||aNetworkType.equalsIgnoreCase(
										SoacServiceOrderConstants.FTTN_INDICATOR)))
		{
			try
			{
				oldNetworkType = aOldNetworkType.theValue();
			}
			catch(org.omg.CORBA.BAD_OPERATION ex)
			{
				//old network type is stringOpt is ok for it to be null
				oldNetworkType = null;
			}

		}
		else
		{
			//No need to check, just making sure it pass validation
			oldNetworkType = "UNCHECKED";
		}
		if(aNetworkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTNBP_INDICATOR)
				|| (oldNetworkType != null &&
						oldNetworkType.equalsIgnoreCase(
								SoacServiceOrderConstants.FTTNBP_INDICATOR)))
		{
			try
			{
				secondaryCircuitId = aSecondaryCircuitID.theValue();
			}
			catch(org.omg.CORBA.BAD_OPERATION ex)
			{
				//secondaryCircuitId is stringOpt it is ok for it to be null
				secondaryCircuitId = null;
			}
		}
		else
		{
			//No need to check, just making sure it pass validation
			secondaryCircuitId = aCircuitId;
		}
		billingAccountNumber = aBillingAccountNumber;

	}
}