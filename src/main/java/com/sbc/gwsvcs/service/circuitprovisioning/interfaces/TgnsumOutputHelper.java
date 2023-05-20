// $Id: TgnsumOutputHelper.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TgnsumOutputHelper { 
	private static TypeCode myTc = null;
	private TgnsumOutputHelper () {
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/TgnsumOutput:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput();
		value.last = i.read_long ();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cmd = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[17];
			i.read_octet_array (_bytes, 0, 17);
			int _j;
			for (_j = 0; _j < 17; _j++)
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
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.location1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.fmt = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.display = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.starttgn = new String (_bytes, 0, _j);
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
			value.TgnsumGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.TgnsumGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefHelper.read (i);
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
			StructMember members[] = new StructMember[11];
			members[0] = new StructMember();
			members[0].name = "last";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
			members[1] = new StructMember();
			members[1].name = "cmd";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "mask";
			members[2].type = ORB.init().create_array_tc (
				17, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "x_for";
			members[3].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "location1";
			members[4].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "fmt";
			members[5].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "display";
			members[6].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "starttgn";
			members[7].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "lterm";
			members[8].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "TgnsumGrp";
			members[9].type = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefHelper.type();
			members[9].type = ORB.init().create_array_tc (18, members[9].type);
			members[10] = new StructMember();
			members[10].name = "msgid";
			members[10].type = ORB.init().create_array_tc (
				81, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "TgnsumOutput", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput value) { 
		o.write_long(value.last);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.cmd.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[17];
			byte[] _bytes_src = value.mask.getBytes();
			int _j;
			for (_j = 0; _j < 17 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 17);
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
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.location1.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.fmt.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.display.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.starttgn.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
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
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefHelper.write (o, value.TgnsumGrp[__i0]);
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
