//$Id: PublishTNPortingNotificationBean.java,v 1.1 2006/06/15 22:06:04 jp2854 Exp $
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
//# 06/05/2006  Jyothi Jasti         Creation.

package com.sbc.eia.bis.facades.rm.ejb.PublishTNPortingNotification;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;

import com.sbc.eia.bis.RmNam.utilities.Logger;

public class PublishTNPortingNotificationBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, PublishTNPortingNotification {

	private javax.ejb.SessionContext mySessionCtx = null;
	private com.sbc.eia.bis.facades.rm.transactions.PublishTNPortingNotification.PublishTNPortingNotification cachePublishTNPortingNotification =
		null;

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws java.rmi.RemoteException {
	}

	/**
	 * ejbCreate method 
	 * @exception javax.ejb.CreateException 
	 * @exception java.rmi.RemoteException
	 */
	public void ejbCreate()
		throws javax.ejb.CreateException, java.rmi.RemoteException {

		init(mySessionCtx);
	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws java.rmi.RemoteException {
	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws java.rmi.RemoteException {
	}

	/* (non-Javadoc)
	 * @see javax.ejb.EJBLocalObject#remove()
	 */
	public void remove() throws EJBException, RemoveException {
	}

	/* (non-Javadoc)
	 * @see javax.ejb.EJBLocalObject#getEJBLocalHome()
	 */
	public EJBLocalHome getEJBLocalHome() throws EJBException {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.ejb.EJBLocalObject#isIdentical(javax.ejb.EJBLocalObject)
	 */
	public boolean isIdentical(EJBLocalObject a) throws EJBException {

		if (a == this)
			return true;

		return false;
	}

	/* (non-Javadoc)
	 * @see javax.ejb.EJBLocalObject#getPrimaryKey()
	 */
	public Object getPrimaryKey() throws EJBException {
		return null;
	}

	/**
	 * Method getSessionContext
	 * @return the sessioncontext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx)
		throws java.rmi.RemoteException {
		mySessionCtx = ctx;
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.facades.rm.ejb.PublishTNPortingNotification.PublishTNPortingNotification#publishTNPortingNotification(java.lang.String, com.sbc.eia.bis.RmNam.utilities.Logger)
	 */
	public void publishTNPortingNotification(String message, Logger aLogger)
		throws Exception {

		myLogger = aLogger;

		String methodName =
			"RM Local EJB PublishTNPortingNotification:publishTNPortingNotification";
		log(LOG_DEBUG_LEVEL_1, ">" + methodName);

		try {
			if (cachePublishTNPortingNotification == null)
				cachePublishTNPortingNotification =
					new com
						.sbc
						.eia
						.bis
						.facades
						.rm
						.transactions
						.PublishTNPortingNotification
						.PublishTNPortingNotification(getPROPERTIES());

			cachePublishTNPortingNotification.execute(null, message, aLogger);

		} finally {
			log(LOG_DEBUG_LEVEL_1, "<" + methodName);
		}

	}
}
