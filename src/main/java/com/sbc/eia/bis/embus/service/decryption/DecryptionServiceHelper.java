//$Id: DecryptionServiceHelper.java,v 1.6 2008/03/14 13:59:50 biscvsid Exp $

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
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 6/21/2005 | Jinmin Ni	        | create
//# 1/10/2006 | Doris Sunga (RM)	| implemented abstract method sendAndReceiveSelfTestMessage()


package com.sbc.eia.bis.embus.service.decryption;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.DefaultServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.access.ServicePasswordDecrypter;

/**
 * @author JN1936
 *
 */

/** Description
 *  This file sit in between specific derived classes and base class of ServiceHelper to
 *  define service password decrypter that client chose to use to encrypt their password
 *  in configuration file.  
 *  Description
 */
public class DecryptionServiceHelper extends ServiceHelper
{
	public static final String PASSWORK_KEY_TAG = "OSS_PUBLIC_KEY";

	public DecryptionServiceHelper(
		Hashtable properties,
		Logger logger,
		String serviceName)
		throws ServiceException
	{
		super(properties, logger, serviceName);

		Hashtable ht = new Hashtable();

		ht.put(
			"PASSWORD_DECRYPTER",
			new ServicePasswordDecrypter((String) properties.get(PASSWORK_KEY_TAG)));

		setServiceAccess(
			new DefaultServiceAccess(
				getEnvironmentName(),
				getClientConfigFileName(),
				null,
				ht,
				logger));

	}

	/**
	 * Send message to the broker
	 */
	protected void sendAndReceiveSelfTestMessage() throws ServiceHelperSelfTestException
	{
		//defaultSendAndReceiveSelfTestMessage();
	}	
}
