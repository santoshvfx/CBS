//$Id: SoacWireCenterTable.java,v 1.2 2008/04/24 18:31:09 sc8468 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/2005	   Jon Costa			  Creation
//# 01/17/2005 Sriram Chevuturu		  Changed the method getDBConnection from private to public to be used in SOACResponseHelper.

package com.sbc.eia.bis.rm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SoacWireCenterTable extends Table
{
	/**
	 * Constructor for SoacWireCenterTable.
	 */
	public SoacWireCenterTable()
	{
		super();
	}

	public SoacWireCenterRow retrieveRow(String aNpaNxx, Hashtable aProperties, Logger aLogger) throws SQLException
	{
		aLogger.log(LogEventId.INFO_LEVEL_1, "SoacWireCenterTable:retrieveRow()");
		String SQLstatement =
			"select TYPE, HOST_NAME, CONTROLLER, ENTITY, TWO_LETTER_WC, NPANXX, CLLI8 from SOAC_WIRE_CENTER "
				+ "where NPANXX = ?";
		String[] queryValues = { aNpaNxx };
		return retrieveRow(SQLstatement, queryValues, aProperties, aLogger);
	}

	public SoacWireCenterRow retrieveRow(String aNpaNxx, String aCLLI8, Hashtable aProperties, Logger aLogger)
		throws SQLException
	{
		aLogger.log(LogEventId.INFO_LEVEL_1, "SoacWireCenterTable:retrieveRow()");
		String SQLstatement =
			"select TYPE, HOST_NAME, CONTROLLER, ENTITY, TWO_LETTER_WC, NPANXX, CLLI8 from SOAC_WIRE_CENTER "
				+ "where NPANXX = ? AND upper(CLLI8) = ?";
		String[] queryValues = { aNpaNxx, aCLLI8.toUpperCase()};

		return retrieveRow(SQLstatement, queryValues, aProperties, aLogger);
	}

	public SoacWireCenterRow retrieveRow(String SQLstatement, String[] queryValues, Hashtable aProperties, Logger aLogger)
		throws SQLException
	{
		aLogger.log(LogEventId.INFO_LEVEL_1, "SoacWireCenterTable:retrieveRow()");
		
		ResultSet rs = null;
		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		SoacWireCenterRow resultRow = null;
		int reTryCnt = 0;

		while (true) {		
			try
			{
				aDBConn = getDBConnection(aProperties, aLogger);
				ps = aDBConn.getConnection().prepareStatement(SQLstatement);
	
				for (int i = 0; i < queryValues.length; i++)
					ps.setString(i + 1, queryValues[i]);
	
				rs = ps.executeQuery();
	
				if (rs.next())
				{
					aLogger.log(
						LogEventId.DEBUG_LEVEL_2,
						"SQLResult: |"
							+ rs.getString(1)
							+ "|"
							+ rs.getString(2)
							+ "|"
							+ rs.getString(3)
							+ "|"
							+ rs.getString(4)
							+ "|"
							+ rs.getString(5)
							+ "|"
							+ rs.getString(6)
							+ "|"
							+ rs.getString(7)
							+ "|");
					resultRow = new SoacWireCenterRow();
					resultRow.setRow(rs);
				}
			}
			catch (StaleConnectionException e) 
			{

				aLogger.log(
						LogEventId.DEBUG_LEVEL_1,
						"SQLResult: <no data found>");

				aLogger.log(
						LogEventId.ERROR,
						"Error message [" + e.getMessage() + "]");
				
				//retry only once
	
				if (reTryCnt == 0) {
					reTryCnt++;
					aDBConn = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw e;
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
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
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
			return resultRow;
		}
	}

	/**
	 * Method getDBConnection.
	 * @param props
	 * @param logger
	 * @return DBConnection
	 * @throws SQLException
	 */
	public static DBConnection getDBConnection(Hashtable props, Logger logger) throws SQLException
	{
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
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined in properties file." + e.getMessage());
		}

		logger.log(
			LogEventId.INFO_LEVEL_2,
			"<dataSourceName>"
				+ dataSourceName
				+ "<jdbcUserId>"
				+ jdbcUserId
				+ "<jdbcUrl>"
				+ jdbcUrl
				+ "<jdbcDriver>"
				+ jdbcDriver);
		return new DBConnection(dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, logger);
	}
}
