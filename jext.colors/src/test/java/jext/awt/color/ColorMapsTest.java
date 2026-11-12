package jext.awt.color;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import jext.awt.color.cmap.ColorMap;
import jext.awt.color.cmap.ColorMaps;
import jext.awt.color.cmap.Tab10;
import jext.awt.color.cmap.Tab20;
import jext.awt.color.cmap.Viridis;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColorMapsTest {
    @Test public void matchesMatplotlibReference() throws Exception {
        Set<String> tested = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/matplotlib-colormaps.tsv"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                ColorMap map = ColorMaps.get(fields[0]);
                int expected = Integer.parseInt(fields[2], 16);
                assertEquals(line, expected, map.getColor(Double.parseDouble(fields[1])).getRGB() & 0xffffff);
                tested.add(fields[0]);
            }
        }
        assertEquals(ColorMaps.names(), tested);
    }

    @Test public void exposesPairedClassesAndImmutablePalettes() throws Exception {
        Set<Class<?>> classes = new HashSet<>();
        for (String name : ColorMaps.names()) {
            ColorMap map = ColorMaps.get(name);
            boolean reversed = name.endsWith("_r");
            if (!reversed) {
                assertTrue(classes.add(map.getClass()));
                ColorMap reverse = ColorMaps.get(name + "_r");
                assertSame(map.getClass(), reverse.getClass());
                assertSame(map, map.getClass().getField("INSTANCE").get(null));
                assertSame(reverse, map.getClass().getField("REVERSED").get(null));
                assertEquals(map.size(), reverse.size());
                assertArrayEquals(map.getColors(), map.getClass().getConstructor().newInstance().getColors());
            }
            assertEquals(name, map.getName());
            ColorMap constructed = map.getClass().getConstructor(boolean.class).newInstance(reversed);
            assertEquals(name, constructed.getName());
            assertArrayEquals(map.getColors(), constructed.getColors());
            Color[] copy = map.getColors();
            copy[0] = null;
            assertNotNull(map.getColor(0));
            assertEquals(0, map.getColor(Double.NaN).getAlpha());
            for (int i = 0; i < map.size(); i++)
                assertEquals(map.getColor(i), map.getColor((i + 0.5) / map.size()));
        }
        assertEquals(ColorMaps.names().size() / 2, classes.size());
        assertThrows(UnsupportedOperationException.class, () -> ColorMaps.names().clear());
        assertThrows(IllegalArgumentException.class, () -> ColorMaps.get("missing"));
    }

    @Test public void samplesForPandasStyleSeries() {
        ColorMap map = Viridis.INSTANCE;
        assertEquals(256, map.size());
        assertEquals(10, Tab10.INSTANCE.size());
        assertEquals(20, Tab20.INSTANCE.size());
        assertEquals(0x440154, map.getColor(0).getRGB() & 0xffffff);
        assertEquals(0xfde725, map.getColor(1.0).getRGB() & 0xffffff);
        assertArrayEquals(new Color[] {map.getColor(0.0), map.getColor(0.5), map.getColor(1.0)}, map.sample(3));
        assertArrayEquals(new Color[] {map.getColor(0)}, map.sample(1));
        assertEquals(0, map.sample(0).length);
        assertThrows(IllegalArgumentException.class, () -> map.sample(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> map.getColor(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> map.getColor(map.size()));
    }
}
