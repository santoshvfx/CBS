// $Id: RetrieveServiceProvidersForResource.java,v 1.11 2007/01/25 14:19:11 cy4727 Exp $


//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      		| Author       	 	| Notes
//# ----------------------------------------------------------------------------
//# 8/17/04	     Jinmin Ni     	  	RM141220: add OCN by clli inquiry
//# 04/01/05		 jp2854         	Changes for removal of ObjectHandle from IDL bundle
//# 06/01/2005	Chaitanya			Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1
//# 01/11/2007  Changchuan Yin      Changed APTOS to CODES.

package com.sbc.eia.bis.facades.rm.transactions.RetrieveServiceProvidersForResource;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.InvalidServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.NotImplementedException;
import com.sbc.eia.bis.BusinessInterface.ServiceCenter;
import com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException;
//import com.sbc.eia.bis.BusinessInterface.rm.APTOS.APTOS;
import com.sbc.eia.bis.BusinessInterface.rm.CODES.CODES;
import com.sbc.eia.bis.BusinessInterface.rm.ASON.ASON;
import com.sbc.eia.bis.BusinessInterface.rm.CARE.CARE;
import com.sbc.eia.bis.BusinessInterface.rm.INEXS.INEXS;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForResourceIntf;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;
import com.sbc.eia.idl.rm.bishelpers.RetrieveServiceProvidersForResourceReturnBisHelper;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.bishelpers.ObjectKeySeqBisHelper;

/**
 * Insert the type's description here.
 * Creation date: (3/21/01 9:11:55 AM)
 * @author: Sam Lok - Local
 */
