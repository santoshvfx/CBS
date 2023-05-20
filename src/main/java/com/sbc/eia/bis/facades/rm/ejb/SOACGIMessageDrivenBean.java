//$Id: SOACGIMessageDrivenBean.java,v 1.34 2005/12/20 20:12:12 hw7243  Exp $
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
//# 11/12/2007	 Changchuan Yin			  Updated for RM BIS LS6 
//# 11/14/2007   Changchuan Yin	          Fixed BISContext-BusinessUnit for accessing IOM BIS. 
//# 11/14/2007   Changchuan Yin	          Changed BISContext-Application to RMBIS.
//# 12/12/2007   Changchuan Yin           Updated for Defect ID : 80064, Log ID did not start with 1.
//# 12/13/2007   Changchuan Yin           Updated for Defect ID : 80158.
//# 06/26/2008   Jon Costa                DR:99255: A Unique Correlation ID is not being returned.

package com.sbc.eia.bis.facades.rm.ejb;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.facades.rm.ejb.ModifyInventory.ModifyInventory;
import com.sbc.eia.bis.facades.rm.ejb.ModifyInventory.ModifyInventoryHome;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;


public class SOACGIMessageDrivenBean extends TranBase implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener {
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;
	private javax.ejb.SessionContext mySessionCtx = null;
	
