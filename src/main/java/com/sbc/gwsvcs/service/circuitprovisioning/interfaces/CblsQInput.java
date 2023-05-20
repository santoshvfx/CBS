// $Id: CblsQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CblsQInput implements java.io.Serializable { 
	public String ims_region;
	public String term_A;
	public String term_Z;
	public String cable;
	public String from_unit;
	public String last_unit;

	public CblsQInput () {
	}
	public CblsQInput (String ims_region, String term_A, String term_Z, String cable, String from_unit, String last_unit) { 
		this.ims_region = ims_region;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.cable = cable;
		this.from_unit = from_unit;
		this.last_unit = last_unit;

	}
}
