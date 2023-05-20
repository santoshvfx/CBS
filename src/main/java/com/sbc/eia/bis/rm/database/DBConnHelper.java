//$Id: DBConnHelper.java,v 1.7 2008/09/29 19:31:48 vc7563 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/2007	    Jon Costa			  Creation.
//#	10/2007	    Deepti Nayar          Modified for LS6.
//# 11/01/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 08/29/2008	Souvik Paul			  CR 20389: Added 3 new columns to LFACS_SERVICES2 table.
//# 09/29/2008	Vickie Ng			  LS9: LFACS_SERVICES3 replaces LFACS_SERVICES2 table

package com.sbc.eia.bis.rm.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * @author jc1421 This class consolidates into common code the data base connection and single row retrieval.
 *  ** getDBConnection(Hashtable props, Logger logger) = requires jdbc values in props input 
 *  ** retrieveRow(String SQLstatement, String[] queryValues, Hashtable aProperties, Logger aLogger, DBRowBase rowObj)
 * 		   utilizes the redefined setRow(ResultSet rs, Logger aLogger) method from a subclass of
 *         DBRowBase used as the the last argument.
 */
public class DBConnHelper
{
    /**
     * @param props
     * @param logger
     * @return
     * @throws SQLException
     */
    public static DBConnection getDBConnection(Hashtable props, Logger aLogger) throws SQLException
    {
        String myMethodNameName = "DBConnHelper:getDBConnection()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        String dataSourceName = null;
        String jdbcUserId = null;
        String jdbcPassWord = null;
        String jdbcUrl = null;
        String jdbcDriver = null;
        String jdbcOption = null;

        try
        {
            dataSourceName = ((String) props.get("jdbcDATA_SOURCE_NAME")).trim();
            jdbcUserId = ((String) props.get("jdbcUSERID")).trim();
            jdbcPassWord = ((String) props.get("jdbcPASSWORD")).trim();
            jdbcUrl = ((String) props.get("jdbcURL")).trim();
            jdbcDriver = ((String) props.get("jdbcDRIVER")).trim();
            jdbcOption = ((String) props.get("jdbcUSE_CONNECTION_POOL")).trim();
        }
        catch (NullPointerException e)
        {
            throw new SQLException("Get JDBC properties failed. JDBC required tags are not defined in properties file."
                    + e.getMessage());
        }

        aLogger.log(LogEventId.INFO_LEVEL_2, "<dataSourceName>" + dataSourceName + "<jdbcUserId>" + jdbcUserId
                + "<jdbcUrl>" + jdbcUrl + "<jdbcDriver>" + jdbcDriver);

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return new DBConnection(dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, aLogger);
    }

    /**
     * @param SQLstatement
     * @param queryValues
     * @param aProperties
     * @param aLogger
     * @param rowObj
     * @throws SQLException
     */
    public static void retrieveRow(String SQLstatement, String[] queryValues, Hashtable aProperties, Logger aLogger,
            DBRowBase rowObj) throws SQLException
    {
        String myMethodNameName = "DBConnHelper:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        ResultSet rs = null;
        DBConnection aDBConn = null;
        PreparedStatement ps = null;

        try
        {
            aDBConn = DBConnHelper.getDBConnection(aProperties, aLogger);
            ps = aDBConn.getConnection().prepareStatement(SQLstatement);
          
            for (int i = 0; i < queryValues.length; i++)
                ps.setString(i + 1, queryValues[i]);

            rs = ps.executeQuery();     
            rowObj.setRow(rs, aLogger);
            
        }
        catch (Exception e)
        {
            aLogger.log(LogEventId.ERROR, "Error message [" + e.getMessage() + "]");
            throw new SQLException("No data found");
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            }
            catch (Exception e)
            {}
            try
            {
                aDBConn.disconnect();
            }
            catch (Exception e)
            {}
            aDBConn = null;
        }
        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
    }

/**
 * @param SQLstatement
 * @param queryValues
 * @param aProperties
 * @param aLogger
 * @param rowObj
 * @throws SQLException
 */
    public static void retrieveRows(String SQLstatement, String[] queryValues, Hashtable aProperties, Logger aLogger,
            ArrayList aRows) throws SQLException
    {
        String myMethodNameName = "DBConnHelper:retrieveRows()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        ResultSet rs = null;
        DBConnection aDBConn = null;
        PreparedStatement ps = null;
        LfacsServicesRow[] resultRows=null;
        
		try {
            aDBConn = DBConnHelper.getDBConnection(aProperties, aLogger);
            ps = aDBConn.getConnection().prepareStatement(SQLstatement);

            for (int i = 0; i < queryValues.length; i++)
                ps.setString(i + 1, queryValues[i]);

            rs = ps.executeQuery();
            while (rs.next()) {
            	aRows.add(
            			new LfacsServicesRow(
            					rs.getString(1),
								Boolean.valueOf(rs.getString(2)),
            		            rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getString(7),
								Boolean.valueOf(rs.getString(8)),
								rs.getString(9),
								rs.getString(10)));		
            }
		} 
        catch (Exception e) {
            aLogger.log(LogEventId.ERROR, "Error message [" + e.getMessage() + "]");
            throw new SQLException("No data found");
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            }
            catch (Exception e)
            {}
            try {
                aDBConn.disconnect();
            }
            catch (Exception e)
            {}
            aDBConn = null;
        }
        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
    }
}
     