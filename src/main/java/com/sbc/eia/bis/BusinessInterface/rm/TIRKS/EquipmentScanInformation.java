// $Id: EquipmentScanInformation.java,v 1.4 2011/04/07 02:30:09 rs278j Exp $
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

import java.util.StringTokenizer;

import com.sbc.eia.idl.rm_types.ChannelStatus;
import com.sbc.eia.idl.types.EiaDate;

/**
 * Contains design related information.
 * Creation date: (06/25/02)
 * @author: Mark Liljequist
 */

public class EquipmentScanInformation 
{
	private String theIMSRegion;
	private String theDataCenter;
	private String theLocation;
	private String theEquipmentCode;
	private String theLevel;
	private String theRelayRack;

	private String thePendAct;
	private String theDate;
	private String theCurAct;
	private String theCkitIdClo;
	private String theAsnType;
	private String theInvStat;
	private String theUnit;

	private int MAX_CIRCUIT_LENGTH = 46;

	/**
	 * Constructor for class EquipmentScanInformation
	 */
	public EquipmentScanInformation() 
	{
		super();

		theIMSRegion = "";
		theDataCenter = "";
		theLocation = "";
		theEquipmentCode = "";
		theLevel = "";
		theRelayRack = "";

		thePendAct = "";
		theDate = "";
		theCurAct = "";
		theCkitIdClo = "";
		theAsnType = "";
		theInvStat = "";
		theUnit = "";
	}
	
	/**
	 * Formats the CircuitID
	 * @return String
	 */
	public String formatCircuit() 
	{
		String aEccCkt = null;
		StringTokenizer st = new StringTokenizer(theCkitIdClo, "/");

		if (st.countTokens() > 1) 
		{
			while (st.hasMoreTokens()) 
			{
				if (aEccCkt == null)
					aEccCkt = st.nextToken().trim();
				else
					aEccCkt = aEccCkt + "." + st.nextToken().trim();
			}

		} 
		else 
		{
			aEccCkt = theCkitIdClo;
		}
		return aEccCkt;
	}
	
	/**
	 * Format the EccCkt
	 * 
	 * @return String
	 */
	public String formatEccCkt() 
	{
		String aEccCkt = null;

		aEccCkt = this.formatCircuit();
		if (aEccCkt.length() > MAX_CIRCUIT_LENGTH)
			aEccCkt = aEccCkt.substring(0, MAX_CIRCUIT_LENGTH);

		return aEccCkt;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getAsnType() 
	{
		return theAsnType;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCkitIdClo() 
	{
		return theCkitIdClo;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCurAct() 
	{
		return theCurAct;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getDueDate() 
	{
		return theDate;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getEquipmentCode() 
	{
		return theEquipmentCode;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getInvStat() 
	{
		return theInvStat;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getLevel() 
	{
		return theLevel;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getLocation() 
	{
		return theLocation;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getPendAct() 
	{
		return thePendAct;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getRelayRack() 
	{
		return theRelayRack;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getUnit() 
	{
		return theUnit;
	}

	/**
	 * Determine if this is a WA transaction
	 * 
	 * @return boolen
	 */
	public boolean isWATransaction() 
	{
		// Determine if this is a WA transaction.

		if (getCurAct().equals("W"))
			return true;

		if (getCurAct().equals("$") && getPendAct().length() > 0)
			return true;

		return false;

	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newAsnType java.lang.String
	 */
	public void setAsnType(java.lang.String newAsnType) 
	{
		theAsnType = newAsnType;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newCkitId_Clo java.lang.String
	 */
	public void setCkitIdClo(java.lang.String newCkitIdClo) 
	{
		theCkitIdClo = newCkitIdClo;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newCur_act java.lang.String
	 */
	public void setCurAct(java.lang.String newCurAct) 
	{
		theCurAct = newCurAct;
	}
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newDate java.lang.String
	 */
	public void setDueDate(java.lang.String newDate) 
	{
		theDate = newDate;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newEqipmentCode java.lang.String
	 */
	public void setEquipmentCode(java.lang.String newEquipmentCode) 
	{
		theEquipmentCode = newEquipmentCode;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newInvStat java.lang.String
	 */
	public void setInvStat(java.lang.String newInvStat) 
	{
		theInvStat = newInvStat;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newLevel java.lang.String
	 */
	public void setLevel(java.lang.String newLevel) 
	{
		theLevel = newLevel;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newLocation java.lang.String
	 */
	public void setLocation(java.lang.String newLocation) 
	{
		theLocation = newLocation;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newPend_act java.lang.String
	 */
	public void setPendAct(java.lang.String newPendAct) 
	{
		thePendAct = newPendAct;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newRelayRack java.lang.String
	 */
	public void setRelayRack(java.lang.String newRelayRack) 
	{
		theRelayRack = newRelayRack;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newUnit java.lang.String
	 */
	public void setUnit(java.lang.String newUnit) 
	{
		theUnit = newUnit;
	}
	
	/**
	 * Creation date: (6/26/02 5:33:21 PM)
	 * @param newDate java.lang.String
	 */
	public ChannelStatus translateChannelStatus() 
	{

		if (thePendAct.equals("A")
			|| thePendAct.equals("D")
			|| thePendAct.equals("R")
			|| thePendAct.equals("H"))
			return ChannelStatus.PENDING;
		else if (theCurAct.equals("W"))
			return ChannelStatus.ASSIGNED;
		else if (theCurAct.equals("J"))
			return ChannelStatus.INVALID;
		else
			return ChannelStatus.SPARE;
	}

	/**
	* Creation date: (6/26/02 5:33:21 PM)
	* @return EiaDate
	*/
	public EiaDate translateDueDate() 
	{

		String s = getDueDate();
		String mo = s.substring(0, 2);
		String dy = s.substring(2, 4);
		String yr = s.substring(4);
		int year = Integer.parseInt(yr);
		int cc;
		if (year < 60)
			cc = year + 2000;
		else
			cc = year + 1900;
		EiaDate aDueDate =
			new EiaDate((short) cc, Short.parseShort(mo), Short.parseShort(dy));
		return aDueDate;
	}
	
	/**
	 * @return String
	 */
	public String getDataCenter() 
	{
		return theDataCenter;
	}
	
	/**
	 * @param String theDataCenter
	 */
	public void setDataCenter(String theDataCenter) 
	{
		this.theDataCenter = theDataCenter;
	}
	
	/**
	 * @return String
	 */
	public String getIMSRegion() 
	{
		return theIMSRegion;
	}
	
	/**
	 * @param String theIMSRegion
	 */
	public void setIMSRegion(String theIMSRegion) 
	{
		this.theIMSRegion = theIMSRegion;
	}
}
