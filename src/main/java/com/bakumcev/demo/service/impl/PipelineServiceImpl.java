package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.bakumcev.demo.enums.MavenCommand.MVN_CLEAN_INSTALL;
import static com.bakumcev.demo.enums.MavenKeywords.BUILD_SUCCESS;
import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    @Override
    @SneakyThrows
    public boolean run() {
        String result = "";
        var process = Runtime.getRuntime().exec(MVN_CLEAN_INSTALL.getCommand());
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        result = input.lines().collect(joining());
        return result.contains(BUILD_SUCCESS.getCommand());
    }
}
