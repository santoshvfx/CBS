// $Id: ZrxlocOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrxlocOutput implements java.io.Serializable { 
	public int last;
	public String mask;
	public String x_for;
	public String function;
	public String location1;
	public String lpc1;
	public String pc_ncp1;
	public String pcbit;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef ZrxlocGrp[];
	public String msgid;

	public ZrxlocOutput () {
	}
	public ZrxlocOutput (int last, String mask, String x_for, String function, String location1, String lpc1, String pc_ncp1, String pcbit, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef ZrxlocGrp[], String msgid) { 
		this.last = last;
		this.mask = mask;
		this.x_for = x_for;
		this.function = function;
		this.location1 = location1;
		this.lpc1 = lpc1;
		this.pc_ncp1 = pc_ncp1;
		this.pcbit = pcbit;
		this.ZrxlocGrp = ZrxlocGrp;
		this.msgid = msgid;

	}
}
