package com.sbc.eia.bis.facades.testing;
import gnu.getopt.Getopt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;

/**
 * Insert the type's description here.
 * Creation date: (5/16/00 9:02:14 PM)
 * @author: Administrator
 * 
#   History :
#   Date		| Author			| Version	|Notes
#   ----------------------------------------------------------------------------
#	6/08/2004     Stevan Dunkin		  RM 9  	 Changed URL from separate Host and Port entry to 
#												 a single Corbaloc URL scheme.
#	03/14/2005	Sriram Chevuturu	  RM 11		 Corrected parseInt exception while parsing the 
#												 TEST_CASE_CYCYCLES value.
#   04/06/2005   Jinmin Ni                       Added command line option of -x to enable validation XML i/o
#   09/23/2005   Jinmin Ni                       Enhanced to accept combination of range of testcases and list seperated by ','
#                                                as sublist of testcase in command line arugment 
#   07/06/2006   Changchuan Yin       RM 15.10   Added variables for Hydra Vicuna proxy testing.
#	05/02/2008   Sriram Chevuturu     RM21.10    Added two more command line options, -l and -m, to the test client 
#												 so that it case pick up the test case data files and mdb config file 
#                                                from user specified location, instead of default locations.
#   05/02/2008   Hongmei Parkin       RM21.10    Added JMX test client
**/

public class TestClient {
	private Properties testProps;
	private Vector vCases;
	public static TestLogger log;
	public static com.sbc.bccs.utilities.Logger logger;
	public static String bisProviderURL;
	public static String bisHome = null;
	public static String dtdURL = null;
	public static ObjectPropertyManager myObjProp = null;
	/*
	 * 	PROXY CHANGE
	 */
	public static String applData = null;
	public static String gwsvcsCLNTUUID = null;
	public static String vicunaXMLFile = null;
	public static String vicunaServiceConfigDir = null;
	public static String targetType = null;

	
	public static String defaultPropertiesFile = "c:/testclient.properties";
	public static String propertiesFile = null;
	public static String propertiesBisFile = null;
	public static String logFile = null;
	public static String packageName = null;
	public final static java.lang.String DEFAULT_DELIMITER = "|";
	public static BisContext myBisContext = null;
	public static ArrayList testList = new ArrayList();
	
	public static String testCaseDataFileLocation 		= null;
	public static String mdbConfigFileLocation	= null;

	//JMX properties
	public static String lookupType = null;
	public static String port = null;
	public static String mbeanIdentifier = null;
	public static String jmxTimeout = null;
	public static String serverName = null;
	
	/*
	 * GETOPT & THREAD
	 */
	public static String optstring = "p:l:m:a:t:c:b:s:x:k:r:v:h";
	public static String usage =
		"Options: [-h] | [-p proterty_file] [-l test_case_data_files_location] [-m mdb_config_file_location] [-a target_type] [-t thread_count] [-c testcases_count] [-b cycle_count] [-s sleep_time][-x xsd_schecma_location] [-k lookup_type] [-r port] [-v server_name] [testlist] where testlist can be a list of number seperated by , or a range of numbers with - e.g.  3,5-9,12";
	public static int threadCount = 1;
	public static int testCount = 1;
	public static int cycleCount = 1;
	public static long sleepTime = 0;
	public static boolean threadOpt = false;
	public static boolean testOpt = false;
	public static boolean cycleOpt = false;
	public static boolean sleepOpt = false;
	public static Vector tempFiles = null;
	public static boolean targetTypeOpt = false;
//	public static boolean SXDOpt = false;
	public static String XMLSchemaLocation=null;
	
