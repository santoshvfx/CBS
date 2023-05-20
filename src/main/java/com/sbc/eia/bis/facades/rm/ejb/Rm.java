// $Id: Rm.java,v 1.54 2010/03/22 22:59:51 eb231v Exp $

package com.sbc.eia.bis.facades.rm.ejb;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.ActivateFiberServiceTNPortReturn;
import com.sbc.eia.idl.rm.AddFiberServiceTNPort2Return;
import com.sbc.eia.idl.rm.AddFiberServiceTNPortReturn;
import com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn;
import com.sbc.eia.idl.rm.InquireFiberServiceTNPortStatusReturn;
import com.sbc.eia.idl.rm.ModifyFacilityInfoReturn;
import com.sbc.eia.idl.rm.PingReturn;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn;
import com.sbc.eia.idl.rm.SelfTestReturn;
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder4Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn;
import com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.SubmitFiberServiceTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.SubmitFiberServiceTNAssignmentOrder2Return;
import com.sbc.eia.idl.rm.ValidateFacilityReturn;
import com.sbc.eia.idl.rm.publishValidateFacilityForProvisioningNotification2Return;
import com.sbc.eia.idl.rm_ls_types.AddFiberServiceTNPortTelephoneNumberRequest;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.Time;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.rm.ValidateFacility2Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return;
import com.sbc.eia.idl.rm.ModifyFacilityInfo2Return;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm.ValidateFacilityForProvisioningReturn;
import com.sbc.eia.idl.rm.ValidateFacility3Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder3Return;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification3Return;
import com.sbc.eia.idl.rm.publishValidateFacilityForProvisioningNotificationReturn;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification3Return;
/**
 * This is an Enterprise Java Bean Remote Interface
 */
