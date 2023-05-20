// $Id: CxrsQTIRKSJXInputMsg.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : CxrsQTIRKSJXInputMsg
 * Description: Class used for handling the TIRKSJX CXRS screen request input message.
 * 
 * @author js7440
 */
public class CxrsQTIRKSJXInputMsg implements MMarshalObject 
{ 
	public CxrsQTIRKSJXInput value;

    /**
     * Constructor: CxrsQTIRKSJXInputMsg
     * 
     * @author js7440
     */
	public CxrsQTIRKSJXInputMsg () 
	{
	}
	
	/**
	 * Constructor: CxrsQTIRKSJXInputMsg
	 * 
	 * @param CxrsQTIRKSJXInput initial
	 * @author js7440
	 */
	public CxrsQTIRKSJXInputMsg (CxrsQTIRKSJXInput initial) 
	{ 
		value = initial; 
	}
	
	/**
	 * Method: create
	 * 
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput
	 * @author js7440
	 */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput create () 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInput();
		value.data_center = new String ();
		value.term_A = new String ();
		value.term_Z = new String ();
		value.fac_des = new String ();
		value.fac_typ = new String ();
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
		value = decodeCxrsQInput (ms, tag); 
	}
	
	/**
	 * Method: decodeCxrsQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param String tag
	 * @return CxrsQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public CxrsQTIRKSJXInput decodeCxrsQInput (
		MMarshalStrategy ms,
		String tag)
		throws 
			MMarshalException 
	{ 
		CxrsQTIRKSJXInput value = create();
		ms.startStruct (tag, false);
		value.data_center = ms.decodeOctetString (9, "data_center");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.fac_des = ms.decodeOctetString (6, "fac_des");
		value.fac_typ = ms.decodeOctetString (7, "fac_typ");
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
		encodeCxrsQInput (ms, value, tag); 
	}
	
	/**
	 * Method: encodeCxrsQInput
	 * 
	 * @param MMarshalStrategy ms
	 * @param CxrsQTIRKSJXInput value
	 * @param String tag
	 * @throws MMarshalException
	 * @author js7440
	 */
	static public void encodeCxrsQInput (
		MMarshalStrategy ms, 
		CxrsQTIRKSJXInput value,
		String tag) 
		throws 
			MMarshalException 
	{ 
		ms.startStruct (tag, true);
		ms.encode (value.data_center, 9, "data_center");
		ms.encode (value.term_A, 12, "term_A");
		ms.encode (value.term_Z, 12, "term_Z");
		ms.encode (value.fac_des, 6, "fac_des");
		ms.encode (value.fac_typ, 7, "fac_typ");
		ms.endStruct (tag, true); 
	}	
	
	/**
	 * Method: fromOctet
	 * 
	 * @param byte [] val
	 * @return CxrsQTIRKSJXInput
	 * @throws MMarshalException
	 * @author js7440
	 */
	public static CxrsQTIRKSJXInput fromOctet (
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
				return decodeCxrsQInput (ms, "CxrsQTIRKSJXInput"); 
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
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQTIRKSJXInputHelper.type(); 
	}
	
	/**
	 * Method: toOctet
	 * 
	 * @param val
	 * @return
	 * @throws MMarshalException
	 */
	public static byte [] toOctet (
		CxrsQTIRKSJXInput val) 
		throws 
			MMarshalException 
	{ 
		try 
		{
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCxrsQInput (ms, val, "CxrsQTIRKSJXInput");
			MBuffer buf = ms.getBuffer();
		
			return buf.get (buf.getWritePosition()); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
