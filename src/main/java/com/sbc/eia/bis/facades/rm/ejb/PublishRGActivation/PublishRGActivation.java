// $Id: PublishRGActivation.java,v 1.4 2005/07/14 20:14:25 ck2932 Exp $
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

package com.sbc.eia.bis.facades.rm.ejb.PublishRGActivation;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.types.Time;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;

/**
 * @author: va6483
 * @creation date:03/30/05
 * This is an Enterprise Java Bean Local Interface
 */
public interface PublishRGActivation
	extends javax.ejb.EJBLocalObject {
		

       
	/**
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDSLAM
	 * @param aRG
	 * @param aActivationTime
	 * @param aOrderAction
	 * @param aProperties
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
	public PublishRGActivationReturn publishRGActivation(
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
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