public class RetrieveServiceProvidersForResource extends com.sbc.eia.bis.RmNam.utilities.TranBase
{
	private static final java.lang.String[] hostList = new String[]
	{
		//APTOS.HostClass_APTOS,
		CODES.HostClass_CODES,
		INEXS.HostClass_INEXS,
		CARE.HostClass_CARE,
        ASON.class.getName()
	} ;
	public com.sbc.eia.bis.BusinessInterface.CompanyFactory companyFactory = null ;

/**
 * Insert the method's description here.
 * Creation date: (3/21/01 9:12:14 AM)
 * @param param java.util.Hashtable
 */
public RetrieveServiceProvidersForResource(java.util.Hashtable param)
{
	super( param ) ;
}
/**
 * retrieveServiceProvidersForResource method returns service providers that provide the specified service
 *				types at the specified resource.
 * @return RetrieveServiceProvidersForResourceReturn
 * @param aContext BisContext
 * @param aResourceHandle String
 * @param aServiceTypeHandles ObjectKey[]
 * @exception InvalidData: An input parameter contained invalid data.
 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception BusinessViolation: The attempted action violates a business rule.
 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception NotImplemented: The method has not been implemented.
 * @exception ObjectNotFound: The desired domain object could not be found.
 * @exception DataNotFound: No data found.
 */
public RetrieveServiceProvidersForResourceReturn
execute(BisContext aContext,
		String aResourceHandle,
		ObjectKey[] aServiceTypeHandles,
		Logger aLogger)
throws 	InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound
{
	callerContext = aContext ;
	myLogger = aLogger;
	
	log( LOG_DEBUG_LEVEL_1, "RM::retrieveServiceProvidersForResource.execute() " ) ;

	RetrieveServiceProvidersForResourceReturn aRetrieveServiceProvidersForResourceReturn = null ;
	try
	{
		// Check the basic input objects exist
		if ( aResourceHandle == null )
			throwException(	ExceptionCode.ERR_RM_MISSING_RESOURCE_HANDLE,
							formatInvalidData( String.class, "aResourceHandle" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		if ( aServiceTypeHandles == null )
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_TYPE_HANDLE,
							formatInvalidData( String.class, "aServiceTypeHandles" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;

		// Log the input data
		log( LOG_INPUT_DATA, "aContext=<" + (new BisContextBisHelper(aContext)).toString() + ">" );
		log( LOG_INPUT_DATA, "aResourceHandle=<" + aResourceHandle + ">" );
		log( LOG_INPUT_DATA, "aServiceTypeHandles=<" + (new ObjectKeySeqBisHelper(aServiceTypeHandles)).toString() + ">" );


		// Validate the input: BisContext, CLLI and the PIC/LPIC flags

		// BisContext aContext
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( aContext.aProperties );

		validateBisContext( aContextOPM,
							BisContextProperty.SERVICECENTER,
							ExceptionCode.ERR_RM_MISSING_SERVICE_CENTER,
							"Required field is null" ) ;
		validateBisContext( aContextOPM,
							BisContextProperty.APPLICATION,
							ExceptionCode.ERR_RM_MISSING_APPLICATION,
							"Required field is null" ) ;
		validateClient(
				aContextOPM,
			 	null,			// group_id
				ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION );

		// CLLI
		String aCLLI = null;
		try
		{
			aCLLI = aResourceHandle.trim();
		}
		catch (NullPointerException e)
		{
            throwException( ExceptionCode.ERR_RM_INVALID_CLLI,
                          formatInvalidData(String.class, "aResourceHandle.aValue"),
                          getEnv( "BIS_NAME" ),
                          Severity.UnRecoverable ) ;

		}
		if ( aCLLI.length() < 1 )
		{
			throwException(	ExceptionCode.ERR_RM_MISSING_RESOURCE_HANDLE,
							formatInvalidData( String.class, "aResourceHandle.aValue" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		}

		// the type for PIC/LPIC/LOCAL_VOICE
		if ( aServiceTypeHandles.length < 1 )
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_TYPE_HANDLE,
							formatInvalidData( ObjectKey.class, "aServiceTypeHandles" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;

		// Build the company factory
		if ( companyFactory == null )
		{
			try {
				companyFactory = new CompanyFactory( hostList, this, getPROPERTIES() );
			}
			catch ( BusinessException e )
			{
				throwException( ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
								"Internal Error: Business Interface Exception: " +
													e.getExceptionCode() + ":" + e.getMessage(),
								Business.BUSINESS_INTERFACE, Severity.UnRecoverable, e );
			}
		}

		// Do the work: find the host object and call the appropriate method
		try {
				aRetrieveServiceProvidersForResourceReturn =
					( (ServiceProvidersForResourceIntf)companyFactory.
					getCompany( new ServiceCenter( aContextOPM.getValue( BisContextProperty.SERVICECENTER ) ) ).
					getBusinessInterface( ServiceProvidersForResourceIntf.ServiceProvidersForResourceInterfaceName ) ).
					retrievePicByCLLI( aContext, aCLLI, aServiceTypeHandles ) ;
		}
		catch (InvalidServiceCenterException e)
		{
			throwException(ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
				formatInvalidData(BisContext.class, "aContext.aProperties",
					aContextOPM.getValue(BisContextProperty.SERVICECENTER),
					"Invalid Service Center: " + e.getExceptionCode() +	":" + e.getMessage()),
				Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
		}
		catch (UnknownServiceCenterException e)
		{
			throwException(ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
				formatInvalidData(BisContext.class, "aContext.aProperties",
					aContextOPM.getValue(BisContextProperty.SERVICECENTER),
					"Unknown Service Center: " + e.getExceptionCode() +	":" + e.getMessage()),
				Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
		}
		catch (NotImplementedException e)
		{
			throwException(ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
				"Functionality not implemented for service center: " +
				aContextOPM.getValue(BisContextProperty.SERVICECENTER) + ". " +
				e.getExceptionCode() + ":" + e.getMessage(), Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
		}
		catch (com.sbc.eia.bis.BusinessInterface.BusinessException e)
		{
			throwException(	ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
							"Internal Error: Business Interface Exception: " + e.getExceptionCode() + ":" + e.getMessage(),
							Business.BUSINESS_INTERFACE, Severity.UnRecoverable, e );
		}

		// Log the output data
		log(LOG_OUTPUT_DATA,
			"RetrieveServiceProvidersForResourceReturn=<" +
			(new RetrieveServiceProvidersForResourceReturnBisHelper(aRetrieveServiceProvidersForResourceReturn)).toString() + ">");
	}
	finally
	{
		log( LOG_DEBUG_LEVEL_1, "RM::retrieveServiceProvidersForResource.execute() " ) ;
	}
	return aRetrieveServiceProvidersForResourceReturn ;
}
}
