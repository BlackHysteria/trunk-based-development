package com.bakumcev.demo.controller;

import com.bakumcev.demo.dto.ResponseDto;
import com.bakumcev.demo.service.GitService;
import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrunkController {

    private final PipelineService pipelineService;
    private final GitService gitService;

    @GetMapping("/pipeline/run")
    public ResponseDto run() {
        var result = pipelineService.run();
        return ResponseDto.builder().success(result).build();
    }

    @GetMapping("/git/push")
    public ResponseDto push() {
        var result = gitService.push();
        return ResponseDto.builder().message(result).build();
    }

    @GetMapping("/test")
    public ResponseDto test() {
        return ResponseDto.builder()
                .success(true)
                .message("Yes")
                .build();
    }

    @ExceptionHandler(Exception.class)
    public void handleException() {
        //
    }

}
