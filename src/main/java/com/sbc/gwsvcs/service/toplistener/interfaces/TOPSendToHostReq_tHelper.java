package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TOPSendToHostReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private TOPSendToHostReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t value = new com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t();
		value.Header = com.sbc.gwsvcs.service.toplistener.interfaces.Header_tHelper.read (i);
		{
			int __seqLength = i.read_long();
			{
				byte[] _bytes = new byte[__seqLength];
				i.read_octet_array (_bytes, 0, __seqLength);
				int _j;
				for (_j = 0; _j < __seqLength; _j++)
					if (_bytes[_j] == 0)
						break;
				value.FCIFData = new String (_bytes, 0, _j);
			}
		}
		{
			int __seqLength = i.read_long();
			{
				byte[] _bytes = new byte[__seqLength];
				i.read_octet_array (_bytes, 0, __seqLength);
				int _j;
				for (_j = 0; _j < __seqLength; _j++)
					if (_bytes[_j] == 0)
						break;
				value.DtnName = new String (_bytes, 0, _j);
			}
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t value) { 
		com.sbc.gwsvcs.service.toplistener.interfaces.Header_tHelper.write (o, value.Header);
		{
			o.write_long (value.FCIFData.length());
			{
				byte[] _bytes = new byte[value.FCIFData.length()];
				byte[] _bytes_src = value.FCIFData.getBytes();
				int _j;
				for (_j = 0; _j < value.FCIFData.length() - 1; _j++)
					_bytes[_j] = _bytes_src[_j];
				_bytes[_j] = 0;
				o.write_octet_array (_bytes, 0, value.FCIFData.length());
			}
		}
		{
			o.write_long (value.DtnName.length());
			{
				byte[] _bytes = new byte[value.DtnName.length()];
				byte[] _bytes_src = value.DtnName.getBytes();
				int _j;
				for (_j = 0; _j < value.DtnName.length() - 1; _j++)
					_bytes[_j] = _bytes_src[_j];
				_bytes[_j] = 0;
				o.write_octet_array (_bytes, 0, value.DtnName.length());
			}
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.toplistener.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "FCIFData";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.toplistener.interfaces.FCIFData_tHelper.id(), "FCIFData_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "DtnName";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.toplistener.interfaces.DtnName_tHelper.id(), "DtnName_t", members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "TOPSendToHostReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/toplistener/interfaces/TOPSendToHostReq_t:1.0"; } 
}
