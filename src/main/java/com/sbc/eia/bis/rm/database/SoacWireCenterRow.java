//$Id: SoacWireCenterRow.java,v 1.1 2006/08/17 17:40:27 ml2917 Exp $
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
//# 05/2005	   Jon Costa			  Creation

package com.sbc.eia.bis.rm.database;

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
public class SoacWireCenterRow
{
	//TYPE, HOST_NAME, CONTROLLER, ENTITY, TWO_LETTER_WC, NPANXX, CLLI8
	private java.lang.String TYPE = "";
	private java.lang.String HOST_NAME = "";
	private java.lang.String CONTROLLER = "";
	private java.lang.String ENTITY = "";
	private java.lang.String TWO_LETTER_WC = "";
	private java.lang.String NPANXX = "";
	private java.lang.String CLLI8 = "";

	/**
	 * Constructor for SoacWireCenterRow.
	 * @param rs
	 */
	public SoacWireCenterRow()
	{}
	
	/**
		 * Returns the CONTROLLER.
		 * @return java.lang.String
		 */
	public java.lang.String getCONTROLLER()
	{
		return CONTROLLER;
	}

	/**
	 * Returns the cLLI8.
	 * @return java.lang.String
	 */
	public java.lang.String getCLLI8()
	{
		return CLLI8;
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
	 * Returns the hOST_NAME.
	 * @return java.lang.String
	 */
	public java.lang.String getHOST_NAME()
	{
		return HOST_NAME;
	}

	/**
	 * Returns the nPANXX.
	 * @return java.lang.String
	 */
	public java.lang.String getNPANXX()
	{
		return NPANXX;
	}

	/**
	 * Returns the tWO_LETTER_WC.
	 * @return java.lang.String
	 */
	public java.lang.String getTWO_LETTER_WC()
	{
		return TWO_LETTER_WC;
	}

	/**
	 * Returns the tYPE.
	 * @return java.lang.String
	 */
	public java.lang.String getTYPE()
	{
		return TYPE;
	}

	/**
	 * Method setRow.
	 * @param rs
	 */
	public void setRow(ResultSet rs)
	{
		try
		{
			this.setTYPE(			rs.getString(1));
			this.setHOST_NAME(		rs.getString(2));
			this.setCONTROLLER(	rs.getString(3));
			this.setENTITY(		rs.getString(4));
			this.setTWO_LETTER_WC(	rs.getString(5));
			this.setNPANXX(		rs.getString(6));
			this.setCLLI8(			rs.getString(7));
		}
		catch (SQLException e)
		{}
	}

	/**
	 * Sets the CONTROLLER.
	 * @param CONTROLLER The CONTROLLER to set
	 */
	public void setCONTROLLER(java.lang.String cONTROLLER)
	{
		CONTROLLER = cONTROLLER;
	}
	
	/**
	 * Sets the cLLI8.
	 * @param cLLI8 The cLLI8 to set
	 */
	public void setCLLI8(java.lang.String cLLI8)
	{
		CLLI8 = cLLI8;
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
	 * Sets the hOST_NAME.
	 * @param hOST_NAME The hOST_NAME to set
	 */
	public void setHOST_NAME(java.lang.String hOST_NAME)
	{
		HOST_NAME = hOST_NAME;
	}

	/**
	 * Sets the nPANXX.
	 * @param nPANXX The nPANXX to set
	 */
	public void setNPANXX(java.lang.String nPANXX)
	{
		NPANXX = nPANXX;
	}

	/**
	 * Sets the tWO_LETTER_WC.
	 * @param tWO_LETTER_WC The tWO_LETTER_WC to set
	 */
	public void setTWO_LETTER_WC(java.lang.String tWO_LETTER_WC)
	{
		TWO_LETTER_WC = tWO_LETTER_WC;
	}

	/**
	 * Sets the tYPE.
	 * @param tYPE The tYPE to set
	 */
	public void setTYPE(java.lang.String tYPE)
	{
		TYPE = tYPE;
	}
}
