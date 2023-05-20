package com.att.lms.bis.controller;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.controller.IController;
import com.att.lms.bis.dto.rm.*;
import com.att.lms.bis.service.RmService;
import com.sbc.eia.idl.rm.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.function.Function;

import static io.vavr.API.*;

@RestController
@Validated
@RequestMapping(BisApi.RM_BASE_URL)
public class RmController {

    @Autowired
    RmService rmService;

    public static final String PING_PATH_NAME = "ping";
    public static final String SELF_TEST_PATH_NAME = "selfTest";
    public static final String RETRIEVE_RESOURCES_FOR_SERVICE_PATH_NAME = "retrieveResourcesForService";
    public static final String RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_PATH_NAME = "retrieveServiceProvidersForService";
    public static final String RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_PATH_NAME = "retrieveServiceProvidersForResource";
    public static final String ACTIVATE_FIBER_SERVICE_TN_PORT_PATH_NAME = "activateFiberServiceTNPort";
    public static final String ADD_FIBER_SERVICE_TN_PORT_2_PATH_NAME = "addFiberServiceTNPort2";
    public static final String ADD_FIBER_SERVICE_TN_PORT_PATH_NAME = "addFiberServiceTNPort";
    public static final String CREATE_FACILITY_ASSIGNMENT_PATH_NAME = "createFacilityAssignment";
    public static final String INQUIRE_FIBER_SERVICE_TN_PORT_STATUS_PATH_NAME = "inquireFiberServiceTNPortStatus";
    public static final String PUBLISH_AUTO_DISCOVERY_RESULTS_PATH_NAME = "publishAutoDiscoveryResults";
    public static final String PUBLISH_RG_ACTIVATION_PATH_NAME = "publishRGActivation";
    public static final String SEND_ACTIVATE_TN_PORTING_SUBSCRIPTIONS_MSG_PATH_NAME = "sendActivateTNPortingSubscriptionsMsg";
    public static final String SEND_FACILITY_ASSIGNMENT_ORDER_2_PATH_NAME = "sendFacilityAssignmentOrder2";
    public static final String SEND_FACILITY_ASSIGNMENT_ORDER_3_PATH_NAME = "sendFacilityAssignmentOrder3";
    public static final String SEND_FACILITY_ASSIGNMENT_ORDER_4_PATH_NAME = "sendFacilityAssignmentOrder4";
    public static final String SEND_FACILITY_ASSIGNMENT_ORDER_PATH_NAME = "sendFacilityAssignmentOrder";
    public static final String SEND_TN_ASSIGNMENT_ORDER_PATH_NAME = "sendTNAssignmentOrder";
    public static final String SUBMIT_FIBER_SERVICE_TN_ASSIGNMENT_ORDER_2_PATH_NAME = "submitFiberServiceTNAssignmentOrder2";
    public static final String SUBMIT_FIBER_SERVICE_TN_ASSIGNMENT_ORDER_PATH_NAME = "submitFiberServiceTNAssignmentOrder";
    public static final String VALIDATE_FACILITY_2_PATH_NAME = "validateFacility2";
    public static final String VALIDATE_FACILITY_3_PATH_NAME = "validateFacility3";
    public static final String VALIDATE_FACILITY_PATH_NAME = "validateFacility";
    public static final String VALIDATE_FACILITY_FOR_PROVISIONING_PATH_NAME = "validateFacilityForProvisioning";





