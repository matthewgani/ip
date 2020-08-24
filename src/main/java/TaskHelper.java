public class TaskHelper {


    public TaskHelper() {

    }

    public static void addTaskToList(String taskDescription, String[] taskList) {
        printDividerLine();
        System.out.println("added: " + taskDescription);
        Task task = new Task(taskDescription);
        taskList[Task.getNumberOfTasks() - 1] = task.getTaskDescription();
        printDividerLine();
    }

    public static void printTaskList(String[] taskList) {
        int taskCount = 0;
        printDividerLine();
        for (String taskDescription : taskList) {
            if(taskDescription != null) {
                System.out.println(taskCount + 1 + ". " + taskDescription);
                taskCount++;
            } else {
                // this occurs when it reaches a null String in taskList
                break;
            }

        }
        printDividerLine();

    }

    private static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }


}
