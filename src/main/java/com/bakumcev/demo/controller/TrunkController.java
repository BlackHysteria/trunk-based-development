package com.bakumcev.demo.controller;

import com.bakumcev.demo.dto.ResponseDto;
import com.bakumcev.demo.service.GitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import static com.bakumcev.demo.enums.MessageCode.ERROR_REQUEST;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrunkController {

    private final GitService gitService;

    @GetMapping("/git/push")
    public ResponseDto push() {
        var result = gitService.push();
        return ResponseDto.builder().message(result).build();
    }

    @ExceptionHandler({Exception.class, RestClientException.class })
    @ResponseBody
    public ResponseDto handleException() {
        return ResponseDto.builder().message(ERROR_REQUEST.getCode()).build();
    }

}
