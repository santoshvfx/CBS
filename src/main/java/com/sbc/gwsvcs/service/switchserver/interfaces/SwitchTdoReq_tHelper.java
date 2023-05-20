package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchTdoReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchTdoReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MSEG_CD = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.Car = new com.sbc.gwsvcs.service.switchserver.interfaces.Car_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Car[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Car_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.Masg = new com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Masg[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_WC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_2_NBR = new String (_bytes, 0, _j);
		}
		value.ORD_2_DDT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.MSEG_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			o.write_long (value.Car.length);
			for (int __i = 0; __i < value.Car.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Car_tHelper.write (o, value.Car[__i]);
			} 
		}
		{ 
			o.write_long (value.Masg.length);
			for (int __i = 0; __i < value.Masg.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tHelper.write (o, value.Masg[__i]);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.SWITCH_WC.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ORD_2_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.ORD_2_DDT);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[7];
				members[0] = new StructMember();
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "MSEG_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.MsegCd_tHelper.id(), "MsegCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "Car";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Car_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				members[3] = new StructMember();
				members[3].name = "Masg";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tHelper.type();
				members[3].type = ORB.init().create_sequence_tc (0, members[3].type);
				members[4] = new StructMember();
				members[4].name = "SWITCH_WC";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (7, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWc_tHelper.id(), "SwitchWc_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "ORD_2_NBR";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (13, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ord2Nbr_tHelper.id(), "Ord2Nbr_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "ORD_2_DDT";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "SwitchTdoReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchTdoReq_t:1.0"; } 
}
