//$Id: PersistenceData.java,v 1.1 2005/05/23 18:00:07 jn1936 Exp $

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
//# 05/16/2005   Jinmin Ni      Creation.


package com.sbc.eia.bis.persistencemanager;

import java.util.Date;

import com.sbc.eia.idl.bis_types.BisContext;

/**
 * @author jn1936
 */
public class PersistenceData 
{
	BisContext aContext;
	Date timeStamp;
	
	/**
	 * @see java.lang.Object#Object()
	 */
	public PersistenceData()
	{
		
	}
	/**
	 * Method PersistenceData.
	 * @param aContext
	 * @param timeStamp
	 */
	public PersistenceData(BisContext aContext,Date timeStamp)
	{
		this.aContext = aContext;
		this.timeStamp = timeStamp;	
	}
	/**
	 * Returns the aContext.
	 * @return BisContext
	 */
	public BisContext getBisContext() {
		return aContext;
	}

	/**
	 * Returns the timeStamp.
	 * @return Date
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the aContext.
	 * @param aContext The aContext to set
	 */
	public void setBisContext(BisContext aContext) {
		this.aContext = aContext;
	}

	/**
	 * Sets the timeStamp.
	 * @param timeStamp The timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
