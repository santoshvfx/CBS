// $Id: SNETCircuitId.java,v 1.11 2003/11/18 23:33:02 vc7563 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.util.StringTokenizer;
/**
 * Insert the type's description here.
 * Creation date: (3/28/02 10:19:09 AM)
 * @author: Mark Liljequist
 */

/* 
#
#   History :
#   Date      | Author        | Notes
#   ----------------------------------------------------------------------------
#	30/27/2002	Mark Liljequist	Creation.
#	Added for SNET circuit types.
# 	02/27/2003	Tanuja Singh	RM 81005 - change for format .AAAA.NNN.NNN.NNNN.ANNNN. in SNET
#								Precede a Serial Number or Telephone Number circuit with "FA" 
#								when circuit starts with a period, otherwise, precede with "FA/", 
#								if not already there 
#	03/26/03	Tanuja Singh	DR 68670 - Added a method formatEccCkt()
#	05/20/03	Tanuja Singh	RM 95828 - Added a method extraFormatForSNET()
#	11/10/03	Vickie Ng		DR 96877 - Fixed NullPointerException (CFA) when 
#									       subset of WA screens encountered TIRKS_ERROR
#										   Remove getTypeOfCircuit()
*/

public class SNETCircuitId extends CircuitId {
	/**
	 * SNETCircuitId constructor comment.
	 * @param aCircuitId java.lang.String
	 * @exception com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitIdException The exception description.
	 */
	public SNETCircuitId(String aCircuitId) throws CircuitIdException {
		super(aCircuitId);
		

	}
	public String asOssString() {
		StringBuffer ossString = new StringBuffer();
		int startTranslation = 0;
		String leadCharacters = "";
		int type = getType();

		StringBuffer workCircuit = new StringBuffer(circuitId);

		// For certain circuits in SNET, add an FA/ if not there.
		// Do only for circuits that begin with alpha.

		String t = circuitId.toUpperCase();
		char c = t.charAt(0);
		if (type == TELEPHONE || type == SERIAL) {
			if (!t.startsWith("FA.")) {
				if (c == '.') {
					leadCharacters = "FA";
					startTranslation += 2;
				} else {
					leadCharacters = "FA/";
					startTranslation += 3;
				}
			}
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

		return ossString.toString();
	}

	/**
	 * Returns the formatted EccCkt
	 * Creation date: (3/26/03 2:34:14 PM)
	 * @return java.lang.String
	 */
	public String formatEccCkt(String aEccCkt) {
		int type = getType();
		String t = aEccCkt.toUpperCase().trim();
		if (type == TELEPHONE || type == SERIAL) {
			if (t.indexOf("FA/") == 2)
				t = t.substring(0, 2) + t.substring(5);
		}
		return t;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/8/02 2:11:02 PM)
	 * @return String
	 */
	public String extraFormatForSNET() {
		if (getType() == TELEPHONE) {
			// RM 95828   --- change done for SNET TELEPHONE circuits 
			String lastToken = null;
			String firstToken = null;
			boolean isEndWithDot = circuitId.endsWith(".");

			StringTokenizer st = new StringTokenizer(circuitId, ".");
			if (st.countTokens() > 1) {
				while (st.hasMoreTokens()) {
					lastToken = st.nextToken().trim();
					if (firstToken == null) {
						firstToken = lastToken;
					} else
						firstToken = firstToken + "." + lastToken;
				}
			}
			int index = firstToken.indexOf("." + lastToken);
			firstToken = firstToken.substring(0, index);
			char firstChar = lastToken.charAt(0);
			char secondChar = lastToken.charAt(1);
			int integer = 0;
			if ((Character.isLetter(firstChar)
				&& Character.isDigit(secondChar))) {
				lastToken = lastToken.substring(1);
				integer = Integer.parseInt(lastToken.toString());
				firstToken =
					firstToken + "." + firstChar + String.valueOf(integer);
				if (isEndWithDot)
					firstToken = firstToken + ".";
				return firstToken;
			} 
		} 
		return circuitId;
	}

	public void setSNETCkt(String cktId) {
		setCkt(cktId);
	}
}
