//$Id: ReflectException.java,v 1.1 2006/08/03 01:33:41 ml2917 Exp $

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


public class ReflectException extends ValidatorException {

	public ReflectException(String message) {

		super(message);
		excptionRule = Validator.ONE_RULE;
	}

	public ReflectException(String message, int aCode) {

		super(message);
		code = aCode;
		excptionRule = Validator.ONE_RULE;
		
	}

}
