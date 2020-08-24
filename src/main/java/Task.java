public class Task {

    private String taskDescription;
    private static int numberOfTasks;

    public Task(String description) {
        this.taskDescription = description;
        numberOfTasks++;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

}
