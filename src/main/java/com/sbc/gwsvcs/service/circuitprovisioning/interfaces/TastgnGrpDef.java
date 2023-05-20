// $Id: TastgnGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TastgnGrpDef implements java.io.Serializable { 
	public String tgn;
	public String starttmn;
	public String starttrunkno;
	public String endtrunk;
	public String cmd;

	public TastgnGrpDef () {
	}
	public TastgnGrpDef (String tgn, String starttmn, String starttrunkno, String endtrunk, String cmd) { 
		this.tgn = tgn;
		this.starttmn = starttmn;
		this.starttrunkno = starttrunkno;
		this.endtrunk = endtrunk;
		this.cmd = cmd;

	}
}
