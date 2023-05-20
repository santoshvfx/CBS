package com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo;

/**
 * @author hw7243
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyFacilityInfoReturn {

	/**
	 * Constructor for ModifyFacilityInfoReturn.
	 */
	public ModifyFacilityInfoReturn() {
		super();
	}
	
	private String fttnDslamName = null;
	private String fttnVlanId = null;
	
	

	/**
	 * Returns the fttnDslamName.
	 * @return String
	 */
	public String getFttnDslamName() {
		return fttnDslamName;
	}

	/**
	 * Returns the fttnVlanId.
	 * @return String
	 */
	public String getFttnVlanId() {
		return fttnVlanId;
	}

	/**
	 * Sets the fttnDslamName.
	 * @param fttnDslamName The fttnDslamName to set
	 */
	public void setFttnDslamName(String fttnDslamName) {
		this.fttnDslamName = fttnDslamName;
	}

	/**
	 * Sets the fttnVlanId.
	 * @param fttnVlanId The fttnVlanId to set
	 */
	public void setFttnVlanId(String fttnVlanId) {
		this.fttnVlanId = fttnVlanId;
	}

}
