//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sbc.eia.idl.rm;

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
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._createFacilityAssignmentResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfo2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfo2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._pingRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._pingRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._pingResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._pingResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishAutoDiscoveryResultsResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotification2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotification2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNAssignmentOrderNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNAssignmentOrderNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNPortingNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNPortingNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotification2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishValidateFacilityNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveResourcesForServiceRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveResourcesForServiceRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveResourcesForServiceResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveResourcesForServiceResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForResourceRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForResourceRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForResourceResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForResourceResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._retrieveServiceProvidersForServiceResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._selfTestRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._selfTestRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._selfTestResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._selfTestResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._sendActivateTNPortingSubscriptionMsgResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder2Request;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder2RequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrder2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrderRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrderRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrderResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._sendFacilityAssignmentOrderResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2Request;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2RequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2Response;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacility2ResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.*;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.*;
import com.sbc.mif.layer.VObject;
import com.sbc.mif.layer.vicunalite.VMObject;
import com.sbc.mif.layer.vicunalite.VMUtil;
import com.sbc.mif.midlet.v1_0.MidletRemoteException;
import com.sbc.mif.midlet.v1_0.MidletRemoteExceptionMsg;
import com.sbc.mif.vidl.midlet.AbsMSGBuilder;
import com.sbc.mif.vidl.midlet.MSGUnBuilder;
import com.sbc.mif.vidl.midlet.MidletRuntimeException;
import com.sbc.ula.Log;
import com.sbc.vicunalite.api.MMarshalObject;

public class RmFacadeSOABuilder extends AbsMSGBuilder implements RmFacadeOperations {
    public static final String _retrieveResourcesForService = "com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveResourcesForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, String[])";
    public static final String _retrieveServiceProvidersForResource = "com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.ObjectKey[])";
    public static final String _retrieveServiceProvidersForService = "com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, com.sbc.eia.idl.types.ObjectKey[])";
    public static final String _sendFacilityAssignmentOrder = "com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _sendFacilityAssignmentOrder2 = "com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder2(com.sbc.eia.idl.bis_types.BisContext, String, String, String, com.sbc.eia.idl.types.StringOpt, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishFacilityAssignmentOrderNotification = "com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification()";
    public static final String _publishFacilityAssignmentOrderNotification2 = "com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification2()";
    public static final String _createFacilityAssignment = "com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn com.sbc.eia.idl.rm.RmFacadePackage.createFacilityAssignment(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.NetworkType, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishAutoDiscoveryResults = "com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn com.sbc.eia.idl.rm.RmFacadePackage.publishAutoDiscoveryResults(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.AddressOpt, com.sbc.eia.idl.sm_ls_types.ProductSubscription[], com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishRGActivation = "com.sbc.eia.idl.rm.PublishRGActivationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishRGActivation(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt, com.sbc.eia.idl.rm_ls_types.ResidentialGateway, com.sbc.eia.idl.types.Time, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _sendTNAssignmentOrder = "com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendTNAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishTNAssignmentOrderNotification = "com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNAssignmentOrderNotification()";
    public static final String _sendActivateTNPortingSubscriptionMsg = "com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn com.sbc.eia.idl.rm.RmFacadePackage.sendActivateTNPortingSubscriptionMsg(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String[])";
    public static final String _publishTNPortingNotification = "com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNPortingNotification()";
    public static final String _ping = "com.sbc.eia.idl.rm.PingReturn com.sbc.eia.idl.rm.RmFacadePackage.ping(com.sbc.eia.idl.bis_types.BisContext)";
    public static final String _selfTest = "com.sbc.eia.idl.rm.SelfTestReturn com.sbc.eia.idl.rm.RmFacadePackage.selfTest(com.sbc.eia.idl.bis_types.BisContext)";
    public static final String _validateFacility = "com.sbc.eia.idl.rm.ValidateFacilityReturn com.sbc.eia.idl.rm.RmFacadePackage.validateFacility(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishValidateFacilityNotification = "com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification()";
    public static final String _validateFacility2 = "com.sbc.eia.idl.rm.ValidateFacility2Return com.sbc.eia.idl.rm.RmFacadePackage.validateFacility2(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectType[], com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)";
    public static final String _publishValidateFacilityNotification2 = "com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification2()";
    public static final String _modifyFacilityInfo = "com.sbc.eia.idl.rm.ModifyFacilityInfoReturn com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo()";
    public static final String _modifyFacilityInfo2 = "com.sbc.eia.idl.rm.ModifyFacilityInfo2Return com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo2()";

    public static void main(String[] var0) {
        if (var0.length <= 0 || !var0[0].toUpperCase().equals("-Q")) {
            System.err.println("// Transaction Tags for RmFacade");
        }

        System.err.println("com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveResourcesForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, String[])");
        System.err.println("com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.ObjectKey[])");
        System.err.println("com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, com.sbc.eia.idl.types.ObjectKey[])");
        System.err.println("com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder2(com.sbc.eia.idl.bis_types.BisContext, String, String, String, com.sbc.eia.idl.types.StringOpt, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification()");
        System.err.println("com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification2()");
        System.err.println("com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn com.sbc.eia.idl.rm.RmFacadePackage.createFacilityAssignment(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.NetworkType, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn com.sbc.eia.idl.rm.RmFacadePackage.publishAutoDiscoveryResults(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.AddressOpt, com.sbc.eia.idl.sm_ls_types.ProductSubscription[], com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishRGActivationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishRGActivation(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt, com.sbc.eia.idl.rm_ls_types.ResidentialGateway, com.sbc.eia.idl.types.Time, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendTNAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNAssignmentOrderNotification()");
        System.err.println("com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn com.sbc.eia.idl.rm.RmFacadePackage.sendActivateTNPortingSubscriptionMsg(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String[])");
        System.err.println("com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNPortingNotification()");
        System.err.println("com.sbc.eia.idl.rm.PingReturn com.sbc.eia.idl.rm.RmFacadePackage.ping(com.sbc.eia.idl.bis_types.BisContext)");
        System.err.println("com.sbc.eia.idl.rm.SelfTestReturn com.sbc.eia.idl.rm.RmFacadePackage.selfTest(com.sbc.eia.idl.bis_types.BisContext)");
        System.err.println("com.sbc.eia.idl.rm.ValidateFacilityReturn com.sbc.eia.idl.rm.RmFacadePackage.validateFacility(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification()");
        System.err.println("com.sbc.eia.idl.rm.ValidateFacility2Return com.sbc.eia.idl.rm.RmFacadePackage.validateFacility2(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectType[], com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)");
        System.err.println("com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification2()");
        System.err.println("com.sbc.eia.idl.rm.ModifyFacilityInfoReturn com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo()");
        System.err.println("com.sbc.eia.idl.rm.ModifyFacilityInfo2Return com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo2()");
    }

    public RmFacadeSOABuilder(MSGUnBuilder var1, Log var2) {
        super(var1, var2);
    }

    public RetrieveResourcesForServiceReturn retrieveResourcesForService(BisContext var1, ObjectKey var2, String[] var3) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        VObject[] var4 = new VObject[]{VMUtil.newVObject(new _retrieveResourcesForServiceRequestBISMsg(new _retrieveResourcesForServiceRequest(var1, var2, var3)))};

        VObject[] var5;
        try {
            var5 = this.client.call("com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveResourcesForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, String[])", var4, this.log);
        } catch (Throwable var11) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveResourcesForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, String[])", var11);
            throw new MidletRuntimeException(var11);
        }

