// $Id: CblsQTIRKSJXInputHelper.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : CblsQTIRKSJXInputHelper
 * Description: Helper used for handling the input of the TIRKSJX CBLS screen.  
 * 
 * @author js7440
 */
public class CblsQTIRKSJXInputHelper 
{ 
	private static TypeCode myTc = null;
	
    /**
     * Constructor: CblsQTIRKSJXInputHelper
     * 
     * @author js7440
     */
	private CblsQTIRKSJXInputHelper () 
	{
	}
	
    /**
     * Method: extract
     * 
     * @param org.omg.CORBA.Any       a
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput
     * @exception	BAD_OPERATION
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput extract (
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
		return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/CblsQTIRKSJXInput:1.0"; 
	}
	
    /**
     * Method: insert
     * 
     * @param org.omg.CORBA.Any a
     * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput t
     * @author js7440
     */
	public static void insert (
		org.omg.CORBA.Any a, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput t) 
	{ 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	
    /**
     * Method: read
     * 
     * @param org.omg.CORBA.portable.InputStream i
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput read (
		org.omg.CORBA.portable.InputStream i) 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput();
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
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.term_A = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.term_Z = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cable = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.from_unit = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.last_unit = new String (_bytes, 0, _j);
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
			StructMember members[] = new StructMember[6];
			members[0] = new StructMember();
			members[0].name = "data_center";
			members[0].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1] = new StructMember();
			members[1].name = "term_A";
			members[1].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "term_Z";
			members[2].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "cable";
			members[3].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "from_unit";
			members[4].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "last_unit";
			members[5].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "CblsQTIRKSJXInput", members); 
		}
		return myTc; 
	}
	
    /**
     * Method: write
     * 
     * @param org.omg.CORBA.portable.OutputStream o
     * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput value
     * @author js7440
     */
	public static void write (
		org.omg.CORBA.portable.OutputStream o, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput value) 
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
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.term_A.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.term_Z.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.cable.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.from_unit.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.last_unit.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
	}
}
