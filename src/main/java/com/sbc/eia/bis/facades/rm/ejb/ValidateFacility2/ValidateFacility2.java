//$Id: ValidateFacility2.java,v 1.2 2008/01/30 00:25:03 dn6370 Exp $
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

public interface ValidateFacility2 extends javax.ejb.EJBLocalObject {

	/**
	 * @param aContext
	 * @param aServiceLocation
	 * @param aRelatedCircuitID
	 * @param aWorkingTelephoneNumber
	 * @param aMaxPairsToAnalyze
     * @param aSOACServiceOrderNumber
     * @param aSOACServiceOrderCorrectionSuffix
     * @param aNtis
     * @param aOrderActionType
     * @param aSubActionType
	 * @param aProperties
	 * @param aLogger
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws java.rmi.RemoteException
	 */

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
			DataNotFound,
			java.rmi.RemoteException;
}