// $Id: TgmrUInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmrUInput implements java.io.Serializable { 
	public String ims_region;
	public String groupaccess;
	public String fmt;
	public String idtype;
	public String groupid;
	public String date;
	public String act;
	public String cspc;

	public TgmrUInput () {
	}
	public TgmrUInput (String ims_region, String groupaccess, String fmt, String idtype, String groupid, String date, String act, String cspc) { 
		this.ims_region = ims_region;
		this.groupaccess = groupaccess;
		this.fmt = fmt;
		this.idtype = idtype;
		this.groupid = groupid;
		this.date = date;
		this.act = act;
		this.cspc = cspc;

	}
}
