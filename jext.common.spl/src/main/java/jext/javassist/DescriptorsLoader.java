package jext.javassist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jext.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DescriptorsLoader {

    // private static final Logger logger = Logger.getLogger(DescriptorsLoader.class);
    //
    // public static final String DESCRIPTOR     = ".descriptor";
    // public static final String DESCRIPTOR_GZ  = ".descriptor.gz";
    // public static final String DESCRIPTOR_ZIP = ".descriptor.zip";

    // public static List<TypeDesc> load(File descriptorFile) {
    //     // .descriptor == JSON
    //     // .descriptor.zip
    //     // .descriptor.gz
    //     String name = descriptorFile.getName();
    //     try(InputStream stream = new FileInputStream(descriptorFile)) {
    //         return load(stream, name);
    //     }
    //     catch (IOException e) {
    //         logger.error(descriptorFile.toString(), e);
    //         return Collections.emptyList();
    //     }
    // }

    // public static List<TypeDesc> load(InputStream stream, String ext) throws IOException {
    //     if (ext.endsWith(DESCRIPTOR))
    //         ;
    //     else if (ext.endsWith(DESCRIPTOR_GZ))
    //         stream = new GZIPInputStream(stream);
    //     else if (ext.endsWith(DESCRIPTOR_ZIP)) {
    //         ZipInputStream zis = new ZipInputStream(stream);
    //         ZipEntry ze = zis.getNextEntry();
    //         stream = zis;
    //     }
    //
    //     return fromJSON(new TypeReference<List<TypeDesc>>() { }, stream);
    // }

    // private static <T> T fromJSON(final TypeReference<T> type, final InputStream jsonPacket) {
    //     T data = null;
    //
    //     try {
    //         ObjectMapper mapper = new ObjectMapper();
    //         // skip problems with 'unkown properties' (properties created by a different
    //         // version of the serialized class
    //         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //         data = mapper.readValue(jsonPacket, type);
    //     } catch (Exception e) {
    //         // Handle the problem
    //         logger.error(e, e);
    //     }
    //     return data;
    // }
}
