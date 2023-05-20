package com.att.lms.bis.service;

import com.att.transformation.BaseJUnit5Test;
import com.sbc.eia.bis.RmNam.utilities.NAMClient.NAMClient;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.nam.GetNetworkAddressReturn;
import com.sbc.eia.idl.nam.NamFacade;
import com.sbc.eia.idl.sm.SmFacade;
import com.sbc.eia.idl.sm.SubscriptionAccountReturn;
import com.sbc.eia.idl.sm_types.Affiliate;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationType;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationTypeOpt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.StringOpt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RmBisToBisTest extends BaseJUnit5Test {

    @Autowired
    private RmLegacyService rmLegacyService;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("bis.platform", "NON271SAT");
    }

    @Test
    public void testNamGetNetworkAddress() throws Exception {
        String EXPECTED_RESPONSE =
            "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"NAMDEV\"},{\"aTag\":\"CustomerName\",\"aValue\":\"NAMDEV\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"lmsnambis-7cdbc8dc6f-gbstw165485098458993600021|SEQ:0\"}]},\"aNetworkAddress\":{\"aNetworkAddress\":{\"aValue\":\"3146211015\",\"aKind\":\"\"},\"aNetworkAddressType\":\"TelephoneNumber\",\"aServiceArea\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNetworkType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aProperties\":{\"___theValue\":[{\"aTag\":\"TelephoneNumber_Pooled_Status\",\"aValue\":\"false\"}],\"__discriminator\":true,\"__uninitialized\":false}}}";

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("NAMDEV");
        bcm.setCustomerName("NAMDEV");
        BisContext bisContext = bcm.getBisContext();

        NAMClient namClient = new NAMClient();
        NamFacade namBean = namClient.getNamConnection(bcm.getBisContext(),rmLegacyService,"","");

        GetNetworkAddressReturn response = namBean.getNetworkAddress(bisContext, "3146211015");
        assertEquals(EXPECTED_RESPONSE, parseResponse(response));
    }

    @Test
    @Disabled
    public void testSmRetrieveSubscriptionAccountsForService() throws Exception{
        String EXPECTED_RESPONSE =
                "{\"aContext\":{\"aProperties\":[{\"aTag\":\"CustomerName\",\"aValue\":\"LSP-WEST\"},{\"aTag\":\"Application\",\"aValue\":\"SM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"KS\"},{\"aTag\":\"UserID\",\"aValue\":\"rb4129\"},{\"aTag\":\"ServiceProviderSystemRegion\",\"aValue\":\"IMSR2\"},{\"aTag\":\"ServiceProvider\",\"aValue\":\"Southwestern Bell\"},{\"aTag\":\"ServiceProviderSystem\",\"aValue\":\"CRIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]}],\"aExceptionData\":[]}";

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("SM_BIS");
        bcm.setCustomerName("SM_BIS");
        BisContext context = bcm.getBisContext();

        String wtn = "9133850136";

        ObjectKey aObjectKey = new ObjectKey(wtn, BisTypesObjectKeyKind.WORKINGTELEPHONENUMBER);

        StringOpt aSortingOrder = new StringOpt();
        aSortingOrder.__default();

        EiaDateOpt dateOpt = new EiaDateOpt();
        dateOpt.__default();

        SmClient smClient = new SmClient();
        SmFacade smBean = smClient.getSmBean(context, rmLegacyService);

        SubscriptionAccountReturn response = smBean.retrieveSubscriptionAccountsForService(
                context,
                aObjectKey,
                Affiliate.INTERNET,
                SubscriptionAccountInformationType.Both,
                aSortingOrder,
                dateOpt);

        Assertions.assertEquals(parseResponse(response), EXPECTED_RESPONSE);
    }


    @Test
    @Disabled
    public void testSmRetrieveSubscriptionAccountsForAffiliatesByAccountNumber() throws Exception{
        String EXPECTED_RESPONSE =
                "{\"aContext\":{\"aProperties\":[{\"aTag\":\"CustomerName\",\"aValue\":\"LSP-WEST\"},{\"aTag\":\"Application\",\"aValue\":\"SM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"KS\"},{\"aTag\":\"UserID\",\"aValue\":\"rb4129\"},{\"aTag\":\"ServiceProviderSystemRegion\",\"aValue\":\"IMSR2\"},{\"aTag\":\"ServiceProvider\",\"aValue\":\"Southwestern Bell\"},{\"aTag\":\"ServiceProviderSystem\",\"aValue\":\"CRIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]}],\"aExceptionData\":[]}";

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("SM_BIS");
        bcm.setCustomerName("SM_BIS");
        BisContext context = bcm.getBisContext();
        ObjectKey aBillingAccountHandle = new ObjectKey("2172224869", BisTypesObjectKeyKind.BILLINGTELEPHONENUMBER);

        StringOpt aZipCode = new StringOpt();
        aZipCode.__default();

        String[] aAffiliates = new String[1];
        aAffiliates[0] = Affiliate.TELCO;

        LongOpt aMaximumServicesToReturn = new LongOpt();
        aMaximumServicesToReturn.theValue(5);

        StringOpt aSortingOrder = new StringOpt();
        aSortingOrder.__default();
        EiaDateOpt aAsOfDate = new EiaDateOpt();
        aAsOfDate.__default();

        SubscriptionAccountInformationTypeOpt aSubscriptionAccountInformationType = new SubscriptionAccountInformationTypeOpt();
        aSubscriptionAccountInformationType.__default();

        Date dateStart = new Date();
        String startTime = dateStart.toString();

        SmClient smClient = new SmClient();
        SmFacade smBean = smClient.getSmBean(context, rmLegacyService);

        SubscriptionAccountReturn response = smBean.retrieveSubscriptionAccountsForAffiliatesByAccountNumber(
                context,
                aBillingAccountHandle,
                aZipCode,
                aAffiliates,
                aSubscriptionAccountInformationType,
                aMaximumServicesToReturn,
                aSortingOrder,
                aAsOfDate);

        Assertions.assertEquals(parseResponse(response), EXPECTED_RESPONSE);
    }

}
