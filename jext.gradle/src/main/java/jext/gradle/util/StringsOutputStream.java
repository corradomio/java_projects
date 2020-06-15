package jext.gradle.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringsOutputStream extends ByteArrayOutputStream {
    private List<String> content = new ArrayList<>();

    @Override
    public void close() {
        try {
            super.close();
            String readed = new String(this.toByteArray(), StandardCharsets.UTF_8);
            content = Arrays.stream(readed.split("(\n)"))
                    .map(String::trim)
                    .filter(line -> line.length() > 0)
                    .collect(Collectors.toList());
        } catch (IOException e) {

        }
    }

    public List<String> getContent() {
        return content;
    }

}
