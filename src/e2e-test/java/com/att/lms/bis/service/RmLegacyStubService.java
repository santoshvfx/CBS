package com.att.lms.bis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.idl.rm_ls_types.*;
import com.sbc.eia.idl.rm_types.IntegratedDigitalLoopCarrierIndicatorOpt;
import com.sbc.eia.idl.rm_types.ResourceProvider;
import com.sbc.eia.idl.rm_types.ResourceRole;
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.types.*;
import org.springframework.beans.factory.annotation.Autowired;

public class RmLegacyStubService extends RmLegacyService {
    @Autowired
    ObjectMapper objectMapper;

    public RmLegacyStubService() throws Exception {
        super();
    }

    /*public RetrieveResourcesForServiceReturn retrieveResourcesForService(
            BisContext aContext, ObjectKey aServiceHandle, String[] aResourceRoleNames)
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {

        RetrieveResourcesForServiceReturn response = new RetrieveResourcesForServiceReturn();
        response.aContext = aContext;
        response.aResourceRoles = new ResourceRole[1];
        ResourceRole resourceRole = new ResourceRole();
        resourceRole.aRoleName = "";
        resourceRole.aResourceHandle = new ObjectKey();
        resourceRole.aIntegratedDigitalLoopCarrierIndicator = new IntegratedDigitalLoopCarrierIndicatorOpt();
        resourceRole.aResourceProvider = new ResourceProvider();
        resourceRole.aResourceProvider.aResourceProviderHandle = new ObjectKey();
        resourceRole.aResourceProvider.aResourceProviderTypeHandle = new ObjectKey();
        response.aResourceRoles[0] = resourceRole;
        return response;
    }*/

    /*public RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
            BisContext aContext, String aResourceHandle, ObjectKey[] aServiceTypeHandles)
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {

        RetrieveServiceProvidersForResourceReturn response = new RetrieveServiceProvidersForResourceReturn();
        response.aContext = aContext;
        response.aServiceProvidersForServiceType = new ServiceProvidersForServiceType[1];
        ServiceProvidersForServiceType serviceType = new ServiceProvidersForServiceType();
        serviceType.aServiceTypeHandle = new ObjectKey();
        serviceType.aServiceProviders = new ServiceProvider[1];
        serviceType.aServiceProviders[0] = new ServiceProvider();
        serviceType.aServiceProviders[0].aServiceProviderHandle = new ObjectKey();
        serviceType.aServiceProviders[0].aOperatingCompanyNumbers = new OperatingCompanyNumberSeqOpt();
        response.aServiceProvidersForServiceType[0] = serviceType;
        return response;
    }*/

    /*public RetrieveServiceProvidersForServiceReturn retrieveServiceProvidersForService(
            BisContext aContext, ObjectKey aSerivceHandle, ObjectKey[] aServiceTypeHandles)
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {

        RetrieveServiceProvidersForServiceReturn response = new RetrieveServiceProvidersForServiceReturn();
        response.aContext = aContext;
        response.aServiceProvidersForServiceType = new ServiceProvidersForServiceType[1];
        ServiceProvidersForServiceType serviceType = new ServiceProvidersForServiceType();
        serviceType.aServiceTypeHandle = new ObjectKey();
        serviceType.aServiceProviders = new ServiceProvider[1];
        serviceType.aServiceProviders[0] = new ServiceProvider();
        serviceType.aServiceProviders[0].aServiceProviderHandle = new ObjectKey();
        serviceType.aServiceProviders[0].aOperatingCompanyNumbers = new OperatingCompanyNumberSeqOpt();
        response.aServiceProvidersForServiceType[0] = serviceType;
        return response;
    }*/

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
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {
        CreateFacilityAssignmentReturn response = new CreateFacilityAssignmentReturn();
        response.aContext = aContext;
        response.aCustomerTransportId = "";
        response.aDSLAM = new DSLAMTransportOpt();
        response.aOrderAction = new OrderAction();
        response.aNetwork7450Switch = new Network7450Switch();
        response.aSBCISPOPId = new StringOpt();
        response.aTaperCode = new StringOpt();
        response.aVPLSDomain = new StringOpt();
        response.aSbcServingOfficeWirecenterCLLI = new StringOpt();
        response.aProperties = new ObjectPropertySeqOpt();

        return response;
    }

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
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {
        PublishAutoDiscoveryResultsReturn response = new PublishAutoDiscoveryResultsReturn();
        response.aContext = aContext;
        response.aCustomerTransportId = "";
        response.aBillingAccountNumber = new CompositeObjectKey();
        response.aServiceAddress = new AddressOpt();
        response.aProductSubscriptions = new com.sbc.eia.idl.sm_ls_types.ProductSubscription[0];
        response.aTelephoneNumber = new StringOpt();
        response.aAssignedProductId = "";
        response.aOrderAction = new OrderAction();
        response.aProperties = new ObjectPropertySeqOpt();

        return response;
    }

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
            throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
            ObjectNotFound, DataNotFound {

        SendTNAssignmentOrderReturn response = new SendTNAssignmentOrderReturn();
        response.aContext = aContext;
        response.aSOACServiceOrderCorrectionSuffix = new StringOpt();
        response.aTelephoneNumberOrderPairs = new TelephoneNumberOrderPairSeqOpt();
        response.aSoacServiceOrderNumber = new StringOpt();
        response.aStatus = new ExceptionDataOpt();
        response.aProperties = new ObjectPropertySeqOpt();

        return response;
    }

    /**
     * sendActivateTNPortingSubscriptionMsg
     *
     * @param aContext The client's calling context. Required tags are
     *     BisContextProperty.SERVICECENTER BisContextProperty.APPLICATION -- Initiating System
     *     BisContextProperty.CUSTOMERNAME -- Initiating user BisContextProperty.BUSINESSUNIT
     *     BisContextProperty.LOGGINGINFORMATION
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
            throws AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound,
            DataNotFound, InvalidData {

        SendActivateTNPortingSubscriptionMsgReturn response = new SendActivateTNPortingSubscriptionMsgReturn();
        response.aContext = aContext;
        response.aSOACServiceOrderCorrectionSuffix = new StringOpt();
        response.aSoacServiceOrderNumber = new StringOpt();
        response.aTelephoneNumberRequestStatus = new TelephoneNumberRequestStatusSeqOpt();

        return response;
    }
}
