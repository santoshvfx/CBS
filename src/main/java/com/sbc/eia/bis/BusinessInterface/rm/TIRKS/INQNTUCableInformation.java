package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableInformation;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.rm_types.*;

/**
 * Insert the type's description here.
 * Creation date: (7/5/02 11:28:57 AM)
 * @author: Mark Liljequist

#   History :
#   Date      	| Author        | Version	| Notes
#   ----------------------------------------------------------------------------
#
#   09/16/02    Mark Liljequist	6.0.0		RM64179
#											Changes for ACNA in parse ACNA.
*/

 
public class INQNTUCableInformation extends CableInformation{


/**
 * SWITCHCableInformation constructor comment.
 */
public INQNTUCableInformation() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public boolean isclo() {

		return false;

}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public boolean isWATransaction() {

	if (getADSR().equalsIgnoreCase("Y"))
		return true;
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseACNA(String input) {

	int s;

	if ((s = input.indexOf("OWNERSHIP=")) > -1) {
		String t = input.substring(s + 10, s + 13);
		setCableACNA(t.trim());
		return t.trim();
	}

	setCableACNA("");
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseADSR(String input) {

	int s;

	if ((s = input.indexOf("ADSR:")) > -1) {
		String t = input.substring(s + 6, s + 7);
		setADSR(t.trim());
		return t.trim();
	}

	setADSR("NF");
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */

public String parseASM(String input) {

	int s;

	if ((s = input.indexOf("ASM:")) > -1) {
		String t = input.substring(s + 5, s + 8);
		setASM(t.trim());
		return t.trim();
	}

	setASM("NF");
	return null;

}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseCKID(String input) {

	int s;

	if ((s = input.indexOf("CKID:")) > -1) {
		String t = input.substring(s + 6, s + 50);
		t = t.trim();
		if (t.indexOf(".") > -1) {
			String v = t.replace('.', '/');
			setCkitIdClo(v.trim());
			return v.trim();
		}
	}

	setCkitIdClo("");
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseDate(String input) {

	int s;

	if ((s = input.indexOf("PDD:")) > -1) {
		String t = input.substring(s + 5, s + 13);
		setDate(t.trim());
		return t.trim();
	}

	setDate("NF");
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseOA(String input) {

	int s;

	if ((s = input.indexOf("OA:")) > -1) {
		String t = input.substring(s + 4, s + 6);
		setOA(t.trim());
		return t.trim();
	}

	setOA("NF");
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public ChannelStatus translateChannelStatus() {


	String asm = getASM();
	String oa = getOA();

	if (asm.equalsIgnoreCase("CKT")
		&& (oa.equalsIgnoreCase("NF") || oa.equalsIgnoreCase("NA")))
		return ChannelStatus.ASSIGNED;

	if (asm.equalsIgnoreCase("NO") && oa.equalsIgnoreCase("NF"))
		return ChannelStatus.SPARE;

	if (asm.equalsIgnoreCase("NO") && oa.equalsIgnoreCase("PI"))
		return ChannelStatus.PENDING;

	if (asm.equalsIgnoreCase("CKT") && oa.equalsIgnoreCase("PO"))
		return ChannelStatus.PENDING;


	return ChannelStatus.INVALID; 
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public EiaDate translateDueDate() {

	String s = getDate();

	String mo = s.substring(0, 2);
	String dy = s.substring(3, 5);
	String yr = s.substring(6);

	int year;
	EiaDate aDueDate;
	
	try {

		year = Integer.parseInt(yr);

		int cc;
		if (year < 60)
			cc = year + 2000;
		else
			cc = year + 1900;

		aDueDate = new EiaDate((short) cc, Short.parseShort(mo), Short.parseShort(dy));

	} catch (NumberFormatException e) {
		aDueDate = new EiaDate((short) 0, (short) 0, (short) 0);
	}

	return aDueDate;

}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public ServiceOrderType translateOrderType() {

	String oa = getOA();
	String date = getDate();

	if (oa.equalsIgnoreCase("PI"))
		return ServiceOrderType.CONNECT;

	if (oa.equalsIgnoreCase("PO"))
		return ServiceOrderType.DISCONNECT;

	if (date.length() > 0)
		return ServiceOrderType.CHANGE;

	return null;

}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:21:04 PM)
 * @return java.lang.String
 */
public String parseCLLI(String[] input, String cableId) {

	// Go through array looking for CLLI.
	boolean lookForClli = false;
	for (int i = 0; i < input.length; i++) {
		int count;
		if ((input[i].indexOf(cableId)) == 0)
			lookForClli = true;
		if ((count = input[i].indexOf("CLLI:")) > -1 && lookForClli) {
			String c = input[i].substring(count + 6, count + 14);
			return c.trim();
		}

	}

	return null;
}
}
