// $Id: ZrtdsoAInput.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrtdsoAInput implements java.io.Serializable { 
	public String ims_region;
	public String tablename;
	public String fieldvalue;

	public ZrtdsoAInput () {
	}
	public ZrtdsoAInput (String ims_region, String tablename, String fieldvalue) { 
		this.ims_region = ims_region;
		this.tablename = tablename;
		this.fieldvalue = fieldvalue;

	}
}
