//$Id: ReflectUtility.java,v 1.2 2006/08/03 01:28:29 ml2917 Exp $

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ReflectUtility {

	public static Object getInstance(
		String aClass,
		Class[] aClasses,
		Object[] args)
		throws ReflectException {

		try {
			Class creationClass = Class.forName(aClass);
			Constructor constructorClass =
				creationClass.getConstructor(aClasses);
			return (Object) constructorClass.newInstance(args);

		} catch (ClassNotFoundException e) {
			throw new ReflectException(e.getMessage(), 115);
		} catch (NoSuchMethodException e) {
			throw new ReflectException(e.getMessage(), 116);
		} catch (InstantiationException e) {
			throw new ReflectException(e.getMessage(), 117);
		} catch (IllegalAccessException e) {
			throw new ReflectException(e.getMessage(), 118);
		} catch (InvocationTargetException e) {
			throw new ReflectException(e.getMessage(), 119);
		}

	}

	public static ArrayList getFields(Object object) {

		Field[] fields = object.getClass().getFields();

		ArrayList list = new ArrayList();

		for (int i = 0; i < fields.length; i++) {
			list.add(fields[i].getName());
		}

		return list;

	}

	public static String getType(Object object, String field)
		throws ReflectException {

		try {

			return object.getClass().getField(field).getType().getName();

		} catch (NoSuchFieldException e) {
			throw new ReflectException(e.getMessage(), 105);
		}

	}

	public static Object fieldIntrospection(Object object, String field)
		throws ReflectException {

		try {

			return object.getClass().getField(field).get(object);

		} catch (IllegalAccessException e) {
			throw new ReflectException(e.getMessage(), 100);
		} catch (NoSuchFieldException e) {
			throw new ReflectException(e.getMessage(), 101);
		}

	}

	public static boolean isFieldName(Object object, String field)
		throws ReflectException {

		fieldIntrospection(object, field);
		return true;

	}

	public static boolean isMethodName(Object object, String method)
		throws ReflectException {

		methodIntrospection(object, method);
		return true;

	}

	// Helper method.

	public static Object methodIntrospection(Object object, String method)
		throws ReflectException {

		Object[] argList = (Object[]) null;
		Class[] classList = (Class[]) null;

		return methodIntrospection(object, method, classList, argList);

	}

	// Invokes the method.
	// Will return an object or a primitive wrapped in an object.

	public static Object methodIntrospection(
		Object object,
		String method,
		Class[] aClass,
		Object[] argList)
		throws ReflectException {

		try {

			return object.getClass().getMethod(method, aClass).invoke(
				object,
				argList);

		} catch (IllegalAccessException e) {
			throw new ReflectException(e.getMessage(), 110);
		} catch (InvocationTargetException e) {
			throw new NullPointerException(Integer.toString(111));
		} catch (NoSuchMethodException e) {
			throw new ReflectException(e.getMessage(), 112);
		}

	}

}
