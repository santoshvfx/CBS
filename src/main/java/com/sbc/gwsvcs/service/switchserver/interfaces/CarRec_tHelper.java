package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CarRec_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CarRec_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CTRL_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGNM_REQ_IND = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[52];
			i.read_octet_array (_bytes, 0, 52);
			int _j;
			for (_j = 0; _j < 52; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CKT_TERMN_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_TYPE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CORR_CD = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.CarAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.CarAcl[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CTRL_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.ASGNM_REQ_IND.getBytes();
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
			byte[] _bytes = new byte[52];
			byte[] _bytes_src = value.CKT_TERMN_ID.getBytes();
			int _j;
			for (_j = 0; _j < 52 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 52);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.ORD_TYPE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CORR_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			o.write_long (value.CarAcl.length);
			for (int __i = 0; __i < value.CarAcl.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tHelper.write (o, value.CarAcl[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "CTRL_CD";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtrlCd_tHelper.id(), "CtrlCd_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "ASGNM_REQ_IND";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnmReqInd_tHelper.id(), "AsgnmReqInd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "FDT_TX";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FdtTx_tHelper.id(), "FdtTx_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "FRM_RMK_TX";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (29, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FrmRmkTx_tHelper.id(), "FrmRmkTx_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "CKT_TERMN_ID";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (52, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CktTermnId_tHelper.id(), "CktTermnId_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "ORD_TYPE_CD";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (2, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.OrdTypeCd_tHelper.id(), "OrdTypeCd_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "CORR_CD";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (2, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CorrCd_tHelper.id(), "CorrCd_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "CarAcl";
				members[7].type = com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tHelper.type();
				members[7].type = ORB.init().create_sequence_tc (0, members[7].type);
				myTc = ORB.init().create_struct_tc (id(), "CarRec_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CarRec_t:1.0"; } 
}
