package com.bakumcev.demo.controller;

import com.bakumcev.demo.service.GitService;
import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrunkController {

    private final PipelineService pipelineService;
    private final GitService gitService;

    @GetMapping("/pipeline/run")
    public boolean run() {
        return pipelineService.run();
    }

    @GetMapping("/git/push")
    public String push() {
        return gitService.push();
    }

}
