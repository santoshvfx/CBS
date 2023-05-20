// $Id: ZrtdsoGrpDef.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZrtdsoGrpDef implements java.io.Serializable { 
	public String fieldname;
	public String fieldvalue;

	public ZrtdsoGrpDef () {
	}
	public ZrtdsoGrpDef (String fieldname, String fieldvalue) { 
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;

	}
}
