/*
 * Created on May 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.strategy;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.ServiceOrderParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EastSoacFcifServiceOrderParser extends ServiceOrderParser
{
    private String newLine = SoacServiceOrderConstants.NEWLINE;
    private int newLineOffset = 1;
    private int fieldedStringLength = 125;
    private int nonFieldedStringStart = fieldedStringLength + newLineOffset;

    /**
     * 
     */
    public EastSoacFcifServiceOrderParser()
    {
        super();
        // TODO Auto-generated constructor stub
        ssovo = new SoacServiceOrderVO();
        this.logger = new TestLogger();
    }
    

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.ParserSvc#processData(com.sbc.eia.bis.srm.serviceorder.ParseRequest)
     */

    public void processData(ParseRequest request) throws ParserSvcException
    {
        this.logger = request.getLogger();
        String sectionString = null;
        setSoacServiceOrderVO();        

        // Process the header section
        if (request.getFcifDataString().length() < 111)
        {
            throw new ParserSvcException("Invalid data string length for parsing.");
        }
        
        // Check and set the newline character
        setNewLineCharacter(request.getFcifDataString());
        
        int firstNewLinePosition = request.getFcifDataString().indexOf(newLine);
        String fieldedString = request.getFcifDataString().substring(0, firstNewLinePosition);
        logger.log(
            LogEventId.DEBUG_LEVEL_2,
            "Fielded String: " + fieldedString);
        processFieldedSection(fieldedString);

        int stringLength = request.getFcifDataString().length();
        if (stringLength >= nonFieldedStringStart)
        {
//          String tripleDash = "---";
//          String nonFieldedString =
//              request.getFcifDataString().substring(request.getFcifDataString().indexOf(tripleDash));

            String nonFieldedString = request.getFcifDataString();              

            logger.log(
                LogEventId.DEBUG_LEVEL_2,
                "NonFielded String: " + nonFieldedString);

            sectionString = processSection(nonFieldedString, request);
        }
        else
        {
            throw new ParserSvcException("Invalid data string length for parsing.");
        }

        request.setServiceOrderVo(ssovo);
        
        // set the nonFielded string in the request object
        // for client use
        request.setFcifDataString(sectionString);
    }
}
