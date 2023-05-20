//$Id:$
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 11/2005	   Jon Costa			  Creation
//# 05/2006	   Jon Costa			  Changes for LS3.

package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TopListenerSoacLinkRow
{
	//(HOST_NAME, ENTITY) = KEY
	private java.lang.String HOST_NAME = "";
	private java.lang.String ENTITY = "";
	private java.lang.String TELCO_APPLDATA = "";
	private java.lang.String CVOIP_APPLDATA = "";
	private java.lang.String ORIGINATOR = "";
	private java.lang.String REGION = "";
	private java.lang.String LTERM = "";

	/**
	 * Constructor for TopListenerSoacLinkRow.
	 * @param rs
	 */
	public TopListenerSoacLinkRow()
	{}

	/**
	 * Returns the hOST_NAME.
	 * @return java.lang.String
	 */
	public java.lang.String getHOST_NAME()
	{
		return HOST_NAME;
	}

	/**
	 * Returns the eNTITY.
	 * @return java.lang.String
	 */
	public java.lang.String getENTITY()
	{
		return ENTITY;
	}

	/**
	 * Returns the TELCO_APPLDATA.
	 * @return java.lang.String
	 */
	public java.lang.String getTELCO_APPLDATA()
	{
		return TELCO_APPLDATA;
	}

	/**
	 * Returns the CVOIP_APPLDATA.
	 * @return java.lang.String
	 */
	public java.lang.String getCVOIP_APPLDATA()
	{
		return CVOIP_APPLDATA;
	}

	/**
	 * Returns the oRIGINATOR.
	 * @return java.lang.String
	 */
	public java.lang.String getORIGINATOR()
	{
		return ORIGINATOR;
	}

	/**
	 * Returns the REGION.
	 * @return java.lang.String
	 */
	public java.lang.String getREGION()
	{
		return REGION;
	}

	/**
	 * Returns the lTERM.
	 * @return java.lang.String
	 */
	public java.lang.String getLTERM()
	{
		return LTERM;
	}

	/**
	 * Sets the hOST_NAME.
	 * @param hOST_NAME The hOST_NAME to set
	 */
	public void setHOST_NAME(java.lang.String hOST_NAME)
	{
		HOST_NAME = hOST_NAME;
	}

	/**
	 * Sets the eNTITY.
	 * @param eNTITY The eNTITY to set
	 */
	public void setENTITY(java.lang.String eNTITY)
	{
		ENTITY = eNTITY;
	}

	/**
	 * Sets the tELCO_APPLDATA.
	 * @param tELCO_APPLDATA The tELCO_APPLDATA to set
	 */
	public void setTELCO_APPLDATA(java.lang.String tELCO_APPLDATA)
	{
		TELCO_APPLDATA = tELCO_APPLDATA;
	}

	/**
	 * Sets the cVOIP_APPLDATA.
	 * @param cVOIP_APPLDATA The cVOIP_APPLDATA to set
	 */
	public void setCVOIP_APPLDATA(java.lang.String cVOIP_APPLDATA)
	{
		CVOIP_APPLDATA = cVOIP_APPLDATA;
	}

	/**
	 * Sets the oRIGINATOR.
	 * @param oRIGINATOR The ORIGINATOR to set
	 */
	public void setORIGINATOR(java.lang.String oRIGINATOR)
	{
		ORIGINATOR = oRIGINATOR;
	}

	/**
	 * Sets the REGION.
	 * @param rEGION The REGION to set
	 */
	public void setREGION(java.lang.String rEGION)
	{
		REGION = rEGION;
	}

	/**
	 * Sets the lTERM.
	 * @param lTERM The LTERM to set
	 */
	public void setLTERM(java.lang.String lTERM)
	{
		LTERM = lTERM;
	}

	/**
	 * Method setRow.
	 * @param rs
	 */
	public void setRow(ResultSet rs)
	{
		try
		{
			this.setHOST_NAME(			rs.getString(1));
			this.setENTITY(			rs.getString(2));
			this.setTELCO_APPLDATA(	rs.getString(3));
			this.setCVOIP_APPLDATA(	rs.getString(4));
			this.setORIGINATOR(		rs.getString(5));
			this.setREGION(			rs.getString(6));
			this.setLTERM(				rs.getString(7));
		}
		catch (SQLException e)
		{}
	}
}
