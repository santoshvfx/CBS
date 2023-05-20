// $Id: TnMasterTable.java,v 1.4 2003/03/25 22:50:08 ts8181 Exp $

package com.sbc.eia.bis.nam.database;

import java.sql.*;
import com.sbc.bccs.utility.database.*;
import java.util.*;

/**
 * Insert the type's description here.
 * Creation date: (8/14/01 1:46:30 PM)
 * @author: Hongmei Parkin
 */
public class TnMasterTable {
/**
 * TnMasterTable constructor comment.
 */
public TnMasterTable() {
	super();
}

/**
 * Insert the method's description here.
 * Creation date: (8/14/01 1:47:04 PM)
 */
public TnMasterRow getWireCenter(
	String npa,
	String prefix,
	int mask,
	DBConnection conn,
	String limNpa,
	String limNxx)
	throws SQLException {

	PreparedStatement ps = null;
	ResultSet rs = null;

	TnMasterRow row = new TnMasterRow();
	String SQLstatement =
		"select * from TNMASTER where NPA = ? and PRFX_CD = ? and SOAC_WC in (select  distinct SOAC_WC from TNMASTER  where NPA = ? and PRFX_CD = ? and TN_LN_LOW_RNGE <= ? and TN_LN_UPR_RNGE >= ?) order by TN_LN_LOW_RNGE DESC";

	try {
		ps = conn.getConnection().prepareStatement(SQLstatement);
		ps.setString(1, limNpa);
		ps.setString(2, limNxx);
		ps.setString(3, npa);
		ps.setString(4, prefix);
		ps.setInt(5, mask);
		ps.setInt(6, mask);
		rs = ps.executeQuery();
		if (rs.next())
			{
			row = setRow(rs, row);
		} else
			throw new SQLException("No row found");
			
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());

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
/**
 * Insert the method's description here.
 * Creation date: (8/14/01 1:47:04 PM)
 */
public String getWireCenter(String npa, String prefix, DBConnection conn)
    throws SQLException {

    PreparedStatement ps = null;
    ResultSet rs = null;
    String retVal = null;
    String SQLstatement =
        "select SOAC_WC from TNMASTER where NPA = ? and PRFX_CD = ?";

    try {
        ps = conn.getConnection().prepareStatement(SQLstatement);
        ps.setString(1, npa);
        ps.setString(2, prefix);

        rs = ps.executeQuery();

        if (rs.next()) {
            retVal = rs.getString(1);
        } else
            throw new SQLException("No wire center row found for TN.");

    } catch (SQLException e) {
        throw new SQLException("Get wire center failed: " + e.getMessage());

    } finally {
        try {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();

        } catch (SQLException e) {
        }
    }

    return retVal;
}
/**
 * Insert the method's description here.
 * Creation date: (8/14/01 1:47:04 PM)
 */
public String getWireCenter(
	String npa,
	String prefix,
	DBConnection conn,
	String limNpa,
	String limNxx)
	throws SQLException {

	PreparedStatement ps = null;
	ResultSet rs = null;
 
	String wireCenter = null;
	String SQLstatement =
		"select distinct SOAC_WC from TNMASTER where NPA = ? and PRFX_CD = ? and SOAC_WC in (select distinct SOAC_WC from TNMASTER where NPA = ? and PRFX_CD = ?)";
 
		try {
		ps = conn.getConnection().prepareStatement(SQLstatement);
		ps.setString(1, limNpa);
		ps.setString(2, limNxx);
		ps.setString(3, npa);
		ps.setString(4, prefix);
		rs = ps.executeQuery();

		if (rs.next())
			{
			wireCenter = rs.getString(1);
		} else
			throw new SQLException("No row found");
			
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());

	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
		}
	}

	return wireCenter;
}

/**
 * Insert the method's description here.
 * Creation date: (2/3/03 3:55:04 PM)
 */
