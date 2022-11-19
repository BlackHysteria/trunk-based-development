package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MavenKeywords {

    BUILD_SUCCESS("BUILD SUCCESS"),
    BUILD_FAILURE("BUILD FAILURE");

    private final String command;

}
