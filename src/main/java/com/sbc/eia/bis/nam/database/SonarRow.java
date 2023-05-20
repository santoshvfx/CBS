// $Id: SonarRow.java,v 1.2 2002/09/29 03:21:44 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

/**
 * Insert the type's description here.
 * Creation date: (12/13/01 11:02:38 AM)
 * @author: Vickie Chui
 */
public class SonarRow {
	private java.lang.String tn_nbr = "";
	private java.lang.String tn_aecn = "";
	private java.lang.String reservation_id = "";
	private java.sql.Date date;
	
	private java.lang.String tn_status;
/**
 * SonarRow constructor comment.
 */
public SonarRow() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:08:37 AM)
 * @return java.sql.Date
 */
public java.sql.Date getDate() {
	return date;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:07:29 AM)
 * @return java.lang.String
 */
public java.lang.String getReservation_id() {
	return reservation_id;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:07:09 AM)
 * @return java.lang.String
 */
public java.lang.String getTn_aecn() {
	return tn_aecn;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:06:40 AM)
 * @return java.lang.String
 */
public java.lang.String getTn_nbr() {
	return tn_nbr;
}
/**
 * Insert the method's description here.
 * Creation date: (9/23/02 12:32:54 PM)
 * @return java.lang.String
 */
public java.lang.String getTn_status() {
	return tn_status;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:08:37 AM)
 * @param newDate java.sql.Date
 */
public void setDate(java.sql.Date newDate) {
	date = newDate;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:07:29 AM)
 * @param newReservation_id java.lang.String
 */
public void setReservation_id(java.lang.String newReservation_id) {
	reservation_id = newReservation_id;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:07:09 AM)
 * @param newTn_aecn java.lang.String
 */
public void setTn_aecn(java.lang.String newTn_aecn) {
	tn_aecn = newTn_aecn;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/01 11:06:40 AM)
 * @param newTn java.lang.String
 */
public void setTn_nbr(java.lang.String newTn) {
	tn_nbr = newTn;
}
/**
 * Insert the method's description here.
 * Creation date: (9/23/02 12:32:54 PM)
 * @param newTn_status java.lang.String
 */
public void setTn_status(java.lang.String newTn_status) {
	tn_status = newTn_status;
}
}
