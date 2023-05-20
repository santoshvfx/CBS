// $Id: TestRMCaseBase.java,v 1.1 2005/05/09 18:47:40 jp2854 Exp $
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
//# Date      | Author			  | Notes
//# --------------------------------------------------------------------
//#	6/08/2004   Stevan Dunkin       RM 9  	 Changed URL from separate Host and Port entry to 
//#											 a single Corbaloc URL scheme. 
//# 2/10/2004   Jinmin Ni           Add xml validation to soapi testing
//#								    Moved common code for soapi testing from individual transaction
//#									to this abstract class.
//#	06/06/2005  mk3198				Modifications for soap via ldap.
//# 01/17/2005  jp2854              XML test client.
//#	05/02/2008  Sriram Chevuturu    RM21.10  Added two more command line options, -l and -m, to the test client 
//#											 so that it case pick up the test case data files and mdb config file 
//#                                          from user specified location, instead of default locations.


package com.sbc.eia.bis.facades.testing.rm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.ejb.EJBObject;
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
import javax.rmi.PortableRemoteObject;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.rm.ejb.RmHome;
import com.sbc.eia.bis.facades.testing.InitialContextFactory;
import com.sbc.eia.bis.facades.testing.InitialContextFactoryException;
import com.sbc.eia.bis.facades.testing.TestCaseBase;
import com.sbc.eia.bis.facades.testing.TestCaseException;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.XMLCheck;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.embus.common.MessageConstants;
import com.sbc.mif.vidl.midlet.utils.Util;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBReader;
import com.sbc.vicunalite.vaxb.VAXBWriter;
import com.sun.jndi.ldap.LdapCtxFactory;


/**
 * Insert the type's description here.
 * Creation date: (5/24/01 4:54:31 PM)
 * @author: Sam Lok
 */
public abstract class TestRMCaseBase extends TestCaseBase {
 		
	protected String propertiesRMFile = "c:/rm.properties";
	protected String rmPropertiesFile = "rm.properties";
	
	/*******************************************
	 *********used for SOAP PROXY***************/
//	protected Properties clientPropertie = null; 	
	protected final String SOAP_ENVELOP_HEADER =
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+ "\n<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\""
		+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
		+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
	protected final String SOAP_ENVELOP_FOOTER = "</soap:Body>\n</soap:Envelope>";
		/**************for XML validation ************/
	private static final String XSD_FILE = "RM.xsd";
	private String requestHeaderXSD = " xsi:schemaLocation=\"http://schemas.xmlsoap.org/soap/envelope/ " + XSD_FILE +"\"";
	private String responseHeaderXSD= " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://schemas.xmlsoap.org/soap/envelope/ " + XSD_FILE+ "\"";
	
//	XML testClient
	protected Exception responseError = null ;
	protected boolean isXMLTestData = false;
	protected String requestXML = null;
	
	/**
	 * TestCaseBase constructor comment.
	*/
	public TestRMCaseBase() {
		super();
		setMyMethodName(this.getClass().getName());
		System.setProperty("VAXB_JDOM_VER", "1.0beta9");
	}
	
	
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void displayProxyResult();
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void displayResult();
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void execute()
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound;
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void execute(EJBObject remote)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			RemoteException;
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void init(String paramList);
	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void init(String paramList, String propertiesFile);

