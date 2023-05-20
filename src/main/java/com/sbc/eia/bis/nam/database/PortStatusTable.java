// $Id: PortStatusTable.java,v 1.28 2007/02/22 23:33:40 dn6370 Exp $
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
//#      Copyright (C) 2004 SBC Services, Inc.
//#      All Rights Reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
/**
 * Represents the PORT_STATUS table.
 * Creation date: (4/17/01 8:58:41 AM)
 * @author: Creighton Malet
#   History :
#   Date      | Author          | Version   | Notes
#   ----------------------------------------------------------------------------
#   01/03/02   Mark Liljequist  4.0.2      
#   Added NULL FIRST for SQL in getByTN and getByLRN.
#
#   05/09/02   Mark Liljequist  4.0.3      
#   Changed SQL to get distinct in getByTN
#   
#   06/25/03  Sumana Roy        7.0.2
#   RM96232: Added getCLLIByNpaNxx() to find CLLI using 6-digit Npa/Nxx
#   
#   07/03/03  Sumana Roy
#   Changed the debug statements.
#
#   11/14/03  Sumana Roy
#   DR 98101 and DR 96232 changed the query to handle the D records
#
#   02/02/2004 Stevan Dunkin    7.10.2
#   RM 124625 Changed clli query to output ocn_contact_name and ocn_telephone_number
#   06/16/04   Jinmin ni/Jon Costa 
#   JDBC Code changes due to IBM audit for NAM

# 	07/13/2004	Stevan Dunkin		9.0.0
#	RM 138478   Added logic to retrieve CLLI for 7-digit NPA/NXX/X

#	08/06/2004	Stevan Dukin		9.0.0			
#	DR 114251	Changed SQL statement for method getNPANXXX()
				Corrected problem related to retrieve CLLI for 7-digit NPA/NXX/X

#	02/10/2005	CDT Developer			
#	RM 176516	Added logic to retrieve two new fields: Special Service Code (SSC) and Central Office Code (COCT)
#               from the Port Status Table 

#	04/22/2005	Roland Pates		11.0.2	Modified If block at find() for 10-digit LRN and revised its comments
											
#	02/20/2007  Deepti Nayar		DR170206:Added retry logic for database connection.
				
*/

