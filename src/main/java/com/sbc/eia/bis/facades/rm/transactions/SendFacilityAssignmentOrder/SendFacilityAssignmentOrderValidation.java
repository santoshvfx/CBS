// $Id: SendFacilityAssignmentOrder.java,v 1.14 2006/08/25 19:16:31 jc1421 Exp $
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
//# 5/24/2006	Doris Sunga			  The original program was copied from com.sbc.eia.bis.facades.rm.transactions.SenfF1F2Order.java	
//# 5/25/2006	Doris Sunga			  renamed class to SendFacilityAssignemntOrder and
//# 								  applied LS3 requirements
//# 08/25/2006 Jon Costa			  PR18355774 for SOAC IA change to ERR, TOPListener cache 	
//# --------------------------------------------------------------------

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;

//com.sbc.eia.idl.lim_types.ProviderLocationProperty


public class SendFacilityAssignmentOrderValidation {

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
	public StringOpt aTDMTelphoneNumber = null;
	public StringOpt aRelatedServiceOrderNumber = null;
	public BooleanOpt aLineShareDisconnectFlag = null;
	public BooleanOpt aResendFlag = null;
	public String aClassOfService = null;


	public SendFacilityAssignmentOrderValidation(
		BisContext context,
		String sOACServiceOrderNumber,
		String sOACServiceOrderCorrectionSuffix,
		String networkType,
		String orderActionId,
		String orderNumber,
		String orderActionType,
		BooleanOpt completionIndicator,
		StringOpt subActionType,
		String circuitId,
		Location serviceLocation,
  	    EiaDate originalDueDate,
		EiaDateOpt subsequentDueDate,
		EiaDate applicationDate,
		StringOpt tDMTelphoneNumber,
		StringOpt relatedServiceOrderNumber,
		BooleanOpt lineShareDisconnectFlag,
		String classOfService ,
		BooleanOpt resendFlag) 
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
		aTDMTelphoneNumber = tDMTelphoneNumber;
		aRelatedServiceOrderNumber = relatedServiceOrderNumber; 
		aLineShareDisconnectFlag = lineShareDisconnectFlag;
		aResendFlag = resendFlag;
		aClassOfService = classOfService;
	}

}