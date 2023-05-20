// $Id: RdlocQTIRKSJXInputMsg.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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

import org.omg.CORBA.TypeCode;

import com.sbc.vicunalite.api.MBuffer;
import com.sbc.vicunalite.api.MBufferException;
import com.sbc.vicunalite.api.MMarshalException;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.api.MMarshalStrategy;

/**
 * Class      : RdlocQTIRKSJXInputMsg
 * Description: Class used for handling the TIRKSJX RDLOC screen request input message.
 * 
 * @author js7440
 */
public class RdlocQTIRKSJXInputMsg implements MMarshalObject 
{ 
	public RdlocQTIRKSJXInput value;

    /**
     * Constructor: RdlocQTIRKSJXInputMsg
     * 
     * @author js7440
     */
	public RdlocQTIRKSJXInputMsg () 
	{
	}
	
	/**
	 * Constructor: RdlocQTIRKSJXInputMsg
	 * 
	 * @param RdlocQTIRKSJXInput initial
	 * @author js7440
	 */
	public RdlocQTIRKSJXInputMsg (
		RdlocQTIRKSJXInput initial) 
	{ 
		value = initial; 
	}
	
	/**
	 * Method: create
	 * 
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInput
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInput create () 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInput();
		value.data_center = new String ();
		value.location = new String ();
		return value; 
	}
	
    /**
     * Method: decode
     * 
     * @param MMarshalStrategy 	ms
     * @param String 			tag
     * @throws MMarshalException
     * @author js7440
     */
	public void decode (
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		value = decodeRdlocQInput (ms, tag); 
	}
	
	/**
	 * Method: decodeRdlocQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param String tag
	 * @return DriQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public RdlocQTIRKSJXInput decodeRdlocQInput (
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		RdlocQTIRKSJXInput value = create();
		ms.startStruct (tag, false);
		value.data_center = ms.decodeOctetString (9, "data_center");
		value.location = ms.decodeOctetString (12, "location");
		ms.endStruct (tag, false);
		return value; 
	}
	
    /**
     * Method: encode
     * 
     * @param MMarshalStrategy 	ms
     * @param String 			tag
     * @throws MMarshalException
     * @author js7440
     */
	public void encode (
		MMarshalStrategy ms, String tag) 
		throws 
			MMarshalException 
	{ 
		encodeRdlocQInput (ms, value, tag); 
	}
	
	/**
	 * Method: encodeRdlocQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param CxrsQTIRKSJXInput value
	 * @param String tag
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public void encodeRdlocQInput (
		MMarshalStrategy ms, 
		RdlocQTIRKSJXInput value, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		ms.startStruct (tag, true);
		ms.encode (value.data_center, 9, "data_center");
		ms.encode (value.location, 12, "location");
		ms.endStruct (tag, true); 
	}
	
	/**
	 * Method: fromOctet
	 * 
	 * @param byte [] val
	 * @return RdlocQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	public static RdlocQTIRKSJXInput fromOctet (
		byte [] val) 
		throws 
			MMarshalException 
	{ 
		try 
		{ 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRdlocQInput (ms, "RdlocQTIRKSJXInput"); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	
    /**
     * Method: getType
     * 
     * @return TypeCode
     * @author js7440
     */
	public TypeCode getType () 
	{ 
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQTIRKSJXInputHelper.type(); 
	}
	
	/**
	 * Method: toOctet
	 * 
	 * @param val
	 * @return RdlocQTIRKSJXInput
	 * @throws MMarshalException
	 */
	public static byte [] toOctet (
		RdlocQTIRKSJXInput val) 
		throws 
			MMarshalException 
	{ 
		try 
		{
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRdlocQInput (ms, val, "RdlocQTIRKSJXInput");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
