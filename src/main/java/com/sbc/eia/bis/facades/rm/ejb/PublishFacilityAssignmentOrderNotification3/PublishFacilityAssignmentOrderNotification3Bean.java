//$Id: PublishFacilityAssignmentOrderNotification3Bean.java,v 1.2 2009/02/03 23:36:00 ch1463 Exp $
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
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 09/28/2007  Doris Sunga           Creation.
//# 10/31/2007  Doris Sunga			  LS7: Update PFAO2

package com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification3;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;

public class PublishFacilityAssignmentOrderNotification3Bean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean,
			   PublishFacilityAssignmentOrderNotification3 {

private javax.ejb.SessionContext mySessionCtx = null;
private com.sbc.eia.bis.facades.rm.transactions.PublishFacilityAssignmentOrderNotification3.PublishFacilityAssignmentOrderNotification3 cachePublishFacilityAssignmentOrderNotification3 = null;

	/**
	 * ejbActivate method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbActivate()
		throws java.rmi.RemoteException {
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
	public void ejbPassivate()
		throws java.rmi.RemoteException {
	}

	/**
	 * ejbRemove method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbRemove()
		throws java.rmi.RemoteException {
	}

	/**
	 * @see javax.ejb.EJBLocalObject#remove()
	 * @exception javax.ejb.EJBException The exception description.
	 * @exception java.rmi.RemoveException The exception description.
	 */
	public void remove()
		throws EJBException,
			   RemoveException {
	}

	/**
	 * @see javax.ejb.EJBLocalObject#getEJBLocalHome()
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public EJBLocalHome getEJBLocalHome()
		throws EJBException {

	    return null;
	}

	/**
	 * @see javax.ejb.EJBLocalObject#isIdentical(EJBLocalObject)
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public boolean isIdentical(EJBLocalObject a)
		throws EJBException {

	    if (a == this)
	        return true;

	    return false;
	}

	/**
	 * @see javax.ejb.EJBLocalObject#getPrimaryKey()
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public Object getPrimaryKey()
		throws EJBException {

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

	public void publishFacilityAssignmentOrderNotification3(Logger myLogger,SOACServiceOrderResponseParser parsedFCIF, 
	        String correlationId, String aApplicationID)
	    throws
	        Exception {

		String aMethodName = "RM PublishFacilityAssignmentOrderNotification3: publishFacilityAssignmentOrderNotification3 - local EJB";

		myLogger.log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

	    try {
	        if (cachePublishFacilityAssignmentOrderNotification3 == null)
	            cachePublishFacilityAssignmentOrderNotification3 =
	                new com
	                    .sbc
	                    .eia
	                    .bis
	                    .facades
	                    .rm
	                    .transactions
	                    .PublishFacilityAssignmentOrderNotification3
	                    .PublishFacilityAssignmentOrderNotification3(getPROPERTIES());
	        //cache_pFAO = new PublishFacilityAssignmentOrderNotification(aUtility, myProperties, myLogger);
	        cachePublishFacilityAssignmentOrderNotification3.execute(myLogger, parsedFCIF, correlationId, aApplicationID);
	    }
	    finally {
			myLogger.log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
	    }
	}

}