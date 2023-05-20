// $Id: LERG.java,v 1.14.2.1 2005/04/25 20:48:36 rp6496 Exp 

package com.sbc.eia.bis.BusinessInterface.rm.LERG; 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.nam.database.PortStatusRow;
import com.sbc.eia.bis.nam.database.PortStatusTable;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_types.ResourceRoleName;
import com.sbc.eia.idl.types.Severity;

/**
 * Implements the PortabilityStatus Business Interface which defines portability status functionality.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#
#	03/01/02	Mark Liljequist					Update for exception parser in getCLLI.
#
#	11/19/02 	Mark Liljequist		6.0.3		Added OCN data in resource roles.
#												Added the getCLLIInfo.
#
# 	01/09/02 	Mark Liljequist		6.0.5		Add Elogging.
#	
#	06/26/03	Sumana Roy			7.0.2		Added getCLLI() for 6-digit service 
#
#	11/14/03	Sumana Roy			7.6.0		DR 98101 changed query for 'D' records handle.
#
#	02/02/2004	Stevan Dunkin		7.10.2		RM 124625 Added OCN data for ocn_contact_name and ocn_telephone_number

# 	07/13/2004	Stevan Dunkin		9.0.0
#	RM 138478   Added remarks indicating retrieve CLLI for 7-digit NPA/NXX/X

# 	02/10/2005	CDT Developer
#	RM 176516   Revised getClli methods to retrieve specialServiceCode and centralOfficeCode
#   
#   04/22/2005   Roland Pates        11.0.2		Revised comment for getCLLIInfo() at the If block for getting 
#													CLLI using 6-digit NPA-NXX(LRN is truncated to NPANXX)

*/
 
public class LERG extends Host 
	implements PortabilityStatusIntf
{
	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			PortabilityStatusInterfaceName
		};

	// The PortStatusTable
	PortStatusTable aPortStatusTable = null;
/**
 * Class constructor accepting runtime control parameters.
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @param aProperties java.util.Hashtable
 */
