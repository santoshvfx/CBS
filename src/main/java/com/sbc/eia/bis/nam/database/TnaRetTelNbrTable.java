// $Id: TnaRetTelNbrTable.java,v 1.26 2004/06/17 21:51:58 jn1936 Exp $
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
//# Date      | Author                  | Notes
//# --------------------------------------------------------------------------
//# 02/24/04   Mark Liljequist            3.13 DR 104794 Changes for TN delay time.
//# 06/16/04   Jinmin ni/Jon Costa        JDBC Code changes due to IBM audit for NAM

package com.sbc.eia.bis.nam.database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;

/**
 * Insert the type's description here.
 * Creation date: (4/9/01 2:19:06 PM)
 * @author: Hongmei Parkin
*/

public class TnaRetTelNbrTable
{
	private static final String DEFAULT_TIME = ":11:59:59pm";
	private static final String DEFAULT_FORMAT = "yyyymmdd:hh:mi:sspm";
	
	private PreparedStatement findRowsForInquiryPs = null;
	
	public ResultSet findRowsForInquiry(Connection conn, String dbFetchSize, String divCodes)
		throws SQLException
	{

		//SelectedExpLength is in minutes

		ResultSet rs = null;

		String SQLstatement =
			"select TABLE1.NPA,TABLE1.PREFIX,TABLE1.LINE,TABLE1.COMPANY_CODE,TABLE1.TN_STATUS,TABLE1.RESERVATION_ID,TABLE1.UPDATE_DATE,TABLE1.SELECT_TYPE,TABLE1.SWITCH_TN_LIST_NBR,TABLE1.ADDRESS_OBJECT,TABLE1.ADDRESS_VERSION,TABLE1.INQ_CHNG_DT_TM,TABLE1.EXCHANGE,TABLE1.NPANXX,TABLE1.SAGA,TABLE1.DIV_CODE,TABLE1.WIRE_CENTER,TABLE1.SWITCH_WC FROM TNA_RET_TEL_NBR TABLE1 WHERE  TN_STATUS = 'X' AND DIV_CODE IN (" + divCodes + ") AND NOT EXISTS (SELECT NULL FROM TNA_RET_TEL_NBR WHERE TN_STATUS = 'R' AND TABLE1.NPA=NPA and TABLE1.PREFIX=PREFIX and TABLE1.LINE=LINE)";

		try
		{

			findRowsForInquiryPs = conn.prepareStatement(SQLstatement);
			findRowsForInquiryPs.setFetchSize(Integer.parseInt(dbFetchSize));

			rs = findRowsForInquiryPs.executeQuery();
		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Method:findRowsForInquiry:Failed to select rows : "
					+ e.getMessage());
		}
		
