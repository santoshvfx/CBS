//$Id: LfacsCopperServicesTable.java,v 1.5 2009/03/14 05:57:46 hw7243 Exp $
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
 * Class      : LfacsCopperServicesTable
 * Description: Class file for LFACS_COPPER_SERVICES_VIEW queries.
 */
public class LfacsCopperServicesTable extends Table
{    
	private static String sqlSelect = "select REGION, LS_CONDITIONED, CLS_SERVICE_CODE, "
    + "CLS_MODIFIER, USOC, SERVICE_CATEGORY, SERVICE_NAME, PROVISIONING_CONFLICT_IND, "
    + "NEGOTIATION_CONFLICT_IND, PRIORITY_NUMBER from LFACS_COPPER_SERVICES_VIEW ";  

    /**
     * Retrieve a row from LFACS_COPPER_SERVICES_VIEW using Usoc and Service Category.
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aUSOC
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsCopperServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsCopperServicesRow retrieveRowByUsocAndServiceCategory(
        String aREGION,
        String aLS_CONDITIONED,
        String aUSOC,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {        
        String myMethodNameName = "LfacsCopperServicesTable: retrieveRowByUsocAndServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
        
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toUpperCase()
                + " and "
                + "upper(USOC) = " + aUSOC.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase() 
                + "and upper(CLS_MODIFIER) = '*' " 
				+ "and upper(CLS_SERVICE_CODE) = '*' " );
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(LS_CONDITIONED) = ? " +
        		"and upper(SERVICE_CATEGORY) = ? " +
        		 "and upper(USOC) = ? " +
        		"and upper(CLS_MODIFIER) = '*' " +
				"and upper(CLS_SERVICE_CODE) = '*'";
    
        int populatedValues = 3;
        if (aUSOC != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toUpperCase();             // Required value
        queryValues[2] = aSERVICE_CATEGORY.toUpperCase();           // Required value    
        queryValues[3] = aUSOC.toUpperCase();
        
        LfacsCopperServicesRow rowObj = new LfacsCopperServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
	
	/**
     * Retrieve a row from LFACS_COPPER_SERVICES_VIEW using Service Code, Modifier, and Service Category.
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param SERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsCopperServicesRow
     * @exception Exception      : Exception.
     * 
     * @author Lira Galsim
     */
    public LfacsCopperServicesRow retrieveRowByServiceCodeAndModifierAndServiceCategory(
        String aREGION,
        String aLS_CONDITIONED,
        String aCLS_SERVICE_CODE,
        String aCLS_MODIFIER, 
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {
    
        String myMethodNameName = "LfacsCopperServicesTable: retrieveRowByServiceCodeAndModifierAndServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toUpperCase()
                + " and "
                + "upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()
                + " and "
                + "upper(CLS_MODIFIER) = " + aCLS_MODIFIER.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase() 
                + "and upper(USOC) = '*' ");
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(LS_CONDITIONED) = ? " +       		
        		"and upper(SERVICE_CATEGORY) = ? " +
        		"and upper(CLS_SERVICE_CODE) = ? and upper(CLS_MODIFIER) = ? " +
        		"and upper(USOC) = '*'";
    
        int populatedValues = 3;
        if (aCLS_SERVICE_CODE != null) populatedValues++;
        if (aCLS_MODIFIER != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toUpperCase();             // Required value
        queryValues[2] = aSERVICE_CATEGORY.toUpperCase();           // Required value
    
    	queryValues[3] = aCLS_SERVICE_CODE.toUpperCase();
        queryValues[4] = aCLS_MODIFIER.toUpperCase();
        
   
        LfacsCopperServicesRow rowObj = new LfacsCopperServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    /**
     * Retrieve a row from LFACS_COPPER_SERVICES_VIEW using Service Code and Service Category.
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsCopperServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsCopperServicesRow retrieveRowByServiceCodeAndServiceCategory(
        String aREGION,
        String aLS_CONDITIONED,
        String aCLS_SERVICE_CODE,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsCopperServicesTable: retrieveRowByServiceCodeAndServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toUpperCase()
                + " and "
                + (aCLS_SERVICE_CODE == null ? "CLS_SERVICE_CODE is null"
                        : ("upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()))
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase()
                + "and upper(CLS_MODIFIER) = '*' " 
				+ "and upper(USOC) = '*' " );
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        				"and upper(LS_CONDITIONED) = ? " +
        				"and upper(SERVICE_CATEGORY) = ? " +
        				"and upper(CLS_SERVICE_CODE) = ? " +
        				"and upper(CLS_MODIFIER) = '*' " +
        				"and upper(USOC) = '*'";

        int populatedValues = 3;
        if (aCLS_SERVICE_CODE != null) populatedValues++;

        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toUpperCase();             // Required value
        queryValues[2] = aSERVICE_CATEGORY.toUpperCase();           // Required value
    
        queryValues[3] = aCLS_SERVICE_CODE.toUpperCase();
      
        LfacsCopperServicesRow rowObj = new LfacsCopperServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }   
    
    /**
     * Retrieve a row from LFACS_COPPER_SERVICES_VIEW using Service Category.
     * 
     * @param aREGION
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsCopperServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsCopperServicesRow retrieveRowByServiceCategory(
        String aREGION,
        String aLS_CONDITIONED,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsCopperServicesTable: retrieveRowByServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = " 
                + aSERVICE_CATEGORY.toUpperCase() 
                + "and upper(CLS_MODIFIER) = '*' " 
				+ "and upper(USOC) = '*' " 
				+ "and upper(CLS_SERVICE_CODE) = '*' ");
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(LS_CONDITIONED) = ? " +
        		"and upper(SERVICE_CATEGORY) = ? " +
        		"and upper(CLS_MODIFIER) = '*' " +
				"and upper(USOC) = '*' " +
				"and upper(CLS_SERVICE_CODE) = '*'";

        String[] queryValues = new String[3];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toUpperCase();  			// Required value
        queryValues[2] = aSERVICE_CATEGORY.toUpperCase();  			// Required value

        LfacsCopperServicesRow rowObj = new LfacsCopperServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }    
}