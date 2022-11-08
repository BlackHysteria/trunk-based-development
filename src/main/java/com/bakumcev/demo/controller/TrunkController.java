package com.bakumcev.demo.controller;

import com.bakumcev.demo.service.TrunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrunkController {

    private final TrunkService service;

    @GetMapping("/runPipeline")
    public boolean get() {
        return service.solution();
    }

    @GetMapping("/test/negative")
    public String getPositive() {
        return "Finish negative case!";
    }

    @GetMapping("/test/positive")
    public String getNegative() {
        //service.


        return "Finish positive case!";
    }


}
