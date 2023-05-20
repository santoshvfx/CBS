//$Id: PersistenceDataNotFoundException.java,v 1.1 2005/05/23 18:00:07 jn1936 Exp $

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
 *  Exception for Persistence data not found in db
 */
public class PersistenceDataNotFoundException extends PersistenceException
{
	/**
	 * @see com.sbc.eia.bis.persistencemanager.PersistanceException#PersistanceException(String, Exception)
	 */
	public PersistenceDataNotFoundException(String i_ExceptionMessage, Exception i_originalException)
	{
		super(i_ExceptionMessage, i_originalException);
	}

	/**
	 * @see java.lang.Throwable#Throwable(String)
	 */
    public PersistenceDataNotFoundException(String i_ExceptionMessage)
    {
        super(i_ExceptionMessage);
    }

	/**
	 * @see com.sbc.eia.bis.persistencemanager.PersistanceException#PersistanceException(Exception)
	 */
    public PersistenceDataNotFoundException(Exception i_originalException)
    {
        super(i_originalException);
    }
}
