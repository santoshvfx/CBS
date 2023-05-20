//$Id: CableInformation.java,v 1.7 2011/04/07 02:23:45 rs278j Exp $
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
import com.sbc.eia.idl.rm_types.ServiceOrderType;
import com.sbc.eia.idl.types.EiaDate;

/**
 * Contains design related information.
 * Creation date: (05/01/02)
 * @author: Mark Liljequist
 */
 
public class CableInformation 
{
	private String theCable;
	private String theTermA;		
	private String theTermZ;
	private String theDataCenter;
	private String theIMSRegion;
	
	private String thePair;
	private String thePendAct;
	private com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t theDueDate;
	private String theCurAct;
	private String theCkitIdClo;
	private String theFormat;

	private String theOA;
	private String theASM;
	private String theDate;
	private String theADSR;

	private String theCableACNA;
	private String theCableCCNA;
	private String theCableDataCenter;
	private String theCableIMSRegion;
	
	private int MAX_CIRCUIT_LENGTH = 46;

	/**
	 * Constructor for class CableInformation
	 */
	public CableInformation() 
	{
		super();
	
		theCableACNA = "";
		theCableCCNA = "";
		theCableDataCenter = "";
		theCableIMSRegion = "";
		theIMSRegion = "";
		
		theCable = "";
		theTermA = "";
		theTermZ = "";
		theDataCenter = "";
	
		thePair = "";
		thePendAct = "";
		theCurAct = "";
		theCkitIdClo = "";
		theFormat = "";
	
		theOA = "";
		theASM = "";
		theDate = "";
		theADSR = "";
		
	
	}
	
	/**
	 * Creation date: (8/15/2002 2:53:40 PM)
	 * @return java.lang.String
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
	 * Creation date: (8/15/2002 2:53:40 PM)
	 * @return java.lang.String
	 */
	public String formatCkt() 
	{
	
		String aCkt = null;
	
		aCkt = this.formatCircuit();
	
		if (aCkt.startsWith("S") || aCkt.startsWith("T"))
			aCkt = aCkt.substring(1);
		else
			if (aCkt.startsWith("FA"))
				aCkt = aCkt.substring(2);
				
		if (aCkt.startsWith("."))
			aCkt = aCkt.substring(1);
	
		aCkt = aCkt.trim();
		
		if (aCkt.length() > MAX_CIRCUIT_LENGTH)
			aCkt = aCkt.substring(0, MAX_CIRCUIT_LENGTH);
			
		return aCkt;
	
	}
	
	/**
	 * Creation date: (8/15/2002 2:53:40 PM)
	 * @return java.lang.String
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
	 * Creation date: (7/17/02 5:43:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getADSR() 
	{
		return theADSR;
	}
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getASM() 
	{
		return theASM;
	}
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getCable() 
	{
		return theCable;
	}
	/**
	 * Creation date: (8/15/2002 2:24:00 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCableACNA() 
	{	
		return theCableACNA;
	}
	
	/**
	 * Creation date: (8/21/2002 10:28:14 AM)
	 * @return java.lang.String
	 */
	
	public java.lang.String getCableCCNA() 
	{
		return theCableCCNA;
	}
	
	/**
	 * Creation date: (9/17/2002 12:24:14 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCableDataCenter() 
	{
		return theCableDataCenter;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	
	public java.lang.String getCkitIdClo() 
	{
		return theCkitIdClo;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getCurAct() 
	{
		return theCurAct;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getDate() 
	{
		return theDate;
	}
	
	/**
	 * Creation date: (5/13/02 1:17:43 PM)
	 * @return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t
	 */
	public com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t getDueDate() 
	{
		return theDueDate;
	}
	
