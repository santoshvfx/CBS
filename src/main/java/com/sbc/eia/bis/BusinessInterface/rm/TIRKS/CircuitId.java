// $Id: CircuitId.java,v 1.5 2003/11/18 23:33:02 vc7563 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Manages a circuit id, supplying validation and host formatting.
 * Creation date: (5/21/01 2:18:34 PM)
 * @author: Creighton Malet
 */

/*
#   History :
#   Date      | Author        | Notes
#   ----------------------------------------------------------------------------
#   03/14/02  M Liljequist     Fixed validation for CILLI.
#   In isAMessageType and isACarrierFacility.
#   Added getCircuitId.
# 	02/27/2003	Tanuja Singh	RM 81005 - change for format .NN.AAAA.NNNNNN..AA
#								for SWBT/PB/NB/AIT
#								If the circuit starts with a period and the 
#								character following the period is numeric, remove 
#								the period or slash before sending to TIRKS.  
#								Example: .41.AIDT.010046..SW convert to 41/AIDT/010046/SW
*/

public class CircuitId {
	public final static int SERIAL = 1;
	public final static int TELEPHONE = 2;
	public final static int CARRIER_FACILITY = 3;
	public final static int MESSAGE = 4;
	public final static String TN_PATTERN = "000.000.0000";
	public final static String SERIAL_PATTERN = ".000000.000.";
	public final static int SERIAL_PATTERN_REPLACE_INDEX = 7;
	protected String circuitId = null;
	protected String patternizedCkt = null;
	private int type = 0;
	protected char separator = '.';
	protected boolean old_serial_format = false;
	/**
	 * Class constructor.
	 */
	private CircuitId() {
	}
	/**
	 * Constructs from a string, validating and determining the type.
	 * Creation date: (5/21/01 2:22:07 PM)
	 * @param aCircuitId java.lang.String
	 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitIdException: The input string circuit id pattern was unrecognized.
	 */
	public CircuitId(String aCircuitId) throws CircuitIdException {
		// Check the basic format
		checkBasicFormat(aCircuitId);

		// Patternize the circuit
		patternizedCkt = patternize(aCircuitId);

		// Determine the type	
		// Make sure asOssString() is updated for new types
		if (isACarrierFacilityType(aCircuitId))
			type = CARRIER_FACILITY;
		else if (isAMessageType(aCircuitId))
			type = MESSAGE;
		else if (isATelephoneNumberType(aCircuitId))
			type = TELEPHONE;
		else if (isASerialNumberType(aCircuitId))
			type = SERIAL;
		else
			throw new CircuitIdException(
				"Invalid/Unknown Format for Circuit Id: " + aCircuitId);

		circuitId = aCircuitId;
	}
	/**
	 * Returns the circuit id, formatted for the host with the appropriate
	 *	type letter prefixed.
	 * Creation date: (5/21/01 2:34:14 PM)
	 * @return java.lang.String
	 */
	public String asOssString() {
		StringBuffer ossString = new StringBuffer();
		int startTranslation = 0;
		String leadCharacters = "";
		StringBuffer workCircuit = new StringBuffer(circuitId);

		// If the circuit starts with an alphabetic it needs a leading '/  '
		char ch = circuitId.charAt(0);
		if ((type == SERIAL || type == TELEPHONE)
			&& ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
			leadCharacters = "/";
			startTranslation++;
		}

		// Set the first two characters based on the type, plus a possible leading character
		switch (type) {
			case CARRIER_FACILITY :
				ossString.append("C ").append(leadCharacters);
				startTranslation += 2;
				break;
			case MESSAGE :
				ossString.append("M ").append(leadCharacters);
				startTranslation += 2;
				break;
			case SERIAL :
				// Look for the special format where a space has to replace a separator
				//	There can only one node after that pattern
				int patIndex = patternizedCkt.indexOf(SERIAL_PATTERN);
				if (patIndex != -1) {
					// Is there only more node?
					int searchFrom = patIndex + SERIAL_PATTERN.length();
					if (searchFrom < patternizedCkt.length()
						&& patternizedCkt.indexOf(separator, searchFrom) == -1)
						workCircuit.setCharAt(
							patIndex + SERIAL_PATTERN_REPLACE_INDEX,
							' ');
				}
				ossString.append("S ").append(leadCharacters);
				startTranslation += 2;
				break;
			case TELEPHONE :
				ossString.append("T ").append(leadCharacters);
				startTranslation += 2;
				break;
			default :
				return null;
		}

		// Append the original input circuit, substituting all separators with '/'
		ossString.append(workCircuit.toString().replace(separator, '/'));

		// Substitute '//' with /
		for (int i = startTranslation; i < ossString.length(); i++) {
			if (ossString.charAt(i) == '/'
				&& ossString.charAt(i - 1) == '/') // Depends on i starting > 0
				ossString.deleteCharAt(i--);
		}

		// After translation, if cktid starts with a "/", and the next character is numeric, 
		// remove the "/" before sending to TIRKS
		char thirdChar = ossString.charAt(2);
		char fourthChar = ossString.charAt(3);
		if ((type == SERIAL || type == TELEPHONE)
			&& (thirdChar == '/' && Character.isDigit(fourthChar)))
			ossString.deleteCharAt(2);

		return ossString.toString();
	}
	/**
	 * Determines whether a string has the basic format for a circuit id.
	 * Creation date: (5/22/01 10:37:04 AM)
	 * @param aCircuitId java.lang.String
	 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitIdException: The input string circuit id pattern was unrecognized.
	 */
	private void checkBasicFormat(String aCircuitId)
		throws CircuitIdException {
		// There has to be something there
		if (aCircuitId == null || aCircuitId.length() < 1)
			throw new CircuitIdException("Circuit Id cannot be null or zero length");

		// If its all separators reject it
		int i;
		for (i = 0; i < aCircuitId.length(); i++) {
			if (aCircuitId.charAt(i) != separator)
				break;
		}
		if (i >= aCircuitId.length())
			throw new CircuitIdException("Circuit Id cannot be all separators");
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/28/02 10:24:39 AM)
	 * @return java.lang.String
	 */
	public String getCircuitId() {
		return circuitId;
	}

