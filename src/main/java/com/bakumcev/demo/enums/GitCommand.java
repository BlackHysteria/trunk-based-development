package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GitCommand {

    GIT_LOG("git log");

    private final String command;

}
