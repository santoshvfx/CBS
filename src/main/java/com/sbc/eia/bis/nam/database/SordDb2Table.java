// $Id: SordDb2Table.java,v 1.1 2002/09/29 03:20:42 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

import java.sql.*;
/**
 * Insert the type's description here.
 * Creation date: (9/18/01 3:13:22 PM)
 * @author: Hongmei Parkin
 */
public class SordDb2Table extends com.sbc.bccs.utility.database.Table {
/**
 * SordDb2Table constructor comment.
 */
public SordDb2Table() {
	super();
}
/**
 * SordDb2Table constructor comment.
 * @param dbConnection java.sql.Connection
 */
public SordDb2Table(java.sql.Connection dbConnection) {
	super(dbConnection);
}
/**
 * Insert the method's description here.
 * Creation date: (9/18/01 3:17:59 PM)
 */
public SordDb2Row find(String divCode) throws SQLException {
	PreparedStatement ps = null;
	ResultSet rs = null;
	String retVal = null;

	SordDb2Row row = null;
	row = new SordDb2Row();

	String SQLstatement =
		"select host_name, region_code, sub_system,database_name,creater_name,table_name,service_center from sord_db2 where div_code = ?";

	try {
		ps = connection.prepareStatement(SQLstatement);
		ps.setString(1, divCode);

		rs = ps.executeQuery();
		if (rs.next()) //will have one row only
			{
			row.setHostName(rs.getString(1));
			row.setRegionCode(rs.getString(2));
			row.setSubSystem(rs.getString(3));
			row.setDatabaseName(rs.getString(4));
			row.setCreaterName(rs.getString(5));
			row.setTableName(rs.getString(6));
			row.setServiceCenter(rs.getString(7));
			
		} else
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
		}
	}

	return row;
}
}
