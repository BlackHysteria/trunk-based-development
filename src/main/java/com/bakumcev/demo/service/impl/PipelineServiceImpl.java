package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.bakumcev.demo.enums.docker.DockerCommand.DOCKER_RUN_TEST;
import static com.bakumcev.demo.enums.maven.MavenKeywords.BUILD_SUCCESS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    @Override
    @SneakyThrows
    public boolean run() {
        var result = false;
        var row = EMPTY;
        var process = Runtime.getRuntime().exec(DOCKER_RUN_TEST.getCommand());
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        while ((row = input.readLine()) != null) {
            System.out.println("Line: " + row);
            if (row.contains(BUILD_SUCCESS.getCommand())) {
                result = true;
            }
        }
        return result;
    }
}
