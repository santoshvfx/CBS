package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.test;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
/**
 * The class <code>ParserSvcFactoryTest</code> contains tests for the class
 * {@link <code>ParserSvcFactory</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro Studio at 12/23/04 1:59 PM
 *
 * @author ns3580
 *
 * @version $Revision: 1.2 $
 */
public class ParserSvcFactoryTest extends TestCase
{
//	/**
//	 * The object that is being tested.
//	 *
//	 * @see com.sbc.eia.srm.parsersvc.factory.ParserSvcFactory
//	 */
//	private ParserSvcFactory fixture = new ParserSvcFactory();
	ParseRequest request;

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public ParserSvcFactoryTest(String name)
	{
		super(name);
	}


	/**
	 * Perform pre-test initialization
	 *
	 * @throws Exception
	 *
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		this.request = new ParseRequest();
		// Add additional set up code here
	}

	/**
	 * Perform post-test clean up
	 *
	 * @throws Exception
	 *
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
		// Add additional tear down code here
	}

	/**
	 * Run the ParserSvc getParserSvc() method test
	 */
	public void testGetParserSvc() throws Exception
	{
		//fail("Newly generated method - fix or disable");
		// add test code here
		Logger logger = new TestLogger();
		ParserSvc result = ParserSvcFactory.getFactory().getParserSvc(logger);
		assertNotNull(result);
		assertTrue(result instanceof ParserSvc);
	}

}