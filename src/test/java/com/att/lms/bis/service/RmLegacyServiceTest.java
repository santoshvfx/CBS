package com.att.lms.bis.service;

import com.att.transformation.BaseJUnit5Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.bis.facades.rm.ejb.RetrieveResourcesForService.RetrieveResourcesForService;
import com.sbc.eia.bis.framework.BisContextManager;
import com.att.lms.bis.service.RmLegacyService;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.rm.RetrieveResourcesForServiceReturn;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForServiceReturn;
import com.sbc.eia.idl.rm_types.ResourceRoleName;
import com.sbc.eia.idl.rm_types.ServiceTypeHandleObjectKey;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class RmLegacyServiceTest extends BaseJUnit5Test {
    @Autowired
    private RmLegacyService rmLegacyService;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("bis.platform", "NON271SAT");
    }

    @Test
    public void testPing() throws Exception {
        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("JMAS");
        bcm.setCustomerName("JMAS");

        rmLegacyService.ping(bcm.getBisContext());
    }

    @Test
    public void testSelfTest() throws Exception {
        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("JMAS");
        bcm.setCustomerName("JMAS");

        rmLegacyService.selfTest(bcm.getBisContext());
    }

    @Test
    @Disabled
    public void testRetrieveResourcesForService() throws Exception {
        String EXPECTED_RESPONSE = "";
        //prepare input object parameters
        BisContextManager bcm = new BisContextManager();

        ObjectProperty[] bisContextProperties = new ObjectProperty[4];
        bisContextProperties[0] = new ObjectProperty("CustomerName","ZZZZ");
        bisContextProperties[1] = new ObjectProperty("ServiceCenter","CA");
        bisContextProperties[2] = new ObjectProperty("Application", "OBF");
        bisContextProperties[3] = new ObjectProperty("BusinessUnit","SBCWEST");
        bcm.setBisContext(new BisContext(bisContextProperties));

        String[] aResourceRoleNames = new String[2];

        aResourceRoleNames[0] = ResourceRoleName.SERVINGSWITCH;
        aResourceRoleNames[1] = ResourceRoleName.IDLC;

        ObjectKey aObjectKey = new ObjectKey("7149922784", BisTypesObjectKeyKind.TELEPHONENUMBER);

        //call the method on the bim service
        RetrieveResourcesForServiceReturn response = rmLegacyService.retrieveResourcesForService(bcm.getBisContext(),
                aObjectKey,
                aResourceRoleNames
        );

        assertEquals(response, EXPECTED_RESPONSE);

    }

    @Test
    @Disabled
    public void testRetrieveServiceProvidersForResource() throws Exception {
        String EXPECTED_RESPONSE = "";
        //prepare input object parameters
        BisContextManager bcm = new BisContextManager();

        ObjectProperty[] bisContextProperties = new ObjectProperty[3];
        bisContextProperties[1] = new ObjectProperty("ServiceCenter",""); // TODO
        bisContextProperties[2] = new ObjectProperty("BusinessUnit","ATTWireline");
        bisContextProperties[0] = new ObjectProperty("Application","GIOM");
        bcm.setBisContext(new BisContext(bisContextProperties));

        String aResourceHandle = "ElevenCharacter CLLI Code"; // TODO

        ObjectKey[] aServiceTypeHandles = new ObjectKey[1];
        aServiceTypeHandles[0] = new ObjectKey(ServiceTypeHandleObjectKey.LPIC, BisTypesObjectKeyKind.SERVICETYPE);

        //call the method on the bim service
        RetrieveServiceProvidersForResourceReturn response = rmLegacyService.retrieveServiceProvidersForResource(bcm.getBisContext(),
                aResourceHandle,
                aServiceTypeHandles
        );

        assertEquals(response, EXPECTED_RESPONSE);
    }

    @Test
    @Disabled
    public void testRetrieveServiceProvidersForService() throws Exception {
        String EXPECTED_RESPONSE = "";
        //prepare input object parameters
        BisContextManager bcm = new BisContextManager();

        ObjectProperty[] bisContextProperties = new ObjectProperty[3];
        bisContextProperties[1] = new ObjectProperty("ServiceCenter",""); // TODO
        bisContextProperties[2] = new ObjectProperty("BusinessUnit","ATTWireline");
        bisContextProperties[0] = new ObjectProperty("Application","GIOM");
        bcm.setBisContext(new BisContext(bisContextProperties));

        ObjectKey aResourceHandle = new ObjectKey("9259280096", BisTypesObjectKeyKind.WORKINGTELEPHONENUMBER);

        ObjectKey[] aServiceTypeHandles = new ObjectKey[1];
        aServiceTypeHandles[0] = new ObjectKey(ServiceTypeHandleObjectKey.LPIC, BisTypesObjectKeyKind.SERVICETYPE);

        //call the method on the bim service
        RetrieveServiceProvidersForServiceReturn response = rmLegacyService.retrieveServiceProvidersForService(bcm.getBisContext(),
                aResourceHandle,
                aServiceTypeHandles
        );

        assertEquals(response, EXPECTED_RESPONSE);
    }
}
