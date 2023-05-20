// $Id: CarrierByClliRow.java,v 1.4 2004/11/15 18:04:01 biscvsid Exp $

package com.sbc.eia.bis.BusinessInterface.rm.CARE;

/**
 * Insert the type's description here.
 * Creation date: (1/16/02 3:38:42 PM)
 * @author: Sam Lok
 */
public class CarrierByClliRow {
	private java.lang.String CIC_CODE = "";
	private java.lang.String NAME = "" ;
	private java.lang.String JURISDICTIONAL_IND = "";
	private java.lang.String DISPLAY_IND = "";
	private java.lang.String ACNA = "";
	private java.lang.String ORDERABLE_IND = "";
/**
 * CarrierByClliRow constructor comment.
 */
public CarrierByClliRow() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:43:48 PM)
 * @return java.lang.String
 */
public java.lang.String getACNA() {
	return ACNA;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:40:57 PM)
 * @return java.lang.String
 */
public java.lang.String getCIC_CODE() {
	return CIC_CODE;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:43:14 PM)
 * @return java.lang.String
 */
public java.lang.String getDISPLAY_IND() {
	return DISPLAY_IND;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:42:47 PM)
 * @return java.lang.String
 */
public java.lang.String getJURISDICTIONAL_IND() {
	return JURISDICTIONAL_IND;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:41:54 PM)
 * @return java.lang.String
 */
public java.lang.String getNAME() {
	return NAME;
}
/**
 * Returns the oRDERABLE_IND.
 * @return java.lang.String
 */
public java.lang.String getORDERABLE_IND()
{
	return ORDERABLE_IND;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:43:48 PM)
 * @param newACNA java.lang.String
 */
public void setACNA(java.lang.String newACNA) {
	ACNA = newACNA;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:40:57 PM)
 * @param newCIC_CODE java.lang.String
 */
public void setCIC_CODE(java.lang.String newCIC_CODE) {
	CIC_CODE = newCIC_CODE;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:43:14 PM)
 * @param newDISPLAY_IND java.lang.String
 */
public void setDISPLAY_IND(java.lang.String newDISPLAY_IND) {
	DISPLAY_IND = newDISPLAY_IND;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:42:47 PM)
 * @param newJURISDICTIONAL_IND java.lang.String
 */
public void setJURISDICTIONAL_IND(java.lang.String newJURISDICTIONAL_IND) {
	JURISDICTIONAL_IND = newJURISDICTIONAL_IND;
}
/**
 * Insert the method's description here.
 * Creation date: (1/16/02 3:41:54 PM)
 * @param newNAME java.lang.String
 */
public void setNAME(java.lang.String newNAME) {
	NAME = newNAME;
}
/**
 * Sets the ORDERABLE_IND.
 * @param newORDERABLE_IND The ORDERABLE_IND to set
 */
public void setORDERABLE_IND(java.lang.String newORDERABLE_IND)
{
	ORDERABLE_IND = newORDERABLE_IND;
}

}
