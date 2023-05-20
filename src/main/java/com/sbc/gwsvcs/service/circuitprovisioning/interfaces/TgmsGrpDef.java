// $Id: TgmsGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmsGrpDef implements java.io.Serializable { 
	public String s;
	public String trk;
	public String cac;
	public String owner;
	public String stat;
	public String clonbr;
	public String dd;
	public String act;
	public String pdtk;
	public String pdgac;
	public String pddd;
	public String pdact;

	public TgmsGrpDef () {
	}
	public TgmsGrpDef (String s, String trk, String cac, String owner, String stat, String clonbr, String dd, String act, String pdtk, String pdgac, String pddd, String pdact) { 
		this.s = s;
		this.trk = trk;
		this.cac = cac;
		this.owner = owner;
		this.stat = stat;
		this.clonbr = clonbr;
		this.dd = dd;
		this.act = act;
		this.pdtk = pdtk;
		this.pdgac = pdgac;
		this.pddd = pddd;
		this.pdact = pdact;

	}
}
