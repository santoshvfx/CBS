//$Id: OperationManager.java,v 1.2 2006/08/03 01:34:58 ml2917 Exp $

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

import java.util.Hashtable;
import java.util.Iterator;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.validator.interfaces.DataValidation;
import com.sbc.eia.bis.validator.interfaces.OperationItem;

public class OperationManager {

	private Hashtable operationMap;

	public OperationManager() {

		super();

	}

	public void buildOperationMap(DataValidation map)
		throws ValidatorException {

		operationMap = new Hashtable();

		Iterator validationIter = map.getOperation().iterator();

		while (validationIter.hasNext()) {

			OperationItem operation = (OperationItem) validationIter.next();

			// Add the key and class.

			operationMap.put(
				operation.getValidationRule(),
				ReflectUtility.getInstance(
					operation.getClassName(),
					null,
					null));

			// End of validation.

		}

	}

	/**
	 * @return
	 */

	public Hashtable getValidationMap() {
		return operationMap;
	}

	/**
	 * @param hashtable
	 */

	public void setValidationMap(Hashtable hashtable) {
		operationMap = hashtable;
	}

	public Operation getOperation(String entry) throws ValidatorException {

		Operation operation = (Operation) operationMap.get(entry);

		if (operation == null)
			throw new ValidatorException(
				"Operation object is null.",
				160,
				Validator.ONE_RULE);

		return operation;

	}

}
