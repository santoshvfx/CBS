// $Id: TgnsumGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgnsumGrpDef implements java.io.Serializable { 
	public String s;
	public String tgn;
	public String r;
	public String gac;
	public String end;
	public String desc;
	public String location;
	public String cllitrkname;
	public String remarks;

	public TgnsumGrpDef () {
	}
	public TgnsumGrpDef (String s, String tgn, String r, String gac, String end, String desc, String location, String cllitrkname, String remarks) { 
		this.s = s;
		this.tgn = tgn;
		this.r = r;
		this.gac = gac;
		this.end = end;
		this.desc = desc;
		this.location = location;
		this.cllitrkname = cllitrkname;
		this.remarks = remarks;

	}
}