    @ApiOperation(value = "ping", response = PingReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = PING_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> ping(@RequestBody PingRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.ping(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveResourcesForService", response = RetrieveResourcesForServiceReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_RESOURCES_FOR_SERVICE_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveResourcesForService(@RequestBody RetrieveResourcesForServiceRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.retrieveResourcesForService(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveServiceProvidersForService", response = RetrieveServiceProvidersForServiceReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveServiceProvidersForService(@RequestBody RetrieveServiceProvidersForServiceRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.retrieveServiceProvidersForService(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveServiceProvidersForResource", response = RetrieveServiceProvidersForResourceReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveServiceProvidersForResource(@RequestBody RetrieveServiceProvidersForResourceRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.retrieveServiceProvidersForResource(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "activateFiberServiceTNPort", response = ActivateFiberServiceTNPortReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = ACTIVATE_FIBER_SERVICE_TN_PORT_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> activateFiberServiceTNPort(@RequestBody ActivateFiberServiceTNPortRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.activateFiberServiceTNPort(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "addFiberServiceTNPort2", response = AddFiberServiceTNPort2Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = ADD_FIBER_SERVICE_TN_PORT_2_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> addFiberServiceTNPort2(@RequestBody AddFiberServiceTNPort2RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.addFiberServiceTNPort2(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "addFiberServiceTNPort", response = AddFiberServiceTNPortReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = ADD_FIBER_SERVICE_TN_PORT_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> addFiberServiceTNPort(@RequestBody AddFiberServiceTNPortRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.addFiberServiceTNPort(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "createFacilityAssignment", response = CreateFacilityAssignmentReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = CREATE_FACILITY_ASSIGNMENT_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> createFacilityAssignment(@RequestBody CreateFacilityAssignmentRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.createFacilityAssignment(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "inquireFiberServiceTNPortStatus", response = InquireFiberServiceTNPortStatusReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = INQUIRE_FIBER_SERVICE_TN_PORT_STATUS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> inquireFiberServiceTNPortStatus(@RequestBody InquireFiberServiceTNPortStatusRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.inquireFiberServiceTNPortStatus(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "publishAutoDiscoveryResults", response = PublishAutoDiscoveryResultsReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = PUBLISH_AUTO_DISCOVERY_RESULTS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> publishAutoDiscoveryResults(@RequestBody PublishAutoDiscoveryResultsRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.publishAutoDiscoveryResults(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "publishRGActivation", response = PublishRGActivationReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = PUBLISH_RG_ACTIVATION_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> publishRGActivation(@RequestBody PublishRGActivationRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.publishRGActivation(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "selfTest", response = SelfTestReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SELF_TEST_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> selfTest(@RequestBody SelfTestRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.selfTest(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendActivateTNPortingSubscriptionMsg", response = SendActivateTNPortingSubscriptionMsgReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_ACTIVATE_TN_PORTING_SUBSCRIPTIONS_MSG_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendActivateTNPortingSubscriptionMsg(@RequestBody SendActivateTNPortingSubscriptionMsgRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendActivateTNPortingSubscriptionMsg(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendFacilityAssignmentOrder2", response = SendFacilityAssignmentOrder2Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_FACILITY_ASSIGNMENT_ORDER_2_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendFacilityAssignmentOrder2(@RequestBody SendFacilityAssignmentOrder2RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendFacilityAssignmentOrder2(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendFacilityAssignmentOrder3", response = SendFacilityAssignmentOrder3Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_FACILITY_ASSIGNMENT_ORDER_3_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendFacilityAssignmentOrder3(@RequestBody SendFacilityAssignmentOrder3RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendFacilityAssignmentOrder3(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendFacilityAssignmentOrder4", response = SendFacilityAssignmentOrder4Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_FACILITY_ASSIGNMENT_ORDER_4_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendFacilityAssignmentOrder4(@RequestBody SendFacilityAssignmentOrder4RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendFacilityAssignmentOrder4(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendFacilityAssignmentOrder", response = SendFacilityAssignmentOrderReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_FACILITY_ASSIGNMENT_ORDER_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendFacilityAssignmentOrder(@RequestBody SendFacilityAssignmentOrderRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendFacilityAssignmentOrder(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "sendTNAssignmentOrder", response = SendTNAssignmentOrderReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SEND_TN_ASSIGNMENT_ORDER_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> sendTNAssignmentOrder(@RequestBody SendTNAssignmentOrderRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.sendTNAssignmentOrder(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "submitFiberServiceTNAssignmentOrder2", response = SubmitFiberServiceTNAssignmentOrder2Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SUBMIT_FIBER_SERVICE_TN_ASSIGNMENT_ORDER_2_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> submitFiberServiceTNAssignmentOrder2(@RequestBody SubmitFiberServiceTNAssignmentOrder2RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.submitFiberServiceTNAssignmentOrder2(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "submitFiberServiceTNAssignmentOrder", response = SubmitFiberServiceTNAssignmentOrderReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SUBMIT_FIBER_SERVICE_TN_ASSIGNMENT_ORDER_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> submitFiberServiceTNAssignmentOrder(@RequestBody SubmitFiberServiceTNAssignmentOrderRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.submitFiberServiceTNAssignmentOrder(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "validateFacility2", response = ValidateFacility2Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = VALIDATE_FACILITY_2_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> validateFacility2(@RequestBody ValidateFacility2RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.validateFacility2(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "validateFacility3", response = ValidateFacility3Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = VALIDATE_FACILITY_3_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> validateFacility3(@RequestBody ValidateFacility3RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.validateFacility3(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "validateFacility", response = ValidateFacilityReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = VALIDATE_FACILITY_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> validateFacility(@RequestBody ValidateFacilityRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.validateFacility(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "validateFacilityForProvisioning", response = ValidateFacilityForProvisioningRequestDto.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = VALIDATE_FACILITY_FOR_PROVISIONING_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> validateFacilityForProvisioning(@RequestBody ValidateFacilityForProvisioningRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  rmService.validateFacilityForProvisioning(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }
}
