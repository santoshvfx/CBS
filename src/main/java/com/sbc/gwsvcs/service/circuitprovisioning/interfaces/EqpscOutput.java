// $Id: EqpscOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpscOutput implements java.io.Serializable { 
	public int last;
	public String mask;
	public String x_for;
	public String location;
	public String equip_code;
	public String level;
	public String relayrack;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef EqpscGrp[];
	public String msgid;

	public EqpscOutput () {
	}
	public EqpscOutput (int last, String mask, String x_for, String location, String equip_code, String level, String relayrack, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef EqpscGrp[], String msgid) { 
		this.last = last;
		this.mask = mask;
		this.x_for = x_for;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;
		this.relayrack = relayrack;
		this.EqpscGrp = EqpscGrp;
		this.msgid = msgid;

	}
}
