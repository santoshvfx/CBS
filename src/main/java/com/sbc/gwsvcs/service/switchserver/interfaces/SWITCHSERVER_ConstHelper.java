package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.*;

public class SWITCHSERVER_ConstHelper { 
	private static TypeCode myTc = null;
	private SWITCHSERVER_ConstHelper() { }
	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SWITCHSERVER_Const:1.0"; }

	synchronized public static TypeCode type() { 
		if (myTc == null) 
			myTc = org.omg.CORBA.ORB.init().create_interface_tc(id(), "SWITCHSERVER_Const"); 
		return myTc; 
	}

	public static void insert (org.omg.CORBA.Any a, SWITCHSERVER_Const t) { 
		OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static SWITCHSERVER_Const extract (org.omg.CORBA.Any a) { 
		InputStream i = a.create_input_stream();
		return read (i); 
	}

	public static SWITCHSERVER_Const read (InputStream i) { 
		return SWITCHSERVER_ConstHelper.narrow (i.read_Object()); 
	}

	public static void write (OutputStream o, SWITCHSERVER_Const val) { 
		o.write_Object (val); 
	}

	public static SWITCHSERVER_Const narrow (org.omg.CORBA.Object obj) throws BAD_PARAM { 
		if (obj == null) 
			return null; 
		if (obj instanceof SWITCHSERVER_Const) 
			return (SWITCHSERVER_Const) obj; 
		if (!obj._is_a(id())) 
			throw new BAD_PARAM(); 
		Delegate dup = ((ObjectImpl) obj)._get_delegate();
		SWITCHSERVER_Const ret = new _SWITCHSERVER_ConstStub (dup);
		return ret; 
	} 
}

