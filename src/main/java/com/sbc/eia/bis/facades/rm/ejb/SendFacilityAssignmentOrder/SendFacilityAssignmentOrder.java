// $Id: SendFacilityAssignmentOrder.java,v 1.2 2006/05/02 22:16:19 ml2917 Exp $
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

package com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder;

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
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public interface SendFacilityAssignmentOrder extends javax.ejb.EJBLocalObject {

	public SendFacilityAssignmentOrderReturn sendFacilityAssignmentOrder(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aNetworkType,
		String aOrderActionId,
		String aOrderNumber,
		String aOrderActionType,
		BooleanOpt aCompletionIndicator,
		StringOpt aSubActionType,
		String aCircuitId,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		StringOpt aTDMTelphoneNumber,
		StringOpt aRelatedServiceOrderNumber,
		BooleanOpt aLineShareDisconnectFlag,
		String aClassOfService,
		BooleanOpt aResendFlag,
		ObjectPropertySeqOpt aProperties,
		Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;
}
