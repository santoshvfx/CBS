//$Id: INQOSPRequestHelper.java,v 1.2 2008/10/28 16:41:07 lg4542 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 01/29/2008   Shyamali Banerjee    Creation.
//# 10/28/2008   Lira Galsim          PR23245380: Fixed the concatenation of house number prefix, house number, and house number suffix for the LFACS's BAD field.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.ObjectFactory;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.RECType;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.ACLTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.ADDRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.BADRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.CTLTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.RECTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.SUPLTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerLFACS;
import com.sbc.eia.idl.lim_types.Address;


/**
 * Class      : INQOSPRequestHelper
 * Description: Helper used for handling the request of the NINQ contract of FACSRCAccess.  
 */
public class INQOSPRequestHelper 
{
    protected NINQImpl aNINQRequest;
    protected RECTypeImpl aRECRequest; 
    protected CTLTypeImpl aCTLRequest;
    protected ACLTypeImpl aACLRequest;
    protected ADDRTypeImpl aADDRRequest;
    protected BADRTypeImpl aBADRRequest;
    protected SUPLTypeImpl aSUPLRequest;
               
    /**
     * Constructor: INQOSPRequestHelper
     * 
     * @author Shyamali Banerjee
     */
    public INQOSPRequestHelper()
    {
        // initialize request
        aNINQRequest = new NINQImpl();
        aRECRequest = new RECTypeImpl();
        aCTLRequest = new CTLTypeImpl();
        aACLRequest = new ACLTypeImpl();
        aADDRRequest = new ADDRTypeImpl();
        aBADRRequest = new BADRTypeImpl();
        aSUPLRequest = new SUPLTypeImpl();

        aBADRRequest.setBAD("");
        aBADRRequest.setSTR("");
        aBADRRequest.setCNA("");
        aBADRRequest.setAHN("");
        aBADRRequest.setSTN("");
        aSUPLRequest.setSID("");
        aSUPLRequest.setUID("");
        aSUPLRequest.setEID("");
        aSUPLRequest.setSTYP("");
        aSUPLRequest.setUTYP("");
        aSUPLRequest.setETYP("");
        aCTLRequest.setADDROPT("");
        aCTLRequest.setCFOPT("");
        aCTLRequest.setDSPOPT("");
        aCTLRequest.setEMPID("");
        aCTLRequest.setLMUOPT("");
        aCTLRequest.setLQOPT("");
        aCTLRequest.setLSPDOPT("");
        aCTLRequest.setNPUBFLG("");
        aCTLRequest.setPATHOPT("");
        aCTLRequest.setPNDOPT("");
        aCTLRequest.setREGOPT("");
        aCTLRequest.setRETURNALL("");
        aCTLRequest.setTNOPT("");
        aCTLRequest.setWKGOPT("");
        aCTLRequest.setLSPDOPT("");
        aACLRequest.setACCT("");
        aACLRequest.setADSR("");
        aACLRequest.setCKID("");
        aACLRequest.setDATE("");
        aACLRequest.setDPLOC("");
        aACLRequest.setMKSEG("");
        aACLRequest.setTID("");
    } 
         
    /**
     * Constructor: INQOSPRequestHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Shyamali Banerjee
     */
    public NINQImpl INQOSPRequestBuilder(Utility aUtility, Hashtable aProperties, Address aFacilityAddress)
    {
        //this();
        AddressHandlerLFACS aLFACSAddress = null;
        try
        {
            //set LSPDOPT
            aCTLRequest.setLSPDOPT("Y");
            // use LFACS Address Handler from LIM BIS
            aLFACSAddress = new AddressHandlerLFACS(aFacilityAddress);
            // set BAD
            // Concatenate: HouseNumberPrefix + " " + HouseNumber + "-" + HouseNumberSuffix
            aBADRRequest.setBAD(aLFACSAddress.getStreetNumber());
            // set STR
            // Concatenate: StreetDirection + StreetName + StreetThoroughfare +
            // StreetSuffix
            // Note that the concatenation is being done in the getStreetName()
            // method.
            aBADRRequest.setSTR(aLFACSAddress.getStreetName());
            // set CNA : City
            aBADRRequest.setCNA(aLFACSAddress.getCity());
            // set UID : Unit Value
            aSUPLRequest.setUID(aLFACSAddress.getUnitValue());
            // set EID : Elevation Value
            aSUPLRequest.setEID(aLFACSAddress.getLevelValue());
            // set SID : Structure Value
            aSUPLRequest.setSID(aLFACSAddress.getStructValue());
            // set STN : State
            aBADRRequest.setSTN(aLFACSAddress.getState());

            aADDRRequest.setBADR(aBADRRequest);
            aADDRRequest.setSUPL(aSUPLRequest);
            aACLRequest.setADDR(aADDRRequest);

            RECType rec = new ObjectFactory().createRECType();
            rec.setCTL(aCTLRequest);
            rec.getACL().add(aACLRequest);

            aNINQRequest.getREC().add(rec);
        }
        catch (AddressHandlerException ahe)
        {
            String aExceptionMessage = "Error in LFACS Address Handler" + " - " + ahe.getMessage() + " - "
                    + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
        catch (Exception e)
        {
            String aExceptionMessage = "Error in building REQTypeImpl" + " - " + e.getMessage() + " - "
                    + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
        return aNINQRequest;
    }
}
