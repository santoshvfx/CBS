// $Id: PointInterfaceIdException.java,v 1.1 2002/09/29 03:12:39 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Thrown on an attempt to construct a CableId from an invalid String.
 * Creation date: (5/21/01 2:42:39 PM)
 * @author: Mark Liljequist
 */
public class PointInterfaceIdException extends Exception {
/**
 * Class constructor accepting error message.
 * @param s java.lang.String
 */
public PointInterfaceIdException(String s) {
	super(s);
}
}
