// $Id: CktsrQInput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CktsrQInput implements java.io.Serializable { 
	public String ims_region;
	public int number_of_pages;
	public String fmt;
	public String cktid;

	public CktsrQInput () {
	}
	public CktsrQInput (String ims_region, int number_of_pages, String fmt, String cktid) { 
		this.ims_region = ims_region;
		this.number_of_pages = number_of_pages;
		this.fmt = fmt;
		this.cktid = cktid;

	}
}
