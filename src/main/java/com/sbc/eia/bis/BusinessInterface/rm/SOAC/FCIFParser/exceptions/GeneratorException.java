/*
 * Created on May 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions;

import com.sbc.eia.idl.bis_types.BisContext;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GeneratorException extends BisException
{

	/**
	 * @param srmCxt
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 * @param backend
	 * @param searchDatabase
	 */
	public GeneratorException(
		BisContext srmCxt,
		Throwable ainternal,
		String acode,
		String adescription,
		String aorigination,
		int aseverity,
		Class backend,
		boolean searchDatabase)
	{
		super(
			srmCxt,
			ainternal,
			acode,
			adescription,
			aorigination,
			aseverity,
			backend,
			searchDatabase);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 */
	public GeneratorException(
		Throwable ainternal,
		String acode,
		String adescription,
		String aorigination,
		int aseverity)
	{
		super(ainternal, acode, adescription, aorigination, aseverity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param aorigination
	 * @param backend
	 * @param searchDatabase
	 */
	public GeneratorException(
		Throwable ainternal,
		String aorigination,
		Class backend,
		boolean searchDatabase)
	{
		super(ainternal, aorigination, backend, searchDatabase);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 */
	public GeneratorException(
		Exception ainternal,
		String acode,
		String adescription,
		String aorigination,
		int aseverity)
	{
		super(ainternal, acode, adescription, aorigination, aseverity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public GeneratorException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public GeneratorException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GeneratorException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
