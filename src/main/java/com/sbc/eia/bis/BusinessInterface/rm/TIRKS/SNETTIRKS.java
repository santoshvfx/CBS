// $Id: SNETTIRKS.java,v 1.17 2011/04/07 02:37:11 rs278j Exp $
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
 * Creation date: (03/29/00 3:02:10 PM)
 * @author: Mark Liljequist 
 */

public class SNETTIRKS extends TIRKSBase 
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
	public SNETTIRKS(
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
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SNETTIRKS::getCacheEntries()");

		// Add entries to the HostFactory cache at start time to avoid long searches
		return new Selector[] 
		{
			 new Selector(
				new Company(
					Company.Company_SouthernNETelephone,
					State.getAnAnyState(),
					null,
					null),
				CircuitInformation.CircuitInformationInterfaceName,
				SNETTIRKS.class.getName())
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
			DataNotFound 
	{
		String myMethodName = "SNETTIRKS::getCBLSInformation()";
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

		input.setUnitFormat(CBLSInput.SNET_UNIT_FORMAT);

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
		String myMethodName = "SNETTIRKS::getCXRSInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		SNETCircuitId snetCircuit = null;
		try 
		{
			snetCircuit = new SNETCircuitId(aCircuitId.getCircuitId());
		} 
		catch (CircuitIdException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID " + aCircuitId.getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXCXRSTrans(aContext, snetCircuit, imsRegions);
		}
		else
		{
			return getCXRSTrans(aContext, snetCircuit, imsRegions);
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
		String myMethodName = "SNETTIRKS::getDRInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		SNETCircuitId snetCircuit = null;
		try 
		{
			snetCircuit = new SNETCircuitId(aCircuitId.getCircuitId());
		} 
		catch (CircuitIdException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID " + aCircuitId.getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
		if(isTIRKSJXOption == true)
		{
			return getTIRKSJXDRTrans(aContext, snetCircuit, imsRegions);
		}
		else
		{
			return getDRTrans(aContext, snetCircuit, imsRegions);
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
		String myMethodName = "SNETTIRKS::getEQPSCInformation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		utility.throwException(
			ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
			"The requested RM operation has not been implemented.",
			"RM",
			Severity.UnRecoverable);

		return null;
	}

	/**
	 * Called by the Host Factory to obtain a list of the interfaces 
	 *	that are the supported by the called class.
	 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getInterfaceList method.
	 * @return java.lang.String[]
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 */
	public static String[] getHostList(
		com.sbc.bccs.utilities.Utility aUtility) 
	{
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SNETTIRKS::getHostList()");

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
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "SNETTIRKS::getInterfaceList()");

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
			DataNotFound 
	{
		String myMethodName = "SNETTIRKS::getRDLOCInformation()";
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
		throws 
			NullDataException, 
			InvalidCompanyException, 
			InvalidStateException 
	{
		aUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"SNETIRKS::getSupportedCompanies()");

		// Return the company/state combination supported by this host

		return new Company[] 
		{
			 new Company(
				Company.Company_SouthernNETelephone,
				State.getAnAnyState(),
				null,
				null)
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
			DataNotFound 
	{
		String myMethodName = "SNETTIRKS::getWAInfromation()";
		utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
		
		// Change the circuit types for the interface.
		
		SNETCircuitId aCircuit = null;
		int i = 0;
		boolean isFirst = true;
		boolean isSecond = true;
		String ckt = "";
		
		// preserve original inArray
		WAInput[] tmpInArray = new WAInput[inArray.length];
		for (i = 0; i < inArray.length; i++) 
			tmpInArray[i] = new WAInput(
				inArray[i].getImsRegion(),
				inArray[i].getDataCenter(), 
				inArray[i].getCac(), 
				inArray[i].getClo(), 
				inArray[i].getCkt());
				
		boolean clliCkt = false;
		
		// convert inArray circuit to SNETCircuit.
		try 
		{
			for (i = 0; i < inArray.length; i++) 
			{
				if (inArray[i].getCkt() == null)
					ckt = inArray[i].getClo();
				else
					ckt = inArray[i].getCkt().getCircuitId();
					
				aCircuit = new SNETCircuitId(ckt);
					
				if ( inArray.length == 1 &&	// CLLI only
					aCircuit.getType() == aCircuit.TELEPHONE) 
				{
						// reformat TN for CLLI
						aCircuit.setSNETCkt(aCircuit.extraFormatForSNET());
						clliCkt = true;
				}	
					
				// Set the inArray ckt to SNETCircuitId object	
				inArray[i].setCkt(aCircuit);
			}
		}
		catch (CircuitIdException e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_INVALID_SERVICE_HANDLE,
				"Invalid Format for Circuit ID "
					+ inArray[i].getCkt().getCircuitId(),
				myMethodName,
				Severity.UnRecoverable);
		}
		// WA request
		WorkAuthorizationInformation[] aWaOutput = null;
		
		int counter = 1;	// For CLLI with TN, if WA failed 1st time, 
							// try again.
		while (isSecond && counter < 3) 
		{
			if (isFirst || (clliCkt && isSecond)) 
			{ 
				if (!isFirst) 
				{ 		// Retry for CLLI/TN
					// use the original circuit with the 
					// original number of embedded zeros for CLLI/TN
					aCircuit.setSNETCkt(ckt);
					inArray[0].setCkt(aCircuit);
				}	
				if(isTIRKSJXOption == true)
				{
					aWaOutput = getTIRKSJXWATrans(aContext, inArray);
				}
				else
				{
					aWaOutput = getWATrans(aContext, inArray);
				}
			}
			else
				break;
		
			String aEccCkt = null;

			// Extract the ckt from aWaOutput and format the ckt for SNET (remove "FA/").
			if (aWaOutput != null && aWaOutput.length >= 1) 
			{
				for (i = 0; i < aWaOutput.length; i++) 
				{
					if (aWaOutput[i] != null) 
					{
						aEccCkt = aWaOutput[i].getCkt().toString();
						if ((aEccCkt.indexOf("/") > 0)) 
						{
							try 
							{
								aCircuit = new SNETCircuitId(aEccCkt);
								aWaOutput[i].setCkt(aCircuit.formatEccCkt(aEccCkt));
							}
							catch (CircuitIdException e) 
							{
								utility.log(
									LogEventId.DEBUG_LEVEL_2,
									"Invalid Format for Circuit ID "
										+ aWaOutput[i].getCkt().toString());
							}
						}
					}
				}
				// CKT failed, try again for CLLI
				if (aWaOutput != null && aWaOutput.length == 1 && aWaOutput[0] == null)
					isFirst = false ;
				else
					isSecond = false;
					
				counter++;
			} 
			else 
			{
				counter++;
				isSecond = true;
				isFirst = false;
			}
		}
		
		// restore original circuit to inArray
		for (i = 0; i < inArray.length; i++) 
			inArray[i].setCkt(tmpInArray[i].getCkt());

		return aWaOutput;
	}
}
