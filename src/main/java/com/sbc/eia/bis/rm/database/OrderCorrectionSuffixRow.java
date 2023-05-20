//$Id: OrderCorrectionSuffixRow.java,v 1.1 2008/07/29 17:06:23 ds4987 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 06/13/2008	Doris Sunga  	      Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ds4987
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class OrderCorrectionSuffixRow
{
	private java.lang.String SOAC_SO_NBR = "";
	private java.lang.String SOAC_CORRECTION_SUFFIX = "";
	private java.lang.String UPDATE_DATE = "";

	/**
	 * Constructor
	 */
	public OrderCorrectionSuffixRow()
	{}

	/**
	 * Returns the SOAC_SO_NBR.
	 * @return java.lang.String
	 */
	public java.lang.String getSOAC_SO_NBR()
	{
		return SOAC_SO_NBR;
	}

	/**
	 * Returns the tN.
	 * @return java.lang.String
	 */
	public java.lang.String getSOAC_CORRECTION_SUFFIX()
	{
		return SOAC_CORRECTION_SUFFIX;
	}

	/**
	 * Returns the uPDATE_DATE.
	 * @return java.lang.String
	 */
	public java.lang.String getUPDATE_DATE()
	{
		return UPDATE_DATE;
	}

	/**
	 * Method setRow.
	 * @param rs
	 */
	public void setRow(ResultSet rs)
	{
		try
		{			
			this.setSOAC_SO_NBR(rs.getString(1));
			this.setSOAC_CORRECTION_SUFFIX(rs.getString(2));
			this.setUPDATE_DATE(rs.getString(3));			
		}
		catch (SQLException e)
		{}
	}	
	
	/**
	 * Sets the sOAC_SO_NBR.
	 * @param sOAC_SO_NBR The sOAC_SO_NBR to set
	 */
	public void setSOAC_SO_NBR(java.lang.String sOAC_SO_NBR)
	{
		SOAC_SO_NBR = sOAC_SO_NBR;
	}

	/**
	 * Sets the tN.
	 * @param tN The tN to set
	 */
	public void setSOAC_CORRECTION_SUFFIX(java.lang.String sOAC_CORRECTION_SUFFIX)
	{
		SOAC_CORRECTION_SUFFIX = sOAC_CORRECTION_SUFFIX;
	}

	/**
	 * Sets the uPDATE_DATE.
	 * @param uPDATE_DATE The uPDATE_DATE to set
	 */
	public void setUPDATE_DATE(java.lang.String uPDATE_DATE)
	{
		UPDATE_DATE = uPDATE_DATE;
	}
}
