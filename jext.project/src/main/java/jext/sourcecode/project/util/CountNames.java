package jext.sourcecode.project.util;

import java.util.HashMap;

public class CountNames extends HashMap<String, Integer> {

    public void add(String name) {
        this.put(name, 1 + this.getOrDefault(name, 0));
    }

    public String getMostFrequentName() {
        String selected = null;
        int selectedCount = 0;
        for (String name : keySet()) {
            int count = get(name);
            if (count > selectedCount) {
                selected = name;
                selectedCount = count;
            }
        }
        return selected;
    }
}