//$Id: CvoipRulesRow.java,v 1.1 2006/08/17 17:40:27 ml2917 Exp $
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

public class CvoipRulesRow
{
	private java.lang.String ORDER_ACTION_TYPE = "";
	private java.lang.String ORDER_ACTION_SUBTYPE = "";
	private java.lang.String ACTION_IND = "";
	private java.lang.String ACTIVITY_IND = "";
	private java.lang.Boolean ORDER_EXISTS = null;
	private java.lang.Boolean TN_EXISTS = null;
	private java.lang.String SOAC_FUNC_TYPE = "";
	private java.lang.String SOAC_ACTION_IND = "";
	private java.lang.Boolean HIPCS_SWITCH_UPD = null;
	private java.lang.String SWITCH_ACTION_IND = "";
	private java.lang.String ORDER_TABLE_ACTION = "";
	private java.lang.String UPDATE_DATE = "";

	public CvoipRulesRow()
	{}

	public CvoipRulesRow(
		String aORDER_ACTION_TYPE,
		String aORDER_ACTION_SUBTYPE,
		String aACTION_IND,
		String aACTIVITY_IND,
		String aORDER_EXISTS,
		String aTN_EXISTS,
		String aSOAC_FUNC_TYPE,
		String aSOAC_ACTION_IND,
		String aHIPCS_SWITCH_UPD,
		String aSWITCH_ACTION_IND,
		String aORDER_TABLE_ACTION,
		String aUPDATE_DATE)
	{
		this.setORDER_ACTION_TYPE(aORDER_ACTION_TYPE);
		this.setORDER_ACTION_SUBTYPE(aORDER_ACTION_SUBTYPE);
		this.setACTION_IND(aACTION_IND);
		this.setACTIVITY_IND(aACTIVITY_IND);
		this.setORDER_EXISTS(aORDER_EXISTS);
		this.setTN_EXISTS(aTN_EXISTS);
		this.setSOAC_FUNC_TYPE(aSOAC_FUNC_TYPE);
		this.setSOAC_ACTION_IND(aSOAC_ACTION_IND);
		this.setHIPCS_SWITCH_UPD(aHIPCS_SWITCH_UPD);
		this.setSWITCH_ACTION_IND(aSWITCH_ACTION_IND);
		this.setORDER_TABLE_ACTION(aORDER_TABLE_ACTION);
		this.setUPDATE_DATE(aUPDATE_DATE);
	}

	/**
	 * Returns the aCTION_IND.
	 * @return java.lang.String
	 */
	public java.lang.String getACTION_IND()
	{
		return ACTION_IND;
	}

	/**
	 * Returns the aCTIVITY_IND.
	 * @return java.lang.String
	 */
	public java.lang.String getACTIVITY_IND()
	{
		return ACTIVITY_IND;
	}

	/**
	 * Returns the hIPCS_SWITCH_UPD.
	 * @return java.lang.Boolean
	 */
	public java.lang.Boolean getHIPCS_SWITCH_UPD()
	{
		return HIPCS_SWITCH_UPD;
	}

	/**
	 * Returns the oRDER_ACTION_SUBTYPE.
	 * @return java.lang.String
	 */
	public java.lang.String getORDER_ACTION_SUBTYPE()
	{
		return ORDER_ACTION_SUBTYPE;
	}

	/**
	 * Returns the oRDER_ACTION_TYPE.
	 * @return java.lang.String
	 */
	public java.lang.String getORDER_ACTION_TYPE()
	{
		return ORDER_ACTION_TYPE;
	}

	/**
	 * Returns the oRDER_EXISTS.
	 * @return java.lang.Boolean
	 */
	public java.lang.Boolean getORDER_EXISTS()
	{
		return ORDER_EXISTS;
	}

	/**
	 * Returns the oRDER_TABLE_ACTION.
	 * @return java.lang.String
	 */
	public java.lang.String getORDER_TABLE_ACTION()
	{
		return ORDER_TABLE_ACTION;
	}

	/**
	 * Returns the sOAC_ACTION_IND.
	 * @return java.lang.String
	 */
	public java.lang.String getSOAC_ACTION_IND()
	{
		return SOAC_ACTION_IND;
	}

