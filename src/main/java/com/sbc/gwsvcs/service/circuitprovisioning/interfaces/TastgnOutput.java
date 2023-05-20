// $Id: TastgnOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TastgnOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String ckt;
	public String a;
	public String z;
	public String clo;
	public String action;
	public String duedate;
	public String end;
	public String view;
	public String gac;
	public String key;
	public String type;
	public String trk;
	public String lastupdt;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef TastgnGrp[];
	public String msgid;

	public TastgnOutput () {
	}
	public TastgnOutput (int last, String cmd, String mask, String x_for, String ckt, String a, String z, String clo, String action, String duedate, String end, String view, String gac, String key, String type, String trk, String lastupdt, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef TastgnGrp[], String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.ckt = ckt;
		this.a = a;
		this.z = z;
		this.clo = clo;
		this.action = action;
		this.duedate = duedate;
		this.end = end;
		this.view = view;
		this.gac = gac;
		this.key = key;
		this.type = type;
		this.trk = trk;
		this.lastupdt = lastupdt;
		this.TastgnGrp = TastgnGrp;
		this.msgid = msgid;

	}
}
