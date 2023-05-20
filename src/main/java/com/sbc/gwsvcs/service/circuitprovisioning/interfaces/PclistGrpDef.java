// $Id: PclistGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class PclistGrpDef implements java.io.Serializable { 
	public String c;
	public String to_1;
	public String to_2;
	public String fcn;
	public String date;
	public String clo;
	public String seg;
	public String ckl;
	public String order;
	public String jep;
	public String er;
	public String from_rro;
	public String from_pos;
	public String bcnt;
	public String act;
	public String st;
	public String cktid;
	public String cust;
	public String s;
	public String d;
	public String p;

	public PclistGrpDef () {
	}
	public PclistGrpDef (String c, String to_1, String to_2, String fcn, String date, String clo, String seg, String ckl, String order, String jep, String er, String from_rro, String from_pos, String bcnt, String act, String st, String cktid, String cust, String s, String d, String p) { 
		this.c = c;
		this.to_1 = to_1;
		this.to_2 = to_2;
		this.fcn = fcn;
		this.date = date;
		this.clo = clo;
		this.seg = seg;
		this.ckl = ckl;
		this.order = order;
		this.jep = jep;
		this.er = er;
		this.from_rro = from_rro;
		this.from_pos = from_pos;
		this.bcnt = bcnt;
		this.act = act;
		this.st = st;
		this.cktid = cktid;
		this.cust = cust;
		this.s = s;
		this.d = d;
		this.p = p;

	}
}
