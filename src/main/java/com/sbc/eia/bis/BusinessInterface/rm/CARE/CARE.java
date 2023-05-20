// $Id: CARE.java,v 1.16 2005/06/02 03:36:29 vc7563 Exp $
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
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//#	4/2001		Sam Lok			Creation.
//#	01/09/02	Sam Lok			3.0.0:  Updated to use RM, and LIM version 3 IDL.
//#										Added SNET support: new CARE class.
//#	01/14/02	Sam Lok			3.0.0:  Updated to use non-versioned IDL bundle.
//#	2/21/2002	Sam Lok			DR45126: Use ExceptionBuilder for APTOS and INEXS.
//#	2/25/2002	Sam Lok			DR45369: Check zero length Resource Handle.
//#	1/10/2003	Sam Lok			RM64454: Upgraded to eLogging 1.3 standard.
//#	1/13/2004	Sam Lok			RM127209: Log level changes.
//# 07/16/04	Stevan Dunkin	9.0.0	RM128789: IDPassword migration
//# 8/17/2004   Stevan Dunkin   DR114465: No error message when ServiceTypeHandles are invalid.
//# 8/28/2004   Jinmin Ni       RM141220: add OCN by clli inquiry
//# 11/15/2004	Jon Costa		RM120184: Add TOS & CAUTH to PIC/LPIC.
//#	04/04/2005	Sriram Chevuturu			Updated to comply with the change of ObjectHandle with ObjectKey.
//#	04/05/2005	Sriram Chevuturu			Some more updates to comply with the change of ObjectHandle with ObjectKey.


package com.sbc.eia.bis.BusinessInterface.rm.CARE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ArrayListHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProviderType;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForResourceIntf;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.sm_types.CarrierAuthorizationValue;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.sm_types.TypeOfServiceValue;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;

/**
 * HOSTTemplate is the class which defines a template for a host object.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 */
public class CARE extends Host 
	implements ServiceProvidersForResourceIntf
{
	public static final String HostClass_CARE = CARE.class.getName() ;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ServiceProvidersForResourceInterfaceName /*, AnotherInterface */ 
		};
	// This belongs here - the list of immediate children (classes that derive from this class)
	private static final String hostList[] = null ;

	// Some CARE specific defines
	final static String CARE_PIC_CODE  = "E" ;
	final static String CARE_LPIC_CODE = "A" ;
	final static String CARE_BOTH_CODE = "B" ;
	private static java.util.Properties CAREProperties = null ;
	private CarrierByClliView aCarrierByClliView = null;
	private final static java.lang.String[] DisplayTextList = 
		new String[] {	"",
						CarrierAuthorizationValue.OFFER,
						CarrierAuthorizationValue.DO_NOT_OFFER,
						CarrierAuthorizationValue.DO_NOT_ROTATE,
						CarrierAuthorizationValue.FROZEN_OBSOLETE,
						CarrierAuthorizationValue.NOT_AVAILABLE
		} ;
	private final static short DisplayTextListSIZE = 6;
	private final static short defaultDisplayTextInd = 2;	// Do Not Offer
	private final static Properties typeOfSvcArray = new Properties();
/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public CARE(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
	
	// Initialize type of service (TOS) array for cross reference lookups.
	// The table CARRIER_OPTIONS.ORDERABLE_IND field will return a letter,
	// we return the TypeOfServiceValue.
	typeOfSvcArray.put("A", TypeOfServiceValue.ALL);
	typeOfSvcArray.put("B", TypeOfServiceValue.BUSINESS);
	typeOfSvcArray.put("C", TypeOfServiceValue.COIN);
	typeOfSvcArray.put("R", TypeOfServiceValue.RESIDENCE);
	typeOfSvcArray.put("N", TypeOfServiceValue.NOT_APPLICABLE);
	typeOfSvcArray.put("X", TypeOfServiceValue.BUSINESS_COIN);
	typeOfSvcArray.put("Y", TypeOfServiceValue.RESIDENCE_COIN);
	typeOfSvcArray.put("Z", TypeOfServiceValue.BUSINESS_RESIDENCE);
}
/**
 * 2. Builds the CAREProperties cache from the CARE_JDBC_FILE.<br>
 *
 * Creation date: (5/1/01 2:28:16 PM)
 * @exception InvalidData The exception description.
 * @exception AccessDenied The exception description.
 * @exception BusinessViolation The exception description.
 * @exception SystemFailure The exception description.
 * @exception NotImplemented The exception description.
 * @exception ObjectNotFound The exception description.
 * @exception DataNotFound: No data found.
 */
