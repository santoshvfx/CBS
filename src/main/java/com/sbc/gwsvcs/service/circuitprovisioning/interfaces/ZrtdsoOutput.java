// $Id: ZrtdsoOutput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrtdsoOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String tablename;
	public String tablekey;
	public String adminarea;
	public String tablerecord;
	public String norecords;
	public String note;
	public String rellev;
	public String mod;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef ZrtdsoGrp[];
	public String msgid;

	public ZrtdsoOutput () {
	}
	public ZrtdsoOutput (int last, String cmd, String mask, String x_for, String tablename, String tablekey, String adminarea, String tablerecord, String norecords, String note, String rellev, String mod, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef ZrtdsoGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.tablename = tablename;
		this.tablekey = tablekey;
		this.adminarea = adminarea;
		this.tablerecord = tablerecord;
		this.norecords = norecords;
		this.note = note;
		this.rellev = rellev;
		this.mod = mod;
		this.ZrtdsoGrp = ZrtdsoGrp;
		this.msgid = msgid;

	}
}
