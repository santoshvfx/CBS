// $Id: ZrgrpGrpDef1.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrgrpGrpDef1 implements java.io.Serializable { 
	public String trkeqpttype;
	public String asmtyp;
	public String tcc;
	public String prty;

	public ZrgrpGrpDef1 () {
	}
	public ZrgrpGrpDef1 (String trkeqpttype, String asmtyp, String tcc, String prty) { 
		this.trkeqpttype = trkeqpttype;
		this.asmtyp = asmtyp;
		this.tcc = tcc;
		this.prty = prty;

	}
}
