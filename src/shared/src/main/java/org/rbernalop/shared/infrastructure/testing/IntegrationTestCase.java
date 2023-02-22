package org.rbernalop.shared.infrastructure.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public abstract class IntegrationTestCase {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    protected MvcResult assertRequest(
            HttpMethod method,
            String endpoint,
            HttpStatus expectedStatusCode) throws Exception {
        return mockMvc.perform(request(method, endpoint))
                .andExpect(status().is(expectedStatusCode.value()))
                .andReturn();
    }

    protected MvcResult assertRequestWithBody(
            HttpMethod method,
            String endpoint,
            Object body,
            HttpStatus expectedStatusCode) throws Exception {
        return mockMvc.perform(request(method, endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().is(expectedStatusCode.value()))
                .andReturn();
    }
}
