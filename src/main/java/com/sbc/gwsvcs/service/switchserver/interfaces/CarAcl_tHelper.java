package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CarAcl_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CarAcl_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t();
		{
			byte[] _bytes = new byte[52];
			i.read_octet_array (_bytes, 0, 52);
			int _j;
			for (_j = 0; _j < 52; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CKT_TERMN_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ACTN_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[46];
			i.read_octet_array (_bytes, 0, 46);
			int _j;
			for (_j = 0; _j < 46; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_ID = new String (_bytes, 0, _j);
		}
		value.NO_REUSE_IND = i.read_char ();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TR_CALL_IND = new String (_bytes, 0, _j);
		}
		value.TEL_LN_ID = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SSP_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FDT_TX = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[29];
			i.read_octet_array (_bytes, 0, 29);
			int _j;
			for (_j = 0; _j < 29; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRM_RMK_TX = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CTRL_GRP_ID = new String (_bytes, 0, _j);
		}
		value.AclCec = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t value) { 
		{
			byte[] _bytes = new byte[52];
			byte[] _bytes_src = value.CKT_TERMN_ID.getBytes();
			int _j;
			for (_j = 0; _j < 52 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 52);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.ACTN_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[46];
			byte[] _bytes_src = value.SWITCH_ID.getBytes();
			int _j;
			for (_j = 0; _j < 46 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 46);
		}
		o.write_char(value.NO_REUSE_IND);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TR_CALL_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TEL_LN_ID);
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SSP_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FDT_TX.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[29];
			byte[] _bytes_src = value.FRM_RMK_TX.getBytes();
			int _j;
			for (_j = 0; _j < 29 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 29);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.CTRL_GRP_ID.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.write (o, value.AclCec);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[11];
				members[0] = new StructMember();
				members[0].name = "CKT_TERMN_ID";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (52, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CktTermnId_tHelper.id(), "CktTermnId_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "ACTN_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (4, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ActnCd_tHelper.id(), "ActnCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "SWITCH_ID";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (46, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchId_tHelper.id(), "SwitchId_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "NO_REUSE_IND";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Indicator_tHelper.id(), "Indicator_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "TR_CALL_IND";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (2, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TrCallInd_tHelper.id(), "TrCallInd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "TEL_LN_ID";
				members[5].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				members[6] = new StructMember();
				members[6].name = "SSP_IND";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (2, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SspInd_tHelper.id(), "SspInd_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "FDT_TX";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (6, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FdtTx_tHelper.id(), "FdtTx_t", members[7].type);
				members[8] = new StructMember();
				members[8].name = "FRM_RMK_TX";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (29, members[8].type);
				members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FrmRmkTx_tHelper.id(), "FrmRmkTx_t", members[8].type);
				members[9] = new StructMember();
				members[9].name = "CTRL_GRP_ID";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (8, members[9].type);
				members[9].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtrlGrpId_tHelper.id(), "CtrlGrpId_t", members[9].type);
				members[10] = new StructMember();
				members[10].name = "AclCec";
				members[10].type = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "CarAcl_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CarAcl_t:1.0"; } 
}
