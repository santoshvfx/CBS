// $Id: TgmrGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class TgmrGrpDef implements java.io.Serializable { 
	public String s;
	public String fmt;
	public String groupid;
	public String idtype;
	public String clonbr;
	public String date;
	public String act;
	public String pcapcz;
	public String drtype;

	public TgmrGrpDef () {
	}
	public TgmrGrpDef (String s, String fmt, String groupid, String idtype, String clonbr, String date, String act, String pcapcz, String drtype) { 
		this.s = s;
		this.fmt = fmt;
		this.groupid = groupid;
		this.idtype = idtype;
		this.clonbr = clonbr;
		this.date = date;
		this.act = act;
		this.pcapcz = pcapcz;
		this.drtype = drtype;

	}
}