package com.sbc.eia.bis.nam.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class PortStatusTable {

	/**
	 * Class constructor.
	 * Creation date: (5/4/01 12:33:24 PM)
	 */
	public PortStatusTable() {
	}

	/**
	 * Gets data by a corresponding TN from the PORT_STATUS table.
	 * Creation date: (4/18/01 4:02:35 PM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aDbConnection com.sbc.bccs.utility.database.DBConnection
	 * @param aTN com.sbc.bccs.idl.helpers.TN
	 * @param aLogger com.sbc.bccs.utilites.Logger
	 * @exception java.sql.SQLException If the query command fails, it will throw a SQLException.
	 */
	public static ArrayList getByTN(
		Hashtable props,
		String aNpa,
		String aPrefix,
		String aLine,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::getByTN() ");
		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT DISTINCT PORT_STATUS.TN_LN_LOW_RNGE, PORT_STATUS.TN_LN_UPR_RNGE, "
				+ "PORT_STATUS.REC_STS_CD,  PORT_STATUS.REC_EFF_DT, "
				+ "PORT_STATUS.RTCTR_CD, "
				+ "PORT_STATUS.CLLI, "
				+ "PORT_STATUS.OCN_ID, "
				+ "PORT_STATUS.OCN_NM, "
				+ "PORT_STATUS.PORT_IND, "
				+ "PORT_STATUS.POOL_IND, "
				+ "PORT_STATUS.SSC, "
				+ "PORT_STATUS.COCT "
				+ "FROM PORT_STATUS "
				+ "WHERE PORT_STATUS.NPA = ? "
				+ "AND PORT_STATUS.PRFX_CD = ? "
				+ "AND PORT_STATUS.TN_LN_LOW_RNGE <= ? AND PORT_STATUS.TN_LN_UPR_RNGE >= ? "
				+ "AND PORT_STATUS.POOL_IND = 'Y' "
				+ "ORDER BY TN_LN_LOW_RNGE, TN_LN_UPR_RNGE, REC_EFF_DT NULLS FIRST";
		int reTryCnt = 0;
		while (true) {
			try {
				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				preparedSql =
					tranx.getConnection(props).prepareStatement(SQLstatement);
				preparedSql.setString(1, aNpa);
				preparedSql.setString(2, aPrefix);
				preparedSql.setString(3, aLine);
				preparedSql.setString(4, aLine);
				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					PortStatusRow portStatusRow = new PortStatusRow();
					portStatusRow.setNpa(aNpa);
					portStatusRow.setPrfxCd(aPrefix);
					portStatusRow.setTnLnLowRnge(resultSet.getInt(1));
					portStatusRow.setTnLnUprRnge(resultSet.getInt(2));
					portStatusRow.setRecStsCd(resultSet.getString(3));
					portStatusRow.setRecEffDt(resultSet.getDate(4));
					portStatusRow.setRtctrCd(resultSet.getString(5));
					portStatusRow.setClli(resultSet.getString(6));
					portStatusRow.setLrn("");
					portStatusRow.setOperatingCompanyID(resultSet.getString(7));
					portStatusRow.setOperatingCompanyName(
						resultSet.getString(8));
					portStatusRow.setPortInd(resultSet.getString(9));
					portStatusRow.setPoolInd(resultSet.getString(10));
					//portStatusRow.setSsc(resultSet.getString(11));	             //* Changed name for RM 176516
					portStatusRow.setSpecialServiceCode(
						resultSet.getString(11));
					portStatusRow.setCentralOfficeCode(resultSet.getString(12));

					retVal.add(portStatusRow);
				}

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Get by TN failed...aTN=" + aNpa + aPrefix + aLine);
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::getByTN() .......");
			return retVal;
		}
	}

	/**
	* Gets data by a corresponding TN from the PORT_STATUS table.
	* Creation date: (4/18/01 4:02:35 PM)
	* @return java.util.ArrayList (PortStatusRow)
	* @param aDbConnection com.sbc.bccs.utility.database.DBConnection
	* @param aTN com.sbc.bccs.idl.helpers.TN
	* @param aLogger com.sbc.bccs.utilites.Logger
	* @exception java.sql.SQLException If the query command fails, it will throw a SQLException.
	*/
	public String getStatusByTN(
		Hashtable props,
		Utility aUtility,
		String aNpa,
		String aPrefix,
		String aLine,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			" PortStatusTable::getStatusByTN() ");
		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;

		String result = "false";
		aLogger.log(
			LogEventId.INFO_LEVEL_2,
			"TN Recieved : " + aNpa + "-" + aPrefix + "-" + aLine);

		String SQLstatement =
			"SELECT NPA, PRFX_CD, TN_LN_LOW_RNGE, TN_LN_UPR_RNGE, REC_STS_CD, "
				+ "REC_EFF_DT, SSC, POOL_IND, LEAST(TN_LN_UPR_RNGE -TN_LN_LOW_RNGE) FROM PORT_STATUS WHERE "
				+ "NPA = ? AND "
				+ "PRFX_CD = ? AND "
				+ "TN_LN_LOW_RNGE <= ? AND "
				+ "TN_LN_UPR_RNGE >= ? AND "
				+ "(REC_EFF_DT <= SYSDATE OR REC_EFF_DT IS NULL) AND "
				+ "(REC_STS_CD != 'D' OR REC_STS_CD IS NULL) ORDER BY LEAST(TN_LN_UPR_RNGE -TN_LN_LOW_RNGE)";
		int reTryCnt = 0;
		while (true) {
			try {

				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				preparedSql =
					tranx.getConnection(props, aUtility).prepareStatement(
						SQLstatement);
				preparedSql.setString(1, aNpa);
				preparedSql.setString(2, aPrefix);
				preparedSql.setString(3, aLine);
				preparedSql.setString(4, aLine);
				resultSet = preparedSql.executeQuery();

				if (resultSet.next()) {

					aLogger.log(LogEventId.DEBUG_LEVEL_1, "Record found. ");

					if (!resultSet.isFirst())
						aLogger.log(
							LogEventId.DEBUG_LEVEL_2,
							" Selecting first record.... " + resultSet.first());

					String poolIndValue = resultSet.getString(8);
					aLogger.log(
						LogEventId.DEBUG_LEVEL_2,
						" Pool indicator value: " + poolIndValue);

					if (poolIndValue != null && poolIndValue.equals("Y"))
						result = "true";
				} else
					result = "false";

				aLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					"Exiting PortStatusTable::getStatusByTN() .......");
				return result;

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Get by TN failed...aTN="
						+ aNpa
						+ aPrefix
						+ aLine
						+ e.getMessage());
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

		}
	}

	/**
	 * Creation date: (4/18/01 3:14:59 PM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aProperty java.util.Properties
	 * @param aTN com.sbc.bccs.idl.helpers.TN
	 * @param aLogger com.sbc.bccs.utilities.Logger
	 * @exception java.sql.SQLException If the DBConnection method or the getByTN
	 * method detects an exception, SQLException will be thrown or rethrown.
	 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
	 */
	public ArrayList find(Properties aProperty, TN aTN, Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::find() ");
		ArrayList retVal = null;

		try {

			aLogger.log(
				LogEventId.INFO_LEVEL_2,
				"Query PortStatusTable by TN: " + aTN.toString());

			// Getting CLLI By TN 
			retVal = getByTN(aProperty, aTN, aLogger);
		} catch (SQLException e) {
			throw e;
		}

		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			" Exiting PortStatusTable::find() .......");
		return retVal;
	}

	/**
	 * Creation date: (4/18/01 3:14:59 PM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aProperty java.util.Properties
	 * @param aNpaNxx java.lang.String
	 * @param aLogger com.sbc.bccs.utilities.Logger
	 * @exception java.sql.SQLException If the DBConnection method or the getByLRNAsTN
	 * method detects an exception, SQLException will be thrown or rethrown.
	 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
	 */
	public ArrayList find(Properties aProperty, String aNpaNxx, Logger aLogger)
		throws SQLException {

		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::find() ");

		ArrayList retVal = null;

		try {

			if (aNpaNxx.length() == 6) {
				aLogger.log(
					LogEventId.INFO_LEVEL_2,
					"Query PortStatusTable by NPA-NXX: "
						+ aNpaNxx.substring(0, 3)
						+ ""
						+ aNpaNxx.substring(3, 6));

				/* Getting CLLI using the NPA/NXX, this method will be used for getting CLLI using
				  6-digit NPA-NXX (LRN is truncated to NPA-NXX)*/

				retVal = getByLRNAsTN(aProperty, null, aNpaNxx, aLogger);
			} else {
				aLogger.log(
					LogEventId.INFO_LEVEL_2,
					"Query PortStatusTable by NPA-NXX-X: "
						+ aNpaNxx.substring(0, 3)
						+ ""
						+ aNpaNxx.substring(3, 6)
						+ ""
						+ aNpaNxx.substring(6, 7));
				retVal = getByNPANXXX(aProperty, null, aNpaNxx, aLogger);
			}
		} catch (SQLException e) {
			throw e;
		}

		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			" Exiting PortStatusTable::find() .......");
		return retVal;
	}

	//RM96232
	// This method has been removed as there was a DR (98101). And now the code
	// for this RM is combined in the getByLRNAsTN() method.
	/* 
	public PortStatusRow getCLLIByNpaNxx(Properties aProperty, String anNpaNxx, Logger aLogger) 
	throws SQLException 
	{}
	*/

	/**
	 * Gets an Oracle database connection and calls the getByTN method to 
	 * retrieve the PORT_STATUS data by TN from the table.
	 * The following JDBC properties are required in the properties file:
	 * <p>
	 * jdbcDATA_SOURCE_NAME = Data Source Name <br>
	 * jdbcUSERID = User Id <br>
	 * jdbcPASSWORD = Password <br>
	 * jdbcDRIVER = Oracle Drive name <br>
	 * jdbcURL = Database location for local driver <br>
	 * jdbcUSE_CONNECTION_POOL = True or False <p>
	 * Creation date: (4/16/01 11:08:36 AM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aFile java.lang.String
	 * @param aTN com.sbc.bccs.idl.helpers.TN
	 * @param anLRN java.lang.String
	 * @param aLogger com.sbc.bccs.utilites.Logger
	 * @exception java.sql.SQLException If the DBConnection method or the getByTN
	 * method detects an exception, SQLException will be thrown or rethrown.
	 */
	public static ArrayList find(
		String aFile,
		TN aTN,
		String anLRN,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::find() ");

		ArrayList retVal = null;
		int reTryCnt = 0;
		while (true) {
			try {
				/**
				 * Passes the JDBC access related properties in aFile to DBConnection method
				 * to access the Oracle database.
				 */

				if (anLRN == null)
					retVal = getByTN(aFile, aTN, aLogger);
				else
					retVal = getByLRNAsTN(null, aFile, anLRN, aLogger);
			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					anLRN = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}

				throw e;
			} catch (SQLException e) {
				throw e;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::find() .......");
			return retVal;
		}
	}
	/**
	 * Gets data by a corresponding LRN from the PORT_STATUS table.
	 * Creation date: (4/18/01 4:02:35 PM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aDbConnection com.sbc.bccs.utility.database.DBConnection
	 * @param aLRN java.lang.String
	 * @param aLogger com.sbc.bccs.utilites.Logger
	 * @exception java.sql.SQLException If the query command fails, it will throw a SQLException.
	 */
	public static ArrayList getByLRNAsTN(
		Properties aProperty,
		String aFile,
		String anNPANXX,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			" PortStatusTable::getByLRNAsTN() ");

		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT DISTINCT PORT_STATUS.NPA, PORT_STATUS.PRFX_CD, PORT_STATUS.TN_LN_LOW_RNGE, PORT_STATUS.TN_LN_UPR_RNGE, "
				+ "PORT_STATUS.REC_STS_CD,  PORT_STATUS.REC_EFF_DT, "
				+ "PORT_STATUS.PORT_IND,  PORT_STATUS.RTCTR_CD, "
				+ "PORT_STATUS.CLLI,  PORT_STATUS.OCN_ID, PORT_STATUS.OCN_NM, "
				+ "PORT_STATUS.OCN_CONTACT_NAME, "
				+ "PORT_STATUS.OCN_CONTACT_TELEPHONE, "
				+ "PORT_STATUS.SSC, "
				+ "PORT_STATUS.COCT "
				+ "FROM PORT_STATUS "
				+ "WHERE PORT_STATUS.NPA = ?"
				+ "AND PORT_STATUS.PRFX_CD = ? "
				+ "AND PORT_STATUS.TN_LN_LOW_RNGE <= ? AND PORT_STATUS.TN_LN_UPR_RNGE >= ? "
				+ "ORDER BY REC_EFF_DT NULLS FIRST";
		int reTryCnt = 0;
		while (true) {
			try {
				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				if (aProperty == null) {
					preparedSql =
						tranx.getConnection(aFile, aLogger).prepareStatement(
							SQLstatement);
				} else {
					preparedSql =
						tranx.getConnection(
							aProperty,
							aLogger).prepareStatement(
							SQLstatement);
				}
				preparedSql.setString(1, anNPANXX.substring(0, 3));
				preparedSql.setString(2, anNPANXX.substring(3, 6));
				preparedSql.setString(3, "0000");
				preparedSql.setString(4, "9999");

				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					PortStatusRow portStatusRow = new PortStatusRow();
					portStatusRow.setNpa(resultSet.getString(1));
					portStatusRow.setPrfxCd(resultSet.getString(2));
					portStatusRow.setTnLnLowRnge(resultSet.getInt(3));
					portStatusRow.setTnLnUprRnge(resultSet.getInt(4));
					portStatusRow.setRecStsCd(resultSet.getString(5));
					portStatusRow.setRecEffDt(resultSet.getDate(6));
					portStatusRow.setPortInd(resultSet.getString(7));
					portStatusRow.setRtctrCd(resultSet.getString(8));
					portStatusRow.setClli(resultSet.getString(9));
					portStatusRow.setLrn("");
					portStatusRow.setOperatingCompanyID(
						resultSet.getString(10));
					portStatusRow.setOperatingCompanyName(
						resultSet.getString(11));
					portStatusRow.setOperatingCompanyContactName(
						resultSet.getString(12));
					portStatusRow.setOperatingCompanyTelephoneNumber(
						resultSet.getString(13));

					portStatusRow.setSpecialServiceCode(
						resultSet.getString(14));
					portStatusRow.setCentralOfficeCode(resultSet.getString(15));

					retVal.add(portStatusRow);

				}

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Getting CLLI failed using NPA-NXX...=" + anNPANXX);
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::getByLRNAsTN() .......");
			return retVal;
		}
	}

	/**
	 * Gets data by a corresponding NPA-NXX-X from the PORT_STATUS table.
	 * Creation date: (6/17/04)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aProperty
	 * @param aFile
	 * @param anNPANXXX
	 * @param aLogger
	 * @throws SQLException
	 */
	public static ArrayList getByNPANXXX(
		Properties aProperty,
		String aFile,
		String anNPANXXX,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			" PortStatusTable::getByNPANXXX() ");

		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT DISTINCT PORT_STATUS.NPA, PORT_STATUS.PRFX_CD, PORT_STATUS.TN_LN_LOW_RNGE, PORT_STATUS.TN_LN_UPR_RNGE, "
				+ "PORT_STATUS.REC_STS_CD,  PORT_STATUS.REC_EFF_DT, "
				+ "PORT_STATUS.PORT_IND,  PORT_STATUS.RTCTR_CD, "
				+ "PORT_STATUS.CLLI,  PORT_STATUS.OCN_ID, PORT_STATUS.OCN_NM, "
				+ "PORT_STATUS.OCN_CONTACT_NAME, "
				+ "PORT_STATUS.OCN_CONTACT_TELEPHONE, "
				+ "PORT_STATUS.SSC, "
				+ "PORT_STATUS.COCT "
				+ "FROM PORT_STATUS "
				+ "WHERE PORT_STATUS.NPA = ?"
				+ "AND PORT_STATUS.PRFX_CD = ? "
				+ "AND PORT_STATUS.TN_LN_LOW_RNGE <= ? AND PORT_STATUS.TN_LN_UPR_RNGE >= ? "
				+ "ORDER BY LEAST(TN_LN_UPR_RNGE - TN_LN_LOW_RNGE), REC_EFF_DT NULLS FIRST";
		int reTryCnt = 0;
		while (true) {
			try {

				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				if (aProperty == null) {
					preparedSql =
						tranx.getConnection(aFile, aLogger).prepareStatement(
							SQLstatement);
				} else {
					preparedSql =
						tranx.getConnection(
							aProperty,
							aLogger).prepareStatement(
							SQLstatement);
				}
				preparedSql.setString(1, anNPANXXX.substring(0, 3));
				preparedSql.setString(2, anNPANXXX.substring(3, 6));
				preparedSql.setString(3, anNPANXXX.substring(6, 7) + "000");
				preparedSql.setString(4, anNPANXXX.substring(6, 7) + "999");

				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					PortStatusRow portStatusRow = new PortStatusRow();
					portStatusRow.setNpa(resultSet.getString(1));
					portStatusRow.setPrfxCd(resultSet.getString(2));
					portStatusRow.setTnLnLowRnge(resultSet.getInt(3));
					portStatusRow.setTnLnUprRnge(resultSet.getInt(4));
					portStatusRow.setRecStsCd(resultSet.getString(5));
					portStatusRow.setRecEffDt(resultSet.getDate(6));
					portStatusRow.setPortInd(resultSet.getString(7));
					portStatusRow.setRtctrCd(resultSet.getString(8));
					portStatusRow.setClli(resultSet.getString(9));
					portStatusRow.setLrn("");
					portStatusRow.setOperatingCompanyID(
						resultSet.getString(10));
					portStatusRow.setOperatingCompanyName(
						resultSet.getString(11));
					portStatusRow.setOperatingCompanyContactName(
						resultSet.getString(12));
					portStatusRow.setOperatingCompanyTelephoneNumber(
						resultSet.getString(13));

					portStatusRow.setSpecialServiceCode(
						resultSet.getString(14));
					portStatusRow.setCentralOfficeCode(resultSet.getString(15));

					retVal.add(portStatusRow);
				}

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Getting CLLI failed using NPA-NXX-X...=" + anNPANXXX);
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::getByNPANXXX() .......");
			return retVal;
		}
	}

	/**
	 * Gets data by a corresponding TN from the PORT_STATUS table.
	 * Creation date: (4/18/01 4:02:35 PM)
	 * @return java.util.ArrayList (PortStatusRow)
	 * @param aDbConnection com.sbc.bccs.utility.database.DBConnection
	 * @param aTN com.sbc.bccs.idl.helpers.TN
	 * @param aLogger com.sbc.bccs.utilites.Logger
	 * @exception java.sql.SQLException If the query command fails, it will throw a SQLException.
	 */
	public static ArrayList getByTN(
		Properties aProperty,
		TN aTN,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::getByTN() ");
		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT DISTINCT PORT_STATUS.TN_LN_LOW_RNGE, PORT_STATUS.TN_LN_UPR_RNGE, "
				+ "PORT_STATUS.REC_STS_CD,  PORT_STATUS.REC_EFF_DT, "
				+ "PORT_STATUS.RTCTR_CD, "
				+ "PORT_STATUS.CLLI, "
				+ "PORT_STATUS.OCN_ID, "
				+ "PORT_STATUS.OCN_NM, "
				+ "PORT_STATUS.OCN_CONTACT_NAME, "
				+ "PORT_STATUS.OCN_CONTACT_TELEPHONE, "
				+ "PORT_STATUS.SSC, "
				+ "PORT_STATUS.COCT, "
				+ "LEAST(TN_LN_UPR_RNGE - TN_LN_LOW_RNGE) "
				+ "FROM PORT_STATUS "
				+ "WHERE PORT_STATUS.NPA = ? "
				+ "AND PORT_STATUS.PRFX_CD = ? "
				+ "AND PORT_STATUS.TN_LN_LOW_RNGE <= ? AND PORT_STATUS.TN_LN_UPR_RNGE >= ? "
				+ "ORDER BY LEAST(TN_LN_UPR_RNGE - TN_LN_LOW_RNGE), REC_EFF_DT NULLS FIRST";
		int reTryCnt = 0;
		while (true) {
			try {
				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				preparedSql =
					tranx.getConnection(aProperty, aLogger).prepareStatement(
						SQLstatement);
				preparedSql.setString(1, aTN.getNpa());
				preparedSql.setString(2, aTN.getNxx());
				preparedSql.setString(3, aTN.getLine());
				preparedSql.setString(4, aTN.getLine());
				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					PortStatusRow portStatusRow = new PortStatusRow();
					portStatusRow.setNpa(aTN.getNpa());
					portStatusRow.setPrfxCd(aTN.getNxx());
					portStatusRow.setTnLnLowRnge(resultSet.getInt(1));
					portStatusRow.setTnLnUprRnge(resultSet.getInt(2));
					portStatusRow.setRecStsCd(resultSet.getString(3));
					portStatusRow.setRecEffDt(resultSet.getDate(4));
					portStatusRow.setPortInd("");
					portStatusRow.setRtctrCd(resultSet.getString(5));
					portStatusRow.setClli(resultSet.getString(6));
					portStatusRow.setLrn("");
					portStatusRow.setOperatingCompanyID(resultSet.getString(7));
					portStatusRow.setOperatingCompanyName(
						resultSet.getString(8));
					portStatusRow.setOperatingCompanyContactName(
						resultSet.getString(9));
					portStatusRow.setOperatingCompanyTelephoneNumber(
						resultSet.getString(10));

					portStatusRow.setSpecialServiceCode(
						resultSet.getString(11));
					portStatusRow.setCentralOfficeCode(resultSet.getString(12));

					retVal.add(portStatusRow);
				}

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Get by TN failed...aTN=" + aTN.toString());
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::getByTN() ....... ");
			return retVal;
		}
	}

	public static ArrayList getByTN(String aFile, TN aTN, Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::getByTN() ");
		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();

		String SQLstatement =
			"SELECT DISTINCT PORT_STATUS.TN_LN_LOW_RNGE, PORT_STATUS.TN_LN_UPR_RNGE, "
				+ "PORT_STATUS.REC_STS_CD,  PORT_STATUS.REC_EFF_DT, "
				+ "PORT_STATUS.RTCTR_CD, "
				+ "PORT_STATUS.CLLI, "
				+ "PORT_STATUS.OCN_ID, "
				+ "PORT_STATUS.OCN_NM, "
				+ "PORT_STATUS.OCN_CONTACT_NAME, "
				+ "PORT_STATUS.OCN_CONTACT_TELEPHONE, "
				+ "PORT_STATUS.SSC, "
				+ "PORT_STATUS.COCT, "
				+ "LEAST(TN_LN_UPR_RNGE - TN_LN_LOW_RNGE) "
				+ "FROM PORT_STATUS "
				+ "WHERE PORT_STATUS.NPA = ? "
				+ "AND PORT_STATUS.PRFX_CD = ? "
				+ "AND PORT_STATUS.TN_LN_LOW_RNGE <= ? AND PORT_STATUS.TN_LN_UPR_RNGE >= ? "
				+ "ORDER BY LEAST(TN_LN_UPR_RNGE - TN_LN_LOW_RNGE), REC_EFF_DT NULLS FIRST";
		int reTryCnt = 0;
		while (true) {
			try {
				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
				 * access the Oracle database.
				 */

				preparedSql =
					tranx.getConnection(aFile, aLogger).prepareStatement(
						SQLstatement);
				preparedSql.setString(1, aTN.getNpa());
				preparedSql.setString(2, aTN.getNxx());
				preparedSql.setString(3, aTN.getLine());
				preparedSql.setString(4, aTN.getLine());
				resultSet = preparedSql.executeQuery();
				while (resultSet.next()) {
					PortStatusRow portStatusRow = new PortStatusRow();
					portStatusRow.setNpa(aTN.getNpa());
					portStatusRow.setPrfxCd(aTN.getNxx());
					portStatusRow.setTnLnLowRnge(resultSet.getInt(1));
					portStatusRow.setTnLnUprRnge(resultSet.getInt(2));
					portStatusRow.setRecStsCd(resultSet.getString(3));
					portStatusRow.setRecEffDt(resultSet.getDate(4));
					portStatusRow.setPortInd("");
					portStatusRow.setRtctrCd(resultSet.getString(5));
					portStatusRow.setClli(resultSet.getString(6));
					portStatusRow.setLrn("");
					portStatusRow.setOperatingCompanyID(resultSet.getString(7));
					portStatusRow.setOperatingCompanyName(
						resultSet.getString(8));
					portStatusRow.setOperatingCompanyContactName(
						resultSet.getString(9));
					portStatusRow.setOperatingCompanyTelephoneNumber(
						resultSet.getString(10));

					portStatusRow.setSpecialServiceCode(
						resultSet.getString(11));
					portStatusRow.setCentralOfficeCode(resultSet.getString(12));

					retVal.add(portStatusRow);
				}

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
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
					"Get by TN failed...aTN=" + aTN.toString());
				throw e;

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			}

			aLogger.log(
				LogEventId.DEBUG_LEVEL_1,
				" Exiting PortStatusTable::getByTN() ....... ");
			return retVal;
		}
	}

	public boolean getBySSC(
		String aNpa,
		String aPrefix,
		String aLine,
		String aSSC,
		Logger aLogger,
		Properties aProperty)
		throws SQLException {
		aLogger.log(LogEventId.DEBUG_LEVEL_1, " PortStatusTable::getBySSC() ");

		boolean result = false;
		aLogger.log(
			LogEventId.INFO_LEVEL_2,
			"npa-nxx-line recieved: " + aNpa + aPrefix + aLine);

		if (aNpa.indexOf("*") > -1) // replace * with 0					
			aNpa = aNpa.replace('*', '0');

		if (aPrefix.indexOf("*") > -1) // replace * with 0					
			aPrefix = aPrefix.replace('*', '0');

		if (aLine.indexOf("*") > -1) // replace * with 0					
			aLine = aLine.replace('*', '0');

		DBTransactions tranx = new DBTransactions();
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		PortStatusRow portStatusRow = new PortStatusRow();

		String SQLstatement =
			"SELECT NPA, PRFX_CD, REC_STS_CD, "
				+ "REC_EFF_DT, SSC, LEAST(TN_LN_UPR_RNGE -TN_LN_LOW_RNGE) FROM PORT_STATUS WHERE "
				+ "NPA = ? AND "
				+ "PRFX_CD = ? AND "
				+ "TN_LN_LOW_RNGE <= ? AND "
				+ "TN_LN_UPR_RNGE >= ? AND "
				+ "(REC_EFF_DT <= SYSDATE OR REC_EFF_DT IS NULL) AND "
				+ "(REC_STS_CD != 'D' OR REC_STS_CD IS NULL) ORDER BY LEAST(TN_LN_UPR_RNGE -TN_LN_LOW_RNGE)";
		int reTryCnt = 0;
		while (true) {
			try {

				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"SQLstatement = <" + SQLstatement + ">");
				/**
					* access the Oracle database.
					*/

				preparedSql =
					tranx.getConnection(aProperty, aLogger).prepareStatement(
						SQLstatement);
				preparedSql.setString(1, aNpa);
				preparedSql.setString(2, aPrefix);
				preparedSql.setString(3, aLine);
				preparedSql.setString(4, aLine);
				resultSet = preparedSql.executeQuery();

				if (resultSet.next()) {

					aLogger.log(LogEventId.DEBUG_LEVEL_1, " Record found. ");
					if (!resultSet.isFirst())
						aLogger.log(
							LogEventId.DEBUG_LEVEL_2,
							" Selecting first record... " + resultSet.first());

					String strSSC = resultSet.getString(5);
					aLogger.log(
						LogEventId.DEBUG_LEVEL_2,
						" SSC column value: " + strSSC);

					if (strSSC != null && strSSC.equals("J")) {
						aLogger.log(
							LogEventId.DEBUG_LEVEL_1,
							" [J] in SSC column. ");
						result = true;
					}
				} else
					result = false;

				aLogger.log(
					LogEventId.DEBUG_LEVEL_1,
					" Exiting PortStatusTable::getBySSC() ....... ");

				return result;

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}

				throw e;
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());

			} finally {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				try {
					preparedSql.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				tranx = null;
			} //finally		

		}

	}
}
