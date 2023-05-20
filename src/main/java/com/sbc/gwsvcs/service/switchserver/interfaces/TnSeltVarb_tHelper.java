package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TnSeltVarb_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private TnSeltVarb_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.QTY_CT = new String (_bytes, 0, _j);
		}
		value.NpaPrfx = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.read (i);
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BLG_DY = new String (_bytes, 0, _j);
		}
		value.SpcfcTn = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t value) { 
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.QTY_CT.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.write (o, value.NpaPrfx);
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.BLG_DY.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.SpcfcTn);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[4];
				members[0] = new StructMember();
				members[0].name = "QTY_CT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (7, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.QtyCt_tHelper.id(), "QtyCt_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "NpaPrfx";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "BLG_DY";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (3, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Dy_tHelper.id(), "Dy_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "SpcfcTn";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "TnSeltVarb_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/TnSeltVarb_t:1.0"; } 
}
