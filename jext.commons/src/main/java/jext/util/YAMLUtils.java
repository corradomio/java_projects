package jext.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;

public class YAMLUtils {

    private static Logger logger = Logger.getLogger(JSONUtils.class);

    public static <T> void store(File jsonFile, T item) throws IOException {
        save(jsonFile, item);
    }

    public static <T> T load(File jsonFile, Class<T> objectType) throws IOException {
        return parse(jsonFile, objectType);
    }

    public static <T> void save(File jsonFile, T item) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(jsonFile, item);
    }

    public static <T> T parse(File jsonFile, Class<T> objectType) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        return (T) mapper.readValue(jsonFile, objectType);
    }

    public static <T> T parse(String content, Class<T> objectType) {
        ObjectMapper mapper = newObjectMapper();
        try {
            return (T) mapper.readValue(content, objectType);
        } catch (JsonProcessingException e) {
            logger.error(e, e);
            return null;
        }
    }


    public static <T> String serialize(T data) {
        ObjectMapper mapper = newObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error(e, e);
            return "{}";
        }
    }

    private static ObjectMapper newObjectMapper() {
        return new ObjectMapper(new YAMLFactory()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
