// $Id: RMJmsOpsHelper.java,v 1.3 2008/05/02 19:12:16 sc8468 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 06/07/2006  Rene Duka             Creation.
//#	05/02/2008  Sriram Chevuturu     RM21.10     Added two more command line options, -l and -m, to the test client 
//#												 so that it case pick up the test case data files and mdb config file 
//#                                              from user specified location, instead of default locations.


package com.sbc.eia.bis.facades.testing.rm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;

/**
 * MDB JMS helper.
 * @author: Rene Duka
 */
public class RMJmsOpsHelper {

    private static Properties aJMSProperties = new Properties();    
    private String aTransactionName = null; 
    public String aHostJNDIName = null;
    public String aConnectionFactoryName = null;
    public String aRequestQueue = null;
    
    /**
     * Method: sendMessage
     * @param hostJndiName
     * @param username
     * @param password
     * @param connFactoryname
     * @param requestQ
     * @param replyQ
     * @param waitTime
     * @param msg
     */
    public void sendMessage(
        String hostJndiName,
        String username,
        String password,
        String connFactoryname,
        String requestQ,
        String replyQ,
        long waitTime,
        String xml) {

        QueueConnection queueConnection = null;
        QueueSession queueSession = null;

        try {
            Properties prop = new Properties();

            /** /
            prop.put(
                Context.INITIAL_CONTEXT_FACTORY,
                com.sun.jndi.fscontext.RefFSContextFactory.class.getName());
            /*/
            /**/
            prop.put(
                Context.INITIAL_CONTEXT_FACTORY,
                com.sun.jndi.ldap.LdapCtxFactory.class.getName());
            /**/
            prop.put(Context.PROVIDER_URL, hostJndiName);
            InitialContext initialContext = new InitialContext(prop);
        
            QueueConnectionFactory queueConnectionFactory =
                (javax.jms.QueueConnectionFactory) initialContext.lookup(
                    connFactoryname);

            queueConnection =
                queueConnectionFactory.createQueueConnection(
                    username,
                    password);
            queueConnection.start();

            queueSession =
                queueConnection.createQueueSession(
                    false,
                    javax.jms.Session.AUTO_ACKNOWLEDGE);

            Queue queue = (Queue) initialContext.lookup(requestQ);
            QueueSender queueSender = queueSession.createSender(queue);

            TextMessage message = queueSession.createTextMessage();
            
            message.setText(xml);
            
            // set JMS Property for pVFN
            if (aTransactionName.equalsIgnoreCase("PublishValidateFacilityNotification")) {
                message.setObjectProperty(ValidateFacilityConstants.JMS_propertyName, 
                                          ValidateFacilityConstants.JMS_propertyValue);
            }
            
            try {
                setMessageProperties(message, queue);
            } catch (JMSException jmsex) {
                System.out.println("Error in setting message properties");
                System.out.println(jmsex.toString());
            }

            System.out.println("*************** Request is *************** \n");
            System.out.println("JMSCorrelationID: " + message.getJMSCorrelationID());
            System.out.println("JMSCorrelationIDAsBytes: " + message.getJMSCorrelationIDAsBytes());
            System.out.println("JMSDeliveryMode: " + message.getJMSDeliveryMode());
            System.out.println("JMSDestination: " + message.getJMSDestination());
            System.out.println("JMSExpiration: " + message.getJMSExpiration());
            System.out.println("JMSMessageID: " + message.getJMSMessageID());
            System.out.println("JMSPriority: " + message.getJMSPriority());
            System.out.println("JMSRedelivered: " + message.getJMSRedelivered());
            System.out.println("JMSReplyTo: " + message.getJMSReplyTo());
            System.out.println("JMSTimestamp: " + message.getJMSTimestamp());
            System.out.println("JMSType: " + message.getJMSType() + "\n");
            
            Enumeration e = message.getPropertyNames();
            
            while (e.hasMoreElements()) {
                String name = (String)e.nextElement();
                System.out.println(name + ": " + message.getStringProperty(name));
            }
            
            System.out.println("\n" + message.getText());

            queueSender.send(message);
            System.out.println("\n *************** Request header after sending message *************** \n");
            System.out.println("JMSCorrelationID: " + message.getJMSCorrelationID());
            System.out.println("JMSCorrelationIDAsBytes: " + message.getJMSCorrelationIDAsBytes());
            System.out.println("JMSDeliveryMode: " + message.getJMSDeliveryMode());
            System.out.println("JMSDestination: " + message.getJMSDestination());
            System.out.println("JMSExpiration: " + message.getJMSExpiration());
            System.out.println("JMSMessageID: " + message.getJMSMessageID());
            System.out.println("JMSPriority: " + message.getJMSPriority());
            System.out.println("JMSRedelivered: " + message.getJMSRedelivered());
            System.out.println("JMSReplyTo: " + message.getJMSReplyTo());
            System.out.println("JMSTimestamp: " + message.getJMSTimestamp());
            System.out.println("JMSType: " + message.getJMSType() + "\n");

        } catch (Exception inException) {
            inException.printStackTrace();
        } finally {
            if (queueConnection != null) {
                try {
                    queueSession.close();
                    queueConnection.close();
                } catch (JMSException ee) {
                }
            }
        }
    }

