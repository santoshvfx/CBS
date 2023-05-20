//$Id: LfacsServicesTable.java,v 1.10 2008/11/20 16:11:56 jc1421 Exp $
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
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/2007	   Jon Costa			  Creation.
//# 10/2007	   Deepti Nayar			  Modified for LS6.
//# 11/13/2007 Rene Duka              RM 410745: Project Lightspeed - Release 6.0.
//# 11/29/2007 Rene Duka              Defect 79272: Resolve SQLException.
//# 02/2008	   Jon Costa			  LS7: Changed table name to: LFACS_SERVICES2
//# 03/27/2008 Rene Duka              Defect 88721: Handle Dry Loop in the East region.
//# 08/29/2008 Souvik Paul			  CR 20389: Added 3 new columns to LFACS_SERVICES2 table
//# 09/17/2008 Vickie Ng			  LS9: removed ORDER_NUMBER_MATCH and EXCEPTION_PROCEED_INDICATOR columns, 
//#                                   rename LFACS_SERVICES2 to LFACS_SERVICES3 table
//# 11/19/2008 Jon Costa              PR23410327: CLS_SERVICE_CODE and CLS_MODIFIER is null to retrieveRowByUsocAndServiceType()

/*****************************************************************************
//NOTE: DO NOT RTAG THIS FILE TO ANY RELEASE EALIER THAN LIGHTSPEED 7 (RM21.0)
******************************************************************************/

package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 */
public class LfacsServicesTable extends Table
{    
	private static String sqlSelect = "select REGION, LS_CONDITIONED, CLS_SERVICE_CODE, "
    + "CLS_MODIFIER, USOC, SERVICE_TYPE, SERVICE_NAME, SERVICE_CONFLICT, "
    + "EXCEPTION_CLIENTS, EXCEPTION_CONFLICT_INDICATOR from LFACS_SERVICES3 ";  

