// $Id: ASON.java,v 1.28 2007/08/15 15:07:08 jc1421 Exp $
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
//#	9/2001  	Sam Lok			Creation.
//#	2/14/2002  	Sam Lok			DR42571 for AIT: intermittent lookup failure.  The ASON class was
//#							    using the wrong field to construct EXK.
//#	3/4/2002	Sam Lok			DR46015: Fixed to ignore exception from SM saying TN is not active account.
//#	3/15/2002	Sam Lok			Added BISPERF logging to ASON class.
//#	3/29/2002	Sam Lok			Added SQLException ExceptionBuilder handling in ASON class.
//#	4/22/2002	Sam Lok			DR49476: Fixed AIT swapped carrier_pic (ACNA) and carrier_picx (PIC/LPIC) fields.
//#	7/18/2002	Sam Lok			DR55882: Change SMClient to be a cache object.
//#	12/6/2002	Sam Lok			DR62693: Need to initialize member attributes everytime: exk and sag_co.
//#	7/6/2003	Sam Lok			RM95685: Check SM status for non-live status.
//#	1/13/2004	Sam Lok			RM127209: Log level changes.
//# 8/17/2004   Stevan Dunkin   DR114465: No error message when ServiceTypeHandles are invalid.
//# 8/28/2004   Jinmin Ni       RM141220: add OCN by clli inquiry
//#	10/25/2004	Jinmin Ni		RM120184: Add CAUTH and TOS to return.
//#	04/01/2005	Sriram Chevuturu		Updated to comply with the change of ObjectHandle with ObjectKey.
//#	04/05/2005	Sriram Chevuturu		Some more updates to comply with the change of ObjectHandle with ObjectKey.
//# 11/01/2005 Sumana Roy		Added com.sbc.eia.bis.RmNam.utilities.Logger to retrieveByService()
//# 01/11/2007 Jon Costa		SEV1 PR 19196450, revert to earlier revision, added try/finally for DB Disconnect
//# 03/23/2007 Jon Costa		DR62548: getASONConnection() related to DR for CVOPIReferenceTable to correct retry logic.
//# 08/15/2007 Jon Costa		PR20404361: try/catch added for null value(s) from SM BIS parsing SubscriptionAccountReturn.


