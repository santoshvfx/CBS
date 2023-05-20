package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class EqpOld_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private EqpOld_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t();
		{
			byte[] _bytes = new byte[46];
			i.read_octet_array (_bytes, 0, 46);
			int _j;
			for (_j = 0; _j < 46; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[61];
			i.read_octet_array (_bytes, 0, 61);
			int _j;
			for (_j = 0; _j < 61; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_RMK_TX = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (6)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.AsgLim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AsgLim[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t value) { 
		{
			byte[] _bytes = new byte[46];
			byte[] _bytes_src = value.SWITCH_ID.getBytes();
			int _j;
			for (_j = 0; _j < 46 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 46);
		}
		{
			byte[] _bytes = new byte[61];
			byte[] _bytes_src = value.TN_RMK_TX.getBytes();
			int _j;
			for (_j = 0; _j < 61 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 61);
		}
		{ 
			if (value.AsgLim.length > (6)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.AsgLim.length);
			for (int __i = 0; __i < value.AsgLim.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.write (o, value.AsgLim[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "SWITCH_ID";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (46, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchId_tHelper.id(), "SwitchId_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "TN_RMK_TX";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (61, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRmkTx_tHelper.id(), "TnRmkTx_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "AsgLim";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (6, members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "EqpOld_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/EqpOld_t:1.0"; } 
}
