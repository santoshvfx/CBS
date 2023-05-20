// $Id: SWITCHServerReturn.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $
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
//# 06/07/2006  Rene Duka             Creation.
//# 06/23/2006  Rene Duka             LS R3 enhancements. Code WT changes.

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Request helper for 5170 - WsiNtu event
 * @author: Rene Duka
 */

public class SWITCHServerReturn {

    private boolean aNextTN;
    private boolean aTNSourcePoolUpdateNotifier;
    private ExceptionData aError;
    

    /**
     * Constructor for SWITCHServerReturn
     */
    public SWITCHServerReturn() {
        aError = new ExceptionData();
        aTNSourcePoolUpdateNotifier = true;
        aNextTN = true;
    }

    /**
     * Sets the TNSourcePoolUpdateNotifier.
     * @param boolean     aValue
     */
    public void setTNSourcePoolUpdateNotifier(boolean aValue) {
        aTNSourcePoolUpdateNotifier =  aValue;
    }

    /**
     * Sets the Error.
     * @param ExceptionData     aValue
     */
    public void setError(ExceptionData aException) {
        aError.aCode = aException.aCode;
        aError.aDescription = aException.aDescription;
        aError.aOrigination = (StringOpt) IDLUtil.toOpt(aException.aOrigination.theValue());
        aError.aSeverity = (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class), aException.aSeverity.theValue());
    }

    /**
     * Sets the setNextTN.
     * @param boolean     aValue
     */
    public void setNextTN(boolean aValue) {
        aNextTN =  aValue;
    }

    /**
     * Returns the TNSourcePoolUpdateNotifier.
     * @return boolean
     */
    public boolean getTNSourcePoolUpdateNotifier() {
        return aTNSourcePoolUpdateNotifier;
    }

    /**
     * Returns the Error.
     * @return ExceptionData
     */
    public ExceptionData getError() {
        return aError;
    }

    /**
     * Returns the getNextTN.
     * @return boolean
     */
    public boolean getNextTN() {
        return aNextTN;
    }

}
