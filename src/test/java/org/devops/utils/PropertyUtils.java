package org.devops.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyUtils {

    public static Properties readPropertyFile(String path) throws IOException {
        Properties prop = new Properties();
        InputStream input = Files.newInputStream(Paths.get(path));
        prop.load(input);
        return prop;
    }
}
