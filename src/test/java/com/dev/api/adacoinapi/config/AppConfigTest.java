package com.dev.api.adacoinapi.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
public class AppConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testRestTemplateBean() {
        // Given - Context is already loaded by @SpringBootTest

        // When
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);

        // Then
        assertNotNull(restTemplate, "RestTemplate bean should be created and not null");
    }
}
