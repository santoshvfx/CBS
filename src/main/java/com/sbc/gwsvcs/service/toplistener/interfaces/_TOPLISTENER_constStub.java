package com.sbc.gwsvcs.service.toplistener.interfaces;
import org.omg.CORBA.portable.*;
import org.omg.CORBA.*;
import com.sbc.vicunalite.api.orb.*;
    import com.sbc.vicunalite.api.*;

public class _TOPLISTENER_constStub extends ObjectImpl implements TOPLISTENER_const { 
	private static final String _type_ids[] = { 
		"IDL:com/sbc/gwsvcs/service/toplistener/interfaces/TOPLISTENER_const:1.0"
		};
	public _TOPLISTENER_constStub (Delegate d) { _set_delegate (d); }
	public String[] _ids() { return (String[]) _type_ids.clone(); }
}
