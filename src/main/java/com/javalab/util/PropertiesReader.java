package com.javalab.util;

import com.javalab.exceptions.PropertiesFileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;

    public PropertiesReader() throws PropertiesFileNotFoundException {
        this.properties = new Properties();
        readProperties();
    }

    private void readProperties() throws PropertiesFileNotFoundException {
        try (FileInputStream fis = new FileInputStream(Constants.PROPERTY_FILE_PATH)) {
            this.properties.load(fis);
        } catch (IOException e) {
            throw new PropertiesFileNotFoundException("File to the path " + Constants.PROPERTY_FILE_PATH + " no found");
        }
    }

    public String getPropertyByName(String propertyName) {
        return properties.getProperty(propertyName);
    }
}

