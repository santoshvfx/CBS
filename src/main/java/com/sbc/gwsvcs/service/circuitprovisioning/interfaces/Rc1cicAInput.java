// $Id: Rc1cicAInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1cicAInput implements java.io.Serializable { 
	public String ims_region;
	public String cmd;
	public String loca;
	public String locz;
	public String autopost;
	public String tcic;
	public String start;
	public String end;

	public Rc1cicAInput () {
	}
	public Rc1cicAInput (String ims_region, String cmd, String loca, String locz, String autopost, String tcic, String start, String end) { 
		this.ims_region = ims_region;
		this.cmd = cmd;
		this.loca = loca;
		this.locz = locz;
		this.autopost = autopost;
		this.tcic = tcic;
		this.start = start;
		this.end = end;

	}
}
