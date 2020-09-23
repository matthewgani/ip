import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Boolean isLoadingFromFile;
    private Ui ui;

    public TaskList () {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    public void setIsLoadingFromFile(Boolean booleanValue) {
        isLoadingFromFile = booleanValue;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    public void addTodo(String taskDetails) {
        Task newTodo = new Todo(taskDetails);
        taskList.add(newTodo);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this todo: ");
            System.out.println(newTodo);
            ui.printDividerLine();
        }
    }

    public void addDeadline(String taskDetails) throws MissingInformationException {
        String[] deadlineDetails = taskDetails.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new MissingInformationException("The deadline 'by' date/time was undetected!");
        }
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(newDeadline);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this deadline: ");
            System.out.println(newDeadline);
            ui.printDividerLine();
        }
    }

    public void addEvent(String taskDetails) throws MissingInformationException{
        String[] eventDetails = taskDetails.split("/at", 2);
        if (eventDetails.length < 2) {
            throw new MissingInformationException("The event 'at' date/time was undetected!");
        }
        String eventDescription = eventDetails[0].trim();
        String eventAt = eventDetails[1].trim();
        Task newEvent = new Event(eventDescription, eventAt);
        taskList.add(newEvent);

        if (!isLoadingFromFile) {
            ui.printDividerLine();
            System.out.println("Got it! I've added this event: ");
            System.out.println(newEvent);
            ui.printDividerLine();
        }
    }

    public void addTaskToList(String[] taskCommand) throws MissingInformationException {
        if (taskCommand.length < 2) {
            throw new MissingInformationException("The details of a " + taskCommand[0] + " cannot be empty!");
        }
        String taskType = taskCommand[0];
        String taskDetails = taskCommand[1];
        switch (taskType) {
        case "todo":
            addTodo(taskDetails);
            break;
        case "deadline":
            try {
                addDeadline(taskDetails);
            } catch (MissingInformationException e) {
                ui.printExceptionMessage(e.toString());
            }
            break;
        case "event":
            try {
                addEvent(taskDetails);
            } catch (MissingInformationException e) {
                ui.printExceptionMessage(e.toString());
            }
            break;
        }
        if (!isLoadingFromFile) {
            ui.printTaskNumberMessage();
            ui.printDividerLine();
        }

    }

    public void printTaskList() {
        int taskCount;
        ui.printDividerLine();
        if (Task.getNumberOfTasks() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++) {
                System.out.println(taskCount + 1 + ". " + taskList.get(taskCount));
            }
        } else {
            System.out.println("There are no tasks in your list!");
        }
        ui.printDividerLine();
    }

    public void setTaskAsDone(int taskNumber) {
        ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to mark as done!");
        }
        ui.printDividerLine();

    }

    public void deleteTask(int taskNumber) {
        ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            Task.decreaseNumberOfTasks();
            System.out.println("Got it! I've deleted this task:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to delete!");
        }
        ui.printTaskNumberMessage();
        ui.printDividerLine();
    }


    public void resetTaskList(ArrayList<Task> taskList) {
        taskList.clear();
        Task.resetNumberOfTasks();
    }

}