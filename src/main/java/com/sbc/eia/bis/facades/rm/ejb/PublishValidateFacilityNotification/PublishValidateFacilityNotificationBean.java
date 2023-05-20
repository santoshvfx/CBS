//$Id: PublishValidateFacilityNotificationBean.java,v 1.4 2009/01/21 19:16:39 hw7243 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 07/16/2007  Rene Duka             Creation.
//# 11/06/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.ejb.PublishValidateFacilityNotification;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;

import com.sbc.eia.bis.RmNam.utilities.Logger;

/**
 * Class      : PublishValidateFacilityNotificationBean
 * Description: PublishValidateFacilityNotificationBean.
 */
public class PublishValidateFacilityNotificationBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, 
			   PublishValidateFacilityNotification 
{
    private javax.ejb.SessionContext mySessionCtx = null;
    private com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification.PublishValidateFacilityNotification cachePublishValidateFacilityNotification = null;

	/**
	 * ejbActivate method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbActivate() 
		throws java.rmi.RemoteException 
    {
	}
	
	/**
	 * ejbCreate method comment
	 * @exception javax.ejb.CreateException The exception description.
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbCreate()
	    throws javax.ejb.CreateException, java.rmi.RemoteException 
    {
	    init(mySessionCtx);
	}
	
	/**
	 * ejbPassivate method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbPassivate() 
		throws java.rmi.RemoteException 
    {
	}
	
	/**
	 * ejbRemove method comment
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void ejbRemove() 
		throws java.rmi.RemoteException 
    {
	}
	
	/**
	 * @see javax.ejb.EJBLocalObject#remove()
	 * @exception javax.ejb.EJBException The exception description.
	 * @exception java.rmi.RemoveException The exception description.
	 */
	public void remove() 
		throws EJBException, 
			   RemoveException 
   {
   }
	
	/**
	 * @see javax.ejb.EJBLocalObject#getEJBLocalHome()
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public EJBLocalHome getEJBLocalHome() 
		throws EJBException 
    {
	    return null;
	}
	
	/**
	 * @see javax.ejb.EJBLocalObject#isIdentical(EJBLocalObject)
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public boolean isIdentical(EJBLocalObject a) 
		throws EJBException 
    {
	    if (a == this)
	        return true;
	
	    return false;
	}
	
	/**
	 * @see javax.ejb.EJBLocalObject#getPrimaryKey()
	 * @exception javax.ejb.EJBException The exception description.
	 */
	public Object getPrimaryKey() 
		throws EJBException 
    {
	    return null;
	}
	
	/**
	 * getSessionContext method comment
	 * @return javax.ejb.SessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() 
    {
	    return mySessionCtx;
	}
	
	/**
	 * setSessionContext method comment
	 * @param ctx javax.ejb.SessionContext
	 * @exception java.rmi.RemoteException The exception description.
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx)
	    throws java.rmi.RemoteException 
    {
	    mySessionCtx = ctx;
	}
	
	public void publishValidateFacilityNotification(
		javax.jms.Message aMessage,
	    Logger aLogger)
	    throws
	        Exception 
    {
	    myLogger = aLogger;
	
		String aMethodName = "RM PublishValidateFacilityNotification: publishValidateFacilityNotification - local EJB";
	  
		log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);
	
	    try 
        {
	        if (cachePublishValidateFacilityNotification == null)
	            cachePublishValidateFacilityNotification =
	                new com
	                    .sbc
	                    .eia
	                    .bis
	                    .facades
	                    .rm
	                    .transactions
	                    .PublishValidateFacilityNotification
	                    .PublishValidateFacilityNotification(getPROPERTIES());
	
	        cachePublishValidateFacilityNotification.execute(null, aMessage, aLogger);
	    } 
	    finally 
        {
			log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);        
	    }
	}
}