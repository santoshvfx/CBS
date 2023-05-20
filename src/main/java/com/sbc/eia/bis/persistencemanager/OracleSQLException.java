//$Id: OracleSQLException.java,v 1.1 2005/05/23 18:00:07 jn1936 Exp $

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

import java.sql.SQLException;

/**
 * thin wrapper of SQL Exception
 *
 */
public class OracleSQLException extends PersistenceException
{
	private String aSQLState;
	
	/**
	 * Method OracleSQLException.
	 * @param i_ExceptionMessage
	 * @param i_originalException
	 */
	public OracleSQLException(String i_ExceptionMessage, SQLException i_originalException)
	{
		super(i_ExceptionMessage, i_originalException);
		aSQLState = i_originalException.getSQLState();
	}

	/**
	 * Method OracleSQLException.
	 * @param i_originalException
	 */
    public OracleSQLException(SQLException i_originalException)
    {
        super(i_originalException);
        aSQLState = i_originalException.getSQLState();
    }
    
	/**
	 * Method getSQLState.
	 * @return String
	 */
    public String getSQLState()
    {
    	 return aSQLState;
    }
}
