/*
 * Created on Jun 12, 2006
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
public class CVOIPServiceOrderVO {
	
	private String actionIndicator = null;
	private String telephoneNo = null;
	private String oldProvider = null;
	private String newProvider = null;
	private String LRN = null;
	private String retPortInd = null;
	private String nonRetPortInd = null;
	private String localServingInd = null;
	
	public CVOIPServiceOrderVO(String actionIndicator,String telephoneNo, String oldProvider,String newProvider, String LRN, String retPortInd, String nonRetPortInd, String localServingInd)
	{
		this.actionIndicator = actionIndicator;
		this.telephoneNo = telephoneNo;
		this.oldProvider = oldProvider;
		this.newProvider = newProvider;
		this.LRN = LRN;
		this.retPortInd = retPortInd;
		this.nonRetPortInd = nonRetPortInd;
		this.localServingInd = localServingInd; 
		
	}

	/**
	 * @return
	 */
	public String getLRN() {
		return LRN;
	}

	/**
	 * @return
	 */
	public String getNewProvider() {
		return newProvider;
	}

	/**
	 * @return
	 */
	public String getOldProvider() {
		return oldProvider;
	}

	/**
	 * @return
	 */
	public String getTelephoneNo() {
		return telephoneNo;
	}

	/**
	 * @return
	 */
	public String getActionIndicator() {
		return actionIndicator;
	}

	/**
	 * @return
	 */
	public String getNonRetPortInd() {
		return nonRetPortInd;
	}

	/**
	 * @return
	 */
	public String getRetPortInd() {
		return retPortInd;
	}

	/**
	 * @return
	 */
	public String getLocalServingInd() {
		return localServingInd;
	}

}
