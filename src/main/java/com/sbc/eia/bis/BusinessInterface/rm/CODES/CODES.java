//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of AT&T Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) AT&T Service, Inc., 2007.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 01/04/2007  Changchuan Yin  Created based on APTOS.java from RM 18.0, for the CODES Oracle 10G Project (RM 19.0).
//# 02/21/2001  Changchuan Yin, Removed some system out lines.
//# 03/16/2007  CY4727 updated to fix Defect ID : 62096
//# 05/07/2007  CY4727 updated to fix DR 177047

package com.sbc.eia.bis.BusinessInterface.rm.CODES;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ArrayListHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProviderType;
import com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForResourceIntf;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.codes.CodesRCAccessService;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.GetClliCICAvailRequest;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.impl.*;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.GetClliCICAvailRespTlist;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.ResponseMessage;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.Resultslist;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.*;
import com.sbc.eia.bis.embus.service.codes.access.RmBisRequestsEncoderDecoder;
import com.sbc.eia.bis.embus.service.codes.helpers.ResponseHelper;
import com.sbc.eia.bis.embus.service.codes.helpers.SendRequestToCodes;
import com.sbc.eia.bis.embus.service.netprovision.helpers.NullResourcesForServiceException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;
import com.sbc.eia.idl.rm_types.ServiceProvidersForServiceType;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.sm_types.CarrierAuthorizationValue;
import com.sbc.eia.idl.sm_types.OperatingCompanyNumberSeqOpt;
import com.sbc.eia.idl.sm_types.ServiceProvider;
import com.sbc.eia.idl.sm_types.ServiceProviderProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;
import com.sun.xml.bind.JAXBObject;

/**
 * HOSTTemplate is the class which defines a template for a host object.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 */
public class CODES extends Host 
	implements ServiceProvidersForResourceIntf
{
	public static final String HostClass_CODES = CODES.class.getName() ;
	
	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ServiceProvidersForResourceInterfaceName /*, AnotherInterface */ 
		};
	// This belongs here - the list of immediate children (classes that derive from this class)
	private static final String hostList[] = null ;
	
	// Some APTOS specific defines
	final static String APTOS_PIC_CODE  = "1" ;
	final static String APTOS_LPIC_CODE = "2" ;
	
	// lookup for combining type of service codes
	private static Properties tosProps    = null;

	private static final int CXR_CD_IDX				= 0;	// PIC (or LPIC)
	private static final int CIC_NM_IDX				= 1;	// ACNADES
	private static final int ACNA_CD_IDX		    = 2;	// ACNA
	private static final int LATA_AUTHIND_IDX		= 3;	// CAUTH
	private static final int BUS_RES_COIN_IND_IDX	= 4;	// TOS

	private static final String BUSINESS	= "B";
	private static final String RESIDENCE	= "R";
	private static final String COIN		= "C";
	
	//CODESRC
	private CodesRCAccessService	aService  = null;
	final static String PIC_CODE  = "1" ;
	final static String LPIC_CODE = "2" ;
	//private Hashtable  properties     = null;
	private String 	aCODESRCRuleFile    = null;


