package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory;

import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.common.utilities.PropertyLoader;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.idl.exception_types.ExceptionCode;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.factory
 * ============================================================================
 * File name: ParserSvcHelper
 * ============================================================================
 * Create Date: Dec 27, 2004
 * Create Time: 10:52:50 AM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class ParserSvcHelper
{
	protected Logger logger = null;
	private static Properties props = null;
	private static String propertyFile = "ParserSvc.properties";

	/**
	 * 
	 */
	public ParserSvcHelper(Logger logger) throws ParserSvcException
	{
		super();
		if (logger == null)
		{
            this.logger = new TestLogger();

		}
		else
		{
			this.logger = logger;
		}
		try
		{
			this.loadProperties();
		}
		catch (ParserSvcException pse)
		{
			throw pse;
		}
	}

	/***************************************************************************
	* Loads the properties from the property file passed as argument
	***************************************************************************/
	private void loadProperties() throws ParserSvcException
	{
		try
		{
			if (props == null)
			{
				props =
					PropertyLoader.getInstance().loadProperties(propertyFile);
			}
		}
		catch (java.io.IOException ioe)
		{
			logger.log(
				LogEventId.EXCEPTION,
				"Unable to load properties for " + propertyFile);
			logger.log(LogEventId.EXCEPTION, "Exception: " + ioe.getMessage());
			throw new ParserSvcException(
				ioe,
				LogEventId.EXCEPTION,
				"Error loading property file " + propertyFile + ".",
				"ParserSvcHelper.loadProperties",
				ParserSvcException.UNRECOVERABLE);
		}
	}

	/***************************************************************************
	* Loads appropriate ParserStrategy for the given service center string
	* as specified in the properties file.
	* @param String
	* @throws ParserSvcException
	* @return ParserStrategy
	***************************************************************************/
	public Object createStrategy(String identifier) throws ParserSvcException
	{
	    return createStrategy(identifier, props);
	}

	public Object createStrategy(String identifier, Properties properties) throws ParserSvcException
	{

        logger.log(LogEventId.DEBUG_LEVEL_1, "ParserSvcHelper.getStrategy() - loading class for: " + identifier);
		Object obj = null;

		try
		{

			Class clazz =
				this.getClass().getClassLoader().loadClass(
					(String) properties.getProperty(identifier));
			logger.log(LogEventId.DEBUG_LEVEL_1, "Got Class object: " + clazz);

			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"Creating strategy instance from class : " + clazz);
			obj = clazz.newInstance();

			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"Got strategy instance : " + obj.getClass().getName());
		}
		catch (ClassNotFoundException cnfe)
		{
			logger.log(
				LogEventId.EXCEPTION,
				"Exception encountered loading strategy for : " + identifier);
			logger.log(
				LogEventId.DEBUG_LEVEL_1,
				"Exception: " + cnfe.getMessage());
            throw new ParserSvcException(cnfe, ExceptionCode.ERR_SRM_SYSTEM_FAILURE, cnfe.getMessage(), "ParserSvcHelper.getStrategy()", ParserSvcException.UNRECOVERABLE);
		}
        catch (InstantiationException ie)
        {
            logger.log(
                LogEventId.EXCEPTION,
                "Exception encountered loading strategy for : " + identifier);
            logger.log(
                LogEventId.DEBUG_LEVEL_1,
                "Exception: " + ie.getMessage());
            throw new ParserSvcException(ie, ExceptionCode.ERR_SRM_SYSTEM_FAILURE, ie.getMessage(), "ParserSvcHelper.getStrategy()", ParserSvcException.UNRECOVERABLE);
        }
        catch (IllegalAccessException iae)
        {
            logger.log(
                LogEventId.EXCEPTION,
                "Exception encountered loading strategy for : " + identifier);
            logger.log(
                LogEventId.DEBUG_LEVEL_1,
                "Exception: " + iae.getMessage());
            throw new ParserSvcException(iae, ExceptionCode.ERR_SRM_SYSTEM_FAILURE, iae.getMessage(), "ParserSvcHelper.getStrategy()", ParserSvcException.UNRECOVERABLE);
        }

		return obj;

	}

	/**
	 * Dec 27, 2004	2:03:53 PM
	 * @param logger
	 * 
	 * 
	 */
	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}
    
    public String getRegion(String serviceCenter) throws ParserSvcException
    {
//        try
//		{
//			return Router.getuniinstance().getRouterVO(serviceCenter).getcompany();
//		}
//		catch (RouterException e)
//		{
//			// TODO Auto-generated catch block
//			throw new ParserSvcException("Unknown service center");
//		}
        
        return "SBCMIDWEST";
    }
    

}
