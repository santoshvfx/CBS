//$Id: CorrelationTable.java,v 1.3 2006/08/29 23:14:13 sn4319 Exp $

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
//# Date         | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 05/09/2005   | Jinmin Ni     | Creation.
//# 05/25/2005   | Jinmin Ni     | Modified to use com.sbc.bccs.utility.database.DBConnection
//#                                instead of use com.sbc.eia.bis.nam.database.DBTransactions
//#                                Modified to get table name and expriation time from props

package com.sbc.eia.bis.persistencemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * @author jn1936
 *
 */


public class CorrelationTable
{
	//expires after this days.  by default,it set to 30 days
	private static String DEFAULT_CORRELATION_RECORD_EXPIRATION = "30";
	public static final String CORRELATION_RECORD_EXPIRATION = "CORRELATION_RECORD_EXPIRATION";
	
	//correlation table, by default, it set to "correlation"
	public static final String CORRELATION_TABLE = "CORRELATION_TABLE";
	public static final String DEFAULT_CORRELATION_TABLE = "CORRELATION";
	
	/**
	 * @see java.lang.Object#Object()
	 */
	public CorrelationTable()
	{
	}


	/**
	 * Method retrieveRow.
	 * @param props
	 * @param logger
	 * @param methodName
	 * @param clientKey
	 * @return CorrelationRow
	 * @throws SQLException
	 */
	public static CorrelationRow retrieveRow(
		Hashtable props,
		Logger logger,
		String methodName,
		String clientKey)
		throws SQLException
	{

		CorrelationRow aRow = null;
		PreparedStatement ps = null;
		ResultSet aResultSet = null;
		
		String tableName = (String) props.get(CORRELATION_TABLE);
		if(tableName == null)
		{
			tableName = DEFAULT_CORRELATION_TABLE;
		} 
		
		StringBuffer aSQLsb = new StringBuffer("select BISCONTEXT_DATA, TIME_STAMP from ");
		aSQLsb.append(tableName);
		aSQLsb.append(" where METHOD_NAME = ? and CLIENT_KEY = ? and EXPIRATION_DATE > sysdate ");
		String SQLstatement = aSQLsb.toString();
		
		String aBisContext = null;
		DBConnection aDBConn = null;
		
		logger.log(LogEventId.INFO_LEVEL_2, "SQLstatement = <" + SQLstatement + ">");
		logger.log(LogEventId.INFO_LEVEL_2, "table = <"+tableName + "> | " + "methodName=<" + methodName + "> | clientKey=<" + clientKey + ">");
		
		try
		{	
			aDBConn = getDBConnection(props,logger);
			ps = aDBConn.getConnection().prepareStatement(SQLstatement);
			ps.setString(1, methodName);
			ps.setString(2, clientKey);
			aResultSet = ps.executeQuery();
			if (aResultSet.next())
			{
				aRow = new CorrelationRow();
				aRow.setBisContextData(aResultSet.getString(1));
				aRow.setTimeStamp(aResultSet.getTimestamp(2));
			}
		}
		finally
		{
			try
			{
				ps.close();
				aResultSet.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				aDBConn.disconnect();
			}
			catch (Exception e)
			{
			}
			aDBConn = null;
		}

		return aRow;
	}

	/**
	 * Method insertRow.
	 * insertRow method will first try to insert, if failed due to existing record 
	 * it will try to update.  if both failed, throw SQLException
	 * @param props
	 * @param logger
	 * @param aRow
	 * @throws SQLException
	 */
	public static void insertRow(Hashtable props, Logger logger, CorrelationRow aRow)
		throws SQLException
	{

		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		Connection conn = null;
		
		String tableName = (String) props.get(CORRELATION_TABLE);
		if(tableName == null)
		{
			tableName = DEFAULT_CORRELATION_TABLE;	
		}
		String expiration = (String) props.get(CORRELATION_RECORD_EXPIRATION);
		if(expiration == null)
		{	
			expiration = DEFAULT_CORRELATION_RECORD_EXPIRATION;
		}
		
		StringBuffer aSQLsb = new StringBuffer("insert into ");
		aSQLsb.append(tableName);
		aSQLsb.append(
			" (METHOD_NAME,CLIENT_KEY,BISCONTEXT_DATA,TIME_STAMP,EXPIRATION_DATE) values(?,?,?,sysdate,sysdate+?)");
		String SQLstatement = aSQLsb.toString();

		logger.log(LogEventId.INFO_LEVEL_2, "SQLstatement = <" + SQLstatement + ">");
		logger.log(
			LogEventId.INFO_LEVEL_2,
			"tableName=<"
				+ tableName
				+ "> | methodName=<"
				+ aRow.getMethodName()
				+ "> | clientKey=<"
				+ aRow.getClientKey()
				+ "> | bisContext=<"
				+ aRow.getBisContextData()
				+ ">");

		try
		{
			aDBConn = getDBConnection(props,logger);
			conn = aDBConn.getConnection();
		}
		catch (SQLException e)
		{
			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"Failed to open a sql connection: "
					+ e.getMessage());
			throw e;
		}

		try
		{
			ps = conn.prepareStatement(SQLstatement);
			ps.setString(1, aRow.getMethodName());
			ps.setString(2, aRow.getClientKey());
			ps.setString(3, aRow.getBisContextData());
			ps.setString(4, expiration);
			ps.executeUpdate();

		}
		catch (SQLException e)
		{
			//record with method_name and client_key exising, try to update biscontext_data,time_stamp and expiration_date
			logger.log(LogEventId.DEBUG_LEVEL_1, "Failed to insert  - " + e.getMessage());
			
			if(e.getMessage().trim().startsWith("ORA-00001") == false)
			{
				throw e;	
			}
			
			//record with method_name and client_key existing, try to update biscontext_data,time_stamp and expiration_date
			logger.log(LogEventId.DEBUG_LEVEL_1, "Try update.....");
			StringBuffer aUpdateSQLsb = new StringBuffer("update ");
			aUpdateSQLsb.append(tableName);
			aUpdateSQLsb.append(
				" set BISCONTEXT_DATA = ? ,TIME_STAMP = sysdate,  EXPIRATION_DATE = sysdate+? where METHOD_NAME = ? and CLIENT_KEY = ?");
					
			String updateSQLstatement = aUpdateSQLsb.toString();

			logger.log(
				LogEventId.INFO_LEVEL_2,
				"updateSQLstatement = <" + updateSQLstatement + ">");
			ps = conn.prepareStatement(updateSQLstatement);
			ps.setString(1, aRow.getBisContextData());
			ps.setString(2, expiration);
			ps.setString(3, aRow.getMethodName());
			ps.setString(4, aRow.getClientKey());
			ps.executeUpdate();
		}
		finally
		{

			try
			{
				ps.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				aDBConn.disconnect();
			}
			catch (Exception e)
			{
			}
			aDBConn = null;
		}

	}
	

	private static DBConnection getDBConnection( Hashtable props, Logger logger) 
	throws SQLException
	{	
		String dataSourceName = null;
	 	String jdbcUserId = null;
	 	String jdbcPassWord = null;
	 	String jdbcUrl= null;
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
		catch ( NullPointerException e )
		{	
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined in properties file." +
				e.getMessage() );
		}
		
		logger.log(LogEventId.INFO_LEVEL_2,	"<dataSourceName>" + dataSourceName + "<jdbcUserId>" + jdbcUserId + "<jdbcUrl>" + jdbcUrl + "<jdbcDriver>" + jdbcDriver);
		return new DBConnection (dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, logger);		
	}

}
