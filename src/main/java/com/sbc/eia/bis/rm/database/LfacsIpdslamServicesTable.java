//$Id: LfacsIpdslamServicesTable.java,v 1.8 2009/03/14 16:48:03 hw7243 Exp $
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
//# Date            | Author              | Notes
//# --------------------------------------------------------------------------------
//# 01/2009	        Lira Galsim			  Creation.
//# 03/09/2007      Julius Sembrano       DR123546 - RM BIS is returning service type LS-IP-DSL for Service code LX, modifier XP

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
 * Class      : LfacsIpdslamServicesTable
 * Description: Class file for LFACS_IPDSLAM_SERVICES_VIEW queries.
 */
public class LfacsIpdslamServicesTable extends Table
{    
	private static String sqlSelect = "select REGION, CLS_SERVICE_CODE, "
    + "CLS_MODIFIER, USOC, NETWORK_INTERFACE, SERVICE_CATEGORY, SERVICE_NAME, PROVISIONING_CONFLICT_IND, "
    + "NEGOTIATION_CONFLICT_IND, OK_TO_PROCEED_IND, PRIORITY_NUMBER from LFACS_IPDSLAM_SERVICES_VIEW ";  

    /**
     * Retrieve a row from LFACS_IPDSLAM_SERVICES_VIEW using Usoc, Network Interface, and Service Category.
     * 
     * @param aREGION
     * @param aUSOC
     * @param aNETWORK_INTERFACE
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsIpdslamServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsIpdslamServicesRow retrieveRowByUsocAndServiceCategory(
        String aREGION,
        String aUSOC,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {        
        String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByUsocAndServiceCategory()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
        
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + (aUSOC == null ? "USOC is null"
                		: ("upper(USOC) = " + aUSOC.toUpperCase()))
                + " and upper(SERVICE_CATEGORY) = "
                + aSERVICE_CATEGORY.toUpperCase()
                + " and upper(CLS_MODIFIER) = '*' "       		
        		+ " and upper(CLS_SERVICE_CODE) = '*' "
        		+ " and upper(NETWORK_INTERFACE) = '*' ");
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(SERVICE_CATEGORY) = ? " +
        		"and upper(USOC) = ? " +
        		"and upper(CLS_MODIFIER) = '*' " +       		
        		"and upper(CLS_SERVICE_CODE) = '*' " + 
        		"and upper(NETWORK_INTERFACE) = '*'";
    
        int populatedValues = 2;
        if (aUSOC != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
        queryValues[2] = aUSOC.toUpperCase();
   
        LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
	
    public LfacsIpdslamServicesRow retrieveRowByServiceCodeAndModifierAndServiceCategory(
            String aREGION,
            String aServiceCode,
            String aModifier,
            String aSERVICE_CATEGORY, 
            Hashtable aProperties, 
            Logger aLogger) 
            throws 
                SQLException 
            {        
            String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByServiceCodeAndModifierAndServiceCategory()";
            aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
            
            aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                    + sqlSelect
                    + "where upper(REGION) = "
                    + aREGION.toUpperCase()
                    + " and "
                    + "upper(CLS_SERVICE_CODE) = " + aServiceCode.toUpperCase()
                    + " and upper(CLS_MODIFIER) = " + aModifier.toUpperCase()
                    + " and upper(SERVICE_CATEGORY) = "
                    + aSERVICE_CATEGORY.toUpperCase()
                    + " and USOC = '*' "       		          		
            		+ " and NETWORK_INTERFACE = '*' ");
            String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
            		"and upper(SERVICE_CATEGORY) = ? " +           		
            		"and upper(CLS_MODIFIER) = ? " +       		
            		"and upper(CLS_SERVICE_CODE) = ? " + 
            		"and USOC = '*' " +
            		"and NETWORK_INTERFACE = '*'";
        
            int populatedValues = 2;
            if (aServiceCode != null) populatedValues++;
            if (aModifier != null) populatedValues++;
            
            String[] queryValues = new String[populatedValues];
            queryValues[0] = aREGION.toUpperCase();                     // Required value
            queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
            queryValues[2] = aServiceCode.toUpperCase();
            queryValues[3] = aModifier.toUpperCase();
            
            LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
            DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
            
            // if row is not found, set to null
            if (rowObj.getREGION().length() == 0)
                rowObj = null;

            aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
            return rowObj;
        }
	/**
     * Retrieve a row from LFACS_IPDSLAM_SERVICES_VIEW using Service Code, Modifier, Network Interface, and Service Category.
     * 
     * @param aREGION
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param aNETWORK_INTERFACE
     * @param SERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsIpdslamServicesRow
     * @exception Exception      : Exception.
     * 
     * @author Lira Galsim
     */
    public LfacsIpdslamServicesRow retrieveRowByServiceCodeAndModifierAndNifAndServiceCategory(
        String aREGION, 
        String aCLS_SERVICE_CODE,
        String aCLS_MODIFIER,
        String aNETWORK_INTERFACE,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {
    
        String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByServiceCodeAndModifierAndNifAndServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + "upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()
                + "upper(CLS_MODIFIER) = " + aCLS_MODIFIER.toUpperCase()
                + " and "
                + "upper(NETWORK_INTERFACE) = " + aNETWORK_INTERFACE.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = "
                + aSERVICE_CATEGORY.toUpperCase()
                + "and upper(USOC) = '*' ");
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(SERVICE_CATEGORY) = ? " +
        "and upper(CLS_SERVICE_CODE) = ? and upper(CLS_MODIFIER) = ? and upper(NETWORK_INTERFACE) = ? " +
        		"and upper(USOC) = '*'";
        		
        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;
        if (aCLS_MODIFIER != null) populatedValues++;
        if (aNETWORK_INTERFACE != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
       
    	queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
        queryValues[3] = aCLS_MODIFIER.toUpperCase();
        queryValues[4] = aNETWORK_INTERFACE.toUpperCase();
       
        LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    /**
     * Retrieve a row from LFACS_IPDSLAM_SERVICES_VIEW using Service Code, Network Interface, and Service Category.
     * 
     * @param aREGION
     * @param aCLS_SERVICE_CODE
     * @param aNETWORK_INTERFACE
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsIpdslamServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsIpdslamServicesRow retrieveRowByServiceCodeAndServiceCategory(
        String aREGION, 
        String aCLS_SERVICE_CODE,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByServiceCodeAndServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + "upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = "
                + aSERVICE_CATEGORY.toUpperCase()
                + "and upper(CLS_MODIFIER) = '*' " 
        		+ "and upper(USOC) = '*' " 
        		+ "and upper(NETWORK_INTERFACE) = '*' " );
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(SERVICE_CATEGORY) = ? " +
        		"and upper(CLS_SERVICE_CODE) = ? " +
        		"and upper(CLS_MODIFIER) = '*' " +
        		"and upper(USOC) = '*' " +
        		"and upper(NETWORK_INTERFACE) = '*'";

        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;

        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value

        queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
  
        LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }   
    
    /**
     * Retrieve a row from LFACS_IPDSLAM_SERVICES_VIEW using Network Interface and Service Category.
     * 
     * @param aREGION
     * @param aNETWORK_INTERFACE
     * @param aSERVICE_CATEGORY
     * @param aProperties
     * @param aLogger
     * @return LfacsIpdslamServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
    public LfacsIpdslamServicesRow retrieveRowByNifAndServiceCategory(
        String aREGION,
        String aNETWORK_INTERFACE,
        String aSERVICE_CATEGORY, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByNifAndServiceCategory()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        //added CLS_SERVICE_CODE is null and CLS_MODIFIER is null to make sure we wont get result 
        //with CLS_SERVICE_CODE and CLS_MODIFIER populated w/ other values
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and "
                + "upper(NETWORK_INTERFACE) = " + aNETWORK_INTERFACE.toUpperCase()
                + " and upper(SERVICE_CATEGORY) = "
                + aSERVICE_CATEGORY.toUpperCase()
                + " and upper(CLS_MODIFIER) = '*' " 
        		+ " and upper(USOC) = '*' " 
        		+" and upper(CLS_SERVICE_CODE) = '*' ");
        String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
        		"and upper(SERVICE_CATEGORY) = ? " +
        		"and upper(NETWORK_INTERFACE) = ? " +
        		"and upper(CLS_MODIFIER) = '*' " +
        		"and upper(USOC) = '*' " +
        		"and upper(CLS_SERVICE_CODE) = '*'";
        		
        int populatedValues = 2;
        if (aNETWORK_INTERFACE != null) populatedValues++;

        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value
        queryValues[2] = aNETWORK_INTERFACE.toUpperCase();
        
        LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    public LfacsIpdslamServicesRow retrieveRowByServiceCategory(
            String aREGION,
            String aSERVICE_CATEGORY, 
            Hashtable aProperties, 
            Logger aLogger) 
            throws SQLException {

            String myMethodNameName = "LfacsIpdslamServicesTable: retrieveRowByServiceCategory()";
            aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

            //added CLS_SERVICE_CODE is null and CLS_MODIFIER is null to make sure we wont get result 
            //with CLS_SERVICE_CODE and CLS_MODIFIER populated w/ other values
            aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                    + sqlSelect
                    + "where upper(REGION) = "
                    + aREGION.toUpperCase()                  
                    + " and upper(SERVICE_CATEGORY) = "
                    + aSERVICE_CATEGORY.toUpperCase()
                    + " and upper(CLS_MODIFIER) = '*' " 
            		+ " and upper(USOC) = '*' " 
            		+" and upper(CLS_SERVICE_CODE) = '*' "
            		+ "and upper(NETWORK_INTERFACE) = '*' ");
            String SQLstatement = sqlSelect + "where upper(REGION) = ? " +
            		"and upper(SERVICE_CATEGORY) = ? " +
            		"and upper(CLS_MODIFIER) = '*' " +
            		"and upper(USOC) = '*' " +
            		"and upper(NETWORK_INTERFACE) = '*' " +
            		"and upper(CLS_SERVICE_CODE) = '*'";

            int populatedValues = 2;
          
            String[] queryValues = new String[populatedValues];
            queryValues[0] = aREGION.toUpperCase();                     // Required value
            queryValues[1] = aSERVICE_CATEGORY.toUpperCase();           // Required value

            LfacsIpdslamServicesRow rowObj = new LfacsIpdslamServicesRow();
            DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
            
            // if row is not found, set to null
            if (rowObj.getREGION().length() == 0)
                rowObj = null;

            aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
            return rowObj;
        } 
}