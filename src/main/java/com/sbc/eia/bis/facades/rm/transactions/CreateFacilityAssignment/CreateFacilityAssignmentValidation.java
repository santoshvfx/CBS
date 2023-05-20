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
//# 10/02/2006  mp9129					Created Validator for CFA.			

package com.sbc.eia.bis.facades.rm.transactions.CreateFacilityAssignment;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;


public class CreateFacilityAssignmentValidation {
	
	public BisContext aContext;
	public String aCustomerTransportId;
	public CompositeObjectKey aBillingAccountNumber;
	public Location aServiceLocation;
	public BooleanOpt aMaintenanceFlag;
	public EiaDate aDueDate;
	public OrderAction aOrderAction;
	public StringOpt aTaperCode;
	public String aNetworkType;
	public	NetworkType aNetworkTypeChoice;
    public ObjectPropertySeqOpt aObjectProperties;
	
	public CreateFacilityAssignmentValidation(
		BisContext pContext,
		String pCustomerTransportId,
        CompositeObjectKey pBillingAccountNumber,
        Location pServiceLocation,
        BooleanOpt pMaintenanceFlag,
        EiaDate pDueDate,
        OrderAction pOrderAction,         
        StringOpt pTaperCode,
        String pNetworkType,
        NetworkType pNetworkTypeChoice,
        ObjectPropertySeqOpt pObjectProperties){
        	
        	aContext = pContext;
        	aCustomerTransportId = pCustomerTransportId;
    		aBillingAccountNumber = pBillingAccountNumber;
    		aServiceLocation = pServiceLocation;
    		aMaintenanceFlag = pMaintenanceFlag;
    		aDueDate = pDueDate;
    		aOrderAction = pOrderAction;
    		aTaperCode = pTaperCode;
    		aNetworkType = pNetworkType;
    		aNetworkTypeChoice = pNetworkTypeChoice;
    		aObjectProperties = pObjectProperties;
    		
        	
        }

}
