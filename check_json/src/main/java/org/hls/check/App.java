package org.hls.check;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) throws IOException {

        Map<String,?> json = JSONUtils.load(new File("C:\\Users\\Corrado Mio\\Downloads\\TypeDiagram-Featture-DublicateLineIssue-formatted.json"),
            HashMap.class);

        List<Map<String, ?>> deps = (List<Map<String, ?>>) JSONUtils.get(json, "graph/operationCalls");

        Set<String> edges = new HashSet<>();

        for(Map<String, ?> dep : deps) {
            String id = dep.get("id").toString();
            if (edges.contains(id))
                System.out.println("duplicated " + id);
            else
                edges.add(id);
        }

        System.out.println(edges.size());

        // System.out.println(json);
    }
}
