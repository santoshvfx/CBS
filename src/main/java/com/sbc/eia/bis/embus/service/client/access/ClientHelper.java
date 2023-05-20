//$Id: ClientHelper.java,v 1.4 2007/09/17 16:00:29 rd2842 Exp $
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
//# 6/20/2005 | Jinmin Ni           | Editted to accept encrypted password
//# 7/25/2005 | Jinmin Ni           | Add overload the one way method to use previously
//#                                 | persisted jms properties for asyn. transaction
//# 06/19/2005| Jyothi Jasti        | Added publishMessage method.
//# 09/10/2007| Rene Duka           | Modified for LS6.

package com.sbc.eia.bis.embus.service.client.access;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.embus.common.EMBusNamingException;

/**
* @author jn1936
*
*/

public class ClientHelper 
    extends DecryptionServiceHelper {

    public Hashtable aProperties = null;
    public Logger aLogger = null;
  
    /**
     * Constructor: ClientHelper
     * @param Hashtable     properties
     * @param Logger        logger
     * @param String        serviceName 
     */
    public ClientHelper(Hashtable properties, Logger logger, String serviceName)
        throws ServiceException {
      
        super(properties, logger, serviceName);

        aProperties = properties;
        aLogger = logger;
    }
  
    /**
     * Method: isLDAPQueueLookupException
     * @param ServiceException  e
     */
    public boolean isLDAPQueueLookupException(ServiceException e) {

        boolean isQLookUpFailed = false;
        //check if nested exception is caused by LDAP Queue look up failed with ldap error code 32
        String errorMsg = e.getMessage();
        if (e.getOriginalException() instanceof EMBusNamingException
            && errorMsg != null
            && (errorMsg.indexOf("LDAP: error code 34") != -1 || errorMsg.indexOf("LDAP: error code 32") != -1 )) {
            isQLookUpFailed = true;
        }
        return isQLookUpFailed;
    }

    /**
     * Method: removeTokenWhiteSpace
     * @param String        input
     */
    public String removeTokenWhiteSpace(String input) {
      
        StringBuffer sf = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        boolean isFirst = true;
        while (tokenizer.hasMoreTokens()) {
            if (isFirst == false) {
                sf.append(",");
            }
            else {
                isFirst = false;    
            }
            sf.append(tokenizer.nextToken().trim());
        }
        return sf.toString();
    }
}