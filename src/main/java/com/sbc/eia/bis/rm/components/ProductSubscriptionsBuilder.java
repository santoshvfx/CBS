//$Id: ProductSubscriptionsBuilder.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

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
//# Date      	| Author              | Notes
//# --------------------------------------------------------------------
//# 6/02/2006	 Michael Khalili		Creation
package com.sbc.eia.bis.rm.components;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
// JDOM imports
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.ProcessingInstruction;
import org.jdom.input.DOMBuilder;  
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;  
// SAX and DOM
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import java.util.Stack;
import java.util.StringTokenizer;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sun.java.util.collections.ArrayList;


//// Xerces
//import org.apache.xerces.parsers.DOMParser;
//import javax.xml.parsers.*;
//import org.w3c.dom.*;

/**
 * @author mk2394
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductSubscriptionsBuilder {
	org.w3c.dom.Document domDoc;
	public ProductSubscriptionsBuilder(String systemID) 
	throws Exception 
	{
		sFilename = systemID;
	}
	static int inerPsubCtr = 0;
	private String sFilename = null;
	
    public ProductSubscription[] getProductSubscriptions()
	throws Exception
    {
		ProductSubscription[] pSubsArray = null;
		SAXBuilder builder = new SAXBuilder();
		ArrayList aList = new ArrayList();
		Stack aStack = new Stack();

			try {
			  Document doc = builder.build(sFilename);
			  Element root = doc.getRootElement();
			  listChildren(root, 0,false,aStack,aList);      
			  pSubsArray =(ProductSubscription[]) aList.toArray(new ProductSubscription[aList.size()]);
			}
			// indicates a well-formedness error
			catch (JDOMException e) { 
			  System.out.println(sFilename + " is not well-formed.");
			  System.out.println(e.getMessage());
			}  
//			catch (IOException e) { 
//			  System.out.println(e);
//			}  
  
      return pSubsArray;
    }

	public static void main(String[] args) 
	throws Exception
	{
	  ProductSubscription[] pSubsArray = null;
	  ProductSubscriptionsBuilder psubBuilder = null;
	  psubBuilder = new ProductSubscriptionsBuilder(args[0]);
	  pSubsArray = psubBuilder.getProductSubscriptions();
      int iii = 0;

	}	
	public static void listChildren(Element current, int depth,boolean bChildSub,Stack aStack,ArrayList aList) {
   
		printSpaces(depth);
		String name = current.getName();
		String text = current.getText();
		if (!text.startsWith("\n"))
		  System.out.println(name + " = " + text);
		else
		  System.out.println(name);
		
		if ( name.equalsIgnoreCase("ProductSubscriptions") && !text.equalsIgnoreCase("NULL"))
		{
			bChildSub = true;
		}
		
		if ( name.equalsIgnoreCase("ProdutSubscription"))
		{
		  // create a new ProductSub object and add it into the Stack
		 
		  Location lo = null;
		  LocationOpt loOpt = null;
		
		  lo = new Location(((StringOpt)IDLUtil.toOpt((String) "Testing")),(new ProviderLocationProperty[0]));
		  loOpt = (LocationOpt)IDLUtil.toOpt(LocationOpt.class,lo);
		  ProductSubscription PSub = new ProductSubscription(new ObjectKey("", ""),"",null,null,null,null,null,(new ProductSubscription[0]),loOpt,loOpt,null);
		  if (bChildSub)
		  {
			ProductSubscription aObj;
			aObj = (ProductSubscription) aStack.pop();
			aObj.aProductSubscriptions = new ProductSubscription[1];
			aObj.aProductSubscriptions[0] = PSub;
			//aObj.equals()
			aStack.push(aObj);
			bChildSub = false;
			inerPsubCtr++;		  
		  }
		  aStack.push(PSub);
		}
		else if ( name.equalsIgnoreCase("aProductSubscriptionID"))
        {
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;
			aObj = (ProductSubscription) aStack.pop();
			aObj.aProductSubscriptionID = objHelpers.getObjectKey(st);
			aStack.push(aObj);
        }
		else if ( name.equalsIgnoreCase("aProductId"))
		{
			ProductSubscription aObj;
			aObj = (ProductSubscription) aStack.pop();
			aObj.aProductID = text;
			aStack.push(aObj);
		}
		else if ( (name.equalsIgnoreCase("aProductName")) )
		{
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;

		    aObj = (ProductSubscription) aStack.pop();
		    aObj.aProductName = objHelpers.getStringOpt(st);
		    aStack.push(aObj);
		}
		else if ( (name.equalsIgnoreCase("aBillingAccountNumber")))
		{
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;

		    com.sbc.eia.idl.types.CompositeObjectKey objKey = null;
		    com.sbc.eia.idl.types.CompositeObjectKeyOpt objKeyOpt = null;
		    objKey = objHelpers.getCompositeObjectKey(st);
		    objKeyOpt = (com.sbc.eia.idl.types.CompositeObjectKeyOpt)IDLUtil.toOpt(com.sbc.eia.idl.types.CompositeObjectKeyOpt.class,objKey);
		    aObj = (ProductSubscription) aStack.pop();
		    aObj.aBillingAccountID = (com.sbc.eia.idl.types.CompositeObjectKeyOpt)objKeyOpt;
		    aStack.push(aObj);
		}
		else if ( (name.equalsIgnoreCase("aContractId")))
		{
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;
		  
		  aObj = (ProductSubscription) aStack.pop();
		  aObj.aContractID = objHelpers.getStringOpt(st);
		  aStack.push(aObj);
		}
		else if ( (name.equalsIgnoreCase("aFirstInstallationDate")))
		{
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;

		  aObj = (ProductSubscription) aStack.pop();
		  aObj.aFirstInstallationDate = objHelpers.getDateOpt(st);
		  aStack.push(aObj);
		}
		else if ( (name.equalsIgnoreCase("aProductSubscriptionStatus")))
		{
			StringTokenizer st = new StringTokenizer(text, "|");
			ProductSubscription aObj;

		  aObj = (ProductSubscription) aStack.pop();
		  aObj.aProductSubscriptionStatus = objHelpers.getStringOpt(st);
		  aStack.push(aObj);
		}
		else if ( name.equalsIgnoreCase("Properties"))
		{
			String strProperties = "";
			List children = current.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) 
			{
			  Element child = (Element) iterator.next();
			  strProperties += child.getText();		
			  current = child;
			}
			StringTokenizer st = new StringTokenizer(strProperties, "|");
			ProductSubscription aObj;
			aObj = (ProductSubscription) aStack.pop();
			aObj.aProperties = objHelpers.getObjectPropertySeqOpt(st);
			if (inerPsubCtr == 0)
			  aList.add(aObj);
		   else
		     inerPsubCtr--;
		}

		List children = current.getChildren();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
		  Element child = (Element) iterator.next();
		  listChildren(child, depth+1,bChildSub,aStack,aList);
		}
    
	  }
  
	  private static void printSpaces(int n) {
    
		for (int i = 0; i < n; i++) {
		  System.out.print(' '); 
		}
    
	  }

}
