// $Id: WAInput.java,v 1.3 2011/04/07 02:43:21 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################ 

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Defines input data for the Work Authorization transaction
 * Creation date: (6/25/01 11:34:33 AM)
 * @author: Creighton Malet
 */
public class WAInput 
{
	private String imsRegion = null;
	private String dataCenter = null;
	private String clo = null;
	private String cac = null;
	private CircuitId ckt = null;
	
	/**
	 * Class constructor.
	 */
	public WAInput() 
	{
	}
	
	/**
	 * Class constructor accepting data parameters.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aProperties java.util.Hashtable
	 */
	public WAInput(
			String aIMSRegion, 
			String aDataCenter, 
			String aCac, 
			String aClo, 
			CircuitId aCircuitId)
	{
		imsRegion = aIMSRegion;
		dataCenter = aDataCenter;
		cac = aCac;
		clo = aClo;
		ckt = aCircuitId;
	}
	
	/**
	 * Get the cac.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCac() 
	{
		return cac == null ? "" : cac;
	}
	
	/**
	 * Get the circuit.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 */
	public CircuitId getCkt() 
	{
		return ckt;
	}
	
	/**
	 * Get the clo.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getClo() 
	{
		return clo == null ? "" : clo;
	}
	
	/**
	 * Get the IMS region
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getDataCenter() 
	{
		return dataCenter == null ? "" : dataCenter;
	}
	
	/**
	 * Set the cac.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @param newCac java.lang.String
	 */
	public void setCac(java.lang.String newCac) 
	{
		cac = newCac;
	}
	
	/**
	 * Set the circuit.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @param newCkt com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
	 */
	public void setCkt(CircuitId newCkt) 
	{
		ckt = newCkt;
	}
	
	/**
	 * Set the clo.
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @param newClo java.lang.String
	 */
	public void setClo(java.lang.String newClo) 
	{
		clo = newClo;
	}
	/**
	 * Set the IMS region
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @param newImsRegion java.lang.String
	 */
	public void setDataCenter(java.lang.String newDataCenter) 
	{
		dataCenter = newDataCenter;
	}
	
	/**
	 * get the IMS region
	 * 
	 * @return String
	 */
	public String getImsRegion() 
	{
		return imsRegion;
	}
	
	/**
	 * Set the IMS region
	 * 
	 * @param imsRegion
	 */
	public void setImsRegion(String imsRegion) 
	{
		this.imsRegion = imsRegion;
	}
}
