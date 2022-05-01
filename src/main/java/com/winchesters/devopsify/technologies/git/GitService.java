package com.winchesters.devopsify.technologies.git;

import com.winchesters.devopsify.technologies.TechnologyService;
import com.winchesters.devopsify.technologies.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface GitService extends TechnologyService {

    int initializeRepository(String path);

}
