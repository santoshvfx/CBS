// $Id: TastgnUInput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TastgnUInput implements java.io.Serializable { 
	public String ims_region;
	public int idx;
	public String cmd;
	public String key;
	public String end;
	public String prev_endtrunk;
	public String tgn;
	public String starttmn;
	public String starttrunkno;
	public String endtrunk;

	public TastgnUInput () {
	}
	public TastgnUInput (String ims_region, int idx, String cmd, String key, String end, String prev_endtrunk, String tgn, String starttmn, String starttrunkno, String endtrunk) { 
		this.ims_region = ims_region;
		this.idx = idx;
		this.cmd = cmd;
		this.key = key;
		this.end = end;
		this.prev_endtrunk = prev_endtrunk;
		this.tgn = tgn;
		this.starttmn = starttmn;
		this.starttrunkno = starttrunkno;
		this.endtrunk = endtrunk;

	}
}
