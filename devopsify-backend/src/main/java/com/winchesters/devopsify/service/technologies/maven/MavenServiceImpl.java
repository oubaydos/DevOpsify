package com.winchesters.devopsify.service.technologies.maven;


import com.winchesters.devopsify.dto.request.GenerateMavenProjectDto;
import com.winchesters.devopsify.dto.response.TestResultDto;
import com.winchesters.devopsify.service.technologies.git.GitServiceImpl;
import com.winchesters.devopsify.utils.PatternUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MavenServiceImpl implements MavenService {

    public static void main(String[] args) throws MavenInvocationException, IOException {
        MavenService mavenService = new MavenServiceImpl();
        GenerateMavenProjectDto dto = new GenerateMavenProjectDto(
                "spring-boot-quick-start-archetype",
                "com.github.supermanhub",
                "com.winchesters",
                "devopsify-maven",
                "0.0.1-SNAPSHOT"
        );

        new MavenServiceImpl().generateMavenProject(dto, "C:\\Users\\oubay\\OneDrive\\Bureau\\devopsify\\project-name");

    }

    private final Logger LOG = LoggerFactory.getLogger(GitServiceImpl.class);


    @Override
    public boolean installed() {
        return installed("maven");
    }

    @Override
    public void install() {
        installFromScript("maven/maven_install.sh");
    }


    @Override
    public List<Dependency> getDependencies(String pomFilePath) throws IOException, XmlPullParserException {
        MavenXpp3Reader mavenReader = new MavenXpp3Reader();
        File pomFile = new File(pomFilePath);
        Model model = mavenReader.read(new FileReader(pomFile));
        return model.getDependencies();
    }

    @Override
    public InvocationResult build(String baseDirPath, String mavenExecutablePath) {

        File baseDirectory = new File(baseDirPath);
        File mavenExecutable = null;
        if (mavenExecutablePath != null)
            mavenExecutable = new File(mavenExecutablePath);

        Invoker invoker = new DefaultInvoker();

        InvocationRequest request = new DefaultInvocationRequest();

        request.setGoals(Collections.singletonList("install"))
                .setMavenOpts("-Dmaven.test.skip=true")
                .setBaseDirectory(baseDirectory)
                .setMavenExecutable(mavenExecutable)
                .setBatchMode(true);
        try {
            LOG.info("before build of project : " + baseDirPath);
            InvocationResult result = invoker.execute(request);
            LOG.info("after build of project : " + baseDirPath);
            return result;
        } catch (MavenInvocationException e) {
            //TODO : create a custom exception
            throw new IllegalStateException("MavenInvocationException");
        }
    }

    @Override
    public InvocationResult build(String baseDirPath) {
        return build(baseDirPath, null);
    }

    @Override
    public TestResultDto test(String baseDirPath, String mavenExecutablePath) {
        File baseDirectory = new File(baseDirPath);
        File mavenExecutable = null;
        if (mavenExecutablePath != null)
            mavenExecutable = new File(mavenExecutablePath);


        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                TeeOutputStream teeOutputStream = new TeeOutputStream(System.out, baos);
                PrintStream printStream = new PrintStream(teeOutputStream)
        ) {
            System.setOut(new PrintStream(printStream));
            Invoker invoker = new DefaultInvoker();

            InvocationRequest request = new DefaultInvocationRequest();
            request.setGoals(Collections.singletonList("test"))
                    .setBaseDirectory(baseDirectory)
                    .setMavenExecutable(mavenExecutable)
                    .setBatchMode(true);

            LOG.info("before running tests of project : " + baseDirPath);
            InvocationResult result = invoker.execute(request);
            LOG.info("after running tests of project : " + baseDirPath);
            if (result.getExitCode() != 0) {
                return null;
            }

            return getTestResultsFromTestOutput(new String(baos.toByteArray()));


        } catch (MavenInvocationException e) {
            //TODO : create a custom exception
            throw new IllegalStateException("MavenInvocationException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TestResultDto test(String baseDirPath) {
        return test(baseDirPath, null);
    }

    @Override
    public void generateMavenProject(GenerateMavenProjectDto dto, String baseDirPath) throws IOException {

        new ProcessBuilder(
                "mvn",
                "archetype:generate",
                String.format("-DgroupId=%s", dto.groupId()),
                String.format("-DartifactId=%s", dto.artifactId()),
                String.format("-DarchetypeGroupId=%s", dto.archetypeGroupId()),
                String.format("-DarchetypeArtifactId=%s", dto.archetypeArtifactId()),
                String.format("-DinteractiveMode=%s", false),
                String.format("-Dversion=%s", dto.version())
        )
                .directory(new File(baseDirPath))
                .inheritIO()
                .start();
    }

    TestResultDto getTestResultsFromTestOutput(String output) {
        Pattern pattern = Pattern.compile("Tests run: \\d+, Failures: \\d+, Errors: \\d+, Skipped: \\d+");
        Matcher matcher = pattern.matcher(output);
        List<Integer> infos;
        String testInfos;
        int testsRun = 0, failures = 0, errors = 0, skipped = 0;
        while (matcher.find()) {
            testInfos = matcher.group();
            LOG.info(testInfos);
            infos = PatternUtils.getAllIntegers(testInfos);
            testsRun += infos.get(0);
            failures += infos.get(1);
            errors += infos.get(2);
            skipped += infos.get(3);
        }
        TestResultDto testResultDto = new TestResultDto(testsRun, failures, errors, skipped);
        LOG.info(testResultDto.toString());
        return testResultDto;
    }
}
