//$Id: PriorityListTable.java,v 1.1 2009/02/05 03:01:24 lg4542 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date          | Author              | Notes
//# --------------------------------------------------------------------------
//# 02/04/2009	    Lira Galsim			  Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : PriorityListTable
 * Description: Class file for PRIORITY_LIST table queries.
 */
public class PriorityListTable extends Table
{
    private static String sqlSelect = "select PRIORITY_NUMBER, SERVICE_NAME from PRIORITY_LIST ";
    
    /**
     * Retrieve a row from PRIORITY_LIST table using Service Name.
     * 
     * @param aSERVICE_NAME
     * @param aProperties
     * @param aLogger
     * @return ServiceCategoryRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public PriorityListRow retrieveRow(
        String aSERVICE_NAME, 
        Hashtable aProperties,
        Logger aLogger) 
            throws 
                SQLException
    {
        String myMethodNameName = "PriorityListTable:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: " + sqlSelect + "where upper(SERVICE_NAME) = " + aSERVICE_NAME.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(SERVICE_NAME) = ?";
        
        String[] queryValues = new String[1];
        queryValues[0] = aSERVICE_NAME.toUpperCase();			// Required value
    
        PriorityListRow rowObj = new PriorityListRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
    
        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
