//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//#	History :
//#	Date      	| Author        	| Notes
//#	----------------------------------------------------------------------------
//#	7/2004			Mike McDonough		Creation.


package com.sbc.eia.bis.RmNam.components;
import com.sbc.bccs.utilities.BisOverrideParser;
import com.sbc.bccs.utilities.BisOverRideParserObject;
import com.sbc.bccs.utilities.OverrideParserAccessDeniedException;
import com.sbc.bccs.utilities.OverrideParserSystemFailureException;
import java.util.Hashtable;
import com.sbc.eia.idl.bis_types.BisContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.sbc.bccs.utilities.Logger;


public class RoutingOverrideParser {
	
	
	private BisOverrideParser bisOverrideParser = null;
	private Logger logger = null;
	private Hashtable bisProperties = null;
	
	
	public RoutingOverrideParser(Hashtable properties, Logger log) 
    {
    	bisProperties = properties;
    	logger  = log;
    	bisOverrideParser = BisOverrideParser.getuniInstance();
	}
	
	

	/**
	 * Retrieves a BisOverRideParserObject if any routing override
	 * keys are in the BisContext for the BIS.
	 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
	 * @param comparisonValue java.lang.String
	 * @exception com.sbc.bccs.utilities.OverrideParserAccessDeniedException 
	 * @exception com.sbc.bccs.utilities.OverrideParserSystemFailureException 
	 */
	public BisOverRideParserObject getBisOverrideParserObject(
		BisContext bixContext, String comparisonValue) throws OverrideParserSystemFailureException, 
            OverrideParserAccessDeniedException
	{
		initBisOverrideParser();
		BisOverRideParserObject bisOverrideParserObject = null;

		String overrideKey = bisOverrideParser.getOverRideKey(bixContext,
			comparisonValue);
			
		if(overrideKey != null)
		{
			bisOverrideParserObject = bisOverrideParser.getParsedObject(
				overrideKey);
		}	
			
		return bisOverrideParserObject;
	}
	
	/**
	 * Returns a list all of the routing override keys in the Biscontext. 
	 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
	 */
	public List getRoutingOverrideListForBIS(BisContext bisContext) 
	{
		return getRoutingOverrideListForBIS(bisContext,null);
	}
	
	/**
	 * Returns a list of routing override keys.  If bisName is null
	 * it will return all routing override keys, otherwise only 
	 * routing override keys for the specified BIS.
	 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
	 * @param bisName java.lang.String
	 */
	public List getRoutingOverrideListForBIS(BisContext bisContext,
		String bisName) 
	{

        ArrayList routingList = new ArrayList();

        for (int i = 0; i < bisContext.aProperties.length; ++i) {
            if (bisContext.aProperties[i].aTag.equalsIgnoreCase("Routing")) 
            {
                if (bisName != null) {

                    if (bisContext.aProperties[i].aValue.substring(0,bisContext.aProperties[i].aValue.indexOf("|")).equals(bisName)) {
                        routingList.add(bisContext.aProperties[i].aValue);
                    }
                } else {
                	if(bisContext.aProperties[i].aValue.length()>0)
                	{
                    	routingList.add(bisContext.aProperties[i].aValue);
                	}
                }
            }
        }

        if (routingList.isEmpty()) {
            return null;
        }

        return routingList;
    }
    
    
    /**
	 * Checks the BisContext for routing overrides.  If any 
	 * are found validates for supported environments, and
	 * that client requests are valid. 
	 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
	 * @exception com.sbc.bccs.utilities.OverrideParserAccessDeniedException 
	 * @exception com.sbc.bccs.utilities.OverrideParserSystemFailureException 
	 */
    public void validateRoutingOverride(BisContext bisContext)
    	throws OverrideParserSystemFailureException, 
            OverrideParserAccessDeniedException
    {
    	if(Boolean.valueOf((String)bisProperties.get("BIS_OVERRIDE")).booleanValue())
	    {
			initBisOverrideParser();
			bisOverrideParser.validateOverride(bisContext);
	    }
	    else
	    {
	    	ArrayList routingList = null;
	    	if((routingList=(ArrayList)getRoutingOverrideListForBIS(bisContext))!=null)
	    	{
	    		Iterator iter = routingList.iterator();
	    		while(iter.hasNext())
	    		{
	    			logger.log("INFO_LEVEL_1","Routing Override found: " + iter.next());
	    		}
	    		throw new OverrideParserAccessDeniedException("Routing overrides not supported for this environment");

	    	}
	    }
    }
    
     /**
	 * Initializes the BisOverrideParser
	 * @exception com.sbc.bccs.utilities.OverrideParserAccessDeniedException 
	 * @exception com.sbc.bccs.utilities.OverrideParserSystemFailureException 
	 */
    private void initBisOverrideParser()
    	throws OverrideParserSystemFailureException, 
            OverrideParserAccessDeniedException
    {	
    	//Initialize the parser
		bisOverrideParser.setParserParameters(getBisName(), 
			(String)bisProperties.get("jdbcDATA_SOURCE_NAME"), 
			(String)bisProperties.get("jdbcUSERID"),
			(String)bisProperties.get("jdbcPASSWORD"), 
			logger);
		
    }
    
    /**
	 * Extracts the BIS name from the properties
	 */
    private String getBisName()
	{
		//Load bis name
		String bisName = (String)bisProperties.get("BIS_NAME");
		
		if(bisName.startsWith("NAM"))
			bisName = "NAM";
		else if(bisName.startsWith("RM"))
			bisName = "RM";

		return bisName;
	}
    
    
    
    
	
}