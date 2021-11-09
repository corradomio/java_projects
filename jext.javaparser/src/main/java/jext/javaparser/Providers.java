package jext.javaparser;

import com.github.javaparser.Provider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static com.github.javaparser.utils.Utils.assertNotNull;

public class Providers {

    public static Provider provider(Path path) throws IOException {
        // synchronized (Providers.class)
        {
            return com.github.javaparser.Providers.provider(assertNotNull(path), StandardCharsets.UTF_8);
        }
    }

}
