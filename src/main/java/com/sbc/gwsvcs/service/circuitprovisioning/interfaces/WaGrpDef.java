// $Id: WaGrpDef.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class WaGrpDef implements java.io.Serializable { 
	public String clo_nbr1;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date1;
	public String act1;
	public String te1;
	public String clo_nbr2;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date2;
	public String act2;
	public String te2;

	public WaGrpDef () {
	}
	public WaGrpDef (String clo_nbr1, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date1, String act1, String te1, String clo_nbr2, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date2, String act2, String te2) { 
		this.clo_nbr1 = clo_nbr1;
		this.due_date1 = due_date1;
		this.act1 = act1;
		this.te1 = te1;
		this.clo_nbr2 = clo_nbr2;
		this.due_date2 = due_date2;
		this.act2 = act2;
		this.te2 = te2;

	}
}
