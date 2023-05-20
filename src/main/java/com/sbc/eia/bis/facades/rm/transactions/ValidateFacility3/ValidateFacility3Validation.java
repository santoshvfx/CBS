//$Id
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
//# Date         | Author              | Notes
//# --------------------------------------------------------------------
//# 02/03/2009     Lira Galsim           Creation.

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility3;


import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Class      : ValidateFacility3Validation
 * Description: Validator.
 *              - used by vF3.
 */
public class ValidateFacility3Validation
{
	public BisContext aContext;
    public Location aServiceLocation;
    public StringOpt aRelatedCircuitID;
    public StringOpt aWorkingTelephoneNumber;
    public ShortOpt aMaxPairsToAnalyze;
    public StringOpt aSOACServiceOrderNumber;
    public EiaDateOpt aOrderDueDate;
    public OrderAction2Opt aOrderAction;
    public ObjectPropertySeqOpt aObjectProperties;

    
    public ValidateFacility3Validation (
        BisContext pContext,
        Location pServiceLocation,
        StringOpt pRelatedCircuitID,
        StringOpt pWorkingTelephoneNumber,
        ShortOpt pMaxPairsToAnalyze,
        StringOpt pSOACServiceOrderNumber,
        EiaDateOpt pOrderDueDate,
        OrderAction2Opt pOrderAction,
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
        aOrderDueDate = pOrderDueDate;
        aOrderAction = pOrderAction;
        aObjectProperties = pObjectProperties;
   }
}