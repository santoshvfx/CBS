//# $Id: Migration.java,v 1.1 2007/11/07 20:15:19 cy4727 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date         | Author                 | Notes
//# --------------------------------------------------------------------------
//# 09/09/2007	 Changchuan Yin			  Creation

package com.sbc.eia.bis.BusinessInterface.rm.ModifyInventory;

public class Migration {
	String wireCenterCLLI8 = null;
	String migrationClient = "OMS"; //default
	String migrationComplete = null;
	
	public Migration(String clientName, String wireCenterCLLI8) {
		super();
		
		this.migrationClient = clientName;
		
		this.wireCenterCLLI8 = wireCenterCLLI8;
	}
	
	public String getMigrationClient() {
		return migrationClient;
	}
	
	public void setMigrationClient(String clientName) {
		this.migrationClient = clientName;
	}
	
	public String getWireCenterCLLI8() {
		return wireCenterCLLI8;
	}
	
	public void setWireCenterCLLI8(String wireCenterCLLI8) {
		this.wireCenterCLLI8 = wireCenterCLLI8;
	}
	
	public String getMigrationComplete() {
		return migrationComplete;
	}
	
	public void setMigrationComplete(String migrationComplete) {
		this.migrationComplete = migrationComplete;
	}
	
	
}
