// $Id: PublishRGActivationBean.java,v 1.5 2005/07/14 20:14:25 ck2932 Exp $
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
//# 03/30/2005	Vani Sree		       Creation.
//# 04/08/2005  Jinmin Ni              Changed to accommodate the IDL interface change
//# 06/01/2005	Chaitanya			   Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1

package com.sbc.eia.bis.facades.rm.ejb.PublishRGActivation;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.types.Time;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;

/**
 * @author va6483
 * @Creation Date: 03/30/2005
 * This is a Local Session Bean Class
 */

public class PublishRGActivationBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, PublishRGActivation {

	private javax.ejb.SessionContext mySessionCtx = null;
	private com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.transactions
		.PublishRGActivation
		.PublishRGActivation cachePublishRGActivation =
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

	public PublishRGActivationReturn publishRGActivation(
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties,
		Logger aLogger)
	 throws
		 InvalidData,
		 AccessDenied,
		 BusinessViolation,
		 SystemFailure,
		 NotImplemented,
		 ObjectNotFound,
		 DataNotFound	{
		
		myLogger = aLogger;

		log(LOG_DEBUG_LEVEL_1, "RM PublishRGActivation - publishRGActivation");
		PublishRGActivationReturn retVal = null;

		try {

			if (cachePublishRGActivation == null)
				cachePublishRGActivation =
					new com
						.sbc
						.eia
						.bis
						.facades
						.rm
						.transactions
						.PublishRGActivation
						.PublishRGActivation(getPROPERTIES());
			retVal =
				cachePublishRGActivation.execute(
					aContext,
					aCustomerTransportId,
					aBillingAccountNumber,
					aDSLAM,
					aRG,
					aActivationTime,
					aOrderAction,
					aProperties,
					aLogger);


		} finally {
			log(LOG_DEBUG_LEVEL_1, "RM PublishRGActivation - publishRGActivation");
		}
		return retVal;
	}
}
