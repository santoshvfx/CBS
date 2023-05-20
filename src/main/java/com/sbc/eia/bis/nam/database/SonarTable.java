// $Id: SonarTable.java,v 1.2 2002/09/29 03:21:44 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

import com.sbc.bccs.utility.database.*;
import java.io.*;
import java.sql.*;
/**
 * Insert the type's description here.
 * Creation date: (12/13/01 11:09:43 AM)
 * @author: Vickie Chui
 */
public class SonarTable {
/**
 * SonarTable constructor comment.
 */
public SonarTable() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:17:07 AM)
 * @param row com.sbc.eia.bis.nam.database.SonarRow
 * @param DBTable java.lang.String
 * @param SonarConn com.ibm.rmi.iiop.Connection
 */
public void delete(SonarRow row, String DBTable, Connection SonarConn)
throws SQLException {
	PreparedStatement ps = null;

	String SQLstatement =
		"delete from " + DBTable + 
		" where TN_NBR = ?";
	try {
		ps = SonarConn.prepareStatement(SQLstatement);

		ps.setString(1, row.getTn_nbr());
		
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
public SonarRow find(SonarRow row, String DBTable, Connection SonarConn) 
throws SQLException {
	PreparedStatement ps = null;
	ResultSet rs = null;

	SonarRow sonarRow = new SonarRow();

	String SQLstatement =
		"select TN_NBR, TN_AECN, RESERVATION_ID, TN_DATE, TN_STATUS from " +
		DBTable + " WHERE TN_NBR = ?";

	try {
		ps = SonarConn.prepareStatement(SQLstatement);
		ps.setString(1, row.getTn_nbr());
		
		rs = ps.executeQuery();
		
		if (rs.next()) //will have one row only
			sonarRow = setRow(rs, sonarRow);
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
	return sonarRow;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:17:07 AM)
 * @param row com.sbc.eia.bis.nam.database.SonarRow
 * @param DBTable java.lang.String
 * @param SonarConn com.ibm.rmi.iiop.Connection
 */
public void insert(SonarRow row, String DBTable, Connection SonarConn)
throws SQLException {
	PreparedStatement ps = null;

	String SQLstatement =
		"insert into " + DBTable + 
		" (TN_NBR, TN_AECN, RESERVATION_ID, TN_DATE, TN_STATUS) values(?,?,?,?,?)";

	try {
		ps = SonarConn.prepareStatement(SQLstatement); 
		ps.setString(1, row.getTn_nbr());
		ps.setString(2, row.getTn_aecn());
		ps.setString(3, row.getReservation_id());
		ps.setDate(4, row.getDate());
		ps.setString(5, row.getTn_status());
		
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
 * Creation date: (12/13/01 11:12:29 AM)
 * @param rs java.sql.ResultSet
 * @param row com.sbc.eia.bis.nam.database.SonarRow
 */
public SonarRow setRow(java.sql.ResultSet rs, SonarRow row)
	throws SQLException 
{
	try {
		row.setTn_nbr(rs.getString(1));
		row.setTn_aecn(rs.getString(2));
		row.setReservation_id(rs.getString(3));
		row.setDate(rs.getDate(4));
		row.setTn_status(rs.getString(5));
		
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());
	}

	return row;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:17:07 AM)
 * @param row com.sbc.eia.bis.nam.database.SonarRow
 * @param DBTable java.lang.String
 * @param SonarConn com.ibm.rmi.iiop.Connection
 */
public void update(SonarRow row, String DBTable, Connection SonarConn)
throws SQLException {
	
	PreparedStatement ps = null;
	
	String SQLstatement =
		"update " + DBTable + 
		" set TN_DATE = CURRENT DATE, RESERVATION_ID = ?, TN_STATUS = ? where TN_NBR = ?";
	try {
		ps = SonarConn.prepareStatement(SQLstatement); 
		ps.setString(1, row.getReservation_id());
		ps.setString(2, row.getTn_status());
		ps.setString(3, row.getTn_nbr());

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
