package ae.ac.ebtic.tools.vr.data;

public class Task {

    private int id;
    private String desc = "";
    private int locId;

    public Task() {
    }

    public Task(int id, int locId) {
        this.id = id;
        this.desc = "T"+id;
        this.locId = locId;
    }

    public Task(int id, String desc, int locId) {
        this.id = id;
        this.desc = desc;
        this.locId = locId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

}
