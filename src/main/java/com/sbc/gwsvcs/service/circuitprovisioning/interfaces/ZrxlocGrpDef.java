// $Id: ZrxlocGrpDef.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrxlocGrpDef implements java.io.Serializable { 
	public String sel;
	public String location;
	public String lpc;
	public String pc_ncp;
	public String level;
	public String hr;
	public String type;
	public String stat;
	public String ciccomp;

	public ZrxlocGrpDef () {
	}
	public ZrxlocGrpDef (String sel, String location, String lpc, String pc_ncp, String level, String hr, String type, String stat, String ciccomp) { 
		this.sel = sel;
		this.location = location;
		this.lpc = lpc;
		this.pc_ncp = pc_ncp;
		this.level = level;
		this.hr = hr;
		this.type = type;
		this.stat = stat;
		this.ciccomp = ciccomp;

	}
}
