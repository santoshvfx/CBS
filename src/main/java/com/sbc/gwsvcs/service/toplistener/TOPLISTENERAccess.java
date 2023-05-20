// $Id: TOPLISTENERAccess.java,v 1.3 2006/02/07 19:28:15 jc1421 Exp $

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
//# 6/14/2005 | Jinmin Ni	        | create

package com.sbc.gwsvcs.service.toplistener;

import com.sbc.gwsvcs.access.vicuna.ServiceAccess;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPLISTENER_const;
import com.sbc.vicunalite.api.MEventType;

/**
 * Wraps the service side for access to the TOPSender service.
 * Creation date: (6/06/05 11:36:38 AM)
 * @author: Jinmin Ni
 */
public class TOPLISTENERAccess extends ServiceAccess
{

	public final static String version = "2.1";
	public final static String name = "TOPLISTENER";

	public final static int TOPLISTENER_REQ_NBR 	= TOPLISTENER_const.TOPSendToHostReq;
	public final static int TOPLISTENER_RESP_NBR 	= TOPLISTENER_const.TOPSendToHostResp;
				
	public final static int EXCEPTION_NBR =  TOPLISTENER_const.ExceptionResp;
	
	public final static MEventType TOPLISTENER_REQ = 		new MEventType("TOPLISTENER_REQ");		// Event 6000
	public final static MEventType TOPLISTENER_RESP = 	new MEventType("TOPLISTENER_RESP");		// Event 6001
				
	public final static MEventType EXCEPTION = 			new MEventType("EXCEPTION");			// Event 9999
/**
 * Constructor accepting Vicuna configuration file, directory for configuration files and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public TOPLISTENERAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
