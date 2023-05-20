//$Id: ValidatorException.java,v 1.1 2006/08/03 01:28:52 ml2917 Exp $

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

public class ValidatorException extends Exception {

	protected String excptionRule = null;
	protected int code = 0;

	public ValidatorException(String message) {

		super(message);

	}

	public ValidatorException(String message, int aCode) {

		super(message);
		code = aCode;
	}

	public ValidatorException(
		String message,
		int aCode,
		String aExceptionRule) {

		super(message);
		code = aCode;
		excptionRule = aExceptionRule;

	}

	/**
	 * @return
	 */
	public String getExceptionRule() {
		return excptionRule;
	}

	/**
	 * @param string
	 */
	public void setExceptionRule(String string) {
		excptionRule = string;
	}

	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param i
	 */
	public void setCode(int i) {
		code = i;
	}

}
