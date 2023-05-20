// $Id: WaQInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class WaQInput implements java.io.Serializable { 
	public String ims_region;
	public String clo;
	public String cac;
	public String ckt;

	public WaQInput () {
	}
	public WaQInput (String ims_region, String clo, String cac, String ckt) { 
		this.ims_region = ims_region;
		this.clo = clo;
		this.cac = cac;
		this.ckt = ckt;

	}
}
