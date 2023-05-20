//$Id: CxrsOutputFields.java,v 1.2 2011/04/07 03:00:37 rs278j Exp $
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
 * Holds the values of a CXRS Output field
 * Creation date: (01/13/2011)
 * @author: Julius Sembrano
 */
public class CxrsOutputFields 
{
	private String channel_pair;
	private String asgt_rstn;
	private String cur_act;
	private String inv_stat;
	private String d;
	private String circuit_id;
	private String pend_act;
	private com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data;
	private String due_date_month;
	private String due_date_day;
	private String due_date_year;
	private int ws_flag;
	private int instance;
	
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getAsgt_rstn() 
	{
		return asgt_rstn;
	}
	/**
	 * set IMS region.
	 * Creation date: (01/13/2011)
	 * @param asgt_rstn java.lang.String
	 */
	public void setAsgt_rstn(String asgt_rstn) 
	{
		this.asgt_rstn = asgt_rstn;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getChannel_pair() 
	{
		return channel_pair;
	}
	public void setChannel_pair(String channel_pair) 
	{
		this.channel_pair = channel_pair;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getCircuit_id() 
	{
		return circuit_id;
	}
	public void setCircuit_id(String circuit_id) 
	{
		this.circuit_id = circuit_id;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getCur_act() 
	{
		return cur_act;
	}
	public void setCur_act(String cur_act) 
	{
		this.cur_act = cur_act;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getD() 
	{
		return d;
	}
	public void setD(String d) 
	{
		this.d = d;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t
	 */
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t getDue_date_data() 
	{
		return due_date_data;
	}
	public void setDue_date_data(
			com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data) 
	{
		this.due_date_data = due_date_data;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getInv_stat() 
	{
		return inv_stat;
	}
	public void setInv_stat(String inv_stat) 
	{
		this.inv_stat = inv_stat;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getPend_act() 
	{
		return pend_act;
	}
	public void setPend_act(String pend_act) 
	{
		this.pend_act = pend_act;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public int getWs_flag() 
	{
		return ws_flag;
	}
	public void setWs_flag(int ws_flag) 
	{
		this.ws_flag = ws_flag;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.int
	 */
	public int getInstance() 
	{
		return instance;
	}
	public void setInstance(int instance) 
	{
		this.instance = instance;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getDue_date_day() 
	{
		return due_date_day;
	}
	public void setDue_date_day(String due_date_day) 
	{
		this.due_date_day = due_date_day;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getDue_date_month() 
	{
		return due_date_month;
	}
	public void setDue_date_month(String due_date_month) 
	{
		this.due_date_month = due_date_month;
	}
	/**
	 * Creation date: (01/11/2011)
	 * @return java.lang.String
	 */
	public String getDue_date_year() 
	{
		return due_date_year;
	}
	public void setDue_date_year(String due_date_year) 
	{
		this.due_date_year = due_date_year;
	}
}
