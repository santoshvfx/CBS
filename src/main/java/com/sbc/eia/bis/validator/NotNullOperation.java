//$Id: NotNullOperation.java,v 1.1.4.1 2006/10/11 00:22:10 mb6834 Exp $

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
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 2/17/2006	 Mark Liljequist		Creation

package com.sbc.eia.bis.validator;



public class NotNullOperation extends Operation {

	public NotNullOperation() {
		super();
	}

	public String validate(Object object) throws ValidationException {

		try {

			if (!object.toString().trim().equalsIgnoreCase("null")) {
				return SUCCESS;
			}

		} catch (NullPointerException e) {
		}

		throw new ValidationException("Null object : NotNullOperation", 1030);
	}

}
