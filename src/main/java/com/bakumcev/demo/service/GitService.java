package com.bakumcev.demo.service;

import com.bakumcev.demo.enums.GitKeywords;

import java.util.List;
import java.util.Map;

public interface GitService {

    /**
     * Push в GitHub
     */
    String push();

    /**
     * Коллекция логов
     */
    List<Map<GitKeywords, String>> getFullGitLogs();

    /**
     * Получение последнего локального хэша коммита
     */
    String getLastSha();

}
