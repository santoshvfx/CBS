// $Id: RmBean.java,v 1.81.2.2 2012/11/21 19:24:56 rg039m Exp $

package com.sbc.eia.bis.facades.rm.ejb;

import javax.ejb.SessionBean;
import javax.naming.InitialContext;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.facades.rm.ejb.CreateFacilityAssignment.CreateFacilityAssignment;
import com.sbc.eia.bis.facades.rm.ejb.CreateFacilityAssignment.CreateFacilityAssignmentBean;
import com.sbc.eia.bis.facades.rm.ejb.CreateFacilityAssignment.CreateFacilityAssignmentHome;
import com.sbc.eia.bis.facades.rm.ejb.PublishAutoDiscoveryResults.PublishAutoDiscoveryResults;
import com.sbc.eia.bis.facades.rm.ejb.PublishAutoDiscoveryResults.PublishAutoDiscoveryResultsBean;
import com.sbc.eia.bis.facades.rm.ejb.PublishAutoDiscoveryResults.PublishAutoDiscoveryResultsHome;
import com.sbc.eia.bis.facades.rm.ejb.PublishRGActivation.PublishRGActivation;
import com.sbc.eia.bis.facades.rm.ejb.PublishRGActivation.PublishRGActivationHome;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveResourcesForService.RetrieveResourcesForService;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveResourcesForService.RetrieveResourcesForServiceBean;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveResourcesForService.RetrieveResourcesForServiceHome;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource.RetrieveServiceProvidersForResource;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource.RetrieveServiceProvidersForResourceBean;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource.RetrieveServiceProvidersForResourceHome;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForService.RetrieveServiceProvidersForService;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForService.RetrieveServiceProvidersForServiceBean;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForService.RetrieveServiceProvidersForServiceHome;
import com.sbc.eia.bis.facades.rm.ejb.SendActivateTNPortingSubscriptionMsg.SendActivateTNPortingSubscriptionMsg;
import com.sbc.eia.bis.facades.rm.ejb.SendActivateTNPortingSubscriptionMsg.SendActivateTNPortingSubscriptionMsgBean;
import com.sbc.eia.bis.facades.rm.ejb.SendActivateTNPortingSubscriptionMsg.SendActivateTNPortingSubscriptionMsgHome;
import com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder.SendFacilityAssignmentOrder;
import com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder.SendFacilityAssignmentOrderHome;
import com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder3.SendFacilityAssignmentOrder3;
import com.sbc.eia.bis.facades.rm.ejb.SendFacilityAssignmentOrder3.SendFacilityAssignmentOrder3Home;
import com.sbc.eia.bis.facades.rm.ejb.SendTNAssignmentOrder.SendTNAssignmentOrder;
import com.sbc.eia.bis.facades.rm.ejb.SendTNAssignmentOrder.SendTNAssignmentOrderBean;
import com.sbc.eia.bis.facades.rm.ejb.SendTNAssignmentOrder.SendTNAssignmentOrderHome;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility.ValidateFacility;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility.ValidateFacilityHome;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility2.ValidateFacility2;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility2.ValidateFacility2Home;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility3.ValidateFacility3;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacility3.ValidateFacility3Home;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacilityForProvisioning.ValidateFacilityForProvisioning;
import com.sbc.eia.bis.facades.rm.ejb.ValidateFacilityForProvisioning.ValidateFacilityForProvisioningHome;
import com.sbc.eia.bis.facades.rm.transactions.ValidateFacility.ValidateFacilityConstants;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.idl.rm_ls_types.AddFiberServiceTNPortTelephoneNumberRequest;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscription;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ObjectType;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.Time;

/**
 * This is a Session Bean Class
 */

