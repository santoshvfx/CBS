// $Id: PclistAInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class PclistAInput implements java.io.Serializable { 
	public String ims_region;
	public String rro;
	public String pos;
	public String uac;
	public String c;
	public String fcn;
	public String date;
	public String clo;
	public String seg;
	public String ckl;
	public String order;
	public String cktid;
	public String cust;

	public PclistAInput () {
	}
	public PclistAInput (String ims_region, String rro, String pos, String uac, String c, String fcn, String date, String clo, String seg, String ckl, String order, String cktid, String cust) { 
		this.ims_region = ims_region;
		this.rro = rro;
		this.pos = pos;
		this.uac = uac;
		this.c = c;
		this.fcn = fcn;
		this.date = date;
		this.clo = clo;
		this.seg = seg;
		this.ckl = ckl;
		this.order = order;
		this.cktid = cktid;
		this.cust = cust;

	}
}
