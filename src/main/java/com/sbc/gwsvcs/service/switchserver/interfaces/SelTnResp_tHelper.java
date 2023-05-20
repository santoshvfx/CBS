package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SelTnResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SelTnResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t();
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (5)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.Nbr = new com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Nbr[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tHelper.read (i);
			} 
		}
		value.Lst = com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.REQ_STS_2_CD = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t value) { 
		{ 
			if (value.Nbr.length > (5)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.Nbr.length);
			for (int __i = 0; __i < value.Nbr.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tHelper.write (o, value.Nbr[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tHelper.write (o, value.Lst);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.REQ_STS_2_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Nbr";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (5, members[0].type);
				members[1] = new StructMember();
				members[1].name = "Lst";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "REQ_STS_2_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.IndCd_tHelper.id(), "IndCd_t", members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "SelTnResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SelTnResp_t:1.0"; } 
}
