// $Id: WaOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class WaOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String ckt;
	public String loca;
	public String locz;
	public String clo;
	public String from;
	public String to;
	public String act;
	public String prq;
	public String tsp;
	public String msc;
	public String mco;
	public String cac;
	public String ord;
	public String sup;
	public String tp;
	public String stat;
	public String lease;
	public String dpi;
	public String pg;
	public String cust;
	public String tgac;
	public String puls;
	public String dv;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dd;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t iad;
	public String ccon;
	public String tel;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t ptd;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t swc;
	public String acna;
	public String ccna;
	public String imp;
	public String imc;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t fcd;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t wot;
	public String btn;
	public String cus;
	public String aor;
	public String rri;
	public String lata;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dva;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t rid;
	public String sa;
	public String jobid;
	public String ex1;
	public String rclo;
	public String proj;
	public String ex2;
	public String ro;
	public String cco;
	public String ex3;
	public String cro;
	public String oco;
	public String ex4;
	public String origr;
	public String tel2;
	public String dggnr_group;
	public String tel3;
	public String ver;
	public String pre_ck;
	public String pon;
	public String ckr;
	public String mcn;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef WaGrp[];
	public String notes;
	public String msgid;

	public WaOutput () {
	}
	public WaOutput (int last, String cmd, String mask, String x_for, String ckt, String loca, String locz, String clo, String from, String to, String act, String prq, String tsp, String msc, String mco, String cac, String ord, String sup, String tp, String stat, String lease, String dpi, String pg, String cust, String tgac, String puls, String dv, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dd, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t iad, String ccon, String tel, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t ptd, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t swc, String acna, String ccna, String imp, String imc, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t fcd, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t wot, String btn, String cus, String aor, String rri, String lata, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dva, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t rid, String sa, String jobid, String ex1, String rclo, String proj, String ex2, String ro, String cco, String ex3, String cro, String oco, String ex4, String origr, String tel2, String dggnr_group, String tel3, String ver, String pre_ck, String pon, String ckr, String mcn, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef WaGrp[], String notes, String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.ckt = ckt;
		this.loca = loca;
		this.locz = locz;
		this.clo = clo;
		this.from = from;
		this.to = to;
		this.act = act;
		this.prq = prq;
		this.tsp = tsp;
		this.msc = msc;
		this.mco = mco;
		this.cac = cac;
		this.ord = ord;
		this.sup = sup;
		this.tp = tp;
		this.stat = stat;
		this.lease = lease;
		this.dpi = dpi;
		this.pg = pg;
		this.cust = cust;
		this.tgac = tgac;
		this.puls = puls;
		this.dv = dv;
		this.dd = dd;
		this.iad = iad;
		this.ccon = ccon;
		this.tel = tel;
		this.ptd = ptd;
		this.swc = swc;
		this.acna = acna;
		this.ccna = ccna;
		this.imp = imp;
		this.imc = imc;
		this.fcd = fcd;
		this.wot = wot;
		this.btn = btn;
		this.cus = cus;
		this.aor = aor;
		this.rri = rri;
		this.lata = lata;
		this.dva = dva;
		this.rid = rid;
		this.sa = sa;
		this.jobid = jobid;
		this.ex1 = ex1;
		this.rclo = rclo;
		this.proj = proj;
		this.ex2 = ex2;
		this.ro = ro;
		this.cco = cco;
		this.ex3 = ex3;
		this.cro = cro;
		this.oco = oco;
		this.ex4 = ex4;
		this.origr = origr;
		this.tel2 = tel2;
		this.dggnr_group = dggnr_group;
		this.tel3 = tel3;
		this.ver = ver;
		this.pre_ck = pre_ck;
		this.pon = pon;
		this.ckr = ckr;
		this.mcn = mcn;
		this.WaGrp = WaGrp;
		this.notes = notes;
		this.msgid = msgid;

	}
}
