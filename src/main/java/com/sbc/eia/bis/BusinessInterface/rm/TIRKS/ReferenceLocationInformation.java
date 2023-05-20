package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Contains design related information.
 * Creation date: (05/01/02)
 * @author: Mark Liljequist

#   History :
#   Date      	| Author        | Version	| Notes
#   ----------------------------------------------------------------------------
#
#   09/16/02    Mark Liljequist	6.0.0		RM64179
#											Changes for ACNA in the parse.
#	07/15/04	Mark Liljequist 9.0.0		Changes for parsing the description for cages.
*/

public class ReferenceLocationInformation {

	private String theDescription;
	private String theACNA;
	private String theCCNA;
	private CableId theCableId;
	private String theOwner;
	private String theLocName;

	private static final String[] theCollocationNames =
		new String[] {
			"CAGE",
			"COLLOC",
			"COLOC",
			"PHYSICAL",
			"VIRTUAL",
			"COMMON",
			"SUBLEASED",
			};

	/**
	 *  ReferenceLocationInformation constructor comment.
	 */
	
	public ReferenceLocationInformation() {

		super();

		theDescription = "";
		theACNA = "";
		theCCNA = "";
		theOwner = "";
		theLocName = "";
	}
	/**
	 * Get the ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getACNA() {
		return theACNA;
	}
	/**
	 * Get the ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getCCNA() {
		return theCCNA;
	}
	/**
	 * Get the description.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getDescription() {
		return theDescription;
	}
	/**
	 * Get the location name.
	 * Creation date: (9/17/2002 11:36:07 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getLocName() {
		return theLocName;
	}
	/**
	 * Get the owner.
	 * Creation date: (9/17/2002 11:36:07 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getOwner() {
		return theOwner;
	}
	/**
	 * Check for a valid ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public boolean isValidACNA(String aACNA) {

		if (aACNA == null)
			return false;

		if (aACNA.trim().length() != 3)
			return false;

		if (aACNA.trim().indexOf(" ") > -1)
			return false;

		return true;
	}
	/**
	 * Check for a valid ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public String parseAITForACNA() {

		if (this.parseOwnerForACNA() == null)
			if (this.parseLocNameForACNA() == null)
				this.parseDescriptionForACNA();

		return theACNA;

	}
	/**
	 * Look for an ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String parseDescriptionForACNA() {

		String s;

		if (theDescription != null) {
			int l = 0;
			if ((l = theDescription.indexOf("ACNA")) > -1) {
				try {
					s = theDescription.substring(l + 5, l + 8);
				} catch (Exception e) {
					s = "";
				}
				if (isValidACNA(s)) {
					theACNA = s.trim();
					return theACNA;
				}
			} else if ((l = theDescription.indexOf("CCNA")) > -1) {
				try {
					s = theDescription.substring(l + 5, l + 8);
				} catch (Exception e) {
					s = "";
				}
				if (isValidACNA(s)) {
					theACNA = s.trim();
					return theACNA;
				}
			}
		}

		return null;
	}
	/**
	 * Look for an ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String parseIACDescriptionForACNA() {

		String s;

		if (theDescription != null) {
			int l = 0;
			if ((l = theDescription.indexOf("IAC=")) > -1) {
				try {
					s = theDescription.substring(l + 4, l + 7);
				} catch (Exception e) {
					s = "";
				}
				if (isValidACNA(s)) {
					theACNA = s.trim();
					return theACNA;
				}
			}
		}

		return null;

	}
	/**
	 * Look for an ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String parseLocNameForACNA() {

		if (isValidACNA(theLocName))
			theACNA = theLocName.trim();
		else
			return null;

		return theACNA;
	}
	/**
	 * Look for an ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String parseOwnerForACNA() {

		if (isValidACNA(theOwner))
			theACNA = theOwner.trim();
		else
			return null;

		return theACNA;
	}

	/**
	* Method parseDescriptionForColocation.
	* @param collocationNames
	* @return boolean
	*/

	public boolean parseDescriptionForCollocation() {
		
		for (int i = 0; i < theCollocationNames.length; i++) {
			if (theDescription.indexOf(theCollocationNames[i]) > -1) {
				return true;
			}
		}
		
		return false;

	}

	/**
	 * Look for an ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @return java.lang.String
	 */
	public java.lang.String parseSWBTForACNA() {

		if (this.parseIACDescriptionForACNA() == null)
			if (this.parseDescriptionForACNA() == null)
				if (this.parseOwnerForACNA() == null)
					this.parseLocNameForACNA();

		return theACNA;
	}
	/**
	 * Set the ACNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @param newACNA java.lang.String
	 */
	public void setACNA(java.lang.String newACNA) {
		theACNA = newACNA;
	}
	/**
	 * Set the CCNA.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @param newCCNA java.lang.String
	 */
	public void setCCNA(java.lang.String newCCNA) {
		theCCNA = newCCNA;
	}
	/**
	 * set the Description.
	 * Creation date: (9/12/2002 10:10:33 AM)
	 * @param newDescription java.lang.String
	 */
	public void setDescription(java.lang.String newDescription) {
		theDescription = newDescription;
	}
	/**
	 * Set the location name.
	 * Creation date: (9/17/2002 11:36:07 AM)
	 * @param newLocname java.lang.String
	 */
	public void setLocName(java.lang.String newLocName) {
		theLocName = newLocName;
	}
	/**
	 * Set the owner.
	 * Creation date: (9/17/2002 11:36:07 AM)
	 * @param newOwner java.lang.String
	 */
	public void setOwner(java.lang.String newOwner) {
		theOwner = newOwner;
	}
}
