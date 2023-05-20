// $Id: CableId.java,v 1.3 2003/04/30 17:18:55 ts8181 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Manages a circuit id, supplying validation and host formatting.
 * Creation date: (5/21/01 2:18:34 PM)
 * @author: Mark Liljequist
 */
 
/*
#   History :
#   Date      | Author        | Notes
#   ----------------------------------------------------------------------------
#   05/14/02  M Liljequist     Creation.
#  
# 
#
*/
 
public class CableId {
	
	public final static int CABLE = 1;
	protected String cableId = null;
	private int type = 0;
	protected char separator = '.';

/**
 * Class constructor.
 */
private CableId() {
}
/**
 * Constructs from a string, validating and determining the type.
 * Creation date: (5/21/01 2:22:07 PM)
 * @param aCableId java.lang.String
 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CableIdException.
 */
public CableId(String aCableId)
	throws CableIdException
{
	// Check the basic format
	checkBasicFormat(aCableId);

	// Determine the type	
	// 
	
	if (isACableType(aCableId))
		type = CABLE;
	else
		throw new CableIdException("Invalid/Unknown Format type for Cable Id: " + aCableId);
	
	cableId = aCableId;
}
/**
 * Determines whether a string has the basic format for a Cable id.
 * Creation date: (5/22/01 10:37:04 AM)
 * @param aCableId java.lang.String
 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CableIdException.
 */
private void checkBasicFormat(String aCableId)
	throws CableIdException
{
	// There has to be something there
	if (aCableId == null || aCableId.length() < 1)
		throw new CableIdException("Cable Id cannot be null or zero length");

	// If its all separators reject it
	int i;
	for (i = 0; i < aCableId.length(); i++)
	{
		if (aCableId.charAt(i) != separator)
			break;
	}
	if (i >= aCableId.length())
		throw new CableIdException("Cable Id cannot be all separators");
}
/**
 * Returns the LOCZ CLLI code for a cable type of CFA or Message.
 * Creation date: (5/22/01 11:32:52 AM)
 * @return java.lang.String
 */
public String getCable()
{
	if (type != CABLE)
		return null;

	return cableId.substring(0, cableId.indexOf(separator));
}
/**
 * Insert the method's description here.
 * Creation date: (3/28/02 10:24:39 AM)
 * @return java.lang.String
 */
public String getCableId() {
	return cableId;
}
/**
 * Returns the LOCA for Cable.
 * Creation date: (5/22/01 11:32:52 AM)
 * @return java.lang.String
 */
public String getTermA() {

    if (type != CABLE)
        return null;
        
    int lastIndex = cableId.lastIndexOf(separator);

    return cableId.substring(
        cableId.lastIndexOf(separator, lastIndex - 1) + 1,
        lastIndex);

}
/**
 * Returns the LOCZ CLLI code for a cable type of CFA or Message.
 * Creation date: (5/22/01 11:32:52 AM)
 * @return java.lang.String
 */
public String getTermZ()
{
	if (type != CABLE)
		return null;

	return cableId.substring(cableId.lastIndexOf(separator) + 1);
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
 * Determines whether a string has a cable format.
 * Creation date: (5/22/01 10:37:04 AM)
 * @return boolean
 * @param acableId java.lang.String
  * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.cableIdException.
 */
private boolean isACableType(String aCableId)
	throws CableIdException
{
	int numberOfElements = 1;
	int secondLastIndex = -1;
	int lastIndex = -1;
	int thisIndex = 0;

	while ((thisIndex = aCableId.indexOf(separator, thisIndex)) > -1 && numberOfElements < 10)
	{
		// Each element must have at least one character
		if ((thisIndex - lastIndex) < 2)
			return false;
		secondLastIndex = lastIndex;
		lastIndex = thisIndex++;
		numberOfElements++;
	}

	// There must be 3 elements.
	
	if (numberOfElements != 3)
		return false;

	// Check the 2rd and 3th elements are 8 or 11 characters.
	
	int el;
	if ((el = lastIndex - secondLastIndex - 1) != 8
		&& el != 11)
		return false;
	if ((el = aCableId.length() - lastIndex - 1) != 8 &&
		el != 11)
		return false;

	
	return true;
}
/**
 * Returns the original circuit id as a string 
 * Creation date: (6/6/01 11:28:30 AM)
 * @return java.lang.String
 */
public String toString() {
	return cableId;
}
}
