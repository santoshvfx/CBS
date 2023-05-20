// $Id: CblsOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CblsOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String term_A;
	public String term_Z;
	public String cable;
	public String from_unit;
	public String last_unit;
	public String qty_spare;
	public String tot_qty;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t cmpl_date;
	public String inv_stat;
	public String x_2w_spares;
	public String x_1w_spares;
	public String fac_detail;
	public String fac_use;
	public String fac_group;
	public String comp_id;
	public String no_of_sprs;
	public String no_of_wires;
	public String shld_dir;
	public String ckt_clo_scan;
	public String alt_pri;
	public String dr_class;
	public String misuse;
	public String mw;
	public String termn_frames;
	public String seq_nbr;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef CblsGrp[];
	public String msgid;

	public CblsOutput () {
	}
	public CblsOutput (int last, String cmd, String mask, String x_for, String term_A, String term_Z, String cable, String from_unit, String last_unit, String qty_spare, String tot_qty, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t cmpl_date, String inv_stat, String x_2w_spares, String x_1w_spares, String fac_detail, String fac_use, String fac_group, String comp_id, String no_of_sprs, String no_of_wires, String shld_dir, String ckt_clo_scan, String alt_pri, String dr_class, String misuse, String mw, String termn_frames, String seq_nbr, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef CblsGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.cable = cable;
		this.from_unit = from_unit;
		this.last_unit = last_unit;
		this.qty_spare = qty_spare;
		this.tot_qty = tot_qty;
		this.cmpl_date = cmpl_date;
		this.inv_stat = inv_stat;
		this.x_2w_spares = x_2w_spares;
		this.x_1w_spares = x_1w_spares;
		this.fac_detail = fac_detail;
		this.fac_use = fac_use;
		this.fac_group = fac_group;
		this.comp_id = comp_id;
		this.no_of_sprs = no_of_sprs;
		this.no_of_wires = no_of_wires;
		this.shld_dir = shld_dir;
		this.ckt_clo_scan = ckt_clo_scan;
		this.alt_pri = alt_pri;
		this.dr_class = dr_class;
		this.misuse = misuse;
		this.mw = mw;
		this.termn_frames = termn_frames;
		this.seq_nbr = seq_nbr;
		this.CblsGrp = CblsGrp;
		this.msgid = msgid;

	}
}
