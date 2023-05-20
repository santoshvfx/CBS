// $Id: ErrorUtil.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

import com.sbc.vicunalite.api.*;

final public class ErrorUtil implements java.io.Serializable { 
	public int OrigEvent;
	public String ErrorMsg;

	public ErrorUtil () {
	}
	public ErrorUtil (int OrigEvent, String ErrorMsg) { 
		this.OrigEvent = OrigEvent;
		this.ErrorMsg = ErrorMsg;

	}
}
