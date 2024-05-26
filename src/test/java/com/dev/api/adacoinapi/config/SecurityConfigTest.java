package com.dev.api.adacoinapi.config;

import com.dev.api.adacoinapi.controller.QuoteController;
import com.dev.api.adacoinapi.service.QuoteService;
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

@WebMvcTest(QuoteController.class)
@Import(SecurityConfig.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    @WithMockUser
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/quotes")
                        .param("currencies", "USD,EUR"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testSecuredEndpoint() throws Exception {
        mockMvc.perform(get("/api/quotes/convert/USD/EUR/100"))
                .andExpect(status().isOk());
    }
}