    /**
     * Method: sendMessage
     * @param message
     * @param destination
     * @param message Expiration Time
     */
    private void setMessageProperties(
        TextMessage message, 
        Queue destination)
        throws 
            JMSException {

        message.setJMSDestination(destination);
        message.setJMSPriority(Message.DEFAULT_PRIORITY);
        message.setJMSDeliveryMode(Message.DEFAULT_DELIVERY_MODE);
        message.setJMSRedelivered(false);
    }   

    /**
     * Method: loadProperties
     * @param message
     * @param destination
     * @param message Expiration Time
     */
    public void loadProperties(
        String aJMSPropertiesFile, 
        String aTransaction)
        throws 
            FileNotFoundException,
            IOException,
            Exception {

        aTransactionName = aTransaction;

        if (aJMSPropertiesFile != null && aJMSPropertiesFile.length() > 0) {
            System.out.println("Loading JMS configuration properties from: <"
                                + aJMSPropertiesFile
                                + ">");

            try {
                PropertiesFileLoader.read(aJMSProperties,
                                          aJMSPropertiesFile,
                                          null);
                                          
                System.out.println("Loaded " 
                                   + aJMSProperties.size()
                                   + " JMS configuration properties.");

                getJmsTransactionProperties(aTransaction);
            } 
            catch (FileNotFoundException fne) {
            	
            	try
				{            	
					File file = new File(  aJMSPropertiesFile.trim());
					FileInputStream fis = new FileInputStream(file);
					aJMSProperties.load(fis);
					
	                System.out.println("Loaded " 
	                        + aJMSProperties.size()
	                        + " JMS configuration properties.");
	
	                getJmsTransactionProperties(aTransaction);
					
				}
    			catch (FileNotFoundException fn) {
	            	throw new FileNotFoundException(
                    "\nProperties File Not Found:" + fne.getMessage());                             
    			}            	
            }             
            catch (IOException ioe) {
                throw new IOException(
                    "\nError Loading Properties File:" + ioe.getMessage());

            }
            catch (Exception e) {
                throw new Exception(
                    "\nError Loading Properties File:" + e.getMessage());
               
            }
        }
        else {
            System.out.println("Properties File is NULL/Empty.");
        }
    }

 	/**
     * Method: getHostJNDIName
     */
    public String getHostJNDIName() {
        return aHostJNDIName; 
    }

    /**
     * Method: getConnectionFactoryName
     */
    public String getConnectionFactoryName() {
        return aConnectionFactoryName; 
    }

    /**
     * Method: getConnectionFactoryName
     */
    public String getRequestQueue() {
        return aRequestQueue; 
    }
    
    /**
	 * @param aTransaction
	 */
	private void getJmsTransactionProperties(String aTransaction) {
		String aJMSPropertiesList = (String) aJMSProperties.get(aTransaction);
		if (aJMSPropertiesList != null) {
		    StringTokenizer aStringTokens = new StringTokenizer(aJMSPropertiesList, "|");
		    while (aStringTokens.hasMoreElements()) {
		        String aToken = aStringTokens.nextToken().trim();
		        if (aToken.equalsIgnoreCase("HOST_JNDI_NAME")) {
		            aHostJNDIName = aStringTokens.nextToken().trim();
		        }
		        else if (aToken.equalsIgnoreCase("CONNECTION_FACTORY_NAME")) {
		            aConnectionFactoryName = aStringTokens.nextToken().trim();
		        }
		        else if (aToken.equalsIgnoreCase("REQUEST_QUEUE")) {
		            aRequestQueue = aStringTokens.nextToken().trim();
		        }
		        else {
		            System.out.println("JMS Property " + aToken + " NOT defined.");
		        }
		    }
		}
	}

    
}
