// $Id: DBTransactions.java,v 1.14 2004/06/17 21:31:40 jn1936 Exp $
//############################################################################
//#
//#	Copyright Notice:
//#
//#		RESTRICTED - PROPRIETARY INFORMATION
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
//# 02/24/04   Mark Liljequist	      3.13 DR 104794 Changes for TN delay time.
//# 06/16/04   Jinmin ni/Jon Costa    JDBC Code changes due to IBM audit for NAM

package com.sbc.eia.bis.nam.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * Insert the type's description here.
 * Creation date: (4/25/01 10:45:25 AM)
 * @author: Hongmei Parkin 
*/


public class DBTransactions {

	public final static String SPECIFIC = "S"; //select_type
	public final static String RANDOM = "R"; //select_type

	public final static String INQUIRY = "I"; //tn_status inquiry
	public final static String RESERVE = "R"; //tn_status reserve
	public final static String CANCELED = "C"; //tn_status canceled
	public final static String ARCHIVE = "A"; //tn_status archived
	public final static String DELETE = "D"; //tn_status deleted
	private DBConnection conn = null;   
	/**
	 * DBTransactions constructor comment.
	 */
	public DBTransactions() {
		super();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 3:29:54 PM)
	 */
	
	/**
	 * getter method for conn class attribute, set during getConnection()
	 * Creation date: (3/17/04 3:29:54 PM)
	 */
	public DBConnection getDBConn()
	{
		return this.conn;
	}
	
	public Connection getConnection(Hashtable props) throws SQLException {

		String jdbcUserId = (String) props.get("jdbcUSERID");
		String jdbcPassWord = (String) props.get("jdbcPASSWORD");
		String jdbcUrl = (String) props.get("jdbcURL");
		String jdbcDriver = (String) props.get("jdbcDRIVER");

		conn = 
			new DBConnection(jdbcDriver, jdbcUrl, jdbcUserId, jdbcPassWord);
		return conn.getConnection();

	}
	


	
	public Connection getConnection(String aPropertyFile, Logger aLogger)
	throws SQLException
	{
		try
		{
			conn = new DBConnection(aPropertyFile, aLogger);
		}
		catch (SQLException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			aLogger.log(
				LogEventId.DEBUG_LEVEL_2,
				"load propertyFile failed.. ");
			throw new SQLException("DBConnection failed. " + e.getMessage());
		}
		return conn.getConnection();
	}
	
	/**
	 * Method setAutoCommit
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException
	{
		conn.setAutoCommit( autoCommit );
	}
	
	/**
	 * Method commit.
	 */
	public void commit() throws SQLException
	{
		conn.commit();
	}

	/**
	 * Method disconnect
	 */
	public void disconnect() throws SQLException 
	{		
		conn.disconnect();				
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 3:29:54 PM)
	 */
	public Connection getConnection(
		Hashtable props,
		Utility aUtility,
		String DB,
		String hostName)
		throws SQLException, IOException {

		String SWBTjdbcFile = (String) props.get("SWBT_SORD_JDBC_FILE");
		//DBConnection conn = null;
		try {
			conn = new DBConnection(SWBTjdbcFile, aUtility, DB, hostName);
		} catch (IOException e) {
			throw e;
		}
		return conn.getConnection();

	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/12/01 1:11:58 PM)
	 */
	public Connection getConnection(Hashtable props, Logger aLogger)
		throws SQLException {

		String dataSourceNm = (String) props.get("jdbcDATA_SOURCE_NAME");
		String jdbcUsrId = (String) props.get("jdbcUSERID");
		String jdbcPassWord = (String) props.get("jdbcPASSWORD");
		String jdbcURL = (String) props.get("jdbcURL");
		String jdbcDriver = (String) props.get("jdbcDRIVER");
		String jdbcOption = (String) props.get("jdbcUSE_CONNECTION_POOL");

		//DBConnection conn =
		conn = 
			new DBConnection(
				dataSourceNm,
				jdbcUsrId,
				jdbcPassWord,
				jdbcURL,
				jdbcDriver,
				jdbcOption,
				aLogger);
		return conn.getConnection();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/9/01 4:03:05 PM)
	 */
	public boolean insertDatabase(
		String npa,
		String prefix,
		String line,
		String companyCd,
		String tnStatus,
		String reservationId,
		String selectType,
		String tnList,
		byte[] addressObject,
		String addressVersion,
		String chngDtTm,
		String saga,
		String divCode,
		String exchange,
		String switchWc,
		String wireCenter,
		Connection conn, 
		//Hashtable props,
		Utility utility,
		boolean delayFlag)
		throws SQLException {

		/* Added to enable delete in case tn_status changes from R to I
		in the local database table: TNA_RET_TEL_NBR
		*/
		boolean remoteDeleteStatus = false;

		TnaRetTelNbrRow tnaRow = new TnaRetTelNbrRow();

		tnaRow.setNpa(npa);
		tnaRow.setPrefix(prefix);
		tnaRow.setLine(line);
		tnaRow.setCompanyCode(companyCd);
		tnaRow.setTnStatus(tnStatus);
		tnaRow.setReservationId(reservationId);
		tnaRow.setSelectType(selectType);
		tnaRow.setSwitchTnListNbr(tnList);
		tnaRow.setAddressObject(addressObject);
		tnaRow.setAddressVersion(addressVersion);
		tnaRow.setInqChngDtTm(chngDtTm);
		tnaRow.setExchange(exchange);
		tnaRow.setSwitchWc(switchWc);
		tnaRow.setWireCenter(wireCenter);
		//	tnaRow.setNpanxx("");
		tnaRow.setSaga(saga);
		tnaRow.setDivCode(divCode);

		TnaRetTelNbrTable tnaTable =
			new TnaRetTelNbrTable();

		String tnaRowStatus = null;


		utility.log(LogEventId.DEBUG_LEVEL_2, "delayFlag......" + delayFlag);

		try {
			
			//tnaTable.insert(tnaRow);

			tnaTable.insert(conn,tnaRow, delayFlag);
			utility.log(
				LogEventId.DEBUG_LEVEL_1,
				"DBTransaction: Calling insert()....");

			utility.log(
				LogEventId.DEBUG_LEVEL_1,
				"Inserted row npa="
					+ tnaRow.getNpa()
					+ ", prefix="
					+ tnaRow.getPrefix()
					+ ",line="
					+ tnaRow.getLine());
            
			return remoteDeleteStatus;
		} catch (SQLException e) {

			utility.log(
				LogEventId.DEBUG_LEVEL_1,
				"Insert failed, try update.....");

			try {
				try {
					tnaRowStatus = tnaTable.findReserve(conn,tnaRow);
				} catch (SQLException sqe) {
					utility.log(
						LogEventId.DEBUG_LEVEL_1,
						"tna row not found in the database");
				}
				if (tnaRowStatus != null
					&& tnaRowStatus.equalsIgnoreCase("R")) {
					remoteDeleteStatus = true;
				}
				//tnaTable.update(tnaRow);

				utility.log(
					LogEventId.DEBUG_LEVEL_1,
					"DBTransaction. :Calling update()....");
				tnaTable.update(conn,tnaRow, delayFlag);

				utility.log(
					LogEventId.DEBUG_LEVEL_1,
					"updated row npa="
						+ tnaRow.getNpa()
						+ ", prefix="
						+ tnaRow.getPrefix()
						+ ",line="
						+ tnaRow.getLine());

				return remoteDeleteStatus;
			} catch (SQLException se) {
				throw new SQLException(se.getMessage());
			}
		}
	}
	
}
