// $Id: ZrxlocQInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrxlocQInput implements java.io.Serializable { 
	public String ims_region;
	public String location;
	public String pc_network;
	public String pc_cluster;
	public String pc_point;

	public ZrxlocQInput () {
	}
	public ZrxlocQInput (String ims_region, String location, String pc_network, String pc_cluster, String pc_point) { 
		this.ims_region = ims_region;
		this.location = location;
		this.pc_network = pc_network;
		this.pc_cluster = pc_cluster;
		this.pc_point = pc_point;

	}
}
