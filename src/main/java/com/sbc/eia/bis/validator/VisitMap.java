//$Id: VisitMap.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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
import java.util.Iterator;


public class VisitMap {

	private ArrayList visitMap = null;
	private Iterator mapIterator = null;
	private boolean arrayIndicator = false;

	public VisitMap() {
		visitMap = new ArrayList();
	}

	public void iterator() {
		mapIterator = visitMap.iterator();
	}

	public void clear() {
		visitMap = new ArrayList();
	}

	/**
	 * @return boolean
	 */
	public boolean hasNext() {
		return mapIterator.hasNext();
	}

	/**
	 * @return DataVisit
	 */
	public DataVisit next() {

		return (DataVisit) mapIterator.next();
	}

	/**
	 * @param ArrayList
	 */
	public void setVisitMap(ArrayList list) {
		visitMap = list;
	}

	/**
	 * @param index
	 * @return DataVisit
	 */
	public DataVisit get(int index) {
		return (DataVisit) visitMap.get(index);
	}

	public void trimToSize() {
		visitMap.trimToSize();
	}

	public int size() {
		return visitMap.size();
	}

	/**
	 * @param Object
	 */
	public void add(Object arg0) {
		visitMap.add(arg0);
	}

	/**
	 * @return boolean
	 */
	public boolean isEmpty() {
		return visitMap.isEmpty();
	}

	/**
	 * @return boolean
	 */
	public boolean hasArray() {
		return arrayIndicator;
	}

	/**
	 * @param boolean
	 */
	public void setHasArray(boolean bool) {
		arrayIndicator = bool;
	}

}
