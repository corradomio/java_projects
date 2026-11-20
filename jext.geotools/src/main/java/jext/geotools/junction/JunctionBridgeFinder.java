package jext.geotools.junction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JunctionBridgeFinder {

    public static List<JunctionBridge> findJunctionBridges(
        List<BoundaryJunctions> boundariesJunctions,
        double maxBridgeDistance, double maxBridgeAngle
    ) {
        // 1) find the junction bridges comparing all junctions with all junctions
        List<JunctionBridge> junctionBridges = findBridges(boundariesJunctions, maxBridgeDistance, maxBridgeAngle);

        // 2) remove multiple bridges FROM the same junction, selection the shortest one
        junctionBridges = filterOnOrigin(junctionBridges);

        // 3) remove multiple bridges TO the same junction, selection the shortest one
        junctionBridges = filterOnTarget(junctionBridges);

        return junctionBridges;
    }

    private static List<JunctionBridge> findBridges(
        List<BoundaryJunctions> boundariesJunctions,
        double maxBridgeDistance, double maxBridgeAngle
    ) {
        List<JunctionBridge> junctionBridges = new ArrayList<>();
        int njs = boundariesJunctions.size();
        double angle, distance;

        Set<Integer> registeredBridges = new HashSet<>();

        // Note: could be a good idea to check for the bridge from
        // BOTH directions. This because the bridge from the junction i in
        // segment j of boundary k could have a correct direction (90 degrees from the
        // segment), BUT the intersection on junction a of segment b of boundary c
        // could have a strange direction (for example less than 60 degrees)
        //
        // For now we suppose

        for (int i=0; i<njs; ++i) {
            BoundaryJunctions bji = boundariesJunctions.get(i);
            for (int j=0; j<njs; ++j) {
                BoundaryJunctions bjj = boundariesJunctions.get(j);
                if (i == j) continue;

                for (Junction ji : bji.nearJunctions) {
                    for (Junction jj : bjj.nearJunctions) {
                        JunctionBridge bridge = new JunctionBridge(ji, jj);

                        // junctions too far
                        distance = bridge.distance();
                        if (distance > maxBridgeDistance)
                            continue;

                        // angles too extreme
                        angle = bridge.angle();
                        if (angle > maxBridgeAngle)
                            continue;

                        Integer bridgeId = bridge.hashCode();
                        if (registeredBridges.contains(bridgeId))
                            continue;

                        junctionBridges.add(bridge);
                        registeredBridges.add(bridgeId);
                    }
                }
            }
        }

        return junctionBridges;
    }

    private static List<JunctionBridge> filterOnOrigin(List<JunctionBridge> junctionBridges) {
        List<JunctionBridge> selectedBridges = new ArrayList<>();
        Map<Integer, List<JunctionBridge>> duplicatedBridges = new HashMap<>();

        for (JunctionBridge junctionBridge : junctionBridges) {
            Integer originId = junctionBridge.origin.hashCode();
            if (!duplicatedBridges.containsKey(originId))
                duplicatedBridges.put(originId, new ArrayList<>());

            duplicatedBridges.get(originId).add(junctionBridge);
        }

        for (List<JunctionBridge> bridges : duplicatedBridges.values()) {
            JunctionBridge selectedBridge = bridges.get(0);
            if (bridges.size() > 1) {
                for (int i = 1; i < bridges.size(); ++i) {
                    JunctionBridge bridge = bridges.get(i);
                    if (bridge.distance() < selectedBridge.distance())
                        selectedBridge = bridge;
                }
            }
            selectedBridges.add(selectedBridge);
        }

        return selectedBridges;
    }

    private static List<JunctionBridge> filterOnTarget(List<JunctionBridge> junctionBridges) {
        List<JunctionBridge> selectedBridges = new ArrayList<>();
        Map<Integer, List<JunctionBridge>> duplicatedBridges = new HashMap<>();

        for (JunctionBridge junctionBridge : junctionBridges) {
            Integer targetId = junctionBridge.target.hashCode();
            if (!duplicatedBridges.containsKey(targetId))
                duplicatedBridges.put(targetId, new ArrayList<>());

            duplicatedBridges.get(targetId).add(junctionBridge);
        }

        for (List<JunctionBridge> bridges : duplicatedBridges.values()) {
            JunctionBridge selectedBridge = bridges.get(0);
            if (bridges.size() > 1) {
                for (int i = 1; i < bridges.size(); ++i) {
                    JunctionBridge bridge = bridges.get(i);
                    if (bridge.distance() < selectedBridge.distance())
                        selectedBridge = bridge;
                }
            }
            selectedBridges.add(selectedBridge);
        }

        return selectedBridges;
    }
}
