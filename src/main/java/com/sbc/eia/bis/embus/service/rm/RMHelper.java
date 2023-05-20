//$Id: RMHelper.java,v 1.2 2008/10/30 20:34:07 jc1421 Exp $
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
//# 09/17/2007  Rene Duka             Creation.
//# 10/30/2008  Jon Costa             DR111198: Changed remote_call/remote_return to use rm.properties.BIS_NAME.

package com.sbc.eia.bis.embus.service.rm;

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
public class RMHelper 
    extends ClientHelper
    implements ClientHelperIF {

    /**
     * Constructor: RMHelper
     * @param Logger        logger
     * @param Hashtable     properties
     * @throws ServiceException
     */
    public RMHelper(Logger logger, Hashtable properties)
        throws ServiceException {

        super(properties, logger, ClientHelperConstants.RM_SERVICE_NAME);
      
        aProperties = properties;
        aLogger = logger;
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

        getServiceAccess().send(ClientHelperConstants.RM_REQUEST, aInputXMLMsg, aJMSRequestPropertiesList);
    }

    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL() {

        aLogger.log(LogEventId.REMOTE_CALL,
                    ClientHelperConstants.RM_SERVICE_NAME,
                    (String) this.aProperties.get("BIS_NAME").toString(),
                    (String) this.aProperties.get("BIS_NAME").toString(),
                    ClientHelperConstants.RM_REQUEST);
    }

    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN() {

        aLogger.log(LogEventId.REMOTE_RETURN,
                    ClientHelperConstants.RM_SERVICE_NAME,
                    (String) this.aProperties.get("BIS_NAME").toString(),
                    (String) this.aProperties.get("BIS_NAME").toString(),
                    ClientHelperConstants.RM_REQUEST);
    }
}

