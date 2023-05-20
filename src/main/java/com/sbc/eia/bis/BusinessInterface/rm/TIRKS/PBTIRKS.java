// $Id: PBTIRKS.java,v 1.9 2011/04/07 02:34:00 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.util.ArrayList;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * Implements the CircuitInformation interface which defines circuit functionality.
 * Creation date: (06/24/02)
 * @author: Mark Liljequist
 */

public class PBTIRKS extends TIRKSBase 
	implements CircuitInformation 
{
	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] { CircuitInformationInterfaceName };

	private Properties stateToIMSRegion = new Properties();
	boolean isTIRKSJXOption = false;
	
	/**
	 * Class constructor accepting runtime control parameters.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aProperties java.util.Hashtable
	 */
	public PBTIRKS(
		Company aCompany,
		com.sbc.bccs.utilities.Utility aUtility,
		java.util.Hashtable aProperties) 
	{
		super(aCompany, aUtility, aProperties);
		isTIRKSJXOption = Boolean.valueOf((String)aProperties.get("TIRKSJX_OPTION")).booleanValue();
	}
	
	/**
	 * Called by the Host Factory to preload entries in the Host cache.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getCacheEntries method.
	 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
	 */
	public static Selector[] getCacheEntries(
		com.sbc.bccs.utilities.Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "PBTIRKS::getCacheEntries()");

		// Add entries to the HostFactory cache at start time to avoid long searches
		return new Selector[] {
			 new Selector(
				new Company(
					Company.Company_PacificBell,
					State.getAnAnyState(),
					null,
					null),
				CircuitInformation.CircuitInformationInterfaceName,
				PBTIRKS.class.getName())};
	}

	/**
	 * Returns Cable Releated Information from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param CBLSInput input
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getCBLSInformation(
		BisContext aContext, 
		CBLSInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "PBTIRKS::getCBLSInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		FacilityWireCenter fwc =
			new FacilityWireCenter(utility, getProperties());

		if (input.getTN() == null) 
		{
			input.setWireCenters(
				fwc.getWireCenterBySwitchCLLI(
					input.getCableId().getTermA().trim().toUpperCase()));

		} 
		else 
		{
			TN aTN = fwc.getWireCenterTN(input.getTN(), aContext);
			input.setWireCenters(fwc.getWireCenterByTN(aTN));

		}

		input.setUnitFormat(CBLSInput.PB_UNIT_FORMAT);

		return getINQNTUTrans(aContext, input);
	}
	
	/**
	 * Returns Carrier Channel Assignment from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param CircuitId aCircuitId
	 * @param String imsRegions
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getCXRSInformation(
		BisContext aContext,
		CircuitId aCircuitId,
		String dataCenters)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{

		String myMethodName = "PBTIRKS::getCXRSInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXCXRSTrans(aContext, aCircuitId, dataCenters);
		}
		else
		{
			return getCXRSTrans(aContext, aCircuitId, dataCenters);			
		}
	}
	
	/**
	 * Returns Design Releated Information from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param CircuitId aCircuitId
	 * @param String imsRegions
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public DesignRelatedInformation getDRInformation(
		BisContext aContext,
		CircuitId aCircuitId,
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "PBTIRKS::getDRInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXDRTrans(aContext, aCircuitId, imsRegions);
		}
		else
		{
			return getDRTrans(aContext, aCircuitId, imsRegions);
		}
	}
	
	/**
	 * Returns equipemnt scan Information from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param EQPSCInput input
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getEQPSCInformation(
		BisContext aContext, 
		EQPSCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "PBTIRKS::getEQPSCInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		input.setPropertyFileName(
			(String) getProperties().get("PB_EQUIPMENT_CODE_FAC_TYPE"));

		String facs;
		if ((facs = input.loadTranslationTable()) == null)
			utility.throwException(
				ExceptionCode
					.ERR_RM_FAC_TYPE_TO_EQUIPMENT_CODE_MAPPING_NOT_FOUND,
				"Cound not find fac type to equipment code translation table.",
				(String) getProperties().get("BIS_NAME"),
				Severity.UnRecoverable);
		else
			utility.log(
				LogEventId.INFO_LEVEL_2,
				myMethodName + "Fac types for PB: " + facs);

		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXEQPSCTrans(aContext, input);
		}
		else
		{
			return getEQPSCTrans(aContext, input);
		}
	}
	
	/**
	 * Called by the Host Factory to obtain a list of hosts that are the immediate
	 * 	children of the called class.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getHostList method.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */
	public static String[] getHostList(
		com.sbc.bccs.utilities.Utility aUtility) {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "PBTIRKS::getHostList()");

		// Return the list of immediate children (classes that derive from this class)

		return hostList;
	}
	
	/**
	 * Called by the Host Factory to obtain a list of the interfaces 
	 *	that are the supported by the called class.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getInterfaceList method.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */
	public static String[] getInterfaceList(
		com.sbc.bccs.utilities.Utility aUtility) {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "PBTIRKS::getInterfaceList()");

		// Return the list of interfaces supported by this host
		return interfaceList;
	}
	
	/**
	 * Returns Reference Location Information from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param RDLOCInput input
	 * @return ArrayList
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public ArrayList getRDLOCInformation(BisContext aContext, RDLOCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "PBTIRKS::getRDLOCInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXRDLOCTrans(aContext, input);
		}
		else
		{
			return getRDLOCTrans(aContext, input);
		}
	}
	
	/**
	 * Called by the Host Factory to obtain a list of the companies 
	 *	that are the supported by the called class.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getSupportedCompanies method.
	 * @return com.sbc.eia.bis.BusinessInterface.Company[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
	 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
	 */
	public static Company[] getSupportedCompanies(
		com.sbc.bccs.utilities.Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException {
		aUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"PBTIRKS::getSupportedCompanies()");

		// Return the company/state combination supported by this host

		return new Company[] {
			new Company(
				Company.Company_PacificBell,
				State.getAnAnyState(),
				null,
				null),
			};
	}
	
	/**
	 * Returns work authorization information from TIRKS.
	 * 
	 * @param BisContext aContext
	 * @param RDLOCInput input
	 * @return WorkAuthorizationInformation[]
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public WorkAuthorizationInformation[] getWAInformation(
		BisContext aContext,
		WAInput[] inArray)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
		String myMethodName = "PBTIRKS::getWAInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Get the WA data.
		WorkAuthorizationInformation[] workAuthorizationInformation = null;
		
		if(isTIRKSJXOption == true)
		{
			workAuthorizationInformation = getTIRKSJXWATrans(aContext, inArray);
		}
		else
		{
			workAuthorizationInformation = getWATrans(aContext, inArray);
		}

		// Determine the service type for each WA.
		// Only do this for PB.

		// Check if there is a good return.
		
		if (workAuthorizationInformation == null
			|| workAuthorizationInformation.length < 1
			|| workAuthorizationInformation[0] == null)
			return workAuthorizationInformation;

		// Set the status for each WA returned.
		
		for (int i = 0; i < workAuthorizationInformation.length; i++) {
			workAuthorizationInformation[i].determineServiceType();
		}

		return workAuthorizationInformation;
	}
}
