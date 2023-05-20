// $Id: G2Helper.java,v 1.1 2006/08/15 20:34:26 jo8461 Exp $
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
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//#	05/09/2005  jp2854		 Creation.
//# 05/17/2005  jp2854       Added logic to getAuthenticationKey and getAddress methods
//# 05/27/2005  jp2854       Updated the ExceptionCode for AuthenticationKey.
//# 10/21/2005  jp2854       Updated getAuthenticationKey method to decrypt the authentication key.

package com.sbc.eia.bis.rm.utilities;
import java.net.UnknownHostException;
import java.util.Hashtable;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.encryption.Encryption;
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

/**
 * @author jp2854
 */
public class G2Helper {

	/**  getAuthenticationKey gets the AuthenticationKey, decrypts the authenticationKey and returns the AuthenticationKey for the host machine
	 * @param utility Utility
	 * @param properties Hashtable
	 * @return String
	 */
	public static String getAuthenticationKey(
		Utility utility,
		Hashtable properties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(
			LogEventId.DEBUG_LEVEL_1,
			">G2Helper::getAuthenticationKey");

		String authenticationKey = null;
		String hostName = null;

		// Get  the host name for the machine
		utility.log(LogEventId.DEBUG_LEVEL_2, "Getting Machine Host Name.....");
		try 
		{
			java.net.InetAddress localMachine =
				java.net.InetAddress.getLocalHost();
			hostName = localMachine.getHostName();
			utility.log(LogEventId.INFO_LEVEL_2, "HostName : "+ hostName);
		} 
		catch (UnknownHostException unkonwnHostEx) 
		{
			utility.log(LogEventId.DEBUG_LEVEL_2, "Error getting the host name"+ unkonwnHostEx.getMessage());
		}

		//If hostname exists get authenticationKey for the host
		if (hostName != null && hostName.trim().length() > 0) 
		{
			String fileName =
				(String) properties.get(
					"G2_HOSTNAME_TO_AUTHENTICATION_KEY_MAPPING_FILE");			

			try 
			{
				java.util.Properties hostToKeyMapProp = PropertiesFileLoader.read(fileName, utility);
				authenticationKey = (String) hostToKeyMapProp.get(hostName);
			} 
			catch (Exception e) 
			{
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					"Error getting the Authentication Key from the property file for the host "
						+ e.getMessage());
			}
			
			try
			{
				authenticationKey =(new Encryption()).decodePassword((String)properties.get("OSS_PUBLIC_KEY"), authenticationKey);
				utility.log(LogEventId.INFO_LEVEL_2, "Decrypted the authentication key...");
			}
			catch (Exception e)
			{
				utility.log(LogEventId.INFO_LEVEL_2,"Error decrypting the password "+ e.getMessage() );
				authenticationKey = null;
			}
		}

		
		//	throw Exception if AuthenticationKey is not found for the host machine.
		if (authenticationKey == null || authenticationKey.trim().length() < 1 ) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_AUTHENTICATION_KEY_NOT_FOUND,
				"Authentication Key not found.",
				properties.get("BIS_NAME").toString(),
				Severity.UnRecoverable);
		}
			

		utility.log(LogEventId.DEBUG_LEVEL_1, "AuthenticationKey "+ authenticationKey);
		utility.log(LogEventId.DEBUG_LEVEL_1, "<G2Helper::getAuthenticationKey");
			
		return authenticationKey;
	}

	/** getAddress method gets and returns the G2 Service end point address
	 * @param utility Utility
	 * @param properties Hashtable
	 * @return String
	 */
	public static String getEndPointAddress(Utility utility, Hashtable properties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(LogEventId.DEBUG_LEVEL_1, ">G2Helper::getEndPointAddress");

		String g2EndPointAddress = (String) properties.get("G2_SOAP_ADDRESS");

		// throw Exception if no G2 service end point address.
		if (g2EndPointAddress == null || g2EndPointAddress.trim().length() < 1) {
			utility.throwException(
				ExceptionCode.ERR_RM_SOAP_ADDRESS_NOT_FOUND,
				"G2 Service end point address not found.",
				properties.get("BIS_NAME").toString(),
				Severity.UnRecoverable);
		}
		
		utility.log(LogEventId.DEBUG_LEVEL_1, "<G2Helper::getEndPointAddress");
		
		return g2EndPointAddress;

	}

}
