package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class QueryCktResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private QueryCktResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PTY_CKT = new String (_bytes, 0, _j);
		}
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.read (i);
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
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CO_ASGNM_TYPE = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CTGY_CD = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.EqpOldId = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.EqpOldId[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.read (i);
			} 
		}
		value.CktRecSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PTY_CKT.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.write (o, value.INQ_CHNG_DT_TM);
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
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.CO_ASGNM_TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
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
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CTGY_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			o.write_long (value.EqpOldId.length);
			for (int __i = 0; __i < value.EqpOldId.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.write (o, value.EqpOldId[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.write (o, value.CktRecSvc);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "PTY_CKT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.PtyCkt_tHelper.id(), "PtyCkt_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "INQ_CHNG_DT_TM";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "ASGN_USOC_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Au_tHelper.id(), "Au_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "CO_ASGNM_TYPE";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (7, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Caty_tHelper.id(), "Caty_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "CLS_SVC_USOC_CD";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (6, members[4].type);
				members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ClsSvcUsocCd_tHelper.id(), "ClsSvcUsocCd_t", members[4].type);
				members[5] = new StructMember();
				members[5].name = "CTGY_CD";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (2, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CtgyCd_tHelper.id(), "CtgyCd_t", members[5].type);
				members[6] = new StructMember();
				members[6].name = "EqpOldId";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.type();
				members[6].type = ORB.init().create_sequence_tc (0, members[6].type);
				members[7] = new StructMember();
				members[7].name = "CktRecSvc";
				members[7].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "QueryCktResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/QueryCktResp_t:1.0"; } 
}
