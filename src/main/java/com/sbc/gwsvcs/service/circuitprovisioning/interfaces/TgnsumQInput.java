// $Id: TgnsumQInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgnsumQInput implements java.io.Serializable { 
	public String ims_region;
	public int start_page;
	public int end_page;
	public String location;
	public String fmt;
	public String display;
	public String starttgn;

	public TgnsumQInput () {
	}
	public TgnsumQInput (String ims_region, int start_page, int end_page, String location, String fmt, String display, String starttgn) { 
		this.ims_region = ims_region;
		this.start_page = start_page;
		this.end_page = end_page;
		this.location = location;
		this.fmt = fmt;
		this.display = display;
		this.starttgn = starttgn;

	}
}
