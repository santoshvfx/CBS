// $Id: WAInputTIRKSJX.java,v 1.1 2011/04/05 21:46:20 rs278j Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Defines input data for the Work Authorization transaction
 * Creation date: (6/25/01 11:34:33 AM)
 * @author: Creighton Malet
 */
public class WAInputTIRKSJX 
{	
	private String dataCenter = null;
	private String clo = null;
	private String cac = null;
	private CircuitId ckt = null;
	/**
	 * Class constructor.
	 */
	public WAInputTIRKSJX() 
	{
	}
	/**
	 * Class constructor accepting data parameters.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aProperties java.util.Hashtable
	 * @return 
	 * @return 
	 */
	public WAInputTIRKSJX(String aDataCenter, String aCac, String aClo, CircuitId aCircuitId)
	{
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
	 * Get the Data Center
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
	 * Set the Data Center
	 * Creation date: (6/25/01 4:20:48 PM)
	 * @param newDataCenter java.lang.String
	 */
	public void setDataCenter(java.lang.String newDataCenter) 
	{
		dataCenter = newDataCenter;
	}
}