	private Properties getProps() throws TestCaseException {

		File file;
		FileInputStream fis = null;
		Properties p = new Properties();

		//	Change in reading properties file in WSAD5 using PropertiesFileLoader	

		try {
			System.out.println(
				"Loading " + propertiesFile + " file from classpath");

			if (propertiesFile == null)
				propertiesFile = defaultPropertiesFile;

			PropertiesFileLoader.read(p, propertiesFile, logger);

		}
		catch (FileNotFoundException fe) {

			//	get properties file		

			file = new File(propertiesFile);

			try {
				System.out.println(
					"\nLoading " + propertiesFile + "  from file system \n");
				fis = new FileInputStream(file);
				p.load(fis);
			}
			catch (FileNotFoundException fne) {
				throw new TestCaseException(
					"\nProperties File Not Found::" + fe.getMessage());
			}
			catch (IOException ie) {
				throw new TestCaseException(
					"Error Loading Properties - " + ie.getMessage());
			}
		}
		catch (IOException ie) {
			throw new TestCaseException(
				"Error Loading Properties::::" + ie.getMessage());
		}

		testProps = p;
		String strFlag = null;
		// get misc info

		if (p.getProperty("CLASSNAME") != null) {
			packageName = p.getProperty("CLASSNAME").trim();
			System.out.println("CLASSNAME = <" + packageName + ">");
		}
		else
			System.out.println(
				"\n***CLASSNAME NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("BIS_PROVIDER_URL") != null) {
			bisProviderURL = p.getProperty("BIS_PROVIDER_URL").trim();
			System.out.println("BIS_PROVIDER_URL = <" + bisProviderURL + ">");
		}
		else
			System.out.println(
				"\n***BIS_PROVIDER_URL NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("BIS_HOME") != null) {
			bisHome = p.getProperty("BIS_HOME").trim();
			System.out.println("BIS_HOME  = <" + bisHome + ">");
		}
		else
			System.out.println(
				"\n***BIS_HOME NOT FOUND OR INCORRECT VALUE***\n");
		
		//lookupType can be entered via command line
		if (lookupType == null){
			if (p.getProperty("LOOKUP_TYPE") != null) {
				lookupType = p.getProperty("LOOKUP_TYPE").trim();
				System.out.println("LOOKUP_TYPE  = <" + lookupType + ">");
			}
			else
				System.out.println(
					"\n***LOOKUP_TYPE NOT FOUND OR INCORRECT VALUE***\n");
		}
		
		//if thread count is entered from command line, then use it.
		if (threadOpt == false) {
			if (p.getProperty("THREAD_COUNT") != null) {
				threadCount = Integer.parseInt(p.getProperty("THREAD_COUNT").trim());
				System.out.println("THREAD_COUNT  = <" + threadCount + ">");
			}
			else
				System.out.println(
					"\n***THREADS_COUNT NOT FOUND OR INCORRECT VALUE***\n");
			}
		
//		if iterations is entered from command line, then use it.
		if (cycleOpt == false) {
			if (p.getProperty("CYCLE_COUNT") != null) {
				cycleCount = Integer.parseInt(p.getProperty("CYCLE_COUNT").trim());
				System.out.println("CYCLE_COUNT  = <" + cycleCount + ">");
			}
			else
				System.out.println(
					"\n***CYCLE_COUNT NOT FOUND OR INCORRECT VALUE***\n");
			} 
		
		try {
		if (sleepOpt == false) {
			if (p.getProperty("SLEEP_TIME") != null) {
				sleepTime = Long.parseLong(p.getProperty("SLEEP_TIME").trim());
				System.out.println("SLEEP_TIME  = <" + sleepTime + ">");
			}
			else
				System.out.println(
					"\n***SLEEP_TIME NOT FOUND OR INCORRECT VALUE***\n");
			} 
		}
		catch (NumberFormatException e1) {
			System.err.println("NumberFormatException: " + e1.getMessage());
		}
		
		if (p.getProperty("MBEAN_IDENTIFIER") != null) {
			mbeanIdentifier = p.getProperty("MBEAN_IDENTIFIER").trim();
			System.out.println("MBEAN_IDENTIFIER  = <" + mbeanIdentifier + ">");
		}
		else
			System.out.println(
				"\n***MBEAN_IDENTIFIER NOT FOUND OR INCORRECT VALUE***\n");
		
		if (p.getProperty("JMX_TIMEOUT") != null) {
			jmxTimeout = p.getProperty("JMX_TIMEOUT").trim();
			System.out.println("JMX_TIMEOUT  = <" + jmxTimeout + ">");
		}
		else
			System.out.println(
				"\n***JMX_TIMEOUT NOT FOUND OR INCORRECT VALUE***\n");
		
		//port can be entered via command line
		if (port == null) {
			if (p.getProperty("PORT") != null) {
				port = p.getProperty("PORT").trim();
				System.out.println("PORT  = <" + port + ">");
			}
			else
				System.out.println(
					"\n***PORT NOT FOUND OR INCORRECT VALUE***\n");
		}
		
		//serverName can be entered via command line
		if (serverName == null) {
			if (p.getProperty("SERVER_NAME") != null) {
				serverName = p.getProperty("SERVER_NAME").trim();
				System.out.println("SERVER_NAME  = <" + serverName + ">");
			}
			else
				System.out.println(
					"\n***SERVER_NAME NOT FOUND OR INCORRECT VALUE***\n");
		}
		
		if(targetTypeOpt == true )
		{
		   System.out.println("TARGET_TYPE = <"+targetType+">"); 
		   p.setProperty("TARGET_TYPE",targetType);
		}
		else if (targetTypeOpt == false && p.getProperty("TARGET_TYPE") != null) {
			targetType = p.getProperty("TARGET_TYPE").trim();
			System.out.println("TARGET_TYPE = <" + targetType + ">");
		}
		else
			System.out.println(
				"\n***TARGET_TYPE NOT FOUND OR INCORRECT VALUE***\n");
		
		if(p.getProperty("SOAFI_LDAP") != null)
		{
			System.out.println("SOAFI_LDAP = <" + p.getProperty("SOAFI_LDAP").trim() + ">");
		}
		else
		System.out.println(
				"\n***SOAFI_LDAP NOT FOUND OR INCORRECT VALUE***\n");
		
		if(p.getProperty("CONNECTION_FACTORY") != null)
		{
			System.out.println("CONNECTION_FACTORY = <" + p.getProperty("CONNECTION_FACTORY").trim() + ">");
		}
		else
		System.out.println(
				"\n***CONNECTION_FACTORY NOT FOUND OR INCORRECT VALUE***\n");
				
		if(p.getProperty("REQUESET_QUEUE") != null)
		{
			System.out.println("REQUESET_QUEUE = <" + p.getProperty("REQUESET_QUEUE").trim() + ">");
		}
		else
		System.out.println(
				"\n***REQUESET_QUEUE NOT FOUND OR INCORRECT VALUE***\n");
		
		if(p.getProperty("REPLY_TO_QUEUE") != null)
		{
			System.out.println("REPLY_TO_QUEUE = <" + p.getProperty("REPLY_TO_QUEUE").trim() + ">");
		}
		else
		System.out.println(
				"\n***REPLY_TO_QUEUE NOT FOUND.  USING TEMP QUEUE***\n");
		
		if (p.getProperty("SERVICE_LOCATOR") != null)	
		{
			System.out.println("SERVICE_LOCATOR = <" + p.getProperty("SERVICE_LOCATOR").trim() + ">");
		}
		else
			System.out.println("\n***SERVICE_LOCATOR NOT FOUND****\n");
			
		if (p.getProperty("LOGGING_KEY") != null)	
		{
			System.out.println("LOGGING_KEY = <" + p.getProperty("LOGGING_KEY").trim() + ">");
		}
		else
			System.out.println("\n***SERVICE_LOCATOR NOT FOUND****\n");
			
		if (XMLSchemaLocation != null)
		{
			System.out.println("XSD_FILES_DIRECTORY = <" + XMLSchemaLocation + ">");
			p.setProperty("XSD_FILES_DIRECTORY",XMLSchemaLocation);
		}
		else if(p.getProperty("XSD_FILES_DIRECTORY") != null)
		{
			System.out.println("XSD_FILES_DIRECTORY = <" + p.getProperty("XSD_FILES_DIRECTORY").trim() + ">");
		}
		else
			System.out.println(
			"\n***XSD_VALIDATION NOT FOUND. WILL NOT VALIDATE INPUT XML AGAIN SCHEMA. ONLY APPLICATIABLE TO TARGET_TYPE=SOAP***\n");
			
		if (p.getProperty("LOGFILE_PATH") != null) {
			logFile = p.getProperty("LOGFILE_PATH").trim();
			System.out.println("LOGFILE_PATH = <" + logFile + ">");
		}
		else
			System.out.println(
				"\n***LOGFILE_PATH NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("APPLDATA") != null) {
			applData = p.getProperty("APPLDATA").trim();
			System.out.println("APPLDATA = <" + applData + ">");
		}
		else
			System.out.println(
				"\n***APPLDATA NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("GWSVCS_CLNTUUID") != null) {
			gwsvcsCLNTUUID = p.getProperty("GWSVCS_CLNTUUID").trim();
			System.out.println("GWSVCS_CLNTUUID = <" + gwsvcsCLNTUUID + ">");
		}
		else
			System.out.println(
				"\n***GWSVCS_CLNTUUID NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("VICUNA_XML_FILE") != null) {
			vicunaXMLFile = p.getProperty("VICUNA_XML_FILE").trim();
			System.out.println("VICUNA_XML_FILE = <" + vicunaXMLFile + ">");
		}
		else
			System.out.println(
				"\n***VICUNA_XML_FILE NOT FOUND OR INCORRECT VALUE***\n");

		if (p.getProperty("VICUNA_SERVICE_CONFIG_DIR") != null) {
			vicunaServiceConfigDir =
				p.getProperty("VICUNA_SERVICE_CONFIG_DIR").trim();
			System.out.println(
				"VICUNA_SERVICE_CONFIG_DIR = <" + vicunaServiceConfigDir + ">");
		}
		else
			System.out.println(
				"\n***VICUNA_SERVICE_CONFIG_DIR NOT FOUND OR INCORRECT VALUE***\n");

		String tmpStr;
		myObjProp = new ObjectPropertyManager();

		tmpStr = p.getProperty("BIS_CONTEXT_Application");
		if (tmpStr != null) {
			myObjProp.add(BisContextProperty.APPLICATION, tmpStr.trim());

			tmpStr = p.getProperty("BIS_CONTEXT_CustomerName");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.CUSTOMERNAME, tmpStr.trim());

			tmpStr = p.getProperty("BIS_CONTEXT_BusinessUnit");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.BUSINESSUNIT, tmpStr.trim());

