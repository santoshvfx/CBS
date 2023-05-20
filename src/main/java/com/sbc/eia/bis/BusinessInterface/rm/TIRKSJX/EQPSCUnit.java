//$Id: EQPSCUnit.java,v 1.2 2011/04/07 03:04:41 rs278j Exp $
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
 * Class for handling the EPQSC Unit Structure
 * 
 * @author js7440
 */
public class EQPSCUnit 
{
	private int instance;
	private String unit = null;
	private String circuitid = null;
	private String curact = null;
	private String pendact = null;
	private String duedate = null;
	
	/**
	 * get tje Circuit ID
	 * 
	 * @return String
	 */
	public String getCircuitid() 
	{
		return circuitid;
	}
	
	/**
	 * set the Circuit ID
	 * 
	 * @param String circuitid
	 */
	public void setCircuitid(String circuitid) 
	{
		this.circuitid = circuitid;
	}
	
	/**
	 * get the Current Activity Indicator
	 * 
	 * @return String
	 */
	public String getCuract() 
	{
		return curact;
	}
	
	/**
	 * set the Current Activity Indicator
	 * 
	 * @param String curact
	 */
	public void setCuract(String curact) 
	{
		this.curact = curact;
	}
	
	/**
	 * get the Due Date
	 * 
	 * @return String
	 */
	public String getDuedate() 
	{
		return duedate;
	}
	
	/**
	 * set the Due Date
	 * 
	 * @param duedate String
	 */
	public void setDuedate(String duedate) 
	{
		this.duedate = duedate;
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
	 * get the Pending Activity Indicator
	 * 
	 * @return String
	 */
	public String getPendact() 
	{
		return pendact;
	}
	
	/**
	 * set the Pending Activity Indicator
	 * 
	 * @param String pendact
	 */
	public void setPendact(String pendact) 
	{
		this.pendact = pendact;
	}
	
	/**
	 * get the Unit
	 * 
	 * @return String
	 */
	public String getUnit() 
	{
		return unit;
	}
	
	/**
	 * set the Unit
	 * 
	 * @param String unit
	 */
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
}