    /**
     * Retrieve a row from LFACS_SERVICES table using Service Code and Modifier.
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param aProperties
     * @param aLogger
     * @return LfacsServicesRow
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    public LfacsServicesRow retrieveRowByServiceCodeAndModifier(
        String aREGION, 
        String aLS_CONDITIONED, 
        String aCLS_SERVICE_CODE,
        String aCLS_MODIFIER, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {
    
        String myMethodNameName = "LfacsServicesTable: retrieveRowByServiceCodeAndModifier()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toString().toUpperCase()
                + " and "
                + (aCLS_SERVICE_CODE == null ? "CLS_SERVICE_CODE is null"
                        : ("upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()))
                + " and "
                + (aCLS_MODIFIER == null ? "CLS_MODIFIER is null"
                        : ("upper(CLS_MODIFIER) = " + aCLS_MODIFIER.toUpperCase())));
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(LS_CONDITIONED) = ? and ";
    
        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;
        if (aCLS_MODIFIER != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toString().toUpperCase();  // Required value
    
        switch(populatedValues)
        {
            case 2: // both optional values are null
            {
                SQLstatement = SQLstatement + "CLS_SERVICE_CODE is null and CLS_MODIFIER is null";
                break;
            }
            case 3: // one is populated, the other null
            {
                if (aCLS_MODIFIER != null)
                {
                    queryValues[2] = aCLS_MODIFIER.toUpperCase();
                    SQLstatement = SQLstatement + "upper(CLS_MODIFIER) = ? and CLS_SERVICE_CODE is null";
                }
                else
                {
                    queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and CLS_MODIFIER is null";
                }
                break;
            }
            case 4: // both optional values are populated
            {
                queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                queryValues[3] = aCLS_MODIFIER.toUpperCase();
                SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and upper(CLS_MODIFIER) = ?";
            }
        }
        LfacsServicesRow rowObj = new LfacsServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);

        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    /**
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param aSERVICE_TYPE
     * @param aProperties
     * @param aLogger
     * @return
     * @throws SQLException
     */
    public LfacsServicesRow retrieveRowByServiceCodeAndServiceType(
        String aREGION, 
        String aLS_CONDITIONED, 
        String aCLS_SERVICE_CODE,
        String aSERVICE_TYPE, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws SQLException {

        String myMethodNameName = "LfacsServicesTable: retrieveRowByServiceCodeAndServiceType()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
                + sqlSelect
                + "where upper(REGION) = "
                + aREGION.toUpperCase()
                + " and upper(LS_CONDITIONED) = "
                + aLS_CONDITIONED.toString().toUpperCase()
                + " and "
                + (aCLS_SERVICE_CODE == null ? "CLS_SERVICE_CODE is null"
                        : ("upper(CLS_SERVICE_CODE) = " + aCLS_SERVICE_CODE.toUpperCase()))
                + " and "
                + (aSERVICE_TYPE == null ? "SERVICE_TYPE is null"
                        : ("upper(SERVICE_TYPE) = " + aSERVICE_TYPE.toUpperCase())));
        String SQLstatement = sqlSelect + "where upper(REGION) = ? and upper(LS_CONDITIONED) = ? and ";

        int populatedValues = 2;
        if (aCLS_SERVICE_CODE != null) populatedValues++;
        if (aSERVICE_TYPE != null) populatedValues++;

        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toString().toUpperCase();  // Required value

        switch(populatedValues)
        {
            case 2: // both optional values are null
            {
                SQLstatement = SQLstatement + "CLS_SERVICE_CODE is null and SERVICE_TYPE is null";
                break;
            }
            case 3: // one is populated, the other null
            {
                if (aSERVICE_TYPE != null)
                {
                    queryValues[2] = aSERVICE_TYPE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(SERVICE_TYPE) = ? and CLS_SERVICE_CODE is null";
                }
                else
                {
                    queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and SERVICE_TYPE is null";
                }
                break;
            }
            case 4: // both optional values are populated
            {
                queryValues[2] = aCLS_SERVICE_CODE.toUpperCase();
                queryValues[3] = aSERVICE_TYPE.toUpperCase();
                SQLstatement = SQLstatement + "upper(CLS_SERVICE_CODE) = ? and upper(SERVICE_TYPE) = ?";
            }
        }

        LfacsServicesRow rowObj = new LfacsServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
    
    /**
     * Retrieve a row from LFACS_SERVICES table using Usoc and Service Type.
     * 
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aUSOC
     * @param aSERVICE_TYPE
     * @param aProperties
     * @param aLogger
     * @return LfacsServicesRow
     * @exception Exception      : Exception.
     * 
     * @author Rene Duka
     */
    public LfacsServicesRow retrieveRowByUsocAndServiceType(
        String aREGION,
        String aLS_CONDITIONED,
        String aUSOC,
        String aSERVICE_TYPE, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {        
        String myMethodNameName = "LfacsServicesTable: retrieveRowByUsocAndServiceType()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQL parameters:  aREGION["
                + aREGION
                + "] aLS_CONDITIONED["
                + aLS_CONDITIONED
                + "] aUSOC["
                + aUSOC
                + "]  aSERVICE_TYPE["
                + aSERVICE_TYPE
                + "]");
        String SQLstatement = sqlSelect + 
            "where upper(REGION) = ? and upper(LS_CONDITIONED) = ? and CLS_SERVICE_CODE is null and CLS_MODIFIER is null and ";
    
        int populatedValues = 2;
        if (aUSOC != null) populatedValues++;
        if (aSERVICE_TYPE != null) populatedValues++;
    
        String[] queryValues = new String[populatedValues];
        queryValues[0] = aREGION.toUpperCase();                     // Required value
        queryValues[1] = aLS_CONDITIONED.toString().toUpperCase();  // Required value
    
        switch(populatedValues)
        {
            case 2: // both optional values are null
            {
                SQLstatement = SQLstatement + "USOC is null and SERVICE_TYPE is null";
                break;
            }
            case 3: // one is populated, the other null
            {
                if (aSERVICE_TYPE != null)
                {
                    queryValues[2] = aSERVICE_TYPE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(SERVICE_TYPE) = ? and USOC is null";
                }
                else
                {
                    queryValues[2] = aUSOC.toUpperCase();
                    SQLstatement = SQLstatement + "upper(USOC) = ? and SERVICE_TYPE is null";
                }
                break;
            }
            case 4: // both optional values are populated
            {
                queryValues[2] = aSERVICE_TYPE.toUpperCase();
                queryValues[3] = aUSOC.toUpperCase();
                SQLstatement = SQLstatement + "upper(SERVICE_TYPE) = ? and upper(USOC) = ?";
            }
        }
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: " + SQLstatement );
        LfacsServicesRow rowObj = new LfacsServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getREGION().length() == 0)
            rowObj = null;

        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
