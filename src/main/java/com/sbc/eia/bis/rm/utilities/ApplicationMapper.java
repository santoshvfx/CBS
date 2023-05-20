//$Id: ApplicationMapper.java,v 1.3.2.1 2007/11/14 23:25:35 op1664 Exp $
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
//#      � 2007-2010 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/23/2007	   Ott Phannavong	Creation
//############################################################################
package com.sbc.eia.bis.rm.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.SeriousFailureException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;
import com.sbc.bccs.utilities.Utility;
import java.util.Hashtable;

/**
 * @author op1664 This utility class load data from a file
 *         (applicationIDToIndicator.properties).
 *  
 */
public class ApplicationMapper
{
	private Utility myUtility = null;

	private Properties applicationIDToIndicator = new Properties();

	private static ApplicationMapper singleton = null;

	private Hashtable reverseLookUp = new Hashtable();

	private Hashtable myProperties = null;

	/**
	 * This method will create a new instance of ApplicationMapper if one don't
	 * exist.
	 * 
	 * @param aUtility
	 * @param aProperties
	 * @return ApplicationMapper - an instance of ApplicationMapper
	 * @throws DataNotFound
	 * @throws ObjectNotFound
	 * @throws NotImplemented
	 * @throws SystemFailure
	 * @throws BusinessViolation
	 * @throws AccessDenied
	 * @throws InvalidData
	 * @throws DataNotFound
	 * @throws DataNotFound
	 * @throws SeriousFailureException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws NullDataException
	 */
	public static synchronized ApplicationMapper getInstanceOfApplicationMapper(
			Utility aUtility, Hashtable aProperties) throws InvalidData,
			AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
			ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: getInstanceOfApplicationMapper(...)";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		if(singleton == null)
		{
			try
			{
				singleton = new ApplicationMapper(aUtility, aProperties);
			}
			catch(NullDataException e)
			{
				aUtility.throwException(ExceptionCode.ERR_RM_DATA_NOT_FOUND,
						"Can not load the applicationIDToIndicator.properties.",
						(String) aProperties.get("BIS_NAME").toString(),
						Severity.UnRecoverable);
			}
			catch(SeriousFailureException e)
			{
				aUtility.throwException(ExceptionCode.ERR_RM_DATA_NOT_FOUND,
						"Can not load the applicationIDToIndicator.properties.",
						(String) aProperties.get("BIS_NAME").toString(),
						Severity.UnRecoverable);
			}
		}
		else
		{
			singleton.myUtility = aUtility;
		}
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
		return singleton;
	}

	/**
	 * private constructor of ApplicationMapper
	 * 
	 * @param aLogger
	 * @param aProperties
	 * @throws NullDataException
	 * @throws BusinessViolation
	 * @throws SeriousFailureException
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws InvalidData
	 */
	private ApplicationMapper(Utility aUtility, Hashtable aProperties)
			throws NullDataException, SeriousFailureException, InvalidData,
			AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
			ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: ApplicationMapper(...)";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
		if(aUtility == null)
		{
			throw new NullDataException(
					ExceptionCode.ERR_RM_UNEXPECTED_NULL_FIELD,
					"Utility is null in ApplicationMapper: ApplicationMapper()");
		}
		if(aProperties == null)
		{
			throw new NullDataException(
					ExceptionCode.ERR_RM_UNEXPECTED_NULL_FIELD,
					"Properties is null in ApplicationMapper: ApplicationMapper()");
		}
		myUtility = aUtility;
		myProperties = aProperties;
		loadApplicationIDToIndicator();

		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	}

	/**
	 * This method will load APPLICATIONID_TO_INDICATOR_MAP_FILE.
	 * 
	 * @throws SeriousFailureException
	 * @throws AccessDenied
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws InvalidData
	 */
	private void loadApplicationIDToIndicator() throws SeriousFailureException,
			InvalidData, AccessDenied, BusinessViolation, SystemFailure,
			NotImplemented, ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: loadApplicationIDToIndicator()";
		myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
		/*
		 * The file contains data in this format: ApplicationID=Indicator (eg:
		 * BBNMS=O)
		 */
		String applicationIDToIndicatorMapFile = (String) myProperties
				.get("APPLICATIONID_TO_INDICATOR_MAP_FILE");
		if(applicationIDToIndicatorMapFile != null
				|| applicationIDToIndicatorMapFile.length() > 0)
		{
			try
			{
				PropertiesFileLoader.read(applicationIDToIndicator,
						applicationIDToIndicatorMapFile, myUtility);
				myUtility
						.log(
								LogEventId.DEBUG_LEVEL_2,
								("Loaded " + applicationIDToIndicator.size() + " ApplicationId to Indicator mappings"));
				Enumeration tmp = applicationIDToIndicator.keys();
				while(tmp.hasMoreElements())
				{
					String key = (String) tmp.nextElement();
					String value = (String) applicationIDToIndicator.get(key);
					if(value.length() == 1)
					{
						reverseLookUp.put(value, key);
					}
				}
			}
			catch(FileNotFoundException e)
			{
				throw new SeriousFailureException(
						ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
						"FileNotFoundException: " + e.getMessage());
			}
			catch(IOException e)
			{
				throw new SeriousFailureException(
						ExceptionCode.ERR_RM_IO_EXCEPTION, "IOExeption: "
								+ e.getMessage());
			}
		}
		else
		{
			throw new SeriousFailureException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
					"APPLICATIONID_TO_INDICATOR_MAP_FILE property not found/not set");
		}
		myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	}

	/**
	 * This method retrieve application indicator base on the application id.
	 * 
	 * @param origHost
	 *            -original host to map to indicator (eg OMS or BBNMS)
	 * @return String -application indicator (eg O or B)
	 * @throws DataNotFound
	 * @throws ObjectNotFound
	 * @throws NotImplemented
	 * @throws SystemFailure
	 * @throws BusinessViolation
	 * @throws AccessDenied
	 * @throws InvalidData
	 */
	public String retrieveApplicationIndicator(String aApplicationID)
			throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
			NotImplemented, ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: retrieveApplicationIndicator()";
		myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		String indicator = null;
		if(aApplicationID != null)
		{
			indicator = (String) applicationIDToIndicator.get(aApplicationID);

			if(indicator.length() > 1)
			{
				//prevent infinite loop
				if(aApplicationID.equalsIgnoreCase(indicator))
				{
					myUtility.throwException(ExceptionCode.ERR_RM_DATA_IS_INVALID,
							"The application ID can not be the same as indicator.",
							(String) myProperties.get("BIS_NAME").toString(),
							Severity.UnRecoverable);
				}
				//Use indicator (BBNMS) to look for indicator B for testing
				indicator = retrieveApplicationIndicator(indicator);
			}
		}
		else
		{
			myUtility.throwException(ExceptionCode.ERR_RM_DATA_NOT_FOUND,
					"The application ID is null.", (String) myProperties.get(
							"BIS_NAME").toString(), Severity.UnRecoverable);
		}
		if(indicator == null)
		{
			myUtility.throwException(ExceptionCode.ERR_RM_DATA_NOT_FOUND,
					"There is no entry for <" + indicator
							+ "> in applicationIDToIndicator.properties.",
					(String) myProperties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);
		}

		myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
		return indicator;
	}

	/**
	 * This method retrieve application ID base on application indicator.
	 * 
	 * @param indicator
	 *            -application indicator (eg O or B)
	 * @return String -original host (eg OMS or BBNMS)
	 * @throws DataNotFound
	 * @throws ObjectNotFound
	 * @throws NotImplemented
	 * @throws SystemFailure
	 * @throws BusinessViolation
	 * @throws AccessDenied
	 * @throws InvalidData
	 */
	public String retrieveApplicationID(String aIndicator) throws InvalidData,
			AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
			ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: retrieveApplicationID()";
		myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		String applicationID = null;
		if(aIndicator != null)
		{
			applicationID = (String) reverseLookUp.get(aIndicator);
		}

		myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
		return applicationID;
	}

	/**
	 * This method determine if the system is supposed to check for LST.
	 * 
	 * @param aApplicationID
	 * @return boolean - true if the client is BBNMS otherwise false
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public boolean isLST(String aApplicationID) throws InvalidData,
			AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
			ObjectNotFound, DataNotFound
	{
		String myMethodNameName = "ApplicationMapper: isLST()";
		myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		String LSTValueName = "LST";
		//Concatenate the indicator to the string "LST"
		LSTValueName = LSTValueName
				+ (String) applicationIDToIndicator.get(aApplicationID);
		//LSTFlag will be a true or false value for each application indicator
		String LSTFlag = (String) applicationIDToIndicator.get(LSTValueName);
		boolean reVal = false;
		if(LSTFlag == null)
		{
			myUtility.log(LogEventId.DEBUG_LEVEL_2, "LSTFlag is null for + <"
					+ aApplicationID
					+ "> please check applicationIDToIndicator.properties");
		}
		else
		{
			reVal = LSTFlag.equalsIgnoreCase("true");
		}

		myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
		return reVal;
	}

	/**
	 * Getting a list of application IDs from
	 * applicationIDToIndicator.properties.
	 * 
	 * @return list of application IDs
	 */
	public String[] getIDList()
	{
		String myMethodNameName = "ApplicationMapper: getIDList()";
		myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

		ArrayList list = new ArrayList();
		String key;
		//Get all the keys
		Enumeration enumeration = applicationIDToIndicator.keys();
		while(enumeration.hasMoreElements())
		{
			key = (String) enumeration.nextElement();
			if(!key.startsWith("LST"))
			{
				list.add(key);
			}
		}
		String[] IDList = new String[list.size()];
		list.toArray(IDList);

		myUtility.log(LogEventId.DEBUG_LEVEL_2, "<" + list.toString());
		myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
		return IDList;
	}
}