package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbc.bccs.utilities.Logger;

/******************************************************************************
 * ============================================================================
 * File name: SRMTestLogger.java
 * ============================================================================
 * Create Date: Sep 9, 2004
 * Create Time: 1:31:41 PM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public class TestLogger implements Logger
{

    private static final String loggerId = "[TestLogger]: ";

    /**
     * Constructor for SRMTestLogger.
     */
    public TestLogger()
    {
        super();
    }

    private String getTimeString()
    {
        Date startDt = new Date();
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MMM-dd::HH.mm.ss.SSS");
        String s = formatter.format(startDt);
        
        return s;
    }

    /**
     * @see com.sbc.bccs.utilities.Logger#log(String, String)
     */
    public void log(String eventId, String message)
    {
        System.out.println(
            loggerId + "|" + getTimeString()
            + "|" + eventId + "|" + message);
    }

    /**
     * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String)
     */
    public void log(
        String eventId,
        String error,
        String message,
        String source)
    {
        System.out.println(
            loggerId + "|" + getTimeString()
                + "|" + eventId
                + "|" + error
                + "|" + message
                + "|" + source);
    }

    /**
     * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String, String)
     */
    public void log(
        String eventId,
        String location,
        String service,
        String component,
        String operation)
    {
        System.out.println(
            loggerId + "|" + getTimeString()
                + "|" + eventId
                + "|" + location
                + "|" + service
                + "|" + component
                + "|" + operation);
    }

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#endConversationContext()
	 */
	public void endConversationContext()
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#get_correlation_id()
	 */
	public String get_correlation_id()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#getBisLogInformation()
	 */
	public String getBisLogInformation()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#getBisName()
	 */
	public String getBisName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#getBisVersion()
	 */
	public String getBisVersion()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_entry(java.lang.String)
	 */
	public void log_entry(String the_operation)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_exit(java.lang.String)
	 */
	public void log_exit(String the_operation)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_failure(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void log_failure(String the_error, String the_message, String the_source)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_partial_failure(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void log_partial_failure(String the_error, String the_message, String the_source)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_remote_call(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void log_remote_call(String the_location, String the_service, String the_component, String the_operation)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#log_remote_return(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void log_remote_return(String the_location, String the_service, String the_component, String the_operation)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#set_correlation_id(java.lang.String)
	 */
	public void set_correlation_id(String new_corr_id)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#setBisLogInformation(java.lang.String)
	 */
	public void setBisLogInformation(String i_bisLogInformation)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#setBisName(java.lang.String)
	 */
	public void setBisName(String i_name)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#setBisVersion(java.lang.String)
	 */
	public void setBisVersion(String i_version)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#startConversationContext()
	 */
	public void startConversationContext()
	{
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.sbc.eia.bis.bccs.logger.BisLogger#startConversationContext(java.lang.String)
	 */
	public void startConversationContext(String i_bisLogInformation)
	{
		// TODO Auto-generated method stub
		
	}

}
