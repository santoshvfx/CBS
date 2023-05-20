package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVAutoTransactionExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class AVAutoTransactionException extends NetProvisionException
{

	AVAutoTransactionExceptionTypeImpl exceptionObject;

	public AVAutoTransactionException(
		String arg1,
		String arg2,
		AVAutoTransactionExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return AVAutoTransactionExceptionTypeImpl
	 */
	
	public AVAutoTransactionExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */
	
	public void setExceptionObject(AVAutoTransactionExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}

}
