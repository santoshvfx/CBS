package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.AVExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class AVException extends NetProvisionException
{

	AVExceptionTypeImpl exceptionObject;

	public AVException(String arg1, String arg2, AVExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return AVExceptionTypeImpl
	 */
	
	public AVExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */
	public void setExceptionObject(AVExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}
}