// $Id: EqpuOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpuOutput implements java.io.Serializable { 
	public int last;
	public String mask;
	public String x_for;
	public String location;
	public String hecig;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef EqpuGrp[];
	public String msgid;

	public EqpuOutput () {
	}
	public EqpuOutput (int last, String mask, String x_for, String location, String hecig, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef EqpuGrp[], String msgid) { 
		this.last = last;
		this.mask = mask;
		this.x_for = x_for;
		this.location = location;
		this.hecig = hecig;
		this.EqpuGrp = EqpuGrp;
		this.msgid = msgid;

	}
}
