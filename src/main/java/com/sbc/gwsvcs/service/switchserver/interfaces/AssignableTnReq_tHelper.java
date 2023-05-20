package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class AssignableTnReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private AssignableTnReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t();
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
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_TN_REQ_QTY = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_TYPE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RT_ZONE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[19];
			i.read_octet_array (_bytes, 0, 19);
			int _j;
			for (_j = 0; _j < 19; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CTX_NM = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t value) { 
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
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ic);
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.SWITCH_TN_REQ_QTY.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TN_TYPE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.RT_ZONE.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[19];
			byte[] _bytes_src = value.CTX_NM.getBytes();
			int _j;
			for (_j = 0; _j < 19 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 19);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "TN_HI_RNGE_ID";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (25, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnHiRngeId_tHelper.id(), "TnHiRngeId_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "Ic";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[3] = new StructMember();
				members[3].name = "SWITCH_TN_REQ_QTY";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (3, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnReqQty_tHelper.id(), "SwitchTnReqQty_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "TN_TYPE_CD";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnTypeCd_tHelper.id(), "TnTypeCd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "RT_ZONE";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (4, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.RtZone_tHelper.id(), "RtZone_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "CTX_NM";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (19, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtxNm_tHelper.id(), "CtxNm_t", members[6].type);
				myTc = ORB.init().create_struct_tc (id(), "AssignableTnReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/AssignableTnReq_t:1.0"; } 
}
