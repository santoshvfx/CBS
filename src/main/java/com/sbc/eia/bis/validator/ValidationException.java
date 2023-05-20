//$Id: ValidationException.java,v 1.2 2006/08/03 01:34:48 ml2917 Exp $

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

public class ValidationException extends ValidatorException {

	public ValidationException(String message) {

		super(message);

	}

	public ValidationException(String message, int aCode) {

		super(message);
		code = aCode;
	}

	public ValidationException(
		String message,
		int aCode,
		String aExceptionRule) {

		super(message);
		code = aCode;
		excptionRule = aExceptionRule;

	}

}
