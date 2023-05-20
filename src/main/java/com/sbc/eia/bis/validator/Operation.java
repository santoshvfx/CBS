//$Id: Operation.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 2/17/2006	Mark Liljequist		Creation

package com.sbc.eia.bis.validator;


public abstract class Operation {

	protected static final String SUCCESS = "0";
	protected static final String FAILURE = "1";

	protected Parameters parameters = null;

	protected Operation() {
	}

	public abstract String validate(Object variable)
		throws ValidationException;

	/**
	 * @return
	 */
	public Parameters getParameters() {
		return parameters;
	}

	/**
	 * @param parms
	 */
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

}
