public class Task {

    private String description;
    private static int numberOfTasks = 0;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
        numberOfTasks++;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public Boolean getDoneStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String getTaskType() {
        return taskType;
    }



}
