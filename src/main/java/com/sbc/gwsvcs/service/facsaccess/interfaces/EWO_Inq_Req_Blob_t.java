package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class EWO_Inq_Req_Blob_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_t ENGMNU;

	public EWO_Inq_Req_Blob_t () {
	}
	public EWO_Inq_Req_Blob_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_t ENGMNU) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.ENGMNU = ENGMNU;

	} 
}
