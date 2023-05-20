// $Id: CblsQTIRKSJXInputMsg.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : CblsQTIRKSJXInputMsg
 * Description: Class used for handling the TIRKSJX CBLS screen request input message. 
 *  
 * @author js7440
 */
public class CblsQTIRKSJXInputMsg 
	implements MMarshalObject 
{ 
	public CblsQTIRKSJXInput value;

    /**
     * Constructor: CblsQTIRKSJXInputMsg
     * 
     * @author js7440
     */
	public CblsQTIRKSJXInputMsg () 
	{
	}
	
    /**
     * Constructor: CblsQTIRKSJXInputMsg
     * 
     * @param CblsQTIRKSJXInput initial
     * @author js7440
     */
	public CblsQTIRKSJXInputMsg (CblsQTIRKSJXInput initial) 
	{ 
		value = initial; 
	}
	
    /**
     * Method: create
     * 
     * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput
     * @author js7440
     */
	public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput create () 
	{ 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInput();
		value.data_center = new String ();
		value.term_A = new String ();
		value.term_Z = new String ();
		value.cable = new String ();
		value.from_unit = new String ();
		value.last_unit = new String ();
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
		value = decodeCblsQInput (ms, tag); 
	}
	
    /**
     * Method: decode
     * 
     * @param MMarshalStrategy 	ms
     * @param String 			tag
     * @return CblsQTIRKSJXInput
     * @throws MMarshalException
     * @author js7440
     */
	static public CblsQTIRKSJXInput decodeCblsQInput (
		MMarshalStrategy ms, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		CblsQTIRKSJXInput value = create();
		ms.startStruct (tag, false);
		value.data_center = ms.decodeOctetString (9, "data_center");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.cable = ms.decodeOctetString (11, "cable");
		value.from_unit = ms.decodeOctetString (6, "from_unit");
		value.last_unit = ms.decodeOctetString (6, "last_unit");
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
		encodeCblsQInput (ms, value, tag); 
	}
	
    /**
     * Method: encodeCblsQInput
     * 
     * @param MMarshalStrategy 	ms
     * @param CblsQTIRKSJXInput value
     * @param String 			tag
     * @throws MMarshalException
     * @author js7440
     */
	static public void encodeCblsQInput (
		MMarshalStrategy ms, 
		CblsQTIRKSJXInput value, 
		String tag) 
		throws 
			MMarshalException 
	{ 
		ms.startStruct (tag, true);
		ms.encode (value.data_center, 9, "data_center");
		ms.encode (value.term_A, 12, "term_A");
		ms.encode (value.term_Z, 12, "term_Z");
		ms.encode (value.cable, 11, "cable");
		ms.encode (value.from_unit, 6, "from_unit");
		ms.encode (value.last_unit, 6, "last_unit");
		ms.endStruct (tag, true); 
	}
	
    /**
     * Method: fromOctet
     * 
     * @param byte [] val
     * @return CblsQTIRKSJXInput
     * @throws MMarshalException
     * @author js7440
     */
	public static CblsQTIRKSJXInput fromOctet (
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
			return decodeCblsQInput (ms, "CblsQTIRKSJXInput"); 
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
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQTIRKSJXInputHelper.type(); 
	}
	public static byte [] toOctet (CblsQTIRKSJXInput val) throws MMarshalException 
	{ 
		try 
		{
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCblsQInput (ms, val, "CblsQTIRKSJXInput");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} 
		catch (MBufferException e) 
		{ 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
