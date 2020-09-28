public class Deadline extends Task {

    private String by;

    /**
     * Constructs a Deadline type of task.
     *
     * @param description String containing the description of the task.
     * @param by String containing the by timing of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    /**
     * Returns the by timing of the deadline.
     *
     * @return String of by timing of the deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a formatted String that shows the task is a deadline and its description and by timing.
     *
     * @return formatted String that shows the task is a deadline, contains description and by timing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
