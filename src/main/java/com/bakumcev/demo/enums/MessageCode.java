package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

    ERROR_REQUEST("Error executing request!"),

    GITHUB_IS_NOT_AVAILABLE("GitHub is not available"),
    GITHUB_IS_AVAILABLE("Get a response from GitHub"),
    GITHUB_SEND_REQUEST("Send request in Github"),

    PIPELINE_FAILED("Pipeline is failed"),
    PIPELINE_SUCCESS("Pipeline is success"),

    PIPELINE_RUN("Run tests in containers..."),
    PIPELINE_FINISH("Finish tests in containers!"),

    COMMIT_PUSHED("Last commit pushed!"),
    SHA_LAST_COMMIT("Sha last commit: {}"),
    LAST_COMMIT_ALREADY("Latest commit is already in the repository!");

    private final String code;

}
