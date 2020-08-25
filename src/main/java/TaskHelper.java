import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskHelper {


    public TaskHelper() {

    }

    public static void addTaskToList(String taskDescription, Task[] taskList) {
        printDividerLine();
        System.out.println("added: " + taskDescription);
        Task currentTask = new Task(taskDescription);
        taskList[Task.getNumberOfTasks() - 1] = currentTask;
        printDividerLine();
    }

    public static void printTaskList(Task[] taskList) {
        int taskCount = 0;
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++ ) {
            System.out.println(taskCount + 1 + ".[" + taskList[taskCount].getStatusIcon()
                    + "] " + taskList[taskCount].getDescription());

        }
        printDividerLine();

    }

    public static void printTaskDone(Task currentTask) {
        printDividerLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + currentTask.getStatusIcon() + "]" + " " + currentTask.getDescription());
        printDividerLine();

    }

    private static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }

    public static void setTaskAsDone(String userResponse, Task[] taskList) {

        Pattern donePattern = Pattern.compile("\\b(done)[\\h]\\d");
        Matcher doneMatcher = donePattern.matcher(userResponse);

        if (doneMatcher.find()) {
            // if userResponse contains "done  x", where x is positive integer

            String[] splitDoneResponse = doneMatcher.group(0).split(" ");
            int taskNumber = Integer.parseInt(splitDoneResponse[1]);
            if (taskNumber <= Task.getNumberOfTasks() && taskNumber > 0) {
                // splitDoneResponse[1] should be a integer
                // we check if it is a valid task number to mark as done
                Task currentTask = taskList[taskNumber - 1];
                currentTask.markAsDone();
                TaskHelper.printTaskDone(currentTask);


            } else {
                printDividerLine();
                System.out.println("Invalid task number to mark as done!");
                printDividerLine();
            }

        } else {
            printDividerLine();
            System.out.println("No task number detected!");
            printDividerLine();
        }

    }


}
