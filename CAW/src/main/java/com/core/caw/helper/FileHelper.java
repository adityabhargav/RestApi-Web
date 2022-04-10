package com.core.caw.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileHelper {
    public static String getPropFile(String file, String key) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream(file);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {

        }
        return prop.getProperty(key);
    }
}
