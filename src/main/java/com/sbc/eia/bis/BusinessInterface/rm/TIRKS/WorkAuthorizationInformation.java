//$Id: WorkAuthorizationInformation.java,v 1.7 2011/04/07 02:46:14 rs278j Exp $
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

import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;

/**
 * @author ml2917
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
/**
 * Contains work authorization information.
 * Creation date: (5/23/01 3:21:06 PM)
 * @author: Creighton Malet

#   History :
#   Date       | Author        | Version   | Notes
#   ----------------------------------------------------------------------------
#	09/16/02	Mark Liljequist	6.0.0		RM64179
#											Added mcn field.
#	07/10/2007	Prasad Ganji			 	Add CVS Id keyword on line 1
#	
#		
*/

public class WorkAuthorizationInformation 
{
	private String acna = "";
	private String ccna = "";
	private String ckr = "";
	private String ckt = "";
	private String clo = "";
	private String loca = "";
	private String locz = "";
	private String ord = "";
	private String pon = "";
	private String mcn = "";
	private String dataCenter = "";
	private String imsRegion = "";
	private String btn = "";
	private String serviceType = null;
	private Exception exception = null;

	private static int MAX_CKR_CIRCUIT_LENGTH = 41;

	private static String[] UNE_BTN =
		{ "172", "173", "272", "273", "372", "373" };

	private static String ACCESS_BTN = "0";

	/**
	 * Class constructor.
	 */
	public WorkAuthorizationInformation() 
	{
		super();
	}

	/**
	 * Format the circuit..
	 * Creation date: (8/15/2002 2:53:40 PM)
	 * @return java.lang.String
	 */

	public String formatCkr() 
	{
		if (ckr.length() > MAX_CKR_CIRCUIT_LENGTH)
			return ckr.substring(0, MAX_CKR_CIRCUIT_LENGTH);

		return ckr;
	}

	/**
	* Method determineServiceType.
	*/
	public void determineServiceType() 
	{
		try 
		{
			for (int i = 0; i < UNE_BTN.length; i++) 
			{
				if (btn.startsWith(UNE_BTN[i])) 
				{
					serviceType = ServiceTypeHandleObjectKey.UNE;
					return;
				}
			}
			if (btn.startsWith(ACCESS_BTN)) 
			{
				serviceType = ServiceTypeHandleObjectKey.ACCESS;
				return;
			}

		} 
		catch (NullPointerException e) 
		{
		} 
		catch (IndexOutOfBoundsException e) 
		{
		}
	}

	/**
	 * @return String
	 * @throws NullDataException
	 */
	public String getServiceType() 
		throws 
			NullDataException 
	{
		if (serviceType == null) 
		{
			throw new NullDataException(
				"1",
				"No btn data to determine service type in WA");
		}
		return serviceType;
	}
	
	/**
	 * Get the acna.
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getAcna() 
	{
		return acna;
	}

	/**
	 * Method getBtn.
	 * @return String
	 */
	public java.lang.String getBtn() 
	{
		return btn;
	}

	/**
	 * Get the ccna.
	 * Creation date: (6/26/01 1:33:18 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getCcna() 
	{
		return ccna;
	}
	
	/**
	 * Get the ckr.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getCkr() 
	{
		return ckr;
	}
	
	/**
	 * Get the ckt.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getCkt() 
	{
		return ckt;
	}
	
	/**
	 * Get the clo.
	 * Creation date: (6/27/01 4:38:06 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getClo() 
	{
		return clo;
	}
	
	/**
	 * Get the exception.
	 * Creation date: (6/26/01 9:45:50 AM)
	 * @return java.lang.Exception
	 */
	public java.lang.Exception getException() 
	{
		return exception;
	}
	
	/**
	 * Get the IMSRegion.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getDataCenter() 
	{
		return dataCenter;
	}
	
	/**
	 * Get the loca
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getLoca() 
	{
		return loca;
	}
	
	/**
	 * Get the locz.
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getLocz() 
	{
		return locz;
	}
	
	/**
	 * Creation date: (9/16/2002 4:35:42 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getMcn() 
	{
		return mcn;
	}
	
	/**
	 * Get the ord.
	 * Creation date: (7/23/01 9:24:45 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getOrd() 
	{
		return ord;
	}
	
	/**
	 * Get the pon.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getPon() 
	{
		return pon;
	}
	
	/**
	 * Set the acna.
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @param newAcna java.lang.String
	 */
	public void setAcna(java.lang.String newAcna) 
	{
		acna = newAcna;
	}

	/**
	 * Method setbtn.
	 * @param newBtn
	 */
	public void setBtn(java.lang.String newBtn) 
	{
		btn = newBtn;
	}

	/**
	 * Set the ccna.
	 * Creation date: (6/26/01 1:33:18 PM)
	 * @param newCcna java.lang.String
	 */
	public void setCcna(java.lang.String newCcna) 
	{
		ccna = newCcna;
	}
	
	/**
	 * Set the ckr.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @param newCkr java.lang.String
	 */
	public void setCkr(java.lang.String newCkr) 
	{
		ckr = newCkr;
	}
	
	/**
	 * Set the ckt.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @param newCkt java.lang.String
	 */
	public void setCkt(java.lang.String newCkt) 
	{
		ckt = newCkt;
	}
	
	/**
	 * Set the clo.
	 * Creation date: (6/27/01 4:38:06 PM)
	 * @param newClo java.lang.String
	 */
	public void setClo(java.lang.String newClo) 
	{
		clo = newClo;
	}
	
	/**
	 * Set the exception.
	 * Creation date: (6/26/01 9:45:50 AM)
	 * @param newException java.lang.Exception
	 */
	public void setException(java.lang.Exception newException) 
	{
		exception = newException;
	}
	
	/**
	 * Set the IMSRegion.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @param newIMSRegion java.lang.String
	 */
	public void setDataCenter(java.lang.String newDataCenter) 
	{
		dataCenter = newDataCenter;
	}
	
	/**
	 * Set the loca.
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @param newLoca java.lang.String
	 */
	public void setLoca(java.lang.String newLoca) 
	{
		loca = newLoca;
	}
	
	/**
	 * Set the locz.
	 * Creation date: (5/23/01 5:16:31 PM)
	 * @param newLocz java.lang.String
	 */
	public void setLocz(java.lang.String newLocz) 
	{
		locz = newLocz;
	}
	
	/**
	 * Creation date: (9/16/2002 4:35:42 PM)
	 * @param newMcn java.lang.String
	 */
	public void setMcn(java.lang.String newMcn) 
	{
		mcn = newMcn;
	}
	
	/**
	 * Set the ord.
	 * Creation date: (7/23/01 9:24:45 AM)
	 * @param newOrd java.lang.String
	 */
	public void setOrd(java.lang.String newOrd) 
	{
		ord = newOrd;
	}
	
	/**
	 * Set the pon.
	 * Creation date: (6/14/01 10:05:09 AM)
	 * @param newPon java.lang.String
	 */
	public void setPon(java.lang.String newPon) 
	{
		pon = newPon;
	}

	/**
	 * @return String
	 */
	public String getIMSRegion() 
	{
		return imsRegion;
	}

	/**
	 * @param String imsRegion
	 */
	public void setIMSRegion(String imsRegion) 
	{
		this.imsRegion = imsRegion;
	}
}
