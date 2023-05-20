package com.sbc.gwsvcs.service.toplistener.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.*;

public class TOPLISTENER_constHelper { 
	private static TypeCode myTc = null;
	private TOPLISTENER_constHelper() { }
	public static String id() { return "IDL:com/sbc/gwsvcs/service/toplistener/interfaces/TOPLISTENER_const:1.0"; }

	synchronized public static TypeCode type() { 
		if (myTc == null) 
			myTc = org.omg.CORBA.ORB.init().create_interface_tc(id(), "TOPLISTENER_const"); 
		return myTc; 
	}

	public static void insert (org.omg.CORBA.Any a, TOPLISTENER_const t) { 
		OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static TOPLISTENER_const extract (org.omg.CORBA.Any a) { 
		InputStream i = a.create_input_stream();
		return read (i); 
	}

	public static TOPLISTENER_const read (InputStream i) { 
		return TOPLISTENER_constHelper.narrow (i.read_Object()); 
	}

	public static void write (OutputStream o, TOPLISTENER_const val) { 
		o.write_Object (val); 
	}

	public static TOPLISTENER_const narrow (org.omg.CORBA.Object obj) throws BAD_PARAM { 
		if (obj == null) 
			return null; 
		if (obj instanceof TOPLISTENER_const) 
			return (TOPLISTENER_const) obj; 
		if (!obj._is_a(id())) 
			throw new BAD_PARAM(); 
		Delegate dup = ((ObjectImpl) obj)._get_delegate();
		TOPLISTENER_const ret = new _TOPLISTENER_constStub (dup);
		return ret; 
	} 
}