private void buildCaches()
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
{
	utility.log( LogEventId.DEBUG_LEVEL_1, "CARE::buildCARECaches" ) ;
	
	// load the CARE JDBC Properties cache
	if ( CAREProperties == null )
	{
		utility.log( LogEventId.DEBUG_LEVEL_1, "Reading the CARE JDBC Properties file.." ) ;
		try
		{
			CAREProperties = PropertiesFileLoader.read( (String)getProperties().get( "CARE_JDBC_FILE" ), utility ) ;
			
			//Decrypt password with given key
		 	Encryption enc = new Encryption() ;
		  	CAREProperties.put("jdbcPASSWORD", enc.decodePassword( CAREProperties.getProperty("OSS_PUBLIC_KEY"),
																   CAREProperties.getProperty("jdbcPASSWORD")));

		}
		catch (FileNotFoundException e)
		{
			utility.throwException( ExceptionCode.ERR_RM_CARE_JDBC_FILE_NOT_FOUND,
									"FileNotFoundException: " + e.getMessage(),
									(String)getProperties().get("BIS_NAME" ), Severity.Recoverable, e ) ;
		}
		catch (IOException e)
		{
			utility.throwException( ExceptionCode.ERR_RM_CARE_JDBC_FILE_READ_ERROR,
									"IOException while reading CARE_JDBC_FILE: " + e.getMessage(),
									(String)getProperties().get("BIS_NAME" ), Severity.Recoverable, e ) ;
		}
	}
}
/**
 * Format a CIC_CODE to be 4 digit with leading zeros.
 * Creation date: (1/21/02 1:46:43 PM)
 * @return java.lang.String
 */
private static String formatCicCode( String aCicCode )
{
	int myCicCode ;
	try
	{
		myCicCode = Integer.parseInt( aCicCode ) ;
	}
	catch ( NumberFormatException e ) { return aCicCode ; } ;

	return (new DecimalFormat( "0000" )).format( myCicCode ) ;

}
/**
 * The getCacheEntries method is called by the Host Factory to get entries for preloading into the Host cache.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "CARE::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] { 
		new Selector(new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
					 ServiceProvidersForResourceInterfaceName, HostClass_CARE)
	};
}
/**
 * The getHostList method is called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "CARE::getHostList()");

	// Return the list of immediate children (classes that derive from this class)
	return hostList; /* null if no children */
}
/**
 * The getInterfaceList method is called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "CARE::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * The getSupportedCompanies method is called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws InvalidCompanyException, InvalidStateException, NullDataException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "CARE::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] { new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null) };
}
/**
 * Supports the retrieveServiceProvidersForResource business transaction.
 * Creation date: (4/25/01 9:10:57 AM)
 * @return RetrieveServiceProvidersForResourceReturn
 * @param aContext BisContext
 * @param aCLLI String
 * @param aServiceTypeHandles ObjectKey[]
 * @exception InvalidData The exception description.
 * @exception AccessDenied The exception description.
 * @exception BusinessViolation The exception description.
 * @exception SystemFailure The exception description.
 * @exception NotImplemented The exception description.
 * @exception ObjectNotFound The exception description.
 * @exception DataNotFound: No data found.
 */
