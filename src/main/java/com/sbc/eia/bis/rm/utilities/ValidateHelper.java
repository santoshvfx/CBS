//$Id: ValidateHelper.java,v 1.3 2006/10/25 20:51:42 ds4987 Exp $
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
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 04/12/2005   Jinmin Ni      Creation.
//# 04/25/2005   Jinmin Ni      Create empty methods with signatures 
//#                             Removed commented-out codes
//# 04/27/2005   Jp2854         Added validateBillingAccountNumber method
//# 04/28/2005   Mark Kashevaroff
//#                             filled out validateOrderAction
//# 04/28/2005   kk8467         Added code to validateResidentialGateway
//# 04/29/2005   Rene Duka      Added the following methods:
//#                                 - validateCustomerTransportId
//#                                 - validateSiteId
//#                                 - validateServiceBundleId
//#                                 - validateDueDate
//#                                 - validateSAILocation
//#                                 - validateSAILocationId
//#                                 - validateSAIWireCenter
//# 05/12/2005   Rene Duka      Added DSLAM error codes.
//# 05/20/2005   Rene Duka      Added the following methods:
//#                                 - validateDSLAMF1Cable
//#                                 - validateDSLAMF1CentralOfficePort
//#                                 - validateDSLAMF2Cable
//#                                 - validateDSLAMF2TerminalAddress
//# 06/16/2005   Chaitanya      Added the following methods:
//#                                 - validateProductCode
//#                                 - validateDestination
//# 06/20/2005   Rene Duka      Added validateSAINpaNxx method.
//# 06/30/2005   Kavitha Kodali Changed aDeviceId to aSerialNumber in validateResidentialGateway().
//# 07/05/2005   Kavitha Kodali Updated validateResidentialGateway().
//# 07/07/2005   Kavitha Kodali Changed back aSerialNumber to aDeviceId in validateResidentialGateway().
//# 07/10/2005   mk3198         Added method validateTerminalAddress.
//# 08/01/2005   Manjula Goniguntla   Added methods :
//#                                      - validateWireCenter()
//#                                      - validateCustomerPremisEquipmentOpt()
//#                                      - validateAssignedProductId()             
//# 08/11/2005   Rene Duka      DR 142554: Added validate7450LogicalEgressPort().
//# 08/15/2005   Kavitha Kodali DR 142324: Added new exception codes in validateResidentialGateway().
//# 08/24/2005   Rene Duka      DR 143306 / DR 143006: Added validateDSLAMId() and validateDSLAMPhysicalPort().
//# 08/30/2005   Rene Duka      DR 143673: Changed error code for missing Service Bundle Id from 03168 to 03300. 
//# 09/01/2005   Rene Duka      DR 143686: Changed error code for missing Transport Type from 03471 to 03291. 
//# 09/01/2005   Rene Duka      DR 143690: Modified validation of Location. 
//# 09/19/2005   Kavitha Kodali	CR# KDM 1: Changed aDeviceId to aSerialNumber in validateResidentialGateway().
//# 09/19/2005   Kavitha Kodali	CR# 86: Changed Device ID as required, Serial Number as optional.
//# 10/20/2005   Manjula Goniguntla   Modified validateCustomerPremisEquipmentOpt() as CPE is made optional.
//# 11/08/2005   jp2854         Changes for IDL bundle 33. Removed the validateCustomerPremisEquipmentOpt() method.
//# 12/08/2005   Changchuan Yin Add validateProductSubscription
//# 01/16/2006   Michael Khalili      The following methods got added for LS2 support.
//#                                 validateProductSubsriptionsNetP() and validateProductSubsriptionsXNG()                                        
//# 03/20/06     Changchuan Yin. Fixed validateProductSubsriptionsMS_XNG()
//# 04/25/2006   Eswara Batchu   Changed Exception Code for Missing OLT TId. 
//# 06/06/2006	 Doris Sunga	 LS R3: Added RGPON in validateNetworkType() method
//# 06/14/2006	 Chaitanya		 Added validateProductSubscription() method
//# 06/20/2006	 Chaitanya		 Added validateProductSubscriptionSeqOpt() method
//#									Modified validateProductSubscription() method
//# 07/13/06     Liljequist		Change validateProductSubscription to take a Validator object.
//# 10/25/2006	 Doris Sunga	LS4:  Adding GPON network type on validateNetworkType()	  

