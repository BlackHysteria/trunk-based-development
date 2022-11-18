package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.bakumcev.demo.enums.MessageCode.PIPELINE_FINISH;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_RUN;
import static com.bakumcev.demo.enums.docker.DockerCommand.DOCKER_RUN_TEST;
import static com.bakumcev.demo.enums.maven.MavenKeywords.BUILD_SUCCESS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Component
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    @Override
    @SneakyThrows
    public boolean run() {
        var result = false;
        var row = EMPTY;
        var process = Runtime.getRuntime().exec(DOCKER_RUN_TEST.getCommand());
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        log.info(PIPELINE_RUN.getCode());
        while ((row = input.readLine()) != null) {
            if (row.contains(BUILD_SUCCESS.getCommand())) {
                result = true;
                log.info(PIPELINE_RUN.getCode());
            }
        }
        log.info(PIPELINE_FINISH.getCode());
        return result;
    }
}
