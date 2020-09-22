import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private static Boolean isLoadingFromFile;

    public TaskList () {
        TaskList.taskList = new ArrayList<>();
    }

    public static void setIsLoadingFromFile(Boolean booleanValue) {
        TaskList.isLoadingFromFile = booleanValue;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }


    public static void addTodo(String taskDetails) {
        Task newTodo = new Todo(taskDetails);
        taskList.add(newTodo);

        if (!isLoadingFromFile) {
            Ui.printDividerLine();
            System.out.println("Got it! I've added this todo: ");
            System.out.println(newTodo);
            Ui.printDividerLine();
        }
    }

    public static void addDeadline(String taskDetails) throws MissingInformationException {
        String[] deadlineDetails = taskDetails.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new MissingInformationException("The deadline 'by' date/time was undetected!");
        }
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(newDeadline);

        if (!isLoadingFromFile) {
            Ui.printDividerLine();
            System.out.println("Got it! I've added this deadline: ");
            System.out.println(newDeadline);
            Ui.printDividerLine();
        }
    }

    public static void addEvent(String taskDetails) throws MissingInformationException{
        String[] eventDetails = taskDetails.split("/at", 2);
        if (eventDetails.length < 2) {
            throw new MissingInformationException("The event 'at' date/time was undetected!");
        }
        String eventDescription = eventDetails[0].trim();
        String eventAt = eventDetails[1].trim();
        Task newEvent = new Event(eventDescription, eventAt);
        taskList.add(newEvent);

        if (!isLoadingFromFile) {
            Ui.printDividerLine();
            System.out.println("Got it! I've added this event: ");
            System.out.println(newEvent);
            Ui.printDividerLine();
        }
    }

    public static void addTaskToList(String[] taskCommand) throws MissingInformationException {
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
                Ui.printExceptionMessage(e.toString());
            }
            break;
        case "event":
            try {
                addEvent(taskDetails);
            } catch (MissingInformationException e) {
                Ui.printExceptionMessage(e.toString());
            }
            break;
        }
        if (!isLoadingFromFile) {
            Ui.printTaskNumberMessage();
            Ui.printDividerLine();
        }

    }

    public static void printTaskList() {
        int taskCount;
        Ui.printDividerLine();
        if (Task.getNumberOfTasks() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++) {
                System.out.println(taskCount + 1 + ". " + taskList.get(taskCount));
            }
        } else {
            System.out.println("There are no tasks in your list!");
        }
        Ui.printDividerLine();
    }

    public static void setTaskAsDone(int taskNumber) {
        Ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to mark as done!");
        }
        Ui.printDividerLine();

    }

    public static void deleteTask(int taskNumber) {
        Ui.printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            Task.decreaseNumberOfTasks();
            System.out.println("Got it! I've deleted this task:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to delete!");
        }
        Ui.printTaskNumberMessage();
        Ui.printDividerLine();
    }


    public static void resetTaskList(ArrayList<Task> taskList) {
        taskList.clear();
        Task.resetNumberOfTasks();
    }
}
