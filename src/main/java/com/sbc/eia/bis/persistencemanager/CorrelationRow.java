//$Id: CorrelationRow.java,v 1.1 2005/05/23 18:00:07 jn1936 Exp $

//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 05/06/2005   Jinmin Ni      Creation.


package com.sbc.eia.bis.persistencemanager;

import java.util.Date;

/**
 * @author jn1936
 *
 */
public class CorrelationRow 
{
	private String methodName;
	private String clientKey;
	private String bisContextData;
	private Date timeStamp;
	private Date expirationDate;
	
	/**
	 * @see java.lang.Object#Object()
	 */
	public CorrelationRow(){}
	
	/**
	 * Method CorrelationRow.
	 * @param methodName
	 * @param clientKey
	 * @param bisContextDate
	 */
	public CorrelationRow(String methodName, String clientKey, String bisContextDate)
	{
		this.methodName = methodName;
		this.clientKey = clientKey;
		this.bisContextData = bisContextDate;
		
	}

	/**
	 * Returns the clientKey.
	 * @return String
	 */
	public String getClientKey() {
		return clientKey;
	}

	/**
	 * Returns the expirationDate.
	 * @return Date
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Returns the methodName.
	 * @return String
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Returns the timeStamp.
	 * @return Date
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the bisContextDate.
	 * @param bisContextDate The bisContextDate to set
	 */
	public void setBisContextData(String bisContextData) {
		this.bisContextData = bisContextData;
	}

	/**
	 * Sets the methodName.
	 * @param methodName The methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Sets the clientKey.
	 * @param clientKey The clientKey to set
	 */
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	/**
	 * Sets the timeStamp.
	 * @param timeStamp The timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Returns the bisContextData.
	 * @return String
	 */
	public String getBisContextData() {
		return bisContextData;
	}

	/**
	 * Sets the expirationDate.
	 * @param expirationDate The expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
