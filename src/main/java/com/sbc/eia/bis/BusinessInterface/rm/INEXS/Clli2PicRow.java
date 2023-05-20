// $Id: Clli2PicRow.java,v 1.1 2002/09/29 03:19:50 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.INEXS;

/**
 * Insert the type's description here.
 * Creation date: (5/4/01 11:43:10 AM)
 * @author: David Brawley
 */
public class Clli2PicRow {
	private java.lang.String picTypeCd = "";
	private java.lang.String cxrCd = "";
	private java.lang.String cxrNm = "";
	private java.lang.String acnaCd = "";
/**
 * Clli2PicRow constructor comment.
 */
public Clli2PicRow() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:52:39 AM)
 * @return java.lang.String
 */
public String display() {
	String retVal = new String();
	return (retVal = picTypeCd + "|" + cxrCd + "|" + cxrNm + "|" + acnaCd + "|");
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:47:34 AM)
 * @return java.lang.String
 */
public java.lang.String getAcnaCd() {
	return acnaCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:46:35 AM)
 * @return java.lang.String
 */
public java.lang.String getCxrCd() {
	return cxrCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:47:04 AM)
 * @return java.lang.String
 */
public java.lang.String getCxrNm() {
	return cxrNm;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:46:11 AM)
 * @return java.lang.String
 */
public java.lang.String getPicTypeCd() {
	return picTypeCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:47:34 AM)
 * @param newAcnaCd java.lang.String
 */
public void setAcnaCd(java.lang.String newAcnaCd) {
	acnaCd = newAcnaCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:46:35 AM)
 * @param newCxrCd java.lang.String
 */
public void setCxrCd(java.lang.String newCxrCd) {
	cxrCd = newCxrCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:47:04 AM)
 * @param newCxrNm java.lang.String
 */
public void setCxrNm(java.lang.String newCxrNm) {
	cxrNm = newCxrNm;
}
/**
 * Insert the method's description here.
 * Creation date: (5/4/01 11:46:11 AM)
 * @param newPicTypeCd java.lang.String
 */
public void setPicTypeCd(java.lang.String newPicTypeCd) {
	picTypeCd = newPicTypeCd;
}
}