public LERG(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
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
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, " LERG::getCacheEntries() ");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] {
		new Selector(new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null),
			PortabilityStatusIntf.PortabilityStatusInterfaceName, LERG.class.getName()),
		new Selector(new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
			PortabilityStatusIntf.PortabilityStatusInterfaceName, LERG.class.getName()),
		new Selector(new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
			PortabilityStatusIntf.PortabilityStatusInterfaceName, LERG.class.getName()),
		new Selector(new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
			PortabilityStatusIntf.PortabilityStatusInterfaceName, LERG.class.getName()),
		new Selector(new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null),
			PortabilityStatusIntf.PortabilityStatusInterfaceName, LERG.class.getName())			
	};
}
/**
 * Returns the CLLI for the LRN/TN.
 * @return java.lang.String
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @param anLRN java.lang.String
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
public ResourceRoleInformation getCLLI(BisContext aContext, TN aTN, String anLRN)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{
	String myMethodName = "LERG::getCLLI()";
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

	PortStatusRow aPortStatusRow = null;
	aPortStatusRow = getCLLIInfo(aContext, aTN, anLRN);
	
	return 
		 new ResourceRoleInformation(
			ResourceRoleName.SERVINGSWITCH,
			aPortStatusRow.getClli(),
			aPortStatusRow.getOperatingCompanyName(),
			aPortStatusRow.getOperatingCompanyID(),
			aPortStatusRow.getOperatingCompanyContactName(),
			aPortStatusRow.getOperatingCompanyTelephoneNumber(),
			null,
			null,
			aPortStatusRow.getSpecialServiceCode(),
			aPortStatusRow.getCentralOfficeCode());

}

//DR 98101 : Added the additional String parameter anNpaNxx to get CLLI info using NPA-NXX
public PortStatusRow getCLLIInfo(BisContext aContext, TN aTN, String anNpaNxx)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{
	String myMethodName = "LERG::getCLLIInfo()";
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

	// NOTE: This code assumes the data is sorted by npa, prfxCd, tnLnLowRnge, tnLnUprRnge, recEffDt
	// Get a database connection and do the lookup

	utility.log(
				LogEventId.REMOTE_CALL,
				(String)getProperties().get( "jdbcURL" ),
				(String)getProperties().get( "jdbcURL" ),
				(String)getProperties().get( "jdbcUSERID" ),
				"LERG::PortStatusTable.find()");													  		
														  		
														  		
	if (aPortStatusTable == null)
		aPortStatusTable = new PortStatusTable() ;
	ArrayList portStatusRows = null ;
	
	try
	{
		// To find CLLI using 6-digit NPA-NXX(LRN is truncated to NPANXX), or 7-digit NPA-NXX-X 
		if( anNpaNxx != null )
			portStatusRows = aPortStatusTable.find( (Properties) getProperties(), anNpaNxx, utility ) ;						  	
		
		// To find CLLI using 10-digit TN 	
		else
			portStatusRows = aPortStatusTable.find( (Properties) getProperties(), aTN, utility ) ;										
	
	}	
	// Parse the SQL exception
	// Throw exception.
	
	catch (SQLException e )
	{
		ExceptionBuilder.parseException(aContext,
										(String) getProperties().get("EXCEPTION_BUILDER_LERG_RULE_FILE"),
										null,
										null,				//code
										e.getMessage(),
										true,
										ExceptionBuilderRule.NO_DEFAULT,
										null,
										null,
										utility,
										null,       		// origin use file
										null,				// severity use file
										null);				// tag values none		
	}
	
	utility.log(
				LogEventId.REMOTE_RETURN,
				(String)getProperties().get( "jdbcURL" ),
				(String)getProperties().get( "jdbcURL" ),
				(String)getProperties().get( "jdbcUSERID" ),
				"LERG::PortStatusTable.find()");			

	// Analyse the result set to figure out the correct CLLI
	utility.log(LogEventId.INFO_LEVEL_2, "Analyzing data... (number of rows: " + portStatusRows.size() + ")");

	// Was at least one row found (same error below if changed)
	if ( portStatusRows == null || portStatusRows.isEmpty() )
		utility.throwException(ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
			"Service not found.",
			"PortabilityStatusIntf", Severity.UnRecoverable ) ;
	
	// Loop through the rows, finding the first group that is usable:
	Iterator portStatusIterator = portStatusRows.iterator() ;
	PortStatusRow aPortStatusRow = null;

	// Todays date without time
	Calendar workDate = GregorianCalendar.getInstance();
	Calendar today = new GregorianCalendar(
		workDate.get(Calendar.YEAR),
		workDate.get(Calendar.MONTH),
		workDate.get(Calendar.DAY_OF_MONTH));
	utility.log(LogEventId.INFO_LEVEL_2, "Today's date: " + today.get(Calendar.YEAR) + "/" + 
		(1 + today.get(Calendar.MONTH)) + "/" + today.get(Calendar.DAY_OF_MONTH));

	PortStatusRow aCLLIRow = null;
	String npaK = "";
	String prfxCdK = "";
	int lowLnK = -1;
	int uprLnK = -1;
	boolean first = true;
	Calendar rowDate = null;
	Calendar subGroupDate = null;
	int startIndex = -1;
	int endIndex = -1;
	int currIndex = -1;
	boolean skipRestOfGroup = false;
	boolean subGroupStarting = true;
	int baseIndex = -1;
	String bisName = (String)getProperties().get( "BIS_NAME" );

	// NOTE: This code assumes the data is sorted by npa, prfxCd, tnLnLowRnge, tnLnUprRnge, recEffDt
	// Break the rows up into logical groups. A group consists of the same npa, prfxCd, tnLnLowRnge, 
	//	tnLnUprRnge. Only the subgroup within a group with the same, most recent effective date of
	//	today or earlier OR the base record is of interest.
	while (portStatusIterator.hasNext())
	{
		currIndex++;
		aPortStatusRow = (PortStatusRow) portStatusIterator.next() ;
		utility.log(LogEventId.INFO_LEVEL_2, "Process row: " + aPortStatusRow.display());

		// Set the control break for the first row
		if (first)
		{
			npaK = aPortStatusRow.getNpa();
			prfxCdK = aPortStatusRow.getPrfxCd();
			lowLnK = aPortStatusRow.getTnLnLowRnge();
			uprLnK = aPortStatusRow.getTnLnUprRnge();

			first = false;
		}

		// End of the group
		if (! aPortStatusRow.getNpa().equals(npaK) ||  ! aPortStatusRow.getPrfxCd().equals(prfxCdK) ||
			aPortStatusRow.getTnLnLowRnge() != lowLnK ||  aPortStatusRow.getTnLnUprRnge() != uprLnK)
		{
			// See if there is a valid row
			if ((aCLLIRow = lookForValidRow(startIndex, endIndex, portStatusRows, baseIndex, bisName, utility)) != null)
				return aCLLIRow;
			
			npaK = aPortStatusRow.getNpa();
			prfxCdK = aPortStatusRow.getPrfxCd();
			lowLnK = aPortStatusRow.getTnLnLowRnge();
			uprLnK = aPortStatusRow.getTnLnUprRnge();
			skipRestOfGroup = false;
			subGroupStarting = true;
			startIndex = -1;
			endIndex = -1;
			baseIndex = -1;
		}

		// The rest of the rows in this group are after today
		if (skipRestOfGroup)
		{
			utility.log(LogEventId.INFO_LEVEL_2, "Ignore row (future)");
			continue;
		}

		// The effective date is not null - check if its after today
		if (aPortStatusRow.getRecEffDt() != null)
		{
			workDate.setTime(aPortStatusRow.getRecEffDt());
			rowDate = new GregorianCalendar(
				workDate.get(Calendar.YEAR),
				workDate.get(Calendar.MONTH),
				workDate.get(Calendar.DAY_OF_MONTH));
			
			utility.log(LogEventId.INFO_LEVEL_2, "Row date: " + rowDate.get(Calendar.YEAR) + "/" + 
				(1 + rowDate.get(Calendar.MONTH)) + "/" + rowDate.get(Calendar.DAY_OF_MONTH));
			
			// If its a future date ignore the row (and all after)
			if (rowDate.after(today))
			{
				utility.log(LogEventId.INFO_LEVEL_2, "Ignore row (future)");
				skipRestOfGroup = true;
				continue;
			}

			// Is this the base record
			if (aPortStatusRow.getRecStsCd() == null || aPortStatusRow.getRecStsCd().trim().length() < 1)
			{
				utility.log(LogEventId.INFO_LEVEL_2, "Base row (no status)");
				baseIndex = currIndex;
			}
		}

		// The effective date is null
		else
		{
			utility.log(LogEventId.INFO_LEVEL_2, "Base row (date null)");
			rowDate = null;
			baseIndex = currIndex;
		}

		// Determine if the row is part of the sub group (same, most recent effective date)
		// Null dates will be at the start of the group (as will "default" base dates)
		if (subGroupStarting)
		{
			subGroupDate = rowDate;
			subGroupStarting = false;
			startIndex = currIndex;
		}
		if (rowDate != null)
		{
			if ((subGroupDate == null) || rowDate.after(subGroupDate))
			{
				subGroupDate = rowDate;
				startIndex = currIndex;
			}
		}
		endIndex = currIndex;
	}

	// Check the last group (same error above if changed)
	if ((aCLLIRow = lookForValidRow(startIndex, endIndex, portStatusRows, baseIndex, bisName, utility)) == null)
		utility.throwException(ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
			"Service not found.",
			"PortabilityStatusIntf", Severity.UnRecoverable ) ;

	return aCLLIRow;
}

/**
 * Returns the CLLI for the 6-digitNpa/Nxx or 7-digit Npa/Nxx/X.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param	anNpaNxx java.lang.String
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */

