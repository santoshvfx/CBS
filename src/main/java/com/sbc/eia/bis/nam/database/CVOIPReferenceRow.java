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
//#      Copyright (C) 2006 SBC Services, Inc.
//#      All Rights Reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/02/06   Vinod Rachapudi		Initial Creation for Lightspeed 3. This class contains fields
//#									for table CVOIP_Reference table
package com.sbc.eia.bis.nam.database;

/**
 * @author vr6412
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CVOIPReferenceRow {
	
	private   String multiEsrnIndicator= "";
	private   String esrn = "";
	private   String npa = ""; 
	private   String rateCenter="";
	private   String stateCd  = "";
	private   String tnListId  = "";
	private   String lightspeedLrn = ""; 
	private   String cvoipSoacSwitchWc = "";
	private   String cvoipSoacEntity = "";
	private   String cvoipSwitchEntity = "";
	 
	

	/**
	 * @return
	 */
	public String getCvoipSoacEntity() {
		return cvoipSoacEntity;
	}

	/**
	 * @return
	 */
	public String getCvoipSoacSwitchWc() {
		return cvoipSoacSwitchWc;
	}

	/**
	 * @return
	 */
	public String getCvoipSwitchEntity() {
		return cvoipSwitchEntity;
	}

	/**
	 * @return
	 */
	public String getEsrn() {
		return esrn;
	}

	/**
	 * @return
	 */
	public String getLightspeedLrn() {
		return lightspeedLrn;
	}

	/**
	 * @return
	 */
	public String getMultiEsrnIndicator() {
		return multiEsrnIndicator;
	}

	/**
	 * @return
	 */
	public String getNpa() {
		return npa;
	}

	/**
	 * @return
	 */
	public String getRateCenter() {
		return rateCenter;
	}

	/**
	 * @return
	 */
	public String getStateCd() {
		return stateCd;
	}

	/**
	 * @return
	 */
	public String getTnListId() {
		return tnListId;
	}

	/**
	 * @param string
	 */
	public void setCvoipSoacEntity(String string) {
		cvoipSoacEntity = string;
	}

	/**
	 * @param string
	 */
	public void setCvoipSoacSwitchWc(String string) {
		cvoipSoacSwitchWc = string;
	}

	/**
	 * @param string
	 */
	public void setCvoipSwitchEntity(String string) {
		cvoipSwitchEntity = string;
	}

	/**
	 * @param string
	 */
	public void setEsrn(String string) {
		esrn = string;
	}

	/**
	 * @param string
	 */
	public void setLightspeedLrn(String string) {
		lightspeedLrn = string;
	}

	/**
	 * @param string
	 */
	public void setMultiEsrnIndicator(String string) {
		multiEsrnIndicator = string;
	}

	/**
	 * @param string
	 */
	public void setNpa(String string) {
		npa = string;
	}

	/**
	 * @param string
	 */
	public void setRateCenter(String string) {
		rateCenter = string;
	}

	/**
	 * @param string
	 */
	public void setStateCd(String string) {
		stateCd = string;
	}

	/**
	 * @param string
	 */
	public void setTnListId(String string) {
		tnListId = string;
	}

}
