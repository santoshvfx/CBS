package com.sbc.gwsvcs.service.toplistener.interfaces;
import org.omg.CORBA.portable.*;
import org.omg.CORBA.*;
  import com.sbc.vicunalite.api.orb.*;
  import com.sbc.vicunalite.api.*;

public abstract class _TOPLISTENER_constImplBase extends DynamicImplementation implements TOPLISTENER_const { 
	private static final String _type_ids[] = { 
		"IDL:com/sbc/gwsvcs/service/toplistener/interfaces/TOPLISTENER_const:1.0" 
	};
	private static java.util.Dictionary _methods = new java.util.Hashtable();
	static { 
	}
	public _TOPLISTENER_constImplBase () { }
	public String[] _ids() { return (String[]) _type_ids.clone(); }
	public void invoke (ServerRequest req) { 
		switch (((java.lang.Integer) _methods.get(req.operation())).intValue()) { 
			default: 
				throw new BAD_OPERATION ();  
		} 
	} 
}
