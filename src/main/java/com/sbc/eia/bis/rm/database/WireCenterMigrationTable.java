//# $Id: WireCenterMigrationTable.java,v 1.1 2007/11/07 19:42:23 cy4727 Exp $
//###############################################################################
//#
//#       Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date         | Author                 | Notes
//# --------------------------------------------------------------------------
//# 09/09/2007	 Changchuan Yin			  Creation

package com.sbc.eia.bis.rm.database;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.logging.LogEventId;
import java.sql.DriverManager;

public class WireCenterMigrationTable extends Table
{
	
	public WireCenterMigrationTable()
	{
		super();
	}
	
	public static Connection getDBConnection(Hashtable props) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Connection conn = null;
		String dataSourceName = null;
		String jdbcUserId = null;
		String eJdbcPassWord = null;
		String jdbcPassWord = null;
		String publicKey = null;
		String jdbcUrl = null;
		String jdbcDriver = null;
		String jdbcOption = null;
		

		try
		{
			dataSourceName = ((String) props.get("jdbcDATA_SOURCE_NAME")).trim();
			jdbcUserId = ((String) props.get("jdbcUSERID")).trim();
			eJdbcPassWord = ((String) props.get("jdbcPASSWORD")).trim();
			publicKey = ((String) props.get("OSS_PUBLIC_KEY")).trim();
			jdbcUrl = ((String) props.get("jdbcURL")).trim();
			jdbcDriver = ((String) props.get("jdbcDRIVER")).trim();
			jdbcOption = ((String) props.get("jdbcUSE_CONNECTION_POOL")).trim();
			
			Encryption enc = new Encryption();
			try {
				jdbcPassWord = 
				  enc.decodePassword(publicKey, eJdbcPassWord);
			} 
			catch (RemoteException e1) {
				  e1.printStackTrace();
			}
			
			Class.forName(jdbcDriver).newInstance();
			conn = DriverManager.getConnection(jdbcUrl, jdbcUserId , jdbcPassWord);
		    
				
		}
		catch (NullPointerException e)
		{
			throw new SQLException(
				"Get Connection failed. " + e.getMessage());
		}

		
		return conn;
	}
	
	public static DBConnection getDBConnection(Hashtable props, Logger logger) throws SQLException
	{
		String dataSourceName = null;
		String jdbcUserId = null;
		String jdbcPassWord = null;
		String jdbcUrl = null;
		String jdbcDriver = null;
		String jdbcOption = null;

		try
		{
			dataSourceName = ((String) props.get("jdbcDATA_SOURCE_NAME")).trim();
			jdbcUserId = ((String) props.get("jdbcUSERID")).trim();
			jdbcPassWord = ((String) props.get("jdbcPASSWORD")).trim();
			jdbcUrl = ((String) props.get("jdbcURL")).trim();
			jdbcDriver = ((String) props.get("jdbcDRIVER")).trim();
			jdbcOption = ((String) props.get("jdbcUSE_CONNECTION_POOL")).trim();
		}
		catch (NullPointerException e)
		{
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined in properties file." + e.getMessage());
		}

		logger.log(
			LogEventId.INFO_LEVEL_2,
			"<dataSourceName>"
				+ dataSourceName
				+ "<jdbcUserId>"
				+ jdbcUserId
				+ "<jdbcUrl>"
				+ jdbcUrl
				+ "<jdbcDriver>"
				+ jdbcDriver);
		return new DBConnection(dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, logger);
	}
	
	
}
