package com.att.lms.bis.rest.facade;

import com.att.lms.bis.LmsRestResponseHandler;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.nam.GetNetworkAddressRequestDto;
import com.att.lms.bis.dto.nam.PingRequestDto;
import com.att.lms.bis.rest.service.NamRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.nam.*;
import com.sbc.eia.idl.nam_types.*;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.StringOpt;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;


@Component
public class NamRestFacade implements NamFacade {

    @Autowired
    NamRestService namRestService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public PingReturn ping(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        PingRequestDto requestDto = PingRequestDto.builder()
                .aBisContext(bisContext).build();

        Either<Throwables, Option<PingReturn>> result =
                namRestService.ping(requestDto, new ParameterizedTypeReference<PingReturn>() {});

        return LmsRestResponseHandler.parseExcludingMultiple(result);
    }

    @Override
    public GetNetworkAddressReturn getNetworkAddress(BisContext bisContext, String s) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        GetNetworkAddressRequestDto requestDto = GetNetworkAddressRequestDto.builder()
                .aBisContext(bisContext)
                .aNetworkAddress(s)
                .build();

        Either<Throwables, Option<GetNetworkAddressReturn>> result =
                namRestService.getNetworkAddress(requestDto, new ParameterizedTypeReference<GetNetworkAddressReturn>() {});

        return LmsRestResponseHandler.parseExcludingMultiple(result);
    }

    @Override
    public FindAvailableNetworkAddressesForLocationReturn findAvailableNetworkAddressesForLocation(BisContext bisContext, String s, ObjectKey objectKey, NetworkAddressFilter networkAddressFilter, Location location, StringOpt stringOpt) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ReserveNetworkAddressReturn reserveNetworkAddress(BisContext bisContext, String s, NetworkAddressSeqOpt networkAddressSeqOpt, String s1, StringOpt stringOpt, ObjectKeyOpt objectKeyOpt, NetworkAddressFilterOpt networkAddressFilterOpt, LocationOpt locationOpt) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ReserveWirelessNetworkAddressReturn reserveWirelessNetworkAddress(BisContext bisContext, String s, String s1, SubscriberReservation[] subscriberReservations) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ConfirmNetworkAddressReservationReturn confirmNetworkAddressReservation(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public CancelNetworkAddressReservationReturn cancelNetworkAddressReservation(BisContext bisContext, String s, String s1) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public CancelWirelessNetworkAddressReservationReturn cancelWirelessNetworkAddressReservation(BisContext bisContext, String s, StringOpt stringOpt, String[] strings) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public DeterminePortabilityStatusReturn determinePortabilityStatus(BisContext bisContext, String s, NetworkAddress[] networkAddresses) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public DetermineLocalNumberPortabilityReturn determineLocalNumberPortability(BisContext bisContext, NetworkAddress networkAddress, Location location) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public DetermineLocalNumberPortability2Return determineLocalNumberPortability2(BisContext bisContext, NetworkAddress networkAddress, Location location) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ReturnAvailableNetworkAddressesReturn returnAvailableNetworkAddresses(BisContext bisContext, NetworkAddress[] networkAddresses) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ReverseNetworkAddressLookupReturn reverseNetworkAddressLookup(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, MultipleExceptions, NotImplemented {
        return null;
    }

    @Override
    public ReserveProprietaryIdReturn reserveProprietaryId(BisContext bisContext, String s, String s1) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public CancelProprietaryIdReservationReturn cancelProprietaryIdReservation(BisContext bisContext, String s) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public GetMaximumPerCallReturn getMaximumPerCall(BisContext bisContext, String s) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public SelfTestReturn selfTest(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        return null;
    }

    @Override
    public boolean _is_a(String repositoryIdentifier) {
        return false;
    }

    @Override
    public boolean _is_equivalent(Object other) {
        return false;
    }

    @Override
    public boolean _non_existent() {
        return false;
    }

    @Override
    public int _hash(int maximum) {
        return 0;
    }

    @Override
    public Object _duplicate() {
        return null;
    }

    @Override
    public void _release() {

    }

    @Override
    public Object _get_interface_def() {
        return null;
    }

    @Override
    public Request _request(String operation) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type) {
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers() {
        return new DomainManager[0];
    }

    @Override
    public Object _set_policy_override(Policy[] policies, SetOverrideType set_add) {
        return null;
    }
}
