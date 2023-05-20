//$Id: ValidateFacilityValidation.java,v 1.3 2007/11/13 17:08:49 rd2842 Exp $
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
//# 07/23/2007  Rene Duka             Creation.
//# 11/13/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Class      : ValidateFacilityValidation
 * Description: Validator.
 *              - used by vF.
 */
public class ValidateFacilityValidation 
{
    public BisContext aContext;
    public Location aServiceLocation;
    public StringOpt aRelatedCircuitID;
    public StringOpt aWorkingTelephoneNumber;
    public ShortOpt aMaxPairsToAnalyze;
    public StringOpt aSOACServiceOrderNumber;
    public StringOpt aSOACServiceOrderNumberSuffix;
    public EiaDateOpt aUverseOrderDueDate;
    public ObjectPropertySeqOpt aObjectProperties;
    
    public ValidateFacilityValidation(
        BisContext pContext,
        Location pServiceLocation,
        StringOpt pRelatedCircuitID,
        StringOpt pWorkingTelephoneNumber,
        ShortOpt pMaxPairsToAnalyze,
        StringOpt pSOACServiceOrderNumber,
        StringOpt pSOACServiceOrderNumberSuffix,
        EiaDateOpt pUverseOrderDueDate,
        ObjectPropertySeqOpt pObjectProperties) 
    {
        aContext = pContext;
        aServiceLocation = pServiceLocation;
        aRelatedCircuitID = pRelatedCircuitID;
        aWorkingTelephoneNumber = pWorkingTelephoneNumber;
        aMaxPairsToAnalyze = pMaxPairsToAnalyze;
        aSOACServiceOrderNumber = pSOACServiceOrderNumber;
        aSOACServiceOrderNumberSuffix = pSOACServiceOrderNumberSuffix;
        aUverseOrderDueDate = pUverseOrderDueDate;
        aObjectProperties = pObjectProperties;
    }
}
