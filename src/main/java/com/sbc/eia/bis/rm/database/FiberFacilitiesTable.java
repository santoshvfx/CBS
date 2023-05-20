//$Id: FiberFacilitiesTable.java,v 1.4 2008/03/14 14:28:33 jc1421 Exp $
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
//# 12/2007    Deepti Nayar			  Defect 80628: Address with Fiber and Segment info results in Null Pointer.
//# 12/28/2007 Rene Duka              Defect 81126: pVFN returns System Failure exception in the logs.
//# 12/31/2007 Rene Duka              Defect 80628: pVFN address with Fiber and Segment info results in Null Pointer.

package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
* @author jc1421
*/
public class FiberFacilitiesTable extends Table
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
    public FiberFacilitiesRow retrieveRow(
        String aTYPE, 
        String aMODEL, 
        String aLTS, 
        Hashtable aProperties,
        Logger aLogger) 
            throws 
                SQLException
    {
        String myMethodNameName = "FiberFacitlitiesTable:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: " + sqlSelect + "where "
                + (aTYPE == null ? "TYPE is null" : ("upper(TYPE) = " + aTYPE.toUpperCase())) + " and "
                + (aMODEL == null ? "MODEL is null" : ("upper(MODEL) = " + aMODEL.toUpperCase())) + " and "
                + (aLTS == null ? "LTS is null" : ("upper(LTS) = " + aLTS.toUpperCase())));
        String SQLstatement = sqlSelect + "where ";
    
        int populatedValues=0;
        if (aTYPE != null)
        {
            populatedValues++;
        }
        if (aMODEL != null)
        {
            populatedValues++;
        }
        if (aLTS != null)
        {
            populatedValues++;
        }
        
        String[] queryValues = new String[populatedValues];
        
        switch (populatedValues)
        {
            case 0: // all values are null
            {
                SQLstatement = SQLstatement + "TYPE is null and MODEL is null and LTS is null";
                break;
            }
            case 1: // one is populated, the other 2 values are  null
            {
                if (aTYPE != null)
                {
                    queryValues[0] = aTYPE.toUpperCase();
                    SQLstatement = SQLstatement + "upper(TYPE) = ? and MODEL is null and LTS is null";
                }
                else if (aMODEL != null)
                {
                    queryValues[0] = aMODEL.toUpperCase();
                    SQLstatement = SQLstatement + "upper(MODEL) = ? and LTS is null and TYPE is null";
                }
                else if (aLTS != null)
                {
                    queryValues[0] = aLTS.toUpperCase();
                    SQLstatement = SQLstatement + "upper(LTS) = ? and MODEL is null and TYPE is null";
                }
                break;
            }
            case 2: // two are populated, one is  null
            {
                if ((aTYPE != null) && (aMODEL != null))
                {           
                    queryValues[0] = aTYPE.toUpperCase();
                    queryValues[1] = aMODEL.toUpperCase();
                    SQLstatement = SQLstatement + "upper(TYPE) = ?  and upper(MODEL) = ? and LTS is null";
                }
                
                else if ((aTYPE != null) && (aLTS != null))
                {
                    queryValues[0] = aTYPE.toUpperCase();
                	queryValues[1] = aLTS.toUpperCase();
                	SQLstatement = SQLstatement + "upper(TYPE) = ? and upper(LTS) = ? and MODEL is null";
                }
                else if((aMODEL != null) && (aLTS != null))
            	{
                    queryValues[0] = aMODEL.toUpperCase();
                    queryValues[1] = aLTS.toUpperCase();
                    SQLstatement = SQLstatement + "upper(MODEL) = ? and upper(LTS) = ? and TYPE is null";
                }
                break;
            }
            
            case 3: // all values are populated
            {
                queryValues[0] = aTYPE.toUpperCase();
                queryValues[1] = aMODEL.toUpperCase();
                queryValues[2] = aLTS.toUpperCase();
                SQLstatement = SQLstatement + "upper(TYPE) = ? and upper(MODEL) = ? and upper(LTS) = ?";
            }
        }
    
        FiberFacilitiesRow rowObj = new FiberFacilitiesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
    
        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }
}
