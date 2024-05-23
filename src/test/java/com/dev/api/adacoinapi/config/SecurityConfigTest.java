package com.dev.api.adacoinapi.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
@Import(SecurityConfig.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private SomeService someService; // Mock any service that might be required by the controller

    @Test
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(post("/api/users/create"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser // This annotation mocks a user with default username, password, and role
    public void testSecuredEndpoint() throws Exception {
        mockMvc.perform(get("/api/some-secured-endpoint"))
                .andExpect(status().isForbidden());
    }
}
