// $Id: EqpscQTIRKSJXInputMsg.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : EqpscQTIRKSJXInputMsg
 * Description: Class used for handling the TIRKSJX DRI screen request input message.
 * 
 * @author js7440
 */
public class EqpscQTIRKSJXInputMsg implements MMarshalObject 
{ 
	public EqpscQTIRKSJXInput value;

    /**
     * Constructor: EqpscQTIRKSJXInputMsg
     * 
     * @author js7440
     */
	public EqpscQTIRKSJXInputMsg () 
	{
	}
	
	/**
	 * Constructor: EqpscQTIRKSJXInputMsg
	 * 
	 * @param EqpscQTIRKSJXInput initial
	 * @author js7440
	 */
	public EqpscQTIRKSJXInputMsg (EqpscQTIRKSJXInput initial) 
	{ 
		value = initial; 
	}
	
	/**
	 * Method: create
	 * 
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput create () 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInput();
		value.data_center = new String ();
		value.location = new String ();
		value.equip_code = new String ();
		value.level = new String ();
		value.relayrack = new String ();
		value.unit = new String ();
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
		value = decodeEqpscQInput (ms, tag); 
	}
	
	/**
	 * Method: decodeEqpscQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param String tag
	 * @return DriQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public EqpscQTIRKSJXInput decodeEqpscQInput (
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		EqpscQTIRKSJXInput value = create();
		ms.startStruct (tag, false);
		value.data_center = ms.decodeOctetString (9, "data_center");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		value.relayrack = ms.decodeOctetString (11, "relayrack");
		value.unit = ms.decodeOctetString (7, "unit");
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
		encodeEqpscQInput (ms, value, tag); 
	}
	
	/**
	 * Method: encodeEqpscQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param CxrsQTIRKSJXInput value
	 * @param String tag
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public void encodeEqpscQInput (
		MMarshalStrategy ms, 
		EqpscQTIRKSJXInput value, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		ms.startStruct (tag, true);
		ms.encode (value.data_center, 9, "data_center");
		ms.encode (value.location, 12, "location");
		ms.encode (value.equip_code, 15, "equip_code");
		ms.encode (value.level, 2, "level");
		ms.encode (value.relayrack, 11, "relayrack");
		ms.encode (value.unit, 7, "unit");
		ms.endStruct (tag, true); 
	}
	
	/**
	 * Method: fromOctet
	 * 
	 * @param byte [] val
	 * @return EqpscQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	public static EqpscQTIRKSJXInput fromOctet (
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
			return decodeEqpscQInput (ms, "EqpscQTIRKSJXInput"); 
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
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQTIRKSJXInputHelper.type(); 
	}
	
	/**
	 * Method: toOctet
	 * 
	 * @param val
	 * @return
	 * @throws MMarshalException
	 */
	public static byte [] toOctet (
		EqpscQTIRKSJXInput val) 
		throws 
			MMarshalException 
	{ 
		try 
		{
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEqpscQInput (ms, val, "EqpscQTIRKSJXInput");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
