//$Id: PublishAutoDiscoveryResultsValidation.java,v 1.0 2006/09/01 08:19:10 mb6834 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date         | Author              | Notes
//# --------------------------------------------------------------------

package com.sbc.eia.bis.facades.rm.transactions.PublishAutoDiscoveryResults;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;

public class PublishAutoDiscoveryResultsValidation {
	public BisContext aContext;
	public String aCustomerTransportId;
	public CompositeObjectKey aBillingAccountNumber;
	public AddressOpt aServiceAddress;
	public ProductSubscriptionSeqOpt aProductSubscriptions;
	public StringOpt aTelephoneNumber;
	public String aAssignedProductId;
	public OrderAction aOrderAction;
	public ObjectPropertySeqOpt aProperties;
	
	public PublishAutoDiscoveryResultsValidation(
		BisContext pContext, 
		String pCustomerTransportId, 
		CompositeObjectKey pBillingAccountNumber, 
		AddressOpt pServiceAddress, 
		ProductSubscription[] pProductSubscriptions, 
		StringOpt pTelephoneNumber, 
		String pAssignedProductId,
		OrderAction pOrderAction,
		ObjectPropertySeqOpt pProperties) {
			
			aContext = pContext;
			aCustomerTransportId = pCustomerTransportId;
			aBillingAccountNumber = pBillingAccountNumber;
			aServiceAddress = pServiceAddress;
			
			aProductSubscriptions = new ProductSubscriptionSeqOpt();
			aProductSubscriptions.theValue(pProductSubscriptions);
			
			aTelephoneNumber = pTelephoneNumber;
			aAssignedProductId = pAssignedProductId;
			
			aOrderAction = pOrderAction;
			aProperties = pProperties;
	}
}
