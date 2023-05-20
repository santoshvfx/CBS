package com.sbc.gwsvcs.service.toplistener.interfaces;
import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;

public class DtnName_tHelper { 

	private static TypeCode myTc = null;

	public static String read(org.omg.CORBA.portable.InputStream i) {
		String value;
		{
			int __seqLength = i.read_long();
			{
				byte[] _bytes = new byte[__seqLength];
				i.read_octet_array (_bytes, 0, __seqLength);
				int _j;
				for (_j = 0; _j < __seqLength; _j++)
					if (_bytes[_j] == 0)
						break;
				value = new String (_bytes, 0, _j);
			}
		}
		return value;
		}

	public static void write(org.omg.CORBA.portable.OutputStream o, String value) {
		{
			o.write_long (value.length());
			{
				byte[] _bytes = new byte[value.length()];
				byte[] _bytes_src = value.getBytes();
				int _j;
				for (_j = 0; _j < value.length() - 1; _j++)
					_bytes[_j] = _bytes_src[_j];
				_bytes[_j] = 0;
				o.write_octet_array (_bytes, 0, value.length());
			}
		}
		}

	public static void insert (org.omg.CORBA.Any a, String t) { 
		a.insert_string (t); 
	}
	public static String extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return a.extract_string (); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.toplistener.interfaces.DtnName_t:1.0"; }

	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember[] members = new StructMember[1];
			members[0] = new StructMember();
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
			members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.toplistener.interfaces.DtnName_tHelper.id(), "DtnName_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	} 
}
