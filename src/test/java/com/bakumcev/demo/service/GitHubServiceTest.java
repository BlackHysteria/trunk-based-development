package com.bakumcev.demo.service;

import com.bakumcev.demo.config.AbstractSpringIntegrationTest;
import com.bakumcev.demo.sender.GitHubSender;
import com.bakumcev.demo.service.impl.GitServiceImpl;
import com.bakumcev.demo.utils.JsonReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.bakumcev.demo.enums.GitCommand.GIT_SHOW_LAST;
import static com.bakumcev.demo.enums.MessageCode.COMMIT_PUSHED;
import static com.bakumcev.demo.enums.MessageCode.LAST_COMMIT_ALREADY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class GitHubServiceTest extends AbstractSpringIntegrationTest {

    @Autowired
    private GitServiceImpl service;

    JsonReader jsonReader = new JsonReader();

    @MockBean
    private GitHubSender gitHubSender;

    @MockBean
    private PipelineService pipelineService;

    @Test
    void pushCompleted() {
        var gitCommand = GIT_SHOW_LAST.getCommand();
        var gitResponse = jsonReader.getResourceAsString("json/git_response.json");

        var gitServiceSpy = spy(service);
        when(gitServiceSpy.getLastSha(gitCommand)).thenReturn("4a3bcb4960bd78072c61b6773b20f857216e741n");
        when(gitHubSender.getCommits()).thenReturn(gitResponse);
        when(pipelineService.run()).thenReturn(true);

        var result = gitServiceSpy.push();

        assertEquals(COMMIT_PUSHED.getCode(), result);
    }

    @Test
    void pushNotCompleted() {
        var gitCommand = GIT_SHOW_LAST.getCommand();
        var gitResponse = jsonReader.getResourceAsString("json/git_response.json");

        var gitServiceSpy = spy(service);
        when(gitServiceSpy.getLastSha(gitCommand)).thenReturn("0199da360958afff5006f04a7e5ccbacca7c4b10");
        when(gitHubSender.getCommits()).thenReturn(gitResponse);

        var result = gitServiceSpy.push();

        assertEquals(LAST_COMMIT_ALREADY.getCode(), result);
    }
}
