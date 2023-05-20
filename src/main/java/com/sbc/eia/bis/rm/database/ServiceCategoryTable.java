//$Id: ServiceCategoryTable.java,v 1.3 2009/04/02 20:48:30 lg4542 Exp $
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
 * Class      : ServiceCategoryTable
 * Description: Class file for SERVICE_CATEGORY table queries.
 */
public class ServiceCategoryTable extends Table
{
    private static String sqlSelect = "select SERVICE_TYPE, SERVICE_CATEGORY from SERVICE_CATEGORY ";
    
    /**
     * Retrieve a row from SERVICE_CATEGORY table using Service Type.
     * 
     * @param aSERVICE_TYPE
     * @param aProperties
     * @param aLogger
     * @return ServiceCategoryRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public ServiceCategoryRow retrieveRow(
        String aSERVICE_TYPE, 
        Hashtable aProperties,
        Logger aLogger) 
            throws 
                SQLException
    {
        String myMethodNameName = "ServiceCategoryTable:retrieveRow()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: " + sqlSelect + "where upper(SERVICE_TYPE) = " + aSERVICE_TYPE.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(SERVICE_TYPE) = ?";
        
        String[] queryValues = new String[1];
        queryValues[0] = aSERVICE_TYPE.toUpperCase();			// Required value
    
        ServiceCategoryRow rowObj = new ServiceCategoryRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
    
        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
