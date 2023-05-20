package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Nbr_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Nbr_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t();
		value.TN = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TRMNTG_TRAF_AREA_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BDY_DT = new String (_bytes, 0, _j);
		}
		value.Rls2Dt = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MTCH_IND = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TN);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.TRMNTG_TRAF_AREA_CD.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.BDY_DT.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.Rls2Dt);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.MTCH_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[5];
				members[0] = new StructMember();
				members[0].name = "TN";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "TRMNTG_TRAF_AREA_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (7, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TrmntgTrafAreaCd_tHelper.id(), "TrmntgTrafAreaCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "BDY_DT";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (3, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Dy_tHelper.id(), "Dy_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "Rls2Dt";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[4] = new StructMember();
				members[4].name = "MTCH_IND";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.IndCd_tHelper.id(), "IndCd_t", members[4].type);
				myTc = ORB.init().create_struct_tc (id(), "Nbr_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Nbr_t:1.0"; } 
}
