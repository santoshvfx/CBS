//$Id: ServiceBundleIdCalculator.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;


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
/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceBundleIdCalculator {
    private static final String FileNamePropertyTag = "SERVICE_BUNDLE_ID_PROPERTIES_FILE_NAME",
	                     ARRAYSIZE_TAG = "ARRAY_SIZE",
	                     DELIMITER_TAG = "DELIMITER",
	                     DEFAULT_DELIMITER = "&",
	                     ANY_PRODUCTS_TAG = "ANY_PRODUCT_IDS";


	private final int DEFAULT_ARRAY_SIZE = 6;
	
    
    private int arraySize = 0;
	private String delimiter = null;
	

	private Utility utility;
	private Properties properties;
	private ArrayList  anyProductIds = null;
	
    /**
	 * @param aUtility
	 * @throws FileNotFoundException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws IOException
	 */
	public ServiceBundleIdCalculator(Utility aUtility) 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
    {
		setUtility(aUtility);
        loadProperties();
		initDelimiter();
		initArraySize();   
		initAnyProductsList();		 
    }

	/**
	 * @return
	 */
//	private Properties getProperties() {
//		return properties;
//	}

	/**
	 * @return
	 */
//	private Utility getUtility() {
//		return utility;
//	}



	/**
	 * @param productId
	 * @param propertyName
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
	private boolean validatePropertyName(String productId,String propertyName) 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound, REException
	{
		boolean bRetValue = false;
		String rule = null;
		ValidationRulesManager validationRulesMgr = null;
		
		if (this.isItAnyProduct(productId))
		  propertyName = productId;
		
		rule = this.properties.getProperty("PROPERTY_NAMES_RULE");
		if ( null != rule)
		{
			validationRulesMgr = new ValidationRulesManager(delimiter);
			bRetValue = validationRulesMgr.ExecuteValidationRule(rule,propertyName);
		}
	    
		return bRetValue;
	}


	/**
	 * 
	 */
	private void initArraySize()
	 {
	   String sArraySize = null;
	   
	   sArraySize = properties.getProperty(ARRAYSIZE_TAG);
	   if (null != sArraySize)
		  arraySize = Integer.parseInt(sArraySize);
	   else
		 arraySize = DEFAULT_ARRAY_SIZE;
	   
	}

	/**
	 * @return
	 */
//	private int getArraySize()
//	 {
//       return arraySize;
//     }
	
	/**
	 * @param productId
	 * @param propertyName
	 * @return
	 */
	int getArrayIndex(String productId,String propertyName)
	 {
	   int iRetValue = 100;
	   int index = 0;
	   String strIndexTag = null,
	          strIndex = null;
	   
	   strIndexTag = productId.toUpperCase();
	   strIndexTag += "_";
	   if ( !isItAnyProduct(productId))
	      strIndexTag += propertyName.toUpperCase();
	   else
	      strIndexTag += "ANY";
	      
	   strIndex = this.properties.getProperty(strIndexTag);
       if ( null != strIndex)
	     iRetValue = Integer.parseInt(strIndex);
	   
	   return iRetValue;
	 }

	/**
	 * @param aActionCode
	 * @return
	 * @throws REException
	 */
	private boolean validateActionCode(String aActionCode) throws REException
	{
		boolean bRetValue = false;
		String rule = null;
		ValidationRulesManager validationRulesMgr = null;
		
		rule = this.properties.getProperty("ACTIONID_RULE");
		if ( null != rule)
		{
			validationRulesMgr = new ValidationRulesManager(delimiter);
			bRetValue = validationRulesMgr.ExecuteValidationRule(rule,aActionCode);
		}
	    
		return bRetValue;
	}

	

	/**
	 * @param aProductId
	 * @return
	 * @throws REException
	 */
	private boolean validateProductId(String aProductId) throws REException
	{
	   
		boolean bRetValue = false;
		String rule = null;
		ValidationRulesManager validationRulesMgr = null;
		 		
		rule = this.properties.getProperty("PRODUCT_TYPE_RULE");
		if ( null != rule)
		{
			validationRulesMgr = new ValidationRulesManager(delimiter);
			bRetValue = validationRulesMgr.ExecuteValidationRule(rule,aProductId);
		}
	    
		return bRetValue;
	   
	}

	/**
	 * @return
	 */
	private String getActionCode(ProductSubscription pSub) {
		String sActionCode = null;
		int propertiesIndex = 0;
		
		ObjectProperty[] pSubProperties = null;
		ObjectProperty aPSubProperty = null;

		pSubProperties = pSub.aProperties.theValue();
		propertiesIndex = 0;
			
		while (propertiesIndex < pSubProperties.length)
		{
					
			aPSubProperty = pSubProperties[propertiesIndex];
		    if ( aPSubProperty.aTag.equalsIgnoreCase("ActionID") )
		    {
			   sActionCode = aPSubProperty.aValue;
		       break;
		    }
			propertiesIndex++;
	    }//while (propertiesIndex < pSubProperties.length)
		
		return sActionCode;
	}

   /**
 * @param aList
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
   public String getServiceBundleId(ArrayList aList) 
	  throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
	  {
		  int index, propertiesIndex, serviceBundleIdArrayIndex = 0;
		  ProductSubscription pSub;
		  ObjectProperty[] pSubProperties = null;
		  ObjectProperty aPSubProperty = null;
		  ServiceBundleIdArray serviceBundleIdArray = null;
		  String sBundleId = null, sErrorMsg = null, sBisName = null;
	   	
		  try
		  {
			 serviceBundleIdArray = new ServiceBundleIdArray(arraySize,delimiter);
			 index = 0;
			 while (index < aList.size())
			 {
				 pSub = (ProductSubscription)aList.get(index);
				 if ( validateProductId(pSub.aProductID) && validateActionCode(getActionCode(pSub)) )
				 {
					pSubProperties = pSub.aProperties.theValue();
					propertiesIndex = 0;
			
					while (propertiesIndex < pSubProperties.length)
					{
					   aPSubProperty = pSubProperties[propertiesIndex];
					   if (validatePropertyName(pSub.aProductID,aPSubProperty.aTag))	   
					   {	
							   serviceBundleIdArrayIndex = getArrayIndex(pSub.aProductID,aPSubProperty.aTag);
							   if (isItAnyProduct(pSub.aProductID))
							       serviceBundleIdArray.set(serviceBundleIdArrayIndex,pSub.aProductID,"ANY");
                               else
						           serviceBundleIdArray.set(serviceBundleIdArrayIndex,pSub.aProductID,aPSubProperty.aValue);
                               
					   }
					   propertiesIndex++;
					} // while (propertiesIndex < pSubProperties.length)
				 }//if ( validateActionCode(getActionCode(pSub)) && validateProductId(pSub.aProductID) )
				 index++;
			 } // while (index < aList.size() )

			 sBundleId = properties.getProperty(serviceBundleIdArray.toString());
		  } 
		  catch (REException e)
		  {
			  sErrorMsg = "Can not calculate ServiceBundleId. getServiceBundleId() received: " + e.toString();
			  sBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");
			  utility.throwException(ExceptionCode.ERR_RM_INVALID_SERVICE_BUNDLE_ID,sErrorMsg,sBisName,Severity.Recoverable);
		  }
		  if (null == sBundleId)
		  {   
			 sErrorMsg = "< Can not calculate ServiceBundleId. Please check your input Data: " + serviceBundleIdArray.toString();
			 sBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");
			 utility.throwException(ExceptionCode.ERR_RM_INVALID_SERVICE_BUNDLE_ID,sErrorMsg,sBisName,Severity.Recoverable);
		  }
	   
		  return sBundleId;
	  }

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void loadProperties() 
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
	{
         String aFileName = null,
                aBisName = null;
		 
		
         aFileName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv(FileNamePropertyTag);
		 aBisName = ((com.sbc.eia.bis.RmNam.utilities.TranBase)utility).getEnv("BIS_NAME");

		 try
		 {
			  properties = PropertiesLoader.loadServiceBundleIdProperties(aFileName,aBisName,utility);
		 }
		catch (FileNotFoundException e)
		 {
			utility.throwException(ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
			  "FileNotFoundException on ServiceBundleIdCalculator.loadproperties method: " + e.getMessage() + ">",
			  aBisName,
			  Severity.Recoverable);
	     }
		catch (IOException e)
		{
		   utility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
			 "IOException on ServiceBundleIdCalculator.loadproperties method: " + e.getMessage() + ">",
			 aBisName,
			 Severity.Recoverable);
		} 

	}

	/**
	 * @param utility
	 */
	private void setUtility(Utility utility) {
		this.utility = utility;
	}

	/**
	 * @param utility
	 */
	private void initDelimiter() {

		delimiter = properties.getProperty(DELIMITER_TAG);
		if ( null == delimiter)
		   delimiter = DEFAULT_DELIMITER;
	}

	/**
	 * @param utility
	 */
//	private String getDelimiter() {
//
//		return delimiter;
//	}

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



}
