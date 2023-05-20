// $Id: RmProxyHelper.java,v 1.5 2006/09/12 18:21:42 ds4987 Exp $

package com.sbc.gwsvcs.service.rmproxy; 

import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.rmproxy.exceptions.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.bis_types.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.*;
import com.sbc.gwsvcs.service.rmproxy.interfaces.com.sbc.eia.idl.rm.RmFacadePackage.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * Provides support for client access to the SORDServer service.
 * Creation date: (4/16/00 11:35:56 AM)
 * @author: Creighton Malet
 * 
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	6/24/2004	  Stevan Dunkin		  RM 9		 Added logic for 5 new transactions: 
												 ActivateResourcesForDSLService, FindAvailablePort, 
												 ModifyResourcesForDSLService, RetrieveResourcesForDSLService, 
	02/03/06      Changchuan Yin      RM15.0	 RetrieveResourceStatusForDSLService. 
	09/08/06 	  Vickie Ng			  RM 18.0    LS4 split
 */
public class RmProxyHelper 
	extends ServiceHelper
{
	EventClassPair retrieveResourcesForServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_RESP, RetrieveResourcesForServiceReturnMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_SYSTEMFAILURE_NBR) };

	EventClassPair retrieveServiceProvidersForResourceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_RESP, RetrieveServiceProvidersForResourceReturnMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_SYSTEMFAILURE_NBR) };

	EventClassPair retrieveServiceProvidersForServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_RESP, RetrieveServiceProvidersForServiceReturnMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_SYSTEMFAILURE_NBR) };
		
	EventClassPair activateResourcesForDSLServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_RESP, ActivateResourcesForDSLServiceReturnMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR) };
		
	EventClassPair modifyResourcesForDSLServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_RESP, ModifyResourcesForDSLServiceReturnMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR) };
		
	EventClassPair retrieveResourcesForDSLServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_RESP, RetrieveResourcesForDSLServiceReturnMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR) };
		
	EventClassPair retrieveResourceStatusForDSLServiceExpected[] = new EventClassPair [] { 
		new EventClassPair(RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP, RetrieveResourceStatusForDSLServiceReturnMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP_NBR),
		new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_ACCESSDENIED_NBR),
		new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR),
		new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_DATANOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_INVALIDDATA_NBR),  
		new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR), 
		new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR),
		new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR) };
		
	//For LS2, RM BIS 15.0.0
	EventClassPair modifyPathStatusExpected[] = new EventClassPair [] { 
			new EventClassPair(RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP, RetrieveResourceStatusForDSLServiceReturnMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP_NBR),
			new EventClassPair(RmProxyAccess.ACCESS_DENIED_ERROR, AccessDeniedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_ACCESSDENIED_NBR),
			new EventClassPair(RmProxyAccess.BUSINESS_VIOLATION_ERROR, BusinessViolationMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR),
			new EventClassPair(RmProxyAccess.DATA_NOT_FOUND_ERROR, DataNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_DATANOTFOUND_NBR),
			new EventClassPair(RmProxyAccess.INVALID_DATA_ERROR, InvalidDataMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_INVALIDDATA_NBR),  
			new EventClassPair(RmProxyAccess.NOT_IMPLEMENTED_ERROR, NotImplementedMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR), 
			new EventClassPair(RmProxyAccess.OBJECT_NOT_FOUND_ERROR, ObjectNotFoundMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR),
			new EventClassPair(RmProxyAccess.SYSTEM_FAILURE_ERROR, SystemFailureMsg.class, RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR) };
		
	
