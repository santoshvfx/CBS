package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.loader.test;

import junit.framework.TestCase;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.loader.StateLoader;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.constants.loader.test
 * ============================================================================
 * File name: StateLoaderImplTest
 * ============================================================================
 * Create Date: Jan 6, 2005
 * Create Time: 4:00:19 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class StateLoaderImplTest extends TestCase
{

    /**
     * Constructor for StateLoaderImplTest.
     * @param arg0
     */
    public StateLoaderImplTest(String arg0)
    {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testLoadState()
    {
        ParserConstants constants = StateLoader.getInstance().loadState(ParserConstants.SBCEAST);
        assertNotNull(constants);
        assertTrue(constants instanceof SoacServiceOrderConstants);
    }

}
