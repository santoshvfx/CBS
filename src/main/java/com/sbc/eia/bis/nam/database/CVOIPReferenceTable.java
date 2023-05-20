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
//#      Copyright (C) 2006 SBC Services, Inc.
//#      All Rights Reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/02/06   Vinod Rachapudi		Initial Creation for Lightspeed 3. This class contains methods 
//#                                 for table CVOIP_Reference table
//# 02/20/06   Deepti				DR170206:Added retry logic to database connection.
//# 03/23/07	Jon Costa			DR62548: Added loop exit to retry logic.

package com.sbc.eia.bis.nam.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;

public class CVOIPReferenceTable {
	protected Utility utility = null;
	protected Hashtable props = null;

	public CVOIPReferenceTable() {
		super();
	}

	/**
	 * Constructor
	 * @param utility
	 * @param props
	 */
	public CVOIPReferenceTable(Utility utility, Hashtable props) {
		this.utility = utility;
		this.props = props;
	}

	/** 
	 * checkDB()::  This method is used to check if the oracle database is up.
	 * 				It will be called in LS methods before calling QIP.
	 *				it returns a flag. 
	 */
	public boolean checkDB() throws SQLException {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		boolean dBFlag = false;

		DBTransactions tranx = new DBTransactions();
		String SQLstatement = "select sysdate from dual";
		int reTryCnt = 0;
		while (true) {
			try {
				conn = tranx.getConnection(props);
				ps = conn.prepareStatement(SQLstatement);

				rs = ps.executeQuery();

				if (rs.next()) //will have one row only
					dBFlag = true;

			} catch (StaleConnectionException e) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					conn = null;
					utility.log(
						TranBase.LOG_INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw e;
			} catch (SQLException e) {
				utility.log(
					TranBase.LOG_DEBUG_LEVEL_2,
					"In checkDB(): Database is down.");
			} finally {
				try {
					rs.close();
				} catch (Exception e) {
				}
				try {
					ps.close();
				} catch (Exception e) {
				}
			}

			return dBFlag;

		}
	} // End of checkDB()

	//	TODO - LS R3 Coding by MK2647
	/**
	 * getWireCenterAndTnListId():: find a distinct row which has the given rate center and state code 
	 * @param row
	 * @author mk2647
	 */
	public void getWireCenterAndTnListId(CVOIPReferenceRow row) throws SQLException
	{
		DBTransactions tranx = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQLstatement =
			"select "
				+ "MULTI_ESRN_INDICATOR, "
				+ "ESRN, "
				+ "NPA, "
				+ "RATE_CENTER, "
				+ "STATE_CD, "
				+ "TN_LIST_ID, "
				+ "LIGHTSPEED_LRN, "
				+ "CVOIP_SOAC_SWITCH_WC, "
				+ "CVOIP_SOAC_ENTITY, "
				+ "CVOIP_SWITCH_ENTITY "
				+ "from CVOIP_REFERENCE where RATE_CENTER = ? and STATE_CD = ?";
		int reTryCnt = 0;
		while (reTryCnt++ < 2)
		{
			try
			{
				tranx = new DBTransactions();
				conn = tranx.getConnection(props);
				ps = conn.prepareStatement(SQLstatement);
				ps.setString(1, row.getRateCenter());
				ps.setString(2, row.getStateCd());

				rs = ps.executeQuery();
				if (rs.next()) //will have one row only
				{
					// Populate object provided by caller
					row.setMultiEsrnIndicator(rs.getString("MULTI_ESRN_INDICATOR"));
					row.setEsrn(rs.getString("ESRN"));
					row.setNpa(rs.getString("NPA"));
					row.setTnListId(rs.getString("TN_LIST_ID"));
					row.setLightspeedLrn(rs.getString("LIGHTSPEED_LRN"));
					row.setCvoipSoacSwitchWc(rs.getString("CVOIP_SOAC_SWITCH_WC"));
					row.setCvoipSoacEntity(rs.getString("CVOIP_SOAC_ENTITY"));
					row.setCvoipSwitchEntity(rs.getString("CVOIP_SWITCH_ENTITY"));
					reTryCnt++;
				}
				else
					throw new SQLException(
						"No row found for Rate Center = "
							+ row.getRateCenter()
							+ ", State = "
							+ row.getStateCd());
			}
			catch (StaleConnectionException e)
			{
				//retry only once
				if (reTryCnt == 2)
				{
					throw e;
				}
				utility.log(TranBase.LOG_INFO_LEVEL_2, "*****Getting Connection second time*****");
			}
			catch (SQLException e)
			{
				throw new SQLException(
					"Failed to fetch record from CVOIP getWireCenterAndTnListId(): "
						+ e.getMessage());
			}
			finally
			{
				try
				{
					if (rs != null)
						rs.close();
				}
				catch (Exception e)
				{}
				try
				{
					if (ps != null)
						ps.close();
				}
				catch (Exception e)
				{}
				try
				{
					if (conn != null)
						conn.close();
				}
				catch (Exception e)
				{}
				try
				{
					if (tranx != null)
					{
						tranx.disconnect();
					}
				}
				catch (Exception e)
				{}
				rs = null;
				ps = null;
				conn = null;
				tranx = null;
			}
		}
	}
}
