

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
//# 8/17/04     Jinmin Ni       RM141220:  OCN by clli inquiry

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

import java.sql.Date;

/**
 * Created on Jul 21, 2004
 * @author Jinmin Ni
 */

public class ClliToSwitchOwnerRow
{
  
    private String ocn;
    private String ocn_name;
	private Date eff_date;
    private String status;

	/**
	 * Returns the ocn.
	 * @return String
	 */
	public String getOcn()
	{
		return ocn;
	}

	/**
	 * Returns the ocn_name.
	 * @return String
	 */
	public String getOcn_name()
	{
		return ocn_name;
	}

	/**
	 * Sets the ocn.
	 * @param ocn The ocn to set
	 */
	public void setOcn(String ocn)
	{
		this.ocn = ocn;
	}

	/**
	 * Sets the ocn_name.
	 * @param ocn_name The ocn_name to set
	 */
	public void setOcn_name(String ocn_name)
	{
		this.ocn_name = ocn_name;
	}

    public String toString(){
         return eff_date+"|"+status+"|"+ocn + "|" + ocn_name;  
    }
	/**
	 * Returns the eff_date.
	 * @return String
	 */
	public Date getEff_date()
	{
		return eff_date;
	}

	/**
	 * Returns the status.
	 * @return String
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the eff_date.
	 * @param eff_date The eff_date to set
	 */
	public void setEff_date(Date eff_date)
	{
		this.eff_date = eff_date;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

}