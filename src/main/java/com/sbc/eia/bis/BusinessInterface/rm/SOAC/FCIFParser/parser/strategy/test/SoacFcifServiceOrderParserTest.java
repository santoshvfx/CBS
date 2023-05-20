/*
 * Created on Jun 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.strategy.test;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.ObjectPrinter;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SoacFcifServiceOrderParserTest extends TestCase
{
	String dataString = 
        //Rel2 New Connect/Disconnect FTTN example
//        "*C2= PRESO C031774L1       479442SOAC    SOPOMS    050930    0;%*SC=      ;%*EC=1230317741 1234567890 FTTNS ;%*SN=FUAU            ;%\n---ASGM\nG1   CLS XX.MCXX.001074..SW\nFA   632 NORSTROM DR/RT 22221/RZ \n     13\nIOE  NR\nG2   WC 479 442\nIF1  /CA 28/PR 190/CUC E NREQ/DF \n     F13-00-07L10-4-15/PRQ Y/BP \n     440/OBP 2/TEA VRAD FB 717 \n     BIRWIN; PXJ/BCF BP 440 TEA \n     FB 717 BIRWIN/TPR 222219\nIF2  /CA 717BFN/PR 2/PGSC U/PGS \n     73RMD/CUR E DVV8/BP 502/OBP \n     1525/TEA FB 717 BIRWIN; PXJ\n     /RLC KNW/RLA UNKNOWN/RMTE \n     D423265-1,4647321-7\nIF3  /CA 717B/PR 1525/BP BL-W+V-S\n     /OPC BL-W+W-BL/TEA 2ND SAI\n     LAYOUT 3; EXJ/RMTE \n     LIGHTSPEED/RMK RXJ TEA FB \n     717 BIRWIN BP 490/RMKP \n     LIGHTSPEED\nIF4  /CA 717BL3/PR 1/BP 1/TEA \n     HHF 632 NORSTROM DR; CDW\n     /RMTE LIGHTSPEED/RMKP \n     LIGHTSPEED\n}%";
        //Rel2 New Connect/Disconnect FTTP example
//      "*C2= PRESO C031778L1       479442SOAC    SOPOMS    050930    0;%*SC=      ;%*EC=1230317781 1234567890 FTTPS ;%*SN=FANK            ;%\n---ASGM(D)\nG1   CLS 14.MC1A.123002.013.SW\nFA   406 SUMMIT DR/RZ 13\nG2   WC 281 444\n*IF1 /CA PON/PR NR/PRQ Y/TIDC\n     HSTXTEJVOL001BAY10A/TEA\n     HSTXTEJVOLT101/TPR 2337PC\n     /RMTE 5397539/TEC\n     HSTNTXJVOL0/CMOD A7340/CCP\n     1-1-1\nIF2  /CA PON/PR NR/CUD E ES/TEA\n     ONT00002 R 738 GRAND PLAINS\n     DR FST/CCLA 734 GRAND\n     PLAINS DR/CMOD H-ONT-A/AIDI\n     ONT-1-1-1-1-9/INDX 9/TSRN\n     0100101009/CCP 5\nTF1  /NPN 24/APN 24/TEA\n     HSTNTXJVRR08.01 FDF; EOX\n     /RMTE 5397539\nTF2  /CA IR012/PR 24/NPN 1/APN 7\n     /TEA 13602 KUYKENDAHL SPL-1\n     /RMTE 5397539\nTF3  /CA SPT2337PC/PR 7/NPN 7\n     /APN 80/TEA 13602\n     KUYKENDAHL PFP; EOX/RMTE\n     5397539\nTF4  /CA PON13602K/PR 80/NPN 2\n     /APN 2/TEA R 738 GRAND\n     PLAINS DR FST/RMTE 5375642\nTF5  /TEA ONT00002 R 738 GRAND\n     PLAINS DR FST; EOD\n}%";
        //Defective Pair example
//        "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE  NR\nG2   WC 479 442\n*IF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*IF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";       
//LAURA          "*C2= PRESO C036810L1   A   210494SOAC    SOPRMSAT  060923    0;%*SC=      ;%*EC=368101     368102A    FTTPS ;%*SN=FANK            ;%\n---ASGM\nG1   CLS A2.MCXX.009307..SW\nFA   3358 HIGHLINE TRL/RZ 13\n     /RMKS 5892409 PL3\nG2   WC 210 494\nIF1  /CA PON/PR NR/PRQ Y/TIDC \n     SNANTXWEOL0010161041/TEA \n     SNANTXWEOLT101/TEC \n     SNANTXWEOL0/CCLA 17219 \n     USHWY 281 N/CMOD A7340/CCP \n     1-2-1\nIF2  /CA PON/PR NR/CUD E DOE/TEA \n     ONT00001 R 3354 HIGHLINE \n     TRL FST; PNT/CCLA 3358 \n     HIGHLINE TRL/CMOD H-ONT-A\n     /AIDI ONT-1-1-2-1-8/INDX 8\n     /TSRN 0100201008/CCP 5\nTF1  /NPN 29/APN 29/TEA SNANTXWE \n     OSP 010160.04.06; EOX\nTF2  /CA WE016/PR 533/NPN 1/APN 8\n     /TEA R 3327 HIGHLINE TRL \n     SPL-1/RMTE 588186!";
//	"*C2= PRESO C852923L1   C   650355SOAC    SOPRMQC   061209    0;%*SC=      ;%*EC=9734934490 9798334434 FTTNWR;%*SN=FANK            ;%\n---ASGM\nG1    CLS A2.MCXX.921323..SW\nFA    26001 W OUTLOOK RIVER \n      DISPATCH HWY\nRT    1151\nIOE   /TN 925 875-0138\nG2    WC 650 355\nIF1   /CA 13/PR 998/PRQ Y/BP 423\n      /OBP 1501/TEA 3598 HIGHLAND \n      DR VRAD; PXJ/TPR 115102/RMK \n      RXJ TEA 3598 HIGHLAND DR BP \n      1498/RZ 13/CUC E NREQ\nIF2   /CA 0612FN/PR 1/PGS 73RMD\n      /CUR E DVV4/BP 801/OBP 1498\n      /TEA 3598 HIGHLAND DR; PXJ\n      /RLC TEST/RLA 3598 HIGHLAND \n      DR/PGSC U\nIF3   /CA 0612/PR 1498/BP 598/TEA \n      4-1.1 OUTLOOK CIR -PM; CIW\n      /RMKP LIGHTSPEED\n";
"*C2= PRESO C847898L1   B   414321SOAC    SOPRMQC   071127    0;%*SC=      ;%*EC=8474584491 8478984431 FTTNM B;%*SN=FACH            ;%\n---ASGM(A)\nG1   CLS AA.MCXX.847898..SW\nFA   6221 W VERONA CT/RZ 13\nLST  \nIOE  NR\nG2   WC 414 321\nIF1  /CA 10/PR 1342/CUC E NREQ/DF \n     F01-01-082V/PRQ Y/BP 321/OBP 1\n     /TEA SAI R 6720 W MORGAN AV \n     VRAD; PXJ/TPR 325602\nIF2  /CA 6720MFN/PR 1/PGS 73RMD\n     /PGSC U/CUR E DVV4/BP 701/OBP \n     84/TEA SAI R 6720 W MORGAN AV;\n     PXJ/RLA 6720 W MORGAN AV\n*IF3 /CA 6720M/PR 84/BP 9/TEA R \n     6216 W MORGAN AV; PDW/RTC\n     /RMTE BP 26 DD,/RMK RXJ TEA \n     SAI R 6720 W MORGAN AV BP 84\nG1   LST 1\nG2   ITM 1\nG3   TN 414 545-5891\nFA   6132 W EDEN PL/RZ 13\nG4   WC 414 321\nOF1  /CA 10/PR 1034/PRQ Y/BP 84\n     /OBP 84/TEA SAI R 6720 W \n     MORGAN AV; EXJ/TPR 325602\nIF1  /CA 10/PR 1034/PRQ Y/BP 84\n     /OBP 76/TEA SAI R 6720 W \n     MORGAN AV; PXJ/TPR 325602/RMK \n     RXJ TEA SAI R 6720 W MORGAN \n     AV BP 84\nOF2  /CA 6720M/PR 84/BP 14/TEA R \n     6118 W EDEN PL; CDW\nIF2  /CA 6720M/PR 76/BP 6/TEA R \n     6118 W EDEN PL; TDW/BCF BP 1 \n     TEA R 6216 W MORGAN AV/RTC\n     /RMK RXJ TEA SAI R 6720 W \n     MORGAN AV BP 510\n";
//      "*C2= PRESO                       SOAC    SOPOMS              0;%*SC=      ;%*EC=                     ;%*SN=FUAU            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS1C;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105251092502432AAAAAA;PL_LTERM=IA6AFC40;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%\n---ASGM\nG1   CLS       \nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE  NR\nG2   WC 479 442\nIF1  /CA 25/PR 803/CUC E NREQ/DF \n     F23-10-06U03-1-03/PRQ Y/BP \n     103/OBP 1201/TEA FB 1850\n     MORNINGSIDE DR VRAD; PXJ\n     /BCF BP 103 TEA FB 1850\n     MORNINGSIDE DR/TPR 118722\nIF2  /CA 1850MFN/PR 1/PGSC U/PGS \n     73RMD/CUR E DVV4/BP 201/OBP \n     476/TEA FB !\n     1850 MORNINGSIDE \n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\nIF3  /CA 1850/PR 476/BP 1/TEA F \n     106 SCHEELE DR; CDW/RMTE \n     LIGHTSPEED/RMKP LIGHTSPEED\n}%";
//      "*C2= CORSO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FANK            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213121807584AAAAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%";
//      "*C2= CORSO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FANK            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213121807584AAAAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%---ASGM\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE  NR\nG2   WC 479 442\nIF1  \n/CA 25/PR 808/CUC E NREQ/DF\n      F23-10-06U03-1-08/PRQ Y/BP\n      108/OBP 2/TEA VRAD FB 1850\n      MORNINGSIDE DR; PXJ/TPR\n      118722/RMK RXJ TEA FB 1850\n      MORNINGSIDE DR BP 477\nIF2  /CA 1850MFN/PR 2/PGSC U/PGS\n      73RMD/CUR E DVV8/BP 202/OBP\n      477/TEA FB 1850 MORNINGSIDE\n      DR; PXJ/RLC JSONOOOJ/RLA\n      VRAD AT 1850 MORNINGSIDE DR\n    /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\nIF3  /CA 1850/PR 477/BP 2/TEA R\n      106 SCHEELE DR; CDW/RMTE\n      LIGHTSPEED/RMKP LIGHTSPEED\n}%";
//      "*C2= CORSO C910200L3   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=ESOI            ;%\n*PLHDR{ PLREL=10.5   ;PLSECID=SOPOMS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213131309960AA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;PLAT=N;}%---ERRS004-009: CONTROL FILE ORDER NUMBER C910200L3B DOES NOT EQUAL ORDER NUMBER IN FIXED FIELDED ID SECTION C910200L1B";
//      "*C2= PRESO C060211         314991SOAC    SOPSTL    051229    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FANK            ;%\n                                                                                                                                                                                             ---ASGM\nG1   CLS 12.ABCD.060211..SW\nFA   801 N LINDBERGH BLVD, CREVE \n     COEUR/LOC BLDG G/RZ 13\nIROE ZD00-0-07-17/EXK 314 111/LPS\n     /DF F13-11-10L02-4-02\nG2   WC 314 991\nIF1  /CA 40/PR 47/DF F13-04-\n     05U06-2-22/PRQ Y/BP 47/TEA \n     MDF MONSANTO BLDG G; PIW\n     /TPR 111502/RMTE CWO2236707";
//		"*C2= PRESO C060211         314991SOAC    SOPSTL    051229    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FANK            ;%\n---ASGM\nG1   CLS 12.ABCD.060211..SW\nFA   801 N LINDBERGH BLVD, CREVE \n     COEUR/LOC BLDG G/RZ 13\nIROE ZD00-0-07-17/EXK 314 111/LPS\n     /DF F13-11-10L02-4-02\nG2   WC 314 991\nIF1  /CA 40/PR 47/DF F13-04-\n     05U06-2-22/PRQ Y/BP 47/TEA \n     MDF MONSANTO BLDG G; PIW\n     /TPR 111502/RMTE CWO2236707";
//      "*C2= PRESO C910200L1   A   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FMIS            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF; SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;PLSECID=SOPOMS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105224093629508AAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%";
//      "*C2= PRESO C910200L2   A   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FMIS            ;%\n";
//      "*C2= PRESO C910222L1   A   479442SOAC    SOPRMQC   051030    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FMIS            ;%\n";
	Logger logger;

	ParseRequest aParserResponse;
    ParserSvc aParserSvc;
//	ServiceOrderParser parser;


	/**
	 * Constructor for SoacFcifServiceOrderParserTest.
	 * @param arg0
	 */
	public SoacFcifServiceOrderParserTest(String arg0)
	{
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		
		logger = new TestLogger();
		aParserResponse = new ParseRequest();
        aParserResponse.setLogger(logger);
		aParserResponse.setFcifDataString(dataString);
 //       aParserResponse.setRegion(SoacServiceOrderConstants.SBCMIDWEST);
//        aParserResponse.setRegion(SoacServiceOrderConstants.SBCEAST);
//        aParserResponse.setRegion(SoacServiceOrderConstants.SBCSOUTHWEST);
  //      aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
        aParserResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
        aParserResponse.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);

        aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testProcessData() throws ParserSvcException
	{
		aParserSvc.processData(aParserResponse);
		Object o = aParserResponse.getServiceOrderVo();
		ObjectPrinter objPrinter = new ObjectPrinter();

        System.out.println(aParserResponse.getFcifDataString());
       
	}

}
