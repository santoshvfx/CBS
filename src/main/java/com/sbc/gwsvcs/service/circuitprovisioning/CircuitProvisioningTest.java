// $Id: CircuitProvisioningTest.java,v 1.3 2002/10/17 17:29:18 rg3454 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning;

import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import java.util.*;
import com.sbc.gwsvcs.service.circuitprovisioning.exceptions.*;
import com.sbc.gwsvcs.service.circuitprovisioning.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Provides standalone testing.
 * Creation date: (2/26/01 12:32:12 PM)
 * @author: Creighton Malet
 */
public class CircuitProvisioningTest implements com.sbc.bccs.utilities.Logger {
/**
 * Class constructor.
 */
public CircuitProvisioningTest() {
	super();
}
/**
 * Implementation of Logger.log().
 * Creation date: (2/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * Executes the program.
 * Creation date: (2/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
	Hashtable props = new Hashtable();
	props.put("APPLDATA", "ccmalet");
	props.put("CIRCUITPROVISIONING_APPLDATA", "");
	props.put("CIRCUITPROVISIONING_TIMEOUT", "60");
	props.put("GWSVCS_CLNTUUID", "rmbis");
	props.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
	props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");
	
	try
	{
		EventResultPair response = null;
		ArrayList responseSet = null;
		CircuitProvisioningTest aCircuitProvisioningTest = new CircuitProvisioningTest();
		CircuitProvisioningHelper helper = new CircuitProvisioningHelper(props, aCircuitProvisioningTest);

 
//		RdlocQInput rdlocRequest = new RdlocQInput("IMSCTL09", "NWHNCT03DC0");
//		RdlocQInput rdlocRequest = new RdlocQInput("AIMSDI31", "CHCGILCL");
		RdlocQInput rdlocRequest = new RdlocQInput("IMSA5", "STLSMOVZWTC");
		responseSet = helper.rdlocQInput(null, null, 0, rdlocRequest, helper.RECEIVE_ALL_MESSAGES);
		ListIterator rdlocE = responseSet.listIterator();
		while (rdlocE.hasNext())
		{
			response = (EventResultPair)rdlocE.next();
			System.out.println("Received event: " + response.getEventNbr());
			RdlocOutput aRdlocOutput = (RdlocOutput)response.getTheObject();
			System.out.println("description<" + aRdlocOutput.description + ">");
		}
		
		
 		CblsQInput cblsRequest = new CblsQInput("IMSA5", "KSCYMO55", "KSCYMO55HA1", "FIBERNET", "3", "25"); // Tirks error 302 -> 999
//		CblsQInput cblsRequest = new CblsQInput("IMSA5", "KSCYMO55", "KSCYMO55H16", " WAN-DSX1", "1", "60"); // Tirks error 302 -> 999
		responseSet = helper.cblsQInput(null, null, 0, cblsRequest, helper.RECEIVE_ALL_MESSAGES);
		ListIterator cblsE = responseSet.listIterator();
		while (cblsE.hasNext())
		{
			response = (EventResultPair)cblsE.next();
			System.out.println("Received event: " + response.getEventNbr());
			CblsOutput aCblsOutput = (CblsOutput)response.getTheObject();
			for (int i = 0 ; i < aCblsOutput.CblsGrp.length; i++)		
				System.out.println("unit<" + aCblsOutput.CblsGrp[i].unit + 
					"> cktid_clo<" + aCblsOutput.CblsGrp[i].cktid_clo +
					"> activity_cur<" + aCblsOutput.CblsGrp[i].activity_cur +
					"> activity_pnd<" + aCblsOutput.CblsGrp[i].activity_pnd +
					"> due_date<" + aCblsOutput.CblsGrp[i].due_date.MO + "/" +
									aCblsOutput.CblsGrp[i].due_date.DY + "/" +
									aCblsOutput.CblsGrp[i].due_date.YR +
					">");
		}
 		
		EqpscQInput eqpscRequest = new EqpscQInput("AIMSDI31",
		 		"CRPNINCX", "SNM1ZRA1RA", "I", "010103.33A", "155");	
		responseSet = helper.eqpscQInput(null, null, 0, eqpscRequest, helper.RECEIVE_ALL_MESSAGES);
		ListIterator eqpscE = responseSet.listIterator();
		while (eqpscE.hasNext())
		{
			response = (EventResultPair)eqpscE.next();
			System.out.println("Received event: " + response.getEventNbr());
			EqpscOutput aEqpscOutput = (EqpscOutput)response.getTheObject();
			for (int i = 0 ; i < aEqpscOutput.EqpscGrp.length; i++)		
				System.out.println("unit<" + aEqpscOutput.EqpscGrp[i].unit + 
					"> cktid_clo<" + aEqpscOutput.EqpscGrp[i].circuitid +
					"> activity_cur<" + aEqpscOutput.EqpscGrp[i].curact +
					"> activity_pnd<" + aEqpscOutput.EqpscGrp[i].pendact +
					"> due_date<" + aEqpscOutput.EqpscGrp[i].duedate +
					">");
		}
		
 
		CxrsQInput request1 = new CxrsQInput("IMSA5", "STLSMO01", "STLSMOVZWTC", "302", "T3"); // Tirks error 302 -> 999
		responseSet = helper.cxrsQInput(null, null, 0, request1, helper.RECEIVE_ALL_MESSAGES);
		ListIterator e1 = responseSet.listIterator();
		while (e1.hasNext())
		{
			response = (EventResultPair)e1.next();
			System.out.println("Received event: " + response.getEventNbr());
			CxrsOutput aCxrsOutput = (CxrsOutput)response.getTheObject();
			System.out.println("cxr_type<" + aCxrsOutput.cxr_type + ">");
			for (int i = 0 ; i < aCxrsOutput.CxrsGrp.length; i++)		
				System.out.println("channel_pair<" + aCxrsOutput.CxrsGrp[i].channel_pair + 
					"> circuit_id<" + aCxrsOutput.CxrsGrp[i].circuit_id +
					"> activity_cur<" + aCxrsOutput.CxrsGrp[i].cur_act +
					"> activity_pnd<" + aCxrsOutput.CxrsGrp[i].pend_act +
					"> due_date<" + aCxrsOutput.CxrsGrp[i].due_date_data.MO + "/" +
					aCxrsOutput.CxrsGrp[i].due_date_data.DY + "/" +
					aCxrsOutput.CxrsGrp[i].due_date_data.YR +
					">");
		}


	
		DriQInput request2 = new DriQInput("IMSA5", "", "s 41/HCGS/601881/sw", "", "");
		responseSet = helper.driQInput(null, null, 0, request2, helper.RECEIVE_ALL_MESSAGES);
		ListIterator e2 = responseSet.listIterator();
		while (e2.hasNext())
		{
			response = (EventResultPair)e2.next(); 
			System.out.println("Received event: " + response.getEventNbr());
			DriOutput aDriOutput = (DriOutput)response.getTheObject();
			System.out.println("ic_tel<" + aDriOutput.ic_tel + ">");
		}

		
		WaQInput request3 = new WaQInput("IMSA5", "", "", "s 41/HCGS/601881/sw"); // Tirks error cac = junk
		responseSet = helper.waQInput(null, null, 0, request3, helper.RECEIVE_ALL_MESSAGES);
		ListIterator e3 = responseSet.listIterator();
		while (e3.hasNext())
		{
			response = (EventResultPair)e3.next();
			System.out.println("Received event: " + response.getEventNbr());
			WaOutput aWaOutput = (WaOutput)response.getTheObject();
			System.out.println("acna<" + aWaOutput.acna + ">");
		}

		// WA with separate send/receive
		try {
			helper.connect(null, null);
			helper.waQInput(request3);
			responseSet = helper.waQInput(0, helper.RECEIVE_ALL_MESSAGES);
			ListIterator e3a = responseSet.listIterator();
			while (e3a.hasNext())
			{
				response = (EventResultPair)e3a.next();
				System.out.println("Received event: " + response.getEventNbr());
				WaOutput aWaOutput = (WaOutput)response.getTheObject();
				System.out.println("acna<" + aWaOutput.acna + ">");
			}
		}
 

 		finally
 		{
 			helper.disconnect();
 		}
	}

	
	catch (CircuitProvisioningException e)
	{
		System.out.println("CircuitProvisioningException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}
}
}