	/**
	 * Returns the sOAC_FUNC_TYPE.
	 * @return java.lang.String
	 */
	public java.lang.String getSOAC_FUNC_TYPE()
	{
		return SOAC_FUNC_TYPE;
	}

	/**
	 * Returns the sWITCH_ACTION_IND.
	 * @return java.lang.String
	 */
	public java.lang.String getSWITCH_ACTION_IND()
	{
		return SWITCH_ACTION_IND;
	}

	/**
	 * Returns the tN_EXISTS.
	 * @return java.lang.Boolean
	 */
	public java.lang.Boolean getTN_EXISTS()
	{
		return TN_EXISTS;
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
	 * Sets the aCTION_IND.
	 * @param aCTION_IND The aCTION_IND to set
	 */
	public void setACTION_IND(java.lang.String aCTION_IND)
	{
		ACTION_IND = aCTION_IND;
	}

	/**
	 * Sets the aCTIVITY_IND.
	 * @param aCTIVITY_IND The aCTIVITY_IND to set
	 */
	public void setACTIVITY_IND(java.lang.String aCTIVITY_IND)
	{
		ACTIVITY_IND = aCTIVITY_IND;
	}

	/**
	 * Sets the hIPCS_SWITCH_UPD.
	 * @param hIPCS_SWITCH_UPD The hIPCS_SWITCH_UPD to set
	 */
	public void setHIPCS_SWITCH_UPD(java.lang.String hIPCS_SWITCH_UPD)
	{
		HIPCS_SWITCH_UPD = new Boolean(hIPCS_SWITCH_UPD);
	}

	/**
	 * Sets the oRDER_ACTION_SUBTYPE.
	 * @param oRDER_ACTION_SUBTYPE The oRDER_ACTION_SUBTYPE to set
	 */
	public void setORDER_ACTION_SUBTYPE(java.lang.String oRDER_ACTION_SUBTYPE)
	{
		ORDER_ACTION_SUBTYPE = oRDER_ACTION_SUBTYPE;
	}

	/**
	 * Sets the oRDER_ACTION_TYPE.
	 * @param oRDER_ACTION_TYPE The oRDER_ACTION_TYPE to set
	 */
	public void setORDER_ACTION_TYPE(java.lang.String oRDER_ACTION_TYPE)
	{
		ORDER_ACTION_TYPE = oRDER_ACTION_TYPE;
	}

	/**
	 * Sets the oRDER_EXISTS.
	 * @param oRDER_EXISTS The oRDER_EXISTS to set
	 */
	public void setORDER_EXISTS(java.lang.String oRDER_EXISTS)
	{
		ORDER_EXISTS = new Boolean(oRDER_EXISTS);
	}

	/**
	 * Sets the oRDER_TABLE_ACTION.
	 * @param oRDER_TABLE_ACTION The oRDER_TABLE_ACTION to set
	 */
	public void setORDER_TABLE_ACTION(java.lang.String oRDER_TABLE_ACTION)
	{
		ORDER_TABLE_ACTION = oRDER_TABLE_ACTION;
	}

	/**
	 * Sets the sOAC_ACTION_IND.
	 * @param sOAC_ACTION_IND The sOAC_ACTION_IND to set
	 */
	public void setSOAC_ACTION_IND(java.lang.String sOAC_ACTION_IND)
	{
		SOAC_ACTION_IND = sOAC_ACTION_IND;
	}

	/**
	 * Sets the sOAC_FUNC_TYPE.
	 * @param sOAC_FUNC_TYPE The sOAC_FUNC_TYPE to set
	 */
	public void setSOAC_FUNC_TYPE(java.lang.String sOAC_FUNC_TYPE)
	{
		SOAC_FUNC_TYPE = sOAC_FUNC_TYPE;
	}

	/**
	 * Sets the sWITCH_ACTION_IND.
	 * @param sWITCH_ACTION_IND The sWITCH_ACTION_IND to set
	 */
	public void setSWITCH_ACTION_IND(java.lang.String sWITCH_ACTION_IND)
	{
		SWITCH_ACTION_IND = sWITCH_ACTION_IND;
	}

	/**
	 * Sets the tN_EXISTS.
	 * @param tN_EXISTS The tN_EXISTS to set
	 */
	public void setTN_EXISTS(java.lang.String tN_EXISTS)
	{
		TN_EXISTS = new Boolean(tN_EXISTS);
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