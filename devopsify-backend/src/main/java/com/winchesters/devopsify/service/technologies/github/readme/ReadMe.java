package com.winchesters.devopsify.service.technologies.github.readme;

import com.winchesters.devopsify.enums.ReadMeStatus;

import java.util.List;
import java.util.regex.Pattern;

public class ReadMe {
    // TODO : verify those synonyms
    private static final List<String> technologySynonyms = List.of("technolog","tool");
    private static final List<String> statusSynonyms = List.of("status","phase","development","deployment","release");
    private static final List<String> howToUseSynonyms = List.of("use","how to","example");
    private static final Pattern versionPattern = Pattern.compile("version|v[0-9]");

    public static ReadMeStatus analyseReadMe(String readMe, String projectTitle) {
        if (!readMeIsNotEmpty(readMe).equals(ReadMeStatus.OKAY)) return readMeIsNotEmpty(readMe);
        readMe = readMe.toLowerCase();
        projectTitle = projectTitle.toLowerCase();
        if (!readMe.contains(projectTitle)) return ReadMeStatus.DOES_NOT_CONTAIN_TITLE;
        if (technologySynonyms.stream().noneMatch(readMe::contains)) return ReadMeStatus.DOES_NOT_CONTAIN_TECHNOLOGIES;
        if (statusSynonyms.stream().noneMatch(readMe::contains)) return ReadMeStatus.DOES_NOT_CONTAIN_STATUS;
        if (howToUseSynonyms.stream().noneMatch(readMe::contains)) return ReadMeStatus.DOES_NOT_CONTAIN_HOW_TO_USE;
        if (readMe.matches(versionPattern.pattern())) return ReadMeStatus.DOES_NOT_CONTAIN_VERSION;
        return ReadMeStatus.OKAY;
    }

    private static ReadMeStatus readMeIsNotEmpty(String readMe) {
        if (readMe == null || readMe.isEmpty()) return ReadMeStatus.EMPTY;
        if (readMe.lines().count() < 5) return ReadMeStatus.SMALL; // TODO figure out a better number
        return ReadMeStatus.OKAY;
    }
}
