//$Id: RtidMessagesTable.java,v 1.4 2008/03/14 14:28:33 jc1421 Exp $
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
//# 02/2008	   Jon Costa			  LS7: column ORDER_ACTION_TYPE added to table
//# 02/2008	   Jon Costa			  LS7: Changed table name to: RTID_MESSAGES2

/*****************************************************************************
//NOTE: DO NOT RTAG THIS FILE TO ANY RELEASE EALIER THAN LIGHTSPEED 7 (RM21.0)
******************************************************************************/
package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class RtidMessagesTable extends Table
{
    private static String sqlSelect = "select CODE, APPLICATION, SEVERITY, TEXT, ORDER_ACTION_TYPE from RTID_MESSAGES2 ";

    /**
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param aProperties
     * @param aLogger
     * @return
     * @throws SQLException
     */
    public RtidMessagesRow retrieveRow(String aCODE, String aAPPLICATION, String aORDER_ACTION_TYPE, Hashtable aProperties, Logger aLogger)
            throws SQLException
    {
        String myMethodNameName = "RtidMessagesTable:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
        
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: " + sqlSelect + "where upper(CODE) = "
                + aCODE.toUpperCase() + " and upper(APPLICATION) = " + aAPPLICATION.toUpperCase()+ " and "
                + (aORDER_ACTION_TYPE == null ? "ORDER_ACTION_TYPE is null"
                        : ("upper(aORDER_ACTION_TYPE) = " + aORDER_ACTION_TYPE.toUpperCase())));

        String SQLstatement = sqlSelect + "where upper(CODE) = ? and upper(APPLICATION) = ?";

        int populatedValues = (aORDER_ACTION_TYPE == null ? 2 : 3);    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aCODE.toUpperCase();			// Required value
        queryValues[1] = aAPPLICATION.toUpperCase();	// Required value
    
        switch(populatedValues)
        {
            case 2: // optional aORDER_ACTION_TYPE value is null
            {
                SQLstatement = SQLstatement + " and ORDER_ACTION_TYPE is null";
                break;
            }
            case 3: // aORDER_ACTION_TYPE is populated
            {
                queryValues[2] = aORDER_ACTION_TYPE.toUpperCase();
                SQLstatement = SQLstatement + " and upper(ORDER_ACTION_TYPE) = ?";
                break;
            }
        }
        
        RtidMessagesRow rowObj = new RtidMessagesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
