//$Id: NotEmptyOperation.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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


/**
 * 
 * Generic validation for any type.
 * 
 */

public class NotEmptyOperation extends Operation {

	public NotEmptyOperation() {
		super();
	}

	public String validate(Object object) throws ValidationException {

		try {

			if (object instanceof String) {
				if (((String) object).trim().length() > 0)
					return SUCCESS;
				else
					throw new ValidationException(
						"Object empty : NotEmptyOperation",
						1021);
			}
			if (object instanceof Integer) {
				object.toString();
				return SUCCESS;
			}
			if (object instanceof Boolean) {
				object.toString();
				return SUCCESS;
			}

		} catch (NullPointerException e) {
		}

		throw new ValidationException("Null object : NotEmptyOperation", 1020);
	}

}
