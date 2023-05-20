// $Id: SWITCHServerHelper.java,v 1.3 2006/06/05 18:44:20 rd2842 Exp $

package com.sbc.gwsvcs.service.switchserver;

import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.switchserver.exceptions.*;
import com.sbc.gwsvcs.service.switchserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * Provides support for client access to the SWITCHServer service.
 * Creation date: (4/16/00 11:35:56 AM)
 * @author: Creighton Malet
 */
public class SWITCHServerHelper 
	extends ServiceHelper
{
	static final EventClassPair switchTnInqExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_TN_INQ_RESP, SwitchTnInqResp_tMsg.class, SWITCHServerAccess.SWITCH_TN_INQ_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR)
		};

	static final EventClassPair switchTnUpdExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_TN_UPD_RESP, SwitchTnUpdResp_tMsg.class, SWITCHServerAccess.SWITCH_TN_UPD_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair switchInqNtuExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_INQ_NTU_RESP, SwitchInqNtuResp_tMsg.class, SWITCHServerAccess.SWITCH_INQ_NTU_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR)
		};

	static final EventClassPair switchRtnTnExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_RTN_TN_RESP, SwitchRtnTnResp_tMsg.class, SWITCHServerAccess.SWITCH_RTN_TN_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR) };

	static final EventClassPair switchSelTneExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_SEL_TNE_RESP, SwitchSelTneResp_tMsg.class, SWITCHServerAccess.SWITCH_SEL_TNE_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR) };
	static final EventClassPair switchInqCktExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_INQ_CKT_RESP, SwitchInqCktResp_tMsg.class, SWITCHServerAccess.SWITCH_INQ_CKT_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR) };
	
	static final EventClassPair switchSelTnExpected[] = new EventClassPair [] { 
		new EventClassPair(SWITCHServerAccess.SWITCH_SEL_TN_RESP, SwitchSelTnResp_tMsg.class, SWITCHServerAccess.SWITCH_SEL_TN_RESP_NBR),
		new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR) };		

    static final EventClassPair switchWsiNtuExpected[] = new EventClassPair [] { 
        new EventClassPair(SWITCHServerAccess.SWITCH_WSI_NTU_RESP, SwitchWsiNtuResp_tMsg.class, SWITCHServerAccess.SWITCH_WSI_NTU_RESP_NBR),
        new EventClassPair(SWITCHServerAccess.EXCEPTION, ExceptionResp_tMsg.class, SWITCHServerAccess.EXCEPTION_NBR) };             
