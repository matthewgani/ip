import java.util.ArrayList;

public class TaskHelper {


    public TaskHelper() {
    }

    public static void printTaskNumberMessage() {
        if (Task.getNumberOfTasks() > 1) {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " task in the list.");
        }
    }

    public static void addTodo(String taskDetails, ArrayList<Task> taskList) {
        Task newTodo = new Todo(taskDetails);
        taskList.add(newTodo);
        System.out.println(newTodo);
    }

    public static void addDeadline(String taskDetails, ArrayList<Task> taskList) {
        String[] deadlineDetails = taskDetails.split("/by", 2);
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(newDeadline);
        System.out.println(newDeadline);
    }

    public static void addEvent(String taskDetails, ArrayList<Task> taskList) {
        String[] eventDetails = taskDetails.split("/at", 2);
        // try catch for at
        String eventDescription = eventDetails[0].trim();
        String eventAt = eventDetails[1].trim();
        Task newEvent = new Event(eventDescription, eventAt);
        taskList.add(newEvent);
        System.out.println(newEvent);
    }

    public static void addTaskToList(String[] taskCommand, ArrayList<Task> taskList) throws MissingInformationException {
        if(taskCommand.length < 2) {
            throw new MissingInformationException("The details of a " + taskCommand[0] + " cannot be empty!");
        }

        String taskType = taskCommand[0];
        String taskDetails = taskCommand[1];
        printDividerLine();

        System.out.println("Got it! I've added this task: ");
        switch(taskType) {
        case "todo":
            addTodo(taskDetails, taskList);
            break;
        case "deadline":
            addDeadline(taskDetails, taskList);
            break;
        case "event":
            addEvent(taskDetails, taskList);
            break;
        }
        printTaskNumberMessage();
        printDividerLine();
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        int taskCount;
        printDividerLine();
        if(Task.getNumberOfTasks() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++) {
                System.out.println(taskCount + 1 + ". " + taskList.get(taskCount));
            }
        } else {
            System.out.println("There are no tasks in your list!");
        }
        printDividerLine();

    }

    private static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }

    public static void setTaskAsDone(int taskNumber, ArrayList<Task> taskList) {
        printDividerLine();
        if(taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
            Task currentTask = taskList.get(taskNumber - 1);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currentTask);
        } else {
            System.out.println("Invalid task number to mark as done!");
        }
        printDividerLine();

    }

    public static int getTaskNumberToSetAsDone(String command) {
        int taskNumber;
        String[] splitCommand = command.split(" ", 2);
        taskNumber = Integer.parseInt(splitCommand[1]);
        return taskNumber;
    }

    public static String[] splitTaskCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }

    public static void printInvalidCommand() {
        printDividerLine();
        System.out.println("Sorry, I could not understand your command!");
        printDividerLine();
    }

}
