package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.dto.rm.SendFacilityAssignmentOrder2RequestDto;
import com.att.lms.bis.dto.rm.SendFacilityAssignmentOrder3RequestDto;
import com.att.lms.bis.dto.rm.SendFacilityAssignmentOrder4RequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.types.*;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test.properties", "classpath:application-e2e-real.properties" })
public class SendFacilityAssignmentOrder4TestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        BisContext aBisContext = getBisContext("RMDEV", "RMDEV");

        SendFacilityAssignmentOrder4RequestDto request = SendFacilityAssignmentOrder4RequestDto.builder()
                .aBisContext(aBisContext)
                .aSOACServiceOrderNumber("SOAC Service Order Number")
                .aSOACServiceOrderCorrectionSuffix("SOAC Service Order Correction Suffix")
                .aSOACServiceOrderCorrectionSuffix("SOAC Service Order Correction Suffix")
                .aNetworkType("Network Type")
                .aOldNetworkType(new StringOpt())
                .aOrderActionId("Order Action Id")
                .aOrderNumber("Order Number")
                .aOrderActionType("Order Action Type")
                .aCompletionIndicator(new BooleanOpt())
                .aSubActionType(new StringOpt())
                .aCircuitId("Circuit Id")
                .aRelatedCircuitID(new StringOpt())
                .aSecondaryCircuitId(new StringOpt())
                .aSecondaryRelatedCircuitID(new StringOpt())
                .aServiceLocation(new Location())
                .aOriginalDueDate(new EiaDate())
                .aSubsequentDueDate(new EiaDateOpt())
                .aApplicationDate(new EiaDate())
                .aRelatedServiceOrderNumber(new StringOpt())
                .aLineShareDisconnectFlag(new BooleanOpt())
                .aClassOfService("Class Of Service")
                .aResendFlag(new BooleanOpt())
                .aBillingAccountNumber(new CompositeObjectKey())
                .aInterceptRedirectIndicator(new BooleanOpt())
                .aDryloopRelatedCircuitId(new StringOpt())
                .aSecondaryDryloopRelatedCircuitId(new StringOpt())
                .aDSLDisconnectCircuitIds(new StringSeqOpt())
                .aExceptionRoutingIndicator(new StringOpt())
                .aProperties(new ObjectPropertySeqOpt()).build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("sendFacilityAssignmentOrder4",
                        preprocessRequest(maskCredentials(),prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(getRequestFieldDescriptor()),
                        responseFields(getErrorResponseFieldDescriptor())))
                .auth()
                .preemptive()
                .basic(user.getUserId(), user.getPassword())
                .contentType(ContentType.JSON)
                .when()
                .request().body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        BisApi.RM_BASE_URL,
                        "/",
                        RmController.SEND_FACILITY_ASSIGNMENT_ORDER_4_PATH_NAME))
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body();

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property"),
                subsectionWithPath("aSOACServiceOrderNumber").description("SOAC Service Order Number"),
                subsectionWithPath("aSOACServiceOrderCorrectionSuffix").description("SOAC Service Order Correction Suffix"),
                subsectionWithPath("aNetworkType").description("Network Type"),

                subsectionWithPath("aOldNetworkType").description("Old Network Type"),

                subsectionWithPath("aOrderActionId").description("Order Action Id"),
                subsectionWithPath("aOrderNumber").description("Order Number"),
                subsectionWithPath("aOrderActionType").description("Order Action Type"),
                subsectionWithPath("aCircuitId").description("Circuit Id"),

                subsectionWithPath("aRelatedCircuitID").description("Related Circuit Id"),

                subsectionWithPath("aSecondaryCircuitId").description("Secondary Circuit Id"),

                subsectionWithPath("aSecondaryRelatedCircuitID").description("Secondary Related Circuit Id"),

                subsectionWithPath("aSubActionType").description("Sub Action Type"),

                subsectionWithPath("aCompletionIndicator").description("Completion Indicator"),

                subsectionWithPath("aServiceLocation").description("Service Location"),

                subsectionWithPath("aOriginalDueDate").description("Original Due Date"),

                subsectionWithPath("aSubsequentDueDate").description("Subsequent Due Date"),

                subsectionWithPath("aApplicationDate").description("Application Date"),

                subsectionWithPath("aRelatedServiceOrderNumber").description("Related Service Order Number"),

                subsectionWithPath("aLineShareDisconnectFlag").description("Line Share Disconnect Flag"),

                subsectionWithPath("aClassOfService").description("Class Of Service"),

                subsectionWithPath("aResendFlag").description("Resend Flag"),

                subsectionWithPath("aInterceptRedirectIndicator").description("Intercept Redirect Indicator"),

                subsectionWithPath("aDryloopRelatedCircuitId").description("Dryloop Related Circuit Id"),

                subsectionWithPath("aSecondaryDryloopRelatedCircuitId").description("Secondary Dryloop Related Circuit Id"),

                subsectionWithPath("aDSLDisconnectCircuitIds").description("DSL Disconnect Circuit Ids"),

                subsectionWithPath("aExceptionRoutingIndicator").description("Exception Routing Indicator"),

                subsectionWithPath("aBillingAccountNumber").description("Billing Account Number"),

                subsectionWithPath("aProperties").description("Properties")
        };
    }
}
