package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.util.*;
import com.sbc.bccs.idl.helpers.*;

/**
 * Insert the type's description here.
 * Creation date: (5/2/02 2:55:21 PM)
 * @author: Mark Liljequist
 */
public class CBLSInput {

	public static final int SNET_UNIT_FORMAT = 1;
	public static final int PB_UNIT_FORMAT = 2;

	private String theFromUnit;
	private String theLastUnit;

	private CableId theCableId;
	private TN theTN;
	private String theWireCenter;
	private ArrayList theWireCenters;

	private String theIMSRegion;
	private int theUnitFormatType;

	/**
	 * CBLSInput constructor comment.
	 */
	public CBLSInput() {
		super();

		theFromUnit = "";
		theLastUnit = "";
		theIMSRegion = "";
		theCableId = null;

		theWireCenters = null;
		theWireCenter = "";
		theTN = null;
		theUnitFormatType = 0;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/02 3:54:31 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getFromUnit() {
		return theFromUnit;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/02 3:54:31 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getLastUnit() {
		return theLastUnit;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/02 3:54:31 PM)
	 * @param newFromUnit java.lang.String
	 */
	public void setFromUnit(java.lang.String newFromUnit) {
		theFromUnit = newFromUnit;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/02 3:54:31 PM)
	 * @param newLastUnit java.lang.String
	 */
	public void setLastUnit(java.lang.String newLastUnit) {
		theLastUnit = newLastUnit;
	}

	/**
	 * CBLSInput constructor comment.
	 */
	public CBLSInput(CableId aCable) {

		this();

		theCableId = aCable;

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @param newCableId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public String buildSwitchId() {

		return theCableId.getCable() + "-" + formatUnit();
	}
	
	public String buildSwitchChannelPair() {

		return formatUnit();
	}
	
	private String formatUnit() {

		String unit = theFromUnit.trim();

		if (theUnitFormatType == PB_UNIT_FORMAT) {
			if (unit.length() == 1)
				unit = "000" + unit;
			else if (unit.length() == 2)
				unit = "00" + unit;
			else if (unit.length() == 3)
				unit = "0" + unit;
			return unit;
		} else if (theUnitFormatType == SNET_UNIT_FORMAT)
			return unit;

		return unit;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public String getATerm() {

		return theCableId.getTermA();

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public String getCable() {

		return theCableId.getCable();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public CableId getCableId() {
		return theCableId;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/9/02 2:27:10 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getIMSRegion() {
		return theIMSRegion;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 1:25:40 PM)
	 * @return com.sbc.bccs.idl.helpers.TN
	 */
	public com.sbc.bccs.idl.helpers.TN getTN() {
		return theTN;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (7/18/02 3:51:06 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getWireCenter() {
		return theWireCenter;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 1:19:26 PM)
	 * @return java.util.ArrayList
	 */
	public java.util.ArrayList getWireCenters() {
		return theWireCenters;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public String getZTerm() {

		return theCableId.getTermZ();

	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/13/02 12:51:23 PM)
	 * @param newCableId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CableId
	 */
	public void setCableId(CableId newCableId) {
		theCableId = newCableId;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (5/9/02 2:27:10 PM)
	 * @param newIMSRegion java.lang.String
	 */
	public void setIMSRegion(java.lang.String newIMSRegion) {
		theIMSRegion = newIMSRegion;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 1:25:40 PM)
	 * @param newTN com.sbc.bccs.idl.helpers.TN
	 */
	public void setTN(com.sbc.bccs.idl.helpers.TN newTN) {
		theTN = newTN;
	}
	
	public void setUnitFormat(int aUnitFormat) {
		theUnitFormatType = aUnitFormat;
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (7/18/02 3:51:06 PM)
	 * @param newWireCenter java.lang.String
	 */
	public void setWireCenter(java.lang.String newWireCenter) {
		theWireCenter = newWireCenter;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (8/14/2002 1:19:26 PM)
	 * @param newWireCenters java.util.ArrayList
	 */
	public void setWireCenters(java.util.ArrayList newWireCenters) {
		theWireCenters = newWireCenters;
	}
}