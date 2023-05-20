// $Id: CircuitIdException.java,v 1.1 2002/09/29 03:12:23 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Thrown on an attempt to construct a CircuitId from an invalid String.
 * Creation date: (5/21/01 2:42:39 PM)
 * @author: Creighton Malet
 */
public class CircuitIdException extends Exception {
/**
 * Class constructor accepting error message.
 * @param s java.lang.String
 */
public CircuitIdException(String s) {
	super(s);
}
}