	/**
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void initMessage(String paramList, Hashtable propsFile);
    
    	
	/* PROXY CHANGE
	 *
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	protected abstract void execute(Hashtable propsFile)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound;

	protected abstract void init(String paramList, Hashtable propsFile);

	/**
	 * 
	 *  processCase for EJB testing  
	 * 
	 **/
	public void processCase(String paramList) throws TestCaseException {
		System.out.println(
			"Testing " + myMethodName + "\n TestCase:: <" + paramList + ">");
		TestClient.log.init(myMethodName);

		init(paramList); // to be implemented by derived classes

		if (initialContext == null) {
			TestClient.log.printLog("Get a new initialContext...");
			InitialContextFactory iFactory =
				new InitialContextFactory(TestClient.bisProviderURL);

			try {
				initialContext = iFactory.getInitialContext();
			}
			catch (InitialContextFactoryException i) {
				TestClient.log.printLog("Error in InitialContext lookup");
				throw new TestCaseException(
					"Error in InitialContext lookup: " + i.getMessage());
			}
		}

		// Now run the transaction

		try {
			if (beanHome == null) {
				TestClient.log.printLog("Lookup bisHome...");
				beanHome = initialContext.lookup(TestClient.bisHome);
			}

			StringTokenizer aTokenizer =
				new StringTokenizer(TestClient.bisHome, "-");
			String bisName = TestClient.nextToken(aTokenizer);

			TestClient.log.printLog("Narrow beanHome to RmHome");
			RmHome rmhome =
				(RmHome) PortableRemoteObject.narrow(beanHome, RmHome.class);

			TestClient.log.printLog("Calling rmhome.create()...");
			Rm rmremote = rmhome.create();

			TestClient.log.printLog("calling execute()...");
			execute(rmremote); // to be implemented by derived classes
		}
		catch (Exception e) {
			logExceptionData( e) ;
		}

		displayResult(); // to be implemented by derived classes
		TestClient.log.printLog("ending the transaction");
	}

	/**
	 * 
	 *  processCase for standalone testing  
	 * 
	 **/

	public void processCase(String paramList, String propertiesFile)
		throws TestCaseException {
		System.out.println("Testing " + myMethodName + " <" + paramList + ">");
		TestClient.log.init(myMethodName);

		// Default rm.properties file

		if ((propertiesFile == null) | (propertiesFile == "")) {
			propertiesFile = propertiesRMFile;
		}

		init(paramList, propertiesFile);
		// to be implemented by derived classes

		// Now run the transaction
		try {

			TestClient.log.printLog("calling execute()...");
			execute();

		}
		catch (Exception e) {
			logExceptionData( e) ;
		}		

		displayResult(); // to be implemented by derived classes
		TestClient.log.printLog("ending the transaction");
	}

	/* PROXY CHANGE
	 *
	 * Insert the method's description here.
	 * Creation date: (5/24/01 5:14:32 PM)
	 */
	public void processCase(String paramList, Hashtable props)
		throws TestCaseException {

		System.out.println(
			"Testing " + myMethodName + " \nTestCase:: < " + paramList + " >");
		TestClient.log.init(myMethodName);

		init(paramList, props); // to be implemented by derived classes

		try {
			TestClient.log.printLog("calling execute()...");
			execute(props);
		}
		catch (Exception e) {
			logExceptionData( e) ;
		}

		//displayResult() ;									// to be implemented by derived classes
		displayProxyResult();
		TestClient.log.printLog("ending the transaction");

	}
	
