// $Id: CktsrOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CktsrOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String cac;
	public String admin;
	public String famdr;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef CktsrGrp[];
	public String sscur_gac;
	public String sscur_trknbr;
	public String sscur_cktstat;
	public String sscur_currspare;
	public String sscur_fmt;
	public String sscur_grpid;
	public String sspend_gac;
	public String sspend_trknbr;
	public String sspend_clo;
	public String sspend_pendact;
	public String sspend_fmt;
	public String sspend_grpid;
	public String sspend_duedate;
	public String relckt_cac;
	public String relckt_fmt;
	public String relckt_relid;
	public String msgid;

	public CktsrOutput () {
	}
	public CktsrOutput (int last, String cmd, String mask, String x_for, String cac, String admin, String famdr, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef CktsrGrp[], String sscur_gac, String sscur_trknbr, String sscur_cktstat, String sscur_currspare, String sscur_fmt, String sscur_grpid, String sspend_gac, String sspend_trknbr, String sspend_clo, String sspend_pendact, String sspend_fmt, String sspend_grpid, String sspend_duedate, String relckt_cac, String relckt_fmt, String relckt_relid, String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.cac = cac;
		this.admin = admin;
		this.famdr = famdr;
		this.CktsrGrp = CktsrGrp;
		this.sscur_gac = sscur_gac;
		this.sscur_trknbr = sscur_trknbr;
		this.sscur_cktstat = sscur_cktstat;
		this.sscur_currspare = sscur_currspare;
		this.sscur_fmt = sscur_fmt;
		this.sscur_grpid = sscur_grpid;
		this.sspend_gac = sspend_gac;
		this.sspend_trknbr = sspend_trknbr;
		this.sspend_clo = sspend_clo;
		this.sspend_pendact = sspend_pendact;
		this.sspend_fmt = sspend_fmt;
		this.sspend_grpid = sspend_grpid;
		this.sspend_duedate = sspend_duedate;
		this.relckt_cac = relckt_cac;
		this.relckt_fmt = relckt_fmt;
		this.relckt_relid = relckt_relid;
		this.msgid = msgid;

	}
}
