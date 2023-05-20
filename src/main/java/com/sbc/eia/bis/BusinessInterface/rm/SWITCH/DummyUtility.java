//$Id: DummyUtility.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $
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
//# 06/23/2006  Rene Duka             Creation.

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import com.sbc.bccs.utilities.ExceptionThrower;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;


/**
 * @author rd2842
 *
 */

public class DummyUtility implements Utility {

    protected BisContext callerContext = null;
    private final static String LOGDELIM = "|";

    /**
    * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String, String)
    */

    public void log(
        String eventId,
        String the_location,
        String the_service,
        String the_component,
        String the_operation) {

        System.out.println(
            "Event Id: " + eventId
             + "Location: " + the_location
             + "Service: " + the_service
             + "Component: " + the_component
             + "Operation: " + the_operation);
    }

    /**
     * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String)
     */

    public void log(
        String eventId, 
        String the_error, 
        String the_message, 
        String the_source) {

        System.out.println(
            "Event Id: " + eventId
             + "Error: " + the_error
             + "Message: " + the_message
             + "Source: " + the_source);
    }

    /**
     * @see com.sbc.bccs.utilities.Logger#log(String, String)
     */

    public void log(
        String eventId, 
        String message) {

        System.out.println(
            "Event Id: " + eventId
             + "Message: " + message);
    }

    public void throwException(
        String code, 
        String text, 
        String origination, 
        Severity severity) 
        throws 
            InvalidData, 
            SystemFailure, 
            BusinessViolation, 
            AccessDenied, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {
    
        ExceptionThrower.throwException(callerContext, code, text, origination, severity, this ) ;
    }


    public void throwException(
        String code, 
        String text, 
        String origination, 
        Severity severity, 
        Exception exception)
        throws 
            InvalidData, 
            SystemFailure, 
            BusinessViolation, 
            AccessDenied, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {
    
        ExceptionThrower.throwException( callerContext, code, text, origination, severity, exception, this ) ;
    }

}

