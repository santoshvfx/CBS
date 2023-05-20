package com.att.lms.bis.config;

import com.att.lms.bis.service.RmLegacyService;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

@Configuration
@PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "application.properties")
public class RmLegacyConfig {

    @Autowired
    public RmLegacyConfig(Environment environment) {
        // set system properties before the beans are being created.
        String bisPlatform = "bis.platform";
        System.getProperties().setProperty(bisPlatform, environment.getProperty(bisPlatform));
        System.setProperty("com.sbc.elogging.quicklogstatus","1");
    }

    @Bean
    public RmLegacyService legacyService() throws Exception {
        return  new RmLegacyService();
    }

    @Bean
    public BisContext bisContext(){
        BisContextManager contextMgr;
        contextMgr = new BisContextManager();
        contextMgr.setApplication("Rm");
        contextMgr.setCustomerName("Rm");

        return contextMgr.getBisContext() ;
    }

}
