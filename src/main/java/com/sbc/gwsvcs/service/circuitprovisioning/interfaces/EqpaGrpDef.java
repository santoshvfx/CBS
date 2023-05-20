// $Id: EqpaGrpDef.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpaGrpDef implements java.io.Serializable { 
	public String activity;
	public String clo;
	public String ddmon;
	public String ddday;
	public String ddyr;
	public String ap;
	public String conf;
	public String mc;
	public String fmt;
	public String cktid;
	public String drclass;
	public String misuse;
	public String tsp;
	public String mcc;
	public String funct;
	public String dv;

	public EqpaGrpDef () {
	}
	public EqpaGrpDef (String activity, String clo, String ddmon, String ddday, String ddyr, String ap, String conf, String mc, String fmt, String cktid, String drclass, String misuse, String tsp, String mcc, String funct, String dv) { 
		this.activity = activity;
		this.clo = clo;
		this.ddmon = ddmon;
		this.ddday = ddday;
		this.ddyr = ddyr;
		this.ap = ap;
		this.conf = conf;
		this.mc = mc;
		this.fmt = fmt;
		this.cktid = cktid;
		this.drclass = drclass;
		this.misuse = misuse;
		this.tsp = tsp;
		this.mcc = mcc;
		this.funct = funct;
		this.dv = dv;

	}
}
