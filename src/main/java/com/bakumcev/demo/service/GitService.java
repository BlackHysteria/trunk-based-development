package com.bakumcev.demo.service;

import com.bakumcev.demo.enums.GitKeywords;

import java.util.List;
import java.util.Map;

public interface GitService {

    String log();

    List<Map<GitKeywords, String>> getGitLog();

}
