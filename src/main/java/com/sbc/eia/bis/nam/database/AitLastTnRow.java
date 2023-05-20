// $Id: AitLastTnRow.java,v 1.1 2002/09/29 03:20:41 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

/**
 * Insert the type's description here.
 * Creation date: (4/9/01 2:25:56 PM)
 * @author: Hongmei Parkin
 */
public class AitLastTnRow {
	private String exchange = "";
	private String sagco = "";
	private String primaryNpa = "";
	private String telNbr = "";
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @return java.lang.String
 */
public String getExchange() {
	return exchange;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @return java.lang.String
 */
public String getPrimaryNpa() {
	return primaryNpa;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @return java.lang.String
 */
public String getSagco() {
	return sagco;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @return java.lang.String
 */
public String getTelNbr() {
	return telNbr;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @param newExchange java.lang.String
 */
public void setExchange(String newExchange) {
	exchange = newExchange;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @param newPrimaryNpa java.lang.String
 */
public void setPrimaryNpa(String newPrimaryNpa) {
	primaryNpa = newPrimaryNpa;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @param newSagco java.lang.String
 */
public void setSagco(String newSagco) {
	sagco = newSagco;
}
/**
 * Insert the method's description here.
 * Creation date: (3/18/02 3:34:21 PM)
 * @param newTelNbr java.lang.String
 */
public void setTelNbr(String newTelNbr) {
	telNbr = newTelNbr;
}
}
