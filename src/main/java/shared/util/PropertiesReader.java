package shared.util;

import shared.exceptions.PropertiesFileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;
    private String pathFile;

    public PropertiesReader(String pathFile) throws PropertiesFileNotFoundException {
        this.properties = new Properties();
        this.pathFile=pathFile;
        readProperties();
    }

    private void readProperties() throws PropertiesFileNotFoundException {

        try (FileInputStream fis = new FileInputStream(pathFile)) {
            this.properties.load(fis);
        } catch (IOException e) {

            throw new PropertiesFileNotFoundException("File to the path " + pathFile + " no found");
        }
    }

    public String getPropertyByName(String propertyName) {
        return properties.getProperty(propertyName);
    }
}

