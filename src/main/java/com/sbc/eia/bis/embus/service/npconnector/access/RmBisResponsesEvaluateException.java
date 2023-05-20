package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVAutoTransactionExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.AVExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.NptExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.NptGetValueExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.NptSaveExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.SystemExceptionType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVAutoTransactionExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptGetValueExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptSaveExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.SystemExceptionTypeImpl;
import com.sbc.eia.idl.exception_types.ExceptionCode;



/**
 * @author ML2917
 * Evaluate the reponse.
 *
 */

public class RmBisResponsesEvaluateException
{

	/**
	 * @see java.lang.Object#Object()
	 * Evaluate the reponse
	 */

	private RmBisResponsesEvaluateException() throws NetProvisionException
	{
		super();
	}


	/**
	 * Method evaluate.
	 * Check the repsonse for an exception.
	 * @param exceptionType
	 * @throws NetProvisionException
	 */
	
	public static void evaluate(ExceptionTypeImpl exceptionType)
		throws NetProvisionException
	{

		// Check the instance type for an exception.

		if (exceptionType.getNptException() instanceof NptExceptionType)
		{
			evaluateClassException(
				(NptExceptionTypeImpl) exceptionType.getNptException());

		}

		if (exceptionType.getSystemException()
			instanceof SystemExceptionType)
		{
			evaluateClassException(
				(SystemExceptionTypeImpl) exceptionType.getSystemException());

		}

		if (exceptionType.getAvAutoTransactionException()
			instanceof AVAutoTransactionExceptionType)
		{
			evaluateClassException(
				(AVAutoTransactionExceptionTypeImpl) exceptionType
					.getAvAutoTransactionException());

		}

		if (exceptionType.getNptGetValueException()
			instanceof NptGetValueExceptionType)
		{
			evaluateClassException(
				(NptGetValueExceptionTypeImpl) exceptionType
					.getNptGetValueException());

		}

		if (exceptionType.getNptSaveException()
			instanceof NptSaveExceptionType)
		{
			evaluateClassException(
				(NptSaveExceptionTypeImpl) exceptionType.getNptSaveException());

		}

		if (exceptionType.getAvException() instanceof AVExceptionType)
		{
			evaluateClassException(
				(AVExceptionTypeImpl) exceptionType.getAvException());

		}

		throw new NetProvisionException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			"Invalid exception object to evaluate.");

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */
	private static void evaluateClassException(NptExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getErrorStack().getCategory()
				+ "|"
				+ e.getErrorStack().getStatus()
				+ "|";

		throw new NptException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */

	private static void evaluateClassException(SystemExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getMessage()
				+ "|"
				+ e.getExceptionClass()
				+ "|";

		throw new SystemException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */

	private static void evaluateClassException(AVAutoTransactionExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getMessage()
				+ "|"
				+ e.getCategory()
				+ "|"
				+ e.getAbandonedTransactionId()
				+ "|";

		throw new AVAutoTransactionException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */

	private static void evaluateClassException(NptGetValueExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getReturnedValue()
				+ "|"
				+ e.getErrorStack().getCategory()
				+ "|"
				+ e.getErrorStack().getStatus()
				+ "|";

		throw new NptGetValueException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */

	private static void evaluateClassException(NptSaveExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getErrorStack().getCategory()
				+ "|"
				+ e.getErrorStack().getStatus()
				+ "|";

		throw new NptSaveException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

	/**
	 * Method evaluateClassException.
	 * @param e
	 * @throws NetProvisionException
	 */

	private static void evaluateClassException(AVExceptionTypeImpl e)
		throws NetProvisionException
	{

		String errorMessage =
			"Exception error|"
				+ e.getPrimaryInterface()
				+ "|"
				+ e.getMessage()
				+ "|"
				+ e.getCategory()
				+ "|"
				+ e.getAbandonedTransactionId()
				+ "|";

		throw new AVException(
			ExceptionCode.ERR_EMBUS_NP_CONNECTOR_SERVICE,
			errorMessage, e);

	}

}
