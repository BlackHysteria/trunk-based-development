package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.enums.GitKeywords;
import com.bakumcev.demo.service.GitService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.bakumcev.demo.enums.GitCommand.GIT_LOG;
import static com.bakumcev.demo.enums.GitKeywords.AUTHOR;
import static com.bakumcev.demo.enums.GitKeywords.COMMIT;
import static com.bakumcev.demo.enums.GitKeywords.DATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class GitServiceImpl implements GitService {

    @Value("${git.line.in.log}")
    private int lineInLog;

    @SneakyThrows
    @Override
    public String log() {
        //runGitCommand(GIT_LOG.getCommand());
        getGitLog();

        return "Ok";
    }

    @SneakyThrows
    private void runGitCommand(String command) {
        getGitLog();
    }

    @SneakyThrows
    @Override
    public List<Map<GitKeywords, String>> getGitLog() {
        List<Map<GitKeywords, String>> gitLogs = new ArrayList<>();
        Map<GitKeywords, String> gitLogModel = new EnumMap<>(GitKeywords.class);

        var command = GIT_LOG.getCommand();
        var process = Runtime.getRuntime().exec(command);
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var line = EMPTY;

        while ((line = input.readLine()) != null) {
            lineProcessing(line, gitLogModel);

            if (gitLogModel.size() == lineInLog) {
                gitLogs.add(copy(gitLogModel));
                gitLogModel.clear();
            }
        }

        return gitLogs;
    }

    public static Map<GitKeywords, String> copy(Map<GitKeywords, String> original) {
        Map<GitKeywords, String> copy = new EnumMap<>(GitKeywords.class);
        for (Map.Entry<GitKeywords, String> entry : original.entrySet()) {
            copy.put(entry.getKey(), (entry.getValue()));
        }
        return copy;
    }

    private void lineProcessing(String line, Map<GitKeywords, String> gitLog) {
        if (isNotBlank(line)) {
            if (line.contains(COMMIT.getKeywords()) && safety1(line)) {
                gitLog.put(COMMIT, get(line));
            }

            if (line.contains(AUTHOR.getKeywords()) && safety1(line)) {
                gitLog.put(AUTHOR, get(line));
            }

            if (line.contains(DATE.getKeywords()) && safety2(line)) {
                gitLog.put(DATE, get2(line));
            }
        }
    }

    private boolean safety1(String line) {
        return Optional.ofNullable(line)
                .map(String::strip)
                .map(str -> str.split(SPACE))
                .map(str1 -> str1[1])
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private boolean safety2(String line) {
        return Optional.ofNullable(line)
                .map(String::strip)
                .map(str -> str.split(" {2}"))
                .map(str1 -> str1[1])
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private String get(String line) {
        return Optional.ofNullable(line)
                .map(str -> str.split(SPACE))
                .map(str1 -> str1[1])
                .map(String::strip)
                .orElse(EMPTY);
    }

    private String get2(String line) {
        return Optional.ofNullable(line)
                .map(str -> str.split(" {2}"))
                .map(str1 -> str1[1])
                .map(String::strip)
                .orElse(EMPTY);
    }

}
