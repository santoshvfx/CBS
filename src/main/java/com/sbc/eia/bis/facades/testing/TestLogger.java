package com.sbc.eia.bis.facades.testing;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
/**
 * Insert the type's description here.
 * Creation date: (5/15/00 3:57:55 PM)
 * @author: Administrator
 */
public class TestLogger {
	public static java.lang.String fileLocation;
	public java.io.File file;
	public java.io.FileOutputStream fos;
	public java.io.DataOutputStream dos;

	public java.lang.String testCase = "DeterminePotabilityStatus";
	public final static String NL = System.getProperty("line.separator", "\n");
	/**
	 * Insert the method's description here.
	 * Creation date: (5/15/00 5:20:19 PM)
	 * @param argLogLocation java.lang.String
	 * @param argTestCase java.lang.String
	 */
	public TestLogger(String argLogLocation, String argTestCase) {
		fileLocation = argLogLocation;

		file = null;
		fos = null;
		dos = null;

		file = new File(fileLocation);

		try {
			fos = new FileOutputStream(fileLocation, true);
			dos = new DataOutputStream(fos);
		} catch (FileNotFoundException fe) {
			System.out.println(fe.getMessage());
		}
		/*catch(IOException fe){
			System.out.println(fe.getMessage());
		}*/
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (7/9/01 3:05:54 PM)
	 */
	public void clearFile(String inputFile) {

		java.io.FileOutputStream fos1 = null;
		java.io.DataOutputStream dos1 = null;

		try {
			fos1 = new FileOutputStream(inputFile);
			dos1 = new DataOutputStream(fos1);
			dos1.writeBytes("");
		    
		} catch (FileNotFoundException fe) {
			System.out.println(fe.getMessage());
		} catch (IOException fe) {
			System.out.println(fe.getMessage());
		} finally {
			try{
				fos1.close();
				dos1.close();
			}catch (Exception e){
			}
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/15/00 4:19:56 PM)
	 */
	public void init(String argTestCase) {
		printLog("*************************************");
		printLog("Test case for : " + argTestCase);
		printLog("*************************************");

	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/15/00 4:07:58 PM)
	 * @param argLogData java.lang.String
	 */
	public void printLog(String argLogData) {
		try {
			Calendar now = Calendar.getInstance();
			DecimalFormat dd = new DecimalFormat();
			dd.applyPattern("00");
			DecimalFormat ddd = new DecimalFormat();
			ddd.applyPattern("000");
			if(TestClient.threadCount != 1){
				dos.writeBytes(Thread.currentThread().getName()
					+ "--");
			}
			dos.writeBytes(
				now.get(Calendar.YEAR)
					+ "/"
					+ dd.format((now.get(Calendar.MONTH) + 1))
					+ "/"
					+ dd.format(now.get(Calendar.DAY_OF_MONTH))
					+ " "
					+ dd.format(now.get(Calendar.HOUR_OF_DAY))
					+ ":"
					+ dd.format(now.get(Calendar.MINUTE))
					+ ":"
					+ dd.format(now.get(Calendar.SECOND))
					+ "."
					+ ddd.format(now.get(Calendar.MILLISECOND))
					+ (now.get(Calendar.ZONE_OFFSET) / 1000 / 60 / 60)
					+ " "
					+ argLogData
					+ NL);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (6/28/01 2:04:39 PM)
	 */
	public void printTestOutput(String argLogData) {
		try {
			dos.writeBytes(argLogData + NL);

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	public void close() {
		try {
			fos.close();
			dos.close();
		} catch (IOException e) {
		} catch (Exception e){
		}
	}
}
