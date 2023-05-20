// $Id: EqpuGrpDef.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpuGrpDef implements java.io.Serializable { 
	public String hecigs;
	public String units;
	public String sprs;

	public EqpuGrpDef () {
	}
	public EqpuGrpDef (String hecigs, String units, String sprs) { 
		this.hecigs = hecigs;
		this.units = units;
		this.sprs = sprs;

	}
}
