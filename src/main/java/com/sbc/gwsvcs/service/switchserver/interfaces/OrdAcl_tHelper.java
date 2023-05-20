package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class OrdAcl_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private OrdAcl_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t();
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ACTN_CD = new String (_bytes, 0, _j);
		}
		value.AclCec = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.read (i);
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGN_USOC_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CLS_SVC_USOC_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.USOC = new String (_bytes, 0, _j);
		}
		value.TEL_LN_ID = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i);
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
			value.CKT_2_ID = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t value) { 
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.ACTN_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.write (o, value.AclCec);
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.ASGN_USOC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.CLS_SVC_USOC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.USOC.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TEL_LN_ID);
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
			byte[] _bytes_src = value.CKT_2_ID.getBytes();
			int _j;
			for (_j = 0; _j < 52 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 52);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "ACTN_CD";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (4, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ActnCd_tHelper.id(), "ActnCd_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "AclCec";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "ASGN_USOC_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnUsocCd_tHelper.id(), "AsgnUsocCd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "CLS_SVC_USOC_CD";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (6, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ClsSvcUsocCd_tHelper.id(), "ClsSvcUsocCd_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "USOC";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (6, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Usoc_tHelper.id(), "Usoc_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "TEL_LN_ID";
				members[5].type = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
				members[6] = new StructMember();
				members[6].name = "FRM_RMK_TX";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (29, members[6].type);
				members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FrmRmkTx_tHelper.id(), "FrmRmkTx_t", members[6].type);
				members[7] = new StructMember();
				members[7].name = "CKT_2_ID";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (52, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ckt2Id_tHelper.id(), "Ckt2Id_t", members[7].type);
				myTc = ORB.init().create_struct_tc (id(), "OrdAcl_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/OrdAcl_t:1.0"; } 
}
