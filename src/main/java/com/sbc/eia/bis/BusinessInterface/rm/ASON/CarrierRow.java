// $Id: CarrierRow.java,v 1.2 2004/12/03 18:35:53 biscvsid Exp $
//$Id: CarrierRow.java,v 1.2 2004/12/03 18:35:53 biscvsid Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 9/2001      Sam Lok         Creation.
//# 10/25/2004  Jinmin Ni       RM120184: Add CAUTH and TOS to return.

package com.sbc.eia.bis.BusinessInterface.rm.ASON;

/**
 * Insert the type's description here.
 * Creation date: (10/18/01 1:15:54 PM)
 * @author: Sanjeev Verma
 */
public class CarrierRow {
	private java.lang.String carrier_pic;
	private java.lang.String carrier_picx;
	private java.lang.String carrier_type;
	private java.lang.String res_bus_code;
	private java.lang.String interlata_cauth;
	private java.lang.String interlata_acnades;
	private java.lang.String intralata_cauth;
	private java.lang.String intralata_acnades;
	private java.lang.String rai_code;
	private boolean         isDuplicate;
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:51 PM)
 */
public CarrierRow() {

	super();
	isDuplicate = false;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getCarrier_pic() {
	return carrier_pic;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getCarrier_picx() {
	return carrier_picx;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getCarrier_type() {
	return carrier_type;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getIntralata_cauth() {
	return intralata_cauth;
}

public java.lang.String getIntralata_acnades() {
	return intralata_acnades;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getInterlata_acnades() {
	return interlata_acnades;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getInterlata_cauth() {
	return interlata_cauth;
}
/**
 * Insert the method's description here.
 * Creation date: (10/26/01 3:36:52 PM)
 * @return java.lang.String
 */
public java.lang.String getRai_code() {
	return rai_code;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @return java.lang.String
 */
public java.lang.String getRes_bus_code() {
	return res_bus_code;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newCarrier_pic java.lang.String
 */
public void setCarrier_pic(java.lang.String newCarrier_pic) {
	carrier_pic = newCarrier_pic;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newCarrier_picx java.lang.String
 */
public void setCarrier_picx(java.lang.String newCarrier_picx) {
	carrier_picx = newCarrier_picx;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newCarrier_type java.lang.String
 */
public void setCarrier_type(java.lang.String newCarrier_type) {
	carrier_type = newCarrier_type;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newInteralata_carrier_text java.lang.String
 */
public void setIntralata_cauth(java.lang.String newIntralata_cauth) {
	intralata_cauth = newIntralata_cauth;
}

public void setIntralata_acnades(java.lang.String newIntralata_acnades) {
	intralata_acnades = newIntralata_acnades;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newInterlata_carrier_text java.lang.String
 */
public void setInterlata_acnades(java.lang.String newInterlata_acnades) {
	interlata_acnades = newInterlata_acnades;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newInterlata_carrier_text java.lang.String
 */
public void setInterlata_cauth(java.lang.String newInterlata_cauth) {
	interlata_cauth = newInterlata_cauth;
}
/**
 * Insert the method's description here.
 * Creation date: (10/26/01 3:36:52 PM)
 * @param newRai_code java.lang.String
 */
public void setRai_code(java.lang.String newRai_code) {
	rai_code = newRai_code;
}
/**
 * Insert the method's description here.
 * Creation date: (10/18/01 1:25:17 PM)
 * @param newRes_bus_code java.lang.String
 */
public void setRes_bus_code(java.lang.String newRes_bus_code) {
	res_bus_code = newRes_bus_code;
}
	/**
	 * Returns the isDeplicate.
	 * @return boolean
	 */
    public boolean getIsDuplicate()
    {
        return isDuplicate;
    }
    /**
     * Sets the isDeplicate.
     * @param isDeplicate The isDeplicate to set
     */
    public void setIsDuplicate(boolean newIsDuplicate)
    {
        this.isDuplicate = newIsDuplicate;
    }


	public boolean hasSamePicAndPicx(CarrierRow compareTo)
	{
		return equals(carrier_pic, compareTo.getCarrier_pic())&& equals(carrier_picx,compareTo.getCarrier_picx());
	}
    public boolean needToCombine(CarrierRow compareTo)
    {
        if (equals(carrier_pic, compareTo.getCarrier_pic())
            && equals(carrier_picx, compareTo.getCarrier_picx())
            && equals(
                interlata_cauth,
                compareTo.getInterlata_cauth())
            && equals(
                intralata_cauth,
                compareTo.getIntralata_cauth())
            && res_bus_code != null && compareTo.getRes_bus_code() != null)
        {
            return true;
        }
        return false;
    }


    public boolean equals(String compareFrom, String compareTo)
    {
        if (compareFrom == null && compareTo == null)
        {
            return true;
        }
        else if (compareFrom != null && compareTo != null)
        {
            return compareFrom.equalsIgnoreCase(compareTo);
        }
        else
        {
            return false;
        }
    }
}
