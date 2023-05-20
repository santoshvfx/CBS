// $Id: CircuitProvisioningHelper.java,v 1.2 2002/09/29 04:13:00 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning;

import java.util.*;
import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.circuitprovisioning.exceptions.*;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * Provides support for client access to the CircuitProvisioning service.
 * Creation date: (4/16/00 11:35:56 AM)
 * @author: Creighton Malet
 */
public class CircuitProvisioningHelper 
	extends ServiceHelper
{
	public final static int RECEIVE_ALL_MESSAGES = -1;
	
	EventClassPair cxrsQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_CXRS_QO, CxrsOutputMsg.class, CircuitProvisioningAccess.TIRKS_CXRS_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };

	EventClassPair waQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_WA_QO, WaOutputMsg.class, CircuitProvisioningAccess.TIRKS_WA_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };

	EventClassPair driQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_DRI_QO, DriOutputMsg.class, CircuitProvisioningAccess.TIRKS_DRI_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };
		
	EventClassPair cblsQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_CBLS_QO, CblsOutputMsg.class, CircuitProvisioningAccess.TIRKS_CBLS_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };
	
	EventClassPair eqpscQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_EQPSC_QO, EqpscOutputMsg.class, CircuitProvisioningAccess.TIRKS_EQPSC_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };
	
	EventClassPair rdlocQInputExpected[] = new EventClassPair [] { 
		new EventClassPair(CircuitProvisioningAccess.TIRKS_RDLOC_QO, RdlocOutputMsg.class, CircuitProvisioningAccess.TIRKS_RDLOC_QO_NBR),
		new EventClassPair(CircuitProvisioningAccess.TIRKS_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.TIRKS_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.GENERAL_SERVICE_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR),
		new EventClassPair(CircuitProvisioningAccess.FATAL_ERROR, ErrorUtilMsg.class, CircuitProvisioningAccess.FATAL_ERROR_NBR) };	
