package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Pkt_t implements org.omg.CORBA.portable.IDLEntity { 
	public String PROCSG_MODE_IND;
	public String ORD_WRK_TASK_IND;
	public String MSEG_CD;
	public String STS_3_CD;

	public Pkt_t () {
	}
	public Pkt_t (String PROCSG_MODE_IND, String ORD_WRK_TASK_IND, String MSEG_CD, String STS_3_CD) { 
		this.PROCSG_MODE_IND = PROCSG_MODE_IND;
		this.ORD_WRK_TASK_IND = ORD_WRK_TASK_IND;
		this.MSEG_CD = MSEG_CD;
		this.STS_3_CD = STS_3_CD;

	} 
}
