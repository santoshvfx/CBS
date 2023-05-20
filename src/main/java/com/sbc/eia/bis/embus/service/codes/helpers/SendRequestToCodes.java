
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of AT&T Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       (C) AT&T Services, Inc 2006.  All Rights Reserved.
//#
//# History :
//# Date        | Author         | Notes
//# ----------------------------------------------------------------------------
//# 12/05/2006   Changchuan Yin        Creation.

package com.sbc.eia.bis.embus.service.codes.helpers;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.embus.service.codes.CodesRCAccessService;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.impl.GetClliCICAvailRequestImpl;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.ResponseMessageImpl;
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

public class SendRequestToCodes
{

	public static final String CODES_EXCEPTION_RULE_FILE_TAG= "EXCEPTION_BUILDER_CODESRC_RULE_FILE";

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
	    GetClliCICAvailRequestImpl request,
		CodesRCAccessService service)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		Object response = null;
		try
		{
			response = service.rmBisRequests(request);
		}
		catch (EncoderException e)
		{ 
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
		
        return response;
    }
}
