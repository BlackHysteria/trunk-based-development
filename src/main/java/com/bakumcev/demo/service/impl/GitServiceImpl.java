package com.bakumcev.demo.service.impl;

import com.bakumcev.demo.enums.git.GitKeywords;
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
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.bakumcev.demo.enums.MessageCode.COMMIT_PUSHED;
import static com.bakumcev.demo.enums.MessageCode.LAST_COMMIT_ALREADY;
import static com.bakumcev.demo.enums.MessageCode.PIPELINE_FAILED;
import static com.bakumcev.demo.enums.MessageCode.SHA_LAST_COMMIT;
import static com.bakumcev.demo.enums.git.GitCommand.GIT_LOG;
import static com.bakumcev.demo.enums.git.GitCommand.GIT_PUSH;
import static com.bakumcev.demo.enums.git.GitCommand.GIT_SHOW_LAST;
import static com.bakumcev.demo.utils.Utils.copy;
import static com.bakumcev.demo.utils.Utils.lineProcessing;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
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

        //Получаем хэш последнего коммита
        var lastSha = getLastSha(GIT_SHOW_LAST.getCommand());
        log.info(SHA_LAST_COMMIT.getCode(),  lastSha);

        //Проверяем если ли этот коммит в github`е
        var gitHubCommits = gitHubSender.getCommits(key);

        if (!gitHubCommits.contains(lastSha)) {
            answer = pipelineRun(command);
        } else {
            answer = LAST_COMMIT_ALREADY.getCode();
        }
        return answer;
    }

    private String pipelineRun(String command) {
        //Запускаем тесты в контейнере
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
        Map<GitKeywords, String> gitLogModel = new EnumMap<>(GitKeywords.class);

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
    public Process runProcess(String command) {
        return Runtime.getRuntime().exec(command);
    }

}
