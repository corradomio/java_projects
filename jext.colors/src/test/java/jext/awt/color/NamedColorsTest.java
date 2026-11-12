package jext.awt.color;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class NamedColorsTest {
    @Test public void matchesAllPythonReferenceColors() throws Exception {
        Set<String> names = new HashSet<>();
        Set<String> css = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/named-colors.tsv"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                Color expected = new Color(Integer.parseInt(fields[1], 16));
                assertEquals(fields[0], expected, NamedColors.get(fields[0]));
                assertEquals(expected, NamedColors.MATPLOTLIB.get(fields[0]));
                names.add(fields[0]);
                if (Boolean.parseBoolean(fields[2])) {
                    css.add(fields[0]);
                    // assertEquals(expected, NamedColors.CSS.get(fields[0]));
                }
            }
        }
        assertEquals(1163, names.size());
        assertEquals(148, css.size());
        assertEquals(names, NamedColors.MATPLOTLIB.keySet());
        // assertEquals(css, NamedColors.CSS.keySet());
        names.add("transparent");
        assertEquals(names, NamedColors.names());
    }

    @Test public void supportsNamesAliasesAndTransparency() {
        assertEquals(new Color(0x663399), NamedColors.get(" RebeccaPurple "));
        assertEquals(new Color(0x1f77b4), NamedColors.get("TAB:BLUE"));
        assertEquals(new Color(0x75bbfd), NamedColors.get("xkcd:sky blue"));
        assertEquals(new Color(0x00bfbf), NamedColors.get("c"));
        assertEquals(new Color(0x00ffff), NamedColors.get("cyan"));
        assertEquals(NamedColors.get("gray"), NamedColors.get("grey"));
        assertEquals(NamedColors.get("xkcd:gray"), NamedColors.get("xkcd:grey"));
        assertEquals(NamedColors.get("tab:gray"), NamedColors.get("tab:grey"));
        assertEquals(0, NamedColors.get("transparent").getRGB());
        assertThrows(IllegalArgumentException.class, () -> NamedColors.get(null));
        assertThrows(IllegalArgumentException.class, () -> NamedColors.get("unknown"));
        assertThrows(IllegalArgumentException.class, () -> NamedColors.get("currentColor"));
        assertThrows(UnsupportedOperationException.class, () -> NamedColors.ALL.clear());
        // assertThrows(UnsupportedOperationException.class, () -> NamedColors.CSS.clear());
        assertThrows(UnsupportedOperationException.class, () -> NamedColors.MATPLOTLIB.clear());
        assertThrows(UnsupportedOperationException.class, () -> NamedColors.names().clear());
    }
}
