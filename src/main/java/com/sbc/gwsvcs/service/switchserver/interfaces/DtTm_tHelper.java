package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class DtTm_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private DtTm_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		value.TM = com.sbc.gwsvcs.service.switchserver.interfaces.Tm_tHelper.read (i);
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TM_OST = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.DT);
		com.sbc.gwsvcs.service.switchserver.interfaces.Tm_tHelper.write (o, value.TM);
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TM_OST.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[3];
				members[0] = new StructMember();
				members[0].name = "DT";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "TM";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Tm_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "TM_OST";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TmOst_tHelper.id(), "TmOst_t", members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "DtTm_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/DtTm_t:1.0"; } 
}
