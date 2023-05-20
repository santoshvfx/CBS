/*
 * Created on Jun 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.test;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParserSvc;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.factory.ParserSvcFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.ObjectPrinter;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.SoacFcifDataAccessor;

/**
 * @author ns3580
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SoacServiceOrderAccessorTest extends TestCase
{
    String dataString = 
//      "*C2= PRESO                       SOAC    SOPOMS              0;%*SC=      ;%*EC=                     ;%*SN=FUAU            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS1C;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105251092502432AAAAAA;PL_LTERM=IA6AFC40;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%\n---ASGM\nG1   CLS       \nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE  NR\nG2   WC 479 442\nIF1  /CA 25/PR 803/CUC E NREQ/DF \n     F23-10-06U03-1-03/PRQ Y/BP \n     103/OBP 1201/TEA FB 1850\n     MORNINGSIDE DR VRAD; PXJ\n     /BCF BP 103 TEA FB 1850\n     MORNINGSIDE DR/TPR 118722\nIF2  /CA 1850MFN/PR 1/PGSC U/PGS \n     73RMD/CUR E DVV4/BP 201/OBP \n     476/TEA FB !\n     1850 MORNINGSIDE \n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\nIF3  /CA 1850/PR 476/BP 1/TEA F \n     106 SCHEELE DR; CDW/RMTE \n     LIGHTSPEED/RMKP LIGHTSPEED\n}%";
        //Southwest example
//      "*C2= PRESO C031774L1       479442SOAC    SOPOMS    050930    0;%*SC=      ;%*EC=1230317741 1234567890 FTTNS ;%*SN=FUAU            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS1C;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105251092502432AAAAAA;PL_LTERM=IA6AFC40;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%\n---ASGM\nG1   CLS XX.MCXX.001074..SW\nFA   632 NORSTROM DR/RT 22221/RZ 13\nIOE  NR\nG2   WC 479 442\nIF1  /CA 28/PR 190/CUC E NREQ/DF \n     F13-00-07L10-4-15/PRQ Y/BP \n     440/OBP 2/TEA VRAD FB 717 \n     BIRWIN; PXJ/BCF BP 440 TEA \n     FB 717 BIRWIN/TPR 222219\nIF2  /CA 717BFN/PR 2/PGSC U/PGS \n     73RMD/CUR E DVV8/BP 502/OBP \n     1525/TEA FB 717 BIRWIN; PXJ\n     /RLC KNW/RLA UNKNOWN/RMTE \n     D423265-1,4647321-7\nIF3  /CA 717B/PR 1525/BP BL-W+V-S\n     /OPC BL-W+W-BL/TEA 2ND SAI\n     LAYOUT 3; EXJ/RMTE \n     LIGHTSPEED/RMK RXJ TEA FB \n     717 BIRWIN BP 490/RMKP \n     LIGHTSPEED\nIF4  /CA 717BL3/PR 1/BP 1/TEA \n     HHF 632 NORSTROM DR; CDW\n     /RMTE LIGHTSPEED/RMKP \n     LIGHTSPEED\n}%";
        //Defective Pair example
        "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nOOE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n*OF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*OF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*OF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";
//        "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nOOE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n}%";       
//      "*C2= CORSO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FANK            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213121807584AAAAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%";
//      "*C2= CORSO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FANK            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF;SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213121807584AAAAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%---ASGM\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE  NR\nG2   WC 479 442\nIF1  \n/CA 25/PR 808/CUC E NREQ/DF\n      F23-10-06U03-1-08/PRQ Y/BP\n      108/OBP 2/TEA VRAD FB 1850\n      MORNINGSIDE DR; PXJ/TPR\n      118722/RMK RXJ TEA FB 1850\n      MORNINGSIDE DR BP 477\nIF2  /CA 1850MFN/PR 2/PGSC U/PGS\n      73RMD/CUR E DVV8/BP 202/OBP\n      477/TEA FB 1850 MORNINGSIDE\n      DR; PXJ/RLC JSONOOOJ/RLA\n      VRAD AT 1850 MORNINGSIDE DR\n    /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\nIF3  /CA 1850/PR 477/BP 2/TEA R\n      106 SCHEELE DR; CDW/RMTE\n      LIGHTSPEED/RMKP LIGHTSPEED\n}%";
//      "*C2= CORSO C910200L3   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=ESOI            ;%\n*PLHDR{ PLREL=10.5   ;PLSECID=SOPOMS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105213131309960AA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;PLAT=N;}%---ERRS004-009: CONTROL FILE ORDER NUMBER C910200L3B DOES NOT EQUAL ORDER NUMBER IN FIXED FIELDED ID SECTION C910200L1B";
//      "*C2= PRESO C060211         314991SOAC    SOPSTL    051229    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FANK            ;%\n                                                                                                                                                                                             ---ASGM\nG1   CLS 12.ABCD.060211..SW\nFA   801 N LINDBERGH BLVD, CREVE \n     COEUR/LOC BLDG G/RZ 13\nIROE ZD00-0-07-17/EXK 314 111/LPS\n     /DF F13-11-10L02-4-02\nG2   WC 314 991\nIF1  /CA 40/PR 47/DF F13-04-\n     05U06-2-22/PRQ Y/BP 47/TEA \n     MDF MONSANTO BLDG G; PIW\n     /TPR 111502/RMTE CWO2236707";
//      "*C2= PRESO C060211         314991SOAC    SOPSTL    051229    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FANK            ;%\n---ASGM\nG1   CLS 12.ABCD.060211..SW\nFA   801 N LINDBERGH BLVD, CREVE \n     COEUR/LOC BLDG G/RZ 13\nIROE ZD00-0-07-17/EXK 314 111/LPS\n     /DF F13-11-10L02-4-02\nG2   WC 314 991\nIF1  /CA 40/PR 47/DF F13-04-\n     05U06-2-22/PRQ Y/BP 47/TEA \n     MDF MONSANTO BLDG G; PIW\n     /TPR 111502/RMTE CWO2236707";
//      "*C2= PRESO C910200L1   A   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234;%*SN=FMIS            ;%\n*PLHDR{ PLREL=10.5   ;DEBUG=:FFF; SUBSYS=ALL;DLI=ALL;TSO=SOACSA.DBG.RMBIS;PLSECID=SOPOMS;IMSTRAN=YICDMPC ;UNIQID=IA6AMR1105224093629508AAAA;PL_LTERM=IA6AFC37;YIRFS=C;PASSOFF=YES;MSGSW=Y;YERFS=C;PLAT=N;}%";
//      "*C2= PRESO C910200L2   A   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FMIS            ;%\n";
//      "*C2= PRESO C910222L1   A   479442SOAC    SOPRMQC   051030    0;%*SC=      ;%*EC=1234567890 1234567890;%*SN=FMIS            ;%\n";
    Logger logger;

    ParseRequest aParserResponse;
    ParserSvc aParserSvc;
//  ServiceOrderParser parser;


    /**
     * Constructor for SoacFcifServiceOrderParserTest.
     * @param arg0
     */
    public SoacServiceOrderAccessorTest(String arg0)
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
//        aParserResponse.setRegion(SoacServiceOrderConstants.SBCMIDWEST);
//        aParserResponse.setRegion(SoacServiceOrderConstants.SBCEAST);
//        aParserResponse.setRegion(SoacServiceOrderConstants.SBCSOUTHWEST);
        aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
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

    public void testProcessData() throws ParserSvcException, DataAccessorException
    {
        aParserSvc.processData(aParserResponse);
        ObjectPrinter objPrinter = new ObjectPrinter();
        System.out.println(aParserResponse.getFcifDataString());

        System.out.println("**************Get Accessor Values******************\n");
        SoacServiceOrderVO ssovo = (SoacServiceOrderVO) aParserResponse.getServiceOrderVo();

        SoacFcifDataAccessor accessor = new SoacFcifDataAccessor(ssovo);
        System.out.println("TN ID: " + accessor.getTelephoneNumber());
        System.out.println("Binding Post Data: " + accessor.getBindingPostData());
        System.out.println("Cable Data: " + accessor.getCableData());
        System.out.println("Error Message: " + accessor.getErrorMessage());
        System.out.println("Facility Type: " + accessor.getFacilityType());
        System.out.println("Circuit ID: " + accessor.getLsCircuitId());
        System.out.println("Defective PrCa: " + ssovo.getLhFidData("ASGM", "DEF"));
    }

	public void testIOE() throws ParserSvcException, DataAccessorException{
		String dataString =  "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIOE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n*IF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*IF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";
        logger = new TestLogger();
        aParserResponse = new ParseRequest();
        aParserResponse.setLogger(logger);
        aParserResponse.setFcifDataString(dataString);
        aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
        aParserResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
        aParserResponse.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);

        aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
       aParserSvc.processData(aParserResponse);
        ObjectPrinter objPrinter = new ObjectPrinter();
        System.out.println(aParserResponse.getFcifDataString());

        System.out.println("**************Get Accessor Values******************\n");
        SoacServiceOrderVO ssovo = (SoacServiceOrderVO) aParserResponse.getServiceOrderVo();

        SoacFcifDataAccessor accessor = new SoacFcifDataAccessor(ssovo);
        String tn = accessor.getTelephoneNumber();
        System.err.println("IOE TN : " + tn);
        assertEquals("Extract IOE TN", "217 381-0211", tn);
	}
	public void testIROE() throws ParserSvcException, DataAccessorException{
		String dataString =  "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIROE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n*IF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*IF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";
        logger = new TestLogger();
        aParserResponse = new ParseRequest();
        aParserResponse.setLogger(logger);
        aParserResponse.setFcifDataString(dataString);
        aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
        aParserResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
        aParserResponse.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);

        aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
       aParserSvc.processData(aParserResponse);
        ObjectPrinter objPrinter = new ObjectPrinter();
        System.out.println(aParserResponse.getFcifDataString());

        System.out.println("**************Get Accessor Values******************\n");
        SoacServiceOrderVO ssovo = (SoacServiceOrderVO) aParserResponse.getServiceOrderVo();

        SoacFcifDataAccessor accessor = new SoacFcifDataAccessor(ssovo);
        String tn = accessor.getTelephoneNumber();
        System.err.println("IROE TN : " + tn);
        assertEquals("Extract IROE TN", "217 381-0211", tn);
	}

	public void testIBOE() throws ParserSvcException, DataAccessorException{
		String dataString =  "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIBOE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n*IF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*IF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";
        logger = new TestLogger();
        aParserResponse = new ParseRequest();
        aParserResponse.setLogger(logger);
        aParserResponse.setFcifDataString(dataString);
        aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
        aParserResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
        aParserResponse.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);

        aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
       aParserSvc.processData(aParserResponse);
        ObjectPrinter objPrinter = new ObjectPrinter();
        System.out.println(aParserResponse.getFcifDataString());

        System.out.println("**************Get Accessor Values******************\n");
        SoacServiceOrderVO ssovo = (SoacServiceOrderVO) aParserResponse.getServiceOrderVo();

        SoacFcifDataAccessor accessor = new SoacFcifDataAccessor(ssovo);
        String tn = accessor.getTelephoneNumber();
        System.err.println("IBOE TN : " + tn);
        assertEquals("Extract IBOE TN", "217 381-0211", tn);
	}

	public void testIRBE() throws ParserSvcException, DataAccessorException{
		String dataString =  "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n---ASGM(B)\nG1   CLS AA.MCXX.101002..SW\nFA   102 SCHEELE DR/RT 1187/RZ 13\nIRBE   AA16-1-04-30/EXK 217 524/TN\n      217 381-0211/LPS/DF F30-09-\n      097G/CUC E ES\nG2   WC 479 442\n*IF1 /CA 25/PR 808/CUC E NREQ/DF\n     F23-10-06U03-1-08/PRQ Y/BP\n     108/OBP 5/TEA VRAD FB 1850\n     MORNINGSIDE DR; PXJ/TPR\n     118722/RMK RXJ TEA VRAD FB\n     1850 MORNINGSIDE DR BP 2\n     /OAB JAN\n*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n     73RMD/CUR E DVV8/BP 205/OBP\n     477/TEA FB 1850 MORNINGSIDE\n     DR; PXJ/RLC JSONOOOJ/RLA\n     VRAD AT 1850 MORNINGSIDE DR\n     /RMTE 17,476-500 BZ D433941-\n     2,D440783-4\n*IF3 /CA 1850/PR 477/BP 2/TEA R\n     106 SCHEELE DR; CDW/RMTE\n     LIGHTSPEED/RMK RXJ TEA FB\n     1850 MORNINGSIDE DR BP 202\n     /RMKP LIGHTSPEED\nDEF  OPN/CA 10/PR 1201\nDEF  OPN/CA 102R/PR 47\n*DEF OPN/CA 1850MFN/PR 2\n*DEF SHT/CA 1850/PR 490\n}%";
        logger = new TestLogger();
        aParserResponse = new ParseRequest();
        aParserResponse.setLogger(logger);
        aParserResponse.setFcifDataString(dataString);
        aParserResponse.setRegion(SoacServiceOrderConstants.SBCWEST);
        aParserResponse.setDataFormat(SoacServiceOrderConstants.SOAC_FCIF);
        aParserResponse.setOperationType(SoacServiceOrderConstants.SOAC_FCIF_TO_SOAC_VALUEOBJECT);

        aParserSvc = ParserSvcFactory.getFactory().getParserSvc(logger);
       aParserSvc.processData(aParserResponse);
        ObjectPrinter objPrinter = new ObjectPrinter();
        System.out.println(aParserResponse.getFcifDataString());

        System.out.println("**************Get Accessor Values******************\n");
        SoacServiceOrderVO ssovo = (SoacServiceOrderVO) aParserResponse.getServiceOrderVo();

        SoacFcifDataAccessor accessor = new SoacFcifDataAccessor(ssovo);
        String tn = accessor.getTelephoneNumber();
        System.err.println("IRBE TN : " + tn);
        assertEquals("Extract IRBE TN", "217 381-0211", tn);
	}

}


