//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\ParserSvcImpl.java

//Source file: C:\\Appdev\\Wsad\\Projects\\BISCommonsProject\\ParserSvcProject\\src\\com\\sbc\\eia\\bis\\bccs\\parsersvc\\factory\\ParserSvcImpl.java

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy.AssemblerStrategy;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.loader.StateLoader;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.GeneratorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.GeneratorI;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.ParserStrategy;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.ServiceOrderParser;
/**
 * com.sbc.eia.srm.parsersvc
 * ============================================================================
 * File name: ParserSvcImpl
 * ============================================================================
 * Create Date: Dec 15, 2004
 * Create Time: 3:54:21 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 */
public class ParserSvcImpl implements ParserSvc
{
    private ParserStrategy parserStrategy;
    private AssemblerStrategy assembler;
    private String generatorStrategy;
    private ParserSvcHelper helper = null;
    private Logger logger;

    /**
     * @param helper
     * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException
     * @roseuid 4284B88F0067
     */
    public ParserSvcImpl(ParserSvcHelper helper) throws ParserSvcException
    {
        super();
        this.helper = helper;
    }

    /**
     * (non-Javadoc)
     * @see com.sbc.eia.srm.parsersvc.ParserSvc#processData(java.lang.Object)
     * @param request
     * @throws com.sbc.eia.srm.parsersvc.exceptions.ParserSvcException
     * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException
     * @roseuid 4284B7BD03E2
     */
    public void processData(ParseRequest request) throws ParserSvcException
    {
        // set logger in the Helper
        this.helper.setLogger(request.getLogger());
        this.logger = request.getLogger();

        logger.log(
            LogEventId.DEBUG_LEVEL_2,
            "Enter executing ParserSvcImpl.processData(ParseRequest request)");

        if ((request.getSon() == null)
            && (request.getOperationType()
                == ParserConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF))
        {
            throw new ParserSvcException("ServiceOrderData required for operation type SOAC_VALUEOBJECT_TO_SOAC_FCIF.");
        }
        else if (request.getLogger() == null)
        {
            throw new ParserSvcException("Logger object not set.");
        }

        // Check the operation type and determine if the
        // request id for generating a data string
        // or for creating an object from given data
        if (request
            .getOperationType()
            .equalsIgnoreCase(ParserConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF))
        {
            // need the FCIF generator strategy
            GeneratorI generator =
                (com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.SoacFcifGenerator) 
                helper.createStrategy(getGeneratorStrategy(request.getRegion()));
            try
            {
                generator.generate(request);
            }
            catch (GeneratorException e)
            {
                throw new ParserSvcException(
                    e,
                    "Error creating GeneratorImpl class - ",
                    e.getMessage(),
                    "ParserSvcIMPL::processData",
                    ParserSvcException.RECOVERABLE);
            }
        }
        else
        {     
        	
//        	commenting out this line to code as the ParserStartegy is the same for all the regions.
// 			Bypassing retrieving the ParserStrategy Logic   - With new upcoming regions and similar parser Stregey for all regions.
    
//            getParserStrategy(
//                request.getRegion(),
//                request.getDataFormat(),
//                request.getSon()).processData(
//                request);
			logger.log(
						LogEventId.DEBUG_LEVEL_2,
						"Excecuting Parser Startegy - ServiceOrderParser().processData(request)");    
            new ServiceOrderParser().processData(request);                         
                
        }

    }

    /**
     * @param region - String identifier,
     * @param svcOrderNumber
     * @return com.sbc.eia.bis.srm.serviceorder.parser.ParserStrategy
     * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException
     * @roseuid 4284B7BE0038
     */
    private ParserStrategy getParserStrategy(
        String region,
        String dataFormat,
        String svcOrderNumber)
        throws ParserSvcException
    {
        logger.log(
            LogEventId.DEBUG_LEVEL_1,
            "ParserSvcImpl.getParserStrategy() - Obtaining the ParserStrategy");

        String strategyName = "";
        if (dataFormat.equalsIgnoreCase(ParserConstants.SOAC_FCIF))
        {
            if (region.equalsIgnoreCase(ParserConstants.SBCSOUTHWEST))
            {
                strategyName = "SBCSOUTHWEST";
            }
            else
                if (region.equalsIgnoreCase(ParserConstants.SBCMIDWEST))
                {
                    strategyName = "SBCMIDWEST";
                }
                else
                    if (region.equalsIgnoreCase(ParserConstants.SBCWEST))
                    {
                        strategyName = "SBCWEST";
                    }
                    else
                        if (region.equalsIgnoreCase(ParserConstants.SBCWEST))
                        {
                            strategyName = "SBCWEST";
                        }
        else
        {
            throw new ParserSvcException("dataFormat must be type: " + ParserConstants.SOAC_FCIF);            
        }
        }   

        return createParser(strategyName);
    }

    /**
     * @param strategyName
     * @return com.sbc.eia.bis.srm.serviceorder.parser.ParserStrategy
     * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException
     * @roseuid 4284B7BE0077
     */
    private ParserStrategy createParser(String strategyName)
        throws ParserSvcException
    {
        parserStrategy = (ParserStrategy) helper.createStrategy(strategyName);
        parserStrategy.setConstants(
            StateLoader.getInstance().loadState(strategyName));

        return parserStrategy;
    }

    /**
     * @param key
     * @return com.sbc.eia.bis.srm.serviceorder.assembler.strategy.AssemblerStrategy
     * @throws com.sbc.eia.bis.srm.serviceorder.exceptions.ParserSvcException
     * @roseuid 4284B7BE00B6
     */
    private AssemblerStrategy getAssembler(String region, String operationType)
        throws ParserSvcException
    {

        if ((region.equalsIgnoreCase(ParserConstants.SBCSOUTHWEST)
                && (operationType
                    .equalsIgnoreCase(ParserConstants.STRINGARRAY_TO_PSR))))
        {
            assembler =
                (AssemblerStrategy) helper.createStrategy("SOAC_VO_ASSEMBLER");
        }

        return assembler;
    }

    private String getGeneratorStrategy(String region)
        throws ParserSvcException
    {
        if (region.equalsIgnoreCase(ParserConstants.SBCSOUTHWEST))
        {
            generatorStrategy = "SOUTHWEST_SOAC_SERVICE_ORDER_GENERATOR";
        }
        else if (region.equalsIgnoreCase(ParserConstants.SBCWEST))
        {
            generatorStrategy = "WEST_SOAC_SERVICE_ORDER_GENERATOR";
        }
        else if (region.equalsIgnoreCase(ParserConstants.SBCMIDWEST))
        {
            generatorStrategy = "MIDWEST_SOAC_SERVICE_ORDER_GENERATOR";
        }
        else if (region.equalsIgnoreCase(ParserConstants.SBCEAST))
        {
            generatorStrategy = "EAST_SOAC_SERVICE_ORDER_GENERATOR";
        }
		else if (region.equalsIgnoreCase(ParserConstants.SBCSOUTHEAST))
		{
			generatorStrategy = "SOUTHEAST_SOAC_SERVICE_ORDER_GENERATOR";
		}
		else if (region.equalsIgnoreCase(ParserConstants.CVOIP))
		{
			generatorStrategy = "CVOIP_SOAC_SERVICE_ORDER_GENERATOR";
		}

        return generatorStrategy;
    }

}
