package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.BashService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class BaseServiceImpl implements BashService {

    @Override
    @SneakyThrows
    public Process runCommand(String command) {
        return Runtime.getRuntime().exec(command);
    }

    @SneakyThrows
    @Override
    public Process runFromFile(String command, String[] envp, File dir) {
        return Runtime.getRuntime().exec(command, envp, dir);
    }
}
