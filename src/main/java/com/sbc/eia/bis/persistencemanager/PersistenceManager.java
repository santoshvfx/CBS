//$Id: PersistenceManager.java,v 1.3 2005/07/25 19:33:50 jn1936 Exp $

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
//# Date        | Author        | Notes
//# ------------------------------------------------------------------------------------
//# 05/13/2005   Jinmin Ni       Creation.
//# 05/25/2005   Jinmin Ni       Modified to remove logging that has been done in correlationTable.java
//#                              Changed Utility to Logger as class var
//# 07/22/2005   Jinmin Ni       Changed the DEFAULT_DELIMITER to "^" insteand of "|" due to the LoggingInformation
//#                              value in biscontext contains a "|" as data.  Also, change to set the tag value of bisContext 
//#                              to empty string instead of null when no aValue in db. 

package com.sbc.eia.bis.persistencemanager;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.types.ObjectProperty;

/**
 * @author jn1936
 *
 */
public class PersistenceManager 
{
	public final static String DEFAULT_DELIMITER = "^";
	private Logger logger;
	private Hashtable props;
	

	/**
	 * Method PersistenceManager.
	 * @param props
	 * @param logger
	 */
	public PersistenceManager(Hashtable props, Logger logger)
	{
		this.props = props;	
		this.logger = logger;
	}
	
	/**
	 * Method persistData.
	 * This method will create key string from array and call correlationTable.insertRow to store persistence data
	 * OracleSQLException which is subclass of PersistenceException will be thrown if SQLException encounted.
	 * PersistenceExcepiotn will be thrown for general exception other than SQLException including NullpointerException for
	 * persistence data being null
	 * @param methodName
	 * @param aContext
	 * @param aClientKey
	 * @throws PersistenceException
	 */
	public void persistData(String methodName, BisContext aContext, String[] aClientKey)
		throws PersistenceException
	{
		logger.log(LogEventId.DEBUG_LEVEL_1, "> PersistenceManager::persistData() ");
		
		try
		{
			String formattedClientKey = createKey(aClientKey);
		
			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"PersistenceData - MethodName=<"
					+ methodName
					+ ">"
					+ " | BisContext=<"
					+ new BisContextBisHelper(aContext).toString()
					+ ">"
					+ " | ClientKey=<"
					+ formattedClientKey
					+ ">");

			CorrelationRow aRow = new CorrelationRow();
			aRow.setMethodName(methodName);
			aRow.setClientKey(formattedClientKey);
			aRow.setBisContextData(getBisContextString(aContext));
			try
			{
				CorrelationTable.insertRow(props, logger, aRow);
			}
			catch (SQLException e)
			{
				throw new OracleSQLException("Failed to insert persistence data into database table : " + e.getMessage(), e);
			}
		}
		catch(OracleSQLException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new PersistenceException("Failed to persist data: " + (e.getMessage() == null?e.toString():e.getMessage()), e);	
		}
		finally
		{
			logger.log(LogEventId.DEBUG_LEVEL_1, "< PersistenceManager::persistData() ");	
		}
	}
	/**
	 * Method retrieveData.
	 * This method will create key string from array and call correlationTable.retrieveRow to find persistence data
	 * OracleSQLException which is subclass of PersistenceException will be thrown if SQLException encounted.
	 * PersistenceDataNotFoundException which extends PersistenceException will be thrown if persistence data is not found in DB
	 * PersistenceExcepiotn will be thrown for general exception other than SQLException including NullpointerException for
	 * persistence data being null
	 * @param methodName
	 * @param clientKey
	 * @return PersistenceData
	 * @throws PersistenceException
	 */
	public PersistenceData retrieveData(String methodName, String[] aClientKey)
		throws PersistenceException
			
	{
		logger.log(LogEventId.DEBUG_LEVEL_1, "> PersistenceManager::retrieveData() ");
		CorrelationRow aCorrelationRow = null;
		PersistenceData aPersistenceData = null;
		try
		{	
			String formattedClientKey = createKey(aClientKey);
			
			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"LookupData - MethodName=<"
					+ methodName
					+ ">"
					+ " | ClientKey=<"
					+ formattedClientKey
					+ ">");

			try
			{
				aCorrelationRow =
					CorrelationTable.retrieveRow(props, logger, methodName, formattedClientKey);

			}
			catch(SQLException e)
			{
				throw new OracleSQLException("Failed to retrieve persistence data from database table: " + e.getMessage(), e);
			}
			if (aCorrelationRow == null)
			{
				throw new PersistenceDataNotFoundException("Persistence record not found in the database table");
			}

			logger.log(
				LogEventId.INFO_LEVEL_1,
				"persistent data found: BisContext=<"
					+ aCorrelationRow.getBisContextData()
					+ ">"
					+ " | TimeStamp=<"
					+ aCorrelationRow.getTimeStamp()
					+ ">" );
			aPersistenceData =
				new PersistenceData(
					getBisContextObject(aCorrelationRow.getBisContextData()),
					aCorrelationRow.getTimeStamp());
		}
		catch (OracleSQLException e)
		{
			throw e;	
		}
		catch (PersistenceDataNotFoundException e)
		{
			throw e;	
		}
		catch (Exception e)
		{
			throw new PersistenceException("Failed to retrieve persistence data: " + (e.getMessage() == null?e.toString():e.getMessage()), e);
		}
		finally
		{		
			logger.log(LogEventId.DEBUG_LEVEL_1, "< PersistenceManager::retrieveData() ");
		}
		return aPersistenceData;
	}
	/**
	 * Method createKey.
	 * @param aClientKey
	 * @return String
	 */
	private String createKey(String[] aClientKey)
	{
		StringBuffer key = new StringBuffer();
		
		for(int i = 0; i< aClientKey.length; i++)
		{
			key.append((String)aClientKey[i]);
			key.append(DEFAULT_DELIMITER);		
		}		
		return key.toString();		
	}
	
	/**
	 * Method getBisContextString.
	 * @param aContext
	 * @return String
	 */
	private String getBisContextString(BisContext aContext)
	{
		StringBuffer bisContextString = new StringBuffer();
		ObjectPropertyManager aContextOPM =
			new ObjectPropertyManager(aContext.aProperties);
		 ObjectProperty[] aContextList = aContextOPM.toArray();
		 ObjectProperty aObjectProperty;
		 for(int index = 0; index < aContextList.length; index++)
		 {
		 	aObjectProperty = aContextList[index];	
		 	bisContextString.append(aObjectProperty.aTag);
		 	bisContextString.append(DEFAULT_DELIMITER);
		 	bisContextString.append(aObjectProperty.aValue);
		 	bisContextString.append(DEFAULT_DELIMITER);
		 }
		return bisContextString.toString();
	}
	
	/**
	 * Method getBisContextObject.
	 * @param aBisContext_data
	 * @return BisContext
	 */
	private BisContext getBisContextObject(String aBisContext_data)
	{
		ObjectPropertyManager aContextOPM =
			new ObjectPropertyManager();
		StringTokenizer aTokenizer = new StringTokenizer(aBisContext_data,DEFAULT_DELIMITER);	
		String aTag = null;

		while(aTokenizer.hasMoreElements())
		{
			aTag = aTokenizer.nextToken(DEFAULT_DELIMITER);
			aContextOPM.add(aTag,aTokenizer.hasMoreElements() ? aTokenizer.nextToken(DEFAULT_DELIMITER) : "");
				
		}
		return new BisContext(aContextOPM.toArray());	
	}
}

