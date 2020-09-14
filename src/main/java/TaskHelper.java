import java.util.ArrayList;

public class TaskHelper {

    public static void addTodo(String taskDetails, ArrayList<Task> taskList, Boolean loadingFromFile) {
        Task newTodo = new Todo(taskDetails);
        taskList.add(newTodo);
        if (!loadingFromFile) {
            printDividerLine();
            System.out.println("Got it! I've added this todo: ");
            System.out.println(newTodo);
            printDividerLine();
        }
    }

    public static void addDeadline(String taskDetails, ArrayList<Task> taskList, Boolean loadingFromFile) throws MissingInformationException {
        String[] deadlineDetails = taskDetails.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new MissingInformationException("The deadline 'by' date/time was undetected!");
        }
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(newDeadline);
        if (!loadingFromFile) {
            printDividerLine();
            System.out.println("Got it! I've added this deadline: ");
            System.out.println(newDeadline);
            printDividerLine();
        }
    }

    public static void addEvent(String taskDetails, ArrayList<Task> taskList, Boolean loadingFromFile) throws MissingInformationException{
        String[] eventDetails = taskDetails.split("/at", 2);
        if (eventDetails.length < 2) {
            throw new MissingInformationException("The event 'at' date/time was undetected!");
        }
        String eventDescription = eventDetails[0].trim();
        String eventAt = eventDetails[1].trim();
        Task newEvent = new Event(eventDescription, eventAt);
        taskList.add(newEvent);
        if (!loadingFromFile) {
            printDividerLine();
            System.out.println("Got it! I've added this event: ");
            System.out.println(newEvent);
            printDividerLine();
        }
    }

    public static void addTaskToList(String[] taskCommand, ArrayList<Task> taskList, Boolean loadingFromFile) throws MissingInformationException {
        if (taskCommand.length < 2) {
            throw new MissingInformationException("The details of a " + taskCommand[0] + " cannot be empty!");
        }

        String taskType = taskCommand[0];
        String taskDetails = taskCommand[1];

        switch (taskType) {
        case "todo":
            addTodo(taskDetails, taskList, loadingFromFile);
            break;
        case "deadline":
            try {
                addDeadline(taskDetails, taskList, loadingFromFile);
            } catch (MissingInformationException e) {
                printExceptionMessage(e.toString());
            }
            break;
        case "event":
            try {
                addEvent(taskDetails, taskList, loadingFromFile);
            } catch (MissingInformationException e) {
                printExceptionMessage(e.toString());
            }
            break;
        }
        if (!loadingFromFile) {
            printTaskNumberMessage();
        }
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        int taskCount;
        printDividerLine();
        if (Task.getNumberOfTasks() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++) {
                System.out.println(taskCount + 1 + ". " + taskList.get(taskCount));
            }
        } else {
            System.out.println("There are no tasks in your list!");
        }
        printDividerLine();

    }

    public static void setTaskAsDone(int taskNumber, ArrayList<Task> taskList) {
        printDividerLine();
        if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
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

    public static void printTaskNumberParseError() {
        printDividerLine();
        System.out.println("Unable to obtain task number!");
        printDividerLine();
    }

    public static void printTaskNumberNotFound() {
        printDividerLine();
        System.out.println("No task number detected!");
        printDividerLine();
    }

    public static void printTaskNumberMessage() {
        System.out.println("Now you have " + Task.getNumberOfTasks() + " task(s) in your list!");
        printDividerLine();
    }

    public static void printExceptionMessage(String exceptionMessage) {
        printDividerLine();
        System.out.println(exceptionMessage);
        printDividerLine();
    }

    private static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }


}
