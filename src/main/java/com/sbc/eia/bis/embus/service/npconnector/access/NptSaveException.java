package com.sbc.eia.bis.embus.service.npconnector.access;

import com.sbc.eia.bis.embus.service.netprovision.NetProvisionException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NptSaveExceptionTypeImpl;

/**
 * @author ML2917
 *
 */

public class NptSaveException extends NetProvisionException
{

	NptSaveExceptionTypeImpl exceptionObject;

	public NptSaveException(
		String arg1,
		String arg2,
		NptSaveExceptionTypeImpl arg3)
	{
		super(arg1, arg2);

		exceptionObject = arg3;
	}

	/**
	 * Returns the exceptionObject.
	 * @return NptSaveExceptionTypeImpl
	 */
	public NptSaveExceptionTypeImpl getExceptionObject()
	{
		return exceptionObject;
	}

	/**
	 * Sets the exceptionObject.
	 * @param exceptionObject The exceptionObject to set
	 */
	public void setExceptionObject(NptSaveExceptionTypeImpl exceptionObject)
	{
		this.exceptionObject = exceptionObject;
	}

}