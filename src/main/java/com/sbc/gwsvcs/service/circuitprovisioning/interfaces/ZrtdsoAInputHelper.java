// $Id: ZrtdsoAInputHelper.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ZrtdsoAInputHelper { 
	private static TypeCode myTc = null;
	private ZrtdsoAInputHelper () {
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/ZrtdsoAInput:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ims_region = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[17];
			i.read_octet_array (_bytes, 0, 17);
			int _j;
			for (_j = 0; _j < 17; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tablename = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[61];
			i.read_octet_array (_bytes, 0, 61);
			int _j;
			for (_j = 0; _j < 61; _j++)
				if (_bytes[_j] == 0)
					break;
			value.fieldvalue = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "ims_region";
			members[0].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1] = new StructMember();
			members[1].name = "tablename";
			members[1].type = ORB.init().create_array_tc (
				17, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "fieldvalue";
			members[2].type = ORB.init().create_array_tc (
				61, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ZrtdsoAInput", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput value) { 
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.ims_region.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[17];
			byte[] _bytes_src = value.tablename.getBytes();
			int _j;
			for (_j = 0; _j < 17 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 17);
		}
		{
			byte[] _bytes = new byte[61];
			byte[] _bytes_src = value.fieldvalue.getBytes();
			int _j;
			for (_j = 0; _j < 61 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 61);
		}
	}
}
