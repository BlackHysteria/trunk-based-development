package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.enums.GitKeywords;
import com.bakumcev.demo.service.GitService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.bakumcev.demo.enums.GitCommand.GIT_LOG;
import static com.bakumcev.demo.enums.GitKeywords.AUTHOR;
import static com.bakumcev.demo.enums.GitKeywords.COMMIT;
import static com.bakumcev.demo.enums.GitKeywords.DATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class GitServiceImpl implements GitService {

    @SneakyThrows
    @Override
    public String log() {
        //runGitCommand(GIT_LOG.getCommand());
        getGitLog();

        return "Ok";
    }

    @SneakyThrows
    private void runGitCommand(String command) {
        /*Process p = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String text = command + "\n";
        while ((line = input.readLine()) != null) {
            text += line;
            System.out.println("Line: " + line);
        }*/

        getGitLog();
    }

    @SneakyThrows
    @Override
    public List<HashMap<GitKeywords, String>> getGitLog() {
        var gitLogs = new ArrayList<HashMap<GitKeywords, String>>();
        var gitLogModel = new HashMap<GitKeywords, String>();

        var command = GIT_LOG.getCommand();
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        //String text = command + "\n";
        while ((line = input.readLine()) != null) {
            //text += line;
            System.out.println("Line: " + line);
            lineProcessing(line, gitLogs, gitLogModel);
        }

        return gitLogs;
    }

    private void lineProcessing(
            String line,
            List<HashMap<GitKeywords, String>> gitLogs,
            HashMap<GitKeywords, String> gitLog) {


        if (isNotBlank(line)) {
            if (line.contains(COMMIT.getKeywords()) && safety(line)) {
                gitLog.put(COMMIT, get(line));
            }

            if (line.contains(AUTHOR.getKeywords()) && safety(line)) {
                gitLog.put(AUTHOR, get(line));
            }

            if (line.contains(DATE.getKeywords()) && safety(line)) {
                gitLog.put(DATE, get(line));
            }

            if (isBlank(line)) {
                gitLogs.add((HashMap<GitKeywords, String>) gitLogs);
            }
        }

        //return gitLogs;
    }

    private boolean safety(String line) {
        return Optional.ofNullable(line)
                .map(String::strip)
                .map(str -> str.split(" "))
                .map(str1 -> str1[1])
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private String get(String line) {
        return Optional.ofNullable(line)
                .map(str -> str.split(" "))
                .map(str1 -> str1[1])
                .map(String::strip)
                .orElse(EMPTY);
    }

}
