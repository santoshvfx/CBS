package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class MobileCTNLookup_RHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private MobileCTNLookup_RHelper () {
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R();
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tn = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.property = new String (_bytes, 0, _j);
		}
		value.OrigEvent = i.read_long ();
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R value) { 
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.tn.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.property.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		o.write_long(value.OrigEvent);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[3];
				members[0] = new StructMember();
				members[0].name = "tn";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (11, members[0].type);
				members[1] = new StructMember();
				members[1].name = "property";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (6, members[1].type);
				members[2] = new StructMember();
				members[2].name = "OrigEvent";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
				myTc = ORB.init().create_struct_tc (id(), "MobileCTNLookup_R", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/hostlookup/interfaces/MobileCTNLookup_R:1.0"; } 
}
