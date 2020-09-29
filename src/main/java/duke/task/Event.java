package duke.task;

public class Event extends Task {

    private String at;

    /**
     * Constructs an Event type of task.
     *
     * @param description String that contains the description of the event.
     * @param at String that contains the at timing of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "event";
    }

    /**
     * Returns the at timing of the event task.
     *
     * @return String that contains the at timing of the event.
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns a formatted String that shows the task is an data.Event and its description and at timing.
     *
     * @return formatted String that shows the task is an data.Event, contains description and at timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
