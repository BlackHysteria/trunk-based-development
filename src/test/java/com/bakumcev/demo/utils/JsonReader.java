package com.bakumcev.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.util.Assert.notNull;

@Slf4j
public class JsonReader {

    public String getResourceAsString(String fileName) {
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(fileName);
        notNull(is, "Not found resource by path " + fileName);
        try {
            return FileCopyUtils.copyToString(new InputStreamReader(is));
        } catch (IOException e) {
            log.error("Error read file {}", fileName, e);
        }
        return null;
    }
}
