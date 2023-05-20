// $Id: CblsQTIRKSJXInput.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

/**
 * Class      : CblsQTIRKSJXInput
 * Description: Class used for handling the TIRKSJX CBLS screen request input.
 * 
 * @author js7440
 */
final public class CblsQTIRKSJXInput 
	implements java.io.Serializable 
{ 
	public String data_center;
	public String ims_region;
	public String term_A;
	public String term_Z;
	public String cable;
	public String from_unit;
	public String last_unit;

    /**
     * Constructor: CblsQTIRKSJXInput
     * 
     * @author js7440
     */
	public CblsQTIRKSJXInput () 
	{
	}
	
    /**
     * Constructor: CblsQTIRKSJXInput
     * 
     * @param String 	ims_region
     * @param String 	data_center
     * @param String 	term_A
     * @param String	term_Z
     * @param String	cable
     * @param String	from_unit
     * @param String	last_unit 
     * @author js7440
     */
	public CblsQTIRKSJXInput (
		String ims_region,
		String data_center, 
		String term_A, 
		String term_Z, 
		String cable, 
		String from_unit, 
		String last_unit) 
	{ 
		this.data_center = data_center;
		this.ims_region = ims_region;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.cable = cable;
		this.from_unit = from_unit;
		this.last_unit = last_unit;

	}
}
