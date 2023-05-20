// $Id: ZretsiQInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZretsiQInput implements java.io.Serializable { 
	public String ims_region;
	public String location;
	public String isopt;
	public String asopt;
	public String equiptype;
	public String lvl;

	public ZretsiQInput () {
	}
	public ZretsiQInput (String ims_region, String location, String isopt, String asopt, String equiptype, String lvl) { 
		this.ims_region = ims_region;
		this.location = location;
		this.isopt = isopt;
		this.asopt = asopt;
		this.equiptype = equiptype;
		this.lvl = lvl;

	}
}
