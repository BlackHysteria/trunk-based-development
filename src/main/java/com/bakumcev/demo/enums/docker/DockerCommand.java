package com.bakumcev.demo.enums.docker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DockerCommand {

    DOCKER_BUILD("docker build ."),
    //DOCKER_RUN_TEST("docker run -it --rm trunk-docker ./mvnw test"),
    DOCKER_RUN_TEST("docker build --no-cache --tag trunk-docker .");

    private final String command;

}
