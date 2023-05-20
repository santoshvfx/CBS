//$Id: CPSOSResponseHelper.java,v 1.2 2009/02/19 21:39:15 sl7683 Exp $
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

import java.util.Hashtable;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class:		CPSOSResponseHelper
 * Description:	This class will transform the String XML response to XML document to easily retrieve attribute values
 * @author 		sl7683
 *
 */
public class CPSOSResponseHelper {
	
	private Utility aUtility = null;
    private Hashtable aProperties = null;
        
    DslAccountLookupResponse aRespDetails = new DslAccountLookupResponse(aUtility, aProperties);
    
    
    /**
     * Constructor.
     * 
     * @param utility
     * @param properties
     */
    public CPSOSResponseHelper(Utility utility, Hashtable properties)
	{
		aProperties = properties;
        aUtility = utility;
    }
    
	/**
	 * Converts the CPSOS response to Xml document.
	 * 
	 * @param xmlString
	 * @return
	 * 
	 * @author sl7683
	 */
	public DslAccountLookupResponse convertStringToDoc (String xmlString)
	{
		String aMethodName = "CPSOSResponseHelper: convertStringToDoc()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        //create instance of Document Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
        try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			//create Document that will hold XML response
			Document document = builder.parse(new InputSource(new StringReader(xmlString)));
			
			document.getDocumentElement().normalize();
			
			NodeList listRespId = document.getElementsByTagName(CPSOSConstants.Xml_RespId_Element);
			
			Node reqIdNode = listRespId.item(0);
			
			Element reqIdElem = (Element) reqIdNode;
			
			//get the Response Type
			String respType = reqIdElem.getAttribute(CPSOSConstants.Xml_RespType_Attribute);
			aRespDetails.setRespType(respType);
			
			aRespDetails.setUserId(reqIdElem.getAttribute(CPSOSConstants.Xml_ToolbarUserId_Attribute));
			aRespDetails.setReqAffiliateId(reqIdElem.getAttribute(CPSOSConstants.Xml_ReqstrAffId_Attribute));
			aRespDetails.setPartnerId(reqIdElem.getAttribute(CPSOSConstants.Xml_PartnerId_Attribute));
			aRespDetails.setReqOrgId(reqIdElem.getAttribute(CPSOSConstants.Xml_ReqstrOrgId_Attribute));
			aRespDetails.setServerName(reqIdElem.getAttribute(CPSOSConstants.Xml_ReqstrServerName_Attribute));
			aRespDetails.setReqTimestamp(reqIdElem.getAttribute(CPSOSConstants.Xml_ReqstrTimeStamp_Attribute));
			aRespDetails.setRespTimestamp(reqIdElem.getAttribute(CPSOSConstants.Xml_RespTimeStamp_Attribute));
			aRespDetails.setRespId(reqIdElem.getAttribute(CPSOSConstants.Xml_ResponderId_Attribute));
			aRespDetails.setSbcReqTrackingNumber(reqIdElem.getAttribute(CPSOSConstants.Xml_ReqstrTrkNbr_Attribute));
			aRespDetails.setInterfaceVersion(reqIdElem.getAttribute(CPSOSConstants.Xml_IntVer_Attribute));
			
			aUtility.log(LogEventId.INFO_LEVEL_1, "Response Type:: " + respType);
			
			if (respType.equalsIgnoreCase(CPSOSConstants.Xml_AccountLookupResp_Element)) 
			{
				//get WTN if successful
				NodeList listDslAcct = document.getElementsByTagName(CPSOSConstants.Xml_DslPhone_Element);
				
				Node dslAcctNode = listDslAcct.item(0);
				
				Element dslAcctElem = (Element) dslAcctNode;
				
				String npaElem = dslAcctElem.getAttribute(CPSOSConstants.Xml_DslTnNpa_Attribute);
				aRespDetails.setNpa(npaElem);
				String nxxElem = dslAcctElem.getAttribute(CPSOSConstants.Xml_DslTnNxx_Attribute);
				aRespDetails.setNxx(nxxElem);
				String lineElem = dslAcctElem.getAttribute(CPSOSConstants.Xml_DslTnLine_Attribute);
				aRespDetails.setLine(lineElem);
			}
			
			if (respType.equalsIgnoreCase(CPSOSConstants.Xml_ErrorResponse_Element)) 
			{
				//get the Error ID number if not successful
				NodeList listErrResp = document.getElementsByTagName(CPSOSConstants.Xml_ErrorResponse_Element);
				
				Node errRespNode = listErrResp.item(0);
				
				Element errRespElem = (Element) errRespNode;
				
				String errIdElem = errRespElem.getAttribute(CPSOSConstants.Xml_ErrId_Attribute);
				aRespDetails.setErrorId(errIdElem);	
				
				aRespDetails.setErrorLogId(errRespElem.getAttribute(CPSOSConstants.Xml_ErrLogId_Attribute));
				aRespDetails.setErrorText(errRespElem.getAttribute(CPSOSConstants.Xml_ErrTxt_Attribute));
				aRespDetails.setErrorTimestamp(errRespElem.getAttribute(CPSOSConstants.Xml_ErrTimeStamp_Attribute));
			}
		} 
		catch (ParserConfigurationException e) 
		{
			StringBuffer parserLogMessage = new StringBuffer();
			parserLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, parserLogMessage.toString());
		} 
		catch (SAXException e) 
		{
			StringBuffer saxLogMessage = new StringBuffer();
			saxLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, saxLogMessage.toString());
		} 
		catch (IOException e) 
		{
			StringBuffer ioLogMessage = new StringBuffer();
			ioLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, ioLogMessage.toString());
		}
		catch (Exception e) 
		{
			StringBuffer eLogMessage = new StringBuffer();
			eLogMessage.append("Exception: [ ").append(e.getMessage()).append(" ]");
			aUtility.log(LogEventId.ERROR, eLogMessage.toString());
		}
		finally 
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
		}
		
		return aRespDetails;
	}

}
