//$Id: UtilityOperation.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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

import java.util.Iterator;

public class UtilityOperation extends Operation {


	private final static String CASE_SENSITIVE = "CASE_SENSITIVE";
	private final static String YES = "YES";

	public UtilityOperation() {
		super();

	}

	/*
	 * This utiltiy validates an object.
	 * A list can be configured to evaluate against the object. 
	 * A null object will return a validation exception.
	 * 
	 */

	public String validate(Object object) throws ValidationException {

		try {
			if (object instanceof String) {
				evaluateParms((String) object);
				return SUCCESS;
			}
			if (object instanceof Integer) {
				evaluateParms((Integer) object);
				return SUCCESS;
			}
			if (object instanceof Boolean) {
				evaluateParms((Boolean) object);
				return SUCCESS;
			}

		} catch (NullPointerException npe) {
		}

		throw new ValidationException("Null object : UtilityOperation", 1040);

	}

	private void evaluateParms(String string) throws ValidationException {

		evaluateValues(string);
		evaluateRegExp(string);

	}

	private void evaluateParms(Boolean bool) throws ValidationException {

		evaluateValues(bool.toString());

	}

	private void evaluateParms(Integer integer) throws ValidationException {

		evaluateValues(integer.toString());

	}

	private void evaluateValues(String string) throws ValidationException {

		boolean compareCaseSensitive = false;

		try {
			try {
				String compareCase =
					(String) parameters.getArgumentHash().get(CASE_SENSITIVE);
				if (compareCase.compareTo(YES) == 0)
					compareCaseSensitive = true;
			} catch (Exception e) {
			}

			Iterator valueIter = parameters.getValueList().iterator();
			while (valueIter.hasNext()) {
				if (compareCaseSensitive) {
					if (string.compareTo((String) valueIter.next()) == 0) {
						return;
					}
				} else if (
					string.compareToIgnoreCase((String) valueIter.next())
						== 0) {
					return;
				}

			}

		} catch (NullPointerException e) {
			return;
		}

		throw new ValidationException(
			"Value [" + string + "] not found :" + " UtilityOperation",
			1041);

	}

	private void evaluateRegExp(String aString) throws ValidationException {

		if (true)
			return;

		throw new ValidationException("UtilityOperation", 1042);
	}

}
