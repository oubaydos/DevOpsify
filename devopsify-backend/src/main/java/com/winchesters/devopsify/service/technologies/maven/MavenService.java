package com.winchesters.devopsify.service.technologies.maven;

import com.winchesters.devopsify.dto.request.GenerateMavenProjectDto;
import com.winchesters.devopsify.dto.response.TestResultDto;
import com.winchesters.devopsify.service.technologies.TechnologyService;
import org.apache.maven.model.Dependency;
import org.apache.maven.shared.invoker.InvocationResult;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public interface MavenService extends TechnologyService {

    /**
     * List a maven project dependencies
     * @param pomXmlFilePath The path of the project's pom.xml file
     * @return list of dependencies
     * @throws IOException if there is I/O problems
     * @throws XmlPullParserException if the pom.xml can't be parsed
     */
    List<Dependency> getDependencies(String pomXmlFilePath) throws IOException, XmlPullParserException;

    /**
     * Run the build for a maven project ( mvn build )
     * this methode uses the wrapper if exists and the system's maven executable otherwise
     * @param baseDirPath the base directory of the project
     * @return InvocationResult - an Object containing information about the build
     */
    InvocationResult build(String baseDirPath);

    /**
     * Run the build for a maven project ( mvn build )
     * @param baseDirPath the base directory of the project
     * @param executablePath the path of the maven executable
     * @return InvocationResult - an Object containing information about the build
     */
    InvocationResult build(String baseDirPath,String executablePath);

    /**
     * Run project's tests
     * @param baseDirPath the base directory of the project
     * @param mavenExecutablePath the path of the maven executable
     * @return TestResultDto - an Object containing testsRun, failures, errors and skipped
     */
    TestResultDto test(String baseDirPath, String mavenExecutablePath);

    /**
     * Run project's tests
     * This methode uses the wrapper if exists and the system's maven executable otherwise
     * @param baseDirPath the base directory of the project
     * @return TestResultDto - an Object containing testsRun, failures, errors and skipped
     */
    TestResultDto test(String baseDirPath);

    /**
     * Generates maven project with maven archetype
     * @param dto an object containing the options for generating the project
     * @param baseDirPath the base directory of the project
     * @throws IOException if there is I/O problems
     * @throws InterruptedException if the process is interrupted
     */
    void generateMavenProject(GenerateMavenProjectDto dto, String baseDirPath) throws IOException, InterruptedException;
}
