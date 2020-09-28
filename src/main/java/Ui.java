import java.util.ArrayList;

public class Ui {

    /**
     * Prints file scanner IO error message.
     */
    public void printFileScannerIOError() {
        printDividerLine();
        System.out.println("IO error, Unable to find dukeMemory file!");
        printDividerLine();
    }

    /**
     * Prints error message while reading file.
     */
    public void printReadError() {
        printDividerLine();
        System.out.println("Problems with reading file!");
        printDividerLine();
    }

    /**
     * Prints invalid command message.
     */
    public void printInvalidCommand() {
        printDividerLine();
        System.out.println("Sorry, I could not understand your command!");
        printDividerLine();
    }

    /**
     * Prints message when task number cannot be parsed properly.
     */
    public void printTaskNumberParseError() {
        printDividerLine();
        System.out.println("Unable to obtain task number!");
        printDividerLine();
    }

    /**
     * Prints message when task number is not entered.
     */
    public void printTaskNumberNotFound() {
        printDividerLine();
        System.out.println("No task number detected!");
        printDividerLine();
    }

    /**
     * Prints total number of tasks in the task list.
     */
    public void printTaskNumberMessage() {
        System.out.println("Now you have " + Task.getNumberOfTasks() + " task(s) in your list!");
    }

    /**
     * Prints exception message.
     *
     * @param exceptionMessage String of warning message from various exceptions.
     */
    public void printExceptionMessage(String exceptionMessage) {
        printDividerLine();
        System.out.println(exceptionMessage);
        printDividerLine();
    }

    /**
     * Prints the divider line between messages.
     */
    public void printDividerLine() {
        System.out.println("___________________________________ \n");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printDividerLine();
        System.out.println("Hello! I'm Duke! \n " + logo);
        System.out.println("What can I do for you?");
        printDividerLine();

    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printDividerLine();
    }


    /**
     * Prints the ArrayList of tasks.
     *
     * @param taskList ArrayList of tasks to be printed out.
     */
    public void printTaskList(ArrayList<Task> taskList) {
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
    }

    public void printKeywordMissingError() {
        printDividerLine();
        System.out.println("There was no keyword input after the find command!");
        printDividerLine();
    }
}

