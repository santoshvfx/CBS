// $Id: Rc1scnOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1scnOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public int page;
	public String window;
	public String of;
	public String x_date;
	public String time;
	public String searchfmt;
	public String ac_input;
	public String fmtid;
	public String type_input;
	public String asvc;
	public String zts;
	public String dgec;
	public String tysg;
	public String clo;
	public String cust;
	public String acna;
	public String ownr_trt;
	public String cabssysid;
	public String crissysid;
	public String family_typ;
	public String ccna;
	public String lata;
	public String scantyp;
	public String fc;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef Rc1scnGrp[];
	public String msgid;

	public Rc1scnOutput () {
	}
	public Rc1scnOutput (int last, String cmd, String mask, String x_for, int page, String window, String of, String x_date, String time, String searchfmt, String ac_input, String fmtid, String type_input, String asvc, String zts, String dgec, String tysg, String clo, String cust, String acna, String ownr_trt, String cabssysid, String crissysid, String family_typ, String ccna, String lata, String scantyp, String fc, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef Rc1scnGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.page = page;
		this.window = window;
		this.of = of;
		this.x_date = x_date;
		this.time = time;
		this.searchfmt = searchfmt;
		this.ac_input = ac_input;
		this.fmtid = fmtid;
		this.type_input = type_input;
		this.asvc = asvc;
		this.zts = zts;
		this.dgec = dgec;
		this.tysg = tysg;
		this.clo = clo;
		this.cust = cust;
		this.acna = acna;
		this.ownr_trt = ownr_trt;
		this.cabssysid = cabssysid;
		this.crissysid = crissysid;
		this.family_typ = family_typ;
		this.ccna = ccna;
		this.lata = lata;
		this.scantyp = scantyp;
		this.fc = fc;
		this.Rc1scnGrp = Rc1scnGrp;
		this.msgid = msgid;

	}
}
