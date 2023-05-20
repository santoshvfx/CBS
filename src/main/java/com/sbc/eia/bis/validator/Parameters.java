//$Id: Parameters.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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

import java.util.ArrayList;
import java.util.Hashtable;

public class Parameters {

	private ArrayList valueList = null;
	private Hashtable argumentHash = null;

	public Parameters() {

		super();

	}

	/**
	 * @return
	 */
	
	public ArrayList getValueList() {
		return valueList;
	}

	/**
	 * @param list
	 */
	
	public void setValueList(ArrayList list) {
		valueList = list;
	}

	/**
	 * @return
	 */
	
	public Hashtable getArgumentHash() {
		return argumentHash;
	}

	/**
	 * @param Hashtable
	 */
	
	public void setArgumentHash(Hashtable hashtable) {
		argumentHash = hashtable;
	}

}
