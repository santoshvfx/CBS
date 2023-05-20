package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;

/**
 * Insert the type's description here.
 * Creation date: (06/25/2002)
 * @author: Mark Liljequist
 */
 
public class EQPSCInput {

	private String theUnit;
	private String theLocation;
	private String theEqipmentCode;	
	private String theIMSRegion;
	private String theLevel;
	private String theRelayRack;
	private String theFacType;

	private PointInterfaceId thePointInterfaceId;

	private String thePropertyFileName;
	private java.util.Properties theFacTypes;
	private String theTranslationTable;

	private Utility aUtility = null;

/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public EQPSCInput() {

	super();

	setLevel("S");

	thePropertyFileName = null;
	theFacTypes = null;
	theTranslationTable = null;
	thePointInterfaceId = null;
	
	theUnit = "";
	theLocation = "";
	theEqipmentCode = "";
	theIMSRegion = "";
	theRelayRack = "";
	theFacType = "";
}



/**
 * Insert the method's description here.
 * Creation date: (7/26/02 1:02:01 PM)
 * @return java.lang.String
 */
public java.lang.String getFacType() {
	return theFacType;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getIMSRegion() {
	return theIMSRegion;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getLevel() {
	return theLevel;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getLocation() {
	return theLocation;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.PointInterfaceId
 */
public PointInterfaceId getPointInterfaceId() {
	return thePointInterfaceId;
}
	/**
 * Insert the method's description here.
 * Creation date: (6/26/02 5:06:27 PM)
 * @return java.lang.String
 */
public java.lang.String getPropertyFileName() {

	return thePropertyFileName;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getRelayRack() {

	return theRelayRack;

}
	/**
 * Insert the method's description here.
 * Creation date: (6/26/02 5:06:43 PM)
 * @return java.lang.String
 */
public String getTranslationTable() {
	return theTranslationTable;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String getUnit() {
	return theUnit;
}

/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newEqipmentCode java.lang.String
 */
public void setEqipmentCode(java.lang.String newEqipmentCode) {
	theEqipmentCode = newEqipmentCode;
}
/**
 * Insert the method's description here.
 * Creation date: (7/26/02 1:02:01 PM)
 * @param newFacType java.lang.String
 */
public void setFacType(java.lang.String newFacType) {
	theFacType = newFacType;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newIMSRegion java.lang.String
 */
public void setIMSRegion(java.lang.String newIMSRegion) {
	theIMSRegion = newIMSRegion;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newLevel java.lang.String
 */
public void setLevel(java.lang.String newLevel) {
	theLevel = newLevel;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newLocation java.lang.String
 */
public void setLocation(java.lang.String newLocation) {
	theLocation = newLocation;
}

	/**
 * Insert the method's description here.
 * Creation date: (6/26/02 5:06:27 PM)
 * @param newPropertyFileName java.lang.String
 */
public void setPropertyFileName(java.lang.String newPropertyFileName) {

	// This will set the property file name for correct region.
	
	thePropertyFileName = newPropertyFileName;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newRelayRack java.lang.String
 */
public void setRelayRack(java.lang.String newRelayRack) {
	theRelayRack = newRelayRack;
}
/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newUnit java.lang.String
 */
public void setUnit(java.lang.String newUnit) {
	
	theUnit = newUnit;
	
}


/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public EQPSCInput(PointInterfaceId aInterface, Utility aUtility) {

	this();
	
	this.aUtility = aUtility;

	thePointInterfaceId = aInterface;

	parsePointInterfaceId(thePointInterfaceId);
}

/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @return java.lang.String
 */
public java.lang.String formatRelayRack() {


	return theRelayRack;
}

/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newUnit java.lang.String
 */
public String formatUnit() {
	
	return theUnit;
	
}

/**
* Returns the Eqipment Code and FAC Types for PointInterface.
* Creation date: (06/25/2002)
* 
*/
public String loadTranslationTable() {

	// This will load the table for EQUIPMENT_CODE_FAC_TYPE.

	if (thePropertyFileName == null) {
		return null;
	}
	try {
		theFacTypes = PropertiesFileLoader.read( thePropertyFileName, aUtility);

		StringBuffer sb = new StringBuffer();

		Set set = theFacTypes.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			sb.append("Key:<" + entry.getKey() + ">Value:<" + entry.getValue() + ">");
		}

		theTranslationTable = sb.toString();
	} catch (Exception e) {
		return null;
	}

	return theTranslationTable;
}

/**
 * Insert the method's description here.
 * Creation date: (6/26/02 3:09:22 PM)
 * @param newPointInterfaceId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.PointInterfaceId
 */
public void parsePointInterfaceId(PointInterfaceId newPointInterfaceId) {
	
	thePointInterfaceId = newPointInterfaceId;

	setRelayRack(thePointInterfaceId.getRelayRack());
	setLocation(thePointInterfaceId.getLSTType());
	setFacType(thePointInterfaceId.getFacType());
}

/**
* Returns the Equipemnt Code for the FAC Type for PointInterfaceId.
* Creation date: (06/25/2002)
* 
*/

public String translateEquipmentCode(int facNumber) {

	// Check the fac types for the correct equipment code.

	String t;
	Set set = theFacTypes.entrySet();
	int facCount = 1;
	Iterator it = set.iterator();

	while (it.hasNext()) {
		Map.Entry entry = (Map.Entry) it.next();
		t = entry.getValue().toString();
		if ((t.compareTo(theFacType) == 0) && facNumber == facCount) {
			return entry.getKey().toString();
		}
		if ((t.compareTo(theFacType) == 0)) {
			facCount++;
		}
	}

	return null;

}

/**
* Returns the Equipemnt Code for the FAC Type for PointInterfaceId.
* Creation date: (06/25/2002)
* 
*/

public String translateEquipmentCode(String facType, int facNumber) {

	// Check the fac types for the correct equipment code.

	String t;
	Set set = theFacTypes.entrySet();
	int facCount = 1;
	Iterator it = set.iterator();

	while (it.hasNext()) {
		Map.Entry entry = (Map.Entry) it.next();
		t = entry.getValue().toString();
		if ((t.compareTo(facType) == 0) && facNumber == facCount) {
			return entry.getKey().toString();
		}
		if ((t.compareTo(facType) == 0)) {
			facCount++;
		}
	}

	return null;

}
}