// $Id: Clli2PicView.java,v 1.4 2007/02/19 23:19:59 dn6370 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.INEXS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Contains methods to retrieve state code by zip code from an Oracle ZC table.
 * Creation date: (4/17/01 8:58:41 AM)
 * @author: Donald W. Lee
 */
public class Clli2PicView extends Table {
	private String jdbcDataSourceName = "";
	private String jdbcUsrId = "";
	private String jdbcPassWord = "";
	private String jdbcDriver = "";
	private String useDataSourcePool = "";
	private String jdbcUrl = "";

	private com.sbc.bccs.utility.database.DBConnection dbConn = null;
	/**
	 * Constructs a Clli2PicView object.
	 * The following JDBC properties are required in the properties file:
	 * <p>
	 * jdbcDATA_SOURCE_NAME = Data Source Name <br>
	 * jdbcUSERID = User Id <br>
	 * jdbcPASSWORD = Password <br>
	 * jdbcDRIVER = Oracle Drive name <br>
	 * jdbcINITIAL_CONTEXT_FACTORY = jndi.CNInitialContextFactory package name <br>
	 * jdbcCONTEXT_PROVIDER_URL = Database location for WebSphere to access <br>
	 * jdbcURL = Database location for local driver <br>
	 * jdbcUSE_CONNECTION_POOL = True or False <p>
	 * Creation date: (6/4/01 3:46:02 PM)
	 * @param aProperty java.util.Properties
	 * @param aLogger com.sbc.bccs.utilities.Logger
	 * @exception java.sql.SQLException The exception description.
	 */
	public Clli2PicView(Properties aProperty, Logger aLogger)
		throws java.sql.SQLException {
		try {
			jdbcDataSourceName =
				aProperty.getProperty("jdbcDATA_SOURCE_NAME").trim();
			jdbcUsrId = aProperty.getProperty("jdbcUSERID").trim();
			jdbcPassWord = aProperty.getProperty("jdbcPASSWORD").trim();
			jdbcDriver = aProperty.getProperty("jdbcDRIVER").trim();
			useDataSourcePool =
				aProperty.getProperty("jdbcUSE_CONNECTION_POOL").trim();
			jdbcUrl = aProperty.getProperty("jdbcURL").trim();
		} catch (Exception e) {
			aLogger.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get JDBC properties failed. JDBC required tags are not defined in properties file. ");
			throw new SQLException("Get JDBC properties failed. JDBC required tags are not defined in properties file.");
		}

	}
	/**
	 * Gets an Oracle database connection and calls getPicByCLLI method to 
	 * retrieve the PIC_CD and PIC_NM by CLLI from the ostl315 Oracle table.
	 * If the requested CLLI is found, the corresponding PI_CD, PIC_NM is
	 * returned to the client. <p>
	 * Creation date: (4/18/01 3:14:59 PM)
	 * @return PIC Code, PIC Name java.util.ArrayList[]
	 * @param aCLLI java.lang.String
	 * @param aLogger com.sbc.bccs.utilities.Logger
	 * @exception java.sql.SQLException If the DBConnection method or the getPicByCLLI
	 * method detects an exception, SQLException will be thrown or rethrown.
	 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
	 */
	public ArrayList find(String aCLLI, Logger aLogger) throws SQLException {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"Clli2PicView::find(Properties...)");

		ArrayList retVal = null;
		int reTryCnt = 0;
		while (true) {
			try {

				/**
				/**
				 * Passes the JDBC access related properties to DBConnection() to
				 * access the Oracle database.
				 */

				if (dbConn == null) {
					aLogger.log(
						LogEventId.DEBUG_LEVEL_1,
						"Calling DBConnection... ");
					dbConn =
						new DBConnection(
							jdbcDataSourceName,
							jdbcUsrId,
							jdbcPassWord,
							jdbcUrl,
							jdbcDriver,
							useDataSourcePool,
							aLogger);
				}

				retVal = getPicByCLLI(dbConn, aCLLI, aLogger);

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					dbConn = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw e;

			} catch (Exception e) {
				e.printStackTrace();
				aLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"Get DBConnection failed... ");
				throw new SQLException(
					"DBConnection failed. " + e.getMessage());

			} finally {
				try {
					dbConn.disconnect();
				} catch (Exception e) {
				}
			}
			return retVal;
		}
	}
	/**
	 * Gets PIC Code and PIC Name by a corresponding CLLI from the CLLI2PIC Oracle table.
	 * Creation date: (4/18/01 4:02:35 PM)
	 * @return PIC Code, PIC Name java.lang.String[]
	 * @param aDbConnection com.sbc.bccs.utility.database.DBConnection
	 * @param aCLLI java.lang.String
	 * @param aLogger com.sbc.bccs.utilites.Logger
	 * @exception java.sql.SQLException If the query command fails, it will throw a SQLException.
	 */
	public ArrayList getPicByCLLI(
		DBConnection aDbConnection,
		String aCLLI,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"Clli2PicView::getPicByCLLI(DBConnection...)");
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT CLLI2PIC.PIC_TYPE_CD, CLLI2PIC.CXR_CD, "
				+ "PIC_TRNSLTN.CXR_NM,  PIC_TRNSLTN.ACNA_CD "
				+ "FROM CLLI2PIC, PIC_TRNSLTN "
				+ "WHERE CLLI2PIC.CLLI = ? "
				+ "AND CLLI2PIC.CXR_CD = PIC_TRNSLTN.CXR_CD(+)";

		int reTryCnt = 0;
		while (true) {
			try {

				aLogger.log(
					LogEventId.INFO_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */
				preparedSql =
					aDbConnection.getConnection().prepareStatement(
						SQLstatement);
				preparedSql.setString(1, aCLLI);
				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					Clli2PicRow clli2PicRow = new Clli2PicRow();
					clli2PicRow.setPicTypeCd(resultSet.getString(1));
					clli2PicRow.setCxrCd(resultSet.getString(2));
					clli2PicRow.setCxrNm(resultSet.getString(3));
					clli2PicRow.setAcnaCd(resultSet.getString(4));
					//			aLogger.log( LogEventId.DEBUG_LEVEL_2, "CLLI=" + aCLLI +
					//				" Row=" + clli2PicRow.display());
					retVal.add(clli2PicRow);
				}
			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw e;
			} catch (SQLException e) {
				e.printStackTrace();
				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"Get Pic by CLLI failed...aCLLI=" + aCLLI);
				throw e;

			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (preparedSql != null)
						preparedSql.close();
				} catch (Exception e) {
				}
			}

			return retVal;
		}
	}
}