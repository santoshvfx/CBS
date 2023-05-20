// $Id: CxrsQTIRKSJXInput.java,v 1.1 2011/04/04 13:43:24 jo8461 Exp $
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
 * Class      : CxrsQTIRKSJXInput
 * Description: Class used for handling the TIRKSJX CXRS screen request input.  
 * 
 * @author js7440
 */
final public class CxrsQTIRKSJXInput 
	implements java.io.Serializable 
{ 
	public String data_center;
	public String term_A;
	public String term_Z;
	public String fac_des;
	public String fac_typ;

    /**
     * Constructor: CxrsQTIRKSJXInput
     * 
     * @author js7440
     */
	public CxrsQTIRKSJXInput () 
	{
	}
	
    /**
     * Constructor: CxrsQTIRKSJXInput
     * 
     * @param String data_center
     * @param String term_A
     * @param String term_Z
     * @param String fac_des 
     * @param String fac_typ  
     * @author js7440
     */
	public CxrsQTIRKSJXInput (
		String data_center, 
		String term_A, 
		String term_Z, 
		String fac_des, 
		String fac_typ) 
	{ 
		this.data_center = data_center;
		this.term_A = term_A;
		this.term_Z = term_Z;
		this.fac_des = fac_des;
		this.fac_typ = fac_typ;

	}
}
