package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MavenKeywords {

    BUILD_SUCCESS("BUILD SUCCESS");

    private final String command;

}
