package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.service.TrunkService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TrunkServiceImpl implements TrunkService {

    @Override
    public boolean run() {
        return false;
    }

    @Override
    public boolean solution() {
        var currentMinute = LocalDateTime.now().getMinute();
        return (currentMinute & 1) ==  0;
    }


}
