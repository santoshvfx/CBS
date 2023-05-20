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
//# Date      | Author             | Notes
//# ----------------------------------------------------------------------------
//#	03/30/2005  jp2854		        Creation.
//# 04/08/2005  Jinmin Ni           Changed to accommodate the IDL interface change
//#	05/09/2005  Manjula Goniguntla	Added Validation
//# 06/01/2005  Chaitanya			Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1
//# 08/03/2005  Manjula Goniguntla  Validated BisContext and Client before logging input.
//# 11/08/2005  jp2854              Changes for IDL bundle 33. Removed the references for CustomerPremisEquipment.
//# 01/16/2006  Michael Khalili     Changes for LS2 Support.


package com.sbc.eia.bis.facades.rm.transactions.PublishAutoDiscoveryResults;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.NetProvision.Utilities.RmbisProductSubscriptionToNetPComponentList;
import com.sbc.eia.bis.BusinessInterface.rm.XNGRC.Utilities.RmbisProductSubscriptionToXngRcComponentList;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.rm.components.ProductSubscriptionManager;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.validator.Validator;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.bishelpers.AddressOptBisHelper;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm.bishelpers.PublishAutoDiscoveryResultsReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionProperty;
import com.sbc.eia.idl.sm_ls_types.bishelpers.ProductSubscriptionBisHelper;
import com.sbc.eia.idl.sm_ls_types.bishelpers.ProductSubscriptionSeqBisHelper;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import java.util.ArrayList;

/**
 * @author jp2854
 */
public class PublishAutoDiscoveryResults extends TranBase {

	private Utility utility = null;
	private Hashtable aProperties = null;
	private Validator validatorObj = null;
	private Validator validatorProductSubscription = null;

	/**
	 * Constructor for PublishAutoDiscoveryResults.
	 */
	public PublishAutoDiscoveryResults() {
		super();
	}

	/**
	 * Constructor for PublishAutoDiscoveryResults.
	 * @param param
	 */
	public PublishAutoDiscoveryResults(Hashtable param) {
		super(param);
		utility = this;
		aProperties = getPROPERTIES();
	}

	/**
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDestination
	 * @param aServiceAddress
	 * @param aProductSubscriptions
	 * @param aTelephoneNumber
	 * @param aAssignedProductId
	 * @param aOrderAction
	 * @param aObjProperties
	 * @param aLogger
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public PublishAutoDiscoveryResultsReturn execute(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aObjProperties,
		Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		callerContext = aContext;
		myLogger = aLogger;

		log(LOG_DEBUG_LEVEL_1, ">" + "PublishAutoDiscoveryResults.execute()");

		String aBisName = getEnv("BIS_NAME");

		// log BisContext
		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
			log(LOG_DEBUG_LEVEL_1, "logInput():aContext");
		}

		PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResultsReturn =
			null;
		com
			.sbc
			.eia
			.bis
			.BusinessInterface
			.rm
			.PublishAutoDiscoveryResults
			.PublishAutoDiscoveryResults service =
			null;

		try {

			//Is client Authorized
			ObjectPropertyManager aContextOPM =	new ObjectPropertyManager(aContext.aProperties);

			validateClient(aContextOPM, null, // group_id
			ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);

			ValidateHelper.validateBisContext(
				utility,
				aContextOPM,
				aBisName);


			//logInput
			logInput(
				aContext,
				aCustomerTransportId,
				aBillingAccountNumber,
				aServiceAddress,
				aProductSubscriptions,
				aTelephoneNumber,
				aAssignedProductId,
				aOrderAction,
				aObjProperties);

			//Validate input using validator
			if (validatorObj ==  null) {
				String map_file_name = (String) aProperties.get("PADR_VALIDATOR_VARIABLE_MAP_FILE");
			
				validatorObj = new Validator(utility, aProperties, map_file_name);
			}	

				validatorObj.validate(aContext, 
						new PublishAutoDiscoveryResultsValidation(
							aContext, 
							aCustomerTransportId, 
							aBillingAccountNumber, 
							aServiceAddress, 
							aProductSubscriptions, 
							aTelephoneNumber, 
							aAssignedProductId, 
							aOrderAction, 
							aObjProperties) , 
						true);

			//Check if ProductSubscription[] is null or if it does not exist
			ValidateHelper.validateProductSubscription(
				utility, 
				aProductSubscriptions, 
				aBisName);

			if (validatorProductSubscription == null){
				validatorProductSubscription = new Validator(
					utility, 
					aProperties, 
					(String) aProperties.get("PRODUCT_SUBSCRIPTION_VALIDATOR_VARIABLE_MAP_FILE"));
			}
			
			//Validate ProductSubscription mandatory attributes
			ValidateHelper.validateProductSubscription(
				validatorProductSubscription, 
				utility, 
				aProperties, 
				aProductSubscriptions, 
				aContext);
			
			validateProductSubsriptions_XNG_RC_AND_NETP(utility, 
				aProductSubscriptions, 
				aBisName);

		    if (service == null) {
				service =
					new com
						.sbc
						.eia
						.bis
						.BusinessInterface
						.rm
						.PublishAutoDiscoveryResults
						.PublishAutoDiscoveryResults(utility, getPROPERTIES(),aProductSubscriptions);
		    }
		    
			publishAutoDiscoveryResultsReturn =
				service.publishAutoDiscoveryResults(
					aContext,
					aCustomerTransportId,
					aBillingAccountNumber,
					aServiceAddress,
					aProductSubscriptions,
					aTelephoneNumber,
					aAssignedProductId,
					aOrderAction,
					aObjProperties);

			log(
				LOG_OUTPUT_DATA,
				"publishAutoDiscoveryResultsReturn=<"
					+ (
						new PublishAutoDiscoveryResultsReturnBisHelper(publishAutoDiscoveryResultsReturn))
						.toString()
					+ ">");

		} finally {
			log(
				LOG_DEBUG_LEVEL_1,
				"<" + "RM::publishAutoDiscoveryResults:execute");
		}

		return publishAutoDiscoveryResultsReturn;

	}

	/**
	 * Method logInput.
	 * @param aContext
	 */

