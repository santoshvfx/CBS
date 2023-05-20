//$Id:$
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
//# 11/2005	   Jon Costa			  Creation
//# 05/2006	   Jon Costa			  Changes for LS3.

package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

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
public class TopListenerSoacLinkTable extends Table
{
	/**
	 * Constructor for TopListenerSoacLinkTable.
	 */
	public TopListenerSoacLinkTable()
	{
		super();
	}

	public TopListenerSoacLinkRow retrieveRow(String aHostname, String aEntity, Hashtable aProperties, Logger aLogger)
		throws SQLException
	{
		aLogger.log(LogEventId.INFO_LEVEL_1, "TopListenerSoacLinkTable:retrieveRow()");

		ResultSet rs = null;
		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		TopListenerSoacLinkRow resultRow = null;

		String SQLstatement =
			"select HOST_NAME, ENTITY, TELCO_APPLDATA, CVOIP_APPLDATA, ORIGINATOR, REGION, LTERM "
				+ "from TOPLISTENER_SOAC_LINK "
				+ "where upper(HOST_NAME) = ? AND upper(ENTITY) = ?";

		try
		{
			aDBConn = getDBConnection(aProperties, aLogger);
			ps = aDBConn.getConnection().prepareStatement(SQLstatement);
			ps.setString(1, aHostname.toUpperCase());
			ps.setString(2, aEntity.toUpperCase());
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
				resultRow = new TopListenerSoacLinkRow();
				resultRow.setRow(rs);
			}
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

	/**
	 * Method getDBConnection.
	 * @param props
	 * @param logger
	 * @return DBConnection
	 * @throws SQLException
	 */
	private static DBConnection getDBConnection(Hashtable props, Logger logger) throws SQLException
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
				"Get JDBC properties failed. JDBC required tags are not defined/found in properties file." + e.getMessage());
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
