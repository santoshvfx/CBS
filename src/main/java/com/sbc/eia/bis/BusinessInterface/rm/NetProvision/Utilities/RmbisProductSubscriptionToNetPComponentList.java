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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 01/16/2006  Michael Khalili      Creation.
package com.sbc.eia.bis.BusinessInterface.rm.NetProvision.Utilities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;

/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RmbisProductSubscriptionToNetPComponentList {

	protected final String VALID_COMPONENTS_ENVIRONMENT_VARIABLE = "PADR_NETP_VALID_COMPONENT_LIST";
	private ArrayList masterProductSubList = null,
					  validComponentList = null;
	private Hashtable aProperties = null;
	ProductSubscription[] aProductSubscriptions = null;

	public RmbisProductSubscriptionToNetPComponentList(Hashtable P,ProductSubscription[] pSubs)
	{
		setProperties(P);
		setProductSubscriptions(pSubs);
		masterProductSubList = new ArrayList();
		breakProductSubscriptions(pSubs);
		setComponentList();
	}

	public RmbisProductSubscriptionToNetPComponentList(Hashtable P)
	{
		setProperties(P);
		setComponentList();
	}

	/**
	 * 
	 */

	private void setComponentList()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponentList)
	  {
		validComponentList = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponentList.add(tag);
	  }
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentValid(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponentList.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @return
	 */
	public ArrayList ProductSubscription2ComponentListMap()
	{
	  ArrayList retVal = null;
	  
	  int index = 0;
	  ProductSubscription pSub = null;

	  retVal = new ArrayList();
	  while (index < masterProductSubList.size())
	  {
		pSub = (ProductSubscription)masterProductSubList.get(index);
	    
		if ( isComponentValid(pSub.aProductID) )
		{
		  retVal.add(pSub);
		} // if ( isComponentValid(pSub.aProductID) )
		index++;
	  } // while (index < masterProductSubList.size())
	  
	  return retVal;
	}
	
	/**
	 * @return
	 */
	public ArrayList ProductSubscription2ComponentListMap(ArrayList masterList)
	{
	  ArrayList retVal = null;
	  
	  int index = 0;
	  ProductSubscription pSub = null;

	  retVal = new ArrayList();
	  while (index < masterList.size())
	  {
		pSub = (ProductSubscription)masterList.get(index);
	    
		if ( isComponentValid(pSub.aProductID) )
		{
		  retVal.add(pSub);
		} // if ( isComponentValid(pSub.aProductID) )
		index++;
	  } // while (index < masterList.size())
	  
	  return retVal;
	}

	/**
	 * @return
	 */
	private void breakProductSubscriptions(ProductSubscription[] pSubs)
	{
	  
	  ProductSubscription aPsub;	
	  ProductSubscription[] aTempPsubs;
	  int index;
	  
	  aPsub = null;
	  aTempPsubs = null;
	  index = 0;
	  
	  try
	  {
	  while (index < pSubs.length)
	  {
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
 
		   masterProductSubList.add(aPsub);
		   aTempPsubs = pSubs[index].aProductSubscriptions;
		   breakProductSubscriptions(aTempPsubs);
		   index++;
		}//while (index < pSubs.length)
	  } catch (Exception e)
		{
		  System.out.println(e.toString());
		}
	}
	
	/**
	 * @return
	 */
	public Hashtable getProperties() {
		return aProperties;
	}

	/**
	 * @param hashtable
	 */
	public void setProperties(Hashtable hashtable) {
		aProperties = hashtable;
	}

	/**
	 * @return
	 */
	public ProductSubscription[] getProductSubscriptions() {
		return aProductSubscriptions;
	}

	/**
	 * @param subscriptions
	 */
	public void setProductSubscriptions(ProductSubscription[] subscriptions) {
		aProductSubscriptions = subscriptions;
	}


}
