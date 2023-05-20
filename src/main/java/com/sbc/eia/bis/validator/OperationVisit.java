//$Id: OperationVisit.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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


public class OperationVisit extends DataVisit {

	private String operation = null;
	private String exceptionRule = null;
	private Parameters parameters = null;

	public OperationVisit(String aOperation) {

		super();
		operation = aOperation;

	}
	public OperationVisit(String aOperation, String aRule, String aMessage) {

		super();
		operation = aOperation;
		exceptionRule = aRule;
		message = aMessage;

	}
	public Object accept(DataVisitor visitor) throws DataVisitorException {

		return visitor.visitOperation(this);

	}

	/**
	 * @return
	 */

	public String getOperation() {
		return operation;
	}

	/**
	 * @param string
	 */

	public void setOperation(String string) {
		operation = string;
	}

	/**
	 * @return
	 */
	public String getExceptionRule() {
		return exceptionRule;
	}

	/**
	 * @param string
	 */
	public void setExceptionRule(String string) {
		exceptionRule = string;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
	}

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
