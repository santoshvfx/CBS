// $Id: EqpscGrpDef.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpscGrpDef implements java.io.Serializable { 
	public String unit;
	public String invstat;
	public String subdiv;
	public String asntyp;
	public String dvcd;
	public String curact;
	public String circuitid;
	public String pendact;
	public String duedate;

	public EqpscGrpDef () {
	}
	public EqpscGrpDef (String unit, String invstat, String subdiv, String asntyp, String dvcd, String curact, String circuitid, String pendact, String duedate) { 
		this.unit = unit;
		this.invstat = invstat;
		this.subdiv = subdiv;
		this.asntyp = asntyp;
		this.dvcd = dvcd;
		this.curact = curact;
		this.circuitid = circuitid;
		this.pendact = pendact;
		this.duedate = duedate;

	}
}
