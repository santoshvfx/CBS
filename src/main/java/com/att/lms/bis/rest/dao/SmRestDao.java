package com.att.lms.bis.rest.dao;

import com.att.lms.bis.common.config.settings.IHttpSettings;
import com.att.lms.bis.common.dao.impl.RestEndpointDao;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.common.rest.Restful;
import com.att.lms.bis.common.url.UriPath;
import com.att.lms.bis.dto.sm.GetServiceLocationRequestDto;
import com.att.lms.bis.dto.sm.PingRequestDto;
import com.att.lms.bis.dto.sm.RetrieveSubscriptionAccountsForAffiliatesByAccountNumberRequestDto;
import com.att.lms.bis.dto.sm.RetrieveSubscriptionAccountsForServiceRequestDto;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;
import com.sbc.eia.idl.sm.PingReturn;
import com.sbc.eia.idl.sm.SubscriptionAccountReturn;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SmRestDao extends RestEndpointDao implements Restful {

    public static final String PING_PATH_NAME = "ping";
    public static final String RETRIEVE_SUBSCRIPTION_ACCOUNTS_FOR_SERVICE_PATH_NAME = "retrieveSubscriptionAccountsForService";
    public static final String RETRIEVE_SUBSCRIPTION_ACCOUNTS_FOR_AFFILIATES_BY_ACCOUNT_NUMBER_PATH_NAME = "retrieveSubscriptionAccountsForAffiliatesByAccountNumber";

    public Either<Throwables, Option<PingReturn>> ping(
            IHttpSettings httpSettings,
            PingRequestDto requestDto,
            ParameterizedTypeReference<PingReturn> type,
            MultiValueMap<String, String> headers) {

        Map<String, String> parameters = HashMap.empty();

        return tryHttp(
                httpPostFunc(UriPath.authorizationTokenBase64Encoded(httpSettings),
                        type, requestDto, headers),
                UriPath.builder().iHttpSettings(httpSettings)
                        .parameters(parameters)
                        .appendPath(httpSettings.getPath())
                        .build()
                        .toString() + PING_PATH_NAME,
                new Object() {
                }.getClass().getEnclosingMethod().getName());
    }

    public Either<Throwables, Option<SubscriptionAccountReturn>> retrieveSubscriptionAccountsForService(
            IHttpSettings httpSettings,
            RetrieveSubscriptionAccountsForServiceRequestDto requestDto,
            ParameterizedTypeReference<SubscriptionAccountReturn> type,
            MultiValueMap<String, String> headers) {

        Map<String, String> parameters = HashMap.empty();

        return tryHttp(
                httpPostFunc(UriPath.authorizationTokenBase64Encoded(httpSettings),
                        type, requestDto, headers),
                UriPath.builder().iHttpSettings(httpSettings)
                        .parameters(parameters)
                        .appendPath(httpSettings.getPath())
                        .build()
                        .toString() + RETRIEVE_SUBSCRIPTION_ACCOUNTS_FOR_SERVICE_PATH_NAME,
                new Object() {
                }.getClass().getEnclosingMethod().getName());
    }

    public Either<Throwables, Option<SubscriptionAccountReturn>> retrieveSubscriptionAccountsForAffiliatesByAccountNumber(
            IHttpSettings httpSettings,
            RetrieveSubscriptionAccountsForAffiliatesByAccountNumberRequestDto requestDto,
            ParameterizedTypeReference<SubscriptionAccountReturn> type,
            MultiValueMap<String, String> headers) {

        Map<String, String> parameters = HashMap.empty();

        return tryHttp(
                httpPostFunc(UriPath.authorizationTokenBase64Encoded(httpSettings),
                        type, requestDto, headers),
                UriPath.builder().iHttpSettings(httpSettings)
                        .parameters(parameters)
                        .appendPath(httpSettings.getPath())
                        .build()
                        .toString() + RETRIEVE_SUBSCRIPTION_ACCOUNTS_FOR_AFFILIATES_BY_ACCOUNT_NUMBER_PATH_NAME,
                new Object() {
                }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public Logger log() {
        return log;
    }

    @Override
    public Function<String, RestTemplate> restTemplate() {
        return this.getPartialRestTemplateBuilder();
    }
}