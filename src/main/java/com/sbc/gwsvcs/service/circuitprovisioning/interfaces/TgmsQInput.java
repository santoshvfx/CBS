// $Id: TgmsQInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmsQInput implements java.io.Serializable { 
	public String ims_region;
	public String groupaccess;
	public String groupid;
	public String trknbr;
	public String fmt;

	public TgmsQInput () {
	}
	public TgmsQInput (String ims_region, String groupaccess, String groupid, String trknbr, String fmt) { 
		this.ims_region = ims_region;
		this.groupaccess = groupaccess;
		this.groupid = groupid;
		this.trknbr = trknbr;
		this.fmt = fmt;

	}
}
