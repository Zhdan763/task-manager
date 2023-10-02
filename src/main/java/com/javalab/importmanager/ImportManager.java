package com.javalab.importmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.javalab.exceptions.ImportException;
import com.javalab.model.Journal;

import java.io.File;
import java.io.IOException;

public class ImportManager {
    public Journal importJournal(String filePath, String dataFormat) throws ImportException {
        switch (dataFormat) {
            case ("json"):
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Journal journal = objectMapper.readValue(new File(filePath), Journal.class);
                    return journal;
                } catch (IOException e) {
                    throw new ImportException("Filed to export because " + e.getMessage());
                }
            case ("xml"):
                ObjectMapper xmlMapper = new XmlMapper();
                try {
                    Journal journal = xmlMapper.readValue(new File(filePath), Journal.class);
                    return journal;
                } catch (IOException e) {
                    throw new ImportException("Filed to export because " + e.getMessage());
                }
            default:
                throw new ImportException("Incorrect data format in properties. Please choose json or xml");
        }
    }
}
