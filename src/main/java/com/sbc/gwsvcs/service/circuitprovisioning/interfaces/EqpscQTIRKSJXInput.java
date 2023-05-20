// $Id: EqpscQTIRKSJXInput.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : EqpscQTIRKSJXInput
 * Description: Class used for handling the TIRKSJX EQPSC screen request input.  
 * 
 * @author js7440
 */
final public class EqpscQTIRKSJXInput implements java.io.Serializable 
{ 
	public String ims_region;
	public String data_center;
	public String location;
	public String equip_code;
	public String level;
	public String relayrack;
	public String unit;

    /**
     * Constructor: EqpscQTIRKSJXInput
     * 
     * @author js7440
     */
	public EqpscQTIRKSJXInput () 
	{
	}	
	
	/**
	 * Constructor: EqpscQTIRKSJXInput
	 * 
	 * @param String ims_region
	 * @param String data_center
	 * @param String location
	 * @param String equip_code
	 * @param String level
	 * @param String relayrack
	 * @param String unit
	 * @author js7440
	 */
	public EqpscQTIRKSJXInput (
		String ims_region, 
		String data_center, 
		String location, 
		String equip_code, 
		String level, 
		String relayrack, 
		String unit) 
	{ 
		this.ims_region = ims_region;
		this.data_center = data_center;
		this.location = location;
		this.equip_code = equip_code;
		this.level = level;
		this.relayrack = relayrack;
		this.unit = unit;

	}
}
