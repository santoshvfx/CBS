//$Id: CvoipOrderTable.java,v 1.3 2008/07/29 16:54:59 ds4987 Exp $
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
//# 05/2006    Jon Costa              Creation.
//# 06/2006    Rene Duka              Added updateRow, insertByOrderTN and deleteByOrderTN.
//# 06/2006    Rene Duka              Added retrieveByOrderTN and updateByOrderTN.
//# 02/2007    Deepti                 DR170206:Added retry logic for database connection.
//# 07/03/2008 Doris Sunga			  CR 19539: Added code to handle system failure
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

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CvoipOrderTable extends Table {
	private static String sqlSelect =
		"select SOAC_SO_NBR, TN, UPDATE_DATE from CVOIP_ORDER ";
	private static String sqlInsert = "insert into CVOIP_ORDER ";
	private static String sqlDelete = "delete from CVOIP_ORDER ";
	private static String sqlUpdate = "update CVOIP_ORDER ";

	/**
	 * Constructor
	 */
	public CvoipOrderTable() {
		super();
	}

	/**
	 * Method retrieveByOrder.
	 * @param aOrder
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public CvoipOrderRow[] retrieveByOrder(
		String aOrder,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "CvoipOrderTable:retrieveByOrder");
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlSelect
				+ "where upper(SOAC_SO_NBR) = "
				+ aOrder.toUpperCase());
		String[] aKeyValue = new String[1];
		aKeyValue[0] = aOrder.toUpperCase();
		return retrieveRows(
			sqlSelect + "where upper(SOAC_SO_NBR) = ?",
			aKeyValue,
			aProperties,
			aLogger);
	}

	/**
	 * Method retrieveByTN.
	 * @param aTN
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public CvoipOrderRow[] retrieveByTN(
		String aTN,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "CvoipOrderTable:retrieveByTN()");
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: " + sqlSelect + "where TN = " + aTN);
		String[] aKeyValue = new String[1];
		aKeyValue[0] = aTN;
		return retrieveRows(
			sqlSelect + "where TN = ?",
			aKeyValue,
			aProperties,
			aLogger);
	}

	/**
	 * Method retrieveByOrderTN.
	 * @param aOrder
	 * @param aTN
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public CvoipOrderRow[] retrieveByOrderTN(
		String aOrder,
		String aTN,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"CvoipOrderTable:retrieveByOrderTN()");
		String[] aKeyValue = new String[2];
		aKeyValue[0] = aOrder;
		aKeyValue[1] = aTN;
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlInsert
				+ "values("
				+ aOrder
				+ ", "
				+ aTN
				+ ", "
				+ "SYSDATE)");
		return retrieveRows(
			sqlSelect + "where SOAC_SO_NBR = ? and TN = ?",
			aKeyValue,
			aProperties,
			aLogger);
	}

	/**
	 * Method insertByOrderTN.
	 * @param aOrder
	 * @param aTN
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public int insertByOrderTN(
		String aOrder,
		String aTN,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"CvoipOrderTable:insertByOrderTN()");
		String[] aKeyValue = new String[2];
		aKeyValue[0] = aOrder;
		aKeyValue[1] = aTN;
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlInsert
				+ "values("
				+ aOrder
				+ ", "
				+ aTN
				+ ", "
				+ "SYSDATE)");
		return updateRow(
			sqlInsert + "values(?, ?, SYSDATE)",
			aKeyValue,
			aProperties,
			aLogger);
	}

	/**
	 * Method updateByOrderTN.
	 * @param aOrder
	 * @param aTN
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public int updateByOrderTN(
		String aOrder,
		String aTN,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"CvoipOrderTable:updateByOrderTN()");
		String[] aKeyValue = null;
		String aSQLStatement =
			sqlUpdate
				+ "set UPDATE_DATE = SYSDATE where SOAC_SO_NBR = '"
				+ aOrder
				+ "' and TN = '"
				+ aTN
				+ "'";
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlInsert
				+ "values("
				+ aOrder
				+ ", "
				+ aTN
				+ ", "
				+ "SYSDATE)");
		return updateRow(aSQLStatement, aKeyValue, aProperties, aLogger);
	}

	/**
	 * Method deleteByOrderTN.
	 * @param aOrder
	 * @param aTN
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public int deleteByOrderTN(
		String aOrder,
		String aTN,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"CvoipOrderTable:deleteByOrderTN()");
		String[] aKeyValue = null;
		String aSQLStatement =
			sqlDelete
				+ "where SOAC_SO_NBR = '"
				+ aOrder
				+ "' and TN = '"
				+ aTN
				+ "'";
		aLogger.log(LogEventId.DEBUG_LEVEL_1, aSQLStatement);
		return updateRow(aSQLStatement, aKeyValue, aProperties, aLogger);
	}

	/**
	 * Method retrieveRows.
	 * @param SQLstatement
	 * @param aKeyValue
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public CvoipOrderRow[] retrieveRows(
		String SQLstatement,
		String[] aKeyValue,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "CvoipOrderTable:retrieveRows()");

		ResultSet rs = null;
		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		CvoipOrderRow[] resultRows = null;
		int reTryCnt = 0;
		
		aLogger.log( LogEventId.REMOTE_CALL, (String)aProperties.get( "jdbcURL" ),
                (String)aProperties.get( "jdbcUSERID" ),
                (String)aProperties.get( "jdbcUSERID" ),
                "CvoipOrderTable::retrieveRows()" );
		
		while (true) {
			try {
				aDBConn = getDBConnection(aProperties, aLogger);
				ps = aDBConn.getConnection().prepareStatement(SQLstatement);
				//ps.setString(1, aKeyValue);
				for (int i = 0; i < aKeyValue.length; i++) {
					ps.setString(i + 1, aKeyValue[i]);
				}
				rs = ps.executeQuery();

				ArrayList rows = new ArrayList();

				while (rs.next()) {
					rows.add(
						new CvoipOrderRow(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3)));
				}

				if (rows.size() > 0) {
					resultRows = new CvoipOrderRow[rows.size()];

					for (int i = 0; i < rows.size(); i++) {
						resultRows[i] = (CvoipOrderRow) rows.get(i);
						aLogger.log(
							LogEventId.DEBUG_LEVEL_1,
							"SQLResult: "
								+ resultRows[i].getSOAC_SO_NBR()
								+ "|"
								+ resultRows[i].getTN()
								+ "|"
								+ resultRows[i].getUPDATE_DATE()
								+ "|");
					}
				} else {
					aLogger.log(LogEventId.DEBUG_LEVEL_1, 
						"No data found from CvoipOrderTable ignored, okay to proceed. ");
				}				
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
						"Error message [" + e.getMessage() + " : CVOIP_ORDER ]");				
			    throw e;
			} catch (Exception e) {
				aLogger.log(
					LogEventId.ERROR,
					"Error message [" + e.getMessage() + "]");
				throw new SQLException("No data found");
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
                        "CvoipOrderTable::retrieveRows()" ); 				
			}

			return resultRows;
		}
	}

	/**
	 * Method updateRow.
	 * @param SQLstatement
	 * @param aKeyValue
	 * @param aProperties
	 * @param aLogger
	 * @return CvoipOrderRow[]
	 * @throws SQLException
	 */
	public int updateRow(
		String SQLstatement,
		String[] aKeyValue,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "CvoipOrderTable:updateRow()");

		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		int reTryCnt = 0;
		
		aLogger.log( LogEventId.REMOTE_CALL, (String)aProperties.get( "jdbcURL" ),
                (String)aProperties.get( "jdbcUSERID" ),
                (String)aProperties.get( "jdbcUSERID" ),
                "CvoipOrderTable::updateRow()" );
		
		while (true) {
			try {
				aDBConn = getDBConnection(aProperties, aLogger);
				ps = aDBConn.getConnection().prepareStatement(SQLstatement);
				if (aKeyValue != null) {
					for (int i = 0; i < aKeyValue.length; i++) {
						ps.setString(i + 1, aKeyValue[i]);
					}
				}
				returnValue = ps.executeUpdate();
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
						"Error message [" + e.getMessage() + " : CVOIP_ORDER ]");				
			    throw e;
			} catch (Exception e) {
				aLogger.log(
					LogEventId.ERROR,
					"Error message [" + e.getMessage() + "]");
				throw new SQLException("Update failed.");
			} finally {
				try {
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
						"CvoipOrderTable::updateRow()" );				
			}

			return returnValue;
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