		return rs;
	}
    
	public int updateRowsForInquiry(Connection conn, int selectedExp, String divCodes)
		throws SQLException
	{
		//selectedExp is in minutes
		
		// set autocommit to false so that we can rollback the update in case a failure
		//
		conn.setAutoCommit(false);

		PreparedStatement ps = null;
		int count = 0;

		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'X', UPDATE_DATE = sysdate  where sysdate - ? >= update_date and tn_status = 'I' and div_code in ("+ divCodes + ")";

		try
		{
			ps = conn.prepareStatement(SQLstatement);
			float f1 = (float) selectedExp / 1440;
			ps.setFloat(1, f1);

			count = ps.executeUpdate();

			conn.commit(); 			

		}
		catch (SQLException e)
		{
			conn.rollback();
			throw new SQLException(
				"Method:updateRowsForInquiry:Failed to update rows when changing status from I to X: "
					+ e.getMessage());
		}
		finally
		{
			conn.setAutoCommit(true); 
			try{ps.close();}catch (Exception e){}

		}
		return count;
	}

	public int updateRowStatusChangeForInquiry(
		Connection conn,
		String reservationId)
		throws SQLException
	{

		PreparedStatement ps = null;
		int count = 0;


		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'I', UPDATE_DATE = sysdate  where RESERVATION_ID = ?";
		try
		{
			ps = conn.prepareStatement(SQLstatement);

			ps.setString(1, reservationId);

			count = ps.executeUpdate();

			conn.commit();  

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Method:updateRowStatusChangeForInquiry:Failed to change status from A back to I: "
					+ e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
		}
		return count;
	}

	public int updateRowStatusForInquiry(Connection conn,String divCodes)
		throws SQLException
	{
		// set autocommit to false so that we can rollback the update in case a failure
		//
		conn.setAutoCommit(false);
		
		PreparedStatement ps = null;
		int count = 0;

		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'A'  where tn_status = 'X' and div_code in ("+ divCodes + ")";

		try
		{
			ps = conn.prepareStatement(SQLstatement);

			count = ps.executeUpdate();

			conn.commit(); 
		}
		catch (SQLException e)
		{
			conn.rollback();
			throw new SQLException(
				"Method:updateRowStatusForInquiry:Failed to update when changing status from X to A: "
					+ e.getMessage());
		}
		finally
		{
			conn.setAutoCommit(true); 
			try{ps.close();}catch (Exception e){}
		}
		return count;
	}

	
	/**
	 * TnaRetTelNbrTable constructor comment.
	 */
	public TnaRetTelNbrTable()
	{}

	/**
	 * Delete the archive data after the specified reserve cancel days.
	 * Creation date: (7/23/01 1:46:49 PM)
	 */
	
		public void deleteArchive(Connection conn, int reserveExp)   
		throws SQLException
	{
		PreparedStatement ps = null;


		String SQLstatement =
			"delete from TNA_RET_TEL_NBR where TN_STATUS = 'A' and sysdate - ? >= update_date";
		try
		{
			ps = conn.prepareStatement(SQLstatement);

			ps.setInt(1, reserveExp);

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update NRM_TNA_TN_NBRS: " + e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
	
		}
	}
	/**
	 * Made for TN Cancel transaction
	 * Creation date: (5/9/01 10:54:01 AM)
	 */
	public TnaRetTelNbrRow find(
		Hashtable props,
		Utility aUtility,
		String reservationId)
		throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;
		row = new TnaRetTelNbrRow();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE RESERVATION_ID = ?";

		try
		{
			ps =
				tranx.getConnection(props, aUtility).prepareStatement(
					SQLstatement);
			ps.setString(1, reservationId);

			rs = ps.executeQuery();
			if (rs.next()) //will have one row only
			{
				row = setRow(rs, row);
			}
			else
				throw new SQLException("No row found");

		}
		catch (SQLException e)
		{
			throw e;

		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return row;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/24/01 3:28:10 PM)
	 */
	
