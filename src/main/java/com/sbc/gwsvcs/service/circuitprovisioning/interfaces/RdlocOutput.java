// $Id: RdlocOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class RdlocOutput implements java.io.Serializable { 
	public int last;
	public String cmd;
	public String x_for;
	public String mask;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef RdlocGrp[];
	public String mstr;
	public String mstrloc;
	public String hr;
	public String hostloc;
	public String pointcode;
	public String tciccomp;
	public String description;
	public String adminarea;
	public String locname;
	public String mcotel;
	public String drarea;
	public String street;
	public String switchsys;
	public String paa1;
	public String town;
	public String tasuniv;
	public String owner;
	public String cnty;
	public String state;
	public String statsw;
	public String lata;
	public String zip;
	public String phone;
	public String ext;
	public String loss;
	public String db;
	public String pop;
	public String title;
	public String range;
	public String company;
	public String impedance;
	public String bse;
	public String ctl;
	public String tasoptions;
	public String cgnbr;
	public String exchange;
	public String corridor;
	public String officeclass;
	public String sic;
	public String locatetype;
	public String swc;
	public String wats;
	public String remarks;
	public String msgid;

	public RdlocOutput () {
	}
	public RdlocOutput (int last, String cmd, String x_for, String mask, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef RdlocGrp[], String mstr, String mstrloc, String hr, String hostloc, String pointcode, String tciccomp, String description, String adminarea, String locname, String mcotel, String drarea, String street, String switchsys, String paa1, String town, String tasuniv, String owner, String cnty, String state, String statsw, String lata, String zip, String phone, String ext, String loss, String db, String pop, String title, String range, String company, String impedance, String bse, String ctl, String tasoptions, String cgnbr, String exchange, String corridor, String officeclass, String sic, String locatetype, String swc, String wats, String remarks, String msgid) { 
		this.last = last;
		this.cmd = cmd;
		this.x_for = x_for;
		this.mask = mask;
		this.RdlocGrp = RdlocGrp;
		this.mstr = mstr;
		this.mstrloc = mstrloc;
		this.hr = hr;
		this.hostloc = hostloc;
		this.pointcode = pointcode;
		this.tciccomp = tciccomp;
		this.description = description;
		this.adminarea = adminarea;
		this.locname = locname;
		this.mcotel = mcotel;
		this.drarea = drarea;
		this.street = street;
		this.switchsys = switchsys;
		this.paa1 = paa1;
		this.town = town;
		this.tasuniv = tasuniv;
		this.owner = owner;
		this.cnty = cnty;
		this.state = state;
		this.statsw = statsw;
		this.lata = lata;
		this.zip = zip;
		this.phone = phone;
		this.ext = ext;
		this.loss = loss;
		this.db = db;
		this.pop = pop;
		this.title = title;
		this.range = range;
		this.company = company;
		this.impedance = impedance;
		this.bse = bse;
		this.ctl = ctl;
		this.tasoptions = tasoptions;
		this.cgnbr = cgnbr;
		this.exchange = exchange;
		this.corridor = corridor;
		this.officeclass = officeclass;
		this.sic = sic;
		this.locatetype = locatetype;
		this.swc = swc;
		this.wats = wats;
		this.remarks = remarks;
		this.msgid = msgid;

	}
}
