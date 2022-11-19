package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GitCommand {

    GIT_SHOW_LAST("git rev-parse --verify HEAD"),
    GIT_PUSH("git push");

    private final String command;

}
