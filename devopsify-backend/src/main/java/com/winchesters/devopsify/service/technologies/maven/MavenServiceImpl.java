package com.winchesters.devopsify.service.technologies.maven;

import com.winchesters.devopsify.service.technologies.git.GitServiceImpl;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MavenServiceImpl implements MavenService {

    public static void main(String[] args) {
        MavenService mavenService = new MavenServiceImpl();
        mavenService.build("/media/hamza/ENSIAS/Ensias/s4/JEE/account-sharing-app",
                "/media/hamza/ENSIAS/Ensias/s4/JEE/account-sharing-app/mvnw" );
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
    public InvocationResult build(String baseDirPath,String mavenExecutablePath) {

        File baseDirectory = new File(baseDirPath);
        File mavenExecutable = null;
        if(mavenExecutablePath!=null)
            mavenExecutable = new File(mavenExecutablePath);

        Invoker invoker = new DefaultInvoker();

        InvocationRequest request = new DefaultInvocationRequest();

        request.setGoals(Collections.singletonList("install"))
                .setMavenOpts("-Dmaven.test.skip=true")
                .setMavenExecutable(null)
                .setBaseDirectory(baseDirectory)
                .setMavenExecutable(mavenExecutable)
                .setBatchMode(true);
        try{
            LOG.info("before build of project : "+baseDirPath);
            InvocationResult result = invoker.execute(request);
            LOG.info("after build of project : "+baseDirPath);
            return result;
        }catch (MavenInvocationException e){
            //TODO : create a custom exception
            throw new IllegalStateException("MavenInvocationException");
        }
    }

    @Override
    public InvocationResult build(String baseDirPath) {
        return build(baseDirPath,null);
    }
}
