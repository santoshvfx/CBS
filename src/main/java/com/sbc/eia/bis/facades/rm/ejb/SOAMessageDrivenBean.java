// $Id: SOAMessageDrivenBean.java,v 1.6 2008/06/26 18:24:56 jc1421 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 06/13/2006	Jyothi Jasti       Creation
//# 06/22/2006  Jyothi Jasti       Pre Code WT and WT changes.
//# 10/30/2007  Mrinalini Peddi    Added ERR_RM_UNEXPECTED_ERROR in logging.
//# 06/26/2008  Jon Costa          DR:99255: A Unique Correlation ID is not being returned.

package com.sbc.eia.bis.facades.rm.ejb;

import javax.jms.TextMessage;
import javax.naming.InitialContext;

import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.facades.rm.ejb.PublishTNPortingNotification.PublishTNPortingNotification;
import com.sbc.eia.bis.facades.rm.ejb.PublishTNPortingNotification.PublishTNPortingNotificationHome;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.exception_types.ExceptionCode;

/**
 * Bean implementation class for Enterprise Bean: SOAMessageDrivenBean
 */
public class SOAMessageDrivenBean
	extends TranBase
	implements javax.ejb.MessageDrivenBean, javax.jms.MessageListener
{
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	private javax.ejb.SessionContext mySessionCtx = null;

	/**
	 * Method getMessageDrivenContext
	 * @return the MessageDrivenContext
	 */
	public javax.ejb.MessageDrivenContext getMessageDrivenContext()
	{
		return fMessageDrivenCtx;
	}

	/* (non-Javadoc)
	 * @see javax.ejb.MessageDrivenBean#setMessageDrivenContext(javax.ejb.MessageDrivenContext)
	 */
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx)
	{
		fMessageDrivenCtx = ctx;
	}

	/**
	 * Method getSessionContext
	 * @return the Sessioncontext
	 */
	public javax.ejb.SessionContext getSessionContext()
	{
		return mySessionCtx;
	}

	/**
	 * Method ejbCreate
	 * @throws javax.ejb.CreateException
	 * @throws java.rmi.RemoteException
	 */
	public void ejbCreate() throws javax.ejb.CreateException, java.rmi.RemoteException
	{
		init(mySessionCtx);
	}

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(javax.jms.Message msg)
	{

		callerContext = new BisContext();
        this.setCallerContext(callerContext);
		log(LOG_ENTRY, "SOAMessageDrivenBean:onMessage()");
		String msgTxt = null;

		try
		{

			if (msg instanceof TextMessage)
			{

				msgTxt = ((TextMessage) msg).getText();

				//doing the local EJB lookup     
				InitialContext ctx = new InitialContext();
				Object o = ctx.lookup("java:comp/env/ejb/PublishTNPortingNotificationHome");

				PublishTNPortingNotificationHome home =
					(PublishTNPortingNotificationHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						PublishTNPortingNotificationHome.class);

				PublishTNPortingNotification publishTNPortingNotificationBean = home.create();

				publishTNPortingNotificationBean.publishTNPortingNotification(msgTxt, myLogger);
			}
			else
			{
				log(LOG_INFO_LEVEL_1, "Received NON TextMessage from SOA: ");
			}

		}
		catch (Throwable e)
		{
			log(LOG_FAILURE,ExceptionCode.ERR_RM_UNEXPECTED_ERROR + "Unexpected error processing the message. " + e.getMessage());
		}

		log(LOG_EXIT, "SOAMessageDrivenBean:onMessage()");

	}

	/**
	 * Method setSessionContext
	 * @param ctx
	 * @throws java.rmi.RemoteException
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) throws java.rmi.RemoteException
	{
		mySessionCtx = ctx;
	}

	/* (non-Javadoc)
	 * @see javax.ejb.MessageDrivenBean#ejbRemove()
	 */
	public void ejbRemove()
	{}

}