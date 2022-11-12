package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.GitService;
import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    private final GitService gitService;

    @Override
    public boolean run() {
        if (yesOrNo()) {
            gitService.push();
        }

        return false;
    }

    private boolean yesOrNo() {
        var currentMinute = LocalDateTime.now().getMinute();
        return (currentMinute & 1) ==  0;
    }
}
