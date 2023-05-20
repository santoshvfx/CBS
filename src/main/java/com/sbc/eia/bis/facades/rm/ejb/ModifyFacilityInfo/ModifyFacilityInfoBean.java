//$Id: ModifyFacilityInfoBean.java,v 1.6 2008/02/07 22:12:58 ds4987 Exp $
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
//# 09/28/2007  Doris Sunga           Creation.
//# 11/15/2007  Doris Sunga			  LS6: Add calling of setUtility() to get latest utility value

package com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;

import java.util.Hashtable;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
//import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;


public class ModifyFacilityInfoBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean,
			   ModifyFacilityInfo {

private javax.ejb.SessionContext mySessionCtx = null;
private com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo.ModifyFacilityInfo cacheModifyFacilityInfo = null;

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

	public void modifyFacilityInfo(Utility utility, 
	        Hashtable properties, 
	        SOACServiceOrderResponseParser parsedFCIF,
	        String correlationId,
	        String aApplicationID,
	        com.sbc.bccs.utilities.Logger myLogger)
	    throws
	        Exception {

		String aMethodName = "RM ModifyFacilityInfo: ModifyFacilityInfo - local EJB";

		utility.log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

	    try {
	        if (cacheModifyFacilityInfo == null)
	            cacheModifyFacilityInfo =
	                new com
	                    .sbc
	                    .eia
	                    .bis
	                    .facades
	                    .rm
	                    .transactions
	                    .ModifyFacilityInfo
	                    .ModifyFacilityInfo(utility, properties);
	        else
	            cacheModifyFacilityInfo.setUtility(utility);
	        
         cacheModifyFacilityInfo.modifyFacilityInfoRequest(parsedFCIF, correlationId, aApplicationID, myLogger);	            
	    }
	    finally {
			utility.log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
	    }
	}
}