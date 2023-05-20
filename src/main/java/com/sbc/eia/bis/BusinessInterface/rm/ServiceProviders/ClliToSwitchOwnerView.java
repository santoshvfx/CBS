

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
//# 8/17/04     Jinmin Ni       RM141220: OCN by clli inquiry
//# 2/14/07		Deepti			DR170206:Added retry logic to database connection

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;
/** 
 * Created on Jul 21, 2004
 * @author Jinmin Ni
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.nam.database.DBTransactions;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumber;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;

public class ClliToSwitchOwnerView extends Table {
	public ClliToSwitchOwnerView() {
		super();
	}

	public ArrayList getOcnByClli(
		Hashtable props,
		Utility aUtility,
		String aClli)
		throws SQLException, ClliToSwitchOwnerNotFoundException {

		String myMethodName = "ClliToSwitchOwnerView::getOcnByClli()";
		aUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		PreparedStatement ps = null;
		ResultSet rs = null;
		String SQLstatement =
			"select TO_DATE(EFF_DATE,'MMDDYY'), STATUS,OCN_ID, OCN_NAME from CLLI_TO_SWITCH_OWNER where CLLI = ? order by TO_DATE(EFF_DATE,'MMDDYY') DESC NULLS LAST";

		DBTransactions tranx = new DBTransactions();
		ArrayList retVal = new ArrayList();
		aUtility.log(
			LogEventId.DEBUG_LEVEL_2,
			"SQLstatement = <" + SQLstatement + ">");

		int reTryCnt = 0;
		while (true) {
			try {

				ps =
					tranx.getConnection(props, aUtility).prepareStatement(
						SQLstatement);
				ps.setString(1, aClli);
				rs = ps.executeQuery();
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"rows from database table: ");
				while (rs.next()) {
					ClliToSwitchOwnerRow row = new ClliToSwitchOwnerRow();
					row.setEff_date(rs.getDate(1));
					row.setStatus(rs.getString(2));
					row.setOcn(rs.getString(3));
					row.setOcn_name(rs.getString(4));
					aUtility.log(LogEventId.DEBUG_LEVEL_2, "record=" + row);
					retVal.add(row);
				}
				if (retVal.size() < 1) {
					throw new ClliToSwitchOwnerNotFoundException(
						"CLLI: "
							+ aClli
							+ " was not found in CLLI_TO_SWITCH_OWNER table for OCN inquiry");
				}
			} catch (StaleConnectionException e) {
				//retry only once

				if (reTryCnt == 0) {
					reTryCnt++;
					tranx = null;
					aUtility.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw e;
			} catch (SQLException e) {
				throw e;
			} finally {
				aUtility.log(
					LogEventId.DEBUG_LEVEL_1,
					" Exiting from " + myMethodName);
				try {
					rs.close();
				} catch (Exception e) {
				}
				try {
					ps.close();
				} catch (Exception e) {
				}
				try {
					tranx.disconnect();
				} catch (Exception e) {
				}
				rs = null;
				ps = null;
				tranx = null;

			}
			return retVal;
		}
	}
}