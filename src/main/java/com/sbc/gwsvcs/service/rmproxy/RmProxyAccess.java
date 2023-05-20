// $Id: RmProxyAccess.java,v 1.9 2006/09/13 14:36:52 vc7563 Exp $

package com.sbc.gwsvcs.service.rmproxy;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
//import com.sbc.gwsvcs.service.rmproxy.exceptions.*;
//import com.sbc.gwsvcs.service.rmproxy.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Wraps the service side for access to the SORDServer service.
 * Creation date: (4/16/00 11:36:38 AM)
 * @author: Creighton Malet
 *
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	6/24/2004	  Stevan Dunkin		  RM 9		 Added logic for 5 new transactions: 
												 ActivateResourcesForDSLService, FindAvailablePort, 
												 ModifyResourcesForDSLService, RetrieveResourcesForDSLService, 
												 RetrieveResourceStatusForDSLService. 
    02/03/2006   Changchuan Yin       RM 15
    09/08/2006   Vickie Ng			  RM 18	     LS 4 split
 **/
public class RmProxyAccess extends ServiceAccess
{
	public final static String version = "18.0";
	public final static String name = "RMPROXY";
	
	
	// RetrieveResourceForService - CLLI
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_NBR 								=	100;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_RESP_NBR 							=	101;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_BUSINESSVIOLATION_NBR 			=	102;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_OBJECTNOTFOUND_NBR 				=	103;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_DATANOTFOUND_NBR 					=	104;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_INVALIDDATA_NBR 					=	105;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_ACCESSDENIED_NBR 					=	106;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_SYSTEMFAILURE_NBR 				=	107;
	public final static int RETRIEVE_RESOURCES_FOR_SERVICE_NOTIMPLEMENTED_NBR 				=	108;
	
	// RetrieveServiceProvidersForResource
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_NBR 						=	109;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_RESP_NBR 				=	110;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_BUSINESSVIOLATION_NBR 	=	111;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_OBJECTNOTFOUND_NBR 		=	112;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_DATANOTFOUND_NBR 		=	113;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_INVALIDDATA_NBR 			=	114;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_ACCESSDENIED_NBR 		=	115;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_SYSTEMFAILURE_NBR 		=	116;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_NOTIMPLEMENTED_NBR 		=	117;

	// RetrieveServiceProvidersForService
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_NBR 						=	118;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_RESP_NBR 					=	119;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_BUSINESSVIOLATION_NBR 	=	120;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_OBJECTNOTFOUND_NBR 		=	121;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_DATANOTFOUND_NBR 			=	122;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_INVALIDDATA_NBR 			=	123;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_ACCESSDENIED_NBR 			=	124;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_SYSTEMFAILURE_NBR 		=	125;
	public final static int RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_NOTIMPLEMENTED_NBR 		=	126;
	
	//ActivateResourcesForDSLService
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_NBR 							=	127;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR 						=	128;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR 		=	129;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR 			=	130;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR 				=	131;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR 				=	132;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR 				=	133;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR 			=	134;
	public final static int ACTIVATE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR 			=	135;
	
	//ModifyResourcesForDSLService
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_NBR 							=	136;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_RESP_NBR 						=	137;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR 			=	138;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR 				=	139;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR 				=	140;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR 				=	141;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR 				=	142;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR 				=	143;
	public final static int MODIFY_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR 				=	144;
	
	//RetrieveResourcesForDSLService
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_NBR 							=	145;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_RESP_NBR 						=	146;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR 		=	147;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR 			=	148;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_DATANOTFOUND_NBR 				=	149;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_INVALIDDATA_NBR 				=	150;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_ACCESSDENIED_NBR 				=	151;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR 			=	152;
	public final static int RETRIEVE_RESOURCES_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR 			=	153;
	
	//RetrieveResourceStatusForDSLService
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NBR 					=	154;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP_NBR 				=	155;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_BUSINESSVIOLATION_NBR 	=	156;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_OBJECTNOTFOUND_NBR 	=	157;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_DATANOTFOUND_NBR 		=	158;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_INVALIDDATA_NBR 		=	159;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_ACCESSDENIED_NBR 		=	160;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_SYSTEMFAILURE_NBR 		=	161;
	public final static int RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_NOTIMPLEMENTED_NBR 	=	162;
		
	public final static MEventType RETRIEVE_RESOURCES_FOR_SERVICE_REQ = new MEventType("retrieveResourcesForService");
	public final static MEventType RETRIEVE_RESOURCES_FOR_SERVICE_RESP = new MEventType("retrieveResourcesForServiceResponse");
	
	public final static MEventType RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_REQ = new MEventType("retrieveServiceProvidersForResource");
	public final static MEventType RETRIEVE_SERVICE_PROVIDERS_FOR_RESOURCE_RESP = new MEventType("retrieveServiceProvidersForResourceResponse");
	
	public final static MEventType RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_REQ = new MEventType("retrieveServiceProvidersForService");
	public final static MEventType RETRIEVE_SERVICE_PROVIDERS_FOR_SERVICE_RESP = new MEventType("retrieveServiceProvidersForServiceResponse");

	public final static MEventType ACTIVATE_RESOURCES_FOR_DSL_SERVICE_REQ = new MEventType("activateResourcesForDSLService");
	public final static MEventType ACTIVATE_RESOURCES_FOR_DSL_SERVICE_RESP = new MEventType("activateResourcesForDSLServiceResponse");
	

	public final static MEventType MODIFY_RESOURCES_FOR_DSL_SERVICE_REQ = new MEventType("modifyResourcesForDSLService");
	public final static MEventType MODIFY_RESOURCES_FOR_DSL_SERVICE_RESP = new MEventType("modifyResourcesForDSLServiceResponse");
	
	public final static MEventType RETRIEVE_RESOURCES_FOR_DSL_SERVICE_REQ = new MEventType("retrieveResourcesForDSLService");
	public final static MEventType RETRIEVE_RESOURCES_FOR_DSL_SERVICE_RESP = new MEventType("retrieveResourcesForDSLServiceResponse");
	
	public final static MEventType RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_REQ = new MEventType("retrieveResourceStatusForDSLService");
	public final static MEventType RETRIEVE_RESOURCES_STATUS_FOR_DSL_SERVICE_RESP = new MEventType("retrieveResourceStatusForDSLServiceResponse");
	

	public final static MEventType ACCESS_DENIED_ERROR = new MEventType("AccessDenied");
	public final static MEventType BUSINESS_VIOLATION_ERROR = new MEventType("BusinessViolation");
	public final static MEventType DATA_NOT_FOUND_ERROR = new MEventType("DataNotFound");
	public final static MEventType INVALID_DATA_ERROR = new MEventType("InvalidData");
	public final static MEventType NOT_IMPLEMENTED_ERROR = new MEventType("NotImplemented");
	public final static MEventType OBJECT_NOT_FOUND_ERROR = new MEventType("ObjectNotFound");
	public final static MEventType SYSTEM_FAILURE_ERROR = new MEventType("SystemFailure");
	
	/**
 * Constructor accepting Vicuna configuration file, directory for configuration files and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public RmProxyAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
