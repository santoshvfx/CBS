// $Id: ZretsiGrpDef.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZretsiGrpDef implements java.io.Serializable { 
	public String trkequip;
	public String tnn_tl;
	public String tnn_f;
	public String tnn_g;
	public String tc_cd;
	public String tnn_s;
	public String tnn_l;
	public String key;
	public String isasia;

	public ZretsiGrpDef () {
	}
	public ZretsiGrpDef (String trkequip, String tnn_tl, String tnn_f, String tnn_g, String tc_cd, String tnn_s, String tnn_l, String key, String isasia) { 
		this.trkequip = trkequip;
		this.tnn_tl = tnn_tl;
		this.tnn_f = tnn_f;
		this.tnn_g = tnn_g;
		this.tc_cd = tc_cd;
		this.tnn_s = tnn_s;
		this.tnn_l = tnn_l;
		this.key = key;
		this.isasia = isasia;

	}
}
