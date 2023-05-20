//$Id: Validator.java,v 1.3 2006/08/09 17:09:25 ml2917 Exp $

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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.validator.interfaces.DataValidation;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class Validator {

	private static final String VALIDATOR_EXCEPTION_RULE_FILE =
		"EXCEPTION_BUILDER_VALIDATOR_RULE_FILE";
	private static final String aPackage = DataValidation.class.getPackage().getName();
	public static final String ONE_RULE = "001";
	private Hashtable properties = null;
	private Utility utility = null;
	private ScenarioManager scenarioManager = null;
	private OperationManager operationManager = null;
	private String ruleFile = null;
	private String configurationFile = null;

	public Validator(
		Utility aUtility,
		Hashtable aProperties,
		String aConfigurationFile) {

		super();
		properties = aProperties;
		utility = aUtility;
		ruleFile = (String) aProperties.get(VALIDATOR_EXCEPTION_RULE_FILE);
		configurationFile = aConfigurationFile;

	}


	public int validate(BisContext context, Object object, boolean logIndicator)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">validator(BisContext, Object)");

		if (logIndicator) {
			Log.logIDL(utility, object);
		}

		return validate(context, object);	

	}

	/**
	 * @param BisContext
	 * @param Object
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	
	public int validate(BisContext context, Object object)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">validator(BisContext, Object)");

		try {	
			initialize(context);
			validateRoot(context, object);
		} catch (ValidatorException e) {
			generateException(context, e);
		}

		utility.log(LogEventId.DEBUG_LEVEL_2, "<validator(BisContext, Object)");
		
		return validate(context, object, scenarioManager.getScenarioList());
	}

	/**
	 * @param BisContext
	 * @param Object
	 * @param String
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public int validate(BisContext context, Object object, String scenario)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			">validator(BisContext, Object, String)");

			ScenarioList list = null;
		try {
			initialize(context);
			validateRoot(context, object);
			list = scenarioManager.getScenarioList();
			if (list.getMapItem(scenario) == null) {
				throw new ValidatorException(
					ONE_RULE,
					178,
					"Invalid scenario");
			}

		} catch (ValidatorException e) {
			generateException(context, e);
		}

		list.clearList();
		list.addListItem(scenario);
		
		return validate(context, object, list);

	}

	/**
	 * @param BisContext
	 * @param Object
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private int validate(BisContext context, Object object, ScenarioList list)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">validator(BisContext, Object)");

		list.iterator();

		Traverse traverse = new Traverse(utility);

		int result = 0;
		while (list.hasNext()) {

			String key = list.next();
			utility.log(
				LogEventId.INFO_LEVEL_2,
				"Validation scenario [" + key + "]");

			try {
				traverse.traverse(
					context,
					list.getMapItem(key),
					operationManager,
					object,
					key);
			} catch (ValidatorException e) {
				try {
					result = generateException(context, e);
				} catch (DataNotFound dnf) {
					utility.log(
						LogEventId.INFO_LEVEL_1,
						"***** IGNORE THIS FAILURE MESSAGE, THIS IS NOT AN ERROR *****");
					utility.log(
						LogEventId.DEBUG_LEVEL_1,
						"No data found for object: "
							+ dnf.aExceptionData.aDescription
							+ ":"
							+ dnf.aExceptionData.aCode);
				}
			}

		}

		utility.log(LogEventId.DEBUG_LEVEL_2, "<validator(BisContext, Object)");
		
		return result;

	}

	/**
	 * @param BisContext
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void initialize(BisContext context) throws ValidatorException {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">initialize()");

		if (scenarioManager != null) {
			utility.log(LogEventId.DEBUG_LEVEL_2, "<initialize()");
			return;
		}

		DataValidation map = null;

		map = decode(getXMLFIle(configurationFile).toString(), aPackage);

		// Build the maps.

		operationManager = new OperationManager();
		operationManager.buildOperationMap(map);
		scenarioManager = new ScenarioManager();
		scenarioManager.buildScenarioMap(map);

		utility.log(
			LogEventId.INFO_LEVEL_1,
			"Schema version [" + map.getSchemaVersion() + "]");

		utility.log(LogEventId.DEBUG_LEVEL_2, "<initialize()");

	}

	/**
	 * @param BisContext
	 * @param Object
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private String validateRoot(BisContext context, Object object)
		throws ValidatorException {

		try {
			String name = object.getClass().getName();
			utility.log(LogEventId.INFO_LEVEL_1, "Class name [" + name + "]");
			return name;
		} catch (NullPointerException e) {
			throw new ValidatorException(
				ONE_RULE,
				177,
				"Received null root object: " + e.getMessage());
		}

	}

	private int generateException(BisContext context, ValidatorException e)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		return generateException(
			context,
			e.getExceptionRule(),
			Integer.toString(e.getCode()),
			e.getMessage());

	}

	/**
	 * @param BisContext
	 * @param String 
	 * @param String 
	 * @param String 
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private int generateException(
		BisContext context,
		String errorCode,
		String errorText,
		String message)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Error message [" + message + "]"+ " Error code [" + errorCode + "] Error text [" + errorText + "]");

		ExceptionBuilderResult result =
			ExceptionBuilder.parseException(
				context,
				ruleFile,
				null,
				errorCode,
				errorText,
				false,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				utility,
				null,
				null,
				Validator.buildMessage(message));

		if (result.getBusinessRule() == 0) {
			result.throwException(context, utility);
		}

		return result.getBusinessRule();

	}

	/**
	 * @param String
	 * @return Properties
	 */
	public static Properties buildMessage(String message) {

		Properties tagValues = null;
		if (message != null) {
			tagValues = new Properties();
			tagValues.setProperty("VARIABLE", message);
		}

		return tagValues;
	}

	/**
	 * @param String
	 * @param String
	 * @return
	 * @throws ValidatorException
	 */
	private DataValidation decode(String message, String aPackage)
		throws ValidatorException {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">decode()");

		try {

			utility.log(LogEventId.DEBUG_LEVEL_2, "Pacakge [" + aPackage + "]");

			// create a JAXBContext capable of handling classes generated into
			// the generated package

			JAXBContext jc = JAXBContext.newInstance(aPackage);

			Unmarshaller decoder = jc.createUnmarshaller();

			// unmarshal a generated instance document into a Java object

			return (DataValidation) decoder.unmarshal(
				new InputSource(new StringReader(message)));

		} catch (JAXBException e) {
			throw new ValidatorException(
				"JAXB error [" + e.getMessage() + ":" + message + "]",
				175,
				Validator.ONE_RULE);
		} finally {
			utility.log(LogEventId.DEBUG_LEVEL_2, "<decode()");
		}

	}

	/**
	 * @param String
	 * @return
	 * @throws ValidatorException
	 */
	private StringBuffer getXMLFIle(String referenceFile)
		throws ValidatorException {

		utility.log(LogEventId.DEBUG_LEVEL_2, ">getXMLFIle()");

		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Loading file [" + referenceFile + "]");

		if (referenceFile == null) {
			throw new ValidatorException(
				"Reference file is null.",
				174,
				Validator.ONE_RULE);

		}

		InputStream anInputStream = null;
		InputStreamReader anInputStreamReader = null;
		BufferedReader aBufferedReader = null;
		StringBuffer buffer = new StringBuffer();

		try {
			anInputStream =
				PropertiesFileLoader.getAsStream(referenceFile.trim(), utility);
			anInputStreamReader = new InputStreamReader(anInputStream);
			aBufferedReader = new BufferedReader(anInputStreamReader);

			// Read a line
			String xmlLine;
			while ((xmlLine = aBufferedReader.readLine()) != null) {

				buffer.append(xmlLine);

			}
		} catch (FileNotFoundException e) {
			throw new ValidatorException(
				"FileNotFoundException on ["
					+ referenceFile
					+ ":"
					+ e.getMessage()
					+ "]",
				170,
				Validator.ONE_RULE);
		} catch (IOException e) {
			throw new ValidatorException(
				"IOException on [" + referenceFile + ":" + e.getMessage(),
				171,
				Validator.ONE_RULE);
		} finally {

			utility.log(LogEventId.DEBUG_LEVEL_2, "<getXMLFIle()");

			try {
				if (aBufferedReader != null)
					aBufferedReader.close();
				if (anInputStreamReader != null)
					anInputStreamReader.close();
				if (anInputStream != null)
					anInputStream.close();
			} catch (IOException e) {
				throw new ValidatorException(
					"IOException (close) on ["
						+ referenceFile
						+ "]"
						+ e.getMessage(),
					173,
					Validator.ONE_RULE);
			}

		}

		return buffer;
	}

}
