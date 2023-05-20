//$Id: ParserSvcHelper2.java,v 1.2 2008/02/25 18:59:43 op1664 Exp $
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
//#      © 2008-2020 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 02/04/2008  Ott Phannavong		Creator 
//#									
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory;

import java.util.HashMap;
import java.util.Properties;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.common.utilities.PropertyLoader;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class ParserSvcHelper2 extends ParserSvcHelper
{
    private static HashMap hashMap = new HashMap();
    private String propertyFileName;
    private Properties props;

    public ParserSvcHelper2(Logger logger, String aPropertyFileName)
            throws ParserSvcException
    {
        super(logger);
        propertyFileName = aPropertyFileName;
        props = (Properties) hashMap.get(aPropertyFileName);
        if(props == null)
        {
            loadProperties2();
        }
    }
    private void loadProperties2() throws ParserSvcException
    {
        try
        {
            if(props == null)
            {
                props = PropertyLoader.getInstance().loadProperties(
                        propertyFileName);
                hashMap.put(propertyFileName, props);
            }
        }
        catch(java.io.IOException ioe)
        {
            logger.log(LogEventId.EXCEPTION, "Unable to load properties for "
                    + propertyFileName);
            logger.log(LogEventId.EXCEPTION, "Exception: " + ioe.getMessage());
            throw new ParserSvcException(ioe, LogEventId.EXCEPTION,
                    "Error loading property file " + propertyFileName + ".",
                    "ParserSvcHelper.loadProperties",
                    ParserSvcException.UNRECOVERABLE);
        }
    }
    public Object createStrategy(String identifier) throws ParserSvcException
    {
        return createStrategy(identifier, props);
    }

}