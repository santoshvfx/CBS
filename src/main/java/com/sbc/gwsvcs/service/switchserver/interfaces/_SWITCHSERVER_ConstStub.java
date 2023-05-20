package com.sbc.gwsvcs.service.switchserver.interfaces;
import org.omg.CORBA.portable.*;
import org.omg.CORBA.*;
import com.sbc.vicunalite.api.orb.*;
    import com.sbc.vicunalite.api.*;

public class _SWITCHSERVER_ConstStub extends ObjectImpl implements SWITCHSERVER_Const { 
	private static final String _type_ids[] = { 
		"IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SWITCHSERVER_Const:1.0"
		};
	public _SWITCHSERVER_ConstStub (Delegate d) { _set_delegate (d); }
	public String[] _ids() { return (String[]) _type_ids.clone(); }
}
