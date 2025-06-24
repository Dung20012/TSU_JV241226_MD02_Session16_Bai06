package BTVN.Exercise06;

public class Task {
    private int id;
    private String taskName;
    private String status;

//    Constructor có tham số
    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
    }
//    Getter
    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }
}
