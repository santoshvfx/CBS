package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class OrdCtl_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private OrdCtl_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DATA_IND = new String (_bytes, 0, _j);
		}
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
			byte[] _bytes = new byte[52];
			i.read_octet_array (_bytes, 0, 52);
			int _j;
			for (_j = 0; _j < 52; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CKT_2_ID = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[29];
			i.read_octet_array (_bytes, 0, 29);
			int _j;
			for (_j = 0; _j < 29; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FRM_RMK_TX = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.Trdata = new com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Trdata[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DATA_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
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
			byte[] _bytes = new byte[52];
			byte[] _bytes_src = value.CKT_2_ID.getBytes();
			int _j;
			for (_j = 0; _j < 52 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 52);
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
			byte[] _bytes = new byte[29];
			byte[] _bytes_src = value.FRM_RMK_TX.getBytes();
			int _j;
			for (_j = 0; _j < 29 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 29);
		}
		{ 
			o.write_long (value.Trdata.length);
			for (int __i = 0; __i < value.Trdata.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tHelper.write (o, value.Trdata[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "DATA_IND";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.DataInd_tHelper.id(), "DataInd_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "CTRL_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtrlCd_tHelper.id(), "CtrlCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "CKT_2_ID";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (52, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ckt2Id_tHelper.id(), "Ckt2Id_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "ASGNM_REQ_IND";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (2, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.AsgnmReqInd_tHelper.id(), "AsgnmReqInd_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "FRM_RMK_TX";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (29, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.FrmRmkTx_tHelper.id(), "FrmRmkTx_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "Trdata";
				members[5].type = com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tHelper.type();
				members[5].type = ORB.init().create_sequence_tc (0, members[5].type);
				myTc = ORB.init().create_struct_tc (id(), "OrdCtl_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/OrdCtl_t:1.0"; } 
}
