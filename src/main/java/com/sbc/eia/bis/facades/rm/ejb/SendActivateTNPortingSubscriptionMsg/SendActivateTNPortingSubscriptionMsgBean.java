// $Id: SendActivateTNPortingSubscriptionMsgBean.java,v 1.3 2006/06/05 16:14:54 jp2854 Exp $
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

package com.sbc.eia.bis.facades.rm.ejb.SendActivateTNPortingSubscriptionMsg;

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
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public class SendActivateTNPortingSubscriptionMsgBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, SendActivateTNPortingSubscriptionMsg {

	private javax.ejb.SessionContext mySessionCtx = null;
	private com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.transactions
		.SendActivateTNPortingSubscriptionMsg
		.SendActivateTNPortingSubscriptionMsg cacheSendActivateTNPortingSubscriptionMsg =
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

	public SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers,
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
			"RM SendActivateTNPortingSubscriptionMsg - sendActivateTNPortingSubscriptionMsg");
		SendActivateTNPortingSubscriptionMsgReturn retVal = null;

		try {

			if (cacheSendActivateTNPortingSubscriptionMsg == null)
				cacheSendActivateTNPortingSubscriptionMsg =
					new com
						.sbc
						.eia
						.bis
						.facades
						.rm
						.transactions
						.SendActivateTNPortingSubscriptionMsg
						.SendActivateTNPortingSubscriptionMsg(getPROPERTIES());
			retVal =
				cacheSendActivateTNPortingSubscriptionMsg.execute(
					aContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aLocalServiceProviderId,
					aTelephoneNumbers,
					aLogger);

		} finally {
			log(
				LOG_DEBUG_LEVEL_1,
				"RM SendActivateTNPortingSubscriptionMsg - sendActivateTNPortingSubscriptionMsg");
		}
		return retVal;
	}
}
