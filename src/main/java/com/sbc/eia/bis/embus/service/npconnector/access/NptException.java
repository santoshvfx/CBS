package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class NptException extends NetProvisionException
{

	NptExceptionTypeImpl exceptionObject;

	public NptException(String arg1, String arg2, NptExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return NptExceptionTypeImpl
	 */

	public NptExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */

	public void setExceptionObject(NptExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}

}