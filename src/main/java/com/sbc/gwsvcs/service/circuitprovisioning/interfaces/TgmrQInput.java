// $Id: TgmrQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmrQInput implements java.io.Serializable { 
	public String ims_region;
	public String groupaccess;

	public TgmrQInput () {
	}
	public TgmrQInput (String ims_region, String groupaccess) { 
		this.ims_region = ims_region;
		this.groupaccess = groupaccess;

	}
}
