package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Updopt_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Updopt_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t();
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_LN_CT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PNDG_IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SELT_STATE_CD = new String (_bytes, 0, _j);
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
		value.Uattr = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (3)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.EMP_ID = new String[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				{
					byte[] _bytes = new byte[9];
					i.read_octet_array (_bytes, 0, 9);
					int _j;
					for (_j = 0; _j < 9; _j++)
						if (_bytes[_j] == 0)
							break;
					value.EMP_ID[__i] = new String (_bytes, 0, _j);
				}
			} 
		}
		{ 
			int __seqLength = i.read_long();
			if (__seqLength > (2)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			value.CHG_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.CHG_DT[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
			} 
		}
		value.FROM_AVAIL_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		value.TO_AVAIL_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.Comp = new com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Comp[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.Memb = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Memb[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
			} 
		}
		value.Tric = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t value) { 
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TN_LN_CT.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PNDG_IND.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.SELT_STATE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
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
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.write (o, value.Uattr);
		{ 
			if (value.EMP_ID.length > (3)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.EMP_ID.length);
			for (int __i = 0; __i < value.EMP_ID.length; __i++) { 
				{
					byte[] _bytes = new byte[9];
					byte[] _bytes_src = value.EMP_ID[__i].getBytes();
					int _j;
					for (_j = 0; _j < 9 - 1; _j++)
						_bytes[_j] = _bytes_src[_j];
					_bytes[_j] = 0;
					o.write_octet_array (_bytes, 0, 9);
				}
			} 
		}
		{ 
			if (value.CHG_DT.length > (2)) {
				throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
				}
			o.write_long (value.CHG_DT.length);
			for (int __i = 0; __i < value.CHG_DT.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.CHG_DT[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.FROM_AVAIL_DT);
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.TO_AVAIL_DT);
		{ 
			o.write_long (value.Comp.length);
			for (int __i = 0; __i < value.Comp.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tHelper.write (o, value.Comp[__i]);
			} 
		}
		{ 
			o.write_long (value.Memb.length);
			for (int __i = 0; __i < value.Memb.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Memb[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Tric);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ic);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "TN_LN_CT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (4, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnLnCt_tHelper.id(), "TnLnCt_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "PNDG_IND";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.PndgInd_tHelper.id(), "PndgInd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "SELT_STATE_CD";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (6, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SeltStateCd_tHelper.id(), "SeltStateCd_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "TN_LIST_NBR";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (4, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.TnListNbr_tHelper.id(), "TnListNbr_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "Uattr";
				members[4].type = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.type();
				members[5] = new StructMember();
				members[5].name = "EMP_ID";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (9, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.EmpId_tHelper.id(), "EmpId_t", members[5].type);
				members[5].type = ORB.init().create_sequence_tc (3, members[5].type);
				members[6] = new StructMember();
				members[6].name = "CHG_DT";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[6].type = ORB.init().create_sequence_tc (2, members[6].type);
				members[7] = new StructMember();
				members[7].name = "FROM_AVAIL_DT";
				members[7].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[8] = new StructMember();
				members[8].name = "TO_AVAIL_DT";
				members[8].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				members[9] = new StructMember();
				members[9].name = "Comp";
				members[9].type = com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tHelper.type();
				members[9].type = ORB.init().create_sequence_tc (0, members[9].type);
				members[10] = new StructMember();
				members[10].name = "Memb";
				members[10].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[10].type = ORB.init().create_sequence_tc (0, members[10].type);
				members[11] = new StructMember();
				members[11].name = "Tric";
				members[11].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[12] = new StructMember();
				members[12].name = "Ic";
				members[12].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "Updopt_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Updopt_t:1.0"; } 
}
