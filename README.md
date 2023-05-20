# Rm Bis

## Getting Started

1. Copy over fully hydrated code (from both ejb/<bis-name>/common/java/src and ejb/<bis-name>/common/java/src)
2. Copy over properties files only into [src/main/resources](src/main/resources)
3. Perform a global find on Rm and resolve these naming instances
    1. Note: This includes making changes in the [pom.xml](pom.xml), [Dockerfile](Dockerfile),
       and [api-guide.adoc](src/main/asciidoc/api-guide.adoc)
4. For each method to expose:
    1. Add a wrapper Dto object under the dto package that "wraps" all input objects of your
        1. NOTE: If an inner request object has properties following the naming convention aPropertyName, to get your
           Dto to correctly serialize, you need to "pull in" any classes from the idl_bundle jar and annotate those
           fields with `@JsonProperty("aPropertyName")`
    2. Add the method in the Lms Service that calls your Legacy Service
    3. Add the controller with endpoint in the Lms Service (Note: the endpoint path should be in camelCase and match the
       legacy ejb method name)
    4. Add a corresponding e2e test
        1. In the package com.att.lms.bis.http, add a new class with the same name as your endpoint + "TestE2E" (e.g.
           RetrieveLocationForAddressTestE2E)
        2. Add a new method for your test, updating the method source
        3. Construct the wrapper input object
            1. Instantiate each object, then add them to the wrapper object you previously created
            2. Use the lombok builder to construct the wrapper input object
        4. Copy the given statement from a previous test, and update accordingly
            1. Update document name with the identifier for the method (e.g. get-location-for-address)
            2. Update request and response fields methods accordingly
            3. Update the post URL
            4. Run the test. When it fails, it will specify what you need to document. Update the descriptions
               accordingly in the request and response fields methods
    5. Generate the Documents
        1. Get the test to run successfully
        2. go to target > generated snippits, locate your method and that should have all the documents for your method
        3. In [api-guide.adoc](src/main/asciidoc/api-guide.adoc):
            1. Copy the Rm section
            2. Paste it in notepad
            3. Search and replace
                1. The snippet url (e.g. change-me to get-service-list-for-partial-service)
                2. Method names (e.g. changeMe to retrieveServiceListForPartialService)
                3. Return object and description (e.g. RmReturn.java to
                   retrieveServiceListForPartialServiceReturn.java)
            5. Paste back into the api-guide.adoc
        4. Run `mvn clean verify -Pintegration-test > errors.txt`
        5. The output will be stored in errors.txt. Check that text file to see any errors or if the build succeeded
        6. The documents should be found in the target/generated-docs folder


## BIS to BIS replacement

1. Upgrade your common rest version to at least 1.1.4 pom to use the following dependencies:
```xml
        <!-- idl-common_types -->
<dependency>
    <groupId>com.sbc.eia</groupId>
    <artifactId>idl-internal-common_types</artifactId>
    <version>1.0.1</version>
</dependency>
<dependency>
<groupId>com.att.lms.bis</groupId>
<artifactId>att-lms-response-handler</artifactId>
<version>1.0.2</version>
</dependency>
```
2. In [application-e2e-test.properties](src/e2e-test/resources/application-e2e-test.properties)
   and [application.properties](src/main/resources/application.properties), add the properties for your BIS rest call,
   e.g.:

```
bis.restEndpoints.nam.scheme=https
bis.restEndpoints.nam.host=nambis-nprd.az.3pc.att.com
bis.restEndpoints.nam.port=443
bis.restEndpoints.nam.path=restservices/lms/v1/nam/
bis.restEndpoints.nam.username=m49476@lms.att.com
bis.restEndpoints.nam.password=Middleware$123
```

3. Add [BisRestEndpointSettings.java](src/main/java/com/att/lms/bis/rest/config/settings/BisRestEndpointSettings.java)
   to grab the bis prefixed properties in the application properties
4. Update [RestConstants.java](src/main/java/com/att/lms/bis/rest/config/settings/RestConstants.java) to include any BIS
   app you'll be talking to via rest
