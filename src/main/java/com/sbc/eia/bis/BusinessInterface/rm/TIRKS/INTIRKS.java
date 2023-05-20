//$Id: INTIRKS.java,v 1.9 2011/04/07 02:32:56 rs278j Exp $
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
 * Creation date: (03/29/00 3:02:10 PM)
 * @author: Mark Liljequist
 */ 
public class INTIRKS extends TIRKSBase 
	implements CircuitInformation
{
	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			CircuitInformationInterfaceName
		};
		
	private Properties stateToIMSRegion = new Properties();
	boolean isTIRKSJXOption = false;
	
	/**
	 * Class constructor accepting runtime control parameters.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aProperties java.util.Hashtable
	 */
	public INTIRKS(
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
	 */
	public static Selector[] getCacheEntries(
		com.sbc.bccs.utilities.Utility aUtility)
		throws 
			NullDataException, 
			InvalidCompanyException, 
			InvalidStateException
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "INTIRKS::getCacheEntries()");
	
		// Add entries to the HostFactory cache at start time to avoid long searches
		return new Selector[] {
			new Selector(new Company(Company.Company_Ameritech, new State(State.State_Indiana), null, null),
				CircuitInformation.CircuitInformationInterfaceName, INTIRKS.class.getName())
		};
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
			DataNotFound {
	
		String myMethodName = "INTIRKS::getCBLSInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		ArrayList cableList = new ArrayList();
		
		if(isTIRKSJXOption == true)
		{
			cableList = getTIRKSJXCBLSTrans(aContext, input);
		}
		else
		{
			cableList = getCBLSTrans(aContext, input);
		}
		CableInformation aCable = (CableInformation) cableList.get(0);
	
		RDLOCInput refInput = new RDLOCInput(aCable.getCableDataCenter(), input.getCableId());
	
		for (int i = 0; i < 2; i++) 
		{
			refInput.setLocation(refInput.findCableLocation(i));
			if (refInput.getLocation() == null)
				continue;
			try 
			{
				ReferenceLocationInformation rli =
					((ReferenceLocationInformation) getRDLOCInformation(aContext, refInput).get(0));
				aCable.setCableACNA(rli.parseAITForACNA());
				break;
			} 
			catch (ObjectNotFound f) 
			{
			}
		}
	
		return cableList;
	
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
		String imsRegions)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
	
		String myMethodName = "INTIRKS::getCXRSInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		
		INCircuitId inCircuit = null;
		try {
			inCircuit = new INCircuitId(aCircuitId.getCircuitId());
		} catch (CircuitIdException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID "
				+ aCircuitId.getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXCXRSTrans(aContext, inCircuit, imsRegions);
		}
		else
		{
			return getCXRSTrans(aContext, inCircuit, imsRegions);
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
		String myMethodName = "INTIRKS::getDRInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
	
		INCircuitId inCircuit = null;
		try 
		{
			inCircuit = new INCircuitId(aCircuitId.getCircuitId());
		} 
		catch (CircuitIdException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID "
				+ aCircuitId.getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXDRTrans(aContext, inCircuit, imsRegions);
		}
		else
		{
			return getDRTrans(aContext, inCircuit, imsRegions);
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
		String myMethodName = "INTIRKS::getEQPSCInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		
		utility.throwException(
			ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
			"The requested RM operation has not been implemented.",
			"RM",
			Severity.UnRecoverable);
	
		return null;	
	}
	
	/**
	 * Called by the Host Factory to obtain a list of hosts that are the immediate
	 * 	children of the called class.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getHostList method.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */ 
	public static String[] getHostList(
		com.sbc.bccs.utilities.Utility aUtility)
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "INTIRKS::getHostList()");
	
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
		com.sbc.bccs.utilities.Utility aUtility)
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "INTIRKS::getInterfaceList()");
	
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
	public ArrayList getRDLOCInformation(
		BisContext aContext,
		RDLOCInput input)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {
	
		String myMethodName = "INTIRKS::getRDLOCInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
	
		if (isTIRKSJXOption == true)
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
	public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
		throws NullDataException, InvalidCompanyException, InvalidStateException
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "INTIRKS::getSupportedCompanies()");
	
		// Return the company/state combination supported by this host
		 
		return new Company[] {
			new Company(Company.Company_Ameritech, new State(State.State_Indiana), null, null)
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
		String myMethodName = "INTIRKS::getWAInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
	
		// Change the circuit types for the interface.
		INCircuitId aCircuit;
		int i = 0;
		try {
	
			for (i = 0; i < inArray.length; i++) {
				try {
					aCircuit = new INCircuitId(inArray[i].getCkt().getCircuitId());
					inArray[i].setCkt(aCircuit);
				} catch (NullPointerException e) {}
			}
	
		} catch (CircuitIdException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID " + inArray[i].getCkt().getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
	
		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXWATrans(aContext, inArray);
		}
		else
		{
			return getWATrans(aContext, inArray);
		}
	
	}
}
