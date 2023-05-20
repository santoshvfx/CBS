package com.sbc.eia.bis.rm.components;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.SystemFailure;

import com.sbc.eia.idl.types.Severity;
/**
 * @author dr7597
 *
 */
public class NetpDbUtil extends TranBase
{

	public Connection dbConnect(Logger alogger, Properties p)
		throws 
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		Connection conn = null;
		myLogger = alogger;
		try
		{

			
			log(LOG_DEBUG_LEVEL_1, "JdbcDriver=" + p.getProperty("NetpDRIVER"));

			log(
				LOG_DEBUG_LEVEL_1,
				"JdbcUrl=<"
					+ p.getProperty("NetpURL")
					+ "> "
					+ "NetpUSER=<"
					+ p.getProperty("NetpUSERID")
					+ "> "
					+ "NetpPASSWORD=<"
					+ p.getProperty("NetpPASSWORD")
					+ "> ");
			DriverManager.registerDriver(
				(Driver) Class
					.forName(p.getProperty("NetpDRIVER"))
					.newInstance());
			conn =
				DriverManager.getConnection(
					p.getProperty("NetpURL")+";user="
						+ p.getProperty("NetpUSERID")+";password="
						+ p.getProperty("NetpPASSWORD"));
			log(LOG_INFO_LEVEL_1, " Connection obtained");
		}
		catch (SQLException e)
		{
			log(
				LOG_DEBUG_LEVEL_1,
				"SQL Exception in dbConnect: " + e.getMessage());
			throwException(
				"RM-SYSTEMFAILURE-09999",
				"Database connection: " + e.getMessage(),
				"dbConnect()",
				Severity.UnRecoverable);
		}
		catch (IllegalAccessException e)
		{
			log(
				LOG_DEBUG_LEVEL_1,
				"IllegalAccessException Exception in dbConnect: "
					+ e.getMessage());
			throwException(
				"RM-SYSTEMFAILURE-09999",
				"Database connection: " + e.getMessage(),
				"dbConnect()",
				Severity.UnRecoverable);
		}
		catch (InstantiationException e)
		{
			log(
				LOG_DEBUG_LEVEL_1,
				"InstantiationException in dbConnect: " + e.getMessage());
			throwException(
				"RM-SYSTEMFAILURE-09999",
				"Database connection: " + e.getMessage(),
				"dbConnect()",
				Severity.UnRecoverable);
		}
		catch (ClassNotFoundException e)
		{
			log(
				LOG_DEBUG_LEVEL_1,
				"ClassNotFoundException in dbConnect: " + e.getMessage());
			throwException(
				"RM-SYSTEMFAILURE-09999",
				"Database connection: " + e.getMessage(),
				"dbConnect()",
				Severity.UnRecoverable);
		}
		return conn;
	}
}
