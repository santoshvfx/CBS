package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ComnUattr_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ComnUattr_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.AIS_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGNM_CAPY_QTY = new String (_bytes, 0, _j);
		}
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
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGNM_USE_QTY = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.AVAIL_CAPY_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CALL_CT = new String (_bytes, 0, _j);
		}
		value.DATETNONLIST = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.read (i);
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
		value.DUE_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.INTRCPT_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_3_NBR = new String (_bytes, 0, _j);
		}
		value.NEGDATE = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.NEGSTATUS = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.REQCATG = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLS_DT_IND = new String (_bytes, 0, _j);
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
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_SELT_IND = new String (_bytes, 0, _j);
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
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.AIS_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.ASGNM_CAPY_QTY.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
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
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.ASGNM_USE_QTY.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.AVAIL_CAPY_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.CALL_CT.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.write (o, value.DATETNONLIST);
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
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.DUE_DT);
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.INTRCPT_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.ORD_3_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.write (o, value.NEGDATE);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.NEGSTATUS.getBytes();
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
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.REQCATG.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.RLS_DT_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
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
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TN_SELT_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
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
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[21];
				members[0] = new StructMember();
				members[0].name = "AIS_CD";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AisCd_tHelper.id(), "AisCd_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "ASGNM_CAPY_QTY";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (3, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnmCapyQty_tHelper.id(), "AsgnmCapyQty_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "Asglim";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (6, members[2].type);
				members[3] = new StructMember();
				members[3].name = "ASGNM_USE_QTY";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (3, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnmUseQty_tHelper.id(), "AsgnmUseQty_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "AVAIL_CAPY_IND";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AvailCapyInd_tHelper.id(), "AvailCapyInd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "CALL_CT";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (3, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CallCt_tHelper.id(), "CallCt_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "DATETNONLIST";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[7] = new StructMember();
				members[7].name = "DISCT_ASGNM_CTGY_CD";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (8, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctAsgnmCtgyCd_tHelper.id(), "DisctAsgnmCtgyCd_t", members[7].type);
				members[8] = new StructMember();
				members[8].name = "DISCT_CO_TYPE_CD";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (6, members[8].type);
				members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DisctCoTypeCd_tHelper.id(), "DisctCoTypeCd_t", members[8].type);
				members[9] = new StructMember();
				members[9].name = "DUE_DT";
				members[9].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[10] = new StructMember();
				members[10].name = "INTRCPT_CD";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (4, members[10].type);
				members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.IntrcptCd_tHelper.id(), "IntrcptCd_t", members[10].type);
				members[11] = new StructMember();
				members[11].name = "ORD_3_NBR";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (14, members[11].type);
				members[11].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ord3Nbr_tHelper.id(), "Ord3Nbr_t", members[11].type);
				members[12] = new StructMember();
				members[12].name = "NEGDATE";
				members[12].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[13] = new StructMember();
				members[13].name = "NEGSTATUS";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (2, members[13].type);
				members[13].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.NegStatus_tHelper.id(), "NegStatus_t", members[13].type);
				members[14] = new StructMember();
				members[14].name = "NPUB_IND";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (2, members[14].type);
				members[14].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.NpubInd_tHelper.id(), "NpubInd_t", members[14].type);
				members[15] = new StructMember();
				members[15].name = "REQCATG";
				members[15].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[15].type = ORB.init().create_array_tc (11, members[15].type);
				members[15].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ReqCtgyCd_tHelper.id(), "ReqCtgyCd_t", members[15].type);
				members[16] = new StructMember();
				members[16].name = "RLS_DT_IND";
				members[16].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[16].type = ORB.init().create_array_tc (2, members[16].type);
				members[16].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.RlsDtInd_tHelper.id(), "RlsDtInd_t", members[16].type);
				members[17] = new StructMember();
				members[17].name = "TN_RLS_DT_TX";
				members[17].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[17].type = ORB.init().create_array_tc (9, members[17].type);
				members[17].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRlsDtTx_tHelper.id(), "TnRlsDtTx_t", members[17].type);
				members[18] = new StructMember();
				members[18].name = "TN_RMK_TX";
				members[18].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[18].type = ORB.init().create_array_tc (61, members[18].type);
				members[18].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnRmkTx_tHelper.id(), "TnRmkTx_t", members[18].type);
				members[19] = new StructMember();
				members[19].name = "TN_SELT_IND";
				members[19].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[19].type = ORB.init().create_array_tc (2, members[19].type);
				members[19].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltInd_tHelper.id(), "TnSeltInd_t", members[19].type);
				members[20] = new StructMember();
				members[20].name = "TN_TYPE_CD";
				members[20].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[20].type = ORB.init().create_array_tc (2, members[20].type);
				members[20].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnTypeCd_tHelper.id(), "TnTypeCd_t", members[20].type);
				myTc = ORB.init().create_struct_tc (id(), "ComnUattr_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/ComnUattr_t:1.0"; } 
}