/**
 * Constructor accepting properties and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public SWITCHServerHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, SWITCHServerAccess.name);

	serviceAccess = new SWITCHServerAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}
/**
 * Evaluate result from switchInqCktReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchInqCktReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_INQ_CKT_RESP_NBR:
			return new EventResultPair(((SwitchInqCktResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchInqCktReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchInqNtuReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchInqNtuReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_INQ_NTU_RESP_NBR:
			return new EventResultPair(((SwitchInqNtuResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchInqNtuReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchRtnTnReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchRtnTnReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_RTN_TN_RESP_NBR:
			return new EventResultPair(((SwitchRtnTnResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchRtnTnReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchSelTneReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchSelTneReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_SEL_TNE_RESP_NBR:
			return new EventResultPair(((SwitchSelTneResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchSelTneReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchSelTnReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchSelTnReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_SEL_TN_RESP_NBR:
			return new EventResultPair(((SwitchSelTnResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchSelTnReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchTnInqReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchTnInqReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_TN_INQ_RESP_NBR:
			return new EventResultPair(((SwitchTnInqResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchTnInqReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from switchTnUpdReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalSwitchTnUpdReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case SWITCHServerAccess.SWITCH_TN_UPD_RESP_NBR:
			return new EventResultPair(((SwitchTnUpdResp_tMsg)result.anObject).value, result.eventNbr);
		case SWITCHServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchTnUpdReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
private EventResultPair evalSwitchWsiNtuReq(EventObjectPair result) throws ServiceException
{
    switch(result.eventNbr)
    {
        case SWITCHServerAccess.SWITCH_WSI_NTU_RESP_NBR:
            return new EventResultPair(((SwitchWsiNtuResp_tMsg)result.anObject).value, result.eventNbr);
        case SWITCHServerAccess.EXCEPTION_NBR:
            ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
            throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
        default:
            throw new SWITCHServerException(ExceptionCode.ERR_GWS_SWITCHSERVER, "SWITCHServerHelper::evalSwitchWsiNtuReq: Unexpected return from sendAndReceive() " +
                result.event);
    }
}
/**
 * Event 5051 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchInqCktReq(long aTimeOut, SwitchInqCktReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_INQ_CKT_REQ, new SwitchInqCktReq_tMsg(request),switchServerAccess.SWITCH_INQ_CKT_REQ_NBR);

	return evalSwitchInqCktReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchInqCktExpected));
}
/**
 * Event 5220 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchInqCktReq(String anApplData, String aService, long aTimeOut, SwitchInqCktReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_INQ_CKT_REQ, new SwitchInqCktReq_tMsg(request),switchServerAccess.SWITCH_INQ_CKT_REQ_NBR);

	return evalSwitchInqCktReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchInqCktExpected));
}
/**
 * Event 5010 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchInqNtuReq(long aTimeOut, SwitchInqNtuReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_INQ_NTU_REQ, new SwitchInqNtuReq_tMsg(request),switchServerAccess.SWITCH_INQ_NTU_REQ_NBR);

	return evalSwitchInqNtuReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchInqNtuExpected));
}
/**
 * Event 5010 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchInqNtuReq(String anApplData, String aService, long aTimeOut, SwitchInqNtuReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;
	
	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_INQ_NTU_REQ, new SwitchInqNtuReq_tMsg(request),switchServerAccess.SWITCH_INQ_NTU_REQ_NBR);

	return evalSwitchInqNtuReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchInqNtuExpected));
}
/**
 * Event 5220 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchRtnTnReq(long aTimeOut, SwitchRtnTnReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_RTN_TN_REQ, new SwitchRtnTnReq_tMsg(request),switchServerAccess.SWITCH_RTN_TN_REQ_NBR);

	return evalSwitchRtnTnReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchRtnTnExpected));
}
/**
 * Event 5220 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchRtnTnReq(String anApplData, String aService, long aTimeOut, SwitchRtnTnReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_RTN_TN_REQ, new SwitchRtnTnReq_tMsg(request),switchServerAccess.SWITCH_RTN_TN_REQ_NBR);

	return evalSwitchRtnTnReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchRtnTnExpected));
}
/**
 * Event 5210 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchSelTneReq(long aTimeOut, SwitchSelTneReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_SEL_TNE_REQ, new SwitchSelTneReq_tMsg(request),switchServerAccess.SWITCH_SEL_TNE_REQ_NBR);

	return evalSwitchSelTneReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchSelTneExpected));
}
/**
 * Event 5210 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchSelTneReq(String anApplData, String aService, long aTimeOut, SwitchSelTneReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_SEL_TNE_REQ, new SwitchSelTneReq_tMsg(request),switchServerAccess.SWITCH_SEL_TNE_REQ_NBR);

	return evalSwitchSelTneReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchSelTneExpected));
}
/**
 * Event 5200 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchSelTnReq(long aTimeOut, SwitchSelTnReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_SEL_TN_REQ, new SwitchSelTnReq_tMsg(request),switchServerAccess.SWITCH_SEL_TN_REQ_NBR);

	return evalSwitchSelTnReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchSelTnExpected));
}
/**
 * Event 5200 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchSelTnReq(String anApplData, String aService, long aTimeOut, SwitchSelTnReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_SEL_TN_REQ, new SwitchSelTnReq_tMsg(request),switchServerAccess.SWITCH_SEL_TN_REQ_NBR);

	return evalSwitchSelTnReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchSelTnExpected));
}
/**
 * Event 5000 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchTnInqReq(long aTimeOut, SwitchTnInqReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_TN_INQ_REQ, new SwitchTnInqReq_tMsg(request),switchServerAccess.SWITCH_TN_INQ_REQ_NBR);

	return evalSwitchTnInqReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchTnInqExpected));
}
/**
 * Event 5000 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchTnInqReq(String anApplData, String aService, long aTimeOut, SwitchTnInqReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;
	
	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_TN_INQ_REQ, new SwitchTnInqReq_tMsg(request),switchServerAccess.SWITCH_TN_INQ_REQ_NBR);

	return evalSwitchTnInqReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchTnInqExpected));
}
/**
 * Event 5010 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchTnUpdReq(long aTimeOut, SwitchTnUpdReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_TN_UPD_REQ, new SwitchTnUpdReq_tMsg(request),switchServerAccess.SWITCH_TN_UPD_REQ_NBR);

	return evalSwitchTnUpdReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchTnUpdExpected));
}
/**
 * Event 5010 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchTnUpdReq(String anApplData, String aService, long aTimeOut, SwitchTnUpdReq_t request) throws SWITCHServerException, ServiceException
{
	SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;
	
	EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_TN_UPD_REQ, new SwitchTnUpdReq_tMsg(request),switchServerAccess.SWITCH_TN_UPD_REQ_NBR);

	return evalSwitchTnUpdReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchTnUpdExpected));
}
/**
 * Event 5170 (connect, send, receive and disconnect).
 * Creation date: (06/05/06 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchWsiNtuReq(String anApplData, String aService, long aTimeOut, SwitchWsiNtuReq_t request) throws SWITCHServerException, ServiceException
{
    SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;
    
    EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_WSI_NTU_REQ, new SwitchWsiNtuReq_tMsg(request),switchServerAccess.SWITCH_WSI_NTU_REQ_NBR);

    return evalSwitchWsiNtuReq(switchServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, switchWsiNtuExpected));
}
/**
 * Event 5170 (send and receive).
 * Creation date: (06/05/06 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t
 * @exception com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException: an SWITCHServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair switchWsiNtuReq(long aTimeOut, SwitchWsiNtuReq_t request) throws SWITCHServerException, ServiceException
{
    SWITCHServerAccess switchServerAccess = (SWITCHServerAccess)serviceAccess;
    
    EventObjectPair inRequest = new EventObjectPair(switchServerAccess.SWITCH_WSI_NTU_REQ, new SwitchWsiNtuReq_tMsg(request),switchServerAccess.SWITCH_WSI_NTU_REQ_NBR);

    return evalSwitchWsiNtuReq(switchServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, switchWsiNtuExpected));
}
}
