// $Id: TnaRetTelNbrRow.java,v 1.1 2002/09/29 03:20:42 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

/**
 * Insert the type's description here.
 * Creation date: (4/9/01 2:25:56 PM)
 * @author: Hongmei Parkin
 */
public class TnaRetTelNbrRow {
	private java.lang.String npa = "";
	private java.lang.String prefix= "";
	private java.lang.String line= "";
	private java.lang.String companyCode= "";
	private java.lang.String tnStatus= "";
	private java.lang.String reservationId= "";
	private java.sql.Date updateDate;
	private java.lang.String selectType= "";
	private java.lang.String switchTnListNbr= "";
	private byte [] addressObject = null;
	private java.lang.String addressVersion= "";
	private java.lang.String inqChngDtTm= "";
	private java.lang.String exchange= "";
	private java.lang.String npanxx= "";
	private java.lang.String saga = "";
	private java.lang.String divCode = "";
	private java.lang.String wireCenter = "";	
	private java.lang.String switchWc = "";	
/**
 * TnaRetTelNbrRow constructor comment.
 */
public TnaRetTelNbrRow() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/01 1:20:14 PM)
 * @return byte[]
 */
public byte [] getAddressObject() {
	return addressObject;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:33 PM)
 * @return java.lang.String
 */
public java.lang.String getAddressVersion() {
	return addressVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:33 PM)
 * @return java.lang.String
 */
public java.lang.String getCompanyCode() {
	return companyCode;
}
/**
 * Insert the method's description here.
 * Creation date: (8/2/01 3:53:59 PM)
 * @return java.lang.String
 */
public java.lang.String getDivCode() {
	return divCode;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 3:48:14 PM)
 * @return java.lang.String
 */
public java.lang.String getExchange() {
	return exchange;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getInqChngDtTm() {
	return inqChngDtTm;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getLine() {
	return line;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getNpa() {
	return npa;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getNpanxx() {
	return npanxx;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getPrefix() {
	return prefix;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getReservationId() {
	return reservationId;
}
/**
 * Insert the method's description here.
 * Creation date: (5/15/01 3:07:25 PM)
 * @return java.lang.String
 */
public java.lang.String getSaga() {
	return saga;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getSelectType() {
	return selectType;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getSwitchTnListNbr() {
	return switchTnListNbr;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:16:19 AM)
 * @return java.lang.String
 */
public java.lang.String getSwitchWc() {
	return switchWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.lang.String
 */
public java.lang.String getTnStatus() {
	return tnStatus;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @return java.sql.Date
 */
public java.sql.Date getUpdateDate() {
	return updateDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/22/02 11:06:52 AM)
 * @return java.lang.String
 */
public java.lang.String getWireCenter() {
	return wireCenter;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/01 1:20:14 PM)
 * @param newAddressObject byte[]
 */
public void setAddressObject(byte [] newAddressObject) {
	addressObject = newAddressObject;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:33 PM)
 * @param newAddressVersion java.lang.String
 */
public void setAddressVersion(java.lang.String newAddressVersion) {
	addressVersion = newAddressVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newCompanyCode java.lang.String
 */
public void setCompanyCode(java.lang.String newCompanyCode) {
	companyCode = newCompanyCode.toUpperCase();
}
/**
 * Insert the method's description here.
 * Creation date: (8/2/01 3:53:59 PM)
 * @param newDivCode java.lang.String
 */
public void setDivCode(java.lang.String newDivCode) {
	if (newDivCode != null)
		divCode = newDivCode.toUpperCase();

}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 3:48:14 PM)
 * @param newExchange java.lang.String
 */
public void setExchange(java.lang.String newExchange) {
	exchange = newExchange;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newInqChngDtTm java.lang.String
 */
public void setInqChngDtTm(java.lang.String newInqChngDtTm) {
	inqChngDtTm = newInqChngDtTm;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newLine java.lang.String
 */
public void setLine(java.lang.String newLine) {
	line = newLine;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newNpa java.lang.String
 */
public void setNpa(java.lang.String newNpa) {
	npa = newNpa;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newNpanxx java.lang.String
 */
public void setNpanxx(java.lang.String newNpanxx) {
	npanxx = newNpanxx;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newPrefix java.lang.String
 */
public void setPrefix(java.lang.String newPrefix) {
	prefix = newPrefix;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newReservationId java.lang.String
 */
public void setReservationId(java.lang.String newReservationId) {
	reservationId = newReservationId.toUpperCase();
}
/**
 * Insert the method's description here.
 * Creation date: (5/15/01 3:07:25 PM)
 * @param newSaga java.lang.String
 */
public void setSaga(java.lang.String newSaga) {
	saga = newSaga;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newSelectType java.lang.String
 */
public void setSelectType(java.lang.String newSelectType) {
	selectType = newSelectType;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newSwitchTnListNbr java.lang.String
 */
public void setSwitchTnListNbr(java.lang.String newSwitchTnListNbr) {
	switchTnListNbr = newSwitchTnListNbr;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:16:19 AM)
 * @param newSwitchWc java.lang.String
 */
public void setSwitchWc(java.lang.String newSwitchWc) {
	switchWc = newSwitchWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newTnStatus java.lang.String
 */
public void setTnStatus(java.lang.String newTnStatus) {
	tnStatus = newTnStatus.toUpperCase();
}
/**
 * Insert the method's description here.
 * Creation date: (4/9/01 2:30:34 PM)
 * @param newUpdateDate java.sql.Date
 */
public void setUpdateDate(java.sql.Date newUpdateDate) {
	updateDate = newUpdateDate;
}
/**
 * Insert the method's description here.
 * Creation date: (1/22/02 11:06:52 AM)
 * @param newWireCenter java.lang.String
 */
public void setWireCenter(java.lang.String newWireCenter) {
	wireCenter = newWireCenter;
}
}
