// $Id: CircuitProvisioningAccess.java,v 1.2 2002/09/29 04:13:00 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.service.circuitprovisioning.exceptions.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Wraps the service side for access to the CircuitProvisioning service.
 * Creation date: (4/16/00 11:36:38 AM)
 * @author: Creighton Malet
 */
public class CircuitProvisioningAccess extends ServiceAccess
{
	public final static String version = "10.0";
	public final static String name = "CIRCUITPROVISIONING";

	public final static int TIRKS_CXRS_QI_NBR 		=  12001;
	public final static int TIRKS_CXRS_QO_NBR 		=  12001;
	public final static int TIRKS_DRI_QI_NBR 		=  13001;
	public final static int TIRKS_DRI_QO_NBR 		=  13001;
	public final static int TIRKS_RDLOC_QI_NBR		=  22001;
	public final static int TIRKS_RDLOC_QO_NBR		=  22001;
	public final static int TIRKS_WA_QI_NBR 		=  27001;
	public final static int TIRKS_WA_QO_NBR 		=  27001;
	public final static int TIRKS_CBLS_QI_NBR		=  32001;
	public final static int TIRKS_CBLS_QO_NBR		=  32001;
	public final static int TIRKS_EQPSC_QI_NBR		=  15001;
	public final static int TIRKS_EQPSC_QO_NBR		=  15001;

	public final static int TIRKS_ERROR_NBR 			=   20;
	public final static int GENERAL_SERVICE_ERROR_NBR 	=  120;
	public final static int NO_SERVICE_AVAIL_ERROR_NBR 	=  220;
	public final static int FATAL_ERROR_NBR 			=  320;
		
	public final static MEventType TIRKS_CXRS_QI = 			new MEventType("TIRKS_CXRS_QI");	// Event  12001
	public final static MEventType TIRKS_CXRS_QO = 			new MEventType("TIRKS_CXRS_QO");	// Event -12001
	public final static MEventType TIRKS_DRI_QI = 			new MEventType("TIRKS_DRI_QI");		// Event  13001
	public final static MEventType TIRKS_DRI_QO = 			new MEventType("TIRKS_DRI_QO");		// Event -13001
	public final static MEventType TIRKS_RDLOC_QI = 		new MEventType("TIRKS_RDLOC_QI");	// Event  22001
	public final static MEventType TIRKS_RDLOC_QO = 		new MEventType("TIRKS_RDLOC_QO");	// Event -22001
	public final static MEventType TIRKS_WA_QI = 			new MEventType("TIRKS_WA_QI");		// Event  27001
	public final static MEventType TIRKS_WA_QO = 			new MEventType("TIRKS_WA_QO");		// Event -27001
	public final static MEventType TIRKS_CBLS_QI = 			new MEventType("TIRKS_CBLS_QI");	// Event  32001
	public final static MEventType TIRKS_CBLS_QO = 			new MEventType("TIRKS_CBLS_QO");	// Event -32001
	public final static MEventType TIRKS_EQPSC_QI = 		new MEventType("TIRKS_EQPSC_QI");	// Event  15001
	public final static MEventType TIRKS_EQPSC_QO = 		new MEventType("TIRKS_EQPSC_QO");	// Event -15001

	public final static MEventType TIRKS_ERROR = 			new MEventType("TIRKS_ERROR");				// Event   20
	public final static MEventType GENERAL_SERVICE_ERROR = 	new MEventType("GENERAL_SERVICE_ERROR");	// Event  120
	public final static MEventType NO_SERVICE_AVAIL_ERROR = new MEventType("NO_SERVICE_AVAIL_ERROR");	// Event  220
	public final static MEventType FATAL_ERROR = 			new MEventType("FATAL_ERROR");				// Event  320
/**
 * Constructor accepting Vicuna configuration file, directory for configuration files and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public CircuitProvisioningAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
