//$Id: ScenarioManager.java,v 1.2 2006/08/03 01:33:58 ml2917 Exp $

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
import java.util.StringTokenizer;

import com.sbc.eia.bis.validator.interfaces.ArgumentItem;
import com.sbc.eia.bis.validator.interfaces.DataValidation;
import com.sbc.eia.bis.validator.interfaces.ScenarioItem;
import com.sbc.eia.bis.validator.interfaces.ValueItem;
import com.sbc.eia.bis.validator.interfaces.VariableItem;

public class ScenarioManager {

	private static final String METHOD = MethodVisit.class.getName();
	private static final String KEY = KeyVisit.class.getName();
	private static final String FIELD = FieldVisit.class.getName();
	private static final String ARRAY = ArrayVisit.class.getName();
	private static final String OPERATION = OperationVisit.class.getName();
	private static final String ARRAY_ELEMENT = "[]";
	private static final String METHOD_ELEMENT = "()";
	private static final String DOT = ".";

	private ScenarioList scenarioList = null;

	public ScenarioManager() {

		super();

	}

	/**
	 * @param DataValidation
	 * @return ScenarioList
	 * @throws DataVisitorException
	 */
	public ScenarioList buildScenarioMap(DataValidation map)
		throws ValidatorException {

		// Create new maps

		scenarioList = new ScenarioList();

		Iterator scenarioIter = map.getScenario().iterator();

		while (scenarioIter.hasNext()) {

			ScenarioItem scenarioItem = (ScenarioItem) scenarioIter.next();
			scenarioList.addListItem(scenarioItem.getName());
			scenarioList.addMapItem(
				scenarioItem.getName(),
				buildVisitMap(scenarioItem));

		}

		return scenarioList;
	}

	/**
	 * @param ScenarioItem
	 * @return ScenarioMap
	 * @throws DataVisitorException
	 */
	private ScenarioMap buildVisitMap(ScenarioItem scenario)
		throws ValidatorException {

		// Create a new sceanrio map.

		ScenarioMap scenarioMap = new ScenarioMap();

		// Parse the variables for all the fields and build a node list.
		// Get the scenario configuration.

		Iterator variableIter = scenario.getVariable().iterator();

		while (variableIter.hasNext()) {

			VisitMap visitMap = new VisitMap();

			// Add the key.
			visitMap.add(
				ReflectUtility.getInstance(
					KEY,
					new Class[] { String.class },
					new String[] { scenario.getName()}));

			VariableItem variable = (VariableItem) variableIter.next();

			createFromPathElement(visitMap, variable);

			// Add an operation to the list.

			OperationVisit visit =
				(OperationVisit) ReflectUtility.getInstance(
					OPERATION,
					new Class[] { String.class, String.class, String.class },
					new String[] {
						variable.getValidationRule(),
						variable.getExceptionRule(),
						variable.getName()});

			visit.setParameters(buildParameters(variable));

			visitMap.add(visit);
			visitMap.trimToSize();

			// Check if the variable has an array element.

			if (variable.getName().indexOf(ARRAY_ELEMENT) > -1) {
				visitMap.setHasArray(true);
			}

			// End of variable.
			scenarioMap.add(visitMap);

		}

		return scenarioMap;

	}

	private void createFromPathElement(
		VisitMap visitMap,
		VariableItem variable)
		throws ValidatorException {

		StringTokenizer tokenizer =
			new StringTokenizer(variable.getPathElement(), DOT);

		// Add the elements in the path.
		while (tokenizer.hasMoreTokens()) {

			String s = tokenizer.nextToken();
			visitMap.add(
				ReflectUtility.getInstance(
					parseForType(s),
					new Class[] { String.class },
					new String[] { parseForToken(s)}));

		}

	}

	/**
	 * @param VariableItem
	 * @return ValidationParms
	 */
	private Parameters buildParameters(VariableItem variable) {

		Parameters parameters = new Parameters();

		// Add the values from the list.

		if (variable.isSetValidationValue()) {
			ValueItem valueItem = variable.getValidationValue();
			if (valueItem.isSetValue()) {
				ArrayList list = new ArrayList();
				Iterator listIterator = valueItem.getValue().iterator();
				while (listIterator.hasNext()) {
					list.add(listIterator.next());
				}
				parameters.setValueList(list);
			}
		}
		
		if (variable.isSetArgument()) {
			Iterator listIterator = variable.getArgument().iterator();
			Hashtable hash = new Hashtable();
			String tag;
			String value;
			while (listIterator.hasNext()) {
				ArgumentItem argumentItem = (ArgumentItem) listIterator.next();
				hash.put(argumentItem.getTag(), argumentItem.getValue());
			}
			parameters.setArgumentHash(hash);
		}

		return parameters;

	}

	/**
	 * @param String
	 * @return String
	 */

	private String parseForType(String s) {

		// Determine the type of variable.

		if (s.indexOf(METHOD_ELEMENT) > -1) {
			return METHOD;
		}

		if (s.indexOf(ARRAY_ELEMENT) > -1) {
			return ARRAY;
		}

		return FIELD;

	}

	/**
	 * @param String
	 * @return String
	 */

	private String parseForToken(String s) {

		// Determine the element of the variable or method.

		int i;
		if ((i = s.lastIndexOf("()")) > 0) {
			return s.substring(0, i);
		}

		return s;

	}

	/**
	 * @return ScenarioList
	 */
	public ScenarioList getScenarioList() {
		return scenarioList;
	}

}