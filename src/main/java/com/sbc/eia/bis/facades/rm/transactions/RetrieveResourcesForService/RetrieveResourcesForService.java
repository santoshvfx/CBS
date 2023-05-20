// $Id: RetrieveResourcesForService.java,v 1.25 2005/09/08 23:35:19 ml2917 Exp $

package com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.InvalidServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.NotImplementedException;
import com.sbc.eia.bis.BusinessInterface.ServiceCenter;
import com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitId;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitIdIntf;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByTN.ResourcesForServiceByTN;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByTN.ResourcesForServiceByTNIntf;
import com.sbc.eia.bis.BusinessInterface.rm.SWITCH.NetworkUnitInquiryInterface;
import com.sbc.eia.bis.BusinessInterface.rm.SWITCH.SWITCH;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitIdException;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.FacilityWireCenter;
import com.sbc.eia.bis.RmNam.components.StateLookUp;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.rm.components.NamClientGetNetworkAddress;
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
import com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn;
import com.sbc.eia.idl.rm.bishelpers.RetrieveResourcesForServiceReturnBisHelper;
import com.sbc.eia.idl.rm_types.IntegratedDigitalLoopCarrierIndicatorOpt;
import com.sbc.eia.idl.rm_types.ResourceProvider;
import com.sbc.eia.idl.rm_types.ResourceProviderProperty;
import com.sbc.eia.idl.rm_types.ResourceRole;
import com.sbc.eia.idl.rm_types.ResourceRoleName;
import com.sbc.eia.idl.rm_types.ServiceHandleObjectKeyKind_retrieveResourcesForService;
//import com.sbc.eia.idl.types.ObjectIdOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.bishelpers.ObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringSeqBisHelper;
import com.sun.java.util.collections.ArrayList;

/**
 * Retrieves the resources that implement a specified service.
 * Creation date: (3/21/01 9:08:44 AM)
 * @author: Creighton Malet

#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	01/04/2002	Mark Liljequist		2.0
#	Added state lookup for blank service centers in execute.
#
#	03/05/2002	Mark Liljequist		4.0.0
#	Added exception parser in getStateCode.
#
#	05/07/2002	Mark Liljequist		4.0.2
#	Change error codes for correct message in execute and checkResourceRoleNames.
#
#   05/13/2002	Mark Liljequist		4.0.3
#	Add try in execute
#
#   05/13/2002	Mark Liljequist		6.0.0
#	Add context validation.

#   11/19/2002	Mark Liljequist		6.0.3
#	Add OCN data in the build  method.

#   01/09/2003	Mark Liljequist		6.0.5
#	Add changes for Elogging..

#   06/24/03     Sumana Roy         7.0.2
#	Added changes in execute() for 6 digit service handle
#	for RM96232.

#	02/02/2004 	Stevan Dunkin		7.10.2
#	RM 124625	Changed buildRetrieveResourcesForServiceReturn() method for addition of
#				ocn_contact_name and ocn_telephone_number to clli request.

#   03/19/2004  Stevan Dunkin		8.0.1
#	RM 131998   Added logic to indicate if a given TN contains IDLC information.

# 	07/13/2004	Stevan Dunkin		9.0.0
#	RM 138478   Added logic to retrieve CLLI for 7-digit NPA/NXX/X

# 	02/10/2005	CDT Developer
#	RM 176516   Added logic to retrieve two new fields: Special Service Code and Central Office Code
#               from the Port Status table.

#       03/17/2005      Sriram Chevuturu
#       DR132495        Added Logic for Validating the Service Center If it is null.

#   04/01/2005  Jyothi Pentyala
#	RM185219    changes for removal of ObjectHandle from IDL

#	06/01/2005	Chaitanya			Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1

#	09/08/2005  Mark Liljequist     11.0     Change methods calls on NamClientGetNetworkAddress.		
*/

