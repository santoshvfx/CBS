package com.sbc.eia.bis.facades.testing;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author sr1284
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class TestThread extends Thread {

	private TestClient tc = null;
	public Vector fileNames;
	
	public TestThread() {
	}

	public TestThread(TestClient tc) {
		this.tc = tc;
		this.fileNames = new Vector();
	}
	public void run() {
		TestClient.runTestSet(tc);
	}
	
	public void deleteFile(){
		
		for(Enumeration e=fileNames.elements();e.hasMoreElements();){
			File fil = new File((String)e.nextElement());
				fil.delete();		
		}		
	}
}