	public void processJmxCase(String paramList) throws TestCaseException {
		System.out.println(
			"Testing " + myMethodName + "\n TestCase:: <" + paramList + ">");
		TestClient.log.init(myMethodName);

		init(paramList); // to be implemented by derived classes
		
		String lookupType = TestClient.lookupType;
        String lookupName = "n/a";
        String homeJndi = null;
        String clusterName = null;
        String host = null;
        
        StringTokenizer st = new StringTokenizer(TestClient.bisHome, "/");
        
        if (st.countTokens() > 1){
        	st.nextToken(); //skip
            st.nextToken(); //skip
            clusterName=st.nextToken();
            homeJndi=st.nextToken();
        }
        else {
        	clusterName = "n/a";
        	homeJndi=TestClient.bisHome;
        }
        
        StringTokenizer st1 = new StringTokenizer(TestClient.bisProviderURL, ":"); 
        st1.nextToken();        	
        host=st1.nextToken();
        	
        if (lookupType.equalsIgnoreCase("cluster")) {
        	lookupName = clusterName;
        }
        else if (lookupType.equalsIgnoreCase("server")) {
        	lookupName = TestClient.serverName;
        }
       
        String port = TestClient.port;
        String mBeanIdentifier = TestClient.mbeanIdentifier;
       
        String soapRequestTimeOut = TestClient.jmxTimeout;
        
        StringTokenizer st2 = new StringTokenizer(paramList, "|");
        Properties props = new Properties();
        
        while(st2.hasMoreTokens()){
        	if(st2.nextToken().equalsIgnoreCase("Application")) {
        		props.put("Application", st2.nextToken());
        		break;
        	}
        }
              
        if (!myMethodName.equals("ping") && !myMethodName.equals("selfTest")) {
            System.err.println("Only Ping and selfTest are supported");
            return;
        }

        TestClient.log.printLog("");
        TestClient.log.printLog("Running...");
        TestClient.log.printLog("Lookup type: " + lookupType);
        TestClient.log.printLog("Lookup name: " + lookupName);
        TestClient.log.printLog("Method: " + myMethodName);
        TestClient.log.printLog("Host: " + host);
        TestClient.log.printLog("Port: " + port);
        TestClient.log.printLog("MBeanIdentifier: " + mBeanIdentifier);
        TestClient.log.printLog("Home JNDI: " + homeJndi);
        TestClient.log.printLog("SOAP request time out (seconds): " + soapRequestTimeOut);
 
       
        JMXTestClient t = new JMXTestClient();
        t.setLookupType(lookupType);
        t.setLookupName(lookupName);
        t.setMethod(myMethodName);
        t.setData(props);
        t.setHost(host);
        t.setPort(port);
        t.setMBeanIdentifier(mBeanIdentifier);
        t.setHomeJndi(homeJndi);
        t.setSoapRequestTimeOut(soapRequestTimeOut);  
        
        try {
        	t.runJMXTestClient();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        displayResult(); // to be implemented by derived classes
		TestClient.log.printLog("ending the transaction");
    }

    
	/**
	 * 
	 *  processMessage for MDB testing  
	 * 
	 **/
	public void processMessage(String paramList, Hashtable props) throws TestCaseException
	{
		System.out.println("Testing " + myMethodName + "\n TestCase:: <" + paramList + ">");
		TestClient.log.init(myMethodName);

		initMessage(paramList, props); // to be implemented by derived classes

		displayResult(); // to be implemented by derived classes
		TestClient.log.printLog("ending the transaction");
	}
    
    
    /*******************************************************
     * *********************SOAP PROXY**********************/

    
	/**
	* initSoapMessage
	* @param paramList
	*/
	protected abstract void initSoapMessage(String paramList, Properties props);

    /**
     * processSoapMessage for SOAP PROXY testing
     * @param paramList
     * @throws TestCaseException
     */
    public void processSoapMessage(String paramList, Properties props) throws TestCaseException
    {
        System.out.println("Testing " + myMethodName + "\n TestCase:: <" + paramList + ">");
        TestClient.log.init(myMethodName);

        initSoapMessage(paramList, props); // to be implemented by derived classes

        displayResult(); // to be implemented by derived classes
        TestClient.log.printLog("ending the transaction");
    }
    
	protected String getSoapMsgHdr(String methodName) {
			return "\n<soap:Header> \n <embus:MessageHeader xmlns:embus=\"urn:soap.embus.sbc.com\">"
			+ "\n  <embus:MessageTag>"
			+ methodName
			+ "</embus:MessageTag>"
			+ "\n  <embus:ApplicationID>generated</embus:ApplicationID>"
			+ "\n  <embus:LoggingKey>NON</embus:LoggingKey>"
			+ "\n </embus:MessageHeader>"
			+ "\n</soap:Header>"
			+ "\n<soap:Body>";
		}
		
	/**
	 * @param message
	 */
	protected void setMessageProperties(
		TextMessage message,
		Queue replyQ,
		String methodName)
		throws JMSException
	{
		message.setStringProperty(MessageConstants.MESSAGE_TAG, methodName);
		message.setLongProperty(
			MessageConstants.RESPONSE_MESSAGE_EXPIRATION,
			3000);
		message.setStringProperty(
			MessageConstants.APPLICATION_ID,
			this.aContext.aProperties[3].aValue);
		message.setStringProperty(MessageConstants.MESSAGING_MODE, "request");
		message.setJMSReplyTo(replyQ);
		message.setStringProperty(Util.MIDLET_PROTOCOL, "SOAP");
	}
	/**
	 * @param xmlResponse
	 * @return String
	 */
	protected String addSOAPTagstoXML(String xmlResponse,String methodName) {
		int index = xmlResponse.indexOf("?>", xmlResponse.indexOf("<?") + 1);
		xmlResponse =
			SOAP_ENVELOP_HEADER
				+ getSoapMsgHdr(methodName)
				+ xmlResponse.substring(index + 2)
				+ SOAP_ENVELOP_FOOTER;
		return xmlResponse;
	}
	
	
	/**
		 * sendMessage
		 * @param hostJndiName
		 * @param username
		 * @param password
		 * @param connFactoryname
		 * @param requestQ
		 * @param replyQ 
		 * @param msg
		 */
		protected void sendMessage(Hashtable properties,
									String methodName,
									String xml) {

			QueueConnection queueConnection = null;
			QueueSession queueSession = null;

			try {
			Properties prop = new Properties();

				prop.put(Context.INITIAL_CONTEXT_FACTORY,LdapCtxFactory.class.getName());

				prop.put(Context.PROVIDER_URL, (String)properties.get("SOAFI_LDAP"));
				InitialContext initialContext = new InitialContext(prop);
				
								QueueConnectionFactory queueConnectionFactory =
					(javax.jms.QueueConnectionFactory) initialContext.lookup((String)properties.get("CONNECTION_FACTORY"));

				String decryptedPwd=(new Encryption()).decodePassword((String)properties.get("OSS_PUBLIC_KEY"), (String)properties.get("PASSWORD"));

				queueConnection = queueConnectionFactory.createQueueConnection((String)properties.get("USER_NAME"), decryptedPwd);
				queueConnection.start();

				queueSession =
					queueConnection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
				
				Queue queue = (Queue) initialContext.lookup((String)properties.get("REQUESET_QUEUE"));
				String replyTo = (String)properties.get("REPLY_TO_QUEUE");
				
				Queue replyToQueue;
				if(replyTo==null || replyTo.trim().equalsIgnoreCase("temp") || replyTo.trim().length() <=0 )
				{
					replyToQueue = queueSession.createTemporaryQueue();
				}
				else
				{
					replyToQueue = (Queue) initialContext.lookup(replyTo);
					
				}
				QueueSender queueSender = queueSession.createSender(queue);
				QueueReceiver queueReceiver = queueSession.createReceiver(replyToQueue);

				TextMessage message = queueSession.createTextMessage();
				message.setText(xml);
				try {
					setMessageProperties(message,replyToQueue,methodName);
				}
				catch (JMSException jmsex) {
					TestClient.log.printLog("Error in setting message properties");
					TestClient.log.printLog(jmsex.toString());
				}
				XMLCheck aXMLCheck = new XMLCheck();
				String xsdFile = (String)properties.get("XSD_FILES_DIRECTORY");
				if (xsdFile != null && xsdFile.trim().length() > 0)
				{
					String tempXML = message.getText();
					int startIndex = tempXML.indexOf("<soap:Envelope");
					int endIndex = tempXML.indexOf(">", startIndex + 1);

					tempXML =
						tempXML.substring(0, endIndex)
							+ requestHeaderXSD
							+ tempXML.substring(endIndex);

					boolean isValid =
						aXMLCheck.validate(
							true,
							false,
							xsdFile,
							XSD_FILE,
							tempXML);
					System.out.println("input xml is: " + isValid);
					TestClient.log.printLog("input xml is :" + isValid);
				}
				
				TestClient.log.printLog("*************** Request is ***************");
				TestClient.log.printLog(message.getText());

				queueSender.send(message);
				TestClient.log.printLog(
					"Successfully sent message to remote Queue and waiting for response on "
						+ queueReceiver.getQueue().getQueueName());
				int waitTime = 310000;
				String wait = (String)properties.get("WAIT_TIME");
				if(wait!= null && wait.trim().length()>0)
				{ 
					waitTime=(new Integer(wait)).intValue();
				}
				
				
				Message msg = queueReceiver.receive(waitTime);

				if (msg != null) {
					if (msg instanceof TextMessage) {
						TextMessage txtmsg = (TextMessage) msg;
						if (xsdFile != null && xsdFile.trim().length() > 0)
						{

							String tempXML = txtmsg.getText();
							int startIndex = tempXML.indexOf("<soap:Envelope");
							int endIndex = tempXML.indexOf(">", startIndex + 1);

							tempXML =
								tempXML.substring(0, endIndex)
									+ responseHeaderXSD
									+ tempXML.substring(endIndex);

							boolean isValid =
								aXMLCheck.validate(
									true,
									false,
									xsdFile,
									XSD_FILE,
									tempXML);
							System.out.println(tempXML);
							System.out.println("output xml is: " + isValid);
							TestClient.log.printLog("output xml is " + isValid);
						}
						TestClient.log.printLog("*************** Reply is ***************");
						TestClient.log.printLog(txtmsg.getText());
					}
					else
						TestClient.log.printLog("Received non-text message :" + msg);
				}
				else
					TestClient.log.printLog("Time out...");
			}
			catch (Exception inException) {
				inException.printStackTrace();
			}
			finally {
				if (queueConnection != null) {
					try {
						queueSession.close();
						queueConnection.close();
					}
					catch (JMSException ee) {
					}
				}
			}
		}
		
	/***********************************************************
     **********************SOAP HTTP PROXY**********************/
    
	/**
	* initSoapHttpMessage
	* @param paramList
	*/
	protected abstract void initSoapHttpMessage(String paramList, Properties props);

    /**
     * processSoapMessage for SOAP HTTP PROXY testing
     * @param paramList
     * @throws TestCaseException
     */
    public void processSoapHttpMessage(String paramList, Properties props) throws TestCaseException
    {
        System.out.println("Testing " + myMethodName + "\n TestCase:: <" + paramList + ">");
        TestClient.log.init(myMethodName);

        initSoapHttpMessage(paramList, props); // to be implemented by derived classes

      //  displayResult(); // to be implemented by derived classes
        TestClient.log.printLog("ending the transaction");
	}
	
	/**
	 * convertXMLToMMarshalObject method converts XML to MMarshalObject using VAXB and returns MMarshalObject
	 * @param xml
	 * @return MMarshalObject
	 */
	public MMarshalObject convertXMLToMMarshalObject(String xml ) throws Exception
	{
		MMarshalObject aMMarshalObject = null;	
	
		try{
			aMMarshalObject = VAXBReader.decode(xml);
		}catch(Exception e){
			System.out.println( "Failure decoding XML to MMarshalObject. " + e.getMessage() ); 
			throw e;
		}
		return aMMarshalObject;
	}
	
	/**
	 * convertMMarshalObjectToXML method converts MMarshalObject to XML using VAXB and returns XML String
	 * @param aMarshalObject
	 * @return XML String
	 */
	public String convertMMarshalObjectToXML(MMarshalObject aMarshalObject)
	{
		String responseXml = null;
	
		try{
			responseXml = VAXBWriter.encode(aMarshalObject);
		} catch (IOException e) {
			System.out.println("Failure encoding MMarshalObject to XML. " + e.getMessage() ); 
		}
	
		return responseXml ;
	}

	
	/**
	 * readFile method reads the test data file and returns the data as String
	 * @param fileName
	 * @param addVAXBTagsToXML
	 * @return String
	 * @throws Exception
	 */
	public String readFile(String fileName, boolean addVAXBTagsToXML) throws Exception{	
		
		String testdata = readFile( fileName ) ;
		if( addVAXBTagsToXML )
			testdata = addVAXBTagsToXML(testdata);
		return testdata;
	}
	
	/**
	 * readFile method reads the test data file and returns the content as String
	 * @param fileName
	 * @return String
	 * @throws Exception
	 */
	public String readFile(String fileName) throws Exception {
		StringBuffer strBuf = new StringBuffer();
		InputStream inputStream = null;
		InputStreamReader inputStreamReader =null;
		BufferedReader bufferedReader = null;
		
		String temp = null; 
		
		temp = TestClient.testCaseDataFileLocation;		
		try {
			
				if(temp == null)
				{
					//Use propertyFileLoader to read environment specific test data file
					inputStream= PropertiesFileLoader.getAsStream("test_data_files/" + fileName.trim(), TestClient.logger);
					inputStreamReader = new InputStreamReader(inputStream);
					bufferedReader = new BufferedReader(inputStreamReader);
					String line;
					while ( (line = bufferedReader.readLine()) != null) {	
						strBuf.append(line);
						strBuf.append("\n");
					}
				}
				else
				{
					System.out.println( "Trying to read test case data file: " + fileName.trim()+" from location: "+ temp); 
					File file = new File(temp + fileName.trim());
					FileInputStream fis = new FileInputStream(file);
					inputStreamReader = new InputStreamReader(fis);
					bufferedReader = new BufferedReader(inputStreamReader);
					String line;
					while ( (line = bufferedReader.readLine()) != null) {	
						strBuf.append(line);
						strBuf.append("\n");
					}										
				}
		} catch (Exception e) {
			System.out.println( "Error reading test data file. " + e.getMessage() ); 
			throw e;
		}
		finally{ 
			try{
				if( bufferedReader!= null) bufferedReader.close();
			}catch (Exception io) {
				System.out.println("Exception on bufferedReader.close()");
			}
			try{
				if( inputStreamReader!= null) inputStreamReader.close();
			}catch (Exception io) {
				System.out.println("Exception on inputStreamReader.close()");
			}
			try{
				if( inputStream!= null) inputStream.close();
			}catch (Exception io) {
				System.out.println("Exception on inputStream.close()");
			}
		}
		
		return strBuf.toString();
	}
	
	/**
	 * addVAXBTagsToXML method adds VAXB tags, which is requred for XML test client.
	 * @param xml
	 * @return
	 */
	public static String addVAXBTagsToXML(String xml) {
        
		int index = xml.indexOf("<vaxb:VAXB");
		
		if( index == -1 ){		
			String BEGIN_VAXB_TAG = "<vaxb:VAXB version=\"3.1.0\" xmlns:vaxb=\"urn:RmFacadePackage.rm.idl.eia.sbc.com\">" ;
			String END_VAXB_TAG = "</vaxb:VAXB>" ;
		  	xml = BEGIN_VAXB_TAG + xml.trim()  + END_VAXB_TAG ;
		}
        
		return xml;
	}    
	
	/**
	 * logExceptionData method logs the Exception data. Used for PROXY, OBJECT and EJB testing
	 * @param e
	 */
	public void logExceptionData(Exception e)
	{

		ExceptionData aExceptionData = null;
		
		responseError = e;
		
		if(e instanceof InvalidData)
		{
			aExceptionData = ((InvalidData)e).aExceptionData;
		}
		else if(e instanceof SystemFailure)
		{
			aExceptionData = ((SystemFailure)e).aExceptionData;
		}
		else if(e instanceof AccessDenied)
		{
			aExceptionData = ((AccessDenied)e).aExceptionData;
		}
		else if(e instanceof BusinessViolation)
		{
			aExceptionData = ((BusinessViolation)e).aExceptionData;
		}
		else if(e instanceof NotImplemented)
		{
			aExceptionData = ((NotImplemented)e).aExceptionData;
		}
		else if(e instanceof ObjectNotFound)
		{
			aExceptionData = ((ObjectNotFound)e).aExceptionData;
		}
		else if(e instanceof DataNotFound)
		{
			aExceptionData = ((DataNotFound)e).aExceptionData;
		}
		
		TestClient.log.printLog("Exception: " + e);
		e.printStackTrace();
		
		if(aExceptionData != null )
		{
			TestClient.log.printLog(
				"Exception Code: " + aExceptionData.aCode);
			TestClient.log.printLog(
				"Exception Description: " + aExceptionData.aDescription);
		}
	}
	
}

