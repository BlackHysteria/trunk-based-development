package com.bakumcev.demo.utils;

import com.bakumcev.demo.enums.GitKeywords;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static com.bakumcev.demo.enums.GitKeywords.AUTHOR;
import static com.bakumcev.demo.enums.GitKeywords.COMMIT;
import static com.bakumcev.demo.enums.GitKeywords.DATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@UtilityClass
public class Utils {

    public static Map<GitKeywords, String> copy(Map<GitKeywords, String> original) {
        Map<GitKeywords, String> copy = new EnumMap<>(GitKeywords.class);
        for (Map.Entry<GitKeywords, String> entry : original.entrySet()) {
            copy.put(entry.getKey(), (entry.getValue()));
        }
        return copy;
    }

    public static void lineProcessing(String line, Map<GitKeywords, String> gitLog) {
        if (isNotBlank(line)) {
            if (line.contains(COMMIT.getKeywords()) && safety1(line)) {
                gitLog.put(COMMIT, get(line));
            }

            if (line.contains(AUTHOR.getKeywords()) && safety1(line)) {
                gitLog.put(AUTHOR, get(line));
            }

            if (line.contains(DATE.getKeywords()) && safety2(line)) {
                gitLog.put(DATE, get2(line));
            }
        }
    }

    private boolean safety1(String line) {
        return Optional.ofNullable(line)
                .map(String::strip)
                .map(str -> str.split(SPACE))
                .map(str1 -> str1[1])
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private boolean safety2(String line) {
        return Optional.ofNullable(line)
                .map(String::strip)
                .map(str -> str.split(" {2}"))
                .map(str1 -> str1[1])
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }

    private String get(String line) {
        return Optional.ofNullable(line)
                .map(str -> str.split(SPACE))
                .map(str1 -> str1[1])
                .map(String::strip)
                .orElse(EMPTY);
    }

    private String get2(String line) {
        return Optional.ofNullable(line)
                .map(str -> str.split(" {2}"))
                .map(str1 -> str1[1])
                .map(String::strip)
                .orElse(EMPTY);
    }
}