package com.sbc.eia.bis.rm.utilities;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.rm.components.ProductSubscriptionManager;
import com.sbc.eia.bis.validator.Validator;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionSeqOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;


/**
 * @author jn1936
 */
public class ValidateHelper {
	
	
	public static void validateStringOpt(
		Utility aUtility,
		StringOpt aStringOpt,
		String aExceptionCode,
		String msg,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {
		if (OptHelper.isStringOptEmpty(aStringOpt)) {
			aUtility.throwException(
				aExceptionCode,
				msg,
				aBisName,
				Severity.UnRecoverable);
		}
	}
	public static void validateString(
		Utility aUtility,
		String aString,
		String aExceptionCode,
		String msg,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {
		if (aString == null || aString.trim().length() < 1) {
			aUtility.throwException(
				aExceptionCode,
				msg,
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateBisContext(
		Utility aUtility,
		ObjectPropertyManager aContextOPM,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {
		
		//validate for common bis context: CUSTOMERNAME
		String propertyValue = null;

		//validate CUSTOMERNAME
		if ((propertyValue =
			aContextOPM.getValue(BisContextProperty.CUSTOMERNAME))
			== null
			|| propertyValue.trim().length() < 1)
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_CUSTOMERNAME,
				formatInvalidData(
					BisContext.class,
					"aContext.aProperties["
						+ BisContextProperty.CUSTOMERNAME
						+ "]"),
				aBisName,
				Severity.UnRecoverable);

	}
	
//	Added more validations Rm 15.10
	  //05/08/2006
	  public static void validateBisContextNew(
			  Utility aUtility,
			  ObjectPropertyManager aContextOPM,
			  String aBisName)
			  throws
				  BusinessViolation,
				  ObjectNotFound,
				  InvalidData,
				  AccessDenied,
				  SystemFailure,
				  DataNotFound,
				  NotImplemented {
		
			  //validate for common bis context: CUSTOMERNAME
			  String propertyValue = null;
 
			  // Business Unit	
			  if ((propertyValue =
							  aContextOPM.getValue(BisContextProperty.BUSINESSUNIT))
							  == null
							  || propertyValue.trim().length() < 1)
							  aUtility.throwException(
								  ExceptionCode.ERR_RM_MISSING_BUSINESS_UNIT,
								  formatInvalidData(
									  BisContext.class,
									  "aContext.aProperties["
										  + BisContextProperty.BUSINESSUNIT
										  + "]"),
								  aBisName,
								  Severity.UnRecoverable);
			  // Application	
			  if ((propertyValue =
								  aContextOPM.getValue(BisContextProperty.APPLICATION))
									  == null || propertyValue.trim().length() < 1)
											  aUtility.throwException(
												  ExceptionCode.ERR_RM_MISSING_APPLICATION,
												  formatInvalidData(
													  BisContext.class,
													  "aContext.aProperties["
														  + BisContextProperty.APPLICATION
														  + "]"),
												  aBisName,
												  Severity.UnRecoverable);					

			  // Customer Name
			  if ((propertyValue =
				  aContextOPM.getValue(BisContextProperty.CUSTOMERNAME))
				  == null
				  || propertyValue.trim().length() < 1)
				  aUtility.throwException(
					  ExceptionCode.ERR_RM_MISSING_CUSTOMERNAME,
					  formatInvalidData(
						  BisContext.class,
						  "aContext.aProperties["
							  + BisContextProperty.CUSTOMERNAME
							  + "]"),
					  aBisName,
					  Severity.UnRecoverable);

			 // Logging Information
			 if ((propertyValue =
									aContextOPM.getValue(BisContextProperty.LOGGINGINFORMATION))
									== null
									|| propertyValue.trim().length() < 1)
									aUtility.throwException(
										ExceptionCode.ERR_RM_MISSING_LOGGING_INFORMATION,
										formatInvalidData(
											BisContext.class,
											"aContext.aProperties["
												+ BisContextProperty.LOGGINGINFORMATION
												+ "]"),
										aBisName,
									  Severity.UnRecoverable);
							
					
		 

	}

	public static void validateOrderAction(
		Utility aUtility,
		OrderAction aOrderAction,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {
		// aOrderAction
		if (aOrderAction == null) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_ORDER_ACTION,
				"Missing required data: ORDER ACTION",
				aBisName,
				Severity.UnRecoverable);
		}

		// aOrderAction.aOrder  :   Required    :   AN 50
		validateStringOpt(
			aUtility,
			aOrderAction.aOrder,
			ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_NUMBER,
			"Missing required data: ORDER ACTION NUMBER",
			aBisName);

		// aOrderAction.aOrderId
		validateStringOpt(
			aUtility,
			aOrderAction.aOrderActionId,
			ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_ID,
			"Missing required data: ORDER ACTION ID",
			aBisName);

		// aOrderAction.aOrderType  :   Required    :   "Provide"
		validateStringOpt(
			aUtility,
			aOrderAction.aOrderActionType,
			ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_TYPE,
			"Missing required data: ORDER ACTION TYPE",
			aBisName);
	}

	public static void validateResidentialGateway(
		Utility aUtility,
		ResidentialGateway aRG,
		String aBisName)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String propertyValue = null;

		// aRG
		if (aRG == null) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_RESIDENTIAL_GATEWAY,
				"Missing required data: Residential Gateway Object",
				aBisName,
				Severity.UnRecoverable);
		}

		// aRG.aDeviceId
		if (OptHelper.isStringOptEmpty(aRG.aDeviceId)) {
			aUtility.throwException(
			ExceptionCode.ERR_RM_MISSING_RESIDENTIAL_GATEWAY_ID,            
			"Missing required data: aRG.aDeviceId",
			aBisName,
			Severity.UnRecoverable);
		}

		// aRG.aFirmwareVersionNumber
		if (OptHelper.isStringOptEmpty(aRG.aFirmwareVersionNumber)) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_FIRMWARE_VERSION_NUMBER,
				"Missing required data: aRG.aFirmwareVersionNumber",
				aBisName,
				Severity.UnRecoverable);
		}

