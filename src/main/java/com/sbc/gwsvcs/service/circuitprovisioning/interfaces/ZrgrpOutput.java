// $Id: ZrgrpOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrgrpOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String gac;
	public String end;
	public String ccstrunk;
	public String last1;
	public String update;
	public String lterm;
	public String location;
	public String f;
	public String groupid;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1 ZrgrpGrp1[];
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2 ZrgrpGrp2[];
	public String msgid;

	public ZrgrpOutput () {
	}
	public ZrgrpOutput (int last, String cmd, String mask, String x_for, String gac, String end, String ccstrunk, String last1, String update, String lterm, String location, String f, String groupid, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1 ZrgrpGrp1[], com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2 ZrgrpGrp2[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.gac = gac;
		this.end = end;
		this.ccstrunk = ccstrunk;
		this.last1 = last1;
		this.update = update;
		this.lterm = lterm;
		this.location = location;
		this.f = f;
		this.groupid = groupid;
		this.ZrgrpGrp1 = ZrgrpGrp1;
		this.ZrgrpGrp2 = ZrgrpGrp2;
		this.msgid = msgid;

	}
}
