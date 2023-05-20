package com.att.lms.bis.rest.service;

import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.common.config.settings.IMappedHttpSettings;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.sm.GetServiceLocationRequestDto;
import com.att.lms.bis.dto.sm.PingRequestDto;
import com.att.lms.bis.dto.sm.RetrieveSubscriptionAccountsForAffiliatesByAccountNumberRequestDto;
import com.att.lms.bis.dto.sm.RetrieveSubscriptionAccountsForServiceRequestDto;
import com.att.lms.bis.rest.config.settings.RestConstants;
import com.att.lms.bis.rest.dao.SmRestDao;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;
import com.sbc.eia.idl.sm.PingReturn;
import com.sbc.eia.idl.sm.SubscriptionAccountReturn;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class SmRestService {

  SmRestDao smRestDao;
  IMappedHttpSettings bisRestEndpointSettings;

  public SmRestService(SmRestDao smRestDao, IMappedHttpSettings bisRestEndpointSettings) {
    this.smRestDao = smRestDao;
    this.bisRestEndpointSettings = bisRestEndpointSettings;
  }

  public Either<Throwables, Option<PingReturn>> ping(
      final PingRequestDto requestDto, final ParameterizedTypeReference<PingReturn> type) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
    headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
    headers.setContentType(APPLICATION_JSON);

    return smRestDao.ping(
        bisRestEndpointSettings.getRestEndpoints().get(RestConstants.SM),
        requestDto,
        type,
        headers);
  }

  public Either<Throwables, Option<SubscriptionAccountReturn>> retrieveSubscriptionAccountsForService(
      final RetrieveSubscriptionAccountsForServiceRequestDto requestDto,
      final ParameterizedTypeReference<SubscriptionAccountReturn> type) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
    headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
    headers.setContentType(APPLICATION_JSON);

    return smRestDao.retrieveSubscriptionAccountsForService(
        bisRestEndpointSettings.getRestEndpoints().get(RestConstants.SM),
        requestDto,
        type,
        headers);
  }

  public Either<Throwables, Option<SubscriptionAccountReturn>> retrieveSubscriptionAccountsForAffiliatesByAccountNumber(
          final RetrieveSubscriptionAccountsForAffiliatesByAccountNumberRequestDto requestDto,
          final ParameterizedTypeReference<SubscriptionAccountReturn> type) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
    headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
    headers.setContentType(APPLICATION_JSON);

    return smRestDao.retrieveSubscriptionAccountsForAffiliatesByAccountNumber(
            bisRestEndpointSettings.getRestEndpoints().get(RestConstants.SM),
            requestDto,
            type,
            headers);
  }
}
