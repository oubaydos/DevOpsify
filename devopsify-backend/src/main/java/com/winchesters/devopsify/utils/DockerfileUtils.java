package com.winchesters.devopsify.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

public class DockerfileUtils {
    private static final String COMMENT_CHARACTER = "#";
    private String DockerfileContent;

    public static String commentLine(File Dockerfile, int lineNumber) throws IOException {
        List<String> lines = FileUtils.readLines(Dockerfile, StandardCharsets.UTF_8);
        lineNumber--;
        lines.set(lineNumber, COMMENT_CHARACTER + lines.get(lineNumber));
        return StringUtils.join(lines, "\n");
    }

    public DockerfileUtils commentLine(int lineNumber) {
        List<String> lines = Arrays.stream(DockerfileContent.split("\n")).collect(toCollection(ArrayList::new));
        lineNumber--;
        lines.set(lineNumber, COMMENT_CHARACTER + lines.get(lineNumber));
        DockerfileContent = StringUtils.join(lines, "\n");
        return this;
    }

    public DockerfileUtils setDockerfileKeywordValue(String keyword, String value) {
        DockerfileContent = this.DockerfileContent.replace(keyword, value);
        return this;
    }

    public DockerfileUtils(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        DockerfileContent = StringUtils.join(lines, "\n");
    }

    public DockerfileUtils setDockerfileKeywordValue(Map<String, String> map) {
        for (String keyword : map.keySet())
            DockerfileContent = this.DockerfileContent.replace(keyword, map.get(keyword));
        return this;
    }

    public String getDockerfileContent() {
        return DockerfileContent;
    }

}
