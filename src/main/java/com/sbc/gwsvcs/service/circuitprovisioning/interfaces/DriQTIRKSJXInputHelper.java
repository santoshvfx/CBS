// $Id: DriQTIRKSJXInputHelper.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.TypeCode;

/**
 * Class      : DriQTIRKSJXInputHelper
 * Description: Helper used for handling the input of the TIRKSJX DRI screen.
 * 
 * @author js7440
 */
public class DriQTIRKSJXInputHelper 
{ 
	private static TypeCode myTc = null;
	
	
    /**
     * Constructor: DriQTIRKSJXInputHelper
     * 
     * @author js7440
     */
	private DriQTIRKSJXInputHelper () 
	{
	}
	
	/**
	 * Method: extract
	 * 
	 * @param org.omg.CORBA.Any a
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput
	 * @throws BAD_OPERATION
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput extract (
		org.omg.CORBA.Any a) 
		throws 
			BAD_OPERATION 
	{ 
		return read(a.create_input_stream()); 
	}
	
    /**
     * Method: id
     * 
     * @return String
     * @author js7440
     */
	public static String id() 
	{ 
		return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/DriQTIRKSJXInput:1.0"; 
	}
	
    /**
     * Method: insert
     * 
     * @param org.omg.CORBA.Any a
     * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput t
     * @author js7440
     */
	public static void insert (
		org.omg.CORBA.Any a, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput t) 
	{ 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	
    /**
     * Method: read
     * 
     * @param org.omg.CORBA.portable.InputStream i
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput read (
			org.omg.CORBA.portable.InputStream i) 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.data_center = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[48];
			i.read_octet_array (_bytes, 0, 48);
			int _j;
			for (_j = 0; _j < 48; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ckt = new String (_bytes, 0, _j);
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
		return value; 
	}
	
	/**
	 * Method: type
	 * 
	 * @return TypeCode
	 * @author js7440
	 */
	synchronized public static TypeCode type() 
	{ 
		if (myTc == null) 
		{ 
			StructMember members[] = new StructMember[5];
			members[0] = new StructMember();
			members[0].name = "data_center";
			members[0].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1] = new StructMember();
			members[1].name = "clo";
			members[1].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "ckt";
			members[2].type = ORB.init().create_array_tc (
				48, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "cac";
			members[3].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "ord";
			members[4].type = ORB.init().create_array_tc (
				22, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "DriQInput", members); 
		}
		return myTc; 
	}
	
	/**
	 * Method: write
	 * 
	 * @param org.omg.CORBA.portable.OutputStream o
	 * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput value
	 * @author js7440
	 */
	public static void write (
			org.omg.CORBA.portable.OutputStream o, 
			com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQTIRKSJXInput value) 
	{ 
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.data_center.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
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
			byte[] _bytes = new byte[48];
			byte[] _bytes_src = value.ckt.getBytes();
			int _j;
			for (_j = 0; _j < 48 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 48);
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
	}
}