/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public CODES(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
	
	aCODESRCRuleFile =
			(String) aProperties.get(
	         SendRequestToCodes.CODES_EXCEPTION_RULE_FILE_TAG
	);
							
}
/**
 * The getCacheEntries method is called by the Host Factory to get entries for preloading into the Host cache.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "CODES::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] { 
		new Selector(new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
					 ServiceProvidersForResourceInterfaceName, HostClass_CODES)
	};
}
/**
 * The getHostList method is called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "CODES::getHostList()");

	// Return the list of immediate children (classes that derive from this class)
	return hostList; /* null if no children */
}
/**
 * The getInterfaceList method is called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "CODES::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * The getSupportedCompanies method is called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws InvalidCompanyException, InvalidStateException, NullDataException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "CODES::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] { new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null) };
}

private int listIdx(String[][] responseList, int len, String cxr_cd, String lata_authind) {
		for (int i = 0; i < len; i++) {
			if (responseList[i][CXR_CD_IDX].equals(cxr_cd) && responseList[i][LATA_AUTHIND_IDX].equals(lata_authind)) {
				return i;
			}
		}
		return -1;
	}


 private String getNewAcnades(String cxr_cd, String acna, String cauth, String acnades, String[][] list, int size) {
	// return the universal ACNA determined by the highest priority type of service
	boolean found = false;
	String newAcnades = acnades;
	for (int i = 0; i < size; i++) {
		String code = list[i][CXR_CD_IDX];
		String acna_cd = list[i][ACNA_CD_IDX];
		String lata_authind = list[i][LATA_AUTHIND_IDX];
		// Priority order is 1, 2, 4
		if (code.equals(cxr_cd) && acna_cd.equals(acna) && lata_authind.equals(cauth)) {
			if (list[i][BUS_RES_COIN_IND_IDX].indexOf("1") != -1) {
				return list[i][CIC_NM_IDX];
			}
			if (!found && list[i][BUS_RES_COIN_IND_IDX].indexOf("2") != -1) {
				found = true;
				newAcnades = list[i][CIC_NM_IDX];
			}
		}
	}
	return newAcnades;
}


 private GetClliCICAvailRequestImpl buildRequest(
			BisContext aContext,
			String aCLLI,
			ObjectKey aServiceTypeHandle)
		   throws
			   InvalidData,
			   AccessDenied,
			   BusinessViolation,
			   SystemFailure,
			   NotImplemented,
			   ObjectNotFound,
			   DataNotFound 
 {
			
			  System.out.println("Start to build CODERC Request");
			  utility.log( LogEventId.DEBUG_LEVEL_1,
					">" + "Start RetrieveServiceProvidersForResource-CODESRC::buildRequest()");
			  GetClliCICAvailRequestImpl request = new GetClliCICAvailRequestImpl();
	    
			  //Translate the ServiceTypeHandle to CODESRC's CIC_FLAG
			  String sCICType = null ; //PIC or LPIC
			  BigInteger iCICFlag = null; //1 or 2
		   
			  try
			  {
			   sCICType  = aServiceTypeHandle.aValue ;
			  }
			  catch (org.omg.CORBA.BAD_OPERATION e){} // skip, just in case, but shouldn't happen
			  catch (NullPointerException e){}        // skip, just in case, but shouldn't happen		

              utility.log( LogEventId.INFO_LEVEL_1, "Looking up ServiceTypeHandle = <" + sCICType + ">" ) ;

			  String sCICFlag = null ;
			  String OKeyKind = null ;
		   
			  if ( sCICType .equalsIgnoreCase( ServiceTypeHandleObjectKey.PIC ) )
			  {
				sCICFlag = PIC_CODE ;// which is equal to 1.
				OKeyKind = ServiceTypeHandleObjectKey.PIC ;
			  }
			  else if ( sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.LPIC ) )
			  {
				sCICFlag = LPIC_CODE ;//which is equal to 2.
			 
				OKeyKind = ServiceTypeHandleObjectKey.LPIC ;
			  }
			  else if ( sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.LOCAL_VOICE ) )
			  {
				;//do nothing, fall through. should not remove.
			  }
			  else
			  {
				utility.log( LogEventId.INFO_LEVEL_1, "Skipping unrecognized ServiceTypeHandle = <" +
				  sCICType + ">" ) ;
			 }
			 iCICFlag = new BigInteger(sCICFlag);// 1 or 2.
	 
		     //Form a request object 
		     //request.setSchemaVersion(CODESRCAccessHelper.CODES_RC_VERSION);
		     request.setCLLI(aCLLI);
		     request.setCICFLAG(iCICFlag);
     
		     utility.log(
				LogEventId.DEBUG_LEVEL_1,
				"<" + "End RetrieveServiceProvidersForResourceRequest:buildRequest()");
			
    
		    return request;	
    		
   }
   /* (non-Javadoc)
	* @see com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders.ServiceProvidersForResourceIntf#retrievePicByCLLI(com.sbc.eia.idl.bis_types.BisContext, java.lang.String, com.sbc.eia.idl.types.ObjectKey[])
	*/
   public RetrieveServiceProvidersForResourceReturn retrievePicByCLLI(BisContext aContext, String aCLLI, ObjectKey[] aServiceTypeHandles) throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
   {
	
	 String myMethodName = "CODES::retrievePicByCLLI()" ;
	 utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName) ;
	  
	 // company code
	 ObjectPropertyManager opm =	new ObjectPropertyManager( aContext.aProperties ) ;
	 String companyCd = opm.getValue( BisContextProperty.CUSTOMERNAME ) ;
	 if ( companyCd == null )
	 {
		utility.throwException(	ExceptionCode.ERR_RM_MISSING_CUSTOMERNAME,
								TranBase.formatInvalidData( BisContext.class, "aContext.aProperties.CUSTOMERNAME" ),
								(String)getProperties().get( "BIS_NAME" ),
								Severity.UnRecoverable ) ;
	 }
		  
	 //get properties file for type of service code combinations
	 if (tosProps == null) {
		try {
			tosProps = new Properties();
			PropertiesFileLoader.read(tosProps, "TypeOfService.properties", utility);

		} catch (Exception e) {
			utility.throwException(
				ExceptionCode.ERR_RM_APTOSSERVER,
				"CODESRC failure: " + e.getMessage(),
				(String) getProperties().get("BIS_NAME"),
				Severity.UnRecoverable);
		}
	  }  
	
	  String sCICType = null ;
	  String cicFlagCode = null ;
	  String OKeyKind = null;
		  
      ArrayList ServiceProvidersForServiceTypeList = new ArrayList() ;
	 
	  //Start of the big for picInx loop  
	  for ( int picIdx = 0; picIdx < aServiceTypeHandles.length; picIdx++ )
	  {
		     GetClliCICAvailRequestImpl aRequest = new GetClliCICAvailRequestImpl();
		
			 try
			 {
				sCICType = aServiceTypeHandles[picIdx].aValue ;//PIC, LPIC, or LOCAL_VOICE
			 }
			 catch (org.omg.CORBA.BAD_OPERATION e){} // skip, just in case, but shouldn't happen
			 catch (NullPointerException e){} // skip, just in case, but shouldn't happen		


			 utility.log( LogEventId.INFO_LEVEL_1, "The Service Type Handle = <" + sCICType + ">" ) ;

			 if ( sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.PIC ))
			 {
			  	
			  OKeyKind = ServiceTypeHandleObjectKey.PIC ;
			  aRequest = buildRequest(aContext, aCLLI, aServiceTypeHandles[picIdx]);
              utility.log( LogEventId.INFO_LEVEL_1, "The CODESRC request was built for CLLI:" + aCLLI);
				 
			 }
			 else if (sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.LPIC ))
			 {
				OKeyKind = ServiceTypeHandleObjectKey.LPIC ;
				aRequest = buildRequest(aContext, aCLLI, aServiceTypeHandles[picIdx]);
				utility.log( LogEventId.INFO_LEVEL_1, "The CODESRC request was built for CLLI:" + aCLLI);
			 }
			 //DR 177047, 05/07/2007
             else if (sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.LOCAL_VOICE))
			 {
				OKeyKind = ServiceTypeHandleObjectKey.LOCAL_VOICE ;
				utility.log( LogEventId.INFO_LEVEL_1, "The CIC type = LOCAL_VOICE; the CLLI value = " + aCLLI);
										
			 }
		     else
			 {
				utility.throwException(
											ExceptionCode.ERR_RM_INVALID_SERVICE_TYPE,
											"Invalid Service Type",
										    getProperties().get("BIS_NAME").toString(),
											Severity.Recoverable);
											
											
			    utility.log( LogEventId.INFO_LEVEL_1, "Invalid service type: Service type should be PIC, LPIC or LOCAL_VOICE. The client inputted: <" +
			    sCICType + ">" ) ;
			 }
		  
		 //If it is LOCAL_VOICE, go to here, ocq inquiry. DR 177047, 05/07/2007
		 if (sCICType.equalsIgnoreCase( ServiceTypeHandleObjectKey.LOCAL_VOICE))
		 {
				utility.log( LogEventId.INFO_LEVEL_1, "The CIC type is LOCAL_VOICE:" + aCLLI);
			
			    ServiceProviderType aServiceProviderType =
										   new ServiceProviderType();
			
				ServiceProvidersForServiceTypeList.add(
										   aServiceProviderType.buildServiceProvidersType(
											   getProperties(),
											   utility,
											   aCLLI,
											   sCICType,
											   aContext));
			
	   }
       // Begin the "else" section for PIC/LPIC, request goes to CODESRC backend 
	   else
	   {  
		
		  String msgRequest = encodeRequest(aRequest);
		  utility.log( LogEventId.INFO_LEVEL_1, "Request Message = " + msgRequest) ;
			
		
		  // process request
		  if (aService == null) 
	      {
	      try 
	      {
		   aService = new CodesRCAccessService(getProperties(), utility);
	      } 
	      catch (ServiceException e) 
	      {
			 ExceptionBuilder.parseException(
				 aContext,
		         aCODESRCRuleFile,
				 null,
				 null,
				 e.getMessage(),
				 true,
				 1,
				 null,
				 e,
				 utility,
				 null, // origin use file
				 null, // severity use file
				 null);
	       }
	      } 

			
	      utility.log( LogEventId.INFO_LEVEL_1, "The CODESRC Service is established." ) ;
	      
		  String serviceName =  aService.getServiceName() + "-" + aService.getServiceVersion();
          
		  utility.log(LogEventId.REMOTE_CALL,aService.getServiceName(), serviceName, serviceName, "getClliCICAvailReq");
			
          Object aResponse = null;
	      aResponse = SendRequestToCodes.sendRequest(
							 utility,
							 aContext,
							 aCODESRCRuleFile,
							 aRequest,
							 aService
							 );
		  
		  utility.log(LogEventId.REMOTE_RETURN,aService.getServiceName(), serviceName, serviceName, "getClliCICAvailReq");
		
		  utility.log( LogEventId.INFO_LEVEL_1, "The CODESRC response message received." ) ;

          //Check the status information from response message
	      ResponseHelper aResponseHelper = new ResponseHelper(aCODESRCRuleFile, aResponse, getProperties().get("BIS_NAME").toString());

          // Check the message status first
		  try 
		  {
			   aResponseHelper.checkErrorMsg(aContext, utility);
          
			   utility.log( LogEventId.INFO_LEVEL_1, "The CODES response message status was validated" );
		  } 
		  catch (NullResourcesForServiceException e) 
		  {
						  utility.log(LogEventId.DEBUG_LEVEL_2, e.getMessage());
						  utility.throwException(
							ExceptionCode.ERR_RM_SERVICE_ITEM_NOT_FOUND,
							"Null resource found",
			               getProperties().get("BIS_NAME").toString(),
							Severity.Recoverable);
		  }
          
		 
           //If no exception throw, parse the response messsage and returned an object to client.
		   ArrayList ServiceProviderList = new ArrayList();
	    
		   Object aReturnResponse = aResponseHelper.getResponse();
	    
		   ServiceProviderList = parseCLLIResponse(aReturnResponse);
	    
		   utility.log(
				  LogEventId.INFO_LEVEL_1,
				  "Randomize " + ServiceProviderList.size() + " elements in the ServiceProviderList...");
		   ArrayListHelper.randomize(ServiceProviderList);
		
		   utility.log(LogEventId.INFO_LEVEL_1, "Build the ServiceProvidersForServiceTypeList...");
		
		   //Array combined the response for each type, PIC, LPIC
		   ServiceProvidersForServiceTypeList.add(
				  new ServiceProvidersForServiceType(
					  new ObjectKey(sCICType, OKeyKind),
					  (ServiceProvider[]) ServiceProviderList.toArray(new ServiceProvider[ServiceProviderList.size()])));
	  
	  
	   }
	  //End "else" section for PIC/LPIC, request goes to CODESRC backend 
	  
	 }//End of the big for picInx loop  
      
      
      
	 //If we get any data, then process them and return to client call
	 if (ServiceProvidersForServiceTypeList.size() < 1)
			utility.throwException(ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND,"Resource not found.",
			   (String) getProperties().get("BIS_NAME"),
			   Severity.UnRecoverable);
	  
	  utility.log(LogEventId.INFO_LEVEL_1,"Build the RetrieveServiceProvidersForResourceReturn with "
			   + ServiceProvidersForServiceTypeList.size()
			   + " ServiceProvidersForServiceType elements...");
	  
	   utility.log(LogEventId.DEBUG_LEVEL_1, "<" + "Completed RetrieveServiceProvidersForResource:retrievePicByCLLI()");
			
	 
	   return new RetrieveServiceProvidersForResourceReturn(aContext,
	  (ServiceProvidersForServiceType[]) ServiceProvidersForServiceTypeList.toArray(
		   new ServiceProvidersForServiceType[ServiceProvidersForServiceTypeList.size()]));
  
	}
	  
  /**
 * @param aRequest
 * @return
 */
  
