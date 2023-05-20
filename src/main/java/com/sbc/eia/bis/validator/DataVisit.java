//$Id: DataVisit.java,v 1.2 2006/08/03 01:34:35 ml2917 Exp $

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

public abstract class DataVisit {

	protected int iteration = 0;
	protected boolean arrayIndicator = false;
	protected int length = 0;
	protected String message = null;
	protected Object object = null;

	protected DataVisit() {

	}

	public abstract Object accept(DataVisitor visitor)
		throws DataVisitorException;

	/**
	 * @return boolean
	 */
	public boolean isArray() {
		return false;
	}

	/**
	 * @return int
	 */
	public int getIteration() {
		return iteration;
	}

	/**
	 * @param int
	 */
	public void setIteration(int i) {
		iteration = i;
	}

	/**
	 * @return int
	 */
	public int getLength() {
		return length;
	}

	public void setLength(int i) {
		length = i;
	}

	/**
	 * @return Object
	 */

	public Object getObject() {
		return object;
	}

	/**
	 * @param Object
	 */

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param String
	 */
	public void setMessage(String string) {
		message = string;
	}

}
