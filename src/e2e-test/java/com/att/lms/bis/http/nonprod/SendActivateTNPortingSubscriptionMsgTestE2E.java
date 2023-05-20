package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.dto.rm.PingRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.dto.rm.SelfTestRequestDto;
import com.att.lms.bis.dto.rm.SendActivateTNPortingSubscriptionMsgRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test.properties", "classpath:application-e2e-real.properties" })
public class SendActivateTNPortingSubscriptionMsgTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "";

        BisContext aBisContext = getBisContext("RMDEV", "RMDEV");

        SendActivateTNPortingSubscriptionMsgRequestDto request = SendActivateTNPortingSubscriptionMsgRequestDto.builder()
                .aBisContext(aBisContext)
                .aSOACServiceOrderNumber("Number")
                .aSOACServiceOrderCorrectionSuffix("Suffix")
                .aLocalServiceProviderId("Provider ID")
                .aTelephoneNumbers(new String[]{"5555555555"})
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("sendActivateTNPortingSubscriptionMsg",
                        preprocessRequest(maskCredentials(),prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(getRequestFieldDescriptor()),
                        responseFields(getResponseFieldDescriptor())))
                .auth()
                .preemptive()
                .basic(user.getUserId(), user.getPassword())
                .contentType(ContentType.JSON)
                .when()
                .request().body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        BisApi.RM_BASE_URL,
                        "/",
                        RmController.SEND_ACTIVATE_TN_PORTING_SUBSCRIPTIONS_MSG_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body();

    }

    // TODO: Update with correct request field descriptors
    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
                subsectionWithPath("aSOACServiceOrderNumber").description("SOAC Service Order Number"),
                subsectionWithPath("aSOACServiceOrderCorrectionSuffix").description("SOAC Service Order Correction Suffix"),
                subsectionWithPath("aLocalServiceProviderId").description("Local Service Provider Id"),
                subsectionWithPath("aTelephoneNumbers").description("Telephone Numbers"),
        };
    }

    // TODO: Update with correct response field descriptors
    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Contact Property Value"),
                subsectionWithPath("aSoacServiceOrderNumber").description("Soac Service Order Number"),
                subsectionWithPath("aSOACServiceOrderCorrectionSuffix").description("Soac Service Order Correction Suffix"),
                subsectionWithPath("aTelephoneNumberRequestStatus").description("Telephone Number Request Status")
        };
    }
}