	/**
	 * Creation date: (5/13/02 1:19:32 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getFormat() 
	{
		return theFormat;
	}
	
	/**
	 * Creation date: (5/10/02 12:10:20 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getDataCenter() 
	{
		return theDataCenter;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getOA() 
	{
		return theOA;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getPair() 
	{
		return thePair;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getPendAct() 
	{
		return thePendAct;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getTermA() 
	{
		return theTermA;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getTermZ() 
	{
		return theTermZ;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public String getCableIMSRegion() 
	{
		return theCableIMSRegion;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @return java.lang.String
	 */
	public String getIMSRegion() 
	{
		return theIMSRegion;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public boolean isclo() 
	{
		return false;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public boolean isWATransaction() 
	{
		return false;
	}
	
	/**
	 * Creation date: (7/17/02 5:43:21 PM)
	 * @param newADSR java.lang.String
	 */
	public void setADSR(java.lang.String newADSR) 
	{
		theADSR = newADSR;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @param newASM java.lang.String
	 */
	public void setASM(java.lang.String newASM) 
	{
		theASM = newASM;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newCable java.lang.String
	 */
	public void setCable(java.lang.String newCable) 
	{
		theCable = newCable;
	}
	
	/**
	 * Creation date: (8/15/2002 2:24:00 PM)
	 * @param newACNA java.lang.String
	 */
	public void setCableACNA(java.lang.String newACNA) 
	{
		theCableACNA = newACNA;
	}
	
	/**
	 * Creation date: (8/21/2002 10:28:14 AM)
	 * @param newCCNA java.lang.String
	 */
	public void setCableCCNA(java.lang.String newCCNA) 
	{
		theCableCCNA = newCCNA;
	}
	
	/**
	 * Creation date: (9/17/2002 12:24:14 PM)
	 * @param newCableIMSRegion java.lang.String
	 */
	public void setCableIMSRegion(java.lang.String newCableIMSRegion) 
	{
		theCableIMSRegion = newCableIMSRegion;
	}
	
	/**
	 * Creation date: (9/17/2002 12:24:14 PM)
	 * @param newCableDataCenter java.lang.String
	 */
	public void setCableDataCenter(java.lang.String newCableDataCenter) 
	{
		theCableDataCenter = newCableDataCenter;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newClo java.lang.String
	 */
	public void setCkitIdClo(String newClo) 
	{
		theCkitIdClo = newClo;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newCur_act java.lang.String
	 */
	public void setCurAct(java.lang.String newCurAct) 
	{
		theCurAct = newCurAct;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @param newDate java.lang.String
	 */
	public void setDate(java.lang.String newDate) 
	{
		theDate = newDate;
	}
	
	/**
	 * Creation date: (5/13/02 1:17:43 PM)
	 * @param newDueDate com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t
	 */
	public void setDueDate(com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t newDueDate) 
	{
		theDueDate = newDueDate;
	}
	
	/**
	 * Creation date: (5/13/02 1:19:32 PM)
	 * @param newFormat java.lang.String
	 */
	public void setFormat(java.lang.String newFormat) 
	{
		theFormat = newFormat;
	}
	
	/**
	 * Creation date: (5/10/02 12:10:20 PM)
	 * @param newIMSRegion java.lang.String
	 */
	public void setIMSRegion(java.lang.String newIMSRegion) 
	{
		theIMSRegion = newIMSRegion;
	}
	
	/**
	 * Creation date: (5/10/02 12:10:20 PM)
	 * @param newDataCenter java.lang.String
	 */
	public void setDataCenter(java.lang.String newDataCenter) 
	{
		theDataCenter = newDataCenter;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @param newOA java.lang.String
	 */
	public void setOA(java.lang.String newOA) 
	{
		theOA = newOA;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newChannel_pair java.lang.String
	 */
	public void setPair(java.lang.String newPair) 
	{
		thePair = newPair;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newPend_act java.lang.String
	 */
	public void setPendAct(java.lang.String newPendAct) 
	{
		thePendAct = newPendAct;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newTermA java.lang.String
	 */
	public void setTermA(java.lang.String newTermA) 
	{
		theTermA = newTermA;
	}
	
	/**
	 * Creation date: (5/10/02 10:43:18 AM)
	 * @param newTermZ java.lang.String
	 */
	public void setTermZ(java.lang.String newTermZ) 
	{
		theTermZ = newTermZ;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public ChannelStatus translateChannelStatus() 
	{
		return null;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public EiaDate translateDueDate() 
	{
		return null;
	}
	
	/**
	 * Creation date: (7/17/02 3:34:13 PM)
	 * @return java.lang.String
	 */
	public ServiceOrderType translateOrderType() 
	{
		return null;
	}
}
