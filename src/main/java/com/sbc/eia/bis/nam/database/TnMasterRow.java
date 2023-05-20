// $Id: TnMasterRow.java,v 1.1 2002/09/29 03:20:42 dm2328 Exp $

package com.sbc.eia.bis.nam.database;

/**
 * Insert the type's description here.
 * Creation date: (3/29/02 7:07:26 AM)
 * @author: Vickie Chui
 */
public class TnMasterRow {
	private java.lang.String npa = "";
	private java.lang.String prfxCd = "";
	private int tnLnLowRnge = 0;	
	private int tnLnUprRnge = 0;	
	private java.lang.String exco = "";		
	private java.lang.String soacWc = "";
	private java.lang.String cosmosWc = "";
	private java.lang.String switchEntity = "";
	private java.lang.String imsRegn = "";
	private java.lang.String stateCd = "";
	private java.lang.String hostRegnCd = "";
	private java.lang.String switchHostRegnCd = "";
	private java.lang.String switchCnvsnSw = "";
	private java.sql.Date switchCnvsnDtTm;	
	private java.lang.String cllcCd = "";		
	
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getCllcCd() {
	return cllcCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getCosmosWc() {
	return cosmosWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getExco() {
	return exco;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getHostRegnCd() {
	return hostRegnCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getImsRegn() {
	return imsRegn;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getNpa() {
	return npa;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getPrfxCd() {
	return prfxCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getSoacWc() {
	return soacWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getStateCd() {
	return stateCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.sql.Date
 */
public java.sql.Date getSwitchCnvsnDtTm() {
	return switchCnvsnDtTm;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getSwitchCnvsnSw() {
	return switchCnvsnSw;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getSwitchEntity() {
	return switchEntity;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return java.lang.String
 */
public java.lang.String getSwitchHostRegnCd() {
	return switchHostRegnCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return int
 */
public int getTnLnLowRnge() {
	return tnLnLowRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @return int
 */
public int getTnLnUprRnge() {
	return tnLnUprRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newCllcCd java.lang.String
 */
public void setCllcCd(java.lang.String newCllcCd) {
	cllcCd = newCllcCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newCosmosWc java.lang.String
 */
public void setCosmosWc(java.lang.String newCosmosWc) {
	cosmosWc = newCosmosWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newExco java.lang.String
 */
public void setExco(java.lang.String newExco) {
	exco = newExco;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newHostRegnCd java.lang.String
 */
public void setHostRegnCd(java.lang.String newHostRegnCd) {
	hostRegnCd = newHostRegnCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newImsRegn java.lang.String
 */
public void setImsRegn(java.lang.String newImsRegn) {
	imsRegn = newImsRegn;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newNpa java.lang.String
 */
public void setNpa(java.lang.String newNpa) {
	npa = newNpa;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newPrfxCd java.lang.String
 */
public void setPrfxCd(java.lang.String newPrfxCd) {
	prfxCd = newPrfxCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newSoacWc java.lang.String
 */
public void setSoacWc(java.lang.String newSoacWc) {
	soacWc = newSoacWc;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newStateCd java.lang.String
 */
public void setStateCd(java.lang.String newStateCd) {
	stateCd = newStateCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newSwitchCnvsnDtTm java.sql.Date
 */
public void setSwitchCnvsnDtTm(java.sql.Date newSwitchCnvsnDtTm) {
	switchCnvsnDtTm = newSwitchCnvsnDtTm;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newSwitchCnvsnSw java.lang.String
 */
public void setSwitchCnvsnSw(java.lang.String newSwitchCnvsnSw) {
	switchCnvsnSw = newSwitchCnvsnSw;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newSwitchEntity java.lang.String
 */
public void setSwitchEntity(java.lang.String newSwitchEntity) {
	switchEntity = newSwitchEntity;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newSwitchHostRegnCd java.lang.String
 */
public void setSwitchHostRegnCd(java.lang.String newSwitchHostRegnCd) {
	switchHostRegnCd = newSwitchHostRegnCd;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newTnLnLowRnge int
 */
public void setTnLnLowRnge(int newTnLnLowRnge) {
	tnLnLowRnge = newTnLnLowRnge;
}
/**
 * Insert the method's description here.
 * Creation date: (4/2/02 11:15:17 AM)
 * @param newTnLnUprRnge int
 */
public void setTnLnUprRnge(int newTnLnUprRnge) {
	tnLnUprRnge = newTnLnUprRnge;
}
}
