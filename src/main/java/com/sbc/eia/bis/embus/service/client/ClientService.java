//$Id: ClientService.java,v 1.5 2007/09/17 15:59:43 rd2842 Exp $
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
//# 5/11/2005 | Jinmin Ni           | create
//# 6/01/2005 | Jinmin Ni           | Changed to use new copyright notice
//# 6/03/2005 | Jinmin Ni           | Changed to one way method call.
//# 7/25/2005 | Jinmin Ni           | Add overload the one way method to get previously
//#                                 | persisted jms properties for asyn. transaction
//# 06/19/2005| Jyothi Jasti        | Added publishMessage method.
//# 09/10/2007| Rene Duka           | Modified for LS6.

package com.sbc.eia.bis.embus.service.client;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperFactory;
import com.sbc.eia.bis.embus.service.client.access.ClientHelperIF;
import com.sbc.eia.idl.bis_types.BisContext;

/**
* @author jn1936
*
*/
public class ClientService {

    ClientHelperIF aClientHelper;
    ClientHelperFactory aClientHelperFactory;

    Utility aUtility = null;
    Properties aProperties;
    Logger aLogger;

    /**
     * Constructor: ClientService
     * @throws ServiceException
     */
    public ClientService()
        throws ServiceException {

        aClientHelperFactory = new ClientHelperFactory();
        aClientHelper = null;
    }

    /**
     * Constructor: ClientService
     * @param Hashtable     hash
     * @param Logger        logger
     * @throws ServiceException
     */
    public ClientService(Hashtable hash, Logger logger)
        throws ServiceException {

        this();

        aLogger = logger;
        aProperties = new Properties();

        // Convert the hash to a properties.
        Set set = hash.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            aProperties.setProperty(
                    (String) entry.getKey(),
                    (String) entry.getValue());
        }
    }

    /**
     * Constructor: ClientService
     * @param Properties    properties
     * @param Logger        logger
     * @throws ServiceException
     */
    public ClientService(Properties properties, Logger logger)
        throws ServiceException {

        this();

        aLogger = logger;
        aProperties = properties;
    }

    /**
     * Method: setClient
     * @param BisContext    aContext
     * @throws Exception
     */
    public void setClient(BisContext aContext)
        throws Exception {

        if (aClientHelper == null) {
            aClientHelper = aClientHelperFactory.getInstance(aContext, aLogger, aProperties);
        }
    }

    /**
     * Method: setClient
     * @param String        aClientName
     * @throws Exception
     */
    public void setClient(String aClientName)
        throws Exception {

        if (aClientHelper == null) {
            aClientHelper = aClientHelperFactory.getInstance(aClientName, aLogger, aProperties);
        }
    }
    
    /**
     * Method: publishMessage
     * @param String        inputXMLMsg
     * @param Properties    jmsRequestPropertiesList
     * @throws ServiceException
     */
    public void publishMessage(
        String inputXMLMsg,
        Properties jmsRequestPropertiesList)
        throws ServiceException {

        aClientHelper.publishMessage(inputXMLMsg, jmsRequestPropertiesList);
    }

    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL() {
        aClientHelper.logREMOTE_CALL();
    }

    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN() {
        aClientHelper.logREMOTE_RETURN();
    }
}
