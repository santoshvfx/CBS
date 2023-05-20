// $Id: EqpaAInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpaAInput implements java.io.Serializable { 
	public String ims_region;
	public String location;
	public String equip_code;
	public String level;
	public String activity;
	public String clo1;
	public String clo2;
	public String clo3;
	public String ddmon;
	public String ddday;
	public String ddyr;
	public String cktid;

	public EqpaAInput () {
	}
	public EqpaAInput (String ims_region, String location, String equip_code, String level, String activity, String clo1, String clo2, String clo3, String ddmon, String ddday, String ddyr, String cktid) { 
		this.ims_region = ims_region;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;
		this.activity = activity;
		this.clo1 = clo1;
		this.clo2 = clo2;
		this.clo3 = clo3;
		this.ddmon = ddmon;
		this.ddday = ddday;
		this.ddyr = ddyr;
		this.cktid = cktid;

	}
}
