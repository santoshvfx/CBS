package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser;

import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.ParseRequest;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.assembler.strategy.AssemblerStrategy;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.ParserConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.parser.strategy.WestSoacFcifServiceOrderParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.DefectFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.FloatedFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.LhFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SectionVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SoacServiceOrderVO;
/**
============================================================================
File name: ServiceOrderParser
============================================================================
Author: Raj Sankuratri
UserID: @ns3580

1/10/2008 Ott Phannavong		  LS6 MFI: C464733L8: Added a call to getSpaceOffset so that is not always 5.
3/06/2009 Lee David 			  LS10: PR 24033372:  Parser stopped processing for FID RFV. SOAC Parser needs to ignore unrecognizable left hand FID and continues
============================================================================
*/

// Changed the class ServiceOrderPaser from an Abstract Class to a non-abstract class
// The logic in the ServiceOrderPaser can be applied for all the regions, hence bypassing the ParserStartegy logic to create
// an instance from the ServicOrderParser class and run the required method.

public class ServiceOrderParser implements ParserStrategy {
	private String newLine = SoacServiceOrderConstants.NEWLINE;
	private int newLineOffset = 1;
	private int fieldedStringLength = 125;
	private int nonFieldedStringStart = fieldedStringLength + newLineOffset;

	protected AssemblerStrategy assembler = null;
	protected Logger logger;
	protected ParseRequest request;
	protected SoacServiceOrderVO ssovo = null;
	protected SectionVO sectionVo;

	protected ParserConstants parserConstants;
	protected String currentSection;
	protected static String defaultSection = "Identification";
	private static int totalSegments = 0;

	/**
	@roseuid 41D032AA02C6
	 */
	public ServiceOrderParser() {
		super();
	}

	/**
	@roseuid 41D032AA03C0
	 */
	public void init() throws ParserSvcException {
		this.setLogger(request.getLogger());
		assembler = request.getAssemblerStrategy();
	}

	public LhFidVO assembleLhFidVo(String lhFidString) {
		if (lhFidString.length() < 5) {
			//this is an exception situation because in some cases I
			//saw the response message ending with a "\r\n" followed by
			//"}%", which is wrong and should not happen according to the IA
			return null;
		}

		String fidName = lhFidString.substring(0, 5).trim();
		if (fidName.compareToIgnoreCase("DEF") == 0) {
			//If the defective FID "DEF" is not proceeded by an asterisk
			//then do not process the record, because this is old cable
			//and pair defect information from a prior assignment response.
			return null;
		}

		if (fidName.startsWith("*")) {
			// remove the "*" from the name
			fidName = fidName.substring(1);
		}

		if ((fidName.substring(0, 2).equals("IF"))
			|| (fidName.substring(0, 2).equals("OF"))
			|| (fidName.substring(0, 2).equals("TF"))) {
			totalSegments++;
		}

		LhFidVO lhFidVo = new LhFidVO(fidName);
		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			"\tCreating LhFidVO: name = [" + fidName + "]");
		if ((fidName.substring(0, 2).equals("IF"))
					|| (fidName.substring(0, 2).equals("OF"))
					|| (fidName.substring(0, 2).equals("TF"))) {
			ssovo.setActionIndicator(fidName.substring(0, 1));
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				"\tFacility Action = [" + ssovo.getActionIndicator() + "]");
			lhFidVo.setFacilityNumber(Integer.parseInt(fidName.substring(2)));

