//$Id: RtidMsgCodeTable.java,v 1.2 2008/09/02 16:41:49 jo8461 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 08/2008    Jon Costa              Creation.

package com.sbc.eia.bis.rm.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.Table;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.rm_ls_types.Message;

/**
 * @author jc1421
 * 
 */
public class RtidMsgCodeTable extends Table
{
    ArrayList QVList = null;
    private static String sqlSelect = "select CODE, PROCEED_IND, ATTDSL_NBR, "
                                      + "NON_ATTDSL_NBR, CONFLICT_IND, APPLICATION, "
                                      + "DSL_SME_ACCT_IND, FIBER_IND, LFACS_ERROR_IND, "
                                      + "SM_ERROR_IND, DISH_IND, DIALUP_IND, "
                                      + "DSL_MISUSE_IND, UVERSE_IND, BBPLOOP_NF_IND, "
                                      + "INVALID_NTI_IND, LOAD_COIL_IND, TCOMMIT_IND " + "from RTID_MSG_CODE ";

    /**
     * @param aREGION
     * @param aLS_CONDITIONED
     * @param aCLS_SERVICE_CODE
     * @param CLS_MODIFIER
     * @param aProperties
     * @param aLogger
     * @return
     * @throws SQLException
     */
    public RtidMsgCodeRow retrieveRows(Boolean PROCEED_IND,
                                       int ATTDSL_NBR,
                                       int NON_ATTDSL_NBR,
                                       Boolean CONFLICT_IND,
                                       String APPLICATION,
                                       Boolean DSL_SME_ACCT_IND,
                                       Boolean FIBER_IND,
                                       Boolean LFACS_ERROR_IND,
                                       Boolean SM_ERROR_IND,
                                       Boolean DISH_IND,
                                       Boolean DIALUP_IND,
                                       Boolean DSL_MISUSE_IND,
                                       Boolean UVERSE_IND,
                                       Boolean BBPLOOP_NF_IND,
                                       Boolean INVALID_NTI_IND,
                                       Boolean LOAD_COIL_IND,
                                       Boolean TCOMMIT_IND,
                                       Hashtable aProperties,
                                       Logger aLogger) throws SQLException
    {
        String myMethodNameName = "RtidMsgCodeTable:retrieveRow()";
        aLogger.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

        QVList = new ArrayList(); // Each scenario call will append it's SQL query value(s) to this list.
        String SQLstatement = sqlSelect + "where " 
                              + scenario_1(PROCEED_IND)
                              + scenario_2(ATTDSL_NBR, NON_ATTDSL_NBR, CONFLICT_IND)
                              + scenario_3(ATTDSL_NBR, NON_ATTDSL_NBR, CONFLICT_IND, APPLICATION, DSL_SME_ACCT_IND)
                              + scenario_4(NON_ATTDSL_NBR, CONFLICT_IND, APPLICATION, FIBER_IND)
                              + scenario_5(LFACS_ERROR_IND, SM_ERROR_IND) 
                              + scenario_6(DISH_IND)
                              + scenario_7(DIALUP_IND) 
                              + scenario_8(DSL_MISUSE_IND)
                              + scenario_9(LFACS_ERROR_IND, UVERSE_IND) 
                              + scenario_10(LFACS_ERROR_IND, BBPLOOP_NF_IND)
                              + scenario_11(INVALID_NTI_IND) 
                              + scenario_12(BBPLOOP_NF_IND) 
                              + scenario_13(LOAD_COIL_IND)
                              + scenario_14(TCOMMIT_IND);

        SQLstatement = trimTrailingOr(SQLstatement);
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQLstatement: [" + SQLstatement + "]");
        aLogger.log(LogEventId.INFO_LEVEL_2, "SQL values: " + QVList.toString());
        RtidMsgCodeRow rowObj = new RtidMsgCodeRow();
        DBConnHelper.retrieveRow(SQLstatement, setQueryValues(), aProperties, aLogger, rowObj);

        aLogger.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
        return rowObj;
    }

    /**
     * 
     * @param SQLstatement
     * @return
     */
    private String trimTrailingOr(String SQLstatement)
    {
        String groomedString = SQLstatement.trim(); // Eliminate any extraneous beginning/trailing spaces
        int endIndex = groomedString.length() - 1;

        // Each scenario method appends an " or " string to join the next segment 
        // except the last scenario, since we don't know ahead of time how many 
        // scenarios will apply or which scenario will be the last to attach itself 
        // to the where clause, we'll read from the end until we eliminate the trailing " or ".
        while ((groomedString = groomedString.substring(0, endIndex--)).charAt(groomedString.length() - 1) != ')');
        
        return groomedString;
    }

