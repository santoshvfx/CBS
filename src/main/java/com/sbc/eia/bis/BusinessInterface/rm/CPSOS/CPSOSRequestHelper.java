//$Id: CPSOSRequestHelper.java,v 1.2 2009/02/19 21:38:31 sl7683 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 01/2009      Sheilla Lirio         Creation.

package com.sbc.eia.bis.BusinessInterface.rm.CPSOS;

import java.io.StringWriter;
import java.util.Date;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class:		CPSOSRequestHelper
 * Description:	This class will create the XML request document and convert the request to String
 * @author 		sl7683
 *
 */
public class CPSOSRequestHelper {
	
	private Utility aUtility = null;
    private Hashtable aProperties = null;
     
	
    DslAccountLookupRequest aReqDetails;
    Document aXmlReq;
    
	/**
	 * Contructor.
	 * 
	 * @param utility
	 * @param properties
	 * @param reqDetails
	 */
	public CPSOSRequestHelper(Utility utility, Hashtable properties, DslAccountLookupRequest reqDetails)
	{
		aProperties = properties;
        aUtility = utility;
        aReqDetails = reqDetails;
	}
	
	/**
	 * Creates CPSOS Xml request in String format.
	 * 
	 * @param reqDetails
	 * @return
	 * 
	 * @author sl7683
	 */
	public String createCPSOSRequest (DslAccountLookupRequest reqDetails)
	{
		String aMethodName = "CPSOSRequestHelper: createCPSOSRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
		String aXmlString;
		createXmlDocument();
		createXmlTree();
		aXmlString = printXmlToString();
		
		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		return aXmlString;
	}
	
	/**
	 * Creates the Xml document where the request will be created.
	 * 
	 * @author sl7683
	 */
	private void createXmlDocument() 
	{
		String aMethodName = "CPSOSRequestHelper: createXmlDocument()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try 
		{
			//get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//create an instance of DOM
			aXmlReq = db.newDocument();
		
		}
		catch (ParserConfigurationException e) 
		{
			StringBuffer parserLogMessage = new StringBuffer();
			parserLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, parserLogMessage.toString());
		} 
		
		finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}

	}
	
	/**
	 * Creates the Xml Header elements.
	 * 
	 * @author sl7683
	 */
	private void createXmlTree()
	{
		
		String aMethodName = "CPSOSRequestHelper: createXmlTree()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		
        try 
        {
        	//create the root element
        	Element rootElem = aXmlReq.createElement(CPSOSConstants.Xml_Root_Element);
        	aXmlReq.appendChild(rootElem);
        	rootElem.setAttribute(CPSOSConstants.NameSpace_Qualified_Name, CPSOSConstants.NameSpace_URI);
        	rootElem.setAttribute(CPSOSConstants.NoNamSpace_Qualified_Name, CPSOSConstants.Schema_Location);
        	
        	//create Request Identification sub element
			Element reqIdElem = createReqIdElement(aReqDetails);
			rootElem.appendChild(reqIdElem);
			
			//create DSL Account Lookup Request sub element
			Element dslAcctReqElem = createDslAcctReqElement(aReqDetails);
			rootElem.appendChild(dslAcctReqElem);
			
        }
        catch (DOMException e){
        	StringBuffer domLogMessage = new StringBuffer();
			domLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, domLogMessage.toString());
        }
        finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
	}
	
	/**
	 * Creates the Xml elements for Request Identification.
	 * 
	 * @param req
	 * @return
	 * 
	 * @author sl7683
	 */
	private Element createReqIdElement(DslAccountLookupRequest req)
	{
		String aMethodName = "CPSOSRequestHelper: createReqIdElement()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        Element reqIdElem = aXmlReq.createElement(CPSOSConstants.Xml_ReqId_Element);
		
        try
        {
	        //create RequestIdentification attributes and populate
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqType_Attribute, req.getReqType());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ToolbarUserId_Attribute, req.getUserId());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ToolbarPswd_Attribute, req.getPassword());
			reqIdElem.setAttribute(CPSOSConstants.Xml_PartnerId_Attribute, req.getPartnerId());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqstrOrgId_Attribute, req.getReqOrgId());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqstrAffId_Attribute, req.getReqAffiliateId());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqstrTrkNbr_Attribute, req.getTrackingNumber());
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqstrTimeStamp_Attribute, req.getTimestamp().format(new Date()));
			reqIdElem.setAttribute(CPSOSConstants.Xml_ReqstrServerName_Attribute, req.getServerName());
			reqIdElem.setAttribute(CPSOSConstants.Xml_IntVer_Attribute, req.getInterfaceVesion());
        
        }
		catch (DOMException e){
        	StringBuffer domLogMessage = new StringBuffer();
			domLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, domLogMessage.toString());
        }
		finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return reqIdElem;
	}
	
	/**
	 * Creates the Xml elements for the DSL Account Lookup Request.
	 * 
	 * @param req
	 * @return
	 * 
	 * @author sl7683
	 */
	private Element createDslAcctReqElement(DslAccountLookupRequest req)
	{
		String aMethodName = "CPSOSRequestHelper: createDslAcctReqElement()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        Element dslAcctReqElem = aXmlReq.createElement(CPSOSConstants.Xml_AccountLookupReq_Element);
                
        try 
        {
			//create DslPhone element and attributes and populate
			Element dslPhoneElem = aXmlReq.createElement(CPSOSConstants.Xml_DslPhone_Element);
			dslPhoneElem.setAttribute(CPSOSConstants.Xml_DslTnNpa_Attribute, req.getNpa());
			dslPhoneElem.setAttribute(CPSOSConstants.Xml_DslTnNxx_Attribute, req.getNxx());
			dslPhoneElem.setAttribute(CPSOSConstants.Xml_DslTnLine_Attribute, req.getLine());
			dslAcctReqElem.appendChild(dslPhoneElem);
			
			//create ServiceAddress element and attributes and populate
			Element svcAddElem = aXmlReq.createElement(CPSOSConstants.Xml_ServiceAddress_Element);
			svcAddElem.setAttribute(CPSOSConstants.Xml_SrvcAddrState_Attribute, req.getState());
			dslAcctReqElem.appendChild(svcAddElem);
			
			//create AcctLookupDetails element and attributes and populate
			Element acctDetailsElem = aXmlReq.createElement(CPSOSConstants.Xml_AcctLookupDetails_Element);
			acctDetailsElem.setAttribute(CPSOSConstants.Xml_EndUserAuthorization_Attribute, Integer.toString(req.getEndUserAuth()));
			dslAcctReqElem.appendChild(acctDetailsElem);
			
        }
        catch (DOMException e){
        	StringBuffer domLogMessage = new StringBuffer();
			domLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, domLogMessage.toString());
        }
        finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return dslAcctReqElem;
	}
	
	/**
	 * Converts Xml document to String format.
	 * 
	 * @author sl7683
	 */
	private String printXmlToString() 
	{
		String aMethodName = "CPSOSRequestHelper: printXmlToString()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        String xmlString = "";
        
		TransformerFactory transFac = TransformerFactory.newInstance();
		try 
		{
			Transformer trans = transFac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(aXmlReq);
			trans.transform(source, result);
			xmlString = sw.toString();
			
		} 
		catch (TransformerException e) 
		{
			StringBuffer transLogMessage = new StringBuffer();
			transLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, transLogMessage.toString());
		} 
		finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return xmlString;
	}

}
