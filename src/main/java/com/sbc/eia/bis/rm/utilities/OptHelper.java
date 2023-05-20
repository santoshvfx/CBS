package com.sbc.eia.bis.rm.utilities;

/**
 * @author jp2854
 *
 */
import com.sbc.eia.idl.bim_types.NameOpt;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.LocationOpt;
import com.sbc.eia.idl.nam_types.NetworkAddressOpt;
import com.sbc.eia.idl.rm_ls_types.BillingAccount2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.BillingAccountSeqOpt;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoop2SeqOpt;
import com.sbc.eia.idl.rm_ls_types.FacilityLoopSeqOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminalOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction2Opt;
import com.sbc.eia.idl.rm_ls_types.PendingServiceOrderSeqOpt;
import com.sbc.eia.idl.rm_ls_types.ResidentialGatewayOpt;
import com.sbc.eia.idl.rm_ls_types.ServiceItemSeqOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.rm_ls_types.VOIPOpt;
import com.sbc.eia.idl.sm_ls_types.ProductSubscriptionSeqOpt;
import com.sbc.eia.idl.types.AttributeTypeSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ExceptionDataSeqOpt;
import com.sbc.eia.idl.types.MeasurementOpt;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

public class OptHelper
{

    /**
     * Constructor for optHelper.
     */

    private OptHelper()
    {
        super();
    }
    

    public static boolean isStringOptEmpty(StringOpt stringOpt)
    {
        try
        {
            stringOpt.theValue();
            
            if (stringOpt.theValue().trim().length() >= 1) 
                return false;

        } 
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }
        