public
RetrieveServiceProvidersForResourceReturn retrievePicByCLLI( BisContext aContext,
															  String aCLLI,
															 ObjectKey[] aServiceTypeHandles )
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
{
	String myMethodName = "CARE::retrievePicByCLLI()" ;
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName) ;

	// company code
	ObjectPropertyManager opm =	new ObjectPropertyManager( aContext.aProperties ) ;
	String companyCd = opm.getValue( BisContextProperty.CUSTOMERNAME ) ;
	if ( companyCd == null )
	{
		utility.throwException(	ExceptionCode.ERR_RM_MISSING_CUSTOMERNAME,
								TranBase.formatInvalidData( BisContext.class, "aContext.aProperties.CUSTOMERNAME" ),
								(String)getProperties().get( "BIS_NAME" ),
								Severity.UnRecoverable ) ;
	}
		

	buildCaches() ;


	ArrayList CarrierByClliRows = null ;

	// Got data, process the result set
	ArrayList ServiceProvidersForServiceTypeList = new ArrayList() ;
    boolean hasData = false;
	for ( int picIdx=0; picIdx<aServiceTypeHandles.length; picIdx++ )
	{
		// translate the ServiceTypeHandle to CARE's CIC_FLAG
		String cicFlag = null ;
		try
		{
			cicFlag = aServiceTypeHandles[picIdx].aValue ;
		}
		catch (org.omg.CORBA.BAD_OPERATION e)		{ break ; } // skip, just in case, but shouldn't happen
		catch (NullPointerException e)				{ break ; }	// skip, just in case, but shouldn't happen		
		utility.log( LogEventId.INFO_LEVEL_1, "Looking up ServiceTypeHandle = <" + cicFlag + ">" ) ;
		if ( cicFlag == null ) cicFlag = "" ;					// need a string later

		String cicFlagCode = null ;
		String OKeyKind = null ;
		if ( cicFlag.equalsIgnoreCase( ServiceTypeHandleObjectKey.PIC ) )
		{
			cicFlagCode = CARE_PIC_CODE ;
			OKeyKind = ServiceTypeHandleObjectKey.PIC ;
		}
		else if ( cicFlag.equalsIgnoreCase( ServiceTypeHandleObjectKey.LPIC ) )
		{
			cicFlagCode = CARE_LPIC_CODE ;
			OKeyKind = ServiceTypeHandleObjectKey.LPIC ;
		}
        else if ( cicFlag.equalsIgnoreCase( ServiceTypeHandleObjectKey.LOCAL_VOICE ) )
        {
            ;//do nothing.  fall through. should not remove
        }
		else
		{
			utility.log( LogEventId.INFO_LEVEL_1, "Skipping ServiceTypeHandle = <" + cicFlag + ">" ) ;
			continue ;
		}

		//ocn inquiry
		if (cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.LOCAL_VOICE))
		{
			ServiceProviderType aServiceProviderType =
				new ServiceProviderType();
			ServiceProvidersForServiceTypeList.add(
				aServiceProviderType.buildServiceProvidersType(
					getProperties(),
					utility,
					aCLLI,
					cicFlag,
					aContext));
		}
        else //pic/lpic inquiry
		{
			if (!hasData)
			{ // Get a database connection and do the lookup
				utility.log(
					LogEventId.REMOTE_CALL,
					(String) CAREProperties.get("jdbcURL"),
					(String) CAREProperties.get("jdbcUSERID"),
					(String) CAREProperties.get("TABLE_SPACE"),
					"CARE::CarrierByClliView.find()");
				try
				{
					if (aCarrierByClliView == null)
						aCarrierByClliView =
							new CarrierByClliView(CAREProperties, utility);
					CarrierByClliRows = aCarrierByClliView.find(aCLLI, utility);
				}
				catch (SQLException e)
				{
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"e.getErrorCode() = <" + e.getErrorCode() + ">");
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"e.getSQLState()  = <" + e.getSQLState() + ">");
					Properties tagValues = new Properties();
					tagValues.setProperty("SYSTEM", "CARE");
					ExceptionBuilder.parseException(
						aContext,
						(String) getProperties().get(
							"EXCEPTION_BUILDER_DB2_RULE_FILE"),
						null,
						e.getSQLState(),
						e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						e,
						utility,
						"CARE",
						null,
						tagValues);
				}
				finally
				{
					utility.log(
						LogEventId.REMOTE_RETURN,
						(String) CAREProperties.get("jdbcURL"),
						(String) CAREProperties.get("jdbcUSERID"),
						(String) CAREProperties.get("TABLE_SPACE"),
						"CARE::CarrierByClliView.find()");
				}
				// Did we get any data?
				if (CarrierByClliRows.isEmpty())
					utility.throwException(
						ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,
						"Resource not found.",
						(String) getProperties().get("BIS_NAME"),
						Severity.UnRecoverable);
                hasData = true;
			}
		// traverse the result set and Build the response structure
		//
		Iterator CarrierByClliIterator = CarrierByClliRows.iterator() ;
		ArrayList ServiceProviderList = new ArrayList() ;
		String CIC_CODE  = "";
		String NAME      = "";
		String TOS_CODE  = "";
		String CAUTH_TXT = "";
		int myDisplayInd;
		
		while( CarrierByClliIterator.hasNext() )
		{
			CarrierByClliRow aCarrierByClliRow = (CarrierByClliRow)CarrierByClliIterator.next() ;
			utility.log( LogEventId.DEBUG_LEVEL_2, aCarrierByClliRow.getCIC_CODE() + "|" +
												   aCarrierByClliRow.getNAME() + "|" +
												   aCarrierByClliRow.getACNA() + "|" +
												   aCarrierByClliRow.getJURISDICTIONAL_IND() + "|" +
												   aCarrierByClliRow.getDISPLAY_IND() + "|" +
												   aCarrierByClliRow.getORDERABLE_IND() + "|" ) ;

			// keep only the PIC code we want and the ones that are maked as BOTH
			if ( aCarrierByClliRow.getJURISDICTIONAL_IND().equalsIgnoreCase( cicFlagCode ) ||
				 aCarrierByClliRow.getJURISDICTIONAL_IND().equalsIgnoreCase( CARE_BOTH_CODE ) )
			{
				ArrayList aSPPropertyList = new ArrayList() ;
				
				CIC_CODE = formatCicCode( aCarrierByClliRow.getCIC_CODE() ) ;

				try
				{
					NAME =
						((aCarrierByClliRow.getNAME() == null)
							? null
							: aCarrierByClliRow.getNAME().substring(0, 35));
				}
				catch (IndexOutOfBoundsException e)
				{
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"Index out of bounds exception for ["
							+ aCarrierByClliRow.getNAME()
							+ "], will assign with no truncation.");
					NAME = aCarrierByClliRow.getNAME();
				}

				TOS_CODE =
					(String) typeOfSvcArray.getProperty(
						aCarrierByClliRow.getORDERABLE_IND().toUpperCase());

				try
				{
					myDisplayInd =
						Integer.parseInt(
							aCarrierByClliRow.getDISPLAY_IND());
					CAUTH_TXT =
						DisplayTextList[myDisplayInd <= DisplayTextListSIZE
							? myDisplayInd
							: defaultDisplayTextInd];
				}
				catch (NumberFormatException e)
				{
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"Number format exception for ["
							+ aCarrierByClliRow.getDISPLAY_IND()
							+ "], using default for CAUTH_TXT.");
					CAUTH_TXT = DisplayTextList[defaultDisplayTextInd];
				}
				
				
				utility.log( LogEventId.DEBUG_LEVEL_1, "Add: " + 
												   		CIC_CODE							+ "|" +
														NAME 								+ "|" +
												   		aCarrierByClliRow.getACNA().trim()	+ "|" +
												   		TOS_CODE							+ "|" +
												   		CAUTH_TXT							+ "|" ) ;

				// Add to the list
				aSPPropertyList.add( new ObjectProperty( ServiceProviderProperty.CODE,
									 					 CIC_CODE ) ) ;
				if ( NAME != null )
					aSPPropertyList.add( new ObjectProperty( ServiceProviderProperty.NAME,
															 NAME ) ) ;
				if ( aCarrierByClliRow.getACNA() != null )
					aSPPropertyList.add( new ObjectProperty( ServiceProviderProperty.NAME_ABBREVIATION,
															 aCarrierByClliRow.getACNA().trim() ) ) ;
				if ( TOS_CODE != null )
					aSPPropertyList.add( new ObjectProperty( ServiceProviderProperty.TYPE_OF_SERVICE, TOS_CODE ));										 
				
				aSPPropertyList.add( new ObjectProperty( ServiceProviderProperty.CARRIER_AUTHORIZATION, CAUTH_TXT ));
	
				ServiceProvider aSP = new ServiceProvider() ;
				aSP.aProperties = (ObjectProperty[])aSPPropertyList.toArray( new ObjectProperty[aSPPropertyList.size()] ) ;
				
				aSP.aServiceProviderHandle = new ObjectKey(new String(),new String());
				
				aSP.aOperatingCompanyNumbers = (OperatingCompanyNumberSeqOpt)
													IDLUtil.toOpt( OperatingCompanyNumberSeqOpt.class, null ) ;

				ServiceProviderList.add( aSP ) ;
			}
		}
		utility.log( LogEventId.INFO_LEVEL_1, "Randomize " + ServiceProviderList.size() +
											  " elements in the ServiceProviderList..." ) ;
		ArrayListHelper.randomize( ServiceProviderList ) ;

		utility.log( LogEventId.INFO_LEVEL_1, "Build the ServiceProvidersForServiceTypeList..." ) ;
		ServiceProvidersForServiceTypeList.add( new ServiceProvidersForServiceType( 
																new ObjectKey( cicFlag, OKeyKind ),
				(ServiceProvider[])ServiceProviderList.toArray(new ServiceProvider[ServiceProviderList.size()]) )) ;
        }//pic/lpic inquiry
    }

             //DR114465: No error message when ServiceTypeHandles are invalid. 
           //      did we get any data? 
	if (ServiceProvidersForServiceTypeList.size() < 1)
		utility.throwException(
			ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,
			"Resource not found.",
			(String) getProperties().get("BIS_NAME"),
			Severity.UnRecoverable);
	utility.log(
		LogEventId.INFO_LEVEL_1,
		"Build the RetrieveServiceProvidersForResourceReturn with "
			+ ServiceProvidersForServiceTypeList.size()
			+ " ServiceProvidersForServiceType elements...");
	return new RetrieveServiceProvidersForResourceReturn(
		aContext,
		(
			ServiceProvidersForServiceType[]) ServiceProvidersForServiceTypeList
				.toArray(
			new ServiceProvidersForServiceType[ServiceProvidersForServiceTypeList
				.size()]));

}
}
