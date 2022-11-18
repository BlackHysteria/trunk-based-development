package com.bakumcev.demo.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.bakumcev.demo.enums.MessageCode.GITHUB_IS_AVAILABLE;
import static com.bakumcev.demo.enums.MessageCode.GITHUB_IS_NOT_AVAILABLE;
import static com.bakumcev.demo.enums.MessageCode.GITHUB_SEND_REQUEST;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Component
public class GitHubSender {

    @Autowired
    @Qualifier("githubTemplate")
    private RestTemplate restTemplate;

    @Value("${github.api.commits}")
    private String apiCommits;

    public String getCommits(String key) {
        try {
            var headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github+json");
            headers.set("Authorization", "Bearer " + key);
            var requestEntity = new HttpEntity<>(null, headers);
            log.info(GITHUB_SEND_REQUEST.getCode());
            var response = restTemplate.exchange(apiCommits, HttpMethod.GET, requestEntity, String.class);
            log.info(GITHUB_IS_AVAILABLE.getCode());
            return String.valueOf(response.getBody());
        } catch (RestClientException exception) {
            log.error(GITHUB_IS_NOT_AVAILABLE.getCode());
        }
        return EMPTY;
    }
}