        return true;

    }
    
    
    public static boolean isStringSeqOptEmpty(StringSeqOpt stringSeqOpt)
    {
        try
        {
            stringSeqOpt.theValue();

            if (stringSeqOpt.theValue().length >= 1) 
                return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;

    }

    public static boolean isTelephoneNumberOrderPairSeqOptEmpty(TelephoneNumberOrderPairSeqOpt telephoneNumberPairSeqOpt)
    {
        try
        {
            telephoneNumberPairSeqOpt.theValue();

            if (telephoneNumberPairSeqOpt.theValue().length >= 1) 
                return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;

    }
    
    public static boolean isTelephoneNumberOrderOptEmpty(TelephoneNumberOrderOpt telephoneNumberOrderOpt)
    {
        try
        {
            telephoneNumberOrderOpt.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;

    }

    public static boolean isMeasurementOptNull(MeasurementOpt measurementOpt)
    {
        try
        {
            measurementOpt.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {

        }

        return true;

    }


    public static boolean isEiaDateOptNull(EiaDateOpt aEiaDateOpt)
    {
        try
        {
            aEiaDateOpt.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;        
    }
    
    public static boolean isLocationOptNull(LocationOpt aLocationOpt)
    {
        try
        {
            aLocationOpt.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;        
    }
    
    public static boolean isAddressOptNull(AddressOpt aAddressOpt)
    {
        try
        {
            aAddressOpt.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;        
    }

    public static boolean isDSLAMTransportOptNull(DSLAMTransportOpt aDSLAMTransport)
    {
        try
        {
            aDSLAMTransport.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;        
    }

    public static boolean isOpticalLineTerminalOptNull(OpticalLineTerminalOpt aOLT)
    {
        try
        {
            aOLT.theValue();
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;        
    }

    public static boolean isVOIPOptNull(VOIPOpt voipOpt)    {
        try {
            voipOpt.theValue();
            return false;

        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {
        } 
        catch (NullPointerException e)  {
        }
        return true;
    }
    
    public static boolean isNetworkAddressOptEmpty(NetworkAddressOpt networkAddressOpt)
    {
        try
        {
            networkAddressOpt.theValue();
            return false;           
        } catch (org.omg.CORBA.BAD_OPERATION e) {
        } catch (NullPointerException e) {
        }
        return true;
    }
    
    public static boolean isRGOptNull(ResidentialGatewayOpt rgOpt)    {
        try {
            rgOpt.theValue();
            return false;

        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {
        } 
        catch (NullPointerException e)  {
        }
        return true;
    }

    public static boolean isCopperSegmentSeqOptEmpty(CopperSegmentSeqOpt copperSegmentSeqOpt)  {
        try  {
            copperSegmentSeqOpt.theValue();
            if (copperSegmentSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  {
        } 
        catch (NullPointerException e) {
        }
        return true;
    }

    public static boolean isFiberSegmentSeqOptEmpty(FiberSegmentSeqOpt fiberSegmentSeqOpt)  {
        try  {
            fiberSegmentSeqOpt.theValue();
            if (fiberSegmentSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  {
        } 
        catch (NullPointerException e) {
        }
        return true;
    }

    public static boolean isOpticalNetworkTerminalOptNull(OpticalNetworkTerminalOpt aONT) {
        try {
            aONT.theValue();
            return false;
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {
        } 
        catch (NullPointerException e) {
        }
        return true;        
    }
    
    public static boolean isBooleanOptEmpty(BooleanOpt booleanOpt)  {
        try  {
            booleanOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  {
        } 
        catch (NullPointerException e) {
        }
        return true;
    }
    
    public static boolean isProductSubscriptionSeqOptEmpty(ProductSubscriptionSeqOpt aProductSubscriptionSeqOpt)
    {
        try
        {
            aProductSubscriptionSeqOpt.theValue();

            if ((aProductSubscriptionSeqOpt.theValue().length >= 1) 
                || (aProductSubscriptionSeqOpt.theValue() == null))
                
                return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;
    }
    
    public static boolean isNameOptEmpty(NameOpt aCustomerName)
    {
        try
        {
            aCustomerName.theValue();           
            return false;

        } catch (org.omg.CORBA.BAD_OPERATION e)
        {
        } catch (NullPointerException e)
        {
        }

        return true;
    }    
    
    public static boolean isStringEmpty(String string) 
    {
        try
        {
            if (string != null && string.trim().length() > 0)
                return false;
        } catch (NullPointerException e)
        {
        }   
        
        return true;
    }

    /**
     * Check if ServiceItemSeqOpt is empty.
     * 
     * @param ServiceItemSeqOpt aServiceItemSeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isServiceItemSeqOptEmpty(ServiceItemSeqOpt aServiceItemSeqOpt)  
    {
        try  
        {
            aServiceItemSeqOpt.theValue();
            if (aServiceItemSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }

    /**
     * Check if PendingServiceOrderSeqOpt is empty.
     * 
     * @param PendingServiceOrderSeqOpt aPendingServiceOrderSeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isPendingServiceOrderSeqOptEmpty(PendingServiceOrderSeqOpt aPendingServiceOrderSeqOpt)  
    {
        try  
        {
            aPendingServiceOrderSeqOpt.theValue();
            if (aPendingServiceOrderSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
   
    /**
     * Check if FacilityLoopSeqOpt is empty.
     * 
     * @param FacilityLoopSeqOpt aFacilityLoopSeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isFacilityLoopSeqOptEmpty(FacilityLoopSeqOpt aFacilityLoopSeqOpt)  
    {
        try  
        {
            aFacilityLoopSeqOpt.theValue();
            if (aFacilityLoopSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }

    /**
     * Check if FacilityLoop2SeqOpt is empty.
     * 
     * @param FacilityLoop2SeqOpt aFacilityLoop2SeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isFacilityLoop2SeqOptEmpty(FacilityLoop2SeqOpt aFacilityLoop2SeqOpt)  
    {
        try  
        {
            aFacilityLoop2SeqOpt.theValue();
            if (aFacilityLoop2SeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }

    /**
     * Check if BillingAccountSeqOpt is empty.
     * 
     * @param BillingAccountSeqOpt aBillingAccountSeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isBillingAccountSeqOptEmpty(BillingAccountSeqOpt aBillingAccountSeqOpt)  
    {
        try  
        {
            aBillingAccountSeqOpt.theValue();
            if (aBillingAccountSeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }

    /**
     * Check if BillingAccount2SeqOpt is empty.
     * 
     * @param BillingAccount2SeqOpt aBillingAccount2SeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isBillingAccount2SeqOptEmpty(BillingAccount2SeqOpt aBillingAccount2SeqOpt)  
    {
        try  
        {
            aBillingAccount2SeqOpt.theValue();
            if (aBillingAccount2SeqOpt.theValue().length >= 1) 
                return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    
    /**
     * Check if ShortOpt is empty.
     * 
     * @param ShortOpt aShortOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isShortOptEmpty(ShortOpt aShortOpt)  
    {
        try  
        {
            aShortOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    
    /**
     * Check if ExceptionDataOpt is empty.
     * 
     * @param ExceptionDataOpt aExceptionDataOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isExceptionDataOptEmpty(ExceptionDataOpt aExceptionDataOpt)  
    {
        try  
        {
            aExceptionDataOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    
    public static boolean isExceptionDataSeqOptEmpty(ExceptionDataSeqOpt aExceptionDataSeqOpt)  
    {
        try  
        {
            aExceptionDataSeqOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    /**
     * Check if ObjectPropertySeqOpt is empty.
     * 
     * @param ObjectPropertySeqOpt aObjectPropertySeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isObjectPropertySeqOptEmpty(ObjectPropertySeqOpt aObjectPropertySeqOpt)  
    {
        try  
        {
            aObjectPropertySeqOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    
    /**
     * Check if ObjectKeyOpt is empty.
     * 
     * @param ObjectKeyOpt aObjectKeyOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isObjectKeyOptEmpty(ObjectKeyOpt aObjectKeyOpt)  
    {
        try  
        {
            aObjectKeyOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }

    /**
     * Check if AttributeTypeSeqOpt is empty.
     * 
     * @param AttributeTypeSeqOpt aAttributeTypeSeqOpt
     * @return boolean (true/false)
     * 
     * @author Rene Duka
     */
    public static boolean isAttributeTypeSeqOptEmpty(AttributeTypeSeqOpt aAttributeTypeSeqOpt)  
    {
        try  
        {
            aAttributeTypeSeqOpt.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
    
    public static boolean isOrderAction2OptNull(OrderAction2Opt aOrderAction2)  
    {
        try  
        {
        	aOrderAction2.theValue();
            return false;
        }
        catch (org.omg.CORBA.BAD_OPERATION e)  
        {
        } 
        catch (NullPointerException e) 
        {
        }
        return true;
    }
}