public interface Rm extends javax.ejb.EJBObject {
	/**
	 * 
	 * @return com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aServiceHandle com.sbc.eia.idl.types.ObjectKey
	 * @param aResourceRoleNames java.lang.String []
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 */
	com
		.sbc
		.eia
		.idl
		.rm
		.RetrieveResourcesForServiceReturn retrieveResourcesForService(
			com.sbc.eia.idl.bis_types.BisContext aContext,
			com.sbc.eia.idl.types.ObjectKey aServiceHandle,
			java.lang.String[] aResourceRoleNames)
		throws
			com.sbc.eia.idl.bis_types.BusinessViolation,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.DataNotFound,
			java.rmi.RemoteException,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.SystemFailure;
	/**
	 * 
	 * @return com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aResourceHandle java.lang.String
	 * @param aServiceTypeHandles com.sbc.eia.idl.types.ObjectKey []
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 */
	com
		.sbc
		.eia
		.idl
		.rm
		.RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
			com.sbc.eia.idl.bis_types.BisContext aContext,
			java.lang.String aResourceHandle,
			com.sbc.eia.idl.types.ObjectKey[] aServiceTypeHandles)
		throws
			com.sbc.eia.idl.bis_types.BusinessViolation,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.DataNotFound,
			java.rmi.RemoteException,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.SystemFailure;
	/**
	 * 
	 * @return com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aSerivceHandle com.sbc.eia.idl.types.ObjectKey
	 * @param aServiceTypeHandles com.sbc.eia.idl.types.ObjectKey []
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 * @exception String The exception description.
	 */
	com
		.sbc
		.eia
		.idl
		.rm
		.RetrieveServiceProvidersForServiceReturn retrieveServiceProvidersForService(
			com.sbc.eia.idl.bis_types.BisContext aContext,
			com.sbc.eia.idl.types.ObjectKey aSerivceHandle,
			com.sbc.eia.idl.types.ObjectKey[] aServiceTypeHandles)
		throws
			com.sbc.eia.idl.bis_types.BusinessViolation,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.DataNotFound,
			java.rmi.RemoteException,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.SystemFailure;

	   /**
		* CreateFacilityAssignment
		* Invoke XNG: The facility will be created.
		* Used to create the cross connects
		* @param aContext The client's calling context.
		* Required tags are
		* BisContextProperty.SERVICECENTER
		* BisContextProperty.APPLICATION
		* BisContextProperty.BUSINESSUNIT
		* BisContextProperty.LOGGINGINFORMATION
		* @param aCustomerTransportId Circuit Id.
		* @param aBillingAccountNumber Customer account.
		* @param aServiceLocation A service location.
		* @param aMaintenanceFlag Maintenance Flag
		* @param aDueDate Order Due Date.
		* @param aOrderAction OrderAction.
		* @param aTaperCode FST Taper Code for FTTP and SAI Taper Code for FTTN.
		* @param aNetworkType FTTP/FTTN/RGPON
		* @param aNetworkTypeChoice Either FTTP, FTTN
		* @param aProperties ObjectPropertySeqOpt
		*/
	public CreateFacilityAssignmentReturn createFacilityAssignment(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		Location aServiceLocation,
		BooleanOpt aMaintenanceFlag,
		EiaDate aDueDate,
		OrderAction aOrderAction,
		StringOpt aTaperCode,
		String aNetworkType,
		NetworkType aNetworkTypeChoice,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;
	/**
		*  PublishAutoDiscoveryResults
		*
		* @param aContext The client's calling context. 
		* Required tags are
		* BisContextProperty.SERVICECENTER
		* BisContextProperty.APPLICATION
		* BisContextProperty.BUSINESSUNIT
		* BisContextProperty.LOGGINGINFORMATION
		* @param aCustomerTransportId Circuit Id.
		* @param aBillingAccountNumber The customer's Billing Account Number.
		* @param aServiceAddress Address.
		* @param aProductSubscriptions A List of Components
		* @param aTelephoneNumber TN.
		* @param aAssignedProductId product id.
		* @param aOrderAction OrderAction.
		* @param aProperties ObjectPropertySeqOpt    
		*/
	public PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;

	/**
	* PublishRGActivation
	* @param aContext The client's calling context.
	* Required tags are
	* BisContextProperty.SERVICECENTER
	* BisContextProperty.APPLICATION
	* BisContextProperty.BUSINESSUNIT
	* BisContextProperty.LOGGINGINFORMATION
	* @param aCustomerTransportId Circuit Id.
	* @param aBillingAccountNumber The customer's Billing Account Number.
	* @param aDSLAM DSLAM transport.
	* @param aRG RG.
	* @param aActivationTime Activation time.
	* @param aOrderAction OrderAction.
	* @param aProperties ObjectPropertySeqOpt    
	* @exception InvalidData: An input parameter contained invalid data.
	* @exception AccessDenied: Access to the specified domain object or information is not allowed.
	* @exception BusinessViolation: The attempted action violates a business rule.
	* @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	* @exception NotImplemented: The method has not been implemented.
	* @exception ObjectNotFound: The desired domain object could not be found.
	* @exception DataNotFound: No data found.
	*/

	public PublishRGActivationReturn publishRGActivation(
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;

	/**
	*  sendTNAssignmentOrder 
	*  LS order for provisioning CVOIP TN assignment(from OMS to CVOIP SOAC via RMBIS)
	*
	* @param aContext The client's calling context. Required tags are
	*           BisContextProperty.CUSTOMERNAME
	*           BisContextProperty.APPLICATION
	*           BisContextProperty.BUSINESSUNIT
	*           BisContextProperty.LOGGINGINFORMATION
	* @param aSOACServiceOrderNumber SOAC Service Order Number 
	* @param aSOACServiceOrderCorrectionSuffix SOAC Service order Correction Suffix 
	* @param aOrderNumber OMS Order ID
	* @param aOrderActionType Action Type (see OrderActionTypeValues)
	* @param aSubActionType Sub-action Type (see SubActionTypeValues)
	* @param aServiceLocation SAG validated Service Address, state info.
	* @param aOriginalDueDate Order Due Date
	* @param aSubsequentDueDate Subsequent Due Date 
	* @param aCompletionIndicator
	* @param aApplicationDate Application Date 
	* @param aResendFlag Indicates resend order
	* @param aWireCenter 
	* @param aRateCenter rateCenter state will be 
	*        used to get the wire center and SOAC entity.
	* @param aTelephoneNumbers Telephone numbers related to this order
	*/

	public SendTNAssignmentOrderReturn sendTNAssignmentOrder(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aOrderNumber,
		String aOrderActionType,
		StringOpt aSubActionType,
		BooleanOpt aCompletionIndicator,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		BooleanOpt aResendFlag,
		StringOpt aWireCenter,
		StringOpt aRateCenter,
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;
	/**
	*  sendActivateTNPortingSubscriptionMsg
	*
	* @param aContext The client's calling context. Required tags are
	* BisContextProperty.SERVICECENTER
	* BisContextProperty.APPLICATION -- Initiating System
	* BisContextProperty.CUSTOMERNAME -- Initiating user 
	* BisContextProperty.BUSINESSUNIT 
	* BisContextProperty.LOGGINGINFORMATION
	* @param aContext
	* @param aLocalServiceProviderId
	* @param aTelephoneNumbers
	* @param aSOACServiceOrderNumber
	* @param aSOACServiceOrderCorrectionSuffix
	*/
	public SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers)
		throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData,
			java.rmi.RemoteException;
	/**
	*  sendFacilityAssignmentOrder
	*  LS order for provisioning the facility assignment (from OMS to Telco SOAC via RMBIS)
	*
	* @param aContext The client's calling context. Required tags are
	*           BisContextProperty.CUSTOMERNAME
	*           BisContextProperty.APPLICATION
	*           BisContextProperty.BUSINESSUNIT
	*           BisContextProperty.LOGGINGINFORMATION
	* @param aSOACServiceOrderNumber SOAC Service Order Number 
	* @param aSOACServiceOrderCorrectionSuffix SOAC Service order Correction Suffix 
	* @param aNetworkType Network Type (FTTP/FTTN/RGPON) (see NetworkTypeValues)
	* @param aOrderActionId OMS Order Action ID
	* @param aOrderNumber OMS Order ID
	* @param aOrderActionType Action Type (see OrderActionTypeValues)
	* @param aCompletionIndicator true/false
	* @param aSubActionType Sub-action Type (see SubActionTypeValues)
	* @param aCircuitId LS Circuit ID
	* @param aServiceLocation SAG validated Service Address
	* @param aOriginalDueDate Order Due Date
	* @param aSubsequentDueDate Subsequent Due Date 
	* @param aContactTelephoneNumber Rep Contact Number 
	* @param aApplicationDate Application Date 
	* @param aTDMTelphoneNumber TDM TN
	* @param aRelatedServiceOrderNumber Related Order Number
	* @param aAdditionalLineFlag Order for additional line?
	* @param aLineShareDisconnectFlag Disconnect lineshare? 
	* @param aClassOfService Class of Service (see ClassOfServiceValues)
	* @param aResendFlag Indicates resend order.
	*/
	public SendFacilityAssignmentOrderReturn sendFacilityAssignmentOrder(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aNetworkType,
		String aOrderActionId,
		String aOrderNumber,
		String aOrderActionType,
		BooleanOpt aCompletionIndicator,
		StringOpt aSubActionType,
		String aCircuitId,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		StringOpt aTDMTelphoneNumber,
		StringOpt aRelatedServiceOrderNumber,
		BooleanOpt aLineShareDisconnectFlag,
		String aClassOfService,
		BooleanOpt aResendFlag,
		ObjectPropertySeqOpt aProperties)
		throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData,
			java.rmi.RemoteException;

    /**
	 *  validateFacility 
	 *
	 *  Interface between OMS/AMSS/FIRST and RM to retrieve and analyze facility availability 
	 *      information applicable to LS ordering.
	 *
	 * @param aContext The client's calling context. Required tags are
	 *           BisContextProperty.CUSTOMERNAME
	 *           BisContextProperty.APPLICATION
	 *           BisContextProperty.BUSINESSUNIT
	 *           BisContextProperty.LOGGINGINFORMATION
	 * @param aServiceLocation
	 * @param aRelatedCircuitID
	 * @param aWorkingTelephoneNumber 
	 * @param aMaxPairsToAnalyze
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aProperties
	 */
	public ValidateFacilityReturn validateFacility(
		BisContext aContext,
		Location aServiceLocation,
		StringOpt aRelatedCircuitID,
		StringOpt aWorkingTelephoneNumber,
		ShortOpt aMaxPairsToAnalyze,
	    StringOpt aSOACServiceOrderNumber,
	    StringOpt aSOACServiceOrderCorrectionSuffix,
        EiaDateOpt aUverseOrderDueDate,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;
	
	/**
	 *  PublishTNAssignmentOrderNotification
	 */

	public PublishTNAssignmentOrderNotificationReturn publishTNAssignmentOrderNotification()
		throws java.rmi.RemoteException;
	/**
	 *  publishTNPortingNotification
	 */

	public PublishTNPortingNotificationReturn publishTNPortingNotification()
		throws java.rmi.RemoteException;

	/**
	 *  publishFacilityAssignmentOrderNotification
	 */

	public PublishFacilityAssignmentOrderNotificationReturn publishFacilityAssignmentOrderNotification()
		throws java.rmi.RemoteException;
		
	/**
	 *  publishValidateFacilityNotification
	 */
	
	public PublishValidateFacilityNotificationReturn publishValidateFacilityNotification()
		throws java.rmi.RemoteException;

	/**
	 *  modifyFacilityInfo
	 */
	
	public ModifyFacilityInfoReturn modifyFacilityInfo() 
		throws java.rmi.RemoteException;	
	
	/**
	 *  selfTest
	 */
	
	public SelfTestReturn selfTest(BisContext aContext)
	    throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData,
			MultipleExceptions,
			java.rmi.RemoteException;
	    
	/**
	 *  ping
	 */
	
	public PingReturn ping(BisContext aContext)
	    throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData,
			MultipleExceptions,
			java.rmi.RemoteException;
	public ValidateFacility2Return validateFacility2(
		BisContext aContext,
		Location aServiceLocation,
		StringOpt aRelatedCircuitID,
		StringOpt aWorkingTelephoneNumber,
		ShortOpt aMaxPairsToAnalyze,
		StringOpt aSOACServiceOrderNumber,
		StringOpt aSOACServiceOrderCorrectionSuffix,
		EiaDateOpt aUverseOrderDueDate,
		ObjectType[] aNtis,
		StringOpt aOrderActionType,
		StringOpt aSubActionType,
		ObjectPropertySeqOpt aProperties)
		throws InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound,
		java.rmi.RemoteException;

	/**
	 *  publishValidateFacilityNotification2
	 */
	public PublishValidateFacilityNotification2Return publishValidateFacilityNotification2()
		throws java.rmi.RemoteException;
	
	/**
	 *  publishFacilityAssignmentOrderNotification2
	 */
	public PublishFacilityAssignmentOrderNotification2Return publishFacilityAssignmentOrderNotification2()
		throws java.rmi.RemoteException;
	
	/**
	 *  modifyFacilityInfo2
	 */
	public ModifyFacilityInfo2Return modifyFacilityInfo2()
		throws java.rmi.RemoteException;
	

	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aNetworkType
	 * @param aOldNetworkType
	 * @param aOrderActionId
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aCompletionIndicator
	 * @param aSubActionType
	 * @param aCircuitId
	 * @param aSecondaryCircuitId
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aRelatedCircuitID
	 * @param aSecondaryRelatedCircuitID
	 * @param aRelatedServiceOrderNumber
	 * @param aLineShareDisconnectFlag
	 * @param aClassOfService
	 * @param aResendFlag
	 * @param aBillingAccountNumber
	 * @param aProperties
	 * @return SendFacilityAssignmentOrder2Return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws java.rmi.RemoteException
	 */
	public SendFacilityAssignmentOrder2Return sendFacilityAssignmentOrder2(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aNetworkType,
		StringOpt aOldNetworkType,
		String aOrderActionId,
		String aOrderNumber,
		String aOrderActionType,
		BooleanOpt aCompletionIndicator,
		StringOpt aSubActionType,
		String aCircuitId,
		StringOpt aSecondaryCircuitId,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		StringOpt aRelatedCircuitID,
		StringOpt aSecondaryRelatedCircuitID,
		StringOpt aRelatedServiceOrderNumber,
		BooleanOpt aLineShareDisconnectFlag,
		String aClassOfService,
		BooleanOpt aResendFlag,
		CompositeObjectKey aBillingAccountNumber,
		ObjectPropertySeqOpt aProperties)
		throws AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound,
		InvalidData,
		java.rmi.RemoteException;
	
	/**
	 * @param aContext
	 * @param aServiceLocation
	 * @param aRelatedCircuitID
	 * @param aWorkingTelephoneNumber
	 * @param aMaxPairsToAnalyze
	 * @param aSOACServiceOrderNumber
	 * @param aOrderDueDate
	 * @param aNtis
	 * @param aOrderAction
	 * @param aProperties
	 * @return ValidateFacility3Return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws java.rmi.RemoteException
	 */
	public ValidateFacility3Return validateFacility3(BisContext aContext, Location aServiceLocation, StringOpt aRelatedCircuitID, StringOpt aWorkingTelephoneNumber, ShortOpt aMaxPairsToAnalyze, StringOpt aSOACServiceOrderNumber, EiaDateOpt aOrderDueDate, ObjectType[] aNtis, OrderAction2Opt aOrderAction, ObjectPropertySeqOpt aProperties) throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound, java.rmi.RemoteException;
	
	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aNetworkType
	 * @param aOldNetworkType
	 * @param aOrderActionId
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aCompletionIndicator
	 * @param aSubActionType
	 * @param aCircuitId
	 * @param aSecondaryCircuitId
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aRelatedCircuitID
	 * @param aSecondaryRelatedCircuitID
	 * @param aRelatedServiceOrderNumber
	 * @param aLineShareDisconnectFlag
	 * @param aClassOfService
	 * @param aResendFlag
	 * @param aBillingAccountNumber
	 * @param aInterceptRedirectIndicator
	 * @param aDryloopRelatedCircuitId
	 * @param aDSLDisconnectTelephoneNumber
	 * @param aExceptionRoutingIndicator
	 * @param aProperties
	 * @return SendFacilityAssignmentOrder3Return
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public SendFacilityAssignmentOrder3Return sendFacilityAssignmentOrder3(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aNetworkType, 
			StringOpt aOldNetworkType, 
			String aOrderActionId, 
			String aOrderNumber, 
			String aOrderActionType, 
			BooleanOpt aCompletionIndicator, 
			StringOpt aSubActionType, 
			String aCircuitId, 
			StringOpt aSecondaryCircuitId, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			StringOpt aRelatedCircuitID, 
			StringOpt aSecondaryRelatedCircuitID, 
			StringOpt aRelatedServiceOrderNumber, 
			BooleanOpt aLineShareDisconnectFlag, 
			String aClassOfService, 
			BooleanOpt aResendFlag, 
			CompositeObjectKey aBillingAccountNumber, 
			BooleanOpt aInterceptRedirectIndicator, 
			StringOpt aDryloopRelatedCircuitId, 
			StringOpt aDSLDisconnectTelephoneNumber, 
			StringOpt aExceptionRoutingIndicator, 
			ObjectPropertySeqOpt aProperties) 
	throws  BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented, 
			java.rmi.RemoteException;
	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public PublishValidateFacilityNotification3Return publishValidateFacilityNotification3() 
	throws java.rmi.RemoteException;
	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public publishValidateFacilityForProvisioningNotificationReturn publishValidateFacilityForProvisioningNotification() 
	throws java.rmi.RemoteException;
	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public publishValidateFacilityForProvisioningNotification2Return publishValidateFacilityForProvisioningNotification2() 
	throws java.rmi.RemoteException;	
	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public PublishFacilityAssignmentOrderNotification3Return publishFacilityAssignmentOrderNotification3() 
	throws java.rmi.RemoteException;
	/**
	 * @param aContext
	 * @param aServiceLocation
	 * @param aRelatedCircuitID
	 * @param aWorkingTelephoneNumber
	 * @param aMaxPairsToAnalyze
	 * @param aSOACServiceOrderNumber
	 * @param aOrderDueDate
	 * @param aNtis
	 * @param aOrderAction
	 * @param aProperties
	 * @return ValidateFacilityForProvisioningReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public ValidateFacilityForProvisioningReturn validateFacilityForProvisioning(
			BisContext aContext, 
			Location aServiceLocation, 
			StringOpt aRelatedCircuitID, 
			StringOpt aWorkingTelephoneNumber, 
			ShortOpt aMaxPairsToAnalyze, 
			StringOpt aSOACServiceOrderNumber, 
			EiaDateOpt aOrderDueDate, 
			ObjectType[] aNtis, 
			OrderAction2Opt aOrderAction, 
			ObjectPropertySeqOpt aProperties) 
	throws  BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented, 
			java.rmi.RemoteException;
	
	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aLocalServiceProviderId
	 * @param aTelephoneNumbers
	 * @return ActivateFiberServiceTNPortReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public ActivateFiberServiceTNPortReturn activateFiberServiceTNPort(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aLocalServiceProviderId, 
			String[] aTelephoneNumbers) 
	throws 	BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented, 
			java.rmi.RemoteException;
	
	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aSubActionType
	 * @param aCompletionIndicator
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aResendFlag
	 * @param aWireCenter
	 * @param aRateCenter
	 * @param aTelephoneNumberOrderPairs
	 * @param aProperties
	 * @return SubmitFiberServiceTNAssignmentOrderReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public SubmitFiberServiceTNAssignmentOrderReturn submitFiberServiceTNAssignmentOrder(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aOrderNumber, 
			String aOrderActionType, 
			StringOpt aSubActionType, 
			BooleanOpt aCompletionIndicator, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			BooleanOpt aResendFlag, 
			StringOpt aWireCenter, 
			StringOpt aRateCenter,
			TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs, 
			ObjectPropertySeqOpt aProperties) 
	throws  BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented, 
			java.rmi.RemoteException;
	/**
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOrderActionType
	 * @param aSubActionType
	 * @param aCompletionIndicator
	 * @param aServiceLocation
	 * @param aOriginalDueDate
	 * @param aSubsequentDueDate
	 * @param aApplicationDate
	 * @param aResendFlag
	 * @param aWireCenter
	 * @param aRateCenter
	 * @param aTelephoneNumberOrderPairs
	 * @param aProperties
	 * @return SubmitFiberServiceTNAssignmentOrder2Return
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public SubmitFiberServiceTNAssignmentOrder2Return submitFiberServiceTNAssignmentOrder2(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aOrderNumber, 
			String aOrderActionType, 
			StringOpt aSubActionType, 
			BooleanOpt aCompletionIndicator, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			BooleanOpt aResendFlag, 
			StringOpt aWireCenter, 
			StringOpt aRateCenter, 
			BooleanOpt aSimplePortIndicator,
			TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs, 
			ObjectPropertySeqOpt aProperties) 
	throws  BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented, 
			java.rmi.RemoteException;
	/**
	 * 
	 * @param aContext
	 * @param aState
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOldServiceProviderId
	 * @param aNewServiceProviderId
	 * @param aOrderDueDate
	 * @param aTelephoneNumbers
	 * @return AddFiberServiceTNPortReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public AddFiberServiceTNPortReturn addFiberServiceTNPort(
			BisContext aContext, 
			String aState, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			StringOpt aOrderNumber, 
			String aOldServiceProviderId, 
			String aNewServiceProviderId, 
			EiaDate aOrderDueDate, 
			String[] aTelephoneNumbers) 
		throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented,
			java.rmi.RemoteException;
	/**
	 * 
	 * @param aContext
	 * @param aState
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aOrderNumber
	 * @param aOldServiceProviderId
	 * @param aNewServiceProviderId
	 * @param aOrderDueDate
	 * @param aTelephoneNumbers
	 * @return AddFiberServiceTNPort2Return
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	public AddFiberServiceTNPort2Return addFiberServiceTNPort2(
			BisContext aContext, 
			String aState, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			StringOpt aOrderNumber, 
			StringOpt aOldServiceProviderId, 
			StringOpt aNewServiceProviderId, 
			EiaDate aOrderDueDate, 
			AddFiberServiceTNPortTelephoneNumberRequest[] aTelephoneNumbers) 
		throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented,
			java.rmi.RemoteException;
	
	/**
	 * 
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aOldServiceProviderId
	 * @param aNewServiceProviderId
	 * @param aOrderDueDateTime
	 * @param aAppointmentDateTime
	 * @param aTelephoneNumbers
	 * @return InquireFiberServiceTNPortStatusReturn
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * @throws java.rmi.RemoteException
	 */
	
	public InquireFiberServiceTNPortStatusReturn inquireFiberServiceTNPortStatus (
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aOldServiceProviderId, 
			String aNewServiceProviderId, 
			Time aOrderDueDateTime, 
			Time aAppointmentDateTime, 
			String[] aTelephoneNumbers) 
	throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented,
			java.rmi.RemoteException;
	
	/**
	 *  
	 * @param BisContext aContext,
	 * @param String aSOACServiceOrderNumber,
	 * @param String aSOACServiceOrderCorrectionSuffix,
	 * @param String aNetworkType,
	 * @param StringOpt aOldNetworkType,
	 * @param String aOrderActionId,
	 * @param String aOrderNumber,
	 * @param String aOrderActionType,
	 * @param BooleanOpt aCompletionIndicator,
	 * @param StringOpt aSubActionType,
	 * @param String aCircuitId,
	 * @param StringOpt aSecondaryCircuitId,
	 * @param Location aServiceLocation,
	 * @param EiaDate aOriginalDueDate,
	 * @param EiaDateOpt aSubsequentDueDate,
	 * @param EiaDate aApplicationDate,
	 * @param StringOpt aRelatedCircuitID, 
	 * @param StringOpt aSecondaryRelatedCircuitID,
	 * @param StringOpt aRelatedServiceOrderNumber,
	 * @param BooleanOpt aLineShareDisconnectFlag,
	 * @param String aClassOfService,
	 * @param BooleanOpt aResendFlag,
	 * @param CompositeObjectKey aBillingAccountNumber,
	 * @param BooleanOpt aInterceptRedirectIndicator,
	 * @param StringOpt aDryloopRelatedCircuitId,
	 * @param StringOpt aSecondaryDryloopRelatedCircuitId,
	 * @param StringSeqOpt aDSLDisconnectCircuitIds,
	 * @param StringOpt aExceptionRoutingIndicator,
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SendFacilityAssignmentOrder4Return
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     * @exception java.rmi.RemoteException
	 */
	
	public SendFacilityAssignmentOrder4Return sendFacilityAssignmentOrder4(
			BisContext aContext,
			String aSOACServiceOrderNumber,
		    String aSOACServiceOrderCorrectionSuffix,
		    String aNetworkType,
		    StringOpt aOldNetworkType,
		    String aOrderActionId,
		    String aOrderNumber,
		    String aOrderActionType,
		    BooleanOpt aCompletionIndicator,
		    StringOpt aSubActionType,
		    String aCircuitId,
		    StringOpt aSecondaryCircuitId,
		    Location aServiceLocation,
		    EiaDate aOriginalDueDate,
		    EiaDateOpt aSubsequentDueDate,
		    EiaDate aApplicationDate,
		    StringOpt aRelatedCircuitID, 
		    StringOpt aSecondaryRelatedCircuitID,
		    StringOpt aRelatedServiceOrderNumber,
		    BooleanOpt aLineShareDisconnectFlag,
		    String aClassOfService,
		    BooleanOpt aResendFlag,
		    CompositeObjectKey aBillingAccountNumber,
		    BooleanOpt aInterceptRedirectIndicator,
		    StringOpt aDryloopRelatedCircuitId,
		    StringOpt aSecondaryDryloopRelatedCircuitId,
		    StringSeqOpt aDSLDisconnectCircuitIds,
		    StringOpt aExceptionRoutingIndicator,
		    ObjectPropertySeqOpt aProperties) 
	throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented,
			java.rmi.RemoteException;	
}