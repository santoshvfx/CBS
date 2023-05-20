// $Id: INEXS.java,v 1.8 2005/06/02 03:38:18 vc7563 Exp $
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
//#       (C) SBC Services, Inc 2001-2003.  All Rights Reserved.
//#
//# History :
//# Date        | Author                   | Notes
//# ----------------------------------------------------------------------------
//#	4/2001		Sam Lok			            Creation.
//#	01/09/02	Sam Lok			            3.0.0:  Updated to use RM, and LIM version 3 IDL.
//#										    Added SNET support: new CARE class.
//#	01/14/02	Sam Lok			            3.0.0:  Updated to use non-versioned IDL bundle.
//#	2/21/2002	Sam Lok			            DR45126: Use ExceptionBuilder for APTOS and INEXS.
//#	2/25/2002	Sam Lok			            DR45369: Check zero length Resource Handle.
//#	1/10/2003	Sam Lok			            RM64454: Upgraded to eLogging 1.3 standard.
//#	1/13/2004	Sam Lok		            	RM127209: Log level changes.
//# 8/17/2004   Stevan Dunkin               DR114465: No error message when ServiceTypeHandles are invalid.
//# 8/28/2004   Jinmin Ni                   RM141220: add OCN by clli inquiry
//# 04/04/2005  Manjula Goniguntla          Updated to comply with the change of ObjectHandle with ObjectKey.


package com.sbc.eia.bis.BusinessInterface.rm.INEXS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
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
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;


/**
 * HOSTTemplate is the class which defines a template for a host object.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 */
 
public class INEXS extends Host 
	implements ServiceProvidersForResourceIntf
{
	public static final String HostClass_INEXS = INEXS.class.getName() ;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ServiceProvidersForResourceInterfaceName /*, AnotherInterface */ 
		};
	// This belongs here - the list of immediate children (classes that derive from this class)
	private static final String hostList[] = null ;

	// some INEXS specific defines
	final static String INEXS_LPIC_CODE = "A" ;
	final static String INEXS_PIC_CODE = "E" ;
	final static String INEXS_BOTH_CODE = "B" ;

	private Clli2PicView aClli2PicView = null;
/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public INEXS(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
}
/**
 * private String extractPic(String picCode)
 *		will remove the first and last 3 digits of a 10 digit picCode.
 *		a null string will return if picCode is shorter than 4 bytes or a partial
 *		extract is done if it is shorter than 7.
 * Creation date: (5/17/00 5:11:41 PM)
 * @return java.lang.String
 * @param picCode java.lang.String
 */
