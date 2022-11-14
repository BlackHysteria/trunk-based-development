package com.bakumcev.demo.enums.docker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DockerCommand {

    DOCKER_BUILD("docker build .");

    private final String command;

}