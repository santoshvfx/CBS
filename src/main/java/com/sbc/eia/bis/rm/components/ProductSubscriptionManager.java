//$Id: ProductSubscriptionManager.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 6/02/2006	 Michael Khalili		Creation
package com.sbc.eia.bis.rm.components;
import gnu.regexp.REException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import java.util.Properties;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductSubscriptionManager {

	private ArrayList pSubsList;
	private ProductSubscription[] pSubs;
	private Utility utility;
	
	               
	
	/**
	 * @param aUtility
	 * @param aPSubs
	 */
	public ProductSubscriptionManager(Utility aUtility,ProductSubscription[] aPSubs)
	{
		String methodName = "ProductSubscriptionManager.ProductSubscriptionManager()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1,">" + methodName);

        setUtility(aUtility);
	    setProductSubscriptions(aPSubs);
	    setProductSubscriptionsList(aPSubs);       
	   
	    aUtility.log(LogEventId.DEBUG_LEVEL_1,"<" + methodName);
	}
               
	
	/**
	 * @param bandWidthFlag
	 * @return
	 * @throws FileNotFoundException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws IOException
	 * @throws REException
	 */
	public ArrayList getComponents(boolean bandWidthFlag) 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
    {    
        ArrayList aList = null;

		String methodName = "ProductSubscriptionManager.getComponents()";
		utility.log(LogEventId.DEBUG_LEVEL_1,">" + methodName);

		aList = getProductSubscriptionsList();
		if (bandWidthFlag)
		{
		   int index = 0;
		   while (index < aList.size())
		   {
		      getBandWidth((ProductSubscription)aList.get(index));
		      index++;
		   }
		}
		utility.log(LogEventId.DEBUG_LEVEL_1,"<" + methodName);

		return aList;
	}


	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws IOException
	 * @throws REException
	 */
	public String getServiceBundleId()	
    throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
	{
		String sRetValue = null;
        ArrayList aList = null;
		ServiceBundleIdCalculator sbidCalculator = null;
		String methodName = "ProductSubscriptionManager.getServiceBundleId()";
		
		utility.log(LogEventId.DEBUG_LEVEL_1,">" + methodName);

		sbidCalculator = new ServiceBundleIdCalculator(utility);
		
		aList = getComponents(false);
		sRetValue = sbidCalculator.getServiceBundleId(aList);

		
		utility.log(LogEventId.DEBUG_LEVEL_1,"<" + methodName);

		return sRetValue;
	}


	/**
	 * @param pSub
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws REException
	 */
	private void getBandWidth(ProductSubscription pSub) 
    throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
	{
//		String sRetValue = null;
		ArrayList aList = null;
		BandWidthCalculator bandwidthCalculator = null;
		String methodName = "ProductSubscriptionManager.getBandWidth()";
		
		utility.log(LogEventId.DEBUG_LEVEL_1,">" + methodName);

		bandwidthCalculator = new BandWidthCalculator(utility);
		bandwidthCalculator.getBandWidth(pSub);
		
		utility.log(LogEventId.DEBUG_LEVEL_1,"<" + methodName);

//		return sRetValue;
	}

	/**
	 * @return
	 */
	public ProductSubscription[] getPSubs() {

		return pSubs;
	}

	/**
	 * @return
	 */
	private ArrayList getProductSubscriptionsList() {

		return pSubsList;
	}

	/**
	 * @return
	 */
//	public Utility getUtility() {
//		return utility;
//	}

	/**
	 * @param subscriptions
	 */
	public void setProductSubscriptions(ProductSubscription[] subscriptions) {

		pSubs = subscriptions;
	}

	/**
	 * @param pSubs
	 */
	private void setProductSubscriptionsList(ProductSubscription[] pSubs) {
		ProductSubscription aPsub;	
		ProductSubscription[] aTempPsubs;
		int index;
	  
		aPsub = null;
		aTempPsubs = null;
		index = 0;
		String methodName = "ProductSubscriptionManager.setProductSubscriptionsList()";
		
		utility.log(LogEventId.DEBUG_LEVEL_1,">" + methodName);
	  
		while (index < pSubs.length)
		{
//		   if ( (null != pSubs[index].aProductID) && (pSubs[index].aProductID.length() > 0) ) 
//		   {
			  aPsub = new ProductSubscription(pSubs[index].aProductSubscriptionID,
											  pSubs[index].aProductID,
											  pSubs[index].aProductName,
											  pSubs[index].aBillingAccountID,
											  pSubs[index].aContractID,
											  pSubs[index].aFirstInstallationDate,
											  pSubs[index].aProductSubscriptionStatus,
											  new ProductSubscription[0],
											  pSubs[index].aALocation,
											  pSubs[index].aZLocation,
											  pSubs[index].aProperties);
				  
			  if ( null == pSubsList)
			    pSubsList = new ArrayList();
			    
			  pSubsList.add(aPsub);
//		   } //if ( (null != pSubs[index].aProductID) && (pSubs[index].aProductID.length() > 0) )
 
		   aTempPsubs = pSubs[index].aProductSubscriptions;
		   setProductSubscriptionsList(aTempPsubs);
		   index++;
		}//while (index < pSubs.length)

		utility.log(LogEventId.DEBUG_LEVEL_1,"<" + methodName);

	}

	/**
	 * @param utility
	 */
	public void setUtility(Utility aUtility) {
		this.utility = aUtility;
	}


}
