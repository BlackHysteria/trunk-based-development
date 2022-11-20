package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.sender.GitHubSender;
import com.bakumcev.demo.service.BashService;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {

    @Value("${git.last.commit}")
    private String gitLastCommit;

    private final GitHubSender gitHubSender;
    private final PipelineService pipelineService;
    private final BashService bashService;

    @Override
    @SneakyThrows
    public String push() {
        String answer = null;

        var lastSha = getLastSha(gitLastCommit);
        log.info(SHA_LAST_COMMIT.getCode(), lastSha);

        var gitHubCommits = gitHubSender.getCommits();

        if (gitHubCommits.contains(lastSha)) {
            log.info(LAST_COMMIT_ALREADY.getCode());
            answer = LAST_COMMIT_ALREADY.getCode();
        } else {
            answer = pipelineRun();
        }

        return answer;
    }

    private String pipelineRun() {
        if (pipelineService.run()) {
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
        var process = bashService.runCommand(command);
        var input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return input.readLine();
    }

}
