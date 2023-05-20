//$Id
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date         | Author              | Notes
//# ------------------------------------------------------------
//# 09/10/2009     Sheilla Lirio         Creation.

package com.sbc.eia.bis.BusinessInterface.rm.GRANITE;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


/**
 * LogHandler:  Class that will print the webservice request and response
 * 
 * @author sl7683
 *
 */
public class LogHandler extends GenericHandler {

	public boolean handleRequest(MessageContext msgContext) {
		
        System.out.println("--- LogHandler: handleRequest()");
        doLog(msgContext);
        return true;
	}
	public boolean handleResponse(MessageContext msgContext) {
	        System.out.println("--- LogHandler: handleResponse()");
	        doLog(msgContext);
	        return true;
	}
	public QName[] getHeaders() {
	        System.out.println("--- LogHandler: getHeaders()");
	        return null;
	}

	public void doLog(MessageContext msgContext) {
		
		String xml = null;
		SOAPMessageContext smc = (SOAPMessageContext)msgContext;
        SOAPMessage msg = smc.getMessage();
        SOAPPart sp = msg.getSOAPPart();
        
        System.out.println("--- LogHandler: doLog()");
        
        try {
                SOAPEnvelope se = sp.getEnvelope();
                SOAPBody sb = se.getBody();
                Iterator be = sb.getChildElements();
                SOAPElement m =((SOAPElement)be.next());
                Name name = m.getElementName();
                
                System.out.println("--- Granite WebService URI: "+
		                      name.getURI()+":"+name.getLocalName());
	            xml = sb.toString();    
	            System.out.println("--- Granite WebService Message: " + xml);
	                
	            
        } catch (SOAPException e) {
                e.printStackTrace();
        }
    }

}

