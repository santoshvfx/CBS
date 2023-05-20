//$Id: ScenarioList.java,v 1.2 2006/08/03 01:33:50 ml2917 Exp $

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
import java.util.Iterator;

public class ScenarioList {

	private ArrayList scenarioList = null;
	private Iterator listIterator = null;
	private Hashtable scenarioMap = null;


	public ScenarioList() {

		scenarioList = new ArrayList();
		scenarioMap = new Hashtable();

	}

	public void iterator() {
		listIterator = scenarioList.iterator();
	}

	/**
	 * @return boolean
	 */
	public boolean hasNext() {
		return listIterator.hasNext();
	}

	/**
	 * @return String
	 */
	public String next() {
		return (String) listIterator.next();
	}

	/**
	 * @param Object
	 * @return ScenarioMap
	 */
	public ScenarioMap getMapItem(Object key) {

		return (ScenarioMap) scenarioMap.get(key);

	}

	/**
	 * @param Object
	 * @param Object
	 */
	public void addMapItem(Object key, Object value) {

		scenarioMap.put(key, value);

	}

	/**
	 * @param Object
	 */
	public void addListItem(Object arg0) {

		scenarioList.add(arg0);

	}

	/**
	 * @return boolean
	 */
	public boolean isEmpty() {
		return scenarioList.isEmpty();
	}
	
	public void clearList() {
		scenarioList = new ArrayList();
	}

}
