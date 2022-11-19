package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.BashService;
import com.bakumcev.demo.service.CreateHook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateHookImpl implements CreateHook {

    private final BashService bashService;

    @PostConstruct
    public void init() {
        bashService.runFromFile("bash ./hook.sh", null, new File("./"));
        log.info("Git hook for pre-push is created!");
    }
}
