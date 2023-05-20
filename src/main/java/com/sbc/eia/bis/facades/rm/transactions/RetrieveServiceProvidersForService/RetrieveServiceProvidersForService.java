// $Id: RetrieveServiceProvidersForService.java,v 1.10 2005/11/16 21:33:17 jp2854 Exp $

package com.sbc.eia.bis.facades.rm.transactions.RetrieveServiceProvidersForService;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.InvalidServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.NotImplementedException;
import com.sbc.eia.bis.BusinessInterface.ServiceCenter;
import com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.rm.ASON.ASON;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersBusLayer;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForServiceIntf;
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
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn;
import com.sbc.eia.idl.rm.bishelpers.RetrieveServiceProvidersForServiceReturnBisHelper;
import com.sbc.eia.idl.rm_types.ServiceHandleObjectKeyKind_retrieveServiceProvidersForService;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.bishelpers.ObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectKeySeqBisHelper;

//#	6/1/2005	Chaitanya			Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1

/**
 * Insert the type's description here.
 * Creation date: (3/21/01 9:11:55 AM)
 * @author: Sam Lok - Local
 */
public class RetrieveServiceProvidersForService extends com.sbc.eia.bis.RmNam.utilities.TranBase
{
	private static final java.lang.String[] hostList = new String[]
	{
		ServiceProvidersBusLayer.class.getName(),
		ASON.class.getName()
	} ;
	public com.sbc.eia.bis.BusinessInterface.CompanyFactory companyFactory = null ;

/**
 * Insert the method's description here.
 * Creation date: (3/21/01 9:12:14 AM)
 * @param param java.util.Hashtable
 */
public RetrieveServiceProvidersForService(java.util.Hashtable param)
{
	super( param ) ;
}
/**
 * retrieveServiceProvidersForService method returns service providers that provide the specified service
 *				types at the specified service.
 * @return com.sbc.eia.idl.rm_2_0_0.RetrieveServiceProvidersForResourceReturn
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aServiceHandle com.sbc.eia.idl.types.ObjectKey
 * @param aServiceTypeHandles com.sbc.eia.idl.types.ObjectKey[]
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public RetrieveServiceProvidersForServiceReturn
execute(BisContext aContext,
		ObjectKey aServiceHandle,
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
	
	log( LOG_DEBUG_LEVEL_1, "RM::retrieveServiceProvidersForService.execute()" ) ;

	RetrieveServiceProvidersForServiceReturn aRetrieveServiceProvidersForServiceReturn = null ;
	try
	{

		// Check the basic input objects exist
		if ( aServiceHandle == null )
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
							formatInvalidData( String.class, "aServiceHandle" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		if ( aServiceTypeHandles == null )
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_TYPE_HANDLE,
							formatInvalidData( ObjectKey.class, "aServiceTypeHandles" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;

		// Log the input data
		log( LOG_INPUT_DATA, "aContext=<" + (new BisContextBisHelper(aContext)).toString() + ">" );
		log( LOG_INPUT_DATA, "aServiceHandle=<" + (new ObjectKeyBisHelper(aServiceHandle)).toString() + ">" );
		log( LOG_INPUT_DATA, "aServiceTypeHandles=<" + (new ObjectKeySeqBisHelper(aServiceTypeHandles)).toString() + ">" );


		// Validate the input: BisContext, TN and the PIC/LPIC flags

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


		// TN
		String aTN = null;
		try
		{
			aTN = aServiceHandle.aValue.trim();
		}
		catch (org.omg.CORBA.BAD_OPERATION e)
		{
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
							formatInvalidData( ObjectKey.class, "aServiceHandle.aValue" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		}
		catch (NullPointerException e)
		{
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
							formatInvalidData( ObjectKey.class, "aServiceHandle.aValue" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		}
		if ( aTN.length() < 1 )
			throwException(	ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
							formatInvalidData( ObjectKey.class, "aServiceHandle.aValue" ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;

		// only want to deal with TN
		if ( !aServiceHandle.aKind.trim().equalsIgnoreCase( ServiceHandleObjectKeyKind_retrieveServiceProvidersForService.TELEPHONENUMBER ) )
		{
			throwException(	ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
							formatInvalidData( ObjectKey.class,
											   "aServiceHandle.aKind",
											   aServiceHandle.aKind,
											   "TN is required.aKind not set to <" +
											   ServiceHandleObjectKeyKind_retrieveServiceProvidersForService.TELEPHONENUMBER +
											   ">." ),
							getEnv( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
		}

		// the type for PIC/LPIC
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
				aRetrieveServiceProvidersForServiceReturn =
					( (ServiceProvidersForServiceIntf)companyFactory.
					getCompany( new ServiceCenter( aContextOPM.getValue( BisContextProperty.SERVICECENTER ) ) ).
					getBusinessInterface( ServiceProvidersForServiceIntf.ServiceProvidersForServiceInterfaceName ) ).
					retrieveByService( aContext, aServiceHandle, aServiceTypeHandles, aLogger ) ;
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
			"RetrieveServiceProvidersForServiceReturn=<" +
			(new RetrieveServiceProvidersForServiceReturnBisHelper(aRetrieveServiceProvidersForServiceReturn)).toString() + ">");

	}
	finally
	{
		log( LOG_DEBUG_LEVEL_1, "RM::retrieveServiceProvidersForService.execute()" ) ;
	}
	return aRetrieveServiceProvidersForServiceReturn ;
}
}
