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

    List<Dependency> getDependencies(String pomXmlFilePath) throws IOException, XmlPullParserException;

    InvocationResult build(String baseDirPath);

    InvocationResult build(String baseDirPath,String executablePath);

    TestResultDto test(String baseDirPath, String mavenExecutablePath);

    TestResultDto test(String baseDirPath);

    void generateMavenProject(GenerateMavenProjectDto dto, String baseDirPath) throws IOException;
}
