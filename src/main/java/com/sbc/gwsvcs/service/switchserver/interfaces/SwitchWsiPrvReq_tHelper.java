package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchWsiPrvReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchWsiPrvReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		value.InqEx = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		value.DUAL_SVC_IND = i.read_char ();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_TYPE_2_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGNM_ACTN_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.VIEW_DT = new String (_bytes, 0, _j);
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
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.InqEx);
		o.write_char(value.DUAL_SVC_IND);
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.ORD_TYPE_2_CD.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.ASGNM_ACTN_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.VIEW_DT.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
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
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[8];
				members[0] = new StructMember();
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "InqEx";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "DUAL_SVC_IND";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Indicator_tHelper.id(), "Indicator_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "ORD_TYPE_2_CD";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (10, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.OrdType2Cd_tHelper.id(), "OrdType2Cd_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "ASGNM_ACTN_CD";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (4, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnmActnCd_tHelper.id(), "AsgnmActnCd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "VIEW_DT";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (9, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ViewDt_tHelper.id(), "ViewDt_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "SWITCH_WC";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (7, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWc_tHelper.id(), "SwitchWc_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "ORD_2_NBR";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (13, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ord2Nbr_tHelper.id(), "Ord2Nbr_t", members[7].type);
				myTc = ORB.init().create_struct_tc (id(), "SwitchWsiPrvReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchWsiPrvReq_t:1.0"; } 
}
