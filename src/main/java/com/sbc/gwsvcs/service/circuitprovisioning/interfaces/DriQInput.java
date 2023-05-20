// $Id: DriQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class DriQInput implements java.io.Serializable { 
	public String ims_region;
	public String clo;
	public String ckt;
	public String cac;
	public String ord;

	public DriQInput () {
	}
	public DriQInput (String ims_region, String clo, String ckt, String cac, String ord) { 
		this.ims_region = ims_region;
		this.clo = clo;
		this.ckt = ckt;
		this.cac = cac;
		this.ord = ord;

	}
}