private String extractPic(String picCode) 
{
	utility.log(LogEventId.DEBUG_LEVEL_1, "INEXS::extractPic()" ) ;
	final int begin = 3 ;
	final int picCodeSize = 4 ;

	int thisPicLength = picCode.length() ;

	if ( thisPicLength < begin+1 ) return "" ;	// can't extract at all
	
	String tmpStr = null ;
	if ( thisPicLength < begin+picCodeSize )
		tmpStr = picCode.substring( begin ) ;
	else
		tmpStr = picCode.substring( begin, begin+picCodeSize ) ;

	return tmpStr ;	
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
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "INEXS::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] { 
		new Selector(new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
					 ServiceProvidersForResourceInterfaceName, HostClass_INEXS)
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
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "INEXS::getHostList()");

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
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "INEXS::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * The getSupportedCompanies method is called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws InvalidCompanyException, InvalidStateException, NullDataException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "INEXS::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] { new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null) };
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
	String myMethodName = "INEXS::retrievePicByCLLI()" ;
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
		
	
    ArrayList Clli2PicRows = null;
	ArrayList ServiceProvidersForServiceTypeList = new ArrayList() ;
    boolean hasData = false;
    
	for (int picIdx = 0; picIdx < aServiceTypeHandles.length; picIdx++)
	{
		// translate the ServiceTypeHandle to APTOS's CIC_FLAG
		String cicFlag = null;
		try
		{
			cicFlag = aServiceTypeHandles[picIdx].aValue;
		}
		catch (org.omg.CORBA.BAD_OPERATION e)
		{
			break;
		} // skip, just in case, but shouldn't happen
		catch (NullPointerException e)
		{
			break;
		} // skip, just in case, but shouldn't happen		
		utility.log(LogEventId.INFO_LEVEL_1, "Looking up ServiceTypeHandle = <" + cicFlag + ">");
		if (cicFlag == null)
			cicFlag = ""; // need a string later
		String cicFlagCode = null;
		String OKeyKind = null;
		if (cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.PIC))
		{
			cicFlagCode = INEXS_PIC_CODE;
			OKeyKind = ServiceTypeHandleObjectKey.PIC;
		}
		else if (cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.LPIC))
		{
			cicFlagCode = INEXS_LPIC_CODE;
			OKeyKind = ServiceTypeHandleObjectKey.LPIC;
		}
        else if ( cicFlag.equalsIgnoreCase( ServiceTypeHandleObjectKey.LOCAL_VOICE ) )
        {
            ;//do nothing, fall through. should not remove.
        }
		else
		{
			utility.log(LogEventId.INFO_LEVEL_1, "Skipping ServiceTypeHandle = <" + cicFlag + ">");
			continue;
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
			{
				// Get a database connection and do the lookup
				utility.log(
					LogEventId.REMOTE_CALL,
					(String) getProperties().get("jdbcURL"),
					(String) getProperties().get("jdbcURL"),
					(String) getProperties().get("jdbcUSERID"),
					"INEXS::Clli2PicView.find()");
				
				try
				{
					if (aClli2PicView == null)
						aClli2PicView = new Clli2PicView((Properties) getProperties(), utility);
					Clli2PicRows = aClli2PicView.find(aCLLI, utility);
				}
				catch (SQLException e)
				{
					ExceptionBuilder.parseException(
						aContext,
						(String) getProperties().get("EXCEPTION_BUILDER_INEXS_RULE_FILE"),
						null,
						null,
						e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						null,
						null,
						null);
				}
				finally
				{
					utility.log(
						LogEventId.REMOTE_RETURN,
						(String) getProperties().get("jdbcURL"),
						(String) getProperties().get("jdbcURL"),
						(String) getProperties().get("jdbcUSERID"),
						"INEXS::Clli2PicView.find()");
				}
				// Did we get any data?
				if (Clli2PicRows.isEmpty())
					utility.throwException(
						ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,
						"Resource not found.",
						(String) getProperties().get("BIS_NAME"),
						Severity.UnRecoverable);
                        
                hasData = true;
			}
            
			// traverse the result set and Build the response structure
			//
			Iterator Clli2PicIterator = Clli2PicRows.iterator();
			ArrayList ServiceProviderList = new ArrayList();
			while (Clli2PicIterator.hasNext())
			{
				Clli2PicRow aClli2PicRow = (Clli2PicRow) Clli2PicIterator.next();
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					aClli2PicRow.getCxrCd()
						+ "|"
						+ aClli2PicRow.getCxrNm()
						+ "|"
						+ aClli2PicRow.getAcnaCd()
						+ "|"
						+ aClli2PicRow.getPicTypeCd()
						+ "|");
				// keep only the PIC code we want and the ones that are maked as BOTH
				if (aClli2PicRow.getPicTypeCd().equalsIgnoreCase(cicFlagCode)
					|| aClli2PicRow.getPicTypeCd().equalsIgnoreCase(INEXS_BOTH_CODE))
				{
					ArrayList aSPPropertyList = new ArrayList();
					String CXR_CD = extractPic(aClli2PicRow.getCxrCd()); //extract just the pic code
					utility.log(
						LogEventId.DEBUG_LEVEL_1,
						"Add: " + CXR_CD + "|" + aClli2PicRow.getCxrNm() + "|" + aClli2PicRow.getAcnaCd() + "|");
					aSPPropertyList.add(new ObjectProperty(ServiceProviderProperty.CODE, CXR_CD));
					if (aClli2PicRow.getCxrNm() != null)
						aSPPropertyList.add(new ObjectProperty(ServiceProviderProperty.NAME, aClli2PicRow.getCxrNm()));
					if (aClli2PicRow.getAcnaCd() != null)
						aSPPropertyList.add(
							new ObjectProperty(ServiceProviderProperty.NAME_ABBREVIATION, aClli2PicRow.getAcnaCd()));
					ServiceProvider aSP = new ServiceProvider();
					aSP.aProperties =
						(ObjectProperty[]) aSPPropertyList.toArray(new ObjectProperty[aSPPropertyList.size()]);
					aSP.aServiceProviderHandle =
						/*new ObjectHandle(
							(ObjectIdOpt) IDLUtil.toOpt(ObjectIdOpt.class, null),
							(ObjectKeyOpt) IDLUtil.toOpt(ObjectKeyOpt.class, null));*/
						new ObjectKey(new String(),new String());	
					aSP.aOperatingCompanyNumbers =
						(OperatingCompanyNumberSeqOpt) IDLUtil.toOpt(OperatingCompanyNumberSeqOpt.class, null);
					ServiceProviderList.add(aSP);
				}
			}
			utility.log(
				LogEventId.INFO_LEVEL_1,
				"Randomize " + ServiceProviderList.size() + " elements in the ServiceProviderList...");
			ArrayListHelper.randomize(ServiceProviderList);
			utility.log(LogEventId.INFO_LEVEL_1, "Build the ServiceProvidersForServiceTypeList...");
			ServiceProvidersForServiceTypeList.add(
				new ServiceProvidersForServiceType(
					/*new ObjectHandle(
						(ObjectIdOpt) IDLUtil.toOpt(ObjectIdOpt.class, null),
						(ObjectKeyOpt) IDLUtil.toOpt(ObjectKeyOpt.class,*/
					new ObjectKey(cicFlag, OKeyKind),
					(ServiceProvider[]) ServiceProviderList.toArray(new ServiceProvider[ServiceProviderList.size()])));
		}
	}
    //DR114465: No error message when ServiceTypeHandles are invalid.
    //  did we get any data?
    if ( ServiceProvidersForServiceTypeList.size() < 1 )
         utility.throwException(    ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,
                             "Resource not found.",
                             (String)getProperties().get( "BIS_NAME" ), Severity.UnRecoverable );


	utility.log( LogEventId.INFO_LEVEL_1, "Build the RetrieveServiceProvidersForResourceReturn with " +
										  ServiceProvidersForServiceTypeList.size() +
										  " ServiceProvidersForServiceType elements..." ) ;
	return new RetrieveServiceProvidersForResourceReturn( aContext,
			(ServiceProvidersForServiceType[])ServiceProvidersForServiceTypeList.toArray(new ServiceProvidersForServiceType[ServiceProvidersForServiceTypeList.size()]) ) ;
}
}
