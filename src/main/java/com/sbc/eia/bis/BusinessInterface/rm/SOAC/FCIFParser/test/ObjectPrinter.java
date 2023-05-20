package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * This class prints an object in a tree structure to a specified writer.
 * Creation date: (3/30/2002 7:56:31 AM)
 * @author: Matt Hicks
 */
public class ObjectPrinter {
	private String indentValue = "\t";
	private int startIndex = -1;
	/**
	 * ObjectPrinter constructor comment.
	 */
	public ObjectPrinter() {
		super();
	}
	/**
	 * ObjectPrinter constructor comment.
	 */
	public ObjectPrinter(String _indentValue) {
		super();

		if (_indentValue != null) {
			indentValue = _indentValue;
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private Object getAccessorValue(Object object, String fieldName)
		throws NoSuchMethodException {
		// Remove all underscores from the fieldName
		fieldName = fieldName.replace('_', ' ');
		fieldName = fieldName.trim();
		Method[] methods = object.getClass().getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName().toUpperCase();
			// Make sure the method name contains the field name
			if (methodName.indexOf(fieldName.toUpperCase()) != -1) {
				try {
					Method method = methods[i];
					// Make sure there are no parameters required
					if (method.getParameterTypes().length == 0) {
						Object value = method.invoke(object, null);
						return value;
					}
				} catch (org.omg.CORBA.BAD_OPERATION e) {
					return "**Unitialized**";
				} catch (Exception e) {
					;
					// Ignore, keep looking for accessor
				}
			}
		}

		throw new NoSuchMethodException("The method was not found.");
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String getTabs(int offset) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < offset; i++) {
			sb.append(indentValue);
		}
		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private boolean isPrimitive(Object object) {
		if (object instanceof Boolean) {
			return true;
		}
		if (object instanceof Integer) {
			return true;
		}
		if (object instanceof Float) {
			return true;
		}
		if (object instanceof Double) {
			return true;
		}
		if (object instanceof Long) {
			return true;
		}
		if (object instanceof Short) {
			return true;
		}
		if (object instanceof String) {
			return true;
		}

		return false;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private boolean isValidField(String fieldName) {
		// Remove the underscores if any
		fieldName = fieldName.replace('_', ' ');
		fieldName = fieldName.trim();

		if (fieldName.equalsIgnoreCase("discriminator")) {
			return false;
		}

		if (fieldName.equalsIgnoreCase("uninitialized")) {
			return false;
		}

		return true;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	public String print(Object object) {
		return printObject(object, 0);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	public String print(Object object, int offset) {
		// Initial checks
		if (offset < 0) {
			return "Error - The offset cannot be less than 0.";
		} else {
			return printObject(object, offset);
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printArray(Object[] array, int offset) {
		StringBuffer sb = new StringBuffer();

		// Need to format the array class to remove the [L at the beginning and the ; at the end
		sb.append(
			printArrayObjectHeader(array.getClass().getComponentType().getName()));

		// Go through each field and print out results
		for (int i = 0; i < array.length; i++) {
			sb.append(printArrayHeader(i, offset));
			sb.append(printObject(array[i], offset + 1));
		}

		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printArrayHeader(int index, int offset) {
		StringBuffer sb = new StringBuffer();
		// Append the tabs
		sb.append(getTabs(offset));
		// Append the new method name
		sb.append("[" + index + "]");
		// Append a colon
		sb.append(":");
		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printArrayObjectHeader(String objectName) {
		// Need to format the array class to remove the [L at the beginning and the ; at the end
		StringBuffer sb = new StringBuffer();
		int startIndex, endIndex;

		// Find the starting point
		startIndex = objectName.indexOf("[L");
		if (startIndex == -1) {
			// The string was not found, start at the beginning
			startIndex = 0;
		} else {
			// Start after the array characters
			startIndex = 2;
		}

		// Find the ending point
		endIndex = objectName.indexOf(";");
		if (endIndex == -1) {
			// The string was not found, start at the beginning
			endIndex = objectName.length();
		}

		// Append the formatted name
		sb.append(" (" + objectName.substring(startIndex, endIndex) + "[])");

		// Print it
		sb.append("\n");
		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printField(Object object, String name, int offset) {
		StringBuffer sb = new StringBuffer();

		try {
			Field field = object.getClass().getDeclaredField(name);
			// Print each field only if they are not constants
			if (!(Modifier.isPublic(field.getModifiers())
				&& Modifier.isStatic(field.getModifiers())
				&& Modifier.isFinal(field.getModifiers()))) {
				// Do not print the discriminator and the uninitialized
				if (isValidField(field.getName())) {
					// Print the Field Header
					sb.append(printFieldHeader(name, offset));
					if (Modifier.isPublic(field.getModifiers())
						&& !Modifier.isStatic(field.getModifiers())
						&& !Modifier.isFinal(field.getModifiers())) {
						// Print out the getter accessors
						try {
							sb.append(printObject(field.get(object), offset + 1));
						} catch (IllegalAccessException e) {
							sb.append("Couldn't access field: ");
							sb.append(name);
							sb.append("\n");
						}
					} else {
						// Look for a getter accessor
						try {
							Object value = getAccessorValue(object, name);
							if (value == null) {
							}
							// Print the result
							sb.append(printObject(value, offset + 1));
						} catch (Exception e) {
							// Ignore - Assume private variable
							sb.append(" Unknown.\n");
						}
					}
				}
			}
		} catch (NoSuchFieldException e) {
			sb.append("The field: ");
			sb.append(name);
			sb.append(" was not found.\n");
		}

		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printFieldHeader(String name, int offset) {
		StringBuffer sb = new StringBuffer();
		// Append the tabs
		sb.append(getTabs(offset));
		// Append the new method name
		sb.append(name);
		// Append a colon
		sb.append(":");
		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printObject(Object object, int offset) {

		StringBuffer sb = new StringBuffer();
		if (object != null) {
			// Check the object type
			if (object.getClass().isArray()) {
				sb.append(printArray((Object[]) object, offset));
			} else {
				if (isPrimitive(object)) {
					// Print the object
					sb.append(printPrimitiveObject(object, offset));
				} else {
					sb.append(" (");
					sb.append(object.getClass().getName());
					sb.append(")\n");
					// The object is not primitive, go through each field and print values
					Field[] objectFields = object.getClass().getDeclaredFields();
					// Print each field
					offset = offset++;
					for (int i = 0; i < objectFields.length; i++) {
						sb.append(printField(object, objectFields[i].getName(), offset));
					}
				}
			}
		}

		return sb.toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/2/01 11:19:48 AM)
	 */
	private String printPrimitiveObject(Object object, int offset) {
		StringBuffer sb = new StringBuffer();
		// Append the tabs
		sb.append(" ");
		// Append the object
		if (object == null) {
			sb.append("{null}");
		} else {
			sb.append("{");
			sb.append(object.toString());
			sb.append("}");
		}

		sb.append("\n");
		return sb.toString();
	}
}