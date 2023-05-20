// $Id: RdlocGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class RdlocGrpDef implements java.io.Serializable { 
	public String locationcode;
	public String fmt;
	public String idtype;
	public String stat;
	public String effdate;

	public RdlocGrpDef () {
	}
	public RdlocGrpDef (String locationcode, String fmt, String idtype, String stat, String effdate) { 
		this.locationcode = locationcode;
		this.fmt = fmt;
		this.idtype = idtype;
		this.stat = stat;
		this.effdate = effdate;

	}
}
