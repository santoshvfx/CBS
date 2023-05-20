//$Id: BandWidthCalculator.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;

import com.sbc.eia.idl.types.ExceptionData;
import gnu.regexp.*;

//import com.sbc.bccs.idl.helpers.*;

//import com.sbc.eia.idl.bis_types.*;
//import com.sbc.eia.idl.types.*;
//import com.sbc.eia.idl.exception_types.*;

import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sun.java.util.collections.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BandWidthCalculator {


	private static final String FileNamePropertyTag = "BANDWIDTH_PROPERTIES_FILE_NAME",
	                     BandWidthPropetyTag = "GRANITE_BANDWIDTH",
	                     DELIMITER_TAG = "DELIMITER",
	                     ANY_PRODUCTS_TAG = "ANY_PRODUCT_IDS",
	                     BASE_PRODUCTS_TAG = "BASE_PRODUCT_IDS",
	                     BASE_PROPERTIES_TAG = "BASE_PROPERTY_NAMES",
	                     DEFAULT_DELIMITER = "&";

	
	private String delimiter = null;
   

	private Utility utility;
	private Properties properties = null;
	private ArrayList  anyProductIds = null,
	                   baseProductIds = null,
	                   baseProperties = null;
	
	/**
	 * @param aUtility
	 * @throws InvalidData
	 * @throws SystemFailure
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public BandWidthCalculator(Utility aUtility) 
	throws InvalidData, SystemFailure, AccessDenied, BusinessViolation, NotImplemented, ObjectNotFound, DataNotFound
	{
		setUtility(aUtility);
		loadProperties();
		initDelimiter();
		initBaseProductsList();
        initAnyProductsList();
        initBasePropertiesList();		
	}

	/**
	 * @param productId
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws REException
	 */
	private boolean validateProperty(String productId,String propertyName,String propertyValue) 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound, REException
	{
		boolean bRetValue = true;
		String ruleName = null,rule = null;
		ValidationRulesManager validationRulesMgr = null;
		
		ruleName = propertyName.toUpperCase() + "_" + "RULE";
		// Check to see if we have a validation Rule defined for it? if Yes then
		// execute the Rule.
		rule = this.properties.getProperty(ruleName);
		if ( null != rule)
		{
			validationRulesMgr = new ValidationRulesManager(delimiter);
			bRetValue = validationRulesMgr.ExecuteValidationRule(rule,propertyValue);
		}
	    if ( false == bRetValue)
	    {
			String msg,aBisName;
			msg = "Can not calculate Bandwidth.Invalid input data for Component Type: " + productId + " <" + propertyName + "=" + propertyValue + ">";
			aBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");
			utility.throwException(ExceptionCode.ERR_RM_INVALID_BANDWIDTH,msg,aBisName,Severity.Recoverable);
	    }
		
		return bRetValue;
	}



	/**
	 * @param productId
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	private String getBandWidthPropertTag(String productId,String propertyName,String propertyValue)
	{ 
	   String propertyTag = null;
	   int index = 0;
	   
	   // If it is one of the ANY Product Ids Then set its propertyName and propertyValue into ANY
	   if (isItAnyProduct(productId))
	   {
		  propertyName = "ANY";
		  propertyValue = "ANY"; 
	   }
	   // if it is one of the BASE Product Ids then set its propertyValue to BASE
	   else if (isItBaseProduct(productId) && isItBaseProperty(propertyName))
	   {
		  propertyValue = "BASE"; 
	   }
	   
	   propertyTag = productId + "_" + propertyName + "_" + propertyValue + "_" + "BANDWIDTH";
	   
	   return propertyTag.toUpperCase();
	}


	/**
	 * @param bandWidthProperyTag
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	private int findBandWidth(String bandWidthProperyTag,String propertyName,String propertyValue) 
	{ 
	   int iBandWidth = 0;
	   String sBandWidth = null;
	   
	   sBandWidth = properties.getProperty(bandWidthProperyTag);
	   if ( null != sBandWidth)
	   {   
		   iBandWidth = Integer.parseInt(sBandWidth);
		   if (this.isItBaseProperty(propertyName))
	       {
	          iBandWidth *= Integer.parseInt(propertyValue);
	       }
	   }
	   return iBandWidth;
	}


	/**
	 * @throws InvalidData
	 * @throws SystemFailure
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void loadProperties() 
	throws InvalidData, SystemFailure, AccessDenied, BusinessViolation, NotImplemented, ObjectNotFound, DataNotFound 
	{
		 String aFileName = null,
				aBisName = null;
		
		 if ( null == properties)
		 {
		    aFileName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv(FileNamePropertyTag);
		    aBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");

		    try
		    {
			    properties = PropertiesLoader.loadBandwidthProperties(aFileName,aBisName,utility);
		    }
		    catch (FileNotFoundException e)
		    { // This is the case of SystemFailure exception
				String msg = null;
				msg = "FileNotFoundException on BandWidthCalculator.loadProperties() method: " + e.getMessage() + ">";
				utility.throwException(ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,msg,aBisName,Severity.Recoverable);
		    }
		    catch (IOException e)
		    { // This is the case of SystemFailure exception
				String msg = null;
				msg = "IOException on BandWidthCalculator.loadProperties() method: " + e.getMessage() + ">";
				utility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,msg,aBisName,Severity.Recoverable);
		    } 

		 }
	}

	/**
	 * @param utility
	 */
	private void setUtility(Utility utility) {
		this.utility = utility;
	   
	}

	/**
	 * @return
	 */
//	private Utility getUtility() {
//		return utility;
//	}

	/**
	 * @param aProductId
	 * @return
	 * @throws REException
	 */
	private boolean validateProductId(String aProductId) 
	throws REException
	{
	   
		boolean bRetValue = false;
		String rule = null;
		ValidationRulesManager validationRulesMgr = null;
		
		rule = this.properties.getProperty("PRODUCT_IDS_RULE");
		if ( null != rule)
		{
			validationRulesMgr = new ValidationRulesManager(delimiter);
			bRetValue = validationRulesMgr.ExecuteValidationRule(rule,aProductId);
		}
	    
		return bRetValue;
	   
	}


	/**
	 * @param pSub
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws REException
	 */
	public void getBandWidth(ProductSubscription pSub) 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
	{
		int iBandWidth,propertiesIndex;
		com.sbc.eia.idl.types.ObjectProperty[] pSubProperties = null;
		com.sbc.eia.idl.types.ObjectProperty aPSubProperty = null;
		String sBandWidthPropetyTag = null,sBandWidth =null;

		iBandWidth = 0;
		try
		{
		   if ( validateProductId(pSub.aProductID))
		   {
			  pSubProperties = pSub.aProperties.theValue();
			  propertiesIndex = 0;
			  while (propertiesIndex < pSubProperties.length) 
			  {
				 aPSubProperty = pSubProperties[propertiesIndex];
				 if (validateProperty(pSub.aProductID,aPSubProperty.aTag,aPSubProperty.aValue))
				 {
					sBandWidthPropetyTag = getBandWidthPropertTag(pSub.aProductID,aPSubProperty.aTag,aPSubProperty.aValue);
					iBandWidth += findBandWidth(sBandWidthPropetyTag,aPSubProperty.aTag,aPSubProperty.aValue);
					if (this.isItAnyProduct(pSub.aProductID))
					   break;
				 }
				 propertiesIndex++;
			 } // while (propertiesIndex < pSubProperties.length)
     	   
		   }//if ( validateProductId(pSub.aProductID) )
		} 
		catch (REException e)
		{	   
			String sErrorMsg;
			sErrorMsg = "< Can not calculate Bandwidth. BandWidthCalculator.getBandWidth() received: " + e.toString();
			String aBisName;
			aBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");
			utility.throwException(ExceptionCode.ERR_RM_INVALID_BANDWIDTH,sErrorMsg,aBisName,Severity.Recoverable);
		}
		if (0 < iBandWidth)
		{		   
		   sBandWidth = Integer.toString(iBandWidth);
		   addBandwidthProperty(pSub,sBandWidth);
		}
	   
	   //return sBandWidth;
	}	



	/**
	 * @return
	 */
//	private Properties getProperties() {
//		return properties;
//	}
	
	/**
	 * @param pSub
	 * @param bandWidth
	 */
	private void addBandwidthProperty(ProductSubscription pSub,String bandWidth) 
	{
		ObjectPropertyManager objPropertyMgr = null;
		
		objPropertyMgr = new ObjectPropertyManager(pSub.aProperties);
		objPropertyMgr.add(new ObjectProperty(BandWidthPropetyTag,bandWidth));
		pSub.aProperties = (ObjectPropertySeqOpt) IDLUtil.toOpt(ObjectPropertySeqOpt.class, objPropertyMgr.toArray());
	}


	/**
	 * 
	 */
	private void initDelimiter() {

		delimiter = properties.getProperty(DELIMITER_TAG);
		if ( null == delimiter)
		   delimiter = DEFAULT_DELIMITER;
	}

	/**
	 * 
	 */
	private void initBaseProductsList() {

		String productIdList = null,
		       aProductId = null;
		StringTokenizer strTok = null;
		if (null == baseProductIds)
		{
			baseProductIds = new ArrayList();
		}
		productIdList = properties.getProperty(BASE_PRODUCTS_TAG); 
		if ( null != productIdList)
		{
			strTok = new StringTokenizer( productIdList, delimiter );
			aProductId = strTok.nextToken();
		    while (!aProductId.equalsIgnoreCase("NULL"))
		    {
		       baseProductIds.add(aProductId);
			   aProductId = strTok.nextToken();
		    }
		}
	}

	/**
	 * @param aProduct
	 * @return
	 */
	private boolean isItBaseProduct(String aProduct) 
	{
	   boolean retValue = false;
	   
	   retValue = baseProductIds.contains(aProduct);
	
	   return retValue;
	}

	/**
	 * 
	 */
	private void initAnyProductsList() {

		String productIdList = null,
			   aProductId = null;
		StringTokenizer strTok = null;
		if (null == anyProductIds)
		{
			anyProductIds = new ArrayList();
		}
		productIdList = properties.getProperty(ANY_PRODUCTS_TAG); 
		if ( null != productIdList)
		{
			strTok = new StringTokenizer( productIdList, delimiter );
			aProductId = strTok.nextToken();
			while (!aProductId.equalsIgnoreCase("NULL"))
			{
			   anyProductIds.add(aProductId);
			   aProductId = strTok.nextToken();
			}
		}
	}

	/**
	 * @param aProduct
	 * @return
	 */
	private boolean isItAnyProduct(String aProduct) 
	{
	   boolean retValue = false;
	   
	   retValue = anyProductIds.contains(aProduct);
	
	   return retValue;
	}

	/**
	 * 
	 */
	private void initBasePropertiesList() {

		String propertiesList = null,
			   aPropertyName = null;
		StringTokenizer strTok = null;
		if (null == baseProperties)
		{
			baseProperties = new ArrayList();
		}
		propertiesList = properties.getProperty(BASE_PROPERTIES_TAG); 
		if ( null != propertiesList)
		{
			strTok = new StringTokenizer( propertiesList, delimiter );
			aPropertyName = strTok.nextToken();
			while (!aPropertyName.equalsIgnoreCase("NULL"))
			{
				baseProperties.add(aPropertyName);
			    aPropertyName = strTok.nextToken();
			}
		}
	}

	/**
	 * @param aProperty
	 * @return
	 */
	private boolean isItBaseProperty(String aProperty) 
	{
	   boolean retValue = false;
	   
	   retValue = baseProperties.contains(aProperty);
	
	   return retValue;
	}

	/**
	 * @return
	 */
//	private String getDelimiter() {
//
//		return delimiter;
//	}


}
