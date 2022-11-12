package com.bakumcev.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Команды для Maven
 */
@Getter
@AllArgsConstructor
public enum MavenCommand {

    MVN_CLEAN_INSTALL("mvn clean install");

    private final String command;

}
