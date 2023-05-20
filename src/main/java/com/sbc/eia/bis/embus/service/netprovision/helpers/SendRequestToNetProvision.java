//$Id: SendRequestToNetProvision.java,v 1.5 2005/09/27 22:04:37 jn1936 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date        | Author         | Notes
//# ----------------------------------------------------------------------------
//# 10/14/2004   Jinmin Ni        Creation.
//# 10/27/2004   Jinmin Ni		  Add static final string for netp exception rule file
//# 11/3/2004    Jinmin Ni        Add jms remote logging
//# 01/05/2004   Jinmin Ni        Accommodate the changes made in NetProvisionHelper.java 
//# 04/15/2005   Jinmin Ni	      Copy form netprovision.utities 
//# 06/03/2005   Jinmin Ni        Log remote return before handling exception
//# 07/14/2005   Jinmin Ni        Enhance exception handling
//# 08/07/2005   Jinmin Ni        Passing error message from XnG as aText instead of msg to exceptionBuilder
           
package com.sbc.eia.bis.embus.service.netprovision.helpers;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.embus.service.netprovision.NetProvisionService;
import com.sbc.eia.bis.embus.service.netprovision.access.NetProvisionHelper;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestImpl;
import com.sbc.eia.bis.embus.service.utilities.ExceptionHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class SendRequestToNetProvision
{

	public static final String NET_PROVISION_EXCEPTION_RULE_FILE_TAG= "EXCEPTION_BUILDER_NET_PROVISION_RULE_FILE";

	/**
	 * @param aUtility
	 * @param aContext
	 * @param ruleFile
	 * @param request
	 * @param service
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public static Object sendRequest(
		Utility aUtility,
		BisContext aContext,
		String ruleFile,
		RequestImpl request,
		NetProvisionService service)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
        aUtility.log(
            LogEventId.REMOTE_CALL,
            NetProvisionHelper.NET_PROVISION_SERVICE_NAME,
            NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
            NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
            NetProvisionHelper.NET_PROVISION_REQUEST);	
            			
        Object response = null;
		try
		{
			response = service.rmBisRequests(request);
		}
		catch (EncoderException e)
		{
			aUtility.log(
						LogEventId.REMOTE_RETURN,
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME,
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
						NetProvisionHelper.NET_PROVISION_REQUEST);
			
			//throw rm exception			
			ExceptionHelper.throwException(
				e,
				request.getClass().getPackage().getName(),
				aUtility,
				ruleFile,
				aContext);
		}
		catch (IllegalArgumentException e)
		{
			aUtility.log(
						LogEventId.REMOTE_RETURN,
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME,
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
						NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
						NetProvisionHelper.NET_PROVISION_REQUEST);
						
			//throw rm exception			
			ExceptionHelper.throwException(
				e,
				request.getClass().getPackage().getName(),
				aUtility,
				ruleFile,
				aContext);
		}
		catch(ServiceTimeOutException e)
		{
			//xng  time out exception
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				null,
				null,
				e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				e,
				aUtility,
				null,
				null,
				null);
		}
		catch (Exception e) // any other exception includeing ServiceException
        {
        	
        	aUtility.log(
			LogEventId.REMOTE_RETURN,
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME,
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
			NetProvisionHelper.NET_PROVISION_REQUEST);
			
			//throw NetP exception
            ExceptionBuilder.parseException(
                aContext,
                ruleFile,
                null,
                null,
                e.getMessage(),
                true,
                1,
                null,
                e,
                aUtility,
                null,
                null,
                null);
		}
		aUtility.log(
			LogEventId.REMOTE_RETURN,
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME,
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
			NetProvisionHelper.NET_PROVISION_SERVICE_NAME + request.getSchemaVersion(),
			NetProvisionHelper.NET_PROVISION_REQUEST);

        return response;
    }
}
