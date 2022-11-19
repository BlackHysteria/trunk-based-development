package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DockerCommand {

    DOCKER_RUN_TEST("docker build --no-cache --tag trunk-docker .");

    private final String command;

}
