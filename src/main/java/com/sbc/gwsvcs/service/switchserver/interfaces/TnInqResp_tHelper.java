package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TnInqResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private TnInqResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t();
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DISCT_ASGNM_CTGY_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DISCT_CO_TYPE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_RLS_DT_TX = new String (_bytes, 0, _j);
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
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.read (i);
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
			value.TN_LIST_NBR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_LIM_VALU_CD = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.DISCT_ASGNM_CTGY_CD.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.DISCT_CO_TYPE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.TN_RLS_DT_TX.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
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
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.write (o, value.INQ_CHNG_DT_TM);
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
			byte[] _bytes_src = value.TN_LIST_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TN_LIM_VALU_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[1].name = "DISCT_ASGNM_CTGY_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (8, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctAsgnmCtgyCd_tHelper.id(), "DisctAsgnmCtgyCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "DISCT_CO_TYPE_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctCoTypeCd_tHelper.id(), "DisctCoTypeCd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "TN_RLS_DT_TX";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (9, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRlsDtTx_tHelper.id(), "TnRlsDtTx_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "TN_RMK_TX";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (61, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRmkTx_tHelper.id(), "TnRmkTx_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "INQ_CHNG_DT_TM";
				members[5].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[6] = new StructMember();
				members[6].name = "TN_TYPE_CD";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (2, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnTypeCd_tHelper.id(), "TnTypeCd_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "TN_LIST_NBR";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (4, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnListNbr_tHelper.id(), "TnListNbr_t", members[7].type);
				members[8] = new StructMember();
				members[8].name = "TN_LIM_VALU_CD";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (4, members[8].type);
				members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnLimValuCd_tHelper.id(), "TnLimValuCd_t", members[8].type);
				myTc = ORB.init().create_struct_tc (id(), "TnInqResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/TnInqResp_t:1.0"; } 
}
