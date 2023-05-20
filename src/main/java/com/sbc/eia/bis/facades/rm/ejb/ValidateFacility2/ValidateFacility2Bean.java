//$Id: ValidateFacility2Bean.java,v 1.2 2008/01/30 00:25:12 dn6370 Exp $
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
//# 12/21/2007  Hongmei Parkin	     Creation
//# 01/23/2008	Deepti Nayar		Modified for LS7


package com.sbc.eia.bis.facades.rm.ejb.ValidateFacility2;

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
import com.sbc.eia.idl.rm.ValidateFacility2Return;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;

public class ValidateFacility2Bean
extends com.sbc.eia.bis.RmNam.utilities.TranBase
implements SessionBean, ValidateFacility2 {

private javax.ejb.SessionContext mySessionCtx = null;
private com.sbc.eia.bis.facades.rm.transactions.ValidateFacility2.ValidateFacility2 cacheValidateFacility2 = null;

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

public ValidateFacility2Return validateFacility2(
		BisContext aContext, 
		Location aServiceLocation, 
		StringOpt aRelatedCircuitID, 
		StringOpt aWorkingTelephoneNumber, 
		ShortOpt aMaxPairsToAnalyze, 
		StringOpt aSOACServiceOrderNumber, 
		StringOpt aSOACServiceOrderCorrectionSuffix, 
		EiaDateOpt aUverseOrderDueDate, 
		ObjectType[] aNtis, 
		StringOpt aOrderActionType, 
		StringOpt aSubActionType, 
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
        "RM ValidateFacility2 - ValidateFacility2");
        ValidateFacility2Return retVal = null;

    try {

        if (cacheValidateFacility2 == null)
            cacheValidateFacility2 =
                new com
                    .sbc
                    .eia
                    .bis
                    .facades
                    .rm
                    .transactions
                    .ValidateFacility2
                    .ValidateFacility2(getPROPERTIES());
        retVal =
            cacheValidateFacility2.execute(
                aContext,
					aServiceLocation, 
					aRelatedCircuitID, 
					aWorkingTelephoneNumber, 
					aMaxPairsToAnalyze, 
					aSOACServiceOrderNumber, 
					aSOACServiceOrderCorrectionSuffix, 
					aUverseOrderDueDate, 
					aNtis, 
					aOrderActionType, 
					aSubActionType, 
					aProperties,
                aLogger);

    } finally {
        log(
            LOG_DEBUG_LEVEL_1,
            "RM ValidateFacility2 - ValidateFacility2");
    }
    return retVal;
}
}