public String getWireCenter(
	String npa,
	String prefix,
	DBConnection conn,
	String limNpaNxx)
	throws SQLException {

	PreparedStatement ps = null;
	ResultSet rs = null;
 	System.out.println("Inside TnMasterTable:getWireCenter() : Value of LimNPANXX: " + limNpaNxx);
	String wireCenter = null;
	String SQLstatement =
		"select distinct SOAC_WC from TNMASTER where SOAC_WC = ? and SOAC_WC in (select distinct SOAC_WC from TNMASTER where NPA = ? and PRFX_CD = ?)";
 
		try {
		ps = conn.getConnection().prepareStatement(SQLstatement);
		ps.setString(1, limNpaNxx);
		ps.setString(2, npa);
		ps.setString(3, prefix);
		rs = ps.executeQuery();
		
		if (rs.next())
			{
			wireCenter = rs.getString(1);
			System.out.println("Retrieved wireCenter from TNMASTER using SOAC_WC column : " + wireCenter);								
		} else
			throw new SQLException("No row found");
			
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());

	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
		}
	}

	return wireCenter;
}


/**
 * Insert the method's description here.
 * Creation date: (8/14/01 1:47:04 PM)
 */

public ArrayList getWireCenterBySwitchCLLI(String clli, DBConnection conn)
	throws SQLException {

	PreparedStatement ps = null;
	ResultSet rs = null;
	String row = null;
	ArrayList rowsV = null;

	String SQLstatement =
		"select distinct soac_wc from TNMASTER where substr(CLLC_CD,1,8) = ?";

	try {
		ps = conn.getConnection().prepareStatement(SQLstatement);
		ps.setString(1, clli.substring(0, 8));

		rs = ps.executeQuery();

		while (rs.next()) {
			if (rowsV == null)
				rowsV = new ArrayList();

			rowsV.add(rs.getString(1));
		}

	} catch (SQLException e) {
		throw new SQLException(
			"Get wire center by CLLI code failed: " + e.getMessage());

	} finally {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();

		} catch (SQLException e) {
		}
	}

	if (rowsV == null)
		throw new SQLException("No wire center row found for CLLI.");

	return rowsV;
}
	/*
	 *  This will get a list of wire centers based on the npa nxx and line.
	 *  
	*/

	public ArrayList getWireCenter(
		String aNPA,
		String aNXX,
		int aLine,
		DBConnection conn)
		throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList rowsV = null;

		String SQLstatement =
			"select  soac_wc, tn_ln_low_rnge from tnmaster "
				+ "where ((npa = ? and prfx_cd = ?) "
				+ "and (tn_ln_low_rnge <= ? and tn_ln_upr_rnge >= ?)) order by tn_ln_low_rnge desc";

		try {
			ps = conn.getConnection().prepareStatement(SQLstatement);
			ps.setString(1, aNPA);
			ps.setString(2, aNXX);
			ps.setInt(3, aLine);
			ps.setInt(4, aLine);

			rs = ps.executeQuery();

			String duplicate = "";
			while (rs.next()) {
				if (rowsV == null)
					rowsV = new ArrayList();

				rowsV.add(rs.getString(1));
			}

		} catch (SQLException e) {
			throw new SQLException(
				"Get wire center by TN code failed: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
			}
		}

		if (rowsV == null)
			throw new SQLException("No wire center rows found for npa, nxx and line: <" + aNPA + aNXX + aLine + ">");

		return rowsV;
	}

/**
 * Insert the method's description here.
 * Creation date: (6/4/01 2:04:21 PM)
 */
public TnMasterRow setRow(ResultSet rs, TnMasterRow row)
	throws SQLException {
 
	try {
		row.setNpa(rs.getString(1));
		row.setPrfxCd(rs.getString(2));
		row.setTnLnLowRnge(rs.getInt(3));
		row.setTnLnUprRnge(rs.getInt(4));		
		row.setExco(rs.getString(5));
		row.setSoacWc(rs.getString(6));
		row.setCosmosWc(rs.getString(7));
		row.setSwitchEntity(rs.getString(8));
		row.setImsRegn(rs.getString(9));
		row.setStateCd(rs.getString(10));
		row.setHostRegnCd(rs.getString(11));
		row.setSwitchHostRegnCd(rs.getString(12));
		row.setSwitchCnvsnSw(rs.getString(13));
		row.setSwitchCnvsnDtTm(rs.getDate(14));
		row.setCllcCd(rs.getString(15));
	} catch (SQLException e) {
		throw new SQLException(e.getMessage());
	} 
	return row;
}
}
