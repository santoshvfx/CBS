//$Id: OMSHelper.java,v 1.3 2007/09/17 15:57:28 rd2842 Exp $
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
//# 09/10/2007  Rene Duka             Modified for LS6.

package com.sbc.eia.bis.embus.service.oms;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.access.ClientHelper;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperConstants;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperIF;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
* @author rd2842
*
*/
public class OMSHelper 
    extends ClientHelper
    implements ClientHelperIF {

    /**
     * Constructor: OMSHelper
     * @param Logger        logger
     * @param Hashtable     properties
     * @throws ServiceException
     */
    public OMSHelper(Logger logger, Hashtable properties)
        throws ServiceException {

        super(properties, logger, ClientHelperConstants.OMS_SERVICE_NAME);
        
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

        getServiceAccess().send(ClientHelperConstants.OMS_REQUEST, aInputXMLMsg, aJMSRequestPropertiesList);
    }
  
    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL() {

        aLogger.log(LogEventId.REMOTE_CALL,
                    ClientHelperConstants.OMS_SERVICE_NAME,
                    ClientHelperConstants.OMS_SERVICE_NAME + ClientHelperConstants.OMS_VERSION,
                    ClientHelperConstants.OMS_SERVICE_NAME + ClientHelperConstants.OMS_VERSION,
                    ClientHelperConstants.OMS_REQUEST);
    }
  
    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN() {

        aLogger.log(LogEventId.REMOTE_RETURN,
                    ClientHelperConstants.OMS_SERVICE_NAME,
                    ClientHelperConstants.OMS_SERVICE_NAME + ClientHelperConstants.OMS_VERSION,
                    ClientHelperConstants.OMS_SERVICE_NAME + ClientHelperConstants.OMS_VERSION,
                    ClientHelperConstants.OMS_REQUEST);
    }
  
    /**
     * Method: rmBisRequests
     * 
     * @param String     aInputXMLMsg
     * @param Properties aJMSRequestPropertiesList
     * @throws ServiceException
     */

    public void rmBisRequests(String aInputXMLMsg) 
        throws ServiceException {

        getServiceAccess().send(ClientHelperConstants.OMS_REQUEST, aInputXMLMsg);
    }

    /**
     * Method: rmBisRequests
     * 
     * @param String     aInputXMLMsg
     * @param Properties aJMSRequestPropertiesList
     * @throws ServiceException
     */
    public void rmBisRequests(
        String aInputXMLMsg,
        Properties aJMSRequestPropertiesList)
        throws ServiceException {
      
        getServiceAccess().send(ClientHelperConstants.OMS_REQUEST, aInputXMLMsg, aJMSRequestPropertiesList);
    }

    /**
     * Method: rmBisRequests
     * 
     * @param String     aInputXMLMsg
     * @param Properties aJMSRequestPropertiesList
     * @param Properties aOverride
     * @throws ServiceException
     */
    public void rmBisRequests(
        String aInputXMLMsg,
        Properties aJMSRequestPropertiesList,
        Properties aOverride)
        throws ServiceException {

        //reset the jms/embus tag with given values
        ServiceAccess access = getServiceAccess();
        boolean retry = true;
      
        if (aOverride == null) {
            rmBisRequests(aInputXMLMsg, aJMSRequestPropertiesList);
        }
        else {
            try {
                //remove whitespace for each token and check if the destinationName 
                //include the "t=jms,c=us". if does, take it out
                String destinationName = aOverride.getProperty(ClientHelperConstants.JMS_DESTINATION_NAME);
              
                if (destinationName != null) {
                    destinationName = removeTokenWhiteSpace(destinationName);
                    if (destinationName.endsWith(",t=jms,c=us")) {
                        String trimDestination = destinationName.trim().substring(0, destinationName.indexOf(",t=jms,c=us"));
                        aOverride.put(ClientHelperConstants.JMS_DESTINATION_NAME, trimDestination);
                        try {
                            aLogger.log(LogEventId.INFO_LEVEL_1, "Send to [" + trimDestination + "]");
                            access.send(ClientHelperConstants.OMS_REQUEST,
                                        aInputXMLMsg,
                                        aJMSRequestPropertiesList,
                                        aOverride);
                        }
                        catch (ServiceException e) {
                            //if exception is look up failure, try with ",t=jms,c=us"
                            if (isLDAPQueueLookupException(e)) {
                                aOverride.put(ClientHelperConstants.JMS_DESTINATION_NAME, destinationName);
                                //if look up falied again, retry with default queue
                                aLogger.log(LogEventId.INFO_LEVEL_1, e.getMessage()+" | try [" + destinationName+"]");
                                access.send(ClientHelperConstants.OMS_REQUEST,
                                            aInputXMLMsg,
                                            aJMSRequestPropertiesList,
                                            aOverride);
                            }
                            else {
                                //some other service exceptioin. do not retry with default queue
                                retry = false;
                                throw e;
                            }
                        }
                    }
                    else {
                        //retry with default queue only if look up failed
                        aLogger.log(LogEventId.INFO_LEVEL_1, "Send to [" + destinationName+"]");
                        access.send(ClientHelperConstants.OMS_REQUEST,
                                    aInputXMLMsg,
                                    aJMSRequestPropertiesList,
                                    aOverride);
                    }
                }
                else {
                    //no retry with default queue since it is using default queue already
                    retry = false;
                    aLogger.log(LogEventId.INFO_LEVEL_1, "No destinationName in overrride properties. Send to default queue on config xml file ");
                    access.send(ClientHelperConstants.OMS_REQUEST,
                                aInputXMLMsg,
                                aJMSRequestPropertiesList,
                                aOverride);
                }
            }
            catch (ServiceException e) {
                if(retry == false || isLDAPQueueLookupException(e) == false) {
                    throw e;    
                }
              
                //use default queue if look up failed
                aLogger.log(LogEventId.INFO_LEVEL_1, e.getMessage() + " | try default queue...");
                aOverride.remove(ClientHelperConstants.JMS_DESTINATION_NAME);
                access.send(ClientHelperConstants.OMS_REQUEST,
                            aInputXMLMsg,
                            aJMSRequestPropertiesList,
                            aOverride);
            }
        }
    }
}
