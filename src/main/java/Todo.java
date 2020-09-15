public class Todo extends Task {


    public Todo(String description) {
        super(description);
        this.taskType = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
