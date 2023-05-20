// $Id: GcocmaAInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class GcocmaAInput implements java.io.Serializable { 
	public String ims_region;
	public String clo;
	public String ord;
	public String ordtype;
	public String orig;
	public String cust;
	public String app;
	public String dd;
	public String mdfr;
	public String wco;
	public String oco;
	public String cco;
	public String doc;
	public String relord;
	public String ndnw;
	public String id;
	public String sid;
	public String ckt;
	public String cid;
	public String fmt;
	public String actn;
	public String old_id;
	public String old_id_fmt;

	public GcocmaAInput () {
	}
	public GcocmaAInput (String ims_region, String clo, String ord, String ordtype, String orig, String cust, String app, String dd, String mdfr, String wco, String oco, String cco, String doc, String relord, String ndnw, String id, String sid, String ckt, String cid, String fmt, String actn, String old_id, String old_id_fmt) { 
		this.ims_region = ims_region;
		this.clo = clo;
		this.ord = ord;
		this.ordtype = ordtype;
		this.orig = orig;
		this.cust = cust;
		this.app = app;
		this.dd = dd;
		this.mdfr = mdfr;
		this.wco = wco;
		this.oco = oco;
		this.cco = cco;
		this.doc = doc;
		this.relord = relord;
		this.ndnw = ndnw;
		this.id = id;
		this.sid = sid;
		this.ckt = ckt;
		this.cid = cid;
		this.fmt = fmt;
		this.actn = actn;
		this.old_id = old_id;
		this.old_id_fmt = old_id_fmt;

	}
}
