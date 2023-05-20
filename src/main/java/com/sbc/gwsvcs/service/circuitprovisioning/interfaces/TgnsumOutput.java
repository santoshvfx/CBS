// $Id: TgnsumOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgnsumOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String location1;
	public String fmt;
	public String display;
	public String starttgn;
	public String lterm;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef TgnsumGrp[];
	public String msgid;

	public TgnsumOutput () {
	}
	public TgnsumOutput (int last, String cmd, String mask, String x_for, String location1, String fmt, String display, String starttgn, String lterm, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef TgnsumGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.location1 = location1;
		this.fmt = fmt;
		this.display = display;
		this.starttgn = starttgn;
		this.lterm = lterm;
		this.TgnsumGrp = TgnsumGrp;
		this.msgid = msgid;

	}
}
