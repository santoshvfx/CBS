// $Id: TNSourcePool.java,v 1.9 2008/07/29 16:48:09 ds4987 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 06/12/2006  Rene Duka             Creation.
//# 06/23/2006  Rene Duka             LS R3 enhancements. Code WT changes.
//# 07/18/2006  Rene Duka             LS R3 enhancements. Integration Test changes.
//# 07/25/2006  Rene Duka             LS R3 enhancements. Implement caching.
//# 07/27/2006  Rene Duka             DR 165393. Remove throwing of exception for rules not found.
//# 01/25/2007  Jon Costa             PR 19319197: additional logging output per EMAS request.
//# 01/29/2007  Rene Duka             PR 19334305: Fixed exception as a result of HIPCS Update Notifier being null.
//# 02/06/2007  Rene Duka             DR 174665: Convert the Requester ID to Requester Category mappings into property file.
//# 05/15/2007  Rene Duka             CR 13804: Check TN and old TN if actionInd is M.
//# 07/20/2007  Doris sunga			  CR 19539: Added method to check if an order has HIPCS TN

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.CvoipOrderRow;
import com.sbc.eia.bis.rm.database.CvoipRulesRow;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_ls_types.TNSourcePoolValues;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrder;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPair;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Driver for the SWITCH Update process of sendTNAssignmentOrder (sTAO) transaction
 * @author: Rene Duka
 */
