// $Id: TOPLISTENERHelper.java,v 1.2 2008/06/02 18:51:13 my6546 Exp $

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
//# --------------------------------------------------------------------
//# 6/14/2005 | Jinmin Ni	        | create
//# 6/02/2008 | Jon Costa           | Removed system.out.println of FCIF string.

package com.sbc.gwsvcs.service.toplistener;

import com.sbc.gwsvcs.access.vicuna.EventClassPair;
import com.sbc.gwsvcs.access.vicuna.EventObjectPair;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.toplistener.exceptions.TOPLISTENERException;
import com.sbc.gwsvcs.service.toplistener.interfaces.ExceptionResp_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.ExceptionResp_tMsg;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_tMsg;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_tMsg;

public class TOPLISTENERHelper extends ServiceHelper
{
	public static final short SEGMENT_SIZE=2000;

	static final EventClassPair topListenerExpected[] =
		new EventClassPair[] {
			new EventClassPair(
				TOPLISTENERAccess.TOPLISTENER_REQ,
				TOPSendToHostReq_tMsg.class,
				TOPLISTENERAccess.TOPLISTENER_REQ_NBR),
			new EventClassPair(
				TOPLISTENERAccess.TOPLISTENER_RESP,
				TOPSendToHostAckResp_tMsg.class,
				TOPLISTENERAccess.TOPLISTENER_RESP_NBR),
			new EventClassPair(
				TOPLISTENERAccess.EXCEPTION,
				ExceptionResp_tMsg.class,
				TOPLISTENERAccess.EXCEPTION_NBR)};

	/**
	 * @param properties
	 * @param aLogger
	 * @throws ServiceException
	 */
	public TOPLISTENERHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
		throws ServiceException
	{
		super(properties, aLogger, TOPLISTENERAccess.name);
		
		serviceAccess = new TOPLISTENERAccess(vicunaXmlFile, serviceXmlDir, logger);
		if (extractedTimeOut != null)
			setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
		setDefaultApplData(extractedApplData);
	}
  
	/**
	 * @param result
	 * @return
	 * @throws TOPLISTENERException
	 * @throws ServiceException
	 */
	private EventResultPair evalTopListenerReq(EventObjectPair result)
		throws TOPLISTENERException, ServiceException
	{
		try
		{

			switch (result.eventNbr)
			{
				case TOPLISTENERAccess.TOPLISTENER_RESP_NBR :
					return new EventResultPair(
						((TOPSendToHostAckResp_tMsg) result.anObject).value,
						result.eventNbr);
				case TOPLISTENERAccess.EXCEPTION_NBR :
					ExceptionResp_t e = ((ExceptionResp_tMsg) result.anObject).value;
					throw new TOPLISTENERException(
						String.valueOf(e.OutExcp.ERR_CD),
						e.OutExcp.ERR_TX);
				default :
					throw new TOPLISTENERException(
						null,
						"TOPLISTENERHelper::evalTopListnerReq: Unexpected return from sendAndReceive() : "
							+ result.event
							+ " with eventNBR of "
							+ result.eventNbr);
			}
		}
		catch (ClassCastException e)
		{
			throw new TOPLISTENERException(null, "TOPLISTENERHelper::evalTopListnerReq: "+e.getMessage());
		}
	}

	/**
	 * @param anApplData
	 * @param aService
	 * @param aTimeOut
	 * @param request
	 * @return
	 * @throws TOPLISTENERException
	 * @throws ServiceException
	 */
	//client inteface of the this servicewrapper
	public EventResultPair topListenerReq(
		String anApplData,
		String aService,
		long aTimeOut,
		TOPSendToHostReq_t request)
		throws TOPLISTENERException, ServiceException
	{
		request.FCIFData = addMessageDescriptor(request.FCIFData);
		EventObjectPair inRequest =
			new EventObjectPair(
				TOPLISTENERAccess.TOPLISTENER_REQ,
				new TOPSendToHostReq_tMsg(request),
				TOPLISTENERAccess.TOPLISTENER_REQ_NBR);
		

		return evalTopListenerReq(
				serviceAccess.connectSendReceiveAndDisconnect(
				anApplData,
				aService,
				factorTimeOut(aTimeOut),
				inRequest,
				topListenerExpected));

	}
	
	//convert FCIF by adding 2 binary length at the beginning of each segment 
	//to indicate the length of the each segment
	public String addMessageDescriptor(String aFCIFString)
	{
		int lenght = 0;
		int position = 0;
		int segCount = 0;
	    short lenPrfx = 0;
		
		//FCIF string should end with new line. if not,  add new line to the end of string
		if(!aFCIFString.endsWith("\n"))
		{
			aFCIFString = aFCIFString + "\n";
		}
		
		StringBuffer aStringBuf = new StringBuffer(aFCIFString);
		for(lenght=aFCIFString.length();lenght > 0; lenght-=(SEGMENT_SIZE-2),segCount++)
		{
			if(lenght>(SEGMENT_SIZE-2))
			{
				lenPrfx = SEGMENT_SIZE;
			}
			else
			{
				lenPrfx =  (new Integer(lenght+ 2)).shortValue();	
			}
			position = segCount*SEGMENT_SIZE;
			aStringBuf.insert(position,getBytesForShort(lenPrfx));
		}
		return aStringBuf.toString();
	}
	
	/**
	 * @param size
	 * @return
	 */
	public String getBytesForShort(short size)
	{
		byte[] bytes = new byte[2];
		bytes[0] = (byte) ((size >> 8) & 0xff);
		bytes[1] = (byte) ((size) & 0xff);
		return new String(bytes);
	}
}
