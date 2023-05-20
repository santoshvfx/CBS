package com.att.lms.bis.http.sat_prod;//package com.att.lms.bis.http.prod;
//
//import com.att.lms.bis.common.BisApi;
//import com.att.lms.bis.common.CommonApi;
//import com.att.lms.bis.controller.RmController;
//import com.att.lms.bis.AbstractControllerE2E;
//import com.att.lms.bis.dto.rm.RetrieveServiceProvidersForServiceRequestDto;
//import com.att.lms.bis.security.User;
//import com.sbc.eia.bis.framework.BisContextManager;
//import com.sbc.eia.idl.bis_types.BisContext;
//import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
//import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
//import com.sbc.eia.idl.types.ObjectKey;
//import com.sbc.eia.idl.types.ObjectProperty;
//import io.restassured.http.ContentType;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.restdocs.payload.FieldDescriptor;
//import org.springframework.test.context.TestPropertySource;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
//
//@TestPropertySource(locations = { "classpath:application-e2e-test-non271prod.properties", "classpath:application-e2e-real.properties" })
//public class RetrieveServiceProvidersForServiceTestE2ENon271Prod extends AbstractControllerE2E {
//
//    @ParameterizedTest
//    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
//    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {
//
//        String EXPECTED_RESPONSE = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("RmServiceProvidersForServiceResponseExample.txt").toURI())));
//
//        BisContextManager bcm = new BisContextManager();
//
//        ObjectProperty[] bisContextProperties = new ObjectProperty[4];
//        bisContextProperties[0] = new ObjectProperty("ServiceCenter","CA");
//        bisContextProperties[1] = new ObjectProperty("Application","AM_BIS");
//        bisContextProperties[2] = new ObjectProperty("CustomerName","AM_BIS");
//        bisContextProperties[3] = new ObjectProperty("BusinessUnit", "SBCSout");
//        bcm.setBisContext(new BisContext(bisContextProperties));
//
//        ObjectKey[] aServiceTypeHandles = new ObjectKey[2];
//        aServiceTypeHandles[0] = new ObjectKey(ServiceTypeHandleObjectKey.LPIC, BisTypesObjectKeyKind.SERVICETYPE);
//        aServiceTypeHandles[1] = new ObjectKey(ServiceTypeHandleObjectKey.PIC, BisTypesObjectKeyKind.SERVICETYPE);
//
//        RetrieveServiceProvidersForServiceRequestDto request = RetrieveServiceProvidersForServiceRequestDto.builder()
//                .aBisContext(bcm.getBisContext())
//                .aServiceHandle(new ObjectKey("7149922784", BisTypesObjectKeyKind.TELEPHONENUMBER))
//                .aServiceTypeHandles(aServiceTypeHandles)
//                .build();
//
//        String requestBodyAsJSON = objectMapper.writeValueAsString(request);
//
//        String responseBody = given(documentationSpec)
//                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
//                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
//                .accept(APPLICATION_JSON.toString())
//                .filter(document("retrieveServiceProvidersForService",
//                        preprocessRequest(maskCredentials(),prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(getRequestFieldDescriptor()),
//                        responseFields(getResponseFieldDescriptor())))
//                .auth()
//                .preemptive()
//                .basic(user.getUserId(), user.getPassword())
//                .contentType(ContentType.JSON)
//                .when()
//                .request().body(requestBodyAsJSON)
//                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
//                        BisApi.RM_BASE_URL,
//                        "/",
//                        RmController.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_PATH_NAME))
//                .then()
//                .statusCode(httpStatus)
//                .extract()
//                .response()
//                .body().toString();
//
//        assertThat(cleanResponse(responseBody)).isEqualTo(EXPECTED_RESPONSE);
//    }
//
//    // TODO: Update with correct request field descriptors
//    private FieldDescriptor[] getRequestFieldDescriptor() {
//        return new FieldDescriptor[] {
//                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
//                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
//                fieldWithPath("aServiceHandle.aValue").description("RM BIS Service Handle Value"),
//                fieldWithPath("aServiceHandle.aKind").description("RM BIS Service Handle Kind"),
//                fieldWithPath("aServiceTypeHandles[]").description("RM BIS Resource Role Names"),
//                fieldWithPath("aServiceTypeHandles[].aValue").description(""),
//                fieldWithPath("aServiceTypeHandles[].aKind").description("")
//        };
//    }
//
//    // TODO: Update with correct response field descriptors
//    private FieldDescriptor[] getResponseFieldDescriptor() {
//        return new FieldDescriptor[] {
//                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
//                fieldWithPath("aContext.aProperties[].aValue").description("Contact Property Value"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceTypeHandle.aValue").description("Service Provider for Service Type Service Type Handle Value"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceTypeHandle.aKind").description("Service Provider for Service Type Service Type Handle Kind"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aServiceProviderHandle.aValue").description("Service Provider for Service Type ServiceProviders Handle Value"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aServiceProviderHandle.aKind").description("Service Provider for Service Type ServiceProviders Handle Kind"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.__discriminator").description("Service Provider for Service Type Operating Company Numbers Discriminator"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.__uninitialized").description("Service Provider for Service Type Operating Company Numbers Uninitialized"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.___theValue").description("Service Provider for Service Type Operating Company Numbers Value"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties").description("Service Provider for Service Type Properties"),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties[].aTag").description(""),
//                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties[].aValue").description("")
//        };
//    }
//}
