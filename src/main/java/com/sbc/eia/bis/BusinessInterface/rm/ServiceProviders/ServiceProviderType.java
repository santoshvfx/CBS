
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
//# 8/17/04     Jinmin Ni       RM141220: OCN by clli inquiry
//# 02/17/2007  Changchuan Yin  Added REMOTE_CALL and REMOTE_RETURN as ASON
//# 03/02/2007  Changchuan Yin  Chagne "ASON" to LERG for REMOTE_CALL and REMOTE_RETURN log

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Iterator;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
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
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumber;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;


/**
 * Created on Aug 17, 2004
 * @author Jinmin Ni
 */

public class ServiceProviderType
{
	public ServiceProviderType()
	{
		super();
	}
	/**
	* @return com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType
	* @param java.util.Hashtable
	* @param aUtility com.sbc.bccs.utilities.Utility
	* @param string
	* @param string
	* @param com.sbc.eia.idl.bis_types.BisContext
	* @exception InvalidData: An input parameter contained invalid data.
	* @exception AccessDenied: Access to the specified domain object or information is not allowed.
	* @exception BusinessViolation: The attempted action violates a business rule.
    * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
    * @exception NotImplemented: The method has not been implemented.
    * @exception ObjectNotFound: The desired domain object could not be found.
    * @exception DataNotFound: No data found.	 
	*/
	public ServiceProvidersForServiceType buildServiceProvidersType(
		Hashtable props,
		Utility aUtility,
		String aClli,
		String cicFlag,
		BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			DataNotFound,
			ObjectNotFound
	{   aUtility.log(
                LogEventId.INFO_LEVEL_1,
                "ServiceProviders::buildServiceProvidersType()...");
        ArrayList aClliToSwitchOwnerRowList = null;
        ClliToSwitchOwnerRow aClliToSwitchOwnerRow = null;
        
        ClliToSwitchOwnerView aClliToSwitchOwnerView = new ClliToSwitchOwnerView();
        
        String aService = "LERG";
		try
		{
			aUtility.log(LogEventId.REMOTE_CALL, aService, aService, aService, "OCNByCLLIInquiry");
		
			aClliToSwitchOwnerRowList =
				aClliToSwitchOwnerView.getOcnByClli(props, aUtility, aClli);
		}
        catch (ClliToSwitchOwnerNotFoundException e)
        {
		
            aUtility.log(
                LogEventId.DEBUG_LEVEL_2,
                "getOcnByClli() return no row");
                

		 aUtility.log(LogEventId.REMOTE_RETURN, aService, aService, aService, "OCNByCLLIInquiry");
		
            
            aUtility.throwException(
                ExceptionCode.ERR_RM_CLLI_TO_SWITCH_OWNER_NOT_FOUND,
                e.toString(),
                (String) props.get("BIS_NAME"),
                Severity.UnRecoverable);
        }
        catch (SQLException e)
        {
        	
            aUtility.log(
                LogEventId.DEBUG_LEVEL_2,
                "SQLException while accessing the Clli_To_Switch_Owner table");
            Properties tagValues = new Properties();
            tagValues.setProperty("SYSTEM", "ClliToSwitchOwnerView");

		    aUtility.log(LogEventId.REMOTE_RETURN, aService, aService, aService, "OCNByCLLIInquiry");
		
            ExceptionBuilder.parseException(
                aContext,
                (String) props.get("EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
                null,
                e.getSQLState(),
                e.getMessage(),
                true,
                1,
                null,
                e,
                aUtility,
                "ClliToSwitchOwnerView",
                null,
                tagValues);
        }
        
		aUtility.log(LogEventId.REMOTE_RETURN, aService, aService, aService, "OCNByCLLIInquiry");
		

        //filtering the row as stated in SR doc.
        aClliToSwitchOwnerRow = filterRow(props,aClliToSwitchOwnerRowList,aUtility);
        aUtility.log(
                LogEventId.DEBUG_LEVEL_2,
                "filterRow() return = "+aClliToSwitchOwnerRow);
        
        ArrayList aSPPropertyList = new ArrayList();
        aSPPropertyList.add(
            new ObjectProperty(
                ServiceProviderProperty.NAME,
                aClliToSwitchOwnerRow.getOcn_name()));
        ServiceProvider aSP = new ServiceProvider();
        
        aSP.aProperties =
            (ObjectProperty[]) aSPPropertyList.toArray(
                new ObjectProperty[aSPPropertyList.size()]);
        
        aSP.aServiceProviderHandle = new ObjectKey("", "");
                
        aSP.aOperatingCompanyNumbers =
            (
                OperatingCompanyNumberSeqOpt) IDLUtil
                    .toOpt(OperatingCompanyNumberSeqOpt.class,
            new OperatingCompanyNumber[] {
                 new OperatingCompanyNumber(
                    com.sbc.eia.idl.sm_types.OperatingCompanyNumberCategory.from_int(0),
                    (String)"",
                    aClliToSwitchOwnerRow.getOcn())});


        ArrayList ServiceProviderList = new ArrayList();
        ServiceProviderList.add(aSP);
        return (
            new ServiceProvidersForServiceType(
                        new ObjectKey(
                            cicFlag,
                            ServiceTypeHandleObjectKey.LOCAL_VOICE),
                (ServiceProvider[]) ServiceProviderList.toArray(
                    new ServiceProvider[ServiceProviderList.size()])));
    }



	/**
    * Filter the lerg data as following: 
    * Group the records by Switch (CLLI) and for each Switch record do the following:    
    * 1 Ignore all records with an Effective Date after today.
    * 2 Ignore all records with an invalid Record Status.
    * 3 Retain the record with the latest Effective Date to work with in each group.  Each group should end up with one or more or zero records. 
    *  - If the record is a "D" (Delete) status record, the TN has been deleted.  Remove the group entirely. 
    *  - If the record is an "E" (Establish) status record, retain it.
    *  - If the record is a base or current view status record (no Effective Date or Record Status), retain it.
    *  - If the record is a "M" record, retain it.
    * 4 Select the record from the first Switch (CLLI) left to retrieve the OCN.  If there are no Switch records left, an "Object Not Found" error message is returned to the client. 
    * 5 If one of the following conditions applies, return a "Duplicate OCN Data" error to the client:
    *    (1) There is more than one record for a given Switch and
    *        There is no Effective Date for any of the records
    *        There is no Record Status for any of the records
    *    (2) There is more than one record for a given Switch and
    *        There is no Effective Date for any of the records
    *        There is a Record Status for one or more record
    *    (3) There is more than one record for a given Switch and
    *        Two or more of the records have the same Effective Date
    * 
	* @return com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ClliToSwitchOwnerRow
	* @param java.util.Hashtable
    * @param java.util.ArrayList
	* @param aUtility com.sbc.bccs.utilities.Utility
	* @exception InvalidData: An input parameter contained invalid data.
	* @exception AccessDenied: Access to the specified domain object or information is not allowed.
	* @exception BusinessViolation: The attempted action violates a business rule.
	* @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	* @exception NotImplemented: The method has not been implemented.
	* @exception ObjectNotFound: The desired domain object could not be found.
	* @exception DataNotFound: No data found.    
	*/
	public ClliToSwitchOwnerRow filterRow(
		Hashtable props,
		ArrayList aClliToSwitchOwnerRowList,
		Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			DataNotFound,
			ObjectNotFound
	{
		aUtility.log(
			LogEventId.INFO_LEVEL_1,
			"ServiceProviders::filterRow()...");
		//only most recent effective date of today or earlier or the base record is of interest.
		//Today's date without time
		Calendar workDate = GregorianCalendar.getInstance();
		Calendar today =
			new GregorianCalendar(
				workDate.get(Calendar.YEAR),
				workDate.get(Calendar.MONTH),
				workDate.get(Calendar.DAY_OF_MONTH));
		Iterator it = aClliToSwitchOwnerRowList.iterator();
		Calendar currentDate = null;
		Calendar prevDate = null;
		ClliToSwitchOwnerRow currentRow = null;
		ClliToSwitchOwnerRow prevRow = null;
		int baseCount = 0;
		while (it.hasNext())
		{
			currentRow = (ClliToSwitchOwnerRow) it.next();
			// The effective date is not null - check if its after today
			String status = currentRow.getStatus();
			if ((status != null
				&& !status.trim().equalsIgnoreCase("M")
				&& !status.trim().equalsIgnoreCase("D")
				&& !status.trim().equalsIgnoreCase("E")))
			{
				//invalid status. ignore. 
				aUtility.log(
					LogEventId.INFO_LEVEL_2,
					"Ignore row (invalid status:  " + status + ")");
				continue;
			}
			if (currentRow.getEff_date() != null)
			{
				workDate.setTime(currentRow.getEff_date());
				currentDate =
					new GregorianCalendar(
						workDate.get(Calendar.YEAR),
						workDate.get(Calendar.MONTH),
						workDate.get(Calendar.DAY_OF_MONTH));
				// If it is a future date or has nonrecognized status ignore the row
				if (currentDate.after(today))
				{
					aUtility.log(
						LogEventId.INFO_LEVEL_2,
						"Ignore row (future)");
					continue;
				}
				if (prevRow == null) //latest effective day not in the future
				{
					prevRow = currentRow;
					prevDate = currentDate;
				}
				else if (
					prevDate.after(
						currentDate)) //prevDate is latest effective day not in the future. use this record
				{
					break;
				}
				else //duplicate records
					{
					aUtility.log(
						LogEventId.INFO_LEVEL_2,
						"duplicate record....");
					aUtility.throwException(
						ExceptionCode.ERR_RM_OCN_DUPLICATE_DATA,
						"Duplicate OCN records found",
						(String) props.get("BIS_NAME"),
						Severity.UnRecoverable);
				}
			}
			// The effective date is null base record
			else
			{
				baseCount++;
				if (baseCount > 1) //at least 2 base record. 
				{
					aUtility.log(
						LogEventId.INFO_LEVEL_2,
						"duplicate record....");
					aUtility.throwException(
						ExceptionCode.ERR_RM_OCN_DUPLICATE_DATA,
						"Duplicate OCN record found",
						(String) props.get("BIS_NAME"),
						Severity.UnRecoverable);
				}
				else if (prevDate != null) //not a first record
				{
					break;
				}
				else //first and base record
					{
					currentDate = prevDate = null;
					prevRow = currentRow;
				}
			}
		}
		if (prevRow == null)
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_CLLI_TO_SWITCH_OWNER_NOT_FOUND,
				"No OCN record or no valid OCN record found",
				(String) props.get("BIS_NAME"),
				Severity.UnRecoverable);
		}
		String status = prevRow.getStatus();
		if (status == null)
			// majority are this case.  do not want to try/catch for null pointer
		{
			return prevRow;
		}
		if (status.trim().equalsIgnoreCase("D")) //record delete  
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "OCN record deleted");
			aUtility.throwException(
				ExceptionCode.ERR_RM_CLLI_TO_SWITCH_OWNER_NOT_FOUND,
				"OCN record deleted",
				(String) props.get("BIS_NAME"),
				Severity.UnRecoverable);
		}
		else if (
			status.trim().equalsIgnoreCase("M")
				|| status.trim().equalsIgnoreCase("E"))
		{
			return prevRow;
		}
		else
		{
			aUtility.throwException(
				ExceptionCode.ERR_RM_CLLI_TO_SWITCH_OWNER_NOT_FOUND,
				"Invalid OCN record found",
				(String) props.get("BIS_NAME"),
				Severity.UnRecoverable);
		}
		return prevRow;
	}
}