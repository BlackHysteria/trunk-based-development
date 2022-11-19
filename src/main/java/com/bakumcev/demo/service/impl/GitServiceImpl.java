package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.sender.GitHubSender;
import com.bakumcev.demo.service.GitService;
import com.bakumcev.demo.service.PipelineService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.bakumcev.demo.enums.MessageCode.COMMIT_PUSHED;
import static com.bakumcev.demo.enums.MessageCode.LAST_COMMIT_ALREADY;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_FAILED;
import static com.bakumcev.demo.enums.MessageCode.SHA_LAST_COMMIT;
import static com.bakumcev.demo.enums.GitCommand.GIT_PUSH;
import static com.bakumcev.demo.enums.GitCommand.GIT_SHOW_LAST;

@Slf4j
@Component
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {

    @Value("${git.username}")
    private String username;

    @Value("${git.key}")
    private String key;

    private final GitHubSender gitHubSender;
    private final PipelineService pipelineService;

    @Override
    @SneakyThrows
    public String push() {
        String answer = null;
        var command = GIT_PUSH.getCommand() +
                " https://" +
                username +
                ":" +
                key +
                "@github.com/" +
                username +
                "/trunk-based-development.git/ HEAD";

        var lastSha = getLastSha(GIT_SHOW_LAST.getCommand());
        log.info(SHA_LAST_COMMIT.getCode(), lastSha);

        var gitHubCommits = gitHubSender.getCommits(key);

        if (gitHubCommits.contains(lastSha)) {
            answer = LAST_COMMIT_ALREADY.getCode();
        } else {
            answer = pipelineRun(command);
        }
        return answer;
    }

    private String pipelineRun(String command) {
        if (pipelineService.run()) {
            runProcess(command);
            log.info(COMMIT_PUSHED.getCode());
            return COMMIT_PUSHED.getCode();
        } else {
            log.info(PIPELINE_FAILED.getCode());
            return PIPELINE_FAILED.getCode();
        }
    }

    @Override
    @SneakyThrows
    public String getLastSha(String command) {
        var process = runProcess(command);
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return input.readLine();
    }

    @SneakyThrows
    public Process runProcess(String command) {
        return Runtime.getRuntime().exec(command);
    }

}
