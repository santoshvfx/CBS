// $Id: CktsrGrpDef.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CktsrGrpDef implements java.io.Serializable { 
	public String s;
	public String fmt;
	public String cktid;
	public String idtype;
	public String clo;
	public String dd;
	public String act;
	public String puls;
	public String pdac;
	public String loca;
	public String locz;
	public String dr;
	public String piu;
	public String eac;
	public String te;

	public CktsrGrpDef () {
	}
	public CktsrGrpDef (String s, String fmt, String cktid, String idtype, String clo, String dd, String act, String puls, String pdac, String loca, String locz, String dr, String piu, String eac, String te) { 
		this.s = s;
		this.fmt = fmt;
		this.cktid = cktid;
		this.idtype = idtype;
		this.clo = clo;
		this.dd = dd;
		this.act = act;
		this.puls = puls;
		this.pdac = pdac;
		this.loca = loca;
		this.locz = locz;
		this.dr = dr;
		this.piu = piu;
		this.eac = eac;
		this.te = te;

	}
}
