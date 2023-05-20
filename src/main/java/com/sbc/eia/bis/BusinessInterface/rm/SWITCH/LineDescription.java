// $Id: LineDescription.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

/**
 * SWITCH Line Description.
 * @author: Gerry Escolar
 *
#
#   History :
#   Date      | Author        | Version      
#   ----------------------------------------------------------------------------
#
#	07/16/02	G. Escolar  	4.1 Beta 1
#
#	Notes:
#      LS CFA changes for November 2002 release:
#
#         1) New PENDING_ASSIGNMENT and PENDING_DISCONNECT status for ASM
#         2) New algorithms for determining ASM and OA statuses
#         3) New Past Due Date field (PDD)
#            ** NOTE ** Due Date is sent out as CCYYMMDD  
#     
**/

public class LineDescription {

	private String aASMStatus;

	private String aOA;

	private String aPDD;
	
	private String aACNA;

	private String aEccCkt;
	
	private Exception exception = null;

/**
 * LineDescription constructor comment.
 */
public LineDescription() {
	super();
}
public java.lang.String getACNA() {
	return aACNA;
}
/**
 * Insert the method's description here.
 * @return java.lang.String
 */
public java.lang.String getASMStatus() {
	return aASMStatus;
}
/**
 * Get the exception.
 * @return java.lang.Exception
 */
public java.lang.Exception getException() {
	return exception;
}
public java.lang.String getOA() {
	return aOA;
}
public java.lang.String getPDD() {
	return aPDD;
}
public void setACNA(java.lang.String newACNA) {
	aACNA = newACNA;
}
/**
 * Insert the method's description here.
 */
public void setASMStatus(java.lang.String newASMStatus) {
	aASMStatus = newASMStatus;
}
/**
 * Set the exception.
 * @param newException java.lang.Exception
 */
public void setException(java.lang.Exception newException) {
	exception = newException;
}
public void setOA(java.lang.String newOA) {
	aOA = newOA;
}
public void setPDD(java.lang.String newPDD) {
	aPDD = newPDD;
}
	/**
	 * Returns the aCKID.
	 * @return String
	 */
	public String getEccCkt() {
		return aEccCkt;
	}

	/**
	 * Sets the aCKID.
	 * @param aCKID The aCKID to set
	 */
	public void setEccCkt(String aCKID) {
		this.aEccCkt = aCKID;
	}

}
