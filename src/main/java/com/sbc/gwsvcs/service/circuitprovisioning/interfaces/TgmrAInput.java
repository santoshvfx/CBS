// $Id: TgmrAInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmrAInput implements java.io.Serializable { 
	public String ims_region;
	public String cmd;
	public String adminarea;
	public String fmt;
	public String idtype;
	public String custswloc;
	public String cspc;
	public String mco;
	public String groupid;

	public TgmrAInput () {
	}
	public TgmrAInput (String ims_region, String cmd, String adminarea, String fmt, String idtype, String custswloc, String cspc, String mco, String groupid) { 
		this.ims_region = ims_region;
		this.cmd = cmd;
		this.adminarea = adminarea;
		this.fmt = fmt;
		this.idtype = idtype;
		this.custswloc = custswloc;
		this.cspc = cspc;
		this.mco = mco;
		this.groupid = groupid;

	}
}
