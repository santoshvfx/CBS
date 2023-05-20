// $Id: PublishAutoDiscoveryResults.java,v 1.6 2006/05/02 21:06:05 ml2917 Exp $
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
//# 11/08/2005  jp2854                 IDL bundle 33 changes.
//# 04/02/2006  Mark Liljequist        Changes for Lightspeed 3 IDL bundle 37.

package com.sbc.eia.bis.facades.rm.ejb.PublishAutoDiscoveryResults;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;

public interface PublishAutoDiscoveryResults extends javax.ejb.EJBLocalObject {

	/**
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aServiceAddress
	 * @param aProductSubscriptions
	 * @param aTelephoneNumber
	 * @param aAssignedProductId
	 * @param aOrderAction
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

	public PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
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
			DataNotFound,
			java.rmi.RemoteException;
}
