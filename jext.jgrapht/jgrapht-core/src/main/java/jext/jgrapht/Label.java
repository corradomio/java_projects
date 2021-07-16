package jext.jgrapht;

import java.util.HashMap;
import java.util.Map;

public class Label {

    public static class Factory {
        private final Map<String, Label> labels = new HashMap<>();

        public Label of(String label) {
            if (!labels.containsKey(label))
                labels.put(label, new Label(label));
            return labels.get(label);
        }
    }

    private String label;

    private Label(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        Label that = (Label) obj;
        return this == that;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }
}
