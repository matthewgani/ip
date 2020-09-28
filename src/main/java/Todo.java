public class Todo extends Task {

    /**
     * Constructs a Todo type of task
     *
     * @param description String containing the description of the task.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "todo";
    }

    /**
     * Returns a formatted String that shows the task is a todo and its description.
     *
     * @return formatted String that shows the task is a todo, contains description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