	public void logInput(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties) {

		log(LOG_DEBUG_LEVEL_1, ">" + "PublishAutoDiscoveryResults:logInput()");

		//  Log the input data.

		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
		}

		try {
			log(
				LOG_INPUT_DATA,
				"aCustomerTransportId=<" + aCustomerTransportId + ">");
		} catch (Exception e) {
		}

		try {
			log(
				LOG_INPUT_DATA,
				"aBillingAccountNumber=<"
					+ (new CompositeObjectKeyBisHelper(aBillingAccountNumber))
						.toString()
					+ ">");
		} catch (Exception e) {
		}

		
		try {
			log(
				LOG_INPUT_DATA,
				"aServiceAddress=<"
					+ (new AddressOptBisHelper(aServiceAddress)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// The new field: ProductSubscription
		try {
			log(
				LOG_INPUT_DATA,
				"aProductSubscriptions=<"
					+ (new ProductSubscriptionSeqBisHelper(aProductSubscriptions)).toString()
					+ ">");
		} catch (Exception e) {
		}
		
		
		try {
			log(
				LOG_INPUT_DATA,
				"aTelephoneNumber=<"
					+ (new StringOptBisHelper(aTelephoneNumber)).toString()
					+ ">");
		} catch (Exception e) {
		}

		try {
			log(
				LOG_INPUT_DATA,
				"aAssignedProductId=<" + aAssignedProductId + ">");
		} catch (Exception e) {
		}

		try {
			log(
				LOG_INPUT_DATA,
				"aOrderAction=<"
					+ (new OrderActionBisHelper(aOrderAction)).toString()
					+ ">");
		} catch (Exception e) {
		}

		try {
			log(
				LOG_INPUT_DATA,
				"aProperties=<"
					+ (new ObjectPropertySeqOptBisHelper(aProperties)).toString()
					+ ">");
		} catch (Exception e) {
		}

		log(LOG_DEBUG_LEVEL_1, "<" + "PublishAutoDiscoveryResults:logInput()");
	}

	public void validateProductSubsriptions_XNG_RC_AND_NETP(
			Utility aUtility,
			ProductSubscription[] aProductSubscriptions,
			String aBisName)
			throws
				BusinessViolation,
				ObjectNotFound,
				InvalidData,
				AccessDenied,
				SystemFailure,
				DataNotFound,
				NotImplemented 
			{

				log(LOG_DEBUG_LEVEL_1, "<" + "PublishAutoDiscoveryResults:validateProductSubsriptions_XNG_RC_AND_NETP()");

				RmbisProductSubscriptionToXngRcComponentList pSubUtility = null;
				ArrayList aList = null;

				com.sbc.eia.idl.types.ObjectProperty[] properties = null;
				com.sbc.eia.idl.types.ObjectProperty aProperty = null;
			  	String prodID = "";
				String Tag = "";
				String Value = "";			  
				String QualifierTag = "";
				String QualifierValue = "";

				int index = 0;
				int propertiesIndex = 0;		
	
				boolean bQualifierIsMissing = false;
				boolean bActionIdIsMissing = false;
				boolean bMaterialNumberIsMissing = false;
				boolean bInstalledDateIsMissing = false;

			  try
			  {

					// Check to see if we have at least one ProductSubscription
					// This is a general validation checking, regardless of NetP or XNG service.
					if ( aProductSubscriptions.length <= 0) {
						aUtility.throwException(
								ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
								"Missing required data: ProductSubscription",
								aBisName,
								Severity.UnRecoverable);
					}

					pSubUtility = new RmbisProductSubscriptionToXngRcComponentList(
						((TranBase)aUtility).getPROPERTIES(),
						 aProductSubscriptions);

					aList = pSubUtility.getTheMasterPsubsList();
					index = 0;
				
					while (index < aList.size())
					{
						prodID = ((ProductSubscription)aList.get(index)).aProductID;

						if (((ProductSubscription)aList.get(index)).aProperties ==  null) {
							aUtility.throwException(ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
								"Missing required data: Property in ProductSubscription",
								aBisName,
								Severity.UnRecoverable);
						}
						else {
							properties = ((ProductSubscription)aList.get(index)).aProperties.theValue();
							propertiesIndex = 0;
						}  

						// ActionId is always a required field.
						bActionIdIsMissing = true;

						while (propertiesIndex < properties.length) {

							aProperty = properties[propertiesIndex];
						  	Tag = aProperty.aTag;
						  	Value = aProperty.aValue;

							// ActionID						  
							if ((Tag.equalsIgnoreCase("ActionID")) && (Value != null) && ((Value.trim().length() > 0))) 
								bActionIdIsMissing = false;

							//For Qualifier
							if (prodID.equalsIgnoreCase("NAD")) {

									if ((Tag.equalsIgnoreCase("Qualifier")) && (Value != null) && (Value.trim().length() > 0)) 
											  bQualifierIsMissing = false;

									if (Tag.equalsIgnoreCase("Qualifier") && (Value.equalsIgnoreCase("INID"))) {
										QualifierTag = Tag;
										QualifierValue = Value;
										bMaterialNumberIsMissing = true;
										bInstalledDateIsMissing = true;
									}

									//For Material Number
									if (QualifierTag.equalsIgnoreCase("Qualifier") && (QualifierValue.equalsIgnoreCase("INID"))) {
										if ((Tag.equalsIgnoreCase("MaterialNumber")) && (Value != null) && (Value.trim().length() > 0)) 
												bMaterialNumberIsMissing = false;
									}
									else 
										bMaterialNumberIsMissing = false;

									//For Installed Date
									if (QualifierTag.equalsIgnoreCase("Qualifier") && (QualifierValue.equalsIgnoreCase("INID"))) {
										if ((Tag.equalsIgnoreCase("InstalledDate")) && (Value != null) && (Value.trim().length() > 0))
												bInstalledDateIsMissing = false;
									}
									else 
										bInstalledDateIsMissing = false;
									
							} 
							else 
								 bQualifierIsMissing = false;
								 
							propertiesIndex++;
						  } //End of the While Loop for properties of a prduct

					   
						  if (bMaterialNumberIsMissing) {
							  aUtility.throwException(
									ExceptionCode.ERR_RM_MISSING_COMPONENT_MATERIAL_NUMBER,
									"Missing required data: ProductSubscription - MaterialNumber",
									aBisName,
									Severity.UnRecoverable);
						  }	

						  if (bInstalledDateIsMissing) {
							  aUtility.throwException(
									ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
									"Missing required data: ProductSubscription - InstalledDate",
									aBisName,
									Severity.UnRecoverable);
						  }	
			         
					  index++;
        
				} // end big while for all products
				log(LOG_DEBUG_LEVEL_1, ">" + "PublishAutoDiscoveryResults:validateProductSubsriptions_XNG_RC_AND_NETP()");
			} 
			catch (org.omg.CORBA.BAD_OPERATION e){
				aUtility.throwException(ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
					"Missing required data: In ProductSubscription",
					aBisName,
					Severity.UnRecoverable);
			} 
			catch (NullPointerException e)
			{
				aUtility.throwException(ExceptionCode.ERR_RM_MISSING_NETWORK_COMPONENT,
					"Missing required data: In ProductSubscription",
					aBisName,
					Severity.UnRecoverable);
			  }	
		} 
}
