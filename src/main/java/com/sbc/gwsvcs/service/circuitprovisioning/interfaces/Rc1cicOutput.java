// $Id: Rc1cicOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1cicOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String loca;
	public String locz;
	public String pca;
	public String pcz;
	public String tcicoption;
	public String blocksize;
	public String autopost;
	public String start;
	public String end;
	public String accesscode;
	public String fmt;
	public String id;
	public String starttcic;
	public String display;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef Rc1cicGrp[];
	public String msgid;

	public Rc1cicOutput () {
	}
	public Rc1cicOutput (int last, String cmd, String mask, String x_for, String loca, String locz, String pca, String pcz, String tcicoption, String blocksize, String autopost, String start, String end, String accesscode, String fmt, String id, String starttcic, String display, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef Rc1cicGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.loca = loca;
		this.locz = locz;
		this.pca = pca;
		this.pcz = pcz;
		this.tcicoption = tcicoption;
		this.blocksize = blocksize;
		this.autopost = autopost;
		this.start = start;
		this.end = end;
		this.accesscode = accesscode;
		this.fmt = fmt;
		this.id = id;
		this.starttcic = starttcic;
		this.display = display;
		this.Rc1cicGrp = Rc1cicGrp;
		this.msgid = msgid;

	}
}
