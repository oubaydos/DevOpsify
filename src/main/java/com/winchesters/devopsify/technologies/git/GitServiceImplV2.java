package com.winchesters.devopsify.technologies.git;

import com.winchesters.devopsify.exception.GitException;
import com.winchesters.devopsify.exception.GitNotInstalledException;
import com.winchesters.devopsify.technologies.Version;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GitServiceImplV2 implements GitService{
    public static void main(String[] args){
        GitService gitService = new GitServiceImplV2();
        System.out.println(gitService.installed());
        System.out.println(gitService.getVersion());
        gitService.initializeRepository("/home/hamza/test");
    }

    @Override
    public boolean installed() {
        return getVersion() != null;
    }

    @Override
    public Version getVersion() {
        try {
            Process process = new ProcessBuilder("git", "--version")
                    .start();
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
    @Override
    public void initializeRepository(String path) throws GitException {
        try {
            if(!installed()) throw new GitNotInstalledException();
            Process process = new ProcessBuilder("git", "init")
                    .directory(new File(path))
                    .inheritIO()
                    .start();
            process.waitFor();
            System.out.println(process.exitValue());
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
