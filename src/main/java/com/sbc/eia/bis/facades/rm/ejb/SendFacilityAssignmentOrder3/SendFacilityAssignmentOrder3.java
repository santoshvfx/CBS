// $Id: SendFacilityAssignmentOrder3.java,v 1.1 2009/01/07 22:26:53 hw7243 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author               | Notes
//# ----------------------------------------------------------------------------
//# 03/30/2005	Mark Liljequist		       Creation.

package com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder3;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder3Return;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public interface SendFacilityAssignmentOrder3 extends javax.ejb.EJBLocalObject {

	public SendFacilityAssignmentOrder3Return sendFacilityAssignmentOrder3(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aNetworkType,
		StringOpt aOldNetworkType,
		String aOrderActionId,
		String aOrderNumber,
		String aOrderActionType,
		BooleanOpt aCompletionIndicator,
		StringOpt aSubActionType,
		String aCircuitId,
		StringOpt aSecondaryCircuitId,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		StringOpt aRelatedCircuitID,
		StringOpt aSecondaryRelatedCircuitID,
		StringOpt aRelatedServiceOrderNumber,
		BooleanOpt aLineShareDisconnectFlag,
		String aClassOfService,
		BooleanOpt aResendFlag,
		CompositeObjectKey aBillingAccountNumber,
		BooleanOpt aInterceptRedirectIndicator,
	    StringOpt aDryloopRelatedCircuitId,
	    StringOpt aDSLDisconnectTelephoneNumber,
	    StringOpt aExceptionRoutingIndicator,
		ObjectPropertySeqOpt aProperties,
		Logger aLogger)
		throws InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound;
}
