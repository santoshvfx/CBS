//$Id: ScenarioMap.java,v 1.1 2006/06/23 20:40:30 ml2917 Exp $

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

public class ScenarioMap {

	private ArrayList scenarioMap = null;
	private Iterator mapIterator = null;

	/**
	 * @return
	 */

	public ScenarioMap() {
		scenarioMap = new ArrayList();
	}

	public void iterator() {
		mapIterator = scenarioMap.iterator();
	}

	/**
	 * @return boolean
	 */
	public boolean hasNext() {
		return mapIterator.hasNext();
	}

	/**
	 * @return VisitMap
	 */
	public VisitMap next() {

		return (VisitMap) mapIterator.next();
	}

	public void trimToSize() {
		scenarioMap.trimToSize();
	}

	/**
	 * @return int
	 */

	public int size() {
		return scenarioMap.size();
	}

	/**
	 * @param Object
	 *
	 */
	public void add(Object arg0) {
		scenarioMap.add(arg0);
	}

	/**
	 * @return boolan
	 */
	public boolean isEmpty() {
		return scenarioMap.isEmpty();
	}

}
