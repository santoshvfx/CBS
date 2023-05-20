//# $Id: ModifyInventory.java,v 1.1 2007/11/09 20:30:31 cy4727 Exp $
//###############################################################################
//#
//#       Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date         | Author                 | Notes
//# --------------------------------------------------------------------------
//# 11/09/2007          Changchuan Yin      Created for LS6

package com.sbc.eia.bis.facades.rm.ejb.ModifyInventory;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public interface ModifyInventory extends javax.ejb.EJBLocalObject {
	
	public void modifyInventory(
			BisContext aContext,
			String msgTxt, Logger aLogger)
			throws
				MultipleExceptions,
				java.rmi.RemoteException, InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound;
			
}
