//# $Id: ModifyInventoryBean.java,v 1.1 2007/11/09 20:30:31 cy4727 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#      RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies. Copying or reproduction without prior written 
//#      approval is prohibited.
//#
//#      © AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 10/08/2007	   Changchuan Yin			  Creation

package com.sbc.eia.bis.facades.rm.ejb.ModifyInventory;
import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectKey;

public class ModifyInventoryBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements SessionBean, ModifyInventory {

	private javax.ejb.SessionContext mySessionCtx = null;
	



	public void ejbActivate() throws java.rmi.RemoteException {
	}


	public void ejbCreate()
		throws javax.ejb.CreateException, java.rmi.RemoteException {

		init(mySessionCtx);
		 
	}

	

	public void ejbPassivate() throws java.rmi.RemoteException {
	}



	public void ejbRemove() throws java.rmi.RemoteException {
	}

	

	public void remove() throws EJBException, RemoveException {
	}


	public EJBLocalHome getEJBLocalHome() throws EJBException {

		return null;
	}

	

	public boolean isIdentical(EJBLocalObject a) throws EJBException {

		if (a == this)
			return true;

		return false;
	}

	

	public Object getPrimaryKey() throws EJBException {

		return null;
	}

	

	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	
	public void setSessionContext(javax.ejb.SessionContext ctx)
		throws java.rmi.RemoteException {
		mySessionCtx = ctx;
	}
	
	public void modifyInventory(
			BisContext aContext,
			String msgTxt, Logger aLogger)
			throws
				MultipleExceptions,
				java.rmi.RemoteException, InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
	{
		String myMethodName =
	          "ModifyInventoryBean::modifyInventory()";
	    log(LOG_DEBUG_LEVEL_1, myMethodName);
	      
		callerContext = aContext ;
		myLogger = aLogger;	
		
		validateBisContext(
				aContext,
				getEnv("BIS_NAME"),
				ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
				"Required field is null");
		
	     try {
	     	    com
				.sbc
				.eia
				.bis
				.facades
				.rm
				.transactions
				.ModifyInventory.ModifyInventory cacheModifyInventory = new com
					.sbc
					.eia
					.bis
					.facades
					.rm
					.transactions
					.ModifyInventory.ModifyInventory(getPROPERTIES(),(Utility) this, (com.sbc.bccs.utilities.Logger) this);
				
					
					try {
						cacheModifyInventory.execute(
							 callerContext,
							 msgTxt);
					} catch (Throwable e) {
						  e.printStackTrace();
					}
					

			} 
	        finally 
			{
				log(LOG_DEBUG_LEVEL_1, myMethodName);
			}
	}	 
}
