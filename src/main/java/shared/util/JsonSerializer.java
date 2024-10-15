package shared.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonSerializer {
    private static JsonSerializer instance;
    private ObjectMapper objectMapper;

    private JsonSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    public static synchronized JsonSerializer getInstance() {
        if (instance == null) {
            instance = new JsonSerializer();
        }
        return instance;
    }

    public void readValuesInText() {

    }

    public void writeClassToPath() {

    }

    public <T> T readValueFromFile(String fileName, Class<T> valueType) throws IOException {
        return (T) objectMapper.readValue(new File(fileName), valueType);
    }

    public <T> T readValueFromText(String text, Class<T> valueType) throws IOException {
        return (T) objectMapper.readValue(text, valueType);
    }

    public String writeJson(Object object) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(object);
        return json;
    }


}
