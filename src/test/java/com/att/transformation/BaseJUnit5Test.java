package com.att.transformation;

import com.att.lms.bis.EndtoEndTestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = {MockitoExtension.class, SpringExtension.class})
@SpringBootTest(classes = {
        EndtoEndTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=2200"})
@TestPropertySource(locations = {"classpath:application-e2e-test.properties",
        "classpath:application-e2e-real.properties"})
@ComponentScan(basePackages = {"com.att.lms", "com.sbc.bis"}, useDefaultFilters = true)
public class BaseJUnit5Test {

  protected ObjectMapper objectMapper;

  public BaseJUnit5Test() {
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS);
  }

  protected String parseResponse(Object obj) throws Exception {
    // Replace logging id
    return objectMapper.writeValueAsString(obj)
            .replaceAll("COR:[A-Z0-9|:.-]*", "")
            .replaceAll("INFO\\|[A-Za-z0-9\\|/:.-]*","")
            .replaceAll(
                    "\"aStatusTime\":\\{\"aEiaDate\":\\{\"aYear\":\\d+,\"aMonth\":\\d+,\"aDay\":\\d+\\},\"aHour\":\\d+,\"aMinute\":\\d+,\"aSecond\":\\d+,\"aMilliSeconds\":\\d+,\"UTCOffset\":-?\\d+\\}"
                    ,"\"aStatusTime\":\\{\\}");
  }
}
