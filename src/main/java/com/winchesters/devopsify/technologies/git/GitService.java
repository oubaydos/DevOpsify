package com.winchesters.devopsify.technologies.git;

import com.winchesters.devopsify.technologies.TechnologyService;
import com.winchesters.devopsify.technologies.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface GitService extends TechnologyService {

    @Override
    default Version getVersion() {
        try {
            Process process = new ProcessBuilder("git", "--version").start();
            BufferedReader stdInput
                    = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuilder outputBuilder = new StringBuilder();
            String s;
            while ((s = stdInput.readLine()) != null) {
                outputBuilder.append(s);
            }
            String output = outputBuilder.toString();
            Pattern versionPattern = Pattern.compile("git version \\d+.\\d+.\\d+");
            Matcher versionMatcher = versionPattern.matcher(output);
            if(!versionMatcher.matches()){
                return null;
            }else{
                versionMatcher = Pattern.compile("\\d+").matcher(output);
                Version version = new Version();
                versionMatcher.find();
                version.setMajor(Integer.parseInt(versionMatcher.group()));
                versionMatcher.find();
                version.setMinor(Integer.parseInt(versionMatcher.group()));
                versionMatcher.find();
                version.setPatch(Integer.parseInt(versionMatcher.group()));
                return version;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    int initializeRepository();

}
