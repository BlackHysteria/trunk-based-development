package com.bakumcev.demo.service;

import com.bakumcev.demo.enums.GitKeywords;
import com.bakumcev.demo.model.GitLogModel;

import java.util.HashMap;
import java.util.List;

public interface GitService {

    String log();

    List<HashMap<GitKeywords, String>> getGitLog();

}
