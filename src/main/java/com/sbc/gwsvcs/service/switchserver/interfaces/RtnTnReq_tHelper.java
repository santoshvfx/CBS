package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class RtnTnReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private RtnTnReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LIST_TYPE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_LN_CT = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (5)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.AccpLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AccpLst[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (100)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.RtnLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RtnLst[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.LIST_TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TN_LN_CT.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{ 
			if (value.AccpLst.length > (5)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.AccpLst.length);
			for (int __i = 0; __i < value.AccpLst.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.AccpLst[__i]);
			} 
		}
		{ 
			if (value.RtnLst.length > (100)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.RtnLst.length);
			for (int __i = 0; __i < value.RtnLst.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.RtnLst[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "LIST_TYPE";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ListType_tHelper.id(), "ListType_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "TN_LN_CT";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (4, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnLnCt_tHelper.id(), "TnLnCt_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "AccpLst";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (5, members[2].type);
				members[3] = new StructMember();
				members[3].name = "RtnLst";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				members[3].type = ORB.init().create_sequence_tc (100, members[3].type);
				myTc = ORB.init().create_struct_tc (id(), "RtnTnReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/RtnTnReq_t:1.0"; } 
}
