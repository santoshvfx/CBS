// $Id: CarrierByClliView.java,v 1.7 2007/02/19 23:12:09 dn6370 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.CARE;

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
 * Insert the type's description here.
 * Creation date: (1/11/02 4:27:42 PM)
 * @author: Sam Lok
 */
public class CarrierByClliView extends Table {
	private String jdbcDataSourceName = null;
	private String jdbcUsrId = null;
	private String jdbcPassWord = null;
	private String jdbcDriver = null;
	private String useDataSourcePool = null;
	private String jdbcUrl = null;
	private String TableSpace = null;

	private DBConnection dbConn = null;
	/**
	 * CarrierByClliView constructor comment.
	 */
	public CarrierByClliView() {
		super();
	}
	/**
	 * CarrierByClliView constructor comment.
	 * @param dbConnection java.sql.Connection
	 */
	public CarrierByClliView(java.sql.Connection dbConnection) {
		super(dbConnection);
	}
	/**
	 * Constructs a NcDefinesView object.
	 * <p>
	 * The following JDBC properties are required in the properties file:
	 * <p>
	 * jdbcDATA_SOURCE_NAME = Data Source Name <br>
	 * jdbcUSERID = User Id <br>
	 * jdbcPASSWORD = Password <br>
	 * jdbcDRIVER = Oracle Drive name <br>
	 * jdbcINITIAL_CONTEXT_FACTORY = jndi.CNInitialContextFactory package name <br>
	 * jdbcCONTEXT_PROVIDER_URL = Database location for WebSphere to access <br>
	 * jdbcURL = Database location for local driver <br>
	 * jdbcUSE_CONNECTION_POOL = True or False <br>
	 * TABLE_SPACE = TableSpace name of the LASR database housing the NcDefines table.<p>
	 * Creation date: (6/4/01 2:53:45 PM)
	 * @param aProperty java.util.Properties
	 * @param aLogger com.sbc.bccs.utilities.Logger
	 * @exception java.sql.SQLException The exception description.
	 */
	public CarrierByClliView(Properties aProperty, Logger aLogger)
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
			TableSpace = aProperty.getProperty("TABLE_SPACE").trim();
		} catch (Exception e) {
			aLogger.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get JDBC properties failed. JDBC required tags are not defined in properties file. "
					+ e.getMessage());
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined in properties file."
					+ e.getMessage());
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
			"CarrierByClliView::find(aCLLI...)");

		ArrayList retVal = null;
		int reTryCnt = 0;
		while (true) {

			try {

				/**
				 * Passes the JDBC access related properties to DBConnection() to
				 * access the Oracle database.
				 */

				if (dbConn == null) {
					aLogger.log(
						LogEventId.DEBUG_LEVEL_1,
						"Calling CARE DBConnection... ");
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
			"CarrierByClliView::getPicByCLLI(DBConnection...)");
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement = null;
		SQLstatement =
			"select distinct a.CIC_CODE, b.NAME, c.JURISDICTIONAL_IND, c.DISPLAY_IND, a.ACNA, c.ORDERABLE_IND "
				+ "from "
				+ TableSpace
				+ ".cel1c021 a left join "
				+ TableSpace
				+ ".cel1c022 b "
				+ "on a.ACNA = b.FK_ACNA, "
				+ TableSpace
				+ ".cel1c023 c "
				+ "where a.ACNA = c.FK_ACNA and "
				+ "c.JURISDICTIONAL_IND in ( '"
				+ CARE.CARE_PIC_CODE
				+ "', '"
				+ CARE.CARE_LPIC_CODE
				+ "', '"
				+ CARE.CARE_BOTH_CODE
				+ "' ) and "
				+ "a.ACNA in "
				+ "( "
				+ "select FK_ACNA from "
				+ TableSpace
				+ ".cel1c144 a, "
				+ TableSpace
				+ ".cel1c145 b "
				+ "where a.FK_TANDEM_ID = b.FK_TANDEM_ID and "
				+ "b.CLLI_CODE = '"
				+ aCLLI
				+ "' "
				+ ") ";
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
				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					CarrierByClliRow aCarrierByClliRow = new CarrierByClliRow();
					aCarrierByClliRow.setCIC_CODE(resultSet.getString(1));
					aCarrierByClliRow.setNAME(resultSet.getString(2));
					aCarrierByClliRow.setJURISDICTIONAL_IND(
						resultSet.getString(3));
					aCarrierByClliRow.setDISPLAY_IND(resultSet.getString(4));
					aCarrierByClliRow.setACNA(resultSet.getString(5));
					aCarrierByClliRow.setORDERABLE_IND(resultSet.getString(6));

					retVal.add(aCarrierByClliRow);
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
					LogEventId.DEBUG_LEVEL_1,
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
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		// Insert code to start the application here.
	}
}
