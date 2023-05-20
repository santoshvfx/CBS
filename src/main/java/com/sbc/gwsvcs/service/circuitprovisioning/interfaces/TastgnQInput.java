// $Id: TastgnQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TastgnQInput implements java.io.Serializable { 
	public String ims_region;
	public String ckt;
	public String clo;
	public String end;
	public String key;
	public String type;

	public TastgnQInput () {
	}
	public TastgnQInput (String ims_region, String ckt, String clo, String end, String key, String type) { 
		this.ims_region = ims_region;
		this.ckt = ckt;
		this.clo = clo;
		this.end = end;
		this.key = key;
		this.type = type;

	}
}
