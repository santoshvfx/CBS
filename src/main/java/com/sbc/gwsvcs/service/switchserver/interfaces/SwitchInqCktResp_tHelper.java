package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchInqCktResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchInqCktResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.InqCktResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.InqCktResp[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ExchKeyId = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Umsg[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		{ 
			o.write_long (value.InqCktResp.length);
			for (int __i = 0; __i < value.InqCktResp.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tHelper.write (o, value.InqCktResp[__i]);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.ExchKeyId.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{ 
			o.write_long (value.Umsg.length);
			for (int __i = 0; __i < value.Umsg.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.write (o, value.Umsg[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "InqCktResp";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[2] = new StructMember();
				members[2].name = "ExchKeyId";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (7, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ExchKeyId_tHelper.id(), "ExchKeyId_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "Umsg";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.type();
				members[3].type = ORB.init().create_sequence_tc (0, members[3].type);
				myTc = ORB.init().create_struct_tc (id(), "SwitchInqCktResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchInqCktResp_t:1.0"; } 
}
