package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class AssignableTnResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private AssignableTnResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t();
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (6)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.Asglim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Asglim[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.read (i);
			} 
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
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.NPUB_IND = new String (_bytes, 0, _j);
		}
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
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLCT_IND = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
		{ 
			if (value.Asglim.length > (6)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.Asglim.length);
			for (int __i = 0; __i < value.Asglim.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.write (o, value.Asglim[__i]);
			} 
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
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.NPUB_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
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
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ic);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DLCT_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[13];
				members[0] = new StructMember();
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "Asglim";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (6, members[1].type);
				members[2] = new StructMember();
				members[2].name = "TN_RMK_TX";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (61, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRmkTx_tHelper.id(), "TnRmkTx_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "INQ_CHNG_DT_TM";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[4] = new StructMember();
				members[4].name = "TN_TYPE_CD";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnTypeCd_tHelper.id(), "TnTypeCd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "NPUB_IND";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (2, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.NpubInd_tHelper.id(), "NpubInd_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "DISCT_ASGNM_CTGY_CD";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (8, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctAsgnmCtgyCd_tHelper.id(), "DisctAsgnmCtgyCd_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "DISCT_CO_TYPE_CD";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (6, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctCoTypeCd_tHelper.id(), "DisctCoTypeCd_t", members[7].type);
				members[8] = new StructMember();
				members[8].name = "TN_RLS_DT_TX";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (9, members[8].type);
				members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRlsDtTx_tHelper.id(), "TnRlsDtTx_t", members[8].type);
				members[9] = new StructMember();
				members[9].name = "RT_ZONE";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (4, members[9].type);
				members[9].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.RtZone_tHelper.id(), "RtZone_t", members[9].type);
				members[10] = new StructMember();
				members[10].name = "CTX_NM";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (19, members[10].type);
				members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtxNm_tHelper.id(), "CtxNm_t", members[10].type);
				members[11] = new StructMember();
				members[11].name = "Ic";
				members[11].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[12] = new StructMember();
				members[12].name = "DLCT_IND";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (2, members[12].type);
				members[12].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DlctInd_tHelper.id(), "DlctInd_t", members[12].type);
				myTc = ORB.init().create_struct_tc (id(), "AssignableTnResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/AssignableTnResp_t:1.0"; } 
}
