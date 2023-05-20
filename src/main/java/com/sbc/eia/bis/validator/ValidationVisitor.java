//$Id: ValidationVisitor.java,v 1.3 2007/11/16 18:47:44 ml2917 Exp $

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

import java.lang.reflect.Array;

public class ValidationVisitor extends DataVisitor {

	private Object object = null;
	private String key = null;
	private static boolean assertPrint = false;
	private OperationManager operationManager = null;

	// These are methods from the visitor.
	// Key visit will be called first then the rest of the visit methods.
	// A null pointer will continue down the map to the operation.
	// The operation will be called last to determine the outcome.

	public ValidationVisitor(OperationManager aOperationManager) {

		super();

		operationManager = aOperationManager;

	}

	public Object visitArray(ArrayVisit v) throws DataVisitorException {

		try {

			if (assertPrint)
				System.out.println("Array Object.");

			// Use the visit to store the array index and length.
			v.setLength(Array.getLength(object));

			// Save the object for the next iteration.
			v.setObject(object);

			// Get the object using Array reflection.
			object = Array.get(object, v.getIteration());

			return v;

		} catch (NullPointerException e) {
			object = null;
			return v;
		} catch (ArrayIndexOutOfBoundsException e) {
			object = null;
			return v;
		}

	}

	public Object visitField(FieldVisit v) throws DataVisitorException {

		if (assertPrint) {
			try {

				System.out.println("Field [" + v.getField() + "]");
				System.out.println(
					"Type ["
						+ ReflectUtility.getType(object, v.getField())
						+ "]");
			} catch (ReflectException e) {
				System.out.println(
					"Error [" + e.getMessage() + ":" + e.getCode() + "]");
			} catch (NullPointerException e) {
				System.out.println("Error NullPointer");
			}
		}

		// Pass the object to retrieve another object using reflection..
		// Primitive types in Java will be returned wrapped in their object wrapper.

		try {
			object = ReflectUtility.fieldIntrospection(object, v.getField());
			return v;

		} catch (ReflectException e) {
			throw new DataVisitorException(
				e.getMessage(),
				e.getCode(),
				e.getExceptionRule());
		} catch (NullPointerException e) {
			object = null;
			return v;
		}

	}

	public Object visitKey(KeyVisit v) throws DataVisitorException {

		// Check for a matching key.

		if (v.getKey().compareTo(key) == 0)
			return v;

		throw new DataVisitorException(
			"Scenario [" + key + "] is invalid for key: [" + v.getKey() + "]",
			0,
			Validator.ONE_RULE);

	}

	public Object visitMethod(MethodVisit v) throws DataVisitorException {

		// Use the method name to retrieve another object using reflection.
		// Primitive types in Java will be returned wrapped in their object wrapper.

		try {
			object = ReflectUtility.methodIntrospection(object, v.getMethod());
			return v;

		} catch (ReflectException e) {
			throw new DataVisitorException(
				e.getMessage(),
				e.getCode(),
				e.getExceptionRule());
		} catch (NullPointerException e) {
			object = null;
			return v;
		}
	}

	public Object visitOperation(OperationVisit v)
		throws DataVisitorException {

		String result;

		if (assertPrint) {
			System.out.println(
				"Validation operation [" + v.getOperation() + "]");
			System.out.println("vairable [" + v.getMessage() + "]");
		}

		try {
			// Get the operation using the index from the visit.

			Operation operation =
				(Operation) operationManager.getOperation(v.getOperation());

			// Add the validation parameters

			operation.setParameters(v.getParameters());

			result = operation.validate(object);

		} catch (ValidationException e) {
			throw new DataVisitorException(
				v.getMessage() + " : " + e.getMessage(),
				e.getCode(),
				v.getExceptionRule());
		} catch (ValidatorException e) {
			throw new DataVisitorException(
				e.getMessage(),
				e.getCode(),
				e.getExceptionRule());

		}

		if (assertPrint) {
			System.out.println("Result [" + result + "]");
			System.out.println("Value [" + object.toString() + "]");
		}

		return v;
	}

	/**
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return Object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param String
	 */
	public void setKey(String string) {
		key = string;
	}

	/**
	 * @param Object
	 */
	public void setObject(Object object) {
		this.object = object;
	}

}
