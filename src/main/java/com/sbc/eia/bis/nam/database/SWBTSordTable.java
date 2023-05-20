// $Id: SWBTSordTable.java,v 1.1 2002/09/29 03:20:42 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

import com.sbc.bccs.utility.database.*;
import java.io.*;
import java.sql.*;
/**
 * Insert the type's description here.
 * Creation date: (9/17/01 1:43:46 PM)
 * @author: Vickie Chui
 */
public class SWBTSordTable extends com.sbc.bccs.utility.database.Table {
/**
 * SWBTSordTable constructor comment.
 */
public SWBTSordTable() {
	super();
}
/**
 * Delete row from the SWBTsord Table.
 * Creation date: (7/23/01 1:46:49 PM)
 */
public void delete(SWBTSordRow row, String DBTable, Connection SordConn) throws SQLException{
	PreparedStatement ps = null;

	String SQLstatement =
		"delete from " + DBTable + 
		" where TNRESV_NPA = ? and TNRESV_NXX = ? and TNRESV_LINE = ?";
	try {
		ps = SordConn.prepareStatement(SQLstatement);

		ps.setString(1, row.getTnresv_npa());
		ps.setString(2, row.getTnresv_nxx());
		ps.setString(3, row.getTnresv_line());
		
		if (ps.executeUpdate() == 0)
			throw new SQLException("No row deleted");

	} catch (SQLException e) {
		throw e;
	} finally {
		try {
			if (ps != null)
				ps.close();

		} catch (SQLException e) {}
	}
}
/**
 * Query the SWBTSord Table.
 * Creation date: (4/9/01 2:33:45 PM)
 */
public SWBTSordRow find(SWBTSordRow Row, String DBTable, Connection SordConn) throws SQLException {
	PreparedStatement ps = null;
	ResultSet rs = null;

	SWBTSordRow SordRow = new SWBTSordRow();

	String SQLstatement =
		"select TNRESV_NPA,TNRESV_NXX,TNRESV_LINE, TNRESV_CO_CODE, TNRESV_TN_STAT, TNRESV_RESERVE_ID, TNRESV_UPD_DATE from " +
		DBTable + " WHERE TNRESV_NPA = ? and TNRESV_NXX = ? and TNRESV_LINE = ?";

	try {
		ps = SordConn.prepareStatement(SQLstatement);
		ps.setString(1, Row.getTnresv_npa());
		ps.setString(2, Row.getTnresv_nxx());
		ps.setString(3, Row.getTnresv_line());
		
		rs = ps.executeQuery();
		
		if (rs.next()) //will have one row only
			SordRow = setRow(rs, SordRow);
		else
			throw new SQLException("No row found");
	} finally {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {}
	}
	return SordRow;
}
/**
 * Insert row into the SWBTSord Table.
 * Creation date: (4/9/01 2:33:45 PM)
 */
public void insert(SWBTSordRow Row, String DBTable, Connection SordConn) throws SQLException {
	PreparedStatement ps = null;

	String SQLstatement =
		"insert into " + DBTable + 
		" (TNRESV_NPA,TNRESV_NXX,TNRESV_LINE, TNRESV_CO_CODE, TNRESV_TN_STAT, TNRESV_RESERVE_ID, TNRESV_UPD_DATE) values(?,?,?,?,?,?,?)";

	try {
		ps = SordConn.prepareStatement(SQLstatement); 
		ps.setString(1, Row.getTnresv_npa());
		ps.setString(2, Row.getTnresv_nxx());
		ps.setString(3, Row.getTnresv_line());
		ps.setString(4, Row.getTnresv_co_code());
		ps.setString(5, Row.getTnresv_tn_stat());
		ps.setString(6, Row.getTnresv_reserve_id());
		ps.setDate(7, Row.getTnresv_upd_date());
		
		ps.executeUpdate();
		
	} catch (SQLException e) {
		throw e;
	} finally {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {}
	}
}
/**
 * Insert the method's description here.
 * Creation date: (6/4/01 2:04:21 PM)
 */
public SWBTSordRow setRow(ResultSet rs, SWBTSordRow row)
	throws SQLException {

	try {
		row.setTnresv_npa(rs.getString(1));
		row.setTnresv_nxx(rs.getString(2));
		row.setTnresv_line(rs.getString(3));
		row.setTnresv_co_code(rs.getString(4));
		row.setTnresv_tn_stat(rs.getString(5));
		row.setTnresv_reserve_id(rs.getString(6));
		row.setTnresv_upd_date(rs.getDate(7));
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());
	}

	return row;
}
/**
 * Update row in the SWBTSord Table.
 * Creation date: (4/12/01 2:35:50 PM)
 */
public void update(SWBTSordRow Row, String DBTable, Connection SordConn) throws SQLException{
	PreparedStatement ps = null;

	String SQLstatement =
		"update " + DBTable + 
		" set TNRESV_UPD_DATE = CURRENT DATE, TNRESV_TN_STAT = ?, TNRESV_CO_CODE = ?, TNRESV_RESERVE_ID = ? where TNRESV_NPA = ? and TNRESV_NXX = ? and TNRESV_LINE = ?";
	try {
		ps = SordConn.prepareStatement(SQLstatement); 
//		ps.setDate(1, Row.getTnresv_upd_date());
		ps.setString(1, Row.getTnresv_tn_stat());
		ps.setString(2, Row.getTnresv_co_code());
		ps.setString(3, Row.getTnresv_reserve_id());
		ps.setString(4, Row.getTnresv_npa());
		ps.setString(5, Row.getTnresv_nxx());
		ps.setString(6, Row.getTnresv_line());

		if (ps.executeUpdate() == 0)
			throw new SQLException("No row updated");

	} catch (SQLException e) {
		throw e;
	} finally {
		try {
			if (ps != null)
				ps.close();

		} catch (SQLException e) {}
	}
}
}
