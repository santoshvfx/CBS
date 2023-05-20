// $Id: SendFacilityAssignmentOrder3Validation.java,v 1.2 2008/02/25 07:58:08
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

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder3;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.OrderActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SendFacilityAssignmentOrder3Validation {

 	public BisContext aContext = null;
	public String aSOACServiceOrderNumber = null;
	public String aSOACServiceOrderCorrectionSuffix = null;
	public String aNetworkType = null;
	public String aOrderActionId= null;
	public String aOrderNumber = null;
	public String aOrderActionType = null;
	public BooleanOpt aCompletionIndicator = null; 
	public StringOpt aSubActionType = null;
	public String aCircuitId = null;
	public Location aServiceLocation = null;
	public EiaDate aOriginalDueDate = null;
	public EiaDateOpt aSubsequentDueDate = null;
	public EiaDate aApplicationDate = null;
	public StringOpt aRelatedServiceOrderNumber = null;
	public BooleanOpt aLineShareDisconnectFlag = null;
	public BooleanOpt aResendFlag = null;
	public String aClassOfService = null;
	
	//The name of these fields need to match the xml validation script
	// SendFacilityAssignmentOrder3Validate.xml
	public StringOpt optOldNetworkType = null;
	public StringOpt optSecondaryCircuitId = null;
	public CompositeObjectKey aBillingAccountNumber = null;
	
	//ra0331: Added Changes to LS10
	public BooleanOpt aInterceptRedirectIndicator = null;
	public StringOpt aDryloopRelatedCircuitId = null;
	public StringOpt aDSLDisconnectTelephoneNumber = null;
	public StringOpt aExceptionRoutingIndicator = null;

	public String aOldNetworkType = null;
	public String aSecondaryCircuitId = null;

	
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
	 * @param aInterceptRedirectIndicator
	 * @param aDryloopRelatedCircuitId
	 * @param aDSLDisconnectTelephoneNumber
	 * @param aExceptionRoutingIndicator
	 */
	public SendFacilityAssignmentOrder3Validation(BisContext context,
			String sOACServiceOrderNumber,
	        String sOACServiceOrderCorrectionSuffix, String networkType,
	        StringOpt oldNetworkType, String orderActionId, String orderNumber,
	        String orderActionType, BooleanOpt completionIndicator,
	        StringOpt subActionType, String circuitId,
	        StringOpt secondaryCircuitId, Location serviceLocation,
	        EiaDate originalDueDate, EiaDateOpt subsequentDueDate,
	        EiaDate applicationDate, StringOpt relatedCircuitID,
	        StringOpt secondaryRelatedCircuitID,
	        StringOpt relatedServiceOrderNumber,
	        BooleanOpt lineShareDisconnectFlag, String classOfService,
	        BooleanOpt resendFlag, CompositeObjectKey billingAccountNumber,
	        BooleanOpt interceptRedirectIndicator,
			StringOpt dryloopRelatedCircuitId,
			StringOpt dSLDisconnectTelephoneNumber,
			StringOpt exceptionRoutingIndicator)
	{

		aContext = context;
		aSOACServiceOrderNumber = sOACServiceOrderNumber;
		aSOACServiceOrderCorrectionSuffix = sOACServiceOrderCorrectionSuffix;
		aNetworkType = networkType;
		aOrderActionId = orderActionId;
		aOrderNumber = orderNumber;
		aOrderActionType = orderActionType;
		aCompletionIndicator = completionIndicator;
		aSubActionType = subActionType; 
		aCircuitId = circuitId; 
		aServiceLocation = serviceLocation;		
		aOriginalDueDate = originalDueDate;
		aSubsequentDueDate = subsequentDueDate;
		aApplicationDate = applicationDate;
		aRelatedServiceOrderNumber = relatedServiceOrderNumber; 
		aLineShareDisconnectFlag = lineShareDisconnectFlag;
		aResendFlag = resendFlag;
		aClassOfService = classOfService;
		optOldNetworkType = oldNetworkType;
		optSecondaryCircuitId = secondaryCircuitId;
		aBillingAccountNumber = billingAccountNumber;
		
		//ra0331: Added Changes to LS10
		aInterceptRedirectIndicator = interceptRedirectIndicator;
		aDryloopRelatedCircuitId = dryloopRelatedCircuitId;
		aDSLDisconnectTelephoneNumber = dSLDisconnectTelephoneNumber;
		aExceptionRoutingIndicator = exceptionRoutingIndicator;

		
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
				aOldNetworkType = optOldNetworkType.theValue();
			}
			catch(org.omg.CORBA.BAD_OPERATION ex)
			{
				//old network type is stringOpt is ok for it to be null
				aOldNetworkType = null;
			}

		} else {
			//No need to check, just making sure it pass validation
			aOldNetworkType = "UNCHECKED";
		}
		if(aNetworkType
				.equalsIgnoreCase(SoacServiceOrderConstants.FTTNBP_INDICATOR)
				|| (aOldNetworkType != null &&
						aOldNetworkType.equalsIgnoreCase(
								SoacServiceOrderConstants.FTTNBP_INDICATOR)))
		{
			try
			{
				aSecondaryCircuitId = optSecondaryCircuitId.theValue();
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
			aSecondaryCircuitId = aCircuitId;
		}
		billingAccountNumber = aBillingAccountNumber;	
	}	
}