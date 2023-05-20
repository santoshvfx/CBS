// $Id: CxrsQTIRKSJXInputHelper.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Description: Helper used for handling the input of the TIRKSJX CXRS screen.
 * 
 * @author js7440
 */
public class CxrsQTIRKSJXInputHelper 
{ 
	private static TypeCode myTc = null;
	
    /**
     * Constructor: CxrsQTIRKSJXInputHelper
     * 
     * @author js7440
     */
	private CxrsQTIRKSJXInputHelper () 
	{
	}
	
    /**
     * Method: extract
     * 
     * @param org.omg.CORBA.Any       a
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput
     * @exception	BAD_OPERATION
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput extract (org.omg.CORBA.Any a) 
		throws BAD_OPERATION 
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
		return "IDL:com/sbc/gwsvcs/service/circuitprovisioning/interfaces/CxrsQTIRKSJXInput:1.0"; 
	}
	
    /**
     * Method: insert
     * 
     * @param org.omg.CORBA.Any a
     * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput t
     * @author js7440
     */
	public static void insert (
		org.omg.CORBA.Any a, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput t) 
	{ 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	
    /**
     * Method: read
     * 
     * @param org.omg.CORBA.portable.InputStream i
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput read (
			org.omg.CORBA.portable.InputStream i) 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput();
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
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.fac_des = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.fac_typ = new String (_bytes, 0, _j);
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
			members[1].name = "term_A";
			members[1].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "term_Z";
			members[2].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "fac_des";
			members[3].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "fac_typ";
			members[4].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "CxrsQInput", members); 
		}
		return myTc; 
	}
	
	/**
	 * Method: write
	 * 
	 * @param org.omg.CORBA.portable.OutputStream o
	 * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput value
	 * @author js7440
	 */
	public static void write (
		org.omg.CORBA.portable.OutputStream o,
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput value) 
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
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.fac_des.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.fac_typ.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
	}
}
