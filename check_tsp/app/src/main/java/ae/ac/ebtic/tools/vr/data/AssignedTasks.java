package ae.ac.ebtic.tools.vr.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignedTasks {

    private Map<Vehicle, TaskList> map;

    public AssignedTasks(Map<Vehicle, TaskList> map) {
        this.map = map;
    }

    public Map<Vehicle, TaskList> getMap() {
        return map;
    }

    // public void setMap(Map<Vehicle, TaskList> map) {
    //     this.map = map;
    // }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Vehicle, TaskList> entry : this.getMap().entrySet()) {
            Vehicle v = entry.getKey();
            s += v.getId() + " -> [";
            TaskList tl = entry.getValue();
            for (Task t: tl.getTasks()){
                s += t.getId() + ",";
            }
            s += "] \n";
        }
        return s;
    }

    public Map<Vehicle, List<Location>> getVehiclesPath(List<Location> locations) {

        Map<Vehicle, List<Location>> v2visit = new HashMap<>();

        for (Vehicle v : map.keySet()) {
            List<Location> visitedLocations = new ArrayList<>();
            Location start = locations.get(v.getLocId());

            visitedLocations.add(start);

            for(Task task : map.get(v).getTasks()) {
                Location toVisit = locations.get(task.getLocId());
                visitedLocations.add(toVisit);
            }

            v2visit.put(v, visitedLocations);
        }

        return v2visit;
    }
}
