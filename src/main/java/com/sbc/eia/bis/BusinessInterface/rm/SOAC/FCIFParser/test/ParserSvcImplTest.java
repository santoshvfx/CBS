//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\test\\ParserSvcImplTest.java

//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\factory\\test\\ParserSvcImplTest.java

/*
 * Created on Jan 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.SoacFcifDataAccessor;

/**
 * @author ns3580
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ParserSvcImplTest extends TestCase
{
	private ParseRequest request;
	private String fcifString;
	private Logger logger;
	protected static String suffix = null;
	private String folderName = null;
	private File[] files = null;

	/**
	 * Constructor for ParserSvcImplTest.
	 * @param arg0
	 * @roseuid 4284B89202BD
	 */
	public ParserSvcImplTest(String arg0)
	{
		super(arg0);
	}

	/**
	 * @see TestCase#setUp()
	 * @throws java.lang.Exception
	 * @roseuid 4284B89202C8
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		folderName = System.getProperty("FolderName");

		suffix = System.getProperty("suffix");
		
		if (folderName == null)
		{
			throw new Exception("Need a folder name property to be set");
		}
		

		fetchFiles(new File(folderName));
		
		if (files == null)
		{
			throw new Exception("There are no files in the folder");
		}

		logger = new TestLogger();

		request = new ParseRequest();
		request.setLogger(logger);
		// request.setFcifDataString(fcifString);
		request.setRegion(ParserConstants.SBCSOUTHWEST);
		request.setDataFormat(ParserConstants.SOAC_FCIF);
//		request.setSon("C060211");

		request.setOperationType(ParserConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);
	}

	/**
	 * @see TestCase#tearDown()
	 * @throws java.lang.Exception
	 * @roseuid 4284B89202F7
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
		
		fcifString = null;
		request = null;
	}

	/**
	 * @throws java.lang.Exception
	 * @roseuid 4284B8920335
	 */
	public void testProcessData() throws Exception
	{	
		
		
		for(int i = 0; i < files.length; i ++)
		{
			request.setFcifDataString(generateFcifString(files[i]));
			ParserSvc result = ParserSvcFactory.getFactory().getParserSvc(logger);
		
			result.processData(request);
			assertNotNull(request.getServiceOrderVo());
			SoacFcifDataAccessor accessor =
				new SoacFcifDataAccessor(
					(SoacServiceOrderVO) request.getServiceOrderVo());
			String facilityType = accessor.getFacilityType();
			String lsCircuitID = accessor.getLsCircuitId();
			String tn = accessor.getTelephoneNumber();
			String cableData = accessor.getCableData();
			String pairData = accessor.getPairData();
			String terminalId = accessor.getTerminalIdData();
			String portData = accessor.getPortData();
			String bpData = accessor.getBindingPostData();
	
			String errorMessage = accessor.getErrorMessage();
			logger.log(LogEventId.DEBUG_LEVEL_2, "Error : " + errorMessage);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "Facility Type: " + facilityType);
			// assertEquals("F2", facilityType);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "lsCircuitID: " + lsCircuitID);
			// assertEquals("12.ABCD.123456..SW", lsCircuitID);
			
			logger.log(LogEventId.DEBUG_LEVEL_2, "Telephone number: " + tn);
			// assertEquals("314 537-1129", tn);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "cableData: " + cableData);
			// assertEquals("16805C", cableData);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "pairData: " + pairData);
			// assertEquals("970", pairData);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "terminalId: " + terminalId);
			// assertEquals("F1101 S MCKNIGHT RD", terminalId);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "portData: " + portData);
			// assertEquals("PMS.102A.1.1.2", portData);
	
			logger.log(LogEventId.DEBUG_LEVEL_2, "bpData: " + bpData);
			// assertEquals("3", bpData);
			
			logger.log(LogEventId.DEBUG_LEVEL_2, "********************************************************\n\n");
		}
	}
	
	private String generateFcifString(File file)
	{
		//String file = "TestData/C060210.resp";
		StringBuffer buff = new StringBuffer();
		String fcifString = "";
		char myChar;
		try
		{
			logger.log(LogEventId.DEBUG_LEVEL_2, "Testing data file: " + file.getName());
			DataInputStream in =
				new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

			while (in.available() != 0)
			{
				myChar = (char) in.readByte();
				buff.append(myChar);
			}
			
			fcifString = buff.toString();
			System.out.println(fcifString);
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		
		return fcifString;
	}
	
	public void fetchFiles(File dir)
		throws IOException
	{
		files = dir.listFiles(new SuffixFilter());
		
		for (int i = 0; i < files.length; ++i)
		{
			if (files[i].isDirectory())
			{
				fetchFiles(files[i]);
			}
		}
		
	}
	
	static class SuffixFilter implements FilenameFilter
	{
		public boolean accept(File dir, String name)
		{
			return name.endsWith(suffix);
		}
	}

}
