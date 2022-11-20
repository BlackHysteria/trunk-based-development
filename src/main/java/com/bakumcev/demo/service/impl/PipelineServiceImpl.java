package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.bakumcev.demo.enums.MessageCode.PIPELINE_FAILED;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_FINISH;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_RUN;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_SUCCESS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Component
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    @Value("${docker.run.tests}")
    private String dockerRunTests;

    @Override
    @SneakyThrows
    public boolean run() {
        var result = false;
        var row = EMPTY;
        var process = Runtime.getRuntime().exec(dockerRunTests);
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        log.info(PIPELINE_RUN.getCode());
        while ((row = input.readLine()) != null) {
            var buildFailure = "BUILD FAILURE";
            var buildSuccess = "BUILD SUCCESS";
            if (row.contains(buildSuccess)) {
                result = true;
                log.info(PIPELINE_SUCCESS.getCode());
            } else if (row.contains(buildFailure)) {
                result = false;
                log.info(PIPELINE_FAILED.getCode());
            }
        }

        log.info(PIPELINE_FINISH.getCode());
        return result;
    }
}