		// aRG.aModelNumber
		if (OptHelper.isStringOptEmpty(aRG.aModelNumber)) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_MODEL,
				"Missing required data: aRG.aModelNumber",
				aBisName,
				Severity.UnRecoverable);
		}

		// aRG.aMACAddress
		if (OptHelper.isNetworkAddressOptEmpty(aRG.aMACAddress)) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_MAC_ADDRESS,
				"Missing required data: aRG.aMACAddress",
				aBisName,
				Severity.UnRecoverable);
		}

		// aRG.aVendor
		if (OptHelper.isStringOptEmpty(aRG.aVendor)) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_VENDOR,
				"Missing required data: aRG.aVendor",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateLocation(
		Utility aUtility,
		Location aLocation,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		if (aLocation == null
			|| OptHelper.isAddressOptNull(
				aLocation.aProviderLocationProperties[0].aServiceAddress)) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION,
				"Missing required data: aServiceLocation",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateBillingAccountNumber(
		Utility aUtility,
		CompositeObjectKey aBAN,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {
		boolean isBANValid = false;

		try {
			ObjectKey objectKeys[] = aBAN.aKeys;
			if (objectKeys.length >= 1) {
				ObjectKey aObjectKey = objectKeys[0];
				if (aObjectKey
					.aKind
					.equals("com.sbc.eia.bis.BillingAccountNumber")
					&& (aObjectKey.aValue != null)
					&& (aObjectKey.aValue.trim().length() >= 1))
					isBANValid = true;
			}
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e1) {
		}

		if (isBANValid == false) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_BILLING_ACCOUNT_NUMBER,
				"Missing required Billing Account Number",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateCustomerTransportId(
		Utility aUtility,
		String aCustomerTransportId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aCustomerTransportId,
			ExceptionCode.ERR_RM_MISSING_CUSTOMER_TRANSPORT_ID,
			"Missing required data: aCustomerTransportId",
			aBisName);
	}

	public static void validateSiteId(
		Utility aUtility,
		String aSiteId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aSiteId,
			ExceptionCode.ERR_RM_MISSING_SITE_ID,
			"Missing required data: aSiteId",
			aBisName);
	}

	public static void validateServiceBundleId(
		Utility aUtility,
		StringOpt aServiceBundleId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aServiceBundleId,
			ExceptionCode.ERR_RM_MISSING_QOS_POLICY_ID,
			"Missing required data: aServiceBundleId",
			aBisName);
	}

	public static void validateDueDate(
		Utility aUtility,
		EiaDate aDueDate,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		if (aDueDate == null) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_DUE_DATE,
				"Missing required data: aDueDate",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateSAIBindingPost(
		Utility aUtility,
		StringOpt aSAIBindingPost,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aSAIBindingPost,
			ExceptionCode
				.ERR_RM_MISSING_DSLAM_SERVICE_AREA_INTERFACE_BINDING_POST,
			"Missing required data: aDSLAM.aServiceAreaInterfaceBindingPost",
			aBisName);
	}

	public static void validateSAILocation(
		Utility aUtility,
		Location aSAILocation,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		if (aSAILocation == null) {
			aUtility.throwException(
				ExceptionCode
					.ERR_RM_MISSING_DSLAM_SERVICE_AREA_INTERFACE_LOCATION,
				"Missing required data: aDSLAM.aServiceAreaInterfaceLocation",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validateSAILocationId(
		Utility aUtility,
		StringOpt aSAILocationId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aSAILocationId,
			ExceptionCode.ERR_RM_MISSING_DSLAM_ID,
			"Missing required data: aDSLAM.aServiceAreaInterfaceLocation.aLocationId",
			aBisName);
	}

	public static void validateSAIWireCenter(
		Utility aUtility,
		StringOpt aSAIWireCenter,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aSAIWireCenter,
			ExceptionCode.ERR_RM_MISSING_DSLAM_WIRE_CENTER,
			"Missing required data: aDSLAM.aServiceAreaInterfaceLocation.aProviderLocationProperties[].aSbcServingOfficeWirecenter",
			aBisName);
	}

	public static void validateSAINpaNxx(
		Utility aUtility,
		StringOpt aSAINpaNxx,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aSAINpaNxx,
			ExceptionCode.ERR_RM_MISSING_NPA_NXX,
			"Missing required data: aDSLAM.aServiceAreaInterfaceLocation.aProviderLocationProperties[].aPrimaryNpaNxx",
			aBisName);
	}

	public static void validateDSLAMVirtualLAN_A(
		Utility aUtility,
		StringOpt aVLANAId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aVLANAId,
			ExceptionCode.ERR_RM_MISSING_DSLAM_VLAN_ID_A,
			"Missing required data: aDSLAM.aVLANAId",
			aBisName);
	}

	public static void validateDSLAMVirtualLAN_Z(
		Utility aUtility,
		StringOpt aVLANZId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aVLANZId,
			ExceptionCode.ERR_RM_MISSING_DSLAM_VLAN_ID_Z,
			"Missing required data: aDSLAM.aVLANZId",
			aBisName);
	}

	public static void validateDSLAMF2Cable(
		Utility aUtility,
		StringOpt aF2Cable,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aF2Cable,
			ExceptionCode.ERR_RM_MISSING_F2_CABLE,
			"Missing required data: aDSLAM.aF2Cable",
			aBisName);
	}

	public static void validateDSLAMF2TerminalAddress(
		Utility aUtility,
		StringOpt aF2TerminalAddress,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aF2TerminalAddress,
			ExceptionCode.ERR_RM_MISSING_F2__TERMINAL_ADDRESS,
			"Missing required data: aDSLAM.aF2TerminalAddress",
			aBisName);
	}

	public static void validateDSLAMF1Cable(
		Utility aUtility,
		StringOpt aF1Cable,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aF1Cable,
			ExceptionCode.ERR_RM_MISSING_F1_CABLE,
			"Missing required data: aDSLAM.aF1Cable",
			aBisName);
	}

	public static void validateDSLAMF1CentralOfficePort(
		Utility aUtility,
		StringOpt aF1CentralOfficePort,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aF1CentralOfficePort,
			ExceptionCode.ERR_RM_MISSING_F1_CO_DLSAM_PORT,
			"Missing required data: aDSLAM.aF1CentralOfficePort",
			aBisName);
	}

	public static void validateDSLAMId(
		Utility aUtility,
		StringOpt aId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aId,
			ExceptionCode.ERR_RM_MISSING_DSLAM_ID,
			"Missing required data: aDSLAM.aId",
			aBisName);
	}

	public static void validateDSLAMPhysicalPort(
		Utility aUtility,
		StringOpt aPhysicalPort,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aPhysicalPort,
			ExceptionCode.ERR_RM_MISSING_DSLAM_PORT_ID,
			"Missing required data: aDSLAM.aPhysicalPort",
			aBisName);
	}

	public static void validateTransportType(
		Utility aUtility,
		String aTransportType,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aTransportType,
			ExceptionCode.ERR_RM_INVALID_CUSTOMER_TRANSPORT_TYPE,
			"Missing required data: aTransportType",
			aBisName);
	}

	public static void validateProductCode(
		Utility aUtility,
		String aProductCode,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aProductCode,
			ExceptionCode.ERR_RM_MISSING_PRODUCT_CODE,
			"Missing required data: Product Code",
			aBisName);
	}

	public static void validateDestination(
		Utility aUtility,
		String aProductCode,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aProductCode,
			ExceptionCode.ERR_RM_MISSING_DESTINATION,
			"Missing required data: Destination",
			aBisName);
	}

	public static void validateSbcServingOfficeWireCenter(
		Utility aUtility,
		StringOpt aSbcServingOfficeWireCenter,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aSbcServingOfficeWireCenter,
			ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION_WIRECENTER,
			"Missing required data: Service Location Wire Center",
			aBisName);
	}

	public static String formatInvalidData(
		Class aTopLevelClassContainingTheOffendingField,
		String aFieldName) {
		return "Missing Data: "
			+ aTopLevelClassContainingTheOffendingField.getName()
			+ ":"
			+ aFieldName
			+ ". ";
	}

	public static void validateTerminalAddress(
		Utility aUtility,
		AddressOpt aTerminalAddress,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		if (aTerminalAddress == null) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_MISSING_NAME,
				"Missing required data: aTerminalAddress",
				aBisName,
				Severity.UnRecoverable);
		}
	}

	public static void validate7450LogicalEgressPort(
		Utility aUtility,
		StringOpt a7450LogicalEgressPort,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			a7450LogicalEgressPort,
			ExceptionCode.ERR_RM_MISSING_LOGICAL_7450_EGRESS_PORT,
			"Missing required data: aNetwork7450Switch.aLogicalEgressPort",
			aBisName);
	}

	public static void validateWireCenter(
		Utility aUtility,
		StringOpt aWireCenter,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateStringOpt(
			aUtility,
			aWireCenter,
			ExceptionCode.ERR_RM_MISSING_WIRE_CENTER,
			"Missing required data: Service Location Wire Center",
			aBisName);
	}


	//aAssignedProductId
	public static void validateAssignedProductId(
		Utility aUtility,
		String aAssignedProductId,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		validateString(
			aUtility,
			aAssignedProductId,
			ExceptionCode.ERR_RM_MISSING_ASSIGNED_PRODUCT_ID,
			"Missing required data: Assigned Product ID",
			aBisName);
	}
	
	//NetworkType
	public static void validateNetworkType(
		Utility aUtility,
		String aNetworkType,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		boolean isNetworkTypeValid = false;

		validateString(
			aUtility,
			aNetworkType,
			ExceptionCode.ERR_RM_MISSING_NETWORK_TYPE,
			"Missing the required Network Type.",
			aBisName);

		/* LS R4: added GPON networkType */
		if (aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTN)
			|| aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP)
			|| aNetworkType.equalsIgnoreCase(NetworkTypeValues.RGPON)
			|| aNetworkType.equalsIgnoreCase(NetworkTypeValues.GPON)) {
			isNetworkTypeValid = true;
		}

		if (isNetworkTypeValid == false) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_INVALID_NETWORK_TYPE,
				"Invalid Network Type entered",
				aBisName,
				Severity.UnRecoverable);
		}

	}

	public static void validateDSLAMId(
		Utility aUtility,
		DSLAMTransportOpt aDSLAMTransportOpt,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		try {
			String id =
				aDSLAMTransportOpt.theValue().aEquipmentTargetId.theValue().trim();
			if (id != null && id.length() > 0)
				return;
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		aUtility.throwException(
			ExceptionCode.ERR_RM_MISSING_DSLAM_ID,
			"Missing the required data: DSLAM ID",
			aBisName,
			Severity.UnRecoverable);
	}

	public static void validateOpticalLineTerminalTargetId(
		Utility aUtility,
		OpticalLineTerminalOpt aOpticalLineTerminal,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		try {
			String id = aOpticalLineTerminal.theValue().aEquipmentTargetId.theValue().trim();
			if (id != null && id.length() > 0)
				return;
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		aUtility.throwException(
			ExceptionCode.ERR_RM_MISSING_OLT_TID,
			"Missing OLT TId",
			aBisName,
			Severity.UnRecoverable);
	}

	public static void validateOpticalNetworkTerminalAccessId(
		Utility aUtility,
		OpticalNetworkTerminalOpt aOpticalNetworkTerminal,
		String aBisName)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented {

		try {
			String id =
				aOpticalNetworkTerminal.theValue().aAccessId.theValue().trim();
			if (id != null && id.length() > 0)
				return;
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {
		}

		aUtility.throwException(
			ExceptionCode.ERR_RM_MISSING_ONT_AID,
			"Missing ONT AID",
			aBisName,
			Severity.UnRecoverable);
	}
	
	public static void validateProductSubscription(
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
				NotImplemented {

				if ((aProductSubscriptions == null) || (aProductSubscriptions.length < 1))  {
					aUtility.throwException(
						ExceptionCode.ERR_RM_MISSING_PRODUCT_SUBSCRIPTION,
						"Missing required data: ProductSubscription",
						aBisName,
						Severity.UnRecoverable);
				}
	}
	   
	public static void validateProductSubscription(
		Validator validator,
	   	Utility aUtility,
		Hashtable aProperties,
		ProductSubscription[] aProductSubscriptions,
		BisContext aContext)
		throws
			InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound  		{
	   	  							   	
			   		
	   		ProductSubscriptionManager aProductSubscriptionManager = new ProductSubscriptionManager(aUtility, aProductSubscriptions);
	   			
		   	ArrayList aList = aProductSubscriptionManager.getComponents(false);		   		   								   		

			int index = 0;	   		
	   		while (index < aList.size())
	   		{	   			
	   			validator.validate(aContext, new ProductSubscriptionValidation((ProductSubscription)aList.get(index)));			   			   				
	   			index++; 				
		   	}	
	}		
	
	public static void validateProductSubscriptionSeqOpt(
		  Utility aUtility,
		  ProductSubscriptionSeqOpt aProductSubscriptions,
		  String aBisName)
		  throws
			  BusinessViolation,
			  ObjectNotFound,
			  InvalidData,
			  AccessDenied,
			  SystemFailure,
			  DataNotFound,
			  NotImplemented {

				
			if ((aProductSubscriptions == null) 
				|| OptHelper.isProductSubscriptionSeqOptEmpty(aProductSubscriptions))  
				
				aUtility.throwException(
					ExceptionCode.ERR_RM_MISSING_PRODUCT_SUBSCRIPTION,
					"Missing required data: ProductSubscription",
					aBisName,
					Severity.UnRecoverable);        	
	}
	
}
