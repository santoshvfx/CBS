// $Id: Rc1scnQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1scnQInput implements java.io.Serializable { 
	public String ims_region;
	public int number_of_pages;
	public String fmt;
	public String ac_input;
	public String fmtid;
	public String type_input;
	public String asvc;
	public String zts;
	public String dgec;
	public String tysg;
	public String clo;
	public String cust;
	public String ownr_trt;
	public String cabssysid;
	public String crissysid;
	public String family_typ;
	public String ccna;
	public String lata;
	public String scantyp;
	public String fc;

	public Rc1scnQInput () {
	}
	public Rc1scnQInput (String ims_region, int number_of_pages, String fmt, String ac_input, String fmtid, String type_input, String asvc, String zts, String dgec, String tysg, String clo, String cust, String ownr_trt, String cabssysid, String crissysid, String family_typ, String ccna, String lata, String scantyp, String fc) { 
		this.ims_region = ims_region;
		this.number_of_pages = number_of_pages;
		this.fmt = fmt;
		this.ac_input = ac_input;
		this.fmtid = fmtid;
		this.type_input = type_input;
		this.asvc = asvc;
		this.zts = zts;
		this.dgec = dgec;
		this.tysg = tysg;
		this.clo = clo;
		this.cust = cust;
		this.ownr_trt = ownr_trt;
		this.cabssysid = cabssysid;
		this.crissysid = crissysid;
		this.family_typ = family_typ;
		this.ccna = ccna;
		this.lata = lata;
		this.scantyp = scantyp;
		this.fc = fc;

	}
}
