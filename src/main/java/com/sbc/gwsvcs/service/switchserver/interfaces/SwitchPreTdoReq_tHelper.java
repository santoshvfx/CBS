package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;

public class SwitchPreTdoReq_tHelper { 
	private static TypeCode myTc = null;

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t read(org.omg.CORBA.portable.InputStream i) {
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.read (i);
		return value;
		}

	public static void write(org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value) {
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.write (o, value);
		}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t t) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.insert (a, t); 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.extract (a); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreTdoReq_t:1.0"; }

	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember[] members = new StructMember[1];
			members[0] = new StructMember();
			members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.type();
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreTdoReq_tHelper.id(), "SwitchPreTdoReq_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	} 
}
