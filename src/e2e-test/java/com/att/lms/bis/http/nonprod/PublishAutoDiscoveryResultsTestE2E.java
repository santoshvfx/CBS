package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.dto.rm.CreateFacilityAssignmentRequestDto;
import com.att.lms.bis.dto.rm.PingRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.dto.rm.PublishAutoDiscoveryResultsRequestDto;
import com.att.lms.bis.dto.rm.SelfTestRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
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
public class PublishAutoDiscoveryResultsTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "";

        BisContext aBisContext = getBisContext("RMDEV", "RMDEV");

        PublishAutoDiscoveryResultsRequestDto request = PublishAutoDiscoveryResultsRequestDto.builder()
                .aBisContext(aBisContext)
                .aCustomerTransportId("")
                .aBillingAccountNumber(new CompositeObjectKey())
                .aServiceAddress(new AddressOpt())
                .aProductSubscriptions(new ProductSubscription[0])
                .aTelephoneNumber(new StringOpt())
                .aAssignedProductId("")
                .aOrderAction(new OrderAction())
                .aProperties(new ObjectPropertySeqOpt())
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("publishAutoDiscoveryResults",
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
                        RmController.PUBLISH_AUTO_DISCOVERY_RESULTS_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body();

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
                subsectionWithPath("aCustomerTransportId").description("Customer Transport ID"),
                subsectionWithPath("aBillingAccountNumber").description("Billing Account Number"),
                subsectionWithPath("aServiceAddress").description("Service Address"),
                subsectionWithPath("aOrderAction").description("Order Action"),
                subsectionWithPath("aProductSubscriptions").description("Product Subscriptions"),
                subsectionWithPath("aTelephoneNumber").description("Telephone Number"),
                subsectionWithPath("aAssignedProductId").description("Assigned Product Id"),
                subsectionWithPath("aProperties").description("Properties Value")
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("BIS Context Property Value"),
                subsectionWithPath("aCustomerTransportId").description("Customer Transport ID"),
                subsectionWithPath("aBillingAccountNumber").description("Billing Account Number"),
                subsectionWithPath("aServiceAddress").description("Service Address"),
                subsectionWithPath("aOrderAction").description("Order Action Order"),
                subsectionWithPath("aProductSubscriptions").description("Product Subscriptions"),
                subsectionWithPath("aTelephoneNumber").description("Telephone Number"),
                subsectionWithPath("aAssignedProductId").description("Assigned Product Id"),
                subsectionWithPath("aProperties").description("Properties")
        };
    }
}
