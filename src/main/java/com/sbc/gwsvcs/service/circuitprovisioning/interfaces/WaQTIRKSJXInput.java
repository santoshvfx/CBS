// $Id: WaQTIRKSJXInput.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : WaQTIRKSJXInput
 * Description: Class used for handling the TIRKSJX WA screen request input.  
 * 
 * @author js7440
 */
final public class WaQTIRKSJXInput implements java.io.Serializable 
{ 
	public String data_center;
	public String clo;
	public String cac;
	public String ckt;

    /**
     * Constructor: WaQTIRKSJXInput
     * 
     * @author js7440
     */
	public WaQTIRKSJXInput () 
	{
	}
	
	/**
	 * Constructor: WaQTIRKSJXInput
	 * 
	 * @param String data_center
	 * @param String clo 
	 * @param String cac
	 * @param String ckt
	 * @author js7440
	 */
	public WaQTIRKSJXInput (String data_center, String clo, String cac, String ckt) 
	{ 
		this.data_center = data_center;
		this.clo = clo;
		this.cac = cac;
		this.ckt = ckt;

	}
}
