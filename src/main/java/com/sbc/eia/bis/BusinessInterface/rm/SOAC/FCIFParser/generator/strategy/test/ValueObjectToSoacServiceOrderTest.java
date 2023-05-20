/*
 * Created on May 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.generator.strategy.test;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.CVOIPServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValueObjectToSoacServiceOrderTest extends TestCase
{
	
    private String testData = "*C1= CORSO C910200L1   A   479442SOPOMS  SOACSTC   051030    0;%*SC=      ;%*EC=1234567890 5678901234;%\n479 442-1111     XR7FA          07-26             \nC910200L1A                 10-30-05               \nRO    N567890\nZD    /SD 08-20-05\n---LSTG\nLA    102 SCHEELE DR\n---RMKS\nRMK   LIGHTSPEED - 1234567890 - \n      5678901234\n---S&E\nO     AS3NE\n      /CLS AA.MCXX.101002..SW\n      /LSO 479 442\n      /FCI FTTP\n";
	private ParseRequest parseRequest = null;                        
    private SoacServiceOrderVO serviceOrderVO = null;
    private ParserSvc parserSvc = null; 
	private ParserSvc aParserSvc = null;   
	private Logger logger = new TestLogger();
/**
 * Constructor for ValueObjectToSoacServiceOrderTest.
 * @param arg0
 */
public ValueObjectToSoacServiceOrderTest(String arg0)
{
	super(arg0);
}

/*
 * @see TestCase#setUp()
 */
protected void setUp() throws Exception
{
	super.setUp();
   
//    ArrayList list = new ArrayList();
//    String[] stringArray = new String[0];
//    stringArray = (String[])list.toArray(stringArray);

    
    generateServiceOrderVO();
    parseRequest = new ParseRequest();
    parseRequest.setLogger(logger);


}

/**
 * @return
 */
private void generateServiceOrderVO()
{
    serviceOrderVO = new SoacServiceOrderVO();
    //CONTROL HEADER        
        serviceOrderVO.put(SoacServiceOrderConstants.FUNCTION_TYPE, "PRE");//
        serviceOrderVO.put(SoacServiceOrderConstants.ACTION_INDICATOR, "I");//
        serviceOrderVO.put(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM, "C714491L1");
        serviceOrderVO.put(SoacServiceOrderConstants.CORRECTION_SUFFIX, "A");        
        serviceOrderVO.put(SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX, "414321");
        serviceOrderVO.put(SoacServiceOrderConstants.ORIGINATING_HOST_NAME, "RMQC");
        serviceOrderVO.put(SoacServiceOrderConstants.ENTITY, " ");
        serviceOrderVO.put(SoacServiceOrderConstants.DUE_DATE, "05-23-06");
        serviceOrderVO.put(SoacServiceOrderConstants.ENTITY_PLATFORM, "  ");
//      serviceOrderVO.put(SoacServiceOrderConstants.SPECIAL_SECTION_IND, "");
        serviceOrderVO.put(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM,"C714491L1");

    //ECHO SECTION
        serviceOrderVO.put(SoacServiceOrderConstants.OMS_ORDER_NUM, "8234534490");
        serviceOrderVO.put(SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM, "8678934434");
        serviceOrderVO.put(SoacServiceOrderConstants.REGION_INDICATOR, "B"); //W, M, E ,B, " "(Space)

        serviceOrderVO.put(SoacServiceOrderConstants.RESEND_INDICATOR, " ");    
		serviceOrderVO.put(SoacServiceOrderConstants.APPLICATION_INDICATOR, "B");     
        
        serviceOrderVO.put(SoacServiceOrderConstants.TN_OR_NPANXX, "414 321-1111");
        serviceOrderVO.put(SoacServiceOrderConstants.CLASS_OF_SERVICE, "XR7FA");
        serviceOrderVO.put(SoacServiceOrderConstants.APPLICATION_DATE, "05-23-06");
        serviceOrderVO.put(SoacServiceOrderConstants.ORIGINAL_DUE_DATE, "05-23-06");
        serviceOrderVO.put(SoacServiceOrderConstants.RELATED_SERVICE_ORDER, "12345678912");
        serviceOrderVO.put(SoacServiceOrderConstants.SUBSEQ_DUE_DATE, "05-23-06");

    //LISTING SECTION
        serviceOrderVO.put(SoacServiceOrderConstants.BASIC_ADDRESS, "3436S 61 AND NORTHLAND PKWY N,");
        serviceOrderVO.put(SoacServiceOrderConstants.EXTENDED_BASIC_ADDRESS, "WISCONSIN BELLS,WI");
        serviceOrderVO.put(SoacServiceOrderConstants.ASSIGNED_HOUSE_NUM, "");
        serviceOrderVO.put(SoacServiceOrderConstants.SUPPLEMENTAL_ADDRESS_INFO, "BLDG 3 UNIT 47");
        serviceOrderVO.put(SoacServiceOrderConstants.LOCATION_FID,"BLDG 3 UNIT 47");

    //REMARKS SECTION
        serviceOrderVO.put(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN, "1234567890");

    //S&E Section    
        serviceOrderVO.put(SoacServiceOrderConstants.NEW_CONNECT_DISCONNECT, "I");
        serviceOrderVO.put(SoacServiceOrderConstants.CIRCUIT, "A2.MCxx.601791..sw");
        serviceOrderVO.put(SoacServiceOrderConstants.FACS_WIRE_CENTER, "414 321");      
        serviceOrderVO.put(SoacServiceOrderConstants.RELATED_TDMTN, "414 321-7841");
        serviceOrderVO.put(SoacServiceOrderConstants.NETWORK_TYPE, "FTTN");   // FTTP, RGPN. GPON
        serviceOrderVO.put(SoacServiceOrderConstants.ADDITIONAL_LINE_FLAG, "N");
        
        CVOIPServiceOrderVO[] VoipServiceOrders = new CVOIPServiceOrderVO[1];
        VoipServiceOrders[0] = new CVOIPServiceOrderVO("1","2","3","4","5","6","7","8");
        serviceOrderVO.setVoipServiceOrders(VoipServiceOrders);
}
/*
 * @see TestCase#tearDown()
 */
