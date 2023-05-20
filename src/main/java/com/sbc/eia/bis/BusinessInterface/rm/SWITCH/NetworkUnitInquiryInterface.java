// $Id: NetworkUnitInquiryInterface.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.ArrayList;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public interface NetworkUnitInquiryInterface extends com.sbc.eia.bis.BusinessInterface.Business {
	java.lang.String NetworkUnitInquiryInterfaceName = "NetworkUnitInquiryInterfaceName";

public String getACNA(String aStringDesc)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound;
public String getASMStatus(String aStringDesc)
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound;
public LineDescription getLineDescription(BisContext aContext,String aCircuit, ArrayList aWireCenters) 
	throws
		InvalidData,
		AccessDenied,
		BusinessViolation,
		SystemFailure,
		NotImplemented,
		ObjectNotFound,
		DataNotFound;
public boolean getSWITCHIDLCInfo ( BisContext aContext,String aCircuit, ArrayList aWireCenters ) 
    throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound;
}
