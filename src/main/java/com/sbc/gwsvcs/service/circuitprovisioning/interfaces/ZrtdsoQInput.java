// $Id: ZrtdsoQInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrtdsoQInput implements java.io.Serializable { 
	public String ims_region;
	public String tablename;
	public String tablekey;
	public String adminarea;
	public String tablerecord;

	public ZrtdsoQInput () {
	}
	public ZrtdsoQInput (String ims_region, String tablename, String tablekey, String adminarea, String tablerecord) { 
		this.ims_region = ims_region;
		this.tablename = tablename;
		this.tablekey = tablekey;
		this.adminarea = adminarea;
		this.tablerecord = tablerecord;

	}
}
