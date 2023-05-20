// $Id: TgmrOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmrOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String mask;
	public String x_for;
	public String groupaccess;
	public String adminarea;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef TgmrGrp[];
	public String ineffect;
	public String spare;
	public String custswloc;
	public String pendingdisc;
	public String pendingconn;
	public String pendingdet;
	public String pendingatt;
	public String cspc;
	public String totalworking;
	public String rmks;
	public String mco;
	public String cust;
	public String custloc;
	public String msgid;

	public TgmrOutput () {
	}
	public TgmrOutput (int last, String cmd, String mask, String x_for, String groupaccess, String adminarea, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef TgmrGrp[], String ineffect, String spare, String custswloc, String pendingdisc, String pendingconn, String pendingdet, String pendingatt, String cspc, String totalworking, String rmks, String mco, String cust, String custloc, String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.mask = mask;
		this.x_for = x_for;
		this.groupaccess = groupaccess;
		this.adminarea = adminarea;
		this.TgmrGrp = TgmrGrp;
		this.ineffect = ineffect;
		this.spare = spare;
		this.custswloc = custswloc;
		this.pendingdisc = pendingdisc;
		this.pendingconn = pendingconn;
		this.pendingdet = pendingdet;
		this.pendingatt = pendingatt;
		this.cspc = cspc;
		this.totalworking = totalworking;
		this.rmks = rmks;
		this.mco = mco;
		this.cust = cust;
		this.custloc = custloc;
		this.msgid = msgid;

	}
}
