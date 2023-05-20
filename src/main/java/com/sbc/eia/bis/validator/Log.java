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
//# ----------------------------------------------------------------------------
//# 

package com.sbc.eia.bis.validator;

import java.util.ArrayList;
import java.util.Iterator;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class Log {

	private final static String DOT = ".";
	private final static String BisHelper = "BisHelper";
	private final static String bishepers = "bishelpers";

	public static void logIDL(Utility aUtility, Object aObject) {

		ArrayList fields = ReflectUtility.getFields(aObject);

		Iterator fieldIter = fields.iterator();
		while (fieldIter.hasNext()) {
			logObjects(aUtility, aObject, (String) fieldIter.next());
		}

	}

	private static void logObjects(
		Utility aUtility,
		Object aObject,
		String field) {

		String helper = null;

		try {

			Object fieldObject =
				ReflectUtility.fieldIntrospection(aObject, field);

			if (fieldObject instanceof String
				|| fieldObject instanceof Integer
				|| fieldObject instanceof Long
				|| fieldObject instanceof Boolean) {

				aUtility.log(
					LogEventId.INPUT_DATA,
					field + "=<" + (String) fieldObject.toString() + ">");
				return;

			}

			String type = ReflectUtility.getType(aObject, field);
			int lastDot = type.lastIndexOf(DOT);
			helper =
				type.substring(0, lastDot + 1)
					+ bishepers
					+ type.substring(lastDot)
					+ BisHelper;

			Object logger =
				ReflectUtility.getInstance(
					helper,
					new Class[] { fieldObject.getClass()},
					new Object[] { fieldObject });

			aUtility.log(
				LogEventId.INPUT_DATA,
				field + "=<" + (String) logger.toString() + ">");

		} catch (ReflectException e) {
			aUtility.log(
				LogEventId.INPUT_DATA,
				field + "=<" + helper + "> error code [" + e.getCode() + "]");

		} catch (Exception e) {
			aUtility.log(LogEventId.INPUT_DATA, field + "=<>");

		}

	}
}