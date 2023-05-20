//$Id: PublishRGActivationValidation.java,v 1.3 2007/10/18 21:52:18 ra0331 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date      | Author               | Notes
//# ----------------------------------------------------------------------------
//# 10/02/2006  mp9129					Created Validator for CFA.
//

package com.sbc.eia.bis.facades.rm.transactions.PublishRGActivation;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.Time;


public class PublishRGActivationValidation {
	public BisContext aContext;
	public StringOpt aCustomerTransportId;
	public CompositeObjectKey aBillingAccountNumber;
	public DSLAMTransportOpt aDSLAM;
	public ResidentialGateway aRG;
	public Time aActivationTime;
	public OrderAction aOrderAction;
	
	
	public PublishRGActivationValidation(
		BisContext pContext,
		StringOpt pCustomerTransportId,
		CompositeObjectKey pBillingAccountNumber,
		DSLAMTransportOpt pDSLAM,
		ResidentialGateway pRG,
		Time pActivationTime,
		OrderAction pOrderAction) {
		
        aContext = pContext;
		aCustomerTransportId = pCustomerTransportId;
    	aBillingAccountNumber = pBillingAccountNumber;
    	aDSLAM = pDSLAM;
    	aRG = pRG;
    	aActivationTime = pActivationTime;
        aOrderAction = pOrderAction;      	
        }
}