package com.sbc.eia.bis.BusinessInterface.rm.ASON;
/**
 * This class implements the retrieve() method for AIT.  
 * Creation date: (09/25/01 3:02:10 PM)
 * @author: Sam Lok
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.database.DBConnection;
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
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForServiceIntf;
import com.sbc.eia.bis.RmNam.components.CheckCSRFromSM;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
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
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn;
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.sm.SubscriptionAccountReturn;
import com.sbc.eia.idl.sm_types.Affiliate;
import com.sbc.eia.idl.sm_types.CarrierAuthorizationValue;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationType;
import com.sbc.eia.idl.sm_types.TypeOfServiceValue;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
public class ASON
	extends Host
	implements ServiceProvidersForServiceIntf, ServiceProvidersForResourceIntf
{
	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ServiceProvidersForServiceInterfaceName,
		/*, AnotherInterface */
		ServiceProvidersForResourceInterfaceName };
	// This belongs here - the list of immediate children (classes that derive from this class)
	//		new String[] {
	//			// Use this normally: HOSTTemplateChild1.class.getName()
	//			null
	//		};
	private static final String hostList[] = null;
	private java.util.Hashtable aProperties;
	final static String ASON_PIC_CODE = "E";
	private String sag_co = null;
	private String exk = null;
	final static String ASON_LPIC_CODE = "A";
	final static String ASON_BOTH_CODE = "B";
	private SmClient aSMClient = null;
	private CheckCSRFromSM myCSRStatusChecker = null;
	/**
	 * Class constructor accepting company, utility and properties.
	 * Creation date: (12/19/00 11:29:49 AM)
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
	 * @param aProperties java.util.Hashtable
	 */
	public ASON(
		Company aCompany,
		com.sbc.bccs.utilities.Utility aUtility,
		java.util.Hashtable aProperties)
	{
		super(aCompany, aUtility, aProperties);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (10/25/01 12:23:41 PM)
	 * @param aSubscriptionAccountReturn com.sbc.eia.idl.sm_2_0_0.SubscriptionAccountReturn
	 */
	private void extract(SubscriptionAccountReturn aSubscriptionAccountReturn)
	{
		for (int i = 0;
			i < aSubscriptionAccountReturn.aSubscriptionAccounts.length;
			i++)
		{
			for (int j = 0;
				j
					< aSubscriptionAccountReturn
						.aSubscriptionAccounts[i]
						.aServices
						.length;
				j++)
			{
				for (int k = 0;
					k
						< aSubscriptionAccountReturn
							.aSubscriptionAccounts[i]
							.aServices[j]
							.aProductSubscriptions
							.length;
					k++)
				{
					for (int l = 0;
						l
							< aSubscriptionAccountReturn
								.aSubscriptionAccounts[i]
								.aServices[j]
								.aProductSubscriptions[k]
								.aPropertyValues
								.length;
						l++)
					{
						if (aSubscriptionAccountReturn
							.aSubscriptionAccounts[i]
							.aServices[j]
							.aProductSubscriptions[k]
							.aPropertyValues[l]
							.aPropertyValueHandle
							.theValue()
							.aValue
							.equalsIgnoreCase("SAG"))
						{
							sag_co =
								aSubscriptionAccountReturn
									.aSubscriptionAccounts[i]
									.aServices[j]
									.aProductSubscriptions[k]
									.aPropertyValues[l]
									.aValue;
						}
						if (aSubscriptionAccountReturn
							.aSubscriptionAccounts[i]
							.aServices[j]
							.aProductSubscriptions[k]
							.aPropertyValues[l]
							.aPropertyValueHandle
							.theValue()
							.aValue
							.equalsIgnoreCase("EXK"))
						{
							exk =
								aSubscriptionAccountReturn
									.aSubscriptionAccounts[i]
									.aServices[j]
									.aProductSubscriptions[k]
									.aPropertyValues[l]
									.aValue;
						}
						if (!((sag_co == null) || (exk == null)))
						{
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (10/31/01 11:42:39 AM)
	 */
	public DBConnection getASONConnection() throws SQLException
	{
		ASON_DBconnection ASON_conn = new ASON_DBconnection();
		DBConnection conn = null;

		int reTryCnt = 0;
		while (reTryCnt++ < 2)
		{
			try
			{
				utility.log(LogEventId.INFO_LEVEL_1, "Trying to Connect to the database");
				conn = ASON_conn.getDBconnection(getProperties(), utility);
				reTryCnt++;
			}
			catch (StaleConnectionException ex)
			{
				//retry only once
				if (reTryCnt == 2)
				{
					throw ex;
				}
				conn = null;
				utility.log(TranBase.LOG_INFO_LEVEL_2, "*****Getting Connection second time*****");
			}
		}
		return conn;
	}
	/**
	 * Called by the Host Factory to get entries for preloading into the Host cache.
	 * 	This method must be overridden.
	 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
	 */
	public static Selector[] getCacheEntries(
		com.sbc.bccs.utilities.Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "ASON::getCacheEntries()");
		// Add entries to the HostFactory cache at start time to avoid long searches
		return new Selector[] {
			new Selector(
				new Company(
					Company.Company_Ameritech,
					State.getAnAnyState(),
					null,
					null),
				ServiceProvidersForServiceInterfaceName,
				ASON.class.getName()),
			new Selector(
				new Company(
					Company.Company_Ameritech,
					State.getAnAnyState(),
					null,
					null),
				ServiceProvidersForResourceInterfaceName,
				ASON.class.getName())};
	}
	/**
	 * Called by the Host Factory to obtain a list of hosts that are the immediate
	 * 	children of the called class.
	 *	This method must be overridden.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */
	public static String[] getHostList(
		com.sbc.bccs.utilities.Utility aUtility)
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "ASON::getHostList()");
		// Return the list of immediate children (classes that derive from this class)
		return hostList; /* null if no children */
	}
	/**
	 * Called by the Host Factory to obtain a list of the interfaces 
	 *	that are the supported by the called class.
	 * 	This method must be overridden.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */
	public static String[] getInterfaceList(
		com.sbc.bccs.utilities.Utility aUtility)
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "ASON::getInterfaceList()");
		// Return the list of interfaces supported by this host
		return interfaceList;
	}
	public SubscriptionAccountReturn getSubAccountReturn(
		BisContext aContext,
		ObjectKey aServiceHandle,
		SubscriptionAccountInformationType aType,
		Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		aUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"ASON::getSubAccountReturn()");
		SubscriptionAccountReturn result = null;
		
		
		if (aSMClient == null)
			try
			{
				//Connect to SM Bean for WTN
				aSMClient =
					new SmClient();
//						(String) getProperties().get("SM_PROVIDER_URL"),
//						(String) getProperties().get("SM_BIS_NAME"),
//						(String) getProperties().get("BIS_NAME"),
//						(String) getProperties().get(
//							"INITIAL_CONTEXT_PROPERTIES_FILE"));
			}
			catch (NullPointerException e)
			{
				utility.throwException(
					ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
					TranBase.formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aValue"),
					(String) getProperties().get("BIS_NAME"),
					Severity.UnRecoverable);
			}
		com.sbc.eia.idl.sm.SmFacade smBean = null;
		try
		{
			smBean = aSMClient.getSmBean(aContext, aUtility);
		}
		catch (Exception e)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
				"Exception while trying to get SMBean reference "
					+ e.getMessage(),
				(String) getProperties().get("BIS_NAME"),
				Severity.UnRecoverable);
		}
	
		try
		{
			result =
				smBean.retrieveSubscriptionAccountsForService(
					TranBase.setBisContextAppl(
						aContext,
						(String) getProperties().get(
							"BIS_CONTEXT_APPLICATION")),
					aServiceHandle,
					Affiliate.TELCO,
					aType,
					(StringOpt) IDLUtil.toOpt(StringOpt.class, null),
					(EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null));
		}
		/*catch (RemoteException e)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_SMCLIENT_EXCEPTION,
				"RemoteException when trying to call retrieveSubscriptionAccountsForService from SM: "
					+ e.getMessage(),
				(String) getProperties().get("BIS_NAME"),
				Severity.UnRecoverable);
		}*/
		catch (NotImplemented e)
		{
			throwSmException(
				aContext,
				aServiceHandle,
				e.aExceptionData,
				aUtility);
		}
		catch (SystemFailure e)
		{
			throwSmException(
				aContext,
				aServiceHandle,
				e.aExceptionData,
				aUtility);
		}
		catch (InvalidData e)
		{
			throwSmException(
				aContext,
				aServiceHandle,
				e.aExceptionData,
				aUtility);
		}
		catch (ObjectNotFound e)
		{
			// do nothing
			result = null;
		}
		catch (DataNotFound e)
		{
			// do nothing
			result = null;
		}
		catch (AccessDenied e)
		{
			throwSmException(
				aContext,
				aServiceHandle,
				e.aExceptionData,
				aUtility);
		}
		catch (BusinessViolation e)
		{
			throwSmException(
				aContext,
				aServiceHandle,
				e.aExceptionData,
				aUtility);
			result = null; // hit BusinessRule 1 if we return, no CSI
		}
		aSMClient = null;
		smBean = null;
		return result; //A record is returned send exception
	}
	/**
	 * Called by the Host Factory to obtain a list of the companies 
	 *	that are the supported by the called class.
	 * 	This method must be overridden.
	 * @return com.sbc.eia.bis.BusinessInterface.Company[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
	 */
	public static Company[] getSupportedCompanies(
		com.sbc.bccs.utilities.Utility aUtility)
		throws InvalidCompanyException, InvalidStateException, NullDataException
	{
		aUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"ASON::getSupportedCompanies()");
		// Return the company/state combination supported by this host
		return new Company[] {
			 new Company(
				Company.Company_Ameritech,
				State.getAnAnyState(),
				null,
				null)};
	}
    
     /**
     * Supports the RetrieveServiceProvidersForResource business transaction by utilizing 
     * retrieveServiceProvidersForResource transactions' execute() methods.
     * Note that try/catch exceptions are noticebly missing, because we simply pass any exception through.
     * Creation date: 
     * @return RetrieveServiceProvidersForServiceReturn
     * @param aContext BisContext
     * @param aResourcehandle ObjectHandle
     * @param aServiceTypeHandle ObjectHandle[]
     * @exception InvalidData: An input parameter contained invalid data.
     * @exception AccessDenied: Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented: The method has not been implemented.
     * @exception ObjectNotFound: The desired domain object could not be found.
     * @exception DataNotFound: No data found.
     * @exception NotImplementedException: This method has not been implemented
     */
     
	public RetrieveServiceProvidersForResourceReturn retrievePicByCLLI(
		BisContext aContext,
		String aCLLI,
		ObjectKey[] aServiceTypeHandles)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		String originator = (String) getProperties().get("BIS_NAME");
		String myMethodName = "ASON::retrieveByClli()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		//basic input are already checked in retrieveServiceProviderForResource
		//get serciceTypeHandle.  expecting ServiceTypeHandleObjectKey.LOCAL_VOICE
		//throw not implemented exception if require pic/lpic. 
		//ignore other than pic/lpic/local_voice


        ArrayList ServiceProvidersForResourceTypeList = new ArrayList();
        
		try  // for method exit logging
		{

			String cicFlag = null;
            boolean isOcn = false;
            
			for (int picIdx = 0; picIdx < aServiceTypeHandles.length; picIdx++)
			{
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
				} // skip, just in case, but shouldn't happe
				utility.log(LogEventId.INFO_LEVEL_1, "Looking up ServiceTypeHandle = <" + cicFlag + ">");
				if (cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.PIC)
					|| cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.LPIC))
				{
					utility.log(LogEventId.INFO_LEVEL_1, "ASON is not implemented for PIC/LPIC inquiry");
					utility.throwException(
						ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
						"PIC/LPIC Functionality not implemented for ASON",
						originator,
						Severity.UnRecoverable);
				}
				else if (cicFlag.equalsIgnoreCase(ServiceTypeHandleObjectKey.LOCAL_VOICE))
				{
                    isOcn = true;         
				}
				else
				{
					utility.log(LogEventId.INFO_LEVEL_1, "Skipping ServiceTypeHandle = <" + cicFlag + ">");
					continue;
				}
			} // for
            
			// ocn inquiry only
			if (isOcn)
			{
				ServiceProviderType aServiceProviderType =
					new ServiceProviderType();
				ServiceProvidersForResourceTypeList.add(
					aServiceProviderType.buildServiceProvidersType(
						getProperties(),
						utility,
						aCLLI,
						cicFlag,
						aContext));
			}
		}
		finally
		{
 
			utility.log(LogEventId.DEBUG_LEVEL_1, " Exiting from " + myMethodName);
		}
      
			if (ServiceProvidersForResourceTypeList.size() < 1)
				utility.throwException(
					ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,
					"Resource not found.",
					(String) getProperties().get("BIS_NAME"),
					Severity.UnRecoverable);
			utility.log(
				LogEventId.INFO_LEVEL_1,
				"Build the RetrieveServiceProvidersForResourceReturn with "
					+ ServiceProvidersForResourceTypeList.size()
					+ " ServiceProvidersForServiceType elements...");

		return new RetrieveServiceProvidersForResourceReturn(
			aContext,
			(ServiceProvidersForServiceType[]) ServiceProvidersForResourceTypeList.toArray(
				new ServiceProvidersForServiceType[ServiceProvidersForResourceTypeList.size()]));
	}
	/**
	 * Supports the RetrieveServiceProvidersForService business transaction by utilizing the retrieveResourceForService
	 * and retrieveServiceProvidersForResource transactions' execute() methods.
	 * Note that try/catch exceptions are noticebly missing, because we simply pass any exception through.
	 * Creation date: (5/22/01 3:16:31 PM)
	 * @return RetrieveServiceProvidersForServiceReturn
	 * @param aContext BisContext
	 * @param aServiceHandle ObjectHandle
	 * @param aServiceTypeHandle ObjectHandle[]
	 * @exception InvalidData: An input parameter contained invalid data.
	 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception BusinessViolation: The attempted action violates a business rule.
	 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception NotImplemented: The method has not been implemented.
	 * @exception ObjectNotFound: The desired domain object could not be found.
	 * @exception DataNotFound: No data found.
	 */
	public
	RetrieveServiceProvidersForServiceReturn retrieveByService(	BisContext aContext,
																ObjectKey aServiceKey,
																ObjectKey[] aServiceTypeHandles,
																Logger aLogger)
	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
	{
		ObjectKey aServiceHandle = new ObjectKey(aServiceKey.aValue, BisTypesObjectKeyKind.WORKINGTELEPHONENUMBER);

		String myMethodName = "ASON::retrieveByService()" ;
		utility.log(LogEventId.DEBUG_LEVEL_1,myMethodName) ;


		// basic input are already checked in retrieveServiceProvidersForService


		// These fields will be used to fetch data from Exchpic and Carrier table

		String exk_npa = null;
		String exk_central_office = null;

		String service_center = null;
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( aContext.aProperties );
		service_center = aContextOPM.getValue( BisContextProperty.SERVICECENTER ) ;

		utility.log( LogEventId.INFO_LEVEL_2,"The service center is "+service_center);


		SubscriptionAccountReturn subscriptionAccountReturn =  null;

		// Parameter to call SM:retrieveSubscriptionAccountsForService()
		SubscriptionAccountInformationType infType = SubscriptionAccountInformationType.CustomerServiceInformation;

		// Parameter to call SM:retrieveSubscriptionAccountsForService()
		StringOpt strOpt = new StringOpt();
		strOpt.__default();
		EiaDateOpt dateOpt = new EiaDateOpt();
		dateOpt.__default();



		// Go to SM:retrieveSubscriptionAccountsForService() to get EXK and SAGCO, this makes sure
		// we use the correct switch if the TN is pooled or ported.
		try
		{
			utility.log( LogEventId.REMOTE_CALL, (String)getProperties().get( "SM_PROVIDER_URL" ),
												 (String)getProperties().get( "SM_BIS_NAME" ),
												 (String)getProperties().get( "SM_BIS_NAME" ),
												 "SM::retrieveSubscriptionAccountsForService" ) ;
			subscriptionAccountReturn =
			getSubAccountReturn(
				aContext,
				aServiceHandle,
				infType,
				utility);
		}
		catch(NullPointerException e)
		{
			utility.log( LogEventId.INFO_LEVEL_2,"The subscription Account from SM is null");
		}
		finally
		{
			utility.log( LogEventId.REMOTE_RETURN, (String)getProperties().get( "SM_PROVIDER_URL" ),
												 (String)getProperties().get( "SM_BIS_NAME" ),
												 (String)getProperties().get( "SM_BIS_NAME" ),
												 "SM::retrieveSubscriptionAccountsForService" ) ;
		}

		// initialize private variable everytime
		sag_co	= null ;
		exk		= null ;

		// setup a CRS Checker to check CSR status
		try
		{
			if ( myCSRStatusChecker == null )
				myCSRStatusChecker = new CheckCSRFromSM( utility, (String)getProperties().get( "SM_CSR_STATUS_FILE" ) ) ;
		}
		catch (FileNotFoundException e)
		{
			utility.throwException( ExceptionCode.ERR_RM_SM_CSR_STATUS_FILE_NOT_FOUND,
									"FileNotFoundException: SM_CSR_STATUS_FILE " + e.getMessage(),
									(String)getProperties().get("BIS_NAME" ), Severity.Recoverable, e ) ;
		}
		catch (IOException e)
		{
			utility.throwException( ExceptionCode.ERR_RM_SM_CSR_STATUS_FILE_READ_ERROR,
									"IOException while reading SM_CSR_STATUS_FILE: " + e.getMessage(),
									(String)getProperties().get("BIS_NAME" ), Severity.Recoverable, e ) ;
		}

		// No CSR, or no EXK or SAGCO, use the WTN to select from EXCHCO
		if ( subscriptionAccountReturn!=null &&
			 myCSRStatusChecker.isActiveAccount( subscriptionAccountReturn ) )
		{
			utility.log( LogEventId.INFO_LEVEL_2,"Got subscription Account from SM, it is :"+ subscriptionAccountReturn.toString() );
			// Extract method will provide SAG_CO and EXK
			extract(subscriptionAccountReturn);
			utility.log(LogEventId.INFO_LEVEL_2,"The SAG CO from SM is: <"+sag_co+">");
			utility.log(LogEventId.INFO_LEVEL_2,"The Exchange Key from SM is: <"+exk+">");

		}
		else
		{
			utility.log( LogEventId.INFO_LEVEL_2,"The subscription Account from SM is either null or non-Live.");
		}


		DBConnection conn = null;
		
		try 
		{
		try
		{
			 conn = getASONConnection();
		}
		catch (SQLException e) {
			utility.log(LogEventId.DEBUG_LEVEL_2,"Get Connection failed to ASON Oracle:" + e.getMessage());

			throwASONException(	aContext, e) ;

		}



		// if we got subscriptionAccountReturn then there is no need to use EXCHCO table
		// because we already have the SAG_CO and EXK from subscriptionAccountReturn
		// but we should check first that both are not null
		if( subscriptionAccountReturn == null ||
			(sag_co==null)||(exk==null) )
		{

			ExchcoView ExchcoTable = null;

			utility.log(LogEventId.INFO_LEVEL_1,"Required data is not found in SM so getting it from local database");
			try
			{
				if (conn==null)
				{
					try
					{
						conn = getASONConnection();
					}
					catch (SQLException e)
					{
						utility.log(LogEventId.DEBUG_LEVEL_2,"SQLException while trying to get a connection:" + e.getMessage());

						throwASONException(	aContext, e) ;

					}
				}
				ExchcoTable = new ExchcoView(conn.getConnection());

			}

			catch (SQLException e)
			{
				utility.log( LogEventId.DEBUG_LEVEL_2,"SQLException while accessing the EXCHCO table" );
				try
				{
					utility.log( LogEventId.DEBUG_LEVEL_1,"Disconnecting the connection" );
					conn.disconnect();
				}
				catch(Exception ex) {}
				throwASONException(	aContext, e) ;

			}

			utility.log(LogEventId.DEBUG_LEVEL_1,"Created table  EXCHCO");

			// find method on EXCHCO where wtn is in range and get SAG_CO and EXK( concate)
			ExchcoRow ExchcoRows = new ExchcoRow() ;
			try
			{
				utility.log( LogEventId.REMOTE_CALL, (String)getProperties().get( "jdbcURL" ),
													 (String)getProperties().get( "jdbcUSERID" ),
													 (String)getProperties().get( "jdbcUSERID" ),
													 "ASON::ExchcoTable.find()" ) ;

				ExchcoRows = ExchcoTable.find(aServiceHandle.aValue.trim(),service_center, utility);

				if ( ExchcoRows != null )
				{
					sag_co= ExchcoRows.getSag_co();
					exk_npa = ExchcoRows.getExk_npa();
					exk_central_office = ExchcoRows.getExk_co();
					exk =exk_npa+exk_central_office;
					ExchcoTable = null;
					ExchcoRows = null;

					utility.log( LogEventId.INFO_LEVEL_2,"SAGCO = <" + sag_co + ">" );
					utility.log( LogEventId.INFO_LEVEL_2,"EXK   = <" + exk    + ">" );
				}
				else
					utility.throwException(	ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
											"No Data Found in Carrier and Exchpic Tables",
											(String)getProperties().get( "BIS_NAME" ),
											Severity.UnRecoverable ) ;

			}
			catch( SQLException e )
			{
				utility.log( LogEventId.DEBUG_LEVEL_2,"SQLException while accessing the EXCHCO tables..." );
				try
				{
					utility.log( LogEventId.DEBUG_LEVEL_1,"Disconnecting the connection..." );
					conn.disconnect();
				}
				catch(Exception ex)
				{
				}
				throwASONException(	aContext, e) ;

			}
			finally
			{
				utility.log( LogEventId.REMOTE_RETURN, (String)getProperties().get( "jdbcURL" ),
													   (String)getProperties().get( "jdbcUSERID" ),
													   (String)getProperties().get( "jdbcUSERID" ),
													   "ASON::ExchcoTable.find()" ) ;
			}

			utility.log(LogEventId.INFO_LEVEL_1,"Successfully Got One row from EXCHCO");

		}


		// Now we have SAG_CO and EXK
		// so we will check pic type is PIC or LPIC type
		// according to that we will send data from carrier table


		// Till here we have sag_co, exk_npa ,exk_central_office
		ArrayList aCarrierRows = null ;
		try
		{
			utility.log( LogEventId.REMOTE_CALL, (String)getProperties().get( "jdbcURL" ),
												 (String)getProperties().get( "jdbcUSERID" ),
												 (String)getProperties().get( "jdbcUSERID" ),
												 "ASON::CarrierTable.find()" ) ;
			CarrierView CarrierTable =null;
			if (conn==null)
			{
				try
				{
					conn = getASONConnection();
				}
				catch (SQLException e) {
					utility.log(LogEventId.DEBUG_LEVEL_2,"SQLException while trying to get a connection:" + e.getMessage());

					throwASONException(	aContext, e) ;
				}
			}
			CarrierTable = new CarrierView(conn.getConnection());
			aCarrierRows = CarrierTable.find( sag_co, exk,service_center, utility) ;
			CarrierTable =null;
		}
		catch (SQLException e)
		{
			utility.log(LogEventId.DEBUG_LEVEL_2,"SQLException while accessing the Carrier and Exchpic tables:" + e.getMessage());

			throwASONException(	aContext, e) ;

		}

		finally
		{
			utility.log( LogEventId.REMOTE_RETURN, 	(String)getProperties().get( "jdbcURL" ),
													(String)getProperties().get( "jdbcUSERID" ),
													(String)getProperties().get( "jdbcUSERID" ),
													"ASON::CarrierTable.find()" ) ;
			// Don't need connection further
			try
			{
				utility.log( LogEventId.DEBUG_LEVEL_1,"Disconnecting the connection" );
				conn.disconnect();
			}
			catch(Exception e)
			{
				utility.log(LogEventId.DEBUG_LEVEL_1,"Trying to disconnect the connection failed:" );
			}
		}

		// Did we get any data?
		if ( aCarrierRows.isEmpty() )
		{

			utility.throwException(	ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
									"No Data Found in Carrier and Exchpic Tables",
									(String)getProperties().get( "BIS_NAME" ),
									Severity.UnRecoverable ) ;


		}


		utility.log(LogEventId.INFO_LEVEL_1,"Successfully got the data from Carrier Table:" );


		ArrayList ServiceProvidersForServiceTypeList = new ArrayList() ;
		boolean isCombined = false;
		Hashtable interlataAcnadesHashtable = new Hashtable();
		Hashtable intralataAcnadesHashtable = new Hashtable();
		
		for ( int picIdx=0; picIdx<aServiceTypeHandles.length; picIdx++ )
		{

			// translate the ServiceTypeHandle to ASON's CIC_FLAG
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
				utility.log( LogEventId.INFO_LEVEL_1, "Working on PIC" ) ;
				cicFlagCode = ASON_PIC_CODE ;
				OKeyKind = ServiceTypeHandleObjectKey.PIC ;

			}
			else if ( cicFlag.equalsIgnoreCase( ServiceTypeHandleObjectKey.LPIC ) )
			{
				utility.log( LogEventId.INFO_LEVEL_1, "Working on LPIC" ) ;
				cicFlagCode = ASON_LPIC_CODE ;
				OKeyKind = ServiceTypeHandleObjectKey.LPIC ;
			}
			else
			{
				utility.log( LogEventId.INFO_LEVEL_1, "Skipping ServiceTypeHandle = <" + cicFlag + ">" ) ;
				continue ;
			}

			// traverse the result set and Build the response structure

			ArrayList ServiceProviderList = new ArrayList() ;
			int nRows = aCarrierRows.size();
			CarrierRow aCarrierRow = null;
			CarrierRow aNextCarrierRow = null;
			for (int index = 0; index < nRows; index++)
			{
				aCarrierRow = (CarrierRow) aCarrierRows.get( index );


				utility.log( LogEventId.DEBUG_LEVEL_2, aCarrierRow.getCarrier_pic() + "|" +
													   aCarrierRow.getCarrier_picx() + "|" +
													   aCarrierRow.getCarrier_type() + "|" +
													   aCarrierRow.getRes_bus_code() + "|" +
													   aCarrierRow.getIntralata_cauth() + "|" +
													   aCarrierRow.getIntralata_acnades() + "|" +
													   aCarrierRow.getInterlata_cauth() + "|"+
													   aCarrierRow.getInterlata_acnades() + "|") ;
			
				if ( aCarrierRow.getIsDuplicate() )
				{
					continue;
				}
				String key = aCarrierRow.getCarrier_pic();

				//combine TOS for this pic/lpic code if there is more than 1 record
				// only need to be done once for all serviceTypeHandler
				if (isCombined == false)
				{
					//get universal ACNADES
					int nextIndex = index;
					
					aNextCarrierRow = aCarrierRow;
					boolean hasInterlata = false;
					boolean hasIntralata = false;
					String interlataAcnades = null;
					String intralataAcnades = null;
                    try
                    {
                        do
                        {
                            aNextCarrierRow = (CarrierRow)aCarrierRows.get(nextIndex);
                            if (interlataAcnadesHashtable.containsKey(key))
                            {
                                hasInterlata = true;
                            }
                            if (intralataAcnadesHashtable.containsKey(key))
                            {
                                hasIntralata = true;
                            }
                            interlataAcnades = aNextCarrierRow.getInterlata_acnades();
                            intralataAcnades = aNextCarrierRow.getIntralata_acnades();
                            if (interlataAcnades != null && hasInterlata == false)
                            {
                                hasInterlata = true;
                                interlataAcnadesHashtable.put(key, interlataAcnades);
                            }
                            if (intralataAcnades != null && hasIntralata == false)
                            {
                                hasIntralata = true;
                                intralataAcnadesHashtable.put(key, intralataAcnades);
                            }
                            if (hasInterlata && hasIntralata)
                            {
                                break;
                            }
                            nextIndex++;
                        }
                        while (nextIndex < nRows);
                    }
                    catch (NullPointerException e)
                    {
                        //key is null, ignore.
                    }
					
					//combine TOS if needed
					nextIndex = index + 1;
					while ( nextIndex < nRows )
					{
						aNextCarrierRow = (CarrierRow) aCarrierRows.get( nextIndex );
						if ( aCarrierRow.needToCombine( aNextCarrierRow ) )
						{
							aNextCarrierRow.setIsDuplicate( true );
							String busCodeTo   = aNextCarrierRow.getRes_bus_code();
							String busCodeFrom = aCarrierRow.getRes_bus_code();
							aCarrierRow.setRes_bus_code( busCodeFrom + busCodeTo );
						}
						else
						{
							break;
						}
						nextIndex++;
					}
				}


				// keep only the PIC code we want and the ones that are marked as BOTH
				if ( aCarrierRow.getCarrier_type().equalsIgnoreCase( cicFlagCode ) ||
					 aCarrierRow.getCarrier_type().equalsIgnoreCase( ASON_BOTH_CODE ) )
				{
					ArrayList aSPPropertyList = new ArrayList() ;
					String CXR_CD = aCarrierRow.getCarrier_picx() ;  //extract just the pic code

					if ( CXR_CD != null )
					{
						aSPPropertyList.add(
							new ObjectProperty(
								ServiceProviderProperty.CODE,
								CXR_CD));
						if (aCarrierRow.getCarrier_pic() != null)
							aSPPropertyList.add(
								new ObjectProperty(
									ServiceProviderProperty.NAME_ABBREVIATION,
									aCarrierRow.getCarrier_pic()));

						String name    = null;
						String cauth   = null;
						if ( aCarrierRow.getCarrier_type().equalsIgnoreCase( ASON_LPIC_CODE ) )
						{
							cauth = aCarrierRow.getIntralata_cauth();
							name = (String)interlataAcnadesHashtable.get(key);
						}
						else
						{
							cauth = aCarrierRow.getInterlata_cauth();
							name = (String)intralataAcnadesHashtable.get(key);
						}

						//adding name property
						if ( name != null || name.length() == 0 )
						{
							aSPPropertyList.add(
								new ObjectProperty(
									ServiceProviderProperty.NAME,
									name));
						}

						//mapping tos to enterprise value
						String tos = aCarrierRow.getRes_bus_code();
						if (tos != null)
						{
							if (tos.equalsIgnoreCase("B"))
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty.TYPE_OF_SERVICE,
										TypeOfServiceValue.BUSINESS));
							}
							else if (tos.equalsIgnoreCase("R"))
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty.TYPE_OF_SERVICE,
										TypeOfServiceValue.RESIDENCE));
							}
							else
							{  //else tos==BR or TOS ==RB
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty.TYPE_OF_SERVICE,
										TypeOfServiceValue.BUSINESS_RESIDENCE));
							}
						}

						//mapping cauth to enterprise value
						if ( cauth == null )
						{
							aSPPropertyList.add(
								new ObjectProperty(
									ServiceProviderProperty
										.CARRIER_AUTHORIZATION,
									CarrierAuthorizationValue.OFFER ) );
						}
						else
						{
							if ( cauth.equalsIgnoreCase( "DNA" ) )
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty
											.CARRIER_AUTHORIZATION,
										CarrierAuthorizationValue
											.DO_NOT_ACCEPT));
							}
							else if ( cauth.equalsIgnoreCase( "DNO" ) )
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty
											.CARRIER_AUTHORIZATION,
										CarrierAuthorizationValue
											.DO_NOT_OFFER));
							}
							else if ( cauth.equalsIgnoreCase( "B" ) )
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty
											.CARRIER_AUTHORIZATION,
										CarrierAuthorizationValue
											.PREFER_BUSINESS));
							}
							else if ( cauth.equalsIgnoreCase( "R" ) )
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty
											.CARRIER_AUTHORIZATION,
										CarrierAuthorizationValue
											.PREFER_RESIDENTIAL ) );
							}
							else
							{
								aSPPropertyList.add(
									new ObjectProperty(
										ServiceProviderProperty
											.CARRIER_AUTHORIZATION,
										CarrierAuthorizationValue
											.DO_NOT_ACCEPT ) );
							}
						}


						ServiceProvider aSP = new ServiceProvider() ;
						aSP.aProperties = (ObjectProperty[])aSPPropertyList.toArray( new ObjectProperty[aSPPropertyList.size()] ) ;

						aSP.aServiceProviderHandle = new ObjectKey(new String(),new String());
						
						aSP.aOperatingCompanyNumbers = (OperatingCompanyNumberSeqOpt)
															IDLUtil.toOpt( OperatingCompanyNumberSeqOpt.class, null ) ;

						ServiceProviderList.add( aSP ) ;
					}
				}
			}

			isCombined = true;
			utility.log( LogEventId.INFO_LEVEL_1, "Randomize " + ServiceProviderList.size() +
												  " elements in the ServiceProviderList..." ) ;

			// Randomize result before returning
			ArrayListHelper.randomize( ServiceProviderList ) ; 
			utility.log( LogEventId.INFO_LEVEL_1, "Build the ServiceProvidersForServiceTypeList..." ) ;
			ServiceProvidersForServiceTypeList.add( new ServiceProvidersForServiceType( 
																new ObjectKey( cicFlag, OKeyKind ),
				(ServiceProvider[])ServiceProviderList.toArray(new ServiceProvider[ServiceProviderList.size()])));
		}
		//DR114465: No error message when ServiceTypeHandles are invalid.
		//  did we get any data?
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
		// helping the garbage collector that increases performance
		// it may claim these null objects earlier
		subscriptionAccountReturn = null;
		aContextOPM =null;
		strOpt = null;
		dateOpt =null;
		aCarrierRows = null ;


		utility.log(LogEventId.DEBUG_LEVEL_1," Exiting from "+myMethodName) ;
		return new RetrieveServiceProvidersForServiceReturn( aContext,
				(ServiceProvidersForServiceType[])ServiceProvidersForServiceTypeList.
				toArray(new ServiceProvidersForServiceType[ServiceProvidersForServiceTypeList.size()]) ) ;

		} finally {
			try {
				if (conn != null) {

					conn.disconnect();
					utility.log(LogEventId.DEBUG_LEVEL_1, "connection closed:");

				}
			} catch (Exception e) {
				utility.log(
					LogEventId.DEBUG_LEVEL_1,
					"Trying to disconnect the disconnect failed: " + e.getMessage());
			} finally {
				conn = null;
			}

		}
	}
	/**
	 * Manages ASON SQLException.
	 * Creation date: (3/29/02 2:05:24 PM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param e SQLException
	 * @exception com.sbc.eia.idl.bis_types.InvalidData The exception description.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied The exception description.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation The exception description.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure The exception description.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented The exception description.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound The exception description.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound The exception description.
	 */
	public void throwASONException(BisContext aContext, SQLException e)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			DataNotFound,
			ObjectNotFound
	{
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"e.getErrorCode() = <" + e.getErrorCode() + ">");
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"e.getSQLState()  = <" + e.getSQLState() + ">");
		Properties tagValues = new Properties();
		tagValues.setProperty("SYSTEM", "ASON");
		ExceptionBuilder.parseException(
			aContext,
			(String) getProperties().get(
				"EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
			null,
			e.getSQLState(),
			e.getMessage(),
			true,
			1,
			null,
			e,
			utility,
			"ASON",
			null,
			tagValues);
	}
	public void throwSmException(
		BisContext aContext,
		ObjectKey aServiceHandle,
		ExceptionData aExceptionData,
		Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		String aOrigination = null;
		Severity aSeverity = null;
		String reqNum = aServiceHandle.aValue.trim();
		Properties tagValues = new Properties();
		tagValues.setProperty("TN", reqNum);
		try
		{
			aOrigination = aExceptionData.aOrigination.theValue();
		}
		catch (Exception e)
		{}
		try
		{
			aSeverity = aExceptionData.aSeverity.theValue();
		}
		catch (Exception e)
		{}
		ExceptionBuilderResult aResult =
			ExceptionBuilder.parseException(
				aContext,
				(String) getProperties().get(
					"EXCEPTION_BUILDER_SM_RULE_FILE"),
				null,
				aExceptionData.aCode,
				aExceptionData.aDescription,
				false,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aUtility,
				aOrigination,
				aSeverity,
				tagValues);
		switch (aResult.getBusinessRule())
		{
			case 1 : // ok ignore exception
				aUtility.log(
					LogEventId.INFO_LEVEL_1,
					"Ignore SM exception: "
						+ aExceptionData.aCode
						+ ": "
						+ aExceptionData.aDescription);
				break;
			default : // throw the exception otherwise
				aResult.throwException(aContext, utility);
				break;
		}
	}
}
