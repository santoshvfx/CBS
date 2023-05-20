// $Id: TOPLISTENERTest.java,v 1.6 2006/03/29 15:07:13 jc1421 Exp $

package com.sbc.gwsvcs.service.toplistener;

import java.util.Hashtable;

import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.toplistener.exceptions.TOPLISTENERException;
import com.sbc.gwsvcs.service.toplistener.interfaces.Header_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TrnsptType_e;

public class TOPLISTENERTest implements com.sbc.bccs.utilities.Logger
{

	public TOPLISTENERTest()
	{
		super();
	}

	public void log(String eventId, String message)
	{
		System.out.println("LOG: " + eventId + " " + message);
	}

	public void log(String s1, String s2, String s3, String s4)
	{}

	public void log(String s1, String s2, String s3, String s4, String s5)
	{}

	/**
	 * Executes the program.
	 * Creation date: (2/26/01 12:33:23 PM)
	 * @param args java.lang.String[]
	 */
	public static void main(String[] args)
	{
		System.setProperty("bis.platform", "pc");
		Hashtable props = new Hashtable();
		props.put("TOPLISTENER_TIMEOUT", "30");
		props.put("GWSVCS_CLNTUUID", "rmbis");
		props.put("VICUNA_XML_FILE", "c:\\vicuna\\etc\\vicunalite.prod.xml");
		//props.put("VICUNA_XML_FILE", "c:\\vicuna\\etc\\vicunalite.toplistener.xml");
		//props.put("VICUNA_XML_FILE", "c:\\vicuna\\etc\\vicunalite.qc.xml");
		//props.put("VICUNA_XML_FILE", "c:\\vicuna\\etc\\vicunalite.ets.xml");

		try
		{
			com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
			TOPLISTENERTest aTOPTest = new TOPLISTENERTest();
			TOPLISTENERHelper helper = new TOPLISTENERHelper(props, aTOPTest);

			Header_t hdr = new Header_t("RMBIS", "RMBIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

			//SW
			//String ApplData = "SOACDAA00";	// Dev Test
			//String ApplData = "SOACQAA00";	// QC Test
			//String ApplData = "SOACPBC00";	// PROD
			//String ApplData = "SOACPAN00";	// PROD

			//W
			//String ApplData = "SOACDCAST";	// Dev Test
			//String ApplData = "SOACQCAST";	// QC Test
			//String ApplData = "SOACTCAST";	// SAT Test
			//String ApplData = "SOACECAST";	// ETS
			//String ApplData = "SOACPHBKI";	// PROD
			//String ApplData = "SOACPSBJF";	// PROD

			//MW
			//String ApplData = "SOACDPMCD";	// Dev Test
			//String ApplData = "SOACQPMCD";	// QC Test
			//String ApplData = "SOACTPMCD";	// SAT Test
			//String ApplData = "SOACEPMCD";	// ETS Test
			//String ApplData = "SOACPAM6F"; // PROD
			//String ApplData = "SOACPPMD2";	// PROD

			//E
			//String ApplData = "SOACDGSYS";	// Dev Test
			//String ApplData = "SOACQGSYS";	// QC Test
			//String ApplData = "SOACTGSYS";	// SAT Test
			//String ApplData = "SOACEGSYS";	// ETS Test
			String ApplData = "SOACPIG00";	// PROD test

			String aFCIFData_t =
			// SW
			//APPLDATA=SOACPAN00
			//	"*C1= PRESO C123456L7       972231SOPOMS  SOACSWZ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       817274SOPOMS  SOACSWY   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       409832SOPOMS  SOACSWW   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       713522SOPOMS  SOACSWN   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       936564SOPOMS  SOACSWX   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       713781SOPOMS  SOACSWQ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       512442SOPOMS  SOACSWK   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       806352SOPOMS  SOACSWJ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       956682SOPOMS  SOACSWM   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       956722SOPOMS  SOACSWL   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//APPLDATA=SOACTAA00
			//	"*C1= PRESO C123456L7       479442SOPRMQC SOACSTC   021016    0;%*SC=EV    ;%*EC=TEST;%\n";	
			//APPLDATA=SOACPBC00
			//	"*C1= PRESO C123456L7       314381SOPOMS  SOACSWA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       636527SOPOMS  SOACSWB   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       816842SOPOMS  SOACSWO   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       913764SOPOMS  SOACSWI   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       405321SOPOMS  SOACSWF   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       918582SOPOMS  SOACSWG   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       479442SOPOMS  SOACSWU   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       870932SOPOMS  SOACSWV   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//R2 WireCenter, APPLDATA=SOACTAA00
			//	"*C1= PRESO C123456L7       214331SOPRMDEVSOACSTA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       214331SOPRMQC SOACSTA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       214331SOPRMSATSOACSTA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       214331SOPOMS  SOACSTA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";

			//W
			//	"*C1= PRESO C123456L7       650355SOPRMDEVSOACWTE   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       650355SOPRMQC SOACWTE   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       650355SOPRMSATSOACWTE   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       650355SOPOMS  SOACWTE   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//PROD, ApplData = "SOACPHBKI"
			//	"*C1= PRESO C123456L7       775321SOPOMS  SOACWSM   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       916322SOPOMS  SOACWSA   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       530891SOPOMS  SOACWSF   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       559291SOPOMS  SOACWSK   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       209522SOPOMS  SOACWSG   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       510271SOPOMS  SOACWSJ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       707575SOPOMS  SOACWSL   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       831422SOPOMS  SOACWSE   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       650583SOPOMS  SOACWSD   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       415362SOPOMS  SOACWSB   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       925932SOPOMS  SOACWSH   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       408277SOPOMS  SOACWSC   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//PROD, ApplData = "SOACPSBJF"
			//	"*C1= PRESO C123456L7       213387SOPOMS  SOACWSS   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       323857SOPOMS  SOACWSW   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       858268SOPOMS  SOACWSQ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       619295SOPOMS  SOACWSR   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       951371SOPOMS  SOACWSY   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       714241SOPOMS  SOACWSZ   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       818240SOPOMS  SOACWSU   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       310417SOPOMS  SOACWST   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       310603SOPOMS  SOACWSV   021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       818340SOPOMS  SOACWSX   021016    0;%*SC=EV    ;%*EC=TEST;%\n";

			//MW
			//	"*C1= PRESO C123456L7       414321SOPRMDEVSOACX     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       414321SOPRMQC SOACX     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       414321SOPRMSATSOACX     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       414321SOPOMS  SOACX     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//PROD, ApplData = "SOACPAM6F"
			//	"*C1= PRESO C123456L7       330494SOPOMS  SOACA     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       216621SOPOMS  SOACB     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       614221SOPOMS  SOACC     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       937434SOPOMS  SOACD     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       216921SOPOMS  SOACE     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       812332SOPOMS  SOACF     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       317631SOPOMS  SOACG     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       574232SOPOMS  SOACH     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       734509SOPOMS  SOACI     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       313240SOPOMS  SOACJ     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       248398SOPOMS  SOACK     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       269333SOPOMS  SOACL     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       586445SOPOMS  SOACM     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       616249SOPOMS  SOACN     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       810232SOPOMS  SOACO     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//PROD, ApplData = "SOACPPMD2"
			//	"*C1= PRESO C123456L7       312368SOPOMS  SOACP     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       773783SOPOMS  SOACQ     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       630232SOPOMS  SOACR     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       847367SOPOMS  SOACS     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       773327SOPOMS  SOACT     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       217522SOPOMS  SOACU     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       815226SOPOMS  SOACV     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       708656SOPOMS  SOACW     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       630369SOPOMS  SOACX     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       414257SOPOMS  SOAC6     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       920731SOPOMS  SOAC7     021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       262542SOPOMS  SOAC8     021016    0;%*SC=EV    ;%*EC=TEST;%\n";

			//E
			//	"*C1= PRESO C123456L7       203329SOPRMDEVSOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       203329SOPRMQC SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       203329SOPRMSATSOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       203329SOPOMS  SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//PROD
				"*C1= PRESO C123456L7       203351SOPOMS  SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       860511SOPOMS  SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       860223SOPOMS  SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";
			//	"*C1= PRESO C123456L7       203497SOPOMS  SOAC      021016    0;%*SC=EV    ;%*EC=TEST;%\n";

			// Add order to above header, the order is a dummy order for syntax only.
			/*aFCIFData_t =
				aFCIFData_t
					+ "   314 340-9277                              \n"
					+ "    C123456L7                XR7FA           \n"
					+ "    03-16-06    03-18-06      \n"
					+ "---LIST\n"
					+ "LA   1749 SFLFTTP DR\n"
					+ "---S&E\n"
					+ "I    AS3NF\n"
					+ "     /CLS 12.MCXX.001096.001.MW\n"
					+ "     /LSO 217 522\n"
					+ "     /FCI FTIP\n"
					+ "     /BRTG\n"
					+ "---RMKS\n"
					+ "RMK  LIGHTSPEED - 1230317961 - \n"
					+ "     1234567890\n";*/

			System.out.println(aFCIFData_t);
			String dtnName = "YICDMP";

			TOPSendToHostReq_t request = new TOPSendToHostReq_t(hdr, aFCIFData_t, dtnName);

			response = helper.topListenerReq(ApplData, null, 0, request);

			TOPSendToHostAckResp_t inqNtuResult = (TOPSendToHostAckResp_t) response.getTheObject();
			System.out.println("Sent to toplistener without error..");

			System.exit(0);
		}
		catch (ServiceTimeoutException e)
		{
			System.out.println("timeout reached...");
		}
		catch (TOPLISTENERException e)
		{
			System.out.println(
				"TOPLISTENERException: " + e.getExceptionCode() + " : " + e.getMessage());
		}
		catch (ServiceException e)
		{
			System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
			e.printStackTraces();
		}
	}
}
