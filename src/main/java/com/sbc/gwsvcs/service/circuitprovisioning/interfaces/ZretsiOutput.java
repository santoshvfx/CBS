// $Id: ZretsiOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZretsiOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String lterm;
	public String location;
	public String isopt;
	public String asopt;
	public String zretsidate;
	public String clo;
	public String item;
	public String itemend;
	public String equiptype;
	public String lvl;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef ZretsiGrp[];
	public String msgid;

	public ZretsiOutput () {
	}
	public ZretsiOutput (int last, String cmd, String mask, String x_for, String lterm, String location, String isopt, String asopt, String zretsidate, String clo, String item, String itemend, String equiptype, String lvl, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef ZretsiGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.lterm = lterm;
		this.location = location;
		this.isopt = isopt;
		this.asopt = asopt;
		this.zretsidate = zretsidate;
		this.clo = clo;
		this.item = item;
		this.itemend = itemend;
		this.equiptype = equiptype;
		this.lvl = lvl;
		this.ZretsiGrp = ZretsiGrp;
		this.msgid = msgid;

	}
}
