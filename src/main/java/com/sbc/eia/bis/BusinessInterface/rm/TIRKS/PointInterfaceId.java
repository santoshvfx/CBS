// $Id: PointInterfaceId.java,v 1.2 2003/04/29 22:00:15 ts8181 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;


/**
 * Manages a circuit id, supplying validation and host formatting.
 * Creation date: (06/25/2002)
 * @author: Mark Liljequist
 */

/*
#   History :
#   Date      | Author        |Version	| Notes
#   ----------------------------------------------------------------------------
#   05/14/02  Mark Liljequist  4.0.8   		Creation.
#  
# 
#
*/

public class PointInterfaceId {

	public final static int POINT_INTERFACE = 1;

	protected String pointInterfaceId = null;

	private int type = 0;
	private char separator = '.';
	private int LSTCharCount8 = 8;
	private int LSTCharCount11 = 11;
	private int firstSegmentCount = 3;
	private int lastSegmentCount = 4;

/**
 * Class constructor.
 */
private PointInterfaceId() {
}
/**
 * Constructs from a string, validating and determining the type.
 * Creation date: (5/21/01 2:22:07 PM)
 * @param aCableId java.lang.String
 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CableIdException.
 */
public PointInterfaceId(String aPointInterfaceId)
	throws PointInterfaceIdException {
		
	pointInterfaceId = null;
		
	// Check the basic format.
	
	checkBasicFormat(aPointInterfaceId);

	// Determine the type.	

	if (isAPointInterfaceType(aPointInterfaceId))
		type = POINT_INTERFACE;
	else
		throw new PointInterfaceIdException(
			"Invalid/Unknown Format type for Point Interface Id: " + aPointInterfaceId);
		
	pointInterfaceId = aPointInterfaceId;
	

}
/**
 * Determines whether a string has the basic format for a PointInterface id.
 * Creation date: (06/25/2002)
 * @param aPointInterfaceId java.lang.String
 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.PointInterfaceIdException.
 */
private void checkBasicFormat(String aPointInterfaceId)
	throws PointInterfaceIdException {
		
	// There has to be something there.
	
	if (aPointInterfaceId == null || aPointInterfaceId.length() < 1)
		throw new PointInterfaceIdException("PointInterface Id cannot be null or zero length");

	// If its all separators reject it.

	int i;
	for (i = 0; i < aPointInterfaceId.length(); i++) {
		if (aPointInterfaceId.charAt(i) != separator)
			break;
	}
	if (i >= aPointInterfaceId.length())
		throw new PointInterfaceIdException("PointInterface Id cannot be all separators");
}
/**
 * Returns the FAC Type for PointInterface.
 * Creation date: (06/25/2002)
 * 
 */
public String getFacType() {
	
	// Get everything between the first and second period.
	
	int fi = pointInterfaceId.indexOf(separator, 0) + 1;
	return pointInterfaceId.substring(fi, pointInterfaceId.indexOf(separator, fi));

}
/**
 * Returns the FAC Type for PointInterface.
 * Creation date: (06/25/2002)
 * 
 */
public String getLSTType() {
	
	// Get everything up to the first period.
	
	return pointInterfaceId.substring(0, pointInterfaceId.indexOf(separator));

}
/**
 * Insert the method's description here.
 * Creation date: (3/28/02 10:24:39 AM)
 * @return java.lang.String
 */
public String getPointInterfaceId() {
	return pointInterfaceId;
}
/**
 * Returns the FAC Type for PointInterface.
 * Creation date: (06/25/2002)
 * 
 */
public String getRelayRack() {

	// Get everything after the second period.
	
	int fi = pointInterfaceId.indexOf(separator, 0) + 1;
	fi = pointInterfaceId.indexOf(separator, fi) + 1;	
	return pointInterfaceId.substring(fi);
	
}
/**
 * Returns the type.
 * Creation date: (5/21/01 2:30:55 PM)
 * @return int
 */
public int getType() {
	return type;
}
/**
 * Determines whether a string has a PointInterface format.
 * Creation date: (06/25/2002)
 * @return boolean
 * @param aPointInterfaceId java.lang.String
  * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.PointInterfaceIdException.
 */
private boolean isAPointInterfaceType(String aPointInterfaceId)
    throws PointInterfaceIdException {

    int numberOfElements = 1;
    int lastIndex = -1;
    int thisIndex = 0;

    while ((thisIndex = aPointInterfaceId.indexOf(separator, thisIndex)) > -1
        && numberOfElements < 10) {
        // Each element must have at least one character
        if ((thisIndex - lastIndex) < 2)
            return false;
        lastIndex = thisIndex++;
        numberOfElements++;
    }

    // There must be the correct number of elements.

    if ((numberOfElements < firstSegmentCount)
        || (numberOfElements > lastSegmentCount))
        return false;

    // Check the 1st element for correct number of characters.

    thisIndex = 0;
    int el;
    if (((el = aPointInterfaceId.indexOf(separator, thisIndex)) != LSTCharCount8)
        && (el != LSTCharCount11))
        return false;

    return true;
}
/**
 * Returns the original circuit id as a string 
 * Creation date: (6/6/01 11:28:30 AM)
 * @return java.lang.String
 */
public String toString() {
	return pointInterfaceId;
}
}
