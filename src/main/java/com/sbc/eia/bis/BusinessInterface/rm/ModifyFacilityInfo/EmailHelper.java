//# $Id: EmailHelper.java,v 1.2 2007/11/11 18:25:06 cy4727 Exp $
//###############################################################################
//#
//#       Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date         | Author                 | Notes
//# --------------------------------------------------------------------------
//# 10/10/2007          Changchuan Yin      Created based on OMSEmailHelper.java
//# 11/11/2007          Changchuan Yin      Changed name of email address.
package com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.mail.MessagingException;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.facades.testing.TestLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.Emailer;
import com.sbc.eia.idl.bis_types.BisContext;



public class EmailHelper {

    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String defaultPropertiesFile 	= "rm.properties";
	private String defaultPlatform 	= "dev";	

	private Properties aEmailProps	=	new Properties();
	
	private String MAIL_SERVER_PROP 				=	"mail.smtp.host";
	private String MAIL_TRANSPORT_PROTOCOL_PROP	=	"mail.transport.protocol";
	private String MAIL_TRANSPORT_PROTOCOL			=	"smtp";

	private String SECONDARY_MAIL_SERVER_PROP		=	"secondary.mail.smtp.host";
	
	private BisContext myBisContext = null;	
	private com.sbc.bccs.utilities.Logger logger;	
	private ObjectPropertyManager myObjProp = null;	
	private TestLogger log;	

	
	
	private String PRIMARY_MAIL_HOST	= null;
	private String SECONDARY_MAIL_HOST	= null;	
	private String MAIL_FROM	= null;
	private String[] MAIL_TO;

	private String DELIMITER = ",";

    public EmailHelper(Utility utility, Hashtable properties) {
		super();
        aProperties = properties;
        aUtility = utility;		
	}



	//In case we are using the default Properties.
	public void prepareAndSendEmail(
	String aSubject, 
	String aBody) throws MessagingException, IOException
	{

        String myMethodName = "EmailHelper::prepareAndSendEmail(aSubject,aBody)";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		

		prepareAndSendEmail(
		defaultPropertiesFile,
		defaultPropertiesFile,
		defaultPlatform,
		aSubject,
		aBody );
		
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
	}


	//In case we are using the default Properties.
	public void prepareAndSendEmail(
	String aSubject, 
	String aBody, String recipient) throws MessagingException, IOException
	{
	
	    String myMethodName = "EmailHelper::prepareAndSendEmail(aSubject,aBody)";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
	
		prepareAndSendEmail(
		defaultPropertiesFile,
		defaultPropertiesFile,
		defaultPlatform,
		aSubject,
		aBody,recipient );
		
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
	}	

  
    //In case we are using different files for getting the 
	//MailServer props and Email Addresses.
	public void prepareAndSendEmail(
	String emailServerPropFile,
	String emailAddressesFile,
	String aPlatform, 
	String aSubject, 
	String aBody) throws MessagingException, IOException
	{
        String myMethodName = "EmailHelper::prepareAndSendEmail(emailServerPropFile,emailAddressesFile,aPlatform,aSubject,aBody)";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);			

		getEmailServerProperties(emailServerPropFile,aPlatform);
		getEmailAddresses(emailAddressesFile,aPlatform);	
		
