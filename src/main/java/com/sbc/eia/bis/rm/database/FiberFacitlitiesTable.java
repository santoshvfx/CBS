//$Id: FiberFacitlitiesTable.java,v 1.1 2007/08/23 20:42:54 jc1421 Exp $
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

import java.sql.SQLException;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class FiberFacitlitiesTable extends Table
{
    private static String sqlSelect = "select FIBER_FACILITY, TYPE, MODEL, LTS from FIBER_FACILITIES ";

    /**
     * @param aTYPE
     * @param aMODEL
     * @param aLTS
     * @param aProperties
     * @param aLogger
     * @return
     * @throws SQLException
     */
    public FiberFacilitiesRow retrieveRow(String aTYPE, String aMODEL, String aLTS, Hashtable aProperties,
            Logger aLogger) throws SQLException
    {
        String myMethodNameName = "FiberFacitlitiesTable:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "SQLstatement: " + sqlSelect + "where upper(TYPE) = "
                + aTYPE.toUpperCase() + " and upper(MODEL) = " + aMODEL.toUpperCase() + " and "
                + (aLTS == null ? "LTS is null" : ("upper(LTS) = " + aLTS.toUpperCase())));
        String SQLstatement = sqlSelect + "where upper(TYPE) = ? and upper(MODEL) = ? and ";

        String[] queryValues = new String[(aLTS == null ? 2 : 3)];
        queryValues[0] = aTYPE.toUpperCase();	// Required value
        queryValues[1] = aMODEL.toUpperCase();	// Required value
        if (aLTS == null)
            SQLstatement = SQLstatement + "LTS is null";
        else
        {
            queryValues[2] = aLTS.toUpperCase();
            SQLstatement = SQLstatement + "upper(LTS) = ?";
        }

        FiberFacilitiesRow rowObj = new FiberFacilitiesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
