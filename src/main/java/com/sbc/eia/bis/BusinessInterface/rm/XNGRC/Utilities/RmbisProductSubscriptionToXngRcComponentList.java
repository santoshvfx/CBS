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

package com.sbc.eia.bis.BusinessInterface.rm.XNGRC.Utilities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionProperty;


/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RmbisProductSubscriptionToXngRcComponentList {

	protected final String VALID_COMPONENTS_ENVIRONMENT_VARIABLE = "PADR_XNG_RC_COMPONENT_LIST",
						   VALID_ACTION_CODES_ENVIRONMENT_VARIABLE="PADR_XNG_RC_ACTION_CODE_LIST",
						   VALID_COMPONENTS_FOR_SERIAL_NUMBERCODES_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_SERIAL_NUMBER",
						   VALID_COMPONENTS_FOR_MODEL_NUMBER_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_MODEL_NUMBER",
						   VALID_COMPONENTS_FOR_VENDOR_ID_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_VENDOR_ID",
						   VALID_COMPONENTS_FOR_DEVICE_ID_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_DEVICE_ID",
						   VALID_COMPONENTS_FOR_HSIA_TYPE_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_HSIA_TYPE",
						   VALID_COMPONENTS_FOR_SPEED_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_SPEED",
						   VALID_COMPONENTS_FOR_IP_ADDRESS_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_IP_ADDRESS_TYPE",
						   VALID_COMPONENTS_FOR_STB_TYPE_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_STB_TYPE",
						   VALID_COMPONENTS_FOR_COMPONENT_QUALIFIER_ENVIRONMENT_VARIABLE="PADR_XNG_RC_COMPONENT_LIST_FOR_COMPONENT_QUALIFIER";
	
	
	private Hashtable aProperties = null;
	ProductSubscription[] aProductSubscriptions = null;
	
	private ArrayList theMasterPsubsList = null,
					  validComponentList = null,
					  validActionCodesList = null,
					  validComponetListForSerialNumber = null,
					  validComponetListForModelNumber = null,
					  validComponetListForVendorId = null,
					  validComponetListForDeviceId = null,
					  validComponetListForHsiaType = null,
					  validComponetListForSpeed = null,
					  validComponetListForIPAddress = null,
					  validComponetListForSTBType = null,
					  validComponetListForQualifier = null;
	
	/**
	 * @param P
	 * @param pSubs
	 */
	public RmbisProductSubscriptionToXngRcComponentList(Hashtable P,ProductSubscription[] pSubs)
	{
		setProperties(P);
		setProductSubscriptions(pSubs);
		this.theMasterPsubsList = new ArrayList();
		this.breakProductSubscriptions(pSubs);
		this.initComponentList();
		this.initActionCodeList();
		this.initComponentListForModelNumber();
		this.initComponentListForSerialNumber();
		this.initComponentListForVendorId();
		this.initComponentListForDeviceId();
		this.initComponentListForQualifier();
		this.initComponentListForHsiaType();
		this.initComponentListForSpeed();
		this.initComponentListForIPAddress();
	}
	public RmbisProductSubscriptionToXngRcComponentList(Hashtable P)
	{
		setProperties(P);
		this.initComponentList();
		this.initActionCodeList();
		this.initComponentListForModelNumber();
		this.initComponentListForSerialNumber();
		this.initComponentListForVendorId();
		this.initComponentListForDeviceId();
		this.initComponentListForQualifier();
		this.initComponentListForHsiaType();
		this.initComponentListForSpeed();
		this.initComponentListForIPAddress();
	}

	/**
	 * 
	 */
	private void initComponentListForModelNumber()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForModelNumber)
	  {
		validComponetListForModelNumber = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_MODEL_NUMBER_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForModelNumber.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForSerialNumber()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForSerialNumber)
	  {
		validComponetListForSerialNumber = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_SERIAL_NUMBERCODES_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForSerialNumber.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForVendorId()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForVendorId)
	  {
		validComponetListForVendorId = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_VENDOR_ID_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForVendorId.add(tag);
	  }
	}


	/**
	 * 
	 */
	private void initComponentListForDeviceId()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForDeviceId)
	  {
		validComponetListForDeviceId = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_DEVICE_ID_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForDeviceId.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForHsiaType()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForHsiaType)
	  {
		validComponetListForHsiaType = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_HSIA_TYPE_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForHsiaType.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForSpeed()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForSpeed)
	  {
		validComponetListForSpeed = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_SPEED_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForSpeed.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForIPAddress()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForIPAddress)
	  {
		validComponetListForIPAddress = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_IP_ADDRESS_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForIPAddress.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForSTBType()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForSTBType)
	  {
		validComponetListForSTBType = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_STB_TYPE_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForSTBType.add(tag);
	  }
	}

	/**
	 * 
	 */
	private void initComponentListForQualifier()
	{
	  StringTokenizer strTok = null;
	  String          paramList  = null;
	  if ( null == validComponetListForQualifier)
	  {
		validComponetListForQualifier = new ArrayList();
	  }
	  
	  paramList = (String)getProperties().get(VALID_COMPONENTS_FOR_COMPONENT_QUALIFIER_ENVIRONMENT_VARIABLE);
	  strTok = new StringTokenizer( paramList, "|" );
	  while ( strTok.hasMoreElements() )
	  {
		  String tag = strTok.nextToken() ;
		  validComponetListForQualifier.add(tag);
	  }
	}


	/**
	 * 
	 */
	private void initComponentList()
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
	 * 
	 */
	private void initActionCodeList()
	{
		StringTokenizer strTok = null;
		String          paramList  = null;
		if ( null == validActionCodesList)
		{
			validActionCodesList = new ArrayList();
		}
	  
		paramList = (String)getProperties().get(VALID_ACTION_CODES_ENVIRONMENT_VARIABLE);
		strTok = new StringTokenizer( paramList, "|" );
		while ( strTok.hasMoreElements() )
		{
			String tag = strTok.nextToken() ;
			validActionCodesList.add(tag);
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForSerialNumber(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForSerialNumber.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForModelNumber(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForModelNumber.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForVendorId(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForVendorId.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForDeviceId(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForDeviceId.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForSTBType(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForSTBType.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForQualifier(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForQualifier.contains(str);
	  
	  return bRetVal;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForHsiaType(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForHsiaType.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForSpeed(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForSpeed.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isComponentIdValidForIPAddress(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validComponetListForIPAddress.contains(str);
	  
	  return bRetVal;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean isActionCodeValid(String str)
	{
	  boolean bRetVal = false;
	  
	  bRetVal = validActionCodesList.contains(str);
	  
	  return bRetVal;
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
	  
	  int index = 0,
		  propertiesIndex = 0;
	  ProductSubscription pSub = null;
	  com.sbc.eia.idl.types.ObjectProperty[] properties = null;
	  com.sbc.eia.idl.types.ObjectProperty aProperty = null;
	  String ActionIdValue = null;	  

	  retVal = new ArrayList();

	  while (index < theMasterPsubsList.size())
	  {
		
		pSub = (ProductSubscription)theMasterPsubsList.get(index);
		propertiesIndex = 0;
		ActionIdValue = null;
		properties = pSub.aProperties.theValue();
		while (propertiesIndex < properties.length)
		{
			aProperty = properties[propertiesIndex];
			if (aProperty.aTag.equalsIgnoreCase(ProductSubscriptionProperty.ACTIONID))
			{
				ActionIdValue = aProperty.aValue;
				break;
			}
			propertiesIndex++;
		} // while (propertiesIndex < properties.length)
	    
		if ( (isComponentValid(pSub.aProductID)) && (isActionCodeValid(ActionIdValue)) )
		{
		  retVal.add(pSub);
		} // if ( (isComponentValid(pSub.aProductID)) && (isActionCodeValid(ActionIdValue)) )
		index++;
	  } // while (index < theMasterPsubsList.size())
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
	  
//	  try
//	  {
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
 
		   theMasterPsubsList.add(aPsub);
		   aTempPsubs = pSubs[index].aProductSubscriptions;
		   breakProductSubscriptions(aTempPsubs);
		   index++;
		}//while (index < pSubs.length)
//	  } catch (Exception e)
//		{
//		  System.out.println(e.toString());
//		}
	}

	public static void main(String[] args) {
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

/**
 * @return
 */
public ArrayList getTheMasterPsubsList() {
	return theMasterPsubsList;
}

/**
 * @param list
 */
public void setTheMasterPsubsList(ArrayList list) {
	theMasterPsubsList = list;
}

}
