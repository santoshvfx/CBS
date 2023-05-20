package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.dto.rm.CreateFacilityAssignmentRequestDto;
import com.att.lms.bis.dto.rm.PingRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.dto.rm.SelfTestRequestDto;
import com.att.lms.bis.dto.rm.ValidateFacilityRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test.properties", "classpath:application-e2e-real.properties" })
public class ValidateFacilityTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "";

        BisContext aBisContext = getBisContext("RMDEV", "RMDEV");

        ValidateFacilityRequestDto request = ValidateFacilityRequestDto.builder()
                .aBisContext(aBisContext)
                .aMaxPairsToAnalyze(new ShortOpt())
                .aRelatedCircuitID(new StringOpt())
                .aWorkingTelephoneNumber(new StringOpt())
                .aSOACServiceOrderNumber(new StringOpt())
                .aSOACServiceOrderCorrectionSuffix(new StringOpt())
                .aUverseOrderDueDate(new EiaDateOpt())
                .aServiceLocation(new Location())
                .aProperties(new ObjectPropertySeqOpt())
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("validateFacility",
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
                        RmController.VALIDATE_FACILITY_PATH_NAME))
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body();

    }

    // TODO: Update with correct request field descriptors
    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property"),
                subsectionWithPath("aServiceLocation").description("Service Location"),

                subsectionWithPath("aRelatedCircuitID").description("Related Circuit ID"),

                subsectionWithPath("aWorkingTelephoneNumber").description("Working Telephone Number"),

                subsectionWithPath("aMaxPairsToAnalyze").description("Max Pairs To Analyze"),

                subsectionWithPath("aSOACServiceOrderNumber").description("SOAC Service Order Number"),

                subsectionWithPath("aSOACServiceOrderCorrectionSuffix").description("SOAC Service Order Correction Suffix"),

                subsectionWithPath("aUverseOrderDueDate").description("Uverse Order Due Date"),

                subsectionWithPath("aProperties").description("Properties")
        };
    }
}
