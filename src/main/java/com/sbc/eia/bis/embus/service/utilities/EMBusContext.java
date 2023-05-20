// $Id: EMBusContext.java,v 1.3 2004/03/11 19:17:17 as5472 Exp $

package com.sbc.eia.bis.embus.service.utilities;

import java.util.HashMap;

import javax.jms.JMSException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.client.Messenger;
import com.sbc.embus.common.EMBusException;
import com.sbc.embus.common.EnvironmentsManager;
import com.sbc.embus.common.MapExtractor;
import com.sbc.embus.util.xml.MapGenerator;

/**
 * @author as5472
 *
 * The EMBusContext class hides some of the details of setting up the EMBus framework for use
 * by the LIM BIS.
 */
public class EMBusContext {
	/* 
	 * ovals usps client configuration keys
	 * these keys are used to identify the portion of the XML subtree to
	 * retrieve for configuraiton information
	 */
	public static final String EMBUS_MESSENGER_CONFIG_KEY = "embus.messenger";
	public static final String EMBUS_ENVIRONMENT_CONFIG_KEY = "embus.environment";
	public static final String EMBUS_SYNCHRONOUS_REQUEST_REPLY_CONFIG_KEY = "embus.synchronousRequestReply";
	public static final String EMBUS_ASYNCHRONOUS_REQUEST_REPLY_CONFIG_KEY = "embus.asynchronousRequestReply";
	public static final String EMBUS_NOTIFICATION_CONFIG_KEY = "embus.notification.deliver";

	private String environment_name = null;

	/* the name of the ovals usps client configuration file */
	private String embus_config_file_name = null;
		
	/* the EMBUS client configuration information */
	private HashMap properties_map = null;
	private HashMap environment_map = null;
	private HashMap messenger_map = null;
	private HashMap synchronous_request_reply_map = null;
	private HashMap asynchronous_request_reply_map = null;
	private HashMap notification_map = null;

	/**
	 * @see java.lang.Object#Object()
	 */
	private EMBusContext() {
		super();
	}
	
	/**
	 * Method createMessenger.
	 * @return Messenger
	 * @throws EMBusException
	 * @throws JMSException
	 */
	public Messenger createMessenger() throws EMBusException, JMSException {
		return new Messenger(getEnvironment_name(), getMessenger_map());
	}

	
	/**
	 * Method EMBusContext.
	 * @param newBase
	 * @param newEnvironmentName
	 * @param configFileName
	 * @throws Exception
	 */
	public EMBusContext( Logger logger, String newEnvironmentName, String configFileName ) throws Exception {
		this();
		
		setEmbus_config_file_name(configFileName);
	
		if (properties_map == null) {

			properties_map = new HashMap( MapGenerator.generateMap(PropertiesFileLoader.getAsURL(getEmbus_config_file_name(), logger)) );
			logger.log(LogEventId.DEBUG_LEVEL_2, "Loaded " + embus_config_file_name + ".");
			
			setEnvironment_map(getSubsetMap(logger,EMBUS_ENVIRONMENT_CONFIG_KEY));
            
			setMessenger_map( getSubsetMap( logger, EMBUS_MESSENGER_CONFIG_KEY) );

			setSynchronous_request_reply_map(getSubsetMap(logger,EMBUS_SYNCHRONOUS_REQUEST_REPLY_CONFIG_KEY) );
			
			setAsynchronous_request_reply_map(getSubsetMap( logger, EMBUS_ASYNCHRONOUS_REQUEST_REPLY_CONFIG_KEY) );
			            
        }

		if ( getEnvironment_name() == null ) {
			setEnvironment_name(newEnvironmentName);
		}
		
		logger.log(LogEventId.AUDIT_TRAIL, "EMBusContext::setupEnvironment()|PRE");
		setupEnvironment(logger);
		logger.log(LogEventId.AUDIT_TRAIL, "EMBusContext::::setupEnvironment()|POST");
		
					
	}
	
	/**
	 * Method setupEnvironment.
	 * @throws Exception
	 */
	private void setupEnvironment( Logger logger ) throws Exception {
		
		logger.log(LogEventId.DEBUG_LEVEL_2, "Creating environment: " + environment_name + ".");
		EnvironmentsManager.createEnvironment(environment_name, environment_map);

	}

	/**
	 * Returns the embus_config_file_name.
	 * @return String
	 */
	public String getEmbus_config_file_name() {
		return embus_config_file_name;
	}

	/**
	 * Sets the embus_config_file_name.
	 * @param embus_config_file_name The embus_config_file_name to set
	 */
	public void setEmbus_config_file_name(String embus_config_file_name) {
		this.embus_config_file_name = embus_config_file_name;
	}
	
	/**
	 * Returns the environment_name.
	 * @return String
	 */
	public String getEnvironment_name() {
		return environment_name;
	}

	/**
	 * Sets the environment_name.
	 * @param environment_name The environment_name to set
	 */
	public void setEnvironment_name(String environment_name) {
		this.environment_name = environment_name;
	}

	/**
	 * Returns the environment_map.
	 * @return HashMap
	 */
	public HashMap getEnvironment_map() {
		return environment_map;
	}

	/**
	 * Returns the messenger_map.
	 * @return HashMap
	 */
	public HashMap getMessenger_map() {
		return messenger_map;
	}

	/**
	 * Returns the properties_map.
	 * @return HashMap
	 */
	public HashMap getProperties_map() {
		return properties_map;
	}

	/**
	 * Sets the environment_map.
	 * @param environment_map The environment_map to set
	 */
	public void setEnvironment_map(HashMap environment_map) {
		this.environment_map = environment_map;
	}

	/**
	 * Sets the messenger_map.
	 * @param messenger_map The messenger_map to set
	 */
	public void setMessenger_map(HashMap messenger_map) {
		this.messenger_map = messenger_map;
	}

	/**
	 * Sets the properties_map.
	 * @param properties_map The properties_map to set
	 */
	public void setProperties_map(HashMap properties_map) {
		this.properties_map = properties_map;
	}

	/**
	 * Returns the asynchronous_request_reply_map.
	 * @return HashMap
	 */
	public HashMap getAsynchronous_request_reply_map() {
		return asynchronous_request_reply_map;
	}

	/**
	 * Sets the asynchronous_request_reply_map.
	 * @param asynchronous_request_reply_map The asynchronous_request_reply_map to set
	 */
	public void setAsynchronous_request_reply_map(HashMap asynchronous_request_reply_map) {
		this.asynchronous_request_reply_map = asynchronous_request_reply_map;
	}

	/**
	 * Returns the synchronous_request_reply_map.
	 * @return HashMap
	 */
	public HashMap getSynchronous_request_reply_map() {
		return synchronous_request_reply_map;
	}

	/**
	 * Sets the synchronous_request_reply_map.
	 * @param synchronous_request_reply_map The synchronous_request_reply_map to set
	 */
	public void setSynchronous_request_reply_map(HashMap synchronous_request_reply_map) {
		this.synchronous_request_reply_map = synchronous_request_reply_map;
	}

	public HashMap getSubsetMap( Logger logger, String subsetKey ) {
        
        HashMap map = null;
        try {
    		map =  MapExtractor.subset(getProperties_map(), subsetKey);
        } catch ( Exception e ) {
            logger.log(LogEventId.INFO_LEVEL_1, "Error occured while retrieving " + subsetKey + " configuration.  " + subsetKey + " configuration will not be available for use.");
        }
        
        if ( map == null ) {
            logger.log(LogEventId.INFO_LEVEL_2, "Map extractor was unable to find + " + subsetKey + " map." );
        }
        
        return map;
	}
    
}
