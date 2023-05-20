//$Id: ValidateFacility.java,v 1.2 2007/09/17 16:05:34 rd2842 Exp $
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
//# 09/12/2007  Shyamali Banerjee     Creation.
//# 09/17/2007  Rene Duka             Modified for LS6.

package com.sbc.eia.bis.facades.testing.rm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.facades.rm.ejb.Rm;
import com.sbc.eia.bis.facades.testing.TestCaseException;
import com.sbc.eia.bis.facades.testing.TestClient;
import com.sbc.eia.bis.facades.testing.objHelpers;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.ValidateFacilityReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityResponseMsg;
import com.sbc.eia.idl.rm.bishelpers.ValidateFacilityReturnBisHelper;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ShortOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBWriter;

public class ValidateFacility 
    extends TestRMCaseBase {
	
    private ValidateFacilityReturn aResult = null;
    private Location aServiceLocation = null;
    private StringOpt aRelatedCircuitID = null;
    private StringOpt aWorkingTelephoneNumber = null;
    private ShortOpt aMaxPairsToAnalyze = null;
	private StringOpt aSOACServiceOrderNumber = null;
	private StringOpt aSOACServiceOrderCorrectionSuffix = null;
	private EiaDateOpt aUverseOrderDueDate = null;
    private ObjectPropertySeqOpt aProperties = null;
    private Hashtable aProps = null;
    private Logger aLogger = null;
    
    /**
     * constructor: CreateFacilityAssignment
     */
    public ValidateFacility() {
        super();

        // set method name
        setMyMethodName("ValidateFacility");
    }
    
    /**
     * method: displayResult
     */
    protected void displayResult() {
        //XML test data        
        if (isXMLTestData) {
            _validateFacilityResponse res =  new _validateFacilityResponse();
            String responseXML = null;
            
            if (aResult != null) {       
                res.aValidateFacilityReturn(aResult);
                responseXML = convertMMarshalObjectToXML(new _validateFacilityResponseMsg(res));
            }
            else  {
                if (responseError != null) {
                    if(responseError instanceof SystemFailure)
                        res.aSystemFailure( (SystemFailure)responseError );
                    if(responseError instanceof InvalidData)
                        res.aInvalidData( (InvalidData)responseError );
                    if(responseError instanceof AccessDenied)
                        res.aAccessDenied( (AccessDenied)responseError );
                    if(responseError instanceof BusinessViolation)
                        res.aBusinessViolation( (BusinessViolation)responseError );
                    if(responseError instanceof NotImplemented)
                        res.aNotImplemented( (NotImplemented)responseError );
                    if(responseError instanceof ObjectNotFound)
                        res.aObjectNotFound( (ObjectNotFound)responseError );
                    if(responseError instanceof DataNotFound)
                        res.aDataNotFound( (DataNotFound)responseError );
                  
                    responseXML = convertMMarshalObjectToXML(new _validateFacilityResponseMsg(res));
                }
            }
            
            if(responseXML != null)
                TestClient.log.printLog( "OUTPUT DATA: \n" + responseXML );
        }
        else if (aResult != null) {
            TestClient.log.printLog("OUTPUT DATA: " + (new ValidateFacilityReturnBisHelper(aResult)).toString());
        }
    }

    /**
     * method: displayProxyResult
     */
    protected void displayProxyResult() {
	}

    /**
     * method: execute()
     */
    protected void execute() 
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        displayInput();

        com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacility aTrans =
            new com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacility(aProps);

        aResult = aTrans.execute((aContext != null) ? aContext : TestClient.myBisContext,               
                                 this.aServiceLocation,             
                                 this.aRelatedCircuitID,
                                 this.aWorkingTelephoneNumber,
                                 this.aMaxPairsToAnalyze,
                                 this.aSOACServiceOrderNumber,
                                 this.aSOACServiceOrderCorrectionSuffix,
                                 this.aUverseOrderDueDate,
                                 this.aProperties,     
                                 aLogger);
    }

    /**
     * method: execute(EJBObject)
     */
    protected void execute(javax.ejb.EJBObject remote) 
	    throws 
            InvalidData,
            AccessDenied, 
            BusinessViolation,
            SystemFailure,
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound, 
            RemoteException {

        displayInput();
		
        aResult = ((Rm) remote).validateFacility((aContext != null) ? aContext : TestClient.myBisContext,                
                                                 this.aServiceLocation,
                                                 this.aRelatedCircuitID,
                                                 this.aWorkingTelephoneNumber,
                                                 this.aMaxPairsToAnalyze,
                                                 this.aSOACServiceOrderNumber,
                                                 this.aSOACServiceOrderCorrectionSuffix,
                                                 this.aUverseOrderDueDate,
                                                 this.aProperties);
    }	

    /**
     * method: execute(Hashtable)
     */
	protected void execute(Hashtable props) 
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {
	}
    
    /**
     * method: init(String)
     */
	protected void init(String paramList) {

        // parse the argument from the test case
        StringTokenizer st = new StringTokenizer(paramList, TestClient.DEFAULT_DELIMITER);
        try {
            while (st.hasMoreElements()) {
                String tag = TestClient.nextToken(st) ;
                System.out.println("Next Tag => " + tag);
                if (tag.equals("XML")) {
                    isXMLTestData = true;
                    String xmlFileName = TestClient.nextToken( st ) ;
                    TestClient.log.printLog("XML Test Data File :" + xmlFileName);
                    buildIDLObjectsFromXML( xmlFileName );
                    return;
                }
                if (tag.equals("BisContext")) {
                    aContext = objHelpers.getBisContext(st);
                }
                if (tag.equals("Location")) {
                    aServiceLocation = objHelpers.getLocation(st);
                }        
                if (tag.equals("RelatedCircuitID")){
                    aRelatedCircuitID = objHelpers.getStringOpt(st);
                }
                if (tag.equals("WorkingTelephoneNumber")){
                    aWorkingTelephoneNumber = objHelpers.getStringOpt(st);
                }
                if (tag.equals("MaxPairsToAnalyze")){
                    aMaxPairsToAnalyze = objHelpers.getShortOpt(st);
                }
                if (tag.equals("SOACServiceOrderNumber")){
                    aSOACServiceOrderNumber = objHelpers.getStringOpt(st);
                }
                if (tag.equals("SOACServiceOrderCorrectionSuffix")){
                    aSOACServiceOrderCorrectionSuffix = objHelpers.getStringOpt(st);
                }
                if (tag.equals("UverseOrderDueDate")) {
                    aUverseOrderDueDate = objHelpers.getDateOpt(st);
                }
                if (tag.equals("Properties")) {
                    aProperties = objHelpers.getObjectPropertySeqOpt(st);
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Notify user of error
            System.out.println("Verify test data/format, failure parsing test case string");
        }
	}

    /**
     * method: buildIDLObjectsFromXML(String)
     */
	private void buildIDLObjectsFromXML(String xmlFileName) 
        throws Exception {
	    
        //Read XML test data file
	    requestXML = readFile(xmlFileName, true);
      
	    //Convert XML to IDL object
	    MMarshalObject aRequest = convertXMLToMMarshalObject(requestXML);
      
	    //build request IDL objects from MMarshalObject
	    _validateFacilityRequestMsg aRequestMsg = (_validateFacilityRequestMsg)aRequest;

	    aContext = aRequestMsg.value.aContext;
        aServiceLocation = aRequestMsg.value.aServiceLocation;
        aRelatedCircuitID = aRequestMsg.value.aRelatedCircuitID;
		aWorkingTelephoneNumber = aRequestMsg.value.aWorkingTelephoneNumber;
		aMaxPairsToAnalyze = aRequestMsg.value.aMaxPairsToAnalyze;
		aSOACServiceOrderNumber = aRequestMsg.value.aSOACServiceOrderNumber;
		aSOACServiceOrderCorrectionSuffix = aRequestMsg.value.aSOACServiceOrderCorrectionSuffix;
		aUverseOrderDueDate = aRequestMsg.value.aUverseOrderDueDate;
		aProperties = aRequestMsg.value.aProperties;
	}

    
    /**
     * method: init(String, String)
     */
	protected void init(java.lang.String paramList, java.lang.String propertiesFile) {

	    File file;
	    FileInputStream fis = null;

	    try {
	        file = new File(propertiesFile.trim());
	        fis = new FileInputStream(file);
	    }
	    catch (Exception fe) {
	        System.out.println("Properties File Not Found: " + fe.getMessage());
	    }

	    //get properties file
	    Properties p = new Properties();

	    try {
	        p.load(fis);
	    }
	    catch (IOException ie) {
	        System.out.println("IOException reading properties file.");
	    }

	    // Set up properties for specific Bis
	    this.aProps = (Hashtable) p;
	    init(paramList);
	}

    /**
     * method: init(String, Hashtable)
     */
	protected void init(java.lang.String paramList, Hashtable props) {
	}

    /**
     * method: initMessage(String)
     */
	protected void initMessage(java.lang.String paramList, Hashtable props) {
	}	
	

    /**
     * method: displayInput()
     */
	private void displayInput() {
	    if (isXMLTestData) {
	        TestClient.log.printLog( "INPUT DATA: \n" +  requestXML );
	        return;
	    }

	    TestClient.log.printLog(
            "INPUT DATA:aContext<"
	            + (new BisContextBisHelper((aContext != null) ? aContext : TestClient.myBisContext)).toString()
	            + ">");

        try {
            TestClient.log.printLog(
                "INPUT DATA:aServiceLocation<" 
                    + (new LocationBisHelper(aServiceLocation)).toString() 
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aServiceLocation<null>");
        }

        try {
            TestClient.log.printLog(
                "INPUT DATA:aRelatedCircuitID<" 
                    + (new StringOptBisHelper(aRelatedCircuitID)).toString() 
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aRelatedCircuitID<null>");
        }

        try {
            TestClient.log.printLog(
                "INPUT DATA:aWorkingTelephoneNumber<" 
                    + (new StringOptBisHelper(aWorkingTelephoneNumber)).toString() 
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aWorkingTelephoneNumber<null>");
        }

        try {
            TestClient.log.printLog(
                "INPUT DATA:aMaxPairsToAnalyze<" 
                    + (new ShortOptBisHelper(aMaxPairsToAnalyze)).toString()                    
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aMaxPairsToAnalyze<null>");
        }
        
        try {
            TestClient.log.printLog(
                "INPUT DATA:aSOACServiceOrderNumber<"
                    + (new StringOptBisHelper(aSOACServiceOrderNumber)).toString()                    
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderNumber<null>");
        }

        try {
            TestClient.log.printLog(
                "INPUT DATA:aSOACServiceOrderCorrectionSuffix<" 
                    + (new StringOptBisHelper(aSOACServiceOrderCorrectionSuffix)).toString()
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aSOACServiceOrderCorrectionSuffix<null>");
        }
      
        try {
            TestClient.log.printLog(
                "INPUT DATA:aUverseOrderDueDate<"
                    + (new EiaDateOptBisHelper(aUverseOrderDueDate)).toString()
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aUverseOrderDueDate<null>");
        }

        try {
            TestClient.log.printLog(
                "INPUT DATA:aProperties<" 
                    + (new ObjectPropertySeqOptBisHelper(aProperties)).toString() 
                    + ">");
        }
        catch (Exception e) {
            TestClient.log.printLog("INPUT DATA:aProperties<null>");
        }
	}
	
    /**
     * method: initSoapMessage(String, Properties)
     */
	protected void initSoapMessage(String paramList, Properties props) {
	}

    /**
     * method: initSoapHttpMessage()
     */
    protected void initSoapHttpMessage( String ParamList, Properties props )  {
        TestClient.log.printLog ("SOAP/HTTP not implemented" );
    }
}