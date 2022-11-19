package com.bakumcev.demo.controller;

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
    public String push() {
        return gitService.push();
    }

    @ExceptionHandler({ Exception.class, RestClientException.class })
    @ResponseBody
    public String handleException() {
        return ERROR_REQUEST.getCode();
    }

}
