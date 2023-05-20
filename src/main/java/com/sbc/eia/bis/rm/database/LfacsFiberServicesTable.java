//$Id: LfacsFiberServicesTable.java,v 1.4 2009/03/14 04:01:32 lg4542 Exp $
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
 * Class      : LfacsFiberServicesTable
 * Description: Class file for LFACS_FIBER_SERVICES table queries.
 */
public class LfacsFiberServicesTable extends Table
{    
	private static String sqlSelect = "select REGION, CLS_SERVICE_CODE, "
    + "CLS_MODIFIER, USOC, SERVICE_CATEGORY, SERVICE_NAME, "
    + "PROVISIONING_CONFLICT_IND, NEGOTIATION_CONFLICT_IND from LFACS_FIBER_SERVICES ";  

    /**
     * Retrieve a row from LFACS_FIBER_SERVICES table using Usoc and Service Category.
     * 
     * @param aREGION
     * @param aUSOC
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsFiberServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsFiberServicesRow retrieveRowByUsocAndServiceCategory(
        String aREGION,
        String aUSOC,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {        
        String myMethodNameName = "LfacsFiberServicesTable: retrieveRowByUsocAndServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
        
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + (aUSOC == null ? "USOC is null"
                        : ("upper(USOC) = " + aUSOC.toUpperCase()))
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(SERVICE_CATEGORY) = ? and ";
    
        int populatedValues = 2;
        if (aUSOC != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
    
        switch(populatedValues)
        {
            case 2: // optional value is null
            {
                SQLstatement = SQLstatement + "USOC is null";
                break;
            }
            case 3: // optional value is populated
            {
                queryValues[2] = aUSOC.toUpperCase();
                SQLstatement = SQLstatement + "upper(USOC) = ?";
                break;
            }
        }
        LfacsFiberServicesRow rowObj = new LfacsFiberServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
	
	/**
     * Retrieve a row from LFACS_FIBER_SERVICES table using Service Code, Modifier, and Service Category.
     * 
     * @param aREGION
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param SERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsFiberServicesRow
     * @exception Exception      : Exception.
     * 
     * @author Lira Galsim
     */
    public LfacsFiberServicesRow retrieveRowByServiceCodeAndModifierAndServiceCategory(
        String aREGION, 
        String aCLS_SERVICE_CODE,
        String aCLS_MODIFIER, 
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {
    
        String myMethodNameName = "LfacsFiberServicesTable: retrieveRowByServiceCodeAndModifierAndServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + (aCLS_SERVICE_CODE == null ? "CLS_SERVICE_CODE is null"
                        : ("upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()))
                + " and "
                + (aCLS_MODIFIER == null ? "CLS_MODIFIER is null"
                        : ("upper(CLS_MODIFIER) = " + aCLS_MODIFIER.toUpperCase()))
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(SERVICE_CATEGORY) = ? and ";
    
        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;
        if (aCLS_MODIFIER != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
    
        switch(populatedValues)
        {
            case 2: // both optional values are null
            {
                SQLstatement = SQLstatement + "CLS_SERVICE_CODE is null and CLS_MODIFIER is null";
                break;
            }
            case 3: // one is populated, the other optional value is null
            {
                if (aCLS_SERVICE_CODE != null)
                {
                    queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and CLS_MODIFIER is null";
                }
                else
                {
                    queryValues[2] = aCLS_MODIFIER.toUpperCase();
                    SQLstatement = SQLstatement + "upper(CLS_MODIFIER) = ? and CLS_SERVICE_CODE is null";
                }
                break;
            }
            case 4: // both optional values are populated
            {
            	queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                queryValues[3] = aCLS_MODIFIER.toUpperCase();
                SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and upper(CLS_MODIFIER) = ?";
                break;
            }
        }
        LfacsFiberServicesRow rowObj = new LfacsFiberServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    /**
     * Retrieve a row from LFACS_FIBER_SERVICES table using Service Code and Service Category.
     * 
     * @param aREGION
     * @param aCLS_SERVICE_CODE
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsFiberServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */    
    public LfacsFiberServicesRow retrieveRowByServiceCodeAndServiceCategory(
        String aREGION, 
        String aCLS_SERVICE_CODE,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsFiberServicesTable: retrieveRowByServiceCodeAndServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + (aCLS_SERVICE_CODE == null ? "CLS_SERVICE_CODE is null"
                        : ("upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()))
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(SERVICE_CATEGORY) = ? and ";

        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;

        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value

        switch(populatedValues)
        {
            case 2: // optional value is null
            {
                SQLstatement = SQLstatement + "CLS_SERVICE_CODE is null";
                break;
            }
            case 3: // optional value is populated
            {
                queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ?";
                break;
            }
        }

        LfacsFiberServicesRow rowObj = new LfacsFiberServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }   
    
    /**
     * Retrieve a row from LFACS_FIBER_SERVICES table using Service Category.
     * 
     * @param aREGION
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsFiberServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsFiberServicesRow retrieveRowByServiceCategory(
        String aREGION, 
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsFiberServicesTable: retrieveRowByServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(SERVICE_CATEGORY) = ?";

        String[] queryValues = new String[2];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value

        LfacsFiberServicesRow rowObj = new LfacsFiberServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }    
}