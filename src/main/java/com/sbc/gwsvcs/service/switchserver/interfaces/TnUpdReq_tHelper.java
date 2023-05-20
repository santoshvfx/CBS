package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TnUpdReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private TnUpdReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t();
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
		{
			byte[] _bytes = new byte[25];
			i.read_octet_array (_bytes, 0, 25);
			int _j;
			for (_j = 0; _j < 25; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_MASK_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_PARSE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_UPD_FCN_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.OPTNL_MSG_TX = new String (_bytes, 0, _j);
		}
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.read (i);
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.INVNTY_ORD_NBR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STEP_ID = new String (_bytes, 0, _j);
		}
		value.Updopt = com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tHelper.read (i);
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
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ACTN_CD_MEMB = new String (_bytes, 0, _j);
		}
		value.Ntu = com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tHelper.read (i);
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SOURCE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PORT_IND = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t value) { 
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
		{
			byte[] _bytes = new byte[25];
			byte[] _bytes_src = value.TN_MASK_ID.getBytes();
			int _j;
			for (_j = 0; _j < 25 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 25);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TN_PARSE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TN_UPD_FCN_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.OPTNL_MSG_TX.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.write (o, value.INQ_CHNG_DT_TM);
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.INVNTY_ORD_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.STEP_ID.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tHelper.write (o, value.Updopt);
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
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.ACTN_CD_MEMB.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tHelper.write (o, value.Ntu);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.SOURCE.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PORT_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[15];
				members[0] = new StructMember();
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "TN_HI_RNGE_ID";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (25, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnHiRngeId_tHelper.id(), "TnHiRngeId_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "TN_MASK_ID";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (25, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnMaskId_tHelper.id(), "TnMaskId_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "TN_PARSE_CD";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (5, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnParseCd_tHelper.id(), "TnParseCd_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "TN_UPD_FCN_CD";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (4, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdFcnCd_tHelper.id(), "TnUpdFcnCd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "OPTNL_MSG_TX";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (10, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.OptnlMsgTx_tHelper.id(), "OptnlMsgTx_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "INQ_CHNG_DT_TM";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[7] = new StructMember();
				members[7].name = "INVNTY_ORD_NBR";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (14, members[7].type);
				members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.InvntyOrdNbr_tHelper.id(), "InvntyOrdNbr_t", members[7].type);
				members[8] = new StructMember();
				members[8].name = "STEP_ID";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (4, members[8].type);
				members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.StepId_tHelper.id(), "StepId_t", members[8].type);
				members[9] = new StructMember();
				members[9].name = "Updopt";
				members[9].type = com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tHelper.type();
				members[10] = new StructMember();
				members[10].name = "ACTN_CD";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (4, members[10].type);
				members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ActnCd_tHelper.id(), "ActnCd_t", members[10].type);
				members[11] = new StructMember();
				members[11].name = "ACTN_CD_MEMB";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (4, members[11].type);
				members[11].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ActnCd_tHelper.id(), "ActnCd_t", members[11].type);
				members[12] = new StructMember();
				members[12].name = "Ntu";
				members[12].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tHelper.type();
				members[13] = new StructMember();
				members[13].name = "SOURCE";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (7, members[13].type);
				members[13].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Source_tHelper.id(), "Source_t", members[13].type);
				members[14] = new StructMember();
				members[14].name = "PORT_IND";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (2, members[14].type);
				members[14].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.PortInd_tHelper.id(), "PortInd_t", members[14].type);
				myTc = ORB.init().create_struct_tc (id(), "TnUpdReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/TnUpdReq_t:1.0"; } 
}
