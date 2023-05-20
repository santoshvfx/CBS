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
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.rm.ModifyFacilityInfoReturn;
import com.sbc.eia.idl.rm.PingReturn;
import com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification3Return;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification3Return;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn;
import com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn;
import com.sbc.eia.idl.rm.SelfTestReturn;
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder3Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn;
import com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn;
import com.sbc.eia.idl.rm.ValidateFacility3Return;
import com.sbc.eia.idl.rm.ValidateFacilityForProvisioningReturn;
import com.sbc.eia.idl.rm.ValidateFacilityReturn;
import com.sbc.eia.idl.rm.publishValidateFacilityForProvisioningNotificationReturn;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.Time;

import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.rm.ValidateFacility2Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return;
import com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return;
import com.sbc.eia.idl.rm.ModifyFacilityInfo2Return;
/**
 * Local interface for Enterprise Bean: Rm
 */
public interface RmLocal extends javax.ejb.EJBLocalObject {
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
	RetrieveResourcesForServiceReturn retrieveResourcesForService(
		BisContext aContext,
		ObjectKey aServiceHandle,
		String[] aResourceRoleNames)
		throws
			BusinessViolation,
			NotImplemented,
			DataNotFound,
			java.rmi.RemoteException,
			InvalidData,
			AccessDenied,
			ObjectNotFound,
			SystemFailure;
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
	RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
		BisContext aContext,
		String aResourceHandle,
		ObjectKey[] aServiceTypeHandles)
		throws
			BusinessViolation,
			NotImplemented,
			DataNotFound,
			java.rmi.RemoteException,
			InvalidData,
			AccessDenied,
			ObjectNotFound,
			SystemFailure;
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
	RetrieveServiceProvidersForServiceReturn retrieveServiceProvidersForService(
		BisContext aContext,
		ObjectKey aSerivceHandle,
		ObjectKey[] aServiceTypeHandles)
		throws
			BusinessViolation,
			NotImplemented,
			DataNotFound,
			java.rmi.RemoteException,
			InvalidData,
			AccessDenied,
			ObjectNotFound,
			SystemFailure;

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
	com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn createFacilityAssignment(
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
	PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(
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
	SendFacilityAssignmentOrderReturn sendFacilityAssignmentOrder(
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
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;

	/**
	 *   publishFacilityAssignmentOrderNotification
	 */
	
	PublishFacilityAssignmentOrderNotificationReturn publishFacilityAssignmentOrderNotification();

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

	SendTNAssignmentOrderReturn sendTNAssignmentOrder(
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
	 *  PublishTNAssignmentOrderNotification
	 */
	
	PublishTNAssignmentOrderNotificationReturn publishTNAssignmentOrderNotification();

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
	SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers)
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
	 *  publishTNPortingNotification
	 */
	
	PublishTNPortingNotificationReturn publishTNPortingNotification();

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
	ValidateFacilityReturn validateFacility(
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
	 *  publishValidateFacilityNotification
	 */

	PublishValidateFacilityNotificationReturn publishValidateFacilityNotification();

	/**
	 *  modifyFacilityInfo
	 */

	ModifyFacilityInfoReturn modifyFacilityInfo(); 
	
	/**
	 *  selfTest
	 */
	
	SelfTestReturn selfTest(BisContext aContext)
	throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions,
			java.rmi.RemoteException;
	
	/**
	 *  ping
	 */
	
	PingReturn ping(BisContext aContext)
	throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
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
		DataNotFound;
	public PublishValidateFacilityNotification2Return publishValidateFacilityNotification2();
	public PublishFacilityAssignmentOrderNotification2Return publishFacilityAssignmentOrderNotification2();
	public ModifyFacilityInfo2Return modifyFacilityInfo2();
	public PublishValidateFacilityNotification3Return publishValidateFacilityNotification3();
		public publishValidateFacilityForProvisioningNotificationReturn publishValidateFacilityForProvisioningNotification();
			
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
					throws AccessDenied,
					BusinessViolation,
					SystemFailure,
					NotImplemented,
					ObjectNotFound,
					DataNotFound,
					InvalidData;
			public PublishFacilityAssignmentOrderNotification3Return publishFacilityAssignmentOrderNotification3();
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
			 * @return
			 * @throws BusinessViolation
			 * @throws ObjectNotFound
			 * @throws InvalidData
			 * @throws AccessDenied
			 * @throws SystemFailure
			 * @throws DataNotFound
			 * @throws NotImplemented
			 */
			public ValidateFacilityForProvisioningReturn validateFacilityForProvisioning(BisContext aContext, Location aServiceLocation, StringOpt aRelatedCircuitID, StringOpt aWorkingTelephoneNumber, ShortOpt aMaxPairsToAnalyze, StringOpt aSOACServiceOrderNumber, EiaDateOpt aOrderDueDate, ObjectType[] aNtis, OrderAction2Opt aOrderAction, ObjectPropertySeqOpt aProperties) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented;
			public ValidateFacility3Return validateFacility3(BisContext aContext, Location aServiceLocation, StringOpt aRelatedCircuitID, StringOpt aWorkingTelephoneNumber, ShortOpt aMaxPairsToAnalyze, StringOpt aSOACServiceOrderNumber, EiaDateOpt aOrderDueDate, ObjectType[] aNtis, OrderAction2Opt aOrderAction, ObjectPropertySeqOpt aProperties) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented;
			
}