	/**
	 * Returns the LOCA CLLI code for a circuit type of CFA or Message.
	 * Creation date: (5/22/01 11:32:52 AM)
	 * @return java.lang.String
	 */
	public String getLocA() {
		int lastIndex = circuitId.lastIndexOf(separator);
		switch (type) {
			case CARRIER_FACILITY :
				return circuitId.substring(
					circuitId.lastIndexOf(separator, lastIndex - 1) + 1,
					lastIndex);

			case MESSAGE :
				lastIndex = circuitId.lastIndexOf(separator, lastIndex - 1);
				return circuitId.substring(
					circuitId.lastIndexOf(separator, lastIndex - 1) + 1,
					lastIndex);

			default :
				return null;
		}
	}
	/**
	 * Returns the LOCZ CLLI code for a circuit type of CFA or Message.
	 * Creation date: (5/22/01 11:32:52 AM)
	 * @return java.lang.String
	 */
	public String getLocZ() {
		if (type != CARRIER_FACILITY && type != MESSAGE)
			return null;

		return circuitId.substring(circuitId.lastIndexOf(separator) + 1);
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
	 * Determines whether a string has a carrier facility circuit id format.
	 * Creation date: (5/22/01 10:37:04 AM)
	 * @return boolean
	 * @param aCircuitId java.lang.String
	  * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitIdException: The input string circuit id pattern was invalid.
	 */
	private boolean isACarrierFacilityType(String aCircuitId)
		throws CircuitIdException {
		int numberOfElements = 1;
		int secondLastIndex = -1;
		int lastIndex = -1;
		int thisIndex = 0;
		boolean emptyNode = false;

		while ((thisIndex = aCircuitId.indexOf(separator, thisIndex)) > -1
			&& numberOfElements < 5) {
			// Each element must have at least one character
			if ((thisIndex - lastIndex) < 2)
				emptyNode = true;
			secondLastIndex = lastIndex;
			lastIndex = thisIndex++;
			numberOfElements++;
		}

		// There must be 4 elements
		if (numberOfElements != 4)
			return false;

		// Check the 3rd and 4th elements are 8 or 11 characters
		int el;
		if ((el = lastIndex - secondLastIndex - 1) != 8 && el != 11)
			return false;
		if ((el = aCircuitId.length() - lastIndex - 1) != 8 && el != 11)
			return false;

		// Now its a CFA type - check for empty fields
		//	(an emptyNode check for the last element wasn't done in the loop above
		//	but it is known to be from 8 to 11 characters to qualify as a CFA)
		if (emptyNode)
			throw new CircuitIdException(
				"Invalid Format for CARRIER FACILITY (CFA) type Circuit Id: "
					+ aCircuitId);

		return true;
	}
	/**
	 * Determines whether a string has a message circuit id format.
	 * Creation date: (5/22/01 10:37:04 AM)
	 * @return boolean
	 * @param aCircuitId java.lang.String
	 * @exception com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitIdException: The input string circuit id pattern was invalid.
	 */
	private boolean isAMessageType(String aCircuitId)
		throws CircuitIdException {
		int numberOfElements = 1;
		int thirdLastIndex = -1;
		int secondLastIndex = -1;
		int lastIndex = -1;
		int thisIndex = 0;
		boolean emptyNode = false;

		while ((thisIndex = aCircuitId.indexOf(separator, thisIndex)) > -1
			&& numberOfElements < 6) {
			// Each element must have at least one character
			if ((thisIndex - lastIndex) < 2)
				emptyNode = true;
			thirdLastIndex = secondLastIndex;
			secondLastIndex = lastIndex;
			lastIndex = thisIndex++;
			numberOfElements++;
		}

		// There must be 5 elements
		if (numberOfElements != 5)
			return false;

		// Check the 3rd and 5th elements are 8 or 11 characters
		if ((secondLastIndex - thirdLastIndex - 1) != 8
			&& (secondLastIndex - thirdLastIndex - 1) != 11)
			return false;
		if ((aCircuitId.length() - lastIndex - 1) != 8
			&& (aCircuitId.length() - lastIndex - 1) != 11)
			return false;

		// Now its a Message type - check for empty fields
		//	(an emptyNode check for the last element wasn't done in the loop above
		//	but it is known to be 11 characters to qualify as a Message)
		if (emptyNode)
			throw new CircuitIdException(
				"Invalid Format for MESSAGE type Circuit Id: " + aCircuitId);

		return true;
	}
	/**
	 * Determines whether a string has a serial number circuit id format.
	 * Creation date: (5/22/01 10:37:04 AM)
	 * @return boolean
	 * @param aCircuitId java.lang.String
	 */
	private boolean isASerialNumberType(String aCircuitId) {
		// This is the default
		return true;
	}
	/**
	 * Determines whether a string has a telephone number type circuit id format.
	 * Creation date: (5/22/01 10:37:04 AM)
	 * @return boolean
	 * @param aCircuitId java.lang.String
	 */
	private boolean isATelephoneNumberType(String aCircuitId) {
		if (patternizedCkt.indexOf(TN_PATTERN) == -1)
			return false;

		return true;
	}
	/**
	 * Converts a string into a pattern for the match method. 
	 * Creation date: (5/21/01 3:04:37 PM)
	 * @return java.lang.String
	 * @param aString java.lang.String
	 */
	private String patternize(String aString) {
		if (aString == null)
			return null;

		StringBuffer work = new StringBuffer(aString);
		char ch;
		for (int i = 0; i < work.length(); i++) {
			ch = work.charAt(i);
			if (ch >= 'a' && ch <= 'z')
				work.setCharAt(i, 'a');
			else if (ch >= 'A' && ch <= 'Z')
				work.setCharAt(i, 'A');
			else if (ch >= '0' && ch <= '9')
				work.setCharAt(i, '0');
		}
		return work.toString();
	}
	/**
	 * Returns the original circuit id as a string 
	 * Creation date: (6/6/01 11:28:30 AM)
	 * @return java.lang.String
	 */
	public String toString() {
		return circuitId;
	}
	
	protected void setCkt(String cktId) {
		circuitId = cktId;
	}
}
