package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

    COMMIT_PUSHED("Last commit pushed!"),
    PIPELINE_FILED("Pipeline failed"),
    LAST_COMMIT_ALREADY("Latest commit is already in the repository!");

    private final String code;

}
