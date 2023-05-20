//$Id: DBRowBase.java,v 1.1 2007/08/23 20:42:54 jc1421 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/2007	   Jon Costa			  Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.sbc.bccs.utilities.Logger;

/**
 * @author jc1421
 */
public abstract class DBRowBase
{
    public DBRowBase()
    {
        super();
    }

    /**
     * @param rs
     * @param aLogger
     * @throws SQLException
     */
    public abstract void setRow(ResultSet rs, Logger aLogger) throws SQLException;
}