public class TNSourcePool
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private boolean aOrderExist = false;
    private boolean aTNExist = false;
    private boolean aSWITCHUpdate = false;
    private String aSWITCHActionType = null;

    private SWITCHServer aSWITCHServer = null;
    
    /**
     * Constructor for TNSourcePool
     * @param Hashtable properties
     */
    public TNSourcePool(Hashtable properties) {
        aProperties = properties;
    }

    /**
     * Constructor for TNSourcePool
     * @param Utility utility
     * @param Hashtable properties
     */
    public TNSourcePool(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Method: updatePortingInfo
     * @param BisContext                     aContext
     * @param String                         aSOACServiceOrderNumber
     * @param EiaDate                        aOriginalDueDate
     * @param TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs
     * @param CvoipOrderRow[]                aCvoipOrderRow
     * @param CvoipRulesRow[]                aCvoipRulesRow
     * @return boolean
     */
    public boolean updatePortingInfo(
        BisContext aContext, 
        String aSOACServiceOrderNumber,
        EiaDate aOriginalDueDate,
        TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
        CvoipOrderRow[] aCvoipOrderRow,
        CvoipRulesRow[] aCvoipRulesRow)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "TNSourcePool: updatePortingInfo()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        boolean aUpdateSWITCHOk = true;

        // Loop through all the TNs to check the HIPCS TN indicator
        // If HIPCS TN, 
        //      - Check if order and TN is in CVOIP Order Table by looking at the aCvoipOrderRow
        //      - Set Order Exist and TN Exist variables
        //      - Set SWITCHUpdate and SWITCHActionType by looking at the aCvoipRulesRow
        //      - Format TN and Requester Id
        //      - Call updateSWITCH
        //      - Update the aError and aTNSourcePoolUpdateNotifier in aTelephoneNumberOrder
        //      - Return 
        
        if (!OptHelper.isTelephoneNumberOrderPairSeqOptEmpty(aTelephoneNumberOrderPairs)) {
            TelephoneNumberOrderPair[] aTelephoneNumberOrderPair = aTelephoneNumberOrderPairs.theValue();
            for (int i=0; i < aTelephoneNumberOrderPair.length; i++) {
                // format aTN
                String aTN = null;
                if (!OptHelper.isStringOptEmpty(aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTelephoneNumber)) {
                    aTN = aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTelephoneNumber.theValue();
                }
                aUtility.log(LogEventId.INFO_LEVEL_1, "Processing TN <" + aTN + ">.");
                
                // format aOldTN
                String aOldTN = null;
                if (!OptHelper.isTelephoneNumberOrderOptEmpty(aTelephoneNumberOrderPair[i].aOldTelephoneNumberOrder)) {
                    TelephoneNumberOrder aOldTNOrder = aTelephoneNumberOrderPair[i].aOldTelephoneNumberOrder.theValue();
                    if (!OptHelper.isStringOptEmpty(aOldTNOrder.aTelephoneNumber)) {
                        aOldTN = aOldTNOrder.aTelephoneNumber.theValue();
                    }
                }
                aUtility.log(LogEventId.INFO_LEVEL_1, "Processing old TN <" + aOldTN + ">.");

                if (!OptHelper.isStringOptEmpty(aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool)) {
                    String aTNSourcePool = aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool.theValue();
                    // check if HIPCS TN
                    if (aTNSourcePool.equalsIgnoreCase(TNSourcePoolValues.HIPCS_TN)) {

                        // format RequesterId
                        String aRequesterId = null;
                        if (!OptHelper.isStringOptEmpty(aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aRequesterId)) {
                            aRequesterId = aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aRequesterId.theValue();
                        }
    
                        // evaluate CVOIP Order Table rows
                        evaluateCvoipOrderRows(aCvoipOrderRow, 
                                               aTN);
                        
                        // evaluate CVOIP Rule Table rows
                        aUpdateSWITCHOk = evaluateCvoipRuleRows(aCvoipRulesRow,
                                                                aTelephoneNumberOrderPair[i].aTelephoneNumberOrder,
                                                                aTN,
                                                                aOldTN);

                        // if SWITCH Update is true, process TN SWITCH    
                        if (aSWITCHUpdate) {
                            // initialize return object
                            SWITCHServerReturn aSWITCHServerReturn = null;

                            // update SWITCH
                            if (aSWITCHServer == null) {                            
                                aSWITCHServer = new SWITCHServer(aUtility, aProperties);
                            }

                            aSWITCHServerReturn = aSWITCHServer.updateSWITCH(aContext,
                                                                             aSOACServiceOrderNumber,
                                                                             aOriginalDueDate,
                                                                             aSWITCHActionType,
                                                                             aTN,
                                                                             aRequesterId);
                                                                                                
                            // update TelephoneNumberOrderPair if there is an error
                            if (!aSWITCHServerReturn.getTNSourcePoolUpdateNotifier()) {
                                aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier.theValue(aSWITCHServerReturn.getTNSourcePoolUpdateNotifier());
                                aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aError.theValue(aSWITCHServerReturn.getError());
                                aUpdateSWITCHOk = false;
                            }
                            else {
                                aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePoolUpdateNotifier.theValue(true);
                                aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aError.__default();
                            }
                            
                            // exit loop if NextTN flag is false
                            if (!aSWITCHServerReturn.getNextTN()) {
                                break;
                            }
                        }
                        else {
                            aUtility.log(LogEventId.INFO_LEVEL_1, "Bypassing SWITCH for TN <" + aTN
                                                                    + ">. HIPCS SWITCH UPDATE Flag in CVOIP_RULES set to <" + aSWITCHUpdate + ">.");
                        }
                    }
                    else {
                        aUtility.log(LogEventId.INFO_LEVEL_1, "TNSourcePool <" + aTNSourcePool + "> for TN <" + aTN + ">. Value NOT HIPCS!");
                    }
                }
                else {
                    aUtility.log(LogEventId.INFO_LEVEL_1, "TNSourcePool <null> for TN <" + aTN + ">.");
                }
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aUpdateSWITCHOk;
    }

    /**
     * Method: checkHIPCSTNExist
     * This method Loop through TNs to check for HIPCS TN
     * If there is one HIPCS TN , return TRUE
     * @param BisContext                     aContext
     * @param TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs
     * @return boolean
     */
    public boolean checkHIPCSTNExist(
    		BisContext aContext,
    		TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs)
    		throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound, 
    		DataNotFound    
    {
        String myMethodName = "TNSourcePool: checkHIPCSTNExist()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        boolean aHIPCSTNExist = false;
        
        // Loop through TNs to check for HIPCS TN
        // If there is one HIPCS TN , return TRUE      

        if (!OptHelper.isTelephoneNumberOrderPairSeqOptEmpty(aTelephoneNumberOrderPairs)) {
            TelephoneNumberOrderPair[] aTelephoneNumberOrderPair = aTelephoneNumberOrderPairs.theValue();
            for (int i=0; i < aTelephoneNumberOrderPair.length; i++) {
                if (!OptHelper.isStringOptEmpty(aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool)) {
                    String aTNSourcePool = aTelephoneNumberOrderPair[i].aTelephoneNumberOrder.aTNSourcePool.theValue();
                    // check if HIPCS TN
                    if (aTNSourcePool.equalsIgnoreCase(TNSourcePoolValues.HIPCS_TN)) {   
                    	aHIPCSTNExist = true;
                    	 break;
                    }
                }    
            }    
         }
    	return aHIPCSTNExist;
    }
        
    /**
     * Method: evaluateCvoipOrderRows
     * @param CvoipOrderRow[]  aCvoipOrderRow
     * @param String           aTN
     */
    private void evaluateCvoipOrderRows(
        CvoipOrderRow[] aCvoipOrderRow,
        String aTN)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "TNSourcePool: evaluateCvoipOrderRows()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // initialize instance variables
        aOrderExist = false;
        aTNExist = false;
        
        // If null, it means no record found in  CVOIP_ORDER table
        if (aCvoipOrderRow == null)
            return;
            
        // Loop through the rows in aCvoipOrderRow[]
        if (aCvoipOrderRow.length > 0) {
            aOrderExist = true;
            for (int i = 0; i < aCvoipOrderRow.length; i++) {
                if (aCvoipOrderRow[i].getTN().equalsIgnoreCase(aTN)) {
                    aTNExist = true;    
                }
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return;
    }

    /**
     * Method: evaluateCvoipRuleRows
     * @param CvoipRulesRow[]       aCvoipRulesRow
     * @param TelephoneNumberOrder  aTelephoneNumber,
     * @param String                aTN,
     * @param String                aOldTN
     */
    private boolean evaluateCvoipRuleRows(
        CvoipRulesRow[] aCvoipRulesRow,
        TelephoneNumberOrder aTelephoneNumber,
        String aTN,
        String aOldTN)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "TNSourcePool: evaluateCvoipRuleRows()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // Use the following to format aSWITCHUpdate and aSWITCHActionType:
        //      - ActivityIndicator and ActionInd (from aTelephoneNumber)
        //      - aOrderExist
        //      - aTNExist

        // initialize instance variables
        aSWITCHUpdate = false;
        aSWITCHActionType = null;

        // format aActionInd
        String aActionInd = aTelephoneNumber.aActionInd;

        // format aActivityIndicator
        String aActivityIndicator = null;
        if (!OptHelper.isStringOptEmpty(aTelephoneNumber.aActivityIndicator)) {
            aActivityIndicator = aTelephoneNumber.aActivityIndicator.theValue();
        }
        
        // compare the old and new TN
        if (aActionInd != null && aActionInd.equalsIgnoreCase("M") && aTN.equalsIgnoreCase(aOldTN)) {
            aActivityIndicator = "S";
        }

        // Loop through the rows in aCvoipRulesRow[]
        boolean aFoundRule = false;

        StringBuffer aRule = new StringBuffer();
        if (aCvoipRulesRow.length > 0) {
            aRule.append("< ORDER_ACTION_TYPE: ");
            aRule.append(aCvoipRulesRow[0].getORDER_ACTION_TYPE());
            aRule.append(" > < ORDER_ACTION_SUBTYPE: ");
            aRule.append(aCvoipRulesRow[0].getORDER_ACTION_SUBTYPE());
            aRule.append(" > < ACTION_IND: ");
            aRule.append(aActionInd);
            aRule.append("> < ACTIVITY_IND: ");
            aRule.append(aActivityIndicator);
            aRule.append("> < ORDER_EXISTS: ");
            aRule.append(aOrderExist);
            aRule.append(" > < TN_EXISTS: ");
            aRule.append(aTNExist);
            aRule.append(" > ");
            
            aUtility.log(LogEventId.INFO_LEVEL_1, "Finding rule: " + aRule);                        

			for (int i = 0; i < aCvoipRulesRow.length; i++)
			{
				if ((aCvoipRulesRow[i].getORDER_EXISTS().booleanValue() == aOrderExist)
					&& (aCvoipRulesRow[i].getTN_EXISTS().booleanValue() == aTNExist)
					&& (((aCvoipRulesRow[i].getACTION_IND() == null || aCvoipRulesRow[i].getACTION_IND().equals(""))
						&& (aActionInd == null || aActionInd.equals("")))
						|| aCvoipRulesRow[i].getACTION_IND().equalsIgnoreCase(aActionInd))
					&& (((aCvoipRulesRow[i].getACTIVITY_IND() == null || aCvoipRulesRow[i].getACTIVITY_IND().equals(""))
						&& (aActivityIndicator == null || aActivityIndicator.equals("")))
						|| aCvoipRulesRow[i].getACTIVITY_IND().equalsIgnoreCase(aActivityIndicator)))
				{
					aSWITCHUpdate = aCvoipRulesRow[i].getHIPCS_SWITCH_UPD().booleanValue();
					aSWITCHActionType = aCvoipRulesRow[i].getSWITCH_ACTION_IND();
					aFoundRule = true;
					break;
				}
			}
        }
        
        if (!aFoundRule) {
            String aErrDesc = "Rule NOT found: " + aRule;
            aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);
            aUtility.log(LogEventId.INFO_LEVEL_1, "> Unable to process: " + aTelephoneNumber.aTelephoneNumber.theValue());

            // update aTelephoneNumber
            ExceptionData aExceptionData = new ExceptionData();
            aExceptionData.aCode = ExceptionCode.ERR_RM_NO_MATCHING_RULE;
            aExceptionData.aDescription = aErrDesc;
            aExceptionData.aOrigination = (StringOpt) IDLUtil.toOpt(aProperties.get("BIS_NAME").toString());
            aExceptionData.aSeverity = (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class), Severity.Recoverable);

            aTelephoneNumber.aTNSourcePoolUpdateNotifier.theValue(false);
            aTelephoneNumber.aError = (ExceptionDataOpt) IDLUtil.toOpt((ExceptionDataOpt.class), aExceptionData);
        }
        else {
            aTelephoneNumber.aTNSourcePoolUpdateNotifier.theValue(true);
            aTelephoneNumber.aError.__default();
        }
        
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aFoundRule;
    }
}
