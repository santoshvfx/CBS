//$Id: ClientHelperIF.java,v 1.2 2007/09/10 15:48:14 rd2842 Exp $
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
//# 09/07/2007  Rene Duka             Creation.
//# 09/10/2007  Rene Duka             Modified for LS6.

package com.sbc.eia.bis.embus.service.client.access;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
* @author rd2842
*
*/
public interface ClientHelperIF {

    /**
     * Method: publishMessage
     * @param String     aInputXMLMsg
     * @param Properties aJMSRequestPropertiesList
     * @throws ServiceException
     */
    public void publishMessage(
        String aInputXMLMsg,
        Properties aJMSRequestPropertiesList)
        throws ServiceException;

    /**
     * Method: logREMOTE_CALL
     */
    public void logREMOTE_CALL();

    /**
     * Method: logREMOTE_RETURN
     */
    public void logREMOTE_RETURN();
}
