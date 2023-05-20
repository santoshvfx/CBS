package com.att.lms.bis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, JmxAutoConfiguration.class})
@ComponentScan(basePackages = {"com.att.lms","com.att.transformation"})
public class LmsRmBisApplication {

    private static final Logger LOG = LoggerFactory.getLogger(LmsRmBisApplication.class);

    public LmsRmBisApplication() {
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LmsRmBisApplication.class, args);
        // Setting this property to correct the SNI issue with aaf_url
        System.setProperty("jsse.enableSNIExtension", "true");
        // TODO: Rm add any System properties that are NOT Bis Platform
    }

    @PostConstruct
    void started() {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        LOG.info("-- LmsRmBisApplication - It' UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //throw new RuntimeException("It's Broken!!!!!!!!!!!!!");
    }

}
