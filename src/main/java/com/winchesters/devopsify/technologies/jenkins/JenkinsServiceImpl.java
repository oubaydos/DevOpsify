package com.winchesters.devopsify.technologies.jenkins;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JenkinsServiceImpl implements JenkinsService{
    @Override
    public void generateJenkinsFile(File directory) {
            //for now, it only generates empty Jenkinsfile
            try{
                File file = new File(directory,"Jenkinsfile");
                FileWriter writer = new FileWriter(file);
                writer.append("//empty Jenkinsfile");
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
    }

    @Override
    public void pingJenkinsServer(String serverUrl) {

    }
}