public class RetrieveResourcesForService
	extends com.sbc.eia.bis.RmNam.utilities.TranBase {
	private static final java.lang.String[] hostList =
		new String[] {
			ResourcesForServiceByCircuitId.class.getName(),
			ResourcesForServiceByTN.class.getName(),
            SWITCH.class.getName()};

	public com.sbc.eia.bis.BusinessInterface.CompanyFactory companyFactory =
		null;

	//private HOSTLOOKUPHelper hostHelper = null;
	private StateLookUp stateLookup = null;

    //Used to indicate a IDLC inquiry
    private boolean isIDLCInquiry = false;
    //Used to indicate if IDLC data was found
    private boolean isIDLCFound = false;
    //Used to indicate if data was found in the TNMaster table for IDLC inquiry
    private boolean isTNMasterDataFound = false;
    
    private NamClientGetNetworkAddress theGNA = null;

	/**
	 * Class constructor.
	 */
	public RetrieveResourcesForService() {
		super();
	}
	/**
	 * Class constructor accepting runtime control parameters.
	 * Creation date: (3/21/01 9:09:13 AM)
	 * @param param java.util.Hashtable
	 */
	public RetrieveResourcesForService(java.util.Hashtable param) {
		setPROPERTIES(param);
	}
	/**
	 * Filters out the requested result data and builds the return value.
	 * @return com.sbc.eia.idl.rm_2_0_0.RetrieveResourcesForServiceReturn
	 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
	 * @param aResourceRoleNames String[]
	 * @param resultPairs String[][]
	 */
	private RetrieveResourcesForServiceReturn buildRetrieveResourcesForServiceReturn(
		BisContext aContext,
		String[] aResourceRoleNames,
		ResourceRoleInformation[] resultPairs) 
	{
		String myMethodName =
			"RetrieveResourcesForService::buildRetrieveResourcesForServiceReturn()";
		log(LOG_DEBUG_LEVEL_1, myMethodName);

		ArrayList resourceRoleObjects = new ArrayList();

		for (int i = 0; i < resultPairs.length; i++) {
			for (int j = 0; j < aResourceRoleNames.length; j++) {

				if (resultPairs[i]
					.getResourceRoleName()
					.equalsIgnoreCase(aResourceRoleNames[j])) {

					// Set up the acna and ccna
					ArrayList aProperties = new ArrayList();

					if (resultPairs[i].getAcna() != null
						&& resultPairs[i].getAcna().length() > 0)
						aProperties.add(
							new ObjectProperty(
								ResourceProviderProperty
									.ACCESSCARRIERNAMEABBREVIATION,
								resultPairs[i].getAcna()));

					if (resultPairs[i].getCcna() != null
						&& resultPairs[i].getCcna().length() > 0)
						aProperties.add(
							new ObjectProperty(
								ResourceProviderProperty
									.CUSTOMERCARRIERNAMEABBREVIATION,
								resultPairs[i].getCcna()));

					if (resultPairs[i].getOperatingCompanyName() != null
						&& resultPairs[i].getOperatingCompanyName().length() > 0)
						aProperties.add(
							new ObjectProperty(
								ResourceProviderProperty.OPERATINGCOMPANYNAME,
								resultPairs[i]
									.getOperatingCompanyName()
									.trim()));

					if (resultPairs[i].getOperatingCompanyID() != null
						&& resultPairs[i].getOperatingCompanyID().length() > 0)
						aProperties.add(
							new ObjectProperty(
								ResourceProviderProperty.OPERATINGCOMPANYNUMBER,
								resultPairs[i].getOperatingCompanyID().trim()));

					if( resultPairs[i].getOperatingCompanyContactName() != null
						&& resultPairs[i].getOperatingCompanyContactName().length() > 0 ) {
							aProperties.add(
								new ObjectProperty( ResourceProviderProperty.CONTACTNAME,
									resultPairs[i].getOperatingCompanyContactName().trim() ));
						}

					if( resultPairs[i].getOperatingCompanyTelephoneNumber() != null
						&& resultPairs[i].getOperatingCompanyTelephoneNumber().length() > 0 ) {
							aProperties.add(
								new ObjectProperty( ResourceProviderProperty.CONTACTTELEPHONENUMBER,
									resultPairs[i].getOperatingCompanyTelephoneNumber().trim() ));
						}

					if( resultPairs[i].getSpecialServiceCode() != null
						&& resultPairs[i].getSpecialServiceCode().length() > 0 ) {
							aProperties.add(
								new ObjectProperty( ResourceProviderProperty.SPECIALSERVICECODE,
									resultPairs[i].getSpecialServiceCode().trim() ));
						}

					if( resultPairs[i].getCentralOfficeCode() != null
						&& resultPairs[i].getCentralOfficeCode().length() > 0 ) {
							aProperties.add(
								new ObjectProperty( ResourceProviderProperty.CENTRALOFFICECODE,
									resultPairs[i].getCentralOfficeCode().trim() ));
						}


                    IntegratedDigitalLoopCarrierIndicatorOpt aIntegratedDigitalLoopCarrierIndicatorOpt;
                    aIntegratedDigitalLoopCarrierIndicatorOpt =
                                this.buildIDLCOpt( this.isTNMasterDataFound, this.isIDLCFound );

					// Build resource role
					resourceRoleObjects.add(
						new ResourceRole(
							resultPairs[i].getResourceRoleName(),
							new ObjectKey( resultPairs[i].getResourceRoleValue(),""),
							aIntegratedDigitalLoopCarrierIndicatorOpt,
							new ResourceProvider(

							// verify for ObjectHandle->ObjectKey changes
								new ObjectKey("", ""),
								new ObjectKey("", ""),
								(ObjectProperty[]) aProperties.toArray(
									new ObjectProperty[aProperties.size()]))));
					break;
				}
			}
		}

		return new RetrieveResourcesForServiceReturn(
			aContext,
			(ResourceRole[]) resourceRoleObjects.toArray(
				new ResourceRole[resourceRoleObjects.size()]));
	}
	/**
	 * Checks that the resource role names are valid and consistent.
	 * Creation date: (5/25/01 12:25:40 PM)
	 * @param aResourceRoleNames java.lang.String[]
	 * @param byTN boolean
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
	 */
	public void checkResourceRoleNames(
		String[] aResourceRoleNames,
		boolean byTN)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		// Cannot be null or empty
		if (aResourceRoleNames == null || aResourceRoleNames.length < 1)
			throwException(
				ExceptionCode.ERR_RM_MISSING_RESOURCE_ROLE_NAMES,
				formatInvalidData(String.class, "aResourceRoleNames[]"),
				getEnv("BIS_NAME"),
				Severity.UnRecoverable);

		// Check the values are valid and match the input request type
		for (int i = 0; i < aResourceRoleNames.length; i++) {

			if (aResourceRoleNames[i].trim().length() < 1) {
				String s = "Circuit id";
				if (byTN)
					s = "Telephone number";

				throwException(
					ExceptionCode.ERR_RM_MISSING_RESOURCE_ROLE_NAMES,
					formatInvalidData(
						String.class,
						"aResourceRoleNames[]",
						aResourceRoleNames[i],
						"Missing Resource Role Name for" + s),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			}

			if (aResourceRoleNames[i]
				.equalsIgnoreCase(ResourceRoleName.SERVINGSWITCH)) {
				// Only for Working Telephone Number
				if (!byTN)
					throwException(
						ExceptionCode.ERR_RM_INVALID_RESOURCE_ROLE_NAMES,
						formatInvalidData(
							String.class,
							"aResourceRoleNames[]",
							aResourceRoleNames[i],
							"Invalid Resource Role Name for Circuit Id"),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);
			}
            else if (
                aResourceRoleNames[i].equalsIgnoreCase(
                    ResourceRoleName.IDLC)) {
                isIDLCInquiry = true;

                // Only for TN
                if (!byTN)
                    throwException(
                        ExceptionCode.ERR_RM_INVALID_RESOURCE_ROLE_NAMES,
                        formatInvalidData(
                            String.class,
                            "aResourceRoleNames[]",
                            aResourceRoleNames[i],
                            "Invalid Resource Role Name for Circuit Id"),
                        getEnv("BIS_NAME"),
                        Severity.UnRecoverable);
            }
            else if (
				aResourceRoleNames[i].equalsIgnoreCase(
					ResourceRoleName.ALOCATIONTERMINATION)) {
				// Only for Circuit Id
				if (byTN)
					throwException(
						ExceptionCode.ERR_RM_INVALID_RESOURCE_ROLE_NAMES,
						formatInvalidData(
							String.class,
							"aResourceRoleNames[]",
							aResourceRoleNames[i],
							"Invalid Resource Role Name for Working Telephone Number"),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);
			} else if (
				aResourceRoleNames[i].equalsIgnoreCase(
					ResourceRoleName.ZLOCATIONTERMINATION)) {
				// Only for Circuit Id
				if (byTN)
					throwException(
						ExceptionCode.ERR_RM_INVALID_RESOURCE_ROLE_NAMES,
						formatInvalidData(
							String.class,
							"aResourceRoleNames[]",
							aResourceRoleNames[i],
							"Invalid Resource Role Name for Working Telephone Number"),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);
			} else {
				throwException(
					ExceptionCode.ERR_RM_INVALID_RESOURCE_ROLE_NAMES,
					formatInvalidData(
						String.class,
						"aResourceRoleNames[]",
						aResourceRoleNames[i],
						"Invalid Resource Role Name"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			}
		}
	}
	/**
	 * Executes the logic, returning the resources that implement the specified service.
	 * @return com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aServiceHandle com.sbc.eia.idl.types.ObjectHandle
	 * @param aResourceRoleNames String[]
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public RetrieveResourcesForServiceReturn execute(
		BisContext aContext,
		ObjectKey aServiceHandle,
		String[] aResourceRoleNames,
		Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		RetrieveResourcesForServiceReturn aRetrieveResourcesForServiceReturn =
			null;

		try {

			// Set the context into the logger
			callerContext = aContext;
			myLogger = aLogger;

			// Log the entry.

			log(LOG_DEBUG_LEVEL_1, "RM::retrieveResourcesForService.execute() ");

			// Check the basic input objects exist.

			if (aServiceHandle == null)
				throwException(
					ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
					formatInvalidData(ObjectKey.class, "aServiceHandle"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);

			// Get properties from aContext.

			ObjectPropertyManager aContextOPM =
				new ObjectPropertyManager(aContext.aProperties);

			// Validate application.

			validateBisContext(
				aContextOPM,
				BisContextProperty.APPLICATION,
				ExceptionCode.ERR_RM_MISSING_APPLICATION,
				"Required field is null");

 			//Validate Service Center
            validateBisContext(
                aContextOPM,
                BisContextProperty.SERVICECENTER,
                ExceptionCode.ERR_RM_MISSING_SERVICE_CENTER,
                "Required field is null");


			// Log the input data
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
			log(
				LOG_INPUT_DATA,
				"aServiceHandle=<"
					+ (new ObjectKeyBisHelper(aServiceHandle)).toString()
					+ ">");
			log(
				LOG_INPUT_DATA,
				"aResourceRoleNames=<"
					+ (new StringSeqBisHelper(aResourceRoleNames)).toString()
					+ ">");

			// Validate the input

			// Determine whether input is by TN or CircuitId.

			boolean byTN = true;
			try {
				String aTNorCKTType =
					aServiceHandle.aKind.trim();
				if (aTNorCKTType.length() < 1)
					throwException(
						ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
						formatInvalidData(
							ObjectKey.class,
							"aServiceHandle.aKind"),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);
				if (aTNorCKTType
					.equalsIgnoreCase(
						ServiceHandleObjectKeyKind_retrieveResourcesForService
							.TELEPHONENUMBER))
					byTN = true;
				else if (
					aTNorCKTType.equalsIgnoreCase(
						ServiceHandleObjectKeyKind_retrieveResourcesForService
							.CIRCUITID))
					byTN = false;
				else
					throwException(
						ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
						formatInvalidData(
							ObjectKey.class,
							"aServiceHandle.aKind",
							aServiceHandle.aKind,
							"Service Handle must be "
								+ ServiceHandleObjectKeyKind_retrieveResourcesForService
									.TELEPHONENUMBER
								+ " or "
								+ ServiceHandleObjectKeyKind_retrieveResourcesForService
									.CIRCUITID),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				throwException(
					ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
					formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aKind"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			} catch (NullPointerException e) {
				throwException(
					ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
					formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aKind"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			}

			TN aTN = null;
			CircuitId aCircuitId = null;

			// Extract the TN or CircuitId and validate accordingly
			// Check for nothing.

			try {
				if (aServiceHandle.aValue.trim().length()
					< 1)
					throwException(
						ExceptionCode.ERR_RM_MISSING_SERVICE_HANDLE,
						formatInvalidData(
							ObjectKey.class,
							"aServiceHandle.aValue"),
						getEnv("BIS_NAME"),
						Severity.UnRecoverable);

				if (byTN) {

					//Validation for 6 or 7 digit service handle containing all numbers in it.
					if(aServiceHandle.aValue.trim().length() == 6
						|| aServiceHandle.aValue.trim().length() == 7)
					{
						log(LOG_DEBUG_LEVEL_2, (aServiceHandle.aValue.trim().length() == 6
												? "retrieveResourcesForService: check if the 6-digit service handle is a valid number "
												: "retrieveResourcesForService: check if the 7-digit service handle is a valid number "));
						for (int i = 0; i < aServiceHandle.aValue.trim().length(); i++)
						{
							if (!Character.isDigit(aServiceHandle.aValue.trim().charAt(i)))
							throwException(
								ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
								formatInvalidData(
									ObjectKey.class,
									"aServiceHandle.aObjectKey.theValue().aValue",
									aServiceHandle.aValue,
									"Invalid Format for Working Telephone Number"),
								getEnv("BIS_NAME"),
								Severity.UnRecoverable);
						}
					}
					//For 6 or 7 digit service handle do not instantiate TN class.
					if(!(aServiceHandle.aValue.trim().length() == 6
					|| aServiceHandle.aValue.trim().length() == 7))
					{
						aTN =
						new TN(
							aServiceHandle.aValue.trim());
						if (!aTN.isValid())
							throwException(
								ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
								formatInvalidData(
									ObjectKey.class,
									"aServiceHandle.aValue",
									aServiceHandle.aValue,
									"Invalid Format for Working Telephone Number"),
								getEnv("BIS_NAME"),
								Severity.UnRecoverable);
					}
				} else {
					aCircuitId =
						new CircuitId(
							aServiceHandle.aValue.trim());
				}
			} catch (CircuitIdException e) {
				throwException(
					ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
					formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aValue",
						aServiceHandle.aValue,
						"Invalid Format for Circuit ID"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			} catch (org.omg.CORBA.BAD_OPERATION e) {
				throwException(
					ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
					formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aValue"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			} catch (NullPointerException e) {
				throwException(
					ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
					formatInvalidData(
						ObjectKey.class,
						"aServiceHandle.aValue"),
					getEnv("BIS_NAME"),
					Severity.UnRecoverable);
			}

            this.isIDLCFound = false;
            this.isIDLCInquiry = false;
            this.isTNMasterDataFound = false;

			// Make sure the resource role names are consistent
			checkResourceRoleNames(aResourceRoleNames, byTN);

			// Check for a tn and service center

			if (byTN) {
				String tmp ;

				if ((tmp = aContextOPM.getValue(BisContextProperty.SERVICECENTER)) == null || tmp.trim().length() < 1)
				{
					String state = null;

				//Directly send the npa and nxx for 6 digit SERVICE HANDLE(npa/nxx) or 7 digit SERVICE HANDLE(npa/nxx/x) instead of TN object.
					if(aServiceHandle.aValue.trim().length() == 6
						|| aServiceHandle.aValue.trim().length() == 7)
					{

                    //Set the value of the state to any valid default value. For 6-digit service handle, state/host lookup is not required.
								state = "CA" ;
					}
					else
					{
						if(stateLookup == null)
							stateLookup = new StateLookUp();

						log(LOG_DEBUG_LEVEL_1, "Accessing HOSTLOOKUP SERVICE for 10-digit TN");
						state = stateLookup.getStateCode(aContext,this,
								aTN.getNpa() + aTN.getNxx() + aTN.getLine(),
								getPROPERTIES());
					}

					if (tmp != null)
						aContextOPM.remove(BisContextProperty.SERVICECENTER);

					aContextOPM.add(BisContextProperty.SERVICECENTER, state);
					aContext = new BisContext(aContextOPM.toArray());
				}
			}


			validateClient(
			 	aContextOPM,
			 	null,			// group_id
				ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION );

			// Build the company factory

			if (companyFactory == null) {
				try {
					companyFactory =
						new CompanyFactory(hostList, this, getPROPERTIES());
				} catch (BusinessException e) {
					throwException(
						ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
						"Internal Error: Business Interface Exception: "
							+ e.getExceptionCode()
							+ ":"
							+ e.getMessage(),
						Business.BUSINESS_INTERFACE,
						Severity.UnRecoverable,
						e);
				}
			}

			// Find the host object and call the retrieveResources method
			ResourceRoleInformation[] resourceRoleResults = null;

			try {
				if (byTN){

					//Directly send the npa and nxx for 6 digit SERVICE HANDLE(npa/nxx) or 7 digit SERVICE HANDLE(npa/nxx/x)
					if(aServiceHandle.aValue.trim().length() == 6
						|| aServiceHandle.aValue.trim().length() == 7)
					{
						log(LOG_DEBUG_LEVEL_1,(aServiceHandle.aValue.trim().length() == 6
												?" Retrieving resource role results for a 6-digit service handle "
												:" Retrieving resource role results for a 7-digit service handle "));
						resourceRoleResults = (	(ResourcesForServiceByTNIntf) companyFactory.getCompany(new ServiceCenter
												(aContextOPM.getValue(BisContextProperty.SERVICECENTER)))
															.getBusinessInterface(
																	ResourcesForServiceByTNIntf
															.ResourcesForServiceByTNInterfaceName))
															.retrieveResources(aContext,aContextOPM,aServiceHandle.aValue.trim());

					}

					else
					{
						log(LOG_DEBUG_LEVEL_1, " Retrieving resource role results for a 10-digit TN ");
						
						// Find LRN 
						String anLRN = null;
	
						if(theGNA == null)
							theGNA = new NamClientGetNetworkAddress(this, this.getPROPERTIES());

						anLRN = theGNA.retrievePortedTN(aTN.toString(), aContext);

						resourceRoleResults = (	(ResourcesForServiceByTNIntf) companyFactory.getCompany(
														new ServiceCenter(aContextOPM.getValue(
																	BisContextProperty.SERVICECENTER)))
															.getBusinessInterface(
																	ResourcesForServiceByTNIntf
															.ResourcesForServiceByTNInterfaceName))
															.retrieveResources(aContext,aContextOPM,aTN,anLRN);

                        //RM 131998: IDLC Inquiry
                        if ( isIDLCInquiry )
                        {
                            retreiveIDLCInfo( aContext, aContextOPM,  aTN );
                            //resource role name must be set becuase retrieveResources above for 10-digit TN
                            //sets resource role name to SERVINGSWITCH.
                            resourceRoleResults[0].setResourceRoleName( ResourceRoleName.IDLC );
                        }

					}
				}
				else
					resourceRoleResults =
						(
							(ResourcesForServiceByCircuitIdIntf) companyFactory
								.getCompany(
									new ServiceCenter(
										aContextOPM.getValue(
											BisContextProperty.SERVICECENTER)))
								.getBusinessInterface(
									ResourcesForServiceByCircuitIdIntf
										.ResourcesForServiceByCircuitIdInterfaceName))
								.retrieveResources(
							aContext,
							aContextOPM,
							aCircuitId);

			} catch (InvalidServiceCenterException e) {
				throwException(
					ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
					formatInvalidData(
						BisContext.class,
						"aContext.aProperties",
						aContextOPM.getValue(BisContextProperty.SERVICECENTER),
						"Invalid Service Center: "
							+ e.getExceptionCode()
							+ ":"
							+ e.getMessage()),
					Business.BUSINESS_INTERFACE,
					Severity.UnRecoverable);
			} catch (UnknownServiceCenterException e) {
				throwException(
					ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
					formatInvalidData(
						BisContext.class,
						"aContext.aProperties",
						aContextOPM.getValue(BisContextProperty.SERVICECENTER),
						"Unknown Service Center: "
							+ e.getExceptionCode()
							+ ":"
							+ e.getMessage()),
					Business.BUSINESS_INTERFACE,
					Severity.UnRecoverable);
			} catch (NotImplementedException e) {
				throwException(
					ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
					"Functionality not implemented for service center: "
						+ aContextOPM.getValue(BisContextProperty.SERVICECENTER)
						+ ". "
						+ e.getExceptionCode()
						+ ":"
						+ e.getMessage(),
					Business.BUSINESS_INTERFACE,
					Severity.UnRecoverable);
			} catch (com.sbc.eia.bis.BusinessInterface.BusinessException e) {
				throwException(
					ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
					"Internal Error: Business Interface Exception: "
						+ e.getExceptionCode()
						+ ":"
						+ e.getMessage(),
					Business.BUSINESS_INTERFACE,
					Severity.UnRecoverable,
					e);
			}

			// Build the final result
			aRetrieveResourcesForServiceReturn =
				buildRetrieveResourcesForServiceReturn(
					aContext,
					aResourceRoleNames,
					resourceRoleResults);

			// Log the output data
			log(
				LOG_OUTPUT_DATA,
				"aRetrieveResourcesForServiceReturn=<"
					+ (	new RetrieveResourcesForServiceReturnBisHelper(aRetrieveResourcesForServiceReturn))
						.toString()
					+ ">");

		} finally {

			// Log the exit
			log(LOG_DEBUG_LEVEL_1, "RM::retrieveResourcesForService");

		}

		return aRetrieveResourcesForServiceReturn;
	}

    //RM 131998
	/**
	 * Method retreiveIDLCInfo.
	 * @param aContext BisContext
	 * @param aContextOPM ObjectPropertyManager
	 * @param aTN TN
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
    private void retreiveIDLCInfo ( BisContext aContext, ObjectPropertyManager aContextOPM, TN aTN )
        throws AccessDenied,
            BusinessViolation,
            InvalidData,
            NotImplemented,
            ObjectNotFound,
            SystemFailure,
            DataNotFound{

        log(LOG_DEBUG_LEVEL_1, " Retrieving resource role results for IDLC info. ");
        FacilityWireCenter facilityWireCenter =
                                    new FacilityWireCenter (this, this.getPROPERTIES() );

        java.util.ArrayList aWireCenters = facilityWireCenter.getWireCenterByTNForIDLC( aTN);

        if( aWireCenters.size() > 0 && !aWireCenters.contains( new String( "NoDataFound" ) ) ) {
            isTNMasterDataFound = true;
        }
        else {
        	isTNMasterDataFound = false;
        }

        if ( this.isTNMasterDataFound ) {
            try {
                isIDLCFound = ((NetworkUnitInquiryInterface) companyFactory.getCompany(
                                              new ServiceCenter(aContextOPM.getValue(
                                               BisContextProperty.SERVICECENTER)))
                                                .getBusinessInterface(
                                                   NetworkUnitInquiryInterface
                                                     .NetworkUnitInquiryInterfaceName))
                                                       .getSWITCHIDLCInfo( aContext,  aTN.toString(), aWireCenters );
            }
            catch (InvalidServiceCenterException e) {
                throwException(
                    ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
                    formatInvalidData(
                        BisContext.class,
                        "aContext.aProperties",
                        aContextOPM.getValue(BisContextProperty.SERVICECENTER),
                        "Invalid Service Center: "
                            + e.getExceptionCode()
                            + ":"
                            + e.getMessage()),
                    Business.BUSINESS_INTERFACE,
                    Severity.UnRecoverable);
            } catch (UnknownServiceCenterException e) {
                throwException(
                    ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
                    formatInvalidData(
                        BisContext.class,
                        "aContext.aProperties",
                        aContextOPM.getValue(BisContextProperty.SERVICECENTER),
                        "Unknown Service Center: "
                            + e.getExceptionCode()
                            + ":"
                            + e.getMessage()),
                    Business.BUSINESS_INTERFACE,
                    Severity.UnRecoverable);
            } catch (NotImplementedException e) {
                throwException(
                    ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
                    "Functionality not implemented for service center: "
                        + aContextOPM.getValue(BisContextProperty.SERVICECENTER)
                        + ". "
                        + e.getExceptionCode()
                        + ":"
                        + e.getMessage(),
                    Business.BUSINESS_INTERFACE,
                    Severity.UnRecoverable);
            } catch (com.sbc.eia.bis.BusinessInterface.BusinessException e) {
                throwException(
                    ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
                    "Internal Error: Business Interface Exception: "
                        + e.getExceptionCode()
                        + ":"
                        + e.getMessage(),
                    Business.BUSINESS_INTERFACE,
                    Severity.UnRecoverable, e);
            }
        }

    }

    // RM 131998
	/**
	 * Method buildIDLCOpt.
	 * @param tnData boolean
	 * @param idlcFound boolean
	 * @return IntegratedDigitalLoopCarrierIndicatorOpt
	 */
    static public IntegratedDigitalLoopCarrierIndicatorOpt buildIDLCOpt ( boolean tnData, boolean idlcFound ) {
        if (!tnData) {
            return (
                (IntegratedDigitalLoopCarrierIndicatorOpt) IDLUtil.toOpt(
                    IntegratedDigitalLoopCarrierIndicatorOpt.class,
                    null));
        }
        else {
            IntegratedDigitalLoopCarrierIndicatorOpt idlc = new IntegratedDigitalLoopCarrierIndicatorOpt();

            idlc.theValue( idlcFound );

            return (idlc );
        }
    }
}