    private String[] setQueryValues()
    {
        String[] queryValues = null;

        if (QVList.size() > 0)
        {
            queryValues = (String[]) QVList.toArray(new String[QVList.size()]);
            for (int i = 0; i < QVList.size(); i++)
            {
                queryValues[i] = (String) QVList.get(i);
            }
        }
        return queryValues;
    }

    // FORMAT of each senario segment:
    // - each segment will be enclosed within parenthesis ().
    // - each segment is joined with an "or", zero to many scenarios could apply.
    // - each segment logic should return only it's data and should not be applicable to other scenarios.
    // - each scenario method should return the segment for it's scenario or an empty string.    
    // - each scenario will append to the QVList (ArrayList) it's SQL look up values in the order listed.
    /**
     * 
     * @param PROCEED_IND
     * @return
     */
    private String scenario_1(Boolean PROCEED_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (PROCEED_IND != null && PROCEED_IND.booleanValue())
        {
            whereClauseSegment = "(upper(PROCEED_IND) = ?) or ";
            QVList.add(PROCEED_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param ATTDSL_NBR
     * @param NON_ATTDSL_NBR
     * @param CONFLICT_IND
     * @return
     */
    private String scenario_2(int ATTDSL_NBR, int NON_ATTDSL_NBR, Boolean CONFLICT_IND)
    {
        String whereClauseSegment = "";

        if (ATTDSL_NBR >= 0 && NON_ATTDSL_NBR >= 0 && CONFLICT_IND != null)
        {
            whereClauseSegment = "(ATTDSL_NBR = ? and NON_ATTDSL_NBR = ? and upper(CONFLICT_IND) = ?) or ";
            QVList.add(Integer.toString(ATTDSL_NBR));
            QVList.add(Integer.toString(NON_ATTDSL_NBR));
            QVList.add(CONFLICT_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param ATTDSL_NBR
     * @param NON_ATTDSL_NBR
     * @param CONFLICT_IND
     * @param APPLICATION
     * @param DSL_SME_ACCT_IND
     * @return
     */
    private String scenario_3(int ATTDSL_NBR,
                              int NON_ATTDSL_NBR,
                              Boolean CONFLICT_IND,
                              String APPLICATION,
                              Boolean DSL_SME_ACCT_IND)
    {
        String whereClauseSegment = "";

        if (NON_ATTDSL_NBR >= 0 && CONFLICT_IND != null && APPLICATION != null && APPLICATION.length() > 0)
        {
            String DslOnSameAcct = ") or ";     // If null(optional), it does not apply to this scenario
            
            // Note: DB RTID_MSG_CODE Table search logic considers 2 = multiple DSL(s)
            QVList.add(Integer.toString(ATTDSL_NBR > 1 ? 2 : ATTDSL_NBR));
            QVList.add(Integer.toString(NON_ATTDSL_NBR));
            QVList.add(CONFLICT_IND.toString().toUpperCase());
            QVList.add(APPLICATION.toUpperCase());
            if (DSL_SME_ACCT_IND != null)
            {
                DslOnSameAcct = " and upper(DSL_SME_ACCT_IND) = ?) or ";
                QVList.add(DSL_SME_ACCT_IND.toString().toUpperCase());
            }
            whereClauseSegment = "(ATTDSL_NBR = ? and NON_ATTDSL_NBR = ? and upper(CONFLICT_IND) = ? and upper(APPLICATION) = ?"
                                 + DslOnSameAcct;
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param NON_ATTDSL_NBR
     * @param CONFLICT_IND
     * @param APPLICATION
     * @param FIBER_IND
     * @return
     */
    private String scenario_4(int NON_ATTDSL_NBR, Boolean CONFLICT_IND, String APPLICATION, Boolean FIBER_IND)
    {
        String whereClauseSegment = "";

        if (NON_ATTDSL_NBR >= 0 && CONFLICT_IND != null && APPLICATION != null && APPLICATION.length() > 0
            && FIBER_IND != null)
        {
            whereClauseSegment = "((upper(CONFLICT_IND) = ? or NON_ATTDSL_NBR = ?) and upper(APPLICATION) = ? and upper(FIBER_IND) = ?) or ";
            QVList.add(CONFLICT_IND.toString().toUpperCase());
            QVList.add(Integer.toString(NON_ATTDSL_NBR));
            QVList.add(APPLICATION.toUpperCase());
            QVList.add(FIBER_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param LFACS_ERROR_IND
     * @param SM_ERROR_IND
     * @return
     */
    private String scenario_5(Boolean LFACS_ERROR_IND, Boolean SM_ERROR_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (LFACS_ERROR_IND != null && LFACS_ERROR_IND.booleanValue())
        {
            whereClauseSegment = "(LFACS_ERROR_IND = ?";
            QVList.add(LFACS_ERROR_IND.toString().toUpperCase());
            if (SM_ERROR_IND != null && SM_ERROR_IND.booleanValue())
            {
                whereClauseSegment = whereClauseSegment + " or SM_ERROR_IND = ?";
                QVList.add(SM_ERROR_IND.toString().toUpperCase());
            }
        }
        else
        {
            if (SM_ERROR_IND != null && SM_ERROR_IND.booleanValue())
            {
                whereClauseSegment = "(SM_ERROR_IND = ?";
                QVList.add(SM_ERROR_IND.toString().toUpperCase());
            }
        }
        return whereClauseSegment == "" ? whereClauseSegment : (whereClauseSegment + ") or ");
    }

    /**
     * 
     * @param DISH_IND
     * @return
     */
    private String scenario_6(Boolean DISH_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (DISH_IND != null && DISH_IND.booleanValue())
        {
            whereClauseSegment = "(DISH_IND = ?) or ";
            QVList.add(DISH_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param DIALUP_IND
     * @return
     */
    private String scenario_7(Boolean DIALUP_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (DIALUP_IND != null && DIALUP_IND.booleanValue())
        {
            whereClauseSegment = "(DIALUP_IND = ?) or ";
            QVList.add(DIALUP_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param DSL_MISUSE_IND
     * @return
     */
    private String scenario_8(Boolean DSL_MISUSE_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (DSL_MISUSE_IND != null && DSL_MISUSE_IND.booleanValue())
        {
            whereClauseSegment = "(DSL_MISUSE_IND = ?) or ";
            QVList.add(DSL_MISUSE_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param LFACS_ERROR_IND
     * @param UVERSE_IND
     * @return
     */
    private String scenario_9(Boolean LFACS_ERROR_IND, Boolean UVERSE_IND)
    {
        String whereClauseSegment = "";

        if (LFACS_ERROR_IND != null && UVERSE_IND != null)
        {
            whereClauseSegment = "(LFACS_ERROR_IND = ? and UVERSE_IND = ?) or ";
            QVList.add(LFACS_ERROR_IND.toString().toUpperCase());
            QVList.add(UVERSE_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param LFACS_ERROR_IND
     * @param BBPLOOP_NF_IND
     * @return
     */
    private String scenario_10(Boolean LFACS_ERROR_IND, Boolean BBPLOOP_NF_IND)
    {
        String whereClauseSegment = "";

        if (LFACS_ERROR_IND != null && BBPLOOP_NF_IND != null)
        {
            whereClauseSegment = "(LFACS_ERROR_IND = ? and BBPLOOP_NF_IND = ?) or ";
            QVList.add(LFACS_ERROR_IND.toString().toUpperCase());
            QVList.add(BBPLOOP_NF_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param INVALID_NTI_IND
     * @return
     */
    private String scenario_11(Boolean INVALID_NTI_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (INVALID_NTI_IND != null && INVALID_NTI_IND.booleanValue())
        {
            whereClauseSegment = "(INVALID_NTI_IND = ?) or ";
            QVList.add(INVALID_NTI_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param BBPLOOP_NF_IND
     * @return
     */
    private String scenario_12(Boolean BBPLOOP_NF_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (BBPLOOP_NF_IND != null && BBPLOOP_NF_IND.booleanValue())
        {
            whereClauseSegment = "(BBPLOOP_NF_IND = ?) or ";
            QVList.add(BBPLOOP_NF_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param LOAD_COIL_IND
     * @return
     */
    private String scenario_13(Boolean LOAD_COIL_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (LOAD_COIL_IND != null && LOAD_COIL_IND.booleanValue())
        {
            whereClauseSegment = "(LOAD_COIL_IND = ?) or ";
            QVList.add(LOAD_COIL_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }

    /**
     * 
     * @param TCOMMIT_IND
     * @return
     */
    private String scenario_14(Boolean TCOMMIT_IND)
    {
        String whereClauseSegment = "";
        // Note: Only a 'TRUE' value can trigger this scenario

        if (TCOMMIT_IND != null && TCOMMIT_IND.booleanValue())
        {
            whereClauseSegment = "(TCOMMIT_IND = ?)"; // Currently last called scenario, no " or " appended.
            QVList.add(TCOMMIT_IND.toString().toUpperCase());
        }
        return whereClauseSegment;
    }
}
