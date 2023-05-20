//$Id: ValidateFacility2Validation.java,v 1.2 2008/02/18 21:54:27 dn6370 Exp $
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
//# 01/18/2008  Deepti Nayar             Creation.
//# 02/18/2008	Deepti Nayar			Modified for LS7.

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility2;


import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Class      : ValidateFacility2Validation
 * Description: Validator.
 *              - used by vF2.
 */
public class ValidateFacility2Validation

{
	public BisContext aContext;
    public Location aServiceLocation;
    public StringOpt aRelatedCircuitID;
    public StringOpt aWorkingTelephoneNumber;
    public ShortOpt aMaxPairsToAnalyze;
    public StringOpt aSOACServiceOrderNumber;
    public StringOpt aSOACServiceOrderNumberSuffix;
    public EiaDateOpt aUverseOrderDueDate;
    public StringOpt aOrderActionType;
    public StringOpt aSubActionType;
    public ObjectPropertySeqOpt aObjectProperties;

    
    public ValidateFacility2Validation (
        BisContext pContext,
        Location pServiceLocation,
        StringOpt pRelatedCircuitID,
        StringOpt pWorkingTelephoneNumber,
        ShortOpt pMaxPairsToAnalyze,
        StringOpt pSOACServiceOrderNumber,
        StringOpt pSOACServiceOrderNumberSuffix,
        EiaDateOpt pUverseOrderDueDate,
		StringOpt pOrderActionType,
		StringOpt pSubActionType,
        ObjectPropertySeqOpt pObjectProperties)
    	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound
    {
        aContext = pContext;
        aServiceLocation = pServiceLocation;
        aRelatedCircuitID = pRelatedCircuitID;
        aWorkingTelephoneNumber = pWorkingTelephoneNumber;
        aMaxPairsToAnalyze = pMaxPairsToAnalyze;
        aSOACServiceOrderNumber = pSOACServiceOrderNumber;
        aSOACServiceOrderNumberSuffix = pSOACServiceOrderNumberSuffix;
        aUverseOrderDueDate = pUverseOrderDueDate;
        aOrderActionType = pOrderActionType;
        aSubActionType = pSubActionType;
        aObjectProperties = pObjectProperties;
   }
}


