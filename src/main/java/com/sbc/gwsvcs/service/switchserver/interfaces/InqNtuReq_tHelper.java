package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class InqNtuReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private InqNtuReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t();
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{
			byte[] _bytes = new byte[25];
			i.read_octet_array (_bytes, 0, 25);
			int _j;
			for (_j = 0; _j < 25; _j++)
				if (_bytes[_j] == 0)
					break;
			value.HI_VALU = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CKT_OPTN_IND = new String (_bytes, 0, _j);
		}
		value.VIEW_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LAST_VIEW_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FOPTN = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
		{
			byte[] _bytes = new byte[25];
			byte[] _bytes_src = value.HI_VALU.getBytes();
			int _j;
			for (_j = 0; _j < 25 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 25);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CKT_OPTN_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.VIEW_DT);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.LAST_VIEW_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.FOPTN.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[6];
				members[0] = new StructMember();
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "HI_VALU";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (25, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.HiValu_tHelper.id(), "HiValu_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "CKT_OPTN_IND";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CktOptnInd_tHelper.id(), "CktOptnInd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "VIEW_DT";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[4] = new StructMember();
				members[4].name = "LAST_VIEW_IND";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.LastViewInd_tHelper.id(), "LastViewInd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "FOPTN";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (2, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Foptn_tHelper.id(), "Foptn_t", members[5].type);
				myTc = ORB.init().create_struct_tc (id(), "InqNtuReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/InqNtuReq_t:1.0"; } 
}
