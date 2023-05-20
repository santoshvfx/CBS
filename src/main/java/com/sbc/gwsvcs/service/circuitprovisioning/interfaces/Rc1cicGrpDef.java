// $Id: Rc1cicGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1cicGrpDef implements java.io.Serializable { 
	public String sel;
	public String bind;
	public String p;
	public String tcic;
	public String access_code;
	public String formatid;

	public Rc1cicGrpDef () {
	}
	public Rc1cicGrpDef (String sel, String bind, String p, String tcic, String access_code, String formatid) { 
		this.sel = sel;
		this.bind = bind;
		this.p = p;
		this.tcic = tcic;
		this.access_code = access_code;
		this.formatid = formatid;

	}
}
