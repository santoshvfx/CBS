// $Id: ResourceRoleInformation.java,v 1.4 2005/03/09 22:14:32 ge2851 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService;

/**
 * Contains resource role information.
 * Creation date: (7/5/01 10:12:28 AM)
 * @author: Creighton Malet
 * 
#   History :
#   Date		| Author		| Version	|Notes
#   ----------------------------------------------------------------------------
#
#	11/19/2002	Mark Liljequist	6.0.3								
#	Added OCN data in resource roles.
#
#	02/02/2004	Stevan Dunkin	7.10.2
#	RM 124625	Added ocn_contact_name and ocn_telephone_number to resource role.
#				Added a constructor that does not use OCN data. 
#
#	02/10/2005	CDT Developer 
#	RM 1765165	Added specialServiceCode and centralOfficeCode to resource role.  
 */
public class ResourceRoleInformation
{
	private String resourceRoleName = null;
	private String resourceRoleValue = null;
	
	private String acna = null;
	private String ccna = null;
	
	private String operatingCompanyName = null;
	private String operatingCompanyID = null;
	
	private String operatingCompanyContactName; 
	private String operatingCompanyTelephoneNumber;

	private String specialServiceCode = null; 
	private String centralOfficeCode = null;
	
/**
 * Class constructor
 */
public ResourceRoleInformation()
{
}
/**
 * Class constructor with individual elements.
 * Creation date: (7/5/01 10:18:35 AM)
 * @param aResourceRoleName java.lang.String
 * @param anAcna java.lang.String
 * @param aCcna java.lang.String
 */
public ResourceRoleInformation(String aResourceRoleName, String aResourceRoleValue, String companyName, String companyID, 
		String companyContactName, String companyTelephoneNumber, String anAcna, String aCcna, String aSpecialServiceCode, String aCentralOfficeCode)
{
	resourceRoleName = aResourceRoleName;
	resourceRoleValue = aResourceRoleValue;
	
	operatingCompanyName = companyName;
	operatingCompanyID = companyID;	
	
	operatingCompanyContactName = companyContactName;
	operatingCompanyTelephoneNumber = companyTelephoneNumber;
	
	acna = anAcna;
	ccna = aCcna;

	specialServiceCode = aSpecialServiceCode;
	centralOfficeCode  = aCentralOfficeCode;

}

/**
 * Class constructor with individual elements - No specialServiceCode nor centralOfficeCode
 * Creation date: (7/5/01 10:18:35 AM)
 * @param aResourceRoleName java.lang.String
 * @param anAcna java.lang.String
 * @param aCcna java.lang.String
 */
public ResourceRoleInformation(String aResourceRoleName, String aResourceRoleValue, String companyName, String companyID, 
		String companyContactName, String companyTelephoneNumber, String anAcna, String aCcna)
{
	resourceRoleName = aResourceRoleName;
	resourceRoleValue = aResourceRoleValue;
	
	operatingCompanyName = companyName;
	operatingCompanyID = companyID;	
	
	operatingCompanyContactName = companyContactName;
	operatingCompanyTelephoneNumber = companyTelephoneNumber;
	
	acna = anAcna;
	ccna = aCcna;

}

/**
 * Method ResourceRoleInformation.
 * Class constructor with individual elements.
 * @param aResourceRoleName
 * @param aResourceRoleValue
 * @param anAcna
 * @param aCcna
 */
public ResourceRoleInformation(String aResourceRoleName, String aResourceRoleValue, String anAcna, String aCcna)
{
	resourceRoleName = aResourceRoleName;
	resourceRoleValue = aResourceRoleValue;
	
	acna = anAcna;
	ccna = aCcna;
}

/**
 * Get acna.
 * Creation date: (7/5/01 10:21:23 AM)
 * @return java.lang.String
 */
public java.lang.String getAcna() {
	return acna;
}
/**
 * Get ccna.
 * Creation date: (7/5/01 10:21:23 AM)
 * @return java.lang.String
 */
public java.lang.String getCcna() {
	return ccna;
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
public String getOperatingCompanyContactName() {
	return operatingCompanyContactName;	
}

/**
 * Method getOperatingCompanyTelephoneNumber.
 * @return String
 */
public String getOperatingCompanyTelephoneNumber() {
	return operatingCompanyTelephoneNumber;	
}

/**
 * Get resource role name.
 * Creation date: (7/5/01 10:21:23 AM)
 * @return java.lang.String
 */
public java.lang.String getResourceRoleName() {
	return resourceRoleName;
}
/**
 * Get resource role value.
 * Creation date: (7/5/01 10:28:59 AM)
 * @return java.lang.String
 */
public java.lang.String getResourceRoleValue() {
	return resourceRoleValue;
}

/**
 * Get Special Service Code.
 * @return java.lang.String
 */
public java.lang.String getSpecialServiceCode() {
	return specialServiceCode;
}

/**
 * Get Central Office Code.
 * @return java.lang.String
 */
public java.lang.String getCentralOfficeCode() {
	return centralOfficeCode;
}

/**
 * Set acna.
 * Creation date: (7/5/01 10:21:23 AM)
 * @param newAcna java.lang.String
 */
public void setAcna(java.lang.String newAcna) {
	acna = newAcna;
}
/**
 * Set ccna.
 * Creation date: (7/5/01 10:21:23 AM)
 * @param newCcna java.lang.String
 */
public void setCcna(java.lang.String newCcna) {
	ccna = newCcna;
}

public void setOperatingCompanyName(String aOperatingCompanyName) {
	 operatingCompanyName = aOperatingCompanyName;
}

public void setOperatingCompanyID(String aOperatingCompanyID) {
	operatingCompanyID = aOperatingCompanyID;
}


/**
 * Method setOperatingCompanyContactName.
 * @param aOperatingCompanyContactName
 */
public void setOperatingCompanyContactName( String aOperatingCompanyContactName ) {
	this.operatingCompanyContactName = aOperatingCompanyContactName;
}


/**
 * Method setOperatingCompanyTelephoneNumber.
 * @param aOperatingCompanyTelephoneNumber
 */
public void setOperatingCompanyTelephoneNumber( String aOperatingCompanyTelephoneNumber ) {
	this.operatingCompanyTelephoneNumber = aOperatingCompanyTelephoneNumber;	
}

/**
 * Set resource role name.
 * Creation date: (7/5/01 10:21:23 AM)
 * @param newResourceRoleName java.lang.String
 */
public void setResourceRoleName(java.lang.String newResourceRoleName) {
	resourceRoleName = newResourceRoleName;
}
/**
 * Set resource role value.
 * Creation date: (7/5/01 10:28:59 AM)
 * @param newResourceRoleValue java.lang.String
 */
public void setResourceRoleValue(java.lang.String newResourceRoleValue) {
	resourceRoleValue = newResourceRoleValue;
}

/**
 * Set Special Service Code.
 * @param aSpecialServiceCode java.lang.String
 */
public void setSpecialServiceCode(java.lang.String aSpecialServiceCode) {
	specialServiceCode = aSpecialServiceCode;
}

/**
 * Set Central Office Code.
 * @param aCentralOfficeCode java.lang.String
 */
public void setCentralOfficeCode(java.lang.String aCentralOfficeCode) {
	centralOfficeCode = aCentralOfficeCode;
}

}


