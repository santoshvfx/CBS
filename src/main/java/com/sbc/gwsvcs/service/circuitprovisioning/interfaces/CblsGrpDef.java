// $Id: CblsGrpDef.java,v 1.1 2002/09/29 04:10:27 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CblsGrpDef implements java.io.Serializable { 
	public String unit;
	public String subd_f;
	public String subd_t;
	public String asgt_rstn;
	public String activity_cur;
	public String activity_pnd;
	public String d;
	public String f;
	public String cktid_clo;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date;

	public CblsGrpDef () {
	}
	public CblsGrpDef (String unit, String subd_f, String subd_t, String asgt_rstn, String activity_cur, String activity_pnd, String d, String f, String cktid_clo, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date) { 
		this.unit = unit;
		this.subd_f = subd_f;
		this.subd_t = subd_t;
		this.asgt_rstn = asgt_rstn;
		this.activity_cur = activity_cur;
		this.activity_pnd = activity_pnd;
		this.d = d;
		this.f = f;
		this.cktid_clo = cktid_clo;
		this.due_date = due_date;

	}
}
