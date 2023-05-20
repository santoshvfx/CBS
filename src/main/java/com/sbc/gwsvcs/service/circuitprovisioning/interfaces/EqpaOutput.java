// $Id: EqpaOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpaOutput implements java.io.Serializable { 
	public int last;
	public String mask;
	public String x_for;
	public String location;
	public String equip_code;
	public String level;
	public String relayrack;
	public String nmrunits;
	public String nbrspares;
	public String invauth;
	public String asgnauth;
	public String nbrdv;
	public String note;
	public String unit;
	public String unitstatus;
	public String asgnstatus;
	public String spare;
	public String count;
	public String swfw;
	public String invctl;
	public String faultid;
	public String userdata;
	public String pli;
	public String scid;
	public String eqpuse;
	public String edsx;
	public String eqp_ordernbr;
	public String eqp_item;
	public String eqp_date;
	public String eqp_type;
	public String relloc;
	public String supl_ordernbr;
	public String supl_item;
	public String supl_date;
	public String supl_type;
	public String cpypct_a;
	public String cpypct_ty;
	public String cpypct_fr;
	public String cpypct_addr1;
	public String cpypct_addr2;
	public String cpypct_addr3;
	public String cpypct_addr4;
	public String cpypct_leads;
	public String leasenbr_a;
	public String leasenbr_ty;
	public String leasenbr_fr;
	public String leasenbr_addr1;
	public String leasenbr_addr2;
	public String leasenbr_addr3;
	public String leasenbr_addr4;
	public String leasenbr_leads;
	public String subdivision;
	public String assign_note;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef EqpaGrp[];
	public String msgid;

	public EqpaOutput () {
	}
	public EqpaOutput (int last, String mask, String x_for, String location, String equip_code, String level, String relayrack, String nmrunits, String nbrspares, String invauth, String asgnauth, String nbrdv, String note, String unit, String unitstatus, String asgnstatus, String spare, String count, String swfw, String invctl, String faultid, String userdata, String pli, String scid, String eqpuse, String edsx, String eqp_ordernbr, String eqp_item, String eqp_date, String eqp_type, String relloc, String supl_ordernbr, String supl_item, String supl_date, String supl_type, String cpypct_a, String cpypct_ty, String cpypct_fr, String cpypct_addr1, String cpypct_addr2, String cpypct_addr3, String cpypct_addr4, String cpypct_leads, String leasenbr_a, String leasenbr_ty, String leasenbr_fr, String leasenbr_addr1, String leasenbr_addr2, String leasenbr_addr3, String leasenbr_addr4, String leasenbr_leads, String subdivision, String assign_note, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef EqpaGrp[], String msgid) { 
		this.last = last;
		this.mask = mask;
		this.x_for = x_for;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;
		this.relayrack = relayrack;
		this.nmrunits = nmrunits;
		this.nbrspares = nbrspares;
		this.invauth = invauth;
		this.asgnauth = asgnauth;
		this.nbrdv = nbrdv;
		this.note = note;
		this.unit = unit;
		this.unitstatus = unitstatus;
		this.asgnstatus = asgnstatus;
		this.spare = spare;
		this.count = count;
		this.swfw = swfw;
		this.invctl = invctl;
		this.faultid = faultid;
		this.userdata = userdata;
		this.pli = pli;
		this.scid = scid;
		this.eqpuse = eqpuse;
		this.edsx = edsx;
		this.eqp_ordernbr = eqp_ordernbr;
		this.eqp_item = eqp_item;
		this.eqp_date = eqp_date;
		this.eqp_type = eqp_type;
		this.relloc = relloc;
		this.supl_ordernbr = supl_ordernbr;
		this.supl_item = supl_item;
		this.supl_date = supl_date;
		this.supl_type = supl_type;
		this.cpypct_a = cpypct_a;
		this.cpypct_ty = cpypct_ty;
		this.cpypct_fr = cpypct_fr;
		this.cpypct_addr1 = cpypct_addr1;
		this.cpypct_addr2 = cpypct_addr2;
		this.cpypct_addr3 = cpypct_addr3;
		this.cpypct_addr4 = cpypct_addr4;
		this.cpypct_leads = cpypct_leads;
		this.leasenbr_a = leasenbr_a;
		this.leasenbr_ty = leasenbr_ty;
		this.leasenbr_fr = leasenbr_fr;
		this.leasenbr_addr1 = leasenbr_addr1;
		this.leasenbr_addr2 = leasenbr_addr2;
		this.leasenbr_addr3 = leasenbr_addr3;
		this.leasenbr_addr4 = leasenbr_addr4;
		this.leasenbr_leads = leasenbr_leads;
		this.subdivision = subdivision;
		this.assign_note = assign_note;
		this.EqpaGrp = EqpaGrp;
		this.msgid = msgid;

	}
}
