//$Id: DataCenters.java,v 1.2 2011/04/07 03:02:34 rs278j Exp $
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

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

/**
 * Manages a set of Data Centers.
 * @author js7440
 */
public class DataCenters
{
	private String listOfDataCenters = null;
	private boolean moreDataCenters;
	private int lastDataCenterIndex;
	private char SEPARATOR = ':';
	
	/**
	 * Class constructor.
	 */
	private DataCenters() 
	{
		super();
	}
	
	/**
	 * Class constructor accepting a list of regions.
	 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
	 */
	public DataCenters(String alistOfDataCenters)
	{
		listOfDataCenters = alistOfDataCenters;
		reset();
	}
	
	/**
	 * Get the original list of regions.
	 * Creation date: (7/9/01 3:15:37 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getListOfDataCenters() 
	{
		return listOfDataCenters;
	}
	
	/**
	 * Returns the next region
	 * Creation date: (6/14/01 2:33:28 PM)
	 * @return java.lang.String
	 */
	public String getNextDataCenter()
	{
		if (listOfDataCenters == null || listOfDataCenters.length() < 1)
			return null;
			
		int thisRegionIndex;
		String thisRegion;
		
		while (lastDataCenterIndex < listOfDataCenters.length() && moreDataCenters)
		{
			// Its the last region
			if ((thisRegionIndex = listOfDataCenters.indexOf(SEPARATOR, lastDataCenterIndex)) == -1)
			{
				thisRegion = listOfDataCenters.substring(lastDataCenterIndex);
				moreDataCenters = false;
			}
			else
			{
				thisRegion = listOfDataCenters.substring(lastDataCenterIndex, thisRegionIndex);
			}
			thisRegion = thisRegion.trim();
			lastDataCenterIndex = thisRegionIndex + 1;
			if (thisRegion.length() < 1)
				continue;
			return thisRegion;
		}
		return null;
	}
	
	/**
	 * Resets to start the list again
	 * Creation date: (6/14/01 2:33:28 PM)
	 */
	public void reset()
	{
		moreDataCenters = true;
		lastDataCenterIndex = 0;
	}
}