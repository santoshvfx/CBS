package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions;

import com.sbc.eia.idl.bis_types.BisContext;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.exceptions
 * ============================================================================
 * File name: ParserSvcException
 * ============================================================================
 * Create Date: Dec 15, 2004
 * Create Time: 3:51:04 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class ParserSvcException extends BisException
{

	/**
	 * 
	 */
	public ParserSvcException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 */
	public ParserSvcException(
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
	 * @param s
	 */
	public ParserSvcException(String s)
	{
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param aorigination
	 * @param backend
	 * @param searchDatabase
	 */
	public ParserSvcException(
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
	public ParserSvcException(
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
	 * @param srmCtxt
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 * @param backend
	 * @param searchDatabase
	 */
	public ParserSvcException(
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

}
