/*
 * Created on Apr 26, 2005
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
public class BisException extends Exception
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
	public BisException(BisContext srmCxt, Throwable ainternal, String acode, String adescription, String aorigination, int aseverity, Class backend, boolean searchDatabase)
	{
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 */
	public BisException(Throwable ainternal, String acode, String adescription, String aorigination, int aseverity)
	{
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param aorigination
	 * @param backend
	 * @param searchDatabase
	 */
	public BisException(Throwable ainternal, String aorigination, Class backend, boolean searchDatabase)
	{
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ainternal
	 * @param acode
	 * @param adescription
	 * @param aorigination
	 * @param aseverity
	 */
	public BisException(Exception ainternal, String acode, String adescription, String aorigination, int aseverity)
	{
		
		// TODO Auto-generated constructor stub
	}

	public static int RECOVERABLE = 0;
    public static int UNRECOVERABLE = 1;
	private Throwable cause;
	/**
	 * 
	 */
	public BisException()
	{
		super("Application exception encountered. Specific error message not available");
	}

	/**
	 * @param message
	 */
	public BisException(String message)
	{
		super(message);
	}

	/**
	 * @param message
	 */
	public BisException(String message, Throwable cause)
	{
		super(message);
		this.cause = cause;
	}

//	public void printStackTrace()
//	{
//		super.printStackTrace();
//		if (cause != null)
//		{
//			System.err.println("Caused by:");
//			cause.printStackTrace();
//		}
//	}
//
//	public void printStackTrace(PrintStream ps)
//	{
//		super.printStackTrace(ps);
//		if (cause != null)
//		{
//			ps.println("Caused by:");
//			cause.printStackTrace(ps);
//		}
//	}
//
//	public void printStackTrace(PrintWriter pw)
//	{
//		super.printStackTrace(pw);
//		if (cause != null)
//		{
//			pw.println("Caused by:");
//			cause.printStackTrace(pw);
//		}
//	}
//    
}
