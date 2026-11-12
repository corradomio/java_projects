package jext.geotools;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PolygonClusterer {

    public static List<List<Polygon>> clusterPolygons(List<Polygon> polygons, double tolerance) {
        int n = polygons.size();
        List<Geometry> bufferedPolys = new ArrayList<>(n);
        STRtree index = new STRtree();

        // 1. Buffer slightly to bridge tiny coordinate gaps, and index them
        for (int i = 0; i < n; i++) {
            Polygon p = polygons.get(i);
            Geometry buffered = p.buffer(tolerance); // Bridges small gaps
            bufferedPolys.add(buffered);
            index.insert(buffered.getEnvelopeInternal(), i);
        }
        index.build();

        // 2. Build adjacency graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            Geometry geomI = bufferedPolys.get(i);
            @SuppressWarnings("unchecked")
            List<Integer> candidates = index.query(geomI.getEnvelopeInternal());

            for (int j : candidates) {
                if (i < j && geomI.intersects(bufferedPolys.get(j))) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        // 3. Find Connected Components (Clusters) via BFS
        boolean[] visited = new boolean[n];
        List<List<Polygon>> clusters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Polygon> cluster = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    cluster.add(polygons.get(curr));

                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                clusters.add(cluster);
            }
        }
        return clusters;
    }
}
