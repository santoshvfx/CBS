//$Id: VoipReqSnEDataObject.java,v 1.4 2006/07/27 15:01:24 jc1421 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		Sriram Chevuturu      Creation

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class VoipReqSnEDataObject {

	private String actionIndicator = null;
	private String aTelephoneNumber = null;
	private String aOldProvider = null;
	private String aNewProvider = null;
	private String aLocalRountingNumber = null;
	private String aRetainedPortingIndicator = null;
	private String aNonRetainedPortingIndicator = null;
	private String aLocalServingOfficeData = null;

	/**
	 * Constructor for VoipSnEDataObject.
	 */
	public VoipReqSnEDataObject() {

	}

	/**
	 * Method VoipReqSnEDataObject.
	 * @param aActInd
	 * @param aTN
	 * @param aOP
	 * @param aNP
	 * @param aLRN
	 * @param aRPI
	 * @param aNRPI
	 */
	public VoipReqSnEDataObject(
		String aActInd,
		String aTN,
		String aOP,
		String aNP,
		String aLRN,
		String aRPI,
		String aNRPI,
		String aLSOData) {
		actionIndicator = aActInd;
		aTelephoneNumber = aTN;
		aOldProvider = aOP;
		aNewProvider = aNP;
		aLocalRountingNumber = aLRN;
		aRetainedPortingIndicator = aRPI;
		aNonRetainedPortingIndicator = aNRPI;
		aLocalServingOfficeData = aLSOData;
	}

	/**
	 * Returns the localRountingNumber.
	 * @return String
	 */
	public String getLocalRountingNumber() {
		return aLocalRountingNumber;
	}

	/**
	 * Returns the newProvider.
	 * @return String
	 */
	public String getNewProvider() {
		return aNewProvider;
	}

	/**
	 * Returns the oldProvider.
	 * @return String
	 */
	public String getOldProvider() {
		return aOldProvider;
	}

	/**
	 * Returns the telephoneNumber.
	 * @return String
	 */
	public String getTelephoneNumber() {
		return aTelephoneNumber;
	}

	/**
	 * Sets the localRountingNumber.
	 * @param localRountingNumber The localRountingNumber to set
	 */
	public void setLocalRountingNumber(String localRountingNumber) {
		aLocalRountingNumber = localRountingNumber;
	}

	/**
	 * Sets the newProvider.
	 * @param newProvider The newProvider to set
	 */
	public void setNewProvider(String newProvider) {
		aNewProvider = newProvider;
	}

	/**
	 * Sets the oldProvider.
	 * @param oldProvider The oldProvider to set
	 */
	public void setOldProvider(String oldProvider) {
		aOldProvider = oldProvider;
	}

	/**
	 * Sets the telephoneNumber.
	 * @param telephoneNumber The telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		aTelephoneNumber = telephoneNumber;
	}

	/**
	 * Returns the actionIndicator.
	 * @return String
	 */
	public String getActionIndicator() {
		return actionIndicator;
	}

	/**
	 * Sets the actionIndicator.
	 * @param actionIndicator The actionIndicator to set
	 */
	public void setActionIndicator(String actionIndicator) {
		this.actionIndicator = actionIndicator;
	}

	/**
	 * Returns the aLocalRountingNumber.
	 * @return String
	 */
	public String getALocalRountingNumber() {
		return aLocalRountingNumber;
	}

	/**
	 * Returns the aNewProvider.
	 * @return String
	 */
	public String getANewProvider() {
		return aNewProvider;
	}

	/**
	 * Returns the aOldProvider.
	 * @return String
	 */
	public String getAOldProvider() {
		return aOldProvider;
	}

	/**
	 * Returns the aTelephoneNumber.
	 * @return String
	 */
	public String getATelephoneNumber() {
		return aTelephoneNumber;
	}

	/**
	 * Sets the aLocalRountingNumber.
	 * @param aLocalRountingNumber The aLocalRountingNumber to set
	 */
	public void setALocalRountingNumber(String aLocalRountingNumber) {
		this.aLocalRountingNumber = aLocalRountingNumber;
	}

	/**
	 * Sets the aNewProvider.
	 * @param aNewProvider The aNewProvider to set
	 */
	public void setANewProvider(String aNewProvider) {
		this.aNewProvider = aNewProvider;
	}

	/**
	 * Sets the aOldProvider.
	 * @param aOldProvider The aOldProvider to set
	 */
	public void setAOldProvider(String aOldProvider) {
		this.aOldProvider = aOldProvider;
	}

	/**
	 * Sets the aTelephoneNumber.
	 * @param aTelephoneNumber The aTelephoneNumber to set
	 */
	public void setATelephoneNumber(String aTelephoneNumber) {
		this.aTelephoneNumber = aTelephoneNumber;
	}

	/**
	 * Returns the aNonRetainedPortingIndicator.
	 * @return String
	 */
	public String getNonRetainedPortingIndicator() {
		return aNonRetainedPortingIndicator;
	}

	/**
	 * Returns the aRetainedPortingIndicator.
	 * @return String
	 */
	public String getRetainedPortingIndicator() {
		return aRetainedPortingIndicator;
	}

	/**
	 * Sets the aNonRetainedPortingIndicator.
	 * @param aNonRetainedPortingIndicator The aNonRetainedPortingIndicator to set
	 */
	public void setNonRetainedPortingIndicator(String aNonRetainedPortingIndicator) {
		this.aNonRetainedPortingIndicator = aNonRetainedPortingIndicator;
	}

	/**
	 * Sets the aRetainedPortingIndicator.
	 * @param aRetainedPortingIndicator The aRetainedPortingIndicator to set
	 */
	public void setRetainedPortingIndicator(String aRetainedPortingIndicator) {
		this.aRetainedPortingIndicator = aRetainedPortingIndicator;
	}

	/**
	 * Returns the aLocalServingOfficeData.
	 * @return String
	 */
	public String getLocalServingOfficeData() {
		return aLocalServingOfficeData;
	}

	/**
	 * Sets the aLocalServingOfficeData.
	 * @param aLocalServingOfficeData The aLocalServingOfficeData to set
	 */
	public void setLocalServingOfficeData(String aLocalServingOfficeData) {
		this.aLocalServingOfficeData = aLocalServingOfficeData;
	}

}
