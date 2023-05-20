//###############################################################################
//#
//#   Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//# 05/09/2005  kk8467    	Created and added business interface logic.
//# 05/12/2005  kk8467    	Added business interface logic.
//# 05/16/2005  kk8467    	Modified publishActivation().
//# 05/19/2005  kk8467    	Updated for remote logging.
//# 06/20/2005  kk8467    	Added soap envelope to XML output.
//# 06/22/2005  kk8467    	Added soap header to XML output.
//# 08/02/2005  kk8467    	Moved removeVAXB(), appendSoapEnvelope() to SoapParserHelper.java.
//# 08/15/2005  kk8467		Modified for DR 142178 to display correct Exception Details.
//# 09/20/2005  kk8467		Modified idlToXML() to send PublishRGActivationResponseBISMsg to OMS.
//# 09/20/2005  kk8467		Removed system print statement.
//# 10/03/2007  ra0331		Modified idlToXML() to send PublishRGActivationResponseBISMsg to either OMS or BBNMS based on the DSLAM ID.

package com.sbc.eia.bis.BusinessInterface.rm.PublishRGActivation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.ClientService;
import com.sbc.eia.bis.facades.rm.transactions.PublishRGActivation.PublishRGActivationConstants;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.DslamMigrationRow;
import com.sbc.eia.bis.rm.database.DslamMigrationTable;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishRGActivationResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.Time;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

/**
* @author kk8467
*/
public class PublishRGActivation {

	private Utility aUtility = null;
	private Hashtable properties = null;
	private ClientService aService = null;
	private Date today = new Date();
	private Date start_date = null;
	private Date end_date = null;
	private String tableDSLAM = "";
	private String inputDSLAM = null;
	
	/**
	 * Method PublishRGActivation.
	 * @param utility
	 * @param properties
	 */
	public PublishRGActivation(Utility utility, Hashtable properties) {
		this.properties = properties;
		aUtility = utility;
	}

	
	/**
	 * Method publishActivation.
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDSLAM
	 * @param aRG
	 * @param aActivationTime
	 * @param aOrderAction
	 * @param aProperties
	 * @return PublishRGActivationReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public PublishRGActivationReturn publishActivation(
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">PublishRGActivation::publishActivation()");

		// build return object
		PublishRGActivationReturn publishRGActivationReturn = new PublishRGActivationReturn(
				aContext,
				aCustomerTransportId,
				aBillingAccountNumber,
				aDSLAM,
				aRG,
				aActivationTime,
				aOrderAction,
				aProperties);

		// build request
		idlToXML(			
			aContext,
			aCustomerTransportId,
			aBillingAccountNumber,
			aDSLAM,
			aRG,
			aActivationTime,
			aOrderAction,
			aProperties,
			publishRGActivationReturn);

		aUtility.log(LogEventId.DEBUG_LEVEL_1, "<PublishRGActivation::publishActivation()");

		return publishRGActivationReturn;
	}

	/**
	 * Method idlToXML.
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDSLAM
	 * @param aRG
	 * @param aActivationTime
	 * @param aOrderAction
	 * @param aProperties
	 * @throws DataNotFound
	 * @throws ObjectNotFound
	 * @throws NotImplemented
	 * @throws SystemFailure
	 * @throws BusinessViolation
	 * @throws AccessDenied
	 * @throws InvalidData
	 */
	private void idlToXML(	
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aProperties,
		PublishRGActivationReturn publishRGActivationReturn)
		throws
			DataNotFound,
			ObjectNotFound,
			NotImplemented,
			SystemFailure,
			BusinessViolation,
			AccessDenied,
			InvalidData {

		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">PublishRGActivation::idlToXML()");

		String responseXml = null;
		String myClient = null;

		Properties messageTags;

		// build response object		
		_publishRGActivationResponse aPublishRGActivationResponse = 
			new _publishRGActivationResponse();

		aPublishRGActivationResponse.aPublishRGActivationReturn(publishRGActivationReturn);

		_publishRGActivationResponseBISMsg aPublishRGActivationResponseBISMsg =
			new _publishRGActivationResponseBISMsg(aPublishRGActivationResponse);
			
		try {

			// encode request object
			responseXml = VAXBDocumentWriter.encode(aPublishRGActivationResponseBISMsg);

			// remove VAXB tag and then append the request xml with Soap Envelope and Soap Header
			responseXml =
				SoapParserHelper.removeTagsFromXML(	responseXml, "<vaxb:VAXB", "</vaxb:VAXB>");

			messageTags = new Properties();

			messageTags.setProperty("embus:MessageTag", "PublishRGActivation");
			messageTags.setProperty("embus:ApplicationID", "ApplicationID");
			messageTags.setProperty("embus:MessageID", "MessageID");
			messageTags.setProperty("embus:CorrelationID", "CorrelationID");
			messageTags.setProperty("embus:ConversationKey", "ConversationKey");
			messageTags.setProperty("embus:LoggingKey", "LoggingKey");
			messageTags.setProperty("embus:ResponseMessageExpiration", "0");

			responseXml =
				SoapParserHelper.appendEMBUSSoapEnvelope( responseXml, messageTags );
						
		} catch (IOException ioEx) {
			aUtility.throwException(
				ExceptionCode.ERR_RM_IO_EXCEPTION,
				"IOException " + ioEx.getMessage(),
				(String) properties.get("BIS_NAME").toString(),
				Severity.Recoverable);
		}

