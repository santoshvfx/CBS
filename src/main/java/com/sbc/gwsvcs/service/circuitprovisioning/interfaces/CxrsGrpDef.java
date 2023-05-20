// $Id: CxrsGrpDef.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CxrsGrpDef implements java.io.Serializable { 
	public String channel_pair;
	public String asgt_rstn;
	public String cur_act;
	public String inv_stat;
	public String d;
	public String circuit_id;
	public String pend_act;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data;
	public int ws_flag;

	public CxrsGrpDef () {
	}
	public CxrsGrpDef (String channel_pair, String asgt_rstn, String cur_act, String inv_stat, String d, String circuit_id, String pend_act, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_data, int ws_flag) { 
		this.channel_pair = channel_pair;
		this.asgt_rstn = asgt_rstn;
		this.cur_act = cur_act;
		this.inv_stat = inv_stat;
		this.d = d;
		this.circuit_id = circuit_id;
		this.pend_act = pend_act;
		this.due_date_data = due_date_data;
		this.ws_flag = ws_flag;

	}
}
