// $Id: PortStatusRow.java,v 1.4 2005/03/09 21:49:05 ge2851 Exp $

package com.sbc.eia.bis.nam.database;

/**
 * Represents a row in the PORT_STATUS table.
 * Creation date: (5/4/01 11:43:10 AM)
 * @author: Creighton Malet
 * 
 * 	 History :
 #   Date      | Author			| Version	| Notes
 #   ----------------------------------------------------------------------------
 #
 #	02/02/2004   Stevan Dunkin	  7.10.2
 #	RM 124625    Added fields for ocn_contact_name and ocn_telephone_number
 #
 #	02/10/2005   CDT Developer    
 #	RM 176516    Added fields for specialServiceCode and centralOfficeCode
 */
public class PortStatusRow {
	private java.lang.String npa = "";
	private java.lang.String prfxCd = "";
	private java.lang.String recStsCd = "";
	private java.sql.Date recEffDt = null;
	private int tnLnLowRnge = 0;
	private int tnLnUprRnge = 0;
	private java.lang.String portInd = "";
	private java.lang.String rtctrCd = "";
	private java.lang.String clli = "";
	private java.lang.String lrn = "";
	
	private java.lang.String operatingCompanyName = "";
	private java.lang.String operatingCompanyID = "";
	
	private java.lang.String operatingCompanyContactName = "";
	private java.lang.String operatingCompanyTelephoneNumber = "";

	private java.lang.String poolInd = "";
	private java.lang.String ssc = "";	
	
	private java.lang.String specialServiceCode = "";
	private java.lang.String centralOfficeCode = "";
	
/**
 * Class constructor.
 */
public PortStatusRow() {
	super();
}
/**
 * Formats a row as a string.
 * Creation date: (5/4/01 11:52:39 AM)
 * @return java.lang.String
 */
public String display()
{
	StringBuffer retVal = new StringBuffer();
	retVal.append(npa).append("|").append(prfxCd).append("|").append(tnLnLowRnge).append("|").append(tnLnUprRnge).append("|");
	retVal.append(recStsCd).append("|").append(recEffDt != null ? recEffDt.toString() : "null").append("|").append(portInd).append("|");
	retVal.append(rtctrCd).append("|").append(clli).append("|").append(lrn).append("|");
	retVal.append(operatingCompanyName).append("|").append(operatingCompanyID).append("|");
	retVal.append(operatingCompanyContactName).append("|").append(operatingCompanyTelephoneNumber).append("|");
	
	retVal.append(specialServiceCode).append("|").append(centralOfficeCode).append("|");
	
	return retVal.toString();
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getClli() {
	return clli;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getLrn() {
	return lrn;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getNpa() {
	return npa;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getPortInd() {
	return portInd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getPrfxCd() {
	return prfxCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.sql.Date
 */
public java.sql.Date getRecEffDt() {
	return recEffDt;
}

public java.lang.String getOperatingCompanyName() {
	return operatingCompanyName;
}

public java.lang.String getOperatingCompanyID() {
	return operatingCompanyID;
}

/**
 * Method getOperatingCompanyContactName.
 * @return String
 */
public java.lang.String getOperatingCompanyContactName() {
	return operatingCompanyContactName;
}

/**
 * Method getOperatingCompanyTelephoneNumber.
 * @return String
 */
public java.lang.String getOperatingCompanyTelephoneNumber() {
	return operatingCompanyTelephoneNumber;	
}

/**
 * Method getSpecialServiceCode.
 * @return String
 */
public java.lang.String getSpecialServiceCode() {
	return specialServiceCode;	
}

/**
 * Method getCentralOfficeCode.
 * @return String
 */
public java.lang.String getCentralOfficeCode() {
	return centralOfficeCode;	
}


/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getRecStsCd() {
	return recStsCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return java.lang.String
 */
public java.lang.String getRtctrCd() {
	return rtctrCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return int
 */
public int getTnLnLowRnge() {
	return tnLnLowRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @return int
 */
public int getTnLnUprRnge() {
	return tnLnUprRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newClli java.lang.String
 */
public void setClli(java.lang.String newClli) {
	clli = newClli;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newLrn java.lang.String
 */
public void setLrn(java.lang.String newLrn) {
	lrn = newLrn;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newNpa java.lang.String
 */
public void setNpa(java.lang.String newNpa) {
	npa = newNpa;
}

public void setOperatingCompanyName(java.lang.String aOperatingCompanyName) {
	operatingCompanyName = aOperatingCompanyName;
}

public void setOperatingCompanyID(java.lang.String aOperatingCompanyID) {
	operatingCompanyID = aOperatingCompanyID;
}

/**
 * Method setOperatingCompanyContactName.
 * @param aOperatingCompanyContactName
 */
public void setOperatingCompanyContactName( java.lang.String aOperatingCompanyContactName ) {
	operatingCompanyContactName = aOperatingCompanyContactName;
}


/**
 * Method setOperatingCompanyTelephoneNumber.
 * @param aOperatingCompanyTelephoneNumber
 */
public void setOperatingCompanyTelephoneNumber( java.lang.String aOperatingCompanyTelephoneNumber ) {
	operatingCompanyTelephoneNumber = aOperatingCompanyTelephoneNumber;	
}

/**
 * Method setSpecialServiceCode.
 * @param aSpecialServiceCode
 */
public void setSpecialServiceCode( java.lang.String aSpecialServiceCode ) {
	specialServiceCode = aSpecialServiceCode;	
}

/**
 * Method setCentralOfficeCode.
 * @param aCentralOfficeCode
 */
public void setCentralOfficeCode( java.lang.String aCentralOfficeCode ) {
	centralOfficeCode = aCentralOfficeCode;	
}

/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newPortInd java.lang.String
 */
public void setPortInd(java.lang.String newPortInd) {
	portInd = newPortInd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newPrfxCd java.lang.String
 */
public void setPrfxCd(java.lang.String newPrfxCd) {
	prfxCd = newPrfxCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newRecEffDt java.sql.Date
 */
public void setRecEffDt(java.sql.Date newRecEffDt) {
	recEffDt = newRecEffDt;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newRecStsCd java.lang.String
 */
public void setRecStsCd(java.lang.String newRecStsCd) {
	recStsCd = newRecStsCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newRtctrCd java.lang.String
 */
public void setRtctrCd(java.lang.String newRtctrCd) {
	rtctrCd = newRtctrCd;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newTnLnLowRnge int
 */
public void setTnLnLowRnge(int newTnLnLowRnge) {
	tnLnLowRnge = newTnLnLowRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (5/22/01 3:36:14 PM)
 * @param newTnLnUprRnge int
 */
public void setTnLnUprRnge(int newTnLnUprRnge) {
	tnLnUprRnge = newTnLnUprRnge;
}
	/**
	 * Returns the poolInd.
	 * @return java.lang.String
	 */
	public java.lang.String getPoolInd() {
		return poolInd;
	}

	/**
	 * Sets the poolInd.
	 * @param poolInd The poolInd to set
	 */
	public void setPoolInd(java.lang.String newPoolInd) {
		poolInd = newPoolInd;
	}

	/**
	 * Returns the ssc.
	 * @return java.lang.String
	 */
	public java.lang.String getSsc() {
		return ssc;
	}

	/**
	 * Sets the ssc.
	 * @param ssc The ssc to set
	 */
	public void setSsc(java.lang.String newSsc) {
		ssc = newSsc;
	}

}
