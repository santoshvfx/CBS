//$Id: CvoipOrderRow.java,v 1.1 2006/08/17 17:33:22 ml2917 Exp $
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
//# 05/2006	   Jon Costa			  Creation.

package com.sbc.eia.bis.rm.database;

/**
 * @author jc1421
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CvoipOrderRow
{
	private java.lang.String SOAC_SO_NBR = "";
	private java.lang.String TN = "";
	private java.lang.String UPDATE_DATE = "";

	public CvoipOrderRow()
	{}
	
	public CvoipOrderRow(String aSOAC_SO_NBR, String aTN, String aUPDATE_DATE)
	{
		this.setSOAC_SO_NBR(aSOAC_SO_NBR);
		this.setTN(aTN);
		this.setUPDATE_DATE(aUPDATE_DATE);
	}
	
	/**
	 * Returns the sOAC_SO_NBR.
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
	public java.lang.String getTN()
	{
		return TN;
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
	public void setTN(java.lang.String tN)
	{
		TN = tN;
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
