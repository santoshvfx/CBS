// $Id: DriOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class DriOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String ckt;
	public String loca;
	public String locz;
	public String clo;
	public String act;
	public String stat;
	public String segowner;
	public String slc;
	public String puls;
	public String dv;
	public String cac;
	public String ord;
	public String jt_msg;
	public String ctx;
	public String sig_op;
	public String drc;
	public String mic;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dd;
	public String ec_dsgcon;
	public String ec_tel;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dlrd;
	public String ec_mco;
	public String ec_oco;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t cdlrd;
	public String ic;
	public String pon;
	public String ver;
	public String ckr;
	public String refnum;
	public String ic_dsgcon;
	public String ic_tel;
	public String acccode;
	public String dsg_addr;
	public String nc;
	public String pri_loc;
	public String nci1;
	public String tlvt1;
	public String tlvr1;
	public String apot;
	public String sec_loc;
	public String nci2;
	public String tlvt2;
	public String tlvr2;
	public String spot;
	public String other_loc;
	public String cfa_a;
	public String cfa_z;
	public String remarks;
	public String msgid;

	public DriOutput () {
	}
	public DriOutput (int last, String cmd, String mask, String x_for, String ckt, String loca, String locz, String clo, String act, String stat, String segowner, String slc, String puls, String dv, String cac, String ord, String jt_msg, String ctx, String sig_op, String drc, String mic, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dd, String ec_dsgcon, String ec_tel, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t dlrd, String ec_mco, String ec_oco, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t cdlrd, String ic, String pon, String ver, String ckr, String refnum, String ic_dsgcon, String ic_tel, String acccode, String dsg_addr, String nc, String pri_loc, String nci1, String tlvt1, String tlvr1, String apot, String sec_loc, String nci2, String tlvt2, String tlvr2, String spot, String other_loc, String cfa_a, String cfa_z, String remarks, String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.ckt = ckt;
		this.loca = loca;
		this.locz = locz;
		this.clo = clo;
		this.act = act;
		this.stat = stat;
		this.segowner = segowner;
		this.slc = slc;
		this.puls = puls;
		this.dv = dv;
		this.cac = cac;
		this.ord = ord;
		this.jt_msg = jt_msg;
		this.ctx = ctx;
		this.sig_op = sig_op;
		this.drc = drc;
		this.mic = mic;
		this.dd = dd;
		this.ec_dsgcon = ec_dsgcon;
		this.ec_tel = ec_tel;
		this.dlrd = dlrd;
		this.ec_mco = ec_mco;
		this.ec_oco = ec_oco;
		this.cdlrd = cdlrd;
		this.ic = ic;
		this.pon = pon;
		this.ver = ver;
		this.ckr = ckr;
		this.refnum = refnum;
		this.ic_dsgcon = ic_dsgcon;
		this.ic_tel = ic_tel;
		this.acccode = acccode;
		this.dsg_addr = dsg_addr;
		this.nc = nc;
		this.pri_loc = pri_loc;
		this.nci1 = nci1;
		this.tlvt1 = tlvt1;
		this.tlvr1 = tlvr1;
		this.apot = apot;
		this.sec_loc = sec_loc;
		this.nci2 = nci2;
		this.tlvt2 = tlvt2;
		this.tlvr2 = tlvr2;
		this.spot = spot;
		this.other_loc = other_loc;
		this.cfa_a = cfa_a;
		this.cfa_z = cfa_z;
		this.remarks = remarks;
		this.msgid = msgid;

	}
}
