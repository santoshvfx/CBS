//$Id: ServiceOrderRequestHelper.java,v 1.7 2007/12/20 17:19:10 op1664 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		Sriram Chevuturu      Creation
//# 12/05/2006	Doris Sunga			  PR-18927751-AddressHandler - need to use 
//#									  AddHandler.getStreetAddress(), street Direction
//# 								  comes after street number.  	

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;


import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.idl.lim.helpers.AddressHandlerLFACS;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ServiceOrderRequestHelper {
	
 	protected Logger myLogger = null;
	protected Hashtable myProperties = null;	
	
		
	public ServiceOrderRequestHelper(Hashtable aProperties, Logger aLogger)
	{
		myProperties = aProperties;
		myLogger   = aLogger;
	}
 
 
 	//some helper methods for Request Processing
	
	public EiaDate processDueDate(EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatTelephoneNumber()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		EiaDate aDueDate = null;

		// Due Date will be subesequent if present else original		
		if (aSubsequentDueDate == null)
			aDueDate = aOriginalDueDate;
		else
		{ //Opt field Hence try/catch
			try
			{
				aDueDate =
					aSubsequentDueDate.theValue() != null
						? aSubsequentDueDate.theValue()
						: aOriginalDueDate;

			}
			catch (Exception e)
			{
				aDueDate = aOriginalDueDate;
				myLogger.log(LogEventId.DEBUG_LEVEL_1, ">>aSubsequentDueDate is Not Available.<<");
			}

		}
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aDueDate;
	}


	/**
	 * Method formatTelephoneNumber.
	 * @param aTN
	 * @return String
	 */
	protected String formatTelephoneNumber(String aTN)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatTelephoneNumber()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String aFormattedTN = null;

		//if the phone number is 6 digits, then split them in half "xxxyyy" -- "xxx yyy"
		// for primary npanxx.
		//
		if (aTN.length() == 6)
		{
			aFormattedTN = aTN.substring(0, 3) + " " + aTN.substring(3);

		}
		//if the phone number is 10 digits, then do this  "xxxyyyzzzz" -- "xxx yyy-zzzz"
		else
			if (aTN.length() == 10)
			{
				aFormattedTN =
					aTN.substring(0, 3) + " " + aTN.substring(3, 6) + "-" + aTN.substring(6);
			}

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aFormattedTN;
	}

	/**
	 * Method formatYear into YY string
	 * @param aShort
	 * @return String
	 */
	protected String formatYear(short aShort)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatYear()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String yrString = new String();

		// If less than 4 digits
		if (aShort < 1000)
		{
			// If less than 2 digits
			if (aShort < 10)
				yrString = "0" + aShort;
			else
				yrString = "" + aShort;
		}
		else
			yrString = ("" + aShort).substring(2);

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return yrString;
	}

	/**
	 * Method formatDate.
	 * @param aShort
	 * @return String
	 */
	protected String formatDate(short aShort)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatDate()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String dateString = new String();
		int x = aShort;
		if (x < 10)
			dateString = "0" + x;
		else
			dateString = "" + x;

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return dateString;
	}

	/**
	 * Method formatBasicAddress.
	 * @param anAddHandler
	 * @return String
	 */
	protected String formatBasicAddress(AddressHandlerLFACS anAddHandler)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatBasicAddress()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//Basic Info contains street number, street, directions and thoroughfare

		String basicAddress = new String();
		String temp;
/*
		if (((temp = anAddHandler.getStreetNumber()) != null) && !temp.trim().equals(""))
			basicAddress = basicAddress.concat(temp);
		if (((temp = anAddHandler.getStDir()) != null) && !temp.trim().equals(""))
			basicAddress = basicAddress.concat(" " + temp);
		if (((temp = anAddHandler.getStName()) != null) && !temp.trim().equals(""))
			basicAddress = basicAddress.concat(" " + temp);
		if (((temp = anAddHandler.getStreetThoroughfare()) != null) && !temp.trim().equals(""))
			basicAddress = basicAddress.concat(" " + temp);
*/
		//getStreetAddress()
		if (((temp = anAddHandler.getStreetAddress()) != null) && !temp.trim().equals(""))
			basicAddress = basicAddress.concat(temp);
			
		

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return basicAddress;
	}

	/**
	 * Method formatExtendedBasicAddress.
	 * @param anAddHandler
	 * @return String
	 */
	protected String formatExtendedBasicAddress(AddressHandlerLFACS anAddHandler)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatExtendedBasicAddress()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//Extended Basic Info contians all the remaining except what is in
		//Supplemental and House Number	 i.e. City State and Postal Code

		String extnAddress = new String();
		String temp;
		String tempCty;

		//include the state string if only we have city string
		if (((tempCty = anAddHandler.getCity()) != null) && !tempCty.trim().equals(""))
			extnAddress = extnAddress.concat(tempCty);

		if (!tempCty.trim().equals("")
			&& ((temp = anAddHandler.getState()) != null)
			&& !temp.trim().equals(""))
			extnAddress = extnAddress.concat("," + temp);

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

		return extnAddress;
	}

	/**
	 * Method formatSupplementalAddressInfo.
	 * @param anAddHandler
	 * @param aRegion
	 * @return String
	 */
	protected String formatSupplementalAddressInfo(AddressHandlerLFACS anAddHandler, String aRegion)
	{
		String myMethodName = "SvcOrderRequestProcessorHelper::formatSupplementalAddressInfo()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		//Supplemental info has any of the following info or a combo of any of them
		//		WNG, PIER, FLR, APT, RM, UNIT, SUIT, SLIP, or LOT
		// i.e. structure type and unit type and level type

		String suppAddressInfo = new String();
		String temp = null;
		String separator = ((aRegion != null && aRegion.equalsIgnoreCase("W")) ? "," : ";");

		if (((temp = anAddHandler.getStructType()) != null) && !temp.trim().equals(""))
			suppAddressInfo = suppAddressInfo.concat(temp + " ");

		if (((temp = anAddHandler.getStructValue()) != null) && !temp.trim().equals(""))
			suppAddressInfo = suppAddressInfo.concat(temp);

		if (((temp = anAddHandler.getLevelType()) != null) && !temp.trim().equals(""))
		{
			if (suppAddressInfo != null && !suppAddressInfo.trim().equals(""))
				suppAddressInfo = suppAddressInfo.concat(separator + temp + " ");
			else
				suppAddressInfo = suppAddressInfo.concat(temp + " ");
		}

		if (((temp = anAddHandler.getLevelValue()) != null) && !temp.trim().equals(""))
			suppAddressInfo = suppAddressInfo.concat(temp);

		if (((temp = anAddHandler.getUnitType()) != null) && !temp.trim().equals(""))
		{
			if (suppAddressInfo != null && !suppAddressInfo.trim().equals(""))
				suppAddressInfo = suppAddressInfo.concat(separator + temp + " ");
			else
				suppAddressInfo = suppAddressInfo.concat(temp + " ");
		}

		if (((temp = anAddHandler.getUnitValue()) != null) && !temp.trim().equals(""))
			suppAddressInfo = suppAddressInfo.concat(temp);

		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return suppAddressInfo;
	}	
}
