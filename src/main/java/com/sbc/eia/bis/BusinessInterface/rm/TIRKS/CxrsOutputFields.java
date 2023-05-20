package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

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
	
	public String getAsgt_rstn() {
		return asgt_rstn;
	}
	public void setAsgt_rstn(String asgt_rstn) {
		this.asgt_rstn = asgt_rstn;
	}
	public String getChannel_pair() {
		return channel_pair;
	}
	public void setChannel_pair(String channel_pair) {
		this.channel_pair = channel_pair;
	}
	public String getCircuit_id() {
		return circuit_id;
	}
	public void setCircuit_id(String circuit_id) {
		this.circuit_id = circuit_id;
	}
	public String getCur_act() {
		return cur_act;
	}
	public void setCur_act(String cur_act) {
		this.cur_act = cur_act;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t getDue_date_data() {
		return due_date_data;
	}
	public void setDue_date_data(
			com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data) {
		this.due_date_data = due_date_data;
	}
	public String getInv_stat() {
		return inv_stat;
	}
	public void setInv_stat(String inv_stat) {
		this.inv_stat = inv_stat;
	}
	public String getPend_act() {
		return pend_act;
	}
	public void setPend_act(String pend_act) {
		this.pend_act = pend_act;
	}
	public int getWs_flag() {
		return ws_flag;
	}
	public void setWs_flag(int ws_flag) {
		this.ws_flag = ws_flag;
	}
	public int getInstance() {
		return instance;
	}
	public void setInstance(int instance) {
		this.instance = instance;
	}
	public String getDue_date_day() {
		return due_date_day;
	}
	public void setDue_date_day(String due_date_day) {
		this.due_date_day = due_date_day;
	}
	public String getDue_date_month() {
		return due_date_month;
	}
	public void setDue_date_month(String due_date_month) {
		this.due_date_month = due_date_month;
	}
	public String getDue_date_year() {
		return due_date_year;
	}
	public void setDue_date_year(String due_date_year) {
		this.due_date_year = due_date_year;
	}
}
