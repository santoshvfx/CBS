// $Id: AitLastTnTable.java,v 1.1 2002/09/29 03:20:41 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

import com.sbc.eia.bis.RmNam.utilities.*;
import com.sbc.eia.idl.lim.helpers.*;
import java.util.*;
import javax.ejb.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;
import java.io.*;
import com.sbc.bccs.utility.database.*;
import java.sql.*;
/**
 * Insert the type's description here.
 * Creation date: (4/9/01 2:19:06 PM)
 * @author: Hongmei Parkin
 */
public class AitLastTnTable extends Table {
	
/**
 * TnaRetTelNbrTable constructor comment.
 */
public AitLastTnTable() {
	super();
}
/**
 * TnaRetTelNbrTable constructor comment.
 * @param dbConnection java.sql.Connection
 */
public AitLastTnTable(Connection dbConnection) {
	super(dbConnection);
}
/**
 * Insert the method's description here.
 * Creation date: (10/1/01 8:40:02 AM)
 * @return java.lang.String
 * @param aTnaRow com.sbc.eia.bis.nam.database.TnaRetTelNbrRow
 * @exception java.sql.SQLException The exception description.
 */
public String find(String aExchange, String aSagco, String aPrimaryNpa) throws SQLException {

	PreparedStatement ps = null;
	ResultSet rs = null;
	String telephoneNumber = null;
	String SQLstatement =
		"select TEL_NBR from AIT_LAST_TN WHERE EXCHANGE = ? AND SAGCO = ? AND PRIMARY_NPA = ?";

	try {
		ps = connection.prepareStatement(SQLstatement);
		ps.setString(1, aExchange);
		ps.setString(2, aSagco);
		ps.setString(3, aPrimaryNpa);
		
		rs = ps.executeQuery();
		if (rs.next()) //will have one row only
			telephoneNumber = rs.getString(1);
		else
			throw new SQLException("No row found");

	} catch (SQLException e) {
		throw e;

	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
			throw new SQLException("close failed in find");			
		}
	} //finally

	return telephoneNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:33:45 PM)
 */
public void insert(AitLastTnRow aitRow) throws SQLException {

	PreparedStatement ps = null;
	String SQLstatement =
		"insert into AIT_LAST_TN(EXCHANGE,SAGCO,PRIMARY_NPA,TEL_NBR) values(?,?,?,?)";
	try {
		ps = connection.prepareStatement(SQLstatement);
		ps.setString(1, aitRow.getExchange());
		ps.setString(2, aitRow.getSagco());
		ps.setString(3, aitRow.getPrimaryNpa());
		ps.setString(4, aitRow.getTelNbr());
		ps.executeUpdate();

	} catch (SQLException e) {
		throw new SQLException("Failed to insert AIT_LAST_TN: " + e.getMessage());

	} finally {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			throw new SQLException("close failed in insert");
		}
	} //finally
}
/**
 * Insert the method's description here.
 * Creation date: (4/12/01 2:35:50 PM)
 */
public void update(AitLastTnRow aitRow) throws SQLException {

	PreparedStatement ps = null;
	String SQLstatement =
		"update AIT_LAST_TN set TEL_NBR = ? where EXCHANGE = ? and SAGCO = ? and PRIMARY_NPA = ?";
	try {
		ps = connection.prepareStatement(SQLstatement);
		ps.setString(1, aitRow.getTelNbr());
		ps.setString(2, aitRow.getExchange());
		ps.setString(3, aitRow.getSagco());
		ps.setString(4, aitRow.getPrimaryNpa());
		ps.executeUpdate();

	} catch (SQLException e) {
		throw new SQLException("Failed to update AIT_LAST_TN: " + e.getMessage());

	} finally {
		try {
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
			throw new SQLException("close failed in update");
		}
	} //finally
}
}
