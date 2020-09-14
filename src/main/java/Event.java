public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "event";
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}