// $Id: RdlocQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class RdlocQInput implements java.io.Serializable { 
	public String ims_region;
	public String location;

	public RdlocQInput () {
	}
	public RdlocQInput (String ims_region, String location) { 
		this.ims_region = ims_region;
		this.location = location;

	}
}
