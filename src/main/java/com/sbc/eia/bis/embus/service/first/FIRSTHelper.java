//$Id: FIRSTHelper.java,v 1.4 2008/03/14 14:00:52 biscvsid Exp $
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
//# 08/13/2007  Rene Duka             Creation.
//# 09/12/2007  Rene Duka             Modified for LS6.
//#	02/19/2008	Julius Sembrano		  Modified for SelfTest enhancements

package com.sbc.eia.bis.embus.service.first;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.access.ClientHelper;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperConstants;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperIF;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
* @author rd2842
*
*/
public class FIRSTHelper 
    extends ClientHelper
    implements ClientHelperIF {

    /**
     * Constructor: FIRSTHelper
     * @param Logger        logger
     * @param Hashtable     properties
     * @throws ServiceException
     */
    public FIRSTHelper(Logger logger, Hashtable properties)
        throws ServiceException {

        super(properties, logger, ClientHelperConstants.FIRST_SERVICE_NAME);
        
        aProperties = properties;
        aLogger = logger;
    }
   
    public FIRSTHelper(Hashtable properties, Logger logger)
    throws ServiceException {

    	this(logger, properties);
}

    /**
     * Method: publishMessage
     * @param String        aInputXMLMsg
     * @param Properties    aJMSRequestPropertiesList
     * @throws ServiceException
     */
    public void publishMessage(
        String aInputXMLMsg,
        Properties aJMSRequestPropertiesList)
        throws ServiceException {

        getServiceAccess().send(ClientHelperConstants.FIRST_REQUEST, aInputXMLMsg, aJMSRequestPropertiesList);
    }

    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL() {

        aLogger.log(LogEventId.REMOTE_CALL,
                    ClientHelperConstants.FIRST_SERVICE_NAME,
                    ClientHelperConstants.FIRST_SERVICE_NAME + ClientHelperConstants.FIRST_VERSION,
                    ClientHelperConstants.FIRST_SERVICE_NAME + ClientHelperConstants.FIRST_VERSION,
                    ClientHelperConstants.FIRST_REQUEST);
    }
  
    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN() {

        aLogger.log(LogEventId.REMOTE_RETURN,
                    ClientHelperConstants.FIRST_SERVICE_NAME,
                    ClientHelperConstants.FIRST_SERVICE_NAME + ClientHelperConstants.FIRST_VERSION,
                    ClientHelperConstants.FIRST_SERVICE_NAME + ClientHelperConstants.FIRST_VERSION,
                    ClientHelperConstants.FIRST_REQUEST);
    }
}
