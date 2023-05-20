// $Id: ZrgrpOutputHelper.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ZrgrpOutputHelper { 
	private static TypeCode myTc = null;
	private ZrgrpOutputHelper () {
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/ZrgrpOutput:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput();
		value.last = i.read_long ();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cmd = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[19];
			i.read_octet_array (_bytes, 0, 19);
			int _j;
			for (_j = 0; _j < 19; _j++)
				if (_bytes[_j] == 0)
					break;
			value.mask = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.x_for = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.gac = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.end = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ccstrunk = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.last1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.update = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lterm = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.location = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.f = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value.groupid = new String (_bytes, 0, _j);
		}
		{ 
			value.ZrgrpGrp1 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1[8];
			for (int __i0 = 0; __i0 < 8; __i0++) { 
				value.ZrgrpGrp1[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.read (i);
			} 
		}
		{ 
			value.ZrgrpGrp2 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2[4];
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				value.ZrgrpGrp2[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Helper.read (i);
			} 
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
			StructMember members[] = new StructMember[16];
			members[0] = new StructMember();
			members[0].name = "last";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
			members[1] = new StructMember();
			members[1].name = "cmd";
			members[1].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "mask";
			members[2].type = ORB.init().create_array_tc (
				19, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "x_for";
			members[3].type = ORB.init().create_array_tc (
				8, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "gac";
			members[4].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "end";
			members[5].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "ccstrunk";
			members[6].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "last1";
			members[7].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "update";
			members[8].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "lterm";
			members[9].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "location";
			members[10].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "f";
			members[11].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12] = new StructMember();
			members[12].name = "groupid";
			members[12].type = ORB.init().create_array_tc (
				41, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13] = new StructMember();
			members[13].name = "ZrgrpGrp1";
			members[13].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.type();
			members[13].type = ORB.init().create_array_tc (8, members[13].type);
			members[14] = new StructMember();
			members[14].name = "ZrgrpGrp2";
			members[14].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Helper.type();
			members[14].type = ORB.init().create_array_tc (4, members[14].type);
			members[15] = new StructMember();
			members[15].name = "msgid";
			members[15].type = ORB.init().create_array_tc (
				81, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ZrgrpOutput", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput value) { 
		o.write_long(value.last);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.cmd.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[19];
			byte[] _bytes_src = value.mask.getBytes();
			int _j;
			for (_j = 0; _j < 19 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 19);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.x_for.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.gac.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.end.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.ccstrunk.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.last1.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.update.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.lterm.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.location.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.f.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.groupid.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		{ 
			for (int __i0 = 0; __i0 < 8; __i0++) { 
				com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.write (o, value.ZrgrpGrp1[__i0]);
			} 
		}
		{ 
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Helper.write (o, value.ZrgrpGrp2[__i0]);
			} 
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
