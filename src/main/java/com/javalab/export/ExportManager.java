package com.javalab.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.javalab.exceptions.ExportException;
import com.javalab.model.Journal;
import com.javalab.model.Task;

import java.io.File;
import java.io.IOException;

public class ExportManager {

    public void exportTask(Task task, String filePath) throws ExportException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), task);
        } catch (IOException e) {
            throw new ExportException("Filed to export because ?");

        }
    }

    public void exportJournal(Journal journal, String filePath, String format) throws ExportException {
        switch (format) {
            case ("json"):
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    objectMapper.writeValue(new File(filePath), journal);
                } catch (IOException e) {
                    throw new ExportException("Filed to export because " + e.getMessage());
                }
                break;
            case ("xml"):
                ObjectMapper xmlMapper = new XmlMapper();
                try {
                    xmlMapper.writeValue(new File(filePath), journal);
                } catch (IOException e) {
                    throw new ExportException("Filed to export because " + e.getMessage());
                }
                break;
            default:
                throw new ExportException("Incorrect data format in properties. Please choose json or xml");
        }
    }
}