5. Add a Dao class (e.g. [NamRestDao.java](src/main/java/com/att/lms/bis/rest/dao/NamRestDao.java) for each BIS app to hit
   via REST
    1. Add a method for each endpoint you want to hit
6. Add a RestService class (e.g. [NamRestService.java](src/main/java/com/att/lms/bis/rest/service/NamRestService.java))
   that, for each called endpoint, takes in the corresponding Request DTO and calls the corresponding DAO method
7. Add a RestFacade class (e.g. [NamRestFacade.java](src/main/java/com/att/lms/bis/rest/facade/NamRestFacade.java))
    * Have it implement the BIS app's "facade" (e.g. NamFacade))
    * For each method you need to call via REST:
        * Build the RequestDto from the input parameters
        * Call the REST Service
        * Parse out the Either object returned to return the response object or the BIS Context
8. In [NAMClient.java](src/main/java/com/sbc/eia/bis/RmNam/utilities/NAMClient/NAMClient.java), change the getBean method
   to just use the StaticContextAccessor to get the NAM Facade bean,
   e.g.: `return StaticContextAccessor.getBean(NamFacade.class);`
9. Use the default constructor for NAMClient now


## BIS to RC replacement

1. In the [pom.xml](pom.xml), add the following dependency:
```
<dependency>
    <groupId>com.att.lms</groupId>
    <artifactId>lms-rc-domain-v1</artifactId>
    <version>1.0.17</version>
    <exclusions>
        <exclusion>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>com.att.lms.rest</groupId>
    <artifactId>lms-rc-rest</artifactId>
    <version>1.0.0</version>
</dependency>
```
2. In [application-e2e-test.properties](src/e2e-test/resources/application-e2e-test.properties)
   and [application.properties](src/main/resources/application.properties), add the properties for your RC call,
   e.g.:
```
rc.restEndpoints.codes.scheme=https
rc.restEndpoints.codes.host=codesrc-nprd.az.3pc.att.com
rc.restEndpoints.codes.port=443
rc.restEndpoints.codes.path=restservices/lms/v1/codes/processRequest
rc.restEndpoints.codes.username=m49476@lms.att.com
rc.restEndpoints.codes.password=Middleware$123

rc.restEndpoints.facs.scheme=https
rc.restEndpoints.facs.host=rcfacs-nprd.az.3pc.att.com
rc.restEndpoints.facs.port=443
rc.restEndpoints.facs.path=restservices/lms/v1/facs/processRequest
rc.restEndpoints.facs.username=m49476@lms.att.com
rc.restEndpoints.facs.password=Middleware$123
```
3. In [application-e2e-test.properties](src/e2e-test/resources/application-e2e-test.properties)
   set `spring.profiles.active=PROD_PROFILE`
4. Investigate the SelfTest method to test the BIS to RC connections (Note: this will be different for each BIS app)
    1. Add [SelfTestRCAccess.java](src/main/java/com/sbc/eia/bis/facades/rm/transactions/SelfTest/SelfTestRCAccess.java) mirroring SelfTestEMBUS
    2. In [SelfTest_ServicesList.properties.non271sat](src/main/resources/SelfTest_ServicesList.properties.non271sat) comment out the EMBUS prefixed properties and replace with RC prefixed ones:
       * RC_CODESRC=com.sbc.eia.bis.embus.service.codes.access.CodesRCAccessHelper
       * RC_FACSRC=com.sbc.eia.bis.embus.service.facsrc.FACSRCHelper
    3. Add a new method in [ServiceAccess.java](src/main/java/com/sbc/eia/bis/embus/service/access/ServiceAccess.java) to make the RC Call (sendAndReceiveHttp)
    4. Update the respective RC Helpers to call the new sendAndReceiveHttp method for Service Access
         * [CodesRCAccessHelper](src/main/java/com/sbc/eia/bis/embus/service/codes/access/CodesRCAccessHelper.java)
         * [FACSRCHelper](src/main/java/com/sbc/eia/bis/embus/service/facsrc/FACSRCHelper.java)
    5. In the helper, override the selfTest method to make an end to end call to that RC
    6. Update [RMSelfTest.java](src/main/java/com/sbc/eia/bis/facades/rm/transactions/SelfTest/RMSelfTest.java)to call SelfTestRCAccess
    7. Run the legacy selfTest to verify the changes
5. To get the jar/Spring App to run, make the following changes to [LmsRmBisApplication.java](src/main/java/com/att/lms/bis/LmsRmBisApplication.java)
```java
   @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, JmxAutoConfiguration.class})
   @ComponentScan(basePackages = {"com.att.lms","com.att.transformation"})
```