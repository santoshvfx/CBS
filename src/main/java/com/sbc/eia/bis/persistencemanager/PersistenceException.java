//$Id: PersistenceException.java,v 1.1 2005/05/23 18:00:07 jn1936 Exp $

//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 05/17/2005   Jinmin Ni      Creation.

package com.sbc.eia.bis.persistencemanager;

/**
 * @author jn1936
 */
import java.io.PrintStream;
import java.io.PrintWriter;

/** Description
 *  The PersistanceException class is the base for all exceptions pertaining to
 *  Persistence Managing operations.  The user should subclass this class to 
 *  create more specific service exception.  However, this class can be
 *  instantiate to indicate that a general exception.
 *  Description
 */

public class PersistenceException extends Exception
{
	private Exception m_originalException = null;


	/**
	 * @see java.lang.Object#Object()
	 */
    private PersistenceException()
    {
        super();
    }


	/**
	 * Method PersistanceException.
	 * @param i_ExceptionMessage
	 * @param i_originalException
	 */
    public PersistenceException(
        String i_ExceptionMessage,
        Exception i_originalException)
    {
        super(i_ExceptionMessage);
        setOriginalException(i_originalException);
    }


	/**
	 * @see java.lang.Throwable#Throwable(String)
	 */
    public PersistenceException(String i_ExceptionMessage)
    {
        super(i_ExceptionMessage);
    }


	/**
	 * Method PersistanceException.
	 * @param i_originalException
	 */
    public PersistenceException(Exception i_originalException)
    {
        super(i_originalException.getMessage());
        setOriginalException(i_originalException);
    }


	/**
	 * Method setOriginalException.
	 * @param originalException
	 */
    private void setOriginalException(Exception originalException)
    {
        m_originalException = originalException;
    }

	/**
	 * Method getOriginalException.
	 * @return Exception
	 */
    public Exception getOriginalException()
    {
        return m_originalException;
    }


	/**
	 * @see java.lang.Throwable#printStackTrace(PrintWriter)
	 */
    public void printStackTrace(PrintWriter s)
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace(s);
        }
        else
        {
            super.printStackTrace(s);
            if (getOriginalException() != this)
            {
                s.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace(s);
            }
        }
    }


	/**
	 * @see java.lang.Throwable#printStackTrace(PrintStream)
	 */
    public void printStackTrace(PrintStream s)
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace(s);
        }
        else
        {
            super.printStackTrace(s);
            if (getOriginalException() != this)
            {
                s.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace(s);
            }
        }
    }


	/**
	 * @see java.lang.Throwable#printStackTrace()
	 */
    public void printStackTrace()
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace();
        }
        else
        {
            super.printStackTrace();
            if (getOriginalException() != this)
            {
                System.err.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace();
            }
        }
    }
}
