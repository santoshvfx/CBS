package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptGetValueExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class NptGetValueException extends NetProvisionException
{

	NptGetValueExceptionTypeImpl exceptionObject;

	public NptGetValueException(
		String arg1,
		String arg2,
		NptGetValueExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return NptGetValueExceptionTypeImpl
	 */
	public NptGetValueExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */
	public void setExceptionObject(NptGetValueExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}

}