package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.SystemExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class SystemException extends NetProvisionException
{

	SystemExceptionTypeImpl exceptionObject;

	public SystemException(
		String arg1,
		String arg2,
		SystemExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return SystemExceptionTypeImpl
	 */
	public SystemExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */
	public void setExceptionObject(SystemExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}

}