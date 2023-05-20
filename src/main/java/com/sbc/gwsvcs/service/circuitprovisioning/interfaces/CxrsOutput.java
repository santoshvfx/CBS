// $Id: CxrsOutput.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class CxrsOutput implements java.io.Serializable { 
	public int last;
	public String term_A;
	public String term_Z;
	public String fac_des;
	public String fac_typ;
	public String cac;
	public String clo;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_query;
	public String cxr_type;
	public String subpath;
	public String fac_grp;
	public String dds;
	public String f_a;
	public String trans_rate;
	public String avail;
	public String currscan;
	public String timing_pri_sec;
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef CxrsGrp[];
	public String msgid;

	public CxrsOutput () {
	}
	public CxrsOutput (int last, String term_A, String term_Z, String fac_des, String fac_typ, String cac, String clo, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t due_date_query, String cxr_type, String subpath, String fac_grp, String dds, String f_a, String trans_rate, String avail, String currscan, String timing_pri_sec, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef CxrsGrp[], String msgid) { 
		this.last = last;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.fac_des = fac_des;
		this.fac_typ = fac_typ;
		this.cac = cac;
		this.clo = clo;
		this.due_date_query = due_date_query;
		this.cxr_type = cxr_type;
		this.subpath = subpath;
		this.fac_grp = fac_grp;
		this.dds = dds;
		this.f_a = f_a;
		this.trans_rate = trans_rate;
		this.avail = avail;
		this.currscan = currscan;
		this.timing_pri_sec = timing_pri_sec;
		this.CxrsGrp = CxrsGrp;
		this.msgid = msgid;

	}
}