			tmpStr = p.getProperty("BIS_CONTEXT_UserID");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.USERID, tmpStr.trim());
			

			tmpStr = p.getProperty("BIS_CONTEXT_Password");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.PASSWORD, tmpStr.trim());

			tmpStr = p.getProperty("BIS_CONTEXT_LoggingInformation");
			if (tmpStr != null) {
				LogAssistant theLogAssistant =
					LogAssistantFactory.create(tmpStr, "--");
				//		theLogAssistant.log( MessageFactory.create( "" ) ) ;
				theLogAssistant.genNewCorrID();
				myObjProp.add(
					BisContextProperty.LOGGINGINFORMATION,
					theLogAssistant.getCorrID());
			}
			
			tmpStr = p.getProperty("BIS_CONTEXT_ServiceCenter");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.SERVICECENTER, tmpStr.trim());
			
			tmpStr = p.getProperty("BIS_CONTEXT_ConversationId");
			if (tmpStr != null)
				myObjProp.add(BisContextProperty.CONVERSATIONID, tmpStr.trim());

			myBisContext = new BisContext(myObjProp.toArray());

		}

		//	setup log file
		log = new TestLogger(logFile, "Initializing testing process");

		//	add cases to the testcase vector
		if (testOpt == false && p.getProperty("TEST_CASE_COUNT") != null) {
			testCount = Integer.parseInt(p.getProperty("TEST_CASE_COUNT"));
		}
		for (int i = 1; i < testCount + 1; i++) {
			if (testList.isEmpty() || testList.contains(new Integer(i))) {
				if (p.getProperty("TESTCASE" + i) == null)
					System.out.println(
						"No test case found in " + propertiesFile);

				else {
					System.out.print(
						"Adding "
							+ p.getProperty("TESTCASE" + i)
							+ " to the vector\n");	
					this.vCases.addElement(
						packageName + "." + p.getProperty("TESTCASE" + i));
				}
			}
		}
		return p;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/21/01 12:49:04 PM)
	 * @return java.util.Properties
	 */
	public java.util.Properties getTestProps() {
		return testProps;
	}
	private void init() throws TestCaseException {
		//	log = new TestLogger("c:\\TestClient.log","Initializing testing process");
		this.vCases = new Vector();
		this.tempFiles = new Vector();
	}
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {

		TestClient tc = new TestClient();
		getopt(args);

		System.out.println("Reading properties file: <" + propertiesFile + ">");

		Properties props = null;
		try {
			tc.init();
			System.out.println("Getting the properties : ");
			props = tc.getProps();
			if(testCaseDataFileLocation != null || mdbConfigFileLocation != null)
				tc.processOtherCommandLineProps(props);

			//only thread only, using current thread			

		}
		catch (TestCaseException e) {
			System.out.println("Error::" + e.getMessage());
			e.printStackTrace();
		}

		if (threadCount == 1) { //single thread, run within current process
			runTestSet(tc);
		}
		else { // multiple threads, create one thread for each testcase suite
			//parent thread will sleep in between creating child thread if sleepTime!=0
			for (int i = 0; i < threadCount; i++) {
				TestThread testThd = new TestThread(tc);
				testThd.start();
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					}
					catch (InterruptedException e) {
						System.err.println(
							"Interrupted Exception: " + e.getMessage());
					}
				}
			}
		}

		System.out.println("Testing completed.");
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/1/00 3:58:13 PM)
	 * @return java.lang.String
	 * @param param java.util.StringTokenizer
	 */
	public static String nextToken(StringTokenizer param) {
		String token = param.nextToken();
		return (token.equals("null") ? null : token);
	}

	public static String tempFileName(
		StringTokenizer param,
		boolean isOutputFile) {
		String name;
		if (threadCount == 1) {
			name = TestClient.nextToken(param);
			if (isOutputFile == true && tempFiles.contains(name) == false) {
				tempFiles.add(name);
			}
		}
		else {

			TestThread thisThread = (TestThread) Thread.currentThread();
			name = TestClient.nextToken(param) + "." + thisThread.getName();
			if (isOutputFile == true
				&& thisThread.fileNames.contains(name) == false)
				thisThread.fileNames.add(name);
		}
		return name;
	}

	public static void deleteFile() {
		for (Enumeration e = tempFiles.elements(); e.hasMoreElements();) {
			File fil = new File((String) e.nextElement());
			fil.delete();
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (6/29/01 2:49:06 PM)
	 */
	public static Vector processTestOutput(String inputFile) {

		Vector vTel = new Vector();
		FileReader fin = null;
		BufferedReader inp = null;
		try {
			fin = new FileReader(inputFile);
			inp = new BufferedReader(fin);
			String tmp = "";
			while ((tmp = inp.readLine()) != null) {

				vTel.addElement(tmp);
			}

		}
		catch (IOException ie) {
		}
		finally {
			try {
				fin.close();
				inp.close();
			}
			catch (Exception e) {
			}
		}

		return vTel;
	}

	public static void setTestList(String aList) {
		StringTokenizer st = new StringTokenizer(aList, ",");
		String token=null;
		while (st.hasMoreTokens()) {
			try {
				token=st.nextToken();
				testList.add(Integer.valueOf(token));
			}
			catch (NumberFormatException ne) {
				//try to see if the range is provided
				StringTokenizer subST = new StringTokenizer(token, "-");
				if(subST.countTokens()==2)
				{
					try
					{
						int from=(Integer.valueOf(subST.nextToken())).intValue();
						int to=(Integer.valueOf(subST.nextToken())).intValue();
						for(int i=from;i<=to;i++)
						{
							testList.add(new Integer(i));
						}
					}
					catch (NumberFormatException nee){}
				}
			};
		}
	}

	public static void getopt(String args[]) {

		Getopt g = new Getopt("testclient", args, optstring);
		int opt = -1;

		while ((opt = g.getopt()) != -1) {
			try {
				switch (opt) {
					case 'p' : // opt for property file
						propertiesFile = g.getOptarg().trim();
						break;
					case 't' : // opt for thread
						threadCount = Integer.parseInt(g.getOptarg().trim());
						threadOpt = true;
						if (threadCount <= 0) {
							threadCount = 1;
						}
						break;
					case 'c' : //opt for testcount
						testCount = Integer.parseInt(g.getOptarg().trim());
						testOpt = true;
						if (testCount <= 0) {
							testCount = 1;
						}
						break;
					case 'b' : //opt for cycle count
						cycleCount = Integer.parseInt(g.getOptarg().trim());
						cycleOpt = true;
						if (cycleCount <= 0) {
							cycleCount = 1;
						}
						break;
					case 's' : //opt for sleep time  
						sleepTime = Long.parseLong(g.getOptarg().trim());
						sleepOpt = true;
						if (sleepTime < 0) {
							sleepTime = 0;
						}
						break;
					case 'a' : // opt for target type - OBJECT, PROXY, EJB, MDB, JMX
						targetType = g.getOptarg().trim().toUpperCase();
						if(!targetType.equals("EJB")
							&& !targetType.equals("OBJECT")
							&& !targetType.equals("MDB")
							&& !targetType.equals("PROXY")
							&& !targetType.equals("SOAP")
							&& !targetType.equals("SOAPHTTP")
							&& !targetType.equals("JMX"))
						{
							System.err.println("invalid target type: " + targetType); 
							System.exit(-1);  
						}
						targetTypeOpt = true;
						break;
					case 'x': //opt for xml validation
						XMLSchemaLocation = g.getOptarg().trim();
						break;
					case 'l' : // opt for xml test case files location
						testCaseDataFileLocation = g.getOptarg().trim();
						testCaseDataFileLocation = padWithDirectoryDelimiter(testCaseDataFileLocation);
						break;
					case 'm' : // opt for mdb confile file location
						mdbConfigFileLocation = g.getOptarg().trim();
						mdbConfigFileLocation = padWithDirectoryDelimiter(mdbConfigFileLocation);
						break;
					case 'k': //opt for xml validation
						lookupType = g.getOptarg().trim();
						break;
					case 'r': //opt for xml validation
						port = g.getOptarg().trim();
						break;
					case 'v': //opt for xml validation
						serverName = g.getOptarg().trim();
						break;
					case '?' : //missing argument for a opt
						System.err.println("Exit..");
						//getopt() already printed an error msg
						System.exit(-1);
					case 'h' : // opt for usage help
						System.out.println(usage);
						System.out.println("Exit..");
						System.exit(0);
					default : // invalid opt
						System.err.println("invalid option: " + g.getOptopt());
						System.err.println(usage);
						System.err.println("Exit..");
						System.exit(-1);
				}
			}
			catch (NumberFormatException e) {
				System.err.println(e);
				System.err.println("Exit..");
				System.exit(-1);
			}
		}
		// if setTestList is provided
		if (g.getOptind() < args.length) {
			setTestList(args[g.getOptind()]);
		}
	}

	public static void runTestSet(TestClient tc) {

		// Outer for loop executes the entire test suite of testcases for n times, 
		//where n is defined in TEST_CASE_CYCLES prperty in testclient.properties file
		// or specify on command line input option -c
		for (int loops = 0; loops < cycleCount; loops++) {
			TestClient.log.printLog(
				"<<< cycle " + (loops + 1) + " of entire testcases" + " >>>");
			int count = 1;
			//run testcase in the test suite sequentially
			for (Enumeration enumeration = tc.vCases.elements();
				 enumeration.hasMoreElements();
				) {
				try {
					//Get the number of the times a specific test needs to run.
					TestClient.log.printLog(
						"***** TestCase # "
							+ count
							+ " of cycle "
							+ (loops + 1)
							+ " *****");
					String testCaseString = (String) enumeration.nextElement();
					StringTokenizer st =
						new StringTokenizer(testCaseString, DEFAULT_DELIMITER);
					String className = st.nextToken();

					String paramList = null;
					if (st.hasMoreTokens())
						paramList =
							testCaseString.substring(
								testCaseString.indexOf(DEFAULT_DELIMITER) + 1);

					TestCaseBase implClass =
						(TestCaseBase) Class.forName(className).newInstance();

					if (tc
						.testProps
						.getProperty("TARGET_TYPE")
						.equals("EJB")) {
						//EJB Testing: TestRMCaseBase
						implClass.processCase(paramList);

					}
					else if (
						tc.testProps.getProperty("TARGET_TYPE").equals(
							"OBJECT")) {
						// OBJECT Testing:  TestRMCaseBase
						String myBisProperties =
							tc.testProps.getProperty("BIS_PROPERTIES_PATH");
						if (!(myBisProperties == null
							|| myBisProperties.equalsIgnoreCase(""))) {
							propertiesBisFile = myBisProperties;
						}
						implClass.processCase(paramList, propertiesBisFile);

					}
					else if (
						tc.testProps.getProperty("TARGET_TYPE").equals(
							"PROXY")) {
						//PROXY Testing: TestRMCaseBase
						TestClient.log.printLog(
							"Testing properties for Vicuna RM Proxy: ");
						implClass.processCase(paramList, tc.testProps);
					}
					else if (tc.testProps.getProperty("TARGET_TYPE").equals("MDB")) {
						//MDB Testing: TestRMCaseBase
						TestClient.log.printLog("Testing properties for RM MDB: ");
						implClass.processMessage(paramList, tc.testProps);
					}
					else if (tc.testProps.getProperty("TARGET_TYPE").equals("SOAP")) {
						TestClient.log.printLog("Testing properties for RM SOAP PROXY: ");
						implClass.processSoapMessage(paramList,tc.testProps);
					}
					else if (tc.testProps.getProperty("TARGET_TYPE").equals("SOAPHTTP")) {
						TestClient.log.printLog("Testing properties for RM SOAP/HTTP PROXY: ");
						implClass.processSoapHttpMessage(paramList,tc.testProps);
					}
					else if (tc.testProps.getProperty("TARGET_TYPE").equals("JMX")) {
						TestClient.log.printLog("Testing properties for RM JMX: ");
						implClass.processJmxCase(paramList);
					}
					count = count + 1;
				}
				catch (ClassNotFoundException e) {
					System.out.println("Class Not Found:" + e.getMessage());
				}
				catch (InstantiationException e) {
					System.out.println(
						"Error Instantiation TestClass:" + e.getMessage());
				}
				catch (IllegalAccessException e) {
					System.out.println("Illegal Access:" + e.getMessage());
				}
				catch (TestCaseException e) {
					System.out.println(
						"TestCaseException Thrown:" + e.getMessage());
				}
			}
		}

		//delete temp files if any
		if (threadCount == 1) {
			deleteFile();
		}
		else {
			((TestThread) Thread.currentThread()).deleteFile();
		}

	}
	/**
	 * @param props
	 * 
	 */
	private void processOtherCommandLineProps(Properties props) {
		
		System.out.println(
		"\n***Loading Location Properties for XML Test Data Files  and MDB Config File***\n");
		
		if(testCaseDataFileLocation != null && testCaseDataFileLocation.trim().length() != 0)
		{
			props.setProperty("TEST_CASE_DATA_FILE_LOCATION", testCaseDataFileLocation);
			System.out.println("Location of the Test Case Data Files is: "+props.getProperty("TEST_CASE_DATA_FILE_LOCATION"));
		}
		else
			System.out.println("Location of XML Test Data Files is Default Location.");
		
		if(mdbConfigFileLocation != null && mdbConfigFileLocation.trim().length() != 0)
		{
			String temp = props.getProperty("RM_MDB_TEST_CLIENT_CONFIG_FILE_NAME");
			props.setProperty("RM_MDB_TEST_CLIENT_CONFIG_FILE_NAME", mdbConfigFileLocation+temp);
			System.out.println("Location of the MDB Config File is: "+mdbConfigFileLocation);
		}
		else
			System.out.println("Location of MDB Config File is Default Location.");
		
	}
	/**
	 * @param aPath
	 */
	protected static String padWithDirectoryDelimiter(String aPath) {
		if(aPath.indexOf("\\") == -1)
			if(!aPath.endsWith("/"))
				aPath = aPath.concat("/");
		if(aPath.indexOf("/") == -1)
			if(!aPath.endsWith("\\"))
				aPath = aPath.concat("\\");
	return aPath;		
	}
}