//$Id: DslAccountLookupResponse.java,v 1.2 2009/02/19 21:40:29 sl7683 Exp $
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
//# 01/2009      Sheilla Lirio         Creation.

package com.sbc.eia.bis.BusinessInterface.rm.CPSOS;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;

public class DslAccountLookupResponse {
	
	//request identification fields
	private String aRespType;
	private String aRespId;
	private String aSbcTrackingNumber;
	private String aSbcReqTrackingNumber;
	private String aReqOrgId;
	private String aReqAffiliateId;
	private String aPartnerId;
	private String aUserId;
	private String aPassword;
	private String aServerName;
	private String aReqTimestamp;
	private String aRespTimestamp;
	private String aInterfaceVersion;
	
	//dsl account lookup response fields
	private String aNpa;
	private String aNxx;
	private String aLine;
	private String aExtension;
	
	private String aSrvcAddStreetName;
	private String aSrvcAddLoc1;
	private String aSrvcAddLoc2;
	private String aSrvcAddLoc3;
	private String aSrvcAddCity;
	private String aSrvcAddState;
	private String aSrvcAddZipCode;
	private String aNetworkProvider;
	
	//error response fields
	private String aErrorId;
	private String aErrorText;
	private String aErrorLogId;
	private String aErrorTimestamp;
	
	private Utility aUtility = null;
    private Hashtable aProperties = null;
	
	public DslAccountLookupResponse (Utility utility, Hashtable properties)
	{
		aProperties = properties;
        aUtility = utility;
	}

	public String getErrorId() {
		return aErrorId;
	}

	public void setErrorId(String errorId) {
		aErrorId = errorId;
	}

	public String getErrorLogId() {
		return aErrorLogId;
	}

	public void setErrorLogId(String errorLogId) {
		aErrorLogId = errorLogId;
	}

	public String getErrorText() {
		return aErrorText;
	}

	public void setErrorText(String errorText) {
		aErrorText = errorText;
	}

	public String getErrorTimestamp() {
		return aErrorTimestamp;
	}

	public void setErrorTimestamp(String errorTimestamp) {
		aErrorTimestamp = errorTimestamp;
	}

	public String getExtension() {
		return aExtension;
	}

	public void setExtension(String extension) {
		aExtension = extension;
	}

	public String getInterfaceVersion() {
		return aInterfaceVersion;
	}

	public void setInterfaceVersion(String interfaceVersion) {
		aInterfaceVersion = interfaceVersion;
	}

	public String getLine() {
		return aLine;
	}

	public void setLine(String line) {
		aLine = line;
	}

	public String getNetworkProvider() {
		return aNetworkProvider;
	}

	public void setNetworkProvider(String networkProvider) {
		aNetworkProvider = networkProvider;
	}

	public String getNpa() {
		return aNpa;
	}

	public void setNpa(String npa) {
		aNpa = npa;
	}

	public String getNxx() {
		return aNxx;
	}

	public void setNxx(String nxx) {
		aNxx = nxx;
	}

	public String getPartnerId() {
		return aPartnerId;
	}

	public void setPartnerId(String partnerId) {
		aPartnerId = partnerId;
	}

	public String getPassword() {
		return aPassword;
	}

	public void setPassword(String password) {
		aPassword = password;
	}

	public String getReqAffiliateId() {
		return aReqAffiliateId;
	}

	public void setReqAffiliateId(String reqAffiliateId) {
		aReqAffiliateId = reqAffiliateId;
	}

	public String getReqOrgId() {
		return aReqOrgId;
	}

	public void setReqOrgId(String reqOrgId) {
		aReqOrgId = reqOrgId;
	}

	public String getReqTimestamp() {
		return aReqTimestamp;
	}

	public void setReqTimestamp(String reqTimestamp) {
		aReqTimestamp = reqTimestamp;
	}

	public String getRespId() {
		return aRespId;
	}

	public void setRespId(String respId) {
		aRespId = respId;
	}

	public String getRespTimestamp() {
		return aRespTimestamp;
	}

	public void setRespTimestamp(String respTimestamp) {
		aRespTimestamp = respTimestamp;
	}

	public String getRespType() {
		return aRespType;
	}

	public void setRespType(String respType) {
		aRespType = respType;
	}

	public String getSbcTrackingNumber() {
		return aSbcTrackingNumber;
	}

	public void setSbcTrackingNumber(String sbcTrackingNumber) {
		aSbcTrackingNumber = sbcTrackingNumber;
	}

	public String getServerName() {
		return aServerName;
	}

	public void setServerName(String serverName) {
		aServerName = serverName;
	}

	public String getSbcReqTrackingNumber() {
		return aSbcReqTrackingNumber;
	}

	public void setSbcReqTrackingNumber(String reqTrackingNumber) {
		aSbcReqTrackingNumber = reqTrackingNumber;
	}

	public String getSrvcAddCity() {
		return aSrvcAddCity;
	}

	public void setSrvcAddCity(String srvcAddCity) {
		aSrvcAddCity = srvcAddCity;
	}

	public String getSrvcAddLoc1() {
		return aSrvcAddLoc1;
	}

	public void setSrvcAddLoc1(String srvcAddLoc1) {
		aSrvcAddLoc1 = srvcAddLoc1;
	}

	public String getSrvcAddLoc2() {
		return aSrvcAddLoc2;
	}

	public void setSrvcAddLoc2(String srvcAddLoc2) {
		aSrvcAddLoc2 = srvcAddLoc2;
	}

	public String getSrvcAddLoc3() {
		return aSrvcAddLoc3;
	}

	public void setSrvcAddLoc3(String srvcAddLoc3) {
		aSrvcAddLoc3 = srvcAddLoc3;
	}

	public String getSrvcAddState() {
		return aSrvcAddState;
	}

	public void setSrvcAddState(String srvcAddState) {
		aSrvcAddState = srvcAddState;
	}

	public String getSrvcAddStreetName() {
		return aSrvcAddStreetName;
	}

	public void setSrvcAddStreetName(String srvcAddStreetName) {
		aSrvcAddStreetName = srvcAddStreetName;
	}

	public String getSrvcAddZipCode() {
		return aSrvcAddZipCode;
	}

	public void setSrvcAddZipCode(String srvcAddZipCode) {
		aSrvcAddZipCode = srvcAddZipCode;
	}

	public String getUserId() {
		return aUserId;
	}

	public void setUserId(String userId) {
		aUserId = userId;
	}



}