public class RmBean
	extends com.sbc.eia.bis.RmNam.utilities.TranBase
	implements RmFacadeOperations, SessionBean {

	private javax.ejb.SessionContext mySessionCtx = null;
	final static long serialVersionUID = 3206093459760846163L;

	/**
	 * ejbActivate method comment
	 * 
	 * @exception java.rmi.RemoteException
	 *                The exception description.
	 */
	public void ejbActivate() throws java.rmi.RemoteException {
	}
	/**
	 * ejbCreate method comment
	 * 
	 * @exception javax.ejb.CreateException
	 *                The exception description.
	 * @exception java.rmi.RemoteException
	 *                The exception description.
	 */
	public void ejbCreate()
		throws javax.ejb.CreateException, java.rmi.RemoteException {
		init(mySessionCtx);
	}
	/**
	 * ejbPassivate method comment
	 * 
	 * @exception java.rmi.RemoteException
	 *                The exception description.
	 */
	public void ejbPassivate() throws java.rmi.RemoteException {
	}
	/**
	 * ejbRemove method comment
	 * 
	 * @exception java.rmi.RemoteException
	 *                The exception description.
	 */
	public void ejbRemove() throws java.rmi.RemoteException {
	}
	/**
	 * getSessionContext method comment
	 * 
	 * @return javax.ejb.SessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * retrieveResourcesForService method returns the resources that implement
	 * the specified service.
	 * 
	 * @return RetrieveResourcesForServiceReturn
	 * @param aContext
	 *            BisContext
	 * @param aServiceHandle
	 *            ObjectKey
	 * @param aResourceRoleNames
	 *            String[]
	 * @exception InvalidData:
	 *                An input parameter contained invalid data.
	 * @exception AccessDenied:
	 *                Access to the specified domain object or information is
	 *                not allowed.
	 * @exception BusinessViolation:
	 *                The attempted action violates a business rule.
	 * @exception SystemFailure:
	 *                The method could not be completed due to system level
	 *                errors (e.g., out of memory, loss of network
	 *                connectivity)n.
	 * @exception NotImplemented:
	 *                The method has not been implemented.
	 * @exception ObjectNotFound:
	 *                The desired domain object could not be found.
	 * @exception DataNotFound:
	 *                No data found.
	 */
	public RetrieveResourcesForServiceReturn retrieveResourcesForService(
		BisContext aContext,
		ObjectKey aServiceHandle,
		String[] aResourceRoleNames)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
				
		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - retrieveResourcesForService");
		RetrieveResourcesForServiceReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*
			InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup("java:comp/env/ejb/RetrieveResourcesForServiceHome");

			RetrieveResourcesForServiceHome home =
				(
					RetrieveResourcesForServiceHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					RetrieveResourcesForServiceHome.class);
			RetrieveResourcesForService retrieveResourcesForServiceBean =
				home.create(); */
			//TODO: Document creating bean without remote lookup
			RetrieveResourcesForServiceBean retrieveResourcesForServiceBean = new RetrieveResourcesForServiceBean();
			retrieveResourcesForServiceBean.PROPERTIES = getPROPERTIES();

			retVal =
				retrieveResourcesForServiceBean.retrieveResourcesForService(
					callerContext,
					aServiceHandle,
					aResourceRoleNames,
					myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);

		} finally {
			log(LOG_EXIT, "RM Facade - retrieveResourcesForService");
		}
		return retVal;
	}
	/**
	 * retrieveServiceProvidersForResource method returns service providers that
	 * provide the specified service types at the specified resource.
	 * 
	 * @return RetrieveServiceProvidersForResourceReturn
	 * @param aContext
	 *            BisContext
	 * @param aResourceHandle
	 *            String
	 * @param aServiceTypeHandles
	 *            ObjectKey[]
	 * @exception InvalidData:
	 *                An input parameter contained invalid data.
	 * @exception AccessDenied:
	 *                Access to the specified domain object or information is
	 *                not allowed.
	 * @exception BusinessViolation:
	 *                The attempted action violates a business rule.
	 * @exception SystemFailure:
	 *                The method could not be completed due to system level
	 *                errors (e.g., out of memory, loss of network
	 *                connectivity)n.
	 * @exception NotImplemented:
	 *                The method has not been implemented.
	 * @exception ObjectNotFound:
	 *                The desired domain object could not be found.
	 * @exception DataNotFound:
	 *                No data found.
	 */
	public RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
		BisContext aContext,
		String aResourceHandle,
		ObjectKey[] aServiceTypeHandles)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
				
		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - retrieveServiceProvidersForResource");
		RetrieveServiceProvidersForResourceReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*
			InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup(
					"java:comp/env/ejb/RetrieveServiceProvidersForResourceHome");

			RetrieveServiceProvidersForResourceHome home =
				(
					RetrieveServiceProvidersForResourceHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					RetrieveServiceProvidersForResourceHome.class);
			RetrieveServiceProvidersForResource retrieveServiceProvidersForResourceBean =
				home.create(); */

			RetrieveServiceProvidersForResourceBean retrieveServiceProvidersForResourceBean = new RetrieveServiceProvidersForResourceBean();
			retrieveServiceProvidersForResourceBean.PROPERTIES = getPROPERTIES();

			retVal =
				retrieveServiceProvidersForResourceBean
					.retrieveServiceProvidersForResource(
					callerContext,
					aResourceHandle,
					aServiceTypeHandles,
					myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);

		} finally {
			log(LOG_EXIT, "RM Facade - retrieveServiceProvidersForResource");
		}
		return retVal;
	}
	/**
	 * retrieveServiceProvidersForService method returns service providers that
	 * provide the specified service types for the specified service.
	 * 
	 * @return RetrieveServiceProvidersForServiceReturn
	 * @param aContext
	 *            BisContext
	 * @param aSerivceHandle
	 *            ObjectKey
	 * @param aServiceTypeHandles
	 *            ObjectKey[]
	 * @exception InvalidData:
	 *                An input parameter contained invalid data.
	 * @exception AccessDenied:
	 *                Access to the specified domain object or information is
	 *                not allowed.
	 * @exception BusinessViolation:
	 *                The attempted action violates a business rule.
	 * @exception SystemFailure:
	 *                The method could not be completed due to system level
	 *                errors (e.g., out of memory, loss of network
	 *                connectivity)n.
	 * @exception NotImplemented:
	 *                The method has not been implemented.
	 * @exception ObjectNotFound:
	 *                The desired domain object could not be found.
	 * @exception DataNotFound:
	 *                No data found.
	 */
	public RetrieveServiceProvidersForServiceReturn retrieveServiceProvidersForService(
		BisContext aContext,
		ObjectKey aSerivceHandle,
		ObjectKey[] aServiceTypeHandles)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
				
		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - retrieveServiceProvidersForService");
		RetrieveServiceProvidersForServiceReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup(
					"java:comp/env/ejb/RetrieveServiceProvidersForServiceHome");

			RetrieveServiceProvidersForServiceHome home =
				(
					RetrieveServiceProvidersForServiceHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					RetrieveServiceProvidersForServiceHome.class);
			RetrieveServiceProvidersForService retrieveServiceProvidersForServiceBean =
				home.create();*/

			RetrieveServiceProvidersForServiceBean retrieveServiceProvidersForServiceBean = new RetrieveServiceProvidersForServiceBean();
			retrieveServiceProvidersForServiceBean.PROPERTIES = getPROPERTIES();

			retVal =
				retrieveServiceProvidersForServiceBean
					.retrieveServiceProvidersForService(
					callerContext,
					aSerivceHandle,
					aServiceTypeHandles,
					myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);

		} finally {
			log(LOG_EXIT, "RM Facade - retrieveServiceProvidersForService");
		}
		return retVal;
	}

	/**
	 * CreateFacilityAssignment Invoke XNG: The facility will be created. Used
	 * to create the cross connects
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.SERVICECENTER
	 *            BisContextProperty.APPLICATION BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aCustomerTransportId
	 *            Circuit Id.
	 * @param aBillingAccountNumber
	 *            Customer account.
	 * @param aServiceLocation
	 *            A service location.
	 * @param aMaintenanceFlag
	 *            Maintenance Flag
	 * @param aDueDate
	 *            Order Due Date.
	 * @param aOrderAction
	 *            OrderAction.
	 * @param aTaperCode
	 *            FST Taper Code for FTTP and SAI Taper Code for FTTN.
	 * @param aNetworkType
	 *            Either FTTP or FTTN
	 * @param aNetworkTypeChoice
	 *            Either FTTP or FTTN
	 * @param aProperties
	 *            ObjectPropertySeqOpt
	 */

	public CreateFacilityAssignmentReturn createFacilityAssignment(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		Location aServiceLocation,
		BooleanOpt aMaintenanceFlag,
		EiaDate aDueDate,
		OrderAction aOrderAction,
		StringOpt aTaperCode,
		String aNetworkType,
		NetworkType aNetworkTypeChoice,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - createFacilityAssignment");

		CreateFacilityAssignmentReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup("java:comp/env/ejb/CreateFacilityAssignmentHome");
			CreateFacilityAssignmentHome home =
				(
					CreateFacilityAssignmentHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					CreateFacilityAssignmentHome.class);
			CreateFacilityAssignment createFacilityAssignmentBean =
				home.create();*/

			CreateFacilityAssignmentBean createFacilityAssignmentBean = new CreateFacilityAssignmentBean();
			createFacilityAssignmentBean.PROPERTIES = getPROPERTIES();

			retVal =
				createFacilityAssignmentBean.createFacilityAssignment(
					callerContext,
					aCustomerTransportId,
					aBillingAccountNumber,
					aServiceLocation,
					aMaintenanceFlag,
					aDueDate,
					aOrderAction,
					aTaperCode,
					aNetworkType,
					aNetworkTypeChoice,
					aProperties,
					myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);
		} finally {
			log(LOG_EXIT, "RM Facade - createFacilityAssignment");
		}
		return retVal;
	}

	/**
	 * PublishAutoDiscoveryResults
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.SERVICECENTER
	 *            BisContextProperty.APPLICATION BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aCustomerTransportId
	 *            Circuit Id.
	 * @param aBillingAccountNumber
	 *            The customer's Billing Account Number.
	 * @param aServiceAddress
	 *            Address.
	 * @param ProductSubscription[]
	 *            aProductSubscriptions
	 * @param aTelephoneNumber
	 *            TN.
	 * @param aAssignedProductId
	 *            product id.
	 * @param aOrderAction
	 *            OrderAction.
	 * @param aProperties
	 *            ObjectPropertySeqOpt
	 */

	public PublishAutoDiscoveryResultsReturn publishAutoDiscoveryResults(
		BisContext aContext,
		String aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		AddressOpt aServiceAddress,
		ProductSubscription[] aProductSubscriptions,
		StringOpt aTelephoneNumber,
		String aAssignedProductId,
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

		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - publishAutoDiscoveryResults");

		PublishAutoDiscoveryResultsReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup("java:comp/env/ejb/PublishAutoDiscoveryResultsHome");
			PublishAutoDiscoveryResultsHome home =
				(
					PublishAutoDiscoveryResultsHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					PublishAutoDiscoveryResultsHome.class);
			PublishAutoDiscoveryResults publishAutoDiscoveryResultsBean =
				home.create();*/

			PublishAutoDiscoveryResultsBean publishAutoDiscoveryResultsBean = new PublishAutoDiscoveryResultsBean();
			publishAutoDiscoveryResultsBean.PROPERTIES = getPROPERTIES();

			retVal =
				publishAutoDiscoveryResultsBean.publishAutoDiscoveryResults(
					callerContext,
					aCustomerTransportId,
					aBillingAccountNumber,
					aServiceAddress,
					aProductSubscriptions,
					aTelephoneNumber,
					aAssignedProductId,
					aOrderAction,
					aProperties,
					myLogger);

		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);
		} finally {
			log(LOG_EXIT, "RM Facade - publishAutoDiscoveryResults");
		}
		return retVal;
	}

	/**
	 * PublishRGActivation
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.SERVICECENTER
	 *            BisContextProperty.APPLICATION BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aCustomerTransportId
	 *            Circuit Id.
	 * @param aBillingAccountNumber
	 *            The customer's Billing Account Number.
	 * @param aDSLAM
	 *            DSLAM transport.
	 * @param aRG
	 *            RG.
	 * @param aActivationTime
	 *            Activation time.
	 * @param aOrderAction
	 *            OrderAction.
	 * @param aProperties
	 *            ObjectPropertySeqOpt
	 */

	public PublishRGActivationReturn publishRGActivation(
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

		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - publishRGActivation");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - publishRGActivation");
		}
	 	return null;
	}

	/**
	 * sendTNAssignmentOrder LS order for provisioning CVOIP TN assignment(from
	 * OMS to CVOIP SOAC via RMBIS)
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.CUSTOMERNAME BisContextProperty.APPLICATION
	 *            BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aSOACServiceOrderNumber
	 *            SOAC Service Order Number
	 * @param aSOACServiceOrderCorrectionSuffix
	 *            SOAC Service order Correction Suffix
	 * @param aOrderNumber
	 *            OMS Order ID
	 * @param aOrderActionType
	 *            Action Type (see OrderActionTypeValues)
	 * @param aSubActionType
	 *            Sub-action Type (see SubActionTypeValues)
	 * @param aServiceLocation
	 *            SAG validated Service Address, state info.
	 * @param aOriginalDueDate
	 *            Order Due Date
	 * @param aSubsequentDueDate
	 *            Subsequent Due Date
	 * @param aCompletionIndicator
	 * @param aApplicationDate
	 *            Application Date
	 * @param aResendFlag
	 *            Indicates resend order
	 * @param aWireCenter
	 * @param aRateCenter
	 *            rateCenter state will be used to get the wire center and SOAC
	 *            entity.
	 * @param aTelephoneNumbers
	 *            Telephone numbers related to this order
	 */

	public SendTNAssignmentOrderReturn sendTNAssignmentOrder(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aOrderNumber,
		String aOrderActionType,
		StringOpt aSubActionType,
		BooleanOpt aCompletionIndicator,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		BooleanOpt aResendFlag,
		StringOpt aWireCenter,
		StringOpt aRateCenter,
		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - sendTNAssignmentOrder");
		SendTNAssignmentOrderReturn retVal = null;

		try {
			//doing the local EJB lookup
			/*InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup("java:comp/env/ejb/SendTNAssignmentOrderHome");

			SendTNAssignmentOrderHome home =
				(
					SendTNAssignmentOrderHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					o,
					SendTNAssignmentOrderHome.class);
			SendTNAssignmentOrder sendTNAssignmentOrderBean = home.create();*/

			SendTNAssignmentOrderBean sendTNAssignmentOrderBean = new SendTNAssignmentOrderBean();
			sendTNAssignmentOrderBean.PROPERTIES = getPROPERTIES();

			retVal =
				sendTNAssignmentOrderBean.sendTNAssignmentOrder(
					callerContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aOrderNumber,
					aOrderActionType,
					aSubActionType,
					aCompletionIndicator,
					aServiceLocation,
					aOriginalDueDate,
					aSubsequentDueDate,
					aApplicationDate,
					aResendFlag,
					aWireCenter,
					aRateCenter,
					aTelephoneNumberOrderPairs,
					aProperties, myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);

		} finally {
			log(LOG_EXIT, "RM Facade - sendTNAssignmentOrder");
		}
		return retVal;
	}

	/**
	 * sendActivateTNPortingSubscriptionMsg
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.SERVICECENTER
	 *            BisContextProperty.APPLICATION -- Initiating System
	 *            BisContextProperty.CUSTOMERNAME -- Initiating user
	 *            BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aContext
	 * @param aLocalServiceProviderId
	 * @param aTelephoneNumbers
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 */
	public SendActivateTNPortingSubscriptionMsgReturn sendActivateTNPortingSubscriptionMsg(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers)
		throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData {

		validateBisContext(
			aContext,
			getEnv("BIS_NAME"),
			ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
			"Required field is null");

		log(LOG_ENTRY, "RM Facade - sendActivateTNPortingSubscriptionMsg");
		SendActivateTNPortingSubscriptionMsgReturn retVal = null;

		try {
			//doing the local EJB lookup
			InitialContext ctx = new InitialContext();
			Object o =
				ctx.lookup(
					"java:comp/env/ejb/SendActivateTNPortingSubscriptionMsgHome");

			SendActivateTNPortingSubscriptionMsgHome home =
				(SendActivateTNPortingSubscriptionMsgHome) javax
					.rmi
					.PortableRemoteObject
					.narrow(o, SendActivateTNPortingSubscriptionMsgHome.class);

			SendActivateTNPortingSubscriptionMsg sendActivateTNPortingSubscriptionMsgBean =
				home.create();

//			SendActivateTNPortingSubscriptionMsgBean sendActivateTNPortingSubscriptionMsgBean = SendActivateTNPortingSubscriptionMsgBean();
//			sendActivateTNPortingSubscriptionMsgBean.PROPERTIES = getPROPERTIES();

			retVal =
				sendActivateTNPortingSubscriptionMsgBean
					.sendActivateTNPortingSubscriptionMsg(
					callerContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aLocalServiceProviderId,
					aTelephoneNumbers,
					myLogger);
		}
		catch ( InvalidData e )         { throw e ; }
		catch ( AccessDenied e )        { throw e ; }
		catch ( BusinessViolation e )   { throw e ; }
		catch ( SystemFailure e )       { throw e ; }
		catch ( NotImplemented e )      { throw e ; }
		catch ( ObjectNotFound e )      { throw e ; }
		catch ( DataNotFound e )        { throw e ; }
		catch ( Throwable t )
		{
			throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_ERROR,
				"RM Caught an uncaught exception: " + t.toString(),
				"RM",
				Severity.UnRecoverable,
				(Exception) t);

		} finally {
			log(LOG_EXIT, "RM Facade - sendActivateTNPortingSubscriptionMsg");
		}
		return retVal;
	}

	/**
	 * sendFacilityAssignmentOrder LS order for provisioning the facility
	 * assignment (from OMS to Telco SOAC via RMBIS)
	 * 
	 * @param aContext
	 *            The client's calling context. Required tags are
	 *            BisContextProperty.CUSTOMERNAME BisContextProperty.APPLICATION
	 *            BisContextProperty.BUSINESSUNIT
	 *            BisContextProperty.LOGGINGINFORMATION
	 * @param aSOACServiceOrderNumber
	 *            SOAC Service Order Number
	 * @param aSOACServiceOrderCorrectionSuffix
	 *            SOAC Service order Correction Suffix
	 * @param aNetworkType
	 *            Network Type (FTTP/FTTN/RGPON) (see NetworkTypeValues)
	 * @param aOrderActionId
	 *            OMS Order Action ID
	 * @param aOrderNumber
	 *            OMS Order ID
	 * @param aOrderActionType
	 *            Action Type (see OrderActionTypeValues)
	 * @param aCompletionIndicator
	 *            true/false
	 * @param aSubActionType
	 *            Sub-action Type (see SubActionTypeValues)
	 * @param aCircuitId
	 *            LS Circuit ID
	 * @param aServiceLocation
	 *            SAG validated Service Address
	 * @param aOriginalDueDate
	 *            Order Due Date
	 * @param aSubsequentDueDate
	 *            Subsequent Due Date
	 * @param aContactTelephoneNumber
	 *            Rep Contact Number
	 * @param aApplicationDate
	 *            Application Date
	 * @param aTDMTelphoneNumber
	 *            TDM TN
	 * @param aRelatedServiceOrderNumber
	 *            Related Order Number
	 * @param aAdditionalLineFlag
	 *            Order for additional line?
	 * @param aLineShareDisconnectFlag
	 *            Disconnect lineshare?
	 * @param aClassOfService
	 *            Class of Service (see ClassOfServiceValues)
	 * @param aResendFlag
	 *            Indicates resend order.
	 */
	public SendFacilityAssignmentOrderReturn sendFacilityAssignmentOrder(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aNetworkType,
		String aOrderActionId,
		String aOrderNumber,
		String aOrderActionType,
		BooleanOpt aCompletionIndicator,
		StringOpt aSubActionType,
		String aCircuitId,
		Location aServiceLocation,
		EiaDate aOriginalDueDate,
		EiaDateOpt aSubsequentDueDate,
		EiaDate aApplicationDate,
		StringOpt aTDMTelphoneNumber,
		StringOpt aRelatedServiceOrderNumber,
		BooleanOpt aLineShareDisconnectFlag,
		String aClassOfService,
		BooleanOpt aResendFlag,
		ObjectPropertySeqOpt aProperties)
		throws
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			InvalidData {
				validateBisContext(
						aContext,
						getEnv("BIS_NAME"),
						ExceptionCode.ERR_RM_MISSING_BIS_CONTEXT,
						"Required field is null");
				
				callerContext = aContext;
				
			    try 
			    { 
					log(LOG_ENTRY, "RM Facade - sendFacilityAssignmentOrder");
					log(LOG_INFO_LEVEL_1, "Method Not Implemented");       
		            throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
		                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		        } 
			    finally 
			    { 
			        log(LOG_EXIT,  "RM Facade - sendFacilityAssignmentOrder"); 
			    } 
			    return null ; 
				}
	/**
	 * PublishTNAssignmentOrderNotification
	 */

	public PublishTNAssignmentOrderNotificationReturn publishTNAssignmentOrderNotification()
	 {
	 	
		log(LOG_ENTRY, "RM Facade - publishTNAssignmentOrderNotification");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishTNAssignmentOrderNotification");
		return null;
	}

	/**
	 * publishTNPortingNotification
	 */

	public PublishTNPortingNotificationReturn publishTNPortingNotification() {
		
		log(LOG_ENTRY, "RM Facade - publishTNPortingNotification");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishTNPortingNotification");
		return null;
	}

	/**
	 * publishFacilityAssignmentOrderNotification
	 */
	
	public PublishFacilityAssignmentOrderNotificationReturn publishFacilityAssignmentOrderNotification() {
		
		log(LOG_ENTRY, "RM Facade - publishFacilityAssignmentOrderNotification");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishFacilityAssignmentOrderNotification");
		return null;
	} 
	
	 /**
	  * selfTest - This method runs a self test on the server supporting the
	  * method. Throws BIS exception on self test failure.
	  * 
	  * @param aBisContext
	  *            Context properties. List of tag/value pairs. No tags are
	  *            required.
	  */
	public SelfTestReturn selfTest(BisContext aContext) 
	throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions {
		
		com.sbc.eia.bis.facades.rm.transactions.SelfTest.RMSelfTest
			selfTest = new com.sbc.eia.bis.facades.rm.transactions.SelfTest.RMSelfTest(getPROPERTIES());
			
		return selfTest.execute( aContext );
		
	}
	
	
	/**
	 * ping - This is used to test if server supporting the method has been
	 * deployed successfully and is responding to client requests. Successful
	 * return or thrown BIS exception indicates server has been deployed
	 * successfully and is responding
	 * 
	 * @param aBisContext aContext
	 *            Context properties. List of tag/value pairs. No tags are
	 *            required.
	 */
	public PingReturn ping(BisContext aContext) 
	throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions {
		
		com.sbc.eia.bis.facades.rm.transactions.Ping.Ping
			ping = new com.sbc.eia.bis.facades.rm.transactions.Ping.Ping(getPROPERTIES());
			
		return ping.execute( aContext );
				
	}

	  /**
	   * validateFacility
	   * 
	   * Interface between OMS/AMSS/FIRST and RM to retrieve and analyze
	   * facility availability information applicable to LS ordering.
	   * 
	   * @param BisContext aContext
	   * @param Location aServiceLocation
	   * @param StringOpt aRelatedCircuitID
	   * @param StringOpt aWorkingTelephoneNumber
	   * @param ShortOpt aMaxPairsToAnalyze
	   * @param StringOpt aSOACServiceOrderNumber
	   * @param StringOpt aSOACServiceOrderCorrectionSuffix
	   * @param EiaDateOpt aUverseOrderDueDate
	   * @param ObjectPropertySeqOpt aProperties
	   */

	public ValidateFacilityReturn validateFacility(
		BisContext aContext,
		Location aServiceLocation,
		StringOpt aRelatedCircuitID,
		StringOpt aWorkingTelephoneNumber,
		ShortOpt aMaxPairsToAnalyze,
	    StringOpt aSOACServiceOrderNumber,
	    StringOpt aSOACServiceOrderCorrectionSuffix,
        EiaDateOpt aUverseOrderDueDate,
		ObjectPropertySeqOpt aProperties)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound 
	{
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - validateFacility");
		
	    try 
	    { 
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
        } 
	    finally 
	    { 
	        log(LOG_EXIT,  "RM Facade - validateFacility"); 
	    } 
	    return null ; 	    
	}

	/**
	 * publishValidateFacilityNotification
	 */
	
	public PublishValidateFacilityNotificationReturn publishValidateFacilityNotification() {
		
		log(LOG_ENTRY, "RM Facade - publishValidateFacilityNotification");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishValidateFacilityNotification");
		return null;
	} 

	/**
	 * modifyFacilityInfo
	 */
	
	public ModifyFacilityInfoReturn modifyFacilityInfo() {
		
		log(LOG_ENTRY, "RM Facade - modifyFacilityInfo");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - modifyFacilityInfo");
		return null;
	} 
	
	  /**
	   * validateFacility2
	   * 
	   * @param BisContext aContext  
	   * @param Location aServiceLocation
	   * @param StringOpt aRelatedCircuitID
	   * @param StringOpt aWorkingTelephoneNumber
	   * @param ShortOpt aMaxPairsToAnalyze
	   * @param StringOpt aSOACServiceOrderNumber
	   * @param StringOpt aSOACServiceOrderCorrectionSuffix
	   * @param EiaDateOpt aUverseOrderDueDate
	   * @param ObjectPropertySeqOpt aProperties
	   * @param ObjectType[] aNtis
	   * @param StringOpt aOrderActionType
	   * @param StringOpt aSubActionType
	   */
	public ValidateFacility2Return validateFacility2 (
			BisContext aContext, 
			Location aServiceLocation, 
			StringOpt aRelatedCircuitID, 
			StringOpt aWorkingTelephoneNumber, 
			ShortOpt aMaxPairsToAnalyze, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			EiaDateOpt aUverseOrderDueDate, 
			ObjectType[] aNtis, 
			StringOpt aOrderActionType, 
			StringOpt aSubActionType, 
			ObjectPropertySeqOpt aProperties)
			throws
				InvalidData,
				AccessDenied,
				BusinessViolation,
				SystemFailure,
				NotImplemented,
				ObjectNotFound,
				DataNotFound 
				
		{
			setCallerContext( aContext ) ;
			
			log(LOG_ENTRY, "RM Facade - validateFacility2");
			
		    try 
		    { 
				throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
	                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
	        } 
		    finally 
		    { 
		        log(LOG_EXIT,  "RM Facade - validateFacility2"); 
		    } 
		    return null ; 	    
		}
	

	/**
	 * PublishValidateFacilityNotification2
	 */
	public PublishValidateFacilityNotification2Return publishValidateFacilityNotification2() {
		
		log(LOG_ENTRY, "RM Facade - publishValidateFacilityNotification2");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishValidateFacilityNotification2");
		return null;
	} 
	
	/**
	 * sendFacilityAssignmentOrder2
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aSOACServiceOrderCorrectionSuffix
	 * @param String aNetworkType
	 * @param StringOpt aOldNetworkType
	 * @param String aOrderActionId
	 * @param String aOrderNumber
	 * @param BooleanOpt aCompletionIndicator
	 * @param StringOpt aSubActionType
	 * @param String aCircuitId
	 * @param StringOpt aSecondaryCircuitId 
	 * @param Location aServiceLocation 
	 * @param EiaDate aOriginalDueDate 
	 * @param EiaDateOpt aSubsequentDueDate 
	 * @param EiaDate aApplicationDate 
	 * @param StringOpt aRelatedCircuitID 
	 * @param StringOpt aSecondaryRelatedCircuitID 
	 * @param StringOpt aRelatedServiceOrderNumber 
	 * @param BooleanOpt aLineShareDisconnectFlag 
	 * @param String aClassOfService 
     * @param BooleanOpt aResendFlag 
	 * @param CompositeObjectKey aBillingAccountNumber 
	 * @param BooleanOpt aInterceptRedirectIndicator 
	 * @param StringOpt aDryloopRelatedCircuitId 
	 * @param StringOpt aDSLDisconnectTelephoneNumber 
	 * @param StringOpt aExceptionRoutingIndicator 
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SendFacilityAssignmentOrder2Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public SendFacilityAssignmentOrder2Return sendFacilityAssignmentOrder2 (
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aNetworkType, 
			StringOpt aOldNetworkType, 
			String aOrderActionId, 
			String aOrderNumber, 
			String aOrderActionType, 
			BooleanOpt aCompletionIndicator, 
			StringOpt aSubActionType, 
			String aCircuitId, 
			StringOpt aSecondaryCircuitId, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			StringOpt aRelatedCircuitID, 
			StringOpt aSecondaryRelatedCircuitID, 
			StringOpt aRelatedServiceOrderNumber, 
			BooleanOpt aLineShareDisconnectFlag, 
			String aClassOfService, 
			BooleanOpt aResendFlag, 
			CompositeObjectKey aBillingAccountNumber, 
			ObjectPropertySeqOpt aProperties)
			throws
				AccessDenied,
				BusinessViolation,
				SystemFailure,
				NotImplemented,
				ObjectNotFound,
				DataNotFound,
				InvalidData 
				
		{
			setCallerContext( aContext ) ;
			
			log(LOG_ENTRY, "RM Facade - sendFacilityAssignmentOrder2");
		
			try 
			{ 
				throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
			} 
			finally 
			{ 
				log(LOG_EXIT,  "RM Facade - sendFacilityAssignmentOrder2"); 
			} 
			return null ; 
		}
	
	/**
	 * PublishFacilityAssignmentOrderNotification2
	 */
	public PublishFacilityAssignmentOrderNotification2Return publishFacilityAssignmentOrderNotification2() {
		
		log(LOG_ENTRY, "RM Facade - publishFacilityAssignmentOrderNotification2");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishFacilityAssignmentOrderNotification2");
		return null;
	} 
	
	/**
	 * ModifyFacilityInfo2
	 */
	public ModifyFacilityInfo2Return modifyFacilityInfo2() {
		
		log(LOG_ENTRY, "RM Facade - modifyFacilityInfo2");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - modifyFacilityInfo2");
		return null;
	} 
	
	/**
	 * validateFacility3
	 * 
	 * @param BisContext aContext 
	 * @param Location aServiceLocatio 
	 * @param StringOpt aRelatedCircuitID 
	 * @param StringOpt aWorkingTelephoneNumbe 
	 * @param ShortOpt aMaxPairsToAnalyze 
	 * @param StringOpt aSOACServiceOrderNumber 
	 * @param EiaDateOpt aOrderDueDate 
     * @param ObjectType[] aNti 
	 * @param OrderAction2Opt aOrderAction 
	 * @param ObjectPropertySeqOpt aProperties
	 * @return ValidateFacility3Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public ValidateFacility3Return validateFacility3 (
			BisContext aContext, 
			Location aServiceLocation, 
			StringOpt aRelatedCircuitID, 
			StringOpt aWorkingTelephoneNumber, 
			ShortOpt aMaxPairsToAnalyze, 
			StringOpt aSOACServiceOrderNumber, 
			EiaDateOpt aOrderDueDate, 
			ObjectType[] aNtis, 
			OrderAction2Opt aOrderAction, 
			ObjectPropertySeqOpt aProperties)
			throws
				InvalidData,
				AccessDenied,
				BusinessViolation,
				SystemFailure,
				NotImplemented,
				ObjectNotFound,
				DataNotFound 
				
		{
			setCallerContext( aContext ) ;
			
			log(LOG_ENTRY, "RM Facade - validateFacility3");
					
			try 
			{ 
				throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
			                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
			} 
			finally 
			{ 
				log(LOG_EXIT,  "RM Facade - validateFacility3"); 
			} 
			return null ; 	    
		}
	
	/**
	 * PublishValidateFacilityNotification3
	 */
	public PublishValidateFacilityNotification3Return publishValidateFacilityNotification3() {
		
		log(LOG_ENTRY, "RM Facade - publishValidateFacilityNotification3");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishValidateFacilityNotification3");
		return null;
	}

	@Override
	public PublishValidateFacilityNotification4Return publishValidateFacilityNotification4() {
		return null;
	}

	@Override
	public PublishValidateFacilityNotification5Return publishValidateFacilityNotification5() {
		return null;
	}

	/**
	 * PublishFacilityAssignmentOrderNotification3
	 */
	public PublishFacilityAssignmentOrderNotification3Return publishFacilityAssignmentOrderNotification3() {
		log(LOG_ENTRY, "RM Facade - publishFacilityAssignmentOrderNotification3");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishFacilityAssignmentOrderNotification3");
		return null;
	}
	
	/**
	 * PublishValidateFacilityForProvisioningNotification
	 */
	public publishValidateFacilityForProvisioningNotificationReturn publishValidateFacilityForProvisioningNotification() {
		log(LOG_ENTRY, "RM Facade - publishValidateFacilityForProvisioningNotification");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishValidateFacilityForProvisioningNotification");
		return null;
	}
	/**
	 * sendFacilityAssignmentOrder3
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aSOACServiceOrderCorrectionSuffix
	 * @param String aNetworkType
	 * @param StringOpt aOldNetworkType
	 * @param String aOrderActionId
	 * @param String aOrderNumber
	 * @param BooleanOpt aCompletionIndicator
	 * @param StringOpt aSubActionType
	 * @param String aCircuitId
	 * @param StringOpt aSecondaryCircuitId 
	 * @param Location aServiceLocation 
	 * @param EiaDate aOriginalDueDate 
	 * @param EiaDateOpt aSubsequentDueDate 
	 * @param EiaDate aApplicationDate 
	 * @param StringOpt aRelatedCircuitID 
	 * @param StringOpt aSecondaryRelatedCircuitID 
	 * @param StringOpt aRelatedServiceOrderNumber 
	 * @param BooleanOpt aLineShareDisconnectFlag 
	 * @param String aClassOfService 
     * @param BooleanOpt aResendFlag 
	 * @param CompositeObjectKey aBillingAccountNumber 
	 * @param BooleanOpt aInterceptRedirectIndicator 
	 * @param StringOpt aDryloopRelatedCircuitId 
	 * @param StringOpt aDSLDisconnectTelephoneNumber 
	 * @param StringOpt aExceptionRoutingIndicator 
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SendFacilityAssignmentOrder3Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public SendFacilityAssignmentOrder3Return sendFacilityAssignmentOrder3(BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aNetworkType, 
			StringOpt aOldNetworkType, 
			String aOrderActionId, 
			String aOrderNumber, 
			String aOrderActionType, 
			BooleanOpt aCompletionIndicator, 
			StringOpt aSubActionType, 
			String aCircuitId, 
			StringOpt aSecondaryCircuitId, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			StringOpt aRelatedCircuitID, 
			StringOpt aSecondaryRelatedCircuitID, 
			StringOpt aRelatedServiceOrderNumber, 
			BooleanOpt aLineShareDisconnectFlag, 
			String aClassOfService, 
			BooleanOpt aResendFlag, 
			CompositeObjectKey aBillingAccountNumber, 
			BooleanOpt aInterceptRedirectIndicator, 
			StringOpt aDryloopRelatedCircuitId, 
			StringOpt aDSLDisconnectTelephoneNumber, 
			StringOpt aExceptionRoutingIndicator, 
			ObjectPropertySeqOpt aProperties) 
	throws 	BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
		
		{
			
			setCallerContext( aContext ) ;
			
			log(LOG_ENTRY, "RM Facade - sendFacilityAssignmentOrder3");
				
			try 
			{ 
				throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
		                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		    } 
			finally 
			{ 
				log(LOG_EXIT,  "RM Facade - sendFacilityAssignmentOrder3"); 
			} 
			return null ; 
		}
	/**
	 * validateFacilityForProvisioning
	 * 
	 * @param BisContext aContext
	 * @param Location aServiceLocation
	 * @param StringOpt aRelatedCircuitID
	 * @param StringOpt aWorkingTelephoneNumber
	 * @param ShortOpt aMaxPairsToAnalyze
	 * @param StringOpt aSOACServiceOrderNumber
	 * @param EiaDateOpt aOrderDueDate
	 * @param ObjectType[] aNtis
	 * @param OrderAction2Opt aOrderAction
	 * @param ObjectPropertySeqOpt aProperties)
	 * @return ValidateFacilityForProvisioningReturn
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public ValidateFacilityForProvisioningReturn validateFacilityForProvisioning(
			BisContext aContext, 
			Location aServiceLocation, 
			StringOpt aRelatedCircuitID, 
			StringOpt aWorkingTelephoneNumber, 
			ShortOpt aMaxPairsToAnalyze, 
			StringOpt aSOACServiceOrderNumber, 
			EiaDateOpt aOrderDueDate, 
			ObjectType[] aNtis, 
			OrderAction2Opt aOrderAction, 
			ObjectPropertySeqOpt aProperties) 
	throws  BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
			
			{
				setCallerContext( aContext ) ;
				
				log(LOG_ENTRY, "RM Facade - validateFacilityForProvisioning");
						
				try 
				{ 
					throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
				                            "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
				} 
				finally 
				{ 
					log(LOG_EXIT,  "RM Facade - validateFacilityForProvisioning"); 
				} 
				return null ; 	    
			}

	/**
	 * activateFiberServiceTNPort
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aSOACServiceOrderCorrectionSuffix
	 * @param String aLocalServiceProviderId
	 * @param String[] aTelephoneNumbers
	 * @return ActivateFiberServiceTNPortReturn
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public ActivateFiberServiceTNPortReturn activateFiberServiceTNPort(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aLocalServiceProviderId, 
			String[] aTelephoneNumbers) 
	throws  
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
	{
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - activateFiberServiceTNPort");
		
		try 
		{
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
		                    "The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		}
		finally 
		{
			log(LOG_EXIT, "RM Facade - activateFiberServiceTNPort");
		}
		return null;
	}
	/**
	 * SubmitFiberServiceTNAssignmentOrder
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aSOACServiceOrderCorrectionSuffix
	 * @param String aOrderNumber
	 * @param String aOrderActionType
	 * @param StringOpt aSubActionType
	 * @param BooleanOpt aCompletionIndicator
	 * @param Location aServiceLocation
	 * @param EiaDate aOriginalDueDate
	 * @param EiaDateOpt aSubsequentDueDate
	 * @param EiaDate aApplicationDate
	 * @param BooleanOpt aResendFlag
	 * @param StringOpt aWireCenter
	 * @param StringOpt aRateCenter
	 * @param TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SubmitFiberServiceTNAssignmentOrderReturn
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public SubmitFiberServiceTNAssignmentOrderReturn submitFiberServiceTNAssignmentOrder(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aSOACServiceOrderCorrectionSuffix, 
			String aOrderNumber, 
			String aOrderActionType, 
			StringOpt aSubActionType, 
			BooleanOpt aCompletionIndicator, 
			Location aServiceLocation, 
			EiaDate aOriginalDueDate, 
			EiaDateOpt aSubsequentDueDate, 
			EiaDate aApplicationDate, 
			BooleanOpt aResendFlag, 
			StringOpt aWireCenter, 
			StringOpt aRateCenter, 
			TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs, 
			ObjectPropertySeqOpt aProperties) 
	throws  
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
	{
		setCallerContext( aContext ) ;
	
		log(LOG_ENTRY, "RM Facade - submitFiberServiceTNAssignmentOrder");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - submitFiberServiceTNAssignmentOrder");
		}
	 	return null;
	}
	
	/**
	 * addFiberServiceTNPort
	 * 
	 * @param BisContext aContext
	 * @param String aState
	 * @param StringOpt aSOACServiceOrderNumber
	 * @param StringOpt aSOACServiceOrderCorrectionSuffix
	 * @param StringOpt aOrderNumber
	 * @param String aOldServiceProviderId
	 * @param String aNewServiceProviderId
	 * @param EiaDate aOrderDueDate
	 * @param String[] aTelephoneNumbers
	 * @return AddFiberServiceTNPortReturn
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	
	public AddFiberServiceTNPortReturn addFiberServiceTNPort(
			BisContext aContext, 
			String aState, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			StringOpt aOrderNumber, 
			String aOldServiceProviderId, 
			String aNewServiceProviderId, 
			EiaDate aOrderDueDate, 
			String[] aTelephoneNumbers) 
		throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented {
		
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - addFiberServiceTNPort");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - addFiberServiceTNPort");
		}
	 	return null;
	}
	
	/**
	 * addFiberServiceTNPort2
	 * 
	 * @param BisContext aContext
	 * @param String aState
	 * @param StringOpt aSOACServiceOrderNumber
	 * @param StringOpt aSOACServiceOrderCorrectionSuffix
	 * @param StringOpt aOrderNumber
	 * @param String aOldServiceProviderId
	 * @param String aNewServiceProviderId
	 * @param EiaDate aOrderDueDate
	 * @param AddFiberServiceTNPortTelephoneNumberRequest[] aTelephoneNumbers
	 * @return AddFiberServiceTNPort2Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	
	public AddFiberServiceTNPort2Return addFiberServiceTNPort2(
			BisContext aContext, 
			String aState, 
			StringOpt aSOACServiceOrderNumber, 
			StringOpt aSOACServiceOrderCorrectionSuffix, 
			StringOpt aOrderNumber, 
			StringOpt aOldServiceProviderId, 
			StringOpt aNewServiceProviderId, 
			EiaDate aOrderDueDate, 
			AddFiberServiceTNPortTelephoneNumberRequest[] aTelephoneNumbers) 
		throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented {
		
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - addFiberServiceTNPort2");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - addFiberServiceTNPort2");
		}
	 	return null;
	}
	
	/**
	 * inquireFiberServiceTNPortStatus
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aOldServiceProviderId
	 * @param String aNewServiceProviderId
	 * @param Time aOrderDueDateTime
	 * @param Time aAppointmentDateTime
	 * @param String[] aTelephoneNumbers
	 * @return InquireFiberServiceTNPortStatusReturn
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	
	public InquireFiberServiceTNPortStatusReturn inquireFiberServiceTNPortStatus(
			BisContext aContext, 
			String aSOACServiceOrderNumber, 
			String aOldServiceProviderId, 
			String aNewServiceProviderId, 
			Time aOrderDueDateTime, 
			Time aAppointmentDateTime, 
			String[] aTelephoneNumbers) 
	throws 
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
	{
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - inquireFiberServiceTNPortStatus");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - inquireFiberServiceTNPortStatus");
		}
	 	return null;
	}
	/**
	 * setSessionContext method comment
	 * 
	 * @param ctx
	 *            javax.ejb.SessionContext
	 * @exception java.rmi.RemoteException
	 *                The exception description.
	 */
	
	public void setSessionContext(javax.ejb.SessionContext ctx)
		throws java.rmi.RemoteException {
		mySessionCtx = ctx;
	}
	
	/**
	 * SubmitFiberServiceTNAssignmentOrder2
	 * 
	 * @param BisContext aContext
	 * @param String aSOACServiceOrderNumber
	 * @param String aSOACServiceOrderCorrectionSuffix
	 * @param String aOrderNumber
	 * @param String aOrderActionType
	 * @param StringOpt aSubActionType
	 * @param BooleanOpt aCompletionIndicator
	 * @param Location aServiceLocation
	 * @param EiaDate aOriginalDueDate
	 * @param EiaDateOpt aSubsequentDueDate
	 * @param EiaDate aApplicationDate
	 * @param BooleanOpt aResendFlag
	 * @param StringOpt aWireCenter
	 * @param StringOpt aRateCenter
	 * @param TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SubmitFiberServiceTNAssignmentOrder2Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	public SubmitFiberServiceTNAssignmentOrder2Return submitFiberServiceTNAssignmentOrder2 (
    	    BisContext aContext,
    	    String aSOACServiceOrderNumber,
    	    String aSOACServiceOrderCorrectionSuffix,
    	    String aOrderNumber,
    	    String aOrderActionType, 
    	    StringOpt aSubActionType, 
    	    BooleanOpt aCompletionIndicator,
    	    Location aServiceLocation,
    	    EiaDate aOriginalDueDate,
    	    EiaDateOpt aSubsequentDueDate,
    	    EiaDate aApplicationDate,
    	    BooleanOpt aResendFlag,
    	    StringOpt aWireCenter,
    	    StringOpt aRateCenter,
    	    BooleanOpt aSimplePortIndicator,
    	    TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,      
    	    ObjectPropertySeqOpt aProperties)
	throws
    	 	BusinessViolation,
    	    ObjectNotFound,
    	    InvalidData,
    	    AccessDenied,
    	    SystemFailure,
    	    DataNotFound,
    	    NotImplemented
    {
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - submitFiberServiceTNAssignmentOrder2");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - submitFiberServiceTNAssignmentOrder2");
		}
	 	return null;
		}
	/**
	 * SendFacilityAssignmentOrder4
	 * 
	 * @param BisContext aContext,
	 * @param String aSOACServiceOrderNumber,
	 * @param String aSOACServiceOrderCorrectionSuffix,
	 * @param String aNetworkType,
	 * @param StringOpt aOldNetworkType,
	 * @param String aOrderActionId,
	 * @param String aOrderNumber,
	 * @param String aOrderActionType,
	 * @param BooleanOpt aCompletionIndicator,
	 * @param StringOpt aSubActionType,
	 * @param String aCircuitId,
	 * @param StringOpt aSecondaryCircuitId,
	 * @param Location aServiceLocation,
	 * @param EiaDate aOriginalDueDate,
	 * @param EiaDateOpt aSubsequentDueDate,
	 * @param EiaDate aApplicationDate,
	 * @param StringOpt aRelatedCircuitID, 
	 * @param StringOpt aSecondaryRelatedCircuitID,
	 * @param StringOpt aRelatedServiceOrderNumber,
	 * @param BooleanOpt aLineShareDisconnectFlag,
	 * @param String aClassOfService,
	 * @param BooleanOpt aResendFlag,
	 * @param CompositeObjectKey aBillingAccountNumber,
	 * @param BooleanOpt aInterceptRedirectIndicator,
	 * @param StringOpt aDryloopRelatedCircuitId,
	 * @param StringOpt aSecondaryDryloopRelatedCircuitId,
	 * @param StringSeqOpt aDSLDisconnectCircuitIds,
	 * @param StringOpt aExceptionRoutingIndicator,
	 * @param ObjectPropertySeqOpt aProperties
	 * @return SendFacilityAssignmentOrder4Return
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
	 */
	
	public SendFacilityAssignmentOrder4Return sendFacilityAssignmentOrder4(
			BisContext aContext,
			String aSOACServiceOrderNumber,
		    String aSOACServiceOrderCorrectionSuffix,
		    String aNetworkType,
		    StringOpt aOldNetworkType,
		    String aOrderActionId,
		    String aOrderNumber,
		    String aOrderActionType,
		    BooleanOpt aCompletionIndicator,
		    StringOpt aSubActionType,
		    String aCircuitId,
		    StringOpt aSecondaryCircuitId,
		    Location aServiceLocation,
		    EiaDate aOriginalDueDate,
		    EiaDateOpt aSubsequentDueDate,
		    EiaDate aApplicationDate,
		    StringOpt aRelatedCircuitID, 
		    StringOpt aSecondaryRelatedCircuitID,
		    StringOpt aRelatedServiceOrderNumber,
		    BooleanOpt aLineShareDisconnectFlag,
		    String aClassOfService,
		    BooleanOpt aResendFlag,
		    CompositeObjectKey aBillingAccountNumber,
		    BooleanOpt aInterceptRedirectIndicator,
		    StringOpt aDryloopRelatedCircuitId,
		    StringOpt aSecondaryDryloopRelatedCircuitId,
		    StringSeqOpt aDSLDisconnectCircuitIds,
		    StringOpt aExceptionRoutingIndicator,
		    ObjectPropertySeqOpt aProperties) 	
	throws 			
			BusinessViolation, 
			ObjectNotFound, 
			InvalidData, 
			AccessDenied, 
			SystemFailure, 
			DataNotFound, 
			NotImplemented 
		
	{
		setCallerContext( aContext ) ;
		
		log(LOG_ENTRY, "RM Facade - sendFacilityAssignmentOrder4");
		
	    try 
	    {
			throwException( ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED, 
							"The requested RM operation has not been implemented.", "RM", Severity.UnRecoverable ) ; 
		} 
	    finally 
	    {
			log(LOG_EXIT, "RM Facade - sendFacilityAssignmentOrder4");
		}
	 	return null;
	}
	
	
	/**
	 * PublishValidateFacilityForProvisioningNotification2
	 */
	public publishValidateFacilityForProvisioningNotification2Return publishValidateFacilityForProvisioningNotification2() {
		log(LOG_ENTRY, "RM Facade - publishValidateFacilityForProvisioningNotification2");
		log(LOG_INFO_LEVEL_1, "Method Not Implemented");
		log(LOG_EXIT, "RM Facade - publishValidateFacilityForProvisioningNotification2");
		return null;
	}
}
