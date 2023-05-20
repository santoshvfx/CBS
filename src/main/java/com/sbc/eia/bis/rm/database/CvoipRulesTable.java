//$Id: CvoipRulesTable.java,v 1.3 2008/07/29 16:58:18 ds4987 Exp $
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
//# 05/2006	   Jon Costa			  Creation.
//# 02/2007    Deepti                 DR170206:Added retry logic to database connection.
//# 07/03/2008 Doris Sunga			  CR 19539:Added code to handle system failure

package com.sbc.eia.bis.rm.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class CvoipRulesTable extends Table {
	/**
	 * Constructor
	 */
	public CvoipRulesTable() {
		super();
	}

	/**
	 * Method retrieveByOrder.
	 * @param aOrder
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 * @throws Exception
	 */
	public CvoipRulesRow[] retrieveByOrder(
		String aORDER_ACTION_TYPE,
		String aORDER_ACTION_SUBTYPE,
		String aORDER_EXISTS,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException, Exception {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"CvoipRulesTable:retrieveByOrder()");

		ResultSet rs = null;
		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		CvoipRulesRow[] resultRows = null;

		// Note: Must have aORDER_ACTION_TYPE (Provide, Cease, Change) and aORDER_EXISTS (TRUE/FALSE)
		//		 the aORDER_ACTION_SUBTYPE may be null since it doesn't always apply.
		//
		String SQLstatement =
			"select "
				+ "ORDER_ACTION_TYPE, "
				+ "ORDER_ACTION_SUBTYPE, "
				+ "ACTION_IND, "
				+ "ACTIVITY_IND, "
				+ "ORDER_EXISTS, "
				+ "TN_EXISTS, "
				+ "SOAC_FUNC_TYPE, "
				+ "SOAC_ACTION_IND, "
				+ "HIPCS_SWITCH_UPD, "
				+ "SWITCH_ACTION_IND, "
				+ "ORDER_TABLE_ACTION, "
				+ "UPDATE_DATE "
				+ "from CVOIP_RULES "
				+ "where ORDER_ACTION_TYPE = ? "
				+ "AND upper(ORDER_EXISTS) = ? "
				+ "AND ORDER_ACTION_SUBTYPE "
				+ (aORDER_ACTION_SUBTYPE == null ? "is null" : " = ?");

		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ "select "
				+ "ORDER_ACTION_TYPE, "
				+ "ORDER_ACTION_SUBTYPE, "
				+ "ACTION_IND, "
				+ "ACTIVITY_IND, "
				+ "ORDER_EXISTS, "
				+ "TN_EXISTS, "
				+ "SOAC_FUNC_TYPE, "
				+ "SOAC_ACTION_IND, "
				+ "HIPCS_SWITCH_UPD, "
				+ "SWITCH_ACTION_IND, "
				+ "ORDER_TABLE_ACTION, "
				+ "UPDATE_DATE "
				+ "from CVOIP_RULES "
				+ "where ORDER_ACTION_TYPE = "
				+ aORDER_ACTION_TYPE
				+ " AND upper(ORDER_EXISTS) = "
				+ aORDER_EXISTS.toUpperCase()
				+ " AND ORDER_ACTION_SUBTYPE "
				+ (aORDER_ACTION_SUBTYPE == null
					? "is null"
					: " = " + aORDER_ACTION_SUBTYPE));
		int reTryCnt = 0;
		
		aLogger.log( LogEventId.REMOTE_CALL, (String)aProperties.get( "jdbcURL" ),
                (String)aProperties.get( "jdbcUSERID" ),
                (String)aProperties.get( "jdbcUSERID" ),
                "CvoipRulesTable::retrieveByOrder()" );
		
		while (true) {
			try {

				aDBConn = getDBConnection(aProperties, aLogger);
				ps = aDBConn.getConnection().prepareStatement(SQLstatement);
				ps.setString(1, aORDER_ACTION_TYPE);
				ps.setString(2, aORDER_EXISTS.toUpperCase());

				if (aORDER_ACTION_SUBTYPE != null) {
					ps.setString(3, aORDER_ACTION_SUBTYPE);
				}
				rs = ps.executeQuery();

				ArrayList rows = new ArrayList();

				while (rs.next()) {
					rows.add(
						new CvoipRulesRow(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12)));
				}

				if (rows.size() > 0) {
					resultRows = new CvoipRulesRow[rows.size()];

					for (int i = 0; i < rows.size(); i++) {
						resultRows[i] = (CvoipRulesRow) rows.get(i);
						aLogger.log(
							LogEventId.DEBUG_LEVEL_1,
							"SQLResult: "
								+ resultRows[i].getORDER_ACTION_TYPE()
								+ "|"
								+ resultRows[i].getORDER_ACTION_SUBTYPE()
								+ "|"
								+ resultRows[i].getACTION_IND()
								+ "|"
								+ resultRows[i].getACTIVITY_IND()
								+ "|"
								+ resultRows[i].getORDER_EXISTS().toString()
								+ "|"
								+ resultRows[i].getTN_EXISTS().toString()
								+ "|"
								+ resultRows[i].getSOAC_FUNC_TYPE()
								+ "|"
								+ resultRows[i].getSOAC_ACTION_IND()
								+ "|"
								+ resultRows[i].getHIPCS_SWITCH_UPD().toString()
								+ "|"
								+ resultRows[i].getSWITCH_ACTION_IND()
								+ "|"
								+ resultRows[i].getORDER_TABLE_ACTION()
								+ "|"
								+ resultRows[i].getUPDATE_DATE()
								+ "|");
					}
				} else
					throw new Exception("No rules data found");
			} catch (StaleConnectionException e) {
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
			} catch (SQLException e) {
				aLogger.log(
						LogEventId.ERROR,
						"Error message [" + e.getMessage() + " : CVOIP_RULES ]");				
			    throw e;		
			} catch (Exception e) {
				aLogger.log(
					LogEventId.ERROR,
					"Error message [" + e.getMessage() + " : CVOIP_RULES ]");
				throw new Exception(
					"No rules data found for search criteria: ORDER_ACTION_TYPE: "
						+ aORDER_ACTION_TYPE
						+ " ORDER_ACTION_SUBTYPE : "
						+ aORDER_ACTION_SUBTYPE
						+ " ORDER_EXISTS: "
						+ aORDER_EXISTS);
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
				} catch (Exception e) {
				}
				try {
					aDBConn.disconnect();
				} catch (Exception e) {
				}
				aDBConn = null;
				
				aLogger.log( LogEventId.REMOTE_RETURN, (String)aProperties.get( "jdbcURL" ),
                        (String)aProperties.get( "jdbcUSERID" ),
                        (String)aProperties.get( "jdbcUSERID" ),
						"CvoipRulesTable::retrieveByOrder()"  );					
			}

			return resultRows;
		}
	}

	/**
	 * Method getDBConnection.
	 * @param props
	 * @param logger
	 * @return DBConnection
	 * @throws SQLException
	 */
	private static DBConnection getDBConnection(Hashtable props, Logger logger)
		throws SQLException {
		String dataSourceName = null;
		String jdbcUserId = null;
		String jdbcPassWord = null;
		String jdbcUrl = null;
		String jdbcDriver = null;
		String jdbcOption = null;

		try {
			dataSourceName =
				((String) props.get("jdbcDATA_SOURCE_NAME")).trim();
			jdbcUserId = ((String) props.get("jdbcUSERID")).trim();
			jdbcPassWord = ((String) props.get("jdbcPASSWORD")).trim();
			jdbcUrl = ((String) props.get("jdbcURL")).trim();
			jdbcDriver = ((String) props.get("jdbcDRIVER")).trim();
			jdbcOption = ((String) props.get("jdbcUSE_CONNECTION_POOL")).trim();
		} catch (NullPointerException e) {
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined/found in properties file."
					+ e.getMessage());
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
		return new DBConnection(
			dataSourceName,
			jdbcUserId,
			jdbcPassWord,
			jdbcUrl,
			jdbcDriver,
			jdbcOption,
			logger);
	}
}
