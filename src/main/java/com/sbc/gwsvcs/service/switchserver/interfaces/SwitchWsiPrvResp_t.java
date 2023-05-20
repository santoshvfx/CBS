package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiPrvResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t Pkt;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t[] CktRec;
	public com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t OrdCtl;
	public com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t[] OrdCarRec;
	public com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t[] OrdAcl;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchWsiPrvResp_t () {
	}
	public SwitchWsiPrvResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t Pkt, com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t[] CktRec, com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t OrdCtl, com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t[] OrdCarRec, com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t[] OrdAcl, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.Pkt = Pkt;
		this.CktRec = CktRec;
		this.OrdCtl = OrdCtl;
		this.OrdCarRec = OrdCarRec;
		this.OrdAcl = OrdAcl;
		this.Umsg = Umsg;

	} 
}
