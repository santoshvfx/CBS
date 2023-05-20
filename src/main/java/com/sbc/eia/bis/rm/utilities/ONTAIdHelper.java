//$Id: ONTAIdHelper.java,v 1.1 2006/09/29 16:50:16 rd2842 Exp $
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
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 09/21/2006  Rene Duka             Creation.

package com.sbc.eia.bis.rm.utilities;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author rd2842
 *
 */
public class ONTAIdHelper {
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String aOLTPort = null;
    private String aONTIndex = null;

    private String aONTAIdPrefix = null;
    private String aRack = null;
    private String aShelf = null;
    private String aSlot = null;
    private String aPort = null;
    private String aIndex = null;

    static final String DASH = "-";
        
    /**
     * Constructor for ONTAIdHelper.
     */
    public ONTAIdHelper(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility; 
    }

    /**
     * Method: parse
     * @param StringOpt aONTAId
     */
    public void parse(StringOpt aONTAId)   {
        
        String myMethodName = "ONTAIdHelper: parse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        // Derive OLT_Port and ONT Index from ONT AID.
        // ONT AID format : xxx-Rack-Shelf-Slot-Port-Index. 
        // If ONT AID is ONT-1-1-1-1-9, 
        //      - the OLT Port is 1-1-1-1
        //      - the ONT Index is 9
        // If ONT AID is ONTVDSL-2-1-1-1-8,
        //      - the OLT Port is 2-1-1-1
        //      - the ONT Index is 8
        if (!OptHelper.isStringOptEmpty(aONTAId)) {
            int aTokenCounterIdx = 0;
            try {
                StringTokenizer st = new StringTokenizer(aONTAId.theValue(), "-");
                // Prefix (ONT, ONTVDSL, etc)
                if (st.hasMoreElements())
                    aONTAIdPrefix = st.nextToken().trim();
                // Rack
                if (st.hasMoreElements())
                    aRack = st.nextToken().trim();
                // Shelf
                if (st.hasMoreElements())
                    aShelf = st.nextToken().trim();
                // Slot
                if (st.hasMoreElements())
                    aSlot = st.nextToken().trim();
                // Port    
                if (st.hasMoreElements())
                    aPort = st.nextToken().trim();
                // Index
                if (st.hasMoreElements())
                    aIndex = st.nextToken().trim();
            } 
            catch (Exception e) { 
                aUtility.log(LogEventId.EXCEPTION, e.getMessage());
            }
        }
        
        // format OLT Port
        aOLTPort = aRack + DASH + aShelf + DASH + aSlot + DASH + aPort;
        
        // format ONT Index
        aONTIndex = aIndex;
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName); 
    }

    /**
     * @return aOLTPort
     */
    public String getOLTPort()  {
        return aOLTPort;
    }
    
    /**
     * @return aONTIndex
     */
    public String getONTIndex()    {
        return aONTIndex;
    }
}