		try {
			inputDSLAM = aDSLAM.theValue().aId.theValue();
		} catch (org.omg.CORBA.BAD_OPERATION e) {
		} catch (NullPointerException e) {			
		}

		if (!properties.get(PublishRGActivationConstants.MIGRATION_COMPLETE).equals(PublishRGActivationConstants.MIGRATION_FLAG)) {				
		
			if (inputDSLAM == null || inputDSLAM.trim().length() == 0) {
				myClient =(String) properties.get(PublishRGActivationConstants.DURING_MIGRATION);
				aUtility.log(LogEventId.INFO_LEVEL_2, "DSLAM <null>, Client = " + myClient);
				
			} else {
				try {
					DslamMigrationTable dslamTable = new DslamMigrationTable();
					DslamMigrationRow[] aDslamMigrationRows = null;
					
					aDslamMigrationRows = dslamTable.retrieveByDslamId(inputDSLAM, properties, (com.sbc.bccs.utilities.Logger) aUtility);
					
					int i = 0;			
					for (i=0; i < aDslamMigrationRows.length; i++) {
						tableDSLAM = aDslamMigrationRows[i].getDslamId();
						start_date = aDslamMigrationRows[i].getEffectiveStartDate();
						end_date = aDslamMigrationRows[i].getEffectiveEndDate();
					}
			
				} catch (SQLException e1) {
					myClient =(String) properties.get(PublishRGActivationConstants.DURING_MIGRATION);
					aUtility.log(LogEventId.INFO_LEVEL_2, "SQLException, Client = " + myClient);

				} catch (NullPointerException e3) {
					myClient =(String) properties.get(PublishRGActivationConstants.PRE_MIGRATION);
					aUtility.log(LogEventId.INFO_LEVEL_2, "Data Not Found, Client = " + myClient);
				}

				if (myClient == null) {
						if (today.before(start_date)) {
							// Send to OMS
							myClient = (String) properties.get(PublishRGActivationConstants.PRE_MIGRATION);
							aUtility.log(LogEventId.INFO_LEVEL_2, "Today < EFF_START_DT, Client = " + myClient);

						} else if (today.after(end_date)) {
							// Send to BBNMS
							myClient = (String) properties.get(PublishRGActivationConstants.POST_MIGRATION);
							aUtility.log(LogEventId.INFO_LEVEL_2, "Today > EFF_END_DT, Client = " + myClient);

						} else if ((today.after(start_date) || today.compareTo(start_date) == 0) && (today.before(end_date) || today.compareTo(end_date) == 0)) {
							// Send to BBNMS and OMS
							myClient =(String) properties.get(PublishRGActivationConstants.DURING_MIGRATION);
							aUtility.log(LogEventId.INFO_LEVEL_2, "EFF_START_DT <= Today <= EFF_END_DT & , Client = " + myClient);	
						}	
					}
			}
		} else {
			// Send to POST MIGRATION
			myClient = (String) properties.get(PublishRGActivationConstants.POST_MIGRATION);
			aUtility.log(LogEventId.INFO_LEVEL_2, "MIGRATION COMPLETED , Client = " + myClient);
		}
		
			publishMessages (myClient, responseXml);		
	
			aUtility.log(LogEventId.DEBUG_LEVEL_1, ">PublishRGActivation::idlToXML()");			
	}	

	private void publishMessages (String clientList, String responseXML) 
			throws
			DataNotFound,
			ObjectNotFound,
			NotImplemented,
			SystemFailure,
			BusinessViolation,
			AccessDenied,
			InvalidData {

		aUtility.log(LogEventId.DEBUG_LEVEL_1, ">PublishRGActivation::publishMessage()");		
		
		StringTokenizer st = new StringTokenizer(clientList, ":");
		while (st.hasMoreElements()) {

			try {				
				aService = new ClientService(properties, aUtility);
				
				String myClient = st.nextToken();						
				aService.setClient(myClient);
		
				aService.logREMOTE_CALL(); 
      
	            Properties aMessageTags = new Properties();
	            aMessageTags.put(PublishRGActivationConstants.JMS_propertyName,
	            		PublishRGActivationConstants.JMS_propertyValue);
	            
	            aService.publishMessage(responseXML, aMessageTags); 
	            aService.logREMOTE_RETURN();
	                        
			} catch (ServiceException e) {
				aUtility.log(LogEventId.INFO_LEVEL_2, "MISSED_TARGET_XML: [" + responseXML + "]");
				aUtility.throwException(
					ExceptionCode.ERR_RM_SYSTEM_FAILURE,
					"ServiceException " + e.getMessage(),
					(String)properties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);
				
			} catch (Exception e) {
				aUtility.log(LogEventId.INFO_LEVEL_2, "MISSED_TARGET_XML: [" + responseXML + "]");
				aUtility.throwException(
					ExceptionCode.ERR_RM_SYSTEM_FAILURE,
					"Unexpected Error Processing the message. " + e.getMessage(),
					(String)properties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);	
			} finally {
				aUtility.log(LogEventId.DEBUG_LEVEL_1, "<PublishRGActivation:: publishMessage()");
			}
		}
	}

}
