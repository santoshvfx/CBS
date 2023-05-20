// $Id: PublishTNAssignmentOrderNotification.java,v 1.6 2007/11/13 22:05:16 ds4987 Exp $
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
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 06/14/2006   Rene Duka             Creation.
//# 06/27/2006   Rene Duka             LS R3 enhancements. pTAO - Code WT changes.
//# 12/04/2006	 Doris Sunga		   DR #170893 - correlation id issue in PTAO log and xml output
//# 11/13/2007   Doris Sunga		   DR #78344 - add aLogger In calling SOAC() constructor
package com.sbc.eia.bis.facades.rm.transactions.PublishTNAssignmentOrderNotification;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
 * @author rd2842
 */
public class PublishTNAssignmentOrderNotification extends TranBase {

    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private SOAC aSOACHelper = null;
	private Logger aLogger = null;
    /**
     * Constructor for publishTNAssignmentOrderNotification.
     */
    public PublishTNAssignmentOrderNotification() {
        super();
    }

    /**
     * Constructor for publishTNAssignmentOrderNotification.
     * @param param
     */
    public PublishTNAssignmentOrderNotification(Utility utility, Hashtable param, Logger logger) {
        super(param);
        aLogger = logger;
//        aUtility = this;
        aUtility = utility;
        aProperties = getPROPERTIES();
    }

    /**
     * Method: execute => publishTNAssignmentOrderNotification
     *
     * @param ServiceOrderResponseParser  aParsedFCIF
     * @param Logger                      aLogger
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     */
    public void execute(
        SOACServiceOrderResponseParser aParsedFCIF,
        String correlationId)
        throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented {

        String myMethodName = "PublishTNAssignmentOrderNotification:execute()";

        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        SOAC aSOACHelper = null;
        try {
            if (aSOACHelper == null) {
                aSOACHelper = new SOAC(getPROPERTIES(), aUtility, aLogger);
            }
            aSOACHelper.publishTNAssignmentOrderNotification(aUtility, aParsedFCIF, correlationId);            

        }
        catch (Exception e) {
            aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + "Exception in PublishTNAssignmentOrderNotification: " + e.getMessage());
        }
        finally  {
            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        }
    }
}