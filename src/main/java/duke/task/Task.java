package duke.task;

public class Task {

    private String description;
    /** Number of tasks in the task list */
    private static int numberOfTasks = 0;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructs a task that is not done.
     *
     * @param description String containing description of the task.
     */
    public Task(String description) {
        setDescription(description);
        this.isDone = false;
        numberOfTasks++;
    }

    /**
     * Returns the description of the current task.
     *
     * @return String containing description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the input to the description of the task.
     *
     * @param description String containing description to write to the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns number of tasks.
     * @return number of tasks.
     */
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Returns tick or cross depending if the task is done.
     *
     * @return String that represents a tick or cross
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Decreases the number ot tasks by 1.
     */
    public static void decreaseNumberOfTasks() {
        numberOfTasks--;
    }

    /**
     * Resets the number of tasks to 0.
     */
    public static void resetNumberOfTasks() {
        numberOfTasks = 0;
    }

    /**
     * Returns if the task is done.
     *
     * @return Boolean value depending if the task is done.
     */
    public Boolean getDoneStatus(){
        return isDone;
    }

    /**
     * Returns a string message that shows the done status icon of the task and description.
     *
     * @return String that shows the done status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the type of task.
     *
     * @return String that is the type of task.
     */
    public String getTaskType() {
        return taskType;
    }
}
