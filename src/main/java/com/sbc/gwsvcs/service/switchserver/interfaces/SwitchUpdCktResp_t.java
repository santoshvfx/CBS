package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchUpdCktResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public int PLHDR_STS;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchUpdCktResp_t () {
	}
	public SwitchUpdCktResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, int PLHDR_STS, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.PLHDR_STS = PLHDR_STS;
		this.Umsg = Umsg;

	} 
}
