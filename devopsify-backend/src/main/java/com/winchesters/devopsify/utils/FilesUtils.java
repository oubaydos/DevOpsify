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
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public abstract class FilesUtils {
    protected String COMMENT_CHARACTER;
    protected String fileContent;

    public String getFileContent() {
        return fileContent;
    }

    public FilesUtils(File file, String commentCharacter) throws IOException {
        COMMENT_CHARACTER = commentCharacter;
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        fileContent = StringUtils.join(lines, "\n");
    }

    protected static String commentLine(File file, int lineNumber, String commentCharacter) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        lineNumber--;
        lines.set(lineNumber, commentCharacter + lines.get(lineNumber));
        return StringUtils.join(lines, "\n");
    }

    public final List<Integer> getLineContaining(String keyword) {
        List<String> lines = Arrays.stream(fileContent.split("\n")).toList();
        List<Integer> list = IntStream.range(0, lines.size())
                .filter(i -> lines.get(i).contains(keyword))
                .boxed()
                .collect(toCollection(ArrayList::new));
        // increment for consistency reasons ( lines: from 1-n not 0-(n-1))
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + 1);
        }
        return list;
    }

    public final FilesUtils commentLine(int lineNumber) {
        List<String> lines = Arrays.stream(fileContent.split("\n")).collect(toCollection(ArrayList::new));
        lineNumber--;
        if (lineNumber >= lines.size()) return this;
        lines.set(lineNumber, COMMENT_CHARACTER + lines.get(lineNumber));
        fileContent = StringUtils.join(lines, "\n");
        return this;
    }

    public FilesUtils unCommentLine(int lineNumber) {
        List<String> lines = Arrays.stream(fileContent.split("\n")).collect(toCollection(ArrayList::new));
        lineNumber--;
        if (lineNumber >= lines.size()) return this;
        String line = lines.get(lineNumber).replaceAll("^" + COMMENT_CHARACTER + "+", "");
        lines.set(lineNumber, line);
        fileContent = StringUtils.join(lines, "\n");
        return this;
    }

    public FilesUtils commentAllLinesAfter(int lineNumber) {
        int numberOfLines = Math.toIntExact(Arrays.stream(fileContent.split("\n")).count());
        for (int i = lineNumber; i <= numberOfLines; i++) {
            commentLine(i);
        }
        return this;
    }

    public FilesUtils setFileKeywordValue(String keyword, String value) {
        fileContent = this.fileContent.replace(keyword, value);
        return this;
    }

    public FilesUtils setFileKeywordValue(Map<String, String> map) {
        for (String keyword : map.keySet())
            fileContent = this.fileContent.replace(keyword, map.get(keyword));
        return this;
    }
}
