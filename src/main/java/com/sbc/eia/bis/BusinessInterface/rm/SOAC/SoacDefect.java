//$Id:$
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
//# 07/2006		Hongmei Parkin	      Creation

package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

/**
 * @author hw7243
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SoacDefect {

	/**
	 * Constructor for SoacDefect.
	 */
	public SoacDefect() {
		super();
	}
	
	private String defectCode = null;
	private String defectCable = null;
	private String defectPair = null;

	/**
	 * Returns the defectCable.
	 * @return String
	 */
	public String getDefectCable() {
		return defectCable;
	}

	/**
	 * Returns the defectCode.
	 * @return String
	 */
	public String getDefectCode() {
		return defectCode;
	}

	/**
	 * Returns the defectPair.
	 * @return String
	 */
	public String getDefectPair() {
		return defectPair;
	}

	/**
	 * Sets the defectCable.
	 * @param defectCable The defectCable to set
	 */
	public void setDefectCable(String defectCable) {
		this.defectCable = defectCable;
	}

	/**
	 * Sets the defectCode.
	 * @param defectCode The defectCode to set
	 */
	public void setDefectCode(String defectCode) {
		this.defectCode = defectCode;
	}

	/**
	 * Sets the defectPair.
	 * @param defectPair The defectPair to set
	 */
	public void setDefectPair(String defectPair) {
		this.defectPair = defectPair;
	}

}
