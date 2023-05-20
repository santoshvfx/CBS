package com.att.lms.bis.http.prod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.RmController;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.dto.rm.RetrieveServiceProvidersForResourceRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
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
public class RetrieveServiceProvidersForResourceTestE2ENon271Prod extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"AM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"CA\"},{\"aTag\":\"CustomerName\",\"aValue\":\"AM_BIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]},\"aServiceProvidersForServiceType\":[{\"aServiceTypeHandle\":{\"aValue\":\"LocalVoice\",\"aKind\":\"LocalVoice\"},\"aServiceProviders\":[{\"aServiceProviderHandle\":{\"aValue\":\"\",\"aKind\":\"\"},\"aOperatingCompanyNumbers\":{\"___theValue\":[{\"aCategory\":{\"__value\":0},\"aJurisdiction\":\"\",\"aValue\":\"9740\"}],\"__discriminator\":true,\"__uninitialized\":false},\"aProperties\":[{\"aTag\":\"Name\",\"aValue\":\"PACIFIC BELL\"}]}]}]}";

        BisContextManager bcm = new BisContextManager();

        ObjectProperty[] bisContextProperties = new ObjectProperty[3];
        bisContextProperties[1] = new ObjectProperty("ServiceCenter","CA");
        bisContextProperties[0] = new ObjectProperty("Application","AM_BIS");
        bisContextProperties[2] = new ObjectProperty("CustomerName","AM_BIS");
        bcm.setBisContext(new BisContext(bisContextProperties));


        ObjectKey[] aServiceTypeHandles = new ObjectKey[1];
        aServiceTypeHandles[0] = new ObjectKey(ServiceTypeHandleObjectKey.LOCAL_VOICE, BisTypesObjectKeyKind.SERVICETYPE);

        RetrieveServiceProvidersForResourceRequestDto request = RetrieveServiceProvidersForResourceRequestDto.builder()
                .aBisContext(bcm.getBisContext())
                .aResourceHandle("ALGHCA11RS0")
                .aServiceTypeHandles(aServiceTypeHandles)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveServiceProvidersForResource-non271prod",
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
                        RmController.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_PATH_NAME))
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
                fieldWithPath("aResourceHandle").description("RM BIS Resource Handle"),
                fieldWithPath("aServiceTypeHandles[]").description("RM BIS Resource Role Names"),
                fieldWithPath("aServiceTypeHandles[].aValue").description(""),
                fieldWithPath("aServiceTypeHandles[].aKind").description("")
        };
    }

    // TODO: Update with correct response field descriptors
    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Contact Property Value"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceTypeHandle.aValue").description("Service Provider for Service Type Service Type Handle Value"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceTypeHandle.aKind").description("Service Provider for Service Type Service Type Handle Kind"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aServiceProviderHandle.aValue").description("Service Provider for Service Type ServiceProviders Handle Value"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aServiceProviderHandle.aKind").description("Service Provider for Service Type ServiceProviders Handle Kind"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.__discriminator").description("Service Provider for Service Type Operating Company Numbers Discriminator"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.__uninitialized").description("Service Provider for Service Type Operating Company Numbers Initialized"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.___theValue").description("Service Provider for Service Type Operating Company Numbers Value"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties").description("Service Provider for Service Type Properties"),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.___theValue[].aCategory.__value").description(""),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.___theValue[].aJurisdiction").description(""),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aOperatingCompanyNumbers.___theValue[].aValue").description(""),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties[].aTag").description(""),
                fieldWithPath("aServiceProvidersForServiceType[].aServiceProviders[].aProperties[].aValue").description("")
        };
    }
}