protected void tearDown() throws Exception
{
	super.tearDown();
}

public void testProcessData() throws ParserSvcException
{
	assertNull(parseRequest.getFcifDataString()); // since it has to be null before we put anything in it
    parseRequest.setServiceOrderVo(serviceOrderVO);
    parseRequest.setDataFormat("SOAC_FCIF");
    parseRequest.setSon((String) serviceOrderVO.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM));
    parseRequest.setOperationType(ParserConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF);
    parseRequest.setRegion(ParserConstants.SBCSOUTHEAST);  
//	parseRequest.setRegion(ParserConstants.SBCMIDWEST);   
    aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
	aParserSvc.processData(parseRequest);
	System.out.println("**************Generated String******************\n");
	System.out.println(parseRequest.getFcifDataString());
	System.out.println("**************Generated String******************\n");
//    assertEquals(testData, parseRequest.getFcifDataString());
}
/*
public void testProcessData1() throws ParserSvcException{
	assertNull(parseRequest.getFcifDataString()); // since it has to be null before we put anything in it
    
	serviceOrderVO.remove(SoacServiceOrderConstants.REMARKS_DISCONNECT_TN)    ;
    parseRequest.setServiceOrderVo(serviceOrderVO);

    parseRequest.setOperationType(ParserConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF);        
    parseRequest.setDataFormat("SOAC_FCIF");
    parseRequest.setSon((String) serviceOrderVO.get(SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM));
    parseRequest.setOperationType(ParserConstants.SOAC_VALUEOBJECT_TO_SOAC_FCIF);
    parseRequest.setRegion(ParserConstants.SBCWEST);    
    aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
    
    try{
		aParserSvc.processData(parseRequest);
		fail("shouldn't pass REMARKS validation check. ParserSvcException exception was not thrown");
    }catch (ParserSvcException e){
    	System.out.println("=========================== testProcessData1 RESULT ==================="); 
    	System.out.println(e.getMessage());
    	e.printStackTrace();
    }
//    assertEquals(testData, parseRequest.getFcifDataString());


}
*/
}