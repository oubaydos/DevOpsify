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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class DockerfileUtils {
    private static final String COMMENT_CHARACTER = "#";
    private String DockerfileContent;

    public static String commentLine(File Dockerfile, int lineNumber) throws IOException {
        List<String> lines = FileUtils.readLines(Dockerfile, StandardCharsets.UTF_8);
        lineNumber--;
        lines.set(lineNumber, COMMENT_CHARACTER + lines.get(lineNumber));
        return StringUtils.join(lines, "\n");
    }

    public List<Integer> getLineContaining(String keyword) {
        List<String> lines = Arrays.stream(DockerfileContent.split("\n")).toList();
        List<Integer> list = IntStream.range(0, lines.size())
                        .filter(i -> lines.get(i).contains(keyword))
                        .boxed()
                        .collect(toCollection(ArrayList::new));
        // increment for consistency reasons ( lines: from 1-n not 0-(n-1))
        for (int i = 0; i < list.size(); i++) {
            list.set(i,list.get(i)+1);
        }
        return list;
    }

    public DockerfileUtils commentLine(int lineNumber) {
        List<String> lines = Arrays.stream(DockerfileContent.split("\n")).collect(toCollection(ArrayList::new));
        lineNumber--;
        if (lineNumber >= lines.size()) return this;
        lines.set(lineNumber, COMMENT_CHARACTER + lines.get(lineNumber));
        DockerfileContent = StringUtils.join(lines, "\n");
        return this;
    }

    public DockerfileUtils commentAllLinesAfter(int lineNumber){
        int numberOfLines = Math.toIntExact(Arrays.stream(DockerfileContent.split("\n")).count());
        for (int i = lineNumber; i <= numberOfLines; i++) {
            commentLine(i);
        }
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
