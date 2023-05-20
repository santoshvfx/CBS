//$Id: GraniteFiberServicesTable.java,v 1.7 2009/05/21 01:27:24 lg4542 Exp $
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
//# 02/05/2009      Lira Galsim           LS10: Modified to retrieve row from GRANITE_FIBER_SERVICES table by NTI, ORDER_MATCH, ADSL, VDSL, GPON, VOICE, and VLAN.

package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : GraniteFiberServicesTable
 * Description: Class file for GRANITE_FIBER_SERVICES table queries.
 */
public class GraniteFiberServicesTable extends Table
{
	private static String sqlSelect = "select NTI, ORDER_MATCH, ADSL, VDSL, GPON, VOICE, VLAN, SERVICE_NAME, CONFLICT_IND from GRANITE_FIBER_SERVICES "; 
    /**
     * Retrieve a row from GRANITE_FIBER_SERVICES table using NTI, ORDER_MATCH, ADSL, VDSL, GPON, VOICE, and VLAN.
     * 
     * @param aNTI
     * @param aORDER_MATCH
     * @param aADSL
     * @param aVDSL
     * @param aGPON
     * @param aVOICE
     * @param aVLAN
     * @param aProperties
     * @param aLogger
     * @return GraniteFiberServicesRow
     * @throws SQLException
     * 
     * @author Lira Galsim
     */
	public GraniteFiberServicesRow retrieveRow(
        String aNTI, 
        String aORDER_MATCH,
        String aADSL, 
        String aVDSL, 
        String aGPON, 
        String aVOICE, 
        String aVLAN, 
        Hashtable aProperties, 
        Logger aLogger) 
        throws 
            SQLException 
        {
    
        String myMethodNameName = "GraniteFiberServicesTable: retrieveRow()";
        aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
    
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: "
						      + sqlSelect
        					  + "where upper(NTI) = " + aNTI.toUpperCase()
						      + " and "
						      + "upper(ORDER_MATCH) = " + aORDER_MATCH.toUpperCase()
						      + " and "
						      + "upper(ADSL) = " + aADSL.toUpperCase()
						      + " and "
						      + "upper(VDSL) = " + aVDSL.toUpperCase()
						      + " and "
						      + "upper(GPON) = " + aGPON.toUpperCase()
						      + " and "
						      + "upper(VOICE) = " + aVOICE.toUpperCase()
						      + " and "
						      + "upper(VLAN) = " + aVLAN.toUpperCase());
        String SQLstatement = sqlSelect + "where upper(NTI) = ? and upper(ORDER_MATCH) = ? and upper(ADSL) = ? and upper(VDSL) = ? "
        								+ "and upper(GPON) = ? and upper(VOICE) = ? and upper(VLAN) = ?";
        
        // required values
        String[] queryValues = new String[7];
        queryValues[0] = aNTI.toUpperCase();                     
		queryValues[1] = aORDER_MATCH.toUpperCase();
    	queryValues[2] = aADSL.toUpperCase();
    	queryValues[3] = aVDSL.toUpperCase();
    	queryValues[4] = aGPON.toUpperCase();
    	queryValues[5] = aVOICE.toUpperCase();
    	queryValues[6] = aVLAN.toUpperCase();									               

        GraniteFiberServicesRow rowObj = new GraniteFiberServicesRow();
        DBConnHelper.retrieveRow(SQLstatement, queryValues, aProperties, aLogger, rowObj);
        
        // if row is not found, set to null
        if (rowObj.getNTI().length() == 0)
            rowObj = null;
        
        aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }        
}