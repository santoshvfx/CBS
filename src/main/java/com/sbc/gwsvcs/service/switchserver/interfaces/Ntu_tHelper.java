package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Ntu_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Ntu_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t();
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{
			byte[] _bytes = new byte[25];
			i.read_octet_array (_bytes, 0, 25);
			int _j;
			for (_j = 0; _j < 25; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_HI_RNGE_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_PARSE_CD = new String (_bytes, 0, _j);
		}
		value.Uattr = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.AVDT_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RTE_IDX = new String (_bytes, 0, _j);
		}
		value.Fctr = com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tHelper.read (i);
		value.Memb = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Ic[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
		{
			byte[] _bytes = new byte[25];
			byte[] _bytes_src = value.TN_HI_RNGE_ID.getBytes();
			int _j;
			for (_j = 0; _j < 25 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 25);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TN_PARSE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.write (o, value.Uattr);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.AVDT_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.RTE_IDX.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tHelper.write (o, value.Fctr);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Memb);
		{ 
			o.write_long (value.Ic.length);
			for (int __i = 0; __i < value.Ic.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ic[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[9];
				members[0] = new StructMember();
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "TN_HI_RNGE_ID";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (25, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnHiRngeId_tHelper.id(), "TnHiRngeId_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "TN_PARSE_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (5, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnParseCd_tHelper.id(), "TnParseCd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "Uattr";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.type();
				members[4] = new StructMember();
				members[4].name = "AVDT_IND";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AvdtInd_tHelper.id(), "AvdtInd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "RTE_IDX";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (5, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.RteIdx_tHelper.id(), "RteIdx_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "Fctr";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tHelper.type();
				members[7] = new StructMember();
				members[7].name = "Memb";
				members[7].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[8] = new StructMember();
				members[8].name = "Ic";
				members[8].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[8].type = ORB.init().create_sequence_tc (0, members[8].type);
				myTc = ORB.init().create_struct_tc (id(), "Ntu_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Ntu_t:1.0"; } 
}
