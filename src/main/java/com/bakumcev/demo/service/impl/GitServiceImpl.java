package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.enums.GitKeywords;
import com.bakumcev.demo.sender.GitHubSender;
import com.bakumcev.demo.service.GitService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.bakumcev.demo.enums.GitCommand.GIT_LOG;
import static com.bakumcev.demo.enums.GitCommand.GIT_PUSH;
import static com.bakumcev.demo.enums.GitCommand.GIT_SHOW_LAST;
import static com.bakumcev.demo.utils.Utils.copy;
import static com.bakumcev.demo.utils.Utils.lineProcessing;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {

    @Value("${git.line.in.log}")
    private int lineInLog;

    @Value("${git.username}")
    private String username;

    @Value("${git.key}")
    private String key;

    private final GitHubSender gitHubSender;

    @Override
    public String push() {
        var command = GIT_PUSH.getCommand() +
                " https://" +
                username +
                ":" +
                key +
                "@github.com/" +
                username +
                "/trunk-based-development.git/ HEAD";

        var lastSha = getLastSha();
        var gitCommits = gitHubSender.getCommits(key);

        if (!gitCommits.contains(lastSha)) {
            runProcess(command);
            return "Last commit pushed!";
        } else {
            return "Latest commit is already in the repository!";
        }
    }

    @Override
    @SneakyThrows
    public String getLastSha() {
        Map<GitKeywords, String> gitLogModel = new EnumMap<>(GitKeywords.class);

        var command = GIT_SHOW_LAST.getCommand();
        var process = runProcess(command);
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var line = EMPTY;

        while ((line = input.readLine()) != null) {
            lineProcessing(line, gitLogModel);
        }

        return gitLogModel.get(GitKeywords.COMMIT);
    }

    @Override
    @SneakyThrows
    public List<Map<GitKeywords, String>> getFullGitLogs() {
        List<Map<GitKeywords, String>> gitLogs = new ArrayList<>();
        Map<GitKeywords, String> gitLogModel = new EnumMap<>(GitKeywords.class);

        var command = GIT_LOG.getCommand();
        var process = runProcess(command);
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

    @SneakyThrows
    private Process runProcess(String command) {
        return Runtime.getRuntime().exec(command);
    }

}
