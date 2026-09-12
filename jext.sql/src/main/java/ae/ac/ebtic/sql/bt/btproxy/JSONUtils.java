package ae.ac.ebtic.sql.bt.btproxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class JSONUtils {

    //private static Logger logger = Logger.getLogger(JSONUtils.class);

    // ----------------------------------------------------------------------
    // read

    public static LinkedHashMap load(File jsonFile) throws IOException {
        return load(jsonFile, LinkedHashMap.class);
    }

    public static <T> T load(File jsonFile, Class<T> objectType) throws IOException {
        return parse(jsonFile, objectType);
    }

    public static <T> T parse(File jsonFile, Class<T> objectType) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        return (T) mapper.readValue(jsonFile, objectType);
    }

    public static <T> T parse(Reader reader, Class<T> objectType) throws IOException {
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        copy(reader, ostream);
        String content = new String(ostream.toByteArray(), StandardCharsets.UTF_8);
        return parse(content, objectType);
    }

    private static void copy(Reader reader, OutputStream os) throws IOException {
        for (int c = reader.read(); c != -1; c = reader.read()) {
            os.write((byte)c);
        }
    }

    public static <T> T parse(InputStream istream, Class<T> objectType) throws IOException {
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        copy(istream, ostream);
        String content = new String(ostream.toByteArray(), StandardCharsets.UTF_8);
        return parse(content, objectType);
    }

    public static <T> T parse(String content, Class<T> objectType) {
        ObjectMapper mapper = newObjectMapper();
        try {
            return (T) mapper.readValue(content, objectType);
        } catch (JsonProcessingException e) {
            //logger.error(e, e);
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[1024];
        for (int n = in.read(b); n > 0; n = in.read(b))
            out.write(b, 0, n);
    }

    // ----------------------------------------------------------------------
    // write

    // public static <T> void store(File jsonFile, T item) throws IOException {
    //     save(jsonFile, item);
    // }

    // public static <T> void save(File jsonFile, T item) {
    //     try {
    //         ObjectMapper mapper = newObjectMapper();
    //         mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //         mapper.writeValue(jsonFile, item);
    //     }
    //     catch (IOException e) {
    //         //logger.errorf("Unable to write file %s: %s", jsonFile, e);
    //     }
    // }

    // public static <T> void save(Writer writer, T data) throws IOException {
    //     ObjectMapper mapper = newObjectMapper();
    //     mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //     mapper.writeValue(writer, data);
    // }

    public static <T> String serialize(T data) {
        ObjectMapper mapper = newObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            //logger.error(e, e);
            return "{}";
        }
    }

    public static <T> void serialize(Writer writer, T data) throws IOException {
        ObjectMapper mapper = newObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(writer, data);
    }

    private static ObjectMapper newObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
