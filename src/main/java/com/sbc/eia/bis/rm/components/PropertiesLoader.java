//$Id: PropertiesLoader.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 6/02/2006	 Michael Khalili		Creation
package com.sbc.eia.bis.rm.components;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.PropertiesFileLoader;
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
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PropertiesLoader {

	private static Properties bandwidthProperties = new Properties();
	private static Properties serviceBundleIdProperties = new Properties();
	
	/**
		* The constructor could be made private
		* to prevent others from instatiating this class.
		* But this would also make it impossible to
		* create instances of Singleton subclasses.
		*/
	   protected PropertiesLoader() {
	   }
 
 
	/**
	 * @param aFileName
	 * @param aBisName
	 * @param utility
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static synchronized Properties loadBandwidthProperties(
			String aFileName,
	        String aBisName,
	        Utility utility)
	throws FileNotFoundException, IOException 
	{
	   
	   if ( null == bandwidthProperties.get("FileLoaded") )
       {	
			 PropertiesFileLoader.read(bandwidthProperties,aFileName,utility);
		     bandwidthProperties.put("FileLoaded","TRUE");
       }
	
	   return bandwidthProperties;
	}

	/**
	 * @param aFileName
	 * @param aBisName
	 * @param utility
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public static synchronized Properties loadServiceBundleIdProperties(
			String aFileName,
	        String aBisName,
			Utility utility)
		throws FileNotFoundException, IOException, InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound 
		{
	   
		if ( null == serviceBundleIdProperties.get("FileLoaded") )
		{	
		   PropertiesFileLoader.read(serviceBundleIdProperties,aFileName,utility);
		   serviceBundleIdProperties.put("FileLoaded","TRUE");
		}
	
		return serviceBundleIdProperties;
	}

}
