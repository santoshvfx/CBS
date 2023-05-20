//$Id: OrderCorrectionSuffixTable.java,v 1.1 2008/07/29 17:09:25 ds4987 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 06/23/2008  Doris Sunga           Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.bis.nam.database.DBTransactions;
/**
 * @author ds4987
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class OrderCorrectionSuffixTable extends Table {
	private static String sqlSelect =
		"select SOAC_ORDER_NUMBER, SOAC_ORDER_CORRECTION_SUFFIX, UPDATE_DATE from ORDER_CORRECTION_SUFFIX  ";
	private static String sqlInsert = 
		"insert into ORDER_CORRECTION_SUFFIX (SOAC_ORDER_NUMBER, SOAC_ORDER_CORRECTION_SUFFIX) ";
	private static String sqlUpdate = "update ORDER_CORRECTION_SUFFIX  ";

	/**
	 * Constructor
	 */
	public OrderCorrectionSuffixTable() {
		super();
	}

	/**
	 * Method retrieveByOrderNbr.
	 * @param aOrderNbr
	 * @param aProperties
	 * @param aLogger
	 * @return OrderCorrectionSuffixRow[]
	 * @throws SQLException
	 */
	public OrderCorrectionSuffixRow retrieveByOrderNbr(
		String aOrderNbr,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "OrderCorrectionSuffixTable:retrieveByOrderNbr");
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlSelect
				+ "where upper(SOAC_ORDER_NUMBER) = "
				+ aOrderNbr.toUpperCase());
		String[] aKeyValue = new String[1];
		aKeyValue[0] = aOrderNbr.toUpperCase();
		return retrieveRows(
			sqlSelect + "where upper(SOAC_ORDER_NUMBER) = ?",
			aKeyValue,
			aProperties,
			aLogger);
	}

	/**
	 * Method insertByOrderNbr.
	 * @param aOrderNbr
	 * @param aCorrection_Suffix
	 * @param aProperties
	 * @param aLogger
	 * @return OrderCorrectionSuffixRow[]
	 * @throws SQLException
	 */
	public int insertByOrderNbr(
		String aOrderNbr,
		String aCorrection_Suffix,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"OrderCorrectionSuffixTable:insertByOrderNbr()");
		String[] aKeyValue = new String[2];
		aKeyValue[0] = aOrderNbr;
		aKeyValue[1] = aCorrection_Suffix;
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ sqlInsert
				+ "values("
				+ aOrderNbr
				+ ", "
				+ aCorrection_Suffix
				+ ")");
		// UPDATE_DATE default to system date
		return updateRow(
			sqlInsert + "values(?, ?)",
			aKeyValue,
			aProperties,
			aLogger);
	}


	/**
	 * Method updateByOrderNbr
	 * @param aOrderNbr
	 * @param aCorrection_Suffix
	 * @param aProperties
	 * @param aLogger
	 * @return OrderCorrectionSuffixRow[]
	 * @throws SQLException
	 */
	public int updateByOrderNbr(
		String aOrderNbr,
		String aCorrection_Suffix,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(
			LogEventId.INFO_LEVEL_1,
			"OrderCorrectionSuffixTable:updateByOrderNbr()");
		String[] aKeyValue = null;
		String aSQLStatement =
			sqlUpdate
				+ "set SOAC_ORDER_CORRECTION_SUFFIX = "
				+ "'" + aCorrection_Suffix + "'"
				+ ", UPDATE_DATE = SYSDATE "
				+ "where SOAC_ORDER_NUMBER = '"
				+ aOrderNbr
				+ "'";
		aLogger.log(
			LogEventId.DEBUG_LEVEL_1,
			"SQLstatement: "
				+ aSQLStatement);
		return updateRow(aSQLStatement, aKeyValue, aProperties, aLogger);
	}


	/**
	 * Method retrieveRows.
	 * @param SQLstatement
	 * @param aKeyValue
	 * @param aProperties
	 * @param aLogger
	 * @return OrderCorrectionSuffixRow[]
	 * @throws SQLException
	 */
	public OrderCorrectionSuffixRow retrieveRows(
		String SQLstatement,
		String[] aKeyValue,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "OrderCorrectionSuffixTable:retrieveRows()");

		DBTransactions aDBTranx = null;
		Connection aDBConn = null;				
		ResultSet rs = null;
		PreparedStatement ps = null;
		OrderCorrectionSuffixRow resultRow = null;
		
		int reTryCnt = 0;
		
		aLogger.log( LogEventId.REMOTE_CALL, (String)aProperties.get( "jdbcURL" ),
                (String)aProperties.get( "jdbcUSERID" ),
                (String)aProperties.get( "jdbcUSERID" ),
                "OrderCorrectionSuffixTable::updateRow()" ) ;		
		while (true) {
			try {		
				aDBTranx = new DBTransactions();
				aDBConn = aDBTranx.getConnection(aProperties, aLogger);
				ps = aDBConn.prepareStatement(SQLstatement);

				for (int i = 0; i < aKeyValue.length; i++) {
					ps.setString(i + 1, aKeyValue[i]);
				}
				rs = ps.executeQuery();
				if (rs.next())
				{
					aLogger.log(
							LogEventId.DEBUG_LEVEL_1,
							"SQLResult: "
								+ rs.getString(1)
								+ "|"
								+ rs.getString(2)
								+ "|"
								+ rs.getString(3));
					resultRow = new OrderCorrectionSuffixRow();				
					resultRow.setRow(rs);
				} else
					aLogger.log(
							LogEventId.DEBUG_LEVEL_1,
							"SQLResult: <no data found>");							
			} catch (StaleConnectionException sce) {
				//retry only once
				if (reTryCnt == 0) {
					reTryCnt++;
					aDBConn = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw sce;
			} catch (Exception e) {
				aLogger.log(
					LogEventId.ERROR,
					"Non-fatal SQLError: [" + e.getMessage() + "]");
				throw new SQLException("No data found. Okay to proceed");
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (Exception e) {
				}
				try {
				    if (ps != null)
					   ps.close();
				} catch (Exception e) {
				}				    
				try {
					aDBTranx.disconnect();
				} catch (Exception e) {
				}
				aDBConn = null;
				
				aLogger.log( LogEventId.REMOTE_RETURN, (String)aProperties.get( "jdbcURL" ),
                        (String)aProperties.get( "jdbcUSERID" ),
                        (String)aProperties.get( "jdbcUSERID" ),
                        "OrderCorrectionSuffixTable::updateRow()" ) ;				
			}

			return resultRow;
		}
	}

	/**
	 * Method updateRow.
	 * @param SQLstatement
	 * @param aKeyValue
	 * @param aProperties
	 * @param aLogger
	 * @return OrderCorrectionSuffixRow[]
	 * @throws SQLException
	 */
	public int updateRow(
		String SQLstatement,
		String[] aKeyValue,
		Hashtable aProperties,
		Logger aLogger)
		throws SQLException, StaleConnectionException {
		aLogger.log(LogEventId.INFO_LEVEL_1, "OrderCorrectionSuffixTable:updateRow()");

		DBTransactions aDBTranx = null;
		Connection aDBConn = null;			
		PreparedStatement ps = null;
		int returnValue = 0;
		int reTryCnt = 0;
		
		aLogger.log( LogEventId.REMOTE_CALL, (String)aProperties.get( "jdbcURL" ),
                (String)aProperties.get( "jdbcUSERID" ),
                (String)aProperties.get( "jdbcUSERID" ),
                "OrderCorrectionSuffixTable::updateRow()" ) ;
		
		while (true) {
			try 
			{	        
				aDBTranx = new DBTransactions();
				aDBConn = aDBTranx.getConnection(aProperties, aLogger);
				ps = aDBConn.prepareStatement(SQLstatement);								
				
				if (aKeyValue != null) 
				{
					for (int i = 0; i < aKeyValue.length; i++) 
					{
						ps.setString(i + 1, aKeyValue[i]);
					}
				}
				returnValue = ps.executeUpdate();	
			} 
			catch (StaleConnectionException sce) 
			{
				//retry only once
				if (reTryCnt == 0) 
				{
					reTryCnt++;
					aDBConn = null;
					aLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
				}
				throw sce;
			} 
		    catch (SQLException se) {
			    aLogger.log(
					LogEventId.ERROR,
					"Non-fatal SQLError: [" + se.getMessage() +  "] : ORDER_CORRECTION_SUFFIX, "
					+ "SQL State [" + se.getSQLState() + "]");		
		        throw se;
		    } 
		    
			catch (Exception e) 
			{
				aLogger.log(
					LogEventId.ERROR,
					"Non-fatal SQLError: [" + e.getMessage() + "]");
				throw new SQLException("Update failed. Okay to proceed");
			} 
			finally 
			{
				try 
				{
					if (ps != null)
						ps.close();
				} 
				catch (Exception e) 
				{
				}
				try 
				{
					aDBTranx.disconnect();
				} 
				catch (Exception e) 
				{
				}
				aDBConn = null;
				
				aLogger.log( LogEventId.REMOTE_RETURN, (String)aProperties.get( "jdbcURL" ),
                        (String)aProperties.get( "jdbcUSERID" ),
                        (String)aProperties.get( "jdbcUSERID" ),
                        "OrderCorrectionSuffixTable::updateRow()" ) ;				
			}

			return returnValue;
		}
	}

	
	/**
	 * Method UpdateOrderCorrectionSuffix.
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix	
	 * @throws BusinessViolation  
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	public void UpdateOrderCorrectionSuffix(
			 BisContext aContext,
			 String aSOACServiceOrderNumber,
		     String aSOACServiceOrderCorrectionSuffix,
		     Hashtable aProperties,
			 Logger aLogger)
			 throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, 
			 SystemFailure, DataNotFound, NotImplemented
	{
		String myMethodNameName = "SendTNAssignmentOrder.UpdateOrderCorrectionSuffix()";
		String aTextMessage = null;
		aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);		
		
		int aReturnValue = 0;		
		try
		{
            // Update the order suffix in ORDER_CORRECTION_SUFFIX table 		
			aReturnValue = updateByOrderNbr(aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix, 
													aProperties, aLogger);
	        if (aReturnValue ==  1) 
	        {   
	           // Successful update
	        	aLogger.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber 
	                                               + "> Order Correction Suffix <" + aSOACServiceOrderCorrectionSuffix
	                                               + "> UPDATED SUCCESSFULLY in Order Correction Suffix table. ");
	           
	        } else {
	          // record does not exist, need to insert the record 	
	           aReturnValue = insertByOrderNbr(aSOACServiceOrderNumber, 
	        										aSOACServiceOrderCorrectionSuffix, aProperties, aLogger);
	           if (aReturnValue ==  1) 
	           {
	        	   aLogger.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber 
	                                                   + "> Order Correction Suffix <" + aSOACServiceOrderCorrectionSuffix 
	                                                   + "> ADDED SUCCESSFULLY in Order Correction Suffix table.");
	           }	        	
	        }				
		}
		catch (StaleConnectionException e)
		{
			// Connection failure, log System Failure
			aTextMessage = "Non-fatal SQLError: Connection failed on Order_Correction_Suffix.  Okay to proceed.";
			String[] aTextValues = new String[] {aTextMessage};
			
			ExceptionBuilder.parseException(
					aContext,
					(String) aProperties.get(
						"EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
					null,
					e.getSQLState(),
					e.getMessage(),
					false,
					ExceptionBuilderRule.NO_DEFAULT,
					aTextValues,
					null,
					aLogger,
					null,
					null,
					null);			
		}
		catch (SQLException e) 
		{		
			// missing table, field, or permission, log System Failure
			aTextMessage = "Non-fatal SQLError: Update failed on Order_Correction_Suffix.  Okay to proceed.";
			String[] aTextValues = new String[] {aTextMessage};
			
			ExceptionBuilder.parseException(
					aContext,
					(String) aProperties.get(
						"EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
					null,
					e.getSQLState(),
					e.getMessage(),
					false,
					ExceptionBuilderRule.NO_DEFAULT,
					aTextValues,
					null,
					aLogger,
					null,
					null,
					null);
			
		}
		finally 
		{
			aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
		}
	}
	
	/**
	 * This Method is for updating the return message that is being 
	 * sent to OMS with the latest Correction Suffix retrieved from 
	 * the database using the Order Number coming from SOA. If for some 
	 * reason the retrieval from the database fails, then we will send 
	 * the Correction Suffix that we got from SOA itself without 
	 * any modifications.
	 * 
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aProperties
	 * @param aLogger
	 * @return
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 * 
	 * @author Sriram Chevuturu
	 */
	public String RetrieveOrderCorrectionSuffix(
			 BisContext aContext,
			 String aSOACServiceOrderNumber,
		     String aSOACServiceOrderCorrectionSuffix,
		     Hashtable aProperties,
			 Logger aLogger)
			 throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, 
			 SystemFailure, DataNotFound, NotImplemented
	{
		String myMethodNameName = "SendTNAssignmentOrder.RetrieveOrderCorrectionSuffix()";
		String aTextMessage = null;
		aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);		

		OrderCorrectionSuffixTable aOrderCorrectionSuffixTable = new OrderCorrectionSuffixTable();
		OrderCorrectionSuffixRow aOrderCorrectionSuffixRow = null;
			
		try
		{
           // Update the order suffix in ORDER_CORRECTION_SUFFIX table 		
			//retrieve the correction suffix given the order number
			aOrderCorrectionSuffixRow = aOrderCorrectionSuffixTable
					.retrieveByOrderNbr(aSOACServiceOrderNumber, aProperties,
							aLogger);
			
		}
		catch (StaleConnectionException e)
		{
			// Connection failure, log System Failure
			aTextMessage = "Non-fatal SQLError: Connection failed on Order_Correction_Suffix.  Okay to proceed.";
			String[] aTextValues = new String[] {aTextMessage};
			
			ExceptionBuilder.parseException(
					aContext,
					(String) aProperties.get(
						"EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
					null,
					e.getSQLState(),
					e.getMessage(),
					false,
					ExceptionBuilderRule.NO_DEFAULT,
					aTextValues,
					null,
					aLogger,
					null,
					null,
					null);			
		}
		catch (SQLException e) 
		{		
			// missing table, field, or permission, log System Failure
			aTextMessage = "Non-fatal SQLError: Retrieval failed on Order_Correction_Suffix.  Okay to proceed.";
			String[] aTextValues = new String[] {aTextMessage};
			
			ExceptionBuilder.parseException(
					aContext,
					(String) aProperties.get(
						"EXCEPTION_BUILDER_ORACLE_RULE_FILE"),
					null,
					e.getSQLState(),
					e.getMessage(),
					false,
					ExceptionBuilderRule.NO_DEFAULT,
					aTextValues,
					null,
					aLogger,
					null,
					null,
					null);
			
		}
		finally 
		{
			aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
		}

		
		if(aOrderCorrectionSuffixRow != null)
			return aOrderCorrectionSuffixRow.getSOAC_CORRECTION_SUFFIX();
			
		//else return the old correction suffix itself.
		else
			return aSOACServiceOrderCorrectionSuffix;

	}
}
