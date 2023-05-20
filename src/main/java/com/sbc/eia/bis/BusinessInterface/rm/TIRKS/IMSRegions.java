// $Id: IMSRegions.java,v 1.1 2002/09/29 03:12:23 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Manages a set of IMS regions.
 * Creation date: (6/14/01 2:28:32 PM)
 * @author: Creighton Malet
 */
public class IMSRegions
{
	private String listOfRegions = null;
	private boolean moreRegions;
	private int lastRegionIndex;
	private char SEPARATOR = ':';
/**
 * Class constructor.
 */
private IMSRegions() {
	super();
}
/**
 * Class constructor accepting a list of regions.
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 */
public IMSRegions(String aListOfRegions)
{
	listOfRegions = aListOfRegions;
	reset();
}
/**
 * Get the original list of regions.
 * Creation date: (7/9/01 3:15:37 PM)
 * @return java.lang.String
 */
public java.lang.String getListOfRegions() {
	return listOfRegions;
}
/**
 * Returns the next region
 * Creation date: (6/14/01 2:33:28 PM)
 * @return java.lang.String
 */
public String getNextRegion()
{
	if (listOfRegions == null || listOfRegions.length() < 1)
		return null;
		
	int thisRegionIndex;
	String thisRegion;
	while (lastRegionIndex < listOfRegions.length() && moreRegions)
	{
		// Its the last region
		if ((thisRegionIndex = listOfRegions.indexOf(SEPARATOR, lastRegionIndex)) == -1)
		{
			thisRegion = listOfRegions.substring(lastRegionIndex);
			moreRegions = false;
		}
		else
		{
			thisRegion = listOfRegions.substring(lastRegionIndex, thisRegionIndex);
		}
		thisRegion = thisRegion.trim();
		lastRegionIndex = thisRegionIndex + 1;
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
	moreRegions = true;
	lastRegionIndex = 0;
}
}
