//$Id: DslamMigrationTable.java,v 1.3.2.1 2007/10/18 21:48:34 ra0331 Exp $
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
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/23/2007	  Vickie Ng			  Creation.
//# 09/12/2007    Rommel Baldivas     Added retrieveByDslamId method.
//# 09/19/2007    Rommel Baldivas     Added retrieveRows method.
//# 09/26/2007    Rommel Baldivas     Added getDBConnetion method.

package com.sbc.eia.bis.rm.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class DslamMigrationTable extends Table{
	private static String sqlSelect =
		"select DSLAM_ID, EFF_START_DT, EFF_END_DT from DSLAM_MIGRATION_TABLE ";

/**
 * DslamMigrationTable constructor comment.
 */
public DslamMigrationTable() {
	super();
}

/**
 * Method retrieveByDslamId.
 * @param aDslamId
 * @param aProperties
 * @param aLogger
 * @return DslamMigrationRow[]
 * @throws SQLException
 * @throws DataNotFound
 * @throws ObjectNotFound
 * @throws NotImplemented
 * @throws AccessDenied
 * @throws BusinessViolation
 * @throws SystemFailure
 * @throws InvalidData
 */
public DslamMigrationRow[] retrieveByDslamId(
	String aDslamId,
	Hashtable aProperties,
	Logger aLogger)
	throws 
		InvalidData, 
		SystemFailure, 
		BusinessViolation, 
		AccessDenied, 
		NotImplemented, 
		ObjectNotFound, 
		DataNotFound, 
		SQLException {

	aLogger.log(LogEventId.INFO_LEVEL_1, "DslamMigrationTable:retrieveByDslamId()");
	aLogger.log(
		LogEventId.DEBUG_LEVEL_1,
		"SQLstatement: " + sqlSelect + "where DSLAM_ID = " + aDslamId);
	String[] aKeyValue = new String[1];
	aKeyValue[0] = aDslamId.toString();
	return retrieveRows(
		sqlSelect + "where DSLAM_ID = ?",
		aKeyValue,
		aProperties,
		aLogger
		);
}

/**
 * Method retrieveRows.
 * @param SQLstatement
 * @param aKeyValue
 * @param aProperties
 * @param aLogger
 * @return DslamMigrationRow[]
 * @throws SQLException
 * @throws DataNotFound
 * @throws ObjectNotFound
 * @throws NotImplemented
 * @throws AccessDenied
 * @throws BusinessViolation
 * @throws SystemFailure
 * @throws InvalidData
 */
public DslamMigrationRow[] retrieveRows(
	String SQLstatement,
	String[] aKeyValue,
	Hashtable aProperties,
	Logger aLogger)
	throws 
		SQLException, 
		InvalidData, 
		SystemFailure, 
		BusinessViolation, 
		AccessDenied, 
		NotImplemented, 
		ObjectNotFound, 
		DataNotFound {

	ResultSet rs = null;
	DBConnection aDBConn = null;
	PreparedStatement ps = null;
	DslamMigrationRow[] resultRows = null;
	int reTryCnt = 0;
	
	while (true) {
		try {
		
			aDBConn = getDBConnection(aProperties, aLogger);
			ps = aDBConn.getConnection().prepareStatement(SQLstatement);
		    
			for (int i = 0; i < aKeyValue.length; i++) {
				ps.setString(i + 1, aKeyValue[i]);
			}
			rs = ps.executeQuery();

			ArrayList rows = new ArrayList();

			while (rs.next()) {
				rows.add(
					new DslamMigrationRow(
						rs.getString(1),
						rs.getDate(2),
						rs.getDate(3)));
			}

			if (rows.size() > 0) {
				resultRows = new DslamMigrationRow[rows.size()];

				for (int i = 0; i < rows.size(); i++) {
					resultRows[i] = (DslamMigrationRow) rows.get(i);
					aLogger.log(
						LogEventId.DEBUG_LEVEL_1,
						"SQLResult: "
							+ resultRows[i].getDslamId()
							+ "|"
							+ resultRows[i].getEffectiveStartDate()
							+ "|"
							+ resultRows[i].getEffectiveEndDate()
							+ "|");
				}
			} else
				aLogger.log(
					LogEventId.INFO_LEVEL_1,
					"SQLResult: <no data found>");
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

		} catch (SQLException eSql) {
			aLogger.log(
					LogEventId.ERROR,
					"Error message [" + eSql.getMessage() +  "], "
					+ "SQL State [" + eSql.getSQLState() + "]");		
		 throw eSql;
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

public static DBConnection getDBConnection(Hashtable props, Logger aLogger)
	throws SQLException {
	String dataSourceName = null;
	String jdbcUserId = null;
	String jdbcPassWord = null;
	String jdbcUrl = null;
	String jdbcDriver = null;
	String jdbcOption = null;
	
	try {
		dataSourceName = ((String) props.get("jdbcDATA_SOURCE_NAME")).trim();
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

	aLogger.log(
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
		aLogger);
	}
}

