// $Id: INCircuitId.java,v 1.3 2003/03/27 01:10:01 ml2917 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Insert the type's description here.
 * Creation date: (3/28/02 10:19:09 AM)
 * @author: Mark Liljequist
 */

/* 
#
#   History :
#   Date      | Author        | Version   | Notes
#   ----------------------------------------------------------------------------
#	03/27/2002	Mark Liljequist	Creation.
#	Added for SNET circuit types.
#
#   40/29/02  Mark Liljequist  4.0.2       Modified special parse of DIN circuits.
#	In asOssString.
#
*/

public class INCircuitId extends CircuitId {

public INCircuitId(String aCircuitId) throws CircuitIdException {
    super(aCircuitId);

}
	public String asOssString()
{
	StringBuffer ossString = new StringBuffer();
	int startTranslation = 0;
	String leadCharacters = "";
	int type = getType();

	StringBuffer workCircuit = new StringBuffer(circuitId);

	 
	// For certain circuits in IN, add / if not there.
	// Do only for SERIAL and TELEPHONE circuits that begin with alpha.
	// Use all uppercase compares.
	
	String t = circuitId.toUpperCase();
	char c = t.charAt(0);
	if (type == SERIAL) {
		if (c >= 'A' && c <= 'Z') {
			leadCharacters = "/";
			startTranslation++;
 		}
	}

	int lastD = 0;
	int lastPeriod = 0;
	if (type == TELEPHONE ) {
		if (t.indexOf("DIN") > -1) {
			lastD = t.lastIndexOf('D');
			lastPeriod = t.lastIndexOf('.');
			if (lastD > lastPeriod && lastD > -1) {
				workCircuit.deleteCharAt(lastD);
			}
		}			
		if (c >= 'A' && c <= 'Z') {
			leadCharacters = "/";
			startTranslation++;
 		}
	}
	
	// Set the first two characters based on the type, plus a possible leading character
	switch (type)
	{
		case CARRIER_FACILITY:
			ossString.append("C ").append(leadCharacters); startTranslation += 2;
			break;
		case MESSAGE:
			ossString.append("M ").append(leadCharacters); startTranslation += 2;
			break;
		case SERIAL:
			// Look for the special format where a space has to replace a separator
			//	There can only one node after that pattern
			int patIndex = patternizedCkt.indexOf(SERIAL_PATTERN);
			if (patIndex != -1)
			{
				// Is there only more node?
				int searchFrom = patIndex + SERIAL_PATTERN.length();
				if (searchFrom < patternizedCkt.length() && patternizedCkt.indexOf(separator, searchFrom) == -1)
					workCircuit.setCharAt(patIndex + SERIAL_PATTERN_REPLACE_INDEX, ' ');
			}
			ossString.append("S ").append(leadCharacters); 
			startTranslation += 2;
			break;
		case TELEPHONE:
			ossString.append("T ").append(leadCharacters); startTranslation += 2;
			break;
		default:
			return null;
	}

	// Append the original input circuit, substituting all separators with '/'
	ossString.append(workCircuit.toString().replace(separator, '/'));
	
	// Substitute '//' with /
	for (int i = startTranslation; i < ossString.length(); i++)
	{
		if (ossString.charAt(i) == '/' &&
			ossString.charAt(i - 1) == '/') // Depends on i starting > 0
				ossString.deleteCharAt(i--);
		}
	
	return ossString.toString();
}

}
