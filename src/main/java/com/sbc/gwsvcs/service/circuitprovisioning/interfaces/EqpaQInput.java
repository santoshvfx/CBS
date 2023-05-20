// $Id: EqpaQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class EqpaQInput implements java.io.Serializable { 
	public String ims_region;
	public String location;
	public String equip_code;
	public String level;

	public EqpaQInput () {
	}
	public EqpaQInput (String ims_region, String location, String equip_code, String level) { 
		this.ims_region = ims_region;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;

	}
}
