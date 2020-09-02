import java.util.ArrayList;

public class TaskHelper {


    public TaskHelper() {

    }

    public static void addTaskToList(String taskType, String taskDetails, ArrayList<Task> taskList) {
        printDividerLine();
        System.out.println("Got it. I've added this task: ");
        switch(taskType) {
        case "todo":
            Task newToDo = new ToDo(taskDetails);
            taskList.add(newToDo);
            System.out.println(newToDo.toString());
            break;
        case "deadline":
            String[] deadlineDetails = taskDetails.split("/by", 2);
            String deadlineDescription = deadlineDetails[0].trim();
            String deadlineBy = deadlineDetails[1].trim();
            Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
            taskList.add(newDeadline);
            System.out.println(newDeadline.toString());
            break;
        case "event":
            String[] eventDetails = taskDetails.split("/at", 2);
            String eventDescription = eventDetails[0].trim();
            String eventAt = eventDetails[1].trim();
            Task newEvent = new Event(eventDescription, eventAt);
            taskList.add(newEvent);
            System.out.println(newEvent.toString());
            break;
        }
        if (Task.getNumberOfTasks() > 1) {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " task in the list.");
        }
        printDividerLine();
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        int taskCount;
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++) {
            System.out.println(taskCount + 1 + ". " + taskList.get(taskCount).toString());
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
            System.out.println(currentTask.toString());
        } else {
            System.out.println("Invalid task number to mark as done!");
        }
        printDividerLine();

    }

    public static void printDoneErrorMessage() {
        printDividerLine();
        System.out.println("No task to set as done!");
        printDividerLine();

    }


}
