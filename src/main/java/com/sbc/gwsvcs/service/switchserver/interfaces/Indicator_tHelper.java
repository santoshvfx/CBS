package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;

public class Indicator_tHelper { 
	private static TypeCode myTc = null;
	private Indicator_tHelper () { }

	public static char read(org.omg.CORBA.portable.InputStream i) {
		char value = i.read_char ();
		return value;
		}

	public static void write(org.omg.CORBA.portable.OutputStream o, char value) {
		o.write_char(value);
		}

	public static void insert (org.omg.CORBA.Any a, char t) { 
		a.insert_char (t); 
	}
	public static char extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return a.extract_char (); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.switchserver.interfaces.Indicator_t:1.0"; }

	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember[] members = new StructMember[1];
			members[0] = new StructMember();
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Indicator_tHelper.id(), "Indicator_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	} 
}
