package com.bakumcev.demo.utils;

import com.bakumcev.demo.enums.git.GitKeywords;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EnumMap;
import java.util.Map;

import static com.bakumcev.demo.utils.Utils.lineProcessing;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    @SneakyThrows
    void readGitLogOne() {
        Map<GitKeywords, String> gitLogModel = new EnumMap<>(GitKeywords.class);

        var fileReader = new FileReader("src/test/resources/maven/git_log_one.txt");
        var br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            lineProcessing(line, gitLogModel);
        }

        assertEquals("f4e6da7e1a81f8b7569c428f6dff42630a29800c", gitLogModel.get(GitKeywords.COMMIT));
        assertEquals("i.bakumtsev", gitLogModel.get(GitKeywords.AUTHOR));
        assertEquals("Wed Nov 16 19:00:33 2022 +0300", gitLogModel.get(GitKeywords.DATE));
    }

}


