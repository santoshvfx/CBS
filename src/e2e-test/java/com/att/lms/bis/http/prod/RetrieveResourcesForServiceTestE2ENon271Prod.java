package com.att.lms.bis.http.prod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.dto.rm.RetrieveResourcesForServiceRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.rm_types.ResourceRoleName;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test-non271prod.properties", "classpath:application-e2e-real.properties" })
public class RetrieveResourcesForServiceTestE2ENon271Prod extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"AM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"OH\"},{\"aTag\":\"CustomerName\",\"aValue\":\"AM_BIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]},\"aResourceRoles\":[{\"aRoleName\":\"ServingSwitch\",\"aResourceHandle\":{\"aValue\":\"TOLDOH47DS1\",\"aKind\":\"\"},\"aIntegratedDigitalLoopCarrierIndicator\":{\"___theValue\":false,\"__discriminator\":false,\"__uninitialized\":false},\"aResourceProvider\":{\"aResourceProviderHandle\":{\"aValue\":\"\",\"aKind\":\"\"},\"aResourceProviderTypeHandle\":{\"aValue\":\"\",\"aKind\":\"\"},\"aProperties\":[{\"aTag\":\"OPERATINGCOMPANYNAME\",\"aValue\":\"AMERITECH OHIO\"},{\"aTag\":\"OPERATINGCOMPANYNUMBER\",\"aValue\":\"9321\"},{\"aTag\":\"CONTACTNAME\",\"aValue\":\"BROWN,ROWENA\"},{\"aTag\":\"CONTACTTELEPHONENUMBER\",\"aValue\":\"925-543-1526\"},{\"aTag\":\"SPECIALSERVICECODE\",\"aValue\":\"N\"},{\"aTag\":\"CENTRALOFFICECODE\",\"aValue\":\"EOC\"}]}}]}";
        BisContextManager bcm = new BisContextManager();


        ObjectProperty[] bisContextProperties = new ObjectProperty[3];
        bisContextProperties[1] = new ObjectProperty("ServiceCenter","OH");
        bisContextProperties[0] = new ObjectProperty("Application","AM_BIS");
        bisContextProperties[2] = new ObjectProperty("CustomerName","AM_BIS");
        bcm.setBisContext(new BisContext(bisContextProperties));

        RetrieveResourcesForServiceRequestDto request = RetrieveResourcesForServiceRequestDto.builder()
                .aBisContext(bcm.getBisContext())
                .aServiceHandle(new ObjectKey("4194730402", BisTypesObjectKeyKind.TELEPHONENUMBER))
                .aResourceRoleNames(new String[] {ResourceRoleName.SERVINGSWITCH})
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveResourcesForService-non271prod",
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
                        RmController.RETRIEVE_RESOURCES_FOR_SERVICE_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body().asString();

        assertThat(cleanResponse(responseBody)).isEqualTo(EXPECTED_RESPONSE);

    }

    // TODO: Update with correct request field descriptors
    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
                fieldWithPath("aServiceHandle.aValue").description("Service Handle Value"),
                fieldWithPath("aServiceHandle.aKind").description("Service Handle Kind"),
                fieldWithPath("aResourceRoleNames[]").description("Resource Role Names")
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Contact Property Value"),

                fieldWithPath("aResourceRoles[]").description("Resource Roles"),
                fieldWithPath("aResourceRoles[].aRoleName").description("Resource Roles Role Name"),
                fieldWithPath("aResourceRoles[].aResourceHandle").description("Resource Roles Resource Handle"),
                fieldWithPath("aResourceRoles[].aResourceHandle.aValue").description("Resource Roles Resource Handle Value"),
                fieldWithPath("aResourceRoles[].aResourceHandle.aKind").description("Resource Roles Resource Handle Kind"),
                fieldWithPath("aResourceRoles[].aIntegratedDigitalLoopCarrierIndicator.___theValue")
                        .description("Resource Roles Integrated Digital Loop Carrier Indicator Value"),
                fieldWithPath("aResourceRoles[].aIntegratedDigitalLoopCarrierIndicator.__discriminator")
                        .description("Resource Roles Integrated Digital Loop Carrier Indicator Discriminator"),
                fieldWithPath("aResourceRoles[].aIntegratedDigitalLoopCarrierIndicator.__uninitialized")
                        .description("Resource Roles Integrated Digital Loop Carrier Indicator Uninitialized"),

                fieldWithPath("aResourceRoles[].aResourceProvider.aResourceProviderHandle.aValue")
                        .description("Resource Roles Resource Provider Handle Value"),
                fieldWithPath("aResourceRoles[].aResourceProvider.aResourceProviderHandle.aKind")
                        .description("Resource Roles Resource Provider Handle Kind"),

                fieldWithPath("aResourceRoles[].aResourceProvider.aResourceProviderTypeHandle.aValue")
                        .description("Resource Roles Resource Provider Type Handle Value"),
                fieldWithPath("aResourceRoles[].aResourceProvider.aResourceProviderTypeHandle.aKind")
                        .description("Resource Roles Resource Provider Type Handle Kind"),
                fieldWithPath("aResourceRoles[].aResourceProvider.aProperties[].aTag").description(""),
                fieldWithPath("aResourceRoles[].aResourceProvider.aProperties[].aValue").description(""),

                fieldWithPath("aResourceRoles[].aResourceProvider.aProperties").description("Resource Roles Resource Provider Properties"),
        };
    }
}
