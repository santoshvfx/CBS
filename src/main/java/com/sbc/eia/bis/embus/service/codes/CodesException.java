// $Id: CodesException.java,v 1.1 2007/01/23 15:33:45 cy4727 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.    
 */

package com.sbc.eia.bis.embus.service.codes;

import com.sbc.eia.bis.embus.service.access.ServiceException;

/**
 * @author ML2917
 *
 *
 * NetProvision Exception. 
 *
 */

public class CodesException extends ServiceException
{

	private String exceptionCode;

	/**
	 * Method NetProvisionException.
	 * @param arg1
	 * @param arg2
	 */
	
	public CodesException(String arg1, String arg2)
	{
		super(arg2);
		exceptionCode = arg1;
	}

	/**
	 * Method NetProvisionException.
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	
	public CodesException(String arg1, String arg2, Exception arg3)
	{
		super(arg2, arg3);
		
		exceptionCode = arg1;
	}
	

	/**
	 * Method getExceptionCode.
	 * @return String
	 */
	
	public String getExceptionCode()
	{
		return exceptionCode;
	}
	
	/**
	 * Method setExceptionCode.
	 * @param exceptionCode
	 */
	
	public void setExceptionCode(String exceptionCode)
	{
		this.exceptionCode = exceptionCode;
	}

}
