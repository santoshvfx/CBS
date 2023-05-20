// $Id: Dt_t.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Dt_t implements java.io.Serializable { 
	public String YR;
	public String MO;
	public String DY;

	public Dt_t () {
	}
	public Dt_t (String YR, String MO, String DY) { 
		this.YR = YR;
		this.MO = MO;
		this.DY = DY;

	}
}
