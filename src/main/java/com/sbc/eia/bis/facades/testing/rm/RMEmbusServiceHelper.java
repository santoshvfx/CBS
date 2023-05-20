//$Id: RMEmbusServiceHelper.java,v 1.3 2006/07/05 22:30:48 jp2854 Exp $

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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 06/22005  | Jinmin Ni	        | create
//# 07/05/2006| Jyothi Jasti        | Updated for testing SOA async messages.

package com.sbc.eia.bis.facades.testing.rm;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;


/**
 * @author jn1936
 *
 */


public class RMEmbusServiceHelper extends TranBase
{
	private RMServiceHelper aEmbusHelper;
	
	public RMEmbusServiceHelper(Hashtable clientProperties)
		throws ServiceException
		{
			Hashtable loggerProp = new Hashtable();

			//set bcc.logger require properties
			loggerProp.put("BIS_NAME", "RMBIS");
			loggerProp.put("BIS_VERSION", "");
			loggerProp.put("LOG_POLICY_PATH", "");
			loggerProp.put("STDOUT", "FALSE");
			setPROPERTIES(loggerProp);

			aEmbusHelper = new RMServiceHelper(clientProperties, this);
		
		}
		//one way push
		public void send(String inputMsg)
		throws ServiceException
		{
			aEmbusHelper.sendToRmSoacQueue(inputMsg);
		}
		
		public void send(String target, String inputMsg)
		throws ServiceException
		{
			if(target.equals("SOA"))
				aEmbusHelper.sendToRmSoaQueue(inputMsg);
		}
	
		public static void main(String[] args)
		{
			System.setProperty("bis.platform", "dev");
			Properties p = new Properties();
		
			try
			{
				PropertiesFileLoader.read(p,"testclient.properties.rm",null);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				RMEmbusServiceHelper aHelper = new RMEmbusServiceHelper(p);
				aHelper.send("try try");
				System.out.println("message sent");
				System.exit(0);
			}
			catch(ServiceException e)
			{
				e.printStackTrace();	
			}
		}

	private class RMServiceHelper extends DecryptionServiceHelper
	{
		public final static String RM_SERVICE_NAME = "RM_TEST";
		public final static String SOAC_REQUEST = "SoacRequest";
		public final static String SOA_REQUEST = "SoaRequest";


		public RMServiceHelper(Hashtable properties, Logger logger)
			throws ServiceException
		{
			super(properties, logger, RM_SERVICE_NAME);

		}

		public void sendToRmSoacQueue(String inputMsg) throws ServiceException
		{
			getServiceAccess().send(SOAC_REQUEST, inputMsg);
		}
		
		public void sendToRmSoaQueue(String inputMsg) throws ServiceException
		{
			getServiceAccess().send(SOA_REQUEST, inputMsg);
		}
	}
}

