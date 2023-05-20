/*
 * Created on Mar 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject;

/**
 * @author sd8583
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DefectFidVO {
	
	private String defectCode = null;
	private String defectCable = null;
	private String defectPair = null;

	/**
	 * @return
	 */
	public String getDefectCable() {
		return defectCable;
	}

	/**
	 * @return
	 */
	public String getDefectCode() {
		return defectCode;
	}

	/**
	 * @return
	 */
	public String getDefectPair() {
		return defectPair;
	}

	/**
	 * @param string
	 */
	public void setDefectCable(String string) {
		defectCable = string;
	}

	/**
	 * @param string
	 */
	public void setDefectCode(String string) {
		defectCode = string;
	}

	/**
	 * @param string
	 */
	public void setDefectPair(String string) {
		defectPair = string;
	}

}