/**
 * Constructor accepting properties and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public RmProxyHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, RmProxyAccess.name);

	serviceAccess = new RmProxyAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}


/**
 * Evaluate result from RetrieveServiceProvidersForService().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRetrieveServiceProvidersForServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_RESP_NBR:
			return new EventResultPair(((RetrieveServiceProvidersForServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalretrieveFacilityReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}

/**
 * Evaluate result from RetrieveServiceProvidersForResourceReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRetrieveServiceProvidersForResourceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_RESP_NBR:
			return new EventResultPair(((RetrieveServiceProvidersForResourceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalretrieveFacilityReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}

/**
 * Evaluate result from RetrieveResourcesForServiceReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRetrieveResourcesForServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_RESP_NBR:
			return new EventResultPair(((RetrieveResourcesForServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalretrieveFacilityReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}


/**
 * Evaluate result from ActivateResourcesForDSLService().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalActivateResourcesForDSLServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR:
			return new EventResultPair(((ActivateResourcesForDSLServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalActivateResourcesForDSLServiceReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}


/**
 * Evaluate result from ModifyResourcesForDSLService().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalModifyResourcesForDSLServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_RESP_NBR:
			return new EventResultPair(((ModifyResourcesForDSLServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalModifyResourcesForDSLServiceReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}


/**
 * Evaluate result from RetrieveResourcesForDSLService().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRetrieveResourcesForDSLServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR:
			return new EventResultPair(((RetrieveResourcesForDSLServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalRetrieveResourcesForDSLServiceReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}

/**
 * Evaluate result from RetrieveResourceStatusForDSLService().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalRetrieveResourceStatusForDSLServiceReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP_NBR:
			return new EventResultPair(((RetrieveResourceStatusForDSLServiceReturnMsg)result.anObject).value, result.eventNbr);
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_ACCESSDENIED_NBR:
		{
			AccessDenied e = ((AccessDeniedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR:
		{
			BusinessViolation e = ((BusinessViolationMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_DATANOTFOUND_NBR:
		{
			DataNotFound e = ((DataNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_INVALIDDATA_NBR:
		{
			InvalidData e = ((InvalidDataMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR:
		{
			NotImplemented e = ((NotImplementedMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR:
		{
			ObjectNotFound e = ((ObjectNotFoundMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		case RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR:
		{
			SystemFailure e = ((SystemFailureMsg)result.anObject).value;
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, e.aExceptionData.aDescription, e.aExceptionData.aCode);	
		}
		default:
			throw new RmProxyException(ExceptionCode.ERR_RM_PROXY, "RmProxyHelper::evalRetrieveResourceStatusForDSLServiceReq: Unexpected return from connectSendReceiveAndDisconnect(anApplData, aService, anApplData, aService, ) " +
				result.event);
	}
}


/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair retrieveServiceProvidersForServiceInput(String anApplData, String aService,  long aTimeOut, retrieveServiceProvidersForService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_REQ, new retrieveServiceProvidersForServiceMsg(request),
		RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_NBR);
	
	return evalRetrieveServiceProvidersForServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, retrieveServiceProvidersForServiceExpected));
}

/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair retrieveResourcesForServiceInput(String anApplData, String aService,  long aTimeOut, retrieveResourcesForService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_REQ, new retrieveResourcesForServiceMsg(request),
		RmProxyAccess.RETRIEVE_RESOURCES_FOR_SERVICE_NBR);
	
	return evalRetrieveResourcesForServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, retrieveResourcesForServiceExpected));
}

/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair retrieveServiceProvidersForResourceInput(String anApplData, String aService,  long aTimeOut, retrieveServiceProvidersForResource request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_REQ, new retrieveServiceProvidersForResourceMsg(request),
		RmProxyAccess.RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_NBR);
	
	return evalRetrieveServiceProvidersForResourceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, retrieveServiceProvidersForResourceExpected));
}



/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (06/24/2004)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.activateResourcesForDSLServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair activateResourcesForDSLServiceInput(String anApplData, String aService,  long aTimeOut, activateResourcesForDSLService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_REQ, new activateResourcesForDSLServiceMsg(request),
		RmProxyAccess.ACTIVATE_RESOURCES_FOR_DSL_SERVICE_NBR);
	
	return evalActivateResourcesForDSLServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, activateResourcesForDSLServiceExpected));
}

/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (06/24/2004)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair modifyResourcesForDSLServiceInput(String anApplData, String aService,  long aTimeOut, modifyResourcesForDSLService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_REQ, new modifyResourcesForDSLServiceMsg(request),
		RmProxyAccess.MODIFY_RESOURCES_FOR_DSL_SERVICE_NBR);
	
	return evalModifyResourcesForDSLServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, modifyResourcesForDSLServiceExpected));
}
/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (06/24/2004)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair retrieveResourcesForDSLServiceInput(String anApplData, String aService,  long aTimeOut, retrieveResourcesForDSLService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_REQ, new retrieveResourcesForDSLServiceMsg(request),
		RmProxyAccess.RETRIEVE_RESOURCES_FOR_DSL_SERVICE_NBR);
	
	return evalRetrieveResourcesForDSLServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, retrieveResourcesForDSLServiceExpected));
}
/**
 * Event 7510 (send and receive - already connected).
 * Creation date: (06/24/2004)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.sordserver.interfaces.qualifyLocalLoopForServiceType
 * @exception com.sbc.gwsvcs.service.sordserver.exceptions.RmProxyException: a RmProxy exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair retrieveResourceStatusForDSLServiceInput(String anApplData, String aService,  long aTimeOut, retrieveResourceStatusForDSLService request) throws RmProxyException, ServiceException
{
	RmProxyAccess RmProxyAccess = (RmProxyAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_REQ, new retrieveResourceStatusForDSLServiceMsg(request),
		RmProxyAccess.RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NBR);
	
	return evalRetrieveResourceStatusForDSLServiceReq(RmProxyAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, retrieveResourceStatusForDSLServiceExpected));
}


}
