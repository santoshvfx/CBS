//$Id: DslAccountLookupRequest.java,v 1.2 2009/02/19 21:39:51 sl7683 Exp $
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

import java.text.SimpleDateFormat;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;

public class DslAccountLookupRequest {
	
	private final String aReqType = "DslAccountLookupRequest";
	
	//request identification fields
	private String aReqOrgId;
	private String aReqAffiliateId;
	private String aPartnerId;
	private String aUserId;
	private String aPassword;
	private String aTrackingNumber;
	private String aServerName;
	private SimpleDateFormat aTimestamp;
	private String aInterfaceVesion;
	
	//dsl account lookup request fields
	private String aNpa;
	private String aNxx;
	private String aLine;
	private String aExtension;
	private String aState;
	private int aEndUserAuth;
	
	private Utility aUtility = null;
    private Hashtable aProperties = null;
	
	public DslAccountLookupRequest (Utility utility, Hashtable properties)
	{
		aProperties = properties;
        aUtility = utility;
	}


	public int getEndUserAuth() {
		return aEndUserAuth;
	}


	public void setEndUserAuth(int endUserAuth) {
		aEndUserAuth = endUserAuth;
	}


	public String getExtension() {
		return aExtension;
	}


	public void setExtension(String extension) {
		aExtension = extension;
	}


	public String getInterfaceVesion() {
		return aInterfaceVesion;
	}


	public void setInterfaceVesion(String interfaceVesion) {
		aInterfaceVesion = interfaceVesion;
	}


	public String getLine() {
		return aLine;
	}


	public void setLine(String line) {
		aLine = line;
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


	public String getReqType() {
		return aReqType;
	}


	public String getServerName() {
		return aServerName;
	}


	public void setServerName(String serverName) {
		aServerName = serverName;
	}


	public String getState() {
		return aState;
	}


	public void setState(String state) {
		aState = state;
	}


	public SimpleDateFormat getTimestamp() {
		return aTimestamp;
	}


	public void setTimestamp(SimpleDateFormat timestamp) {
		aTimestamp = timestamp;
	}


	public String getTrackingNumber() {
		return aTrackingNumber;
	}


	public void setTrackingNumber(String trackingNumber) {
		aTrackingNumber = trackingNumber;
	}


	public String getUserId() {
		return aUserId;
	}


	public void setUserId(String userId) {
		aUserId = userId;
	}
	
}