        int var7 = 0;

        for(int var8 = var5.length; var7 < var8; ++var7) {
            MMarshalObject var6 = ((VMObject)var5[var7]).getObject();

            try {
                _retrieveResourcesForServiceResponse var9 = ((_retrieveResourcesForServiceResponseBISMsg)var6).value;
                switch(var9.discriminator()) {
                    case 0:
                        return var9.aRetrieveResourcesForServiceReturn();
                    case 1:
                        throw var9.aBusinessViolation();
                    case 2:
                        throw var9.aObjectNotFound();
                    case 3:
                        throw var9.aDataNotFound();
                    case 4:
                        throw var9.aInvalidData();
                    case 5:
                        throw var9.aAccessDenied();
                    case 6:
                        throw var9.aSystemFailure();
                    case 7:
                        throw var9.aNotImplemented();
                }
            } catch (ClassCastException var12) {
                if (var6 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var10 = ((MidletRemoteExceptionMsg)var6).value;
                    throw new MidletRuntimeException(var10.msg, var12);
                }

                throw new MidletRuntimeException("No valid response from call (" + var6.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(BisContext var1, String var2, ObjectKey[] var3) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        VObject[] var4 = new VObject[]{VMUtil.newVObject(new _retrieveServiceProvidersForResourceRequestBISMsg(new _retrieveServiceProvidersForResourceRequest(var1, var2, var3)))};

        VObject[] var5;
        try {
            var5 = this.client.call("com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.ObjectKey[])", var4, this.log);
        } catch (Throwable var11) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForResource(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.ObjectKey[])", var11);
            throw new MidletRuntimeException(var11);
        }

        int var7 = 0;

        for(int var8 = var5.length; var7 < var8; ++var7) {
            MMarshalObject var6 = ((VMObject)var5[var7]).getObject();

            try {
                _retrieveServiceProvidersForResourceResponse var9 = ((_retrieveServiceProvidersForResourceResponseBISMsg)var6).value;
                switch(var9.discriminator()) {
                    case 0:
                        return var9.aRetrieveServiceProvidersForResourceReturn();
                    case 1:
                        throw var9.aBusinessViolation();
                    case 2:
                        throw var9.aObjectNotFound();
                    case 3:
                        throw var9.aDataNotFound();
                    case 4:
                        throw var9.aInvalidData();
                    case 5:
                        throw var9.aAccessDenied();
                    case 6:
                        throw var9.aSystemFailure();
                    case 7:
                        throw var9.aNotImplemented();
                }
            } catch (ClassCastException var12) {
                if (var6 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var10 = ((MidletRemoteExceptionMsg)var6).value;
                    throw new MidletRuntimeException(var10.msg, var12);
                }

                throw new MidletRuntimeException("No valid response from call (" + var6.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public RetrieveServiceProvidersForServiceReturn retrieveServiceProvidersForService(BisContext var1, ObjectKey var2, ObjectKey[] var3) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        VObject[] var4 = new VObject[]{VMUtil.newVObject(new _retrieveServiceProvidersForServiceRequestBISMsg(new _retrieveServiceProvidersForServiceRequest(var1, var2, var3)))};

        VObject[] var5;
        try {
            var5 = this.client.call("com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, com.sbc.eia.idl.types.ObjectKey[])", var4, this.log);
        } catch (Throwable var11) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn com.sbc.eia.idl.rm.RmFacadePackage.retrieveServiceProvidersForService(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.ObjectKey, com.sbc.eia.idl.types.ObjectKey[])", var11);
            throw new MidletRuntimeException(var11);
        }

        int var7 = 0;

        for(int var8 = var5.length; var7 < var8; ++var7) {
            MMarshalObject var6 = ((VMObject)var5[var7]).getObject();

            try {
                _retrieveServiceProvidersForServiceResponse var9 = ((_retrieveServiceProvidersForServiceResponseBISMsg)var6).value;
                switch(var9.discriminator()) {
                    case 0:
                        return var9.aRetrieveServiceProvidersForServiceReturn();
                    case 1:
                        throw var9.aBusinessViolation();
                    case 2:
                        throw var9.aObjectNotFound();
                    case 3:
                        throw var9.aDataNotFound();
                    case 4:
                        throw var9.aInvalidData();
                    case 5:
                        throw var9.aAccessDenied();
                    case 6:
                        throw var9.aSystemFailure();
                    case 7:
                        throw var9.aNotImplemented();
                }
            } catch (ClassCastException var12) {
                if (var6 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var10 = ((MidletRemoteExceptionMsg)var6).value;
                    throw new MidletRuntimeException(var10.msg, var12);
                }

                throw new MidletRuntimeException("No valid response from call (" + var6.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public SendFacilityAssignmentOrderReturn sendFacilityAssignmentOrder(BisContext var1, String var2, String var3, String var4, String var5, String var6, String var7, BooleanOpt var8, StringOpt var9, String var10, Location var11, EiaDate var12, EiaDateOpt var13, EiaDate var14, StringOpt var15, StringOpt var16, BooleanOpt var17, String var18, BooleanOpt var19, ObjectPropertySeqOpt var20) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var21 = new VObject[]{VMUtil.newVObject(new _sendFacilityAssignmentOrderRequestBISMsg(new _sendFacilityAssignmentOrderRequest(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20)))};

        VObject[] var22;
        try {
            var22 = this.client.call("com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var21, this.log);
        } catch (Throwable var28) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var28);
            throw new MidletRuntimeException(var28);
        }

        int var24 = 0;

        for(int var25 = var22.length; var24 < var25; ++var24) {
            MMarshalObject var23 = ((VMObject)var22[var24]).getObject();

            try {
                _sendFacilityAssignmentOrderResponse var26 = ((_sendFacilityAssignmentOrderResponseBISMsg)var23).value;
                switch(var26.discriminator()) {
                    case 0:
                        return var26.aSendFacilityAssignmentOrderReturn();
                    case 1:
                        throw var26.aBusinessViolation();
                    case 2:
                        throw var26.aObjectNotFound();
                    case 3:
                        throw var26.aInvalidData();
                    case 4:
                        throw var26.aAccessDenied();
                    case 5:
                        throw var26.aSystemFailure();
                    case 6:
                        throw var26.aDataNotFound();
                    case 7:
                        throw var26.aNotImplemented();
                }
            } catch (ClassCastException var29) {
                if (var23 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var27 = ((MidletRemoteExceptionMsg)var23).value;
                    throw new MidletRuntimeException(var27.msg, var29);
                }

                throw new MidletRuntimeException("No valid response from call (" + var23.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public SendFacilityAssignmentOrder2Return sendFacilityAssignmentOrder2(BisContext var1, String var2, String var3, String var4, StringOpt var5, String var6, String var7, String var8, BooleanOpt var9, StringOpt var10, String var11, StringOpt var12, Location var13, EiaDate var14, EiaDateOpt var15, EiaDate var16, StringOpt var17, StringOpt var18, StringOpt var19, BooleanOpt var20, String var21, BooleanOpt var22, CompositeObjectKey var23, ObjectPropertySeqOpt var24) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var25 = new VObject[]{VMUtil.newVObject(new _sendFacilityAssignmentOrder2RequestBISMsg(new _sendFacilityAssignmentOrder2Request(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24)))};

        VObject[] var26;
        try {
            var26 = this.client.call("com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder2(com.sbc.eia.idl.bis_types.BisContext, String, String, String, com.sbc.eia.idl.types.StringOpt, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var25, this.log);
        } catch (Throwable var32) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return com.sbc.eia.idl.rm.RmFacadePackage.sendFacilityAssignmentOrder2(com.sbc.eia.idl.bis_types.BisContext, String, String, String, com.sbc.eia.idl.types.StringOpt, String, String, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, String, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var32);
            throw new MidletRuntimeException(var32);
        }

        int var28 = 0;

        for(int var29 = var26.length; var28 < var29; ++var28) {
            MMarshalObject var27 = ((VMObject)var26[var28]).getObject();

            try {
                _sendFacilityAssignmentOrder2Response var30 = ((_sendFacilityAssignmentOrder2ResponseBISMsg)var27).value;
                switch(var30.discriminator()) {
                    case 0:
                        return var30.aSendFacilityAssignmentOrder2Return();
                    case 1:
                        throw var30.aBusinessViolation();
                    case 2:
                        throw var30.aObjectNotFound();
                    case 3:
                        throw var30.aInvalidData();
                    case 4:
                        throw var30.aAccessDenied();
                    case 5:
                        throw var30.aSystemFailure();
                    case 6:
                        throw var30.aDataNotFound();
                    case 7:
                        throw var30.aNotImplemented();
                }
            } catch (ClassCastException var33) {
                if (var27 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var31 = ((MidletRemoteExceptionMsg)var27).value;
                    throw new MidletRuntimeException(var31.msg, var33);
                }

                throw new MidletRuntimeException("No valid response from call (" + var27.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishFacilityAssignmentOrderNotificationReturn publishFacilityAssignmentOrderNotification() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishFacilityAssignmentOrderNotificationResponse var6 = ((_publishFacilityAssignmentOrderNotificationResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishFacilityAssignmentOrderNotificationReturn();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishFacilityAssignmentOrderNotification2Return publishFacilityAssignmentOrderNotification2() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification2()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishFacilityAssignmentOrderNotification2()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishFacilityAssignmentOrderNotification2Response var6 = ((_publishFacilityAssignmentOrderNotification2ResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishFacilityAssignmentOrderNotification2Return();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    @Override
    public publishValidateFacilityForProvisioningNotification2Return publishValidateFacilityForProvisioningNotification2() {
        return null;
    }

    public CreateFacilityAssignmentReturn createFacilityAssignment(BisContext var1, String var2, CompositeObjectKey var3, Location var4, BooleanOpt var5, EiaDate var6, OrderAction var7, StringOpt var8, String var9, NetworkType var10, ObjectPropertySeqOpt var11) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var12 = new VObject[]{VMUtil.newVObject(new _createFacilityAssignmentRequestBISMsg(new _createFacilityAssignmentRequest(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11)))};

        VObject[] var13;
        try {
            var13 = this.client.call("com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn com.sbc.eia.idl.rm.RmFacadePackage.createFacilityAssignment(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.NetworkType, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var12, this.log);
        } catch (Throwable var19) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.CreateFacilityAssignmentReturn com.sbc.eia.idl.rm.RmFacadePackage.createFacilityAssignment(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.NetworkType, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var19);
            throw new MidletRuntimeException(var19);
        }

        int var15 = 0;

        for(int var16 = var13.length; var15 < var16; ++var15) {
            MMarshalObject var14 = ((VMObject)var13[var15]).getObject();

            try {
                _createFacilityAssignmentResponse var17 = ((_createFacilityAssignmentResponseBISMsg)var14).value;
                switch(var17.discriminator()) {
                    case 0:
                        return var17.aCreateFacilityAssignmentReturn();
                    case 1:
                        throw var17.aBusinessViolation();
                    case 2:
                        throw var17.aObjectNotFound();
                    case 3:
                        throw var17.aInvalidData();
                    case 4:
                        throw var17.aAccessDenied();
                    case 5:
                        throw var17.aSystemFailure();
                    case 6:
                        throw var17.aDataNotFound();
                    case 7:
                        throw var17.aNotImplemented();
                }
            } catch (ClassCastException var20) {
                if (var14 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var18 = ((MidletRemoteExceptionMsg)var14).value;
                    throw new MidletRuntimeException(var18.msg, var20);
                }

                throw new MidletRuntimeException("No valid response from call (" + var14.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(BisContext var1, String var2, CompositeObjectKey var3, AddressOpt var4, ProductSubscription[] var5, StringOpt var6, String var7, OrderAction var8, ObjectPropertySeqOpt var9) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var10 = new VObject[]{VMUtil.newVObject(new _publishAutoDiscoveryResultsRequestBISMsg(new _publishAutoDiscoveryResultsRequest(var1, var2, var3, var4, var5, var6, var7, var8, var9)))};

        VObject[] var11;
        try {
            var11 = this.client.call("com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn com.sbc.eia.idl.rm.RmFacadePackage.publishAutoDiscoveryResults(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.AddressOpt, com.sbc.eia.idl.sm_ls_types.ProductSubscription[], com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var10, this.log);
        } catch (Throwable var17) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishAutoDiscoveryResultsReturn com.sbc.eia.idl.rm.RmFacadePackage.publishAutoDiscoveryResults(com.sbc.eia.idl.bis_types.BisContext, String, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.lim_types.AddressOpt, com.sbc.eia.idl.sm_ls_types.ProductSubscription[], com.sbc.eia.idl.types.StringOpt, String, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var17);
            throw new MidletRuntimeException(var17);
        }

        int var13 = 0;

        for(int var14 = var11.length; var13 < var14; ++var13) {
            MMarshalObject var12 = ((VMObject)var11[var13]).getObject();

            try {
                _publishAutoDiscoveryResultsResponse var15 = ((_publishAutoDiscoveryResultsResponseBISMsg)var12).value;
                switch(var15.discriminator()) {
                    case 0:
                        return var15.aPublishAutoDiscoveryResultsReturn();
                    case 1:
                        throw var15.aBusinessViolation();
                    case 2:
                        throw var15.aObjectNotFound();
                    case 3:
                        throw var15.aInvalidData();
                    case 4:
                        throw var15.aAccessDenied();
                    case 5:
                        throw var15.aSystemFailure();
                    case 6:
                        throw var15.aDataNotFound();
                    case 7:
                        throw var15.aNotImplemented();
                }
            } catch (ClassCastException var18) {
                if (var12 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var16 = ((MidletRemoteExceptionMsg)var12).value;
                    throw new MidletRuntimeException(var16.msg, var18);
                }

                throw new MidletRuntimeException("No valid response from call (" + var12.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishRGActivationReturn publishRGActivation(BisContext var1, StringOpt var2, CompositeObjectKey var3, DSLAMTransportOpt var4, ResidentialGateway var5, Time var6, OrderAction var7, ObjectPropertySeqOpt var8) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var9 = new VObject[]{VMUtil.newVObject(new _publishRGActivationRequestBISMsg(new _publishRGActivationRequest(var1, var2, var3, var4, var5, var6, var7, var8)))};

        VObject[] var10;
        try {
            var10 = this.client.call("com.sbc.eia.idl.rm.PublishRGActivationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishRGActivation(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt, com.sbc.eia.idl.rm_ls_types.ResidentialGateway, com.sbc.eia.idl.types.Time, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var9, this.log);
        } catch (Throwable var16) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishRGActivationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishRGActivation(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.CompositeObjectKey, com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt, com.sbc.eia.idl.rm_ls_types.ResidentialGateway, com.sbc.eia.idl.types.Time, com.sbc.eia.idl.rm_ls_types.OrderAction, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var16);
            throw new MidletRuntimeException(var16);
        }

        int var12 = 0;

        for(int var13 = var10.length; var12 < var13; ++var12) {
            MMarshalObject var11 = ((VMObject)var10[var12]).getObject();

            try {
                _publishRGActivationResponse var14 = ((_publishRGActivationResponseBISMsg)var11).value;
                switch(var14.discriminator()) {
                    case 0:
                        return var14.aPublishRGActivationReturn();
                    case 1:
                        throw var14.aBusinessViolation();
                    case 2:
                        throw var14.aObjectNotFound();
                    case 3:
                        throw var14.aInvalidData();
                    case 4:
                        throw var14.aAccessDenied();
                    case 5:
                        throw var14.aSystemFailure();
                    case 6:
                        throw var14.aDataNotFound();
                    case 7:
                        throw var14.aNotImplemented();
                }
            } catch (ClassCastException var17) {
                if (var11 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var15 = ((MidletRemoteExceptionMsg)var11).value;
                    throw new MidletRuntimeException(var15.msg, var17);
                }

                throw new MidletRuntimeException("No valid response from call (" + var11.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public SendTNAssignmentOrderReturn sendTNAssignmentOrder(BisContext var1, String var2, String var3, String var4, String var5, StringOpt var6, BooleanOpt var7, Location var8, EiaDate var9, EiaDateOpt var10, EiaDate var11, BooleanOpt var12, StringOpt var13, StringOpt var14, TelephoneNumberOrderPairSeqOpt var15, ObjectPropertySeqOpt var16) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var17 = new VObject[]{VMUtil.newVObject(new _sendTNAssignmentOrderRequestBISMsg(new _sendTNAssignmentOrderRequest(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16)))};

        VObject[] var18;
        try {
            var18 = this.client.call("com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendTNAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var17, this.log);
        } catch (Throwable var24) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.SendTNAssignmentOrderReturn com.sbc.eia.idl.rm.RmFacadePackage.sendTNAssignmentOrder(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.EiaDate, com.sbc.eia.idl.types.BooleanOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var24);
            throw new MidletRuntimeException(var24);
        }

        int var20 = 0;

        for(int var21 = var18.length; var20 < var21; ++var20) {
            MMarshalObject var19 = ((VMObject)var18[var20]).getObject();

            try {
                _sendTNAssignmentOrderResponse var22 = ((_sendTNAssignmentOrderResponseBISMsg)var19).value;
                switch(var22.discriminator()) {
                    case 0:
                        return var22.aSendTNAssignmentOrderReturn();
                    case 1:
                        throw var22.aBusinessViolation();
                    case 2:
                        throw var22.aObjectNotFound();
                    case 3:
                        throw var22.aInvalidData();
                    case 4:
                        throw var22.aAccessDenied();
                    case 5:
                        throw var22.aSystemFailure();
                    case 6:
                        throw var22.aDataNotFound();
                    case 7:
                        throw var22.aNotImplemented();
                }
            } catch (ClassCastException var25) {
                if (var19 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var23 = ((MidletRemoteExceptionMsg)var19).value;
                    throw new MidletRuntimeException(var23.msg, var25);
                }

                throw new MidletRuntimeException("No valid response from call (" + var19.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishTNAssignmentOrderNotificationReturn publishTNAssignmentOrderNotification() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNAssignmentOrderNotification()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNAssignmentOrderNotification()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishTNAssignmentOrderNotificationResponse var6 = ((_publishTNAssignmentOrderNotificationResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishTNAssignmentOrderNotificationReturn();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(BisContext var1, String var2, String var3, String var4, String[] var5) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var6 = new VObject[]{VMUtil.newVObject(new _sendActivateTNPortingSubscriptionMsgRequestBISMsg(new _sendActivateTNPortingSubscriptionMsgRequest(var1, var2, var3, var4, var5)))};

        VObject[] var7;
        try {
            var7 = this.client.call("com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn com.sbc.eia.idl.rm.RmFacadePackage.sendActivateTNPortingSubscriptionMsg(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String[])", var6, this.log);
        } catch (Throwable var13) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn com.sbc.eia.idl.rm.RmFacadePackage.sendActivateTNPortingSubscriptionMsg(com.sbc.eia.idl.bis_types.BisContext, String, String, String, String[])", var13);
            throw new MidletRuntimeException(var13);
        }

        int var9 = 0;

        for(int var10 = var7.length; var9 < var10; ++var9) {
            MMarshalObject var8 = ((VMObject)var7[var9]).getObject();

            try {
                _sendActivateTNPortingSubscriptionMsgResponse var11 = ((_sendActivateTNPortingSubscriptionMsgResponseBISMsg)var8).value;
                switch(var11.discriminator()) {
                    case 0:
                        return var11.aSendActivateTNPortingSubscriptionMsgReturn();
                    case 1:
                        throw var11.aBusinessViolation();
                    case 2:
                        throw var11.aObjectNotFound();
                    case 3:
                        throw var11.aInvalidData();
                    case 4:
                        throw var11.aAccessDenied();
                    case 5:
                        throw var11.aSystemFailure();
                    case 6:
                        throw var11.aDataNotFound();
                    case 7:
                        throw var11.aNotImplemented();
                }
            } catch (ClassCastException var14) {
                if (var8 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var12 = ((MidletRemoteExceptionMsg)var8).value;
                    throw new MidletRuntimeException(var12.msg, var14);
                }

                throw new MidletRuntimeException("No valid response from call (" + var8.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishTNPortingNotificationReturn publishTNPortingNotification() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNPortingNotification()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishTNPortingNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishTNPortingNotification()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishTNPortingNotificationResponse var6 = ((_publishTNPortingNotificationResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishTNPortingNotificationReturn();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PingReturn ping(BisContext var1) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        VObject[] var2 = new VObject[]{VMUtil.newVObject(new _pingRequestBISMsg(new _pingRequest(var1)))};

        VObject[] var3;
        try {
            var3 = this.client.call("com.sbc.eia.idl.rm.PingReturn com.sbc.eia.idl.rm.RmFacadePackage.ping(com.sbc.eia.idl.bis_types.BisContext)", var2, this.log);
        } catch (Throwable var9) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PingReturn com.sbc.eia.idl.rm.RmFacadePackage.ping(com.sbc.eia.idl.bis_types.BisContext)", var9);
            throw new MidletRuntimeException(var9);
        }

        int var5 = 0;

        for(int var6 = var3.length; var5 < var6; ++var5) {
            MMarshalObject var4 = ((VMObject)var3[var5]).getObject();

            try {
                _pingResponse var7 = ((_pingResponseBISMsg)var4).value;
                switch(var7.discriminator()) {
                    case 0:
                        return var7.aPingReturn();
                    case 1:
                        throw var7.aBusinessViolation();
                    case 2:
                        throw var7.aObjectNotFound();
                    case 3:
                        throw var7.aInvalidData();
                    case 4:
                        throw var7.aAccessDenied();
                    case 5:
                        throw var7.aSystemFailure();
                    case 6:
                        throw var7.aNotImplemented();
                    case 7:
                        throw var7.aDataNotFound();
                    case 8:
                        throw var7.aMultipleExceptions();
                }
            } catch (ClassCastException var10) {
                if (var4 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var8 = ((MidletRemoteExceptionMsg)var4).value;
                    throw new MidletRuntimeException(var8.msg, var10);
                }

                throw new MidletRuntimeException("No valid response from call (" + var4.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public SelfTestReturn selfTest(BisContext var1) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        VObject[] var2 = new VObject[]{VMUtil.newVObject(new _selfTestRequestBISMsg(new _selfTestRequest(var1)))};

        VObject[] var3;
        try {
            var3 = this.client.call("com.sbc.eia.idl.rm.SelfTestReturn com.sbc.eia.idl.rm.RmFacadePackage.selfTest(com.sbc.eia.idl.bis_types.BisContext)", var2, this.log);
        } catch (Throwable var9) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.SelfTestReturn com.sbc.eia.idl.rm.RmFacadePackage.selfTest(com.sbc.eia.idl.bis_types.BisContext)", var9);
            throw new MidletRuntimeException(var9);
        }

        int var5 = 0;

        for(int var6 = var3.length; var5 < var6; ++var5) {
            MMarshalObject var4 = ((VMObject)var3[var5]).getObject();

            try {
                _selfTestResponse var7 = ((_selfTestResponseBISMsg)var4).value;
                switch(var7.discriminator()) {
                    case 0:
                        return var7.aSelfTestReturn();
                    case 1:
                        throw var7.aBusinessViolation();
                    case 2:
                        throw var7.aObjectNotFound();
                    case 3:
                        throw var7.aInvalidData();
                    case 4:
                        throw var7.aAccessDenied();
                    case 5:
                        throw var7.aSystemFailure();
                    case 6:
                        throw var7.aNotImplemented();
                    case 7:
                        throw var7.aDataNotFound();
                    case 8:
                        throw var7.aMultipleExceptions();
                }
            } catch (ClassCastException var10) {
                if (var4 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var8 = ((MidletRemoteExceptionMsg)var4).value;
                    throw new MidletRuntimeException(var8.msg, var10);
                }

                throw new MidletRuntimeException("No valid response from call (" + var4.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public ValidateFacilityReturn validateFacility(BisContext var1, Location var2, StringOpt var3, StringOpt var4, ShortOpt var5, StringOpt var6, StringOpt var7, EiaDateOpt var8, ObjectPropertySeqOpt var9) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var10 = new VObject[]{VMUtil.newVObject(new _validateFacilityRequestBISMsg(new _validateFacilityRequest(var1, var2, var3, var4, var5, var6, var7, var8, var9)))};

        VObject[] var11;
        try {
            var11 = this.client.call("com.sbc.eia.idl.rm.ValidateFacilityReturn com.sbc.eia.idl.rm.RmFacadePackage.validateFacility(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var10, this.log);
        } catch (Throwable var17) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.ValidateFacilityReturn com.sbc.eia.idl.rm.RmFacadePackage.validateFacility(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var17);
            throw new MidletRuntimeException(var17);
        }

        int var13 = 0;

        for(int var14 = var11.length; var13 < var14; ++var13) {
            MMarshalObject var12 = ((VMObject)var11[var13]).getObject();

            try {
                _validateFacilityResponse var15 = ((_validateFacilityResponseBISMsg)var12).value;
                switch(var15.discriminator()) {
                    case 0:
                        return var15.aValidateFacilityReturn();
                    case 1:
                        throw var15.aBusinessViolation();
                    case 2:
                        throw var15.aObjectNotFound();
                    case 3:
                        throw var15.aInvalidData();
                    case 4:
                        throw var15.aAccessDenied();
                    case 5:
                        throw var15.aSystemFailure();
                    case 6:
                        throw var15.aDataNotFound();
                    case 7:
                        throw var15.aNotImplemented();
                }
            } catch (ClassCastException var18) {
                if (var12 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var16 = ((MidletRemoteExceptionMsg)var12).value;
                    throw new MidletRuntimeException(var16.msg, var18);
                }

                throw new MidletRuntimeException("No valid response from call (" + var12.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishValidateFacilityNotificationReturn publishValidateFacilityNotification() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishValidateFacilityNotificationReturn com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishValidateFacilityNotificationResponse var6 = ((_publishValidateFacilityNotificationResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishValidateFacilityNotificationReturn();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public ValidateFacility2Return validateFacility2(BisContext var1, Location var2, StringOpt var3, StringOpt var4, ShortOpt var5, StringOpt var6, StringOpt var7, EiaDateOpt var8, ObjectType[] var9, StringOpt var10, StringOpt var11, ObjectPropertySeqOpt var12) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        VObject[] var13 = new VObject[]{VMUtil.newVObject(new _validateFacility2RequestBISMsg(new _validateFacility2Request(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12)))};

        VObject[] var14;
        try {
            var14 = this.client.call("com.sbc.eia.idl.rm.ValidateFacility2Return com.sbc.eia.idl.rm.RmFacadePackage.validateFacility2(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectType[], com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var13, this.log);
        } catch (Throwable var20) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.ValidateFacility2Return com.sbc.eia.idl.rm.RmFacadePackage.validateFacility2(com.sbc.eia.idl.bis_types.BisContext, com.sbc.eia.idl.lim_types.Location, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ShortOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.EiaDateOpt, com.sbc.eia.idl.types.ObjectType[], com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.StringOpt, com.sbc.eia.idl.types.ObjectPropertySeqOpt)", var20);
            throw new MidletRuntimeException(var20);
        }

        int var16 = 0;

        for(int var17 = var14.length; var16 < var17; ++var16) {
            MMarshalObject var15 = ((VMObject)var14[var16]).getObject();

            try {
                _validateFacility2Response var18 = ((_validateFacility2ResponseBISMsg)var15).value;
                switch(var18.discriminator()) {
                    case 0:
                        return var18.aValidateFacility2Return();
                    case 1:
                        throw var18.aBusinessViolation();
                    case 2:
                        throw var18.aObjectNotFound();
                    case 3:
                        throw var18.aInvalidData();
                    case 4:
                        throw var18.aAccessDenied();
                    case 5:
                        throw var18.aSystemFailure();
                    case 6:
                        throw var18.aDataNotFound();
                    case 7:
                        throw var18.aNotImplemented();
                }
            } catch (ClassCastException var21) {
                if (var15 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var19 = ((MidletRemoteExceptionMsg)var15).value;
                    throw new MidletRuntimeException(var19.msg, var21);
                }

                throw new MidletRuntimeException("No valid response from call (" + var15.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public PublishValidateFacilityNotification2Return publishValidateFacilityNotification2() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification2()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.PublishValidateFacilityNotification2Return com.sbc.eia.idl.rm.RmFacadePackage.publishValidateFacilityNotification2()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _publishValidateFacilityNotification2Response var6 = ((_publishValidateFacilityNotification2ResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aPublishValidateFacilityNotification2Return();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public ModifyFacilityInfoReturn modifyFacilityInfo() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.ModifyFacilityInfoReturn com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.ModifyFacilityInfoReturn com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _modifyFacilityInfoResponse var6 = ((_modifyFacilityInfoResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aModifyFacilityInfoReturn();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    public ModifyFacilityInfo2Return modifyFacilityInfo2() {
        VObject[] var1 = new VObject[0];

        VObject[] var2;
        try {
            var2 = this.client.call("com.sbc.eia.idl.rm.ModifyFacilityInfo2Return com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo2()", var1, this.log);
        } catch (Throwable var8) {
            this.log.error().write("Exception caught RmFacadeSOABuilder: com.sbc.eia.idl.rm.ModifyFacilityInfo2Return com.sbc.eia.idl.rm.RmFacadePackage.modifyFacilityInfo2()", var8);
            throw new MidletRuntimeException(var8);
        }

        int var4 = 0;

        for(int var5 = var2.length; var4 < var5; ++var4) {
            MMarshalObject var3 = ((VMObject)var2[var4]).getObject();

            try {
                _modifyFacilityInfo2Response var6 = ((_modifyFacilityInfo2ResponseBISMsg)var3).value;
                switch(var6.discriminator()) {
                    case 0:
                        return var6.aModifyFacilityInfo2Return();
                }
            } catch (ClassCastException var9) {
                if (var3 instanceof MidletRemoteExceptionMsg) {
                    MidletRemoteException var7 = ((MidletRemoteExceptionMsg)var3).value;
                    throw new MidletRuntimeException(var7.msg, var9);
                }

                throw new MidletRuntimeException("No valid response from call (" + var3.getClass().getName() + ")");
            }
        }

        throw new MidletRuntimeException("No valid response from call");
    }

    @Override
    public ValidateFacility3Return validateFacility3(BisContext bisContext, Location location, StringOpt stringOpt, StringOpt stringOpt1, ShortOpt shortOpt, StringOpt stringOpt2, EiaDateOpt eiaDateOpt, ObjectType[] objectTypes, OrderAction2Opt orderAction2Opt, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public PublishValidateFacilityNotification3Return publishValidateFacilityNotification3() {
        return null;
    }

    @Override
    public ValidateFacilityForProvisioningReturn validateFacilityForProvisioning(BisContext bisContext, Location location, StringOpt stringOpt, StringOpt stringOpt1, ShortOpt shortOpt, StringOpt stringOpt2, EiaDateOpt eiaDateOpt, ObjectType[] objectTypes, OrderAction2Opt orderAction2Opt, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public publishValidateFacilityForProvisioningNotificationReturn publishValidateFacilityForProvisioningNotification() {
        return null;
    }

    @Override
    public SendFacilityAssignmentOrder3Return sendFacilityAssignmentOrder3(BisContext bisContext, String s, String s1, String s2, StringOpt stringOpt, String s3, String s4, String s5, BooleanOpt booleanOpt, StringOpt stringOpt1, String s6, StringOpt stringOpt2, Location location, EiaDate eiaDate, EiaDateOpt eiaDateOpt, EiaDate eiaDate1, StringOpt stringOpt3, StringOpt stringOpt4, StringOpt stringOpt5, BooleanOpt booleanOpt1, String s7, BooleanOpt booleanOpt2, CompositeObjectKey compositeObjectKey, BooleanOpt booleanOpt3, StringOpt stringOpt6, StringOpt stringOpt7, StringOpt stringOpt8, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public PublishFacilityAssignmentOrderNotification3Return publishFacilityAssignmentOrderNotification3() {
        return null;
    }

    @Override
    public SubmitFiberServiceTNAssignmentOrderReturn submitFiberServiceTNAssignmentOrder(BisContext bisContext, String s, String s1, String s2, String s3, StringOpt stringOpt, BooleanOpt booleanOpt, Location location, EiaDate eiaDate, EiaDateOpt eiaDateOpt, EiaDate eiaDate1, BooleanOpt booleanOpt1, StringOpt stringOpt1, StringOpt stringOpt2, TelephoneNumberOrderPairSeqOpt telephoneNumberOrderPairSeqOpt, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public ActivateFiberServiceTNPortReturn activateFiberServiceTNPort(BisContext bisContext, String s, String s1, String s2, String[] strings) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public AddFiberServiceTNPortReturn addFiberServiceTNPort(BisContext bisContext, String s, StringOpt stringOpt, StringOpt stringOpt1, StringOpt stringOpt2, String s1, String s2, EiaDate eiaDate, String[] strings) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public AddFiberServiceTNPort2Return addFiberServiceTNPort2(BisContext bisContext, String s, StringOpt stringOpt, StringOpt stringOpt1, StringOpt stringOpt2, StringOpt stringOpt3, StringOpt stringOpt4, EiaDate eiaDate, AddFiberServiceTNPortTelephoneNumberRequest[] addFiberServiceTNPortTelephoneNumberRequests) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public InquireFiberServiceTNPortStatusReturn inquireFiberServiceTNPortStatus(BisContext bisContext, String s, String s1, String s2, Time time, Time time1, String[] strings) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public SubmitFiberServiceTNAssignmentOrder2Return submitFiberServiceTNAssignmentOrder2(BisContext bisContext, String s, String s1, String s2, String s3, StringOpt stringOpt, BooleanOpt booleanOpt, Location location, EiaDate eiaDate, EiaDateOpt eiaDateOpt, EiaDate eiaDate1, BooleanOpt booleanOpt1, StringOpt stringOpt1, StringOpt stringOpt2, BooleanOpt booleanOpt2, TelephoneNumberOrderPairSeqOpt telephoneNumberOrderPairSeqOpt, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }

    @Override
    public SendFacilityAssignmentOrder4Return sendFacilityAssignmentOrder4(BisContext bisContext, String s, String s1, String s2, StringOpt stringOpt, String s3, String s4, String s5, BooleanOpt booleanOpt, StringOpt stringOpt1, String s6, StringOpt stringOpt2, Location location, EiaDate eiaDate, EiaDateOpt eiaDateOpt, EiaDate eiaDate1, StringOpt stringOpt3, StringOpt stringOpt4, StringOpt stringOpt5, BooleanOpt booleanOpt1, String s7, BooleanOpt booleanOpt2, CompositeObjectKey compositeObjectKey, BooleanOpt booleanOpt3, StringOpt stringOpt6, StringOpt stringOpt7, StringSeqOpt stringSeqOpt, StringOpt stringOpt8, ObjectPropertySeqOpt objectPropertySeqOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented {
        return null;
    }


    @Override
    public PublishValidateFacilityNotification4Return publishValidateFacilityNotification4() {
        return null;
    }

    @Override
    public PublishValidateFacilityNotification5Return publishValidateFacilityNotification5() {
        return null;
    }
}
