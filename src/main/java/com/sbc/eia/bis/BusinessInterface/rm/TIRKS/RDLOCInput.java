package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

/**
 * Insert the type's description here.
 * Creation date: (5/2/02 2:55:36 PM)
 * @author: Mark Liljequist
 

#   History :
#   Date      	| Author        | Version	| Notes
#   ----------------------------------------------------------------------------
#
#   09/16/02    Mark Liljequist	6.0.0		RM64179
#											Changes for location in findFirstCLLI, findSecondCLLI.
*/
 
public class RDLOCInput {

	private String theLocation;
	private String theIMSRegion;
	private CableId theCableId;
	private CircuitId theCircuitId;
/**
 * RDLOCInput constructor comment.
 */
public RDLOCInput() {
	super();
	
	theLocation = "";
	theIMSRegion = "";
	theCableId = null;
	theCircuitId = null;
}

/**
 * RDLOCInput constructor comment.
 */

public RDLOCInput(String region, CableId cable) {
	
	this();
	
	theIMSRegion = region;
	theCableId = cable;
}

public RDLOCInput(String region, CircuitId circuit) {
	
	this();
	
	theIMSRegion = region;
	theCircuitId = circuit;
}


/**
 * Method findCircuitLocation.
 * @param loc
 * @return String
 */

public java.lang.String findCircuitLocation(int loc) {

	if (loc == 0)
		return theCircuitId.getLocA();
	if (loc == 1)
		return theCircuitId.getLocZ();

	return null;
}


/**
 * Find a cable CLLI.
 * Creation date: (9/12/2002 10:10:33 AM)
 * @return java.lang.String
 */

public java.lang.String findCableLocation(int loc) {

	if (loc == 0)
		return this.findFirstCableCLLI();
	if (loc == 1)
		return this.findSecondCableCLLI();

	return null;
}
/**
 * Find the first CLLI in the cable.
 * Creation date: (9/12/2002 10:10:33 AM)
 * @return java.lang.String
 */

private java.lang.String findFirstCableCLLI() {

	if (theCableId.getTermA().length() != 11)
		return null;

	if (theCableId.getTermZ().length() != 11)
		return theCableId.getTermA();

	String cablePrefix = theCableId.getCable().substring(0, 3);

	if (theCableId.getTermA().lastIndexOf(cablePrefix)
		== theCableId.getTermA().length() - 3)
		return theCableId.getTermA();

	return null;

}
/**
 * Find the second CLLI in the cable.
 * Creation date: (9/12/2002 10:10:33 AM)
 * @return java.lang.String
 */
private java.lang.String findSecondCableCLLI() {

	if (theCableId.getTermZ().length() != 11)
		return null;

	if (theCableId.getTermA().length() != 11)
		return theCableId.getTermZ();

	String cablePrefix = theCableId.getCable().substring(0, 3);

	if (theCableId.getTermZ().lastIndexOf(cablePrefix)
		== theCableId.getTermZ().length() - 3)
		return theCableId.getTermZ();

	return null;
}
/**
 * get the IMS region.
 * Creation date: (5/10/02 10:56:03 AM)
 * @return java.lang.String
 */
public java.lang.String getIMSRegion() {
	return theIMSRegion;
}
/**
 * Get the location.
 * Creation date: (5/10/02 10:56:03 AM)
 * @return java.lang.String
 */
public java.lang.String getLocation() {
	return theLocation;
}
/**
 * Set the cable Id.
 * Creation date: (9/12/2002 10:10:33 AM)
 * @param newDescription java.lang.String
 */
public void setCableId(CableId newCableId) {

	theCableId = newCableId;
}
/**
 * set IMS region.
 * Creation date: (5/10/02 10:56:03 AM)
 * @param newIMSRegion java.lang.String
 */
public void setIMSRegion(java.lang.String newIMSRegion) {
	theIMSRegion = newIMSRegion;
}
/**
 * Get the location.
 * Creation date: (5/10/02 10:56:03 AM)
 * @param newLocation java.lang.String
 */
public void setLocation(java.lang.String newLocation) {
	theLocation = newLocation;
}
}
