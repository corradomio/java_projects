package ae.ac.ebtic.tools.vr.data;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();
    private Map<Integer, Task> tasksMap = new HashMap<>();

    // ----------------------------------------------------------------------

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        populateHashMap();
    }

    // ----------------------------------------------------------------------

    public void add(Task t) {
        this.tasks.add(t);
        this.tasksMap.put(t.getId(), t);
    }


    public Task get(int i) {
        return this.getTasks().get(i);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        populateHashMap();
    }

    public List<Location> getLocations(List<Location> locations) {
        return tasks.stream().map(t -> locations.get(t.getLocId())).collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------

    private void populateHashMap(){
        this.tasksMap = new HashMap<>();
        for (Task t : tasks) {
            tasksMap.put(t.getId(), t);
        }
    }

    // ----------------------------------------------------------------------

    public static TaskList load(File tasksFile) throws Exception {
        List<Task> tasks;
        try(FileReader frdr = new FileReader(tasksFile)){
            tasks = new CsvToBeanBuilder(frdr)
                    .withType(Task.class).build().parse();
        }
        return new TaskList(tasks);
    }

    public void save(File taskFile) throws IOException {
        try(FileWriter outputfile  = new FileWriter(taskFile)) {
            CSVWriter writer = new CSVWriter(outputfile, ',', (char)0, '\\', "\n");

            writer.writeNext(new String[]{"id","locId","desc"});
            for (Task t : tasks)
                writer.writeNext(new String[]{
                    String.valueOf(t.getId()),
                    String.valueOf(t.getLocId()),
                    t.getDesc()
                });
        }
    }

}
