// $Id: DriOutputHelper.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class DriOutputHelper { 
	private static TypeCode myTc = null;
	private DriOutputHelper () {
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/DriOutput:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput();
		value.last = i.read_long ();
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cmd = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[28];
			i.read_octet_array (_bytes, 0, 28);
			int _j;
			for (_j = 0; _j < 28; _j++)
				if (_bytes[_j] == 0)
					break;
			value.mask = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.x_for = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[48];
			i.read_octet_array (_bytes, 0, 48);
			int _j;
			for (_j = 0; _j < 48; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ckt = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loca = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locz = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.clo = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.act = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[15];
			i.read_octet_array (_bytes, 0, 15);
			int _j;
			for (_j = 0; _j < 15; _j++)
				if (_bytes[_j] == 0)
					break;
			value.stat = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.segowner = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.slc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.puls = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.dv = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cac = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[22];
			i.read_octet_array (_bytes, 0, 22);
			int _j;
			for (_j = 0; _j < 22; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ord = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.jt_msg = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ctx = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sig_op = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.drc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.mic = new String (_bytes, 0, _j);
		}
		value.dd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ec_dsgcon = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ec_tel = new String (_bytes, 0, _j);
		}
		value.dlrd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ec_mco = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ec_oco = new String (_bytes, 0, _j);
		}
		value.cdlrd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ic = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[17];
			i.read_octet_array (_bytes, 0, 17);
			int _j;
			for (_j = 0; _j < 17; _j++)
				if (_bytes[_j] == 0)
					break;
			value.pon = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ver = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[54];
			i.read_octet_array (_bytes, 0, 54);
			int _j;
			for (_j = 0; _j < 54; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ckr = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.refnum = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ic_dsgcon = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[18];
			i.read_octet_array (_bytes, 0, 18);
			int _j;
			for (_j = 0; _j < 18; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ic_tel = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.acccode = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[31];
			i.read_octet_array (_bytes, 0, 31);
			int _j;
			for (_j = 0; _j < 31; _j++)
				if (_bytes[_j] == 0)
					break;
			value.dsg_addr = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.nc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.pri_loc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.nci1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tlvt1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tlvr1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.apot = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sec_loc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.nci2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tlvt2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tlvr2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.spot = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[36];
			i.read_octet_array (_bytes, 0, 36);
			int _j;
			for (_j = 0; _j < 36; _j++)
				if (_bytes[_j] == 0)
					break;
			value.other_loc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[48];
			i.read_octet_array (_bytes, 0, 48);
			int _j;
			for (_j = 0; _j < 48; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cfa_a = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[48];
			i.read_octet_array (_bytes, 0, 48);
			int _j;
			for (_j = 0; _j < 48; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cfa_z = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[232];
			i.read_octet_array (_bytes, 0, 232);
			int _j;
			for (_j = 0; _j < 232; _j++)
				if (_bytes[_j] == 0)
					break;
			value.remarks = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[81];
			i.read_octet_array (_bytes, 0, 81);
			int _j;
			for (_j = 0; _j < 81; _j++)
				if (_bytes[_j] == 0)
					break;
			value.msgid = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[53];
			members[0] = new StructMember();
			members[0].name = "last";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
			members[1] = new StructMember();
			members[1].name = "cmd";
			members[1].type = ORB.init().create_array_tc (
				8, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "mask";
			members[2].type = ORB.init().create_array_tc (
				28, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "x_for";
			members[3].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "ckt";
			members[4].type = ORB.init().create_array_tc (
				48, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "loca";
			members[5].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "locz";
			members[6].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "clo";
			members[7].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "act";
			members[8].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "stat";
			members[9].type = ORB.init().create_array_tc (
				15, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "segowner";
			members[10].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "slc";
			members[11].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12] = new StructMember();
			members[12].name = "puls";
			members[12].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13] = new StructMember();
			members[13].name = "dv";
			members[13].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[14] = new StructMember();
			members[14].name = "cac";
			members[14].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[15] = new StructMember();
			members[15].name = "ord";
			members[15].type = ORB.init().create_array_tc (
				22, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[16] = new StructMember();
			members[16].name = "jt_msg";
			members[16].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[17] = new StructMember();
			members[17].name = "ctx";
			members[17].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[18] = new StructMember();
			members[18].name = "sig_op";
			members[18].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[19] = new StructMember();
			members[19].name = "drc";
			members[19].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[20] = new StructMember();
			members[20].name = "mic";
			members[20].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[21] = new StructMember();
			members[21].name = "dd";
			members[21].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.type();
			members[22] = new StructMember();
			members[22].name = "ec_dsgcon";
			members[22].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[23] = new StructMember();
			members[23].name = "ec_tel";
			members[23].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[24] = new StructMember();
			members[24].name = "dlrd";
			members[24].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.type();
			members[25] = new StructMember();
			members[25].name = "ec_mco";
			members[25].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[26] = new StructMember();
			members[26].name = "ec_oco";
			members[26].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[27] = new StructMember();
			members[27].name = "cdlrd";
			members[27].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.type();
			members[28] = new StructMember();
			members[28].name = "ic";
			members[28].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[29] = new StructMember();
			members[29].name = "pon";
			members[29].type = ORB.init().create_array_tc (
				17, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[30] = new StructMember();
			members[30].name = "ver";
			members[30].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[31] = new StructMember();
			members[31].name = "ckr";
			members[31].type = ORB.init().create_array_tc (
				54, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[32] = new StructMember();
			members[32].name = "refnum";
			members[32].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[33] = new StructMember();
			members[33].name = "ic_dsgcon";
			members[33].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[34] = new StructMember();
			members[34].name = "ic_tel";
			members[34].type = ORB.init().create_array_tc (
				18, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[35] = new StructMember();
			members[35].name = "acccode";
			members[35].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[36] = new StructMember();
			members[36].name = "dsg_addr";
			members[36].type = ORB.init().create_array_tc (
				31, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[37] = new StructMember();
			members[37].name = "nc";
			members[37].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[38] = new StructMember();
			members[38].name = "pri_loc";
			members[38].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[39] = new StructMember();
			members[39].name = "nci1";
			members[39].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[40] = new StructMember();
			members[40].name = "tlvt1";
			members[40].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[41] = new StructMember();
			members[41].name = "tlvr1";
			members[41].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[42] = new StructMember();
			members[42].name = "apot";
			members[42].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[43] = new StructMember();
			members[43].name = "sec_loc";
			members[43].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[44] = new StructMember();
			members[44].name = "nci2";
			members[44].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[45] = new StructMember();
			members[45].name = "tlvt2";
			members[45].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[46] = new StructMember();
			members[46].name = "tlvr2";
			members[46].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[47] = new StructMember();
			members[47].name = "spot";
			members[47].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[48] = new StructMember();
			members[48].name = "other_loc";
			members[48].type = ORB.init().create_array_tc (
				36, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[49] = new StructMember();
			members[49].name = "cfa_a";
			members[49].type = ORB.init().create_array_tc (
				48, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[50] = new StructMember();
			members[50].name = "cfa_z";
			members[50].type = ORB.init().create_array_tc (
				48, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[51] = new StructMember();
			members[51].name = "remarks";
			members[51].type = ORB.init().create_array_tc (
				232, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[52] = new StructMember();
			members[52].name = "msgid";
			members[52].type = ORB.init().create_array_tc (
				81, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "DriOutput", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput value) { 
		o.write_long(value.last);
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.cmd.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[28];
			byte[] _bytes_src = value.mask.getBytes();
			int _j;
			for (_j = 0; _j < 28 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 28);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.x_for.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[48];
			byte[] _bytes_src = value.ckt.getBytes();
			int _j;
			for (_j = 0; _j < 48 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 48);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.loca.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.locz.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.clo.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.act.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[15];
			byte[] _bytes_src = value.stat.getBytes();
			int _j;
			for (_j = 0; _j < 15 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 15);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.segowner.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.slc.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.puls.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.dv.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.cac.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[22];
			byte[] _bytes_src = value.ord.getBytes();
			int _j;
			for (_j = 0; _j < 22 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 22);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.jt_msg.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.ctx.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.sig_op.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.drc.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.mic.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.write (o, value.dd);
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.ec_dsgcon.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ec_tel.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.write (o, value.dlrd);
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ec_mco.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ec_oco.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tHelper.write (o, value.cdlrd);
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.ic.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[17];
			byte[] _bytes_src = value.pon.getBytes();
			int _j;
			for (_j = 0; _j < 17 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 17);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.ver.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[54];
			byte[] _bytes_src = value.ckr.getBytes();
			int _j;
			for (_j = 0; _j < 54 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 54);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.refnum.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.ic_dsgcon.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[18];
			byte[] _bytes_src = value.ic_tel.getBytes();
			int _j;
			for (_j = 0; _j < 18 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 18);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.acccode.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[31];
			byte[] _bytes_src = value.dsg_addr.getBytes();
			int _j;
			for (_j = 0; _j < 31 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 31);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.nc.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.pri_loc.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.nci1.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.tlvt1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.tlvr1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.apot.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.sec_loc.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.nci2.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.tlvt2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.tlvr2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.spot.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[36];
			byte[] _bytes_src = value.other_loc.getBytes();
			int _j;
			for (_j = 0; _j < 36 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 36);
		}
		{
			byte[] _bytes = new byte[48];
			byte[] _bytes_src = value.cfa_a.getBytes();
			int _j;
			for (_j = 0; _j < 48 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 48);
		}
		{
			byte[] _bytes = new byte[48];
			byte[] _bytes_src = value.cfa_z.getBytes();
			int _j;
			for (_j = 0; _j < 48 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 48);
		}
		{
			byte[] _bytes = new byte[232];
			byte[] _bytes_src = value.remarks.getBytes();
			int _j;
			for (_j = 0; _j < 232 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 232);
		}
		{
			byte[] _bytes = new byte[81];
			byte[] _bytes_src = value.msgid.getBytes();
			int _j;
			for (_j = 0; _j < 81 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 81);
		}
	}
}
