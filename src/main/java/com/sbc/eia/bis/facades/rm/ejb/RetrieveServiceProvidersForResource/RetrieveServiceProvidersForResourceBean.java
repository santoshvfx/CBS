/*
 * Created on Feb 24, 2005
 *
 */
package com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource;

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
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;

//#	6/1/2005	Chaitanya			Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1

/**
 * @author va6483
 * @Creation Date: 02/24/2005
 * This is a Local Session Bean Class
 */

public class RetrieveServiceProvidersForResourceBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, RetrieveServiceProvidersForResource {

	private javax.ejb.SessionContext mySessionCtx = null;
	private com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.transactions
		.RetrieveServiceProvidersForResource
		.RetrieveServiceProvidersForResource cacheRetrieveServiceProvidersForResource =
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

	public RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
			BisContext aContext,
			String aResourceHandle,
			ObjectKey[] aServiceTypeHandles,
			Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		callerContext = aContext ;
		myLogger = aLogger;
		
		log(
			LOG_DEBUG_LEVEL_1,
			"RM RetrieveServiceProvidersForResource - retrieveServiceProvidersForResource");
		RetrieveServiceProvidersForResourceReturn retVal = null;

		try {

			if (cacheRetrieveServiceProvidersForResource == null)
				cacheRetrieveServiceProvidersForResource =
					new com
						.sbc
						.eia
						.bis
						.facades
						.rm
						.transactions
						.RetrieveServiceProvidersForResource
						.RetrieveServiceProvidersForResource(getPROPERTIES());
			retVal =
				cacheRetrieveServiceProvidersForResource.execute(
				 callerContext,
				 aResourceHandle,
				 aServiceTypeHandles,
				 aLogger);

		} finally {
			log(LOG_DEBUG_LEVEL_1, "RM RetrieveServiceProvidersForResource - retrieveServiceProvidersForResource");
		}
		return retVal;
	}
}
