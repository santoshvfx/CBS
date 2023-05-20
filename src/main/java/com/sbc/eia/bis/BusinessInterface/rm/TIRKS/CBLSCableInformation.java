// $Id: CBLSCableInformation.java,v 1.2 2003/02/12 03:09:20 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableInformation;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.rm_types.*;

/**
 * Insert the type's description here.
 * Creation date: (7/5/02 11:29:24 AM)
 * @author: Mark Liljequist

#   History :
#   Date      	| Author        | Version	|Notes
#   ----------------------------------------------------------------------------
#
#   09/30/2002	Mark Liljequist 5.0.2		Change for channel status.
#
*/

 
public class CBLSCableInformation extends CableInformation {
/**
 * CBLSCableInformation constructor comment.
 */
public CBLSCableInformation() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:13:21 PM)
 * @return java.lang.String
 */
public boolean isclo() {

	// Check if WA transaction.

	if ((getFormat().trim().length() == 0)
		&& (!getPendAct().equals("$") && !getPendAct().equals("J")))
		return true;
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:13:21 PM)
 * @return java.lang.String
 */
public boolean isWATransaction() {

	// Check if WA transaction.

	if (!getPendAct().equals("$") && !getPendAct().equals("J"))
		return true;

	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:13:21 PM)
 * @return java.lang.String
 */
public ChannelStatus translateChannelStatus() {

	//***************************************************************************

	// Translate the status from CBLS. 

	if (getPendAct().equals("A")
		|| getPendAct().equals("D")
		|| getPendAct().equals("R")
		|| getPendAct().equals("H"))
		return ChannelStatus.PENDING;
	else
		if (getCurAct().equals("W"))
			return ChannelStatus.ASSIGNED;
		else
			if (getCurAct().equals("J"))
				return ChannelStatus.INVALID;
			else
				return ChannelStatus.SPARE;


}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:13:21 PM)
 * @return java.lang.String
 */
public EiaDate translateDueDate() {
	
	EiaDate aDueDate;
	
	try {

		aDueDate =
			new EiaDate(
				Short.parseShort(getDueDate().YR),
				Short.parseShort(getDueDate().MO),
				Short.parseShort(getDueDate().DY));

	} catch (NumberFormatException e) {
		aDueDate = new EiaDate((short) 0, (short) 0, (short) 0);
	}

	return aDueDate;

}
/**
 * Insert the method's description here.
 * Creation date: (7/5/02 12:13:21 PM)
 * @return java.lang.String
 */
public ServiceOrderType translateOrderType() {

	// Translate service order type.

	if (getPendAct().equals("A"))
		return ServiceOrderType.CONNECT;
	else
		if (getPendAct().equals("D"))
			return ServiceOrderType.DISCONNECT;
		else
			if (getPendAct().equals("R")
				|| getPendAct().equals("H"))
				return ServiceOrderType.CHANGE;

	return null;
}
}
