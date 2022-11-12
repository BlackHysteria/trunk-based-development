package com.bakumcev.demo.service;

import com.bakumcev.demo.config.AbstractSpringIntegrationTest;
import com.bakumcev.demo.service.impl.PipelineServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class PipelineServiceImplTest extends AbstractSpringIntegrationTest {

    @Autowired
    private PipelineServiceImpl service;

    @MockBean
    private LocalDateTime localDateTime;

    /*@Test
    void qwe() {
        when(localDateTime.getMinute()).thenReturn(10);
        assertTrue(service.run());
    }*/

}