		SendEmailRequest(aSubject,aBody);

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);					
	}


	//In case we are using different files for getting the 
	//MailServer props and Email Addresses.
	public void prepareAndSendEmail(
	String emailServerPropFile,
	String emailAddressesFile,
	String aPlatform, 
	String aSubject, 
	String aBody, String recipient) throws MessagingException, IOException
	{
	    String myMethodName = "EmailHelper::prepareAndSendEmail(emailServerPropFile,emailAddressesFile,aPlatform,aSubject,aBody)";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);			
	
		getEmailServerProperties(emailServerPropFile,aPlatform);
		getEmailAddresses(emailAddressesFile,aPlatform,recipient);	
		
		SendEmailRequest(aSubject,aBody);
	
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);					
	}


	/**
	 * Method getEmailAddresses.
	 * @param propertiesFile
	 * @param aPlatform
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void getEmailAddresses(String propertiesFile, String aPlatform, String recepientName) throws  IOException, MessagingException 
	{
        String myMethodName = "EmailHelper::getEmailAddresses()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
        
     	String temp;
		if ((temp = ((String)aProperties.get("SENDER_EMAIL_ADDRESS")).trim()) !=null) {
			MAIL_FROM = temp;

			try 
			{
            	aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_FROM=<"  + MAIL_FROM  + ">");
        	}
        	catch (Exception e) 
        	{}
        	
       	}
		else
		{	aUtility.log(TranBase.LOG_INPUT_DATA, "No sender email address found.");
		}
		
		if ((temp = ((String)aProperties.get(recepientName+"_REPLY_EMAIL_ADDRESS" )).trim()) != null) {
			//MAIL_TO --get the list;
			{
				 String[] tempArray	= new String[10];
				 
				//this is in case only one email address and no delimiter.
				if(temp.indexOf(DELIMITER)== -1)		{
					//no recepient email address
					if(!(temp.trim().equals("")))	{
						tempArray[0] = temp;					
						MAIL_TO = new String[1];				
						MAIL_TO[0] = tempArray[0];
					
					}
					try 
					{
           				aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_TO[0]=<"  + MAIL_TO[0]  + ">");
       				}
	        		catch (Exception e) 
       				{}
					
				}
				//multiple email addreses which are seperated by delimiter.
				else
				{
					int num;
					int beginIndex = 0;
					int nextIndex	= temp.indexOf(DELIMITER);
					int lastIndex	= temp.lastIndexOf(DELIMITER);
					for( num = 0; beginIndex <= lastIndex+1;num++ )
					{
						//this is the case for the last email address which might or might 
						// not be is not delimited by a delimiter.

						//in case it is not delimited.
						if(beginIndex ==  lastIndex+1)
						{	
							if(!temp.substring(lastIndex+1).equals(""))
								tempArray[num] = new String(temp.substring(lastIndex+1));
							//in case it is delimited by a delimiter.
							else
								num--;	
							beginIndex++;
						}
						else
						{
							tempArray[num] = new String(temp.substring(beginIndex,nextIndex));
							beginIndex = nextIndex+1;
							nextIndex	 = temp.indexOf(DELIMITER,beginIndex);
						}	

						try 
						{
            				aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_TO["+num+"]=<"  + tempArray[num]  + ">");
        				}
			        	catch (Exception e) 
    	    			{}
						
											
					}

					// Now allocate the correct number of addresses to the MAIL_TO array.
					MAIL_TO = new String[num];					
					for(int x = 0; x< num; x++)
						MAIL_TO[x] = tempArray[x];
				}
			}
		}
		else
		{	
			//do nothing...			
		}

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		

			if((MAIL_FROM == null) && (MAIL_TO.length == 0))
			throw new MessagingException("Missing Both Sender and Receipient Email Addresses."+
											"Need a Sender Email Address and atleast one Receipient Email Address");
        
		
	}



	/**
	 * Method getEmailAddresses.
	 * @param propertiesFile
	 * @param aPlatform
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void getEmailAddresses(String propertiesFile, String aPlatform) throws  IOException, MessagingException 
	{
	    String myMethodName = "EmailHelper::getEmailAddresses()";
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);		
	    
	    	
	
		String temp;
		if ((temp = ((String)aProperties.get("SENDER_EMAIL_ADDRESS")).trim()) !=null) {
			MAIL_FROM = temp;
	
			try 
			{
	        	aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_FROM=<"  + MAIL_FROM  + ">");
	    	}
	    	catch (Exception e) 
	    	{}
	    	
	   	}
		else
		{	aUtility.log(TranBase.LOG_INPUT_DATA, "No sender email address found.");
		}
	
		if ((temp = ((String)aProperties.get("RECEPIENT_EMAIL_ADDRESS" )).trim()) !=null) {
			//MAIL_TO --get the list;
			{
				 String[] tempArray	= new String[10];
				 
				//this is in case only one email address and no delimiter.
				if(temp.indexOf(DELIMITER)== -1)		{
					//no recepient email address
					if(!(temp.trim().equals("")))	{
						tempArray[0] = temp;					
						MAIL_TO = new String[1];				
						MAIL_TO[0] = tempArray[0];
					
					}
					try 
					{
	       				aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_TO[0]=<"  + MAIL_TO[0]  + ">");
	   				}
	        		catch (Exception e) 
	   				{}
					
				}
				//multiple email addreses which are seperated by delimiter.
				else
				{
					int num;
					int beginIndex = 0;
					int nextIndex	= temp.indexOf(DELIMITER);
					int lastIndex	= temp.lastIndexOf(DELIMITER);
					for( num = 0; beginIndex <= lastIndex+1;num++ )
					{
						//this is the case for the last email address which might or might 
						// not be is not delimited by a delimiter.
	
						//in case it is not delimited.
						if(beginIndex ==  lastIndex+1)
						{	
							if(!temp.substring(lastIndex+1).equals(""))
								tempArray[num] = new String(temp.substring(lastIndex+1));
							//in case it is delimited by a delimiter.
							else
								num--;	
							beginIndex++;
						}
						else
						{
							tempArray[num] = new String(temp.substring(beginIndex,nextIndex));
							beginIndex = nextIndex+1;
							nextIndex	 = temp.indexOf(DELIMITER,beginIndex);
						}	
	
						try 
						{
	        				aUtility.log(TranBase.LOG_INPUT_DATA, "MAIL_TO["+num+"]=<"  + tempArray[num]  + ">");
	    				}
			        	catch (Exception e) 
		    			{}
						
											
					}
	
					// Now allocate the correct number of addresses to the MAIL_TO array.
					MAIL_TO = new String[num];					
					for(int x = 0; x< num; x++)
						MAIL_TO[x] = tempArray[x];
				}
			}
		}
		else
		{	
			aUtility.log(TranBase.LOG_INPUT_DATA, "No receiver email address found.");			
		}
	
	    aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);		
	
			if((MAIL_FROM == null) && (MAIL_TO.length == 0))
			throw new MessagingException("Missing Both Sender and Receipient Email Addresses."+
											"Need a Sender Email Address and atleast one Receipient Email Address");
	    
		
	}



	/**
	 * Method getEmailServerProperties.
	 * @param propertiesFile
	 * @param aPlatform
	 * @throws IOException
	 * @throws MessagingException
	 */
	//First get the email properties from the properties file.
	
	public void getEmailServerProperties(String propertiesFile, String aPlatform) throws  IOException, MessagingException
	{
        String myMethodName = "EmailHelper::getEmailServerProperties()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);			


		
		// now get the  email properries
		//mail.host 
				
		String temp;
		if ((temp = ((String)aProperties.get("PRIMARY_SMTP_SERVER")).trim()) !=null) {
			PRIMARY_MAIL_HOST = temp;

			try 
			{
   				aUtility.log(TranBase.LOG_INPUT_DATA, "PRIMARY_SMTP_SERVER=<"  + PRIMARY_MAIL_HOST  + ">");
			}
        	catch (Exception e) 
   			{}
			
		}
		else
		{	
			//do nothing...
		}

		if ((temp = ((String)aProperties.get("SECONDARY_SMTP_SERVER")).trim()) !=null) {
			SECONDARY_MAIL_HOST = temp;

			try 
			{
   				aUtility.log(TranBase.LOG_INPUT_DATA, "SECONDARY_SMTP_SERVER=<"  + SECONDARY_MAIL_HOST  + ">");
			}
        	catch (Exception e) 
   			{}
			
		}
		else
		{	
		}
		
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);	

		if((PRIMARY_MAIL_HOST.trim().equals("")) && (SECONDARY_MAIL_HOST.trim().equals("")))
			throw new MessagingException("Missing Both Primary SMTP Server "+
											"and  the Secondary SMTP Server Names. "+
											"Require atleast one SMTP Server Name");
        
	}	




	/**
	 * Method SendEmailRequest.
	 * @param aSubject
	 * @param aBody
	 * @throws MessagingException
	 */
	private void SendEmailRequest(String aSubject, String aBody) throws MessagingException
	{
        String myMethodName = "EmailHelper::SendEmailRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);			
	
	
		//set the email props.
	
		aEmailProps.put("mail.smtp.host",PRIMARY_MAIL_HOST);
		aEmailProps.put(MAIL_TRANSPORT_PROTOCOL_PROP,MAIL_TRANSPORT_PROTOCOL);
		aEmailProps.put(SECONDARY_MAIL_SERVER_PROP,SECONDARY_MAIL_HOST);

		//now Instantiate and send Email.
		Emailer aEmailer = new Emailer(aUtility,aProperties);
		aEmailer.setEmailConfig(aEmailProps);
		aEmailer.setSecondarySMTPServerPropName(SECONDARY_MAIL_SERVER_PROP);
		
		//if you want Mail Debug On, then uncomment this below line.
//		aEmailer.setMailDebug(true);

		aEmailer.sendEmail(MAIL_TO,MAIL_FROM,aSubject,aBody);
		
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);				
				
	}
	

	
}