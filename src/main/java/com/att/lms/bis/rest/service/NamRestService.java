package com.att.lms.bis.rest.service;

import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.common.config.settings.IMappedHttpSettings;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.nam.PingRequestDto;
import com.att.lms.bis.dto.nam.GetNetworkAddressRequestDto;
import com.att.lms.bis.rest.config.settings.RestConstants;
import com.att.lms.bis.rest.dao.NamRestDao;
import com.sbc.eia.idl.nam.PingReturn;
import com.sbc.eia.idl.nam.GetNetworkAddressReturn;
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
public class NamRestService {

  NamRestDao namRestDao;
  IMappedHttpSettings bisRestEndpointSettings;

  public NamRestService(NamRestDao namRestDao, IMappedHttpSettings bisRestEndpointSettings) {
    this.namRestDao = namRestDao;
    this.bisRestEndpointSettings = bisRestEndpointSettings;
  }

  public Either<Throwables, Option<PingReturn>> ping(
      final PingRequestDto requestDto, final ParameterizedTypeReference<PingReturn> type) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
    headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
    headers.setContentType(APPLICATION_JSON);

    return namRestDao.ping(
        bisRestEndpointSettings.getRestEndpoints().get(RestConstants.NAM),
        requestDto,
        type,
        headers);
  }

  public Either<Throwables, Option<GetNetworkAddressReturn>> getNetworkAddress(
      final GetNetworkAddressRequestDto requestDto,
      final ParameterizedTypeReference<GetNetworkAddressReturn> type) {

    HttpHeaders headers = new HttpHeaders();
    headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
    headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
    headers.setContentType(APPLICATION_JSON);

    return namRestDao.getNetworkAddress(
        bisRestEndpointSettings.getRestEndpoints().get(RestConstants.NAM),
        requestDto,
        type,
        headers);
  }
}
