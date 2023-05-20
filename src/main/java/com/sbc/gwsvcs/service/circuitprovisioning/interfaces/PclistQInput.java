// $Id: PclistQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class PclistQInput implements java.io.Serializable { 
	public String ims_region;
	public String rro;
	public String pos;
	public String uac;

	public PclistQInput () {
	}
	public PclistQInput (String ims_region, String rro, String pos, String uac) { 
		this.ims_region = ims_region;
		this.rro = rro;
		this.pos = pos;
		this.uac = uac;

	}
}
