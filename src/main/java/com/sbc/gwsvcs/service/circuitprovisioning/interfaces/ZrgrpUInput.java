// $Id: ZrgrpUInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrgrpUInput implements java.io.Serializable { 
	public String ims_region;
	public String gac;
	public String end;
	public String trkeqpttype;
	public String asmtyp;
	public String tcc;

	public ZrgrpUInput () {
	}
	public ZrgrpUInput (String ims_region, String gac, String end, String trkeqpttype, String asmtyp, String tcc) { 
		this.ims_region = ims_region;
		this.gac = gac;
		this.end = end;
		this.trkeqpttype = trkeqpttype;
		this.asmtyp = asmtyp;
		this.tcc = tcc;

	}
}
