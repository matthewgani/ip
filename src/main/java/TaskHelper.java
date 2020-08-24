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
        for (taskCount = 0; taskCount < Task.getNumberOfTasks(); taskCount++ ) {
            System.out.println(taskCount + 1 + ". " + taskList[taskCount].getTaskDescription());

        }
        printDividerLine();

    }

    private static void printDividerLine() {
        System.out.println("___________________________________ \n");
    }


}
