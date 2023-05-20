package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Pkt_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Pkt_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t();
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PROCSG_MODE_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_WRK_TASK_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MSEG_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STS_3_CD = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t value) { 
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.PROCSG_MODE_IND.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.ORD_WRK_TASK_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.MSEG_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.STS_3_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[4];
				members[0] = new StructMember();
				members[0].name = "PROCSG_MODE_IND";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (4, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ProcsgModeInd_tHelper.id(), "ProcsgModeInd_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "ORD_WRK_TASK_IND";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.OrdWrkTaskInd_tHelper.id(), "OrdWrkTaskInd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "MSEG_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.MsegCd_tHelper.id(), "MsegCd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "STS_3_CD";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (4, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Sts3Cd_tHelper.id(), "Sts3Cd_t", members[3].type);
				myTc = ORB.init().create_struct_tc (id(), "Pkt_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Pkt_t:1.0"; } 
}
