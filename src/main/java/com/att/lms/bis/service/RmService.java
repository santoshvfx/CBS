package com.att.lms.bis.service;

import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.common.persistence.IPersistable;
import com.att.lms.bis.dto.rm.*;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.idl.srm.CreateServiceRequestReturn;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RmService {

  public static final String SUCCESS_MESSAGE_TEMPLATE = "operation {} succeeded with value {}";

  public static final String FAILURE_MESSAGE_TEMPLATE =
      "operation {} failed with error type={}, msg={}";

  @Autowired RmLegacyService legacyService;

  public Either<Throwables, PingReturn> ping(PingRequestDto requestDto) {
    return Try.of(() -> legacyService.ping(requestDto.getABisContext()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveResourcesForServiceReturn> retrieveResourcesForService(
      RetrieveResourcesForServiceRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.retrieveResourcesForService(
                    requestDto.getABisContext(),
                    requestDto.getAServiceHandle(),
                    requestDto.getAResourceRoleNames()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveServiceProvidersForServiceReturn>
      retrieveServiceProvidersForService(RetrieveServiceProvidersForServiceRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.retrieveServiceProvidersForService(
                    requestDto.getABisContext(),
                    requestDto.getAServiceHandle(),
                    requestDto.getAServiceTypeHandles()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveServiceProvidersForResourceReturn>
      retrieveServiceProvidersForResource(
          RetrieveServiceProvidersForResourceRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.retrieveServiceProvidersForResource(
                    requestDto.getABisContext(),
                    requestDto.getAResourceHandle(),
                    requestDto.getAServiceTypeHandles()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, ActivateFiberServiceTNPortReturn> activateFiberServiceTNPort(
      ActivateFiberServiceTNPortRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.activateFiberServiceTNPort(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getALocalServiceProviderId(),
                    requestDto.getATelephoneNumbers()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, AddFiberServiceTNPort2Return> addFiberServiceTNPort2(
      AddFiberServiceTNPort2RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.addFiberServiceTNPort2(
                    requestDto.getABisContext(),
                    requestDto.getAState(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOldServiceProviderId(),
                    requestDto.getANewServiceProviderId(),
                    requestDto.getAOrderDueDate(),
                    requestDto.getATelephoneNumbers()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, AddFiberServiceTNPortReturn> addFiberServiceTNPort(
      AddFiberServiceTNPortRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.addFiberServiceTNPort(
                    requestDto.getABisContext(),
                    requestDto.getAState(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOldServiceProviderId(),
                    requestDto.getANewServiceProviderId(),
                    requestDto.getAOrderDueDate(),
                    requestDto.getATelephoneNumbers()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, CreateFacilityAssignmentReturn> createFacilityAssignment(
      CreateFacilityAssignmentRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.createFacilityAssignment(
                    requestDto.getABisContext(),
                    requestDto.getACustomerTransportId(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAMaintenanceFlag(),
                    requestDto.getADueDate(),
                    requestDto.getAOrderAction(),
                    requestDto.getATaperCode(),
                    requestDto.getANetworkType(),
                    requestDto.getANetworkTypeChoice(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, InquireFiberServiceTNPortStatusReturn> inquireFiberServiceTNPortStatus(
      InquireFiberServiceTNPortStatusRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.inquireFiberServiceTNPortStatus(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getAOldServiceProviderId(),
                    requestDto.getANewServiceProviderId(),
                    requestDto.getAOrderDueDateTime(),
                    requestDto.getAAppointmentDateTime(),
                    requestDto.getATelephoneNumbers()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, PublishAutoDiscoveryResultsReturn> publishAutoDiscoveryResults(
      PublishAutoDiscoveryResultsRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.publishAutoDiscoveryResults(
                    requestDto.getABisContext(),
                    requestDto.getACustomerTransportId(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getAServiceAddress(),
                    requestDto.getAProductSubscriptions(),
                    requestDto.getATelephoneNumber(),
                    requestDto.getAAssignedProductId(),
                    requestDto.getAOrderAction(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, PublishRGActivationReturn> publishRGActivation(
      PublishRGActivationRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.publishRGActivation(
                    requestDto.getABisContext(),
                    requestDto.getACustomerTransportId(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getADSLAM(),
                    requestDto.getARG(),
                    requestDto.getAActivationTime(),
                    requestDto.getAOrderAction(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SelfTestReturn> selfTest(SelfTestRequestDto requestDto) {

    return Try.of(() -> legacyService.selfTest(requestDto.getABisContext()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendActivateTNPortingSubscriptionMsgReturn>
      sendActivateTNPortingSubscriptionMsg(
          SendActivateTNPortingSubscriptionMsgRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendActivateTNPortingSubscriptionMsg(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getALocalServiceProviderId(),
                    requestDto.getATelephoneNumbers()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendFacilityAssignmentOrder2Return> sendFacilityAssignmentOrder2(
      SendFacilityAssignmentOrder2RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendFacilityAssignmentOrder2(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getANetworkType(),
                    requestDto.getAOldNetworkType(),
                    requestDto.getAOrderActionId(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getASubActionType(),
                    requestDto.getACircuitId(),
                    requestDto.getASecondaryCircuitId(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getARelatedServiceOrderNumber(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getASecondaryRelatedCircuitID(),
                    requestDto.getALineShareDisconnectFlag(),
                    requestDto.getAClassOfService(),
                    requestDto.getAResendFlag(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendFacilityAssignmentOrder3Return> sendFacilityAssignmentOrder3(
      SendFacilityAssignmentOrder3RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendFacilityAssignmentOrder3(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getANetworkType(),
                    requestDto.getAOldNetworkType(),
                    requestDto.getAOrderActionId(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getASubActionType(),
                    requestDto.getACircuitId(),
                    requestDto.getASecondaryCircuitId(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getASecondaryRelatedCircuitID(),
                    requestDto.getARelatedServiceOrderNumber(),
                    requestDto.getALineShareDisconnectFlag(),
                    requestDto.getAClassOfService(),
                    requestDto.getAResendFlag(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getAInterceptRedirectIndicator(),
                    requestDto.getADryloopRelatedCircuitId(),
                    requestDto.getADSLDisconnectTelephoneNumber(),
                    requestDto.getAExceptionRoutingIndicator(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendFacilityAssignmentOrder4Return> sendFacilityAssignmentOrder4(
      SendFacilityAssignmentOrder4RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendFacilityAssignmentOrder4(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getANetworkType(),
                    requestDto.getAOldNetworkType(),
                    requestDto.getAOrderActionId(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getASubActionType(),
                    requestDto.getACircuitId(),
                    requestDto.getASecondaryCircuitId(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getASecondaryRelatedCircuitID(),
                    requestDto.getARelatedServiceOrderNumber(),
                    requestDto.getALineShareDisconnectFlag(),
                    requestDto.getAClassOfService(),
                    requestDto.getAResendFlag(),
                    requestDto.getABillingAccountNumber(),
                    requestDto.getAInterceptRedirectIndicator(),
                    requestDto.getADryloopRelatedCircuitId(),
                    requestDto.getASecondaryDryloopRelatedCircuitId(),
                    requestDto.getADSLDisconnectCircuitIds(),
                    requestDto.getAExceptionRoutingIndicator(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendFacilityAssignmentOrderReturn> sendFacilityAssignmentOrder(
      SendFacilityAssignmentOrderRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendFacilityAssignmentOrder(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getANetworkType(),
                    requestDto.getAOrderActionId(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getASubActionType(),
                    requestDto.getACircuitId(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getATDMTelphoneNumber(),
                    requestDto.getARelatedServiceOrderNumber(),
                    requestDto.getALineShareDisconnectFlag(),
                    requestDto.getAClassOfService(),
                    requestDto.getAResendFlag(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SendTNAssignmentOrderReturn> sendTNAssignmentOrder(
      SendTNAssignmentOrderRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.sendTNAssignmentOrder(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getASubActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getAResendFlag(),
                    requestDto.getAWireCenter(),
                    requestDto.getARateCenter(),
                    requestDto.getATelephoneNumberOrderPairs(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SubmitFiberServiceTNAssignmentOrder2Return>
      submitFiberServiceTNAssignmentOrder2(
          SubmitFiberServiceTNAssignmentOrder2RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.submitFiberServiceTNAssignmentOrder2(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getASubActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getAResendFlag(),
                    requestDto.getAWireCenter(),
                    requestDto.getARateCenter(),
                    requestDto.getASimplePortIndicator(),
                    requestDto.getATelephoneNumberOrderPairs(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SubmitFiberServiceTNAssignmentOrderReturn>
      submitFiberServiceTNAssignmentOrder(
          SubmitFiberServiceTNAssignmentOrderRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.submitFiberServiceTNAssignmentOrder(
                    requestDto.getABisContext(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAOrderNumber(),
                    requestDto.getAOrderActionType(),
                    requestDto.getASubActionType(),
                    requestDto.getACompletionIndicator(),
                    requestDto.getAServiceLocation(),
                    requestDto.getAOriginalDueDate(),
                    requestDto.getASubsequentDueDate(),
                    requestDto.getAApplicationDate(),
                    requestDto.getAResendFlag(),
                    requestDto.getAWireCenter(),
                    requestDto.getARateCenter(),
                    requestDto.getATelephoneNumberOrderPairs(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, ValidateFacility2Return> validateFacility2(
      ValidateFacility2RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.validateFacility2(
                    requestDto.getABisContext(),
                    requestDto.getAServiceLocation(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getAWorkingTelephoneNumber(),
                    requestDto.getAMaxPairsToAnalyze(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAUverseOrderDueDate(),
                    requestDto.getANtis(),
                    requestDto.getAOrderActionType(),
                    requestDto.getASubActionType(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, ValidateFacility3Return> validateFacility3(
      ValidateFacility3RequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.validateFacility3(
                    requestDto.getABisContext(),
                    requestDto.getAServiceLocation(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getAWorkingTelephoneNumber(),
                    requestDto.getAMaxPairsToAnalyze(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getAOrderDueDate(),
                    requestDto.getANtis(),
                    requestDto.getAOrderAction(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, ValidateFacilityReturn> validateFacility(
      ValidateFacilityRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.validateFacility(
                    requestDto.getABisContext(),
                    requestDto.getAServiceLocation(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getAWorkingTelephoneNumber(),
                    requestDto.getAMaxPairsToAnalyze(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getASOACServiceOrderCorrectionSuffix(),
                    requestDto.getAUverseOrderDueDate(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, ValidateFacilityForProvisioningReturn> validateFacilityForProvisioning(
      ValidateFacilityForProvisioningRequestDto requestDto) {

    return Try.of(
            () ->
                legacyService.validateFacilityForProvisioning(
                    requestDto.getABisContext(),
                    requestDto.getAServiceLocation(),
                    requestDto.getARelatedCircuitID(),
                    requestDto.getAWorkingTelephoneNumber(),
                    requestDto.getAMaxPairsToAnalyze(),
                    requestDto.getASOACServiceOrderNumber(),
                    requestDto.getAOrderDueDate(),
                    requestDto.getANtis(),
                    requestDto.getAOrderAction(),
                    requestDto.getAProperties()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }
}
