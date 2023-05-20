// $Id: CodesRCAccess.java,v 1.1 2007/01/23 15:33:45 cy4727 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

package com.sbc.eia.bis.embus.service.codes.access;

import java.util.Properties;

import javax.jms.Message;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;

/** 
 *  NP Connector access.
 *  Object for access to the resource connector.
 */

public class CodesRCAccess extends ServiceAccess
{

	/**
	 * Constructor for NetProvisioningAccess.
	 * @param environmentName
	 * @param configFileName
	 * @param logger
	 * @throws ServiceException
	 */

	public CodesRCAccess(String environmentName, String configurationFileName, Logger logger)
		throws ServiceException
	{

		super(environmentName, configurationFileName, logger);

	}
	public String handleEmbusResponse(Message aMessage, Properties propertiesInResponse)
		throws ServiceException
	{
		return defaultHandleEmbusResponse(aMessage, propertiesInResponse);
	}

}
