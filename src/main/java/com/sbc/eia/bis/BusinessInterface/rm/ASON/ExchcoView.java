// $Id: ExchcoView.java,v 1.4 2004/01/14 16:52:09 sl2917 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ASON;


/**
 * Insert the type's description here.
 * Creation date: (10/18/01 1:04:07 PM)
 * @author: Sanjeev Verma
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
 
public class ExchcoView extends com.sbc.bccs.utility.database.Table {
/**
 * ExchcoView constructor comment.
 */
public ExchcoView() {
	super();
}
/**
 * ExchcoView constructor comment.
 * @param dbConnection java.sql.Connection
 */
public ExchcoView(java.sql.Connection dbConnection) {
	super(dbConnection);
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/01 9:25:41 AM)
 * @return com.sbc.eia.bis.BusinessInterface.rm.ASON.ExchcoRow
 * @param WTN java.lang.String
 */
public final ExchcoRow find(String WTN,String service_center,Logger aLogger) throws SQLException{
	PreparedStatement ps = null;
	ResultSet rs = null;

	ExchcoRow row = null ;
	
	String SQLstatement =
		"select RAI_CODE,NPA,CENTRAL_OFFICE,LOW_RANGE,HIGH_RANGE,SAG_CO,EXK_NPA,EXK_CO "
		+ "from EXCHCO WHERE RAI_CODE= ?  and NPA = ? and CENTRAL_OFFICE = ? and "
		+ " ? >= LOW_RANGE and ? <= HIGH_RANGE ";
		 
	aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: <" + SQLstatement + ">");
	try {
		ps = connection.prepareStatement(SQLstatement);
		
		ps.setString(1, service_center.trim());
		ps.setString(2, WTN.substring(0,3));
		ps.setString(3, WTN.substring(3,6));
		ps.setString(4, WTN.substring(6,10));
		ps.setString(5, WTN.substring(6,10));
		rs = ps.executeQuery();
		if (rs.next()) //will have one row only
		{
			row = setRow(new ExchcoRow(), rs);
			
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
		aLogger.log(LogEventId.DEBUG_LEVEL_2,
			"Got SQL Exception when trying to get the data from Exchco Table ");
		throw e;
		
	}
	catch (Exception ex) {
		ex.printStackTrace();
		 aLogger.log(LogEventId.DEBUG_LEVEL_2,
			"Got SQL Exception when trying to get the data from Exchco Table ");
		 throw new SQLException("Got SQL Exception when trying to get the data from Exchco Table " + ex.getMessage());
		}
	finally {
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
/**
 * Insert the method's description here.
 * Creation date: (10/23/01 9:43:47 AM)
 * @return com.sbc.eia.bis.BusinessInterface.rm.ASON.ExchcoRow
 * @param ExchcoRow com.sbc.eia.bis.BusinessInterface.rm.ASON.ExchcoRow
 * @param ResultSet java.sql.ResultSet
 */
private final ExchcoRow setRow(ExchcoRow row, ResultSet rs)
	throws SQLException {

	try {
		row.setRai_code(rs.getString(1));
		row.setNpa(rs.getString(2));
		row.setCentral_office(rs.getString(3));
		row.setLow_range(rs.getString(4));
		row.setHigh_range(rs.getString(5));
		row.setSag_co(rs.getString(6));
		row.setExk_npa(rs.getString(7));
		row.setExk_co(rs.getString(8));
		
	
	} catch (SQLException e) {
		
		throw new SQLException(e.getMessage());
	} 
	

	return row;

}
}
