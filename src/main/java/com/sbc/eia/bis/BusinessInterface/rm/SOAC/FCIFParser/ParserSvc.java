package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc
 * ============================================================================
 * File name: ParserSvc
 * ============================================================================
 * Create Date: Dec 15, 2004
 * Create Time: 3:48:50 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public interface ParserSvc
{
    /**
     * This method will use the parameters set in the ParseRequest object
     * to parse the data into a PSR object.
     * @param request
     * @throws ParserSvcException
     */
     public void processData(ParseRequest request) 
        throws ParserSvcException;
}
