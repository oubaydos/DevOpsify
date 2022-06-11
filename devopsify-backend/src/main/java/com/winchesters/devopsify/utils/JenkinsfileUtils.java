package com.winchesters.devopsify.utils;

import java.io.File;
import java.io.IOException;

public class JenkinsfileUtils extends FilesUtils {
    public JenkinsfileUtils(File file) throws IOException {
        super(file, "//");
    }
}