private String encodeRequest(GetClliCICAvailRequest aRequest)// throws EncoderException, com.sbc.eia.bis.embus.service.access.ServiceException
{
    String xmlMsg = "";
    Properties options = new Properties();
    options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");

    String className = GetClliCICAvailRequest.class.getPackage().getName();
	
	RmBisRequestsEncoderDecoder encoder = new RmBisRequestsEncoderDecoder(
				 GetClliCICAvailRequest.class.getPackage().getName(),
				  options);
	
    Object[] i_objectArray = new Object[] {aRequest};
	 		  
    try {
		xmlMsg = encoder.encode(i_objectArray);
		//System.out.println("Encode Request C");	 
	} catch (EncoderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (com.sbc.eia.bis.embus.service.access.ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
				  
    return xmlMsg;
}

 /**
 * @param response
 * @return
 */
 private ArrayList parseCLLIResponse(Object response)
  {
  	           ArrayList serviceProviderList = new ArrayList();
			
			   ResponseMessage aResponse = (ResponseMessage) response;
			   Resultslist resultList = aResponse.getResults();
	           GetClliCICAvailRespTlist cicAvailRespList = resultList.getGetClliCICAvailResponse();//Each CIC response data
			   
			   int responseIdx = 0;
			   
               //Each record may have one CLLI value but multiple CIC Data and Exon Data
	           String clliInfo = cicAvailRespList.getCLLI();
				  
	           List cicDataAll = cicAvailRespList.getCicData();
	           int numOfCIC = cicDataAll.size();
	           utility.log(LogEventId.DEBUG_LEVEL_2, "Number of CIC Returned By CODES: "
					   + String.valueOf(numOfCIC));
	   
	           CicDataListImpl cicData = null;
	           
			   String[][] responseList = new String[numOfCIC][5];
			   for (int i = 0; i < numOfCIC; i++)
			   {  
				  cicData = (CicDataListImpl) cicDataAll.get(i);
				  
				  String cic = cicData.getPICCD();
				  String cic_nm = cicData.getCICNM();
				  String acna_cd = cicData.getACNACD();
				  String lata_authind = cicData.getLATAAUTHIND();
				  String bus_res_coin_ind = cicData.getBUSRESCOININD();
				
				  utility.log(
					   LogEventId.DEBUG_LEVEL_2,
						   "Receive: "
						   + cic
						   + "|"
						   + cic_nm
						   + "|"
						   + acna_cd
						   + "|"
						   + lata_authind
						   + "|"
						   + bus_res_coin_ind
						   + "|");
			
			   	   String CXR_CD = cic;
				  
				 //if(cic.length() == 5 && (cic.charAt(0)=='1' ||cic.charAt(0)=='2' ) )	
				 if(cic.length() == 5 )	
				   {
				   	 CXR_CD = cic.substring(1); //strip 1st byte, sample cic 16864 can be 6864
				   }
				  
				  
				   if (lata_authind != null && lata_authind.toUpperCase().equals("Y")) {//change CAUTH
					   lata_authind = CarrierAuthorizationValue.OFFER;
				   } 
				   else {
					   lata_authind = CarrierAuthorizationValue.DO_NOT_OFFER;
				   }
				   int position = listIdx(responseList, responseIdx, CXR_CD, lata_authind);
				   if (position == -1) {
					   bus_res_coin_ind = tosProps.getProperty(bus_res_coin_ind);
					   utility.log(
						   LogEventId.DEBUG_LEVEL_2,
						      " Add:  "
							   + CXR_CD
							   + "|"
							   + cic_nm
							   + "|"
							   + acna_cd
							   + "|"
							   + lata_authind
							   + "|"
							   + bus_res_coin_ind
							   + "|");
					  
					   responseList[responseIdx][CXR_CD_IDX] = CXR_CD;//outof index 
					   responseList[responseIdx][CIC_NM_IDX] = cic_nm;
					   responseList[responseIdx][ACNA_CD_IDX] = acna_cd;
					   responseList[responseIdx][LATA_AUTHIND_IDX] = lata_authind;
					   responseList[responseIdx][BUS_RES_COIN_IND_IDX] = bus_res_coin_ind;
					   responseIdx++;
				   } 
				   else {
					   String newTos = tosProps.getProperty(responseList[position][BUS_RES_COIN_IND_IDX] + bus_res_coin_ind);
					   String newAcnades = getNewAcnades(responseList[position][CXR_CD_IDX],
												   responseList[position][ACNA_CD_IDX],
												   responseList[position][LATA_AUTHIND_IDX],
												   responseList[position][CIC_NM_IDX],
												   responseList,
												   responseIdx);
					   utility.log(
						   LogEventId.DEBUG_LEVEL_2,
						   " Change: "
							   + responseList[position][CXR_CD_IDX]
							   + "|"
							   + responseList[position][CIC_NM_IDX]
							   + "|"
							   + responseList[position][ACNA_CD_IDX]
							   + "|"
							   + responseList[position][LATA_AUTHIND_IDX]
							   + "|"
							   + responseList[position][BUS_RES_COIN_IND_IDX]
							   + "| To:  "
							   + responseList[position][CXR_CD_IDX]
							   + "|"
							   + newAcnades
							   + "|"
							   + responseList[position][ACNA_CD_IDX]
							   + "|"
							   + responseList[position][LATA_AUTHIND_IDX]
							   + "|"
							   + newTos
							   + "|");
					   responseList[position][BUS_RES_COIN_IND_IDX] = newTos;
					   responseList[position][CIC_NM_IDX] = newAcnades;
				   }
			   }
			// End of FOR loop GetClliCICAvailResp.OutClli.cicData.length
			
	        //Start the for loop responseIdx
			for (int i = 0; i < responseIdx; i++) {
				   String CXR_CD = responseList[i][CXR_CD_IDX];
				   String cic_nm = responseList[i][CIC_NM_IDX];
				   String acna_cd = responseList[i][ACNA_CD_IDX];
				   String lata_authind = responseList[i][LATA_AUTHIND_IDX];
				   String bus_res_coin_ind = responseList[i][BUS_RES_COIN_IND_IDX];

				   utility.log(
					   LogEventId.DEBUG_LEVEL_2,
						   "Sending: "
						   + CXR_CD
						   + "|"
						   + cic_nm
						   + "|"
						   + acna_cd
						   + "|"
						   + lata_authind
						   + "|"
						   + bus_res_coin_ind
						   + "|");
				   ServiceProvider aSP = new ServiceProvider();
				   aSP.aProperties =
					  new ObjectProperty[] {
						 
						   new ObjectProperty(ServiceProviderProperty.CODE, CXR_CD),
						 
						   new ObjectProperty(
							   ServiceProviderProperty.NAME,
							   cic_nm),
						   new ObjectProperty(
							   ServiceProviderProperty.NAME_ABBREVIATION,
							   acna_cd),
						   new ObjectProperty(
							   ServiceProviderProperty.CARRIER_AUTHORIZATION,
							   lata_authind),
						   new ObjectProperty(
							   ServiceProviderProperty.TYPE_OF_SERVICE,
							   bus_res_coin_ind)
					};
					
				   // More additional data
				   aSP.aServiceProviderHandle =
					   new ObjectKey(new String(),new String());
			
				   aSP.aOperatingCompanyNumbers =
					   (OperatingCompanyNumberSeqOpt)IDLUtil.toOpt(OperatingCompanyNumberSeqOpt.class, null);
				   
				   // Add to the list
				   serviceProviderList.add(aSP);
			   } 
			 
		  return serviceProviderList;
  }


}
