// $Id: WaQTIRKSJXInputMsg.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
//#      � 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
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
 * Class      : WaQTIRKSJXInputMsg
 * Description: Class used for handling the TIRKSJX WA screen request input message.
 * 
 * @author js7440
 */
public class WaQTIRKSJXInputMsg implements MMarshalObject 
{ 
	public WaQTIRKSJXInput value;

    /**
     * Constructor: WaQTIRKSJXInputMsg
     * 
     * @author js7440
     */
	public WaQTIRKSJXInputMsg () 
	{
	}
	
	/**
	 * Constructor: WaQTIRKSJXInputMsg
	 * 
	 * @param WaQTIRKSJXInput initial
	 * @author js7440
	 */
	public WaQTIRKSJXInputMsg (WaQTIRKSJXInput initial) 
	{ 
		value = initial; 
	}
	
	/**
	 * Method: create
	 * 
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInput
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInput create () 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInput();
		value.data_center = new String ();
		value.clo = new String ();
		value.cac = new String ();
		value.ckt = new String ();
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
		value = decodeWaQInput (ms, tag); 
	}
	
	/**
	 * Method: decodeWaQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param String tag
	 * @return DriQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public WaQTIRKSJXInput decodeWaQInput (
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		WaQTIRKSJXInput value = create();
		ms.startStruct (tag, false);
		value.data_center = ms.decodeOctetString (9, "data_center");
		value.clo = ms.decodeOctetString (14, "clo");
		value.cac = ms.decodeOctetString (9, "cac");
		value.ckt = ms.decodeOctetString (48, "ckt");
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
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		encodeWaQInput (ms, value, tag); 
	}
	
	/**
	 * Method: encodeWaQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param CxrsQTIRKSJXInput value
	 * @param String tag
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public void encodeWaQInput (
		MMarshalStrategy ms, 
		WaQTIRKSJXInput value, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		ms.startStruct (tag, true);
		ms.encode (value.data_center, 9, "data_center");
		ms.encode (value.clo, 14, "clo");
		ms.encode (value.cac, 9, "cac");
		ms.encode (value.ckt, 48, "ckt");
		ms.endStruct (tag, true); 
	}
	
	/**
	 * Method: fromOctet
	 * 
	 * @param byte [] val
	 * @return WaQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	public static WaQTIRKSJXInput fromOctet (
		byte [] val) 
		throws 
			MMarshalException 
	{ 
		try 
		{ 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeWaQInput (ms, "WaQTIRKSJXInput"); 
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
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQTIRKSJXInputHelper.type(); 
	}
	
	/**
	 * Method: toOctet
	 * 
	 * @param val
	 * @return WaQTIRKSJXInput
	 * @throws MMarshalException
	 */
	public static byte [] toOctet (
		WaQTIRKSJXInput val) 
		throws 
			MMarshalException 
	{ 
		try 
		{
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeWaQInput (ms, val, "WaQTIRKSJXInput");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
