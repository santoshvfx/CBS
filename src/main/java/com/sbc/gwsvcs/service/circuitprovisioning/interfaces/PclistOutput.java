// $Id: PclistOutput.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class PclistOutput implements java.io.Serializable { 
	public int last;
	public String x_for;
	public String mask;
	public String rro;
	public String wk_pos;
	public String uac;
	public String start;
	public String end_item;
	public String rro_wkp1;
	public String rro_wkp2;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef PclistGrp[];
	public String msgid;

	public PclistOutput () {
	}
	public PclistOutput (int last, String x_for, String mask, String rro, String wk_pos, String uac, String start, String end_item, String rro_wkp1, String rro_wkp2, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef PclistGrp[], String msgid) { 
		this.last = last;
		this.x_for = x_for;
		this.mask = mask;
		this.rro = rro;
		this.wk_pos = wk_pos;
		this.uac = uac;
		this.start = start;
		this.end_item = end_item;
		this.rro_wkp1 = rro_wkp1;
		this.rro_wkp2 = rro_wkp2;
		this.PclistGrp = PclistGrp;
		this.msgid = msgid;

	}
}