//not reference. watch out how the connection is open in RM caller	
	public TnaRetTelNbrRow find(
		Hashtable props,
		String npa,
		String prefix,
		String line)
		throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;
		row = new TnaRetTelNbrRow();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE NPA = ? AND PREFIX = ? AND LINE = ? ";

		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);
			ps.setString(1, npa);
			ps.setString(2, prefix);
			ps.setString(3, line);

			rs = ps.executeQuery();
			if (rs.next()) //will have one row only
			{
				row = setRow(rs, row);
			}
			else
				throw new SQLException("No row found");

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return row;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/24/01 3:28:10 PM)
	 */
	public TnaRetTelNbrRow find(
		Hashtable props,
		Utility aUtility,
		String npa,
		String prefix,
		String line,
		String companyCode)
		throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;
		row = new TnaRetTelNbrRow();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE NPA = ? AND PREFIX = ? AND LINE = ? AND COMPANY_CODE = ? AND TN_STATUS = 'I'";

		try
		{
			ps = tranx.getConnection(props,aUtility).prepareStatement(SQLstatement);
			ps.setString(1, npa);
			ps.setString(2, prefix);
			ps.setString(3, line);
			ps.setString(4, companyCode.toUpperCase());  //DR 109846

			rs = ps.executeQuery();
			if (rs.next()) //will have one row only
			{
				row = setRow(rs, row);
			}
			else
				throw new SQLException("No row found");

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return row;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/24/01 3:28:10 PM)
	 */
	public TnaRetTelNbrRow find(
		Hashtable props,
		Utility aUtility,
		String npa,
		String prefix,
		String line,
		String companyCode,
		String tnStatus)
		throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;
		row = new TnaRetTelNbrRow();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE NPA = ? AND PREFIX = ? AND LINE = ? AND COMPANY_CODE = ? AND TN_STATUS = ?";

		try
		{
			ps =
				tranx.getConnection(props, aUtility).prepareStatement(
					SQLstatement);
			ps.setString(1, npa);
			ps.setString(2, prefix);
			ps.setString(3, line);
			ps.setString(4, companyCode.toUpperCase());  //DR109846
			ps.setString(5, tnStatus);

			rs = ps.executeQuery();
			if (rs.next()) //will have one row only
			{
				row = setRow(rs, row);
			}
			else
				throw new SQLException("No row found");

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return row;
	}
	/**
	 * Used for TNACleaner select_type is Random
	 * Select the rows from the database 
	 * Creation date: (5/17/01 2:40:55 PM)
	 */
		
	public ArrayList findInqForClean(
		Hashtable props,
		int selectedExpLength)
		throws SQLException
	{

		//SelectedExpLength is in minutes

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE sysdate - ? >= update_date and tn_status = 'I'";

		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);
			float f1 = (float) selectedExpLength / 1440;
			ps.setFloat(1, f1);

			rs = ps.executeQuery();

			while (rs.next()) //will have many rows
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return rowsV;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (10/10/01 10:03:36 AM)
	 * @param npa java.lang.String
	 * @param prefixes_to_change java.lang.String
	 * @exception java.sql.SQLException The exception description.
	 */
	public ArrayList findRecForSplit(
		Hashtable props,
		String npa,
		String prefixes_to_change)
		throws java.sql.SQLException
	{
		//SelectedExpLength is in minutes

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE npa = ? and prefix in ("
				+ prefixes_to_change
				+ ")";

		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);

			ps.setString(1, npa);

			rs = ps.executeQuery();
			while (rs.next()) //will have many rows
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}

		}
		catch (SQLException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return rowsV;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (7/26/01 3:37:55 PM)
	 */
	public ArrayList findResCanForClean(
		Hashtable props,
		int resCanExpLength)
		throws SQLException
	{

		//SelectedExpLength is in minutes

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE sysdate - ? >= update_date and (tn_status = 'R' or tn_status = 'C')";

		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);
			ps.setInt(1, resCanExpLength);

			rs = ps.executeQuery();
			while (rs.next()) //will have many rows
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return rowsV;
	}
	/**
	 * Used for TNACleaner select_type is Random
	 * Select the rows from the database 
	 * Creation date: (5/17/01 2:40:55 PM)
	 */
	public ArrayList findReservation(
		Hashtable props,
		Utility aUtility,
		String npa,
		String prefix,
		String line)
		throws SQLException
	{

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;
		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();
		row = new TnaRetTelNbrRow();

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE NPA = ? AND PREFIX = ? AND LINE = ? AND TN_STATUS = 'R'";

		try
		{
			ps = tranx.getConnection(props,aUtility).prepareStatement(SQLstatement);
			ps.setString(1, npa);
			ps.setString(2, prefix);
			ps.setString(3, line);

			rs = ps.executeQuery();
			// rs could have more than 1 row (faulty data existing before this method is implemented)
			while (rs.next())
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return rowsV;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (10/1/01 8:40:02 AM)
	 * @return java.lang.String
	 * @param aTnaRow com.sbc.eia.bis.nam.database.TnaRetTelNbrRow
	 * @exception java.sql.SQLException The exception description.
	 */
	public String findReserve(Connection conn, TnaRetTelNbrRow aTnaRow)
		throws java.sql.SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;

		String tnStatus = "Y";


		String SQLstatement =
			"select TN_STATUS from TNA_RET_TEL_NBR WHERE NPA = ? AND PREFIX = ? AND LINE = ? and COMPANY_CODE = ? ";

		try
		{
			ps = conn.prepareStatement(SQLstatement);
			ps.setString(1, aTnaRow.getNpa());
			ps.setString(2, aTnaRow.getPrefix());
			ps.setString(3, aTnaRow.getLine());
			ps.setString(4, aTnaRow.getCompanyCode());

			rs = ps.executeQuery();
			if (rs.next()) //will have one row only
			{
				tnStatus = rs.getString(1);
			}
			else
				throw new SQLException("No row found");

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}

		}
		return tnStatus;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (4/9/01 2:33:45 PM)
	 */
	public void insert(
		Connection conn,
		TnaRetTelNbrRow tnaRow,
		boolean delayFlag)
		throws SQLException
	{
		PreparedStatement ps = null;

		// Set delay time to nothing then check the flag.

		String delayTime = "";
		if (delayFlag)
		{
			delayTime = " - 1/144 ";
		}


		String SQLstatement =
			"insert into TNA_RET_TEL_NBR(NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC) values(?,?,?,?,?,?,sysdate "
				+ delayTime
				+ ", ?,?,empty_blob(),?,?,?,?,?,?,?,?)";

		try
		{
			ps = conn.prepareStatement(SQLstatement);
			ps.setString(1, tnaRow.getNpa());
			ps.setString(2, tnaRow.getPrefix());
			ps.setString(3, tnaRow.getLine());
			ps.setString(4, tnaRow.getCompanyCode());
			ps.setString(5, tnaRow.getTnStatus());
			ps.setString(6, tnaRow.getReservationId());
			ps.setString(7, tnaRow.getSelectType());
			ps.setString(8, tnaRow.getSwitchTnListNbr());
			ps.setString(9, tnaRow.getAddressVersion());
			ps.setString(10, tnaRow.getInqChngDtTm());
			ps.setString(11, tnaRow.getExchange());
			ps.setString(12, tnaRow.getNpanxx());
			ps.setString(13, tnaRow.getSaga());
			ps.setString(14, tnaRow.getDivCode());
			ps.setString(15, tnaRow.getWireCenter());
			ps.setString(16, tnaRow.getSwitchWc());
			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to insert record in TNA_RET_TEL_NBR: "
					+ e.getMessage());
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (Exception e)
			{}
		}

		if (tnaRow.getAddressObject() != null)
		{
			SQLstatement =
				"select address_object from tna_ret_tel_nbr where reservation_id = ? for update";

			Blob blob = null;
			InputStream inStream = null;
			OutputStream outStream = null;
			int length = -1;
			ResultSet rs = null;

			try
			{
				ps = conn.prepareStatement(SQLstatement);
				ps.setString(1, tnaRow.getReservationId());

				rs = ps.executeQuery();

				rs.next();

				blob = rs.getBlob(1);

				inStream =
					new ByteArrayInputStream(tnaRow.getAddressObject());

				outStream =
					((oracle.sql.BLOB) blob).getBinaryOutputStream();

				int size = ((oracle.sql.BLOB) blob).getBufferSize();

				byte[] buffer = new byte[size];
				length = -1;

				while ((length = inStream.read(buffer)) != -1)
					outStream.write(buffer, 0, length);

				inStream.close();
				outStream.close();

			}
			catch (IOException e)
			{}  
			catch (SQLException e)
			{
				throw new SQLException(
					"Failed to insert address blob TNA_RET_TEL_NBR: "
						+ e.getMessage());
			}
			finally
			{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
	
			} // finally
		} // tnaRow.getAddressObject() != null

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (6/4/01 2:04:21 PM)
	 */
	public TnaRetTelNbrRow setRow(ResultSet rs, TnaRetTelNbrRow row)
		throws SQLException
	{

		try
		{
			row.setNpa(rs.getString(1));
			row.setPrefix(rs.getString(2));
			row.setLine(rs.getString(3));
			row.setCompanyCode(rs.getString(4));
			row.setTnStatus(rs.getString(5));
			row.setReservationId(rs.getString(6));
			row.setUpdateDate(rs.getDate(7));
			row.setSelectType(rs.getString(8));
			row.setSwitchTnListNbr(rs.getString(9));

			Blob blob = rs.getBlob(10);

			InputStream instream = blob.getBinaryStream();

			byte[] buffer = new byte[(int) blob.length()];

			instream.read(buffer, 0, buffer.length);

			row.setAddressObject(buffer);

			row.setAddressVersion(rs.getString(11));
			row.setInqChngDtTm(rs.getString(12));
			row.setExchange(rs.getString(13));
			row.setNpanxx(rs.getString(14));
			row.setSaga(rs.getString(15));
			row.setDivCode(rs.getString(16));
			row.setWireCenter(rs.getString(17));
			row.setSwitchWc(rs.getString(18));
		}
		catch (SQLException e)
		{
			throw new SQLException(e.getMessage());
		}
		catch (IOException e)
		{
			throw new SQLException(e.getMessage());
		}

		return row;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (7/26/01 11:54:19 AM)
	 */
	public static void tnaAudit(
		TnaRetTelNbrRow tnaRow,
		AddressHandler addrHandler,
		String status,
		TranBase aTranBase)
	{
		aTranBase.log(
			aTranBase.LOG_AUDIT_TRAIL,
			"TNA Audit|"
				+ tnaRow.getNpa()
				+ "|"
				+ tnaRow.getPrefix()
				+ "|"
				+ tnaRow.getLine()
				+ "|"
				+ tnaRow.getCompanyCode()
				+ "|"
				+ status
				+ "|"
				+ tnaRow.getReservationId()
				+ "|"
				+ tnaRow.getUpdateDate()
				+ "|"
				+ tnaRow.getSelectType()
				+ "|"
				+ tnaRow.getDivCode()
				+ "|"
				+ tnaRow.getSaga()
				+ "|"
				+ tnaRow.getExchange()
				+ "|"
				+ tnaRow.getSwitchWc()
				+ "|"
				+ tnaRow.getWireCenter()
				+ (addrHandler == null
					? "|"
					: (addrHandler.getBox()
						+ "|"
						+ addrHandler.getHousNbrPfx()
						+ "|"
						+ addrHandler.getHousNbr()
						+ "|"
						+ addrHandler.getAasgndHousNbr()
						+ "|"
						+ addrHandler.getHousNbrSfx()
						+ "|"
						+ addrHandler.getStDir()
						+ "|"
						+ addrHandler.getStName()
						+ "|"
						+ addrHandler.getStThorofare()
						+ "|"
						+ addrHandler.getStNameSfx()
						+ "|"
						+ addrHandler.getCity()
						+ "|"
						+ addrHandler.getState()
						+ "|"
						+ addrHandler.getPostalCode()
						+ "|"
						+ addrHandler.getCounty()
						+ "|"
						+ addrHandler.getCountry()
						+ "|"
						+ addrHandler.getUnitValue()
						+ "|"
						+ addrHandler.getAddAddrInfo()
						+ "|"
						+ addrHandler.getRoute()
						+ "|")));
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (4/12/01 2:35:50 PM)
	 */
	public void update(Connection conn, 
	TnaRetTelNbrRow tnaRow, boolean delayFlag) throws SQLException
	{
		PreparedStatement ps = null;

		// Set delay time to nothing then check the flag.

		String delayTime = "";
		if (delayFlag)
		{
			delayTime = " - 1/144 ";
		}
		String SQLstatement =
			"update TNA_RET_TEL_NBR set UPDATE_DATE = sysdate "
				+ delayTime
				+ " ,SELECT_TYPE = ?, SWITCH_TN_LIST_NBR = ?,ADDRESS_VERSION = ?,INQ_CHNG_DT_TM = ?,EXCHANGE = ?,NPANXX = ?, SAGA = ?, TN_STATUS = ?, DIV_CODE = ?, WIRE_CENTER = ?, SWITCH_WC = ?, RESERVATION_ID = ? where NPA = ? and PREFIX = ? and LINE = ? and COMPANY_CODE = ? ";

		try
		{
			ps = conn.prepareStatement(SQLstatement);

			ps.setString(1, tnaRow.getSelectType());
			ps.setString(2, tnaRow.getSwitchTnListNbr());
			ps.setString(3, tnaRow.getAddressVersion());
			ps.setString(4, tnaRow.getInqChngDtTm());
			ps.setString(5, tnaRow.getExchange());
			ps.setString(6, tnaRow.getNpanxx());
			ps.setString(7, tnaRow.getSaga());
			ps.setString(8, tnaRow.getTnStatus());
			ps.setString(9, tnaRow.getDivCode());
			ps.setString(10, tnaRow.getWireCenter());
			ps.setString(11, tnaRow.getSwitchWc());
			ps.setString(12, tnaRow.getReservationId());
			ps.setString(13, tnaRow.getNpa());
			ps.setString(14, tnaRow.getPrefix());
			ps.setString(15, tnaRow.getLine());
			ps.setString(16, tnaRow.getCompanyCode());

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update TNA_RET_TEL_NBR: " + e.getMessage());
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (Exception e)
			{}
		}

		// Update the BLOB object
		if (tnaRow.getAddressObject() != null)
		{
			SQLstatement =
				"select address_object from tna_ret_tel_nbr where reservation_id = ? for update";

			Blob blob = null;
			InputStream inStream = null;
			OutputStream outStream = null;
			int length = -1;
			ResultSet rs = null;

			try
			{
				ps = conn.prepareStatement(SQLstatement);
				ps.setString(1, tnaRow.getReservationId());

				rs = ps.executeQuery();

				rs.next();

				blob = rs.getBlob(1);

				inStream =
					new ByteArrayInputStream(tnaRow.getAddressObject());

				outStream =
					((oracle.sql.BLOB) blob).getBinaryOutputStream();

				int size = ((oracle.sql.BLOB) blob).getBufferSize();

				byte[] buffer = new byte[size];
				length = -1;

				while ((length = inStream.read(buffer)) != -1)
					outStream.write(buffer, 0, length);

				inStream.close();
				outStream.close();

			}
			catch (IOException e)
			{}
			finally
			{
				try{rs.close();}catch (Exception e){}
				try{ps.close();}catch (Exception e){}
				
			}
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/01 2:38:50 PM)
	 */
	public void update(
		Hashtable props,
		Utility aUtility,
		String reservationId,
		String status)
		throws SQLException
	{
		PreparedStatement ps = null;

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = ?, UPDATE_DATE = sysdate  where RESERVATION_ID = ?";


		try
		{
			ps =
				tranx.getConnection(props, aUtility).prepareStatement(
					SQLstatement);

			ps.setString(1, status);
			ps.setString(2, reservationId);

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update NRM_TNA_TN_NBRS: " + e.getMessage());
		}
		finally
		{

			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
	}
	/**
	* Insert the method's description here.
	* Creation date: (4/30/01 2:38:50 PM)
	*/
	public void update( Connection conn, String reservationId, String status )
		throws SQLException
	{


		PreparedStatement ps = null;

		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = ?, UPDATE_DATE = sysdate  where RESERVATION_ID = ?";
		try
		{
			ps = conn.prepareStatement(SQLstatement);

			ps.setString(1, status);
			ps.setString(2, reservationId);

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update NRM_TNA_TN_NBRS: " + e.getMessage());
		}
		finally
		{
			try{if (ps != null)ps.close();}catch (SQLException e){}
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/12/01 2:35:50 PM)
	 */
	public void updateAfterReserve(Hashtable props, Utility utility, TnaRetTelNbrRow tnaRow)
		throws SQLException
	{
		PreparedStatement ps = null;

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'A' where NPA = ? and PREFIX = ? and LINE = ? and COMPANY_CODE != ? and TN_STATUS = 'I'";


		try
		{
			ps = tranx.getConnection(props,utility).prepareStatement(SQLstatement);
			ps.setString(1, tnaRow.getNpa());
			ps.setString(2, tnaRow.getPrefix());
			ps.setString(3, tnaRow.getLine());
			ps.setString(4, tnaRow.getCompanyCode());

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update TNA_RET_TEL_NBR: " + e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
	}
	/**
	 * update the data to archive after certain period for inquired data.
	 * Creation date: (5/17/01 5:22:16 PM)
	 */
	public void updateInquiry(Hashtable props, int selectedExp)
		throws SQLException
	{
		//selectedExp is in minutes

		PreparedStatement ps = null;

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'A', UPDATE_DATE = sysdate  where sysdate - ? >= update_date and tn_status = 'I'";
		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);
			float f1 = (float) selectedExp / 1440;
			ps.setFloat(1, f1);

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update NRM_TNA_TN_NBRS: " + e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (10/10/01 10:05:56 AM)
	 * @param old_npa java.lang.String
	 * @param prefix java.lang.String
	 * @param line java.lang.String
	 * @param company_code java.lang.String
	 * @param new_npa java.lang.String
	 * @exception java.sql.SQLException The exception description.
	 */
	public void updateRecForSplit(
		Hashtable props,
		String old_npa,
		String prefix,
		String line,
		String company_code,
		String new_npa)
		throws java.sql.SQLException
	{
		PreparedStatement ps = null;

		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"update TNA_RET_TEL_NBR set NPA = ? , UPDATE_DATE = sysdate  where  "
				+ "  npa =? and prefix = ? and line = ? and company_code=? ";
		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);

			ps.setString(1, new_npa);
			ps.setString(2, old_npa);
			ps.setString(3, prefix);
			ps.setString(4, line);
			ps.setString(5, company_code.toUpperCase());  //DR109846
			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update TNA_RET_TEL_NBR: " + e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
	}

	/**
	 * Update the data to archive after certain period for reserved and cancelled data.
	 * Creation date: (10/1/01 8:31:10 AM)
	 * @param npa java.lang.String
	 * @param prefix java.lang.String
	 * @param line java.lang.String
	 * @param reserveExp int
	 * @exception java.sql.SQLException The exception description.
	 */
	public void updateResCan(
		Connection conn,
		String npa,
		String prefix,
		String line,
		int reserveExp)
		throws java.sql.SQLException
	{
		PreparedStatement ps = null;

		
		String SQLstatement =
			"update TNA_RET_TEL_NBR set TN_STATUS = 'A', UPDATE_DATE = sysdate  where sysdate - ? >= update_date and (tn_status = 'R' or tn_status = 'C')"
				+ " and npa =? and prefix = ? and line = ?";
		try
		{
			ps = conn.prepareStatement(SQLstatement);

			ps.setInt(1, reserveExp);
			ps.setString(2, npa);
			ps.setString(3, prefix);
			ps.setString(4, line);

			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			throw new SQLException(
				"Failed to update NRM_TNA_TN_NBRS: " + e.getMessage());
		}
		finally
		{
			try{ps.close();}catch (Exception e){}
		
		}
	}
	/**
	 * Used for TNACleaner select_type is Random
	 * Select the rows from the database 
	 * Creation date: (5/17/01 2:40:55 PM)
	 */
	
	

	public ArrayList findForClean(Hashtable props, int selectedExpLength)
		throws SQLException
	{

		//SelectedExpLength is in minutes

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;
		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();
		DBTransactions tranx = new DBTransactions();
		String SQLstatement =
			"select TABLE1.NPA,TABLE1.PREFIX,TABLE1.LINE,TABLE1.COMPANY_CODE,TABLE1.TN_STATUS,TABLE1.RESERVATION_ID,TABLE1.UPDATE_DATE,TABLE1.SELECT_TYPE,TABLE1.SWITCH_TN_LIST_NBR,TABLE1.ADDRESS_OBJECT,TABLE1.ADDRESS_VERSION,TABLE1.INQ_CHNG_DT_TM,TABLE1.EXCHANGE,TABLE1.NPANXX,TABLE1.SAGA,TABLE1.DIV_CODE,TABLE1.WIRE_CENTER,TABLE1.SWITCH_WC FROM TNA_RET_TEL_NBR TABLE1 WHERE SYSDATE - ? >= UPDATE_DATE AND TN_STATUS = 'I' AND NOT EXISTS (SELECT NULL FROM TNA_RET_TEL_NBR WHERE TN_STATUS = 'R' AND TABLE1.NPA=NPA AND TABLE1.PREFIX=PREFIX AND TABLE1.LINE=LINE)";

		try
		{
			ps = tranx.getConnection(props).prepareStatement(SQLstatement);
			float f1 = (float) selectedExpLength / 1440;
			ps.setFloat(1, f1);

			rs = ps.executeQuery();

			while (rs.next()) //will have many rows
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
			try{tranx.disconnect();}catch (Exception e){}
			tranx = null;
		}
		return rowsV;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (7/26/01 3:37:55 PM)
	 */
	public ArrayList findResCanForClean(
		Connection conn,
		int resCanExpLength,
		String div_codes)
		throws SQLException
	{

		//SelectedExpLength is in minutes

		PreparedStatement ps = null;
		ResultSet rs = null;
		String retVal = null;
		TnaRetTelNbrRow row = null;

		ArrayList rowsV = new ArrayList();

		String SQLstatement =
			"select NPA,PREFIX,LINE,COMPANY_CODE,TN_STATUS,RESERVATION_ID,UPDATE_DATE,SELECT_TYPE,SWITCH_TN_LIST_NBR,ADDRESS_OBJECT,ADDRESS_VERSION,INQ_CHNG_DT_TM,EXCHANGE,NPANXX,SAGA,DIV_CODE,WIRE_CENTER,SWITCH_WC from TNA_RET_TEL_NBR WHERE sysdate - ? >= update_date and (tn_status = 'R' or tn_status = 'C') and div_code in ("
				+ div_codes
				+ ")";

		try
		{
			ps = conn.prepareStatement(SQLstatement);
			ps.setInt(1, resCanExpLength);

			rs = ps.executeQuery();
			while (rs.next()) //will have many rows
			{
				row = new TnaRetTelNbrRow();
				row = setRow(rs, row);
				rowsV.add(row);
			}

		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			try{rs.close();}catch (Exception e){}
			try{ps.close();}catch (Exception e){}
		}
		return rowsV;
	}
	/**
	 * Returns the findRowsForInquiryPs.
	 * @return PreparedStatement
	 */
	public PreparedStatement getFindRowsForInquiryPs() {
		return findRowsForInquiryPs;
	}

}