			// Check and set the fMax value only if it is less than the
			// latest value that was parsed
			if (ssovo.getMaxFacility()
				< Integer.parseInt(fidName.substring(2))) {
				ssovo.setMaxFacility(Integer.parseInt(fidName.substring(2)));
				logger.log(
					LogEventId.DEBUG_LEVEL_2,
					"\tMax Facility number = [" + ssovo.getMaxFacility() + "]");
			}
		} else {
			String quantityString =
				lhFidString.substring(
					lhFidString.indexOf(" ") - 1,
					lhFidString.indexOf(" "));
			try {
				int quantity = Integer.parseInt(quantityString);
				lhFidVo.setQuantity(quantity);
			} catch (NumberFormatException nfe) {
				//logger.log(LogEventId.ERROR, "Error converting " + quantityString + " to int for quantity.");
				lhFidVo.setQuantity(-1);
			}
		}
		if (lhFidString.indexOf("/") > 0) {
			// check if the fid data is present and then call the setData method
			int spaceIndex=getSpaceOffset(lhFidString);
			if (!((lhFidString.substring(spaceIndex, lhFidString.indexOf("/")))
				.equals(""))) {
				lhFidVo.setFidData(
					lhFidString.substring(spaceIndex, lhFidString.indexOf("/")));
				logger.log(
					LogEventId.DEBUG_LEVEL_2,
					"\tLhFidVO: data = ["
						+ lhFidString.substring(spaceIndex, lhFidString.indexOf("/"))
						+ "]");
			}

			// Remove the part with the LHFid name and its data
			lhFidString = lhFidString.substring(lhFidString.indexOf("/") + 1);

			StringTokenizer tokenizer = new StringTokenizer(lhFidString, "/");

			FloatedFidVO ffvo = null;

			while (tokenizer.hasMoreTokens()) {
				String ffString = tokenizer.nextToken();

				if (ffString.indexOf(" ") > 0)
					// check if the floated fid has trailing data
					{
					String ffName =
						ffString.substring(0, ffString.indexOf(" "));
					String ffData =
						ffString.substring(ffString.indexOf(" ") + 1);
					// check and limit the data till the ";" character
					if (ffData.indexOf(";") > 0) {
						ffData = ffData.substring(0, ffData.indexOf(";"));
					}
					ffvo = new FloatedFidVO(ffName, ffData);

					logger.log(
						LogEventId.DEBUG_LEVEL_2,
						"\t\tCreating FloatedFidVO: name = ["
							+ ffName
							+ "] data = ["
							+ ffData
							+ "]");
				} else {
					ffvo = new FloatedFidVO(ffString, "");
				}
				lhFidVo.addFloatedFidVo(ffvo);
			}
		} else {
			String dataString = null;
			if (lhFidString.length() <= 5){
				dataString = null;
			}else{
				dataString = lhFidString.substring(getSpaceOffset(lhFidString));
			}
			lhFidVo.setFidData(dataString);
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				"\tLhFidVO: data = [" + dataString + "]");
		}

		return lhFidVo;
	}

	public SectionVO assembleSectionVo(String sectionString) {
		// Check and process the sectionName
		String sectionName =
			sectionString.substring(0, sectionString.indexOf(newLine));
		char sectionSuffix = 'X'; // Just to initialize the char variable

		if (sectionName.endsWith(")")) {
			sectionSuffix = sectionName.charAt(8);
			sectionName = sectionName.substring(0, 7);

		}

		sectionVo = new SectionVO(sectionName);
		sectionVo.setSectionSuffix(sectionSuffix);

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			"******* Creating SectionVO: name = [" + sectionName + "]");

		// Remove the section name and the new line (---ABCD\n)
		// and prune the line continuations
		String prunedString =
			pruneLineContinuations(
				sectionString.substring(
					sectionString.indexOf(newLine) + newLineOffset));

		String[] lhFidLines = separateNewLines(prunedString);

		for (int i = 0; i < lhFidLines.length; i++) {
			sectionVo.addLhFidVo(assembleLhFidVo(lhFidLines[i]));
			sectionVo.addDefectFidVo(assembleDefectFidVo(lhFidLines[i]));
		}

		SoacServiceOrderConstants soac = new SoacServiceOrderConstants();
		soac.setTotalSegments(totalSegments);
		//		System.out.println("Total Segments = " + totalSegments);

		return sectionVo;
	}

	/**
	@param newLine
	@return boolean
	@roseuid 41DF02C101CD
	 */
	public boolean isNewSection(String newLine) {
		boolean answer = false;
		if (parserConstants
			.getEnterpriseSectionNames()
			.containsKey(newLine.trim())) {
			answer = true;
			currentSection =
				parserConstants.convertToEnterpriseSectionName(newLine.trim());
		} else if (newLine.trim().startsWith(ParserConstants.DASH)) {
			answer = false;
		}

		return answer;
	}

	/**
	 * @param sectionString
	 */
	protected void processFieldedSection(String sectionString)
		throws ParserSvcException {
		int statusStart = sectionString.indexOf("SN=") + 3;
		int statusEnd = statusStart + 4;

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.FUNCTION_TYPE
				+ ": "
				+ sectionString.substring(5, 8));
		ssovo.put(
			SoacServiceOrderConstants.FUNCTION_TYPE,
			sectionString.substring(5, 8));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM
				+ ": "
				+ sectionString.substring(11, 21).trim());
		ssovo.put(
			SoacServiceOrderConstants.SOAC_SERVICE_ORDER_NUM,
			sectionString.substring(11, 21).trim());

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.CORRECTION_SUFFIX
				+ ": "
				+ sectionString.substring(23, 24));
		ssovo.put(
			SoacServiceOrderConstants.CORRECTION_SUFFIX,
			sectionString.substring(23, 24));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX
				+ ": "
				+ sectionString.substring(27, 33));
		ssovo.put(
			SoacServiceOrderConstants.WIRE_CENTER_OR_NPANXX,
			sectionString.substring(27, 33));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.ORIGINATING_SYSTEM_NAME
				+ ": "
				+ sectionString.substring(33, 37));
		ssovo.put(
			SoacServiceOrderConstants.ORIGINATING_SYSTEM_NAME,
			sectionString.substring(33, 37));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.ENTITY_PLATFORM
				+ ": "
				+ sectionString.substring(37, 39));
		ssovo.put(
			SoacServiceOrderConstants.ENTITY_PLATFORM,
			sectionString.substring(37, 39));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.ENTITY
				+ ": "
				+ sectionString.substring(40, 41));
		ssovo.put(
			SoacServiceOrderConstants.ENTITY,
			sectionString.substring(40, 41));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.RECEIVING_HOST_NAME
				+ ": "
				+ sectionString.substring(44, 49));
		ssovo.put(
			SoacServiceOrderConstants.RECEIVING_HOST_NAME,
			sectionString.substring(44, 49));

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.DUE_DATE
				+ ": "
				+ sectionString.substring(51, 57));
		ssovo.put(
			SoacServiceOrderConstants.DUE_DATE,
			sectionString.substring(51, 57));

		String echoSection = "*EC";
		if (sectionString.indexOf(echoSection) > 0) {
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				SoacServiceOrderConstants.OMS_ORDER_NUM
					+ ": "
					+ sectionString.substring(80, 90));
			ssovo.put(
				SoacServiceOrderConstants.OMS_ORDER_NUM,
				sectionString.substring(80, 90));

			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM
					+ ": "
					+ sectionString.substring(91, 101));
			ssovo.put(
				SoacServiceOrderConstants.OMS_ORDER_ACTION_NUM,
				sectionString.substring(91, 101));

			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				SoacServiceOrderConstants.NETWORK_TYPE
					+ ": "
					+ sectionString.substring(102, 106));
			ssovo.put(
				SoacServiceOrderConstants.NETWORK_TYPE,
				sectionString.substring(102, 106));
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				SoacServiceOrderConstants.REGION_INDICATOR
					+ ": "
					+ sectionString.substring(106, 107));
			ssovo.put(
				SoacServiceOrderConstants.REGION_INDICATOR,
				sectionString.substring(106, 107));
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				SoacServiceOrderConstants.RESEND_INDICATOR
					+ ": "
					+ sectionString.substring(107, 108));
			ssovo.put(
				SoacServiceOrderConstants.RESEND_INDICATOR,
				sectionString.substring(107, 108));
			logger.log(
							LogEventId.DEBUG_LEVEL_2,
							SoacServiceOrderConstants.APPLICATION_INDICATOR
								+ ": "
								+ sectionString.substring(108, 109));
				ssovo.put(
							SoacServiceOrderConstants.APPLICATION_INDICATOR,
							sectionString.substring(108, 109));
		} else {
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				"Echo section is not present in the response string...\n");
		}

		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			SoacServiceOrderConstants.STATUS_CODE
				+ ": "
				+ sectionString.substring(statusStart, statusEnd));

		ssovo.put(
			SoacServiceOrderConstants.STATUS_CODE,
			sectionString.substring(statusStart, statusEnd));
	}

	/**
	 * @param string, ParseRequest
	 */
	protected String processSection(
		String sectionString,
		ParseRequest request) {
		// Check for the ---ASGM section string
		if (sectionString
			.indexOf(SoacServiceOrderConstants.ASSIGNMENT_SECTION_TAG)
			>= 0) {
			// process the assignmentSection
			sectionString =
				sectionString.substring(
					sectionString.indexOf(
						SoacServiceOrderConstants.ASSIGNMENT_SECTION_TAG));

			String sectionLabel =
				sectionString.substring(3, sectionString.indexOf(newLine));

			if (sectionLabel.endsWith(")")) {
				sectionLabel = sectionLabel.substring(0, 4);
			}
			ssovo.put(sectionLabel, assembleSectionVo(sectionString));

			//set assignment section
			request.setAssignmentString(sectionString);
		}

		// Check for the ---ERR section string
		if (sectionString.indexOf(SoacServiceOrderConstants.ERROR_SECTION_TAG)
			>= 0) {
			// process the errorSection
			String errorString =
				sectionString.substring(
					sectionString.indexOf(
						SoacServiceOrderConstants.ERROR_SECTION_TAG));

			ssovo.put(SoacServiceOrderConstants.ERR_MSG, errorString);
		}

		return sectionString;
	}

	/**
	 * @param string
	 * @return
	 */
	protected String pruneLineContinuations(String rawString) {
		/**
		 * Tokenize on newLine
		 * recursively remove the RESP_LINE_CONTINUATION from the string
		 */
		int spaceOffset = getSpaceOffset(rawString);
		int pointer = 0;

		while ((rawString
			.indexOf(SoacServiceOrderConstants.RESP_LINE_CONTINUATION)
			> 0)) 
		{
			int offset =
				rawString.indexOf(
					SoacServiceOrderConstants.RESP_LINE_CONTINUATION,
					pointer)
					- newLineOffset;

			if (rawString.startsWith(newLine, offset)) 
			{
				rawString =
					(rawString
						.substring(
							0,
							rawString.indexOf(
								SoacServiceOrderConstants
									.RESP_LINE_CONTINUATION)
								- newLineOffset))
						+ rawString.substring(
							rawString.indexOf(
								SoacServiceOrderConstants
									.RESP_LINE_CONTINUATION)
								+ spaceOffset);
			} 
			//Added else if block to fix hung thread when "\n" character is missing
			else if(!rawString.startsWith(newLine, offset) && rawString.indexOf(SoacServiceOrderConstants.RESP_LINE_CONTINUATION) > 0)
			{
				rawString =
					(rawString
						.substring(
							0,
							rawString.indexOf(
								SoacServiceOrderConstants
									.RESP_LINE_CONTINUATION)))
						+ rawString.substring(
							rawString.indexOf(
								SoacServiceOrderConstants
									.RESP_LINE_CONTINUATION)
								+ spaceOffset);
			}
			else 
			{
				pointer = offset + 2;
			}
			logger.log(
					LogEventId.DEBUG_LEVEL_2, "Raw string: " + 
					rawString);
		}
		return rawString;
	}

	public void setConstants(ParserConstants parserConstants) {
		this.parserConstants = parserConstants;
	}

	private String[] separateNewLines(String dataString) {
		StringTokenizer st = new StringTokenizer(dataString, newLine);

		int tokCount = st.countTokens();

		String[] dataArray = new String[tokCount];
		String singleLine = "";

		for (int i = 0; i < tokCount; i++) {
			singleLine = st.nextToken();
			dataArray[i] = singleLine;
		}

		return dataArray;
	}

	/**
	   @param logger
	   @roseuid 41DF028401AE
		*/
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @param string
	 */
	protected void setNewLineCharacter(String dataString) {
		// the default newLine is '\n' so we don't need to
		// check for that.
		if ((dataString.indexOf('\r') > 0) && (dataString.indexOf('\n') > 0)) {
			this.newLine = SoacServiceOrderConstants.SLASH_R_SLASH_N;
			this.newLineOffset = 2; // need to account for the two characters
			this.nonFieldedStringStart = fieldedStringLength + newLineOffset;
		}
	}

	protected void setSoacServiceOrderVO() {
		ssovo = new SoacServiceOrderVO();
	}

	public DefectFidVO assembleDefectFidVo(String defectFidString) {

		DefectFidVO dFidVo = null;
		if (defectFidString.length() < 5) {
			//this is an exception situation because in some cases I
			//saw the response message ending with a "\r\n" followed by
			//"}%", which is wrong and should not happen according to the IA
			return null;
		}
		String fidName = defectFidString.substring(0, 5).trim().toString();
		if (fidName.equalsIgnoreCase("*DEF")) {

			dFidVo = new DefectFidVO();
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				"\tCreating DefectFdVO: name = [" + fidName + "]");

			if (defectFidString.indexOf("/") > 0) {
				// check if the fid data is present and then call the setData method
				if (!((defectFidString
					.substring(5, defectFidString.indexOf("/")))
					.equals(""))) {
					dFidVo.setDefectCode(
						defectFidString.substring(
							5,
							defectFidString.indexOf("/")));
					logger.log(
						LogEventId.DEBUG_LEVEL_2,
						"\tDefectFidVO: Code = ["
							+ defectFidString.substring(
								5,
								defectFidString.indexOf("/"))
							+ "]");
				}

				// Remove the part with the LHFid name and its data
				defectFidString =
					defectFidString.substring(defectFidString.indexOf("/") + 1);

				StringTokenizer tokenizer =
					new StringTokenizer(defectFidString, "/");

				//				FloatedFidVO ffvo = null;

				while (tokenizer.hasMoreTokens()) {
					String ffString = tokenizer.nextToken();

					if (ffString.indexOf(" ") > 0)
						// check if the floated fid has trailing data
						{
						String ffName =
							ffString.substring(0, ffString.indexOf(" "));
						String ffData =
							ffString.substring(ffString.indexOf(" ") + 1);
						// check and limit the data till the ";" character
						if (ffData.indexOf(";") > 0) {
							ffData = ffData.substring(0, ffData.indexOf(";"));
						}

						//						ffvo = new FloatedFidVO(ffName, ffData);

						logger.log(
							LogEventId.DEBUG_LEVEL_2,
							"\t\tCreating FloatedFidVO: name = ["
								+ ffName
								+ "] data = ["
								+ ffData
								+ "]");
						if (ffName.equalsIgnoreCase("CA")) {
							dFidVo.setDefectCable(ffData);
						} else if (ffName.equalsIgnoreCase("PR")) {
							dFidVo.setDefectPair(ffData);
						}
					}

				}
			}

		}
		return dFidVo;
	}

	// Calcute SpaceOffset based on the order.  Cahnged with PR 18576712
	// If the 6 th character is not space, Fid data starts at 6 th character, so spaceOffset is 5
	// if the 6 th character is space, Fid data starts at 7 th character, so spaceOffset is 6

	private int getSpaceOffset(String string) {
		int i;
		char space = 32;
		if (string.charAt(5) != space)
			i = 5;
		else
			i = 6;

		return i;
	}

	private int getSpaceOffsetBasedOnRegion() {
		if (this instanceof WestSoacFcifServiceOrderParser)
			return 6;
		else
			return 5;

	}

	public void processData(ParseRequest request) throws ParserSvcException
		{
			this.logger = request.getLogger();
			String sectionString = null;
			setSoacServiceOrderVO();

			// Process the header section
			if (request.getFcifDataString().length() < 111)
			{
				throw new ParserSvcException("Invalid data string length for parsing.");
			}

			// Check and set the newline character
			setNewLineCharacter(request.getFcifDataString());

			int firstNewLinePosition = request.getFcifDataString().indexOf(newLine);
			String fieldedString = request.getFcifDataString().substring(0, firstNewLinePosition);
			logger.log(
				LogEventId.DEBUG_LEVEL_2,
				"Fielded String: " + fieldedString);
			processFieldedSection(fieldedString);

			int stringLength = request.getFcifDataString().length();
			if (stringLength >= nonFieldedStringStart)
			{
//				String tripleDash = "---";
//				String nonFieldedString =
//					request.getFcifDataString().substring(request.getFcifDataString().indexOf(tripleDash));

				String nonFieldedString = request.getFcifDataString();

				logger.log(
					LogEventId.DEBUG_LEVEL_2,
					"NonFielded String: " + nonFieldedString);

				sectionString = processSection(nonFieldedString, request);
			}
			else
			{
				throw new ParserSvcException("Invalid data string length for parsing.");
			}

			request.setServiceOrderVo(ssovo);

			// set the nonFielded string in the request object
			// for client use
			request.setFcifDataString(sectionString);
		}
}