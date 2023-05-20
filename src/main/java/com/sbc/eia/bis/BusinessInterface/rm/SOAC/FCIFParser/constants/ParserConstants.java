package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants;

import java.util.HashMap;
import java.util.Map;

/******************************************************************************
 * com.sbc.eia.srm.parsersvc.parser.constants
 * ============================================================================
 * File name: AbstractParserConstants
 * ============================================================================
 * Create Date: Dec 16, 2004
 * Create Time: 8:40:29 AM
 * ============================================================================
 * Author: Raj Sankuratri
 * UserID: @ns3580
 * ============================================================================
 * Class Purpose - ** Description and purpose of the Class in brief **
 * 
 * 
 *****************************************************************************/
public abstract class ParserConstants {
	// Region/Companies names.  These are common for all the constants classes
	public static final String SBCWEST = "SBCWEST";
	public static final String SBCSOUTHWEST = "SBCSOUTHWEST";
    public static final String SBCMIDWEST = "SBCMIDWEST";    
	public static final String SBCEAST = "SBCEAST";
    public static final String SOACSOUTHWEST = "SOACSOUTHWEST";
	public static final String CVOIP = "CVOIP";
	public static final String SBCSOUTHEAST = "SBCSOUTHEAST";
	
	public static final int SE_INT = 0;
	public static final int BILL_INT = 1;
	public static final int LIST_INT = 2;
	public static final int IDENT_INT = 3;
	public static final int CTL_INT = 4;
	public static final int ASGM_INT = 5;
	public static final int RMKS_INT = 6;

	public static final int BEGIN_SPACE_INDEX = 0;
	public static final String DASH = "-";

	public static final int USOC_WIDTH = 5;
	public int LHFID_WIDTH = 6;
	public int SE_LHFID_WIDTH = 6;
	public static final int LIST_FID_WIDTH = 5;
	private int totalSegments=0;

	/**
	 * Section names.  These will be set in the individual
	 * constants classes that inherit from ParserConstants.
	 */
	protected String CTL = null;
	protected String BILL = null;
	protected String ASGM = null;
	protected String RMKS = null;
	protected String TRAFFIC = null;
	protected String STAT = null;
	protected String LIST = null;
	protected String SE = null;
	protected String IDENT = null;
	protected String DIR = null;
	char orderTypeChar;
	

	/** 
	 * Order Type codes
	 * The values to these attributes will be set in the child classes
	 */

	/** 
	  * Status Code Indicators
	  */
	protected final static int ADD_PS = 0;
	protected final static int CHANGE_PS = 1;
	protected final static int RECAP_PS = 2;
	protected final static int REMOVE_PS = 3;
	protected final static int SUSPEND_PS = 4;
	protected final static int RESTORE_PS = 5;

	public static final String E_IDENT = "Identification";
	public static final String E_LIST = "Listing";
	public static final String E_CTL = "Control";
	public static final String E_TRAFFIC = "Traffic";
	public static final String E_SE = "Service & Equipment";
	public static final String E_BILL = "Billing";
	public static final String E_RMKS = "Remarks";
	public static final String E_STAT = "Statistics";
	public static final String E_ASGM = "Assignment";
	public static final String E_DIR = "Directory";

   /**
    * Static Data members
    * These are the values to be used when setting the
    * region parameter in the ParseRequest object
    */
   public static final String SW_REGION = "SBCSOUTHWEST";
   public static final String W_REGION = "SBCWEST";
   public static final String MW_REGION = "SBCMIDWEST";
   public static final String E_REGION = "SBCEAST";
   public static final String B_REGION = "SBCSOUTHEAST";
   
   /**
    * The static fields below indicate the type of
    * operation desired of the ParserSvc.  These
    * values are to be used to set the operationType
    * parameter.
    */
   public static final String SOAC_VALUEOBJECT_TO_SOAC_FCIF = "SOAC_VALUEOBJECT_TO_SOAC_FCIF";
   public static final String SOAC_FCIF_TO_SOAC_VALUEOBJECT = "SOAC_FCIF_TO_SOAC_VALUEOBJECT";
   public static final String STRINGARRAY_TO_PSR = "STRINGARRAY_TO_PSR";
   
   /**
    * Set the dataFormat field to SOAC_FCIF if the data is in 
    * SOAC FCIF format.
    */
   public static final String SOAC_FCIF = "SOAC_FCIF";
   
   /**
       * Set the dataFormat field to SOAC_VALUEOBJECT if the data is in 
       * SOAC VALUEOBJECT format.
       */
  public static final String SOAC_VALUEOBJECT = "SOAC_VALUEOBJECT";
   
   /**
    * Set the dataFormat field to POSTED_SERVICE_ORDER if the data is a 
    * Posted Service Order.
    */
   public static final String POSTED_SERVICE_ORDER = "POSTED_SERVICE_ORDER";
   
   /**
    * Set the dataFormat field to PENDING_SERVICE_ORDER if the data is a 
    * Posted Service Order.
    */
   public static final String PENDING_SERVICE_ORDER = "PENDING_SERVICE_ORDER";
   
   public static final String SPECIAL_SECTION_IND = "SPECIAL_SECTION_IND";
//	public static final String CVOIP_SNE_VALUEOBJECT = "CVOIP_SNE_VALUEOBJECT";

	/** 
	 * String manipulation constants
	 * These values will be set in the sub-classes
	*/

	public int QUANTITY_OFFSET = 5;

	//protected Hashtable enterpriseSectionNames = new Hashtable();
	private Map enterpriseSectionNames = new HashMap();
	/**
	 * 
	 */
	public ParserConstants() {
		super();
		// TODO Auto-generated constructor stub

	}

	public String convertToEnterpriseSectionName(String sectionIdentifier) {

		if (enterpriseSectionNames.containsKey(sectionIdentifier)) {
			return ((String) enterpriseSectionNames.get(sectionIdentifier));
		}
		//      if nothing matches return null
		return null;

	}

	/**
	 * @return
	 */
	public Map getEnterpriseSectionNames() {
		return enterpriseSectionNames;
	}

	public void setEnterpriseSectionNames() {
		enterpriseSectionNames.put(this.IDENT, E_IDENT);
		enterpriseSectionNames.put(this.LIST, E_LIST);
		enterpriseSectionNames.put(this.CTL, E_CTL);
		enterpriseSectionNames.put(this.TRAFFIC, E_TRAFFIC);
		enterpriseSectionNames.put(this.SE, E_SE);
		enterpriseSectionNames.put(this.BILL, E_BILL);
		enterpriseSectionNames.put(this.RMKS, E_RMKS);
		enterpriseSectionNames.put(this.STAT, E_STAT);
		enterpriseSectionNames.put(this.ASGM, E_ASGM);
		enterpriseSectionNames.put(this.DIR, E_DIR);
	}

	// Abstract methods that need to be implemented in the descendent classes
	public abstract void assignSectionNameValues();

	public abstract int translateActionCode(char argChar);
	public abstract int translateDefaultActionCode();
	public abstract int translateSectionActionCode(
		char firstLetterInLine,
		int sectionCode);
	/**
	 * Sets the orderTypeChar.
	 * @param orderTypeChar The orderTypeChar to set
	 */
	public void setOrderTypeChar(char orderTypeChar) {
		this.orderTypeChar = orderTypeChar;
	}

	public void setTotalSegments(int totalSegments) {
		this.totalSegments = totalSegments;
	}

	public int getTotalSegments() {
		return totalSegments;
	}
	

	     

}
