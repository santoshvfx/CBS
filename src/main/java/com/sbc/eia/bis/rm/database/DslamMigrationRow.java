// $Id: DslamMigrationRow.java,v 1.3.2.1 2007/10/18 21:48:13 ra0331 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/23/2007	  Vickie Ng			  Creation.
//# 09/26/2007    Rommel Baldivas     Change variable effectiveEndtDate to effectiveEndDate.

package com.sbc.eia.bis.rm.database;

import java.util.Date;

public class DslamMigrationRow {
	private String dslamId = "";
	private Date effectiveStartDate = null;
	private Date effectiveEndDate = null;	
	
	public DslamMigrationRow()
	{}
	
	public DslamMigrationRow(String aDslamId, Date aEffectiveStartDate, Date aEffectiveEndDate)
	{
		this.setDslamId(aDslamId);
		this.setEffectiveStartDate(aEffectiveStartDate);
		this.setEffectiveEndDate(aEffectiveEndDate);
	}

	/**
     * @return Returns the dslamId.
     */
    public java.lang.String getDslamId() {
        return dslamId;
    }

    /**
     * @param dslamId The dslamId to set.
     */
    public void setDslamId(java.lang.String dslamId) {
        this.dslamId = dslamId;
    }
	
    /**
	 * @return Returns the effectiveEndDate.
	 */
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	
	/**
	 * @param effectiveEndDate The effectiveEndDate to set.
	 */
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	
	/**
	 * @return Returns the effectiveStartDate.
	 */
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
	
	/**
	 * @param effectiveStartDate The effectiveStartDate to set.
	 */
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
}
