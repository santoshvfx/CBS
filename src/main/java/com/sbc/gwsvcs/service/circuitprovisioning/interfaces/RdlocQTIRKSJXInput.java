// $Id: RdlocQTIRKSJXInput.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
//#############################################################################

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

/**
 * Class      : RdlocQTIRKSJXInput
 * Description: Class used for handling the TIRKSJX RDLOC screen request input.  
 * 
 * @author js7440
 */
final public class RdlocQTIRKSJXInput implements java.io.Serializable 
{ 
	public String data_center;
	public String location;

    /**
     * Constructor: RdlocQTIRKSJXInput
     * 
     * @author js7440
     */
	public RdlocQTIRKSJXInput () 
	{
	}
	
	/**
	 * Constructor: RdlocQTIRKSJXInput
	 * 
	 * @param String data_center
	 * @param String location 
	 * @author js7440
	 */
	public RdlocQTIRKSJXInput (String data_center, String location) 
	{ 
		this.data_center = data_center;
		this.location = location;

	}
}
