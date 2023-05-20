// $Id: EqpscQTIRKSJXInputHelper.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : EqpscQTIRKSJXInputHelper
 * Description: Helper used for handling the input of the TIRKSJX EQPSC screen.
 * 
 * @author js7440
 */
public class EqpscQTIRKSJXInputHelper 
{ 
	private static TypeCode myTc = null;
	
    /**
     * Constructor: EqpscQTIRKSJXInputHelper
     * 
     * @author js7440
     */
	private EqpscQTIRKSJXInputHelper () 
	{
	}
	
	/**
	 * Method: extract
	 * 
	 * @param org.omg.CORBA.Any a
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput
	 * @throws BAD_OPERATION
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput extract (
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
		return "IDL:com.sbc.gwsvcs.service.circuitprovisioning.interfaces/EqpscQTIRKSJXInput:1.0"; 
	}
	
    /**
     * Method: insert
     * 
     * @param org.omg.CORBA.Any a
     * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput t
     * @author js7440
     */
	public static void insert (
		org.omg.CORBA.Any a, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput t) 
	{ 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	
    /**
     * Method: read
     * 
     * @param org.omg.CORBA.portable.InputStream i
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput read (
		org.omg.CORBA.portable.InputStream i) 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput();
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
			value.location = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[15];
			i.read_octet_array (_bytes, 0, 15);
			int _j;
			for (_j = 0; _j < 15; _j++)
				if (_bytes[_j] == 0)
					break;
			value.equip_code = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.level = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.relayrack = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.unit = new String (_bytes, 0, _j);
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
			members[1].name = "location";
			members[1].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "equip_code";
			members[2].type = ORB.init().create_array_tc (
				15, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "level";
			members[3].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "relayrack";
			members[4].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "unit";
			members[5].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "EqpscQTIRKSJXInput", members); 
		}
		return myTc; 
	}
	
	/**
	 * Method: write
	 * 
	 * @param org.omg.CORBA.portable.OutputStream o
	 * @param com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput value
	 * @author js7440
	 */
	public static void write (
		org.omg.CORBA.portable.OutputStream o, 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput value) 
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
			byte[] _bytes_src = value.location.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[15];
			byte[] _bytes_src = value.equip_code.getBytes();
			int _j;
			for (_j = 0; _j < 15 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 15);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.level.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.relayrack.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.unit.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
	}
}
