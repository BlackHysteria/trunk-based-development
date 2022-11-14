package com.bakumcev.demo.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/application-test.properties")
public abstract class AbstractSpringIntegrationTest {
}
