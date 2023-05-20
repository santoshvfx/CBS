// $Id: GcintcQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class GcintcQInput implements java.io.Serializable { 
	public String ims_region;
	public int T1_flag;
	public String ord_class;
	public String app;
	public String dd;
	public String t_input;
	public String entry_input;
	public String admin_area;
	public String swc;
	public String total_interval;
	public String npa;

	public GcintcQInput () {
	}
	public GcintcQInput (String ims_region, int T1_flag, String ord_class, String app, String dd, String t_input, String entry_input, String admin_area, String swc, String total_interval, String npa) { 
		this.ims_region = ims_region;
		this.T1_flag = T1_flag;
		this.ord_class = ord_class;
		this.app = app;
		this.dd = dd;
		this.t_input = t_input;
		this.entry_input = entry_input;
		this.admin_area = admin_area;
		this.swc = swc;
		this.total_interval = total_interval;
		this.npa = npa;

	}
}