/**
 * Constructor accepting properties and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public CircuitProvisioningHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, CircuitProvisioningAccess.name);

	serviceAccess = new CircuitProvisioningAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}
/**
 * Event 13001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList cblsQInput(long aTimeOut, CblsQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_CBLS_QI, new CblsQInputMsg(request),
		circuitProvisioningAccess.TIRKS_CBLS_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalCblsQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, cblsQInputExpected));
	while (((CblsOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalCblsQInput(circuitProvisioningAccess.receive(cblsQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 13001 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList cblsQInput(String anApplData, String aService, long aTimeOut, CblsQInput request,
	int maximumMessagesToReceive)
		throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_CBLS_QI, new CblsQInputMsg(request),
		circuitProvisioningAccess.TIRKS_CBLS_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalCblsQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, cblsQInputExpected));
		while (((CblsOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalCblsQInput(circuitProvisioningAccess.receive(cblsQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
/**
 * Event 12001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList cxrsQInput(long aTimeOut, CxrsQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_CXRS_QI, new CxrsQInputMsg(request),
		circuitProvisioningAccess.TIRKS_CXRS_QI_NBR);

	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalCxrsQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, cxrsQInputExpected));
	while (((CxrsOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalCxrsQInput(circuitProvisioningAccess.receive(cxrsQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 12001 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList cxrsQInput(String anApplData, String aService, long aTimeOut, CxrsQInput request,
	int maximumMessagesToReceive)
		throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_CXRS_QI, new CxrsQInputMsg(request),
		circuitProvisioningAccess.TIRKS_CXRS_QI_NBR);

	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalCxrsQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, cxrsQInputExpected));
		while (((CxrsOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalCxrsQInput(circuitProvisioningAccess.receive(cxrsQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
/**
 * Event 13001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList driQInput(long aTimeOut, DriQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_DRI_QI, new DriQInputMsg(request),
		circuitProvisioningAccess.TIRKS_DRI_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalDriQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, driQInputExpected));
	while (((DriOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalDriQInput(circuitProvisioningAccess.receive(driQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 13001 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList driQInput(String anApplData, String aService, long aTimeOut, DriQInput request,
	int maximumMessagesToReceive)
		throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_DRI_QI, new DriQInputMsg(request),
		circuitProvisioningAccess.TIRKS_DRI_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalDriQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, driQInputExpected));
		while (((DriOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalDriQInput(circuitProvisioningAccess.receive(driQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
/**
 * Event 13001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList eqpscQInput(long aTimeOut, EqpscQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_EQPSC_QI, new EqpscQInputMsg(request),
		circuitProvisioningAccess.TIRKS_EQPSC_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalEqpscQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest,eqpscQInputExpected));
	while (((EqpscOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalEqpscQInput(circuitProvisioningAccess.receive(eqpscQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 15001 (connect, send, receive and disconnect).
 * Creation date: (06/26/2002)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList eqpscQInput(String anApplData, String aService, long aTimeOut, EqpscQInput request,
	int maximumMessagesToReceive)
		throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_EQPSC_QI, new EqpscQInputMsg(request),
		circuitProvisioningAccess.TIRKS_EQPSC_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalEqpscQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, eqpscQInputExpected));
		while (((EqpscOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalEqpscQInput(circuitProvisioningAccess.receive(eqpscQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
/**
 * Evaluate result from cblsQInput().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalCblsQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_CBLS_QO_NBR:
			return new EventResultPair(((CblsOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalCblsQInput: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from cxrsQInput().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalCxrsQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_CXRS_QO_NBR:
			return new EventResultPair(((CxrsOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, e.ErrorMsg, String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalLQClecQualReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from driQInput().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalDriQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_DRI_QO_NBR:
			return new EventResultPair(((DriOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalLQClecQualReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from eqpscQInput().
 * Creation date: (05/27/2002)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalEqpscQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_EQPSC_QO_NBR:
			return new EventResultPair(((EqpscOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalEqpscQInput: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from rdlocQInput().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRdlocQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_RDLOC_QO_NBR:
			return new EventResultPair(((RdlocOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalRdlocQInput: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from waQInput().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalWaQInput(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case CircuitProvisioningAccess.TIRKS_WA_QO_NBR:
			return new EventResultPair(((WaOutputMsg)result.anObject).value, result.eventNbr);
		case CircuitProvisioningAccess.TIRKS_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_TIRKS_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.GENERAL_SERVICE_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.NO_SERVICE_AVAIL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_NO_SVC_AVAILABLE, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		case CircuitProvisioningAccess.FATAL_ERROR_NBR:
		{
			ErrorUtil e = ((ErrorUtilMsg)result.anObject).value;
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING_FATAL_ERROR, String.valueOf(e.ErrorMsg), String.valueOf(e.OrigEvent));
		}
		default:
			throw new CircuitProvisioningException(ExceptionCode.ERR_GWS_CIRCUITPROVISIONING, "CircuitProvisioningHelper::evalLQClecQualReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Event 13001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList rdlocQInput(long aTimeOut, RdlocQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_RDLOC_QI, new RdlocQInputMsg(request),
		circuitProvisioningAccess.TIRKS_RDLOC_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalRdlocQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, rdlocQInputExpected));
	while (((RdlocOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalRdlocQInput(circuitProvisioningAccess.receive(rdlocQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 13001 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList rdlocQInput(String anApplData, String aService, long aTimeOut, RdlocQInput request,
	int maximumMessagesToReceive)
		throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_RDLOC_QI, new RdlocQInputMsg(request),
		circuitProvisioningAccess.TIRKS_RDLOC_QI_NBR);
	
	ArrayList resultSet = new ArrayList();	
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalRdlocQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, rdlocQInputExpected));
		while (((RdlocOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalRdlocQInput(circuitProvisioningAccess.receive(rdlocQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
/**
 * Event 27001 (receive only - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList waQInput(long aTimeOut, int maximumMessagesToReceive) throws CircuitProvisioningException,
	ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	ArrayList resultSet = new ArrayList();
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalWaQInput(circuitProvisioningAccess.receive( waQInputExpected, factorTimeOut(aTimeOut)));
	while (((WaOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalWaQInput(circuitProvisioningAccess.receive(waQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 27001 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param aTimeOut long
 * @param int maximumMessagesToReceive
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList waQInput(long aTimeOut, WaQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_WA_QI, new WaQInputMsg(request),
		circuitProvisioningAccess.TIRKS_WA_QI_NBR);

	ArrayList resultSet = new ArrayList();
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	result = evalWaQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, waQInputExpected));
	while (((WaOutput)result.getTheObject()).last == 0 &&
		((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
	{
		resultSet.add(result);
		result = evalWaQInput(circuitProvisioningAccess.receive(waQInputExpected, factorTimeOut(aTimeOut)));
		numberOfReceivedMessages++;
	}
	resultSet.add(result);

	return resultSet;
}
/**
 * Event 27001 (send only - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public void waQInput(WaQInput request) throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_WA_QI, new WaQInputMsg(request),
		circuitProvisioningAccess.TIRKS_WA_QI_NBR);

	circuitProvisioningAccess.send(inRequest);
}
/**
 * Event 27001 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return java.util.ArrayList
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput
 * @param int maximumMessagesToReceive
 * @exception com.sbc.gwsvcs.service.circuitprovisioning.exceptions.CircuitProvisioningException: a CircuitProvisioning exception occurred.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
public ArrayList waQInput(String anApplData, String aService, long aTimeOut, WaQInput request, int maximumMessagesToReceive)
	throws CircuitProvisioningException, ServiceException
{
	CircuitProvisioningAccess circuitProvisioningAccess = (CircuitProvisioningAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(circuitProvisioningAccess.TIRKS_WA_QI, new WaQInputMsg(request),
		circuitProvisioningAccess.TIRKS_WA_QI_NBR);

	ArrayList resultSet = new ArrayList();
	EventResultPair result;
	int numberOfReceivedMessages = 1;
	try {
		connect(anApplData, aService);
		result = evalWaQInput(circuitProvisioningAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, waQInputExpected));
		while (((WaOutput)result.getTheObject()).last == 0 &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add(result);
			result = evalWaQInput(circuitProvisioningAccess.receive(waQInputExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add(result);
	}
	finally {
		disconnect();
	}
	return resultSet;
}
}
