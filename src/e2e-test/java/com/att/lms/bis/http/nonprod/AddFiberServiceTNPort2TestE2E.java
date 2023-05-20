package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.dto.rm.*;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.rm_ls_types.AddFiberServiceTNPortTelephoneNumberRequest;
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
public class AddFiberServiceTNPort2TestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        BisContext aBisContext = getBisContext("RMDEV", "RMDEV");

        AddFiberServiceTNPort2RequestDto request = AddFiberServiceTNPort2RequestDto.builder()
                .aBisContext(aBisContext)
                .aState("State")
                .aSOACServiceOrderNumber(new StringOpt())
                .aSOACServiceOrderCorrectionSuffix(new StringOpt())
                .aOrderNumber(new StringOpt())
                .aOldServiceProviderId(new StringOpt())
                .aNewServiceProviderId(new StringOpt())
                .aOrderDueDate(new EiaDate())
                .aTelephoneNumbers(new AddFiberServiceTNPortTelephoneNumberRequest[0])
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("addFiberServiceTNPort2",
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
                        RmController.ADD_FIBER_SERVICE_TN_PORT_2_PATH_NAME))
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body();

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),

                subsectionWithPath("aState").description("State"),
                subsectionWithPath("aSOACServiceOrderNumber").description("SOAC Service Order Number"),
                subsectionWithPath("aSOACServiceOrderCorrectionSuffix").description("SOAC Service Order Correction Suffix"),
                subsectionWithPath("aOrderNumber").description("Order Number"),
                subsectionWithPath("aOldServiceProviderId").description("Old Service Provider Id"),
                subsectionWithPath("aNewServiceProviderId").description("New Service Provider Id"),
                subsectionWithPath("aOrderDueDate").description("Order Due Date"),
                subsectionWithPath("aTelephoneNumbers").description("Telephone Numbers")
        };
    }
}


