package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GitCommand {

    GIT_LOG("git log"),
    GIT_SHOW_LAST("git log -1"),
    GIT_PUSH("git push"),
    HEAD_MASTER("HEAD -> master");

    private final String command;

}
