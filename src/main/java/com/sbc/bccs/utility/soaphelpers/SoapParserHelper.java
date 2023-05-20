//$Id: SoapParserHelper.java,v 1.10 2008/05/13 21:39:30 ch1463 Exp $
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
//# 09/19/2005 Jon Costa			 PR16605198: Change closing tag for MessageHeader
//# 05/13/2008 Sriram Chevuturu		 PR 22096284/ 93253: Make sure the embus header has correct sequence.

/**
 * Created on Jun 21, 2004
 *
 * To change this generated comment edit the template variable "filecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of file comments go to
 * Window>Preferences>Java>Code Generation.
 */
package com.sbc.bccs.utility.soaphelpers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author rk7964
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SoapParserHelper
{
    
    private static final String SOAP_ENVELOP_HEADER   = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=" + 
                    "\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body>" ;
                    
    private static final String SOAP_ENVELOP_FOOTER   = "</soapenv:Body></soapenv:Envelope>";
    
    /**
    * Wraps xml in a soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String appendSoapEnvelope(String xml) {
        
        if (xml != null) {
            
            return SOAP_ENVELOP_HEADER + xml.trim() + SOAP_ENVELOP_FOOTER;
            
        } else
        {
            return "";
         
        }
        
        
    }
    
    /**
    * Extracts xml request wrapped in the soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String removeSoapEnvelope(String xml) {
        
        if (xml != null) {
            
            int startIndex = xml.indexOf("<soapenv:Body>");
            int endIndex = xml.indexOf("</soapenv:Body>");
        
            return xml.substring(startIndex + 14, endIndex).trim();
            
        } else
        {
            return "";
          
        }
        
        
    }
    
    /*
     * Added method for change to VAXBLite version 3.1.0
     * Returns XML if no soap envelope is found.
     */
    public static String removeSoapEnvelopeFromVaxB(String xml) {
        if (xml != null) {
    
            int startIndex = xml.indexOf("<soapenv:Body>");
            if(startIndex == -1){
                return xml;
            }
            startIndex = xml.indexOf(">", startIndex + 1) + 1;
            int endIndex = xml.indexOf("</soapenv:Body>");

            return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + xml.substring(startIndex, endIndex).trim();
    
        } else
        {
            return "";
  
        }
    }
    
    /**
    * Wraps xml in a soap fault header and footer and inturn wraps it in a soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String appendSoapFaultEnvelope(String exceptionName, String xml) {
        
        String soapFaultHeader = "<soapenv:Fault>" +
         "<faultcode xmlns:ns-1382140156=\"http://bis.eia.sbc.com/2004/04\"" +
         " xmlns=\"\">ns-1382140156:" + exceptionName + "</faultcode>" +
         "<faultstring xmlns=\"\"><![CDATA[com.sbc.eia.bis." +
         exceptionName + "]]></faultstring>" +
         "<detail xmlns=\"\">";
         
        String soapFaultFooter = "<stackTrace xmlns=\"http://websphere.ibm.com/webservices/\">" +
               "com.sbc.eia.bis." + exceptionName + 
               "&#xd;</stackTrace></detail></soapenv:Fault>";
               
        return appendSoapEnvelope(soapFaultHeader + xml.trim() + soapFaultFooter);
   
        
    }
    
    public static String replaceVaxBWithSoapEnvelope(String xml) {
        
        int index = xml.indexOf("<VAXB");
           
        if (index == -1) {
            return xml;
        } else
        {
            index = xml.indexOf(">", index + 1);
            xml = xml.substring(index + 1);
        }
        
        index = xml.indexOf("</VAXB>");
        
        if (index == -1) {
            return xml;
        } else
        {
            xml = xml.substring(0,index - 1);
        }
        
        return appendSoapEnvelope(xml);
        
    }
    
    /*
     * Added method for change to VAXBLite version 3.1.0
     */
    public static String wrapVaxBWithSoapEnvelope(String xml) {
        
        int index = xml.indexOf("<vaxb:VAXB");
        if(index == -1) {
            index = xml.indexOf("<vaxb:");
        }
           
        if (index == -1) {
            return xml;
        } else
        {
            xml = xml.substring(index - 1);
        }
        
        index = xml.indexOf("</vaxb:VAXB>");
        if(index == -1) {
            index = xml.indexOf("</vaxb:");
        }
        
        if (index == -1) {
            return xml;
        } else
        {
            index = xml.indexOf(">", index + 1);
            xml = xml.substring(0,index + 1);
        }
        
        return appendSoapEnvelope(xml);
    }
    
	/**
	 * Method removeTagsFromXML.
	 * @param xml
	 * @param startTag
	 * @param endTag
	 * @return String
	 */
   	public static String removeTagsFromXML(String xml, String startTag, String endTag) {

		int index = xml.indexOf(startTag);

		if (index == -1) {
			return xml;
		} else {
			index = xml.indexOf(">", index + 1);
			xml = xml.substring(index + 1);
		}

		index = xml.indexOf(endTag);
		if (index == -1) {
			return xml;
		} else {
			xml = xml.substring(0, index);
		}
		return xml;
	}	

	/**
	 * Method appendEMBUSSoapEnvelope.
	 * @param xml
	 * @param messageTags
	 * @return String
	 */
	public static String appendEMBUSSoapEnvelope(String xml, Properties messageTags) {
		
		String new_SOAP_ENVELOP;
		
		int index = SOAP_ENVELOP_HEADER.indexOf("<soapenv:Body>");
		
		if (index == -1) {
			return SOAP_ENVELOP_HEADER;
		} else {
			new_SOAP_ENVELOP = SOAP_ENVELOP_HEADER.substring(0, index);
		}
		
		String SOAP_HEADER = "\n<soapenv:Header> \n <embus:MessageHeader xmlns:embus=\"urn:soap.embus.sbc.com\">";
		
		Set set = messageTags.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			SOAP_HEADER = SOAP_HEADER 
				+ "\n" 
				+ "  <"
				+ (String) entry.getKey()
				+ ">"
				+ (String) entry.getValue()
				+ "</"
				+ (String) entry.getKey()
				+ ">";			
		}

		SOAP_HEADER = SOAP_HEADER 
				+ "\n </embus:MessageHeader>"
				+ "\n</soapenv:Header>"
				+ "\n<soapenv:Body>";

		return (new_SOAP_ENVELOP + SOAP_HEADER + xml + SOAP_ENVELOP_FOOTER);

	}	
	/**
	 * Method appendEMBUSSoapEnvelope.
	 * @param xml
	 * @param messageTags
	 * @return String
	 */
	public static String appendEMBUSSoapEnvelope(String xml, LinkedHashMap messageTags) {
		
		/* This is a overloaded method with LinkedHashMap Argument instead of Property Object.
		 * We have to use LinkedHashMap, instead of Property Object if we need to populate the fields
		 * in the order of their insertion. Hence this method.
		 */
		String new_SOAP_ENVELOP;
		
		int index = SOAP_ENVELOP_HEADER.indexOf("<soapenv:Body>");
		
		if (index == -1) {
			return SOAP_ENVELOP_HEADER;
		} else {
			new_SOAP_ENVELOP = SOAP_ENVELOP_HEADER.substring(0, index);
		}
		
		String SOAP_HEADER = "\n<soapenv:Header> \n <embus:MessageHeader xmlns:embus=\"urn:soap.embus.sbc.com\">";
		
		Set set = messageTags.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			SOAP_HEADER = SOAP_HEADER 
				+ "\n" 
				+ "  <"
				+ (String) entry.getKey()
				+ ">"
				+ (String) entry.getValue()
				+ "</"
				+ (String) entry.getKey()
				+ ">";			
		}

		SOAP_HEADER = SOAP_HEADER 
				+ "\n </embus:MessageHeader>"
				+ "\n</soapenv:Header>"
				+ "\n<soapenv:Body>";

		return (new_SOAP_ENVELOP + SOAP_HEADER + xml + SOAP_ENVELOP_FOOTER);

	}		
}
