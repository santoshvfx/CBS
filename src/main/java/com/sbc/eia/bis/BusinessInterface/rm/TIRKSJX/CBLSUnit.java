//$Id: CBLSUnit.java,v 1.2 2011/04/07 02:51:17 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

/**
 * Contains the logic for handling the Unit field from the CBLS response
 * 
 * @author js7440
 *
 */
public class CBLSUnit 
{
	private int instance;
	private String unit = null;
	private String cktid_clo = null;
	private String cktid_format = null;
	private String activity_cur = null;
	private String activity_pnd = null;
	private String due_date = null;
	private String due_date_mo = null;
	private String due_date_yr = null;
	private String due_date_day = null;
	
	/**
	 * get the current activity
	 * 
	 * @return String
	 */
	public String getActivity_cur() 
	{
		return activity_cur;
	}
	
	/**
	 * set the current activity
	 * 
	 * @param String activity_cur
	 */
	public void setActivity_cur(String activity_cur) 
	{
		this.activity_cur = activity_cur;
	}
	
	/**
	 * get the pending activity
	 * 
	 * @return String
	 */
	public String getActivity_pnd() 
	{
		return activity_pnd;
	}
	
	/**
	 * set the pending activity
	 * 
	 * @param String activity_pnd
	 */
	public void setActivity_pnd(String activity_pnd) 
	{
		this.activity_pnd = activity_pnd;
	}
	
	/**
	 * get the circuit id
	 * 
	 * @return String
	 */
	public String getCktid_clo() 
	{
		return cktid_clo;
	}
	
	/**
	 * set the circuit id
	 * 
	 * @param String cktid_clo
	 */
	public void setCktid_clo(String cktid_clo) 
	{
		this.cktid_clo = cktid_clo;
	}
	
	/**
	 * get the circuit id format
	 * 
	 * @return String
	 */
	public String getCktid_format() 
	{
		return cktid_format;
	}
	
	/**
	 * set the circuit id format
	 * 
	 * @param String cktid_format
	 */
	public void setCktid_format(String cktid_format) 
	{
		this.cktid_format = cktid_format;
	}
	
	/**
	 * get the due date
	 * 
	 * @return String
	 */
	public String getDue_date() 
	{
		return due_date;
	}
	
	/**
	 * set the due date
	 * 
	 * @param String due_date
	 */
	public void setDue_date(String due_date) 
	{
		this.due_date = due_date;
	}
	
	/**
	 * get the instance
	 * 
	 * @return int
	 */
	public int getInstance() 
	{
		return instance;
	}
	
	/**
	 * set the instance
	 * 
	 * @param int instance 
	 */
	public void setInstance(int instance) 
	{
		this.instance = instance;
	}
	
	/**
	 * get the unit
	 * 
	 * @return String
	 */
	public String getUnit() 
	{
		return unit;
	}
	
	/**
	 * set the unit
	 * 
	 * @param String unit
	 */
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
	
	/**
	 * get the due date day
	 * 
	 * @return String
	 */
	public String getDue_date_day() 
	{
		return due_date_day;
	}
	
	/**
	 * set the due date day
	 * 
	 * @param String due_date_day
	 */
	public void setDue_date_day(String due_date_day) 
	{
		this.due_date_day = due_date_day;
	}
	
	/**
	 * get the due date month
	 * 
	 * @return String
	 */
	public String getDue_date_mo() 
	{
		return due_date_mo;
	}
	
	/**
	 * set the due date
	 * 
	 * @param String due_date_mo
	 */
	public void setDue_date_mo(String due_date_mo) 
	{
		this.due_date_mo = due_date_mo;
	}
	
	/**
	 * get the due date year
	 * 
	 * @return String
	 */
	public String getDue_date_yr() 
	{
		return due_date_yr;
	}
	
	/**
	 * set the due date year
	 * 
	 * @param String due_date_yr
	 */
	public void setDue_date_yr(String due_date_yr) 
	{
		this.due_date_yr = due_date_yr;
	}
}
