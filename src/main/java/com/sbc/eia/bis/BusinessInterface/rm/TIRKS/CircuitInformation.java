// $Id: CircuitInformation.java,v 1.5 2011/04/07 02:26:18 rs278j Exp $

package com.sbc.eia.bis.BusinessInterface.rm.TIRKS;

import java.util.ArrayList;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;


/**
 * CircuitInformation is the Business Interface which defines circuit functionality.
 * Creation date: (2/19/01 1:01:32 PM)
 * @author: Creighton Malet
 */
public interface CircuitInformation extends com.sbc.eia.bis.BusinessInterface.Business{
	static public final String CircuitInformationInterfaceName = "CircuitInformation";
/**
 * Returns a Cable Assignment from TIRKS.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
 * @param imsRegion java.lang.String
 */
public ArrayList getCBLSInformation(BisContext aContext, CBLSInput input)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Returns a list of Carrier Channel Assignment from TIRKS.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
 * @param imsRegion java.lang.String
 */
public ArrayList getCXRSInformation(BisContext aContext, CircuitId aCircuitId, String imsRegion)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Returns Design Releated Information from TIRKS.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
 * @param imsRegion java.lang.String
 */
public DesignRelatedInformation getDRInformation(BisContext aContext, CircuitId aCircuitId, String imsRegion)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Returns a Cable Assignment from TIRKS.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
 * @param imsRegion java.lang.String
 */
public ArrayList getEQPSCInformation(BisContext aContext, EQPSCInput input)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Returns a Reference Location from TIRKS.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.DesignRelatedInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aCircuitId com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId
 * @param imsRegion java.lang.String
 */
public ArrayList getRDLOCInformation(BisContext aContext, RDLOCInput input)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Returns work authorization information.
 * @return com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WorkAuthorizationInformation[]
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param inArray com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WAInput[]
 */
public WorkAuthorizationInformation[] getWAInformation(BisContext aContext, WAInput[] inArray)
	throws AccessDenied, BusinessViolation, InvalidData,
		NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
}
