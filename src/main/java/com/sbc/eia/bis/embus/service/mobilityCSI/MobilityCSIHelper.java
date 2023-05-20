//$Id: MobilityCSIHelper.java,v 1.2 2008/03/14 14:01:38 biscvsid Exp $
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
//# 11/13/2007  Rene Duka             CR 15842: Added mobilityCSI as a client.
//#	02/21/2008	Julius Sembrano		  Modified for SelfTest enhancements

package com.sbc.eia.bis.embus.service.mobilityCSI;

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
public class MobilityCSIHelper 
    extends ClientHelper
    implements ClientHelperIF 
{
    /**
     * Constructor: OPUSHelper
     * @param Logger        logger
     * @param Hashtable     properties
     * @throws ServiceException
     */
    public MobilityCSIHelper(Logger logger, Hashtable properties)
        throws ServiceException 
    {
        super(properties, logger, ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME);
      
        aProperties = properties;
        aLogger = logger;
    }

    public MobilityCSIHelper(Hashtable properties, Logger logger) throws ServiceException{
    	this(logger, properties);
    }
    /**
     * Method: publishMessage
     * @param String     aInputXMLMsg
     * @param Properties aJMSRequestPropertiesList
     * @throws ServiceException
     */
    public void publishMessage(
        String aInputXMLMsg,
        Properties aJMSRequestPropertiesList)
        throws ServiceException {

        getServiceAccess().send(ClientHelperConstants.MOBILITY_CSI_REQUEST, aInputXMLMsg, aJMSRequestPropertiesList);
    }

    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL() {

        aLogger.log(LogEventId.REMOTE_CALL,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME + ClientHelperConstants.MOBILITY_CSI_VERSION,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME + ClientHelperConstants.MOBILITY_CSI_VERSION,
                    ClientHelperConstants.MOBILITY_CSI_REQUEST);
    }

    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN() {

        aLogger.log(LogEventId.REMOTE_RETURN,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME + ClientHelperConstants.MOBILITY_CSI_VERSION,
                    ClientHelperConstants.MOBILITY_CSI_SERVICE_NAME + ClientHelperConstants.MOBILITY_CSI_VERSION,
                    ClientHelperConstants.MOBILITY_CSI_REQUEST);
    }
}
