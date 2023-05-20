// $Id: SendFacilityAssignmentOrder3Bean.java,v 1.1 2009/01/07 22:26:53 hw7243 Exp $
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

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;

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
import com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SendFacilityAssignmentOrder3Bean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, SendFacilityAssignmentOrder3 {

	private javax.ejb.SessionContext mySessionCtx = null;
	private com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.transactions
		.SendFacilityAssignmentOrder3
		.SendFacilityAssignmentOrder3 cacheSendFacilityAssignmentOrder3 =
		null;

	/**
	 * ejbActivate method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */

	public void ejbActivate() throws java.rmi.RemoteException {
	}

	/**
	 * ejbCreate method comment
	 * @exception javax.ejb.CreateException The exception description.
	 * @exception java.rmi.RemoteException The exception description.
	 */

	public void ejbCreate()
		throws javax.ejb.CreateException, java.rmi.RemoteException {

		init(mySessionCtx);
	}

	/**
	 * ejbPassivate method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */

	public void ejbPassivate() throws java.rmi.RemoteException {
	}

	/**
	 * ejbRemove method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */

	public void ejbRemove() throws java.rmi.RemoteException {
	}

	/**
	 * @see javax.ejb.EJBLocalObject#remove()
	 * @exception javax.ejb.EJBException The exception description.
	 * @exception java.rmi.RemoveException The exception description.
	 */

	public void remove() throws EJBException, RemoveException {
	}

	/**
	 * @see javax.ejb.EJBLocalObject#getEJBLocalHome()
	 * @exception javax.ejb.EJBException The exception description.
	 */

	public EJBLocalHome getEJBLocalHome() throws EJBException {

		return null;
	}

	/**
	 * @see javax.ejb.EJBLocalObject#isIdentical(EJBLocalObject)
	 * @exception javax.ejb.EJBException The exception description.
	 */

	public boolean isIdentical(EJBLocalObject a) throws EJBException {

		if (a == this)
			return true;

		return false;
	}

	/**
	 * @see javax.ejb.EJBLocalObject#getPrimaryKey()
	 * @exception javax.ejb.EJBException The exception description.
	 */

	public Object getPrimaryKey() throws EJBException {

		return null;
	}

	/**
	 * getSessionContext method comment
	 * @return javax.ejb.SessionContext
	 */

	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext method comment
	 * @param ctx javax.ejb.SessionContext
	 * @exception java.rmi.RemoteException The exception description.
	 */

	public void setSessionContext(javax.ejb.SessionContext ctx)
		throws java.rmi.RemoteException {
		mySessionCtx = ctx;
	}

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
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		myLogger = aLogger;

		log(
			LOG_DEBUG_LEVEL_1,
			"RM SendFacilityAssignmentOrder3 - SendFacilityAssignmentOrder3");
		SendFacilityAssignmentOrder3Return retVal = null;

		try {

			if (cacheSendFacilityAssignmentOrder3 == null)
				cacheSendFacilityAssignmentOrder3 =
					new com
						.sbc
						.eia
						.bis
						.facades
						.rm
						.transactions
						.SendFacilityAssignmentOrder3
						.SendFacilityAssignmentOrder3(getPROPERTIES());
			retVal =
				cacheSendFacilityAssignmentOrder3.execute(
					aContext,
					aSOACServiceOrderNumber, 
					aSOACServiceOrderCorrectionSuffix, 
					aNetworkType, 
					aOldNetworkType, 
					aOrderActionId, 
					aOrderNumber, 
					aOrderActionType, 
					aCompletionIndicator, 
					aSubActionType, 
					aCircuitId, 
					aSecondaryCircuitId, 
					aServiceLocation, 
					aOriginalDueDate, 
					aSubsequentDueDate, 
					aApplicationDate, 
					aRelatedCircuitID, 
					aSecondaryRelatedCircuitID, 
					aRelatedServiceOrderNumber, 
					aLineShareDisconnectFlag, 
					aClassOfService, 
					aResendFlag, 
					aBillingAccountNumber,
					aInterceptRedirectIndicator,
				    aDryloopRelatedCircuitId,
				    aDSLDisconnectTelephoneNumber,
				    aExceptionRoutingIndicator,
					aProperties,
					aLogger);

		} finally {
			log(
				LOG_DEBUG_LEVEL_1,
				"RM SendFacilityAssignmentOrder3 - SendFacilityAssignmentOrder3");
		}
		return retVal;
	}
}
