// $Id: CxrsQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CxrsQInput implements java.io.Serializable { 
	public String ims_region;
	public String term_A;
	public String term_Z;
	public String fac_des;
	public String fac_typ;

	public CxrsQInput () {
	}
	public CxrsQInput (String ims_region, String term_A, String term_Z, String fac_des, String fac_typ) { 
		this.ims_region = ims_region;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.fac_des = fac_des;
		this.fac_typ = fac_typ;

	}
}
