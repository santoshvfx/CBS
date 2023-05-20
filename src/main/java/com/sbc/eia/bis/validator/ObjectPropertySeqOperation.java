//$Id: ObjectPropertySeqOperation.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 5/17/2006	 Mark Liljequist		Creation

package com.sbc.eia.bis.validator;

import java.util.Iterator;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.idl.types.ObjectProperty;

/**
 * 
 * ObjectPropertySeqOperation validation.
 * 
 */

public class ObjectPropertySeqOperation extends Operation {

	private static String TAG = "TAG";

	private static String ACTION = "ACTION";
	private static String TAG_VALIDATION = "TAG_VALIDATION";
	private static String TAG_NOT_EMPTY = "TAG_NOT_EMPTY";

	public ObjectPropertySeqOperation() {
		super();
	}

	public String validate(Object object) throws ValidationException {

		if (((String) parameters.getArgumentHash().get(ACTION))
			.trim()
			.compareTo(TAG_NOT_EMPTY)
			== 0)
			try {

				ObjectPropertyManager aObjectPropertyManager =
					new ObjectPropertyManager((ObjectProperty[]) object);

				if (aObjectPropertyManager
					.getValue((String) parameters.getArgumentHash().get(TAG))
					.trim()
					.length()
					> 0) {
					return SUCCESS;
				}

			} catch (NullPointerException e) {
			}

		if (((String) parameters.getArgumentHash().get(ACTION))
			.trim()
			.compareTo(TAG_VALIDATION)
			== 0)
			try {

				ObjectPropertyManager aObjectPropertyManager =
					new ObjectPropertyManager((ObjectProperty[]) object);

				Iterator valueIter = parameters.getValueList().iterator();
				while (valueIter.hasNext()) {

					if (aObjectPropertyManager
						.getValue(
							(String) parameters.getArgumentHash().get(TAG))
						.trim()
						.compareTo((String) valueIter.next())
						== 0) {
						return SUCCESS;
					}
				}
			} catch (NullPointerException e) {
			}

		throw new ValidationException(
			"Invalid object : ObjectPropertySeqOperation",
			1050);
	}

}
