// $Id: CarrierView.java,v 1.4 2004/12/03 18:36:02 biscvsid Exp $
//$Id: CarrierView.java,v 1.4 2004/12/03 18:36:02 biscvsid Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 9/2001      Sam Lok         Creation.
//# 10/25/2004  Jinmin Ni       RM120184: Add CAUTH and TOS to return.

package com.sbc.eia.bis.BusinessInterface.rm.ASON;


/**
 * Insert the type's description here.
 * Creation date: (10/23/01 4:09:38 PM)
 * @author: Sanjeev Verma
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
public final class CarrierView extends com.sbc.bccs.utility.database.Table {
/**
 * CarrierView constructor comment.
 */
public CarrierView() {
	super();
}
/**
 * CarrierView constructor comment.
 * @param dbConnection java.sql.Connection
 */
public CarrierView(java.sql.Connection dbConnection) {
	super(dbConnection);
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/01 4:15:05 PM)
 * @return com.sbc.eia.bis.BusinessInterface.rm.ASON.CarrierRow
 * @param param com.sbc.eia.bis.BusinessInterface.rm.ASON.CarrierRow
 */
public ArrayList find(String sag_co,
					  String exk_no,
					  String service_center,
					  Logger aLogger)
throws SQLException
{
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList retVal = new ArrayList();
	CarrierRow row=null;

	String SQLstatement =
		"select distinct b.rai_code,b.carrier_pic,b.carrier_picx, b.carrier_type,"
		+" CASE UPPER(b.res_bus_code) WHEN 'B' THEN 'B'" 
		+" WHEN 'R' then 'R'"  					
   		+" ELSE NULL END res_bus_code,"
		+" SUBSTR(interlata_carrier_text,0,INSTR(interlata_carrier_text,'*')-1) interlata_cauth," 
		+" SUBSTR(interlata_carrier_text,INSTR(interlata_carrier_text,'*')+1) interlata_acndes,"
		+" SUBSTR(b.intralata_carrier_text,0,INSTR(b.intralata_carrier_text,'*')-1) intralata_cauth,"
		+" SUBSTR(b.intralata_carrier_text,INSTR(b.intralata_carrier_text,'*')+1) intralata_acndes"
		+" from exchpic a,carrier b "
		+" where b.rai_code=? and a.npa=? and a.exchange=? and a.sag_co=?"
		+" and a.exk=? "
		+" and a.exch_pic=b.carrier_pic and a.exch_pic_type=b.carrier_type(+)"
		+" order by b.carrier_pic, b.carrier_picx, b.carrier_type, "
		+" res_bus_code";

	aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: <" + SQLstatement + ">");
	try {
		
		ps = connection.prepareStatement(SQLstatement);
		ps.setString(1, service_center);
		ps.setString(2, sag_co.substring(0,3));
		ps.setString(3, sag_co.substring(3,7));
		ps.setString(4, sag_co.substring(7,10));
		ps.setString(5, exk_no);
		
		rs = ps.executeQuery();
		while(rs.next()){
			row = new CarrierRow();
			row = setRow(row, rs);
			retVal.add(row);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		aLogger.log(LogEventId.DEBUG_LEVEL_2,
			"Got SQL Exception when trying to get the data from Carrier and Exchpic Tables ");
		throw e;
		
	}
	catch (Exception ex) {
		ex.printStackTrace();
		 aLogger.log(LogEventId.DEBUG_LEVEL_2,
			"Got SQL Exception when trying to get the data from Carrier and Exchpic Tables ");
		 throw new SQLException("Got SQL Exception when trying to get the data from Carrier and Exchpic Tables " + ex.getMessage());
		}
	finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "Got SQL Exception when trying to close ResultSet" + e.getMessage());
		}

		
	}
	
	return retVal;
}

private final CarrierRow setRow(CarrierRow row, ResultSet rs)
	throws Exception {

	
		row.setRai_code(rs.getString(1));
		row.setCarrier_pic(rs.getString(2));
		row.setCarrier_picx(rs.getString(3));
		row.setCarrier_type(rs.getString(4));
		row.setRes_bus_code(rs.getString(5));
		row.setInterlata_cauth(rs.getString(6));
		row.setInterlata_acnades(rs.getString(7));
		row.setIntralata_cauth(rs.getString(8));
		row.setIntralata_acnades(rs.getString(9));
	
/*		
	System.out.println("Carrier pic is "+rs.getString(1));
	System.out.println(" Carrier picx is "+rs.getString(2));
	System.out.println("Carrier type"+rs.getString(3));
	System.out.println(" bus code is "+	rs.getString(4));
	System.out.println(" Interalata is "+	rs.getString(5));
	System.out.println(" Interlata is "+	rs.getString(6));
	*/
	
	

	return row;

}

}