// RM:96232
public ResourceRoleInformation getCLLI(BisContext aContext,String anNpaNxx)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{
	String myMethodName = "LERG::getCLLI()";
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

	PortStatusRow aPortStatusRow = null;
	//aPortStatusRow = getCLLIInfo(aContext, anNpaNxx);
	aPortStatusRow = getCLLIInfo(aContext, null, anNpaNxx);
	utility.log(LogEventId.DEBUG_LEVEL_1, "Returning the ResourceRoleInformation from LERG::getCLLI() ");	
	
	return 
		 new ResourceRoleInformation(
			ResourceRoleName.SERVINGSWITCH,
			aPortStatusRow.getClli(),
			aPortStatusRow.getOperatingCompanyName(),
			aPortStatusRow.getOperatingCompanyID(),
			aPortStatusRow.getOperatingCompanyContactName(),
			aPortStatusRow.getOperatingCompanyTelephoneNumber(),
			null,
			null,
			aPortStatusRow.getSpecialServiceCode(),
			aPortStatusRow.getCentralOfficeCode());

			
	
}
/*
// RM:96232: This method has been removed as there was a DR (98101). And now the code
 // for this RM is combined in the other getCLLIInfo() method.
  * 
public PortStatusRow getCLLIInfo(BisContext aContext, String anNpaNxx)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{}
*/
/**
 * Called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getHostList method.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "LERG::getHostList()");

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
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "LERG::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
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
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "LERG::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] {
		new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null),
		new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
		new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
		new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
		new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null)
	};
}
/**
 * Evaluates a list of PORT_STATUS rows and determines the correct CLLI to use, if any. 
 * @return java.lang.String
 * @param startIndex int
 * @param endIndex int
 * @param portStatusRows java.util.ArrayList
 * @param baseIndex int
 * @param bisName java.lang.String
 * @param utility  com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
private PortStatusRow lookForValidRow(int startIndex, int endIndex, ArrayList portStatusRows, int baseIndex,
	String bisName, Utility utility)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{
	utility.log(LogEventId.DEBUG_LEVEL_1, "LERG::lookForValidRow()");

	// Check startIndex != -1
	if (startIndex == -1)
		return null;

	utility.log(LogEventId.DEBUG_LEVEL_2, "startIndex<" + startIndex + "> lastIndex<" + endIndex + ">");

	String aRecStsCd = null;

	// This code can actually handle 'other' status records which are ignored
	// If there is only one record (non-D - base, M or E) use that
	// If there is only one record (D) the TN is deleted
	if (startIndex == endIndex)
	{
		try {
			aRecStsCd = ((PortStatusRow)portStatusRows.get(startIndex)).getRecStsCd().trim().toUpperCase();
			if (aRecStsCd.equals("D"))
			{
				utility.log(LogEventId.INFO_LEVEL_2, "Using DELETED row: " +
					((PortStatusRow)portStatusRows.get(startIndex)).display());
				return null;
			}
			if (aRecStsCd.length() < 1 || aRecStsCd.equals("M") || aRecStsCd.equals("E"))
			{
				utility.log(LogEventId.INFO_LEVEL_2, "Using row: " +
					((PortStatusRow)portStatusRows.get(startIndex)).display());
				return ((PortStatusRow)portStatusRows.get(startIndex));
			}
		}
		catch (NullPointerException e) // Base record has null status (or space/"")
		{
			utility.log(LogEventId.INFO_LEVEL_2, "Using row: "
				+ ((PortStatusRow)portStatusRows.get(startIndex)).display());
			return ((PortStatusRow)portStatusRows.get(startIndex));
		};
		
		// Something else is there - try the base
		if (baseIndex != -1)
		{
			utility.log(LogEventId.INFO_LEVEL_2, "Using row: "
				+ ((PortStatusRow)portStatusRows.get(baseIndex)).display());
			return ((PortStatusRow)portStatusRows.get(baseIndex));
		}
	}
	else
	{
		// There should not be more than one row for the same date
		utility.throwException(	ExceptionCode.ERR_RM_PORT_STATUS_DUPLICATE_DATA,
			"Detected duplicate PORT_STATUS data: <" + ((PortStatusRow)portStatusRows.get(startIndex)).display() + ">",
			bisName, Severity.UnRecoverable ) ;
	}

	// No-op
	return null;
}
}