	protected final static String PROP_FILE_LOCATION = "java:comp/env/PROP_FILE_LOCATION";

	
	public javax.ejb.MessageDrivenContext getMessageDrivenContext() {
		return fMessageDrivenCtx;
	}
	
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx) {
		fMessageDrivenCtx = ctx;
	}
	
	public javax.ejb.SessionContext getSessionContext()
	{
		return mySessionCtx;
	}

	
	public void ejbCreate() throws javax.ejb.CreateException, java.rmi.RemoteException
	{
		init(mySessionCtx);
	}
	

	public void onMessage(javax.jms.Message msg) {

        callerContext = new BisContext(); //Required for starting logger
        this.setCallerContext(callerContext);
       
        log(LogEventId.ENTRY, "SOACGIMessageDrivenBean:onMessage()-Start");
		
        String msgTxt= null;
		
        try
		{
		
			if ((msg instanceof TextMessage))
			{      
				setBisContext(msg);
				
	         	try {
						msgTxt = ((TextMessage) msg).getText();
					} catch (JMSException e) {
						
						log(LogEventId.INFO_LEVEL_1, "SOACGI MDB received non text message " + e.getMessage());
						throw e;
					}
					
				log(LogEventId.INFO_LEVEL_1, "MDB Input from SOACGI: [" + msgTxt + "]");
				 
				try {
					     
					InitialContext ctx = new InitialContext();
					Object o =
						ctx.lookup(
							"java:comp/env/ejb/ModifyInventoryHome");
			
					ModifyInventoryHome home =
						(
							ModifyInventoryHome) javax
								.rmi
								.PortableRemoteObject
								.narrow(
							o,
							ModifyInventoryHome.class);
					ModifyInventory modifyInventoryBean = home.create();
			
					modifyInventoryBean.modifyInventory(
							callerContext,
							msgTxt,
							myLogger);
				}
				catch ( InvalidData e )         { throw e ; }
				catch ( AccessDenied e )        { throw e ; }
				catch ( BusinessViolation e )   { throw e ; }
				catch ( SystemFailure e )       { throw e ; }
				catch ( NotImplemented e )      { throw e ; }
				catch ( ObjectNotFound e )      { throw e ; }
				catch ( DataNotFound e )        { throw e ; }
				catch ( Throwable e )
				{
					log(LOG_FAILURE,ExceptionCode.ERR_RM_UNEXPECTED_ERROR + " Unexpected error processing the message. " + e.getMessage());
			
				} 
			}
			
		}
		catch (Throwable e)
		{
			log(LOG_FAILURE,ExceptionCode.ERR_RM_UNEXPECTED_ERROR + " Unexpected error processing the message. " + e.getMessage());
			
		}
		
		log(LogEventId.EXIT, "SOACGIMessageDrivenBean:onMessage()-End");
	 }
	
	
	 private void setBisContext(javax.jms.Message msg)
	 {   
	 	  String logingID = null;
	      String jmsMessageID = null;
		  String jmsCorrelationID = null;
	      Destination jmsReplyTo = null;
	      String jmsReplyToName = null;
	      String embusMessageTag = "modifyInventory";
   
	      try 
		  {
			jmsCorrelationID =  msg.getJMSCorrelationID();
			jmsMessageID = msg.getJMSMessageID();
			
			if (jmsCorrelationID == null)
				jmsCorrelationID = "JMSCorreclationId";
			
			jmsReplyTo = msg.getJMSReplyTo();
			
			if (jmsReplyTo instanceof Queue) 
	         	jmsReplyToName = ((Queue)jmsReplyTo).getQueueName(); 
	        if (jmsReplyTo instanceof Topic) 
	         	jmsReplyToName = ((Topic)jmsReplyTo).getTopicName();
	        
	        if (jmsReplyToName == null)
			   jmsReplyToName = "JMSReplyToQueue";
	        
			} 
          catch (JMSException e) 
		  {
          	
          	log(LOG_FAILURE,ExceptionCode.ERR_RM_UNEXPECTED_ERROR + " Unexpected error processing the message1. " + e.getMessage());
          	
		  }
          
          logingID = myLogger.getBisLogger().get_correlation_id();
          
          //IOM uses Application and BusinessUnit . Value is "RMBIS", but to use generic JMS sender
          //Application will be set as BBNMS.
	      //create aBisContext what else properties ..
		  ObjectProperty[] temp = new ObjectProperty[8]; 
	      temp[0] = new ObjectProperty("Application", "RMBIS");
	      temp[1] = new ObjectProperty("CustomerName", "SOACGI");
	      temp[2] = new ObjectProperty("BusinessUnit", "RMBIS");
	      temp[3] = new ObjectProperty("LoggingInformation", logingID);
	      temp[4] = new ObjectProperty("JMSMessageID", jmsMessageID);
	      temp[5] = new ObjectProperty("JMSCorrelationId", jmsCorrelationID);
	      temp[6] = new ObjectProperty("JMSReplyToQueue", jmsReplyToName);
	      temp[7] = new ObjectProperty("JMSMessageTag", embusMessageTag);
	     
	      callerContext = new BisContext(temp);
	 }

	private void logAuditTrail(String aMsgTxt, String aTrans, String aErrMsg)
	{
		log(LogEventId.AUDIT_TRAIL, "RECEIVED: [ " + (aMsgTxt != null ? aMsgTxt : "unknown") + "]");
		log(LogEventId.AUDIT_TRAIL, "TRANS_TYPE: [" + (aTrans != null ? aTrans : "unknown") + "]");
		log(LogEventId.AUDIT_TRAIL, "EXCEPTION: [" + (aErrMsg != null ? aErrMsg : "unknown") + "]");
	}
	
	public void setSessionContext(javax.ejb.SessionContext ctx) throws java.rmi.RemoteException
	{
		mySessionCtx = ctx;
	}
	
	public void loadProperty(BisContext bisContext) throws SystemFailure
	{
		String myMethodNameName = "SOACGIMessageDrivenBean.loadProperty()";
		log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

		try
		{
			
			if (PROPERTIES == null)
			{
				PROPERTIES = new java.util.Properties();
				Context cxt = new javax.naming.InitialContext();
				String loc = (String) cxt.lookup(PROP_FILE_LOCATION);
				PROPERTIES = PropertiesFileLoader.read(loc, null);
				
				setPROPERTIES(PROPERTIES);
				setCallerContext(bisContext);
				
				log(LogEventId.DEBUG_LEVEL_2, "RM related properties loaded...");
			}
			else
			{
				setCallerContext(bisContext);
			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			log(LogEventId.INFO_LEVEL_1,"Load Properties NullPointerException <" + e + ">");
			ExceptionData excData =
				new ExceptionData(
					ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
					"Required RM properties not found during server startup.",
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable));
			log(LogEventId.INFO_LEVEL_1,"Code=[" + excData.aCode + "] Description=" + excData.aDescription);
			throw new SystemFailure(bisContext, excData);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log(LogEventId.INFO_LEVEL_1,"Load Properties Exception <" + e.getMessage() + ">");
			ExceptionData excData =
				new ExceptionData(
					ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
					"RM properties file not found during server startup.",
					IDLUtil.toOpt("RM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable));
			log(LogEventId.INFO_LEVEL_1,"Code=[" + excData.aCode + "] Description=" + excData.aDescription);
			throw new SystemFailure(bisContext, excData);
		}
		log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
	}
	
	public void ejbRemove() {
	}
}
