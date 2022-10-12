package jext.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jext.logging.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JSONUtils {

    private static Logger logger = Logger.getLogger(JSONUtils.class);

    // ----------------------------------------------------------------------
    // read

    public static <T> T load(File jsonFile, Class<T> objectType) throws IOException {
        return parse(jsonFile, objectType);
    }

    public static <T> T parse(File jsonFile, Class<T> objectType) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        return (T) mapper.readValue(jsonFile, objectType);
    }

    public static <T> T parse(InputStream istream, Class<T> objectType) throws IOException {
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        FileUtils.copy(istream, ostream);
        String content = new String(ostream.toByteArray(), StandardCharsets.UTF_8);
        return parse(content, objectType);
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

    // ----------------------------------------------------------------------
    // write

    public static <T> void store(File jsonFile, T item) throws IOException {
        save(jsonFile, item);
    }

    public static <T> void save(File jsonFile, T item) {
        try {
            ObjectMapper mapper = newObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(jsonFile, item);
        }
        catch (IOException e) {
            logger.errorf("Unable to write file %s: %s", jsonFile, e);
        }
    }

    public static <T> void save(Writer writer, T item) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(writer, item);
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
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
