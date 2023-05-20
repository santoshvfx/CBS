// $Id: Rc1scnGrpDef.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class Rc1scnGrpDef implements java.io.Serializable { 
	public String select;
	public String ac;
	public String fmt;
	public String cktid;
	public String type;
	public String stat;
	public String date;

	public Rc1scnGrpDef () {
	}
	public Rc1scnGrpDef (String select, String ac, String fmt, String cktid, String type, String stat, String date) { 
		this.select = select;
		this.ac = ac;
		this.fmt = fmt;
		this.cktid = cktid;
		this.type = type;
		this.stat = stat;
		this.date = date;

	}
}
