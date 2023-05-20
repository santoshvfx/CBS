// $Id: TgmsOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmsOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String groupaccess;
	public String trknbr;
	public String totaltirks;
	public String fmt1;
	public String groupid1;
	public String status1;
	public String fmt2;
	public String groupid2;
	public String status2;
	public String tgmsdisplay;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef TgmsGrp[];
	public String msgid;

	public TgmsOutput () {
	}
	public TgmsOutput (int last, String cmd, String mask, String x_for, String groupaccess, String trknbr, String totaltirks, String fmt1, String groupid1, String status1, String fmt2, String groupid2, String status2, String tgmsdisplay, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef TgmsGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.groupaccess = groupaccess;
		this.trknbr = trknbr;
		this.totaltirks = totaltirks;
		this.fmt1 = fmt1;
		this.groupid1 = groupid1;
		this.status1 = status1;
		this.fmt2 = fmt2;
		this.groupid2 = groupid2;
		this.status2 = status2;
		this.tgmsdisplay = tgmsdisplay;
		this.TgmsGrp = TgmsGrp;
		this.msgid = msgid;

	}
}
