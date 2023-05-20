//$Id: SvcOrderResponseTester.java,v 1.3 2007/12/20 17:21:20 op1664 Exp $
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
//# 07/2006		Sriram Chevuturu      Creation

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;





import java.util.Hashtable;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.test.TestLogger;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SvcOrderResponseTester {
	


	public static void main(String args[])
	{
		Hashtable myProperties = new Hashtable();
		myProperties.put("STDOUT","TRUE");
		myProperties.put("BIS_NAME","RM13.0");
		myProperties.put("BIS_VERSION","13.0.1");
		myProperties.put("LOG_POLICY_PATH","");	
		myProperties.put("bis.platform","dev");						
		
		
		TestLogger myLogger= new TestLogger();		
		SOACServiceOrderResponseParser aSOACResponseParser = 
									new SOACServiceOrderResponseParser(myProperties, myLogger);		

		String response = null;
		//FTTP or RGPN
/*		 response = "*C2= PRESO C646230L2   A   214331SOAC    SOPRMDEV  061230    0;%*SC=      ;%*EC=1234567890 5678901234 RGPNS ;%*SN=FANK            ;%\n"+
"---ASGM\n"+
"G1   TN 913 432-0595\n"+
"FA   1749 SWFTTP DR/RT 4001/RZ 13\n"+
"IOE  NR/TN 913 432-0595/LPS/SEQN\n"+
"     1\n"+
"IRFV Z010-1-02-92/EXK 913 432\n"+
"     /REMT Y\n"+
"IECC RGNBWG60001/CCLA 0118.21-1+\n"+
"     7400 JOHNSON DR/CCLC\n"+
"     KSKYKSJOG0A/CMOD GNBWG6\n"+
"     /SEQN 2/TID\n"+
"     KSKYKSJOG0A010118211\n"+
"IRFV 1-292/ECC RGNBWG60001\n"+
"G2   WC 913 432\n"+
"IF1  /CA PON/PR NR/PRQ Y/TIDC\n"+
"     KSCYKSJOOL0010017181/TEA\n"+
"     KSCYKSJOOLT101/TPR 4001P1\n"+
"     /TEC KSCYKSJOOL0/CCLA 7400\n"+
"     JOHNSON DR/CMOD A7340/CCP 1-\n"+
"     5-1\n"+
"IF2  /CA PON/PR NR/CUD E ES/TEA\n"+
"     ONT00001 R1747 SWFTTP DR\n"+
"     FST; PNT/CCLA 1749 SWFTTP DR\n"+
"     /CMOD H-ONT-A/AIDI ONT-1-1-\n"+
"     5-1-19/INDX 19/TSRN\n"+
"     0100501019/CCP 1\n"+
"     /CA LT1/PR 9/TEC KSCYKSJOOL0\n"+
"     /CCLA 7400 JOHNSON DR/NPN 9\n"+
"     /APN 109/TEA FIBER FRAME;\n"+
"     EOX/RMTE FDF FOR JOINT\n"+
"     TESTING\n"+
"TF2  /CA OC032/PR 109/CCLA 1806\n"+
"     RIVER LAKES RD S/NPN 1/APN\n"+
"     16/TEA R1806 RIVER LAKES RD\n"+
"     S-1/RMTE SPLITTERS FOR\n"+
"     JOINT TEST\n"+
"TF3  /CA SPT1501/PR 16/CCLA 1806\n"+
"     RIVER LAKES RD/NPN 16/APN 49\n"+
"     /TEA R1806 RIVER LAKES RD S\n"+
"     PFP; POX/RMTE PFP FOR JOINT\n"+
"     TESTING\n"+
"TF4  /CA PON1806R/PR 49/NPN 1\n"+
"     /APN 1/TEA R1747 SWFTTP DR\n"+
"     FST\n"+
"TF5  /TEA ONT00001 R1747 SWFTTP\n"+
"     DR FST; POD";


	//FTTN SAI
	response = "*C2= PRESO C645550L2   A   214331SOAC    SOPRMDEV  063012    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FANK            ;%\n"+
"---ASGM\n"+
"G1   CLS AA.MCXX.101006..SW\n"+
"FA   4555 LA RUE ST/RZ 15\n"+
"IOE  NR\n"+
"G2   WC 214 331\n"+
"IF1  /CA 15/PR 1284/CUC E NREQ\n"+
"     /DF F31-04-07L06-4-09/PRQ Y\n"+
"     /BP 284/OBP 1804/TEA S 4688\n"+
"     TIMBER WOOD DR XBOX VRAD;\n"+
"     PXJ/TPR 431030/RMTE ETE\n"+
"     TESTING/RMK RXJ TEA S 4688\n"+
"     TIMBER WOOD DR XBOX BP 228\n"+
"IF2  /CA 4688TWFN/PR 4/PGSC U\n"+
"     /PGS 73RMB/CUR E DVV4/BP 504\n"+
"     /OBP 228/TEA S 4688 TIMBER\n"+
"     WOOD DR XBOX; PXJ/RLC\n"+
"     LIGHTSPEED/RLA STLSMO201123\n"+
"     /RMTE D484452\n"+
"IF3  /CA 4688T/PR 228/BP 8/TEA R\n"+
"     4544 WYOMING; CDW/RMKP\n"+
"     LIGHTSPEED\n";
*/		
	//	Defect Response Example

	response = "*C2= PRESO C910200L1   B   479442SOAC    SOPOMS    051030    0;%*SC=      ;%*EC=1234567890 5678901234 FTTNS ;%*SN=FUAU            ;%\n"+
"---ASGM(B)\n"+
"G1   CLS AA.MCXX.101002..SW\n"+
"FA   102 SCHEELE DR/RT 1187/RZ 13\n"+
"IOE  NR\n"+
"G2   WC 479 442\n"+
"*IF1 /CA 25/PR 808/CUC E NREQ/DF\n"+
"     F23-10-06U03-1-08/PRQ Y/BP\n"+
"     108/OBP 5/TEA VRAD FB 1850\n"+
"     MORNINGSIDE DR; PXJ/TPR\n"+
"     118722/RMK RXJ TEA VRAD FB\n"+
"     1850 MORNINGSIDE DR BP 2\n"+
"     /OAB JAN\n"+
"*IF2 /CA 1850ARR/PR 5/PGSC U/PGS\n"+
"     73RMD/CUR E DVV8/BP 205/OBP\n"+
"     477/TEA FB 1850 MORNINGSIDE\n"+
"     DR; PXJ/RLC JSONOOOJ/RLA\n"+
"     VRAD AT 1850 MORNINGSIDE DR\n"+
"     /RMTE 17,476-500 BZ D433941-\n"+
"     2,D440783-4\n"+
"*IF3 /CA 1850/PR 477/BP 2/TEA R\n"+
"     106 SCHEELE DR; CDW/RMTE\n"+
"     LIGHTSPEED/RMK RXJ TEA FB\n"+
"     1850 MORNINGSIDE DR BP 202\n"+
"     /RMKP LIGHTSPEED\n"+
"DEF  OPN/CA 10/PR 1201\n"+
"DEF  OPN/CA 102R/PR 47\n"+
"*DEF OPN/CA 1850MFN/PR 2\n"+
"*DEF OPN/CA 1860MFN/PR 2\n"+
"*DEF OPN/CA 1870MFN/PR 2\n"+
"*DEF SHT/CA 1850/PR 490\n";

		try {
			aSOACResponseParser.processFCIFResponseString(response);
		} catch (ParserSvcException e) {
		} catch (DataAccessorException e) {
		}

	}	

}
