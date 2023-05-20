// $Id: EqpscQInput.java,v 1.2 2002/09/29 04:13:00 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpscQInput implements java.io.Serializable { 
	public String ims_region;
	public String location;
	public String equip_code;
	public String level;
	public String relayrack;
	public String unit;

	public EqpscQInput () {
	}
	public EqpscQInput (String ims_region, String location, String equip_code, String level, String relayrack, String unit) { 
		this.ims_region = ims_region;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;
		this.relayrack = relayrack;
		this.unit = unit;

	}
}
