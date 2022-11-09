package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GitKeywords {

    COMMIT("commit"),

    AUTHOR("Author"),

    DATE("Date");

    private final String keywords;

}