package jext.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONUtils {

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

    public static <T> List<T> parseAll(File[] jsonFiles, Class<T> objectType) {
        ObjectMapper mapper = newObjectMapper();
        List<T> ilist = new ArrayList<>();

        if (jsonFiles != null)
        for(File jsonFile : jsonFiles) {
            try {
                T item = (T) mapper.readValue(jsonFile, objectType);
                ilist.add(item);
            }
            catch(Exception e) {
                logger.error(e);
            }
        }

        return ilist;
    }

    private static ObjectMapper newObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static Object get(Map<String, ?> map, String path) {
        String[] steps = path.split("/");
        Object current = map;
        for(String step : steps) {
            if (current == null)
                return current;

            current = ((Map<String, ?>) current).get(step);
        }
        return current;
    }

}
